package com.hci.humbercraft.portal;

import java.util.Random;
import java.util.function.Function;

import javax.annotation.Nullable;

import com.google.common.cache.LoadingCache;
import com.hci.humbercraft.HumberCraft;
import com.hci.humbercraft.init.BlockInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.client.audio.SoundList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.CachedBlockInfo;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.GameRules;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import com.google.common.cache.LoadingCache;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.CachedBlockInfo;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.GameRules;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PortalBlockInit {
	
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<Block>(ForgeRegistries.BLOCKS,
			HumberCraft.MOD_ID);

	public static final RegistryObject<PortalBlock> PORTAL = BLOCKS.register("portal",
			() -> new PortalBlock(Block.Properties.create(Material.PORTAL)));

	public static class PortalBlock extends Block {

		public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
		protected static final VoxelShape X_AABB = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
		protected static final VoxelShape Z_AABB = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);
		private Object random;
		private Object worldIn;

		public PortalBlock(Properties properties) {
			super(properties);
			this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, Direction.Axis.X));
		}

		@Override
		public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
			switch ((Direction.Axis) state.get(AXIS)) {
			case Z:
				return Z_AABB;
			case X:
			default:
				return X_AABB;
			}
		}

		public boolean trySpawnPortal(IWorld worldIn, BlockPos pos) {
			PortalBlock.Size modportalblock$size = this.isPortal(worldIn, pos);
			if (modportalblock$size != null) {
				modportalblock$size.placePortalBlocks();
				return true;
			} else {
				return false;
			}
		}

		@Nullable
		public PortalBlock.Size isPortal(IWorld worldIn, BlockPos pos) {
			PortalBlock.Size modportalblock$size = new PortalBlock.Size(worldIn, pos, Direction.Axis.X);
			if (modportalblock$size.isValid() && modportalblock$size.portalBlockCount == 0) {
				return modportalblock$size;
			} else {
				PortalBlock.Size modportalblock$size1 = new PortalBlock.Size(worldIn, pos, Direction.Axis.Z);
				return modportalblock$size1.isValid() && modportalblock$size1.portalBlockCount == 0
						? modportalblock$size1
						: null;
			}
		}

		@SuppressWarnings("deprecation")
		@Override
		public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState,
				IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
			Direction.Axis direction$axis = facing.getAxis();
			Direction.Axis direction$axis1 = stateIn.get(AXIS);
			boolean flag = direction$axis1 != direction$axis && direction$axis.isHorizontal();
			return !flag && facingState.getBlock() != this
					&& !(new PortalBlock.Size(worldIn, currentPos, direction$axis1)).func_208508_f()
							? Blocks.AIR.getDefaultState()
							: super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		}

		@Override
		public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
			Random random = new Random(worldIn.getSeed());
			if (!entityIn.isPassenger() && !entityIn.isBeingRidden() && entityIn.isNonBoss()) {
				if (entityIn.dimension != DimensionType.byName(HumberCraft.JAVA_DIMENSION_TYPE)) {
					teleportToDimension(entityIn, DimensionType.byName(HumberCraft.JAVA_DIMENSION_TYPE), pos, worldIn);
				} 
				else {
					teleportToDimension(entityIn, DimensionType.byName(new ResourceLocation("minecraft", "overworld")),pos, worldIn);
				}

				new PortalBlock.Size(worldIn, pos, Axis.X).placePortalBlocks();
				
				
				
				if (entityIn.timeUntilPortal > 0) {
					entityIn.timeUntilPortal = entityIn.getPortalCooldown();
				} else {
					
				}
				
				
			}
		}

		private void teleportToDimension(Entity entityIn, DimensionType dimension, BlockPos pos, World worldIn) {
			entityIn.changeDimension(dimension, new ITeleporter() {
				@Override
				public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw,
						Function<Boolean, Entity> repositionEntity) {
					entity = repositionEntity.apply(false);
					entity.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
					makePortal(entityIn, destWorld);
					return entity;
				}
			});
			
		}

		@Override
		@Deprecated
		public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
			if (worldIn.dimension.isSurfaceWorld() && worldIn.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING)
					&& rand.nextInt(2000) < worldIn.getDifficulty().getId()) {
				while (worldIn.getBlockState(pos).getBlock() == this) {
					pos = pos.down();
				}

				if (worldIn.getBlockState(pos).canEntitySpawn(worldIn, pos, EntityType.ZOMBIE_PIGMAN)) {
					Entity entity = EntityType.ZOMBIE_PIGMAN.spawn(worldIn, (CompoundNBT) null, (ITextComponent) null,
							(PlayerEntity) null, pos.up(), SpawnReason.STRUCTURE, false, false);
					if (entity != null) {
						entity.timeUntilPortal = entity.getPortalCooldown();
					}
				}
			}
		}

		@OnlyIn(Dist.CLIENT)
		public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
			if (rand.nextInt(100) == 0) {
				//worldIn.playSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D,
						//SoundList.AMBIENT.get(), SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
			}

			for (int i = 0; i < 4; ++i) {
				double d0 = (double) pos.getX() + (double) rand.nextFloat();
				double d1 = (double) pos.getY() + (double) rand.nextFloat();
				double d2 = (double) pos.getZ() + (double) rand.nextFloat();
				double d3 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
				double d4 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
				double d5 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
				int j = rand.nextInt(2) * 2 - 1;
				if (worldIn.getBlockState(pos.west()).getBlock() != this
						&& worldIn.getBlockState(pos.east()).getBlock() != this) {
					d0 = (double) pos.getX() + 0.5D + 0.25D * (double) j;
					d3 = (double) (rand.nextFloat() * 2.0F * (float) j);
				} else {
					d2 = (double) pos.getZ() + 0.5D + 0.25D * (double) j;
					d5 = (double) (rand.nextFloat() * 2.0F * (float) j);
				}

				//worldIn.addParticle(new ColouredParticleData((float) d0, (float) d1, (float) d2, 1.0F), d0, d1, d2, d3,d4, d5);
			}

		}

		public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
			return ItemStack.EMPTY;
		}

		@Deprecated
		public BlockState rotate(BlockState state, Rotation rot) {
			switch (rot) {
			case COUNTERCLOCKWISE_90:
			case CLOCKWISE_90:
				switch ((Direction.Axis) state.get(AXIS)) {
				case Z:
					return state.with(AXIS, Direction.Axis.X);
				case X:
					return state.with(AXIS, Direction.Axis.Z);
				default:
					return state;
				}
			default:
				return state;
			}
		}

		@Override
		protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
			builder.add(AXIS);
		}

		@SuppressWarnings("deprecation")
		public static BlockPattern.PatternHelper createPatternHelper(IWorld world, BlockPos pos) {
			Direction.Axis direction$axis = Direction.Axis.Z;
			PortalBlock.Size modportalblock$size = new PortalBlock.Size(world, pos, Direction.Axis.X);
			LoadingCache<BlockPos, CachedBlockInfo> loadingcache = BlockPattern.createLoadingCache(world, true);
			if (!modportalblock$size.isValid()) {
				direction$axis = Direction.Axis.X;
				modportalblock$size = new PortalBlock.Size(world, pos, Direction.Axis.Z);
			}

			if (!modportalblock$size.isValid()) {
				return new BlockPattern.PatternHelper(pos, Direction.NORTH, Direction.UP, loadingcache, 1, 1, 1);
			} else {
				int[] aint = new int[Direction.AxisDirection.values().length];
				Direction direction = modportalblock$size.rightDir.rotateYCCW();
				BlockPos blockpos = modportalblock$size.bottomLeft.up(modportalblock$size.getHeight() - 1);

				for (Direction.AxisDirection direction$axisdirection : Direction.AxisDirection.values()) {
					BlockPattern.PatternHelper blockpattern$patternhelper = new BlockPattern.PatternHelper(
							direction.getAxisDirection() == direction$axisdirection ? blockpos
									: blockpos.offset(modportalblock$size.rightDir, modportalblock$size.getWidth() - 1),
							Direction.getFacingFromAxis(direction$axisdirection, direction$axis), Direction.UP,
							loadingcache, modportalblock$size.getWidth(), modportalblock$size.getHeight(), 1);

					for (int i = 0; i < modportalblock$size.getWidth(); ++i) {
						for (int j = 0; j < modportalblock$size.getHeight(); ++j) {
							CachedBlockInfo cachedblockinfo = blockpattern$patternhelper.translateOffset(i, j, 1);
							if (!cachedblockinfo.getBlockState().isAir()) {
								++aint[direction$axisdirection.ordinal()];
							}
						}
					}
				}

				Direction.AxisDirection direction$axisdirection1 = Direction.AxisDirection.POSITIVE;

				for (Direction.AxisDirection direction$axisdirection2 : Direction.AxisDirection.values()) {
					if (aint[direction$axisdirection2.ordinal()] < aint[direction$axisdirection1.ordinal()]) {
						direction$axisdirection1 = direction$axisdirection2;
					}
				}

				return new BlockPattern.PatternHelper(
						direction.getAxisDirection() == direction$axisdirection1 ? blockpos
								: blockpos.offset(modportalblock$size.rightDir, modportalblock$size.getWidth() - 1),
						Direction.getFacingFromAxis(direction$axisdirection1, direction$axis), Direction.UP,
						loadingcache, modportalblock$size.getWidth(), modportalblock$size.getHeight(), 1);
			}
		}
		
		public boolean makePortal(Entity entityIn, ServerWorld worldIn) {
			  Random random = new Random(worldIn.getSeed());
		      int i = 16;
		      double d0 = -1.0D;
		      int j = MathHelper.floor(entityIn.getPosX());
		      int k = MathHelper.floor(entityIn.getPosY());
		      int l = MathHelper.floor(entityIn.getPosZ());
		      int i1 = j;
		      int j1 = k;
		      int k1 = l;
		      int l1 = 0;
		      int i2 = random.nextInt(4);
		      BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

		      for(int j2 = j - 16; j2 <= j + 16; ++j2) {
		         double d1 = (double)j2 + 0.5D - entityIn.getPosX();

		         for(int l2 = l - 16; l2 <= l + 16; ++l2) {
		            double d2 = (double)l2 + 0.5D - entityIn.getPosZ();

		            label276:
		            for(int j3 = (worldIn).getActualHeight() - 1; j3 >= 0; --j3) {
		               if ((worldIn).isAirBlock(blockpos$mutable.setPos(j2, j3, l2))) {
		                  while(j3 > 0 && (worldIn).isAirBlock(blockpos$mutable.setPos(j2, j3 - 1, l2))) {
		                     --j3;
		                  }

		                  for(int k3 = i2; k3 < i2 + 4; ++k3) {
		                     int l3 = k3 % 2;
		                     int i4 = 1 - l3;
		                     if (k3 % 4 >= 2) {
		                        l3 = -l3;
		                        i4 = -i4;
		                     }

		                     for(int j4 = 0; j4 < 3; ++j4) {
		                        for(int k4 = 0; k4 < 4; ++k4) {
		                           for(int l4 = -1; l4 < 4; ++l4) {
		                              int i5 = j2 + (k4 - 1) * l3 + j4 * i4;
		                              int j5 = j3 + l4;
		                              int k5 = l2 + (k4 - 1) * i4 - j4 * l3;
		                              blockpos$mutable.setPos(i5, j5, k5);
		                              if (l4 < 0 && !(worldIn).getBlockState(blockpos$mutable).getMaterial().isSolid() || l4 >= 0 && !(worldIn).isAirBlock(blockpos$mutable)) {
		                                 continue label276;
		                              }
		                           }
		                        }
		                     }

		                     double d5 = (double)j3 + 0.5D - entityIn.getPosY();
		                     double d7 = d1 * d1 + d5 * d5 + d2 * d2;
		                     if (d0 < 0.0D || d7 < d0) {
		                        d0 = d7;
		                        i1 = j2;
		                        j1 = j3;
		                        k1 = l2;
		                        l1 = k3 % 4;
		                     }
		                  }
		               }
		            }
		         }
		      }
		      

		      if (d0 < 0.0D) {
		         for(int l5 = j - 16; l5 <= j + 16; ++l5) {
		            double d3 = (double)l5 + 0.5D - entityIn.getPosX();

		            for(int j6 = l - 16; j6 <= l + 16; ++j6) {
		               double d4 = (double)j6 + 0.5D - entityIn.getPosZ();

		               label214:
		               for(int i7 = (worldIn).getActualHeight() - 1; i7 >= 0; --i7) {
		                  if ((worldIn).isAirBlock(blockpos$mutable.setPos(l5, i7, j6))) {
		                     while(i7 > 0 && (worldIn).isAirBlock(blockpos$mutable.setPos(l5, i7 - 1, j6))) {
		                        --i7;
		                     }

		                     for(int l7 = i2; l7 < i2 + 2; ++l7) {
		                        int l8 = l7 % 2;
		                        int k9 = 1 - l8;

		                        for(int i10 = 0; i10 < 4; ++i10) {
		                           for(int k10 = -1; k10 < 4; ++k10) {
		                              int i11 = l5 + (i10 - 1) * l8;
		                              int j11 = i7 + k10;
		                              int k11 = j6 + (i10 - 1) * k9;
		                              blockpos$mutable.setPos(i11, j11, k11);
		                              if (k10 < 0 && !(worldIn).getBlockState(blockpos$mutable).getMaterial().isSolid() || k10 >= 0 && !(worldIn).isAirBlock(blockpos$mutable)) {
		                                 continue label214;
		                              }
		                           }
		                        }

		                        double d6 = (double)i7 + 0.5D - entityIn.getPosY();
		                        double d8 = d3 * d3 + d6 * d6 + d4 * d4;
		                        if (d0 < 0.0D || d8 < d0) {
		                           d0 = d8;
		                           i1 = l5;
		                           j1 = i7;
		                           k1 = j6;
		                           l1 = l7 % 2;
		                        }
		                     }
		                  }
		               }
		            }
		         }
		      }

		      int i6 = i1;
		      int k2 = j1;
		      int k6 = k1;
		      int l6 = l1 % 2;
		      int i3 = 1 - l6;
		      if (l1 % 4 >= 2) {
		         l6 = -l6;
		         i3 = -i3;
		      }

		      if (d0 < 0.0D) {
		         j1 = MathHelper.clamp(j1, 70, (worldIn).getActualHeight() - 10);
		         k2 = j1;

		         for(int j7 = -1; j7 <= 1; ++j7) {
		            for(int i8 = 1; i8 < 3; ++i8) {
		               for(int i9 = -1; i9 < 3; ++i9) {
		                  int l9 = i6 + (i8 - 1) * l6 + j7 * i3;
		                  int j10 = k2 + i9;
		                  int l10 = k6 + (i8 - 1) * i3 - j7 * l6;
		                  boolean flag = i9 < 0;
		                  blockpos$mutable.setPos(l9, j10, l10);
		                  (worldIn).setBlockState(blockpos$mutable, flag ? BlockInit.JAVIUM_BLOCK.get().getDefaultState() : Blocks.AIR.getDefaultState());
		               }
		            }
		         }
		      }

		      for(int k7 = -1; k7 < 3; ++k7) {
		         for(int j8 = -1; j8 < 4; ++j8) {
		            if (k7 == -1 || k7 == 2 || j8 == -1 || j8 == 3) {
		               blockpos$mutable.setPos(i6 + k7 * l6, k2 + j8, k6 + k7 * i3);
		               (worldIn).setBlockState(blockpos$mutable, BlockInit.JAVIUM_BLOCK.get().getDefaultState(), 3);
		            }
		         }
		      }

		      BlockState blockstate = PortalBlockInit.PORTAL.get().getDefaultState().with(PortalBlock.AXIS, l6 == 0 ? Direction.Axis.Z : Direction.Axis.X);

		      for(int k8 = 0; k8 < 2; ++k8) {
		         for(int j9 = 0; j9 < 3; ++j9) {
		            blockpos$mutable.setPos(i6 + k8 * l6, k2 + j9, k6 + k8 * i3);
		            (worldIn).setBlockState(blockpos$mutable, blockstate, 18);
		         }
		      }

		      return true;
		   }
		
		public static class Size {
			private final IWorld world;
			private final Direction.Axis axis;
			private final Direction rightDir;
			private final Direction leftDir;
			private int portalBlockCount;
			@Nullable
			private BlockPos bottomLeft;
			private int height;
			private int width;

			public Size(IWorld worldIn, BlockPos pos, Direction.Axis axisIn) {
				this.world = worldIn;
				this.axis = axisIn;
				if (axisIn == Direction.Axis.X) {
					this.leftDir = Direction.EAST;
					this.rightDir = Direction.WEST;
				} else {
					this.leftDir = Direction.NORTH;
					this.rightDir = Direction.SOUTH;
				}

				for (BlockPos blockpos = pos; pos.getY() > blockpos.getY() - 21 && pos.getY() > 0
						&& this.func_196900_a(worldIn.getBlockState(pos.down())); pos = pos.down()) {
					;
				}

				int i = this.getDistanceUntilEdge(pos, this.leftDir) - 1;
				if (i >= 0) {
					this.bottomLeft = pos.offset(this.leftDir, i);
					this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);
					if (this.width < 2 || this.width > 21) {
						this.bottomLeft = null;
						this.width = 0;
					}
				}

				if (this.bottomLeft != null) {
					this.height = this.calculatePortalHeight();
				}

			}

			protected int getDistanceUntilEdge(BlockPos pos, Direction directionIn) {
				int i;
				for (i = 0; i < 22; ++i) {
					BlockPos blockpos = pos.offset(directionIn, i);
					if (!this.func_196900_a(this.world.getBlockState(blockpos))
							|| !this.world.getBlockState(blockpos.down()).equals(BlockInit.JAVIUM_BLOCK.get().getDefaultState())) {
						break;
					}
				}

				BlockPos framePos = pos.offset(directionIn, i);
				return this.world.getBlockState(framePos).equals(BlockInit.JAVIUM_BLOCK.get().getDefaultState()) ? i : 0;
			}

			public int getHeight() {
				return this.height;
			}

			public int getWidth() {
				return this.width;
			}

			protected int calculatePortalHeight() {
				label56: for (this.height = 0; this.height < 21; ++this.height) {
					for (int i = 0; i < this.width; ++i) {
						BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i).up(this.height);
						BlockState blockstate = this.world.getBlockState(blockpos);
						if (!this.func_196900_a(blockstate)) {
							break label56;
						}

						Block block = blockstate.getBlock();
						if (block == PortalBlockInit.PORTAL.get()) {
							++this.portalBlockCount;
						}

						if (i == 0) {
							BlockPos framePos = blockpos.offset(this.leftDir);
							if (!this.world.getBlockState(framePos).equals(BlockInit.JAVIUM_BLOCK.get().getDefaultState())) {
								break label56;
							}
						} else if (i == this.width - 1) {
							BlockPos framePos = blockpos.offset(this.rightDir);
							if (!this.world.getBlockState(framePos).equals(BlockInit.JAVIUM_BLOCK.get().getDefaultState())) {
								break label56;
							}
						}
					}
				}

				for (int j = 0; j < this.width; ++j) {
					BlockPos framePos = this.bottomLeft.offset(this.rightDir, j).up(this.height);
					if (!this.world.getBlockState(framePos).equals(BlockInit.JAVIUM_BLOCK.get().getDefaultState())) {
						this.height = 0;
						break;
					}
				}

				if (this.height <= 21 && this.height >= 3) {
					return this.height;
				} else {
					this.bottomLeft = null;
					this.width = 0;
					this.height = 0;
					return 0;
				}
			}

			@SuppressWarnings("deprecation")
			protected boolean func_196900_a(BlockState pos) {
				Block block = pos.getBlock();
				return pos.isAir() || block == Blocks.FIRE || block == PortalBlockInit.PORTAL.get();
			}

			public boolean isValid() {
				return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3
						&& this.height <= 21;
			}

			public void placePortalBlocks() {
				for (int i = 0; i < this.width; ++i) {
					BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i);

					for (int j = 0; j < this.height; ++j) {
						this.world.setBlockState(blockpos.up(j),
								PortalBlockInit.PORTAL.get().getDefaultState().with(NetherPortalBlock.AXIS, this.axis), 18);
					}
				}

			}

			private boolean func_196899_f() {
				return this.portalBlockCount >= this.width * this.height;
			}

			public boolean func_208508_f() {
				return this.isValid() && this.func_196899_f();
			}
			
		}
	}
}