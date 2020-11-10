package com.hci.humbercraft.init;

import com.hci.humbercraft.HumberCraft;
import com.hci.humbercraft.recipes.IJavacRefineryRecipe;
import com.hci.humbercraft.recipes.JavacRefineryRecipe;
import com.hci.humbercraft.recipes.JavacRefineryRecipeSerializer;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeSerializerInit {

	public static final IRecipeSerializer<JavacRefineryRecipe> JAVAC_REFINERY_RECIPE_SERIALIZER = new JavacRefineryRecipeSerializer();
	public static final IRecipeType<IJavacRefineryRecipe> JAVAC_REFINERY_TYPE = registerType(IJavacRefineryRecipe.RECIPE_TYPE_ID);

	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = new DeferredRegister<>(
			ForgeRegistries.RECIPE_SERIALIZERS, HumberCraft.MOD_ID);

	public static final RegistryObject<IRecipeSerializer<?>> JAVAC_REFINERY_SERIALIZER = RECIPE_SERIALIZERS.register("test",
			() -> JAVAC_REFINERY_RECIPE_SERIALIZER);
	
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
