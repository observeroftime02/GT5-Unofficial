package gregtech.common.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.enums.ItemList;
import gregtech.common.items.WB_SimpleItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public final class WB_Blocks {

    public static Block SimpleBlock;
    public static Block SimpleOreBlock;

    public static final void init() {
        GameRegistry.registerBlock(SimpleBlock = new WB_BasicBlock("TestBlock", Material.iron), "TestBlock");
    }

    public static final void postinit(){

        GameRegistry.registerBlock(SimpleOreBlock = new WB_SimpleOreBlock("TestOre", Material.rock, WB_SimpleItem.TestItem, 2, 4), "TestOre");

    }


}
