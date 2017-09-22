package gregtech.loaders.postload;

import gregtech.GT_Mod;
import gregtech.api.GregTech_API;
import gregtech.api.enums.*;
import gregtech.api.util.GT_Log;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Utility;
import gregtech.api.util.GT_OreDictUnificator;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GT_CraftingRecipeLoader implements Runnable {
    private final static String aTextIron1 = "X X";
    private final static String aTextIron2 = "XXX";

    public void run() {
        GT_Log.out.println("GT_Mod: Adding nerfed Vanilla Recipes.");
        GT_ModHandler.addCraftingRecipe(new ItemStack(Items.bucket, 1), "XhX", " X ", 'X', OrePrefixes.plate.get(Materials.AnyIron));
        if (!GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.recipereplacements, "Iron.Bucket", true)) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(Items.bucket, 1), aTextIron1, " X ", 'X', OrePrefixes.ingot.get(Materials.AnyIron));
        }
        ItemStack tMat = new ItemStack(Items.iron_ingot);
        if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.recipereplacements, "Iron.PressurePlate", true)) {
            ItemStack tStack;
            if (null != (tStack = GT_ModHandler.removeRecipe(tMat, tMat, null, null, null, null, null, null, null))) {
                GT_ModHandler.addCraftingRecipe(tStack, "XXh", 'X', OrePrefixes.plate.get(Materials.AnyIron), 'S', OrePrefixes.stick.get(Materials.Wood), 'I', OrePrefixes.ingot.get(Materials.AnyIron));
            }
        }
        if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.recipereplacements, "Iron.Door", true)) {
            ItemStack tStack;
            if (null != (tStack = GT_ModHandler.removeRecipe(tMat, tMat, null, tMat, tMat, null, tMat, tMat, null))) {
                GT_ModHandler.addCraftingRecipe(tStack, "XX ", "XXh", "XX ", 'X', OrePrefixes.plate.get(Materials.AnyIron), 'S', OrePrefixes.stick.get(Materials.Wood), 'I', OrePrefixes.ingot.get(Materials.AnyIron));
            }
        }
        if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.recipereplacements, "Iron.Cauldron", true)) {
            ItemStack tStack;
            if (null != (tStack = GT_ModHandler.removeRecipe(tMat, null, tMat, tMat, null, tMat, tMat, tMat, tMat))) {
                GT_ModHandler.addCraftingRecipe(tStack, aTextIron1, "XhX", aTextIron2, 'X', OrePrefixes.plate.get(Materials.AnyIron), 'S', OrePrefixes.stick.get(Materials.Wood), 'I', OrePrefixes.ingot.get(Materials.AnyIron));
            }
        }
        if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.recipereplacements, "Iron.Hopper", true)) {
            ItemStack tStack;
            if (null != (tStack = GT_ModHandler.removeRecipe(tMat, null, tMat, tMat, new ItemStack(Blocks.chest, 1, 0), tMat, null, tMat, null))) {
                GT_ModHandler.addCraftingRecipe(tStack, "XwX", "XCX", " X ", 'X', OrePrefixes.plate.get(Materials.AnyIron), 'S', OrePrefixes.stick.get(Materials.Wood), 'I', OrePrefixes.ingot.get(Materials.AnyIron), 'C', "craftingChest");
            }
        }
        if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.recipereplacements, "Iron.Bars", true)) {
            ItemStack tStack;
            if (null != (tStack = GT_ModHandler.removeRecipe(tMat, tMat, tMat, tMat, tMat, tMat, null, null, null))) {
                tStack.stackSize /= 2;
                GT_ModHandler.addCraftingRecipe(tStack, " w ", aTextIron2, aTextIron2, 'X', OrePrefixes.stick.get(Materials.AnyIron), 'S', OrePrefixes.stick.get(Materials.Wood), 'I', OrePrefixes.ingot.get(Materials.AnyIron));
            }
        }

        tMat = new ItemStack(Items.gold_ingot);
        if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.recipereplacements, "Gold.PressurePlate", true)) {
            ItemStack tStack;
            if (null != (tStack = GT_ModHandler.removeRecipe(tMat, tMat, null, null, null, null, null, null, null))) {
                GT_ModHandler.addCraftingRecipe(tStack, "XXh", 'X', OrePrefixes.plate.get(Materials.Gold), 'S', OrePrefixes.stick.get(Materials.Wood), 'I', OrePrefixes.ingot.get(Materials.Gold));
            }
        }
        tMat = GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Rubber);
        if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.recipereplacements, "Rubber.Sheet", true)) {
            ItemStack tStack;
            if (null != (tStack = GT_ModHandler.removeRecipe(tMat, tMat, tMat, tMat, tMat, tMat, null, null, null))) {
                GT_ModHandler.addCraftingRecipe(tStack, aTextIron2, aTextIron2, 'X', OrePrefixes.plate.get(Materials.Rubber));
            }
        }
        GT_ModHandler.removeRecipeByOutput(new ItemStack(Items.glass_bottle, 1));
        GT_ModHandler.removeRecipeByOutput(new ItemStack(Blocks.tnt));

        ItemStack tStack = GT_ModHandler.removeRecipe(new ItemStack(Blocks.planks, 1, 0), null, null, new ItemStack(Blocks.planks, 1, 0));
        if (tStack != null) {
            GT_ModHandler.addBasicShapedRecipe(GT_Utility.copyAmount(GT_Mod.gregtechproxy.mNerfedWoodPlank ? tStack.stackSize : tStack.stackSize * 5 / 4, tStack), "s", "P", "P", 'P', GT_OreDictUnificator.get(OrePrefixes.plank, Materials.Wood));
            GT_ModHandler.addBasicShapedRecipe(GT_Utility.copyAmount(GT_Mod.gregtechproxy.mNerfedWoodPlank ? tStack.stackSize / 2 : tStack.stackSize, tStack), "P", "P", 'P', GT_OreDictUnificator.get(OrePrefixes.plank, Materials.Wood));
        }
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.wooden_pressure_plate, 1, 0), "PP ", "   ", "   ", 'P', GT_OreDictUnificator.get(OrePrefixes.plank, Materials.Wood));
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.stone_button, 2, 0), "S  ", "S  ", "   ", 'S', new ItemStack(Blocks.stone, 1));
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.stone_pressure_plate, 1, 0), "SS ", "   ", "   ", 'S', new ItemStack(Blocks.stone, 1));
        GT_ModHandler.addBasicShapelessRecipe(new ItemStack(Blocks.stone_button, 1, 0), new ItemStack(Blocks.stone, 1));

        GT_Log.out.println("GT_Mod: Adding Vanilla Convenience Recipes.");

        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.stonebrick, 1, 3), "f  ", "X  ", "   ", 'X', new ItemStack(Blocks.double_stone_slab, 1, 8));
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.gravel, 1, 0), "h  ", "X  ", "   ", 'X', new ItemStack(Blocks.cobblestone, 1, 0));
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.sand, 1, 0), "h  ", "X  ", "   ", 'X', new ItemStack(Blocks.gravel, 1, 0));
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.cobblestone, 1, 0), "h  ", "X  ", "   ", 'X', new ItemStack(Blocks.stone, 1, 0));
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.stonebrick, 1, 2), "h  ", "X  ", "   ", 'X', new ItemStack(Blocks.stonebrick, 1, 0));

        GT_ModHandler.addBasicShapelessRecipe(new ItemStack(Blocks.double_stone_slab, 1, 8), new ItemStack(Blocks.double_stone_slab, 1, 0));
        GT_ModHandler.addBasicShapelessRecipe(new ItemStack(Blocks.double_stone_slab, 1, 0), new ItemStack(Blocks.double_stone_slab, 1, 8));
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.double_stone_slab, 1, 0), "B  ", "B  ", "   ", 'B', new ItemStack(Blocks.stone_slab, 1, 0));
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.cobblestone, 1, 0), "B  ", "B  ", "   ", 'B', new ItemStack(Blocks.stone_slab, 1, 3));
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.brick_block, 1, 0), "B  ", "B  ", "   ", 'B', new ItemStack(Blocks.stone_slab, 1, 4));
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.stonebrick, 1, 0),  "B  ", "B  ", "   ", 'B', new ItemStack(Blocks.stone_slab, 1, 5));
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.nether_brick, 1, 0), "B  ", "B  ", "   ", 'B', new ItemStack(Blocks.stone_slab, 1, 6));
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.quartz_block, 1, 0), "B  ", "B  ", "   ", 'B', new ItemStack(Blocks.stone_slab, 1, 7));
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.double_stone_slab, 1, 8), "B  ", "B  ", "   ", 'B', new ItemStack(Blocks.stone_slab, 1, 8));
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.planks, 1, 0), "B  ", "B  ", "   ", 'B', new ItemStack(Blocks.wooden_slab, 1, 0));
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.planks, 1, 1), "B  ", "B  ", "   ", 'B', new ItemStack(Blocks.wooden_slab, 1, 1));
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.planks, 1, 2), "B  ", "B  ", "   ", 'B', new ItemStack(Blocks.wooden_slab, 1, 2));
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.planks, 1, 3), "B  ", "B  ", "   ", 'B', new ItemStack(Blocks.wooden_slab, 1, 3));
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.planks, 1, 4), "B  ", "B  ", "   ", 'B', new ItemStack(Blocks.wooden_slab, 1, 4));
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.planks, 1, 5), "B  ", "B  ", "   ", 'B', new ItemStack(Blocks.wooden_slab, 1, 5));
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.planks, 1, 6), "B  ", "B  ", "   ", 'B', new ItemStack(Blocks.wooden_slab, 1, 6));
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.planks, 1, 7), "B  ", "B  ", "   ", 'B', new ItemStack(Blocks.wooden_slab, 1, 7));

        GT_ModHandler.addCraftingRecipe(new ItemStack(Items.stick, 2, 0), "s", "X", 'X', new ItemStack(Blocks.deadbush, 1, 32767));
        GT_ModHandler.addCraftingRecipe(new ItemStack(Items.stick, 2, 0), "s", "X", 'X', new ItemStack(Blocks.tallgrass, 1, 0));
        GT_ModHandler.addCraftingRecipe(new ItemStack(Items.stick, 1, 0), "s", "X", 'X', OrePrefixes.treeSapling);

        GT_ModHandler.addCraftingRecipe(new ItemStack(Items.comparator, 1, 0), " T ", "TQT", "SSS", 'Q', OreDictNames.craftingQuartz, 'S', OrePrefixes.stoneSmooth, 'T', OreDictNames.craftingRedstoneTorch);

        GT_Log.out.println("GT_Mod: Adding Tool Recipes.");
        GT_ModHandler.addCraftingRecipe(new ItemStack(Items.minecart, 1), " h ", "PwP", "WPW", 'P', OrePrefixes.plate.get(Materials.AnyIron), 'W', ItemList.Component_Minecart_Wheels_Iron.get(1));
        GT_ModHandler.addCraftingRecipe(new ItemStack(Items.minecart, 1), " h ", "PwP", "WPW", 'P', OrePrefixes.plate.get(Materials.Steel), 'W', ItemList.Component_Minecart_Wheels_Steel.get(1));

        GT_ModHandler.addCraftingRecipe(new ItemStack(Items.chest_minecart, 1), "X", "C", 'C', new ItemStack(Items.minecart, 1), 'X', OreDictNames.craftingChest);
        GT_ModHandler.addCraftingRecipe(new ItemStack(Items.furnace_minecart, 1), "X", "C", 'C', new ItemStack(Items.minecart, 1), 'X', OreDictNames.craftingFurnace);
        GT_ModHandler.addCraftingRecipe(new ItemStack(Items.hopper_minecart, 1), "X", "C", 'C', new ItemStack(Items.minecart, 1), 'X', new ItemStack(Blocks.hopper, 1, 32767));
        GT_ModHandler.addCraftingRecipe(new ItemStack(Items.tnt_minecart, 1), "X", "C", 'C', new ItemStack(Items.minecart, 1), 'X', new ItemStack(Blocks.tnt, 1, 32767));

        ItemStack aSteelRing = GT_OreDictUnificator.get(OrePrefixes.ring, Materials.Steel);
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Items.chainmail_helmet, 1), "RRR", "RhR", 'R', aSteelRing);
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Items.chainmail_chestplate, 1), "RhR", "RRR", "RRR", 'R', aSteelRing);
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Items.chainmail_leggings, 1), "RRR", "RhR", "R R", 'R', aSteelRing);
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Items.chainmail_boots, 1), "R R", "RhR", 'R', aSteelRing);

        GT_Log.out.println("GT_Mod: Adding Wool and Color releated Recipes.");
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1, 1), new ItemStack(Blocks.wool, 1, 0), Dyes.dyeOrange);
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1, 2), new ItemStack(Blocks.wool, 1, 0), Dyes.dyeMagenta);
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1, 3), new ItemStack(Blocks.wool, 1, 0), Dyes.dyeLightBlue);
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1, 4), new ItemStack(Blocks.wool, 1, 0), Dyes.dyeYellow);
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1, 5), new ItemStack(Blocks.wool, 1, 0), Dyes.dyeLime);
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1, 6), new ItemStack(Blocks.wool, 1, 0), Dyes.dyePink);
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1, 7), new ItemStack(Blocks.wool, 1, 0), Dyes.dyeGray);
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1, 8), new ItemStack(Blocks.wool, 1, 0), Dyes.dyeLightGray);
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1, 9), new ItemStack(Blocks.wool, 1, 0), Dyes.dyeCyan);
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1, 10), new ItemStack(Blocks.wool, 1, 0), Dyes.dyePurple);
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1, 11), new ItemStack(Blocks.wool, 1, 0), Dyes.dyeBlue);
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1, 12), new ItemStack(Blocks.wool, 1, 0), Dyes.dyeBrown);
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1, 13), new ItemStack(Blocks.wool, 1, 0), Dyes.dyeGreen);
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1, 14), new ItemStack(Blocks.wool, 1, 0), Dyes.dyeRed);
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1, 15), new ItemStack(Blocks.wool, 1, 0), Dyes.dyeBlack);

        GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.stained_glass, 8, 0), "GGG", "GDG", "GGG", 'G', new ItemStack(Blocks.glass, 1), 'D', Dyes.dyeWhite);

        GT_Log.out.println("GT_Mod: Putting a Potato on a Stick.");
        GT_ModHandler.addCraftingRecipe(new ItemStack(Items.bowl, 1), "k", "X", 'X', OrePrefixes.plank.get(Materials.Wood));
        GT_ModHandler.addBasicShapedRecipe(GT_OreDictUnificator.get(OrePrefixes.ring, Materials.Rubber), "k  ", "X  ", "   ", 'X', GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Rubber));

        GT_ModHandler.addCraftingRecipe(new ItemStack(Items.arrow, 1), "  H", " S ", "F  ", 'H', new ItemStack(Items.flint, 1, 32767), 'S', OrePrefixes.stick.get(Materials.Wood), 'F', OreDictNames.craftingFeather);

        GT_ModHandler.removeRecipe(new ItemStack(Blocks.planks), null, new ItemStack(Blocks.planks), null, new ItemStack(Blocks.planks));
        //TODO GT_ModHandler.removeRecipeByOutput(ItemList.Food_Baked_Bread.get(1));
        GT_ModHandler.removeRecipeByOutput(new ItemStack(Items.cookie, 1));
        GT_ModHandler.removeRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tin), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper));
        if (null != GT_Utility.setStack(GT_ModHandler.getRecipeOutput(true, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper), null, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tin)), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Bronze, GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.disabledrecipes, "bronzeingotcrafting", true) ? 1 : 2))) {
            GT_Log.out.println("GT_Mod: Changed Forestrys Bronze Recipe");
        }
        if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.disabledrecipes, "enchantmenttable", false)) {
            GT_Log.out.println("GT_Mod: Removing the Recipe of the Enchantment Table, to have more Fun at enchanting with the Anvil and Books from Dungeons.");
            GT_ModHandler.removeRecipeByOutput(new ItemStack(Blocks.enchanting_table, 1));
        }
        if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.disabledrecipes, "enderchest", false)) {
            GT_ModHandler.removeRecipeByOutput(new ItemStack(Blocks.ender_chest, 1));
        }
        tStack = GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ash);
        GT_ModHandler.addBasicShapedRecipe(GT_ModHandler.getRecipeOutput(null, new ItemStack(Blocks.sand, 1, 0), null, null, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Apatite), null, null, new ItemStack(Blocks.sand, 1, 0), null), "S", "A", "S", 'A', GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Apatite), 'S', new ItemStack(Blocks.sand, 1, 32767));
        GT_ModHandler.addBasicShapedRecipe(GT_ModHandler.getRecipeOutput(tStack, tStack, tStack, tStack, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Apatite), tStack, tStack, tStack, tStack), "SSS", "SAS", "SSS", 'A', GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Apatite), 'S', GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ash));

        GT_Log.out.println("GT_Mod: Beginning to add regular Crafting Recipes.");
        GT_ModHandler.addBasicShapedRecipe(GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.Superconductor, 3), "NPT", "CCC", "HPT", 'H', GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Helium), 'N', GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Nitrogen), 'T', GT_OreDictUnificator.get(OrePrefixes.pipeTiny, Materials.TungstenSteel), 'P', ItemList.Electric_Pump_LV.get(1), 'C', GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.NiobiumTitanium));
        GT_ModHandler.addBasicShapedRecipe(GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.Superconductor, 3), "NPT", "CCC", "HPT", 'H', GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Helium), 'N', GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Nitrogen), 'T', GT_OreDictUnificator.get(OrePrefixes.pipeTiny, Materials.TungstenSteel), 'P', ItemList.Electric_Pump_LV.get(1), 'C', GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.VanadiumGallium));
        GT_ModHandler.addBasicShapedRecipe(GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.Superconductor, 3), "NPT", "CCC", "NPT", 'N', GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Nitrogen), 'T', GT_OreDictUnificator.get(OrePrefixes.pipeTiny, Materials.TungstenSteel), 'P', ItemList.Electric_Pump_LV.get(1), 'C', GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.YttriumBariumCuprate));
        GT_ModHandler.addBasicShapedRecipe(GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.Superconductor, 3), "NPT", "CCC", "NPT", 'N', GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Nitrogen), 'T', GT_OreDictUnificator.get(OrePrefixes.pipeTiny, Materials.TungstenSteel), 'P', ItemList.Electric_Pump_LV.get(1), 'C', GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.Naquadah));

        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.IronMagnetic), OrePrefixes.stick.get(Materials.AnyIron), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone));
        GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.ring, Materials.Paper), "PPk", 'P', OrePrefixes.plate.get(Materials.Paper));

        GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.torch, 2), "C", "S", 'C', GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur), 'S', OrePrefixes.stick.get(Materials.Wood));
        GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.torch, 6), "C", "S", 'C', GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Phosphorus), 'S', OrePrefixes.stick.get(Materials.Wood));

        GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.piston, 1), "WWW", "CBC", "CRC", 'W', OrePrefixes.plank.get(Materials.Wood), 'C', OrePrefixes.stoneCobble, 'R', GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone), 'B', OrePrefixes.ingot.get(Materials.AnyIron));
        GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.piston, 1), "WWW", "CBC", "CRC", 'W', OrePrefixes.plank.get(Materials.Wood), 'C', OrePrefixes.stoneCobble, 'R', GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone), 'B', OrePrefixes.ingot.get(Materials.AnyBronze));
        GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.piston, 1), "WWW", "CBC", "CRC", 'W', OrePrefixes.plank.get(Materials.Wood), 'C', OrePrefixes.stoneCobble, 'R', GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone), 'B', OrePrefixes.ingot.get(Materials.Aluminium));
        GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.piston, 1), "WWW", "CBC", "CRC", 'W', OrePrefixes.plank.get(Materials.Wood), 'C', OrePrefixes.stoneCobble, 'R', GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone), 'B', OrePrefixes.ingot.get(Materials.Steel));
        GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.piston, 1), "WWW", "CBC", "CRC", 'W', OrePrefixes.plank.get(Materials.Wood), 'C', OrePrefixes.stoneCobble, 'R', GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone), 'B', OrePrefixes.ingot.get(Materials.Titanium));

        if (!Materials.Steel.mBlastFurnaceRequired) {
            GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Steel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal));
        }
        if (GT_Mod.gregtechproxy.mNerfDustCrafting) {
            GT_ModHandler.removeRecipeByOutput(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Brass));
            GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Electrum, 6), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Silver), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Silver));
            GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Brass, 3), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Zinc));
            GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Brass, 9), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tetrahedrite), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tetrahedrite), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tetrahedrite), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Zinc));
            GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Bronze, 3), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tin));
            GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Bronze, 9), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tetrahedrite), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tetrahedrite), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tetrahedrite), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tin));
            GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Invar, 9), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Nickel));
            GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Cupronickel, 6), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Nickel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper));
            GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Nichrome, 15), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Nickel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Nickel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Nickel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Nickel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Chrome));
        } else {
            GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Electrum, 2L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Silver), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Gold));
            GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Brass, 4L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Zinc));
            GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Brass, 3L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tetrahedrite), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tetrahedrite), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tetrahedrite), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Zinc));
            GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Bronze, 4L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tin));
            GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Bronze, 3L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tetrahedrite), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tetrahedrite), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tetrahedrite), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tin));
            GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Invar, 3L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Nickel));
            GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Cupronickel, 2L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Nickel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper));
            GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Nichrome, 5L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Nickel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Nickel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Nickel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Nickel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Chrome));
        }
        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.RoseGold, 5L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Gold), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Gold), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Gold), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Gold), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper));
        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.SterlingSilver, 5L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Silver), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Silver), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Silver), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Silver), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper));
        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.BlackBronze, 5L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Gold), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Silver), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper));
        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.BismuthBronze, 5L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Bismuth), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Zinc), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper));
        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.BlackSteel, 5L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Nickel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.BlackBronze), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Steel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Steel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Steel));
        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.RedSteel, 8L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.SterlingSilver), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.BismuthBronze), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Steel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Steel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.BlackSteel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.BlackSteel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.BlackSteel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.BlackSteel));
        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.BlueSteel, 8L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.RoseGold), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Brass), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Steel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Steel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.BlackSteel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.BlackSteel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.BlackSteel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.BlackSteel));

        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ultimet, 9L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Cobalt), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Cobalt), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Cobalt), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Cobalt), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Cobalt), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Chrome), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Chrome), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Nickel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Molybdenum));
        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.CobaltBrass, 9L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Brass), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Brass), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Brass), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Brass), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Brass), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Brass), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Brass), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Aluminium), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Cobalt));
        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.StainlessSteel, 9L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Nickel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Manganese), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Chrome));
        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.YttriumBariumCuprate, 6L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Yttrium), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Barium), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Barium), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.AnyCopper));
        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Kanthal, 3L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Aluminium), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Chrome));
        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.FerriteMixture, 6L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Nickel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Zinc), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron));
        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.BorosilicateGlass, 8L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Boron), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glass), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glass), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glass), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glass), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glass), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glass), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glass));
        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.EpoxidFiberReinforced, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Epoxid), ItemList.Circuit_Parts_GlassFiber.get(1));
        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.EpoxidFiberReinforced, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Epoxid), ItemList.RawCarbonFibers.get(1));

        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ultimet), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Cobalt), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Cobalt), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Cobalt), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Cobalt), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Cobalt), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Chrome), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Chrome), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Nickel), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Molybdenum));
        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.CobaltBrass), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Brass), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Brass), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Brass), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Brass), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Brass), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Brass), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Brass), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Aluminium), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Cobalt));
        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.StainlessSteel), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Nickel), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Manganese), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Chrome));
        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.YttriumBariumCuprate, 6L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Yttrium), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Barium), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Barium), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.AnyCopper), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.AnyCopper), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.AnyCopper));
        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Kanthal, 3L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Aluminium), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Chrome));

        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.VanadiumSteel, 9L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Steel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Steel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Steel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Steel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Steel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Steel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Steel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Vanadium), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Chrome));
        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HSSG, 9L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.TungstenSteel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.TungstenSteel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.TungstenSteel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.TungstenSteel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.TungstenSteel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Chrome), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Molybdenum), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Molybdenum), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Vanadium));
        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HSSE, 9L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HSSG), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HSSG), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HSSG), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HSSG), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HSSG), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HSSG), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Cobalt), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Manganese), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Silicon));
        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HSSS, 9L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HSSG), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HSSG), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HSSG), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HSSG), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HSSG), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HSSG), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iridium), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iridium), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Osmium));

        GT_ModHandler.addBasicShapelessRecipe(new ItemStack(Items.gunpowder, 6), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Saltpeter), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Saltpeter));
        GT_ModHandler.addBasicShapelessRecipe(new ItemStack(Items.gunpowder, 6), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Charcoal), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Charcoal), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Charcoal), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Saltpeter), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Saltpeter));
        GT_ModHandler.addBasicShapelessRecipe(new ItemStack(Items.gunpowder, 6), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Carbon), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Carbon), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Carbon), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Saltpeter), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Saltpeter));

        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.IndiumGalliumPhosphide, 3), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Indium), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Gallium), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Phosphor));
        GT_ModHandler.addBasicShapelessRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Saltpeter, 5), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Potassium), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Nitrogen), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oxygen), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oxygen), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oxygen));

        GT_ModHandler.removeRecipe(tStack = GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur), tStack, tStack, tStack, new ItemStack(Items.coal, 1, 0), tStack, tStack, tStack, tStack);
        GT_ModHandler.removeRecipe(tStack = GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur), tStack, tStack, tStack, new ItemStack(Items.coal, 1, 1), tStack, tStack, tStack, tStack);
        GT_ModHandler.removeRecipe(null, tStack = new ItemStack(Items.coal, 1), null, tStack, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron), tStack, null, tStack, null);

        GT_ModHandler.removeFurnaceSmelting(new ItemStack(Blocks.hopper));

        GT_Log.out.println("GT_Mod: Applying harder Recipes for several Blocks.");
        if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.harderrecipes, "blockbreaker", false)) {
            GT_ModHandler.addCraftingRecipe(GT_ModHandler.removeRecipe(new ItemStack(Blocks.cobblestone, 1), new ItemStack(Items.iron_pickaxe, 1), new ItemStack(Blocks.cobblestone, 1), new ItemStack(Blocks.cobblestone, 1), new ItemStack(Blocks.piston, 1), new ItemStack(Blocks.cobblestone, 1), new ItemStack(Blocks.cobblestone, 1), new ItemStack(Items.redstone, 1), new ItemStack(Blocks.cobblestone, 1)), "RGR", "RPR", "RCR", 'G', OreDictNames.craftingGrinder, 'C', OrePrefixes.circuit.get(Materials.Advanced), 'R', OrePrefixes.plate.get(Materials.Steel), 'P', OreDictNames.craftingPiston);
        }
        if ((GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.harderrecipes, "sugarpaper", true))) {
            GT_ModHandler.removeRecipeByOutput(new ItemStack(Items.paper));
            GT_ModHandler.removeRecipeByOutput(new ItemStack(Items.sugar));
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Paper, 2), "SSS", " m ", 'S', new ItemStack(Items.reeds));
            GT_ModHandler.addCraftingRecipe(new ItemStack(Items.sugar, 1), "Sm ", 'S', new ItemStack(Items.reeds));
            GT_ModHandler.addCraftingRecipe(new ItemStack(Items.paper, 2), " C ", "SSS", " C ", 'S', GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Paper, 1), 'C', new ItemStack(Blocks.stone_slab));
        }

        /*TODO if (Loader.isModLoaded("GraviSuite")) {
            GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getModItem("GraviSuite", "advNanoChestPlate", 1, GT_Values.W));
            GT_ModHandler.addCraftingRecipe(GT_ModHandler.getModItem("GraviSuite", "advNanoChestPlate", 1, GT_Values.W), new Object[]{"CJC", "CNC", "WPW", 'C', ItemList.Plate_CarbonAlloy.get(1), 'J', GT_ModHandler.getModItem("GraviSuite", "advJetpack", 1, GT_Values.W), 'N', GT_ModHandler.getIC2Item("nanoBodyarmor", 1), 'W', OrePrefixes.wireGt04.get(Materials.Platinum), 'P', OrePrefixes.circuit.get(Materials.Data)});
        }*/
        GT_ModHandler.addCraftingRecipe(ItemList.ModularBasicHelmet.getWildcard(1), "AAA", "B B", 'A', new ItemStack(Items.leather, 1, 32767), 'B', OrePrefixes.ring.get(Materials.AnyIron));
        GT_ModHandler.addCraftingRecipe(ItemList.ModularBasicChestplate.getWildcard(1), "A A", "BAB", "AAA", 'A', new ItemStack(Items.leather, 1, 32767), 'B', OrePrefixes.ring.get(Materials.AnyIron));
        GT_ModHandler.addCraftingRecipe(ItemList.ModularBasicLeggings.getWildcard(1), "BAB", "A A", "A A", 'A', new ItemStack(Items.leather, 1, 32767), 'B', OrePrefixes.ring.get(Materials.AnyIron));
        GT_ModHandler.addCraftingRecipe(ItemList.ModularBasicBoots.getWildcard(1), "A A", "B B", "A A", 'A', new ItemStack(Items.leather, 1, 32767), 'B', OrePrefixes.ring.get(Materials.AnyIron));
        GT_ModHandler.addCraftingRecipe(ItemList.ModularElectric1Helmet.getWildcard(1), "ACA", "B B", 'A', OrePrefixes.stick.get(Materials.Aluminium), 'B', OrePrefixes.plate.get(Materials.Steel), 'C', OrePrefixes.battery.get(Materials.Advanced));
        GT_ModHandler.addCraftingRecipe(ItemList.ModularElectric1Chestplate.getWildcard(1), "A A", "BCB", "AAA", 'A', OrePrefixes.stick.get(Materials.Aluminium), 'B', OrePrefixes.plate.get(Materials.Steel), 'C', OrePrefixes.battery.get(Materials.Advanced));
        GT_ModHandler.addCraftingRecipe(ItemList.ModularElectric1Leggings.getWildcard(1), "BCB", "A A", "A A", 'A', OrePrefixes.stick.get(Materials.Aluminium), 'B', OrePrefixes.plate.get(Materials.Steel), 'C', OrePrefixes.battery.get(Materials.Advanced));
        GT_ModHandler.addCraftingRecipe(ItemList.ModularElectric1Boots.getWildcard(1), "A A", "BCB", "A A", 'A', OrePrefixes.stick.get(Materials.Aluminium), 'B', OrePrefixes.plate.get(Materials.Steel), 'C', OrePrefixes.battery.get(Materials.Advanced));
        GT_ModHandler.addCraftingRecipe(ItemList.ModularElectric2Helmet.getWildcard(1), "ACA", "B B", 'A', OrePrefixes.stick.get(Materials.TungstenSteel), 'B', ItemList.Plate_CarbonAlloy.get(1), 'C', OrePrefixes.battery.get(Materials.Master));
        GT_ModHandler.addCraftingRecipe(ItemList.ModularElectric2Chestplate.getWildcard(1), "A A", "BCB", "AAA", 'A', OrePrefixes.stick.get(Materials.TungstenSteel), 'B', ItemList.Plate_CarbonAlloy.get(1), 'C', OrePrefixes.battery.get(Materials.Master));
        GT_ModHandler.addCraftingRecipe(ItemList.ModularElectric2Leggings.getWildcard(1), "BCB", "A A", "A A", 'A', OrePrefixes.stick.get(Materials.TungstenSteel), 'B', ItemList.Plate_CarbonAlloy.get(1), 'C', OrePrefixes.battery.get(Materials.Master));
        GT_ModHandler.addCraftingRecipe(ItemList.ModularElectric2Boots.getWildcard(1), "A A", "BCB", "A A", 'A', OrePrefixes.stick.get(Materials.TungstenSteel), 'B', ItemList.Plate_CarbonAlloy.get(1), 'C', OrePrefixes.battery.get(Materials.Master));

        GT_ModHandler.addShapelessCraftingRecipe(Materials.Fireclay.getDust(2), Materials.Brick.getDust(1), Materials.Clay.getDust(1));
    }
}
