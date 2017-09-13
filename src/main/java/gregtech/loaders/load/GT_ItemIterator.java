package gregtech.loaders.load;

import gregtech.api.GregTech_API;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OreDictNames;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.items.GT_Generic_Item;
import gregtech.api.util.GT_Log;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.IFluidContainerItem;

public class GT_ItemIterator implements Runnable {
    public void run() {
        GT_Log.out.println("GT_Mod: Scanning for certain kinds of compatible Machineblocks.");
        ItemStack tStack2;
        ItemStack tStack;
        if (null != (tStack = GT_ModHandler.getRecipeOutput(tStack2 = GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Bronze), tStack2, tStack2, tStack2, null, tStack2, tStack2, tStack2, tStack2))) {
            GT_ModHandler.addPulverisationRecipe(tStack, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Bronze, 8), null, 0, false);
            GT_ModHandler.addSmeltingRecipe(tStack, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Bronze, 8));
        }
        if (null != (tStack = GT_ModHandler.getRecipeOutput(tStack2 = GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Bronze), tStack2, tStack2, tStack2, null, tStack2, tStack2, tStack2, tStack2))) {
            GT_OreDictUnificator.registerOre(OreDictNames.craftingRawMachineTier00, tStack);
            GT_ModHandler.addPulverisationRecipe(tStack, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Bronze, 8), null, 0, false);
            GT_ModHandler.addSmeltingRecipe(tStack, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Bronze, 8));
        }
        ItemStack tStack3;
        if (null != (tStack = GT_ModHandler.getRecipeOutput(tStack2 = GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron), tStack3 = new ItemStack(Blocks.glass, 1, 0), tStack2, tStack3, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Gold), tStack3, tStack2, tStack3, tStack2))) {
            GT_ModHandler.addPulverisationRecipe(tStack, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron, 4), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Gold), 0, false);
        }
        if (null != (tStack = GT_ModHandler.getRecipeOutput(tStack2 = GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel), tStack3 = new ItemStack(Blocks.glass, 1, 0), tStack2, tStack3, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Gold), tStack3, tStack2, tStack3, tStack2))) {
            GT_ModHandler.addPulverisationRecipe(tStack, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Steel, 4), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Gold), 0, false);
        }
        GT_Log.out.println("GT_Mod: Registering various Tools to be usable on GregTech Machines");
        GregTech_API.registerScrewdriver(GT_ModHandler.getRecipeOutput(null, new ItemStack(Items.iron_ingot, 1), null, new ItemStack(Items.stick, 1)));
        GregTech_API.registerScrewdriver(GT_ModHandler.getRecipeOutput(new ItemStack(Items.iron_ingot, 1), null, null, null, new ItemStack(Items.stick, 1)));

        GT_Log.out.println("GT_Mod: Scanning ItemList.");

        for (Object anItemRegistry : Item.itemRegistry) { //TODO DEPRECATE REALLY EXPENSIVE
            if (anItemRegistry instanceof Item && !(anItemRegistry instanceof GT_Generic_Item)) {
                Item tItem = (Item) anItemRegistry;
                if (tItem instanceof IFluidContainerItem) {
                    GT_OreDictUnificator.addToBlacklist(new ItemStack(tItem, 1, 32767));
                }
                /*Block tBlock = GT_Utility.getBlockFromStack(new ItemStack(tItem, 1, 0));
                if (tBlock != null) {
                    if (OrePrefixes.stone.mDefaultStackSize < tItem.getItemStackLimit(new ItemStack(tItem, 1, 0))) {
                        if ((tBlock.isReplaceableOreGen(GT_Values.DW, 0, 0, 0, Blocks.stone)) || (tBlock.isReplaceableOreGen(GT_Values.DW, 0, 0, 0, Blocks.netherrack)) || (tBlock.isReplaceableOreGen(GT_Values.DW, 0, 0, 0, Blocks.end_stone))) {
                            tItem.setMaxStackSize(OrePrefixes.stone.mDefaultStackSize);
                        }
                    }
                }*/
                /*if ((tName.equals("item.fieryBlood")) || (tName.equals("item.fieryTears"))) {
                    GT_Values.RA.addFuel(new ItemStack(tItem, 1, 0), null, 2048, 5);
                }
                /*if (tName.equals("item.ligniteCoal")) {
                    MatUnifier.set(OrePrefixes.gem, Materials.Lignite, new ItemStack(tItem, 1, 0));
                }*/
            }
        }
    }
}
