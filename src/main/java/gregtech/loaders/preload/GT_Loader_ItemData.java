package gregtech.loaders.preload;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.objects.ItemData;
import gregtech.api.objects.MaterialStack;
import gregtech.api.util.GT_Log;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.MatUnifier;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GT_Loader_ItemData
        implements Runnable {
    public void run() {
        GT_Log.out.println("GT_Mod: Loading Item Data Tags");
        MatUnifier.addItemData(GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1, 10), new ItemData(Materials.CertusQuartz, 1814400));
        MatUnifier.addItemData(GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1, 11), new ItemData(Materials.NetherQuartz, 1814400));
        MatUnifier.addItemData(GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1, 12), new ItemData(Materials.Fluix, 1814400));
        MatUnifier.addItemData(new ItemStack(Blocks.quartz_block, 1, 32767), new ItemData(Materials.NetherQuartz, 14515200));
        MatUnifier.addItemData(GT_ModHandler.getModItem("appliedenergistics2", "tile.BlockQuartz", 1, 32767), new ItemData(Materials.CertusQuartz, 14515200));
        MatUnifier.addItemData(GT_ModHandler.getModItem("appliedenergistics2", "tile.BlockQuartzPillar", 1, 32767), new ItemData(Materials.CertusQuartz, 14515200));
        MatUnifier.addItemData(GT_ModHandler.getModItem("appliedenergistics2", "tile.BlockQuartzChiseled", 1, 32767), new ItemData(Materials.CertusQuartz, 14515200));
        MatUnifier.addItemData(new ItemStack(Items.wheat, 1, 32767), new ItemData(Materials.Wheat, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.hay_block, 1, 32767), new ItemData(Materials.Wheat, 32659200));
        MatUnifier.addItemData(new ItemStack(Items.snowball, 1, 32767), new ItemData(Materials.Snow, 907200));
        MatUnifier.addItemData(new ItemStack(Blocks.snow, 1, 32767), new ItemData(Materials.Snow, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.glowstone, 1, 32767), new ItemData(Materials.Glowstone, 14515200));
        MatUnifier.addItemData(new ItemStack(Blocks.redstone_lamp, 1, 32767), new ItemData(Materials.Glowstone, 14515200, new MaterialStack(Materials.Redstone, OrePrefixes.dust.mMaterialAmount * 4)));
        MatUnifier.addItemData(new ItemStack(Blocks.lit_redstone_lamp, 1, 32767), new ItemData(Materials.Glowstone, 14515200, new MaterialStack(Materials.Redstone, OrePrefixes.dust.mMaterialAmount * 4)));
        MatUnifier.addItemData(new ItemStack(Blocks.ice, 1, 32767), new ItemData(Materials.Ice, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.packed_ice, 1, 32767), new ItemData(Materials.Ice, 7257600));
        MatUnifier.addItemData(new ItemStack(Items.clay_ball, 1, 32767), new ItemData(Materials.Clay, 1814400));
        MatUnifier.addItemData(new ItemStack(Blocks.clay, 1, 32767), new ItemData(Materials.Clay, 7257600));
        MatUnifier.addItemData(new ItemStack(Blocks.hardened_clay, 1, 32767), new ItemData(Materials.Clay, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.stained_hardened_clay, 1, 32767), new ItemData(Materials.Clay, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.brick_block, 1, 32767), new ItemData(Materials.Brick, 3628800));
        MatUnifier.addItemData(new ItemStack(Items.book, 1, 32767), new ItemData(Materials.Paper, 10886400));
        MatUnifier.addItemData(new ItemStack(Items.written_book, 1, 32767), new ItemData(Materials.Paper, 10886400));
        MatUnifier.addItemData(new ItemStack(Items.writable_book, 1, 32767), new ItemData(Materials.Paper, 10886400));
        MatUnifier.addItemData(new ItemStack(Items.enchanted_book, 1, 32767), new ItemData(Materials.Paper, 10886400));
        MatUnifier.addItemData(new ItemStack(Items.golden_apple, 1, 1), new ItemData(Materials.Gold, OrePrefixes.block.mMaterialAmount * 8));
        MatUnifier.addItemData(new ItemStack(Items.golden_apple, 1, 0), new ItemData(Materials.Gold, OrePrefixes.ingot.mMaterialAmount * 8));
        MatUnifier.addItemData(new ItemStack(Items.golden_carrot, 1, 0), new ItemData(Materials.Gold, OrePrefixes.nugget.mMaterialAmount * 8));
        MatUnifier.addItemData(new ItemStack(Items.speckled_melon, 1, 0), new ItemData(Materials.Gold, OrePrefixes.nugget.mMaterialAmount * 8));
        MatUnifier.addItemData(new ItemStack(Items.minecart, 1), new ItemData(Materials.Iron, 18144000));
        MatUnifier.addItemData(new ItemStack(Items.cauldron, 1), new ItemData(Materials.Iron, 25401600));
        MatUnifier.addItemData(new ItemStack(Blocks.iron_bars, 8, 32767), new ItemData(Materials.Iron, 10886400));
        MatUnifier.addItemData(new ItemStack(Blocks.light_weighted_pressure_plate, 1, 32767), new ItemData(Materials.Gold, 7257600));
        MatUnifier.addItemData(new ItemStack(Blocks.heavy_weighted_pressure_plate, 1, 32767), new ItemData(Materials.Iron, 7257600));
        MatUnifier.addItemData(new ItemStack(Blocks.anvil, 1, 0), new ItemData(Materials.Iron, 108864000));
        MatUnifier.addItemData(new ItemStack(Blocks.anvil, 1, 1), new ItemData(Materials.Iron, 72576000));
        MatUnifier.addItemData(new ItemStack(Blocks.anvil, 1, 2), new ItemData(Materials.Iron, 36288000));
        MatUnifier.addItemData(new ItemStack(Blocks.hopper, 1, 32767), new ItemData(Materials.Iron, 18144000, new MaterialStack(Materials.Wood, 29030400)));
        MatUnifier.addItemData(new ItemStack(Blocks.tripwire_hook, 1, 32767), new ItemData(Materials.Iron, OrePrefixes.ring.mMaterialAmount * 2, new MaterialStack(Materials.Wood, 3628800)));
        MatUnifier.addItemData(new ItemStack(Items.glass_bottle, 1), new ItemData(Materials.Glass, 3628800));
        MatUnifier.addItemData(new ItemStack(Items.potionitem, 1, 32767), new ItemData(Materials.Glass, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.stained_glass, 1, 32767), new ItemData(Materials.Glass, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.glass, 1, 32767), new ItemData(Materials.Glass, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.stained_glass_pane, 1, 32767), new ItemData(Materials.Glass, 1360800));
        MatUnifier.addItemData(new ItemStack(Blocks.glass_pane, 1, 32767), new ItemData(Materials.Glass, 1360800));
        MatUnifier.addItemData(new ItemStack(Items.clock, 1, 32767), new ItemData(Materials.Gold, 14515200, new MaterialStack(Materials.Redstone, 3628800)));
        MatUnifier.addItemData(new ItemStack(Items.compass, 1, 32767), new ItemData(Materials.Iron, 14515200, new MaterialStack(Materials.Redstone, 3628800)));
        MatUnifier.addItemData(new ItemStack(Items.iron_horse_armor, 1, 32767), new ItemData(Materials.Iron, 29030400, new MaterialStack(Materials.Leather, 21772800)));
        MatUnifier.addItemData(new ItemStack(Items.golden_horse_armor, 1, 32767), new ItemData(Materials.Gold, 29030400, new MaterialStack(Materials.Leather, 21772800)));
        MatUnifier.addItemData(new ItemStack(Items.diamond_horse_armor, 1, 32767), new ItemData(Materials.Diamond, 29030400, new MaterialStack(Materials.Leather, 21772800)));
        MatUnifier.addItemData(new ItemStack(Items.leather, 1, 32767), new ItemData(Materials.Leather, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.beacon, 1, 32767), new ItemData(Materials.NetherStar, 3628800, new MaterialStack(Materials.Obsidian, 10886400), new MaterialStack(Materials.Glass, 18144000)));
        MatUnifier.addItemData(new ItemStack(Blocks.enchanting_table, 1, 32767), new ItemData(Materials.Diamond, 7257600, new MaterialStack(Materials.Obsidian, 14515200), new MaterialStack(Materials.Paper, 10886400)));
        MatUnifier.addItemData(new ItemStack(Blocks.ender_chest, 1, 32767), new ItemData(Materials.EnderEye, 3628800, new MaterialStack(Materials.Obsidian, 29030400)));
        MatUnifier.addItemData(new ItemStack(Blocks.bookshelf, 1, 32767), new ItemData(Materials.Paper, 32659200, new MaterialStack(Materials.Wood, 21772800)));
        MatUnifier.addItemData(new ItemStack(Blocks.lever, 1, 32767), new ItemData(Materials.Stone, 3628800, new MaterialStack(Materials.Wood, 1814400)));
        MatUnifier.addItemData(new ItemStack(Blocks.ice, 1, 32767), new ItemData(Materials.Ice, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.packed_ice, 1, 32767), new ItemData(Materials.Ice, 7257600));
        MatUnifier.addItemData(new ItemStack(Blocks.snow, 1, 32767), new ItemData(Materials.Snow, 3628800));
        MatUnifier.addItemData(new ItemStack(Items.snowball, 1, 32767), new ItemData(Materials.Snow, 907200));
        MatUnifier.addItemData(new ItemStack(Blocks.snow_layer, 1, 32767), new ItemData(Materials.Snow, -1));
        MatUnifier.addItemData(new ItemStack(Blocks.sand, 1, 32767), new ItemData(Materials.Sand, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.sandstone, 1, 32767), new ItemData(Materials.Sand, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.stone_slab, 1, 0), new ItemData(Materials.Stone, 1814400));
        MatUnifier.addItemData(new ItemStack(Blocks.stone_slab, 1, 8), new ItemData(Materials.Stone, 1814400));
        MatUnifier.addItemData(new ItemStack(Blocks.double_stone_slab, 1, 0), new ItemData(Materials.Stone, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.double_stone_slab, 1, 8), new ItemData(Materials.Stone, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.stone_slab, 1, 1), new ItemData(Materials.Sand, 1814400));
        MatUnifier.addItemData(new ItemStack(Blocks.stone_slab, 1, 9), new ItemData(Materials.Sand, 1814400));
        MatUnifier.addItemData(new ItemStack(Blocks.double_stone_slab, 1, 1), new ItemData(Materials.Sand, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.double_stone_slab, 1, 9), new ItemData(Materials.Sand, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.stone_slab, 1, 2), new ItemData(Materials.Wood, 1814400));
        MatUnifier.addItemData(new ItemStack(Blocks.stone_slab, 1, 10), new ItemData(Materials.Wood, 1814400));
        MatUnifier.addItemData(new ItemStack(Blocks.double_stone_slab, 1, 2), new ItemData(Materials.Wood, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.double_stone_slab, 1, 10), new ItemData(Materials.Wood, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.stone_slab, 1, 3), new ItemData(Materials.Stone, 1814400));
        MatUnifier.addItemData(new ItemStack(Blocks.stone_slab, 1, 11), new ItemData(Materials.Stone, 1814400));
        MatUnifier.addItemData(new ItemStack(Blocks.double_stone_slab, 1, 3), new ItemData(Materials.Stone, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.double_stone_slab, 1, 11), new ItemData(Materials.Stone, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.stone_slab, 1, 5), new ItemData(Materials.Stone, 1814400));
        MatUnifier.addItemData(new ItemStack(Blocks.stone_slab, 1, 13), new ItemData(Materials.Stone, 1814400));
        MatUnifier.addItemData(new ItemStack(Blocks.double_stone_slab, 1, 5), new ItemData(Materials.Stone, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.double_stone_slab, 1, 13), new ItemData(Materials.Stone, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.stone, 1, 32767), new ItemData(Materials.Stone, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.furnace, 1, 32767), new ItemData(Materials.Stone, 29030400));
        MatUnifier.addItemData(new ItemStack(Blocks.lit_furnace, 1, 32767), new ItemData(Materials.Stone, 29030400));
        MatUnifier.addItemData(new ItemStack(Blocks.stonebrick, 1, 32767), new ItemData(Materials.Stone, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.cobblestone, 1, 32767), new ItemData(Materials.Stone, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.mossy_cobblestone, 1, 32767), new ItemData(Materials.Stone, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.stone_button, 1, 32767), new ItemData(Materials.Stone, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.stone_pressure_plate, 1, 32767), new ItemData(Materials.Stone, 7257600));
        MatUnifier.addItemData(new ItemStack(Blocks.ladder, 1, 32767), new ItemData(Materials.Wood, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.wooden_button, 1, 32767), new ItemData(Materials.Wood, 3628800));
        MatUnifier.addItemData(new ItemStack(Blocks.wooden_pressure_plate, 1, 32767), new ItemData(Materials.Wood, 7257600));
        MatUnifier.addItemData(new ItemStack(Blocks.fence, 1, 32767), new ItemData(Materials.Wood, 5443200));
        MatUnifier.addItemData(new ItemStack(Items.bowl, 1, 32767), new ItemData(Materials.Wood, 3628800));
        MatUnifier.addItemData(new ItemStack(Items.sign, 1, 32767), new ItemData(Materials.Wood, 7257600));
        MatUnifier.addItemData(new ItemStack(Blocks.chest, 1, 32767), new ItemData(Materials.Wood, 29030400));
        MatUnifier.addItemData(new ItemStack(Blocks.trapped_chest, 1, 32767), new ItemData(Materials.Wood, 32659200, new MaterialStack(Materials.Iron, OrePrefixes.ring.mMaterialAmount * 2)));
        MatUnifier.addItemData(new ItemStack(Blocks.unlit_redstone_torch, 1, 32767), new ItemData(Materials.Wood, 1814400, new MaterialStack(Materials.Redstone, 3628800)));
        MatUnifier.addItemData(new ItemStack(Blocks.redstone_torch, 1, 32767), new ItemData(Materials.Wood, 1814400, new MaterialStack(Materials.Redstone, 3628800)));
        MatUnifier.addItemData(new ItemStack(Blocks.noteblock, 1, 32767), new ItemData(Materials.Wood, 29030400, new MaterialStack(Materials.Redstone, 3628800)));
        MatUnifier.addItemData(new ItemStack(Blocks.jukebox, 1, 32767), new ItemData(Materials.Wood, 29030400, new MaterialStack(Materials.Diamond, 3628800)));
        MatUnifier.addItemData(new ItemStack(Blocks.crafting_table, 1, 32767), new ItemData(Materials.Wood, 14515200));
        MatUnifier.addItemData(new ItemStack(Blocks.piston, 1, 32767), new ItemData(Materials.Stone, 14515200, new MaterialStack(Materials.Wood, 10886400)));
        MatUnifier.addItemData(new ItemStack(Blocks.sticky_piston, 1, 32767), new ItemData(Materials.Stone, 14515200, new MaterialStack(Materials.Wood, 10886400)));
        MatUnifier.addItemData(new ItemStack(Blocks.dispenser, 1, 32767), new ItemData(Materials.Stone, 25401600, new MaterialStack(Materials.Redstone, 3628800)));
        MatUnifier.addItemData(new ItemStack(Blocks.dropper, 1, 32767), new ItemData(Materials.Stone, 25401600, new MaterialStack(Materials.Redstone, 3628800)));

        //TODO MOVE TO ITEMDATALOADER?
        /*if (tOre.mModID.equalsIgnoreCase("enderio") && tOre.mPrefix == OrePrefixes.ingot && tOre.mMaterial == Materials.DarkSteel) {
            MatUnifier.addAssociation(tOre.mPrefix, tOre.mMaterial, tOre.mEvent.Ore, false);
            MatUnifier.set(tOre.mPrefix, tOre.mMaterial, tOre.mEvent.Ore, (GregTech_API.sUnification.get(new StringBuilder().append(ConfigCategories.specialunificationtargets).append(".").append(tOre.mModID).toString(), tOre.mEvent.Name, true)), true);continue;
        } else if (tOre.mModID.equalsIgnoreCase("appliedenergistics2") && tOre.mPrefix == OrePrefixes.gem && tOre.mMaterial == Materials.CertusQuartz) {
            MatUnifier.addAssociation(tOre.mPrefix, tOre.mMaterial, tOre.mEvent.Ore, false);
            MatUnifier.set(tOre.mPrefix, tOre.mMaterial, tOre.mEvent.Ore, (GregTech_API.sUnification.get(new StringBuilder().append(ConfigCategories.specialunificationtargets).append(".").append(tOre.mModID).toString(), tOre.mEvent.Name, true)), true);continue;
        } else if (tOre.mModID.equalsIgnoreCase("appliedenergistics2") && tOre.mPrefix == OrePrefixes.dust && tOre.mMaterial == Materials.CertusQuartz) {
            MatUnifier.addAssociation(tOre.mPrefix, tOre.mMaterial, tOre.mEvent.Ore, false);
            MatUnifier.set(tOre.mPrefix, tOre.mMaterial, tOre.mEvent.Ore, (GregTech_API.sUnification.get(new StringBuilder().append(ConfigCategories.specialunificationtargets).append(".").append(tOre.mModID).toString(), tOre.mEvent.Name, true)), true);continue;
        }*/
    }
}
