package gregtech.common.gui;

import gregtech.api.gui.GT_Container_MultiMachine;
import gregtech.api.gui.GT_GUIContainer_MultiMachine;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Utility;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_FusionReactor extends GT_GUIContainer_MultiMachine {

    public GT_GUIContainer_FusionReactor(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, String aName, String aTextureFile, String aNEI) {
        super(aInventoryPlayer, aTileEntity, aName, aTextureFile, aNEI);
        mName = aName;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRendererObj.drawString(mName, 8, -10, 16448255);

        if (mContainer != null) {
            GT_Container_FusionReactor aFusionContainer = (GT_Container_FusionReactor) mContainer;

            if ((aFusionContainer.mDisplayErrorCode & 64) != 0)
                fontRendererObj.drawString("Incomplete Structure.", 10, 8, 16448255);

            if (aFusionContainer.mDisplayErrorCode == 0) {
                if (((GT_Container_MultiMachine) mContainer).mActive == 0) {
                    fontRendererObj.drawString("Hit with Soft Hammer to (re-)start the Machine if it doesn't start.", -70, 170, 16448255);
                } else {
                    fontRendererObj.drawString("Running perfectly.", 10, 170, 16448255);
                }
            }
            if (aFusionContainer.mTotalEnergyStored > 160000000 && aFusionContainer.mTotalEnergyStored < 160010000) {
                fontRendererObj.drawString("160,000,000 EU", 50, 155, 0x00ff0000);
            } else if (aFusionContainer.mTotalEnergyStored > 320000000 && aFusionContainer.mTotalEnergyStored < 320010000) {
                fontRendererObj.drawString("320,000,000 EU", 50, 155, 0x00ff0000);
            } else if (aFusionContainer.mTotalEnergyStored > 640000000 && aFusionContainer.mTotalEnergyStored < 640010000) {
                fontRendererObj.drawString("640,000,000 EU", 50, 155, 0x00ff0000);
            } else {
                fontRendererObj.drawString(GT_Utility.formatNumbers(aFusionContainer.mTotalEnergyStored) + " EU", 50, 155, 0x00ff0000);
            }
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        if (mContainer != null) {
            GT_Container_FusionReactor aFusionConatiner = (GT_Container_FusionReactor) mContainer;
            double tScale = (double) aFusionConatiner.mTotalEnergyStored / (double) aFusionConatiner.mCurrentEnergyStored;
            drawTexturedModalRect(x + 5, y + 156, 0, 251, Math.min(147, (int) (tScale * 148)), 5);
        }
    }
}