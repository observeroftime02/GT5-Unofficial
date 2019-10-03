package gregtech.common.items;

import gregtech.api.GregTech_API;
import gregtech.api.enums.*;
import gregtech.api.items.GT_MetaGenerated_Item_X32;
import gregtech.api.items.GT_RadioactiveCellIC_Item;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.common.covers.GT_Cover_SolarPanel;

import static gregtech.api.enums.GTNH_ExtraMaterials.*;

public class GT_MetaGenerated_Item_04
        extends GT_MetaGenerated_Item_X32 {
    public static GT_MetaGenerated_Item_04 INSTANCE;

    public GT_MetaGenerated_Item_04() {
        super("metaitem.04", new OrePrefixes[]{OrePrefixes.crateGtDust, OrePrefixes.crateGtIngot, OrePrefixes.crateGtGem, OrePrefixes.crateGtPlate});
        INSTANCE = this;
        int tLastID = 0;
        Object[] o = new Object[0];

        ItemList.Test_Item.set(addItem(tLastID = 100, "Test Item", "This is a Test Item with no purpose"));



    }

    public boolean doesShowInCreative(OrePrefixes aPrefix, Materials aMaterial, boolean aDoShowAllItems) {
        return aDoShowAllItems;
    }
}