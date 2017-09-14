package gregtech.loaders.load;

import cpw.mods.fml.common.Loader;
import gregtech.GT_Mod;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_Log;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_OreDictUnificator;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GT_FuelLoader
        implements Runnable {
    public void run() {
        GT_Log.out.println("GT_Mod: Initializing various Fuels.");
        ItemList.sNitricAcid = GT_Mod.gregtechproxy.addFluid("nitricacid", "Nitric Acid ", Materials.NitricAcid, 1, 295, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.NitricAcid), ItemList.Cell_Empty.get(1), 1000);
        ItemList.sBlueVitriol = GT_Mod.gregtechproxy.addFluid("solution.bluevitriol", "Blue Vitriol water solution", null, 1, 295);
        ItemList.sNickelSulfate = GT_Mod.gregtechproxy.addFluid("solution.nickelsulfate", "Nickel sulfate water solution", null, 1, 295);
        ItemList.sIndiumConcentrate = GT_Mod.gregtechproxy.addFluid("indiumconcentrate", "Indium Concentrate", null, 1, 295);
        ItemList.sLeadZincSolution = GT_Mod.gregtechproxy.addFluid("leadzincsolution", "Lead-Zinc solution", null, 1, 295);
        ItemList.sRocketFuel = GT_Mod.gregtechproxy.addFluid("rocket_fuel", "Rocket Fuel", null, 1, 295);
        new GT_Recipe(new ItemStack(Items.lava_bucket), new ItemStack(Blocks.obsidian), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tin), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Electrum), 30, 2);

        GT_Recipe.GT_Recipe_Map.sSmallNaquadahReactorFuels.addRecipe(true, new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.NaquadahEnriched)}, new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.Naquadah)}, null, null, null, 0, 0, 25000);
        GT_Recipe.GT_Recipe_Map.sLargeNaquadahReactorFuels.addRecipe(true, new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.NaquadahEnriched)}, new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Naquadah)}, null, null, null, 0, 0, 200000);
        GT_Recipe.GT_Recipe_Map.sFluidNaquadahReactorFuels.addRecipe(true, new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.cell, Materials.NaquadahEnriched)}, new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Naquadah)}, null, null, null, 0, 0, 1400000);

        GT_Values.RA.addFuel(GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 1, 4), null, 4, 5);
        GT_Values.RA.addFuel(new ItemStack(Items.experience_bottle, 1), null, 10, 5);
        GT_Values.RA.addFuel(new ItemStack(Items.ghast_tear, 1), null, 50, 5);
        GT_Values.RA.addFuel(new ItemStack(Blocks.beacon, 1), null, Materials.NetherStar.mFuelPower * 2, Materials.NetherStar.mFuelType);
        GT_Values.RA.addFuel(GT_ModHandler.getModItem("EnderIO", "bucketRocket_fuel", 1), null, 250, 1);

        GT_Recipe.GT_Recipe_Map.sLargeBoilerFakeFuels.addSolidRecipes(
                GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Charcoal),
                GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Charcoal),
                GT_OreDictUnificator.get(OrePrefixes.block, Materials.Charcoal),
                GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal),
                GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Coal),
                GT_OreDictUnificator.get(OrePrefixes.block, Materials.Coal),
                GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Coal),
                GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Carbon),
                GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Lignite),
                GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Lignite),
                GT_OreDictUnificator.get(OrePrefixes.block, Materials.Lignite),
                GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Lignite),
                GT_OreDictUnificator.get(OrePrefixes.log, Materials.Wood),
                GT_OreDictUnificator.get(OrePrefixes.plank, Materials.Wood),
                GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood),
                GT_OreDictUnificator.get(OrePrefixes.slab, Materials.Wood),
                GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood),
                GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sodium),
                GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Lithium),
                GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Caesium),
                GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur),
                GT_OreDictUnificator.get(ItemList.Block_SSFUEL.get(1)),
                GT_OreDictUnificator.get(ItemList.Block_MSSFUEL.get(1)),
                GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Blaze));
        if (Loader.isModLoaded("Thaumcraft")) {
            GT_Recipe.GT_Recipe_Map.sLargeBoilerFakeFuels.addSolidRecipe(GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 1));
        }
    }

}
