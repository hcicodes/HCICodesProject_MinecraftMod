package com.hci.humbercraft.blocks;

import java.util.Random;
import java.util.function.Function;

import com.hci.humbercraft.HumberCraft;
import com.hci.humbercraft.init.BlockInit;
import com.hci.humbercraft.init.ModTileEntityTypes;
import com.hci.humbercraft.tileentity.JavaDimensionTeleporterTileEntity;
import com.hci.humbercraft.tileentity.JavacRefineryTileEntity;
import com.hci.humbercraft.util.JavacRefineryItemHandler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Block.Properties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.fml.network.NetworkHooks;

public class JavaDimensionTeleporterBlock extends Block{
	
	public BlockPos posOfLink;	
	public DimensionType dimTypeOfLink;
	
	public JavaDimensionTeleporterBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return ModTileEntityTypes.JAVA_DIMENSION_TELEPORTER.get().create();
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,Hand handIn, BlockRayTraceResult hit) {
		if(worldIn != null && !worldIn.isRemote) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if(tile instanceof JavaDimensionTeleporterTileEntity) {				
				if (player.dimension != DimensionType.byName(HumberCraft.JAVA_DIMENSION_TYPE)) {
					teleportToDimension(player, DimensionType.byName(HumberCraft.JAVA_DIMENSION_TYPE), pos, worldIn);	
				} 
				else {
					teleportToDimension(player, DimensionType.byName(new ResourceLocation("minecraft", "overworld")),pos, worldIn);				
				}
				if(!hasTeleporterBlockOnPos(player.getEntityWorld(), pos)) {
					player.getEntityWorld().setBlockState(new BlockPos(pos.getX(), getYPlacement((ServerWorld)player.getEntityWorld(), pos), pos.getZ()), state);
				}
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.SUCCESS;
	}
	
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		TileEntity tile = worldIn.getTileEntity(pos);
		
		if(state.hasTileEntity() && state.getBlock() != newState.getBlock()) {
			worldIn.removeTileEntity(pos);
		}		
	}
	
	/*
	@Override
	public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
		Random random = new Random(worldIn.getSeed());
		if (!player.isPassenger() && !player.isBeingRidden() && player.isNonBoss()) {
			if (player.dimension != DimensionType.byName(HumberCraft.JAVA_DIMENSION_TYPE)) {
				teleportToDimension(player, DimensionType.byName(HumberCraft.JAVA_DIMENSION_TYPE), pos, worldIn);
			} 
			else {
				teleportToDimension(player, DimensionType.byName(new ResourceLocation("minecraft", "overworld")),pos, worldIn);
			}
		}
	}*/
	  
	private void teleportToDimension(Entity entityIn, DimensionType dimension, BlockPos pos, World worldIn) {
		entityIn.changeDimension(dimension, new ITeleporter() {
			@Override
			public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
				entity = repositionEntity.apply(false);				
				
				entity.setPositionAndUpdate(pos.getX(), getYPlacement(destWorld, pos) + 1, pos.getZ());
				return entity;
			}
		});
		
	}
	
	public int getYPlacement(ServerWorld destWorld, BlockPos pos) {
		int y = 0;
		while(destWorld.getBlockState(new BlockPos(pos.getX(), y, pos.getZ())).getBlock() != Blocks.AIR && destWorld.getBlockState(new BlockPos(pos.getX(), y + 1, pos.getZ())).getBlock() != Blocks.AIR && destWorld.getBlockState(new BlockPos(pos.getX(), y + 2, pos.getZ())).getBlock() != Blocks.AIR) {
			y++;
		}
		return y;
	}
	
	public boolean hasTeleporterBlockOnPos(World worldIn, BlockPos pos) {
		for(int i = 0; i < worldIn.getMaxHeight(); i++) {
			if(worldIn.getBlockState(new BlockPos(pos.getX(), i, pos.getZ())).equals(BlockInit.JAVA_DIMENSION_TELEPORTER.get().getDefaultState()))
				return true;
		}
		return false;
	
	}
}
