package gregtech.loaders.oreprocessing;

import gregtech.GT_Mod;
import gregtech.api.GregTech_API;
import gregtech.api.enums.*;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class ProcessingLog implements gregtech.api.interfaces.IOreRecipeRegistrator {
    public ProcessingLog() {
        OrePrefixes.log.add(this);
    }

    public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
        if (aOreDictName.equals("logRubber")) {
            GT_Values.RA.addCentrifugeRecipe(aStack, null, null, Materials.Methane.getGas(60L), ItemList.IC2_Resin.get(1L), GT_ModHandler.getIC2Item("plantBall", 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Carbon, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 1L), null, null, new int[]{5000, 3750, 2500, 2500}, 200, 20);
            GT_ModHandler.addSawmillRecipe(aStack, ItemList.IC2_Resin.get(1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 16L));
            GT_ModHandler.addExtractionRecipe(aStack, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.RawRubber, 1L));
            GT_ModHandler.addPulverisationRecipe(aStack, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 6L), ItemList.IC2_Resin.get(1L), 33, false);
        } else {
            GT_ModHandler.addPulverisationRecipe(aStack, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 6L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 1L), 80, false);
        }

        GT_Values.RA.addAssemblerRecipe(aStack, ItemList.Circuit_Integrated.getWithDamage(0L, 2L), Materials.SeedOil.getFluid(50L), ItemList.FR_Stick.get(1L), 16, 8);
        GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(8L, aStack), ItemList.Circuit_Integrated.getWithDamage(0L, 8L), Materials.SeedOil.getFluid(250L), ItemList.FR_Casing_Impregnated.get(1L), 64, 16);

        short aMeta = (short) aStack.getItemDamage();
        if (aMeta == Short.MAX_VALUE) {
            if ((GT_Utility.areStacksEqual(GT_ModHandler.getSmeltingOutput(aStack, false, null), new ItemStack(Items.coal, 1, 1)))) {
            	addPyrolyeOvenRecipes(aStack);
            	if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.disabledrecipes, "wood2charcoalsmelting", true)) {
                    GT_ModHandler.removeFurnaceSmelting(aStack);
                }
            }
            for (int i = 0; i < 32767; i++) {
                if ((GT_Utility.areStacksEqual(GT_ModHandler.getSmeltingOutput(new ItemStack(aStack.getItem(), 1, i), false, null), new ItemStack(Items.coal, 1, 1)))) {
                	addPyrolyeOvenRecipes(aStack);
                	if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.disabledrecipes, "wood2charcoalsmelting", true)) {
                        GT_ModHandler.removeFurnaceSmelting(new ItemStack(aStack.getItem(), 1, i));
                    }
                }
                ItemStack tStack = GT_ModHandler.getRecipeOutput(new ItemStack(aStack.getItem(), 1, i));
                if (tStack == null) {
                	if (i >= 16) {
                        break;
                    }
                } else {
                    ItemStack tPlanks = GT_Utility.copy(tStack);
                    tPlanks.stackSize = (tPlanks.stackSize * 3 / 2);
                    GT_Values.RA.addCutterRecipe(new ItemStack(aStack.getItem(), 1, i), Materials.Lubricant.getFluid(1L), GT_Utility.copy(tPlanks), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 1L), 200, 8);
                    GT_Values.RA.addCutterRecipe(new ItemStack(aStack.getItem(), 1, i), GT_Utility.copyAmount(GT_Mod.gregtechproxy.mNerfedWoodPlank ? tStack.stackSize : tStack.stackSize * 5 / 4, tStack), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 2L), 200, 8);
                    GT_ModHandler.addSawmillRecipe(new ItemStack(aStack.getItem(), 1, i), tPlanks, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 1L));
                    GT_ModHandler.removeRecipe(new ItemStack(aStack.getItem(), 1, i));
                    GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(GT_Mod.gregtechproxy.mNerfedWoodPlank ? tStack.stackSize : tStack.stackSize * 5 / 4, tStack), new Object[]{"s", "L", 'L', new ItemStack(aStack.getItem(), 1, i)});
                    GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(tStack.stackSize / (GT_Mod.gregtechproxy.mNerfedWoodPlank ? 2 : 1), tStack), new Object[]{new ItemStack(aStack.getItem(), 1, i)});
                }
            }
        } else {
            if ((GT_Utility.areStacksEqual(GT_ModHandler.getSmeltingOutput(aStack, false, null), new ItemStack(Items.coal, 1, 1)))) {
            	addPyrolyeOvenRecipes(aStack);
            	if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.disabledrecipes, "wood2charcoalsmelting", true)) {
                    GT_ModHandler.removeFurnaceSmelting(aStack);
                }
            }
            ItemStack tStack = GT_ModHandler.getRecipeOutput(aStack);
            if (tStack != null) {
                ItemStack tPlanks = GT_Utility.copy(tStack);
                tPlanks.stackSize = (tPlanks.stackSize * 3 / 2);
                GT_Values.RA.addCutterRecipe(aStack, Materials.Lubricant.getFluid(1L), GT_Utility.copy(tPlanks), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 1L), 200, 8);
                GT_Values.RA.addCutterRecipe(aStack, GT_Utility.copyAmount(GT_Mod.gregtechproxy.mNerfedWoodPlank ? tStack.stackSize : tStack.stackSize * 5 / 4, tStack), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 2L), 200, 8);
                GT_ModHandler.addSawmillRecipe(aStack, tPlanks, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 1L));
                GT_ModHandler.removeRecipe(aStack);
                GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(GT_Mod.gregtechproxy.mNerfedWoodPlank ? tStack.stackSize : tStack.stackSize * 5 / 4, tStack), new Object[]{"s", "L", 'L', aStack});
                GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(tStack.stackSize / (GT_Mod.gregtechproxy.mNerfedWoodPlank ? 2 : 1), tStack), new Object[]{aStack});
            }
        }

        if ((GT_Utility.areStacksEqual(GT_ModHandler.getSmeltingOutput(aStack, false, null), new ItemStack(Items.coal, 1, 1)))) {
        	addPyrolyeOvenRecipes(aStack);
        	if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.disabledrecipes, "wood2charcoalsmelting", true)) {
                GT_ModHandler.removeFurnaceSmelting(aStack);
            }
        }
    }
    
    public void addPyrolyeOvenRecipes(ItemStack aLogStack){
        ItemStack aNewLogStack = GT_Utility.copyAmount(16L, aLogStack);
        ItemStack aCharcoalStack = Materials.Charcoal.getGems(20);
        FluidStack aCreosote = Materials.Creosote.getFluid(4000);
        GT_Values.RA.addPyrolyseRecipe(GT_Utility.copyAmount(16L, aNewLogStack), GT_Values.NF,                    1,  aCharcoalStack, aCreosote,         640, 64);
        GT_Values.RA.addPyrolyseRecipe(GT_Utility.copyAmount(16L, aNewLogStack), Materials.Nitrogen.getGas(1000), 2,  aCharcoalStack, aCreosote,         320, 96);
        GT_Values.RA.addPyrolyseRecipe(GT_Utility.copyAmount(16L, aNewLogStack), GT_Values.NF,                    3,  Materials.Ash.getDust(4),       Materials.OilHeavy.getFluid(200),          320, 192);
        FluidStack aCharcoalByproducts = Materials.CharcoalByproducts.getGas(4000);
    	GT_Values.RA.addPyrolyseRecipe(GT_Utility.copyAmount(16L, aNewLogStack), GT_Values.NF,                    3,  aCharcoalStack, aCharcoalByproducts, 640, 64);
    	GT_Values.RA.addPyrolyseRecipe(GT_Utility.copyAmount(16L, aNewLogStack), Materials.Nitrogen.getGas(1000), 4,  aCharcoalStack, aCharcoalByproducts, 320, 96);
    	FluidStack aWoodGas = Materials.WoodGas.getGas(1500);
    	GT_Values.RA.addPyrolyseRecipe(GT_Utility.copyAmount(16L, aNewLogStack), GT_Values.NF,                    5,  aCharcoalStack, aWoodGas, 640, 64);
    	GT_Values.RA.addPyrolyseRecipe(GT_Utility.copyAmount(16L, aNewLogStack), Materials.Nitrogen.getGas(1000), 6,  aCharcoalStack, aWoodGas, 320, 96);
    	FluidStack aWoodVinegar = Materials.WoodVinegar.getFluid(3000);
    	GT_Values.RA.addPyrolyseRecipe(GT_Utility.copyAmount(16L, aNewLogStack), GT_Values.NF,                    7,  aCharcoalStack, aWoodVinegar, 640, 64);
    	GT_Values.RA.addPyrolyseRecipe(GT_Utility.copyAmount(16L, aNewLogStack), Materials.Nitrogen.getGas(1000), 8,  aCharcoalStack, aWoodVinegar, 320, 96);
    	FluidStack aWoodTar = Materials.WoodTar.getFluid(1500);
    	GT_Values.RA.addPyrolyseRecipe(GT_Utility.copyAmount(16L, aNewLogStack), GT_Values.NF,                    9,  aCharcoalStack, aWoodTar, 640, 64);
    	GT_Values.RA.addPyrolyseRecipe(GT_Utility.copyAmount(16L, aNewLogStack), Materials.Nitrogen.getGas(1000), 10, aCharcoalStack, aWoodTar, 320, 96);
    }
}
