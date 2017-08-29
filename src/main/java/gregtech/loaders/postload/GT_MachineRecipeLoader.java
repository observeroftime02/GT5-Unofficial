package gregtech.loaders.postload;

import codechicken.nei.api.API;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.GT_Mod;
import gregtech.api.GregTech_API;
import gregtech.api.enums.*;
import gregtech.api.objects.MaterialStack;
import gregtech.api.util.*;
import gregtech.common.GT_DummyWorld;
import gregtech.common.items.GT_MetaGenerated_Item_03;
import ic2.api.recipe.ILiquidHeatExchangerManager.HeatExchangeProperty;
import ic2.api.recipe.Recipes;
import ic2.core.Ic2Items;
import mods.railcraft.common.blocks.aesthetics.cube.EnumCube;
import mods.railcraft.common.items.RailcraftToolItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

public class GT_MachineRecipeLoader implements Runnable {
    private final MaterialStack[][] mAlloySmelterList = {{new MaterialStack(Materials.Tetrahedrite, 3), new MaterialStack(Materials.Tin, 1), new MaterialStack(Materials.Bronze, 3)}, {new MaterialStack(Materials.Tetrahedrite, 3), new MaterialStack(Materials.Zinc, 1), new MaterialStack(Materials.Brass, 3)}, {new MaterialStack(Materials.Copper, 3), new MaterialStack(Materials.Tin, 1), new MaterialStack(Materials.Bronze, 4)}, {new MaterialStack(Materials.Copper, 3), new MaterialStack(Materials.Zinc, 1), new MaterialStack(Materials.Brass, 4)}, {new MaterialStack(Materials.Copper, 1), new MaterialStack(Materials.Nickel, 1), new MaterialStack(Materials.Cupronickel, 2)}, {new MaterialStack(Materials.Copper, 1), new MaterialStack(Materials.Redstone, 4), new MaterialStack(Materials.RedAlloy, 1)}, {new MaterialStack(Materials.AnnealedCopper, 3), new MaterialStack(Materials.Tin, 1), new MaterialStack(Materials.Bronze, 4)}, {new MaterialStack(Materials.AnnealedCopper, 3), new MaterialStack(Materials.Zinc, 1), new MaterialStack(Materials.Brass, 4)}, {new MaterialStack(Materials.AnnealedCopper, 1), new MaterialStack(Materials.Nickel, 1), new MaterialStack(Materials.Cupronickel, 2)}, {new MaterialStack(Materials.AnnealedCopper, 1), new MaterialStack(Materials.Redstone, 4), new MaterialStack(Materials.RedAlloy, 1)}, {new MaterialStack(Materials.Iron, 2), new MaterialStack(Materials.Nickel, 1), new MaterialStack(Materials.Invar, 3)}, {new MaterialStack(Materials.WroughtIron, 2), new MaterialStack(Materials.Nickel, 1), new MaterialStack(Materials.Invar, 3)}, {new MaterialStack(Materials.Tin, 9), new MaterialStack(Materials.Antimony, 1), new MaterialStack(Materials.SolderingAlloy, 10)}, {new MaterialStack(Materials.Lead, 4), new MaterialStack(Materials.Antimony, 1), new MaterialStack(Materials.BatteryAlloy, 5)}, {new MaterialStack(Materials.Gold, 1), new MaterialStack(Materials.Silver, 1), new MaterialStack(Materials.Electrum, 2)}, {new MaterialStack(Materials.Magnesium, 1), new MaterialStack(Materials.Aluminium, 2), new MaterialStack(Materials.Magnalium, 3)}};
    private final static String aTextAE = "appliedenergistics2";
    private final static String aTextAEMM = "item.ItemMultiMaterial";
    private final static String aTextForestry = "Forestry";
    private final static String aTextEBXL = "ExtrabiomesXL";
    private final static String aTextTCGTPage = "gt.research.page.1.";
    private final static Boolean isNEILoaded = Loader.isModLoaded("NotEnoughItems");

    public void run() {
        GT_Log.out.println("GT_Mod: Adding non-OreDict Machine Recipes.");
        try {
            GT_Utility.removeSimpleIC2MachineRecipe(GT_Values.NI, ic2.api.recipe.Recipes.metalformerExtruding.getRecipes(), ItemList.Cell_Empty.get(3));
            GT_Utility.removeSimpleIC2MachineRecipe(ItemList.IC2_Energium_Dust.get(1), ic2.api.recipe.Recipes.compressor.getRecipes(), GT_Values.NI);
            GT_Utility.removeSimpleIC2MachineRecipe(new ItemStack(Items.gunpowder), ic2.api.recipe.Recipes.extractor.getRecipes(), GT_Values.NI);
            GT_Utility.removeSimpleIC2MachineRecipe(new ItemStack(Blocks.wool, 1, 32767), ic2.api.recipe.Recipes.extractor.getRecipes(), GT_Values.NI);
            GT_Utility.removeSimpleIC2MachineRecipe(new ItemStack(Blocks.gravel), ic2.api.recipe.Recipes.oreWashing.getRecipes(), GT_Values.NI);
        } catch (Throwable e) {
        }
        GT_Utility.removeIC2BottleRecipe(GT_ModHandler.getIC2Item("fuelRod", 1), GT_ModHandler.getIC2Item("UranFuel", 1), ic2.api.recipe.Recipes.cannerBottle.getRecipes(), GT_ModHandler.getIC2Item("reactorUraniumSimple", 1, 1));
        GT_Utility.removeIC2BottleRecipe(GT_ModHandler.getIC2Item("fuelRod", 1), GT_ModHandler.getIC2Item("MOXFuel", 1), ic2.api.recipe.Recipes.cannerBottle.getRecipes(), GT_ModHandler.getIC2Item("reactorMOXSimple", 1, 1));

        GT_Values.RA.addFluidExtractionRecipe(new ItemStack(Items.wheat_seeds, 1, 32767), GT_Values.NI, Materials.SeedOil.getFluid(10), 10000, 32, 2);
        GT_Values.RA.addFluidExtractionRecipe(new ItemStack(Items.melon_seeds, 1, 32767), GT_Values.NI, Materials.SeedOil.getFluid(10), 10000, 32, 2);
        GT_Values.RA.addFluidExtractionRecipe(new ItemStack(Items.pumpkin_seeds, 1, 32767), GT_Values.NI, Materials.SeedOil.getFluid(10), 10000, 32, 2);
        try {
            GT_DummyWorld tWorld = (GT_DummyWorld) GT_Values.DW;
            while (tWorld.mRandom.mIterationStep > 0) {
                GT_Values.RA.addFluidExtractionRecipe(GT_Utility.copyAmount(1, ForgeHooks.getGrassSeed(tWorld)), GT_Values.NI, Materials.SeedOil.getFluid(10), 10000, 64, 2);
            }
        } catch (Throwable e) {
            GT_Log.out.println("GT_Mod: failed to iterate somehow, maybe it's your Forge Version causing it. But it's not that important\n");
            e.printStackTrace(GT_Log.err);
        }

//        GT_Values.RA.addArcFurnaceRecipe(ItemList.Block_BronzePlate.get(1, new Object[]{}), new ItemStack[]{ MatUnifier.get(OrePrefixes.ingot,Materials.Bronze,4), MatUnifier.get(OrePrefixes.dust,Materials.Stone,1)}, null, 160, 96);
//        GT_Values.RA.addArcFurnaceRecipe(ItemList.Block_IridiumTungstensteel.get(1, new Object[]{}), new ItemStack[]{ MatUnifier.get(OrePrefixes.ingot,Materials.Bronze,4), MatUnifier.get(OrePrefixes.dust,Materials.Stone,1)}, null, 160, 96);
        GT_Values.RA.addArcFurnaceRecipe(ItemList.Block_TungstenSteelReinforced.get(1), new ItemStack[]{MatUnifier.get(OrePrefixes.ingot, Materials.TungstenSteel, 2), MatUnifier.get(OrePrefixes.dust, Materials.Concrete, 1)}, null, 160, 96);

        //Temporary until circuit overhaul
//        GT_Values.RA.addAlloySmelterRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Rubber, 2), MatUnifier.get(OrePrefixes.wireGt01, Materials.Copper, 1), MatUnifier.get(OrePrefixes.cableGt01, Materials.Copper, 1), 100, 16);

        GT_Values.RA.addPrinterRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Paper, 1), FluidRegistry.getFluidStack("squidink", 36), GT_Values.NI, ItemList.Paper_Punch_Card_Empty.get(1), 100, 2);
        GT_Values.RA.addPrinterRecipe(ItemList.Paper_Punch_Card_Empty.get(1), FluidRegistry.getFluidStack("squidink", 36), ItemList.Tool_DataStick.getWithName(0, "With Punch Card Data"), ItemList.Paper_Punch_Card_Encoded.get(1), 100, 2);
        GT_Values.RA.addPrinterRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Paper, 3), FluidRegistry.getFluidStack("squidink", 144), ItemList.Tool_DataStick.getWithName(0, "With Scanned Book Data"), ItemList.Paper_Printed_Pages.get(1), 400, 2);
        GT_Values.RA.addPrinterRecipe(new ItemStack(Items.map, 1, 32767), FluidRegistry.getFluidStack("squidink", 144), ItemList.Tool_DataStick.getWithName(0, "With Scanned Map Data"), new ItemStack(Items.filled_map, 1, 0), 400, 2);
        GT_Values.RA.addPrinterRecipe(new ItemStack(Items.book, 1, 32767), FluidRegistry.getFluidStack("squidink", 144), GT_Values.NI, GT_Utility.getWrittenBook("Manual_Printer", ItemList.Book_Written_01.get(1)), 400, 2);
        for (OrePrefixes tPrefix : Arrays.asList(new OrePrefixes[]{OrePrefixes.dust, OrePrefixes.dustSmall, OrePrefixes.dustTiny})) {
            GT_Values.RA.addMixerRecipe(MatUnifier.get(tPrefix, Materials.EnderPearl), MatUnifier.get(tPrefix, Materials.Blaze), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.getDust(Materials.EnderEye, tPrefix.mMaterialAmount), 100 * tPrefix.mMaterialAmount / 3628800, 8);
            GT_Values.RA.addMixerRecipe(MatUnifier.get(tPrefix, Materials.Gold), MatUnifier.get(tPrefix, Materials.Silver), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.getDust(Materials.Electrum, 2 * tPrefix.mMaterialAmount), 200 * tPrefix.mMaterialAmount / 3628800, 8);
            GT_Values.RA.addMixerRecipe(MatUnifier.get(tPrefix, Materials.Iron, 2), MatUnifier.get(tPrefix, Materials.Nickel), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.getDust(Materials.Invar, 3 * tPrefix.mMaterialAmount), 300 * tPrefix.mMaterialAmount / 3628800, 8);
            GT_Values.RA.addMixerRecipe(MatUnifier.get(tPrefix, Materials.Iron, 4), MatUnifier.get(tPrefix, Materials.Invar, 3), MatUnifier.get(tPrefix, Materials.Manganese), MatUnifier.get(tPrefix, Materials.Chrome), GT_Values.NF, GT_Values.NF, MatUnifier.getDust(Materials.StainlessSteel, 9 * tPrefix.mMaterialAmount), 900 * tPrefix.mMaterialAmount / 3628800, 8);
            GT_Values.RA.addMixerRecipe(MatUnifier.get(tPrefix, Materials.Iron), MatUnifier.get(tPrefix, Materials.Aluminium), MatUnifier.get(tPrefix, Materials.Chrome), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.getDust(Materials.Kanthal, 3 * tPrefix.mMaterialAmount), 300 * tPrefix.mMaterialAmount / 3628800, 8);
            GT_Values.RA.addMixerRecipe(MatUnifier.get(tPrefix, Materials.Copper, 3), MatUnifier.get(tPrefix, Materials.Barium, 2), MatUnifier.get(tPrefix, Materials.Yttrium), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.getDust(Materials.YttriumBariumCuprate, 6 * tPrefix.mMaterialAmount), 600 * tPrefix.mMaterialAmount / 3628800, 8);
            GT_Values.RA.addMixerRecipe(MatUnifier.get(tPrefix, Materials.Copper, 3), MatUnifier.get(tPrefix, Materials.Zinc), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.getDust(Materials.Brass, 4 * tPrefix.mMaterialAmount), 400 * tPrefix.mMaterialAmount / 3628800, 8);
            GT_Values.RA.addMixerRecipe(MatUnifier.get(tPrefix, Materials.Copper, 3), MatUnifier.get(tPrefix, Materials.Tin), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.getDust(Materials.Bronze, 4 * tPrefix.mMaterialAmount), 400 * tPrefix.mMaterialAmount / 3628800, 8);
            GT_Values.RA.addMixerRecipe(MatUnifier.get(tPrefix, Materials.Copper), MatUnifier.get(tPrefix, Materials.Nickel), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.getDust(Materials.Cupronickel, 2 * tPrefix.mMaterialAmount), 200 * tPrefix.mMaterialAmount / 3628800, 8);
            GT_Values.RA.addMixerRecipe(MatUnifier.get(tPrefix, Materials.Copper), MatUnifier.get(tPrefix, Materials.Gold, 4), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.getDust(Materials.RoseGold, 5 * tPrefix.mMaterialAmount), 500 * tPrefix.mMaterialAmount / 3628800, 8);
            GT_Values.RA.addMixerRecipe(MatUnifier.get(tPrefix, Materials.Copper), MatUnifier.get(tPrefix, Materials.Silver, 4), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.getDust(Materials.SterlingSilver, 5 * tPrefix.mMaterialAmount), 500 * tPrefix.mMaterialAmount / 3628800, 8);
            GT_Values.RA.addMixerRecipe(MatUnifier.get(tPrefix, Materials.Copper, 3), MatUnifier.get(tPrefix, Materials.Electrum, 2), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.getDust(Materials.BlackBronze, 5 * tPrefix.mMaterialAmount), 500 * tPrefix.mMaterialAmount / 3628800, 8);
            GT_Values.RA.addMixerRecipe(MatUnifier.get(tPrefix, Materials.Bismuth), MatUnifier.get(tPrefix, Materials.Brass, 4), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.getDust(Materials.BismuthBronze, 5 * tPrefix.mMaterialAmount), 500 * tPrefix.mMaterialAmount / 3628800, 8);
            GT_Values.RA.addMixerRecipe(MatUnifier.get(tPrefix, Materials.BlackBronze), MatUnifier.get(tPrefix, Materials.Nickel), MatUnifier.get(tPrefix, Materials.Steel, 3), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.getDust(Materials.BlackSteel, 5 * tPrefix.mMaterialAmount), 500 * tPrefix.mMaterialAmount / 3628800, 8);
            GT_Values.RA.addMixerRecipe(MatUnifier.get(tPrefix, Materials.SterlingSilver), MatUnifier.get(tPrefix, Materials.BismuthBronze), MatUnifier.get(tPrefix, Materials.BlackSteel, 4), MatUnifier.get(tPrefix, Materials.Steel, 2), GT_Values.NF, GT_Values.NF, MatUnifier.getDust(Materials.RedSteel, 8 * tPrefix.mMaterialAmount), 800 * tPrefix.mMaterialAmount / 3628800, 8);
            GT_Values.RA.addMixerRecipe(MatUnifier.get(tPrefix, Materials.RoseGold), MatUnifier.get(tPrefix, Materials.Brass), MatUnifier.get(tPrefix, Materials.BlackSteel, 4), MatUnifier.get(tPrefix, Materials.Steel, 2), GT_Values.NF, GT_Values.NF, MatUnifier.getDust(Materials.BlueSteel, 8 * tPrefix.mMaterialAmount), 800 * tPrefix.mMaterialAmount / 3628800, 8);
            GT_Values.RA.addMixerRecipe(MatUnifier.get(tPrefix, Materials.Cobalt, 5), MatUnifier.get(tPrefix, Materials.Chrome, 2), MatUnifier.get(tPrefix, Materials.Nickel), MatUnifier.get(tPrefix, Materials.Molybdenum), GT_Values.NF, GT_Values.NF, MatUnifier.getDust(Materials.Ultimet, 9 * tPrefix.mMaterialAmount), 900 * tPrefix.mMaterialAmount / 3628800, 8);
            GT_Values.RA.addMixerRecipe(MatUnifier.get(tPrefix, Materials.Brass, 7), MatUnifier.get(tPrefix, Materials.Aluminium), MatUnifier.get(tPrefix, Materials.Cobalt), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.getDust(Materials.CobaltBrass, 9 * tPrefix.mMaterialAmount), 900 * tPrefix.mMaterialAmount / 3628800, 8);
            GT_Values.RA.addMixerRecipe(MatUnifier.get(tPrefix, Materials.Saltpeter, 2), MatUnifier.get(tPrefix, Materials.Sulfur), MatUnifier.get(tPrefix, Materials.Coal, 3), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.getDust(Materials.Gunpowder, 6 * tPrefix.mMaterialAmount), 600 * tPrefix.mMaterialAmount / 3628800, 8);
            GT_Values.RA.addMixerRecipe(MatUnifier.get(tPrefix, Materials.Saltpeter, 2), MatUnifier.get(tPrefix, Materials.Sulfur), MatUnifier.get(tPrefix, Materials.Charcoal, 3), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.getDust(Materials.Gunpowder, 6 * tPrefix.mMaterialAmount), 600 * tPrefix.mMaterialAmount / 3628800, 8);
            GT_Values.RA.addMixerRecipe(MatUnifier.get(tPrefix, Materials.Saltpeter, 2), MatUnifier.get(tPrefix, Materials.Sulfur), MatUnifier.get(tPrefix, Materials.Carbon, 3), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.getDust(Materials.Gunpowder, 6 * tPrefix.mMaterialAmount), 600 * tPrefix.mMaterialAmount / 3628800, 8);
            GT_Values.RA.addMixerRecipe(MatUnifier.get(tPrefix, Materials.Brick), MatUnifier.get(tPrefix, Materials.Clay), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.getDust(Materials.Fireclay, 2 * tPrefix.mMaterialAmount), 200 * tPrefix.mMaterialAmount / 3628800, 8);
        }
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Clay, 1), MatUnifier.get(OrePrefixes.dust, Materials.Stone, 3), GT_Values.NI, GT_Values.NI, Materials.Water.getFluid(500L), Materials.Concrete.getMolten(576L), GT_Values.NI, 20, 16);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.dustTiny, Materials.Redstone, 5), MatUnifier.get(OrePrefixes.dustTiny, Materials.Ruby, 4), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, ItemList.IC2_Energium_Dust.get(1), 100, 8);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Redstone, 5), MatUnifier.get(OrePrefixes.dust, Materials.Ruby, 4), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, ItemList.IC2_Energium_Dust.get(9), 900, 8);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Sugar), new ItemStack(Blocks.brown_mushroom, 1), new ItemStack(Items.spider_eye, 1), GT_Values.NI, GT_Values.NF, GT_Values.NF, new ItemStack(Items.fermented_spider_eye, 1), 100, 8);
        //GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.dustTiny, Materials.Gold), MatUnifier.get(OrePrefixes.dust, Materials.Iron), MatUnifier.get(OrePrefixes.dust, Materials.LiveRoot), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.dust, Materials.IronWood, 2L), 100, 8);
        //GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Gold), MatUnifier.get(OrePrefixes.dust, Materials.Iron, 9L), MatUnifier.get(OrePrefixes.dust, Materials.LiveRoot, 9L), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.dust, Materials.IronWood, 18L), 900, 8);
        GT_Values.RA.addMixerRecipe(GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 1), MatUnifier.get(OrePrefixes.dust, Materials.Redstone), MatUnifier.get(OrePrefixes.gem, Materials.NetherQuartz), GT_Values.NI, Materials.Water.getFluid(500L), GT_Values.NF, MatUnifier.get(OrePrefixes.gem, Materials.Fluix, 2), 20, 16);
        GT_Values.RA.addMixerRecipe(GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 1), MatUnifier.get(OrePrefixes.dust, Materials.Redstone), MatUnifier.get(OrePrefixes.gem, Materials.NetherQuartz), GT_Values.NI, GT_ModHandler.getDistilledWater(500L), GT_Values.NF, MatUnifier.get(OrePrefixes.gem, Materials.Fluix, 2), 20, 16);
        GT_Values.RA.addMixerRecipe(ItemList.IC2_Fertilizer.get(1), new ItemStack(Blocks.dirt, 8, 32767), GT_Values.NI, GT_Values.NI, Materials.Water.getFluid(1000L), GT_Values.NF, GT_ModHandler.getModItem(aTextForestry, "soil", 8, 0), 64, 16);
        GT_Values.RA.addMixerRecipe(ItemList.FR_Fertilizer.get(1), new ItemStack(Blocks.dirt, 8, 32767), GT_Values.NI, GT_Values.NI, Materials.Water.getFluid(1000L), GT_Values.NF, GT_ModHandler.getModItem(aTextForestry, "soil", 8, 0), 64, 16);
        GT_Values.RA.addMixerRecipe(ItemList.FR_Compost.get(1), new ItemStack(Blocks.dirt, 8, 32767), GT_Values.NI, GT_Values.NI, Materials.Water.getFluid(1000L), GT_Values.NF, GT_ModHandler.getModItem(aTextForestry, "soil", 8, 0), 64, 16);
        GT_Values.RA.addMixerRecipe(ItemList.FR_Mulch.get(1), new ItemStack(Blocks.dirt, 8, 32767), GT_Values.NI, GT_Values.NI, Materials.Water.getFluid(1000L), GT_Values.NF, GT_ModHandler.getModItem(aTextForestry, "soil", 9, 0), 64, 16);
        GT_Values.RA.addMixerRecipe(new ItemStack(Blocks.sand, 1, 32767), new ItemStack(Blocks.dirt, 1, 32767), GT_Values.NI, GT_Values.NI, Materials.Water.getFluid(250L), GT_Values.NF, GT_ModHandler.getModItem(aTextForestry, "soil", 2, 1), 16, 16);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.cell, Materials.LightFuel, 5), MatUnifier.get(OrePrefixes.cell, Materials.HeavyFuel), null, null, null, null, MatUnifier.get(OrePrefixes.cell, Materials.Fuel, 6), 16, 16);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.cell, Materials.Water, 5), MatUnifier.get(OrePrefixes.dust, Materials.Stone), null, null, Materials.Lubricant.getFluid(20), new FluidStack(ItemList.sDrillingFluid, 5000), ItemList.Cell_Empty.get(5), 64, 16);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Lapis, 1), null, null, null, Materials.Water.getFluid(125), FluidRegistry.getFluidStack("ic2coolant", 125), null, 256, 48);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Lapis, 1), null, null, null, GT_ModHandler.getDistilledWater(1000), FluidRegistry.getFluidStack("ic2coolant", 1000), null, 256, 48);

        ItemStack aSulfurDust = MatUnifier.get(OrePrefixes.dust, Materials.Sulfur);
        ItemStack aWoodDust = MatUnifier.get(OrePrefixes.dust, Materials.Wood, 4);
        GT_Values.RA.addMixerRecipe(aSulfurDust, MatUnifier.get(OrePrefixes.dust, Materials.Sodium), aWoodDust, null, Materials.Creosote.getFluid(1000), null, ItemList.SFMixture.get(8), 1600, 16);
        GT_Values.RA.addMixerRecipe(aSulfurDust, MatUnifier.get(OrePrefixes.dust, Materials.Lithium), aWoodDust, null, Materials.Creosote.getFluid(1000), null, ItemList.SFMixture.get(8), 1600, 16);
        GT_Values.RA.addMixerRecipe(aSulfurDust, MatUnifier.get(OrePrefixes.dust, Materials.Caesium), aWoodDust, null, Materials.Creosote.getFluid(1000), null, ItemList.SFMixture.get(12), 1600, 16);
        GT_Values.RA.addMixerRecipe(aSulfurDust, MatUnifier.get(OrePrefixes.dust, Materials.Sodium), aWoodDust, null, Materials.Lubricant.getFluid(300), null, ItemList.SFMixture.get(8), 1200, 16);
        GT_Values.RA.addMixerRecipe(aSulfurDust, MatUnifier.get(OrePrefixes.dust, Materials.Lithium), aWoodDust, null, Materials.Lubricant.getFluid(300), null, ItemList.SFMixture.get(8), 1200, 16);
        GT_Values.RA.addMixerRecipe(aSulfurDust, MatUnifier.get(OrePrefixes.dust, Materials.Caesium), aWoodDust, null, Materials.Lubricant.getFluid(300), null, ItemList.SFMixture.get(12), 1200, 16);
        GT_Values.RA.addMixerRecipe(aSulfurDust, MatUnifier.get(OrePrefixes.dust, Materials.Sodium), aWoodDust, null, Materials.Glue.getFluid(333), null, ItemList.SFMixture.get(8), 800, 16);
        GT_Values.RA.addMixerRecipe(aSulfurDust, MatUnifier.get(OrePrefixes.dust, Materials.Lithium), aWoodDust, null, Materials.Glue.getFluid(333), null, ItemList.SFMixture.get(8), 800, 16);
        GT_Values.RA.addMixerRecipe(aSulfurDust, MatUnifier.get(OrePrefixes.dust, Materials.Caesium), aWoodDust, null, Materials.Glue.getFluid(333), null, ItemList.SFMixture.get(12), 800, 16);


        GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(2), MatUnifier.get(OrePrefixes.dustTiny, Materials.EnderEye), null, null, Materials.Mercury.getFluid(50), null, ItemList.MSFMixture.get(2), 100, 64);
        GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(2), MatUnifier.get(OrePrefixes.dustSmall, Materials.Blaze), null, null, Materials.Mercury.getFluid(50), null, ItemList.MSFMixture.get(2), 100, 64);

        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Lignite, 1), ItemList.MSFMixture.get(6), MatUnifier.get(OrePrefixes.dustTiny, Materials.Diamond), null, Materials.NitroFuel.getFluid(1000), null, ItemList.Block_MSSFUEL.get(1), 120, 96);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Charcoal, 1), ItemList.MSFMixture.get(4), MatUnifier.get(OrePrefixes.dustTiny, Materials.Diamond), null, Materials.NitroFuel.getFluid(800), null, ItemList.Block_MSSFUEL.get(1), 120, 96);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Coal, 1), ItemList.MSFMixture.get(2), MatUnifier.get(OrePrefixes.dustTiny, Materials.Diamond), null, Materials.NitroFuel.getFluid(500), null, ItemList.Block_MSSFUEL.get(1), 120, 96);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Lignite, 1), ItemList.MSFMixture.get(6), MatUnifier.get(OrePrefixes.dustTiny, Materials.Emerald), null, Materials.NitroFuel.getFluid(1000), null, ItemList.Block_MSSFUEL.get(1), 120, 96);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Charcoal, 1), ItemList.MSFMixture.get(4), MatUnifier.get(OrePrefixes.dustTiny, Materials.Emerald), null, Materials.NitroFuel.getFluid(800), null, ItemList.Block_MSSFUEL.get(1), 120, 96);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Coal, 1), ItemList.MSFMixture.get(2), MatUnifier.get(OrePrefixes.dustTiny, Materials.Emerald), null, Materials.NitroFuel.getFluid(500), null, ItemList.Block_MSSFUEL.get(1), 120, 96);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Lignite, 1), ItemList.MSFMixture.get(6), MatUnifier.get(OrePrefixes.dustTiny, Materials.Sapphire), null, Materials.NitroFuel.getFluid(1000), null, ItemList.Block_MSSFUEL.get(1), 120, 96);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Charcoal, 1), ItemList.MSFMixture.get(4), MatUnifier.get(OrePrefixes.dustTiny, Materials.Sapphire), null, Materials.NitroFuel.getFluid(800), null, ItemList.Block_MSSFUEL.get(1), 120, 96);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Coal, 1), ItemList.MSFMixture.get(2), MatUnifier.get(OrePrefixes.dustTiny, Materials.Sapphire), null, Materials.NitroFuel.getFluid(500), null, ItemList.Block_MSSFUEL.get(1), 120, 96);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Lignite, 1), ItemList.MSFMixture.get(6), MatUnifier.get(OrePrefixes.dustTiny, Materials.GreenSapphire), null, Materials.NitroFuel.getFluid(1000), null, ItemList.Block_MSSFUEL.get(1), 120, 96);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Charcoal, 1), ItemList.MSFMixture.get(4), MatUnifier.get(OrePrefixes.dustTiny, Materials.GreenSapphire), null, Materials.NitroFuel.getFluid(800), null, ItemList.Block_MSSFUEL.get(1), 120, 96);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Coal, 1), ItemList.MSFMixture.get(2), MatUnifier.get(OrePrefixes.dustTiny, Materials.GreenSapphire), null, Materials.NitroFuel.getFluid(500), null, ItemList.Block_MSSFUEL.get(1), 120, 96);


        if (Loader.isModLoaded("Thaumcraft")) {
            GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(2), MatUnifier.get(OrePrefixes.dustTiny, Materials.InfusedAir), null, null, Materials.Mercury.getFluid(50), null, ItemList.MSFMixture.get(2), 100, 64);
            GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(2), MatUnifier.get(OrePrefixes.dustTiny, Materials.InfusedEarth), null, null, Materials.Mercury.getFluid(50), null, ItemList.MSFMixture.get(2), 100, 64);
            GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(2), MatUnifier.get(OrePrefixes.dustTiny, Materials.InfusedEntropy), null, null, Materials.Mercury.getFluid(50), null, ItemList.MSFMixture.get(2), 100, 64);
            GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(2), MatUnifier.get(OrePrefixes.dustTiny, Materials.InfusedFire), null, null, Materials.Mercury.getFluid(50), null, ItemList.MSFMixture.get(2), 100, 64);
            GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(2), MatUnifier.get(OrePrefixes.dustTiny, Materials.InfusedOrder), null, null, Materials.Mercury.getFluid(50), null, ItemList.MSFMixture.get(2), 100, 64);
            GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(2), MatUnifier.get(OrePrefixes.dustTiny, Materials.InfusedWater), null, null, Materials.Mercury.getFluid(50), null, ItemList.MSFMixture.get(2), 100, 64);

            FluidStack tFD = FluidRegistry.getFluidStack("fluiddeath", 10);
            if (tFD != null && tFD.getFluid() != null && tFD.amount > 0) {
                GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(8), MatUnifier.get(OrePrefixes.dustTiny, Materials.InfusedAir), null, null, tFD, null, ItemList.MSFMixture.get(8), 100, 64);
                GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(8), MatUnifier.get(OrePrefixes.dustTiny, Materials.InfusedEarth), null, null, tFD, null, ItemList.MSFMixture.get(8), 100, 64);
                GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(8), MatUnifier.get(OrePrefixes.dustTiny, Materials.InfusedEntropy), null, null, tFD, null, ItemList.MSFMixture.get(8), 100, 64);
                GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(8), MatUnifier.get(OrePrefixes.dustTiny, Materials.InfusedFire), null, null, tFD, null, ItemList.MSFMixture.get(8), 100, 64);
                GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(8), MatUnifier.get(OrePrefixes.dustTiny, Materials.InfusedOrder), null, null, tFD, null, ItemList.MSFMixture.get(8), 100, 64);
                GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(8), MatUnifier.get(OrePrefixes.dustTiny, Materials.InfusedWater), null, null, tFD, null, ItemList.MSFMixture.get(8), 100, 64);

                GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Lignite, 1), ItemList.MSFMixture.get(6), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.NitroFuel.getFluid(1000), null, ItemList.Block_MSSFUEL.get(1), 120, 96);
                GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Charcoal, 1), ItemList.MSFMixture.get(4), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.NitroFuel.getFluid(800), null, ItemList.Block_MSSFUEL.get(1), 120, 96);
                GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Coal, 1), ItemList.MSFMixture.get(2), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.NitroFuel.getFluid(500), null, ItemList.Block_MSSFUEL.get(1), 120, 96);

                GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Lignite, 1), ItemList.MSFMixture.get(6), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.HeavyFuel.getFluid(1500), null, ItemList.Block_MSSFUEL.get(1), 120, 96);
                GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Charcoal, 1), ItemList.MSFMixture.get(4), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.HeavyFuel.getFluid(1200), null, ItemList.Block_MSSFUEL.get(1), 120, 96);
                GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Coal, 1), ItemList.MSFMixture.get(2), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.HeavyFuel.getFluid(750), null, ItemList.Block_MSSFUEL.get(1), 120, 96);
            }
        }

        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Lignite, 1), ItemList.SFMixture.get(6), null, null, Materials.NitroFuel.getFluid(1000), null, ItemList.Block_SSFUEL.get(1), 120, 96);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Charcoal, 1), ItemList.SFMixture.get(4), null, null, Materials.NitroFuel.getFluid(800), null, ItemList.Block_SSFUEL.get(1), 120, 96);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Coal, 1), ItemList.SFMixture.get(2), null, null, Materials.NitroFuel.getFluid(500), null, ItemList.Block_SSFUEL.get(1), 120, 96);

        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Lignite, 1), ItemList.SFMixture.get(6), null, null, Materials.HeavyFuel.getFluid(1500), null, ItemList.Block_SSFUEL.get(1), 120, 96);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Charcoal, 1), ItemList.SFMixture.get(4), null, null, Materials.HeavyFuel.getFluid(1200), null, ItemList.Block_SSFUEL.get(1), 120, 96);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Coal, 1), ItemList.SFMixture.get(2), null, null, Materials.HeavyFuel.getFluid(750), null, ItemList.Block_SSFUEL.get(1), 120, 96);

        if (Loader.isModLoaded("Railcraft")) {
            GT_Values.RA.addMixerRecipe(EnumCube.COKE_BLOCK.getItem(), ItemList.SFMixture.get(1), null, null, Materials.NitroFuel.getFluid(250), null, ItemList.Block_SSFUEL.get(1), 120, 96);
            GT_Values.RA.addMixerRecipe(EnumCube.COKE_BLOCK.getItem(), ItemList.SFMixture.get(1), null, null, Materials.HeavyFuel.getFluid(375), null, ItemList.Block_SSFUEL.get(1), 120, 96);
            if (Loader.isModLoaded("Thaumcraft")) {
                GT_Values.RA.addMixerRecipe(EnumCube.COKE_BLOCK.getItem(), ItemList.MSFMixture.get(1), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.NitroFuel.getFluid(250), null, ItemList.Block_MSSFUEL.get(1), 120, 96);
                GT_Values.RA.addMixerRecipe(EnumCube.COKE_BLOCK.getItem(), ItemList.MSFMixture.get(1), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.HeavyFuel.getFluid(375), null, ItemList.Block_MSSFUEL.get(1), 120, 96);
            }
        }
        GT_Values.RA.addExtruderRecipe(ItemList.FR_Wax.get(1), ItemList.Shape_Extruder_Cell.get(0), ItemList.FR_WaxCapsule.get(1), 64, 16);
        GT_Values.RA.addExtruderRecipe(ItemList.FR_RefractoryWax.get(1), ItemList.Shape_Extruder_Cell.get(0), ItemList.FR_RefractoryCapsule.get(1), 128, 16);

        GT_Values.RA.addFluidCannerRecipe(ItemList.Battery_Hull_LV.get(1), ItemList.IC2_ReBattery.get(1), Materials.Redstone.getMolten(288), GT_Values.NF);
        GT_Values.RA.addFluidCannerRecipe(ItemList.Battery_Hull_LV.get(1), ItemList.Battery_SU_LV_Mercury.getWithCharge(1, Integer.MAX_VALUE), Materials.Mercury.getFluid(1000), GT_Values.NF);
        GT_Values.RA.addFluidCannerRecipe(ItemList.Battery_Hull_MV.get(1), ItemList.Battery_SU_MV_Mercury.getWithCharge(1, Integer.MAX_VALUE), Materials.Mercury.getFluid(4000), GT_Values.NF);
        GT_Values.RA.addFluidCannerRecipe(ItemList.Battery_Hull_HV.get(1), ItemList.Battery_SU_HV_Mercury.getWithCharge(1, Integer.MAX_VALUE), Materials.Mercury.getFluid(16000), GT_Values.NF);
        GT_Values.RA.addFluidCannerRecipe(ItemList.Battery_Hull_LV.get(1), ItemList.Battery_SU_LV_SulfuricAcid.getWithCharge(1, Integer.MAX_VALUE), Materials.SulfuricAcid.getFluid(1000), GT_Values.NF);
        GT_Values.RA.addFluidCannerRecipe(ItemList.Battery_Hull_MV.get(1), ItemList.Battery_SU_MV_SulfuricAcid.getWithCharge(1, Integer.MAX_VALUE), Materials.SulfuricAcid.getFluid(4000), GT_Values.NF);
        GT_Values.RA.addFluidCannerRecipe(ItemList.Battery_Hull_HV.get(1), ItemList.Battery_SU_HV_SulfuricAcid.getWithCharge(1, Integer.MAX_VALUE), Materials.SulfuricAcid.getFluid(16000), GT_Values.NF);
        //TODO GT_Values.RA.addFluidCannerRecipe(ItemList.TF_Vial_FieryTears.get(1L, new Object[0]), ItemList.Bottle_Empty.get(1L, new Object[0]), GT_Values.NF, Materials.FierySteel.getFluid(250L));

        Materials tMaterial = Materials.Iron;
        if (tMaterial.mStandardMoltenFluid != null) {
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Casing.get(0), tMaterial.getMolten(72), ItemList.IC2_Item_Casing_Iron.get(1), 16, 8);
        }
        tMaterial = Materials.WroughtIron;
        if (tMaterial.mStandardMoltenFluid != null) {
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Casing.get(0), tMaterial.getMolten(72), ItemList.IC2_Item_Casing_Iron.get(1), 16, 8);
        }
        tMaterial = Materials.Gold;
        if (tMaterial.mStandardMoltenFluid != null) {
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Casing.get(0), tMaterial.getMolten(72), ItemList.IC2_Item_Casing_Gold.get(1), 16, 8);
        }
        tMaterial = Materials.Bronze;
        if (tMaterial.mStandardMoltenFluid != null) {
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Casing.get(0), tMaterial.getMolten(72), ItemList.IC2_Item_Casing_Bronze.get(1), 16, 8);
        }
        tMaterial = Materials.Copper;
        if (tMaterial.mStandardMoltenFluid != null) {
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Casing.get(0), tMaterial.getMolten(72), ItemList.IC2_Item_Casing_Copper.get(1), 16, 8);
        }
        tMaterial = Materials.AnnealedCopper;
        if (tMaterial.mStandardMoltenFluid != null) {
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Casing.get(0), tMaterial.getMolten(72), ItemList.IC2_Item_Casing_Copper.get(1), 16, 8);
        }
        tMaterial = Materials.Tin;
        if (tMaterial.mStandardMoltenFluid != null) {
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Casing.get(0), tMaterial.getMolten(72), ItemList.IC2_Item_Casing_Tin.get(1), 16, 8);
        }
        tMaterial = Materials.Lead;
        if (tMaterial.mStandardMoltenFluid != null) {
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Casing.get(0), tMaterial.getMolten(72), ItemList.IC2_Item_Casing_Lead.get(1), 16, 8);
        }
        tMaterial = Materials.Steel;
        if (tMaterial.mStandardMoltenFluid != null) {
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Casing.get(0), tMaterial.getMolten(72), ItemList.IC2_Item_Casing_Steel.get(1), 16, 8);
        }
        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Ball.get(0), Materials.Mercury.getFluid(1000), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 1, 3), 128, 4);
        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Ball.get(0), Materials.Mercury.getFluid(1000), MatUnifier.get(OrePrefixes.gem, Materials.Mercury), 128, 4);
        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Ball.get(0), Materials.Water.getFluid(250), new ItemStack(Items.snowball, 1, 0), 128, 4);
        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Ball.get(0), GT_ModHandler.getDistilledWater(250), new ItemStack(Items.snowball, 1, 0), 128, 4);
        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Block.get(0), Materials.Water.getFluid(1000), new ItemStack(Blocks.snow, 1, 0), 512, 4);
        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Block.get(0), GT_ModHandler.getDistilledWater(1000), new ItemStack(Blocks.snow, 1, 0), 512, 4);
        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Block.get(0), Materials.Lava.getFluid(1000), new ItemStack(Blocks.obsidian, 1, 0), 1024, 16);
        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Block.get(0), Materials.Concrete.getMolten(144), new ItemStack(GregTech_API.sBlockConcretes, 1, 8), 12, 4);
        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Block.get(0), Materials.Glowstone.getMolten(576), new ItemStack(Blocks.glowstone, 1, 0), 12, 4);
        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Block.get(0), Materials.Glass.getMolten(144), new ItemStack(Blocks.glass, 1, 0), 12, 4);
        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Plate.get(0), Materials.Glass.getMolten(144), MatUnifier.get(OrePrefixes.plate, Materials.Glass), 12, 4);
        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Bottle.get(0), Materials.Glass.getMolten(144), new ItemStack(Items.glass_bottle, 1), 12, 4);
        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Anvil.get(0), Materials.Iron.getMolten(4464), new ItemStack(Blocks.anvil, 1, 0), 128, 16);
        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Anvil.get(0), Materials.WroughtIron.getMolten(4464), new ItemStack(Blocks.anvil, 1, 0), 128, 16);
        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Anvil.get(0), Materials.Steel.getMolten(4464L), GT_ModHandler.getModItem("Railcraft", "tile.railcraft.anvil", 0), 128, 16);

        GT_Values.RA.addChemicalBathRecipe(GT_ModHandler.getIC2Item("dynamite", 1), Materials.Glue.getFluid(10L), GT_ModHandler.getIC2Item("stickyDynamite", 1), GT_Values.NI, GT_Values.NI, null, 16, 4);
        GT_Values.RA.addChemicalRecipe(new ItemStack(Items.paper, 1), new ItemStack(Items.string, 1), Materials.Glyceryl.getFluid(500), GT_Values.NF, GT_ModHandler.getIC2Item("dynamite", 1), 160, 4);
        GT_Values.RA.addChemicalBathRecipe(MatUnifier.get(OrePrefixes.frameGt, Materials.Steel), Materials.Concrete.getMolten(144L), GT_ModHandler.getIC2Item("reinforcedStone", 1), GT_Values.NI, GT_Values.NI, null, 200, 4);
        //GT_Values.RA.addChemicalBathRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Coal), Materials.Water.getFluid(125L), MatUnifier.get(OrePrefixes.dust, Materials.HydratedCoal), GT_Values.NI, GT_Values.NI, null, 12, 4);
        GT_Values.RA.addChemicalBathRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Wood), Materials.Water.getFluid(100L), new ItemStack(Items.paper, 1, 0), GT_Values.NI, GT_Values.NI, null, 200, 4);
        GT_Values.RA.addChemicalBathRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Paper), Materials.Water.getFluid(100L), new ItemStack(Items.paper, 1, 0), GT_Values.NI, GT_Values.NI, null, 100, 4);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Items.reeds, 1, 32767), Materials.Water.getFluid(100L), new ItemStack(Items.paper, 1, 0), GT_Values.NI, GT_Values.NI, null, 100, 8);
        //GT_Values.RA.addChemicalBathRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Coal), GT_ModHandler.getDistilledWater(125L), MatUnifier.get(OrePrefixes.dust, Materials.HydratedCoal), GT_Values.NI, GT_Values.NI, null, 12, 4);
        GT_Values.RA.addChemicalBathRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Wood), GT_ModHandler.getDistilledWater(100L), new ItemStack(Items.paper, 1, 0), GT_Values.NI, GT_Values.NI, null, 200, 4);
        GT_Values.RA.addChemicalBathRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Paper), GT_ModHandler.getDistilledWater(100L), new ItemStack(Items.paper, 1, 0), GT_Values.NI, GT_Values.NI, null, 100, 4);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Items.reeds, 1, 32767), GT_ModHandler.getDistilledWater(100L), new ItemStack(Items.paper, 1, 0), GT_Values.NI, GT_Values.NI, null, 100, 8);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.wool, 1, 1), Materials.Chlorine.getGas(50L), new ItemStack(Blocks.wool, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.wool, 1, 2), Materials.Chlorine.getGas(50L), new ItemStack(Blocks.wool, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.wool, 1, 3), Materials.Chlorine.getGas(50L), new ItemStack(Blocks.wool, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.wool, 1, 4), Materials.Chlorine.getGas(50L), new ItemStack(Blocks.wool, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.wool, 1, 5), Materials.Chlorine.getGas(50L), new ItemStack(Blocks.wool, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.wool, 1, 6), Materials.Chlorine.getGas(50L), new ItemStack(Blocks.wool, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.wool, 1, 7), Materials.Chlorine.getGas(50L), new ItemStack(Blocks.wool, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.wool, 1, 8), Materials.Chlorine.getGas(50L), new ItemStack(Blocks.wool, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.wool, 1, 9), Materials.Chlorine.getGas(50L), new ItemStack(Blocks.wool, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.wool, 1, 10), Materials.Chlorine.getGas(50L), new ItemStack(Blocks.wool, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.wool, 1, 11), Materials.Chlorine.getGas(50L), new ItemStack(Blocks.wool, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.wool, 1, 12), Materials.Chlorine.getGas(50L), new ItemStack(Blocks.wool, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.wool, 1, 13), Materials.Chlorine.getGas(50L), new ItemStack(Blocks.wool, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.wool, 1, 14), Materials.Chlorine.getGas(50L), new ItemStack(Blocks.wool, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.wool, 1, 15), Materials.Chlorine.getGas(50L), new ItemStack(Blocks.wool, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.carpet, 1, 1), Materials.Chlorine.getGas(25L), new ItemStack(Blocks.carpet, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.carpet, 1, 2), Materials.Chlorine.getGas(25L), new ItemStack(Blocks.carpet, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.carpet, 1, 3), Materials.Chlorine.getGas(25L), new ItemStack(Blocks.carpet, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.carpet, 1, 4), Materials.Chlorine.getGas(25L), new ItemStack(Blocks.carpet, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.carpet, 1, 5), Materials.Chlorine.getGas(25L), new ItemStack(Blocks.carpet, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.carpet, 1, 6), Materials.Chlorine.getGas(25L), new ItemStack(Blocks.carpet, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.carpet, 1, 7), Materials.Chlorine.getGas(25L), new ItemStack(Blocks.carpet, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.carpet, 1, 8), Materials.Chlorine.getGas(25L), new ItemStack(Blocks.carpet, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.carpet, 1, 9), Materials.Chlorine.getGas(25L), new ItemStack(Blocks.carpet, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.carpet, 1, 10), Materials.Chlorine.getGas(25L), new ItemStack(Blocks.carpet, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.carpet, 1, 11), Materials.Chlorine.getGas(25L), new ItemStack(Blocks.carpet, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.carpet, 1, 12), Materials.Chlorine.getGas(25L), new ItemStack(Blocks.carpet, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.carpet, 1, 13), Materials.Chlorine.getGas(25L), new ItemStack(Blocks.carpet, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.carpet, 1, 14), Materials.Chlorine.getGas(25L), new ItemStack(Blocks.carpet, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.carpet, 1, 15), Materials.Chlorine.getGas(25L), new ItemStack(Blocks.carpet, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.stained_hardened_clay, 1, 32767), Materials.Chlorine.getGas(50L), new ItemStack(Blocks.hardened_clay, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.stained_glass, 1, 32767), Materials.Chlorine.getGas(50L), new ItemStack(Blocks.glass, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.stained_glass_pane, 1, 32767), Materials.Chlorine.getGas(20L), new ItemStack(Blocks.glass_pane, 1, 0), GT_Values.NI, GT_Values.NI, null, 400, 2);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 8), Materials.Water.getFluid(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 0), GT_Values.NI, GT_Values.NI, null, 200, 4);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 9), Materials.Water.getFluid(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 1), GT_Values.NI, GT_Values.NI, null, 200, 4);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 10), Materials.Water.getFluid(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 2), GT_Values.NI, GT_Values.NI, null, 200, 4);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 11), Materials.Water.getFluid(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 3), GT_Values.NI, GT_Values.NI, null, 200, 4);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 12), Materials.Water.getFluid(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 4), GT_Values.NI, GT_Values.NI, null, 200, 4);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 13), Materials.Water.getFluid(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 5), GT_Values.NI, GT_Values.NI, null, 200, 4);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 14), Materials.Water.getFluid(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 6), GT_Values.NI, GT_Values.NI, null, 200, 4);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 15), Materials.Water.getFluid(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 7), GT_Values.NI, GT_Values.NI, null, 200, 4);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 8), GT_ModHandler.getDistilledWater(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 0), GT_Values.NI, GT_Values.NI, null, 200, 4);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 9), GT_ModHandler.getDistilledWater(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 1), GT_Values.NI, GT_Values.NI, null, 200, 4);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 10), GT_ModHandler.getDistilledWater(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 2), GT_Values.NI, GT_Values.NI, null, 200, 4);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 11), GT_ModHandler.getDistilledWater(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 3), GT_Values.NI, GT_Values.NI, null, 200, 4);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 12), GT_ModHandler.getDistilledWater(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 4), GT_Values.NI, GT_Values.NI, null, 200, 4);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 13), GT_ModHandler.getDistilledWater(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 5), GT_Values.NI, GT_Values.NI, null, 200, 4);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 14), GT_ModHandler.getDistilledWater(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 6), GT_Values.NI, GT_Values.NI, null, 200, 4);
        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 15), GT_ModHandler.getDistilledWater(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 7), GT_Values.NI, GT_Values.NI, null, 200, 4);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.frameGt, Materials.BlackSteel), MatUnifier.get(OrePrefixes.dust, Materials.Plastic), Materials.Concrete.getMolten(144L), ItemList.Block_Plascrete.get(1), 200, 48);
        GT_Values.RA.addChemicalBathRecipe(MatUnifier.get(OrePrefixes.frameGt, Materials.TungstenSteel), Materials.Concrete.getMolten(144L), ItemList.Block_TungstenSteelReinforced.get(1), GT_Values.NI, GT_Values.NI, null, 200, 4);


        for (int j = 0; j < Dyes.dyeRed.getSizeOfFluidList(); j++) {
            GT_Values.RA.addChemicalBathRecipe(MatUnifier.get(OrePrefixes.wireGt01, Materials.RedAlloy), Dyes.dyeRed.getFluidDye(j, 72L), GT_ModHandler.getModItem("BuildCraft|Transport", "pipeWire", 4, 0), GT_Values.NI, GT_Values.NI, null, 32, 16);
        }
        for (int j = 0; j < Dyes.dyeBlue.getSizeOfFluidList(); j++) {
            GT_Values.RA.addChemicalBathRecipe(MatUnifier.get(OrePrefixes.wireGt01, Materials.RedAlloy), Dyes.dyeBlue.getFluidDye(j, 72L), GT_ModHandler.getModItem("BuildCraft|Transport", "pipeWire", 4, 1), GT_Values.NI, GT_Values.NI, null, 32, 16);
        }
        for (int j = 0; j < Dyes.dyeGreen.getSizeOfFluidList(); j++) {
            GT_Values.RA.addChemicalBathRecipe(MatUnifier.get(OrePrefixes.wireGt01, Materials.RedAlloy), Dyes.dyeGreen.getFluidDye(j, 72L), GT_ModHandler.getModItem("BuildCraft|Transport", "pipeWire", 4, 2), GT_Values.NI, GT_Values.NI, null, 32, 16);
        }
        for (int j = 0; j < Dyes.dyeYellow.getSizeOfFluidList(); j++) {
            GT_Values.RA.addChemicalBathRecipe(MatUnifier.get(OrePrefixes.wireGt01, Materials.RedAlloy), Dyes.dyeYellow.getFluidDye(j, 72L), GT_ModHandler.getModItem("BuildCraft|Transport", "pipeWire", 4, 3), GT_Values.NI, GT_Values.NI, null, 32, 16);
        }
        for (byte i = 0; i < 16; i = (byte) (i + 1)) {
            for (int j = 0; j < Dyes.VALUES[i].getSizeOfFluidList(); j++) {
                if (i != 15) {
                    GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.wool, 1, 0), Dyes.VALUES[i].getFluidDye(j, 72L), new ItemStack(Blocks.wool, 1, 15 - i), GT_Values.NI, GT_Values.NI, null, 64, 2);
                }
                GT_Values.RA.addAssemblerRecipe(new ItemStack(Items.string, 3), ItemList.Circuit_Integrated.getWithDamage(0, 3), Dyes.VALUES[i].getFluidDye(j, 24L), new ItemStack(Blocks.carpet, 2, 15 - i), 128, 5);
                GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.glass, 1, 0), Dyes.VALUES[i].getFluidDye(j, 18L), new ItemStack(Blocks.stained_glass, 1, 15 - i), GT_Values.NI, GT_Values.NI, null, 64, 2);
                GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.hardened_clay, 1, 0), Dyes.VALUES[i].getFluidDye(j, 18L), new ItemStack(Blocks.stained_hardened_clay, 1, 15 - i), GT_Values.NI, GT_Values.NI, null, 64, 2);
            }
        }
        GT_Values.RA.addFluidExtractionRecipe(ItemList.Dye_SquidInk.get(1), GT_Values.NI, FluidRegistry.getFluidStack("squidink", 144), 10000, 128, 4);
        GT_Values.RA.addFluidExtractionRecipe(ItemList.Dye_Indigo.get(1), GT_Values.NI, FluidRegistry.getFluidStack("indigo", 144), 10000, 128, 4);
        GT_Values.RA.addFluidExtractionRecipe(ItemList.Crop_Drop_Indigo.get(1), GT_Values.NI, FluidRegistry.getFluidStack("indigo", 144), 10000, 128, 4);
        GT_Values.RA.addFluidExtractionRecipe(ItemList.Crop_Drop_MilkWart.get(1), MatUnifier.get(OrePrefixes.dust, Materials.Milk, 1), GT_ModHandler.getMilk(150), 1000, 128, 4);
        GT_Values.RA.addFluidExtractionRecipe(ItemList.Crop_Drop_OilBerry.get(1), GT_Values.NI, Materials.Oil.getFluid(100L), 10000, 128, 4);
        GT_Values.RA.addFluidExtractionRecipe(ItemList.Crop_Drop_UUMBerry.get(1), GT_Values.NI, Materials.UUMatter.getFluid(4L), 10000, 128, 4);
        GT_Values.RA.addFluidExtractionRecipe(ItemList.Crop_Drop_UUABerry.get(1), GT_Values.NI, Materials.UUAmplifier.getFluid(4L), 10000, 128, 4);
        GT_Values.RA.addFluidExtractionRecipe(new ItemStack(Items.fish, 1, 0), GT_Values.NI, Materials.FishOil.getFluid(40), 10000, 16, 4);
        GT_Values.RA.addFluidExtractionRecipe(new ItemStack(Items.fish, 1, 1), GT_Values.NI, Materials.FishOil.getFluid(60), 10000, 16, 4);
        GT_Values.RA.addFluidExtractionRecipe(new ItemStack(Items.fish, 1, 2), GT_Values.NI, Materials.FishOil.getFluid(70), 10000, 16, 4);
        GT_Values.RA.addFluidExtractionRecipe(new ItemStack(Items.fish, 1, 3), GT_Values.NI, Materials.FishOil.getFluid(30), 10000, 16, 4);
        GT_Values.RA.addFluidExtractionRecipe(new ItemStack(Items.coal, 1, 1), MatUnifier.get(OrePrefixes.dust, Materials.Ash, 1), Materials.WoodTar.getFluid(100L), 1000, 128, 4);
        GT_Values.RA.addFluidExtractionRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Wood, 1), ItemList.IC2_Plantball.get(1), Materials.Creosote.getFluid(5L), 100, 16, 4);
        //GT_Values.RA.addFluidExtractionRecipe(MatUnifier.get(OrePrefixes.dust, Materials.HydratedCoal), MatUnifier.get(OrePrefixes.dust, Materials.Coal), Materials.Water.getFluid(100L), 10000, 32, 4);
        GT_Values.RA.addFluidExtractionRecipe(GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 1, 3), GT_Values.NI, Materials.Mercury.getFluid(1000L), 10000, 128, 4);
        GT_Values.RA.addFluidExtractionRecipe(MatUnifier.get(OrePrefixes.gem, Materials.Mercury), GT_Values.NI, Materials.Mercury.getFluid(1000L), 10000, 128, 4);
        GT_Values.RA.addFluidExtractionRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Monazite), GT_Values.NI, Materials.Helium.getGas(200L), 10000, 64, 64);

        GT_Values.RA.addFluidSmelterRecipe(new ItemStack(Items.snowball, 1, 0), GT_Values.NI, Materials.Water.getFluid(250L), 10000, 32, 4);
        GT_Values.RA.addFluidSmelterRecipe(new ItemStack(Blocks.snow, 1, 0), GT_Values.NI, Materials.Water.getFluid(1000L), 10000, 128, 4);
        GT_Values.RA.addFluidSmelterRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Ice), GT_Values.NI, Materials.Ice.getSolid(1000L), 10000, 128, 4);
        GT_Values.RA.addFluidSmelterRecipe(GT_ModHandler.getModItem(aTextForestry, "phosphor", 1), MatUnifier.get(OrePrefixes.dust, Materials.Phosphor, 1), Materials.Lava.getFluid(800L), 1000, 256, 128);

        GT_Values.RA.addAutoclaveRecipe(ItemList.IC2_Energium_Dust.get(9), Materials.Water.getFluid(1000L), ItemList.IC2_EnergyCrystal.get(1), 10000, 500, 256);
        GT_Values.RA.addAutoclaveRecipe(GT_ModHandler.getModItem(aTextAE, "item.ItemCrystalSeed", 1, 0), Materials.Water.getFluid(200L), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 10), 10000, 2000, 24);
        GT_Values.RA.addAutoclaveRecipe(GT_ModHandler.getModItem(aTextAE, "item.ItemCrystalSeed", 1, 600), Materials.Water.getFluid(200L), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 11), 10000, 2000, 24);
        GT_Values.RA.addAutoclaveRecipe(GT_ModHandler.getModItem(aTextAE, "item.ItemCrystalSeed", 1, 1200), Materials.Water.getFluid(200L), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 12), 10000, 2000, 24);
        GT_Values.RA.addAutoclaveRecipe(ItemList.IC2_Energium_Dust.get(9), GT_ModHandler.getDistilledWater(1000L), ItemList.IC2_EnergyCrystal.get(1), 10000, 250, 256);
        GT_Values.RA.addAutoclaveRecipe(GT_ModHandler.getModItem(aTextAE, "item.ItemCrystalSeed", 1, 0), GT_ModHandler.getDistilledWater(200L), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 10), 10000, 1000, 24);
        GT_Values.RA.addAutoclaveRecipe(GT_ModHandler.getModItem(aTextAE, "item.ItemCrystalSeed", 1, 600), GT_ModHandler.getDistilledWater(200L), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 11), 10000, 1000, 24);
        GT_Values.RA.addAutoclaveRecipe(GT_ModHandler.getModItem(aTextAE, "item.ItemCrystalSeed", 1, 1200), GT_ModHandler.getDistilledWater(200L), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 12), 10000, 1000, 24);
        GT_Values.RA.addAutoclaveRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Carbon, 16), Materials.Palladium.getMolten(4), GT_ModHandler.getIC2Item("carbonFiber", 8), 9000, 600, 5);
        GT_Values.RA.addAutoclaveRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Carbon, 16), Materials.Platinum.getMolten(4), GT_ModHandler.getIC2Item("carbonFiber", 8), 5000, 600, 5);
        GT_Values.RA.addAutoclaveRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Carbon, 16), Materials.Lutetium.getMolten(4), GT_ModHandler.getIC2Item("carbonFiber", 8), 3333, 600, 5);
        GT_Values.RA.addAutoclaveRecipe(MatUnifier.get(OrePrefixes.dust, Materials.NetherStar, 1), Materials.UUMatter.getFluid(576), MatUnifier.get(OrePrefixes.gem, Materials.NetherStar, 1), 3333, 72000, 480);

        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.EnderPearl, 1), OrePrefixes.circuit.get(Materials.Basic), 4, Materials.Osmium.getMolten(288), ItemList.Field_Generator_LV.get(1), 1800, 30);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.EnderEye, 1), OrePrefixes.circuit.get(Materials.Good), 4, Materials.Osmium.getMolten(576), ItemList.Field_Generator_MV.get(1), 1800, 120);
        GT_Values.RA.addAssemblerRecipe(ItemList.QuantumEye.get(1), OrePrefixes.circuit.get(Materials.Advanced), 4, Materials.Osmium.getMolten(1152), ItemList.Field_Generator_HV.get(1), 1800, 480);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.NetherStar, 1), OrePrefixes.circuit.get(Materials.Data), 4, Materials.Osmium.getMolten(2304), ItemList.Field_Generator_EV.get(1), 1800, 1920);
        GT_Values.RA.addAssemblerRecipe(ItemList.QuantumStar.get(1), OrePrefixes.circuit.get(Materials.Elite), 4, Materials.Osmium.getMolten(4608), ItemList.Field_Generator_IV.get(1), 1800, 7680);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.wireFine, Materials.Steel, 64), MatUnifier.get(OrePrefixes.foil, Materials.Zinc, 16), null, ItemList.Component_Filter.get(1), 1600, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Graphite, 8), MatUnifier.get(OrePrefixes.foil, Materials.Silicon, 1), Materials.Glue.getFluid(250L), MatUnifier.get(OrePrefixes.dustSmall, Materials.Graphene, 1), 480, 240);
        GT_Values.RA.addAssemblerRecipe(ItemList.Electric_Pump_LV.get(1), OrePrefixes.circuit.get(Materials.Basic), 2, GT_Values.NF, ItemList.FluidRegulator_LV.get(1), 800, 4);
        GT_Values.RA.addAssemblerRecipe(ItemList.Electric_Pump_MV.get(1), OrePrefixes.circuit.get(Materials.Good), 2, GT_Values.NF, ItemList.FluidRegulator_MV.get(1), 800, 8);
        GT_Values.RA.addAssemblerRecipe(ItemList.Electric_Pump_HV.get(1), OrePrefixes.circuit.get(Materials.Advanced), 2, GT_Values.NF, ItemList.FluidRegulator_HV.get(1), 800, 16);
        GT_Values.RA.addAssemblerRecipe(ItemList.Electric_Pump_EV.get(1), OrePrefixes.circuit.get(Materials.Data), 2, GT_Values.NF, ItemList.FluidRegulator_EV.get(1), 800, 32);
        GT_Values.RA.addAssemblerRecipe(ItemList.Electric_Pump_IV.get(1), OrePrefixes.circuit.get(Materials.Elite), 2, GT_Values.NF, ItemList.FluidRegulator_IV.get(1), 800, 64);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.StainlessSteel, 2), OrePrefixes.circuit.get(Materials.Good), 4, GT_Values.NF, ItemList.Schematic.get(1), 3200, 4);
        GT_Values.RA.addAssemblerRecipe(ItemList.Cover_Shutter.get(1), OrePrefixes.circuit.get(Materials.Advanced), 2, GT_Values.NF, ItemList.FluidFilter.get(1), 800, 4);

        GT_Values.RA.addCentrifugeRecipe(ItemList.Cell_Empty.get(1), null, Materials.Air.getGas(10000), Materials.Nitrogen.getGas(3900), MatUnifier.get(OrePrefixes.cell, Materials.Oxygen, 1), null, null, null, null, null, null, 1600, 8);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.crushedPurified, Materials.Galena, 3), MatUnifier.get(OrePrefixes.crushedPurified, Materials.Sphalerite, 1), Materials.SulfuricAcid.getFluid(4000), new FluidStack(ItemList.sIndiumConcentrate, 8000), null, 60, 150);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Aluminium, 4), null, new FluidStack(ItemList.sIndiumConcentrate, 8000), new FluidStack(ItemList.sLeadZincSolution, 8000), MatUnifier.get(OrePrefixes.dustTiny, Materials.Indium, 1), 50, 600);
        GT_Values.RA.addElectrolyzerRecipe(null, null, new FluidStack(ItemList.sLeadZincSolution, 8000), Materials.Water.getFluid(2000), MatUnifier.get(OrePrefixes.dust, Materials.Lead, 1), MatUnifier.get(OrePrefixes.dust, Materials.Silver, 1), MatUnifier.get(OrePrefixes.dust, Materials.Zinc, 1), MatUnifier.get(OrePrefixes.dust, Materials.Sulfur, 3), null, null, null, 300, 192);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.crushedPurified, Materials.Pentlandite, 1), null, new FluidStack(ItemList.sNitricAcid, 8000), new FluidStack(ItemList.sNickelSulfate, 9000), MatUnifier.get(OrePrefixes.dustTiny, Materials.PlatinumGroupSludge, 1), 50, 30);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.crushedPurified, Materials.Chalcopyrite, 1), null, new FluidStack(ItemList.sNitricAcid, 8000), new FluidStack(ItemList.sBlueVitriol, 9000), MatUnifier.get(OrePrefixes.dustTiny, Materials.PlatinumGroupSludge, 1), 50, 30);
        GT_Values.RA.addElectrolyzerRecipe(ItemList.Cell_Empty.get(1), null, new FluidStack(ItemList.sBlueVitriol, 9000), Materials.SulfuricAcid.getFluid(8000), MatUnifier.get(OrePrefixes.dust, Materials.Copper, 1), MatUnifier.get(OrePrefixes.cell, Materials.Oxygen, 1), null, null, null, null, null, 900, 30);
        GT_Values.RA.addElectrolyzerRecipe(ItemList.Cell_Empty.get(1), null, new FluidStack(ItemList.sNickelSulfate, 9000), Materials.SulfuricAcid.getFluid(8000), MatUnifier.get(OrePrefixes.dust, Materials.Nickel, 1), MatUnifier.get(OrePrefixes.cell, Materials.Oxygen, 1), null, null, null, null, null, 900, 30);
        GT_Values.RA.addCentrifugeRecipe(MatUnifier.get(OrePrefixes.dust, Materials.PlatinumGroupSludge, 1), null, null, null, MatUnifier.get(OrePrefixes.dust, Materials.SiliconDioxide, 1), MatUnifier.get(OrePrefixes.dustTiny, Materials.Gold, 1), MatUnifier.get(OrePrefixes.dustTiny, Materials.Platinum, 1), MatUnifier.get(OrePrefixes.dustTiny, Materials.Palladium, 1), MatUnifier.get(OrePrefixes.dustTiny, Materials.Iridium, 1), MatUnifier.get(OrePrefixes.dustTiny, Materials.Osmium, 1), new int[]{10000, 10000, 10000, 8000, 6000, 6000}, 900, 30);

        //Circuit Recipes!!!
        GT_ModHandler.addCraftingRecipe(ItemList.Circuit_Board_Coated.get(3), new Object[]{" R ", "PPP", " R ", 'P', MatUnifier.get(OrePrefixes.plate, Materials.Wood, 1), 'R', ItemList.IC2_Resin.get(1)});
        GT_ModHandler.addCraftingRecipe(ItemList.Circuit_Board_Phenolic.get(8), new Object[]{"PRP", "PPP", "PPP", 'P', MatUnifier.get(OrePrefixes.dust, Materials.Wood, 1), 'R', MatUnifier.get(OrePrefixes.cell, Materials.Glue, 1)});
        GT_ModHandler.addCraftingRecipe(ItemList.Circuit_Board_Phenolic.get(32), new Object[]{"PRP", "PPP", "PPP", 'P', MatUnifier.get(OrePrefixes.dust, Materials.Wood, 1), 'R', MatUnifier.get(OrePrefixes.cell, Materials.BisphenolA, 1)});
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Epoxid, 1), MatUnifier.get(OrePrefixes.foil, Materials.Copper, 1), Materials.SulfuricAcid.getFluid(125), null, ItemList.Circuit_Board_Epoxy.get(1), 500, 10);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Glass, 1), MatUnifier.get(OrePrefixes.dust, Materials.Carbon, 1), Materials.Electrum.getMolten(16), null, ItemList.Circuit_Board_Fiberglass.get(16), null, 80, 480, 2600);
        GT_Values.RA.addChemicalRecipe(ItemList.Circuit_Board_Fiberglass.get(1), MatUnifier.get(OrePrefixes.foil, Materials.Electrum, 16), Materials.SulfuricAcid.getFluid(250), null, ItemList.Circuit_Board_Multifiberglass.get(1), 100, 480);
        GT_ModHandler.addCraftingRecipe(ItemList.Circuit_Board_Wetware.get(1), new Object[]{"DLD", "PBP", "TPT", 'T', MatUnifier.get(OrePrefixes.plate, Materials.Polytetrafluoroethylene, 1), 'L', ItemList.Electric_Pump_LV.get(1), 'P', MatUnifier.get(OrePrefixes.ingot, Materials.Silicone, 1), 'D', ItemList.Bottle_Dragon_Blood.get(1), 'B', ItemList.Circuit_Board_Multifiberglass.get(1)});

        GT_ModHandler.addCraftingRecipe(ItemList.Circuit_Parts_Resistor.get(3), new Object[]{" P ", "FCF", " P ", 'P', new ItemStack(Items.paper), 'F', OrePrefixes.wireGt01.get(Materials.Copper), 'C', OrePrefixes.dust.get(Materials.Coal)});
        GT_ModHandler.addCraftingRecipe(ItemList.Circuit_Parts_Resistor.get(3), new Object[]{" P ", "FCF", " P ", 'P', new ItemStack(Items.paper), 'F', OrePrefixes.wireFine.get(Materials.Copper), 'C', OrePrefixes.dust.get(Materials.Coal)});
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Coal, 1), MatUnifier.get(OrePrefixes.wireFine, Materials.Copper, 4), ItemList.Circuit_Parts_Resistor.get(12), 160, 6);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Carbon, 1), MatUnifier.get(OrePrefixes.wireFine, Materials.Electrum, 4), Materials.Plastic.getMolten(144), ItemList.Circuit_Parts_ResistorSMD.get(24), 80, 96);
        GT_Values.RA.addAlloySmelterRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Glass, 1), ItemList.Shape_Mold_Ball.get(0), ItemList.Circuit_Parts_Glass_Tube.get(1), 240, 8);
        GT_ModHandler.addCraftingRecipe(ItemList.Circuit_Parts_Vacuum_Tube.get(1), new Object[]{"PGP", "FFF", 'G', ItemList.Circuit_Parts_Glass_Tube, 'P', new ItemStack(Items.paper), 'F', OrePrefixes.wireFine.get(Materials.Copper)});
        GT_ModHandler.addCraftingRecipe(ItemList.Circuit_Parts_Vacuum_Tube.get(1), new Object[]{"PGP", "FFF", 'G', ItemList.Circuit_Parts_Glass_Tube, 'P', new ItemStack(Items.paper), 'F', OrePrefixes.wireGt01.get(Materials.Copper)});
        GT_ModHandler.addCraftingRecipe(ItemList.Circuit_Parts_Diode.get(1), new Object[]{"BG ", "WDW", "BG ", 'B', OrePrefixes.dye.get(Materials.Black), 'G', new ItemStack(Blocks.glass_pane), 'D', ItemList.Circuit_Silicon_Wafer.get(1), 'W', OrePrefixes.wireGt01.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(ItemList.Circuit_Parts_Diode.get(1), new Object[]{"BG ", "WDW", "BG ", 'B', OrePrefixes.dye.get(Materials.Black), 'G', new ItemStack(Blocks.glass_pane), 'D', ItemList.Circuit_Silicon_Wafer.get(1), 'W', OrePrefixes.wireFine.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(ItemList.Circuit_Parts_Diode.get(4), new Object[]{"BG ", "WDW", "BG ", 'B', OrePrefixes.dye.get(Materials.Black), 'G', new ItemStack(Blocks.glass_pane), 'D', MatUnifier.get(OrePrefixes.dustTiny, Materials.Gallium, 1), 'W', OrePrefixes.wireGt01.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(ItemList.Circuit_Parts_Diode.get(4), new Object[]{"BG ", "WDW", "BG ", 'B', OrePrefixes.dye.get(Materials.Black), 'G', new ItemStack(Blocks.glass_pane), 'D', MatUnifier.get(OrePrefixes.dustTiny, Materials.Gallium, 1), 'W', OrePrefixes.wireFine.get(Materials.Tin)});
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.wireFine, Materials.AnnealedCopper, 4), MatUnifier.get(OrePrefixes.dustSmall, Materials.Gallium, 1), Materials.Glass.getMolten(288), ItemList.Circuit_Parts_Diode.get(16), 400, 48);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.wireFine, Materials.Platinum, 4), MatUnifier.get(OrePrefixes.dustSmall, Materials.Gallium, 1), Materials.Glass.getMolten(288), ItemList.Circuit_Parts_DiodeSMD.get(32), 400, 192);
        GT_ModHandler.addCraftingRecipe(ItemList.Circuit_Parts_Coil.get(1), new Object[]{"WWW", "WDW", "WWW", 'G', new ItemStack(Blocks.glass_pane), 'D', ItemList.IC2_Item_Casing_Steel.get(1), 'W', OrePrefixes.wireFine.get(Materials.Copper)});
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Silicon, 1), MatUnifier.get(OrePrefixes.wireFine, Materials.Tin, 6), Materials.Plastic.getMolten(144), ItemList.Circuit_Parts_Transistor.get(8), 80, 24);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Gallium, 1), MatUnifier.get(OrePrefixes.wireFine, Materials.AnnealedCopper, 6), Materials.Plastic.getMolten(288), ItemList.Circuit_Parts_TransistorSMD.get(32), 80, 96);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Plastic, 1), MatUnifier.get(OrePrefixes.foil, Materials.Aluminium, 2), ItemList.Circuit_Parts_Capacitor.get(2), 80, 96);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.foil, Materials.Silicone, 8), MatUnifier.get(OrePrefixes.foil, Materials.Aluminium, 2), Materials.Plastic.getMolten(72), ItemList.Circuit_Parts_CapacitorSMD.get(8), 100, 480);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.foil, Materials.PolyvinylChloride, 8), MatUnifier.get(OrePrefixes.foil, Materials.Aluminium, 2), Materials.Plastic.getMolten(72), ItemList.Circuit_Parts_CapacitorSMD.get(8), 100, 480);

        GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer2.get(1), GT_Utility.copyAmount(0, MatUnifier.get(OrePrefixes.lens, Materials.EnderPearl, 1)), ItemList.Circuit_Wafer_NAND.get(1), 500, 480, true);
        GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer3.get(1), GT_Utility.copyAmount(0, MatUnifier.get(OrePrefixes.lens, Materials.EnderPearl, 1)), ItemList.Circuit_Wafer_NAND.get(4), 200, 1920, true);
        GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer2.get(1), GT_Utility.copyAmount(0, MatUnifier.get(OrePrefixes.lens, Materials.EnderEye, 1)), ItemList.Circuit_Wafer_NOR.get(1), 500, 480, true);
        GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer3.get(1), GT_Utility.copyAmount(0, MatUnifier.get(OrePrefixes.lens, Materials.EnderEye, 1)), ItemList.Circuit_Wafer_NOR.get(4), 200, 1920, true);
        GT_Values.RA.addCutterRecipe(ItemList.Circuit_Wafer_ILC.get(1), ItemList.Circuit_Chip_ILC.get(8), null, 600, 48);
        GT_Values.RA.addCutterRecipe(ItemList.Circuit_Wafer_Ram.get(1), ItemList.Circuit_Chip_Ram.get(32), null, 600, 48);
        GT_Values.RA.addCutterRecipe(ItemList.Circuit_Wafer_NAND.get(1), ItemList.Circuit_Chip_NAND.get(32), null, 600, 48);
        GT_Values.RA.addCutterRecipe(ItemList.Circuit_Wafer_NOR.get(1), ItemList.Circuit_Chip_NOR.get(16), null, 600, 48);
        GT_Values.RA.addCutterRecipe(ItemList.Circuit_Wafer_CPU.get(1), ItemList.Circuit_Chip_CPU.get(8), null, 600, 48);
        GT_Values.RA.addCutterRecipe(ItemList.Circuit_Wafer_SoC.get(1), ItemList.Circuit_Chip_SoC.get(6), null, 600, 48);
        GT_Values.RA.addCutterRecipe(ItemList.Circuit_Wafer_SoC2.get(1), ItemList.Circuit_Chip_SoC2.get(6), null, 600, 48);
        GT_Values.RA.addCutterRecipe(ItemList.Circuit_Wafer_PIC.get(1), ItemList.Circuit_Chip_PIC.get(4), null, 600, 48);
        GT_Values.RA.addCutterRecipe(ItemList.Circuit_Wafer_HPIC.get(1), ItemList.Circuit_Chip_HPIC.get(2), null, 600, 48);
        GT_Values.RA.addCutterRecipe(ItemList.Circuit_Wafer_NanoCPU.get(1), ItemList.Circuit_Chip_NanoCPU.get(7), null, 600, 48);
        GT_Values.RA.addCutterRecipe(ItemList.Circuit_Wafer_QuantumCPU.get(1), ItemList.Circuit_Chip_QuantumCPU.get(5), null, 600, 48);
        GT_Values.RA.addChemicalRecipe(ItemList.Circuit_Wafer_PIC.get(1), MatUnifier.get(OrePrefixes.dust, Materials.IndiumGalliumPhosphide, 2), Materials.RedAlloy.getMolten(288), null, ItemList.Circuit_Wafer_HPIC.get(1), 1200, 1920);
        GT_Values.RA.addChemicalRecipe(ItemList.Circuit_Wafer_CPU.get(1), GT_Utility.copyAmount(16, ic2.core.Ic2Items.carbonFiber), Materials.Glowstone.getMolten(576), null, ItemList.Circuit_Wafer_NanoCPU.get(1), 400, 1920);
        GT_Values.RA.addChemicalRecipe(ItemList.Circuit_Wafer_NanoCPU.get(1), MatUnifier.get(OrePrefixes.dust, Materials.IndiumGalliumPhosphide, 1), Materials.Radon.getGas(50), null, ItemList.Circuit_Wafer_QuantumCPU.get(1), 600, 1920);
        GT_Values.RA.addChemicalRecipe(ItemList.Circuit_Wafer_NanoCPU.get(1), ItemList.QuantumEye.get(2), Materials.GalliumArsenide.getMolten(288), null, ItemList.Circuit_Wafer_QuantumCPU.get(1), 400, 1920);

        GT_Values.RA.addAutoclaveRecipe(MatUnifier.get(OrePrefixes.gemExquisite, Materials.Emerald, 1), Materials.Europium.getMolten(16), ItemList.Circuit_Parts_RawCrystalChip.get(1), 1000, 12000, 320, true);
        GT_Values.RA.addAutoclaveRecipe(MatUnifier.get(OrePrefixes.gemExquisite, Materials.Olivine, 1), Materials.Europium.getMolten(16), ItemList.Circuit_Parts_RawCrystalChip.get(1), 1000, 12000, 320, true);
        GT_Values.RA.addAutoclaveRecipe(MatUnifier.get(OrePrefixes.gemExquisite, Materials.Emerald, 8), Materials.UUMatter.getFluid(100), ItemList.Tool_DataOrb.get(1), 10000, 12000, 320, true);
        GT_Values.RA.addAutoclaveRecipe(MatUnifier.get(OrePrefixes.gemExquisite, Materials.Olivine, 8), Materials.UUMatter.getFluid(100), ItemList.Tool_DataOrb.get(1), 10000, 12000, 320, true);
        GT_ModHandler.addShapelessCraftingRecipe(ItemList.Circuit_Parts_RawCrystalChip.get(9), new Object[]{ItemList.Circuit_Chip_CrystalCPU.get(1)});
        GT_Values.RA.addBlastRecipe(ItemList.Circuit_Parts_RawCrystalChip.get(1), MatUnifier.get(OrePrefixes.plate, Materials.Emerald, 1), Materials.Helium.getGas(1000), null, ItemList.Circuit_Parts_Crystal_Chip_Elite.get(1), null, 900, 480, 5000);
        GT_Values.RA.addBlastRecipe(ItemList.Circuit_Parts_RawCrystalChip.get(1), MatUnifier.get(OrePrefixes.plate, Materials.Olivine, 1), Materials.Helium.getGas(1000), null, ItemList.Circuit_Parts_Crystal_Chip_Elite.get(1), null, 900, 480, 5000);

        GT_ModHandler.addCraftingRecipe(ItemList.Circuit_Good.get(1), new Object[]{"IVC", "VDV", "CVI", 'D', ItemList.Circuit_Parts_Diode.get(1), 'C', MatUnifier.get(OrePrefixes.cableGt01, Materials.RedAlloy, 1), 'V', Ic2Items.electronicCircuit, 'I', ItemList.IC2_Item_Casing_Steel.get(1)});

        GT_Values.RA.addFormingPressRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Iron), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1, 0), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1, 1), 100, 120);
        GT_Values.RA.addFormingPressRecipe(MatUnifier.get(OrePrefixes.plate, Materials.WroughtIron), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1, 0), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1, 1), 100, 120);
        GT_Values.RA.addFormingPressRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Gold), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1, 0), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1, 2), 200, 120);
        GT_Values.RA.addFormingPressRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Diamond), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1, 0), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1, 3), 100, 480);
        GT_Values.RA.addFormingPressRecipe(MatUnifier.get(OrePrefixes.gem, Materials.EnderPearl), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1, 0), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 2, 4), 200, 120);
        GT_Values.RA.addFormingPressRecipe(MatUnifier.get(OrePrefixes.plate, Materials.NetherQuartz), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1, 0), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1, 5), 300, 120);
        GT_Values.RA.addFormingPressRecipe(new ItemStack(Items.comparator, 1, 32767), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1, 0), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1, 6), 300, 120);
        GT_Values.RA.addFormingPressRecipe(GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 10), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 0, 13), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 16), 200, 16);
        GT_Values.RA.addFormingPressRecipe(MatUnifier.get(OrePrefixes.plate, Materials.CertusQuartz), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 0, 13), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 16), 200, 16);
        GT_Values.RA.addFormingPressRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Diamond), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 0, 14), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 17), 200, 16);
        GT_Values.RA.addFormingPressRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Gold), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 0, 15), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 18), 200, 16);
        GT_Values.RA.addFormingPressRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Silicon), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 0, 19), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 20), 200, 16);

        for (Materials tMat : Materials.values()) {
            if (tMat.mStandardMoltenFluid != null && tMat.contains(SubTag.SOLDERING_MATERIAL) && !(GregTech_API.mUseOnlyGoodSolderingMaterials && !tMat.contains(SubTag.SOLDERING_MATERIAL_GOOD))) {
                int tMultiplier = tMat.contains(SubTag.SOLDERING_MATERIAL_GOOD) ? 1 : tMat.contains(SubTag.SOLDERING_MATERIAL_BAD) ? 4 : 2;
                //Circuit soldering
                //Integraded Circuits
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Phenolic.get(1), ItemList.Circuit_Chip_ILC.get(1), ItemList.Circuit_Parts_Resistor.get(2), MatUnifier.get(OrePrefixes.wireFine, Materials.Copper, 4)}, tMat.getMolten(144L * tMultiplier / 2L), ItemList.Circuit_Basic.get(1), 200, 8);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Phenolic.get(1), ItemList.Circuit_Chip_ILC.get(1), ItemList.Circuit_Parts_ResistorSMD.get(2), MatUnifier.get(OrePrefixes.wireFine, Materials.Copper, 4)}, tMat.getMolten(144L * tMultiplier / 2L), ItemList.Circuit_Basic.get(1), 200, 8);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Phenolic.get(1), ItemList.Circuit_Basic.get(3), ItemList.Circuit_Parts_Resistor.get(4), MatUnifier.get(OrePrefixes.wireFine, Materials.Gold, 8)}, tMat.getMolten(144L * tMultiplier / 2L), ItemList.Circuit_Integrated_Good.get(1), 400, 16);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Phenolic.get(1), ItemList.Circuit_Basic.get(3), ItemList.Circuit_Parts_ResistorSMD.get(4), MatUnifier.get(OrePrefixes.wireFine, Materials.Gold, 8)}, tMat.getMolten(144L * tMultiplier / 2L), ItemList.Circuit_Integrated_Good.get(1), 400, 16);

                //Advanced circuit
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Integrated_Good.get(2), ItemList.Circuit_Chip_ILC.get(3), ItemList.Circuit_Chip_Ram.get(1), ItemList.Circuit_Parts_Transistor.get(4), MatUnifier.get(OrePrefixes.wireFine, Materials.Electrum, 16)}, tMat.getMolten(144L * tMultiplier / 2L), GT_ModHandler.getIC2Item("advancedCircuit", 1), 800, 28);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Integrated_Good.get(2), ItemList.Circuit_Chip_ILC.get(3), ItemList.Circuit_Chip_Ram.get(1), ItemList.Circuit_Parts_TransistorSMD.get(4), MatUnifier.get(OrePrefixes.wireFine, Materials.Electrum, 16)}, tMat.getMolten(144L * tMultiplier / 2L), GT_ModHandler.getIC2Item("advancedCircuit", 1), 800, 28);

                //Highly Integrated Circuits
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Epoxy.get(1), ItemList.Circuit_Chip_CPU.get(1), ItemList.Circuit_Parts_Resistor.get(4), ItemList.Circuit_Parts_Capacitor.get(4), ItemList.Circuit_Parts_Transistor.get(4), MatUnifier.get(OrePrefixes.wireFine, Materials.RedAlloy, 4)}, tMat.getMolten(144L * tMultiplier / 2L), ItemList.Circuit_Processor.get(1), 200, 60);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Epoxy.get(1), ItemList.Circuit_Processor.get(3), ItemList.Circuit_Parts_Coil.get(4), ItemList.Circuit_Parts_Capacitor.get(4), ItemList.Circuit_Chip_Ram.get(4), MatUnifier.get(OrePrefixes.wireFine, Materials.RedAlloy, 12)}, tMat.getMolten(144L * tMultiplier), ItemList.Circuit_Computer.get(1), 400, 90);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Epoxy.get(1), ItemList.Circuit_Processor.get(1), ItemList.Circuit_Chip_NAND.get(32), ItemList.Circuit_Chip_Ram.get(4), MatUnifier.get(OrePrefixes.wireFine, Materials.RedAlloy, 8), MatUnifier.get(OrePrefixes.plate, Materials.Plastic, 4)}, tMat.getMolten(144L * tMultiplier), ItemList.Tool_DataStick.get(1), 400, 90);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Epoxy.get(2), ItemList.Circuit_Advanced.get(2), ItemList.Circuit_Parts_Diode.get(4), ItemList.Circuit_Chip_Ram.get(4), MatUnifier.get(OrePrefixes.wireFine, Materials.Electrum, 6)}, tMat.getMolten(144L * tMultiplier), ItemList.Circuit_Data.get(1), 400, 90);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{MatUnifier.get(OrePrefixes.frameGt, Materials.Aluminium, 1), ItemList.Circuit_Data.get(4), ItemList.Circuit_Parts_Coil.get(4), ItemList.Circuit_Parts_Capacitor.get(24), ItemList.Circuit_Chip_Ram.get(16), MatUnifier.get(OrePrefixes.wireGt01, Materials.AnnealedCopper, 12)}, tMat.getMolten(144L * tMultiplier * 2), ItemList.Circuit_Elite.get(1), 1600, 480);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Epoxy.get(1), ItemList.Circuit_Chip_CPU.get(1), ItemList.Circuit_Parts_ResistorSMD.get(4), ItemList.Circuit_Parts_CapacitorSMD.get(4), ItemList.Circuit_Parts_TransistorSMD.get(4), MatUnifier.get(OrePrefixes.wireFine, Materials.RedAlloy, 4)}, tMat.getMolten(144L * tMultiplier / 2L), ItemList.Circuit_Processor.get(1), 200, 60);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Epoxy.get(1), ItemList.Circuit_Processor.get(3), ItemList.Circuit_Parts_Coil.get(4), ItemList.Circuit_Parts_CapacitorSMD.get(4), ItemList.Circuit_Chip_Ram.get(4), MatUnifier.get(OrePrefixes.wireFine, Materials.RedAlloy, 12)}, tMat.getMolten(144L * tMultiplier), ItemList.Circuit_Computer.get(1), 400, 80);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Epoxy.get(2), ItemList.Circuit_Advanced.get(2), ItemList.Circuit_Parts_DiodeSMD.get(4), ItemList.Circuit_Chip_Ram.get(4), MatUnifier.get(OrePrefixes.wireFine, Materials.Electrum, 6)}, tMat.getMolten(144L * tMultiplier), ItemList.Circuit_Data.get(1), 400, 90);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{MatUnifier.get(OrePrefixes.frameGt, Materials.Aluminium, 1), ItemList.Circuit_Data.get(4), ItemList.Circuit_Parts_Coil.get(4), ItemList.Circuit_Parts_CapacitorSMD.get(24), ItemList.Circuit_Chip_Ram.get(16), MatUnifier.get(OrePrefixes.wireGt01, Materials.AnnealedCopper, 12)}, tMat.getMolten(144L * tMultiplier * 2), ItemList.Circuit_Elite.get(1), 1600, 480);
                //Nanotech Circuits
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Fiberglass.get(1), ItemList.Circuit_Chip_NanoCPU.get(1), ItemList.Circuit_Parts_ResistorSMD.get(4), ItemList.Circuit_Parts_CapacitorSMD.get(4), ItemList.Circuit_Parts_TransistorSMD.get(4), MatUnifier.get(OrePrefixes.wireFine, Materials.Electrum, 2)}, tMat.getMolten(144L * tMultiplier / 2L), ItemList.Circuit_Nanoprocessor.get(1), 200, 600);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Fiberglass.get(1), ItemList.Circuit_Nanoprocessor.get(3), ItemList.Circuit_Parts_Coil.get(4), ItemList.Circuit_Parts_CapacitorSMD.get(4), ItemList.Circuit_Chip_Ram.get(4), MatUnifier.get(OrePrefixes.wireFine, Materials.Electrum, 6)}, tMat.getMolten(144L * tMultiplier), ItemList.Circuit_Nanocomputer.get(1), 400, 600);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Fiberglass.get(1), ItemList.Circuit_Nanoprocessor.get(1), ItemList.Circuit_Chip_Ram.get(4), ItemList.Circuit_Chip_NOR.get(32), ItemList.Circuit_Chip_NAND.get(64), MatUnifier.get(OrePrefixes.wireFine, Materials.Platinum, 32)}, tMat.getMolten(144L * tMultiplier), ItemList.Tool_DataOrb.get(1), 400, 1200);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Fiberglass.get(2), ItemList.Circuit_Nanocomputer.get(2), ItemList.Circuit_Parts_DiodeSMD.get(4), ItemList.Circuit_Chip_NOR.get(4), ItemList.Circuit_Chip_Ram.get(4), MatUnifier.get(OrePrefixes.wireFine, Materials.Electrum, 6)}, tMat.getMolten(144L * tMultiplier), ItemList.Circuit_Elitenanocomputer.get(1), 400, 600);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{MatUnifier.get(OrePrefixes.frameGt, Materials.Aluminium, 1), ItemList.Circuit_Elitenanocomputer.get(4), ItemList.Circuit_Parts_Coil.get(4), ItemList.Circuit_Parts_CapacitorSMD.get(24), ItemList.Circuit_Chip_Ram.get(16), MatUnifier.get(OrePrefixes.wireGt01, Materials.AnnealedCopper, 12)}, tMat.getMolten(144L * tMultiplier * 2), ItemList.Circuit_Master.get(1), 1600, 1920);
                //Quantum Circuits
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Multifiberglass.get(1), ItemList.Circuit_Chip_QuantumCPU.get(1), ItemList.Circuit_Chip_NanoCPU.get(1), ItemList.Circuit_Parts_CapacitorSMD.get(4), ItemList.Circuit_Parts_TransistorSMD.get(4), MatUnifier.get(OrePrefixes.wireFine, Materials.Platinum, 2)}, tMat.getMolten(144L * tMultiplier / 2L), ItemList.Circuit_Quantumprocessor.get(1), 200, 2400);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Multifiberglass.get(1), ItemList.Circuit_Quantumprocessor.get(3), ItemList.Circuit_Parts_Coil.get(4), ItemList.Circuit_Parts_CapacitorSMD.get(4), ItemList.Circuit_Chip_Ram.get(4), MatUnifier.get(OrePrefixes.wireFine, Materials.Platinum, 6)}, tMat.getMolten(144L * tMultiplier), ItemList.Circuit_Quantumcomputer.get(1), 400, 2400);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Multifiberglass.get(2), ItemList.Circuit_Quantumcomputer.get(2), ItemList.Circuit_Parts_DiodeSMD.get(4), ItemList.Circuit_Chip_NOR.get(4), ItemList.Circuit_Chip_Ram.get(4), MatUnifier.get(OrePrefixes.wireFine, Materials.Platinum, 6)}, tMat.getMolten(144L * tMultiplier), ItemList.Circuit_Masterquantumcomputer.get(1), 400, 2400);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{MatUnifier.get(OrePrefixes.frameGt, Materials.Aluminium, 1), ItemList.Circuit_Masterquantumcomputer.get(4), ItemList.Circuit_Parts_Coil.get(4), ItemList.Circuit_Parts_CapacitorSMD.get(24), ItemList.Circuit_Chip_Ram.get(16), MatUnifier.get(OrePrefixes.wireGt01, Materials.AnnealedCopper, 12)}, tMat.getMolten(144L * tMultiplier * 2), ItemList.Circuit_Quantummainframe.get(1), 1600, 7680);
                //Crystallized Circuits
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Multifiberglass.get(1), ItemList.Circuit_Chip_CrystalCPU.get(1), ItemList.Circuit_Chip_NanoCPU.get(1), ItemList.Circuit_Parts_CapacitorSMD.get(4), ItemList.Circuit_Parts_TransistorSMD.get(4), MatUnifier.get(OrePrefixes.wireFine, Materials.NiobiumTitanium, 2)}, tMat.getMolten(144L * tMultiplier / 2), ItemList.Circuit_Crystalprocessor.get(1), 200, 9600);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Multifiberglass.get(1), ItemList.Circuit_Crystalprocessor.get(3), ItemList.Circuit_Parts_Coil.get(4), ItemList.Circuit_Parts_CapacitorSMD.get(4), ItemList.Circuit_Chip_Ram.get(4), MatUnifier.get(OrePrefixes.wireFine, Materials.NiobiumTitanium, 6)}, tMat.getMolten(144L * tMultiplier), ItemList.Circuit_Crystalcomputer.get(1), 400, 9600);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Multifiberglass.get(2), ItemList.Circuit_Crystalcomputer.get(2), ItemList.Circuit_Parts_DiodeSMD.get(4), ItemList.Circuit_Chip_NOR.get(4), ItemList.Circuit_Chip_Ram.get(4), MatUnifier.get(OrePrefixes.wireFine, Materials.NiobiumTitanium, 6)}, tMat.getMolten(144L * tMultiplier), ItemList.Circuit_Ultimatecrystalcomputer.get(1), 400, 9600);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{MatUnifier.get(OrePrefixes.frameGt, Materials.Aluminium, 1), ItemList.Circuit_Ultimatecrystalcomputer.get(4), ItemList.Circuit_Parts_Coil.get(4), ItemList.Circuit_Parts_CapacitorSMD.get(24), ItemList.Circuit_Chip_Ram.get(16), MatUnifier.get(OrePrefixes.wireGt01, Materials.Superconductor, 12)}, tMat.getMolten(144L * tMultiplier * 2), ItemList.Circuit_Crystalmainframe.get(1), 1600, 30720);
                //Wetware Circuits
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Wetware.get(1), ItemList.Circuit_Chip_NeuroCPU.get(1), ItemList.Circuit_Chip_CrystalCPU.get(1), ItemList.Circuit_Parts_CapacitorSMD.get(4), ItemList.Circuit_Parts_TransistorSMD.get(4), MatUnifier.get(OrePrefixes.wireFine, Materials.YttriumBariumCuprate, 2)}, tMat.getMolten(144L * tMultiplier / 2), ItemList.Circuit_Neuroprocessor.get(1), 200, 38400);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Wetware.get(1), ItemList.Circuit_Neuroprocessor.get(3), ItemList.Circuit_Parts_Coil.get(4), ItemList.Circuit_Parts_CapacitorSMD.get(4), ItemList.Circuit_Chip_Ram.get(4), MatUnifier.get(OrePrefixes.wireFine, Materials.YttriumBariumCuprate, 6)}, tMat.getMolten(144L * tMultiplier), ItemList.Circuit_Wetwarecomputer.get(1), 400, 38400);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Wetware.get(2), ItemList.Circuit_Wetwarecomputer.get(2), ItemList.Circuit_Parts_DiodeSMD.get(4), ItemList.Circuit_Chip_NOR.get(4), ItemList.Circuit_Chip_Ram.get(4), MatUnifier.get(OrePrefixes.wireFine, Materials.YttriumBariumCuprate, 6)}, tMat.getMolten(144L * tMultiplier), ItemList.Circuit_Wetwaresupercomputer.get(1), 400, 38400);

                //SoC
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Epoxy.get(1), ItemList.Circuit_Chip_SoC.get(1), MatUnifier.get(OrePrefixes.wireFine, Materials.RedAlloy, 4)}, tMat.getMolten(144 * tMultiplier / 2), ItemList.Circuit_Basic.get(1), 50, 600);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Epoxy.get(1), ItemList.Circuit_Chip_SoC.get(1), MatUnifier.get(OrePrefixes.wireFine, Materials.Electrum, 4)}, tMat.getMolten(144 * tMultiplier / 2), ItemList.Circuit_Processor.get(1), 50, 2400);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Fiberglass.get(1), ItemList.Circuit_Chip_SoC2.get(1), MatUnifier.get(OrePrefixes.wireFine, Materials.Platinum, 4)}, tMat.getMolten(144 * tMultiplier / 2), ItemList.Circuit_Nanoprocessor.get(1), 50, 9600);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Fiberglass.get(1), ItemList.Circuit_Chip_SoC2.get(1), MatUnifier.get(OrePrefixes.wireFine, Materials.NiobiumTitanium, 4)}, tMat.getMolten(144 * tMultiplier / 2), ItemList.Circuit_Quantumprocessor.get(1), 50, 38400);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Multifiberglass.get(1), ItemList.Circuit_Chip_CrystalSoC.get(1), MatUnifier.get(OrePrefixes.wireFine, Materials.YttriumBariumCuprate, 4)}, tMat.getMolten(144 * tMultiplier / 2), ItemList.Circuit_Crystalprocessor.get(1), 50, 153600);

                //Lapoorbs
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Multifiberglass.get(1), ItemList.Circuit_Chip_PIC.get(4), ItemList.Circuit_Parts_Crystal_Chip_Master.get(18), ItemList.Circuit_Chip_NanoCPU.get(1), MatUnifier.get(OrePrefixes.wireFine, Materials.Platinum, 16)}, tMat.getMolten(144L * tMultiplier), ItemList.Energy_LapotronicOrb.get(1), 512, 1024);
                GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Multifiberglass.get(1), ItemList.Circuit_Chip_HPIC.get(4), ItemList.Energy_LapotronicOrb.get(8), ItemList.Circuit_Chip_QuantumCPU.get(1), MatUnifier.get(OrePrefixes.wireFine, Materials.Platinum, 16), MatUnifier.get(OrePrefixes.plate, Materials.Europium, 4)}, tMat.getMolten(144L * tMultiplier), ItemList.Energy_LapotronicOrb2.get(1), 1024, 4096);

                for (ItemStack tPlate : new ItemStack[]{MatUnifier.get(OrePrefixes.plate, Materials.Iron), MatUnifier.get(OrePrefixes.plate, Materials.WroughtIron), MatUnifier.get(OrePrefixes.plate, Materials.Aluminium)}) {
                    GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.lever, 1, 32767), tPlate, tMat.getMolten(144L * tMultiplier / 2), ItemList.Cover_Controller.get(1), 800, 16);
                    GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.redstone_torch, 1, 32767), tPlate, tMat.getMolten(144L * tMultiplier / 2), ItemList.Cover_ActivityDetector.get(1), 800, 16);
                    GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.heavy_weighted_pressure_plate, 1, 32767), tPlate, tMat.getMolten(144L * tMultiplier / 2), ItemList.Cover_FluidDetector.get(1), 800, 16);
                    GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.light_weighted_pressure_plate, 1, 32767), tPlate, tMat.getMolten(144L * tMultiplier / 2), ItemList.Cover_ItemDetector.get(1), 800, 16);
                    GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getIC2Item("ecMeter", 1), tPlate, tMat.getMolten(144L * tMultiplier / 2L), ItemList.Cover_EnergyDetector.get(1), 800, 16);
                }
            }
        }

        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Silicon, 32), MatUnifier.get(OrePrefixes.dustTiny, Materials.Gallium, 1), null, null, ItemList.Circuit_Silicon_Ingot.get(1), null, 9000, 120, 1784);
        GT_Values.RA.addCutterRecipe(ItemList.Circuit_Silicon_Ingot.get(1), ItemList.Circuit_Silicon_Wafer.get(16), null, 200, 8);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Silicon, 64), MatUnifier.get(OrePrefixes.dust, Materials.Glowstone, 8), Materials.Nitrogen.getGas(8000), null, ItemList.Circuit_Silicon_Ingot2.get(1), null, 12000, 480, 2484);
        GT_Values.RA.addCutterRecipe(ItemList.Circuit_Silicon_Ingot2.get(1), ItemList.Circuit_Silicon_Wafer2.get(32), null, 400, 64);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.block, Materials.Silicon, 16), MatUnifier.get(OrePrefixes.ingot, Materials.Naquadah, 1), Materials.Argon.getGas(8000), null, ItemList.Circuit_Silicon_Ingot3.get(1), null, 1500, 1920, 5400);
        GT_Values.RA.addCutterRecipe(ItemList.Circuit_Silicon_Ingot3.get(1), ItemList.Circuit_Silicon_Wafer3.get(64), null, 800, 384);


        GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.redstone_torch, 2, 32767), MatUnifier.get(OrePrefixes.dust, Materials.Redstone, 1), Materials.Concrete.getMolten(144L), new ItemStack(Items.repeater, 1, 0), 800, 1);
        GT_Values.RA.addAssemblerRecipe(new ItemStack(Items.leather, 1, 32767), new ItemStack(Items.lead, 1, 32767), Materials.Glue.getFluid(50L), new ItemStack(Items.name_tag, 1, 0), 100, 8);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Paper, 8), new ItemStack(Items.compass, 1, 32767), GT_Values.NF, new ItemStack(Items.map, 1, 0), 100, 8);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Tantalum, 1), MatUnifier.get(OrePrefixes.foil, Materials.Manganese, 1), Materials.Plastic.getMolten(144L), ItemList.Battery_RE_ULV_Tantalum.get(8), 100, 4);
        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getModItem("TwilightForest", "item.charmOfLife1", 4, 0), ItemList.Circuit_Integrated.getWithDamage(0, 4), GT_Values.NF, GT_ModHandler.getModItem("TwilightForest", "item.charmOfLife2", 1, 0), 100, 8);
        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getModItem("TwilightForest", "item.charmOfKeeping1", 4, 0), ItemList.Circuit_Integrated.getWithDamage(0, 4), GT_Values.NF, GT_ModHandler.getModItem("TwilightForest", "item.charmOfKeeping2", 1, 0), 100, 8);
        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getModItem("TwilightForest", "item.charmOfKeeping2", 4, 0), ItemList.Circuit_Integrated.getWithDamage(0, 4), GT_Values.NF, GT_ModHandler.getModItem("TwilightForest", "item.charmOfKeeping3", 1, 0), 100, 8);
        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getModItem("TwilightForest", "item.charmOfLife2", 1, 0), ItemList.Circuit_Integrated.getWithDamage(0, 1), GT_Values.NF, GT_ModHandler.getModItem("TwilightForest", "item.charmOfLife1", 4, 0), 100, 8);
        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getModItem("TwilightForest", "item.charmOfKeeping2", 1, 0), ItemList.Circuit_Integrated.getWithDamage(0, 1), GT_Values.NF, GT_ModHandler.getModItem("TwilightForest", "item.charmOfKeeping1", 4, 0), 100, 8);
        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getModItem("TwilightForest", "item.charmOfKeeping3", 1, 0), ItemList.Circuit_Integrated.getWithDamage(0, 1), GT_Values.NF, GT_ModHandler.getModItem("TwilightForest", "item.charmOfKeeping2", 4, 0), 100, 8);
        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 16), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 20), Materials.Redstone.getMolten(144L), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 23), 64, 32);
        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 17), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 20), Materials.Redstone.getMolten(144L), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 24), 64, 32);
        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 18), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 20), Materials.Redstone.getMolten(144L), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 22), 64, 32);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.CertusQuartz), new ItemStack(Blocks.sand, 1, 32767), GT_Values.NF, GT_ModHandler.getModItem(aTextAE, "item.ItemCrystalSeed", 2, 0), 64, 8);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.NetherQuartz), new ItemStack(Blocks.sand, 1, 32767), GT_Values.NF, GT_ModHandler.getModItem(aTextAE, "item.ItemCrystalSeed", 2, 600), 64, 8);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Fluix), new ItemStack(Blocks.sand, 1, 32767), GT_Values.NF, GT_ModHandler.getModItem(aTextAE, "item.ItemCrystalSeed", 2, 1200), 64, 8);
        GT_Values.RA.addAssemblerRecipe(ItemList.FR_Wax.get(6), new ItemStack(Items.string, 1, 32767), Materials.Water.getFluid(600L), GT_ModHandler.getModItem(aTextForestry, "candle", 24, 0), 64, 8);
        GT_Values.RA.addAssemblerRecipe(ItemList.FR_Wax.get(2), ItemList.FR_Silk.get(1), Materials.Water.getFluid(200L), GT_ModHandler.getModItem(aTextForestry, "candle", 8, 0), 16, 8);
        GT_Values.RA.addAssemblerRecipe(ItemList.FR_Silk.get(9), ItemList.Circuit_Integrated.getWithDamage(0, 9), Materials.Water.getFluid(500L), GT_ModHandler.getModItem(aTextForestry, "craftingMaterial", 1, 3), 64, 8);
        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getModItem(aTextForestry, "propolis", 5, 2), ItemList.Circuit_Integrated.getWithDamage(0, 5), GT_Values.NF, GT_ModHandler.getModItem(aTextForestry, "craftingMaterial", 1, 1), 16, 8);
        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getModItem(aTextForestry, "sturdyMachine", 1, 0), MatUnifier.get(OrePrefixes.gem, Materials.Diamond, 4), Materials.Water.getFluid(5000L), ItemList.FR_Casing_Hardened.get(1), 64, 32);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Bronze, 8), ItemList.Circuit_Integrated.getWithDamage(0, 8), GT_Values.NF, ItemList.FR_Casing_Sturdy.get(1), 32, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Tin), MatUnifier.get(OrePrefixes.dust, Materials.Redstone, 6), Materials.Water.getFluid(1000L), GT_ModHandler.getModItem(aTextForestry, "chipsets", 1, 0), 16, 8);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Bronze, 3), MatUnifier.get(OrePrefixes.dust, Materials.Redstone, 6), Materials.Water.getFluid(1000L), GT_ModHandler.getModItem(aTextForestry, "chipsets", 1, 1), 32, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Iron, 3), MatUnifier.get(OrePrefixes.dust, Materials.Redstone, 6), Materials.Water.getFluid(1000L), GT_ModHandler.getModItem(aTextForestry, "chipsets", 1, 2), 48, 24);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.WroughtIron, 3), MatUnifier.get(OrePrefixes.dust, Materials.Redstone, 6), Materials.Water.getFluid(1000L), GT_ModHandler.getModItem(aTextForestry, "chipsets", 1, 2), 48, 24);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Gold, 3), MatUnifier.get(OrePrefixes.dust, Materials.Redstone, 6), Materials.Water.getFluid(1000L), GT_ModHandler.getModItem(aTextForestry, "chipsets", 1, 3), 64, 32);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.stick, Materials.Wood), new ItemStack(Blocks.wool, 1, 32767), Materials.Creosote.getFluid(1000L), new ItemStack(Blocks.torch, 6, 0), 400, 1);
        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getModItem(aTextForestry, "craftingMaterial", 5, 1), ItemList.Circuit_Integrated.getWithDamage(0, 5), GT_Values.NF, MatUnifier.get(OrePrefixes.gem, Materials.EnderPearl, 1), 64, 8);
        GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.piston, 1, 32767), new ItemStack(Items.slime_ball, 1, 32767), GT_Values.NF, new ItemStack(Blocks.sticky_piston, 1, 0), 100, 4);
        GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.piston, 1, 32767), ItemList.IC2_Resin.get(1), GT_Values.NF, new ItemStack(Blocks.sticky_piston, 1, 0), 100, 4);
        GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.piston, 1, 32767), ItemList.Circuit_Integrated.getWithDamage(0, 1), Materials.Glue.getFluid(100L), new ItemStack(Blocks.sticky_piston, 1, 0), 100, 4);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Rubber, 3), GT_ModHandler.getIC2Item("carbonMesh", 3), Materials.Glue.getFluid(300L), ItemList.Duct_Tape.get(1), 100, 64);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Paper, 3), new ItemStack(Items.leather, 1, 32767), Materials.Glue.getFluid(20L), new ItemStack(Items.book, 1, 0), 32, 8);
        GT_Values.RA.addAssemblerRecipe(ItemList.Paper_Printed_Pages.get(1), new ItemStack(Items.leather, 1, 32767), Materials.Glue.getFluid(20L), new ItemStack(Items.written_book, 1, 0), 32, 8);
        GT_Values.RA.addAssemblerRecipe(ItemList.IC2_Item_Casing_Tin.get(4), new ItemStack(Blocks.glass_pane, 1, 32767), GT_Values.NF, ItemList.Cell_Universal_Fluid.get(1), 128, 8);

        ItemStack aRedstoneDust = MatUnifier.get(OrePrefixes.dust, Materials.Redstone, 2);
        GT_Values.RA.addAssemblerRecipe(aRedstoneDust, MatUnifier.get(OrePrefixes.ingot, Materials.Copper, 5), Materials.Glass.getMolten(72L), GT_ModHandler.getModItem(aTextForestry, "thermionicTubes", 4, 0), 64, 32);
        GT_Values.RA.addAssemblerRecipe(aRedstoneDust, MatUnifier.get(OrePrefixes.ingot, Materials.AnnealedCopper, 5), Materials.Glass.getMolten(72L), GT_ModHandler.getModItem(aTextForestry, "thermionicTubes", 4, 0), 64, 32);
        GT_Values.RA.addAssemblerRecipe(aRedstoneDust, MatUnifier.get(OrePrefixes.ingot, Materials.Tin, 5), Materials.Glass.getMolten(72L), GT_ModHandler.getModItem(aTextForestry, "thermionicTubes", 4, 1), 64, 32);
        GT_Values.RA.addAssemblerRecipe(aRedstoneDust, MatUnifier.get(OrePrefixes.ingot, Materials.Bronze, 5), Materials.Glass.getMolten(72L), GT_ModHandler.getModItem(aTextForestry, "thermionicTubes", 4, 2), 64, 32);
        GT_Values.RA.addAssemblerRecipe(aRedstoneDust, MatUnifier.get(OrePrefixes.ingot, Materials.Iron, 5), Materials.Glass.getMolten(72L), GT_ModHandler.getModItem(aTextForestry, "thermionicTubes", 4, 3), 64, 32);
        GT_Values.RA.addAssemblerRecipe(aRedstoneDust, MatUnifier.get(OrePrefixes.ingot, Materials.WroughtIron, 5), Materials.Glass.getMolten(72L), GT_ModHandler.getModItem(aTextForestry, "thermionicTubes", 4, 3), 64, 32);
        GT_Values.RA.addAssemblerRecipe(aRedstoneDust, MatUnifier.get(OrePrefixes.ingot, Materials.Gold, 5), Materials.Glass.getMolten(72L), GT_ModHandler.getModItem(aTextForestry, "thermionicTubes", 4, 4), 64, 32);
        GT_Values.RA.addAssemblerRecipe(aRedstoneDust, MatUnifier.get(OrePrefixes.gem, Materials.Diamond, 5), Materials.Glass.getMolten(72L), GT_ModHandler.getModItem(aTextForestry, "thermionicTubes", 4, 5), 64, 32);
        GT_Values.RA.addAssemblerRecipe(aRedstoneDust, MatUnifier.get(OrePrefixes.dust, Materials.Blaze, 5), Materials.Glass.getMolten(72L), GT_ModHandler.getModItem(aTextForestry, "thermionicTubes", 4, 7), 64, 32);
        GT_Values.RA.addAssemblerRecipe(aRedstoneDust, MatUnifier.get(OrePrefixes.ingot, Materials.Rubber, 5), Materials.Glass.getMolten(72L), GT_ModHandler.getModItem(aTextForestry, "thermionicTubes", 4, 8), 64, 32);
        GT_Values.RA.addAssemblerRecipe(aRedstoneDust, MatUnifier.get(OrePrefixes.gem, Materials.Emerald, 5), Materials.Glass.getMolten(72L), GT_ModHandler.getModItem(aTextForestry, "thermionicTubes", 4, 9), 64, 32);
        GT_Values.RA.addAssemblerRecipe(aRedstoneDust, MatUnifier.get(OrePrefixes.gem, Materials.Apatite, 5), Materials.Glass.getMolten(72L), GT_ModHandler.getModItem(aTextForestry, "thermionicTubes", 4, 10), 64, 32);
        GT_Values.RA.addAssemblerRecipe(aRedstoneDust, MatUnifier.get(OrePrefixes.gem, Materials.Lapis, 5), Materials.Glass.getMolten(72L), GT_ModHandler.getModItem(aTextForestry, "thermionicTubes", 4, 11), 64, 32);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Aluminium, 2), new ItemStack(Items.iron_door, 1), ItemList.Cover_Shutter.get(2), 800, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Iron, 2), new ItemStack(Items.iron_door, 1), ItemList.Cover_Shutter.get(2), 800, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.WroughtIron, 2), new ItemStack(Items.iron_door, 1), ItemList.Cover_Shutter.get(2), 800, 16);

        GT_Values.RA.addUniversalDistillationRecipe(Materials.OilLight.getFluid(150), new FluidStack[]{Materials.SulfuricHeavyFuel.getFluid(10), Materials.SulfuricLightFuel.getFluid(20), Materials.SulfuricNaphtha.getFluid(30), Materials.SulfuricGas.getGas(240)}, null, 20, 96);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.OilMedium.getFluid(100), new FluidStack[]{Materials.SulfuricHeavyFuel.getFluid(15), Materials.SulfuricLightFuel.getFluid(50), Materials.SulfuricNaphtha.getFluid(20), Materials.SulfuricGas.getGas(60)}, null, 20, 96);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Oil.getFluid(50L), new FluidStack[]{Materials.SulfuricHeavyFuel.getFluid(15), Materials.SulfuricLightFuel.getFluid(50), Materials.SulfuricNaphtha.getFluid(20), Materials.SulfuricGas.getGas(60)}, null, 20, 96);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.OilHeavy.getFluid(100), new FluidStack[]{Materials.SulfuricHeavyFuel.getFluid(250), Materials.SulfuricLightFuel.getFluid(45), Materials.SulfuricNaphtha.getFluid(15), Materials.SulfuricGas.getGas(60)}, null, 20, 288);

        if (FluidRegistry.getFluid("oilgc") != null) {
            GT_Values.RA.addUniversalDistillationRecipe(new FluidStack(FluidRegistry.getFluid("oilgc"), 50), new FluidStack[]{Materials.SulfuricHeavyFuel.getFluid(15), Materials.SulfuricLightFuel.getFluid(50), Materials.SulfuricNaphtha.getFluid(20), Materials.SulfuricGas.getGas(60)}, null, 20, 96);
        }

        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0, 1), new FluidStack(ItemList.sOilExtraHeavy, 10), Materials.OilHeavy.getFluid(15), 16, 24, false);
        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Ball.get(0), new FluidStack(ItemList.sToluene, 100), ItemList.GelledToluene.get(1), 100, 16);
        GT_Values.RA.addChemicalRecipe(ItemList.GelledToluene.get(4), GT_Values.NI, Materials.SulfuricAcid.getFluid(250), GT_Values.NF, new ItemStack(Blocks.tnt, 1), 200, 24);

        GT_Values.RA.addChemicalRecipe(new ItemStack(Items.sugar), MatUnifier.get(OrePrefixes.dustTiny, Materials.Plastic, 1), new FluidStack(ItemList.sToluene, 133), GT_Values.NF, ItemList.GelledToluene.get(2), 140, 192);

        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.cell, Materials.SulfuricAcid, 1), null, null, null, new FluidStack(ItemList.sNitricAcid, 1000), new FluidStack(ItemList.sNitrationMixture, 2000), ItemList.Cell_Empty.get(1), 480, 2);
        GT_Values.RA.addChemicalRecipe(ItemList.GelledToluene.get(4), GT_Values.NI, new FluidStack(ItemList.sNitrationMixture, 500), Materials.DilutedSulfuricAcid.getFluid(500), GT_ModHandler.getIC2Item("industrialTnt", 1), 80, 480);

        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.Hydrogen, 2), GT_Values.NI, Materials.NaturalGas.getGas(16000), Materials.Gas.getGas(16000), MatUnifier.get(OrePrefixes.cell, Materials.HydricSulfide, 1), Materials.Empty.getCells(1), 160);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.NaturalGas, 16), GT_Values.NI, Materials.Hydrogen.getGas(2000), Materials.HydricSulfide.getGas(1000), MatUnifier.get(OrePrefixes.cell, Materials.Gas, 16), 160);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.Hydrogen, 2), GT_Values.NI, Materials.SulfuricGas.getGas(16000), Materials.Gas.getGas(16000), MatUnifier.get(OrePrefixes.cell, Materials.HydricSulfide, 1), Materials.Empty.getCells(1), 160);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.SulfuricGas, 16), GT_Values.NI, Materials.Hydrogen.getGas(2000), Materials.HydricSulfide.getGas(1000), MatUnifier.get(OrePrefixes.cell, Materials.Gas, 16), 160);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.Hydrogen, 2), GT_Values.NI, Materials.SulfuricNaphtha.getFluid(12000), Materials.Naphtha.getFluid(12000), MatUnifier.get(OrePrefixes.cell, Materials.HydricSulfide, 1), Materials.Empty.getCells(1), 160);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.SulfuricNaphtha, 12), GT_Values.NI, Materials.Hydrogen.getGas(2000), Materials.HydricSulfide.getGas(1000), MatUnifier.get(OrePrefixes.cell, Materials.Naphtha, 12), 160);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.Hydrogen, 2), GT_Values.NI, Materials.SulfuricLightFuel.getFluid(12000), Materials.LightFuel.getFluid(12000), MatUnifier.get(OrePrefixes.cell, Materials.HydricSulfide, 1), Materials.Empty.getCells(1), 160);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.SulfuricLightFuel, 12), GT_Values.NI, Materials.Hydrogen.getGas(2000), Materials.HydricSulfide.getGas(1000), MatUnifier.get(OrePrefixes.cell, Materials.LightFuel, 12), 160);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.Hydrogen, 2), GT_Values.NI, Materials.SulfuricHeavyFuel.getFluid(8000), Materials.HeavyFuel.getFluid(8000), MatUnifier.get(OrePrefixes.cell, Materials.HydricSulfide, 1), Materials.Empty.getCells(1), 160);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.SulfuricHeavyFuel, 8), GT_Values.NI, Materials.Hydrogen.getGas(2000), Materials.HydricSulfide.getGas(1000), MatUnifier.get(OrePrefixes.cell, Materials.HeavyFuel, 8), 160);

        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Saltpeter, 1), null, Materials.Naphtha.getFluid(576), Materials.Polycaprolactam.getMolten(1296), MatUnifier.get(OrePrefixes.dustTiny, Materials.Potassium, 1), 640);
        GT_Values.RA.addWiremillRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Polycaprolactam, 1), new ItemStack(Items.string, 32), 80, 48);

        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0, 4), Materials.Creosote.getFluid(3L), Materials.Lubricant.getFluid(1L), 16, 24, false);
        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0, 4), Materials.SeedOil.getFluid(4L), Materials.Lubricant.getFluid(1L), 16, 24, false);
        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0, 4), Materials.FishOil.getFluid(3L), Materials.Lubricant.getFluid(1L), 16, 24, false);
        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0, 5), Materials.Water.getFluid(5L), GT_ModHandler.getDistilledWater(5L), 16, 10, false);
        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0, 1), FluidRegistry.getFluidStack("potion.potatojuice", 2), FluidRegistry.getFluidStack("potion.vodka", 1), 16, 16, true);
        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0, 1), FluidRegistry.getFluidStack("potion.lemonade", 2), FluidRegistry.getFluidStack("potion.alcopops", 1), 16, 16, true);

        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0, 4), Materials.OilLight.getFluid(300L), Materials.Oil.getFluid(100L), 16, 24, false);
        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0, 4), Materials.OilMedium.getFluid(200L), Materials.Oil.getFluid(100L), 16, 24, false);
        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0, 4), Materials.OilHeavy.getFluid(100L), Materials.Oil.getFluid(100L), 16, 24, false);

        GT_Values.RA.addFluidHeaterRecipe(ItemList.Circuit_Integrated.getWithDamage(0, 1), Materials.Water.getFluid(6L), Materials.Water.getGas(960L), 30, 32);
        GT_Values.RA.addFluidHeaterRecipe(ItemList.Circuit_Integrated.getWithDamage(0, 1), GT_ModHandler.getDistilledWater(6L), Materials.Water.getGas(960L), 30, 32);

        GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Talc), FluidRegistry.getFluid("oil"), FluidRegistry.getFluid("lubricant"), false);
        GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Soapstone), FluidRegistry.getFluid("oil"), FluidRegistry.getFluid("lubricant"), false);
        GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Redstone), FluidRegistry.getFluid("oil"), FluidRegistry.getFluid("lubricant"), false);
        GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Talc), FluidRegistry.getFluid("creosote"), FluidRegistry.getFluid("lubricant"), false);
        GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Soapstone), FluidRegistry.getFluid("creosote"), FluidRegistry.getFluid("lubricant"), false);
        GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Redstone), FluidRegistry.getFluid("creosote"), FluidRegistry.getFluid("lubricant"), false);
        GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Talc), FluidRegistry.getFluid("seedoil"), FluidRegistry.getFluid("lubricant"), false);
        GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Soapstone), FluidRegistry.getFluid("seedoil"), FluidRegistry.getFluid("lubricant"), false);
        GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Redstone), FluidRegistry.getFluid("seedoil"), FluidRegistry.getFluid("lubricant"), false);
        for (Fluid tFluid : new Fluid[]{FluidRegistry.WATER, GT_ModHandler.getDistilledWater(1).getFluid()}) {
            GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Milk), tFluid, FluidRegistry.getFluid("milk"), false);
            GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Wheat), tFluid, FluidRegistry.getFluid("potion.wheatyjuice"), false);
            GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Potassium), tFluid, FluidRegistry.getFluid("potion.mineralwater"), false);
            GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Sodium), tFluid, FluidRegistry.getFluid("potion.mineralwater"), false);
            GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Calcium), tFluid, FluidRegistry.getFluid("potion.mineralwater"), false);
            GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Magnesium), tFluid, FluidRegistry.getFluid("potion.mineralwater"), false);
            GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Glowstone), tFluid, FluidRegistry.getFluid("potion.thick"), false);
            GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Redstone), tFluid, FluidRegistry.getFluid("potion.mundane"), false);
            GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Sugar), tFluid, FluidRegistry.getFluid("potion.mundane"), false);
            GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Blaze), tFluid, FluidRegistry.getFluid("potion.mundane"), false);
            GT_Values.RA.addBrewingRecipe(new ItemStack(Items.magma_cream, 1, 0), tFluid, FluidRegistry.getFluid("potion.mundane"), false);
            GT_Values.RA.addBrewingRecipe(new ItemStack(Items.fermented_spider_eye, 1, 0), tFluid, FluidRegistry.getFluid("potion.mundane"), false);
            GT_Values.RA.addBrewingRecipe(new ItemStack(Items.spider_eye, 1, 0), tFluid, FluidRegistry.getFluid("potion.mundane"), false);
            GT_Values.RA.addBrewingRecipe(new ItemStack(Items.speckled_melon, 1, 0), tFluid, FluidRegistry.getFluid("potion.mundane"), false);
            GT_Values.RA.addBrewingRecipe(new ItemStack(Items.ghast_tear, 1, 0), tFluid, FluidRegistry.getFluid("potion.mundane"), false);
            GT_Values.RA.addBrewingRecipe(new ItemStack(Items.nether_wart, 1, 0), tFluid, FluidRegistry.getFluid("potion.awkward"), false);
            GT_Values.RA.addBrewingRecipe(new ItemStack(Blocks.red_mushroom, 1, 0), tFluid, FluidRegistry.getFluid("potion.poison"), false);
            GT_Values.RA.addBrewingRecipe(new ItemStack(Items.fish, 1, 3), tFluid, FluidRegistry.getFluid("potion.poison.strong"), true);
            GT_Values.RA.addBrewingRecipe(ItemList.IC2_Grin_Powder.get(1), tFluid, FluidRegistry.getFluid("potion.poison.strong"), false);
            GT_Values.RA.addBrewingRecipe(new ItemStack(Items.reeds, 1, 0), tFluid, FluidRegistry.getFluid("potion.reedwater"), false);
            GT_Values.RA.addBrewingRecipe(new ItemStack(Items.apple, 1, 0), tFluid, FluidRegistry.getFluid("potion.applejuice"), false);
            GT_Values.RA.addBrewingRecipe(new ItemStack(Items.golden_apple, 1, 0), tFluid, FluidRegistry.getFluid("potion.goldenapplejuice"), true);
            GT_Values.RA.addBrewingRecipe(new ItemStack(Items.golden_apple, 1, 1), tFluid, FluidRegistry.getFluid("potion.idunsapplejuice"), true);
            GT_Values.RA.addBrewingRecipe(ItemList.IC2_Hops.get(1), tFluid, FluidRegistry.getFluid("potion.hopsjuice"), false);
            GT_Values.RA.addBrewingRecipe(GT_ModHandler.getIC2Item("biochaff", 1), tFluid, Materials.Biomass.mFluid, false);

            ItemStack aCalciteDust = MatUnifier.get(OrePrefixes.dust, Materials.Calcite);
            ItemStack aCalciumDust = MatUnifier.get(OrePrefixes.dust, Materials.Calcium);
            ItemStack aApatiteDust = MatUnifier.get(OrePrefixes.dust, Materials.Apatite);
            ItemStack aGlauconiteDust = MatUnifier.get(OrePrefixes.dust, Materials.Glauconite);
            GT_Values.RA.addChemicalRecipe(aCalciteDust, MatUnifier.get(OrePrefixes.dust, Materials.Sulfur, 1), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(2), 200);
            GT_Values.RA.addChemicalRecipe(aCalciteDust, MatUnifier.get(OrePrefixes.dust, Materials.Phosphorus, 1), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(3), 300);
            GT_Values.RA.addChemicalRecipe(aCalciteDust, MatUnifier.get(OrePrefixes.dust, Materials.Phosphate, 1), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(2), 200);
            GT_Values.RA.addChemicalRecipe(aCalciteDust, MatUnifier.get(OrePrefixes.dust, Materials.Ash, 3), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(1), 100);
            GT_Values.RA.addChemicalRecipe(aCalciteDust, MatUnifier.get(OrePrefixes.dust, Materials.DarkAsh, 1), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(1), 100);
            GT_Values.RA.addChemicalRecipe(aCalciumDust, MatUnifier.get(OrePrefixes.dust, Materials.Sulfur, 1), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(3), 300);
            GT_Values.RA.addChemicalRecipe(aCalciumDust, MatUnifier.get(OrePrefixes.dust, Materials.Phosphorus, 1), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(4), 400);
            GT_Values.RA.addChemicalRecipe(aCalciumDust, MatUnifier.get(OrePrefixes.dust, Materials.Phosphate, 1), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(3), 300);
            GT_Values.RA.addChemicalRecipe(aCalciumDust, MatUnifier.get(OrePrefixes.dust, Materials.Ash, 3), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(2), 200);
            GT_Values.RA.addChemicalRecipe(aCalciumDust, MatUnifier.get(OrePrefixes.dust, Materials.DarkAsh, 1), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(2), 200);
            GT_Values.RA.addChemicalRecipe(aApatiteDust, MatUnifier.get(OrePrefixes.dust, Materials.Sulfur, 1), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(3), 300);
            GT_Values.RA.addChemicalRecipe(aApatiteDust, MatUnifier.get(OrePrefixes.dust, Materials.Phosphorus, 1), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(4), 400);
            GT_Values.RA.addChemicalRecipe(aApatiteDust, MatUnifier.get(OrePrefixes.dust, Materials.Phosphate, 1), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(3), 300);
            GT_Values.RA.addChemicalRecipe(aApatiteDust, MatUnifier.get(OrePrefixes.dust, Materials.Ash, 3), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(2), 200);
            GT_Values.RA.addChemicalRecipe(aApatiteDust, MatUnifier.get(OrePrefixes.dust, Materials.DarkAsh, 1), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(2), 200);
            GT_Values.RA.addChemicalRecipe(aGlauconiteDust, MatUnifier.get(OrePrefixes.dust, Materials.Sulfur, 1), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(3), 300);
            GT_Values.RA.addChemicalRecipe(aGlauconiteDust, MatUnifier.get(OrePrefixes.dust, Materials.Phosphorus, 1), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(4), 400);
            GT_Values.RA.addChemicalRecipe(aGlauconiteDust, MatUnifier.get(OrePrefixes.dust, Materials.Phosphate, 1), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(3), 300);
            GT_Values.RA.addChemicalRecipe(aGlauconiteDust, MatUnifier.get(OrePrefixes.dust, Materials.Ash, 3), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(2), 200);
            GT_Values.RA.addChemicalRecipe(aGlauconiteDust, MatUnifier.get(OrePrefixes.dust, Materials.DarkAsh, 1), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(2), 200);
        }
        ItemStack aSugarDust = MatUnifier.get(OrePrefixes.dust, Materials.Sugar);
        GT_Values.RA.addBrewingRecipe(ItemList.IC2_Hops.get(1), FluidRegistry.getFluid("potion.wheatyjuice"), FluidRegistry.getFluid("potion.wheatyhopsjuice"), false);
        GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Wheat), FluidRegistry.getFluid("potion.hopsjuice"), FluidRegistry.getFluid("potion.wheatyhopsjuice"), false);
        GT_Values.RA.addBrewingRecipe(aSugarDust, FluidRegistry.getFluid("potion.tea"), FluidRegistry.getFluid("potion.sweettea"), true);
        GT_Values.RA.addBrewingRecipe(aSugarDust, FluidRegistry.getFluid("potion.coffee"), FluidRegistry.getFluid("potion.cafeaulait"), false);
        GT_Values.RA.addBrewingRecipe(aSugarDust, FluidRegistry.getFluid("potion.cafeaulait"), FluidRegistry.getFluid("potion.laitaucafe"), true);
        GT_Values.RA.addBrewingRecipe(aSugarDust, FluidRegistry.getFluid("potion.lemonjuice"), FluidRegistry.getFluid("potion.lemonade"), false);
        GT_Values.RA.addBrewingRecipe(aSugarDust, FluidRegistry.getFluid("potion.darkcoffee"), FluidRegistry.getFluid("potion.darkcafeaulait"), true);
        GT_Values.RA.addBrewingRecipe(aSugarDust, FluidRegistry.getFluid("potion.darkchocolatemilk"), FluidRegistry.getFluid("potion.chocolatemilk"), false);
        GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Ice), FluidRegistry.getFluid("potion.tea"), FluidRegistry.getFluid("potion.icetea"), false);
        GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Gunpowder), FluidRegistry.getFluid("potion.lemonade"), FluidRegistry.getFluid("potion.cavejohnsonsgrenadejuice"), true);
        GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Sugar), FluidRegistry.getFluid("potion.mundane"), FluidRegistry.getFluid("potion.purpledrink"), true);
        GT_Values.RA.addBrewingRecipe(new ItemStack(Items.fermented_spider_eye, 1, 0), FluidRegistry.getFluid("potion.mundane"), FluidRegistry.getFluid("potion.weakness"), false);
        GT_Values.RA.addBrewingRecipe(new ItemStack(Items.fermented_spider_eye, 1, 0), FluidRegistry.getFluid("potion.thick"), FluidRegistry.getFluid("potion.weakness"), false);

        addPotionRecipes("waterbreathing", new ItemStack(Items.fish, 1, 3));
        addPotionRecipes("fireresistance", new ItemStack(Items.magma_cream, 1, 0));
        addPotionRecipes("nightvision", new ItemStack(Items.golden_carrot, 1, 0));
        addPotionRecipes("weakness", new ItemStack(Items.fermented_spider_eye, 1, 0));
        addPotionRecipes("poison", new ItemStack(Items.spider_eye, 1, 0));
        addPotionRecipes("health", new ItemStack(Items.speckled_melon, 1, 0));
        addPotionRecipes("regen", new ItemStack(Items.ghast_tear, 1, 0));
        addPotionRecipes("speed", MatUnifier.get(OrePrefixes.dust, Materials.Sugar));
        addPotionRecipes("strength", MatUnifier.get(OrePrefixes.dust, Materials.Blaze));

        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("milk", 50), FluidRegistry.getFluidStack("potion.mundane", 25), 1024, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.lemonjuice", 50), FluidRegistry.getFluidStack("potion.limoncello", 25), 1024, true);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.applejuice", 50), FluidRegistry.getFluidStack("potion.cider", 25), 1024, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.goldenapplejuice", 50), FluidRegistry.getFluidStack("potion.goldencider", 25), 1024, true);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.idunsapplejuice", 50), FluidRegistry.getFluidStack("potion.notchesbrew", 25), 1024, true);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.reedwater", 50), FluidRegistry.getFluidStack("potion.rum", 25), 1024, true);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.rum", 50), FluidRegistry.getFluidStack("potion.piratebrew", 10), 2048, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.grapejuice", 50), FluidRegistry.getFluidStack("potion.wine", 25), 1024, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.wheatyjuice", 50), FluidRegistry.getFluidStack("potion.scotch", 25), 1024, true);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.scotch", 50), FluidRegistry.getFluidStack("potion.glenmckenner", 10), 2048, true);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.wheatyhopsjuice", 50), FluidRegistry.getFluidStack("potion.beer", 25), 1024, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.hopsjuice", 50), FluidRegistry.getFluidStack("potion.darkbeer", 25), 1024, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.darkbeer", 50), FluidRegistry.getFluidStack("potion.dragonblood", 10), 2048, true);

        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.beer", 75), FluidRegistry.getFluidStack("potion.vinegar", 50), 2048, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.cider", 75), FluidRegistry.getFluidStack("potion.vinegar", 50), 2048, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.goldencider", 75), FluidRegistry.getFluidStack("potion.vinegar", 50), 2048, true);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.rum", 75), FluidRegistry.getFluidStack("potion.vinegar", 50), 2048, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.wine", 75), FluidRegistry.getFluidStack("potion.vinegar", 50), 2048, false);

        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.awkward", 50), FluidRegistry.getFluidStack("potion.weakness", 25), 1024, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.mundane", 50), FluidRegistry.getFluidStack("potion.weakness", 25), 1024, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.thick", 50), FluidRegistry.getFluidStack("potion.weakness", 25), 1024, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.poison", 50), FluidRegistry.getFluidStack("potion.damage", 25), 1024, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.health", 50), FluidRegistry.getFluidStack("potion.damage", 25), 1024, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.waterbreathing", 50), FluidRegistry.getFluidStack("potion.damage", 25), 1024, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.nightvision", 50), FluidRegistry.getFluidStack("potion.invisibility", 25), 1024, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.fireresistance", 50), FluidRegistry.getFluidStack("potion.slowness", 25), 1024, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.speed", 50), FluidRegistry.getFluidStack("potion.slowness", 25), 1024, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.strength", 50), FluidRegistry.getFluidStack("potion.weakness", 25), 1024, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.regen", 50), FluidRegistry.getFluidStack("potion.poison", 25), 1024, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.poison.strong", 50), FluidRegistry.getFluidStack("potion.damage.strong", 10), 2048, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.health.strong", 50), FluidRegistry.getFluidStack("potion.damage.strong", 10), 2048, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.speed.strong", 50), FluidRegistry.getFluidStack("potion.slowness.strong", 10), 2048, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.strength.strong", 50), FluidRegistry.getFluidStack("potion.weakness.strong", 10), 2048, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.nightvision.long", 50), FluidRegistry.getFluidStack("potion.invisibility.long", 10), 2048, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.regen.strong", 50), FluidRegistry.getFluidStack("potion.poison.strong", 10), 2048, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.poison.long", 50), FluidRegistry.getFluidStack("potion.damage.long", 10), 2048, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.waterbreathing.long", 50), FluidRegistry.getFluidStack("potion.damage.long", 10), 2048, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.fireresistance.long", 50), FluidRegistry.getFluidStack("potion.slowness.long", 10), 2048, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.speed.long", 50), FluidRegistry.getFluidStack("potion.slowness.long", 10), 2048, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.strength.long", 50), FluidRegistry.getFluidStack("potion.weakness.long", 10), 2048, false);
        GT_Values.RA.addFermentingRecipe(FluidRegistry.getFluidStack("potion.regen.long", 50), FluidRegistry.getFluidStack("potion.poison.long", 10), 2048, false);

        GT_ModHandler.addSmeltingRecipe(new ItemStack(Items.slime_ball, 1), ItemList.IC2_Resin.get(1));

        GT_ModHandler.addExtractionRecipe(new ItemStack(Blocks.bookshelf, 1, 32767), new ItemStack(Items.book, 3, 0));
        GT_ModHandler.addExtractionRecipe(new ItemStack(Items.slime_ball, 1), MatUnifier.get(OrePrefixes.dust, Materials.RawRubber, 2));
        GT_ModHandler.addExtractionRecipe(ItemList.IC2_Resin.get(1), MatUnifier.get(OrePrefixes.dust, Materials.RawRubber, 3));
        GT_ModHandler.addExtractionRecipe(GT_ModHandler.getIC2Item("rubberSapling", 1), MatUnifier.get(OrePrefixes.dust, Materials.RawRubber));
        GT_ModHandler.addExtractionRecipe(GT_ModHandler.getIC2Item("rubberLeaves", 16), MatUnifier.get(OrePrefixes.dust, Materials.RawRubber));
        GT_ModHandler.addExtractionRecipe(ItemList.Cell_Air.get(1), ItemList.Cell_Empty.get(1));
        if (Loader.isModLoaded(aTextEBXL)) {
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "waterplant1", 1, 0), new ItemStack(Items.dye, 4, 2));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "vines", 1, 0), new ItemStack(Items.dye, 4, 1));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower3", 1, 11), new ItemStack(Items.dye, 4, 11));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower3", 1, 10), new ItemStack(Items.dye, 4, 5));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower3", 1, 9), new ItemStack(Items.dye, 4, 14));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower3", 1, 8), new ItemStack(Items.dye, 4, 14));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower3", 1, 7), new ItemStack(Items.dye, 4, 1));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower3", 1, 6), new ItemStack(Items.dye, 4, 1));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower3", 1, 5), new ItemStack(Items.dye, 4, 11));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower3", 1, 0), new ItemStack(Items.dye, 4, 9));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower3", 1, 4), new ItemStack(Items.dye, 4, 11));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower3", 1, 3), new ItemStack(Items.dye, 4, 13));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower1", 1, 3), new ItemStack(Items.dye, 4, 5));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower3", 1, 2), new ItemStack(Items.dye, 4, 5));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower1", 1, 1), new ItemStack(Items.dye, 4, 12));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower2", 1, 15), new ItemStack(Items.dye, 4, 11));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower2", 1, 14), new ItemStack(Items.dye, 4, 1));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower2", 1, 13), new ItemStack(Items.dye, 4, 9));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower2", 1, 12), new ItemStack(Items.dye, 4, 14));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower2", 1, 11), new ItemStack(Items.dye, 4, 7));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower1", 1, 7), new ItemStack(Items.dye, 4, 7));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower1", 1, 2), new ItemStack(Items.dye, 4, 11));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower3", 1, 13), new ItemStack(Items.dye, 4, 6));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower2", 1, 6), new ItemStack(Items.dye, 4, 12));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower2", 1, 5), new ItemStack(Items.dye, 4, 10));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower2", 1, 2), new ItemStack(Items.dye, 4, 1));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower2", 1, 1), new ItemStack(Items.dye, 4, 9));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower2", 1, 0), new ItemStack(Items.dye, 4, 13));

            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower2", 1, 7), GT_ModHandler.getModItem(aTextEBXL, "extrabiomes.dye", 1, 0));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower3", 1, 1), GT_ModHandler.getModItem(aTextEBXL, "extrabiomes.dye", 1, 1));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower3", 1, 12), GT_ModHandler.getModItem(aTextEBXL, "extrabiomes.dye", 1, 1));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower2", 1, 4), GT_ModHandler.getModItem(aTextEBXL, "extrabiomes.dye", 1, 1));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower1", 1, 6), GT_ModHandler.getModItem(aTextEBXL, "extrabiomes.dye", 1, 2));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower2", 1, 8), GT_ModHandler.getModItem(aTextEBXL, "extrabiomes.dye", 1, 3));
            GT_ModHandler.addExtractionRecipe(GT_ModHandler.getModItem(aTextEBXL, "flower2", 1, 3), GT_ModHandler.getModItem(aTextEBXL, "extrabiomes.dye", 1, 3));

            GT_ModHandler.addCompressionRecipe(GT_ModHandler.getModItem(aTextEBXL, "saplings_1", 4, 0), ItemList.IC2_Plantball.get(1));
            GT_ModHandler.addCompressionRecipe(GT_ModHandler.getModItem(aTextEBXL, "saplings_1", 4, 1), ItemList.IC2_Plantball.get(1));
            GT_ModHandler.addCompressionRecipe(GT_ModHandler.getModItem(aTextEBXL, "saplings_1", 4, 2), ItemList.IC2_Plantball.get(1));
            GT_ModHandler.addCompressionRecipe(GT_ModHandler.getModItem(aTextEBXL, "saplings_1", 4, 3), ItemList.IC2_Plantball.get(1));
            GT_ModHandler.addCompressionRecipe(GT_ModHandler.getModItem(aTextEBXL, "saplings_1", 4, 4), ItemList.IC2_Plantball.get(1));
            GT_ModHandler.addCompressionRecipe(GT_ModHandler.getModItem(aTextEBXL, "saplings_1", 4, 5), ItemList.IC2_Plantball.get(1));
            GT_ModHandler.addCompressionRecipe(GT_ModHandler.getModItem(aTextEBXL, "saplings_1", 4, 6), ItemList.IC2_Plantball.get(1));
            GT_ModHandler.addCompressionRecipe(GT_ModHandler.getModItem(aTextEBXL, "saplings_1", 4, 7), ItemList.IC2_Plantball.get(1));
            GT_ModHandler.addCompressionRecipe(GT_ModHandler.getModItem(aTextEBXL, "saplings_2", 4, 0), ItemList.IC2_Plantball.get(1));
            GT_ModHandler.addCompressionRecipe(GT_ModHandler.getModItem(aTextEBXL, "saplings_2", 4, 1), ItemList.IC2_Plantball.get(1));
            GT_ModHandler.addCompressionRecipe(GT_ModHandler.getModItem(aTextEBXL, "saplings_2", 4, 2), ItemList.IC2_Plantball.get(1));
            GT_ModHandler.addCompressionRecipe(GT_ModHandler.getModItem(aTextEBXL, "saplings_2", 4, 3), ItemList.IC2_Plantball.get(1));
            GT_ModHandler.addCompressionRecipe(GT_ModHandler.getModItem(aTextEBXL, "saplings_2", 4, 4), ItemList.IC2_Plantball.get(1));

        }
        GT_ModHandler.addCompressionRecipe(ItemList.IC2_Compressed_Coal_Chunk.get(1), ItemList.IC2_Industrial_Diamond.get(1));
        GT_ModHandler.addCompressionRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Uranium, 1), GT_ModHandler.getIC2Item("Uran238", 1));
        GT_ModHandler.addCompressionRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Uranium235, 1), GT_ModHandler.getIC2Item("Uran235", 1));
        GT_ModHandler.addCompressionRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Plutonium, 1), GT_ModHandler.getIC2Item("Plutonium", 1));
        GT_ModHandler.addCompressionRecipe(MatUnifier.get(OrePrefixes.dustTiny, Materials.Uranium235), GT_ModHandler.getIC2Item("smallUran235", 1));
        GT_ModHandler.addCompressionRecipe(MatUnifier.get(OrePrefixes.dustTiny, Materials.Plutonium), GT_ModHandler.getIC2Item("smallPlutonium", 1));
        GT_ModHandler.addCompressionRecipe(new ItemStack(Blocks.ice, 2, 32767), new ItemStack(Blocks.packed_ice, 1, 0));
        GT_ModHandler.addCompressionRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Ice, 1), new ItemStack(Blocks.ice, 1, 0));
        GT_ModHandler.addCompressionRecipe(MatUnifier.get(OrePrefixes.gem, Materials.CertusQuartz, 4), GT_ModHandler.getModItem(aTextAE, "tile.BlockQuartz", 1));
        GT_ModHandler.addCompressionRecipe(GT_ModHandler.getModItem(aTextAE, aTextAEMM, 8, 10), GT_ModHandler.getModItem(aTextAE, "tile.BlockQuartz", 1));
        GT_ModHandler.addCompressionRecipe(GT_ModHandler.getModItem(aTextAE, aTextAEMM, 8, 11), new ItemStack(Blocks.quartz_block, 1, 0));
        GT_ModHandler.addCompressionRecipe(GT_ModHandler.getModItem(aTextAE, aTextAEMM, 8, 12), GT_ModHandler.getModItem(aTextAE, "tile.BlockFluix", 1));
        GT_ModHandler.addCompressionRecipe(new ItemStack(Items.quartz, 4, 0), new ItemStack(Blocks.quartz_block, 1, 0));
        GT_ModHandler.addCompressionRecipe(new ItemStack(Items.wheat, 9, 0), new ItemStack(Blocks.hay_block, 1, 0));
        GT_ModHandler.addCompressionRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Glowstone, 4), new ItemStack(Blocks.glowstone, 1));
        GT_ModHandler.addCompressionRecipe(Materials.Fireclay.getDust(1), ItemList.CompressedFireclay.get(1));

        GameRegistry.addSmelting(ItemList.CompressedFireclay.get(1), ItemList.Firebrick.get(1), 0);
        GT_Values.RA.addCutterRecipe(MatUnifier.get(OrePrefixes.block, Materials.Graphite, 1), MatUnifier.get(OrePrefixes.ingot, Materials.Graphite, 9), GT_Values.NI, 500, 48);
        GT_ModHandler.removeFurnaceSmelting(MatUnifier.get(OrePrefixes.ore, Materials.Graphite, 1));
        GT_ModHandler.addSmeltingRecipe(MatUnifier.get(OrePrefixes.ore, Materials.Graphite, 1), MatUnifier.get(OrePrefixes.dust, Materials.Graphite, 1));
        GT_ModHandler.removeFurnaceSmelting(MatUnifier.get(OrePrefixes.oreBlackgranite, Materials.Graphite, 1));
        GT_ModHandler.addSmeltingRecipe(MatUnifier.get(OrePrefixes.oreBlackgranite, Materials.Graphite, 1), MatUnifier.get(OrePrefixes.dust, Materials.Graphite, 1));
        GT_ModHandler.removeFurnaceSmelting(MatUnifier.get(OrePrefixes.oreEndstone, Materials.Graphite, 1));
        GT_ModHandler.addSmeltingRecipe(MatUnifier.get(OrePrefixes.oreEndstone, Materials.Graphite, 1), MatUnifier.get(OrePrefixes.dust, Materials.Graphite, 1));
        GT_ModHandler.removeFurnaceSmelting(MatUnifier.get(OrePrefixes.oreNetherrack, Materials.Graphite, 1));
        GT_ModHandler.addSmeltingRecipe(MatUnifier.get(OrePrefixes.oreNetherrack, Materials.Graphite, 1), MatUnifier.get(OrePrefixes.dust, Materials.Graphite, 1));
        GT_ModHandler.removeFurnaceSmelting(MatUnifier.get(OrePrefixes.oreRedgranite, Materials.Graphite, 1));
        GT_ModHandler.addSmeltingRecipe(MatUnifier.get(OrePrefixes.oreRedgranite, Materials.Graphite, 1), MatUnifier.get(OrePrefixes.dust, Materials.Graphite, 1));

        GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getModItem(aTextAE, "tile.BlockSkyStone", 1, 32767), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 45), GT_Values.NI, 0, false);
        GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getModItem(aTextAE, "tile.BlockSkyChest", 1, 32767), GT_ModHandler.getModItem(aTextAE, aTextAEMM, 8, 45), GT_Values.NI, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.blaze_rod, 1), new ItemStack(Items.blaze_powder, 3), new ItemStack(Items.blaze_powder, 1), 50, false);
        GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getModItem("Railcraft", "cube.crushed.obsidian", 1), MatUnifier.get(OrePrefixes.dust, Materials.Obsidian, 1), GT_Values.NI, 0, true);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.flint, 1, 32767), MatUnifier.get(OrePrefixes.dustTiny, Materials.Flint, 4), MatUnifier.get(OrePrefixes.dustTiny, Materials.Flint, 1), 40, true);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.red_mushroom, 1, 32767), ItemList.IC2_Grin_Powder.get(1));
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.item_frame, 1, 32767), new ItemStack(Items.leather, 1), MatUnifier.getDust(Materials.Wood, OrePrefixes.stick.mMaterialAmount * 4), 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.bow, 1, 0), new ItemStack(Items.string, 3), MatUnifier.getDust(Materials.Wood, OrePrefixes.stick.mMaterialAmount * 3), 95, false);

        GT_Values.RA.addForgeHammerRecipe(new ItemStack(Blocks.stonebrick, 1, 0), new ItemStack(Blocks.stonebrick, 1, 2), 10, 16);
        GT_Values.RA.addForgeHammerRecipe(new ItemStack(Blocks.stone, 1, 0), new ItemStack(Blocks.cobblestone, 1, 0), 10, 16);
        GT_Values.RA.addForgeHammerRecipe(new ItemStack(Blocks.cobblestone, 1, 0), new ItemStack(Blocks.gravel, 1, 0), 10, 16);
        GT_Values.RA.addForgeHammerRecipe(new ItemStack(Blocks.gravel, 1, 0), new ItemStack(Blocks.sand, 1, 0), 10, 16);
        GT_Values.RA.addSifterRecipe(new ItemStack(Blocks.gravel, 1, 0), new ItemStack[]{new ItemStack(Items.flint, 1, 0)}, new int[]{10000}, 800, 16);
        GT_Values.RA.addForgeHammerRecipe(new ItemStack(Blocks.sandstone, 1, 32767), new ItemStack(Blocks.sand, 1, 0), 10, 16);
        GT_Values.RA.addForgeHammerRecipe(new ItemStack(Blocks.ice, 1, 0), MatUnifier.get(OrePrefixes.dust, Materials.Ice, 1), 10, 16);
        GT_Values.RA.addForgeHammerRecipe(new ItemStack(Blocks.packed_ice, 1, 0), MatUnifier.get(OrePrefixes.dust, Materials.Ice, 2), 10, 16);
        GT_Values.RA.addForgeHammerRecipe(new ItemStack(Blocks.hardened_clay, 1, 0), MatUnifier.get(OrePrefixes.dust, Materials.Clay, 1), 10, 16);
        GT_Values.RA.addForgeHammerRecipe(new ItemStack(Blocks.stained_hardened_clay, 1, 32767), MatUnifier.get(OrePrefixes.dust, Materials.Clay, 1), 10, 16);
        GT_Values.RA.addForgeHammerRecipe(new ItemStack(Blocks.brick_block, 1, 0), new ItemStack(Items.brick, 3, 0), 10, 16);
        GT_Values.RA.addForgeHammerRecipe(new ItemStack(Blocks.nether_brick, 1, 0), new ItemStack(Items.netherbrick, 3, 0), 10, 16);
        GT_Values.RA.addForgeHammerRecipe(new ItemStack(Blocks.stained_glass, 1, 32767), MatUnifier.get(OrePrefixes.dust, Materials.Glass, 1), 10, 16);
        GT_Values.RA.addForgeHammerRecipe(new ItemStack(Blocks.glass, 1, 32767), MatUnifier.get(OrePrefixes.dust, Materials.Glass, 1), 10, 16);
        GT_Values.RA.addForgeHammerRecipe(new ItemStack(Blocks.stained_glass_pane, 1, 32767), MatUnifier.get(OrePrefixes.dustTiny, Materials.Glass, 3), 10, 16);
        GT_Values.RA.addForgeHammerRecipe(new ItemStack(Blocks.glass_pane, 1, 32767), MatUnifier.get(OrePrefixes.dustTiny, Materials.Glass, 3), 10, 16);
        GT_Values.RA.addForgeHammerRecipe(Materials.Brick.getIngots(1), Materials.Brick.getDustSmall(1), 10, 16);
        GT_Values.RA.addForgeHammerRecipe(ItemList.Firebrick.get(1), Materials.Brick.getDust(1), 10, 16);
        GT_Values.RA.addForgeHammerRecipe(ItemList.Casing_Firebricks.get(1), ItemList.Firebrick.get(3), 10, 16);

        GT_ModHandler.addPulverisationRecipe(Materials.Brick.getIngots(1), Materials.Brick.getDustSmall(1));
        GT_ModHandler.addPulverisationRecipe(ItemList.CompressedFireclay.get(1), Materials.Fireclay.getDustSmall(1));
        GT_ModHandler.addPulverisationRecipe(ItemList.Firebrick.get(1), Materials.Brick.getDust(1));
        GT_ModHandler.addPulverisationRecipe(ItemList.Casing_Firebricks.get(1), Materials.Brick.getDust(4));
        GT_ModHandler.addPulverisationRecipe(ItemList.Machine_Bricked_BlastFurnace.get(1), Materials.Brick.getDust(8), Materials.Iron.getDust(1), true);

        GT_Values.RA.addForgeHammerRecipe(GT_ModHandler.getModItem("HardcoreEnderExpansion", "endium_ore", 1), MatUnifier.get(OrePrefixes.crushed, Materials.Endium, 1), 10, 16);
        GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getModItem("HardcoreEnderExpansion", "endium_ore", 1), MatUnifier.get(OrePrefixes.crushed, Materials.Endium, 2), MatUnifier.get(OrePrefixes.dust, Materials.Endstone, 1), 50, GT_Values.NI, 0, true);
        MatUnifier.set(OrePrefixes.ingot, Materials.Endium, GT_ModHandler.getModItem("HardcoreEnderExpansion", "endium_ingot", 1), true, true);

        GT_Values.RA.addAmplifier(ItemList.IC2_Scrap.get(9), 180, 1);
        GT_Values.RA.addAmplifier(ItemList.IC2_Scrapbox.get(1), 180, 1);

        GT_Values.RA.addBoxingRecipe(ItemList.IC2_Scrap.get(9), ItemList.Schematic_3by3.get(0), ItemList.IC2_Scrapbox.get(1), 16, 1);

        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Steel, 3), MatUnifier.get(OrePrefixes.ingot, Materials.Silicon, 1), GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.ingot, Materials.ElectricalSteel, 4), MatUnifier.get(OrePrefixes.dustSmall, Materials.DarkAsh, 2), (int) Math.max(Materials.ElectricalSteel.getMass() / 40L, 1) * Materials.ElectricalSteel.mBlastFurnaceTemp, 120, Materials.ElectricalSteel.mBlastFurnaceTemp);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Gold, 1), MatUnifier.get(OrePrefixes.dust, Materials.Glowstone, 1), Materials.Redstone.getMolten(144), GT_Values.NF, MatUnifier.get(OrePrefixes.ingot, Materials.EnergeticAlloy, 1), null, Materials.EnergeticAlloy.mBlastFurnaceTemp / 10, 120, Materials.EnergeticAlloy.mBlastFurnaceTemp);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.EnergeticAlloy, 1), MatUnifier.get(OrePrefixes.gem, Materials.EnderPearl, 1), GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.ingot, Materials.PhasedGold, 1), null, Materials.VibrantAlloy.mBlastFurnaceTemp / 10, 480, Materials.VibrantAlloy.mBlastFurnaceTemp);
        GT_Values.RA.addAlloySmelterRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Redstone, 1), MatUnifier.get(OrePrefixes.ingot, Materials.Silicon, 1), MatUnifier.get(OrePrefixes.ingot, Materials.RedAlloy, 1), 400, 24);
        GT_Values.RA.addAlloySmelterRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Redstone, 1), MatUnifier.get(OrePrefixes.ingot, Materials.Iron, 1), MatUnifier.get(OrePrefixes.ingot, Materials.ConductiveIron, 1), 400, 24);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Iron, 1), MatUnifier.get(OrePrefixes.gem, Materials.EnderPearl, 1), GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.ingot, Materials.PhasedIron, 1), null, Materials.PulsatingIron.mBlastFurnaceTemp / 10, 120, Materials.PulsatingIron.mBlastFurnaceTemp);
        GT_Values.RA.addAlloySmelterRecipe(new ItemStack(Blocks.soul_sand), MatUnifier.get(OrePrefixes.ingot, Materials.Gold, 1), MatUnifier.get(OrePrefixes.ingot, Materials.Soularium, 1), 400, 24);

        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Tungsten, 1), MatUnifier.get(OrePrefixes.ingot, Materials.Steel, 1), GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.ingotHot, Materials.TungstenSteel, 2), GT_Values.NI, (int) Math.max(Materials.TungstenSteel.getMass() / 80L, 1) * Materials.TungstenSteel.mBlastFurnaceTemp, 480, Materials.TungstenSteel.mBlastFurnaceTemp);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Tungsten, 1), MatUnifier.get(OrePrefixes.dust, Materials.Carbon, 1), GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.ingotHot, Materials.TungstenCarbide, 2), GT_Values.NI, (int) Math.max(Materials.TungstenCarbide.getMass() / 40L, 1) * Materials.TungstenCarbide.mBlastFurnaceTemp, 480, Materials.TungstenCarbide.mBlastFurnaceTemp);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Vanadium, 3), MatUnifier.get(OrePrefixes.ingot, Materials.Gallium, 1), GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.ingotHot, Materials.VanadiumGallium, 4), GT_Values.NI, (int) Math.max(Materials.VanadiumGallium.getMass() / 40L, 1) * Materials.VanadiumGallium.mBlastFurnaceTemp, 480, Materials.VanadiumGallium.mBlastFurnaceTemp);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Niobium, 1), MatUnifier.get(OrePrefixes.ingot, Materials.Titanium, 1), GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.ingotHot, Materials.NiobiumTitanium, 2), GT_Values.NI, (int) Math.max(Materials.NiobiumTitanium.getMass() / 80L, 1) * Materials.NiobiumTitanium.mBlastFurnaceTemp, 480, Materials.NiobiumTitanium.mBlastFurnaceTemp);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Nickel, 4), MatUnifier.get(OrePrefixes.ingot, Materials.Chrome, 1), GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.ingotHot, Materials.Nichrome, 5), GT_Values.NI, (int) Math.max(Materials.Nichrome.getMass() / 32L, 1) * Materials.Nichrome.mBlastFurnaceTemp, 480, Materials.Nichrome.mBlastFurnaceTemp);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Ruby, 1), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.nugget, Materials.Aluminium, 3), MatUnifier.get(OrePrefixes.dustTiny, Materials.DarkAsh, 1), 400, 100, 1200);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.gem, Materials.Ruby, 1), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.nugget, Materials.Aluminium, 3), MatUnifier.get(OrePrefixes.dustTiny, Materials.DarkAsh, 1), 320, 100, 1200);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.dust, Materials.GreenSapphire, 1), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.nugget, Materials.Aluminium, 3), MatUnifier.get(OrePrefixes.dustTiny, Materials.DarkAsh, 1), 400, 100, 1200);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.gem, Materials.GreenSapphire, 1), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.nugget, Materials.Aluminium, 3), MatUnifier.get(OrePrefixes.dustTiny, Materials.DarkAsh, 1), 320, 100, 1200);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Sapphire, 1), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.nugget, Materials.Aluminium, 3), GT_Values.NI, 400, 100, 1200);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.gem, Materials.Sapphire, 1), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.nugget, Materials.Aluminium, 3), GT_Values.NI, 320, 100, 1200);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Ilmenite, 1), MatUnifier.get(OrePrefixes.dust, Materials.Carbon, 1), GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.nugget, Materials.WroughtIron, 4), MatUnifier.get(OrePrefixes.dustTiny, Materials.Rutile, 4), 800, 500, 1700);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.gem, Materials.Ilmenite, 1), MatUnifier.get(OrePrefixes.dust, Materials.Carbon, 1), GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.nugget, Materials.WroughtIron, 4), MatUnifier.get(OrePrefixes.dustTiny, Materials.Rutile, 4), 800, 500, 1700);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Magnesium, 2), GT_Values.NI, Materials.Titaniumtetrachloride.getFluid(1000), GT_Values.NF, MatUnifier.get(OrePrefixes.ingotHot, Materials.Titanium, 1), MatUnifier.get(OrePrefixes.dust, Materials.Magnesiumchloride, 2), 800, 480, Materials.Titanium.mBlastFurnaceTemp + 200);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Galena, 1), GT_Values.NI, Materials.Oxygen.getGas(2000), GT_Values.NF, MatUnifier.get(OrePrefixes.nugget, Materials.Silver, 4), MatUnifier.get(OrePrefixes.nugget, Materials.Lead, 4), 400, 500, 1500);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.gem, Materials.Galena, 1), GT_Values.NI, Materials.Oxygen.getGas(2000), GT_Values.NF, MatUnifier.get(OrePrefixes.nugget, Materials.Silver, 5), MatUnifier.get(OrePrefixes.nugget, Materials.Lead, 5), 320, 500, 1500);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Magnetite, 1), GT_Values.NI, Materials.Oxygen.getGas(2000), GT_Values.NF, MatUnifier.get(OrePrefixes.nugget, Materials.WroughtIron, 4), MatUnifier.get(OrePrefixes.dustSmall, Materials.DarkAsh, 1), 400, 500, 1000);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.gem, Materials.Magnetite, 1), GT_Values.NI, Materials.Oxygen.getGas(2000), GT_Values.NF, MatUnifier.get(OrePrefixes.nugget, Materials.WroughtIron, 5), MatUnifier.get(OrePrefixes.dustSmall, Materials.Ash, 1), 320, 500, 1000);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Iron, 1), GT_Values.NI, Materials.Oxygen.getGas(1000), GT_Values.NF, MatUnifier.get(OrePrefixes.ingot, Materials.Steel, 1), Materials.Ash.getDustTiny(1), 500, 120, 1000);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.PigIron, 1), GT_Values.NI, Materials.Oxygen.getGas(1000), GT_Values.NF, MatUnifier.get(OrePrefixes.ingot, Materials.Steel, 1), Materials.Ash.getDustTiny(1), 100, 120, 1000);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.WroughtIron, 1), GT_Values.NI, Materials.Oxygen.getGas(1000), GT_Values.NF, MatUnifier.get(OrePrefixes.ingot, Materials.Steel, 1), Materials.Ash.getDustTiny(1), 100, 120, 1000);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.MeteoricIron, 1), GT_Values.NI, Materials.Oxygen.getGas(1000), GT_Values.NF, MatUnifier.get(OrePrefixes.ingot, Materials.MeteoricSteel, 1), MatUnifier.get(OrePrefixes.dustSmall, Materials.DarkAsh, 1), 500, 120, 1200);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Copper, 1), GT_Values.NI, Materials.Oxygen.getGas(1000), GT_Values.NF, MatUnifier.get(OrePrefixes.ingot, Materials.AnnealedCopper, 1), GT_Values.NI, 500, 120, 1200);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Copper, 1), GT_Values.NI, Materials.Oxygen.getGas(1000), GT_Values.NF, MatUnifier.get(OrePrefixes.ingot, Materials.AnnealedCopper, 1), GT_Values.NI, 500, 120, 1200);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.ElectricalSteel, 1), MatUnifier.get(OrePrefixes.dust, Materials.Obsidian, 1), GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.ingot, Materials.DarkSteel, 1), GT_Values.NI, 4000, 120, 2000);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Iridium, 3), MatUnifier.get(OrePrefixes.ingot, Materials.Osmium, 1), Materials.Helium.getGas(1000), null, MatUnifier.get(OrePrefixes.ingotHot, Materials.Osmiridium, 4), null, 500, 1920, 2900);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Naquadah, 1), MatUnifier.get(OrePrefixes.ingot, Materials.Osmiridium, 1), Materials.Argon.getGas(1000), null, MatUnifier.get(OrePrefixes.ingotHot, Materials.NaquadahAlloy, 2), null, 500, 30720, Materials.NaquadahAlloy.mBlastFurnaceTemp);
        GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Gallium, 1), MatUnifier.get(OrePrefixes.ingot, Materials.Arsenic, 1), null, null, MatUnifier.get(OrePrefixes.ingot, Materials.GalliumArsenide, 2), null, 600, 120, Materials.GalliumArsenide.mBlastFurnaceTemp);
        if (!GregTech_API.mIC2Classic) {
            GT_Values.RA.addCannerRecipe(GT_ModHandler.getIC2Item("fuelRod", 1), MatUnifier.get(OrePrefixes.dustTiny, Materials.Lithium, 1), GT_ModHandler.getIC2Item("reactorLithiumCell", 1, 1), null, 16, 64);
            GT_Values.RA.addFluidExtractionRecipe(GT_ModHandler.getIC2Item("TritiumCell", 1), GT_ModHandler.getIC2Item("fuelRod", 1), Materials.Tritium.getGas(32), 10000, 16, 64);
            GT_Values.RA.addCannerRecipe(GT_ModHandler.getIC2Item("fuelRod", 1), MatUnifier.get(OrePrefixes.dust, Materials.Thorium, 3), ItemList.ThoriumCell_1.get(1), null, 30, 16);
            GT_Values.RA.addCannerRecipe(GT_ModHandler.getIC2Item("fuelRod", 1), MatUnifier.get(OrePrefixes.dust, Materials.NaquadahEnriched, 3), ItemList.NaquadahCell_1.get(1), null, 30, 16);
            GT_Values.RA.addCannerRecipe(GT_ModHandler.getIC2Item("fuelRod", 1), GT_ModHandler.getIC2Item("UranFuel", 1), ItemList.Uraniumcell_1.get(1), null, 30, 16);
            GT_Values.RA.addCannerRecipe(GT_ModHandler.getIC2Item("fuelRod", 1), GT_ModHandler.getIC2Item("MOXFuel", 1), ItemList.Moxcell_1.get(1), null, 30, 16);
        }
        GT_Values.RA.addFusionReactorRecipe(Materials.Lithium.getMolten(16), Materials.Tungsten.getMolten(16), Materials.Iridium.getMolten(16), 32, 32768, 300000000);
        GT_Values.RA.addFusionReactorRecipe(Materials.Deuterium.getGas(125), Materials.Tritium.getGas(125), Materials.Helium.getPlasma(125), 16, 4096, 40000000);  //Mark 1 Cheap //
        GT_Values.RA.addFusionReactorRecipe(Materials.Deuterium.getGas(125), Materials.Helium3.getGas(125), Materials.Helium.getPlasma(125), 16, 2048, 60000000); //Mark 1 Expensive //
        GT_Values.RA.addFusionReactorRecipe(Materials.Carbon.getMolten(125), Materials.Helium3.getGas(125), Materials.Oxygen.getPlasma(125), 32, 4096, 80000000); //Mark 1 Expensive //
        GT_Values.RA.addFusionReactorRecipe(Materials.Aluminium.getMolten(16), Materials.Lithium.getMolten(16), Materials.Sulfur.getPlasma(125), 32, 10240, 240000000); //Mark 2 Cheap
        GT_Values.RA.addFusionReactorRecipe(Materials.Beryllium.getMolten(16), Materials.Deuterium.getGas(375), Materials.Nitrogen.getPlasma(175), 16, 16384, 180000000); //Mark 2 Expensive //
        GT_Values.RA.addFusionReactorRecipe(Materials.Silicon.getMolten(16), Materials.Magnesium.getMolten(16), Materials.Iron.getPlasma(125), 32, 8192, 360000000); //Mark 3 Cheap //
        GT_Values.RA.addFusionReactorRecipe(Materials.Potassium.getMolten(16), Materials.Fluorine.getGas(125), Materials.Nickel.getPlasma(125), 16, 32768, 480000000); //Mark 3 Expensive //
        GT_Values.RA.addFusionReactorRecipe(Materials.Beryllium.getMolten(16), Materials.Tungsten.getMolten(16), Materials.Platinum.getMolten(16), 32, 32768, 150000000); //
        GT_Values.RA.addFusionReactorRecipe(Materials.Neodymium.getMolten(16), Materials.Hydrogen.getGas(48), Materials.Europium.getMolten(16), 64, 24576, 150000000); //
        GT_Values.RA.addFusionReactorRecipe(Materials.Lutetium.getMolten(16), Materials.Chrome.getMolten(16), Materials.Americium.getMolten(16), 96, 49152, 200000000); //
        GT_Values.RA.addFusionReactorRecipe(Materials.Plutonium.getMolten(16), Materials.Thorium.getMolten(16), Materials.Naquadah.getMolten(16), 64, 32768, 300000000); //
        GT_Values.RA.addFusionReactorRecipe(Materials.Americium.getMolten(16), Materials.Naquadria.getMolten(16), Materials.Neutronium.getMolten(1), 1200, 98304, 600000000); //

        GT_Values.RA.addFusionReactorRecipe(Materials.Tungsten.getMolten(16), Materials.Helium.getGas(16), Materials.Osmium.getMolten(16), 64, 24578, 150000000); //
        GT_Values.RA.addFusionReactorRecipe(Materials.Manganese.getMolten(16), Materials.Hydrogen.getGas(16), Materials.Iron.getMolten(16), 64, 8192, 120000000); //
        GT_Values.RA.addFusionReactorRecipe(Materials.Mercury.getFluid(16), Materials.Magnesium.getMolten(16), Materials.Uranium.getMolten(16), 64, 49152, 240000000); //
        GT_Values.RA.addFusionReactorRecipe(Materials.Gold.getMolten(16), Materials.Aluminium.getMolten(16), Materials.Uranium.getMolten(16), 64, 49152, 240000000); //
        GT_Values.RA.addFusionReactorRecipe(Materials.Uranium.getMolten(16), Materials.Helium.getGas(16), Materials.Plutonium.getMolten(16), 128, 49152, 480000000); //
        GT_Values.RA.addFusionReactorRecipe(Materials.Vanadium.getMolten(16), Materials.Hydrogen.getGas(125), Materials.Chrome.getMolten(16), 64, 24576, 140000000); //
        GT_Values.RA.addFusionReactorRecipe(Materials.Gallium.getMolten(16), Materials.Radon.getGas(125), Materials.Duranium.getMolten(16), 64, 16384, 140000000); //
        GT_Values.RA.addFusionReactorRecipe(Materials.Titanium.getMolten(16), Materials.Duranium.getMolten(125), Materials.Tritanium.getMolten(16), 64, 32768, 200000000); //
        GT_Values.RA.addFusionReactorRecipe(Materials.Gold.getMolten(16), Materials.Mercury.getFluid(16), Materials.Radon.getGas(125), 64, 32768, 200000000); //
        GT_Values.RA.addFusionReactorRecipe(Materials.NaquadahEnriched.getMolten(15), Materials.Radon.getGas(125), Materials.Naquadria.getMolten(3), 64, 49152, 400000000); //

        GT_ModHandler.removeRecipeByOutput(ItemList.IC2_Fertilizer.get(1));
        GT_Values.RA.addImplosionRecipe(ItemList.IC2_Compressed_Coal_Chunk.get(1), 8, ItemList.IC2_Industrial_Diamond.get(1), MatUnifier.get(OrePrefixes.dustTiny, Materials.DarkAsh, 4));
        GT_Values.RA.addImplosionRecipe(ItemList.Ingot_IridiumAlloy.get(1), 8, ItemList.Plate_IridiumAlloy.get(1), MatUnifier.get(OrePrefixes.dustTiny, Materials.DarkAsh, 4));

        if (Loader.isModLoaded("GalacticraftMars")) {
            GT_ModHandler.addCraftingRecipe(ItemList.Ingot_Heavy1.get(1), GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{"BhB", "CAS", "B B", 'B', OrePrefixes.bolt.get(Materials.StainlessSteel), 'C', OrePrefixes.compressed.get(Materials.Bronze), 'A', OrePrefixes.compressed.get(Materials.Aluminium), 'S', OrePrefixes.compressed.get(Materials.Steel)});
            GT_ModHandler.addCraftingRecipe(ItemList.Ingot_Heavy2.get(1), GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{" BB", "hPC", " BB", 'B', OrePrefixes.bolt.get(Materials.Tungsten), 'C', OrePrefixes.compressed.get(Materials.MeteoricIron), 'P', GT_ModHandler.getModItem("GalacticraftCore", "item.heavyPlating", 1)});
            GT_ModHandler.addCraftingRecipe(ItemList.Ingot_Heavy3.get(1), GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{" BB", "hPC", " BB", 'B', OrePrefixes.bolt.get(Materials.TungstenSteel), 'C', OrePrefixes.compressed.get(Materials.Desh), 'P', GT_ModHandler.getModItem("GalacticraftMars", "item.null", 1, 3)});

            GT_Values.RA.addImplosionRecipe(ItemList.Ingot_Heavy1.get(1), 8, GT_ModHandler.getModItem("GalacticraftCore", "item.heavyPlating", 1), MatUnifier.get(OrePrefixes.dustTiny, Materials.StainlessSteel, 2));
            GT_Values.RA.addImplosionRecipe(ItemList.Ingot_Heavy2.get(1), 8, GT_ModHandler.getModItem("GalacticraftMars", "item.null", 1, 3), MatUnifier.get(OrePrefixes.dustTiny, Materials.Tungsten, 2));
            GT_Values.RA.addImplosionRecipe(ItemList.Ingot_Heavy3.get(1), 8, GT_ModHandler.getModItem("GalacticraftMars", "item.itemBasicAsteroids", 1), MatUnifier.get(OrePrefixes.dustTiny, Materials.TungstenSteel, 2));

            GT_Values.RA.addCentrifugeRecipe(GT_ModHandler.getModItem("GalacticraftCore", "tile.moonBlock", 1, 5), null, null, Materials.Helium3.getGas(33), new ItemStack(Blocks.sand, 1), MatUnifier.get(OrePrefixes.dust, Materials.Aluminium, 1), MatUnifier.get(OrePrefixes.dust, Materials.Calcite, 1), MatUnifier.get(OrePrefixes.dust, Materials.Iron, 1), MatUnifier.get(OrePrefixes.dust, Materials.Magnesium, 1), MatUnifier.get(OrePrefixes.dust, Materials.Rutile, 1), new int[]{5000, 400, 400, 100, 100, 100}, 400, 8);
            GT_Values.RA.addPulveriserRecipe(GT_ModHandler.getModItem("GalacticraftCore", "tile.moonBlock", 1, 4), new ItemStack[]{GT_ModHandler.getModItem("GalacticraftCore", "tile.moonBlock", 1, 5)}, null, 400, 2);
            GT_Values.RA.addFluidExtractionRecipe(GT_ModHandler.getModItem("GalacticraftMars", "tile.mars", 1, 9), new ItemStack(Blocks.stone, 1), Materials.Iron.getMolten(50), 10000, 250, 16);
            GT_Values.RA.addPulveriserRecipe(GT_ModHandler.getModItem("GalacticraftMars", "tile.asteroidsBlock", 1, 1), new ItemStack[]{GT_ModHandler.getModItem("GalacticraftMars", "tile.asteroidsBlock", 1, 0)}, null, 400, 2);
            GT_Values.RA.addPulveriserRecipe(GT_ModHandler.getModItem("GalacticraftMars", "tile.asteroidsBlock", 1, 2), new ItemStack[]{GT_ModHandler.getModItem("GalacticraftMars", "tile.asteroidsBlock", 1, 0)}, null, 400, 2);
            GT_Values.RA.addCentrifugeRecipe(GT_ModHandler.getModItem("GalacticraftMars", "tile.asteroidsBlock", 1, 0), null, null, Materials.Nitrogen.getGas(33), new ItemStack(Blocks.sand, 1), MatUnifier.get(OrePrefixes.dust, Materials.Aluminium, 1), MatUnifier.get(OrePrefixes.dust, Materials.Nickel, 1), MatUnifier.get(OrePrefixes.dust, Materials.Iron, 1), MatUnifier.get(OrePrefixes.dust, Materials.Gallium, 1), MatUnifier.get(OrePrefixes.dust, Materials.Platinum, 1), new int[]{5000, 400, 400, 100, 100, 100}, 400, 8);
        }

        GT_Values.RA.addFluidExtractionRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Quartzite, 1), null, Materials.Glass.getMolten(72), 10000, 600, 28);//(MatUnifier.get(OrePrefixes.cell, Materials.SiliconDioxide,1), MatUnifier.get(OrePrefixes.dust,Materials.SiliconDioxide,2),MatUnifier.get(OrePrefixes.cell, Materials.Glass,1)/** GT_Utility.fillFluidContainer(Materials.Glass.getMolten(1000), ItemList.Cell_Empty.get(1, new Object[0]), true, true)**/, 600, 16);

        GT_Values.RA.addDistillationTowerRecipe(Materials.Creosote.getFluid(24), new FluidStack[]{Materials.Lubricant.getFluid(12)}, null, 16, 96);
        GT_Values.RA.addDistillationTowerRecipe(Materials.SeedOil.getFluid(32), new FluidStack[]{Materials.Lubricant.getFluid(12)}, null, 16, 96);
        GT_Values.RA.addDistillationTowerRecipe(Materials.FishOil.getFluid(24), new FluidStack[]{Materials.Lubricant.getFluid(12)}, null, 16, 96);
        GT_Values.RA.addDistillationTowerRecipe(Materials.Water.getFluid(576), new FluidStack[]{GT_ModHandler.getDistilledWater(520)}, null, 16, 120);

        GT_Values.RA.addFuel(new ItemStack(Items.golden_apple, 1, 1), new ItemStack(Items.apple, 1), 6400, 5);
        if (Loader.isModLoaded("Thaumcraft")) {
            GT_Values.RA.addFuel(GT_ModHandler.getModItem("Thaumcraft", "ItemShard", 1, 6), null, 720, 5);
        }
        if (Loader.isModLoaded("ForbiddenMagic")) {
            GT_Values.RA.addFuel(GT_ModHandler.getModItem("ForbiddenMagic", "GluttonyShard", 1), null, 720, 5);
            GT_Values.RA.addFuel(GT_ModHandler.getModItem("ForbiddenMagic", "FMResource", 1, 3), null, 720, 5);
            GT_Values.RA.addFuel(GT_ModHandler.getModItem("ForbiddenMagic", "NetherShard", 1), null, 720, 5);
            GT_Values.RA.addFuel(GT_ModHandler.getModItem("ForbiddenMagic", "NetherShard", 1, 1), null, 720, 5);
            GT_Values.RA.addFuel(GT_ModHandler.getModItem("ForbiddenMagic", "NetherShard", 1, 2), null, 720, 5);
            GT_Values.RA.addFuel(GT_ModHandler.getModItem("ForbiddenMagic", "NetherShard", 1, 3), null, 720, 5);
            GT_Values.RA.addFuel(GT_ModHandler.getModItem("ForbiddenMagic", "NetherShard", 1, 4), null, 720, 5);
            GT_Values.RA.addFuel(GT_ModHandler.getModItem("ForbiddenMagic", "NetherShard", 1, 5), null, 720, 5);
            GT_Values.RA.addFuel(GT_ModHandler.getModItem("ForbiddenMagic", "NetherShard", 1, 6), null, 720, 5);
        }
        if (Loader.isModLoaded("TaintedMagic")) {
            GT_Values.RA.addFuel(GT_ModHandler.getModItem("TaintedMagic", "WarpedShard", 1), null, 720, 5);
            GT_Values.RA.addFuel(GT_ModHandler.getModItem("TaintedMagic", "FluxShard", 1), null, 720, 5);
            GT_Values.RA.addFuel(GT_ModHandler.getModItem("TaintedMagic", "EldritchShard", 1), null, 720, 5);
        }
        if (Loader.isModLoaded("ThaumicTinkerer")) {
            GT_Values.RA.addFuel(GT_ModHandler.getModItem("ThaumicTinkerer", "kamiResource", 1, 6), null, 720, 5);
            GT_Values.RA.addFuel(GT_ModHandler.getModItem("ThaumicTinkerer", "kamiResource", 1, 7), null, 720, 5);
        }

        GT_Values.RA.addElectrolyzerRecipe(GT_Values.NI, ItemList.Cell_Empty.get(1), Materials.Water.getFluid(3000), Materials.Hydrogen.getGas(2000), MatUnifier.get(OrePrefixes.cell, Materials.Oxygen, 1), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 2000, 30);
        GT_Values.RA.addElectrolyzerRecipe(GT_Values.NI, ItemList.Cell_Empty.get(1), GT_ModHandler.getDistilledWater(3000), Materials.Hydrogen.getGas(2000), MatUnifier.get(OrePrefixes.cell, Materials.Oxygen, 1), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 2000, 30);
        GT_Values.RA.addElectrolyzerRecipe(GT_ModHandler.getIC2Item("electrolyzedWaterCell", 3), 0, MatUnifier.get(OrePrefixes.cell, Materials.Hydrogen, 2), MatUnifier.get(OrePrefixes.cell, Materials.Oxygen, 1), GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 30, 30);
        GT_Values.RA.addElectrolyzerRecipe(MatUnifier.get(OrePrefixes.cell, Materials.Water, 1), 0, GT_ModHandler.getIC2Item("electrolyzedWaterCell", 1), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, 490, 30);
        GT_Values.RA.addElectrolyzerRecipe(ItemList.Dye_Bonemeal.get(3), 0, MatUnifier.get(OrePrefixes.dust, Materials.Calcium, 1), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, 98, 26);
        GT_Values.RA.addElectrolyzerRecipe(new ItemStack(Blocks.sand, 8), 0, MatUnifier.get(OrePrefixes.dust, Materials.SiliconDioxide, 1), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, 500, 25);
        GT_Values.RA.addElectrolyzerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Tungstate, 7), GT_Values.NI, Materials.Hydrogen.getGas(7000), Materials.Oxygen.getGas(4000), MatUnifier.get(OrePrefixes.dust, Materials.Tungsten, 1), MatUnifier.get(OrePrefixes.dust, Materials.Lithium, 2), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{10000, 10000, 0, 0, 0, 0}, 120, 1920);
        GT_Values.RA.addElectrolyzerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Scheelite, 7), GT_Values.NI, Materials.Hydrogen.getGas(7000), Materials.Oxygen.getGas(4000), MatUnifier.get(OrePrefixes.dust, Materials.Tungsten, 1), MatUnifier.get(OrePrefixes.dust, Materials.Calcium, 2), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{10000, 10000, 0, 0, 0, 0}, 120, 1920);
        //GT_Values.RA.addElectrolyzerRecipe(MatUnifier.get(OrePrefixes.cell, Materials.CarbonDioxide, 4), GT_Values.NI, GT_Values.NF,GT_Values.NF,									   MatUnifier.get(OrePrefixes.dust, Materials.Carbon, 3),   MatUnifier.get(OrePrefixes.cell, Materials.Oxygen, 1), ItemList.Cell_Empty.get(3, new Object[0]), GT_Values.NI, GT_Values.NI, GT_Values.NI,new int[]{10000,10000,10000,0,0,0}, 180, 60);
        GT_Values.RA.addElectrolyzerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Graphite, 1), 0, MatUnifier.get(OrePrefixes.dust, Materials.Carbon, 4), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, 100, 64);

        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.NetherQuartz, 3), MatUnifier.get(OrePrefixes.dust, Materials.Sodium, 1), Materials.Water.getFluid(1000), GT_Values.NF, MatUnifier.get(OrePrefixes.gem, Materials.NetherQuartz, 3), 500);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.CertusQuartz, 3), MatUnifier.get(OrePrefixes.dust, Materials.Sodium, 1), Materials.Water.getFluid(1000), GT_Values.NF, MatUnifier.get(OrePrefixes.gem, Materials.CertusQuartz, 3), 500);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Quartzite, 3), MatUnifier.get(OrePrefixes.dust, Materials.Sodium, 1), Materials.Water.getFluid(1000), GT_Values.NF, MatUnifier.get(OrePrefixes.gem, Materials.Quartzite, 3), 500);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.NetherQuartz, 3), MatUnifier.get(OrePrefixes.dust, Materials.Sodium, 1), GT_ModHandler.getDistilledWater(1000), GT_Values.NF, MatUnifier.get(OrePrefixes.gem, Materials.NetherQuartz, 3), 500);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.CertusQuartz, 3), MatUnifier.get(OrePrefixes.dust, Materials.Sodium, 1), GT_ModHandler.getDistilledWater(1000), GT_Values.NF, MatUnifier.get(OrePrefixes.gem, Materials.CertusQuartz, 3), 500);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Quartzite, 3), MatUnifier.get(OrePrefixes.dust, Materials.Sodium, 1), GT_ModHandler.getDistilledWater(1000), GT_Values.NF, MatUnifier.get(OrePrefixes.gem, Materials.Quartzite, 3), 500);

        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Uraninite, 1), MatUnifier.get(OrePrefixes.dust, Materials.Aluminium, 1), GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.dust, Materials.Uranium, 1), 1000);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Uraninite, 1), MatUnifier.get(OrePrefixes.dust, Materials.Magnesium, 1), GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.dust, Materials.Uranium, 1), 1000);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Calcium, 1), MatUnifier.get(OrePrefixes.dust, Materials.Carbon, 1), Materials.Oxygen.getGas(3000), GT_Values.NF, MatUnifier.get(OrePrefixes.dust, Materials.Calcite, 5), 500);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Carbon, 1), GT_Values.NI, Materials.Hydrogen.getGas(4000), Materials.Methane.getGas(1000), GT_Values.NI, 200);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.Oxygen, 1), GT_Values.NI, Materials.Hydrogen.getGas(2000), GT_ModHandler.getDistilledWater(1000), ItemList.Cell_Empty.get(1), 10);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.Hydrogen, 1), GT_Values.NI, Materials.Oxygen.getGas(500), GT_ModHandler.getDistilledWater(500), ItemList.Cell_Empty.get(1), 5);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Tin, 1), MatUnifier.get(OrePrefixes.dust, Materials.Saltpeter, 1), Materials.Glass.getMolten(864), GT_Values.NF, GT_ModHandler.getModItem("Railcraft", "tile.railcraft.glass", 6), 50);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Rutile, 1), MatUnifier.get(OrePrefixes.cell, Materials.Carbon, 2), Materials.Chlorine.getGas(4000), Materials.Titaniumtetrachloride.getFluid(1000), MatUnifier.get(OrePrefixes.cell, Materials.CarbonMonoxide, 2), 500, 480);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Rutile, 1), MatUnifier.get(OrePrefixes.dust, Materials.Carbon, 2), Materials.Chlorine.getGas(4000), Materials.Titaniumtetrachloride.getFluid(1000), GT_Values.NI, 500, 480);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Sodium, 1), MatUnifier.get(OrePrefixes.dust, Materials.Magnesiumchloride, 2), GT_Values.NF, Materials.Chlorine.getGas(3000), MatUnifier.get(OrePrefixes.dustSmall, Materials.Magnesium, 6), 300, 240);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.RawRubber, 9), MatUnifier.get(OrePrefixes.dust, Materials.Sulfur, 1), GT_Values.NF, Materials.Rubber.getMolten(1296), GT_Values.NI, 600, 16);

        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.nugget, Materials.Gold, 8), new ItemStack(Items.melon, 1, 32767), new ItemStack(Items.speckled_melon, 1, 0), 50);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.nugget, Materials.Gold, 8), new ItemStack(Items.carrot, 1, 32767), new ItemStack(Items.golden_carrot, 1, 0), 50);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Gold, 8), new ItemStack(Items.apple, 1, 32767), new ItemStack(Items.golden_apple, 1, 0), 50);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.block, Materials.Gold, 8), new ItemStack(Items.apple, 1, 32767), new ItemStack(Items.golden_apple, 1, 1), 50);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Blaze, 1), MatUnifier.get(OrePrefixes.gem, Materials.EnderPearl, 1), MatUnifier.get(OrePrefixes.gem, Materials.EnderEye, 1), 50);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Blaze, 1), new ItemStack(Items.slime_ball, 1, 32767), new ItemStack(Items.magma_cream, 1, 0), 50);

        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Plutonium, 6), null, null, Materials.Radon.getGas(100), MatUnifier.get(OrePrefixes.dust, Materials.Plutonium, 6), 12000, 8);
        GT_Values.RA.addChemicalBathRecipe(MatUnifier.get(OrePrefixes.gem, Materials.EnderEye, 1), Materials.Radon.getGas(250), ItemList.QuantumEye.get(1), null, null, null, 480, 384);
        GT_Values.RA.addChemicalBathRecipe(MatUnifier.get(OrePrefixes.gem, Materials.NetherStar, 1), Materials.Radon.getGas(1250), ItemList.QuantumStar.get(1), null, null, null, 1920, 384);
        GT_Values.RA.addAutoclaveRecipe(MatUnifier.get(OrePrefixes.gem, Materials.NetherStar, 1), Materials.Neutronium.getMolten(288), ItemList.Gravistar.get(1), 10000, 480, 7680);

        //TODO FIX GT_Values.RA.addBenderRecipe(ItemList.IC2_Mixed_Metal_Ingot.get(1), MatUnifier.get(OrePrefixes.plateAlloy, Materials.Advanced, 1), 100, 8);
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.stick, Materials.Aluminium, 6), ItemList.RC_Rail_Standard.get(2), 200, 15);
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.stick, Materials.Iron, 6), ItemList.RC_Rail_Standard.get(4), 400, 15);
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.stick, Materials.WroughtIron, 6), ItemList.RC_Rail_Standard.get(5), 400, 15);
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.stick, Materials.Bronze, 6), ItemList.RC_Rail_Standard.get(3), 300, 15);
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.stick, Materials.Steel, 6), ItemList.RC_Rail_Standard.get(8), 800, 15);
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.stick, Materials.StainlessSteel, 6), ItemList.RC_Rail_Standard.get(12), 1200, 15);
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.stick, Materials.Titanium, 6), ItemList.RC_Rail_Standard.get(16), 1600, 15);
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.stick, Materials.TungstenSteel, 6), ItemList.RC_Rail_Reinforced.get(24), 2400, 30);
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.stick, Materials.Aluminium, 12), ItemList.RC_Rebar.get(4), 200, 15);
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.stick, Materials.Iron, 12), ItemList.RC_Rebar.get(8), 400, 15);
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.stick, Materials.WroughtIron, 12), ItemList.RC_Rebar.get(10), 400, 15);
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.stick, Materials.Bronze, 12), ItemList.RC_Rebar.get(8), 400, 15);
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.stick, Materials.Steel, 12), ItemList.RC_Rebar.get(16), 800, 15);
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.stick, Materials.StainlessSteel, 12), ItemList.RC_Rebar.get(24), 1200, 15);
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.stick, Materials.Titanium, 12), ItemList.RC_Rebar.get(32), 1600, 15);
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.stick, Materials.TungstenSteel, 12), ItemList.RC_Rebar.get(48), 2400, 15);
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Tin, 12), ItemList.Cell_Empty.get(6), 1200, 8);
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Steel, 12), ItemList.Cell_Empty.get(6), 1200, 8);
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.plate, Materials.SteelMagnetic, 12), ItemList.Cell_Empty.get(6), 1200, 8);
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Polytetrafluoroethylene, 12), ItemList.Cell_Empty.get(6), 1200, 8);
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Iron, 12), new ItemStack(Items.bucket, 4, 0), 800, 4);
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.plate, Materials.WroughtIron, 12), new ItemStack(Items.bucket, 4, 0), 800, 4);
        GT_Values.RA.addBenderRecipe(ItemList.IC2_Item_Casing_Iron.get(2), GT_ModHandler.getIC2Item("fuelRod", 1), 100, 8);
        GT_Values.RA.addBenderRecipe(ItemList.IC2_Item_Casing_Tin.get(1), ItemList.IC2_Food_Can_Empty.get(1), 100, 8);
        GT_Values.RA.addPulveriserRecipe(MatUnifier.get(OrePrefixes.block, Materials.Marble, 1), new ItemStack[]{MatUnifier.get(OrePrefixes.dust, Materials.Marble, 1)}, null, 160, 4);

        GT_Values.RA.addVacuumFreezerRecipe(GT_ModHandler.getIC2Item("reactorCoolantSimple", 1, 32767), GT_ModHandler.getIC2Item("reactorCoolantSimple", 1, 1), 100);
        GT_Values.RA.addVacuumFreezerRecipe(GT_ModHandler.getIC2Item("reactorCoolantTriple", 1, 32767), GT_ModHandler.getIC2Item("reactorCoolantTriple", 1, 1), 300);
        GT_Values.RA.addVacuumFreezerRecipe(GT_ModHandler.getIC2Item("reactorCoolantSix", 1, 32767), GT_ModHandler.getIC2Item("reactorCoolantSix", 1, 1), 600);
        GT_Values.RA.addVacuumFreezerRecipe(ItemList.Reactor_Coolant_He_1.getWildcard(1), ItemList.Reactor_Coolant_He_1.get(1), 600);
        GT_Values.RA.addVacuumFreezerRecipe(ItemList.Reactor_Coolant_He_3.getWildcard(1), ItemList.Reactor_Coolant_He_3.get(1), 1800);
        GT_Values.RA.addVacuumFreezerRecipe(ItemList.Reactor_Coolant_He_6.getWildcard(1), ItemList.Reactor_Coolant_He_6.get(1), 3600);
        GT_Values.RA.addVacuumFreezerRecipe(ItemList.Reactor_Coolant_NaK_1.getWildcard(1), ItemList.Reactor_Coolant_NaK_1.get(1), 600);
        GT_Values.RA.addVacuumFreezerRecipe(ItemList.Reactor_Coolant_NaK_3.getWildcard(1), ItemList.Reactor_Coolant_NaK_3.get(1), 1800);
        GT_Values.RA.addVacuumFreezerRecipe(ItemList.Reactor_Coolant_NaK_6.getWildcard(1), ItemList.Reactor_Coolant_NaK_6.get(1), 3600);
        GT_Values.RA.addVacuumFreezerRecipe(MatUnifier.get(OrePrefixes.cell, Materials.Water, 1), MatUnifier.get(OrePrefixes.cell, Materials.Ice, 1), 50);
        GT_Values.RA.addVacuumFreezerRecipe(GT_ModHandler.getIC2Item("airCell", 1), MatUnifier.get(OrePrefixes.cell, Materials.LiquidAir, 1), 25);

        GT_Values.RA.addAlloySmelterRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Lead, 1), MatUnifier.get(OrePrefixes.dust, Materials.Obsidian, 2), ItemList.TE_Hardened_Glass.get(2), 200, 16);
        GT_Values.RA.addAlloySmelterRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Lead, 1), MatUnifier.get(OrePrefixes.dust, Materials.Obsidian, 2), ItemList.TE_Hardened_Glass.get(2), 200, 16);
//        GT_Values.RA.addAlloySmelterRecipe(MatUnifier.get(OrePrefixes.dust, Materials.RawRubber, 3L), MatUnifier.get(OrePrefixes.dust, Materials.Sulfur, 1L), MatUnifier.get(OrePrefixes.ingot, Materials.Rubber, 1L), 200, 8);

        GT_Values.RA.addCutterRecipe(GT_ModHandler.getModItem("BuildCraft|Transport", "item.buildcraftPipe.pipestructurecobblestone", 1, 0), GT_ModHandler.getModItem("BuildCraft|Transport", "pipePlug", 8, 0), GT_Values.NI, 32, 16);
        for (int i = 0; i < 16; i++) {
            GT_Values.RA.addCutterRecipe(new ItemStack(Blocks.stained_glass, 3, i), new ItemStack(Blocks.stained_glass_pane, 8, i), GT_Values.NI, 50, 8);
        }
        GT_Values.RA.addCutterRecipe(new ItemStack(Blocks.glass, 3, 0), new ItemStack(Blocks.glass_pane, 8, 0), GT_Values.NI, 50, 8);
        GT_Values.RA.addCutterRecipe(new ItemStack(Blocks.stone, 1, 0), new ItemStack(Blocks.stone_slab, 2, 0), GT_Values.NI, 25, 8);
        GT_Values.RA.addCutterRecipe(new ItemStack(Blocks.sandstone, 1, 0), new ItemStack(Blocks.stone_slab, 2, 1), GT_Values.NI, 25, 8);
        GT_Values.RA.addCutterRecipe(new ItemStack(Blocks.cobblestone, 1, 0), new ItemStack(Blocks.stone_slab, 2, 3), GT_Values.NI, 25, 8);
        GT_Values.RA.addCutterRecipe(new ItemStack(Blocks.brick_block, 1, 0), new ItemStack(Blocks.stone_slab, 2, 4), GT_Values.NI, 25, 8);
        GT_Values.RA.addCutterRecipe(new ItemStack(Blocks.stonebrick, 1, 0), new ItemStack(Blocks.stone_slab, 2, 5), GT_Values.NI, 25, 8);
        GT_Values.RA.addCutterRecipe(new ItemStack(Blocks.nether_brick, 1, 0), new ItemStack(Blocks.stone_slab, 2, 6), GT_Values.NI, 25, 8);
        GT_Values.RA.addCutterRecipe(new ItemStack(Blocks.quartz_block, 1, 32767), new ItemStack(Blocks.stone_slab, 2, 7), GT_Values.NI, 25, 8);
        GT_Values.RA.addCutterRecipe(new ItemStack(Blocks.glowstone, 1, 0), MatUnifier.get(OrePrefixes.plate, Materials.Glowstone, 4), GT_Values.NI, 100, 16);

        GT_Values.RA.addCutterRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Iron, 1), ItemList.IC2_Item_Casing_Iron.get(2), GT_Values.NI, 50, 16);
        GT_Values.RA.addCutterRecipe(MatUnifier.get(OrePrefixes.plate, Materials.WroughtIron, 1), ItemList.IC2_Item_Casing_Iron.get(2), GT_Values.NI, 50, 16);
        GT_Values.RA.addCutterRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Gold, 1), ItemList.IC2_Item_Casing_Gold.get(2), GT_Values.NI, 50, 16);
        GT_Values.RA.addCutterRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Bronze, 1), ItemList.IC2_Item_Casing_Bronze.get(2), GT_Values.NI, 50, 16);
        GT_Values.RA.addCutterRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Copper, 1), ItemList.IC2_Item_Casing_Copper.get(2), GT_Values.NI, 50, 16);
        GT_Values.RA.addCutterRecipe(MatUnifier.get(OrePrefixes.plate, Materials.AnnealedCopper, 1), ItemList.IC2_Item_Casing_Copper.get(2), GT_Values.NI, 50, 16);
        GT_Values.RA.addCutterRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Tin, 1), ItemList.IC2_Item_Casing_Tin.get(2), GT_Values.NI, 50, 16);
        GT_Values.RA.addCutterRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Lead, 1), ItemList.IC2_Item_Casing_Lead.get(2), GT_Values.NI, 50, 16);
        GT_Values.RA.addCutterRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Steel, 1), ItemList.IC2_Item_Casing_Steel.get(2), GT_Values.NI, 50, 16);
        for (byte i = 0; i < 16; i = (byte) (i + 1)) {
            GT_Values.RA.addCutterRecipe(new ItemStack(Blocks.wool, 1, i), new ItemStack(Blocks.carpet, 2, i), GT_Values.NI, 50, 8);
        }
        for (int g = 0; g < 16; g++) {
            if (!isNEILoaded) {
                break;
            }
            API.hideItem(new ItemStack(GT_MetaGenerated_Item_03.INSTANCE, 1, g));
        }

        GT_Values.RA.addLatheRecipe(new ItemStack(Blocks.wooden_slab, 1, GT_Values.W), new ItemStack(Items.bowl, 1), MatUnifier.get(OrePrefixes.dustSmall, Materials.Wood, 1), 50, 8);
        GT_Values.RA.addLatheRecipe(GT_ModHandler.getModItem(aTextForestry, "slabs", 1, GT_Values.W), new ItemStack(Items.bowl, 1), MatUnifier.get(OrePrefixes.dustSmall, Materials.Wood, 1), 50, 8);
        GT_Values.RA.addLatheRecipe(GT_ModHandler.getModItem(aTextEBXL, "woodslab", 1, GT_Values.W), new ItemStack(Items.bowl, 1), MatUnifier.get(OrePrefixes.dustSmall, Materials.Wood, 1), 50, 8);

        GT_Values.RA.addFormingPressRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Brass, 1), ItemList.Shape_Mold_Credit.get(0), ItemList.Coin_Doge.get(4), 100, 16);

        if (!GT_Mod.gregtechproxy.mDisableIC2Cables) {
            GT_Values.RA.addWiremillRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Copper, 1), GT_ModHandler.getIC2Item("copperCableItem", 3), 100, 2);
            GT_Values.RA.addWiremillRecipe(MatUnifier.get(OrePrefixes.plate, Materials.AnnealedCopper, 1), GT_ModHandler.getIC2Item("copperCableItem", 3), 100, 2);
            GT_Values.RA.addWiremillRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Tin, 1), GT_ModHandler.getIC2Item("tinCableItem", 4), 150, 1);
            GT_Values.RA.addWiremillRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Iron, 1), GT_ModHandler.getIC2Item("ironCableItem", 6), 200, 2);
            GT_Values.RA.addWiremillRecipe(MatUnifier.get(OrePrefixes.plate, Materials.WroughtIron, 1), GT_ModHandler.getIC2Item("ironCableItem", 6), 200, 2);
            GT_Values.RA.addWiremillRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Gold, 1), GT_ModHandler.getIC2Item("goldCableItem", 6), 200, 1);
        }
        GT_Values.RA.addWiremillRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Graphene, 1), MatUnifier.get(OrePrefixes.wireGt01, Materials.Graphene, 1), 400, 2);
        if (!GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.disabledrecipes, "torchesFromCoal", false)) {
            GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.stick, Materials.Wood, 1), new ItemStack(Items.coal, 1, 32767), new ItemStack(Blocks.torch, 4), 400, 1);
        }
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Gold, 2), ItemList.Circuit_Integrated.getWithDamage(0, 2), new ItemStack(Blocks.light_weighted_pressure_plate, 1), 200, 4);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Iron, 2), ItemList.Circuit_Integrated.getWithDamage(0, 2), new ItemStack(Blocks.heavy_weighted_pressure_plate, 1), 200, 4);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Iron, 6), ItemList.Circuit_Integrated.getWithDamage(0, 6), new ItemStack(Items.iron_door, 1), 600, 4);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Iron, 7), ItemList.Circuit_Integrated.getWithDamage(0, 7), new ItemStack(Items.cauldron, 1), 700, 4);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.stick, Materials.Iron, 1), ItemList.Circuit_Integrated.getWithDamage(0, 1), GT_ModHandler.getIC2Item("ironFence", 1), 100, 4);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.stick, Materials.Iron, 3), ItemList.Circuit_Integrated.getWithDamage(0, 3), new ItemStack(Blocks.iron_bars, 4), 300, 4);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.WroughtIron, 2), ItemList.Circuit_Integrated.getWithDamage(0, 2), new ItemStack(Blocks.heavy_weighted_pressure_plate, 1), 200, 4);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.WroughtIron, 6), ItemList.Circuit_Integrated.getWithDamage(0, 6), new ItemStack(Items.iron_door, 1), 600, 4);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.WroughtIron, 7), ItemList.Circuit_Integrated.getWithDamage(0, 7), new ItemStack(Items.cauldron, 1), 700, 4);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.stick, Materials.WroughtIron, 1), ItemList.Circuit_Integrated.getWithDamage(0, 1), GT_ModHandler.getIC2Item("ironFence", 1), 100, 4);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.stick, Materials.WroughtIron, 3), ItemList.Circuit_Integrated.getWithDamage(0, 3), new ItemStack(Blocks.iron_bars, 4), 300, 4);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.stick, Materials.Wood, 3), ItemList.Circuit_Integrated.getWithDamage(0, 3), new ItemStack(Blocks.fence, 1), 300, 4);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), MatUnifier.get(OrePrefixes.ring, Materials.Iron, 2), new ItemStack(Blocks.tripwire_hook, 1), 400, 4);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), MatUnifier.get(OrePrefixes.ring, Materials.WroughtIron, 2), new ItemStack(Blocks.tripwire_hook, 1), 400, 4);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.stick, Materials.Wood, 3), new ItemStack(Items.string, 3, 32767), new ItemStack(Items.bow, 1), 400, 4);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Iron, 3), ItemList.Component_Minecart_Wheels_Iron.get(2), new ItemStack(Items.minecart, 1), 500, 2);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.WroughtIron, 3), ItemList.Component_Minecart_Wheels_Iron.get(2), new ItemStack(Items.minecart, 1), 400, 2);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Steel, 3), ItemList.Component_Minecart_Wheels_Steel.get(2), new ItemStack(Items.minecart, 1), 300, 2);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.stick, Materials.Iron, 1), MatUnifier.get(OrePrefixes.ring, Materials.Iron, 2), ItemList.Component_Minecart_Wheels_Iron.get(1), 500, 2);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.stick, Materials.WroughtIron, 1), MatUnifier.get(OrePrefixes.ring, Materials.WroughtIron, 2), ItemList.Component_Minecart_Wheels_Iron.get(1), 400, 2);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.stick, Materials.Steel, 1), MatUnifier.get(OrePrefixes.ring, Materials.Steel, 2), ItemList.Component_Minecart_Wheels_Steel.get(1), 300, 2);
        GT_Values.RA.addAssemblerRecipe(new ItemStack(Items.minecart, 1), new ItemStack(Blocks.hopper, 1, 32767), new ItemStack(Items.hopper_minecart, 1), 400, 4);
        GT_Values.RA.addAssemblerRecipe(new ItemStack(Items.minecart, 1), new ItemStack(Blocks.tnt, 1, 32767), new ItemStack(Items.tnt_minecart, 1), 400, 4);
        GT_Values.RA.addAssemblerRecipe(new ItemStack(Items.minecart, 1), new ItemStack(Blocks.chest, 1, 32767), new ItemStack(Items.chest_minecart, 1), 400, 4);
        GT_Values.RA.addAssemblerRecipe(new ItemStack(Items.minecart, 1), new ItemStack(Blocks.trapped_chest, 1, 32767), new ItemStack(Items.chest_minecart, 1), 400, 4);
        GT_Values.RA.addAssemblerRecipe(new ItemStack(Items.minecart, 1), new ItemStack(Blocks.furnace, 1, 32767), new ItemStack(Items.furnace_minecart, 1), 400, 4);
        GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.tripwire_hook, 1), new ItemStack(Blocks.chest, 1, 32767), new ItemStack(Blocks.trapped_chest, 1), 200, 4);
        GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.stone, 1, 0), ItemList.Circuit_Integrated.getWithDamage(0, 4), new ItemStack(Blocks.stonebrick, 1, 0), 50, 4);
        GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.sandstone, 1, 0), ItemList.Circuit_Integrated.getWithDamage(0, 1), new ItemStack(Blocks.sandstone, 1, 2), 50, 4);
        GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.sandstone, 1, 1), ItemList.Circuit_Integrated.getWithDamage(0, 1), new ItemStack(Blocks.sandstone, 1, 0), 50, 4);
        GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.sandstone, 1, 2), ItemList.Circuit_Integrated.getWithDamage(0, 1), new ItemStack(Blocks.sandstone, 1, 0), 50, 4);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Iron, 8), ItemList.Circuit_Integrated.getWithDamage(0, 8), GT_ModHandler.getIC2Item("machine", 1), 25, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.WroughtIron, 8), ItemList.Circuit_Integrated.getWithDamage(0, 8), ItemList.Casing_ULV.get(1), 25, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Steel, 8), ItemList.Circuit_Integrated.getWithDamage(0, 8), ItemList.Casing_LV.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Aluminium, 8), ItemList.Circuit_Integrated.getWithDamage(0, 8), ItemList.Casing_MV.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.StainlessSteel, 8), ItemList.Circuit_Integrated.getWithDamage(0, 8), ItemList.Casing_HV.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Titanium, 8), ItemList.Circuit_Integrated.getWithDamage(0, 8), ItemList.Casing_EV.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.TungstenSteel, 8), ItemList.Circuit_Integrated.getWithDamage(0, 8), ItemList.Casing_IV.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Chrome, 8), ItemList.Circuit_Integrated.getWithDamage(0, 8), ItemList.Casing_LuV.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Iridium, 8), ItemList.Circuit_Integrated.getWithDamage(0, 8), ItemList.Casing_ZPM.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Osmium, 8), ItemList.Circuit_Integrated.getWithDamage(0, 8), ItemList.Casing_UV.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Neutronium, 8), ItemList.Circuit_Integrated.getWithDamage(0, 8), ItemList.Casing_MAX.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Invar, 6), MatUnifier.get(OrePrefixes.frameGt, Materials.Invar, 1), ItemList.Casing_HeatProof.get(2), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.wireGt02, Materials.Cupronickel, 8), ItemList.Circuit_Integrated.getWithDamage(0, 8), ItemList.Casing_Coil_Cupronickel.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.wireGt02, Materials.Kanthal, 8), ItemList.Circuit_Integrated.getWithDamage(0, 8), ItemList.Casing_Coil_Kanthal.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.wireGt02, Materials.Nichrome, 8), ItemList.Circuit_Integrated.getWithDamage(0, 8), ItemList.Casing_Coil_Nichrome.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.wireGt02, Materials.TungstenSteel, 8), ItemList.Circuit_Integrated.getWithDamage(0, 8), ItemList.Casing_Coil_TungstenSteel.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.wireGt02, Materials.HSSG, 8), ItemList.Circuit_Integrated.getWithDamage(0, 8), ItemList.Casing_Coil_HSSG.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.wireGt02, Materials.Naquadah, 8), ItemList.Circuit_Integrated.getWithDamage(0, 8), ItemList.Casing_Coil_Naquadah.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.wireGt02, Materials.NaquadahAlloy, 8), ItemList.Circuit_Integrated.getWithDamage(0, 8), ItemList.Casing_Coil_NaquadahAlloy.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.wireGt02, Materials.Superconductor, 8), ItemList.Circuit_Integrated.getWithDamage(0, 8), ItemList.Casing_Coil_Superconductor.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Steel, 6), MatUnifier.get(OrePrefixes.frameGt, Materials.Steel, 1), ItemList.Casing_SolidSteel.get(2), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Aluminium, 6), MatUnifier.get(OrePrefixes.frameGt, Materials.Aluminium, 1), ItemList.Casing_FrostProof.get(2), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.TungstenSteel, 6), MatUnifier.get(OrePrefixes.frameGt, Materials.TungstenSteel, 1), ItemList.Casing_RobustTungstenSteel.get(2), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.StainlessSteel, 6), MatUnifier.get(OrePrefixes.frameGt, Materials.StainlessSteel, 1), ItemList.Casing_CleanStainlessSteel.get(2), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Titanium, 6), MatUnifier.get(OrePrefixes.frameGt, Materials.Titanium, 1), ItemList.Casing_StableTitanium.get(2), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Osmiridium, 6), MatUnifier.get(OrePrefixes.frameGt, Materials.Osmiridium, 1), ItemList.Casing_MiningOsmiridium.get(2), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.TungstenSteel, 6), ItemList.Casing_LuV.get(1), ItemList.Casing_Fusion.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Magnalium, 6), MatUnifier.get(OrePrefixes.frameGt, Materials.BlueSteel, 1), ItemList.Casing_Turbine.get(2), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.StainlessSteel, 6), ItemList.Casing_Turbine.get(1), ItemList.Casing_Turbine1.get(2), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Titanium, 6), ItemList.Casing_Turbine.get(1), ItemList.Casing_Turbine2.get(2), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.TungstenSteel, 6), ItemList.Casing_Turbine.get(1), ItemList.Casing_Turbine3.get(2), 50, 16);
        GT_Values.RA.addAssemblerRecipe(ItemList.Casing_SolidSteel.get(1), GT_Utility.getIntegratedCircuit(6), Materials.Polytetrafluoroethylene.getMolten(216), ItemList.Casing_Chemically_Inert.get(1), 50, 16);

        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.cableGt01, Materials.Lead, 2), ItemList.Casing_ULV.get(1), Materials.Plastic.getMolten(288), ItemList.Hull_ULV.get(1), 25, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.cableGt01, Materials.Tin, 2), ItemList.Casing_LV.get(1), Materials.Plastic.getMolten(288), ItemList.Hull_LV.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.cableGt01, Materials.Copper, 2), ItemList.Casing_MV.get(1), Materials.Plastic.getMolten(288), ItemList.Hull_MV.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.cableGt01, Materials.AnnealedCopper, 2), ItemList.Casing_MV.get(1), Materials.Plastic.getMolten(288), ItemList.Hull_MV.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.cableGt01, Materials.Gold, 2), ItemList.Casing_HV.get(1), Materials.Plastic.getMolten(288), ItemList.Hull_HV.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.cableGt01, Materials.Aluminium, 2), ItemList.Casing_EV.get(1), Materials.Plastic.getMolten(288), ItemList.Hull_EV.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.cableGt01, Materials.Tungsten, 2), ItemList.Casing_IV.get(1), Materials.Plastic.getMolten(288), ItemList.Hull_IV.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.cableGt01, Materials.VanadiumGallium, 2), ItemList.Casing_LuV.get(1), Materials.Plastic.getMolten(288), ItemList.Hull_LuV.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.cableGt01, Materials.Naquadah, 2), ItemList.Casing_ZPM.get(1), Materials.Polytetrafluoroethylene.getMolten(288), ItemList.Hull_ZPM.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.wireGt04, Materials.NaquadahAlloy, 2), ItemList.Casing_UV.get(1), Materials.Polytetrafluoroethylene.getMolten(288), ItemList.Hull_UV.get(1), 50, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.wireGt01, Materials.Superconductor, 2), ItemList.Casing_MAX.get(1), Materials.Polytetrafluoroethylene.getMolten(288), ItemList.Hull_MAX.get(1), 50, 16);

        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.cableGt01, Materials.Tin, 1), MatUnifier.get(OrePrefixes.plate, Materials.BatteryAlloy, 1), Materials.Plastic.getMolten(144), ItemList.Battery_Hull_LV.get(1), 800, 1);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.cableGt01, Materials.Copper, 2), MatUnifier.get(OrePrefixes.plate, Materials.BatteryAlloy, 3), Materials.Plastic.getMolten(432), ItemList.Battery_Hull_MV.get(1), 1600, 2);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.cableGt01, Materials.AnnealedCopper, 2), MatUnifier.get(OrePrefixes.plate, Materials.BatteryAlloy, 3), Materials.Plastic.getMolten(432), ItemList.Battery_Hull_MV.get(1), 1600, 2);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.cableGt01, Materials.Gold, 4), MatUnifier.get(OrePrefixes.plate, Materials.BatteryAlloy, 9), Materials.Plastic.getMolten(1296), ItemList.Battery_Hull_HV.get(1), 3200, 4);

        GT_Values.RA.addAssemblerRecipe(new ItemStack(Items.string, 4, 32767), new ItemStack(Items.slime_ball, 1, 32767), new ItemStack(Items.lead, 2), 200, 2);
        GT_Values.RA.addAssemblerRecipe(ItemList.IC2_Compressed_Coal_Ball.get(8), new ItemStack(Blocks.brick_block, 1), ItemList.IC2_Compressed_Coal_Chunk.get(1), 400, 4);

        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getIC2Item("waterMill", 2), ItemList.Circuit_Integrated.getWithDamage(0, 2), GT_ModHandler.getIC2Item("generator", 1), 6400, 8);
        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getIC2Item("batPack", 1, 32767), ItemList.Circuit_Integrated.getWithDamage(0, 1), ItemList.IC2_ReBattery.get(6), 800, 4);
        GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.stone_slab, 3, 0), ItemList.RC_Rebar.get(1), ItemList.RC_Tie_Stone.get(1), 128, 8);
        GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.stone_slab, 3, 7), ItemList.RC_Rebar.get(1), ItemList.RC_Tie_Stone.get(1), 128, 8);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.wireGt01, Materials.Copper, 9), MatUnifier.get(OrePrefixes.plate, Materials.Lead, 2), GT_Values.NF, ItemList.RC_ShuntingWire.get(4), 1600, 4);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.wireGt01, Materials.AnnealedCopper, 9), MatUnifier.get(OrePrefixes.plate, Materials.Lead, 2), GT_Values.NF, ItemList.RC_ShuntingWire.get(4), 1600, 4);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Steel, 3), MatUnifier.get(OrePrefixes.plate, Materials.Gold, 3), Materials.Blaze.getMolten(432), ItemList.RC_Rail_HS.get(8), 400, 4);
        GT_Values.RA.addAssemblerRecipe(ItemList.RC_Rail_Standard.get(3), MatUnifier.get(OrePrefixes.plate, Materials.Gold, 3), Materials.Redstone.getMolten(432), ItemList.RC_Rail_Adv.get(8), 400, 4);
        GT_Values.RA.addAssemblerRecipe(ItemList.RC_Rail_Standard.get(1), MatUnifier.get(OrePrefixes.wireGt01, Materials.Copper, 1), ItemList.RC_Rail_Electric.get(1), 50, 4);
        GT_Values.RA.addAssemblerRecipe(ItemList.RC_Rail_Standard.get(1), MatUnifier.get(OrePrefixes.wireGt01, Materials.AnnealedCopper, 1), ItemList.RC_Rail_Electric.get(1), 50, 4);
        GT_Values.RA.addAssemblerRecipe(ItemList.RC_Tie_Wood.get(6), MatUnifier.get(OrePrefixes.plate, Materials.Iron, 1), ItemList.RC_Rail_Wooden.get(6), 400, 4);
        GT_Values.RA.addAssemblerRecipe(ItemList.RC_Tie_Wood.get(6), MatUnifier.get(OrePrefixes.plate, Materials.WroughtIron, 1), ItemList.RC_Rail_Wooden.get(6), 400, 4);
        GT_Values.RA.addAssemblerRecipe(ItemList.RC_Tie_Wood.get(4), ItemList.Circuit_Integrated.getWithDamage(0, 4), ItemList.RC_Bed_Wood.get(1), 200, 4);
        GT_Values.RA.addAssemblerRecipe(ItemList.RC_Tie_Stone.get(4), ItemList.Circuit_Integrated.getWithDamage(0, 4), ItemList.RC_Bed_Stone.get(1), 200, 4);
        for (ItemStack tRail : new ItemStack[]{ItemList.RC_Rail_Standard.get(6), ItemList.RC_Rail_Adv.get(6), ItemList.RC_Rail_Reinforced.get(6), ItemList.RC_Rail_Electric.get(6), ItemList.RC_Rail_HS.get(6), ItemList.RC_Rail_Wooden.get(6)}) {
            for (ItemStack tBed : new ItemStack[]{ItemList.RC_Bed_Wood.get(1), ItemList.RC_Bed_Stone.get(1)}) {
                GT_Values.RA.addAssemblerRecipe(tBed, tRail, GT_ModHandler.getRecipeOutput(tRail, GT_Values.NI, tRail, tRail, tBed, tRail, tRail, GT_Values.NI, tRail), 400, 4);
                GT_Values.RA.addAssemblerRecipe(tBed, tRail, Materials.Redstone.getMolten(144L), GT_ModHandler.getRecipeOutput(tRail, GT_Values.NI, tRail, tRail, tBed, tRail, tRail, MatUnifier.get(OrePrefixes.dust, Materials.Redstone), tRail), 400, 4);
                GT_Values.RA.addAssemblerRecipe(tBed, tRail, Materials.Redstone.getMolten(288L), GT_ModHandler.getRecipeOutput(tRail, MatUnifier.get(OrePrefixes.dust, Materials.Redstone), tRail, tRail, tBed, tRail, tRail, MatUnifier.get(OrePrefixes.dust, Materials.Redstone), tRail), 400, 4);
            }
        }
        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getIC2Item("carbonFiber", 2), ItemList.Circuit_Integrated.getWithDamage(0, 2), GT_ModHandler.getIC2Item("carbonMesh", 1), 800, 2);

        GT_Values.RA.addAssemblerRecipe(ItemList.NC_SensorCard.getWildcard(1), ItemList.Circuit_Integrated.getWithDamage(0, 1), ItemList.Circuit_Basic.get(3), 1600, 2);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Aluminium, 4), GT_ModHandler.getIC2Item("generator", 1), GT_ModHandler.getIC2Item("waterMill", 2), 6400, 8);

        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Iron, 5), new ItemStack(Blocks.chest, 1, 32767), new ItemStack(Blocks.hopper), 800, 2);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Iron, 5), new ItemStack(Blocks.trapped_chest, 1, 32767), new ItemStack(Blocks.hopper), 800, 2);

        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.WroughtIron, 5), new ItemStack(Blocks.chest, 1, 32767), new ItemStack(Blocks.hopper), 800, 2);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.WroughtIron, 5), new ItemStack(Blocks.trapped_chest, 1, 32767), new ItemStack(Blocks.hopper), 800, 2);

        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Magnalium, 2), GT_ModHandler.getIC2Item("generator", 1), GT_ModHandler.getIC2Item("windMill", 1), 6400, 8);

        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.gem, Materials.EnderPearl, 1), new ItemStack(Items.blaze_powder, 1, 0), new ItemStack(Items.ender_eye, 1, 0), 400, 2);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.gem, Materials.EnderPearl, 6), new ItemStack(Items.blaze_rod, 1, 0), new ItemStack(Items.ender_eye, 6, 0), 2500, 2);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.gear, Materials.CobaltBrass, 1), MatUnifier.get(OrePrefixes.dust, Materials.Diamond, 1), ItemList.Component_Sawblade_Diamond.get(1), 1600, 2);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Redstone, 4), MatUnifier.get(OrePrefixes.dust, Materials.Glowstone, 4), new ItemStack(Blocks.redstone_lamp, 1), 400, 1);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Redstone, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 1), new ItemStack(Blocks.redstone_torch, 1), 400, 1);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Redstone, 1), MatUnifier.get(OrePrefixes.plate, Materials.Iron, 4), new ItemStack(Items.compass, 1), 400, 4);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Redstone, 1), MatUnifier.get(OrePrefixes.plate, Materials.WroughtIron, 4), new ItemStack(Items.compass, 1), 400, 4);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Redstone, 1), MatUnifier.get(OrePrefixes.plate, Materials.Gold, 4), new ItemStack(Items.clock, 1), 400, 4);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.stick, Materials.Wood, 1), MatUnifier.get(OrePrefixes.dust, Materials.Sulfur, 1), new ItemStack(Blocks.torch, 2), 400, 1);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.stick, Materials.Wood, 1), MatUnifier.get(OrePrefixes.dust, Materials.Phosphorus, 1), new ItemStack(Blocks.torch, 6), 400, 1);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.stick, Materials.Wood, 1), ItemList.IC2_Resin.get(1), new ItemStack(Blocks.torch, 6), 400, 1);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Coal, 8), new ItemStack(Items.flint, 1), ItemList.IC2_Compressed_Coal_Ball.get(1), 400, 4);
        if (!GT_Mod.gregtechproxy.mDisableIC2Cables) {
            GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getIC2Item("tinCableItem", 1), MatUnifier.get(OrePrefixes.ingot, Materials.Rubber, 1), GT_ModHandler.getIC2Item("insulatedTinCableItem", 1), 100, 2);
            GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getIC2Item("copperCableItem", 1), MatUnifier.get(OrePrefixes.ingot, Materials.Rubber, 1), GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1), 100, 2);
            GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getIC2Item("goldCableItem", 1), MatUnifier.get(OrePrefixes.ingot, Materials.Rubber, 2), GT_ModHandler.getIC2Item("insulatedGoldCableItem", 1), 200, 2);
            GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getIC2Item("ironCableItem", 1), MatUnifier.get(OrePrefixes.ingot, Materials.Rubber, 3), GT_ModHandler.getIC2Item("insulatedIronCableItem", 1), 300, 2);
        }
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadSword, Materials.Wood, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 1), new ItemStack(Items.wooden_sword, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadSword, Materials.Stone, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 1), new ItemStack(Items.stone_sword, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadSword, Materials.Iron, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 1), new ItemStack(Items.iron_sword, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadSword, Materials.Gold, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 1), new ItemStack(Items.golden_sword, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadSword, Materials.Diamond, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 1), new ItemStack(Items.diamond_sword, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadSword, Materials.Bronze, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 1), ItemList.Tool_Sword_Bronze.getUndamaged(1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadSword, Materials.Steel, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 1), ItemList.Tool_Sword_Steel.getUndamaged(1), 100, 16);

        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadPickaxe, Materials.Wood, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), new ItemStack(Items.wooden_pickaxe, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadPickaxe, Materials.Stone, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), new ItemStack(Items.stone_pickaxe, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadPickaxe, Materials.Iron, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), new ItemStack(Items.iron_pickaxe, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadPickaxe, Materials.Gold, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), new ItemStack(Items.golden_pickaxe, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadPickaxe, Materials.Diamond, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), new ItemStack(Items.diamond_pickaxe, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadPickaxe, Materials.Bronze, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), ItemList.Tool_Pickaxe_Bronze.getUndamaged(1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadPickaxe, Materials.Steel, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), ItemList.Tool_Pickaxe_Steel.getUndamaged(1), 100, 16);

        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadShovel, Materials.Wood, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), new ItemStack(Items.wooden_shovel, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadShovel, Materials.Stone, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), new ItemStack(Items.stone_shovel, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadShovel, Materials.Iron, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), new ItemStack(Items.iron_shovel, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadShovel, Materials.Gold, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), new ItemStack(Items.golden_shovel, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadShovel, Materials.Diamond, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), new ItemStack(Items.diamond_shovel, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadShovel, Materials.Bronze, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), ItemList.Tool_Shovel_Bronze.getUndamaged(1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadShovel, Materials.Steel, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), ItemList.Tool_Shovel_Steel.getUndamaged(1), 100, 16);

        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadAxe, Materials.Wood, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), new ItemStack(Items.wooden_axe, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadAxe, Materials.Stone, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), new ItemStack(Items.stone_axe, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadAxe, Materials.Iron, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), new ItemStack(Items.iron_axe, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadAxe, Materials.Gold, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), new ItemStack(Items.golden_axe, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadAxe, Materials.Diamond, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), new ItemStack(Items.diamond_axe, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadAxe, Materials.Bronze, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), ItemList.Tool_Axe_Bronze.getUndamaged(1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadAxe, Materials.Steel, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), ItemList.Tool_Axe_Steel.getUndamaged(1), 100, 16);

        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadHoe, Materials.Wood, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), new ItemStack(Items.wooden_hoe, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadHoe, Materials.Stone, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), new ItemStack(Items.stone_hoe, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadHoe, Materials.Iron, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), new ItemStack(Items.iron_hoe, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadHoe, Materials.Gold, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), new ItemStack(Items.golden_hoe, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadHoe, Materials.Diamond, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), new ItemStack(Items.diamond_hoe, 1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadHoe, Materials.Bronze, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), ItemList.Tool_Hoe_Bronze.getUndamaged(1), 100, 16);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.toolHeadHoe, Materials.Steel, 1), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), ItemList.Tool_Hoe_Steel.getUndamaged(1), 100, 16);

        GT_ModHandler.removeRecipe(new ItemStack(Items.lava_bucket), ItemList.Cell_Empty.get(1));
        GT_ModHandler.removeRecipe(new ItemStack(Items.water_bucket), ItemList.Cell_Empty.get(1));

        GT_ModHandler.removeFurnaceSmelting(ItemList.IC2_Resin.get(1));
        if (!GregTech_API.mIC2Classic)
            GT_Values.RA.addPyrolyseRecipe(GT_ModHandler.getIC2Item("biochaff", 4), Materials.Water.getFluid(4000), 1, null, new FluidStack(FluidRegistry.getFluid("ic2biomass"), 5000), 900, 10);
        if (Loader.isModLoaded("Railcraft")) {
            GT_Values.RA.addPyrolyseRecipe(MatUnifier.get(OrePrefixes.gem, Materials.Coal, 16), null, 1, RailcraftToolItems.getCoalCoke(16), Materials.Creosote.getFluid(8000), 640, 64);
            GT_Values.RA.addPyrolyseRecipe(MatUnifier.get(OrePrefixes.gem, Materials.Coal, 16), Materials.Nitrogen.getGas(1000), 2, RailcraftToolItems.getCoalCoke(16), Materials.Creosote.getFluid(8000), 320, 96);
            GT_Values.RA.addPyrolyseRecipe(MatUnifier.get(OrePrefixes.block, Materials.Coal, 8), null, 1, EnumCube.COKE_BLOCK.getItem(8), Materials.Creosote.getFluid(32000), 2560, 64);
            GT_Values.RA.addPyrolyseRecipe(MatUnifier.get(OrePrefixes.block, Materials.Coal, 8), Materials.Nitrogen.getGas(1000), 2, EnumCube.COKE_BLOCK.getItem(8), Materials.Creosote.getFluid(32000), 1280, 96);
        }
        run2();

        GT_Utility.removeSimpleIC2MachineRecipe(new ItemStack(Blocks.cobblestone), GT_ModHandler.getMaceratorRecipeList(), MatUnifier.get(OrePrefixes.dust, Materials.Stone, 1));
        GT_Utility.removeSimpleIC2MachineRecipe(MatUnifier.get(OrePrefixes.gem, Materials.Lapis, 1), GT_ModHandler.getMaceratorRecipeList(), ItemList.IC2_Plantball.get(1));
        GT_Utility.removeSimpleIC2MachineRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Redstone, 1), GT_ModHandler.getMaceratorRecipeList(), ItemList.IC2_Plantball.get(1));
        GT_Utility.removeSimpleIC2MachineRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Glowstone, 1), GT_ModHandler.getMaceratorRecipeList(), ItemList.IC2_Plantball.get(1));

        if (GregTech_API.mMagneticraft && GT_Mod.gregtechproxy.mMagneticraftRecipes) {
            GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getModItem("Magneticraft", "item.ingotCarbide", 8));
            GT_Values.RA.addAlloySmelterRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Coal, 8), MatUnifier.get(OrePrefixes.ingot, Materials.WroughtIron, 1), GT_ModHandler.getModItem("Magneticraft", "item.ingotCarbide", 1), 600, 24);
            GT_Values.RA.addBlastRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Coal, 8), MatUnifier.get(OrePrefixes.ingot, Materials.TungstenCarbide, 1), null, null, GT_ModHandler.getModItem("Magneticraft", "item.ingotCarbide", 8), null, 100, 120, 2600);
            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.chunks", 1, 4));
            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.pebbles", 1, 4));
            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.rubble", 1, 4));
            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.chunks", 1, 13));
            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.pebbles", 1, 13));
            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.rubble", 1, 13));
            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.chunks", 1, 15));
            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.pebbles", 1, 15));
            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.rubble", 1, 15));
            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.chunks", 1, 16));
            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.pebbles", 1, 16));
            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.rubble", 1, 16));
            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.chunks", 1, 21));
            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.pebbles", 1, 21));
            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.rubble", 1, 21));
        }

        for (MaterialStack[] tMats : this.mAlloySmelterList) {
            ItemStack tDust1 = MatUnifier.get(OrePrefixes.dust, tMats[0].mMaterial, tMats[0].mAmount);
            ItemStack tDust2 = MatUnifier.get(OrePrefixes.dust, tMats[1].mMaterial, tMats[1].mAmount);
            ItemStack tIngot1 = MatUnifier.get(OrePrefixes.ingot, tMats[0].mMaterial, tMats[0].mAmount);
            ItemStack tIngot2 = MatUnifier.get(OrePrefixes.ingot, tMats[1].mMaterial, tMats[1].mAmount);
            ItemStack tOutputIngot = MatUnifier.get(OrePrefixes.ingot, tMats[2].mMaterial, tMats[2].mAmount);
            if (tOutputIngot != GT_Values.NI) {
                GT_ModHandler.addAlloySmelterRecipe(tIngot1, tDust2, tOutputIngot, (int)tMats[2].mAmount * 50, 16, false); //TODO REMOVE INT CAST
                GT_ModHandler.addAlloySmelterRecipe(tIngot1, tIngot2, tOutputIngot, (int)tMats[2].mAmount * 50, 16, false); //TODO REMOVE INT CAST
                GT_ModHandler.addAlloySmelterRecipe(tDust1, tIngot2, tOutputIngot, (int)tMats[2].mAmount * 50, 16, false); //TODO REMOVE INT CAST
                GT_ModHandler.addAlloySmelterRecipe(tDust1, tDust2, tOutputIngot, (int)tMats[2].mAmount * 50, 16, false); //TODO REMOVE INT CAST
            }
        }

        if (!GregTech_API.mIC2Classic) {
            try {
                Map<String, HeatExchangeProperty> tLiqExchange = ic2.api.recipe.Recipes.liquidCooldownManager.getHeatExchangeProperties();
                Iterator<Map.Entry<String, HeatExchangeProperty>> tIterator = tLiqExchange.entrySet().iterator();
                while (tIterator.hasNext()) {
                    Map.Entry<String, HeatExchangeProperty> tEntry = tIterator.next();
                    if (tEntry.getKey().equals("ic2hotcoolant")) {
                        tIterator.remove();
                        Recipes.liquidCooldownManager.addFluid("ic2hotcoolant", "ic2coolant", 100);
                    }
                }
            } catch (Throwable e) {/*Do nothing*/}

            try {
                Map<String, HeatExchangeProperty> tLiqExchange = ic2.api.recipe.Recipes.liquidHeatupManager.getHeatExchangeProperties();
                Iterator<Map.Entry<String, HeatExchangeProperty>> tIterator = tLiqExchange.entrySet().iterator();
                while (tIterator.hasNext()) {
                    Map.Entry<String, HeatExchangeProperty> tEntry = tIterator.next();
                    if (tEntry.getKey().equals("ic2coolant")) {
                        tIterator.remove();
                        Recipes.liquidHeatupManager.addFluid("ic2coolant", "ic2hotcoolant", 100);
                    }
                }
            } catch (Throwable e) {/*Do nothing*/}
        }
        GT_Utility.removeSimpleIC2MachineRecipe(ItemList.Crop_Drop_BobsYerUncleRanks.get(1), GT_ModHandler.getExtractorRecipeList(), null);
        GT_Utility.removeSimpleIC2MachineRecipe(ItemList.Crop_Drop_Ferru.get(1), GT_ModHandler.getExtractorRecipeList(), null);
        GT_Utility.removeSimpleIC2MachineRecipe(ItemList.Crop_Drop_Aurelia.get(1), GT_ModHandler.getExtractorRecipeList(), null);

        ItemStack tCrop;
        // Metals Line
        tCrop = ItemList.Crop_Drop_Coppon.get(1);
        addProcess(tCrop, Materials.Copper, 100, true);
        addProcess(tCrop, Materials.Tetrahedrite, 100, false);
        addProcess(tCrop, Materials.Chalcopyrite, 100, false);
        addProcess(tCrop, Materials.Malachite, 100, false);
        addProcess(tCrop, Materials.Pyrite, 100, false);
        addProcess(tCrop, Materials.Stibnite, 100, false);
        tCrop = ItemList.Crop_Drop_Tine.get(1);
        addProcess(tCrop, Materials.Tin, 100, true);
        addProcess(tCrop, Materials.Cassiterite, 100, false);
        tCrop = ItemList.Crop_Drop_Plumbilia.get(1);
        addProcess(tCrop, Materials.Lead, 100, true);
        addProcess(tCrop, Materials.Galena, 100, false);
        tCrop = ItemList.Crop_Drop_Ferru.get(1);
        addProcess(tCrop, Materials.Iron, 100, true);
        addProcess(tCrop, Materials.Magnetite, 100, false);
        addProcess(tCrop, Materials.BrownLimonite, 100, false);
        addProcess(tCrop, Materials.YellowLimonite, 100, false);
        addProcess(tCrop, Materials.VanadiumMagnetite, 100, false);
        addProcess(tCrop, Materials.BandedIron, 100, false);
        addProcess(tCrop, Materials.Pyrite, 100, false);
        addProcess(tCrop, Materials.MeteoricIron, 100, false);
        tCrop = ItemList.Crop_Drop_Nickel.get(1);
        addProcess(tCrop, Materials.Nickel, 100, true);
        addProcess(tCrop, Materials.Garnierite, 100, false);
        addProcess(tCrop, Materials.Pentlandite, 100, false);
        addProcess(tCrop, Materials.Cobaltite, 100, false);
        addProcess(tCrop, Materials.Wulfenite, 100, false);
        addProcess(tCrop, Materials.Powellite, 100, false);
        tCrop = ItemList.Crop_Drop_Zinc.get(1);
        addProcess(tCrop, Materials.Zinc, 100, true);
        addProcess(tCrop, Materials.Sphalerite, 100, false);
        addProcess(tCrop, Materials.Sulfur, 100, false);
        tCrop = ItemList.Crop_Drop_Argentia.get(1);
        addProcess(tCrop, Materials.Silver, 100, true);
        addProcess(tCrop, Materials.Galena, 100, false);
        tCrop = ItemList.Crop_Drop_Aurelia.get(1);
        addProcess(tCrop, Materials.Gold, 100, true);
        addProcess(tCrop, Materials.Magnetite, Materials.Gold, 100, false);

        // Rare Metals Line
        tCrop = ItemList.Crop_Drop_Bauxite.get(1);
        addProcess(tCrop, Materials.Aluminium, 60, true);
        addProcess(tCrop, Materials.Bauxite, 100, false);
        tCrop = ItemList.Crop_Drop_Manganese.get(1);
        addProcess(tCrop, Materials.Manganese, 30, true);
        addProcess(tCrop, Materials.Grossular, 100, false);
        addProcess(tCrop, Materials.Spessartine, 100, false);
        addProcess(tCrop, Materials.Pyrolusite, 100, false);
        addProcess(tCrop, Materials.Tantalite, 100, false);
        tCrop = ItemList.Crop_Drop_Ilmenite.get(1);
        addProcess(tCrop, Materials.Titanium, 100, true);
        addProcess(tCrop, Materials.Ilmenite, 100, false);
        addProcess(tCrop, Materials.Bauxite, 100, false);
        tCrop = ItemList.Crop_Drop_Scheelite.get(1);
        addProcess(tCrop, Materials.Scheelite, 100, true);
        addProcess(tCrop, Materials.Tungstate, 100, false);
        addProcess(tCrop, Materials.Lithium, 100, false);
        tCrop = ItemList.Crop_Drop_Platinum.get(1);
        addProcess(tCrop, Materials.Platinum, 40, true);
        addProcess(tCrop, Materials.Cooperite, 40, false);
        addProcess(tCrop, Materials.Palladium, 40, false);
        addProcess(tCrop, Materials.Neodymium, 100, false);
        addProcess(tCrop, Materials.Bastnasite, 100, false);
        tCrop = ItemList.Crop_Drop_Iridium.get(1);
        addProcess(tCrop, Materials.Iridium, 20, true);
        tCrop = ItemList.Crop_Drop_Osmium.get(1);
        addProcess(tCrop, Materials.Osmium, 20, true);

        // Radioactive Line
        tCrop = ItemList.Crop_Drop_Pitchblende.get(1);
        addProcess(tCrop, Materials.Pitchblende, 50, true);
        tCrop = ItemList.Crop_Drop_Uraninite.get(1);
        addProcess(tCrop, Materials.Uraninite, 50, false);
        addProcess(tCrop, Materials.Uranium, 50, true);
        addProcess(tCrop, Materials.Pitchblende, 50, false);
        addProcess(tCrop, Materials.Uranium235, 50, false);
        tCrop = ItemList.Crop_Drop_Thorium.get(1);
        addProcess(tCrop, Materials.Thorium, 50, true);
        tCrop = ItemList.Crop_Drop_Naquadah.get(1);
        addProcess(tCrop, Materials.Naquadah, 10, true);
        addProcess(tCrop, Materials.NaquadahEnriched, 10, false);
        addProcess(tCrop, Materials.Naquadria, 10, false);

        //Gem Line
        tCrop = ItemList.Crop_Drop_BobsYerUncleRanks.get(1);
        addProcess(tCrop, Materials.Emerald, 100, true);
        addProcess(tCrop, Materials.Beryllium, 100, false);

        addRecipesApril2017ChemistryUpdate();
        if (GT_Mod.gregtechproxy.mReenableSimplifiedChemicalRecipes) {
            addSimplifiedChemicalRecipes();
        } else {
            GT_Values.RA.addChemicalRecipe(Materials.Sodium.getDust(2), Materials.Sulfur.getDust(1), Materials.SodiumSulfide.getDust(1), 60);
            GT_Values.RA.addChemicalRecipe(Materials.HydricSulfide.getCells(1), GT_Values.NI, Materials.Water.getFluid(1000), Materials.DilutedSulfuricAcid.getFluid(750), Materials.Empty.getCells(1), 60);
            GT_Values.RA.addChemicalRecipe(Materials.Water.getCells(1), GT_Values.NI, Materials.HydricSulfide.getGas(1000), Materials.DilutedSulfuricAcid.getFluid(750), Materials.Empty.getCells(1), 60);
        }
        GT_Values.RA.addAutoclaveRecipe(Materials.SiliconDioxide.getDust(1), Materials.Water.getFluid(200L), Materials.Quartzite.getGems(1), 750, 2000, 24);
        GT_Values.RA.addAutoclaveRecipe(Materials.SiliconDioxide.getDust(1), GT_ModHandler.getDistilledWater(200L), Materials.Quartzite.getGems(1), 1000, 1500, 24);

        addRecipesMay2017OilRefining();
        addPrimitiveBlastFurnaceRecipes();
    }

    public void addProcess(ItemStack tCrop, Materials aMaterial, int chance, boolean aMainOutput) {
        if (tCrop == null || aMaterial == null || MatUnifier.get(OrePrefixes.crushed, aMaterial, 1) == null)
            return;
        if (GT_Mod.gregtechproxy.mNerfedCrops) {
            GT_Values.RA.addChemicalRecipe(GT_Utility.copyAmount(9, tCrop), MatUnifier.get(OrePrefixes.crushed, aMaterial, 1), Materials.Water.getFluid(1000), aMaterial.mOreByProducts.isEmpty() ? null : aMaterial.mOreByProducts.get(0).getMolten(144), MatUnifier.get(OrePrefixes.crushedPurified, aMaterial, 4), 96, 24);
            GT_Values.RA.addAutoclaveRecipe(GT_Utility.copyAmount(16, tCrop), Materials.UUMatter.getFluid(Math.max(1, ((aMaterial.getMass() + 9) / 10))), MatUnifier.get(OrePrefixes.crushedPurified, aMaterial, 1), 10000, (int) (aMaterial.getMass() * 128), 384);
        } else {
            if (aMainOutput)
                GT_ModHandler.addExtractionRecipe(tCrop, MatUnifier.get(OrePrefixes.dustTiny, aMaterial, 1));
        }
    }

    public void addProcess(ItemStack tCrop, Materials aMaterial, int chance) {
        if (tCrop == null || aMaterial == null || MatUnifier.get(OrePrefixes.crushed, aMaterial, 1) == null)
            return;
        if (GT_Mod.gregtechproxy.mNerfedCrops) {
            GT_Values.RA.addChemicalRecipe(GT_Utility.copyAmount(9, tCrop), MatUnifier.get(OrePrefixes.crushed, aMaterial, 1), Materials.Water.getFluid(1000), aMaterial.mOreByProducts.isEmpty() ? null : aMaterial.mOreByProducts.get(0).getMolten(144), MatUnifier.get(OrePrefixes.crushedPurified, aMaterial, 4), 96, 24);
            GT_Values.RA.addAutoclaveRecipe(GT_Utility.copyAmount(16, tCrop), Materials.UUMatter.getFluid(Math.max(1, ((aMaterial.getMass() + 9) / 10))), MatUnifier.get(OrePrefixes.crushedPurified, aMaterial, 1), 10000, (int) (aMaterial.getMass() * 128), 384);
        } else {
            GT_ModHandler.addExtractionRecipe(tCrop, MatUnifier.get(OrePrefixes.dustTiny, aMaterial, 1));
        }
    }

    public void addProcess(ItemStack tCrop, Materials aMaterial, Materials aMaterialOut, int chance, boolean aMainOutput) {
        if (tCrop == null || aMaterial == null || MatUnifier.get(OrePrefixes.crushed, aMaterial, 1) == null)
            return;
        if (GT_Mod.gregtechproxy.mNerfedCrops) {
            GT_Values.RA.addChemicalRecipe(GT_Utility.copyAmount(9, tCrop), MatUnifier.get(OrePrefixes.crushed, aMaterial, 1), Materials.Water.getFluid(1000), aMaterialOut.mOreByProducts.isEmpty() ? null : aMaterialOut.mOreByProducts.get(0).getMolten(144), MatUnifier.get(OrePrefixes.crushedPurified, aMaterialOut, 4), 96, 24);
            GT_Values.RA.addAutoclaveRecipe(GT_Utility.copyAmount(16, tCrop), Materials.UUMatter.getFluid(Math.max(1, ((aMaterial.getMass() + 9) / 10))), MatUnifier.get(OrePrefixes.crushedPurified, aMaterial, 1), 10000, (int) (aMaterial.getMass() * 128), 384);
        } else {
            if (aMainOutput)
                GT_ModHandler.addExtractionRecipe(tCrop, MatUnifier.get(OrePrefixes.dustTiny, aMaterial, 1));
        }
    }

    public void addProcess(ItemStack tCrop, Materials aMaterial, Materials aMaterialOut, int chance) {
        if (tCrop == null || aMaterial == null || MatUnifier.get(OrePrefixes.crushed, aMaterial, 1) == null)
            return;
        if (GT_Mod.gregtechproxy.mNerfedCrops) {
            GT_Values.RA.addChemicalRecipe(GT_Utility.copyAmount(9, tCrop), MatUnifier.get(OrePrefixes.crushed, aMaterial, 1), Materials.Water.getFluid(1000), aMaterialOut.mOreByProducts.isEmpty() ? null : aMaterialOut.mOreByProducts.get(0).getMolten(144), MatUnifier.get(OrePrefixes.crushedPurified, aMaterialOut, 4), 96, 24);
            GT_Values.RA.addAutoclaveRecipe(GT_Utility.copyAmount(16, tCrop), Materials.UUMatter.getFluid(Math.max(1, ((aMaterial.getMass() + 9) / 10))), MatUnifier.get(OrePrefixes.crushedPurified, aMaterial, 1), 10000, (int) (aMaterial.getMass() * 128), 384);
        } else {
            GT_ModHandler.addExtractionRecipe(tCrop, MatUnifier.get(OrePrefixes.dustTiny, aMaterial, 1));
        }
    }

    private void run2() {
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Blocks.sand, 1, 1), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.dust, Materials.Iron), MatUnifier.get(OrePrefixes.dustTiny, Materials.Diamond, 1), new ItemStack(Blocks.sand, 1), GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{5000, 100, 5000}, 50, 30);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Blocks.dirt, 1, 32767), GT_Values.NI, GT_Values.NF, GT_Values.NF, ItemList.IC2_Plantball.get(1), MatUnifier.get(OrePrefixes.dustTiny, Materials.Clay), new ItemStack(Blocks.sand, 1), GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{1250, 5000, 5000}, 250, 30);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Blocks.grass, 1, 32767), GT_Values.NI, GT_Values.NF, GT_Values.NF, ItemList.IC2_Plantball.get(1), MatUnifier.get(OrePrefixes.dustTiny, Materials.Clay), new ItemStack(Blocks.sand, 1), GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{2500, 5000, 5000}, 250, 30);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Blocks.mycelium, 1, 32767), GT_Values.NI, GT_Values.NF, GT_Values.NF, new ItemStack(Blocks.brown_mushroom, 1), new ItemStack(Blocks.red_mushroom, 1), MatUnifier.get(OrePrefixes.dustTiny, Materials.Clay, 1), new ItemStack(Blocks.sand, 1), GT_Values.NI, GT_Values.NI, new int[]{2500, 2500, 5000, 5000}, 650, 30);
        GT_Values.RA.addCentrifugeRecipe(ItemList.IC2_Resin.get(1), GT_Values.NI, GT_Values.NF, Materials.Glue.getFluid(100), MatUnifier.get(OrePrefixes.dust, Materials.RawRubber, 3), ItemList.IC2_Plantball.get(1), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{10000, 5000}, 300, 5);
        GT_Values.RA.addCentrifugeRecipe(MatUnifier.get(OrePrefixes.dust, Materials.DarkAsh, 1), 0, MatUnifier.get(OrePrefixes.dust, Materials.Ash), ItemList.TE_Slag.get(1, MatUnifier.get(OrePrefixes.dust, Materials.Carbon)), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, 250);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.magma_cream, 1), 0, new ItemStack(Items.blaze_powder, 1), new ItemStack(Items.slime_ball, 1), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, 500);
        GT_Values.RA.addCentrifugeRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Uranium), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.dustTiny, Materials.Uranium235), MatUnifier.get(OrePrefixes.dustTiny, Materials.Plutonium, 1), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{2000, 200}, 800, 320);
        GT_Values.RA.addCentrifugeRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Plutonium), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.dustTiny, Materials.Plutonium241), MatUnifier.get(OrePrefixes.dustTiny, Materials.Uranium, 1), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{2000, 3000}, 1600, 320);
        GT_Values.RA.addCentrifugeRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Naquadah), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.dustTiny, Materials.NaquadahEnriched), MatUnifier.get(OrePrefixes.dustTiny, Materials.Naquadria, 1), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{5000, 1000}, 3200, 320);
        GT_Values.RA.addCentrifugeRecipe(MatUnifier.get(OrePrefixes.dust, Materials.NaquadahEnriched), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.dustSmall, Materials.Naquadria), MatUnifier.get(OrePrefixes.dustSmall, Materials.Naquadah, 1), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{2000, 3000}, 6400, 640);
        GT_Values.RA.addCentrifugeRecipe(GT_Values.NI, GT_Values.NI, Materials.Hydrogen.getGas(160), Materials.Deuterium.getGas(40), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 160, 20);
        GT_Values.RA.addCentrifugeRecipe(GT_Values.NI, GT_Values.NI, Materials.Deuterium.getGas(160), Materials.Tritium.getGas(40), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 160, 80);
        GT_Values.RA.addCentrifugeRecipe(GT_Values.NI, GT_Values.NI, Materials.Helium.getGas(80), Materials.Helium3.getGas(5), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 160, 80);
        GT_Values.RA.addCentrifugeRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Glowstone), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.dustSmall, Materials.Redstone), MatUnifier.get(OrePrefixes.dustSmall, Materials.Gold, 2), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 488, 80);
        GT_Values.RA.addCentrifugeRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Endstone), GT_Values.NI, GT_Values.NF, Materials.Helium.getGas(120), MatUnifier.get(OrePrefixes.dustSmall, Materials.Tungstate), MatUnifier.get(OrePrefixes.dustTiny, Materials.Platinum, 1), new ItemStack(Blocks.sand, 1), GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{1250, 625, 9000, 0, 0, 0}, 320, 20);
        GT_Values.RA.addCentrifugeRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Netherrack), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.dustTiny, Materials.Redstone), MatUnifier.get(OrePrefixes.dustSmall, Materials.Sulfur, 1), MatUnifier.get(OrePrefixes.dustTiny, Materials.Coal, 1), MatUnifier.get(OrePrefixes.dustTiny, Materials.Gold, 1), GT_Values.NI, GT_Values.NI, new int[]{5625, 9900, 5625, 625, 0, 0}, 160, 20);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Blocks.soul_sand, 1), GT_Values.NI, GT_Values.NF, Materials.Oil.getFluid(80), MatUnifier.get(OrePrefixes.dustSmall, Materials.Saltpeter), MatUnifier.get(OrePrefixes.dustTiny, Materials.Coal), new ItemStack(Blocks.sand, 1), GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{8000, 2000, 9000, 0, 0, 0}, 200, 80);
        GT_Values.RA.addCentrifugeRecipe(GT_Values.NI, GT_Values.NI, Materials.Lava.getFluid(100), GT_Values.NF, MatUnifier.get(OrePrefixes.nugget, Materials.Copper), MatUnifier.get(OrePrefixes.nugget, Materials.Tin), MatUnifier.get(OrePrefixes.nugget, Materials.Gold), MatUnifier.get(OrePrefixes.nugget, Materials.Silver), MatUnifier.get(OrePrefixes.nugget, Materials.Tantalum), MatUnifier.get(OrePrefixes.dustSmall, Materials.Tungstate), new int[]{2000, 1000, 250, 250, 250, 250}, 80, 80);
        GT_Values.RA.addCentrifugeRecipe(GT_Values.NI, GT_Values.NI, FluidRegistry.getFluidStack("ic2pahoehoelava", 100), GT_Values.NF, MatUnifier.get(OrePrefixes.nugget, Materials.Copper), MatUnifier.get(OrePrefixes.nugget, Materials.Tin), MatUnifier.get(OrePrefixes.nugget, Materials.Gold), MatUnifier.get(OrePrefixes.nugget, Materials.Silver), MatUnifier.get(OrePrefixes.nugget, Materials.Tantalum), MatUnifier.get(OrePrefixes.dustSmall, Materials.Tungstate), new int[]{2000, 1000, 250, 250, 250, 250}, 40, 80);

        GT_Values.RA.addCentrifugeRecipe(MatUnifier.get(OrePrefixes.dust, Materials.RareEarth), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.dustSmall, Materials.Neodymium), MatUnifier.get(OrePrefixes.dustSmall, Materials.Yttrium), MatUnifier.get(OrePrefixes.dustSmall, Materials.Lanthanum), MatUnifier.get(OrePrefixes.dustSmall, Materials.Cerium), MatUnifier.get(OrePrefixes.dustSmall, Materials.Cadmium), MatUnifier.get(OrePrefixes.dustSmall, Materials.Caesium), new int[]{2500, 2500, 2500, 2500, 2500, 2500}, 64, 20);
        GT_Values.RA.addCentrifugeRecipe(GT_ModHandler.getModItem(aTextAE, aTextAEMM, 1, 45), GT_Values.NI, GT_Values.NF, GT_Values.NF, MatUnifier.get(OrePrefixes.dustSmall, Materials.BasalticMineralSand), MatUnifier.get(OrePrefixes.dustSmall, Materials.Olivine), MatUnifier.get(OrePrefixes.dustSmall, Materials.Obsidian), MatUnifier.get(OrePrefixes.dustSmall, Materials.Basalt), MatUnifier.get(OrePrefixes.dustSmall, Materials.Flint), MatUnifier.get(OrePrefixes.dustSmall, Materials.RareEarth), new int[]{2000, 2000, 2000, 2000, 2000, 2000}, 64, 20);

        GT_Values.RA.addAssemblylineRecipe(ItemList.Electric_Motor_IV.get(1), 144000, new ItemStack[]{
                MatUnifier.get(OrePrefixes.stick, Materials.NeodymiumMagnetic, 1),
                MatUnifier.get(OrePrefixes.stick, Materials.HSSG, 2),
                MatUnifier.get(OrePrefixes.wireFine, Materials.AnnealedCopper, 64),
                MatUnifier.get(OrePrefixes.wireFine, Materials.AnnealedCopper, 64),
                MatUnifier.get(OrePrefixes.wireFine, Materials.AnnealedCopper, 64),
                MatUnifier.get(OrePrefixes.wireFine, Materials.AnnealedCopper, 64),
                MatUnifier.get(OrePrefixes.cableGt01, Materials.YttriumBariumCuprate, 2)}, new FluidStack[]{
                Materials.SolderingAlloy.getMolten(144),
                Materials.Lubricant.getFluid(250)}, ItemList.Electric_Motor_LuV.get(1), 600, 6000);

        GT_Values.RA.addAssemblylineRecipe(ItemList.Electric_Motor_LuV.get(1), 144000, new ItemStack[]{
                MatUnifier.get(OrePrefixes.stick, Materials.NeodymiumMagnetic, 2),
                MatUnifier.get(OrePrefixes.stick, Materials.HSSE, 4),
                MatUnifier.get(OrePrefixes.ring, Materials.HSSE, 4),
                MatUnifier.get(OrePrefixes.wireFine, Materials.Platinum, 64),
                MatUnifier.get(OrePrefixes.wireFine, Materials.Platinum, 64),
                MatUnifier.get(OrePrefixes.wireFine, Materials.Platinum, 64),
                MatUnifier.get(OrePrefixes.wireFine, Materials.Platinum, 64),
                MatUnifier.get(OrePrefixes.cableGt04, Materials.VanadiumGallium, 2)}, new FluidStack[]{
                Materials.SolderingAlloy.getMolten(288),
                Materials.Lubricant.getFluid(750)}, ItemList.Electric_Motor_ZPM.get(1), 600, 24000);

        GT_Values.RA.addAssemblylineRecipe(ItemList.Electric_Motor_ZPM.get(1), 288000, new ItemStack[]{
                MatUnifier.get(OrePrefixes.block, Materials.NeodymiumMagnetic, 1),
                MatUnifier.get(OrePrefixes.stick, Materials.Neutronium, 4),
                MatUnifier.get(OrePrefixes.ring, Materials.Neutronium, 4),
                MatUnifier.get(OrePrefixes.wireGt01, Materials.Superconductor, 64),
                MatUnifier.get(OrePrefixes.wireGt01, Materials.Superconductor, 64),
                MatUnifier.get(OrePrefixes.wireGt01, Materials.Superconductor, 64),
                MatUnifier.get(OrePrefixes.wireGt01, Materials.Superconductor, 64),
                MatUnifier.get(OrePrefixes.cableGt04, Materials.NiobiumTitanium, 2)}, new FluidStack[]{
                Materials.SolderingAlloy.getMolten(1296),
                Materials.Lubricant.getFluid(2000)}, ItemList.Electric_Motor_UV.get(1), 600, 100000);

        GT_Values.RA.addAssemblylineRecipe(ItemList.Electric_Pump_IV.get(1), 144000, new ItemStack[]{
                ItemList.Electric_Motor_LuV.get(1),
                MatUnifier.get(OrePrefixes.pipeSmall, Materials.Ultimate, 2),
                MatUnifier.get(OrePrefixes.plate, Materials.HSSG, 2),
                MatUnifier.get(OrePrefixes.screw, Materials.HSSG, 8),
                MatUnifier.get(OrePrefixes.ring, Materials.Silicone, 4),
                MatUnifier.get(OrePrefixes.rotor, Materials.HSSG, 2),
                MatUnifier.get(OrePrefixes.cableGt01, Materials.YttriumBariumCuprate, 2)}, new FluidStack[]{
                Materials.SolderingAlloy.getMolten(144),
                Materials.Lubricant.getFluid(250)}, ItemList.Electric_Pump_LuV.get(1), 600, 6000);

        GT_Values.RA.addAssemblylineRecipe(ItemList.Electric_Pump_LuV.get(1), 144000, new ItemStack[]{
                ItemList.Electric_Motor_ZPM.get(1),
                MatUnifier.get(OrePrefixes.pipeMedium, Materials.Ultimate, 2),
                MatUnifier.get(OrePrefixes.plate, Materials.HSSE, 2),
                MatUnifier.get(OrePrefixes.screw, Materials.HSSE, 8),
                MatUnifier.get(OrePrefixes.ring, Materials.Silicone, 16),
                MatUnifier.get(OrePrefixes.rotor, Materials.HSSE, 2),
                MatUnifier.get(OrePrefixes.cableGt04, Materials.VanadiumGallium, 2)}, new FluidStack[]{
                Materials.SolderingAlloy.getMolten(288),
                Materials.Lubricant.getFluid(750)}, ItemList.Electric_Pump_ZPM.get(1), 600, 24000);

        GT_Values.RA.addAssemblylineRecipe(ItemList.Electric_Pump_ZPM.get(1), 288000, new ItemStack[]{
                ItemList.Electric_Motor_UV.get(1),
                MatUnifier.get(OrePrefixes.pipeLarge, Materials.Ultimate, 2),
                MatUnifier.get(OrePrefixes.plate, Materials.Neutronium, 2),
                MatUnifier.get(OrePrefixes.screw, Materials.Neutronium, 8),
                MatUnifier.get(OrePrefixes.ring, Materials.Silicone, 16),
                MatUnifier.get(OrePrefixes.rotor, Materials.Neutronium, 2),
                MatUnifier.get(OrePrefixes.cableGt04, Materials.NiobiumTitanium, 2)}, new FluidStack[]{
                Materials.SolderingAlloy.getMolten(1296),
                Materials.Lubricant.getFluid(2000)}, ItemList.Electric_Pump_UV.get(1), 600, 100000);

//        Conveyor

        GT_Values.RA.addAssemblylineRecipe(ItemList.Conveyor_Module_IV.get(1), 144000, new ItemStack[]{
                ItemList.Electric_Motor_LuV.get(2),
                MatUnifier.get(OrePrefixes.plate, Materials.HSSG, 2),
                MatUnifier.get(OrePrefixes.ring, Materials.HSSG, 4),
                MatUnifier.get(OrePrefixes.cableGt01, Materials.YttriumBariumCuprate, 2)}, new FluidStack[]{
                Materials.StyreneButadieneRubber.getMolten(1440),
                Materials.Lubricant.getFluid(250)}, ItemList.Conveyor_Module_LuV.get(1), 600, 6000);

        GT_Values.RA.addAssemblylineRecipe(ItemList.Conveyor_Module_LuV.get(1), 144000, new ItemStack[]{
                ItemList.Electric_Motor_ZPM.get(2),
                MatUnifier.get(OrePrefixes.plate, Materials.HSSE, 2),
                MatUnifier.get(OrePrefixes.ring, Materials.HSSE, 4),
                MatUnifier.get(OrePrefixes.cableGt04, Materials.VanadiumGallium, 2)}, new FluidStack[]{
                Materials.StyreneButadieneRubber.getMolten(2880),
                Materials.Lubricant.getFluid(750)}, ItemList.Conveyor_Module_ZPM.get(1), 600, 24000);

        GT_Values.RA.addAssemblylineRecipe(ItemList.Conveyor_Module_ZPM.get(1), 288000, new ItemStack[]{
                ItemList.Electric_Motor_UV.get(2),
                MatUnifier.get(OrePrefixes.plate, Materials.Neutronium, 2),
                MatUnifier.get(OrePrefixes.ring, Materials.Neutronium, 4),
                MatUnifier.get(OrePrefixes.cableGt04, Materials.NiobiumTitanium, 2)}, new FluidStack[]{
                Materials.StyreneButadieneRubber.getMolten(2880),
                Materials.Lubricant.getFluid(2000)}, ItemList.Conveyor_Module_UV.get(1), 600, 100000);

//        Piston

        GT_Values.RA.addAssemblylineRecipe(ItemList.Electric_Piston_IV.get(1), 144000, new ItemStack[]{
                ItemList.Electric_Motor_LuV.get(1),
                MatUnifier.get(OrePrefixes.plate, Materials.HSSG, 6),
                MatUnifier.get(OrePrefixes.ring, Materials.HSSG, 4),
                MatUnifier.get(OrePrefixes.stick, Materials.HSSG, 4),
                MatUnifier.get(OrePrefixes.gear, Materials.HSSG),
                MatUnifier.get(OrePrefixes.gearGtSmall, Materials.HSSG, 2),
                MatUnifier.get(OrePrefixes.cableGt01, Materials.YttriumBariumCuprate, 4)}, new FluidStack[]{
                Materials.SolderingAlloy.getMolten(144),
                Materials.Lubricant.getFluid(250)}, ItemList.Electric_Piston_LuV.get(1), 600, 6000);


        GT_Values.RA.addAssemblylineRecipe(ItemList.Electric_Piston_LuV.get(1), 144000, new ItemStack[]{
                ItemList.Electric_Motor_ZPM.get(1),
                MatUnifier.get(OrePrefixes.plate, Materials.HSSE, 6),
                MatUnifier.get(OrePrefixes.ring, Materials.HSSE, 4),
                MatUnifier.get(OrePrefixes.stick, Materials.HSSE, 4),
                MatUnifier.get(OrePrefixes.gear, Materials.HSSE),
                MatUnifier.get(OrePrefixes.gearGtSmall, Materials.HSSE, 2),
                MatUnifier.get(OrePrefixes.cableGt04, Materials.VanadiumGallium, 4)}, new FluidStack[]{
                Materials.SolderingAlloy.getMolten(288),
                Materials.Lubricant.getFluid(750)}, ItemList.Electric_Piston_ZPM.get(1), 600, 24000);

        GT_Values.RA.addAssemblylineRecipe(ItemList.Electric_Piston_ZPM.get(1), 288000, new ItemStack[]{
                ItemList.Electric_Motor_UV.get(1),
                MatUnifier.get(OrePrefixes.plate, Materials.Neutronium, 6),
                MatUnifier.get(OrePrefixes.ring, Materials.Neutronium, 4),
                MatUnifier.get(OrePrefixes.stick, Materials.Neutronium, 4),
                MatUnifier.get(OrePrefixes.gear, Materials.Neutronium),
                MatUnifier.get(OrePrefixes.gearGtSmall, Materials.Neutronium, 2),
                MatUnifier.get(OrePrefixes.cableGt04, Materials.NiobiumTitanium, 4)}, new FluidStack[]{
                Materials.SolderingAlloy.getMolten(1296),
                Materials.Lubricant.getFluid(2000)}, ItemList.Electric_Piston_UV.get(1), 600, 100000);

//        RobotArm


        GT_Values.RA.addAssemblylineRecipe(ItemList.Robot_Arm_IV.get(1), 144000, new ItemStack[]{
                MatUnifier.get(OrePrefixes.stick, Materials.HSSG, 4),
                MatUnifier.get(OrePrefixes.gear, Materials.HSSG),
                MatUnifier.get(OrePrefixes.gearGtSmall, Materials.HSSG, 3),
                ItemList.Electric_Motor_LuV.get(2),
                ItemList.Electric_Piston_LuV.get(1),
                ItemList.Circuit_Masterquantumcomputer.get(2),
                ItemList.Circuit_Quantumcomputer.get(2),
                ItemList.Circuit_Nanoprocessor.get(6),
                MatUnifier.get(OrePrefixes.cableGt01, Materials.YttriumBariumCuprate, 6)}, new FluidStack[]{
                Materials.SolderingAlloy.getMolten(576),
                Materials.Lubricant.getFluid(250)}, ItemList.Robot_Arm_LuV.get(1), 600, 6000);

        GT_Values.RA.addAssemblylineRecipe(ItemList.Robot_Arm_LuV.get(1), 144000, new ItemStack[]{
                MatUnifier.get(OrePrefixes.stick, Materials.HSSE, 4),
                MatUnifier.get(OrePrefixes.gear, Materials.HSSE, 1),
                MatUnifier.get(OrePrefixes.gearGtSmall, Materials.HSSE, 3),
                ItemList.Electric_Motor_ZPM.get(2),
                ItemList.Electric_Piston_ZPM.get(1),
                ItemList.Circuit_Masterquantumcomputer.get(4),
                ItemList.Circuit_Quantumcomputer.get(4),
                ItemList.Circuit_Nanoprocessor.get(12),
                MatUnifier.get(OrePrefixes.cableGt04, Materials.VanadiumGallium, 6)}, new FluidStack[]{
                Materials.SolderingAlloy.getMolten(1152),
                Materials.Lubricant.getFluid(750)}, ItemList.Robot_Arm_ZPM.get(1), 600, 24000);

        GT_Values.RA.addAssemblylineRecipe(ItemList.Robot_Arm_ZPM.get(1), 288000, new ItemStack[]{
                MatUnifier.get(OrePrefixes.stick, Materials.Neutronium, 4),
                MatUnifier.get(OrePrefixes.gear, Materials.Neutronium),
                MatUnifier.get(OrePrefixes.gearGtSmall, Materials.Neutronium, 3),
                ItemList.Electric_Motor_UV.get(2),
                ItemList.Electric_Piston_UV.get(1),
                ItemList.Circuit_Crystalcomputer.get(8),
                ItemList.Circuit_Crystalprocessor.get(8),
                ItemList.Circuit_Nanoprocessor.get(24),
                MatUnifier.get(OrePrefixes.cableGt04, Materials.NiobiumTitanium, 6)}, new FluidStack[]{
                Materials.SolderingAlloy.getMolten(2304),
                Materials.Lubricant.getFluid(2000)}, ItemList.Robot_Arm_UV.get(1), 600, 100000);


//        Emitter

        GT_Values.RA.addAssemblylineRecipe(ItemList.Emitter_IV.get(1), 144000, new ItemStack[]{
                        MatUnifier.get(OrePrefixes.frameGt, Materials.HSSG),
                        ItemList.Emitter_IV.get(1),
                        ItemList.Emitter_EV.get(2),
                        ItemList.Emitter_HV.get(4),
                        ItemList.Circuit_Nanoprocessor.get(7),
                        MatUnifier.get(OrePrefixes.foil, Materials.Electrum, 64),
                        MatUnifier.get(OrePrefixes.foil, Materials.Electrum, 64),
                        MatUnifier.get(OrePrefixes.foil, Materials.Electrum, 64),
                        MatUnifier.get(OrePrefixes.cableGt01, Materials.YttriumBariumCuprate, 7)}, new FluidStack[]{
                        Materials.SolderingAlloy.getMolten(576)},
                ItemList.Emitter_LuV.get(1), 600, 6000);

        GT_Values.RA.addAssemblylineRecipe(ItemList.Emitter_LuV.get(1), 144000, new ItemStack[]{
                        MatUnifier.get(OrePrefixes.frameGt, Materials.HSSE),
                        ItemList.Emitter_LuV.get(1),
                        ItemList.Emitter_IV.get(2),
                        ItemList.Emitter_EV.get(4),
                        ItemList.Circuit_Quantumcomputer.get(7),
                        MatUnifier.get(OrePrefixes.foil, Materials.Platinum, 64),
                        MatUnifier.get(OrePrefixes.foil, Materials.Platinum, 64),
                        MatUnifier.get(OrePrefixes.foil, Materials.Platinum, 64),
                        MatUnifier.get(OrePrefixes.cableGt04, Materials.VanadiumGallium, 7)}, new FluidStack[]{
                        Materials.SolderingAlloy.getMolten(576)},
                ItemList.Emitter_ZPM.get(1), 600, 24000);

        GT_Values.RA.addAssemblylineRecipe(ItemList.Emitter_ZPM.get(1), 288000, new ItemStack[]{
                        MatUnifier.get(OrePrefixes.frameGt, Materials.Neutronium),
                        ItemList.Emitter_ZPM.get(1),
                        ItemList.Emitter_LuV.get(2),
                        ItemList.Emitter_IV.get(4),
                        ItemList.Circuit_Crystalcomputer.get(7),
                        MatUnifier.get(OrePrefixes.foil, Materials.Osmiridium, 64),
                        MatUnifier.get(OrePrefixes.foil, Materials.Osmiridium, 64),
                        MatUnifier.get(OrePrefixes.foil, Materials.Osmiridium, 64),
                        MatUnifier.get(OrePrefixes.cableGt04, Materials.NiobiumTitanium, 7)}, new FluidStack[]{
                        Materials.SolderingAlloy.getMolten(576)},
                ItemList.Emitter_UV.get(1), 600, 100000);

//        Sensor

        GT_Values.RA.addAssemblylineRecipe(ItemList.Sensor_IV.get(1), 144000, new ItemStack[]{
                        MatUnifier.get(OrePrefixes.frameGt, Materials.HSSG),
                        ItemList.Sensor_IV.get(1),
                        ItemList.Sensor_EV.get(2),
                        ItemList.Sensor_HV.get(4),
                        ItemList.Circuit_Nanoprocessor.get(7),
                        MatUnifier.get(OrePrefixes.foil, Materials.Electrum, 64),
                        MatUnifier.get(OrePrefixes.foil, Materials.Electrum, 64),
                        MatUnifier.get(OrePrefixes.foil, Materials.Electrum, 64),
                        MatUnifier.get(OrePrefixes.cableGt01, Materials.YttriumBariumCuprate, 7)}, new FluidStack[]{
                        Materials.SolderingAlloy.getMolten(576)},
                ItemList.Sensor_LuV.get(1), 600, 6000);

        GT_Values.RA.addAssemblylineRecipe(ItemList.Sensor_LuV.get(1), 144000, new ItemStack[]{
                        MatUnifier.get(OrePrefixes.frameGt, Materials.HSSE),
                        ItemList.Sensor_LuV.get(1),
                        ItemList.Sensor_IV.get(2),
                        ItemList.Sensor_EV.get(4),
                        ItemList.Circuit_Quantumcomputer.get(7),
                        MatUnifier.get(OrePrefixes.foil, Materials.Platinum, 64),
                        MatUnifier.get(OrePrefixes.foil, Materials.Platinum, 64),
                        MatUnifier.get(OrePrefixes.foil, Materials.Platinum, 64),
                        MatUnifier.get(OrePrefixes.cableGt04, Materials.VanadiumGallium, 7)}, new FluidStack[]{
                        Materials.SolderingAlloy.getMolten(576)},
                ItemList.Sensor_ZPM.get(1), 600, 24000);

        GT_Values.RA.addAssemblylineRecipe(ItemList.Sensor_ZPM.get(1), 288000, new ItemStack[]{
                        MatUnifier.get(OrePrefixes.frameGt, Materials.Neutronium),
                        ItemList.Sensor_ZPM.get(1),
                        ItemList.Sensor_LuV.get(2),
                        ItemList.Sensor_IV.get(4),
                        ItemList.Circuit_Crystalcomputer.get(7, null),
                        MatUnifier.get(OrePrefixes.foil, Materials.Osmiridium, 64),
                        MatUnifier.get(OrePrefixes.foil, Materials.Osmiridium, 64),
                        MatUnifier.get(OrePrefixes.foil, Materials.Osmiridium, 64),
                        MatUnifier.get(OrePrefixes.cableGt04, Materials.NiobiumTitanium, 7)}, new FluidStack[]{
                        Materials.SolderingAlloy.getMolten(576)},
                ItemList.Sensor_UV.get(1), 600, 100000);

//        Field Generator

        GT_Values.RA.addAssemblylineRecipe(ItemList.Field_Generator_IV.get(1), 144000, new ItemStack[]{
                        MatUnifier.get(OrePrefixes.frameGt, Materials.HSSG),
                        MatUnifier.get(OrePrefixes.plate, Materials.HSSG, 6),
                        ItemList.QuantumStar.get(1),
                        ItemList.Emitter_LuV.get(4),
                        ItemList.Circuit_Masterquantumcomputer.get(8),
                        MatUnifier.get(OrePrefixes.wireFine, Materials.Osmium, 64),
                        MatUnifier.get(OrePrefixes.wireFine, Materials.Osmium, 64),
                        MatUnifier.get(OrePrefixes.wireFine, Materials.Osmium, 64),
                        MatUnifier.get(OrePrefixes.wireFine, Materials.Osmium, 64),
                        MatUnifier.get(OrePrefixes.cableGt01, Materials.YttriumBariumCuprate, 8)}, new FluidStack[]{
                        Materials.SolderingAlloy.getMolten(576)},
                ItemList.Field_Generator_LuV.get(1), 600, 6000);

        GT_Values.RA.addAssemblylineRecipe(ItemList.Field_Generator_LuV.get(1), 144000, new ItemStack[]{
                        MatUnifier.get(OrePrefixes.frameGt, Materials.HSSE),
                        MatUnifier.get(OrePrefixes.plate, Materials.HSSE, 6),
                        ItemList.QuantumStar.get(4),
                        ItemList.Emitter_ZPM.get(4),
                        ItemList.Circuit_Crystalcomputer.get(16),
                        MatUnifier.get(OrePrefixes.wireFine, Materials.Osmium, 64),
                        MatUnifier.get(OrePrefixes.wireFine, Materials.Osmium, 64),
                        MatUnifier.get(OrePrefixes.wireFine, Materials.Osmium, 64),
                        MatUnifier.get(OrePrefixes.wireFine, Materials.Osmium, 64),
                        MatUnifier.get(OrePrefixes.wireFine, Materials.Osmium, 64),
                        MatUnifier.get(OrePrefixes.wireFine, Materials.Osmium, 64),
                        MatUnifier.get(OrePrefixes.wireFine, Materials.Osmium, 64),
                        MatUnifier.get(OrePrefixes.wireFine, Materials.Osmium, 64),
                        MatUnifier.get(OrePrefixes.cableGt04, Materials.VanadiumGallium, 8)}, new FluidStack[]{
                        Materials.SolderingAlloy.getMolten(1152)},
                ItemList.Field_Generator_ZPM.get(1), 600, 24000);

        GT_Values.RA.addAssemblylineRecipe(ItemList.Field_Generator_ZPM.get(1), 288000, new ItemStack[]{
                        MatUnifier.get(OrePrefixes.frameGt, Materials.Neutronium),
                        MatUnifier.get(OrePrefixes.plate, Materials.Neutronium, 6),
                        ItemList.Gravistar.get(1),
                        ItemList.Emitter_UV.get(4),
                        ItemList.Circuit_Neuroprocessor.get(64),
                        MatUnifier.get(OrePrefixes.wireFine, Materials.Osmium, 64),
                        MatUnifier.get(OrePrefixes.wireFine, Materials.Osmium, 64),
                        MatUnifier.get(OrePrefixes.wireFine, Materials.Osmium, 64),
                        MatUnifier.get(OrePrefixes.wireFine, Materials.Osmium, 64),
                        MatUnifier.get(OrePrefixes.wireFine, Materials.Osmium, 64),
                        MatUnifier.get(OrePrefixes.wireFine, Materials.Osmium, 64),
                        MatUnifier.get(OrePrefixes.wireFine, Materials.Osmium, 64),
                        MatUnifier.get(OrePrefixes.wireFine, Materials.Osmium, 64),
                        MatUnifier.get(OrePrefixes.cableGt04, Materials.NiobiumTitanium, 8)}, new FluidStack[]{
                        Materials.SolderingAlloy.getMolten(2304)},
                ItemList.Field_Generator_UV.get(1), 600, 100000);

//        Quantumsuite
        GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("quantumHelmet", 1));
        GT_Values.RA.addAssemblylineRecipe(GT_ModHandler.getIC2Item("nanoHelmet", 1, GT_Values.W), 144000, new ItemStack[]{
                GT_ModHandler.getIC2Item("nanoHelmet", 1, GT_Values.W),
                ItemList.Circuit_Masterquantumcomputer.get(2),
                ItemList.Plate_IridiumAlloy.get(4),
                ItemList.Energy_LapotronicOrb.get(1),
                ItemList.Sensor_IV.get(1),
                ItemList.Field_Generator_EV.get(1),
                MatUnifier.get(OrePrefixes.screw, Materials.Tungsten, 4)
        }, new FluidStack[]{
                Materials.SolderingAlloy.getMolten(2304),
                Materials.Titanium.getMolten(1440)
        }, GT_ModHandler.getIC2Item("quantumHelmet", 1), 1500, 4096);

        GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("quantumBodyarmor", 1));
        GT_Values.RA.addAssemblylineRecipe(Loader.isModLoaded("GraviSuite") ? GT_ModHandler.getModItem("GraviSuite", "advNanoChestPlate", 1, GT_Values.W) : GT_ModHandler.getIC2Item("nanoBodyarmor", 1, GT_Values.W), 144000, new ItemStack[]{
                Loader.isModLoaded("GraviSuite") ? GT_ModHandler.getModItem("GraviSuite", "advNanoChestPlate", 1, GT_Values.W) : GT_ModHandler.getIC2Item("nanoBodyarmor", 1, GT_Values.W),
                ItemList.Circuit_Masterquantumcomputer.get(2),
                ItemList.Plate_IridiumAlloy.get(6),
                ItemList.Energy_LapotronicOrb.get(1),
                ItemList.Field_Generator_HV.get(2),
                ItemList.Electric_Motor_IV.get(2),
                MatUnifier.get(OrePrefixes.screw, Materials.Tungsten, 4)
        }, new FluidStack[]{
                Materials.SolderingAlloy.getMolten(2304),
                Materials.Titanium.getMolten(1440)
        }, GT_ModHandler.getIC2Item("quantumBodyarmor", 1), 1500, 4096);

        GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("quantumLeggings", 1));
        GT_Values.RA.addAssemblylineRecipe(GT_ModHandler.getIC2Item("nanoLeggings", 1, GT_Values.W), 144000, new ItemStack[]{
                GT_ModHandler.getIC2Item("nanoLeggings", 1, GT_Values.W),
                ItemList.Circuit_Masterquantumcomputer.get(2),
                ItemList.Plate_IridiumAlloy.get(6),
                ItemList.Energy_LapotronicOrb.get(1),
                ItemList.Field_Generator_HV.get(2),
                ItemList.Electric_Motor_IV.get(4),
                MatUnifier.get(OrePrefixes.screw, Materials.Tungsten, 4)
        }, new FluidStack[]{
                Materials.SolderingAlloy.getMolten(2304),
                Materials.Titanium.getMolten(1440)
        }, GT_ModHandler.getIC2Item("quantumLeggings", 1), 1500, 4096);

        GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("quantumBoots", 1));
        GT_Values.RA.addAssemblylineRecipe(GT_ModHandler.getIC2Item("nanoBoots", 1, GT_Values.W), 144000, new ItemStack[]{
                GT_ModHandler.getIC2Item("nanoBoots", 1, GT_Values.W),
                ItemList.Circuit_Masterquantumcomputer.get(2),
                ItemList.Plate_IridiumAlloy.get(4),
                ItemList.Energy_LapotronicOrb.get(1),
                ItemList.Field_Generator_HV.get(1),
                ItemList.Electric_Piston_IV.get(2),
                MatUnifier.get(OrePrefixes.screw, Materials.Tungsten, 4)
        }, new FluidStack[]{
                Materials.SolderingAlloy.getMolten(2304),
                Materials.Titanium.getMolten(1440)
        }, GT_ModHandler.getIC2Item("quantumBoots", 1), 1500, 4096);

        if (Loader.isModLoaded("GraviSuite")) {
            GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getModItem("GraviSuite", "graviChestPlate", 1, GT_Values.W));
            GT_Values.RA.addAssemblylineRecipe(GT_ModHandler.getIC2Item("quantumBodyarmor", 1, GT_Values.W), 144000, new ItemStack[]{
                    GT_ModHandler.getIC2Item("quantumBodyarmor", 1, GT_Values.W),
                    GT_ModHandler.getModItem("GraviSuite", "ultimateLappack", 1, GT_Values.W),
                    ItemList.Circuit_Ultimatecrystalcomputer.get(2),
                    MatUnifier.get(OrePrefixes.plate, Materials.Duranium, 6),
                    ItemList.Energy_LapotronicOrb2.get(1),
                    ItemList.Field_Generator_IV.get(2),
                    ItemList.Electric_Motor_ZPM.get(2),
                    MatUnifier.get(OrePrefixes.wireGt02, Materials.Superconductor, 32),
                    MatUnifier.get(OrePrefixes.screw, Materials.Duranium, 4)
            }, new FluidStack[]{
                    Materials.SolderingAlloy.getMolten(2304),
                    Materials.Tritanium.getMolten(1440)
            }, GT_ModHandler.getModItem("GraviSuite", "graviChestPlate", 1, 27), 1500, 16388);
        }

        Object o = new Object[0];
        GT_Values.RA.addAssemblylineRecipe(ItemList.Circuit_Crystalmainframe.get(1), 72000, new ItemStack[]{
                ItemList.Circuit_Board_Wetware.get(1),
                ItemList.Circuit_Chip_Stemcell.get(8),
                ItemList.Circuit_Parts_Glass_Tube.get(8),
                MatUnifier.get(OrePrefixes.pipeTiny, Materials.Plastic, 4),
                ItemList.IC2_Item_Casing_Gold.get(8),
                MatUnifier.get(OrePrefixes.foil, Materials.Silicone, 64),
                MatUnifier.get(OrePrefixes.plate, Materials.StainlessSteel, 4),
        }, new FluidStack[]{
                GregTech_API.mIC2Classic ? Materials.Water.getFluid(250) : new FluidStack(FluidRegistry.getFluid("ic2biomass"), 250),
                Materials.UUMatter.getFluid(100),
                GregTech_API.mIC2Classic ? Materials.Lava.getFluid(1000) : new FluidStack(FluidRegistry.getFluid("ic2coolant"), 1000)
        }, ItemList.Circuit_Chip_NeuroCPU.get(1), 200, 80000);

        GT_Values.RA.addAssemblylineRecipe(ItemList.Circuit_Wetwaresupercomputer.get(1), 288000, new ItemStack[]{
                MatUnifier.get(OrePrefixes.frameGt, Materials.Tritanium, 4),
                ItemList.Circuit_Wetwaresupercomputer.get(8),
                ItemList.Circuit_Parts_Coil.get(4),
                ItemList.Circuit_Parts_CapacitorSMD.get(24),
                ItemList.Circuit_Parts_ResistorSMD.get(64),
                ItemList.Circuit_Parts_TransistorSMD.get(32),
                ItemList.Circuit_Parts_DiodeSMD.get(16),
                ItemList.Circuit_Chip_Ram.get(16), MatUnifier.get(OrePrefixes.wireGt01, Materials.Superconductor, 32),
                MatUnifier.get(OrePrefixes.foil, Materials.Silicone, 64)
        }, new FluidStack[]{
                Materials.SolderingAlloy.getMolten(2880),
                GregTech_API.mIC2Classic ? Materials.Water.getFluid(10000) : new FluidStack(FluidRegistry.getFluid("ic2coolant"), 10000)
        }, ItemList.Circuit_Wetwaremainframe.get(1), 2000, 300000);

        GT_Values.RA.addAssemblylineRecipe(ItemList.Energy_LapotronicOrb2.get(1), 288000, new ItemStack[]{
                MatUnifier.get(OrePrefixes.plate, Materials.Neutronium, 16),
                ItemList.Circuit_Wetwaremainframe.get(1),
                ItemList.Circuit_Wetwaremainframe.get(1),
                ItemList.Circuit_Wetwaremainframe.get(1),
                ItemList.Circuit_Wetwaremainframe.get(1),
                ItemList.Energy_LapotronicOrb2.get(8),
                ItemList.Field_Generator_UV.get(2),
                ItemList.Circuit_Wafer_HPIC.get(64),
                ItemList.Circuit_Wafer_HPIC.get(64),
                ItemList.Circuit_Parts_DiodeSMD.get(16),
                MatUnifier.get(OrePrefixes.wireGt01, Materials.Superconductor, 32),
        }, new FluidStack[]{
                Materials.SolderingAlloy.getMolten(2880),
                GregTech_API.mIC2Classic ? Materials.Water.getFluid(16000) : new FluidStack(FluidRegistry.getFluid("ic2coolant"), 16000)
        }, ItemList.ZPM2.get(1), 2000, 300000);

        GT_Values.RA.addAssemblylineRecipe(MatUnifier.get(OrePrefixes.wireGt01, Materials.Superconductor, 1), 144000, new ItemStack[]{
                ItemList.Casing_Fusion_Coil.get(1),
                ItemList.Circuit_Quantummainframe.get(1),
                ItemList.Circuit_Quantummainframe.get(1),
                ItemList.Circuit_Quantummainframe.get(1),
                ItemList.Circuit_Quantummainframe.get(1),
                MatUnifier.get(OrePrefixes.plate, Materials.Plutonium241),
                MatUnifier.get(OrePrefixes.plate, Materials.NetherStar),
                ItemList.Field_Generator_IV.get(2),
                ItemList.Circuit_Wafer_HPIC.get(32),
                MatUnifier.get(OrePrefixes.wireGt01, Materials.Superconductor, 32),
        }, new FluidStack[]{
                Materials.SolderingAlloy.getMolten(2880),
        }, ItemList.FusionComputer_LuV.get(1), 1000, 30000);

        GT_Values.RA.addAssemblylineRecipe(MatUnifier.get(OrePrefixes.block, Materials.Europium, 1), 288000, new ItemStack[]{
                ItemList.Casing_Fusion_Coil.get(1),
                ItemList.Circuit_Crystalmainframe.get(1),
                ItemList.Circuit_Crystalmainframe.get(1),
                ItemList.Circuit_Crystalmainframe.get(1),
                ItemList.Circuit_Crystalmainframe.get(1),
                MatUnifier.get(OrePrefixes.plate, Materials.Europium, 4),
                ItemList.Field_Generator_LuV.get(2),
                ItemList.Circuit_Wafer_HPIC.get(48),
                MatUnifier.get(OrePrefixes.wireGt02, Materials.Superconductor, 32),
        }, new FluidStack[]{
                Materials.SolderingAlloy.getMolten(2880),
        }, ItemList.FusionComputer_ZPMV.get(1), 1000, 60000);

        GT_Values.RA.addAssemblylineRecipe(MatUnifier.get(OrePrefixes.block, Materials.Americium, 1), 432000, new ItemStack[]{
                ItemList.Casing_Fusion_Coil.get(1),
                ItemList.Circuit_Wetwaresupercomputer.get(1),
                ItemList.Circuit_Wetwaresupercomputer.get(1),
                ItemList.Circuit_Wetwaresupercomputer.get(1),
                ItemList.Circuit_Wetwaresupercomputer.get(1),
                MatUnifier.get(OrePrefixes.plate, Materials.Americium, 4),
                ItemList.Field_Generator_ZPM.get(2),
                ItemList.Circuit_Wafer_HPIC.get(64),
                MatUnifier.get(OrePrefixes.wireGt04, Materials.Superconductor, 32),
        }, new FluidStack[]{
                Materials.SolderingAlloy.getMolten(2880),
        }, ItemList.FusionComputer_UV.get(1), 1000, 90000);

        if (GregTech_API.sThaumcraftCompat != null) {
            String tKey = "GT_WOOD_TO_CHARCOAL";
            GT_LanguageManager.addStringLocalization(aTextTCGTPage + tKey, "You have discovered a way of making charcoal magically instead of using regular ovens for this purpose.<BR><BR>To create charcoal from wood you first need an air-free environment, some vacuus essentia is needed for that, then you need to incinerate the wood using ignis essentia and wait until all the water inside the wood is burned away.<BR><BR>This method however doesn't create creosote oil as byproduct.");

            GregTech_API.sThaumcraftCompat.addResearch(tKey, "Charcoal Transmutation", "Turning wood into charcoal", new String[]{"ALUMENTUM"}, "ALCHEMY", MatUnifier.get(OrePrefixes.gem, Materials.Charcoal), 2, 0, 13, 5, Arrays.asList(new Aspects.AspectStack(Aspects.ARBOR, 10L), new Aspects.AspectStack(Aspects.VACUOS, 8L), new Aspects.AspectStack(Aspects.IGNIS, 8L)), null, new Object[]{aTextTCGTPage + tKey, GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, OrePrefixes.log.get(Materials.Wood), MatUnifier.get(OrePrefixes.gem, Materials.Charcoal), Arrays.asList(new Aspects.AspectStack(Aspects.VACUOS, 2L), new Aspects.AspectStack(Aspects.IGNIS, 1L)))});

            tKey = "GT_FILL_WATER_BUCKET";
            GT_LanguageManager.addStringLocalization(aTextTCGTPage + tKey, "You have discovered a way of filling a bucket with aqua essentia in order to simply get water.");
            GregTech_API.sThaumcraftCompat.addResearch(tKey, "Water Transmutation", "Filling buckets with water", null, "ALCHEMY", MatUnifier.get(OrePrefixes.bucket, Materials.Water), 2, 0, 16, 5, Arrays.asList(new Aspects.AspectStack(Aspects.PERMUTATIO, 4L), new Aspects.AspectStack(Aspects.AQUA, 4L)), null, new Object[]{aTextTCGTPage + tKey, GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, MatUnifier.get(OrePrefixes.bucket, Materials.Empty), MatUnifier.get(OrePrefixes.bucket, Materials.Water), Arrays.asList(new Aspects.AspectStack(Aspects.AQUA, 4L))), GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, MatUnifier.get(OrePrefixes.capsule, Materials.Empty), MatUnifier.get(OrePrefixes.capsule, Materials.Water), Arrays.asList(new Aspects.AspectStack(Aspects.AQUA, 4L))), GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, MatUnifier.get(OrePrefixes.cell, Materials.Empty), MatUnifier.get(OrePrefixes.cell, Materials.Water), Arrays.asList(new Aspects.AspectStack(Aspects.AQUA, 4L)))});

            tKey = "GT_TRANSZINC";
            GT_LanguageManager.addStringLocalization(aTextTCGTPage + tKey, "You have discovered a way to multiply zinc by steeping zinc nuggets in metallum harvested from other metals.");
            GregTech_API.sThaumcraftCompat.addResearch(tKey, "Zinc Transmutation", "Transformation of metals into zinc", new String[]{"TRANSTIN"}, "ALCHEMY", MatUnifier.get(OrePrefixes.nugget, Materials.Zinc), 2, 1, 9, 13, Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 5L), new Aspects.AspectStack(Aspects.PERMUTATIO, 3L), new Aspects.AspectStack(Aspects.SANO, 3L)), null, new Object[]{aTextTCGTPage + tKey, GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, OrePrefixes.nugget.get(Materials.Zinc), MatUnifier.get(OrePrefixes.nugget, Materials.Zinc, 3), Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 2L), new Aspects.AspectStack(Aspects.SANO, 1L)))});

            tKey = "GT_TRANSANTIMONY";
            GT_LanguageManager.addStringLocalization(aTextTCGTPage + tKey, "You have discovered a way to multiply antimony by steeping antimony nuggets in metallum harvested from other metals.");
            GregTech_API.sThaumcraftCompat.addResearch(tKey, "Antimony Transmutation", "Transformation of metals into antimony", new String[]{"GT_TRANSZINC", "TRANSLEAD"}, "ALCHEMY", MatUnifier.get(OrePrefixes.nugget, Materials.Antimony), 2, 1, 9, 14, Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 5L), new Aspects.AspectStack(Aspects.PERMUTATIO, 3L), new Aspects.AspectStack(Aspects.AQUA, 3L)), null, new Object[]{aTextTCGTPage + tKey, GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, OrePrefixes.nugget.get(Materials.Antimony), MatUnifier.get(OrePrefixes.nugget, Materials.Antimony, 3), Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 2L), new Aspects.AspectStack(Aspects.AQUA, 1L)))});

            tKey = "GT_TRANSNICKEL";
            GT_LanguageManager.addStringLocalization(aTextTCGTPage + tKey, "You have discovered a way to multiply nickel by steeping nickel nuggets in metallum harvested from other metals.");
            GregTech_API.sThaumcraftCompat.addResearch(tKey, "Nickel Transmutation", "Transformation of metals into nickel", new String[]{"TRANSLEAD"}, "ALCHEMY", MatUnifier.get(OrePrefixes.nugget, Materials.Nickel), 2, 1, 9, 15, Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 5L), new Aspects.AspectStack(Aspects.PERMUTATIO, 3L), new Aspects.AspectStack(Aspects.IGNIS, 3L)), null, new Object[]{aTextTCGTPage + tKey, GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, OrePrefixes.nugget.get(Materials.Nickel), MatUnifier.get(OrePrefixes.nugget, Materials.Nickel, 3), Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 2L), new Aspects.AspectStack(Aspects.IGNIS, 1L)))});

            tKey = "GT_TRANSCOBALT";
            GT_LanguageManager.addStringLocalization(aTextTCGTPage + tKey, "You have discovered a way to multiply cobalt by steeping cobalt nuggets in metallum harvested from other metals.");
            GregTech_API.sThaumcraftCompat.addResearch(tKey, "Cobalt Transmutation", "Transformation of metals into cobalt", new String[]{"GT_TRANSNICKEL"}, "ALCHEMY", MatUnifier.get(OrePrefixes.nugget, Materials.Cobalt), 2, 1, 9, 16, Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 5L), new Aspects.AspectStack(Aspects.PERMUTATIO, 3L), new Aspects.AspectStack(Aspects.INSTRUMENTUM, 3L)), null, new Object[]{aTextTCGTPage + tKey, GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, OrePrefixes.nugget.get(Materials.Cobalt), MatUnifier.get(OrePrefixes.nugget, Materials.Cobalt, 3), Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 2L), new Aspects.AspectStack(Aspects.INSTRUMENTUM, 1L)))});

            tKey = "GT_TRANSBISMUTH";
            GT_LanguageManager.addStringLocalization(aTextTCGTPage + tKey, "You have discovered a way to multiply bismuth by steeping bismuth nuggets in metallum harvested from other metals.");
            GregTech_API.sThaumcraftCompat.addResearch(tKey, "Bismuth Transmutation", "Transformation of metals into bismuth", new String[]{"GT_TRANSCOBALT"}, "ALCHEMY", MatUnifier.get(OrePrefixes.nugget, Materials.Bismuth), 2, 1, 11, 17, Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 5L), new Aspects.AspectStack(Aspects.PERMUTATIO, 3L), new Aspects.AspectStack(Aspects.INSTRUMENTUM, 3L)), null, new Object[]{aTextTCGTPage + tKey, GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, OrePrefixes.nugget.get(Materials.Bismuth), MatUnifier.get(OrePrefixes.nugget, Materials.Bismuth, 3), Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 2L), new Aspects.AspectStack(Aspects.INSTRUMENTUM, 1L)))});

            tKey = "GT_IRON_TO_STEEL";
            GT_LanguageManager.addStringLocalization(aTextTCGTPage + tKey, "You have discovered a way of making Iron harder by just re-ordering its components.<BR><BR>This Method can be used to create a Material called Steel, which is used in many non-Thaumaturgic applications.");
            GregTech_API.sThaumcraftCompat.addResearch(tKey, "Steel Transmutation", "Transforming iron to steel", new String[]{"TRANSIRON", "GT_WOOD_TO_CHARCOAL"}, "ALCHEMY", MatUnifier.get(OrePrefixes.nugget, Materials.Steel), 3, 0, 13, 8, Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 5L), new Aspects.AspectStack(Aspects.PERMUTATIO, 3L), new Aspects.AspectStack(Aspects.ORDO, 3L)), null, new Object[]{aTextTCGTPage + tKey, GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, OrePrefixes.nugget.get(Materials.Iron), MatUnifier.get(OrePrefixes.nugget, Materials.Steel), Arrays.asList(new Aspects.AspectStack(Aspects.ORDO, 1L)))});

            tKey = "GT_TRANSBRONZE";
            GT_LanguageManager.addStringLocalization(aTextTCGTPage + tKey, "You have discovered a way of creating Alloys using the already known transmutations of Copper and Tin.<BR><BR>This Method can be used to create a Bronze directly without having to go through an alloying process.");
            GregTech_API.sThaumcraftCompat.addResearch(tKey, "Bronze Transmutation", "Transformation of metals into bronze", new String[]{"TRANSTIN", "TRANSCOPPER"}, "ALCHEMY", MatUnifier.get(OrePrefixes.nugget, Materials.Bronze), 2, 0, 13, 11, Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 5L), new Aspects.AspectStack(Aspects.PERMUTATIO, 3L), new Aspects.AspectStack(Aspects.INSTRUMENTUM, 3L)), null, new Object[]{aTextTCGTPage + tKey, GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, OrePrefixes.nugget.get(Materials.Bronze), MatUnifier.get(OrePrefixes.nugget, Materials.Bronze, 3), Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 2L), new Aspects.AspectStack(Aspects.INSTRUMENTUM, 1L)))});

            tKey = "GT_TRANSELECTRUM";
            GT_LanguageManager.addStringLocalization(aTextTCGTPage + tKey, "Your discovery of Bronze Transmutation has lead you to the conclusion it works with other Alloys such as Electrum as well.");
            GregTech_API.sThaumcraftCompat.addResearch(tKey, "Electrum Transmutation", "Transformation of metals into electrum", new String[]{"GT_TRANSBRONZE", "TRANSGOLD", "TRANSSILVER"}, "ALCHEMY", MatUnifier.get(OrePrefixes.nugget, Materials.Electrum), 2, 1, 11, 11, Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 5L), new Aspects.AspectStack(Aspects.PERMUTATIO, 3L), new Aspects.AspectStack(Aspects.LUCRUM, 3L)), null, new Object[]{aTextTCGTPage + tKey, GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, OrePrefixes.nugget.get(Materials.Electrum), MatUnifier.get(OrePrefixes.nugget, Materials.Electrum, 3), Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 2L), new Aspects.AspectStack(Aspects.LUCRUM, 1L)))});

            tKey = "GT_TRANSBRASS";
            GT_LanguageManager.addStringLocalization(aTextTCGTPage + tKey, "Your discovery of Bronze Transmutation has lead you to the conclusion it works with other Alloys such as Brass as well.");
            GregTech_API.sThaumcraftCompat.addResearch(tKey, "Brass Transmutation", "Transformation of metals into brass", new String[]{"GT_TRANSBRONZE", "GT_TRANSZINC"}, "ALCHEMY", MatUnifier.get(OrePrefixes.nugget, Materials.Brass), 2, 1, 11, 12, Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 5L), new Aspects.AspectStack(Aspects.PERMUTATIO, 3L), new Aspects.AspectStack(Aspects.INSTRUMENTUM, 3L)), null, new Object[]{aTextTCGTPage + tKey, GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, OrePrefixes.nugget.get(Materials.Brass), MatUnifier.get(OrePrefixes.nugget, Materials.Brass, 3), Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 2L), new Aspects.AspectStack(Aspects.INSTRUMENTUM, 1L)))});

            tKey = "GT_TRANSINVAR";
            GT_LanguageManager.addStringLocalization(aTextTCGTPage + tKey, "Your discovery of Bronze Transmutation has lead you to the conclusion it works with other Alloys such as Invar as well.");
            GregTech_API.sThaumcraftCompat.addResearch(tKey, "Invar Transmutation", "Transformation of metals into invar", new String[]{"GT_TRANSBRONZE", "GT_TRANSNICKEL"}, "ALCHEMY", MatUnifier.get(OrePrefixes.nugget, Materials.Invar), 2, 1, 11, 15, Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 5L), new Aspects.AspectStack(Aspects.PERMUTATIO, 3L), new Aspects.AspectStack(Aspects.GELUM, 3L)), null, new Object[]{aTextTCGTPage + tKey, GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, OrePrefixes.nugget.get(Materials.Invar), MatUnifier.get(OrePrefixes.nugget, Materials.Invar, 3), Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 2L), new Aspects.AspectStack(Aspects.GELUM, 1L)))});

            tKey = "GT_TRANSCUPRONICKEL";
            GT_LanguageManager.addStringLocalization(aTextTCGTPage + tKey, "Your discovery of Bronze Transmutation has lead you to the conclusion it works with other Alloys such as Cupronickel as well.");
            GregTech_API.sThaumcraftCompat.addResearch(tKey, "Cupronickel Transmutation", "Transformation of metals into cupronickel", new String[]{"GT_TRANSBRONZE", "GT_TRANSNICKEL"}, "ALCHEMY", MatUnifier.get(OrePrefixes.nugget, Materials.Cupronickel), 2, 1, 11, 16, Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 5L), new Aspects.AspectStack(Aspects.PERMUTATIO, 3L), new Aspects.AspectStack(Aspects.PERMUTATIO, 3L), new Aspects.AspectStack(Aspects.IGNIS, 3L)), null, new Object[]{aTextTCGTPage + tKey, GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, OrePrefixes.nugget.get(Materials.Cupronickel), MatUnifier.get(OrePrefixes.nugget, Materials.Cupronickel, 3), Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 2L), new Aspects.AspectStack(Aspects.PERMUTATIO, 1L), new Aspects.AspectStack(Aspects.IGNIS, 1L)))});

            tKey = "GT_TRANSBATTERYALLOY";
            GT_LanguageManager.addStringLocalization(aTextTCGTPage + tKey, "Your discovery of Bronze Transmutation has lead you to the conclusion it works with other Alloys such as Battery Alloy as well.");
            GregTech_API.sThaumcraftCompat.addResearch(tKey, "Battery Alloy Transmutation", "Transformation of metals into battery alloy", new String[]{"GT_TRANSBRONZE", "GT_TRANSANTIMONY"}, "ALCHEMY", MatUnifier.get(OrePrefixes.nugget, Materials.BatteryAlloy), 2, 1, 11, 13, Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 5L), new Aspects.AspectStack(Aspects.PERMUTATIO, 3L), new Aspects.AspectStack(Aspects.PERMUTATIO, 3L), new Aspects.AspectStack(Aspects.IGNIS, 3L)), null, new Object[]{aTextTCGTPage + tKey, GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, OrePrefixes.nugget.get(Materials.BatteryAlloy), MatUnifier.get(OrePrefixes.nugget, Materials.BatteryAlloy, 3), Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 2L), new Aspects.AspectStack(Aspects.AQUA, 1L), new Aspects.AspectStack(Aspects.ORDO, 1L)))});

            tKey = "GT_TRANSSOLDERINGALLOY";
            GT_LanguageManager.addStringLocalization(aTextTCGTPage + tKey, "Your discovery of Bronze Transmutation has lead you to the conclusion it works with other Alloys such as Soldering Alloy as well.");
            GregTech_API.sThaumcraftCompat.addResearch(tKey, "Soldering Alloy Transmutation", "Transformation of metals into soldering alloy", new String[]{"GT_TRANSBRONZE", "GT_TRANSANTIMONY"}, "ALCHEMY", MatUnifier.get(OrePrefixes.nugget, Materials.SolderingAlloy), 2, 1, 11, 14, Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 5L), new Aspects.AspectStack(Aspects.PERMUTATIO, 3L), new Aspects.AspectStack(Aspects.PERMUTATIO, 3L), new Aspects.AspectStack(Aspects.IGNIS, 3L)), null, new Object[]{aTextTCGTPage + tKey, GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, OrePrefixes.nugget.get(Materials.SolderingAlloy), MatUnifier.get(OrePrefixes.nugget, Materials.SolderingAlloy, 3), Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 2L), new Aspects.AspectStack(Aspects.AQUA, 1L), new Aspects.AspectStack(Aspects.VITREUS, 1L)))});

            tKey = "GT_ADVANCEDMETALLURGY";
            GT_LanguageManager.addStringLocalization(aTextTCGTPage + tKey, "Now that you have discovered all the basic metals, you can finally move on to the next Level of magic metallurgy and create more advanced metals");
            GregTech_API.sThaumcraftCompat.addResearch(tKey, "Advanced Metallurgic Transmutation", "Mastering the basic metals", new String[]{"GT_TRANSBISMUTH", "GT_IRON_TO_STEEL", "GT_TRANSSOLDERINGALLOY", "GT_TRANSBATTERYALLOY", "GT_TRANSBRASS", "GT_TRANSELECTRUM", "GT_TRANSCUPRONICKEL", "GT_TRANSINVAR"}, "ALCHEMY", MatUnifier.get(OrePrefixes.ingot, Materials.Iron), 3, 0, 16, 14, Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 50L), new Aspects.AspectStack(Aspects.PERMUTATIO, 20L), new Aspects.AspectStack(Aspects.COGNITIO, 20L), new Aspects.AspectStack(Aspects.PRAECANTATIO, 20L), new Aspects.AspectStack(Aspects.NEBRISUM, 20L), new Aspects.AspectStack(Aspects.MAGNETO, 20L)), null, new Object[]{aTextTCGTPage + tKey});

            tKey = "GT_TRANSALUMINIUM";
            GT_LanguageManager.addStringLocalization(aTextTCGTPage + tKey, "You have discovered a way to multiply aluminium by steeping aluminium nuggets in metallum harvested from other metals.<BR><BR>This transmutation is slightly harder to achieve, because aluminium has special properties, which require more order to achieve the desired result.");
            GregTech_API.sThaumcraftCompat.addResearch(tKey, "Aluminium Transmutation", "Transformation of metals into aluminium", new String[]{"GT_ADVANCEDMETALLURGY"}, "ALCHEMY", MatUnifier.get(OrePrefixes.nugget, Materials.Aluminium), 4, 0, 19, 14, Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 5L), new Aspects.AspectStack(Aspects.PERMUTATIO, 3L), new Aspects.AspectStack(Aspects.VOLATUS, 3L), new Aspects.AspectStack(Aspects.ORDO, 3L), new Aspects.AspectStack(Aspects.IGNIS, 3L)), null, new Object[]{aTextTCGTPage + tKey, GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, OrePrefixes.nugget.get(Materials.Aluminium), MatUnifier.get(OrePrefixes.nugget, Materials.Aluminium, 3), Arrays.asList(new Aspects.AspectStack(Aspects.METALLUM, 2L), new Aspects.AspectStack(Aspects.VOLATUS, 1L), new Aspects.AspectStack(Aspects.ORDO, 1L), new Aspects.AspectStack(Aspects.IGNIS, 1L)))});

            tKey = "GT_CRYSTALLISATION";
            GT_LanguageManager.addStringLocalization(aTextTCGTPage + tKey, "Sometimes when processing your Crystal Shards they become a pile of Dust instead of the mostly required Shard.<BR><BR>You have finally found a way to reverse this Process by using Vitreus Essentia for recrystallising the Shards.");
            GregTech_API.sThaumcraftCompat.addResearch(tKey, "Shard Recrystallisation", "Fixing your precious crystals", new String[]{"ALCHEMICALMANUFACTURE"}, "ALCHEMY", MatUnifier.get(OrePrefixes.gem, Materials.InfusedOrder), 3, 0, -11, -3, Arrays.asList(new Aspects.AspectStack(Aspects.VITREUS, 5L), new Aspects.AspectStack(Aspects.PERMUTATIO, 3L), new Aspects.AspectStack(Aspects.ORDO, 3L)), null, new Object[]{aTextTCGTPage + tKey, GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, OrePrefixes.dust.get(Materials.Amber), MatUnifier.get(OrePrefixes.gem, Materials.Amber), Arrays.asList(new Aspects.AspectStack(Aspects.VITREUS, 4L))), GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, OrePrefixes.dust.get(Materials.InfusedOrder), MatUnifier.get(OrePrefixes.gem, Materials.InfusedOrder), Arrays.asList(new Aspects.AspectStack(Aspects.VITREUS, 4L))), GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, OrePrefixes.dust.get(Materials.InfusedEntropy), MatUnifier.get(OrePrefixes.gem, Materials.InfusedEntropy), Arrays.asList(new Aspects.AspectStack(Aspects.VITREUS, 4L))), GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, OrePrefixes.dust.get(Materials.InfusedAir), MatUnifier.get(OrePrefixes.gem, Materials.InfusedAir), Arrays.asList(new Aspects.AspectStack(Aspects.VITREUS, 4L))), GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, OrePrefixes.dust.get(Materials.InfusedEarth), MatUnifier.get(OrePrefixes.gem, Materials.InfusedEarth), Arrays.asList(new Aspects.AspectStack(Aspects.VITREUS, 4L))), GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, OrePrefixes.dust.get(Materials.InfusedFire), MatUnifier.get(OrePrefixes.gem, Materials.InfusedFire), Arrays.asList(new Aspects.AspectStack(Aspects.VITREUS, 4L))), GregTech_API.sThaumcraftCompat.addCrucibleRecipe(tKey, OrePrefixes.dust.get(Materials.InfusedWater), MatUnifier.get(OrePrefixes.gem, Materials.InfusedWater), Arrays.asList(new Aspects.AspectStack(Aspects.VITREUS, 4L)))});

            tKey = "GT_MAGICENERGY";
            GT_LanguageManager.addStringLocalization(aTextTCGTPage + tKey,
                    "While trying to find new ways to integrate magic into your industrial factories, you have discovered a way to convert magical energy into electrical power.");
            GregTech_API.sThaumcraftCompat.addResearch(tKey,
                    "Magic Energy Conversion",
                    "Magic to Power",
                    new String[]{"ARCANEBORE"},
                    "ARTIFICE",
                    ItemList.MagicEnergyConverter_LV.get(1),
                    3, 0, -3, 10,
                    Arrays.asList(new Aspects.AspectStack(Aspects.MACHINA, 10L),
                            new Aspects.AspectStack(Aspects.COGNITIO, 10L),
                            new Aspects.AspectStack(Aspects.POTENTIA, 20L),
                            new Aspects.AspectStack(Aspects.ELECTRUM, 10L)),
                    null, new Object[]{aTextTCGTPage + tKey,
                            GregTech_API.sThaumcraftCompat.addInfusionRecipe(tKey,
                                    ItemList.Hull_LV.get(1),
                                    new ItemStack[]{
                                            new ItemStack(Blocks.beacon),
                                            MatUnifier.get(OrePrefixes.circuit, Materials.Advanced),
                                            MatUnifier.get(OrePrefixes.plate, Materials.Thaumium),
                                            ItemList.Sensor_MV.get(2),
                                            MatUnifier.get(OrePrefixes.circuit, Materials.Advanced),
                                            MatUnifier.get(OrePrefixes.plate, Materials.Thaumium),
                                            ItemList.Sensor_MV.get(2)
                                    },
                                    ItemList.MagicEnergyConverter_LV.get(1),
                                    5,
                                    Arrays.asList(new Aspects.AspectStack(Aspects.POTENTIA, 32L),
                                            new Aspects.AspectStack(Aspects.ELECTRUM, 16L),
                                            new Aspects.AspectStack(Aspects.MACHINA, 32L)))});

            tKey = "GT_MAGICENERGY2";
            GT_LanguageManager.addStringLocalization(aTextTCGTPage + tKey,
                    "Attempts to increase the output of your Magic Energy generators have resulted in significant improvements.");
            GregTech_API.sThaumcraftCompat.addResearch(tKey,
                    "Adept Magic Energy Conversion",
                    "Magic to Power",
                    new String[]{"GT_MAGICENERGY"},
                    "ARTIFICE",
                    ItemList.MagicEnergyConverter_MV.get(1),
                    1, 1, -4, 12,
                    Arrays.asList(new Aspects.AspectStack(Aspects.MACHINA, 10L),
                            new Aspects.AspectStack(Aspects.COGNITIO, 10L),
                            new Aspects.AspectStack(Aspects.POTENTIA, 20L),
                            new Aspects.AspectStack(Aspects.ELECTRUM, 10L)),
                    null, new Object[]{aTextTCGTPage + tKey,
                            GregTech_API.sThaumcraftCompat.addInfusionRecipe(tKey,
                                    ItemList.Hull_MV.get(1),
                                    new ItemStack[]{
                                            new ItemStack(Blocks.beacon),
                                            MatUnifier.get(OrePrefixes.circuit, Materials.Data),
                                            MatUnifier.get(OrePrefixes.plate, Materials.Thaumium),
                                            ItemList.Sensor_HV.get(2),
                                            MatUnifier.get(OrePrefixes.circuit, Materials.Data),
                                            MatUnifier.get(OrePrefixes.plate, Materials.Iridium),
                                            ItemList.Sensor_HV.get(2)
                                    },
                                    ItemList.MagicEnergyConverter_MV.get(1),
                                    6,
                                    Arrays.asList(new Aspects.AspectStack(Aspects.POTENTIA, 64L),
                                            new Aspects.AspectStack(Aspects.ELECTRUM, 32L),
                                            new Aspects.AspectStack(Aspects.MACHINA, 64L)))});

            tKey = "GT_MAGICENERGY3";
            GT_LanguageManager.addStringLocalization(aTextTCGTPage + tKey,
                    "Attempts to further increase the output of your Magic Energy generators have resulted in great improvements.");
            GregTech_API.sThaumcraftCompat.addResearch(tKey,
                    "Master Magic Energy Conversion",
                    "Magic to Power",
                    new String[]{"GT_MAGICENERGY2"},
                    "ARTIFICE",
                    ItemList.MagicEnergyConverter_HV.get(1),
                    1, 1, -4, 14,
                    Arrays.asList(new Aspects.AspectStack(Aspects.MACHINA, 20L),
                            new Aspects.AspectStack(Aspects.COGNITIO, 20L),
                            new Aspects.AspectStack(Aspects.POTENTIA, 40L),
                            new Aspects.AspectStack(Aspects.ELECTRUM, 20L)),
                    null, new Object[]{aTextTCGTPage + tKey,
                            GregTech_API.sThaumcraftCompat.addInfusionRecipe(tKey,
                                    ItemList.Hull_HV.get(1),
                                    new ItemStack[]{
                                            new ItemStack(Blocks.beacon),
                                            MatUnifier.get(OrePrefixes.circuit, Materials.Elite),
                                            MatUnifier.get(OrePrefixes.plate, Materials.Thaumium),
                                            ItemList.Field_Generator_MV.get(1),
                                            MatUnifier.get(OrePrefixes.circuit, Materials.Elite),
                                            MatUnifier.get(OrePrefixes.plate, Materials.Osmium),
                                            ItemList.Field_Generator_MV.get(1)
                                    },
                                    ItemList.MagicEnergyConverter_HV.get(1),
                                    8,
                                    Arrays.asList(new Aspects.AspectStack(Aspects.POTENTIA, 128L),
                                            new Aspects.AspectStack(Aspects.ELECTRUM, 64L),
                                            new Aspects.AspectStack(Aspects.MACHINA, 128L)))});


            tKey = "GT_MAGICABSORB";
            GT_LanguageManager.addStringLocalization(aTextTCGTPage + tKey,
                    "Research into magical energy conversion methods has identified a way to convert surrounding energies into electrical power.");
            GregTech_API.sThaumcraftCompat.addResearch(tKey,
                    "Magic Energy Absorption",
                    "Harvesting Magic",
                    new String[]{"GT_MAGICENERGY"},
                    "ARTIFICE",
                    ItemList.MagicEnergyAbsorber_LV.get(1),
                    3, 0, -2, 12,
                    Arrays.asList(new Aspects.AspectStack(Aspects.MACHINA, 10L),
                            new Aspects.AspectStack(Aspects.COGNITIO, 10L),
                            new Aspects.AspectStack(Aspects.POTENTIA, 20L),
                            new Aspects.AspectStack(Aspects.ELECTRUM, 10L)),
                    null, new Object[]{aTextTCGTPage + tKey,
                            GregTech_API.sThaumcraftCompat.addInfusionRecipe(tKey,
                                    ItemList.Hull_LV.get(1),
                                    new ItemStack[]{
                                            ItemList.MagicEnergyConverter_LV.get(1),
                                            MatUnifier.get(OrePrefixes.circuit, Materials.Advanced, 1),
                                            MatUnifier.get(OrePrefixes.plate, Materials.Thaumium, 1),
                                            ItemList.Sensor_MV.get(2)
                                    },
                                    ItemList.MagicEnergyAbsorber_LV.get(1),
                                    6,
                                    Arrays.asList(new Aspects.AspectStack(Aspects.POTENTIA, 32L),
                                            new Aspects.AspectStack(Aspects.ELECTRUM, 16L),
                                            new Aspects.AspectStack(Aspects.MACHINA, 32L),
                                            new Aspects.AspectStack(Aspects.VACUOS, 16L),
                                            new Aspects.AspectStack(Aspects.INSTRUMENTUM, 32L),
                                            new Aspects.AspectStack(Aspects.STRONTIO, 4L)))});

            tKey = "GT_MAGICABSORB2";
            GT_LanguageManager.addStringLocalization(aTextTCGTPage + tKey,
                    "Moar output! Drain all the Magic!");
            GregTech_API.sThaumcraftCompat.addResearch(tKey,
                    "Improved Magic Energy Absorption",
                    "Harvesting Magic",
                    new String[]{"GT_MAGICABSORB"},
                    "ARTIFICE",
                    ItemList.MagicEnergyAbsorber_EV.get(1),
                    3, 1, -2, 14,
                    Arrays.asList(new Aspects.AspectStack(Aspects.MACHINA, 10L),
                            new Aspects.AspectStack(Aspects.COGNITIO, 10L),
                            new Aspects.AspectStack(Aspects.POTENTIA, 20L),
                            new Aspects.AspectStack(Aspects.ELECTRUM, 10L)),
                    null, new Object[]{aTextTCGTPage + tKey,
                            GregTech_API.sThaumcraftCompat.addInfusionRecipe(tKey,
                                    ItemList.Hull_MV.get(1),
                                    new ItemStack[]{
                                            ItemList.MagicEnergyConverter_MV.get(1),
                                            MatUnifier.get(OrePrefixes.circuit, Materials.Advanced),
                                            MatUnifier.get(OrePrefixes.plate, Materials.Thaumium),
                                            ItemList.Sensor_HV.get(2),
                                            MatUnifier.get(OrePrefixes.circuit, Materials.Advanced),
                                            MatUnifier.get(OrePrefixes.plate, Materials.Thaumium)
                                    },
                                    ItemList.MagicEnergyAbsorber_MV.get(1),
                                    6,
                                    Arrays.asList(new Aspects.AspectStack(Aspects.POTENTIA, 64L),
                                            new Aspects.AspectStack(Aspects.ELECTRUM, 32L),
                                            new Aspects.AspectStack(Aspects.MACHINA, 64L),
                                            new Aspects.AspectStack(Aspects.VACUOS, 32L),
                                            new Aspects.AspectStack(Aspects.INSTRUMENTUM, 64L),
                                            new Aspects.AspectStack(Aspects.STRONTIO, 8L)))


                            , GregTech_API.sThaumcraftCompat.addInfusionRecipe(tKey,
                            ItemList.Hull_HV.get(1),
                            new ItemStack[]{
                                    ItemList.MagicEnergyConverter_MV.get(1),
                                    MatUnifier.get(OrePrefixes.circuit, Materials.Elite),
                                    GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 1, 16),
                                    ItemList.Field_Generator_MV.get(1),
                                    MatUnifier.get(OrePrefixes.circuit, Materials.Elite),
                                    GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 1, 16)
                            },
                            ItemList.MagicEnergyAbsorber_HV.get(1),
                            8,
                            Arrays.asList(new Aspects.AspectStack(Aspects.POTENTIA, 128L),
                                    new Aspects.AspectStack(Aspects.ELECTRUM, 64L),
                                    new Aspects.AspectStack(Aspects.MACHINA, 128L),
                                    new Aspects.AspectStack(Aspects.VACUOS, 64L),
                                    new Aspects.AspectStack(Aspects.INSTRUMENTUM, 128L),
                                    new Aspects.AspectStack(Aspects.STRONTIO, 16L)))


                            , GregTech_API.sThaumcraftCompat.addInfusionRecipe(tKey,
                            ItemList.Hull_EV.get(1),
                            new ItemStack[]{
                                    ItemList.MagicEnergyConverter_HV.get(1),
                                    MatUnifier.get(OrePrefixes.circuit, Materials.Master),
                                    GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 1, 16),
                                    ItemList.Field_Generator_HV.get(1),
                                    MatUnifier.get(OrePrefixes.circuit, Materials.Master),
                                    GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 1, 16)
                            },
                            ItemList.MagicEnergyAbsorber_EV.get(1),
                            10,
                            Arrays.asList(new Aspects.AspectStack(Aspects.POTENTIA, 256L),
                                    new Aspects.AspectStack(Aspects.ELECTRUM, 128L),
                                    new Aspects.AspectStack(Aspects.MACHINA, 256L),
                                    new Aspects.AspectStack(Aspects.VACUOS, 128L),
                                    new Aspects.AspectStack(Aspects.INSTRUMENTUM, 256L),
                                    new Aspects.AspectStack(Aspects.STRONTIO, 64L)))
                    });
        }

    }

    /**
     * Adds recipes related to the processing of Charcoal Byproducts, Fermented Biomass
     * Adds recipes related to the production of Glue, Gunpowder, Polyvinyl Chloride
     * Adds replacement recipes for Epoxy Resin, Nitric Acid, Polyethylene, Polydimethylsiloxane (Silicone), Polytetrafluoroethylene, Rocket Fuel, Sulfuric Acid
     * Instrumental materials are not mentioned here.
     */
    private void addRecipesApril2017ChemistryUpdate() {
        GT_Values.RA.addElectrolyzerRecipe(Materials.Salt.getDust(2), GT_Values.NI, GT_Values.NF, Materials.Chlorine.getGas(1000), Materials.Sodium.getDust(1), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 720, 30);
        GT_Values.RA.addElectrolyzerRecipe(Materials.Empty.getCells(1), GT_Values.NI, Materials.SaltWater.getFluid(1000), Materials.Chlorine.getGas(1000), Materials.SodiumHydroxide.getDust(1), Materials.Hydrogen.getCells(1), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 720, 30);

        GT_Values.RA.addChemicalRecipe(Materials.Potassium.getDust(1), Materials.Oxygen.getCells(3), Materials.Nitrogen.getGas(1000), GT_Values.NF, Materials.Saltpeter.getDust(1), Materials.Empty.getCells(3), 180);
        GT_Values.RA.addChemicalRecipe(Materials.Potassium.getDust(1), Materials.Nitrogen.getCells(1), Materials.Oxygen.getGas(3000), GT_Values.NF, Materials.Saltpeter.getDust(1), Materials.Empty.getCells(1), 180);

        GT_Values.RA.addMixerRecipe(Materials.Salt.getDust(2), GT_Values.NI, GT_Values.NI, GT_Values.NI, Materials.Water.getFluid(1000), Materials.SaltWater.getFluid(1000), GT_Values.NI, 40, 8);
        GT_Values.RA.addDistilleryRecipe(1, Materials.SaltWater.getFluid(1000), GT_ModHandler.getDistilledWater(1000), Materials.Salt.getDust(2), 3200, 16, false);

        GT_Values.RA.addUniversalDistillationRecipe(FluidRegistry.getFluidStack("potion.vinegar", 40), new FluidStack[]{Materials.AceticAcid.getFluid(5), Materials.Water.getFluid(35)}, GT_Values.NI, 20, 64);
        GT_Values.RA.addMixerRecipe(Materials.Calcite.getDust(1), GT_Values.NI, GT_Values.NI, GT_Values.NI, Materials.AceticAcid.getFluid(2000), GT_Values.NF, MatUnifier.get(OrePrefixes.dust, Materials.CalciumAcetate, 3), 240, 16);
        GT_Values.RA.addMixerRecipe(Materials.Calcium.getDust(1), GT_Values.NI, GT_Values.NI, GT_Values.NI, Materials.AceticAcid.getFluid(2000), GT_Values.NF, MatUnifier.get(OrePrefixes.dust, Materials.CalciumAcetate, 3), 80, 16);
        GameRegistry.addSmelting(Materials.CalciumAcetate.getCells(1), Materials.Acetone.getCells(1), 0);

        GT_Values.RA.addChemicalRecipe(Materials.AceticAcid.getCells(1), GT_Values.NI, Materials.Methanol.getFluid(1000), Materials.Water.getFluid(1000), Materials.MethylAcetate.getCells(1), 240);
        GT_Values.RA.addChemicalRecipe(Materials.Methanol.getCells(1), GT_Values.NI, Materials.AceticAcid.getFluid(1000), Materials.MethylAcetate.getFluid(1000), Materials.Water.getCells(1), 240);

        GT_Values.RA.addMixerRecipe(Materials.Acetone.getCells(3), GT_Values.NI, GT_Values.NI, GT_Values.NI, Materials.PolyvinylAcetate.getFluid(2000), Materials.Glue.getFluid(5000), Materials.Empty.getCells(3), 100, 8);
        GT_Values.RA.addMixerRecipe(Materials.PolyvinylAcetate.getCells(2), GT_Values.NI, GT_Values.NI, GT_Values.NI, Materials.Acetone.getFluid(3000), Materials.Glue.getFluid(5000), Materials.Empty.getCells(2), 100, 8);
        GT_Values.RA.addMixerRecipe(Materials.MethylAcetate.getCells(3), GT_Values.NI, GT_Values.NI, GT_Values.NI, Materials.PolyvinylAcetate.getFluid(2000), Materials.Glue.getFluid(5000), Materials.Empty.getCells(3), 100, 8);
        GT_Values.RA.addMixerRecipe(Materials.PolyvinylAcetate.getCells(2), GT_Values.NI, GT_Values.NI, GT_Values.NI, Materials.MethylAcetate.getFluid(3000), Materials.Glue.getFluid(5000), Materials.Empty.getCells(2), 100, 8);

        GT_Values.RA.addChemicalRecipe(Materials.Carbon.getDust(1), GT_Utility.getIntegratedCircuit(1), Materials.Oxygen.getGas(1000), Materials.CarbonMonoxide.getGas(1000), GT_Values.NI, 40, 8);
        GT_Values.RA.addChemicalRecipe(Materials.Coal.getGems(1), GT_Utility.getIntegratedCircuit(1), Materials.Oxygen.getGas(1000), Materials.CarbonMonoxide.getGas(1000), Materials.DarkAsh.getDustTiny(1), 80, 8);
        GT_Values.RA.addChemicalRecipe(Materials.Coal.getDust(1), GT_Utility.getIntegratedCircuit(1), Materials.Oxygen.getGas(1000), Materials.CarbonMonoxide.getGas(1000), Materials.DarkAsh.getDustTiny(1), 80, 8);
        GT_Values.RA.addChemicalRecipe(Materials.Charcoal.getGems(1), GT_Utility.getIntegratedCircuit(1), Materials.Oxygen.getGas(1000), Materials.CarbonMonoxide.getGas(1000), Materials.DarkAsh.getDustTiny(1), 80, 8);
        GT_Values.RA.addChemicalRecipe(Materials.Charcoal.getDust(1), GT_Utility.getIntegratedCircuit(1), Materials.Oxygen.getGas(1000), Materials.CarbonMonoxide.getGas(1000), Materials.DarkAsh.getDustTiny(1), 80, 8);
        GT_Values.RA.addChemicalRecipe(Materials.Carbon.getDust(1), GT_Utility.getIntegratedCircuit(2), Materials.Oxygen.getGas(2000), Materials.CarbonDioxide.getGas(1000), GT_Values.NI, 40, 8);
        GT_Values.RA.addChemicalRecipe(Materials.Coal.getGems(1), GT_Utility.getIntegratedCircuit(2), Materials.Oxygen.getGas(2000), Materials.CarbonDioxide.getGas(1000), Materials.DarkAsh.getDustTiny(1), 40, 8);
        GT_Values.RA.addChemicalRecipe(Materials.Coal.getDust(1), GT_Utility.getIntegratedCircuit(2), Materials.Oxygen.getGas(2000), Materials.CarbonDioxide.getGas(1000), Materials.DarkAsh.getDustTiny(1), 40, 8);
        GT_Values.RA.addChemicalRecipe(Materials.Charcoal.getGems(1), GT_Utility.getIntegratedCircuit(2), Materials.Oxygen.getGas(2000), Materials.CarbonDioxide.getGas(1000), Materials.DarkAsh.getDustTiny(1), 40, 8);
        GT_Values.RA.addChemicalRecipe(Materials.Charcoal.getDust(1), GT_Utility.getIntegratedCircuit(2), Materials.Oxygen.getGas(2000), Materials.CarbonDioxide.getGas(1000), Materials.DarkAsh.getDustTiny(1), 40, 8);
        GT_Values.RA.addChemicalRecipe(Materials.Carbon.getDust(1), GT_Values.NI, Materials.CarbonDioxide.getGas(1000), Materials.CarbonMonoxide.getGas(2000), GT_Values.NI, 800);

        GT_Values.RA.addChemicalRecipe(Materials.CarbonMonoxide.getCells(1), GT_Values.NI, Materials.Hydrogen.getGas(4000), Materials.Methanol.getFluid(1000), Materials.Empty.getCells(1), 120, 96);
        GT_Values.RA.addChemicalRecipe(Materials.Hydrogen.getCells(4), GT_Values.NI, Materials.CarbonMonoxide.getGas(1000), Materials.Methanol.getFluid(1000), Materials.Empty.getCells(4), 120, 96);

        GT_Values.RA.addChemicalRecipe(Materials.Methanol.getCells(1), GT_Values.NI, Materials.CarbonMonoxide.getGas(1000), Materials.AceticAcid.getFluid(1000), Materials.Empty.getCells(1), 300);
        GT_Values.RA.addChemicalRecipe(Materials.CarbonMonoxide.getCells(1), GT_Values.NI, Materials.Methanol.getFluid(1000), Materials.AceticAcid.getFluid(1000), Materials.Empty.getCells(1), 300);
        GT_Values.RA.addChemicalRecipe(Materials.Ethylene.getCells(1), GT_Utility.getIntegratedCircuit(10), Materials.Oxygen.getGas(2000), Materials.AceticAcid.getFluid(1000), Materials.Empty.getCells(1), 100);
        GT_Values.RA.addChemicalRecipe(Materials.Oxygen.getCells(2), GT_Utility.getIntegratedCircuit(10), Materials.Ethylene.getGas(1000), Materials.AceticAcid.getFluid(1000), Materials.Empty.getCells(2), 100);
        GT_Values.RA.addChemicalRecipeForBasicMachineOnly(Materials.Oxygen.getCells(2), Materials.Ethylene.getCells(1), GT_Values.NF, Materials.AceticAcid.getFluid(1000), Materials.Empty.getCells(3), GT_Values.NI, 100, 30);

        GT_Values.RA.addFermentingRecipe(Materials.Biomass.getFluid(100), Materials.FermentedBiomass.getFluid(100), 150, false);
        GT_Values.RA.addFermentingRecipe(new FluidStack(FluidRegistry.getFluid("ic2biomass"), 100), Materials.FermentedBiomass.getFluid(100), 150, false);

        GT_Values.RA.addDistillationTowerRecipe(Materials.FermentedBiomass.getFluid(1000), new FluidStack[]{
                Materials.AceticAcid.getFluid(25), Materials.Water.getFluid(375), Materials.Ethanol.getFluid(150),
                Materials.Methanol.getFluid(150), Materials.Ammonia.getGas(50), Materials.CarbonDioxide.getGas(400),
                Materials.Methane.getGas(600)}, ItemList.IC2_Fertilizer.get(1), 75, 360);
        GT_Values.RA.addDistilleryRecipe(1, Materials.FermentedBiomass.getFluid(1000), Materials.AceticAcid.getFluid(25), ItemList.IC2_Fertilizer.get(1), 1500, 8, false);
        GT_Values.RA.addDistilleryRecipe(2, Materials.FermentedBiomass.getFluid(1000), Materials.Water.getFluid(375), ItemList.IC2_Fertilizer.get(1), 1500, 8, false);
        GT_Values.RA.addDistilleryRecipe(3, Materials.FermentedBiomass.getFluid(1000), Materials.Ethanol.getFluid(150), ItemList.IC2_Fertilizer.get(1), 1500, 8, false);
        GT_Values.RA.addDistilleryRecipe(4, Materials.FermentedBiomass.getFluid(1000), Materials.Methanol.getFluid(150), ItemList.IC2_Fertilizer.get(1), 1500, 8, false);
        GT_Values.RA.addDistilleryRecipe(5, Materials.FermentedBiomass.getFluid(1000), Materials.Ammonia.getGas(50), ItemList.IC2_Fertilizer.get(1), 1500, 8, false);
        GT_Values.RA.addDistilleryRecipe(6, Materials.FermentedBiomass.getFluid(1000), Materials.CarbonDioxide.getGas(400), ItemList.IC2_Fertilizer.get(1), 1500, 8, false);
        GT_Values.RA.addDistilleryRecipe(7, Materials.FermentedBiomass.getFluid(1000), Materials.Methane.getGas(600), ItemList.IC2_Fertilizer.get(1), 1500, 8, false);

        GT_Values.RA.addDistilleryRecipe(17, Materials.FermentedBiomass.getFluid(1000), new FluidStack(FluidRegistry.getFluid("ic2biogas"), 1800), ItemList.IC2_Fertilizer.get(1), 1600, 8, false);
        GT_Values.RA.addDistilleryRecipe(1, Materials.Methane.getGas(1000), new FluidStack(FluidRegistry.getFluid("ic2biogas"), 3000), GT_Values.NI, 160, 8, false);

        GT_Values.RA.addPyrolyseRecipe(Materials.Sugar.getDust(23), GT_Values.NF, 1, Materials.Charcoal.getDust(12), Materials.Water.getFluid(1500), 320, 64);
        GT_Values.RA.addPyrolyseRecipe(Materials.Sugar.getDust(23), Materials.Nitrogen.getGas(500), 2, Materials.Charcoal.getDust(12), Materials.Water.getFluid(1500), 160, 96);

        GT_Values.RA.addUniversalDistillationRecipe(Materials.CharcoalByproducts.getGas(1000),
                new FluidStack[]{Materials.WoodTar.getFluid(250), Materials.WoodVinegar.getFluid(500), Materials.WoodGas.getGas(250)},
                Materials.Charcoal.getDustSmall(1), 200, 64);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.WoodGas.getGas(1000),
                new FluidStack[]{Materials.CarbonDioxide.getGas(490), Materials.Ethylene.getGas(20), Materials.Methane.getGas(130), Materials.CarbonMonoxide.getGas(340), Materials.Hydrogen.getGas(20)},
                GT_Values.NI, 200, 64);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.WoodVinegar.getFluid(1000),
                new FluidStack[]{Materials.AceticAcid.getFluid(100), Materials.Water.getFluid(500), Materials.Ethanol.getFluid(10), Materials.Methanol.getFluid(300), Materials.Acetone.getFluid(50), Materials.MethylAcetate.getFluid(10)},
                GT_Values.NI, 200, 64);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.WoodTar.getFluid(1000),
                new FluidStack[]{Materials.Creosote.getFluid(500), Materials.Phenol.getFluid(75), Materials.Benzene.getFluid(350), Materials.Toluene.getFluid(75)},
                GT_Values.NI, 200, 64);

        GT_Values.RA.addChemicalRecipe(Materials.Ethylene.getCells(1), Materials.AceticAcid.getCells(1), Materials.Oxygen.getGas(1000), Materials.VinylAcetate.getFluid(1000), Materials.Water.getCells(1), Materials.Empty.getCells(1), 180);
        GT_Values.RA.addChemicalRecipe(Materials.AceticAcid.getCells(1), Materials.Oxygen.getCells(1), Materials.Ethylene.getGas(1000), Materials.VinylAcetate.getFluid(1000), Materials.Water.getCells(1), Materials.Empty.getCells(1), 180);
        GT_Values.RA.addChemicalRecipe(Materials.Oxygen.getCells(1), Materials.Ethylene.getCells(1), Materials.AceticAcid.getFluid(1000), Materials.VinylAcetate.getFluid(1000), Materials.Water.getCells(1), Materials.Empty.getCells(1), 180);

        GT_Values.RA.addDefaultPolymerizationRecipes(Materials.VinylAcetate.mFluid, Materials.PolyvinylAcetate.mFluid);

        GT_Values.RA.addChemicalRecipe(Materials.Ethanol.getCells(1), GT_Values.NI, Materials.SulfuricAcid.getFluid(1000), Materials.Ethylene.getGas(1000), Materials.DilutedSulfuricAcid.getCells(1), 1200, 120);

        GT_Values.RA.addDefaultPolymerizationRecipes(Materials.Ethylene.mGas, Materials.Plastic.mStandardMoltenFluid);

        GT_Values.RA.addChemicalRecipe(Materials.Sodium.getDust(1), GT_Values.NI, Materials.Water.getFluid(1000), Materials.Hydrogen.getGas(1000), Materials.SodiumHydroxide.getDust(1), 40, 8);

        GT_Values.RA.addChemicalRecipe(Materials.Chlorine.getCells(1), GT_Utility.getIntegratedCircuit(1), Materials.Hydrogen.getGas(1000), Materials.HydrochloricAcid.getFluid(1000), Materials.Empty.getCells(1), 60, 8);
        GT_Values.RA.addChemicalRecipe(Materials.Hydrogen.getCells(1), GT_Utility.getIntegratedCircuit(1), Materials.Chlorine.getGas(1000), Materials.HydrochloricAcid.getFluid(1000), Materials.Empty.getCells(1), 60, 8);

        GT_Values.RA.addChemicalRecipe(Materials.Chlorine.getCells(2), GT_Values.NI, Materials.Propene.getGas(1000), Materials.AllylChloride.getFluid(1000), Materials.HydrochloricAcid.getCells(1), Materials.Empty.getCells(1), 160);
        GT_Values.RA.addChemicalRecipe(Materials.Propene.getCells(1), GT_Values.NI, Materials.Chlorine.getGas(2000), Materials.HydrochloricAcid.getFluid(1000), Materials.AllylChloride.getCells(1), 160);

        GT_Values.RA.addChemicalRecipe(Materials.Chlorine.getCells(10), Materials.Mercury.getCells(1), Materials.Water.getFluid(10000), Materials.HypochlorousAcid.getFluid(10000), Materials.Empty.getCells(11), 600, 8);
        GT_Values.RA.addChemicalRecipe(Materials.Water.getCells(10), Materials.Mercury.getCells(1), Materials.Chlorine.getGas(10000), Materials.HypochlorousAcid.getFluid(10000), Materials.Empty.getCells(11), 600, 8);
        GT_Values.RA.addChemicalRecipe(Materials.Chlorine.getCells(1), Materials.Water.getCells(1), Materials.Mercury.getFluid(100), Materials.HypochlorousAcid.getFluid(1000), Materials.Empty.getCells(2), 60, 8);

        GT_Values.RA.addChemicalRecipe(Materials.HypochlorousAcid.getCells(1), Materials.SodiumHydroxide.getDust(1), Materials.AllylChloride.getFluid(1000), Materials.Epichlorohydrin.getFluid(1000), Materials.SaltWater.getCells(1), 480);
        GT_Values.RA.addChemicalRecipe(Materials.SodiumHydroxide.getDust(1), Materials.AllylChloride.getCells(1), Materials.HypochlorousAcid.getFluid(1000), Materials.Epichlorohydrin.getFluid(1000), Materials.SaltWater.getCells(1), 480);

        GT_Values.RA.addDistilleryRecipe(2, Materials.HeavyFuel.getFluid(100), Materials.Benzene.getFluid(40), 160, 24, false);
        GT_Values.RA.addDistilleryRecipe(3, Materials.HeavyFuel.getFluid(100), Materials.Phenol.getFluid(25), 160, 24, false);

        GT_Values.RA.addChemicalRecipe(Materials.Apatite.getDust(1), Materials.SulfuricAcid.getCells(5), Materials.Water.getFluid(10000), Materials.PhosphoricAcid.getFluid(3000), Materials.HydrochloricAcid.getCells(1), Materials.Empty.getCells(4), 320);
        GT_Values.RA.addChemicalRecipe(Materials.Phosphor.getDust(4), GT_Values.NI, Materials.Oxygen.getGas(10000), GT_Values.NF, Materials.PhosphorousPentoxide.getDust(1), 40);
        GT_Values.RA.addChemicalRecipe(Materials.PhosphorousPentoxide.getDust(1), GT_Values.NI, Materials.Water.getFluid(6000), Materials.PhosphoricAcid.getFluid(4000), GT_Values.NI, 40);

        GT_Values.RA.addChemicalRecipe(Materials.Propene.getCells(8), Materials.PhosphoricAcid.getCells(1), Materials.Benzene.getFluid(8000), Materials.Cumene.getFluid(8000), Materials.Empty.getCells(9), 1920);
        GT_Values.RA.addChemicalRecipe(Materials.PhosphoricAcid.getCells(1), Materials.Benzene.getCells(8), Materials.Propene.getGas(8000), Materials.Cumene.getFluid(8000), Materials.Empty.getCells(9), 1920);
        GT_Values.RA.addChemicalRecipe(Materials.Benzene.getCells(1), Materials.Propene.getCells(1), Materials.PhosphoricAcid.getFluid(125), Materials.Cumene.getFluid(1000), Materials.Empty.getCells(2), 240);

        GT_Values.RA.addChemicalRecipe(Materials.Cumene.getCells(1), GT_Values.NI, Materials.Oxygen.getGas(1000), Materials.Acetone.getFluid(1000), Materials.Phenol.getCells(1), 160);
        GT_Values.RA.addChemicalRecipe(Materials.Oxygen.getCells(1), GT_Values.NI, Materials.Cumene.getFluid(1000), Materials.Phenol.getFluid(1000), Materials.Acetone.getCells(1), 160);

        GT_Values.RA.addChemicalRecipe(Materials.BisphenolA.getCells(1), Materials.SodiumHydroxide.getDust(1), Materials.Epichlorohydrin.getFluid(1000), Materials.Epoxid.getMolten(1000), Materials.SaltWater.getCells(1), 200);
        GT_Values.RA.addChemicalRecipe(Materials.SodiumHydroxide.getDust(1), Materials.Epichlorohydrin.getCells(1), Materials.BisphenolA.getFluid(1000), Materials.Epoxid.getMolten(1000), Materials.SaltWater.getCells(1), 200);

        GT_Values.RA.addChemicalRecipe(Materials.Acetone.getCells(1), Materials.Phenol.getCells(2), Materials.HydrochloricAcid.getFluid(1000), Materials.BisphenolA.getFluid(1000), Materials.Water.getCells(1), Materials.Empty.getCells(2), 160);
        GT_Values.RA.addChemicalRecipe(Materials.HydrochloricAcid.getCells(1), Materials.Acetone.getCells(1), Materials.Phenol.getFluid(2000), Materials.BisphenolA.getFluid(1000), Materials.Water.getCells(1), Materials.Empty.getCells(1), 160);
        GT_Values.RA.addChemicalRecipe(Materials.Phenol.getCells(2), Materials.HydrochloricAcid.getCells(1), Materials.Acetone.getFluid(1000), Materials.BisphenolA.getFluid(1000), Materials.Water.getCells(1), Materials.Empty.getCells(2), 160);

        GT_Values.RA.addChemicalRecipe(Materials.Chlorine.getCells(2), GT_Utility.getIntegratedCircuit(1), Materials.Methane.getGas(1000), Materials.Chloromethane.getGas(1000), Materials.HydrochloricAcid.getCells(1), Materials.Empty.getCells(1), 80);
        GT_Values.RA.addChemicalRecipe(Materials.Methane.getCells(1), GT_Utility.getIntegratedCircuit(1), Materials.Chlorine.getGas(2000), Materials.HydrochloricAcid.getFluid(1000), Materials.Chloromethane.getCells(1), 80);
        GT_Values.RA.addChemicalRecipe(Materials.Chlorine.getCells(6), GT_Utility.getIntegratedCircuit(3), Materials.Methane.getGas(1000), Materials.Chloroform.getFluid(1000), Materials.HydrochloricAcid.getCells(3), Materials.Empty.getCells(3), 240);
        GT_Values.RA.addChemicalRecipe(Materials.Methane.getCells(1), GT_Utility.getIntegratedCircuit(3), Materials.Chlorine.getGas(6000), Materials.HydrochloricAcid.getFluid(3000), Materials.Chloroform.getCells(1), 240);

        GT_Values.RA.addChemicalRecipe(Materials.Fluorine.getCells(1), GT_Values.NI, Materials.Hydrogen.getGas(1000), Materials.HydrofluoricAcid.getFluid(1000), Materials.Empty.getCells(1), 60, 8);
        GT_Values.RA.addChemicalRecipe(Materials.Hydrogen.getCells(1), GT_Values.NI, Materials.Fluorine.getGas(1000), Materials.HydrofluoricAcid.getFluid(1000), Materials.Empty.getCells(1), 60, 8);

        GT_Values.RA.addChemicalRecipe(Materials.Chloroform.getCells(2), Materials.HydrofluoricAcid.getCells(4), GT_Values.NF, Materials.Tetrafluoroethylene.getGas(1000), Materials.HydrochloricAcid.getCells(6), 480, 256);
        GT_Values.RA.addChemicalRecipe(Materials.Chloroform.getCells(2), Materials.Empty.getCells(4), Materials.HydrofluoricAcid.getFluid(4000), Materials.Tetrafluoroethylene.getGas(1000), Materials.HydrochloricAcid.getCells(6), 480, 256);
        GT_Values.RA.addChemicalRecipe(Materials.HydrofluoricAcid.getCells(4), Materials.Empty.getCells(2), Materials.Chloroform.getFluid(2000), Materials.Tetrafluoroethylene.getGas(1000), Materials.HydrochloricAcid.getCells(6), 480, 256);

        GT_Values.RA.addDefaultPolymerizationRecipes(Materials.Tetrafluoroethylene.mGas, Materials.Polytetrafluoroethylene.mStandardMoltenFluid);

        GT_Values.RA.addChemicalRecipe(Materials.Silicon.getDust(1), GT_Values.NI, Materials.Chloromethane.getGas(2000), Materials.Dimethyldichlorosilane.getFluid(1000), GT_Values.NI, 240, 96);

        GT_Values.RA.addChemicalRecipe(Materials.Dimethyldichlorosilane.getCells(1), GT_Values.NI, Materials.Water.getFluid(1000), Materials.HydrochloricAcid.getFluid(2000), Materials.Polydimethylsiloxane.getDust(3), Materials.Empty.getCells(1), 240, 96);
        GT_Values.RA.addChemicalRecipe(Materials.Water.getCells(1), GT_Values.NI, Materials.Dimethyldichlorosilane.getFluid(1000), Materials.HydrochloricAcid.getFluid(2000), Materials.Polydimethylsiloxane.getDust(3), Materials.Empty.getCells(1), 240, 96);
        GT_Values.RA.addChemicalRecipe(Materials.Dimethyldichlorosilane.getCells(1), Materials.Water.getCells(1), GT_Values.NF, Materials.HydrochloricAcid.getFluid(2000), Materials.Polydimethylsiloxane.getDust(3), Materials.Empty.getCells(2), 240, 96);

        GT_Values.RA.addChemicalRecipe(Materials.Polydimethylsiloxane.getDust(9), Materials.Sulfur.getDust(1), GT_Values.NF, Materials.Silicone.getMolten(1296), GT_Values.NI, 600);

        GT_Values.RA.addChemicalRecipe(Materials.Nitrogen.getCells(1), GT_Values.NI, Materials.Hydrogen.getGas(3000), Materials.Ammonia.getGas(1000), Materials.Empty.getCells(1), 640, 384);
        GT_Values.RA.addChemicalRecipe(Materials.Hydrogen.getCells(3), GT_Values.NI, Materials.Nitrogen.getGas(1000), Materials.Ammonia.getGas(1000), Materials.Empty.getCells(3), 640, 384);

        GT_Values.RA.addChemicalRecipe(Materials.Methanol.getCells(2), GT_Values.NI, Materials.Ammonia.getGas(1000), Materials.Dimethylamine.getGas(1000), Materials.Water.getCells(2), 240, 120);
        GT_Values.RA.addChemicalRecipe(Materials.Ammonia.getCells(1), GT_Values.NI, Materials.Methanol.getFluid(2000), Materials.Water.getFluid(2000), Materials.Dimethylamine.getCells(1), 240, 120);

        GT_Values.RA.addChemicalRecipe(Materials.Ammonia.getCells(1), GT_Values.NI, Materials.HypochlorousAcid.getFluid(1000), Materials.Water.getFluid(1000), Materials.Chloramine.getCells(1), 160);
        GT_Values.RA.addChemicalRecipe(Materials.HypochlorousAcid.getCells(1), GT_Values.NI, Materials.Ammonia.getGas(1000), Materials.Chloramine.getFluid(1000), Materials.Water.getCells(1), 160);

        GT_Values.RA.addChemicalRecipe(Materials.Chloramine.getCells(1), GT_Values.NI, Materials.Dimethylamine.getGas(1000), Materials.Dimethylhydrazine.getFluid(1000), Materials.HydrochloricAcid.getCells(1), 200);
        GT_Values.RA.addChemicalRecipe(Materials.Dimethylamine.getCells(1), GT_Values.NI, Materials.Chloramine.getFluid(1000), Materials.HydrochloricAcid.getFluid(1000), Materials.Dimethylhydrazine.getCells(1), 200);

        GT_Values.RA.addChemicalRecipe(GT_Utility.getIntegratedCircuit(2), GT_Values.NI, Materials.NitrogenDioxide.getGas(2000), Materials.DinitrogenTetroxide.getGas(1000), GT_Values.NI, 640);

        GT_Values.RA.addMixerRecipe(Materials.Dimethylhydrazine.getCells(1), GT_Values.NI, GT_Values.NI, GT_Values.NI, Materials.DinitrogenTetroxide.getGas(1000), new FluidStack(ItemList.sRocketFuel, 6000), Materials.Empty.getCells(1), 60, 16);
        GT_Values.RA.addMixerRecipe(Materials.DinitrogenTetroxide.getCells(1), GT_Values.NI, GT_Values.NI, GT_Values.NI, Materials.Dimethylhydrazine.getFluid(1000), new FluidStack(ItemList.sRocketFuel, 6000), Materials.Empty.getCells(1), 60, 16);
        GT_Values.RA.addMixerRecipe(Materials.Dimethylhydrazine.getCells(1), GT_Values.NI, GT_Values.NI, GT_Values.NI, Materials.Oxygen.getGas(1000), new FluidStack(ItemList.sRocketFuel, 3000), Materials.Empty.getCells(1), 60, 16);
        GT_Values.RA.addMixerRecipe(Materials.Oxygen.getCells(1), GT_Values.NI, GT_Values.NI, GT_Values.NI, Materials.Dimethylhydrazine.getFluid(1000), new FluidStack(ItemList.sRocketFuel, 3000), Materials.Empty.getCells(1), 60, 16);

        GT_Values.RA.addChemicalRecipe(Materials.Ammonia.getCells(4), GT_Values.NI, Materials.Oxygen.getGas(10000), Materials.Water.getFluid(6000), Materials.NitricOxide.getCells(4), 320, 384);
        GT_Values.RA.addChemicalRecipe(Materials.Oxygen.getCells(10), GT_Values.NI, Materials.Ammonia.getGas(4000), Materials.NitricOxide.getGas(4000), Materials.Water.getCells(6), Materials.Empty.getCells(4), 320, 384);

        GT_Values.RA.addChemicalRecipe(Materials.NitricOxide.getCells(1), GT_Values.NI, Materials.Oxygen.getGas(1000), Materials.NitrogenDioxide.getGas(1000), Materials.Empty.getCells(1), 160);
        GT_Values.RA.addChemicalRecipe(Materials.Oxygen.getCells(1), GT_Values.NI, Materials.NitricOxide.getGas(1000), Materials.NitrogenDioxide.getGas(1000), Materials.Empty.getCells(1), 160);

        GT_Values.RA.addChemicalRecipe(Materials.Water.getCells(1), GT_Utility.getIntegratedCircuit(1), Materials.NitrogenDioxide.getGas(3000), Materials.NitricAcid.getFluid(2000), Materials.NitricOxide.getCells(1), 240);
        GT_Values.RA.addChemicalRecipe(Materials.NitrogenDioxide.getCells(3), GT_Utility.getIntegratedCircuit(1), Materials.Water.getFluid(1000), Materials.NitricOxide.getGas(1000), Materials.NitricAcid.getCells(2), Materials.Empty.getCells(1), 240);

        GT_Values.RA.addChemicalRecipe(Materials.NitrogenDioxide.getCells(4), Materials.Oxygen.getCells(2), Materials.Water.getFluid(2000), Materials.NitricAcid.getFluid(4000), Materials.Empty.getCells(6), 480);
        GT_Values.RA.addChemicalRecipe(Materials.Oxygen.getCells(2), Materials.Water.getCells(2), Materials.NitrogenDioxide.getGas(4000), Materials.NitricAcid.getFluid(4000), Materials.Empty.getCells(4), 480);
        GT_Values.RA.addChemicalRecipe(Materials.Water.getCells(2), Materials.NitrogenDioxide.getCells(4), Materials.Oxygen.getGas(2000), Materials.NitricAcid.getFluid(4000), Materials.Empty.getCells(6), 480);

        GT_Values.RA.addChemicalRecipe(Materials.Sulfur.getDust(1), GT_Values.NI, Materials.Hydrogen.getGas(2000), Materials.HydricSulfide.getGas(1000), GT_Values.NI, 60, 8);

        GT_Values.RA.addChemicalRecipe(Materials.Sulfur.getDust(1), GT_Utility.getIntegratedCircuit(2), Materials.Oxygen.getGas(2000), Materials.SulfurDioxide.getGas(1000), GT_Values.NI, 60, 8);
        GT_Values.RA.addChemicalRecipe(Materials.HydricSulfide.getCells(1), GT_Values.NI, Materials.Oxygen.getGas(3000), Materials.SulfurDioxide.getGas(1000), Materials.Water.getCells(1), 120);
        GT_Values.RA.addChemicalRecipe(Materials.Oxygen.getCells(3), GT_Values.NI, Materials.HydricSulfide.getGas(1000), Materials.Water.getFluid(1000), Materials.SulfurDioxide.getCells(1), Materials.Empty.getCells(2), 120);

        GT_Values.RA.addChemicalRecipe(Materials.SulfurDioxide.getCells(1), GT_Values.NI, Materials.HydricSulfide.getGas(2000), Materials.Water.getFluid(2000), Materials.Sulfur.getDust(3), Materials.Empty.getCells(1), 120);
        GT_Values.RA.addChemicalRecipe(Materials.HydricSulfide.getCells(2), GT_Values.NI, Materials.SulfurDioxide.getGas(1000), Materials.Water.getFluid(2000), Materials.Sulfur.getDust(3), Materials.Empty.getCells(2), 120);

        GT_Values.RA.addChemicalRecipe(Materials.Oxygen.getCells(1), GT_Values.NI, Materials.SulfurDioxide.getGas(1000), Materials.SulfurTrioxide.getGas(1000), Materials.Empty.getCells(1), 200);
        GT_Values.RA.addChemicalRecipe(Materials.SulfurDioxide.getCells(1), GT_Values.NI, Materials.Oxygen.getGas(1000), Materials.SulfurTrioxide.getGas(1000), Materials.Empty.getCells(1), 200);

        GT_Values.RA.addChemicalRecipe(Materials.Water.getCells(1), GT_Values.NI, Materials.SulfurTrioxide.getGas(1000), Materials.SulfuricAcid.getFluid(1000), Materials.Empty.getCells(1), 320, 8);

        GT_Values.RA.addUniversalDistillationRecipe(Materials.DilutedSulfuricAcid.getFluid(3000), new FluidStack[]{Materials.SulfuricAcid.getFluid(2000), Materials.Water.getFluid(1000)}, GT_Values.NI, 600, 120);

        GT_Values.RA.addChemicalRecipe(Materials.Chlorine.getCells(2), GT_Values.NI, Materials.Ethylene.getGas(1000), Materials.VinylChloride.getGas(1000), Materials.HydrochloricAcid.getCells(1), Materials.Empty.getCells(1), 160);
        GT_Values.RA.addChemicalRecipe(Materials.Ethylene.getCells(1), GT_Values.NI, Materials.Chlorine.getGas(2000), Materials.HydrochloricAcid.getFluid(1000), Materials.VinylChloride.getCells(1), 160);

        GT_Values.RA.addChemicalRecipe(Materials.Ethylene.getCells(1), Materials.HydrochloricAcid.getCells(1), Materials.Oxygen.getGas(1000), Materials.VinylChloride.getGas(1000), Materials.Water.getCells(1), Materials.Empty.getCells(1), 160);
        GT_Values.RA.addChemicalRecipe(Materials.HydrochloricAcid.getCells(1), Materials.Oxygen.getCells(1), Materials.Ethylene.getGas(1000), Materials.VinylChloride.getGas(1000), Materials.Water.getCells(1), Materials.Empty.getCells(1), 160);
        GT_Values.RA.addChemicalRecipe(Materials.Oxygen.getCells(1), Materials.Ethylene.getCells(1), Materials.HydrochloricAcid.getFluid(1000), Materials.VinylChloride.getGas(1000), Materials.Water.getCells(1), Materials.Empty.getCells(1), 160);

        GT_Values.RA.addDefaultPolymerizationRecipes(Materials.VinylChloride.mGas, Materials.PolyvinylChloride.mStandardMoltenFluid);

        GT_Values.RA.addMixerRecipe(Materials.Sugar.getDust(4), GT_Values.NI, GT_Values.NI, GT_Values.NI, Materials.SulfuricAcid.getFluid(1000), Materials.DilutedSulfuricAcid.getFluid(1000), Materials.Charcoal.getGems(1), 1200, 2);
        GT_Values.RA.addMixerRecipe(Materials.Wood.getDust(4), GT_Values.NI, GT_Values.NI, GT_Values.NI, Materials.SulfuricAcid.getFluid(1000), Materials.DilutedSulfuricAcid.getFluid(1000), Materials.Charcoal.getGems(1), 1200, 2);

        GT_Values.RA.addUniversalDistillationRecipe(Materials.Acetone.getFluid(1000), new FluidStack[]{Materials.Ethenone.getGas(1000), Materials.Methane.getGas(1000)}, GT_Values.NI, 80, 640);
        GameRegistry.addSmelting(Materials.Acetone.getCells(1), Materials.Ethenone.getCells(1), 0);
        GT_Values.RA.addChemicalRecipe(Materials.AceticAcid.getCells(1), GT_Values.NI, Materials.SulfuricAcid.getFluid(1000), Materials.DilutedSulfuricAcid.getFluid(1000), Materials.Ethenone.getCells(1), 160, 120);
        GT_Values.RA.addChemicalRecipe(Materials.SulfuricAcid.getCells(1), GT_Values.NI, Materials.AceticAcid.getFluid(1000), Materials.Ethenone.getGas(1000), Materials.DilutedSulfuricAcid.getCells(1), 160, 120);

        GT_Values.RA.addChemicalRecipe(Materials.Ethenone.getCells(1), Materials.Empty.getCells(1), Materials.NitricAcid.getFluid(8000), Materials.Water.getFluid(9000), Materials.Tetranitromethane.getCells(2), 480, 16);
        GT_Values.RA.addChemicalRecipe(Materials.NitricAcid.getCells(8), Materials.Empty.getCells(1), Materials.Ethenone.getGas(1000), Materials.Tetranitromethane.getFluid(2000), Materials.Water.getCells(9), 480, 16);
        GT_Values.RA.addChemicalRecipe(Materials.Ethenone.getCells(1), Materials.NitricAcid.getCells(8), GT_Values.NF, Materials.Tetranitromethane.getFluid(2000), Materials.Water.getCells(9), 480, 16);

        GT_Values.RA.addMixerRecipe(Materials.LightFuel.getCells(1), GT_Values.NI, GT_Values.NI, GT_Values.NI, Materials.Tetranitromethane.getFluid(20), Materials.NitroFuel.getFluid(1000), Materials.Empty.getCells(1), 80, 8);
        GT_Values.RA.addMixerRecipe(Materials.Fuel.getCells(2), GT_Values.NI, GT_Values.NI, GT_Values.NI, Materials.Tetranitromethane.getFluid(20), Materials.NitroFuel.getFluid(1000), Materials.Empty.getCells(2), 80, 8);
        GT_Values.RA.addMixerRecipe(Materials.BioDiesel.getCells(4), GT_Values.NI, GT_Values.NI, GT_Values.NI, Materials.Tetranitromethane.getFluid(60), Materials.NitroFuel.getFluid(3000), Materials.Empty.getCells(4), 80, 8);

        GT_Values.RA.addChemicalRecipe(Materials.Propene.getCells(1), GT_Values.NI, Materials.Ethylene.getGas(1000), Materials.Hydrogen.getGas(2000), Materials.Isoprene.getCells(1), 120);
        GT_Values.RA.addChemicalRecipe(Materials.Ethylene.getCells(1), Materials.Empty.getCells(1), Materials.Propene.getGas(1000), Materials.Isoprene.getFluid(1000), Materials.Hydrogen.getCells(2), 120);
        GT_Values.RA.addChemicalRecipe(GT_Utility.getIntegratedCircuit(2), Materials.Empty.getCells(1), Materials.Propene.getGas(2000), Materials.Isoprene.getFluid(1000), Materials.Methane.getCells(1), 120);

        GT_Values.RA.addChemicalRecipe(ItemList.Cell_Air.get(2), GT_Utility.getIntegratedCircuit(1), Materials.Isoprene.getFluid(144), GT_Values.NF, Materials.RawRubber.getDust(1), Materials.Empty.getCells(2), 160);
        GT_Values.RA.addChemicalRecipe(Materials.Oxygen.getCells(4), GT_Utility.getIntegratedCircuit(1), Materials.Isoprene.getFluid(288), GT_Values.NF, Materials.RawRubber.getDust(3), Materials.Empty.getCells(4), 320);
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{GT_Utility.getIntegratedCircuit(2)}, new FluidStack[]{Materials.Isoprene.getFluid(1728), Materials.Air.getGas(12000), Materials.Titaniumtetrachloride.getFluid(80)}, null, new ItemStack[]{Materials.RawRubber.getDust(18)}, 640, 30);
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{GT_Utility.getIntegratedCircuit(2)}, new FluidStack[]{Materials.Isoprene.getFluid(1728), Materials.Oxygen.getGas(12000), Materials.Titaniumtetrachloride.getFluid(80)}, null, new ItemStack[]{Materials.RawRubber.getDust(24)}, 640, 30);

        GT_Values.RA.addChemicalRecipe(Materials.Benzene.getCells(1), GT_Values.NI, Materials.Ethylene.getGas(1000), Materials.Hydrogen.getGas(2000), Materials.Styrene.getCells(1), 120);
        GT_Values.RA.addChemicalRecipe(Materials.Ethylene.getCells(1), Materials.Empty.getCells(1), Materials.Benzene.getFluid(1000), Materials.Styrene.getFluid(1000), Materials.Hydrogen.getCells(2), 120);

        GT_Values.RA.addDefaultPolymerizationRecipes(Materials.Styrene.mFluid, Materials.Polystyrene.mStandardMoltenFluid);

        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{GT_Utility.getIntegratedCircuit(3)}, new FluidStack[]{Materials.Styrene.getFluid(36), Materials.Butadiene.getGas(108), Materials.Air.getGas(2000)}, null, new ItemStack[]{Materials.RawStyreneButadieneRubber.getDust(1)}, 160, 240);
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{GT_Utility.getIntegratedCircuit(3)}, new FluidStack[]{Materials.Styrene.getFluid(72), Materials.Butadiene.getGas(216), Materials.Oxygen.getGas(4000)}, null, new ItemStack[]{Materials.RawStyreneButadieneRubber.getDust(3)}, 160, 240);
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{GT_Utility.getIntegratedCircuit(4)}, new FluidStack[]{Materials.Styrene.getFluid(540), Materials.Butadiene.getGas(1620), Materials.Titaniumtetrachloride.getFluid(100), Materials.Air.getGas(15000)}, null, new ItemStack[]{Materials.RawStyreneButadieneRubber.getDust(22), Materials.RawStyreneButadieneRubber.getDustSmall(2)}, 640, 240);
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{GT_Utility.getIntegratedCircuit(4)}, new FluidStack[]{Materials.Styrene.getFluid(540), Materials.Butadiene.getGas(1620), Materials.Titaniumtetrachloride.getFluid(100), Materials.Oxygen.getGas(15000)}, null, new ItemStack[]{Materials.RawStyreneButadieneRubber.getDust(30)}, 640, 240);

        GT_Values.RA.addChemicalRecipe(Materials.RawStyreneButadieneRubber.getDust(9), Materials.Sulfur.getDust(1), GT_Values.NF, Materials.StyreneButadieneRubber.getMolten(1296), GT_Values.NI, 600);

        GT_Values.RA.addChemicalRecipe(Materials.Benzene.getCells(1), GT_Utility.getIntegratedCircuit(2), Materials.Chlorine.getGas(4000), Materials.HydrochloricAcid.getFluid(2000), Materials.Dichlorobenzene.getCells(1), 240);
        GT_Values.RA.addChemicalRecipe(Materials.Chlorine.getCells(4), GT_Utility.getIntegratedCircuit(2), Materials.Benzene.getFluid(1000), Materials.Dichlorobenzene.getFluid(1000), Materials.HydrochloricAcid.getCells(2), Materials.Empty.getCells(2), 240);

        GT_Values.RA.addChemicalRecipe(Materials.SodiumSulfide.getDust(1), GT_Values.NI, Materials.Dichlorobenzene.getFluid(1000), Materials.PolyphenyleneSulfide.getMolten(1000), Materials.Salt.getDust(2), 240, 360);

        GT_Values.RA.addChemicalRecipe(Materials.Salt.getDust(1), GT_Utility.getIntegratedCircuit(1), Materials.SulfuricAcid.getFluid(1000), Materials.HydrochloricAcid.getFluid(1000), Materials.SodiumBisulfate.getDust(1), 60);
        GT_Values.RA.addElectrolyzerRecipe(Materials.SodiumBisulfate.getDust(2), Materials.Empty.getCells(2), null, Materials.SodiumPersulfate.getFluid(1000), Materials.Hydrogen.getCells(2), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 600, 30);

        GT_Values.RA.addChemicalRecipe(Materials.SodiumHydroxide.getDustTiny(1), Materials.Methanol.getCells(1), Materials.SeedOil.getFluid(6000), Materials.BioDiesel.getFluid(6000), Materials.Glycerol.getCells(1), 600);
        GT_Values.RA.addChemicalRecipe(Materials.SodiumHydroxide.getDustTiny(1), Materials.SeedOil.getCells(6), Materials.Methanol.getFluid(1000), Materials.Glycerol.getFluid(1000), Materials.BioDiesel.getCells(6), 600);
        GT_Values.RA.addChemicalRecipe(Materials.SodiumHydroxide.getDustTiny(1), Materials.Methanol.getCells(1), Materials.FishOil.getFluid(6000), Materials.BioDiesel.getFluid(6000), Materials.Glycerol.getCells(1), 600);
        GT_Values.RA.addChemicalRecipe(Materials.SodiumHydroxide.getDustTiny(1), Materials.FishOil.getCells(6), Materials.Methanol.getFluid(1000), Materials.Glycerol.getFluid(1000), Materials.BioDiesel.getCells(6), 600);

        GT_Values.RA.addChemicalRecipe(Materials.SodiumHydroxide.getDustTiny(1), Materials.Ethanol.getCells(1), Materials.SeedOil.getFluid(6000), Materials.BioDiesel.getFluid(6000), Materials.Glycerol.getCells(1), 600);
        GT_Values.RA.addChemicalRecipe(Materials.SodiumHydroxide.getDustTiny(1), Materials.SeedOil.getCells(6), Materials.Ethanol.getFluid(1000), Materials.Glycerol.getFluid(1000), Materials.BioDiesel.getCells(6), 600);
        GT_Values.RA.addChemicalRecipe(Materials.SodiumHydroxide.getDustTiny(1), Materials.Ethanol.getCells(1), Materials.FishOil.getFluid(6000), Materials.BioDiesel.getFluid(6000), Materials.Glycerol.getCells(1), 600);
        GT_Values.RA.addChemicalRecipe(Materials.SodiumHydroxide.getDustTiny(1), Materials.FishOil.getCells(6), Materials.Ethanol.getFluid(1000), Materials.Glycerol.getFluid(1000), Materials.BioDiesel.getCells(6), 600);

        GT_Values.RA.addChemicalRecipe(Materials.Glycerol.getCells(1), null, Materials.NitrationMixture.getFluid(3000), Materials.DilutedSulfuricAcid.getFluid(3000), Materials.Glyceryl.getCells(1), 180);
        GT_Values.RA.addChemicalRecipe(Materials.NitrationMixture.getCells(3), null, Materials.Glycerol.getFluid(1000), Materials.Glyceryl.getFluid(1000), Materials.DilutedSulfuricAcid.getCells(3), 180);

        GT_Values.RA.addChemicalRecipe(Materials.Quicklime.getDust(2), GT_Values.NI, Materials.CarbonDioxide.getGas(1000), GT_Values.NF, Materials.Calcite.getDust(5), 80);
        GT_Values.RA.addChemicalRecipe(Materials.Calcite.getDust(5), GT_Utility.getIntegratedCircuit(1), GT_Values.NF, Materials.CarbonDioxide.getGas(1000), Materials.Quicklime.getDust(2), 240);
        GT_Values.RA.addChemicalRecipe(Materials.Magnesia.getDust(2), GT_Values.NI, Materials.CarbonDioxide.getGas(1000), GT_Values.NF, Materials.Magnesite.getDust(5), 80);
        GT_Values.RA.addChemicalRecipe(Materials.Magnesite.getDust(5), GT_Utility.getIntegratedCircuit(1), GT_Values.NF, Materials.CarbonDioxide.getGas(1000), Materials.Magnesia.getDust(2), 240);

    }

    private void addSimplifiedChemicalRecipes() {
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.Nitrogen), ItemList.Circuit_Integrated.getWithDamage(0, 1), Materials.Oxygen.getGas(2000L), Materials.NitrogenDioxide.getGas(1000L), ItemList.Cell_Empty.get(1), 1250);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.Oxygen, 2), ItemList.Circuit_Integrated.getWithDamage(0, 1), Materials.Nitrogen.getGas(1000L), Materials.NitrogenDioxide.getGas(1000L), ItemList.Cell_Empty.get(2), 1250);

        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Sulfur), ItemList.Circuit_Integrated.getWithDamage(0, 1), Materials.Water.getFluid(1000L), Materials.SulfuricAcid.getFluid(1000L), GT_Values.NI, 1150);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Sulfur), MatUnifier.get(OrePrefixes.cell, Materials.Hydrogen, 2), Materials.Oxygen.getGas(4000L), Materials.SulfuricAcid.getFluid(1000L), ItemList.Cell_Empty.get(2), 1150);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Sulfur), MatUnifier.get(OrePrefixes.cell, Materials.Oxygen, 4), Materials.Hydrogen.getGas(2000L), Materials.SulfuricAcid.getFluid(1000L), ItemList.Cell_Empty.get(4), 1150);

        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.HydricSulfide, 2), ItemList.Cell_Water.get(2), null, Materials.SulfuricAcid.getFluid(2000), ItemList.Cell_Empty.get(4), 320);
        GT_Values.RA.addChemicalRecipe(ItemList.Cell_Water.get(2), null, new FluidStack(ItemList.sHydricSulfur, 2000), Materials.SulfuricAcid.getFluid(2000), ItemList.Cell_Empty.get(2), 320);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.HydricSulfide, 2), null, Materials.Water.getFluid(2000), Materials.SulfuricAcid.getFluid(2000), ItemList.Cell_Empty.get(2), 320);

        GT_Values.RA.addChemicalRecipe(ItemList.Cell_Air.get(2), null, Materials.Naphtha.getFluid(288), Materials.Plastic.getMolten(144), ItemList.Cell_Empty.get(2), 640);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dustTiny, Materials.Titanium), MatUnifier.get(OrePrefixes.cell, Materials.Oxygen, 16), Materials.Naphtha.getFluid(1296), Materials.Plastic.getMolten(1296), ItemList.Cell_Empty.get(16), 640);

        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.Naphtha, 3), MatUnifier.get(OrePrefixes.cell, Materials.NitrogenDioxide), new FluidStack(ItemList.sEpichlorhydrin, 144), Materials.Epoxid.getMolten(288), ItemList.Cell_Empty.get(4), 240, 30);

        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Carbon), MatUnifier.get(OrePrefixes.cell, Materials.Chlorine), Materials.LPG.getFluid(432), new FluidStack(ItemList.sEpichlorhydrin, 432), ItemList.Cell_Empty.get(1), 480, 30);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.Naphtha, 3), MatUnifier.get(OrePrefixes.cell, Materials.Fluorine), new FluidStack(ItemList.sEpichlorhydrin, 432), Materials.Polytetrafluoroethylene.getMolten(432), ItemList.Cell_Empty.get(4), 240, 256);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Silicon), null, new FluidStack(ItemList.sEpichlorhydrin, 144), Materials.Silicone.getMolten(144), GT_Values.NI, 240, 96);

        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.NitrogenDioxide), MatUnifier.get(OrePrefixes.cell, Materials.Hydrogen, 3), Materials.Air.getGas(500), new FluidStack(ItemList.sRocketFuel, 3000), ItemList.Cell_Water.get(4), 1000, 388);

        GT_Values.RA.addCentrifugeRecipe(ItemList.Cell_Empty.get(4), GT_Values.NI, Materials.Gas.getGas(8000), GT_Values.NF, MatUnifier.get(OrePrefixes.cell, Materials.LPG, 4), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{10000}, 200, 5);

        FluidStack tFD = FluidRegistry.getFluidStack("fluiddeath", 10);
        if (Loader.isModLoaded("Thaumcraft") && tFD != null && tFD.getFluid() != null && tFD.amount > 0) {
            GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Lignite, 1), ItemList.MSFMixture.get(6), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.LPG.getFluid(1500), null, ItemList.Block_MSSFUEL.get(1), 120, 96);
            GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Charcoal, 1), ItemList.MSFMixture.get(4), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.LPG.getFluid(1200), null, ItemList.Block_MSSFUEL.get(1), 120, 96);
            GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Coal, 1), ItemList.MSFMixture.get(2), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.LPG.getFluid(750), null, ItemList.Block_MSSFUEL.get(1), 120, 96);
        }
        if (Loader.isModLoaded("Railcraft")) {
            GT_Values.RA.addMixerRecipe(EnumCube.COKE_BLOCK.getItem(), ItemList.SFMixture.get(1), null, null, Materials.LPG.getFluid(375), null, ItemList.Block_SSFUEL.get(1), 120, 96);
            if (Loader.isModLoaded("Thaumcraft")) {
                GT_Values.RA.addMixerRecipe(EnumCube.COKE_BLOCK.getItem(), ItemList.MSFMixture.get(1), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.LPG.getFluid(375), null, ItemList.Block_MSSFUEL.get(1), 120, 96);
            }
        }
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Lignite, 1), ItemList.SFMixture.get(6), null, null, Materials.LPG.getFluid(1500), null, ItemList.Block_SSFUEL.get(1), 120, 96);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Charcoal, 1), ItemList.SFMixture.get(4), null, null, Materials.LPG.getFluid(1200), null, ItemList.Block_SSFUEL.get(1), 120, 96);
        GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.block, Materials.Coal, 1), ItemList.SFMixture.get(2), null, null, Materials.LPG.getFluid(750), null, ItemList.Block_SSFUEL.get(1), 120, 96);

        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.golden_apple, 1, 1), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(4608L), new ItemStack(Items.gold_ingot, 64), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 9216, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.golden_apple, 1, 0), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), new ItemStack(Items.gold_ingot, 7), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 9216, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.golden_carrot, 1, 0), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), new ItemStack(Items.gold_nugget, 6), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 9216, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.speckled_melon, 1, 0), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), new ItemStack(Items.gold_nugget, 6), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 9216, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.mushroom_stew, 16, 0), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), new ItemStack(Items.bowl, 16, 0), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.apple, 32, 0), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.bread, 64, 0), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.porkchop, 12, 0), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.cooked_porkchop, 16, 0), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.beef, 12, 0), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.cooked_beef, 16, 0), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.fish, 12, 32767), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.cooked_fished, 16, 32767), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.chicken, 12, 0), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.cooked_chicken, 16, 0), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.melon, 64, 0), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Blocks.pumpkin, 16, 0), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.rotten_flesh, 16, 0), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.spider_eye, 32, 0), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.carrot, 16, 0), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.potato, 16), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.poisonous_potato, 12), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.baked_potato, 24), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.cookie, 64, 0), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.cake, 8, 0), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Blocks.brown_mushroom_block, 12, 32767), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Blocks.red_mushroom_block, 12, 32767), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Blocks.brown_mushroom, 32, 32767), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Blocks.red_mushroom, 32, 32767), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(new ItemStack(Items.nether_wart, 32, 32767), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(GT_ModHandler.getIC2Item("terraWart", 16), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(GT_ModHandler.getModItem("TwilightForest", "item.meefRaw", 12, 32767), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(GT_ModHandler.getModItem("TwilightForest", "item.meefSteak", 16, 32767), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(GT_ModHandler.getModItem("TwilightForest", "item.venisonRaw", 12, 32767), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
        GT_Values.RA.addCentrifugeRecipe(GT_ModHandler.getModItem("TwilightForest", "item.venisonCooked", 16, 32767), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(576L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);

        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0, 1), Materials.HeavyFuel.getFluid(10L), new FluidStack(ItemList.sToluene, 4), 16, 24, false);
        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0, 1), new FluidStack(ItemList.sToluene, 30), Materials.LightFuel.getFluid(30L), 16, 24, false);

        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.Fuel, 1), GT_Values.NI, Materials.Glyceryl.getFluid(250L), Materials.NitroFuel.getFluid(1000L), ItemList.Cell_Empty.get(1), 250);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.Glyceryl, 1), GT_Values.NI, Materials.Fuel.getFluid(4000L), Materials.NitroFuel.getFluid(4000L), ItemList.Cell_Empty.get(1), 1000);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.LightFuel), GT_Values.NI, Materials.Glyceryl.getFluid(250L), Materials.NitroFuel.getFluid(1250L), ItemList.Cell_Empty.get(1), 250);
        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.Glyceryl), GT_Values.NI, Materials.LightFuel.getFluid(4000L), Materials.NitroFuel.getFluid(5000L), ItemList.Cell_Empty.get(1), 1000);

        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Sulfur), MatUnifier.get(OrePrefixes.dust, Materials.Sodium), Materials.Oxygen.getGas(4000L), Materials.SodiumPersulfate.getFluid(6000L), GT_Values.NI, 8000);
        GT_Values.RA.addMixerRecipe(Materials.Sodium.getDust(2), Materials.Sulfur.getDust(1), GT_Utility.getIntegratedCircuit(2), null, null, null, Materials.SodiumSulfide.getDust(1), 60, 30);

        GT_Values.RA.addChemicalRecipe(MatUnifier.get(OrePrefixes.cell, Materials.Nitrogen), MatUnifier.get(OrePrefixes.dust, Materials.Carbon), Materials.Water.getFluid(2000L), Materials.Glyceryl.getFluid(4000L), ItemList.Cell_Empty.get(1), 2700);

        if (!GregTech_API.mIC2Classic) {
            GT_Values.RA.addDistillationTowerRecipe(new FluidStack(FluidRegistry.getFluid("ic2biomass"), 250), new FluidStack[]{new FluidStack(FluidRegistry.getFluid("ic2biogas"), 8000), Materials.Water.getFluid(125L)}, ItemList.IC2_Fertilizer.get(1), 250, 480);
            GT_Values.RA.addFuel(GT_ModHandler.getIC2Item("biogasCell", 1), null, 32, 1);

            GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0, 1), new FluidStack(FluidRegistry.getFluid("ic2biomass"), 1), new FluidStack(FluidRegistry.getFluid("ic2biogas"), 32), 40, 16, false);
            GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0, 2), new FluidStack(FluidRegistry.getFluid("ic2biomass"), 4), Materials.Water.getFluid(2), 80, 30, false);
        }
        GT_Values.RA.addDistillationTowerRecipe(Materials.Biomass.getFluid(600L), new FluidStack[]{Materials.Ethanol.getFluid(240L), Materials.Water.getFluid(240L)}, MatUnifier.get(OrePrefixes.dustSmall, Materials.Wood), 16, 400);
        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0, 1), Materials.Biomass.getFluid(40L), Materials.Ethanol.getFluid(12L), 16, 24, false);
        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0, 5), Materials.Biomass.getFluid(40L), Materials.Water.getFluid(12L), 16, 24, false);

    }

    private void addRecipesMay2017OilRefining() {
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Gas.getGas(1000),
                new FluidStack[]{Materials.Butane.getGas(60), Materials.Propane.getGas(70), Materials.Ethane.getGas(100), Materials.Methane.getGas(750), Materials.Helium.getGas(20)},
                GT_Values.NI, 240, 120);

        GT_Values.RA.addCentrifugeRecipe(null, null, Materials.Propane.getGas(320), Materials.LPG.getFluid(290), null, null, null, null, null, null, null, 20, 5);
        GT_Values.RA.addCentrifugeRecipe(null, null, Materials.Butane.getGas(320), Materials.LPG.getFluid(370), null, null, null, null, null, null, null, 20, 5);

        GT_Values.RA.addUniversalDistillationRecipe(Materials.Ethylene.getLightlyHydroCracked(1000),
                new FluidStack[]{Materials.Ethane.getGas(1000)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Ethylene.getModeratelyHydroCracked(1000),
                new FluidStack[]{Materials.Methane.getGas(2000)},
                null, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Ethylene.getSeverelyHydroCracked(1000),
                new FluidStack[]{Materials.Methane.getGas(2000), Materials.Hydrogen.getGas(2000)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Ethylene.getLightlySteamCracked(1000),
                new FluidStack[]{Materials.Methane.getGas(1000)},
                Materials.Carbon.getDust(1), 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Ethylene.getModeratelySteamCracked(1000),
                new FluidStack[]{Materials.Methane.getGas(1000)},
                Materials.Carbon.getDust(1), 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Ethylene.getSeverelySteamCracked(1000),
                new FluidStack[]{Materials.Methane.getGas(1000)},
                Materials.Carbon.getDust(1), 120, 120);

        GT_Values.RA.addUniversalDistillationRecipe(Materials.Ethane.getLightlyHydroCracked(1000),
                new FluidStack[]{Materials.Methane.getGas(2000)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Ethane.getModeratelyHydroCracked(1000),
                new FluidStack[]{Materials.Methane.getGas(2000), Materials.Hydrogen.getGas(2000)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Ethane.getSeverelyHydroCracked(1000),
                new FluidStack[]{Materials.Methane.getGas(2000), Materials.Hydrogen.getGas(4000)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Ethane.getLightlySteamCracked(1000),
                new FluidStack[]{Materials.Ethylene.getGas(250), Materials.Methane.getGas(1250)},
                Materials.Carbon.getDustSmall(1), 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Ethane.getModeratelySteamCracked(2000),
                new FluidStack[]{Materials.Ethylene.getGas(250), Materials.Methane.getGas(2750)},
                Materials.Carbon.getDustSmall(3), 240, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Ethane.getSeverelySteamCracked(1000),
                new FluidStack[]{Materials.Methane.getGas(1500)},
                Materials.Carbon.getDustSmall(2), 120, 120);

        GT_Values.RA.addUniversalDistillationRecipe(Materials.Propene.getLightlyHydroCracked(1000),
                new FluidStack[]{Materials.Propane.getGas(500), Materials.Ethylene.getGas(500), Materials.Methane.getGas(500)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Propene.getModeratelyHydroCracked(1000),
                new FluidStack[]{Materials.Ethane.getGas(1000), Materials.Methane.getGas(1000)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Propene.getSeverelyHydroCracked(1000),
                new FluidStack[]{Materials.Methane.getGas(3000)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Propene.getLightlySteamCracked(1000),
                new FluidStack[]{Materials.Ethylene.getGas(1000), Materials.Methane.getGas(500)},
                Materials.Carbon.getDustSmall(2), 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Propene.getModeratelySteamCracked(1000),
                new FluidStack[]{Materials.Ethylene.getGas(750), Materials.Methane.getGas(750)},
                Materials.Carbon.getDustSmall(3), 180, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Propene.getSeverelySteamCracked(1000),
                new FluidStack[]{Materials.Methane.getGas(1500)},
                Materials.Carbon.getDustSmall(6), 180, 120);

        GT_Values.RA.addUniversalDistillationRecipe(Materials.Propane.getLightlyHydroCracked(1000),
                new FluidStack[]{Materials.Ethane.getGas(1000), Materials.Methane.getGas(1000)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Propane.getModeratelyHydroCracked(1000),
                new FluidStack[]{Materials.Methane.getGas(3000)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Propane.getSeverelyHydroCracked(1000),
                new FluidStack[]{Materials.Methane.getGas(3000), Materials.Hydrogen.getGas(2000)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Propane.getLightlySteamCracked(2000),
                new FluidStack[]{Materials.Ethylene.getGas(1500), Materials.Methane.getGas(2500)},
                Materials.Carbon.getDustSmall(1), 240, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Propane.getModeratelySteamCracked(1000),
                new FluidStack[]{Materials.Ethylene.getGas(500), Materials.Methane.getGas(1500)},
                Materials.Carbon.getDustSmall(1), 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Propane.getSeverelySteamCracked(2000),
                new FluidStack[]{Materials.Ethylene.getGas(500), Materials.Methane.getGas(3500)},
                Materials.Carbon.getDustSmall(3), 240, 120);

        GT_Values.RA.addUniversalDistillationRecipe(Materials.Butadiene.getLightlyHydroCracked(750),
                new FluidStack[]{Materials.Butene.getGas(500), Materials.Ethylene.getGas(500)},
                GT_Values.NI, 90, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Butadiene.getModeratelyHydroCracked(900),
                new FluidStack[]{Materials.Butane.getGas(200), Materials.Propene.getGas(200), Materials.Ethane.getGas(400), Materials.Ethylene.getGas(400), Materials.Methane.getGas(200)},
                GT_Values.NI, 108, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Butadiene.getSeverelyHydroCracked(270),
                new FluidStack[]{Materials.Propane.getGas(70), Materials.Ethane.getGas(250), Materials.Ethylene.getGas(105), Materials.Methane.getGas(720)},
                GT_Values.NI, 30, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Butadiene.getLightlySteamCracked(2000),
                new FluidStack[]{Materials.Propene.getGas(1500), Materials.Ethylene.getGas(375), Materials.Methane.getGas(375)},
                Materials.Carbon.getDustSmall(6), 240, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Butadiene.getModeratelySteamCracked(2000),
                new FluidStack[]{Materials.Propene.getGas(250), Materials.Ethylene.getGas(2250), Materials.Methane.getGas(375)},
                Materials.Carbon.getDustSmall(6), 240, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Butadiene.getSeverelySteamCracked(2000),
                new FluidStack[]{Materials.Propene.getGas(250), Materials.Ethylene.getGas(375), Materials.Methane.getGas(2250)},
                Materials.Carbon.getDust(2), 240, 120);

        GT_Values.RA.addUniversalDistillationRecipe(Materials.Butene.getLightlyHydroCracked(750),
                new FluidStack[]{Materials.Butane.getGas(250), Materials.Propene.getGas(250), Materials.Ethane.getGas(250), Materials.Ethylene.getGas(250), Materials.Methane.getGas(250)},
                GT_Values.NI, 90, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Butene.getModeratelyHydroCracked(900),
                new FluidStack[]{Materials.Propane.getGas(350), Materials.Ethane.getGas(500), Materials.Ethylene.getGas(300), Materials.Methane.getGas(950)},
                GT_Values.NI, 108, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Butene.getSeverelyHydroCracked(1000),
                new FluidStack[]{Materials.Ethane.getGas(1000), Materials.Methane.getGas(2000)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Butene.getLightlySteamCracked(1000),
                new FluidStack[]{Materials.Propene.getGas(750), Materials.Ethylene.getGas(500), Materials.Methane.getGas(250)},
                Materials.Carbon.getDustSmall(1), 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Butene.getModeratelySteamCracked(1250),
                new FluidStack[]{Materials.Propene.getGas(250), Materials.Ethylene.getGas(1625), Materials.Methane.getGas(500)},
                Materials.Carbon.getDustSmall(2), 240, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Butene.getSeverelySteamCracked(2000),
                new FluidStack[]{Materials.Propene.getGas(250), Materials.Ethylene.getGas(625), Materials.Methane.getGas(3000)},
                Materials.Carbon.getDust(3), 240, 120);

        GT_Values.RA.addUniversalDistillationRecipe(Materials.Butane.getLightlyHydroCracked(750),
                new FluidStack[]{Materials.Propane.getGas(500), Materials.Ethane.getGas(500), Materials.Methane.getGas(500)},
                GT_Values.NI, 90, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Butane.getModeratelyHydroCracked(1000),
                new FluidStack[]{Materials.Ethane.getGas(1000), Materials.Methane.getGas(2000)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Butane.getSeverelyHydroCracked(1000),
                new FluidStack[]{Materials.Methane.getGas(4000)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Butane.getLightlySteamCracked(4000),
                new FluidStack[]{Materials.Propane.getGas(3000), Materials.Ethane.getGas(500), Materials.Ethylene.getGas(500), Materials.Methane.getGas(4250)},
                Materials.Carbon.getDustSmall(3), 480, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Butane.getModeratelySteamCracked(4000),
                new FluidStack[]{Materials.Propane.getGas(500), Materials.Ethane.getGas(3000), Materials.Ethylene.getGas(3000), Materials.Methane.getGas(1750)},
                Materials.Carbon.getDustSmall(3), 480, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Butane.getSeverelySteamCracked(2000),
                new FluidStack[]{Materials.Propane.getGas(250), Materials.Ethane.getGas(250), Materials.Ethylene.getGas(250), Materials.Methane.getGas(4000)},
                Materials.Carbon.getDustSmall(9), 240, 120);

        GT_Values.RA.addUniversalDistillationRecipe(Materials.Gas.getLightlyHydroCracked(1000),
                new FluidStack[]{Materials.Methane.getGas(1400), Materials.Hydrogen.getGas(1340), Materials.Helium.getGas(20)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Gas.getModeratelyHydroCracked(1000),
                new FluidStack[]{Materials.Methane.getGas(1400), Materials.Hydrogen.getGas(3340), Materials.Helium.getGas(20)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Gas.getSeverelyHydroCracked(1000),
                new FluidStack[]{Materials.Methane.getGas(1400), Materials.Hydrogen.getGas(4340), Materials.Helium.getGas(20)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Gas.getLightlySteamCracked(2500),
                new FluidStack[]{Materials.Propene.getGas(113), Materials.Ethane.getGas(19), Materials.Ethylene.getGas(213), Materials.Methane.getGas(2566), Materials.Helium.getGas(50)},
                Materials.Carbon.getDustTiny(1), 300, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Gas.getModeratelySteamCracked(1700),
                new FluidStack[]{Materials.Propene.getGas(13), Materials.Ethane.getGas(77), Materials.Ethylene.getGas(157), Materials.Methane.getGas(1732), Materials.Helium.getGas(34)},
                Materials.Carbon.getDustTiny(1), 204, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Gas.getSeverelySteamCracked(800),
                new FluidStack[]{Materials.Propene.getGas(6), Materials.Ethane.getGas(6), Materials.Ethylene.getGas(20), Materials.Methane.getGas(914), Materials.Helium.getGas(16)},
                Materials.Carbon.getDustTiny(1), 96, 120);

        GT_Values.RA.addUniversalDistillationRecipe(Materials.Naphtha.getLightlyHydroCracked(1000),
                new FluidStack[]{Materials.Butane.getGas(800), Materials.Propane.getGas(300), Materials.Ethane.getGas(250), Materials.Methane.getGas(250)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Naphtha.getModeratelyHydroCracked(1000),
                new FluidStack[]{Materials.Butane.getGas(200), Materials.Propane.getGas(1100), Materials.Ethane.getGas(400), Materials.Methane.getGas(400)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Naphtha.getSeverelyHydroCracked(1000),
                new FluidStack[]{Materials.Butane.getGas(125), Materials.Propane.getGas(125), Materials.Ethane.getGas(1500), Materials.Methane.getGas(1500)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Naphtha.getLightlySteamCracked(1000),
                new FluidStack[]{Materials.HeavyFuel.getFluid(75), Materials.LightFuel.getFluid(150), Materials.Toluene.getFluid(40), Materials.Benzene.getFluid(150), Materials.Butene.getGas(80),
                        Materials.Butadiene.getGas(150), Materials.Propane.getGas(15), Materials.Propene.getGas(200), Materials.Ethane.getGas(35), Materials.Ethylene.getGas(200), Materials.Methane.getGas(200)},
                Materials.Carbon.getDustTiny(1), 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Naphtha.getModeratelySteamCracked(1000),
                new FluidStack[]{Materials.HeavyFuel.getFluid(50), Materials.LightFuel.getFluid(100), Materials.Toluene.getFluid(30), Materials.Benzene.getFluid(125), Materials.Butene.getGas(65),
                        Materials.Butadiene.getGas(100), Materials.Propane.getGas(30), Materials.Propene.getGas(400), Materials.Ethane.getGas(50), Materials.Ethylene.getGas(350), Materials.Methane.getGas(350)},
                Materials.Carbon.getDustTiny(2), 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.Naphtha.getSeverelySteamCracked(1000),
                new FluidStack[]{Materials.HeavyFuel.getFluid(25), Materials.LightFuel.getFluid(50), Materials.Toluene.getFluid(20), Materials.Benzene.getFluid(100), Materials.Butene.getGas(50),
                        Materials.Butadiene.getGas(50), Materials.Propane.getGas(15), Materials.Propene.getGas(300), Materials.Ethane.getGas(65), Materials.Ethylene.getGas(500), Materials.Methane.getGas(500)},
                Materials.Carbon.getDustTiny(3), 120, 120);

        GT_Values.RA.addUniversalDistillationRecipe(Materials.LightFuel.getLightlyHydroCracked(1000),
                new FluidStack[]{Materials.Naphtha.getFluid(800), Materials.Butane.getGas(150), Materials.Propane.getGas(200), Materials.Ethane.getGas(125), Materials.Methane.getGas(125)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.LightFuel.getModeratelyHydroCracked(1000),
                new FluidStack[]{Materials.Naphtha.getFluid(500), Materials.Butane.getGas(200), Materials.Propane.getGas(1100), Materials.Ethane.getGas(400), Materials.Methane.getGas(400)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.LightFuel.getSeverelyHydroCracked(1000),
                new FluidStack[]{Materials.Naphtha.getFluid(200), Materials.Butane.getGas(125), Materials.Propane.getGas(125), Materials.Ethane.getGas(1500), Materials.Methane.getGas(1500)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.LightFuel.getLightlySteamCracked(1000),
                new FluidStack[]{Materials.HeavyFuel.getFluid(150), Materials.Naphtha.getFluid(400), Materials.Toluene.getFluid(40), Materials.Benzene.getFluid(200), Materials.Butene.getGas(75),
                        Materials.Butadiene.getGas(60), Materials.Propane.getGas(20), Materials.Propene.getGas(150), Materials.Ethane.getGas(10), Materials.Ethylene.getGas(50), Materials.Methane.getGas(50)},
                Materials.Carbon.getDustTiny(1), 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.LightFuel.getModeratelySteamCracked(1000),
                new FluidStack[]{Materials.HeavyFuel.getFluid(100), Materials.Naphtha.getFluid(250), Materials.Toluene.getFluid(50), Materials.Benzene.getFluid(300), Materials.Butene.getGas(90),
                        Materials.Butadiene.getGas(75), Materials.Propane.getGas(35), Materials.Propene.getGas(200), Materials.Ethane.getGas(30), Materials.Ethylene.getGas(150), Materials.Methane.getGas(150)},
                Materials.Carbon.getDustTiny(2), 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.LightFuel.getSeverelySteamCracked(1000),
                new FluidStack[]{Materials.HeavyFuel.getFluid(50), Materials.Naphtha.getFluid(100), Materials.Toluene.getFluid(30), Materials.Benzene.getFluid(150), Materials.Butene.getGas(65),
                        Materials.Butadiene.getGas(50), Materials.Propane.getGas(50), Materials.Propene.getGas(250), Materials.Ethane.getGas(50), Materials.Ethylene.getGas(250), Materials.Methane.getGas(250)},
                Materials.Carbon.getDustTiny(3), 120, 120);

        GT_Values.RA.addUniversalDistillationRecipe(Materials.HeavyFuel.getLightlyHydroCracked(1000),
                new FluidStack[]{Materials.LightFuel.getFluid(600), Materials.Naphtha.getFluid(100), Materials.Butane.getGas(100), Materials.Propane.getGas(100), Materials.Ethane.getGas(75), Materials.Methane.getGas(75)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.HeavyFuel.getModeratelyHydroCracked(1000),
                new FluidStack[]{Materials.LightFuel.getFluid(400), Materials.Naphtha.getFluid(400), Materials.Butane.getGas(150), Materials.Propane.getGas(150), Materials.Ethane.getGas(100), Materials.Methane.getGas(100)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.HeavyFuel.getSeverelyHydroCracked(1000),
                new FluidStack[]{Materials.LightFuel.getFluid(200), Materials.Naphtha.getFluid(250), Materials.Butane.getGas(300), Materials.Propane.getGas(300), Materials.Ethane.getGas(175), Materials.Methane.getGas(175)},
                GT_Values.NI, 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.HeavyFuel.getLightlySteamCracked(1000),
                new FluidStack[]{Materials.LightFuel.getFluid(300), Materials.Naphtha.getFluid(50), Materials.Toluene.getFluid(25), Materials.Benzene.getFluid(125), Materials.Butene.getGas(25),
                        Materials.Butadiene.getGas(15), Materials.Propane.getGas(3), Materials.Propene.getGas(30), Materials.Ethane.getGas(5), Materials.Ethylene.getGas(50), Materials.Methane.getGas(50)},
                Materials.Carbon.getDustTiny(1), 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.HeavyFuel.getModeratelySteamCracked(1000),
                new FluidStack[]{Materials.LightFuel.getFluid(200), Materials.Naphtha.getFluid(200), Materials.Toluene.getFluid(40), Materials.Benzene.getFluid(200), Materials.Butene.getGas(40),
                        Materials.Butadiene.getGas(25), Materials.Propane.getGas(5), Materials.Propene.getGas(50), Materials.Ethane.getGas(7), Materials.Ethylene.getGas(75), Materials.Methane.getGas(75)},
                Materials.Carbon.getDustTiny(2), 120, 120);
        GT_Values.RA.addUniversalDistillationRecipe(Materials.HeavyFuel.getSeverelySteamCracked(1000),
                new FluidStack[]{Materials.LightFuel.getFluid(100), Materials.Naphtha.getFluid(125), Materials.Toluene.getFluid(80), Materials.Benzene.getFluid(400), Materials.Butene.getGas(80),
                        Materials.Butadiene.getGas(50), Materials.Propane.getGas(10), Materials.Propene.getGas(100), Materials.Ethane.getGas(15), Materials.Ethylene.getGas(150), Materials.Methane.getGas(150)},
                Materials.Carbon.getDustTiny(3), 120, 120);
    }

    public void addPotionRecipes(String aName, ItemStack aItem) {
        //normal
        GT_Values.RA.addBrewingRecipe(aItem, FluidRegistry.getFluid("potion.awkward"), FluidRegistry.getFluid("potion." + aName), false);
        //strong
        GT_Values.RA.addBrewingRecipe(aItem, FluidRegistry.getFluid("potion.thick"), FluidRegistry.getFluid("potion." + aName + ".strong"), false);
        //long
        GT_Values.RA.addBrewingRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Redstone), FluidRegistry.getFluid("potion." + aName), FluidRegistry.getFluid("potion." + aName + ".long"), false);
        //splash
        if (!(FluidRegistry.getFluid("potion." + aName) == null || FluidRegistry.getFluid("potion." + aName + ".splash") == null))
            GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Gunpowder), null, null, null, new FluidStack(FluidRegistry.getFluid("potion." + aName), 750), new FluidStack(FluidRegistry.getFluid("potion." + aName + ".splash"), 750), null, 200, 24);
        //splash strong
        if (!(FluidRegistry.getFluid("potion." + aName + ".strong") == null || FluidRegistry.getFluid("potion." + aName + ".strong.splash") == null))
            GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Gunpowder), null, null, null, new FluidStack(FluidRegistry.getFluid("potion." + aName + ".strong"), 750), new FluidStack(FluidRegistry.getFluid("potion." + aName + ".strong.splash"), 750), null, 200, 24);
        //splash long
        if (!(FluidRegistry.getFluid("potion." + aName + ".long") == null || FluidRegistry.getFluid("potion." + aName + ".long.splash") == null))
            GT_Values.RA.addMixerRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Gunpowder), null, null, null, new FluidStack(FluidRegistry.getFluid("potion." + aName + ".long"), 750), new FluidStack(FluidRegistry.getFluid("potion." + aName + ".long.splash"), 750), null, 200, 24);
    }

    private void addPrimitiveBlastFurnaceRecipes() {
        GT_Values.RA.addPrimitiveBlastRecipe(Materials.Iron.getIngots(1), GT_Values.NI, 4, Materials.Steel.getIngots(1), GT_Values.NI, 7200);
        GT_Values.RA.addPrimitiveBlastRecipe(Materials.Iron.getDust(1), GT_Values.NI, 4, Materials.Steel.getIngots(1), GT_Values.NI, 7200);
        GT_Values.RA.addPrimitiveBlastRecipe(Materials.Iron.getBlocks(1), GT_Values.NI, 36, Materials.Steel.getIngots(9), GT_Values.NI, 64800);
        GT_Values.RA.addPrimitiveBlastRecipe(Materials.Steel.getDust(1), GT_Values.NI, 2, Materials.Steel.getIngots(1), GT_Values.NI, 7200);

    }

}
