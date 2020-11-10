package com.hci.humbercraft.recipes;

import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class JavacRefineryRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>>implements IRecipeSerializer<JavacRefineryRecipe> {
	
	@Override
	public JavacRefineryRecipe read(ResourceLocation recipeId, JsonObject json) {
		ItemStack output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
		Ingredient input = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input"));

		return new JavacRefineryRecipe(recipeId, input, output);
	}
	

	@Override
	public JavacRefineryRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
		ItemStack output = buffer.readItemStack();
		Ingredient input = Ingredient.read(buffer);

		return new JavacRefineryRecipe(recipeId, input, output);
	}

	@Override
	public void write(PacketBuffer buffer, JavacRefineryRecipe recipe) {
		Ingredient input = recipe.getIngredients().get(0);
		input.write(buffer);

		buffer.writeItemStack(recipe.getRecipeOutput(), false);
		
	}
}
