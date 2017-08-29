package gregtech.loaders.postload;

import gregtech.GT_Mod;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_Log;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.MatUnifier;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GT_ScrapboxDropLoader
        implements Runnable {
    public void run() {
        GT_Log.out.println("GT_Mod: (re-)adding Scrapbox Drops.");

        GT_ModHandler.addScrapboxDrop(9.5F, new ItemStack(Items.wooden_hoe));
        GT_ModHandler.addScrapboxDrop(2.0F, new ItemStack(Items.wooden_axe));
        GT_ModHandler.addScrapboxDrop(2.0F, new ItemStack(Items.wooden_sword));
        GT_ModHandler.addScrapboxDrop(2.0F, new ItemStack(Items.wooden_shovel));
        GT_ModHandler.addScrapboxDrop(2.0F, new ItemStack(Items.wooden_pickaxe));
        GT_ModHandler.addScrapboxDrop(2.0F, new ItemStack(Items.sign));
        GT_ModHandler.addScrapboxDrop(9.5F, new ItemStack(Items.stick));
        GT_ModHandler.addScrapboxDrop(5.0F, new ItemStack(Blocks.dirt));
        GT_ModHandler.addScrapboxDrop(3.0F, new ItemStack(Blocks.grass));
        GT_ModHandler.addScrapboxDrop(3.0F, new ItemStack(Blocks.gravel));
        GT_ModHandler.addScrapboxDrop(0.5F, new ItemStack(Blocks.pumpkin));
        GT_ModHandler.addScrapboxDrop(1.0F, new ItemStack(Blocks.soul_sand));
        GT_ModHandler.addScrapboxDrop(2.0F, new ItemStack(Blocks.netherrack));
        GT_ModHandler.addScrapboxDrop(1.0F, new ItemStack(Items.bone));
        GT_ModHandler.addScrapboxDrop(9.0F, new ItemStack(Items.rotten_flesh));
        GT_ModHandler.addScrapboxDrop(0.4F, new ItemStack(Items.cooked_porkchop));
        GT_ModHandler.addScrapboxDrop(0.4F, new ItemStack(Items.cooked_beef));
        GT_ModHandler.addScrapboxDrop(0.4F, new ItemStack(Items.cooked_chicken));
        GT_ModHandler.addScrapboxDrop(0.5F, new ItemStack(Items.apple));
        GT_ModHandler.addScrapboxDrop(0.5F, new ItemStack(Items.bread));
        GT_ModHandler.addScrapboxDrop(0.1F, new ItemStack(Items.cake));
        GT_ModHandler.addScrapboxDrop(1.0F, ItemList.IC2_Food_Can_Filled.get(1));
        GT_ModHandler.addScrapboxDrop(2.0F, ItemList.IC2_Food_Can_Spoiled.get(1));
        GT_ModHandler.addScrapboxDrop(0.2F, MatUnifier.get(OrePrefixes.dust, Materials.Silicon));
        GT_ModHandler.addScrapboxDrop(1.0F, MatUnifier.get(OrePrefixes.cell, Materials.Water));
        GT_ModHandler.addScrapboxDrop(2.0F, ItemList.Cell_Empty.get(1));
        GT_ModHandler.addScrapboxDrop(5.0F, MatUnifier.get(OrePrefixes.plate, Materials.Paper));
        GT_ModHandler.addScrapboxDrop(1.0F, new ItemStack(Items.leather));
        GT_ModHandler.addScrapboxDrop(1.0F, new ItemStack(Items.feather));
        GT_ModHandler.addScrapboxDrop(0.7F, GT_ModHandler.getIC2Item("plantBall", 1));
        GT_ModHandler.addScrapboxDrop(3.8F, MatUnifier.get(OrePrefixes.dust, Materials.Wood));
        GT_ModHandler.addScrapboxDrop(0.6F, new ItemStack(Items.slime_ball));
        GT_ModHandler.addScrapboxDrop(0.8F, MatUnifier.get(OrePrefixes.dust, Materials.Rubber));
        GT_ModHandler.addScrapboxDrop(2.7F, GT_ModHandler.getIC2Item("suBattery", 1));
        GT_ModHandler.addScrapboxDrop(3.6F, ItemList.Circuit_Primitive.get(1));
        GT_ModHandler.addScrapboxDrop(0.8F, ItemList.Circuit_Parts_Advanced.get(1));
        GT_ModHandler.addScrapboxDrop(1.8F, ItemList.Circuit_Board_Basic.get(1));
        GT_ModHandler.addScrapboxDrop(0.4F, ItemList.Circuit_Board_Advanced.get(1));
        GT_ModHandler.addScrapboxDrop(0.2F, ItemList.Circuit_Board_Elite.get(1));
        if (!GT_Mod.gregtechproxy.mDisableIC2Cables) {
            GT_ModHandler.addScrapboxDrop(2.0F, GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1));
            GT_ModHandler.addScrapboxDrop(0.4F, GT_ModHandler.getIC2Item("insulatedGoldCableItem", 1));
        }
        GT_ModHandler.addScrapboxDrop(0.9F, MatUnifier.get(OrePrefixes.dust, Materials.Redstone));
        GT_ModHandler.addScrapboxDrop(0.8F, MatUnifier.get(OrePrefixes.dust, Materials.Glowstone));
        GT_ModHandler.addScrapboxDrop(0.8F, MatUnifier.get(OrePrefixes.dust, Materials.Coal));
        GT_ModHandler.addScrapboxDrop(2.5F, MatUnifier.get(OrePrefixes.dust, Materials.Charcoal));
        GT_ModHandler.addScrapboxDrop(1.0F, MatUnifier.get(OrePrefixes.dust, Materials.Iron));
        GT_ModHandler.addScrapboxDrop(1.0F, MatUnifier.get(OrePrefixes.dust, Materials.Gold));
        GT_ModHandler.addScrapboxDrop(0.5F, MatUnifier.get(OrePrefixes.dust, Materials.Silver));
        GT_ModHandler.addScrapboxDrop(0.5F, MatUnifier.get(OrePrefixes.dust, Materials.Electrum));
        GT_ModHandler.addScrapboxDrop(1.2F, MatUnifier.get(OrePrefixes.dust, Materials.Tin));
        GT_ModHandler.addScrapboxDrop(1.2F, MatUnifier.get(OrePrefixes.dust, Materials.Copper));
        GT_ModHandler.addScrapboxDrop(0.5F, MatUnifier.get(OrePrefixes.dust, Materials.Bauxite));
        GT_ModHandler.addScrapboxDrop(0.5F, MatUnifier.get(OrePrefixes.dust, Materials.Aluminium));
        GT_ModHandler.addScrapboxDrop(0.5F, MatUnifier.get(OrePrefixes.dust, Materials.Lead));
        GT_ModHandler.addScrapboxDrop(0.5F, MatUnifier.get(OrePrefixes.dust, Materials.Nickel));
        GT_ModHandler.addScrapboxDrop(0.5F, MatUnifier.get(OrePrefixes.dust, Materials.Zinc));
        GT_ModHandler.addScrapboxDrop(0.5F, MatUnifier.get(OrePrefixes.dust, Materials.Brass));
        GT_ModHandler.addScrapboxDrop(0.5F, MatUnifier.get(OrePrefixes.dust, Materials.Steel));
        GT_ModHandler.addScrapboxDrop(1.5F, MatUnifier.get(OrePrefixes.dust, Materials.Obsidian));
        GT_ModHandler.addScrapboxDrop(1.5F, MatUnifier.get(OrePrefixes.dust, Materials.Sulfur));
        GT_ModHandler.addScrapboxDrop(2.0F, MatUnifier.get(OrePrefixes.dust, Materials.Saltpeter));
        GT_ModHandler.addScrapboxDrop(2.0F, MatUnifier.get(OrePrefixes.dust, Materials.Lazurite));
        GT_ModHandler.addScrapboxDrop(2.0F, MatUnifier.get(OrePrefixes.dust, Materials.Pyrite));
        GT_ModHandler.addScrapboxDrop(2.0F, MatUnifier.get(OrePrefixes.dust, Materials.Calcite));
        GT_ModHandler.addScrapboxDrop(2.0F, MatUnifier.get(OrePrefixes.dust, Materials.Sodalite));
        GT_ModHandler.addScrapboxDrop(4.0F, MatUnifier.get(OrePrefixes.dust, Materials.Netherrack));
        GT_ModHandler.addScrapboxDrop(4.0F, MatUnifier.get(OrePrefixes.dust, Materials.Flint));
        GT_ModHandler.addScrapboxDrop(0.03F, MatUnifier.get(OrePrefixes.dust, Materials.Platinum));
        GT_ModHandler.addScrapboxDrop(0.03F, MatUnifier.get(OrePrefixes.dust, Materials.Tungsten));
        GT_ModHandler.addScrapboxDrop(0.03F, MatUnifier.get(OrePrefixes.dust, Materials.Chrome));
        GT_ModHandler.addScrapboxDrop(0.03F, MatUnifier.get(OrePrefixes.dust, Materials.Titanium));
        GT_ModHandler.addScrapboxDrop(0.03F, MatUnifier.get(OrePrefixes.dust, Materials.Magnesium));
        GT_ModHandler.addScrapboxDrop(0.03F, MatUnifier.get(OrePrefixes.dust, Materials.Endstone));
        GT_ModHandler.addScrapboxDrop(0.5F, MatUnifier.get(OrePrefixes.dust, Materials.GarnetRed));
        GT_ModHandler.addScrapboxDrop(0.5F, MatUnifier.get(OrePrefixes.dust, Materials.GarnetYellow));
        GT_ModHandler.addScrapboxDrop(0.05F, MatUnifier.get(OrePrefixes.gem, Materials.Olivine));
        GT_ModHandler.addScrapboxDrop(0.05F, MatUnifier.get(OrePrefixes.gem, Materials.Ruby));
        GT_ModHandler.addScrapboxDrop(0.05F, MatUnifier.get(OrePrefixes.gem, Materials.Sapphire));
        GT_ModHandler.addScrapboxDrop(0.05F, MatUnifier.get(OrePrefixes.gem, Materials.GreenSapphire));
        GT_ModHandler.addScrapboxDrop(0.05F, MatUnifier.get(OrePrefixes.gem, Materials.Emerald));
        GT_ModHandler.addScrapboxDrop(0.05F, MatUnifier.get(OrePrefixes.gem, Materials.Diamond));
    }
}
