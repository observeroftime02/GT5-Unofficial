package gregtech.loaders.misc;

import gregtech.api.GregTech_API;
import gregtech.api.objects.GT_CopiedBlockTexture;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class GT_CoverLoader
        implements Runnable {
    public void run() {
        for (byte i = 0; i < 16; i = (byte) (i + 1)) {
            GregTech_API.registerCover(new ItemStack(Blocks.carpet, 1, i), new GT_CopiedBlockTexture(Blocks.wool, 0, i), null);
        }
        /* TODO GregTech_API.registerCover(GT_ModHandler.getIC2Item("reactorVent", 1, 1), new GT_RenderedTexture(Textures.BlockIcons.VENT_NORMAL), new GT_Cover_Vent(1));
        GregTech_API.registerCover(GT_ModHandler.getIC2Item("reactorVentCore", 1, 1), new GT_RenderedTexture(Textures.BlockIcons.VENT_NORMAL), new GT_Cover_Vent(1));
        GregTech_API.registerCover(GT_ModHandler.getIC2Item("reactorVentGold", 1, 1), new GT_RenderedTexture(Textures.BlockIcons.VENT_ADVANCED), new GT_Cover_Vent(2));
        GregTech_API.registerCover(GT_ModHandler.getIC2Item("reactorVentSpread", 1), new GT_RenderedTexture(Textures.BlockIcons.VENT_NORMAL), new GT_Cover_Vent(2));
        GregTech_API.registerCover(GT_ModHandler.getIC2Item("reactorVentDiamond", 1, 1), new GT_RenderedTexture(Textures.BlockIcons.VENT_ADVANCED), new GT_Cover_Vent(3));*/
    }
}
