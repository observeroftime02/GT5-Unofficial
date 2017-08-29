package gregtech.loaders.load;

import buildcraft.api.tools.IToolWrench;
import cpw.mods.fml.common.Loader;
import gregtech.api.GregTech_API;
import gregtech.api.enums.*;
import gregtech.api.items.GT_Generic_Item;
import gregtech.api.util.GT_Log;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.MatUnifier;
import gregtech.api.util.GT_Utility;
import gregtech.common.tools.GT_Tool_Scoop;
import mods.railcraft.api.core.items.IToolCrowbar;
import net.minecraft.block.Block;
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
        if (null != (tStack = GT_ModHandler.getRecipeOutput(tStack2 = MatUnifier.get(OrePrefixes.ingot, Materials.Bronze), tStack2, tStack2, tStack2, null, tStack2, tStack2, tStack2, tStack2))) {
            GT_ModHandler.addPulverisationRecipe(tStack, MatUnifier.get(OrePrefixes.dust, Materials.Bronze, 8), null, 0, false);
            GT_ModHandler.addSmeltingRecipe(tStack, MatUnifier.get(OrePrefixes.ingot, Materials.Bronze, 8));
        }
        if (null != (tStack = GT_ModHandler.getRecipeOutput(tStack2 = MatUnifier.get(OrePrefixes.plate, Materials.Bronze), tStack2, tStack2, tStack2, null, tStack2, tStack2, tStack2, tStack2))) {
            MatUnifier.registerOre(OreDictNames.craftingRawMachineTier00, tStack);
            GT_ModHandler.addPulverisationRecipe(tStack, MatUnifier.get(OrePrefixes.dust, Materials.Bronze, 8), null, 0, false);
            GT_ModHandler.addSmeltingRecipe(tStack, MatUnifier.get(OrePrefixes.ingot, Materials.Bronze, 8));
        }
        ItemStack tStack3;
        if (null != (tStack = GT_ModHandler.getRecipeOutput(tStack2 = MatUnifier.get(OrePrefixes.ingot, Materials.Iron), tStack3 = new ItemStack(Blocks.glass, 1, 0), tStack2, tStack3, MatUnifier.get(OrePrefixes.ingot, Materials.Gold), tStack3, tStack2, tStack3, tStack2))) {
            GT_ModHandler.addPulverisationRecipe(tStack, MatUnifier.get(OrePrefixes.dust, Materials.Iron, 4), MatUnifier.get(OrePrefixes.dust, Materials.Gold), 0, false);
        }
        if (null != (tStack = GT_ModHandler.getRecipeOutput(tStack2 = MatUnifier.get(OrePrefixes.ingot, Materials.Steel), tStack3 = new ItemStack(Blocks.glass, 1, 0), tStack2, tStack3, MatUnifier.get(OrePrefixes.ingot, Materials.Gold), tStack3, tStack2, tStack3, tStack2))) {
            GT_ModHandler.addPulverisationRecipe(tStack, MatUnifier.get(OrePrefixes.dust, Materials.Steel, 4), MatUnifier.get(OrePrefixes.dust, Materials.Gold), 0, false);
        }
        GT_Log.out.println("GT_Mod: Registering various Tools to be usable on GregTech Machines");
        GregTech_API.registerScrewdriver(GT_ModHandler.getRecipeOutput(null, new ItemStack(Items.iron_ingot, 1), null, new ItemStack(Items.stick, 1)));
        GregTech_API.registerScrewdriver(GT_ModHandler.getRecipeOutput(new ItemStack(Items.iron_ingot, 1), null, null, null, new ItemStack(Items.stick, 1)));

        GT_Log.out.println("GT_Mod: Adding Food Recipes to the Automatic Canning Machine. (also during the following Item Iteration)");
        GT_Values.RA.addCannerRecipe(new ItemStack(Items.rotten_flesh, 2, 32767), ItemList.IC2_Food_Can_Empty.get(1), ItemList.IC2_Food_Can_Spoiled.get(1), null, 200, 1);
        GT_Values.RA.addCannerRecipe(new ItemStack(Items.spider_eye, 2, 32767), ItemList.IC2_Food_Can_Empty.get(1), ItemList.IC2_Food_Can_Spoiled.get(1), null, 100, 1);
        GT_Values.RA.addCannerRecipe(new ItemStack(Items.cake, 1, 32767), ItemList.IC2_Food_Can_Empty.get(12), ItemList.IC2_Food_Can_Filled.get(12), null, 600, 1);
        GT_Values.RA.addCannerRecipe(new ItemStack(Items.mushroom_stew, 1, 32767), ItemList.IC2_Food_Can_Empty.get(6), ItemList.IC2_Food_Can_Filled.get(6), new ItemStack(Items.bowl, 1), 300, 1);

        GT_Log.out.println("GT_Mod: Scanning ItemList.");

        for (Object anItemRegistry : Item.itemRegistry) {
            Object tObject;
            if (((tObject = anItemRegistry) instanceof Item) && (!(tObject instanceof GT_Generic_Item))) {
                Item tItem = (Item) tObject;
                String tName;
                if ((tName = tItem.getUnlocalizedName()) != null) {
                    if ((tItem instanceof IToolCrowbar)) {
                        if ((!tItem.isDamageable()) && (!GT_ModHandler.isElectricItem(new ItemStack(tItem, 1, 0)))) {
                            if ((GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.disabledrecipes, "infiniteDurabilityRCCrowbars", false)) &&
                                    (GT_ModHandler.removeRecipeByOutput(new ItemStack(tItem, 1, 32767)))) {
                                GT_Log.out.println("GT_Mod: Removed infinite RC Crowbar: " + tName);
                            }
                        } else if (GregTech_API.registerCrowbar(new ItemStack(tItem, 1, 32767))) {
                            GT_Log.out.println("GT_Mod: Registered valid RC Crowbar: " + tName);
                        }
                    }
                    if ((tItem instanceof IToolWrench)) {
                        if ((!tItem.isDamageable()) && (!GT_ModHandler.isElectricItem(new ItemStack(tItem, 1, 0)))) {
                            if ((GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.disabledrecipes, "infiniteDurabilityBCWrenches", false)) &&
                                    (GT_ModHandler.removeRecipeByOutput(new ItemStack(tItem, 1, 32767)))) {
                                GT_Log.out.println("GT_Mod: Removed infinite BC Wrench: " + tName);
                            }
                        } else if (GregTech_API.registerWrench(new ItemStack(tItem, 1, 32767))) {
                            GT_Log.out.println("GT_Mod: Registered valid BC Wrench: " + tName);
                        }
                    }
                    Block tBlock = GT_Utility.getBlockFromStack(new ItemStack(tItem, 1, 0));
                    if (tBlock != null) {
                        if (tName.endsWith("beehives")) {
                            tBlock.setHarvestLevel("scoop", 0);
                            GT_Tool_Scoop.sBeeHiveMaterial = tBlock.getMaterial();
                        }
                        if (OrePrefixes.stone.mDefaultStackSize < tItem.getItemStackLimit(new ItemStack(tItem, 1, 0))) {
                            if ((tBlock.isReplaceableOreGen(GT_Values.DW, 0, 0, 0, Blocks.stone)) || (tBlock.isReplaceableOreGen(GT_Values.DW, 0, 0, 0, Blocks.netherrack)) || (tBlock.isReplaceableOreGen(GT_Values.DW, 0, 0, 0, Blocks.end_stone))) {
                                tItem.setMaxStackSize(OrePrefixes.stone.mDefaultStackSize);
                            }
                        }
                    }
                    if ((tItem instanceof IFluidContainerItem)) {
                        MatUnifier.addToBlacklist(new ItemStack(tItem, 1, 32767));
                    }
                    if ((tName.equals("item.ItemSensorLocationCard")) || (tName.equals("item.ItemEnergySensorLocationCard")) || (tName.equals("item.ItemEnergyArrayLocationCard")) || (tName.equals("item.ItemTextCard"))) {
                        GT_Values.RA.addAssemblerRecipe(new ItemStack(tItem, 1, 32767), null, ItemList.Circuit_Basic.get(2), 200, 32);
                    }
                    if (tName.equals("item.ItemTimeCard")) {
                        GT_Values.RA.addAssemblerRecipe(new ItemStack(tItem, 1, 32767), null, ItemList.Circuit_Basic.get(1), 100, 32);
                    }
                    if ((tName.equals("item.fieryBlood")) || (tName.equals("item.fieryTears"))) {
                        GT_Values.RA.addFuel(new ItemStack(tItem, 1, 0), null, 2048, 5);
                    }
                    if (tName.equals("tile.TFRoots")) {
                        GT_ModHandler.addPulverisationRecipe(new ItemStack(tItem, 1, 0), new ItemStack(Items.stick, 2), new ItemStack(Items.stick, 1), 30);
                        GT_ModHandler.addSawmillRecipe(new ItemStack(tItem, 1, 0), new ItemStack(Items.stick, 4), new ItemStack(Items.stick, 2));
                        GT_Values.RA.addFuel(new ItemStack(tItem, 1, 1), new ItemStack(Items.stick, 4), 32, 5);
                    }
                    if (tName.equals("item.tconstruct.manual")) {
                        MatUnifier.registerOre("bookTinkersManual", new ItemStack(tItem, 1, 32767));
                    }
                    if (tName.equals("item.itemManuelBook")) {
                        MatUnifier.registerOre("bookWritten", new ItemStack(tItem, 1, 0));
                    }
                    if (tName.equals("item.blueprintItem")) {
                        MatUnifier.registerOre("paperBlueprint", new ItemStack(tItem, 1, 32767));
                    }
                    if (tName.equals("item.ccprintout")) {
                        MatUnifier.registerOre("paperWritten", new ItemStack(tItem, 1, 0));
                        MatUnifier.registerOre("paperWritten", new ItemStack(tItem, 1, 1));
                        MatUnifier.registerOre("bookWritten", new ItemStack(tItem, 1, 2));
                    }
                    if (tName.equals("item.blueprintItem")) {
                        MatUnifier.registerOre("paperBlueprint", new ItemStack(tItem, 1, 32767));
                    }
                    if (tName.equals("item.wirelessmap")) {
                        MatUnifier.registerOre("paperMap", new ItemStack(tItem, 1, 32767));
                    }
                    if (Loader.isModLoaded("Thaumcraft")) {
                        if (tName.equals("item.ItemResearchNotes")) {
                            MatUnifier.registerOre("paperResearch", new ItemStack(tItem, 1, 32767));
                        }
                        if (tName.equals("item.ItemThaumonomicon")) {
                            MatUnifier.registerOre("bookThaumonomicon", new ItemStack(tItem, 1, 32767));
                        }
                        if (tName.equals("tile.blockCosmeticSolid")) {
                            MatUnifier.registerOre(OrePrefixes.stone, Materials.Obsidian, new ItemStack(tItem, 1, 0));
                            MatUnifier.registerOre(OrePrefixes.stone, Materials.Obsidian, new ItemStack(tItem, 1, 1));
                            MatUnifier.registerOre(OrePrefixes.block, Materials.Thaumium, new ItemStack(tItem, 1, 4));
                        }
                    }
                    if (tName.equals("item.ligniteCoal")) {
                        MatUnifier.set(OrePrefixes.gem, Materials.Lignite, new ItemStack(tItem, 1, 0));
                    }
                    if (tName.equals("tile.rpstone")) {
                        MatUnifier.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 0));
                        MatUnifier.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 1));
                        MatUnifier.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 2));
                        MatUnifier.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 3));
                        MatUnifier.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 4));
                        MatUnifier.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 5));
                        MatUnifier.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 6));
                    }
                    if (Loader.isModLoaded("UndergroundBiomesConstructs")) {
                        if (tName.equals("tile.igneousStone") || tName.equals("tile.igneousStoneBrick") || tName.equals("tile.igneousCobblestone")) {
                            MatUnifier.registerOre(OrePrefixes.stone, Materials.GraniteRed, new ItemStack(tItem, 1, 0));
                            MatUnifier.registerOre(OrePrefixes.stone, Materials.GraniteBlack, new ItemStack(tItem, 1, 1));
                            MatUnifier.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 5));

                            MatUnifier.registerOre(OrePrefixes.stone, Materials.GraniteRed, new ItemStack(tItem, 1, 8));
                            MatUnifier.registerOre(OrePrefixes.stone, Materials.GraniteBlack, new ItemStack(tItem, 1, 9));
                            MatUnifier.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 13));
                        }
                        if (tName.equals("tile.metamorphicStone") || tName.equals("tile.metamorphicStoneBrick") || tName.equals("tile.metamorphicCobblestone")) {
                            MatUnifier.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 2));
                            MatUnifier.registerOre(OrePrefixes.stone, Materials.Quartzite, new ItemStack(tItem, 1, 3));
                            MatUnifier.registerOre(OrePrefixes.stone, Materials.Soapstone, new ItemStack(tItem, 1, 6));

                            MatUnifier.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 10));
                            MatUnifier.registerOre(OrePrefixes.stone, Materials.Quartzite, new ItemStack(tItem, 1, 11));
                            MatUnifier.registerOre(OrePrefixes.stone, Materials.Soapstone, new ItemStack(tItem, 1, 14));
                        }
                    }
                    if (tName.equals("tile.enderchest")) {
                        MatUnifier.registerOre(OreDictNames.enderChest, new ItemStack(tItem, 1, 32767));
                    }
                    if (tName.equals("tile.autoWorkbenchBlock")) {
                        MatUnifier.registerOre(OreDictNames.craftingWorkBench, new ItemStack(tItem, 1, 0));
                    }
                    if (tName.equals("tile.pumpBlock")) {
                        MatUnifier.registerOre(OreDictNames.craftingPump, new ItemStack(tItem, 1, 0));
                        if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.disabledrecipes, "BCPump", false)) {
                            GT_ModHandler.removeRecipeByOutput(new ItemStack(tItem, 1, 0));
                        }
                    }
                    if (tName.equals("tile.tankBlock")) {
                        MatUnifier.registerOre(OreDictNames.craftingTank, new ItemStack(tItem, 1, 0));
                    }
                }
            }
        }
    }
}
