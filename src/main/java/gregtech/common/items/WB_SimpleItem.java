package gregtech.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public final class WB_SimpleItem {

    public static Item TestItem;
    public static Item SolarDiode;

    public static final void init() {

        TestItem = new Item().setUnlocalizedName("TestItem").setCreativeTab(CreativeTabs.tabMisc).setTextureName("gregtech" + ":TestItem");
        GameRegistry.registerItem(TestItem, "TestItem");

        SolarDiode = new Item().setUnlocalizedName("SolarDiode").setCreativeTab(CreativeTabs.tabMisc).setTextureName("gregtech" + ":SolarDiode");
        GameRegistry.registerItem(SolarDiode, "SolarDiode");

    }

}
