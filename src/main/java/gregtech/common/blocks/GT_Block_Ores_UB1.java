package gregtech.common.blocks;

import gregtech.api.GregTech_API;
import gregtech.api.enums.Materials;
import gregtech.api.util.GT_LanguageManager;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class GT_Block_Ores_UB1 extends GT_Block_Ores_Abstract {
    public static Block aUBBlock1;
    public static Block aUBBlock2;

    public GT_Block_Ores_UB1(Materials aMaterial) {
        super("blockores.ub1", aMaterial);
        for (int i = 0; i < 15; i++) {
            GT_LanguageManager.addStringLocalization(getUnlocalizedName() + "." + i + ".name", getLocalizedName(aMaterial));
            if (aHideOres) codechicken.nei.api.API.hideItem(new ItemStack(this, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName() {
        return "gt.blockores.ub1." + aMaterial.mName.toLowerCase();
    }

    @Override
    public int getHarvestLevel(int aMeta) {
        return aMeta == 5 || aMeta == 6 ? 2 : aMeta % 8;
    }

    @Override
    public IIcon getIcon(int aSide, int aMeta) {
        switch (aMeta) {
            case 0: return Blocks.stone.getIcon(0, 0);
            case 1: return GregTech_API.sBlockGranites.getIcon(0, 0);
            case 2: return GregTech_API.sBlockGranites.getIcon(0, 8);
            case 3: return GregTech_API.sBlockStones.getIcon(0, 0);
            case 4: return GregTech_API.sBlockStones.getIcon(0, 8);
            case 5: return Blocks.netherrack.getIcon(0, 0);
            case 6: return Blocks.end_stone.getIcon(0, 0);
        }
        return Blocks.stone.getIcon(0, 0);
    }

    @Override
    public void getSubBlocks(Item aItem, CreativeTabs aTab, List aList) {
        for (int i = 0; i < 15; i++) {
            aList.add(new ItemStack(aItem, 1, i));
        }
    }
}
