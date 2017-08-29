package gregtech.loaders.load;

import cpw.mods.fml.common.Loader;
import gregtech.GT_Mod;
import gregtech.api.GregTech_API;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_Log;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.MatUnifier;
import gregtech.api.util.GT_Recipe;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GT_FuelLoader
        implements Runnable {
    public void run() {
        GT_Log.out.println("GT_Mod: Initializing various Fuels.");
        ItemList.sNitricAcid = GT_Mod.gregtechproxy.addFluid("nitricacid", "Nitric Acid ", Materials.NitricAcid, 1, 295, MatUnifier.get(OrePrefixes.cell, Materials.NitricAcid, 1), ItemList.Cell_Empty.get(1), 1000);
        ItemList.sBlueVitriol = GT_Mod.gregtechproxy.addFluid("solution.bluevitriol", "Blue Vitriol water solution", null, 1, 295);
        ItemList.sNickelSulfate = GT_Mod.gregtechproxy.addFluid("solution.nickelsulfate", "Nickel sulfate water solution", null, 1, 295);
        ItemList.sIndiumConcentrate = GT_Mod.gregtechproxy.addFluid("indiumconcentrate", "Indium Concentrate", null, 1, 295);
        ItemList.sLeadZincSolution = GT_Mod.gregtechproxy.addFluid("leadzincsolution", "Lead-Zinc solution", null, 1, 295);
        ItemList.sRocketFuel = GT_Mod.gregtechproxy.addFluid("rocket_fuel", "Rocket Fuel", null, 1, 295);
        new GT_Recipe(new ItemStack(Items.lava_bucket), new ItemStack(Blocks.obsidian), MatUnifier.get(OrePrefixes.ingot, Materials.Copper), MatUnifier.get(OrePrefixes.ingot, Materials.Tin), MatUnifier.get(OrePrefixes.ingot, Materials.Electrum), 30, 2);

        GT_Recipe.GT_Recipe_Map.sSmallNaquadahReactorFuels.addRecipe(true, new ItemStack[]{MatUnifier.get(OrePrefixes.bolt, Materials.NaquadahEnriched)}, new ItemStack[]{MatUnifier.get(OrePrefixes.bolt, Materials.Naquadah)}, null, null, null, 0, 0, 25000);
        GT_Recipe.GT_Recipe_Map.sLargeNaquadahReactorFuels.addRecipe(true, new ItemStack[]{MatUnifier.get(OrePrefixes.ingot, Materials.NaquadahEnriched)}, new ItemStack[]{MatUnifier.get(OrePrefixes.ingot, Materials.Naquadah)}, null, null, null, 0, 0, 200000);
        GT_Recipe.GT_Recipe_Map.sFluidNaquadahReactorFuels.addRecipe(true, new ItemStack[]{MatUnifier.get(OrePrefixes.cell, Materials.NaquadahEnriched)}, new ItemStack[]{MatUnifier.get(OrePrefixes.cell,  Materials.Naquadah)}, null, null, null, 0, 0, 1400000);

        GT_Values.RA.addFuel(GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 1, 4), null, 4, 5);
        GT_Values.RA.addFuel(new ItemStack(Items.experience_bottle, 1), null, 10, 5);
        GT_Values.RA.addFuel(new ItemStack(Items.ghast_tear, 1), null, 50, 5);
        GT_Values.RA.addFuel(new ItemStack(Blocks.beacon, 1), null, Materials.NetherStar.mFuelPower * 2, Materials.NetherStar.mFuelType);
        GT_Values.RA.addFuel(GT_ModHandler.getModItem("EnderIO", "bucketRocket_fuel", 1), null, 250, 1);
        if(GregTech_API.mMagneticraft){
        	 GT_Values.RA.addFuel(GT_ModHandler.getModItem("Magneticraft", "item.bucket_light_oil", 1), null, 256, 0);
        	 GT_Values.RA.addFuel(GT_ModHandler.getModItem("Magneticraft", "item.bucket_heavy_oil", 1), null, 192, 3);
        }
        if(GregTech_API.mImmersiveEngineering){
       	 GT_Values.RA.addFuel(GT_ModHandler.getModItem("ImmersiveEngineering", "fluidContainers", 1, 7), null, 128, 0);
        }
        if(Loader.isModLoaded("PneumaticCraft")){
          	 GT_Values.RA.addFuel(GT_ModHandler.getModItem("PneumaticCraft", "pgBucket", 1), null, 512, 1);
          	 GT_Values.RA.addFuel(GT_ModHandler.getModItem("PneumaticCraft", "fuelBucket", 1), null, 400, 0);
          	 GT_Values.RA.addFuel(GT_ModHandler.getModItem("PneumaticCraft", "fuelBucket", 1, 1), null, 400, 0);
          	 GT_Values.RA.addFuel(GT_ModHandler.getModItem("PneumaticCraft", "keroseneBucket", 1), null, 256, 0);
          	 GT_Values.RA.addFuel(GT_ModHandler.getModItem("PneumaticCraft", "dieselBucket", 1), null, 200, 0);        	
        }
    }
}
