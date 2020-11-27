package com.hci.humbercraft.tileentity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.hci.humbercraft.HumberCraft;
import com.hci.humbercraft.blocks.BlockJavacRefinery;
import com.hci.humbercraft.container.JavacRefineryContainer;
import com.hci.humbercraft.init.ModTileEntityTypes;
import com.hci.humbercraft.init.RecipeSerializerInit;
import com.hci.humbercraft.recipes.JavacRefineryRecipe;
import com.hci.humbercraft.util.JavacRefineryItemHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.minecraftforge.api.distmarker.Dist;

public class JavacRefineryTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider{

	
	private ITextComponent customName;
	public int currentSmeltTime;
	public final int maxSmeltTime = 100;
	private JavacRefineryItemHandler inventory;
	
	public JavacRefineryTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
		
		this.inventory = new JavacRefineryItemHandler(2);
	}
	
	public JavacRefineryTileEntity() {
		this(ModTileEntityTypes.JAVAC_REFINERY.get());
	}

	@Override
	public Container createMenu(int windowID, PlayerInventory playerInv, PlayerEntity playerIn) {
		return new JavacRefineryContainer(windowID, playerInv, this);
	}
	
	@Override
	public void tick() {
		boolean dirty = false;
		
		if(this.world != null && !this.world.isRemote) {
			if(this.world.isBlockPowered(this.getPos())) {
				if (this.getRecipe(this.inventory.getStackInSlot(0)) != null) {
					if (this.currentSmeltTime != this.maxSmeltTime) {
						this.world.setBlockState(this.getPos(), this.getBlockState().with(BlockJavacRefinery.LIT, true));
						this.currentSmeltTime++;
						dirty = true;
					} else {
						this.world.setBlockState(this.getPos(),
								this.getBlockState().with(BlockJavacRefinery.LIT, false));
						this.currentSmeltTime = 0;
						ItemStack output = this.getRecipe(this.inventory.getStackInSlot(0)).getRecipeOutput();
						this.inventory.insertItem(1, output.copy(), false);
						this.inventory.decrStackSize(0, 1);
						dirty = true;
					}
				}
			}
		}
		
		if (dirty) {
			this.markDirty();
			this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(),
					Constants.BlockFlags.BLOCK_UPDATE);
		}
	}
	
	public void setCustomName(ITextComponent name) {
		this.customName = name;
	}
	
	public ITextComponent getName() {
		return this.customName != null ? this.customName : this.getDefaultName();
	}
	
	private ITextComponent getDefaultName() {
		return new TranslationTextComponent("Container." + HumberCraft.MOD_ID + ".javac_refinery");
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return this.getName();
	}
	
	@Nullable
	public ITextComponent getCustomName() {
		return this.customName;
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		if(compound.contains("CustomeName", Constants.NBT.TAG_STRING)) {
			this.customName = ITextComponent.Serializer.fromJson(compound.getString("CustomeName"));
		}
		
		NonNullList<ItemStack> inv = NonNullList.<ItemStack>withSize(this.inventory.getSlots(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, inv);
		this.inventory.setNonNullList(inv);

		this.currentSmeltTime = compound.getInt("CurrentSmeltTime");
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		if(this.customName != null) {
			compound.putString("CustomeName", ITextComponent.Serializer.toJson(customName));
		}
		
		ItemStackHelper.saveAllItems(compound, this.inventory.toNonNullList());
		compound.putInt("CurrentSmeltTime", this.currentSmeltTime);

		return compound;
	}
	
	@Nullable
	private JavacRefineryRecipe getRecipe(ItemStack stack) {
		if(stack == null) {
			return null;
		}
		
		Set<IRecipe<?>> recipes = findRecipesByType(RecipeSerializerInit.JAVAC_REFINERY_TYPE, this.world);
		for (IRecipe<?> iRecipe : recipes) {
			JavacRefineryRecipe recipe = (JavacRefineryRecipe) iRecipe;
			if (recipe.matches(new RecipeWrapper(this.inventory), this.world)) {
				return recipe;
			}
		}
		
		return null;
	}
	
	public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> typeIn, World world) {
		return world != null ? world.getRecipeManager().getRecipes().stream()
				.filter(recipe -> recipe.getType() == typeIn).collect(Collectors.toSet()) : Collections.emptySet();
	}
	
	@SuppressWarnings("resource")
	@OnlyIn(Dist.CLIENT)
	public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> typeIn) {
		ClientWorld world = Minecraft.getInstance().world;
		return world != null ? world.getRecipeManager().getRecipes().stream()
				.filter(recipe -> recipe.getType() == typeIn).collect(Collectors.toSet()) : Collections.emptySet();
	}

	public static Set<ItemStack> getAllRecipeInputs(IRecipeType<?> typeIn, World worldIn) {
		Set<ItemStack> inputs = new HashSet<ItemStack>();
		Set<IRecipe<?>> recipes = findRecipesByType(typeIn, worldIn);
		for (IRecipe<?> recipe : recipes) {
			NonNullList<Ingredient> ingredients = recipe.getIngredients();
			ingredients.forEach(ingredient -> {
				for (ItemStack stack : ingredient.getMatchingStacks()) {
					inputs.add(stack);
				}
			});
		}
		return inputs;
	}
	
	public final IItemHandlerModifiable getInventory() {
		return this.inventory;
	}
	
	@Nullable
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT nbt = new CompoundNBT();
		this.write(nbt);
		return new SUpdateTileEntityPacket(this.pos, 0, nbt);
	}
	
	@Override
	public CompoundNBT getUpdateTag() {
		CompoundNBT nbt = new CompoundNBT();
		this.write(nbt);
		return nbt;
	}

	@Override
	public void handleUpdateTag(CompoundNBT nbt) {
		this.read(nbt);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, LazyOptional.of(() -> this.inventory));
	}
}
