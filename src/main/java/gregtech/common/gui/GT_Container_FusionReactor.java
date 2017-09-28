package gregtech.common.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.gui.GT_Container_MultiMachine;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class GT_Container_FusionReactor extends GT_Container_MultiMachine {
    public int mTotalEnergyStored = 0, mCurrentEnergyStored = 0;
    private int oTotalEnergyStored = 0, oCurrentEnergyStored = 0, mTimer = 0;

    public GT_Container_FusionReactor(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, boolean bindInventory) {
        super(aInventoryPlayer, aTileEntity, bindInventory);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        mTotalEnergyStored = (int) Math.min(Integer.MAX_VALUE, mTileEntity.getStoredEU());
        mCurrentEnergyStored = (int) Math.min(Integer.MAX_VALUE, mTileEntity.getEUCapacity());
        mTimer++;

        for (Object crafter : this.crafters) {
            ICrafting var1 = (ICrafting) crafter;
            if (mTimer % 500 == 10 || oTotalEnergyStored != mTotalEnergyStored) {
                var1.sendProgressBarUpdate(this, 200, mTotalEnergyStored);
            }
            if (mTimer % 500 == 10 || oCurrentEnergyStored != mCurrentEnergyStored) {
                var1.sendProgressBarUpdate(this, 201, mCurrentEnergyStored);
            }
        }

        oTotalEnergyStored = mTotalEnergyStored;
        oCurrentEnergyStored = mCurrentEnergyStored;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int par1, int par2) {
        super.updateProgressBar(par1, par2);
        switch (par1) {
            case 200:
                mTotalEnergyStored = par2;
                break;
            case 201:
                mCurrentEnergyStored = par2;
                break;
        }
    }

    @Override
    public boolean doesBindPlayerInventory() {
        return false;
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity, 0, 0, 0));
    }

    @Override
    public int getSlotCount() {
        return 1;
    }
}
