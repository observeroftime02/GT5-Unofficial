package gregtech.loaders.preload;

import gregtech.api.GregTech_API;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OreDictNames;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_Log;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.MatUnifier;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GT_Loader_OreDictionary implements Runnable {
    public void run() {
        GT_Log.out.println("GT_Mod: Register OreDict Entries of Non-GT-Items.");
        MatUnifier.set(OrePrefixes.cell, Materials.Creosote, GT_ModHandler.getModItem("Railcraft", "fluid.creosote.cell", 1));

        MatUnifier.set(OrePrefixes.bucket, Materials.Empty, new ItemStack(Items.bucket, 1, 0));
        MatUnifier.set(OrePrefixes.bucket, Materials.Water, new ItemStack(Items.water_bucket, 1, 0));
        MatUnifier.set(OrePrefixes.bucket, Materials.Lava, new ItemStack(Items.lava_bucket, 1, 0));
        MatUnifier.set(OrePrefixes.bucket, Materials.Milk, new ItemStack(Items.milk_bucket, 1, 0));

        MatUnifier.set(OrePrefixes.bottle, Materials.Empty, new ItemStack(Items.glass_bottle, 1, 0));
        MatUnifier.set(OrePrefixes.bottle, Materials.Water, new ItemStack(Items.potionitem, 1, 0));

        MatUnifier.set(OrePrefixes.ore, Materials.Coal, new ItemStack(Blocks.coal_ore, 1));
        MatUnifier.set(OrePrefixes.ore, Materials.Iron, new ItemStack(Blocks.iron_ore, 1));
        MatUnifier.set(OrePrefixes.ore, Materials.Lapis, new ItemStack(Blocks.lapis_ore, 1));
        MatUnifier.set(OrePrefixes.ore, Materials.Redstone, new ItemStack(Blocks.redstone_ore, 1));
        MatUnifier.set(OrePrefixes.ore, Materials.Redstone, new ItemStack(Blocks.lit_redstone_ore, 1));
        MatUnifier.set(OrePrefixes.ore, Materials.Gold, new ItemStack(Blocks.gold_ore, 1));
        MatUnifier.set(OrePrefixes.ore, Materials.Diamond, new ItemStack(Blocks.diamond_ore, 1));
        MatUnifier.set(OrePrefixes.ore, Materials.Emerald, new ItemStack(Blocks.emerald_ore, 1));
        MatUnifier.set(OrePrefixes.ore, Materials.NetherQuartz, new ItemStack(Blocks.quartz_ore, 1));
        MatUnifier.set(OrePrefixes.gem, Materials.Lapis, new ItemStack(Items.dye, 1, 4));
        MatUnifier.set(OrePrefixes.gem, Materials.EnderEye, new ItemStack(Items.ender_eye, 1));
        MatUnifier.set(OrePrefixes.gem, Materials.EnderPearl, new ItemStack(Items.ender_pearl, 1));
        MatUnifier.set(OrePrefixes.gem, Materials.Diamond, new ItemStack(Items.diamond, 1));
        MatUnifier.set(OrePrefixes.gem, Materials.Emerald, new ItemStack(Items.emerald, 1));
        MatUnifier.set(OrePrefixes.gem, Materials.Coal, new ItemStack(Items.coal, 1, 0));
        MatUnifier.set(OrePrefixes.gem, Materials.Charcoal, new ItemStack(Items.coal, 1, 1));
        MatUnifier.set(OrePrefixes.gem, Materials.NetherQuartz, new ItemStack(Items.quartz, 1));
        MatUnifier.set(OrePrefixes.gem, Materials.NetherStar, new ItemStack(Items.nether_star, 1));
        MatUnifier.set(OrePrefixes.nugget, Materials.Gold, new ItemStack(Items.gold_nugget, 1));
        MatUnifier.set(OrePrefixes.ingot, Materials.Gold, new ItemStack(Items.gold_ingot, 1));
        MatUnifier.set(OrePrefixes.ingot, Materials.Iron, new ItemStack(Items.iron_ingot, 1));
        MatUnifier.set(OrePrefixes.plate, Materials.Paper, new ItemStack(Items.paper, 1));
        MatUnifier.set(OrePrefixes.dust, Materials.Sugar, new ItemStack(Items.sugar, 1));
        MatUnifier.set(OrePrefixes.dust, Materials.Bone, ItemList.Dye_Bonemeal.get(1));
        MatUnifier.set(OrePrefixes.stick, Materials.Wood, new ItemStack(Items.stick, 1));
        MatUnifier.set(OrePrefixes.dust, Materials.Redstone, new ItemStack(Items.redstone, 1));
        MatUnifier.set(OrePrefixes.dust, Materials.Gunpowder, new ItemStack(Items.gunpowder, 1));
        MatUnifier.set(OrePrefixes.dust, Materials.Glowstone, new ItemStack(Items.glowstone_dust, 1));
        MatUnifier.set(OrePrefixes.dust, Materials.Blaze, new ItemStack(Items.blaze_powder, 1));
        MatUnifier.set(OrePrefixes.stick, Materials.Blaze, new ItemStack(Items.blaze_rod, 1));
        MatUnifier.set(OrePrefixes.block, Materials.Iron, new ItemStack(Blocks.iron_block, 1, 0));
        MatUnifier.set(OrePrefixes.block, Materials.Gold, new ItemStack(Blocks.gold_block, 1, 0));
        MatUnifier.set(OrePrefixes.block, Materials.Diamond, new ItemStack(Blocks.diamond_block, 1, 0));
        MatUnifier.set(OrePrefixes.block, Materials.Emerald, new ItemStack(Blocks.emerald_block, 1, 0));
        MatUnifier.set(OrePrefixes.block, Materials.Lapis, new ItemStack(Blocks.lapis_block, 1, 0));
        MatUnifier.set(OrePrefixes.block, Materials.Coal, new ItemStack(Blocks.coal_block, 1, 0));
        MatUnifier.set(OrePrefixes.block, Materials.Redstone, new ItemStack(Blocks.redstone_block, 1, 0));
        if (Blocks.ender_chest != null) {
            MatUnifier.registerOre(OreDictNames.enderChest, new ItemStack(Blocks.ender_chest, 1));
        }
        MatUnifier.registerOre(OreDictNames.craftingAnvil, new ItemStack(Blocks.anvil, 1));
        MatUnifier.registerOre(OreDictNames.craftingAnvil, GT_ModHandler.getModItem("Railcraft", "tile.railcraft.anvil", 1, 0));
        MatUnifier.registerOre(OrePrefixes.glass, Materials.Reinforced, GT_ModHandler.getModItem("ThermalExpansion", "glassHardened", 1));

        MatUnifier.registerOre(OrePrefixes.stone, Materials.Basalt, GT_ModHandler.getModItem("Railcraft", "tile.railcraft.cube", 1, 6));
        MatUnifier.registerOre(OrePrefixes.stone, Materials.Marble, GT_ModHandler.getModItem("Railcraft", "tile.railcraft.cube", 1, 7));
        MatUnifier.registerOre(OrePrefixes.stone, Materials.Basalt, GT_ModHandler.getModItem("Railcraft", "tile.railcraft.brick.abyssal", 1, 32767));
        MatUnifier.registerOre(OrePrefixes.stone, Materials.Marble, GT_ModHandler.getModItem("Railcraft", "tile.railcraft.brick.quarried", 1, 32767));
        MatUnifier.registerOre(OrePrefixes.stone, Materials.Obsidian, new ItemStack(Blocks.obsidian, 1, 32767));
        MatUnifier.registerOre(OrePrefixes.stoneMossy, new ItemStack(Blocks.mossy_cobblestone, 1, 32767));
        MatUnifier.registerOre(OrePrefixes.stoneCobble, new ItemStack(Blocks.mossy_cobblestone, 1, 32767));
        MatUnifier.registerOre(OrePrefixes.stoneCobble, new ItemStack(Blocks.cobblestone, 1, 32767));
        MatUnifier.registerOre(OrePrefixes.stoneSmooth, new ItemStack(Blocks.stone, 1, 32767));
        MatUnifier.registerOre(OrePrefixes.stoneBricks, new ItemStack(Blocks.stonebrick, 1, 32767));
        MatUnifier.registerOre(OrePrefixes.stoneMossy, new ItemStack(Blocks.stonebrick, 1, 1));
        MatUnifier.registerOre(OrePrefixes.stoneCracked, new ItemStack(Blocks.stonebrick, 1, 2));
        MatUnifier.registerOre(OrePrefixes.stoneChiseled, new ItemStack(Blocks.stonebrick, 1, 3));
        MatUnifier.registerOre(OrePrefixes.stone, Materials.Sand, new ItemStack(Blocks.sandstone, 1, 32767));
        MatUnifier.registerOre(OrePrefixes.stone, Materials.Netherrack, new ItemStack(Blocks.netherrack, 1, 32767));
        MatUnifier.registerOre(OrePrefixes.stone, Materials.NetherBrick, new ItemStack(Blocks.nether_brick, 1, 32767));
        MatUnifier.registerOre(OrePrefixes.stone, Materials.Endstone, new ItemStack(Blocks.end_stone, 1, 32767));

        MatUnifier.registerOre("paperResearchFragment", GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 1, 9));
        MatUnifier.registerOre("itemCertusQuartz", GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1, 1));
        MatUnifier.registerOre("itemCertusQuartz", GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1, 10));
        MatUnifier.registerOre("itemNetherQuartz", GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1, 11));
        MatUnifier.registerOre(OreDictNames.craftingQuartz, GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1, 1));
        MatUnifier.registerOre(OreDictNames.craftingQuartz, GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1, 10));
        MatUnifier.registerOre(OreDictNames.craftingQuartz, GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1, 11));
        MatUnifier.registerOre("cropLemon", ItemList.FR_Lemon.get(1));
        MatUnifier.registerOre("cropPotato", new ItemStack(Items.baked_potato));

        MatUnifier.registerOre(OreDictNames.craftingRedstoneTorch, new ItemStack(Blocks.redstone_torch, 1, 32767));
        MatUnifier.registerOre(OreDictNames.craftingRedstoneTorch, new ItemStack(Blocks.unlit_redstone_torch, 1, 32767));

        MatUnifier.registerOre(OreDictNames.craftingWorkBench, new ItemStack(Blocks.crafting_table, 1));
        MatUnifier.registerOre(OreDictNames.craftingWorkBench, new ItemStack(GregTech_API.sBlockMachines, 1, 16));

        MatUnifier.registerOre(OreDictNames.craftingPiston, new ItemStack(Blocks.piston, 1, 32767));
        MatUnifier.registerOre(OreDictNames.craftingPiston, new ItemStack(Blocks.sticky_piston, 1, 32767));

        MatUnifier.registerOre(OreDictNames.craftingSafe, new ItemStack(GregTech_API.sBlockMachines, 1, 45));

        MatUnifier.registerOre(OreDictNames.craftingChest, new ItemStack(Blocks.chest, 1, 32767));
        MatUnifier.registerOre(OreDictNames.craftingChest, new ItemStack(Blocks.trapped_chest, 1, 32767));

        MatUnifier.registerOre(OreDictNames.craftingFurnace, new ItemStack(Blocks.furnace, 1, 32767));
        MatUnifier.registerOre(OreDictNames.craftingFurnace, new ItemStack(Blocks.lit_furnace, 1, 32767));

        MatUnifier.registerOre(OreDictNames.craftingMacerator, new ItemStack(GregTech_API.sBlockMachines, 1, 50));

        MatUnifier.registerOre(OreDictNames.craftingExtractor, new ItemStack(GregTech_API.sBlockMachines, 1, 51));

        MatUnifier.registerOre(OreDictNames.craftingCompressor, new ItemStack(GregTech_API.sBlockMachines, 1, 52));

        MatUnifier.registerOre(OreDictNames.craftingRecycler, new ItemStack(GregTech_API.sBlockMachines, 1, 53));

        MatUnifier.registerOre(OreDictNames.craftingCentrifuge, new ItemStack(GregTech_API.sBlockMachines, 1, 62));

        MatUnifier.registerOre(OreDictNames.craftingElectricFurnace, new ItemStack(GregTech_API.sBlockMachines, 1, 54));

        MatUnifier.registerOre(OreDictNames.craftingFeather, new ItemStack(Items.feather, 1, 32767));
        MatUnifier.registerOre(OreDictNames.craftingFeather, GT_ModHandler.getModItem("TwilightForest", "item.tfFeather", 1, 32767));

        MatUnifier.registerOre("itemWheat", new ItemStack(Items.wheat, 1, 32767));
        MatUnifier.registerOre("paperEmpty", new ItemStack(Items.paper, 1, 32767));
        MatUnifier.registerOre("paperMap", new ItemStack(Items.map, 1, 32767));
        MatUnifier.registerOre("paperMap", new ItemStack(Items.filled_map, 1, 32767));
        MatUnifier.registerOre("bookEmpty", new ItemStack(Items.book, 1, 32767));
        MatUnifier.registerOre("bookWritable", new ItemStack(Items.writable_book, 1, 32767));
        MatUnifier.registerOre("bookWritten", new ItemStack(Items.written_book, 1, 32767));
        MatUnifier.registerOre("bookEnchanted", new ItemStack(Items.enchanted_book, 1, 32767));
        MatUnifier.registerOre(OreDictNames.craftingBook, new ItemStack(Items.book, 1, 32767));
        MatUnifier.registerOre(OreDictNames.craftingBook, new ItemStack(Items.writable_book, 1, 32767));
        MatUnifier.registerOre(OreDictNames.craftingBook, new ItemStack(Items.written_book, 1, 32767));
        MatUnifier.registerOre(OreDictNames.craftingBook, new ItemStack(Items.enchanted_book, 1, 32767));
    }
}
