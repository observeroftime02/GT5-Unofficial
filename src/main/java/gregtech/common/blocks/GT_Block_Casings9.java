package gregtech.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Textures;
import gregtech.api.objects.GT_CopiedBlockTexture;
import gregtech.api.util.GT_LanguageManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class GT_Block_Casings9
        extends GT_Block_Casings_Abstract {

    //WATCH OUT FOR TEXTURE ID's
    public GT_Block_Casings9() {
        super(GT_Item_Casings9.class, "gt.blockcasings9", GT_Material_Casings.INSTANCE);
        for (int i = 0; i < 2; i = (i + 1)) {
            Textures.BlockIcons.CASING_BLOCKS[(i + 126)] = new GT_CopiedBlockTexture(this, 6, i);
        }
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".0.name", "UUM Machine Casing");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".1.name", "Smug Weebium Casing");

        ItemList.Casing_UUM.set(new ItemStack(this, 1, 0));
        ItemList.Casing_Weebium.set(new ItemStack(this, 1, 1));

    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int aSide, int aMeta) {
        switch (aMeta) {
        case 0:
            return Textures.BlockIcons.MACHINE_CASING_UUM.getIcon();
        case 1:
            return Textures.BlockIcons.MACHINE_CASING_WEEBIUM.getIcon();
        }
        return Textures.BlockIcons.MACHINE_CASING_ROBUST_TUNGSTENSTEEL.getIcon();
    }
}
