package gregtech.api.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_LanguageManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * <p/>
 * The Container I use for all my MetaTileEntities
 */
public class GT_ContainerMetaTile_Machine extends GT_Container {
    boolean mHasInitialBasicMachineClientUpdateBeenRecieved = false;

    public int mMaxProgressTime = 0, mProgressTime = 0;
    private int oMaxProgressTime = 0, oProgressTime = 0, mTimer = 0;


    public GT_ContainerMetaTile_Machine(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(aInventoryPlayer, aTileEntity);

        mTileEntity = aTileEntity;

        if (mTileEntity != null && mTileEntity.getMetaTileEntity() != null) {
            addSlots(aInventoryPlayer);
            if (doesBindPlayerInventory()) bindPlayerInventory(aInventoryPlayer);
            detectAndSendChanges();
        } else {
            aInventoryPlayer.player.openContainer = aInventoryPlayer.player.inventoryContainer;
        }
    }
    public GT_ContainerMetaTile_Machine(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, boolean doesBindInventory) {
        super(aInventoryPlayer, aTileEntity);
        mTileEntity = aTileEntity;

        if (mTileEntity != null && mTileEntity.getMetaTileEntity() != null) {
            addSlots(aInventoryPlayer);
            if (doesBindPlayerInventory() && doesBindInventory) bindPlayerInventory(aInventoryPlayer);
            detectAndSendChanges();
        } else {
            aInventoryPlayer.player.openContainer = aInventoryPlayer.player.inventoryContainer;
        }
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if (mTileEntity.isClientSide() || mTileEntity.getMetaTileEntity() == null) return;
        mProgressTime = mTileEntity.getProgress();
        mMaxProgressTime = mTileEntity.getMaxProgress();
        mTimer++;

        for (Object crafter : this.crafters) {
            ICrafting var1 = (ICrafting) crafter;
            if (mTimer % 500 == 10 || oProgressTime != mProgressTime) {
                var1.sendProgressBarUpdate(this, 0, mProgressTime & 65535);
                var1.sendProgressBarUpdate(this, 1, mProgressTime >>> 16);
            }
            if (mTimer % 500 == 10 || oMaxProgressTime != mMaxProgressTime) {
                var1.sendProgressBarUpdate(this, 2, mMaxProgressTime & 65535);
                var1.sendProgressBarUpdate(this, 3, mMaxProgressTime >>> 16);
            }
        }

        oProgressTime = mProgressTime;
        oMaxProgressTime = mMaxProgressTime;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int par1, int par2) {
        super.updateProgressBar(par1, par2);
        switch (par1) {
            case 0:
                mProgressTime = mProgressTime & -65536 | par2;
                break;
            case 1:
                mProgressTime = mProgressTime & 65535 | par2 << 16;
                break;
            case 2:
                mMaxProgressTime = mMaxProgressTime & -65536 | par2;
                break;
            case 3:
                mMaxProgressTime = mMaxProgressTime & 65535 | par2 << 16;
                break;
        }
        mHasInitialBasicMachineClientUpdateBeenRecieved = true;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return mTileEntity.isUseableByPlayer(player);
    }
    
    public String trans(String aKey, String aEnglish){
    	return GT_LanguageManager.addStringLocalization("Interaction_DESCRIPTION_Index_"+aKey, aEnglish, false);
    }
}