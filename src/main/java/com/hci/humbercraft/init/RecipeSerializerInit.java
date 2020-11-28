package com.hci.humbercraft.init;

import com.hci.humbercraft.HumberCraft;
import com.hci.humbercraft.recipes.IRefiningRecipe;
import com.hci.humbercraft.recipes.RefiningRecipe;
import com.hci.humbercraft.recipes.RefiningRecipeSerializer;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeSerializerInit {

	public static final IRecipeSerializer<RefiningRecipe> REFINING_RECIPE_SERIALIZER = new RefiningRecipeSerializer();
	public static final IRecipeType<IRefiningRecipe> REFINING_TYPE = registerType(IRefiningRecipe.RECIPE_TYPE_ID);

	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = new DeferredRegister<>(
			ForgeRegistries.RECIPE_SERIALIZERS, HumberCraft.MOD_ID);

	public static final RegistryObject<IRecipeSerializer<?>> REFINING_SERIALIZER = RECIPE_SERIALIZERS.register("refining",
			() -> REFINING_RECIPE_SERIALIZER);
	
	private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> {
		@Override
		public String toString() {
			return Registry.RECIPE_TYPE.getKey(this).toString();
		}
	}
	
	private static <T extends IRecipeType> T registerType(ResourceLocation recipeTypeId) {
		return (T) Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RecipeType<>());
	}
}
