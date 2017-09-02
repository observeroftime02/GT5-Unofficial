package gregtech.common.items.behaviors;

import gregtech.api.items.GT_MetaBase_Item;
import gregtech.api.util.GT_LanguageManager;
import net.minecraft.item.ItemStack;

import java.util.List;

public class Behaviour_Sense
        extends Behaviour_None {
    private final int mCosts;
    private final String mTooltip = GT_LanguageManager.addStringLocalization("gt.behaviour.sense", "Rightclick to harvest Crop Sticks");

    public Behaviour_Sense(int aCosts) {
        this.mCosts = aCosts;
    }

    public List<String> getAdditionalToolTips(GT_MetaBase_Item aItem, List<String> aList, ItemStack aStack) {
        aList.add(this.mTooltip);
        return aList;
    }
}
