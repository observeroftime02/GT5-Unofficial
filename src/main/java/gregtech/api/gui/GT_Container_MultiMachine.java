package gregtech.api.gui;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_MultiBlockBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_MultiMachine extends GT_ContainerMetaTile_Machine {
    public boolean mHasInitialMultiMachineClientUpdateBeenRecieved = false;

    public int mActive = 0, mDisplayErrorCode = 0, mWorkEnabled = 0;
    private int oActive = 0, oDisplayErrorCode = 0, oWorkEnabled = 0, mTimer = 0;

    public GT_Container_MultiMachine(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(aInventoryPlayer, aTileEntity);
    }

    public GT_Container_MultiMachine(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, boolean bindInventory) {
        super(aInventoryPlayer, aTileEntity, bindInventory);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        mActive = mTileEntity.isActive() ? 1 : 0;
        mDisplayErrorCode = mTileEntity.getErrorDisplayID();
        mWorkEnabled = mTileEntity.isAllowedToWork() ? 1 : 0;
        mTimer++;

        for (Object crafter : this.crafters) {
            ICrafting var1 = (ICrafting) crafter;
            if (mTimer % 500 == 10 || oActive != mActive) {
                var1.sendProgressBarUpdate(this, 100, mActive);
            }
            if (mTimer % 500 == 10 || oDisplayErrorCode != mDisplayErrorCode) {
                var1.sendProgressBarUpdate(this, 101, mDisplayErrorCode);
            }
            if (mTimer % 500 == 10 || oWorkEnabled != mWorkEnabled) {
                var1.sendProgressBarUpdate(this, 102, mWorkEnabled);
            }
        }

        if (mTileEntity!=null && mTileEntity.getMetaTileEntity() instanceof GT_MetaTileEntity_MultiBlockBase && mTileEntity.isServerSide()) {
            GT_MetaTileEntity_MultiBlockBase aMetaTile = (GT_MetaTileEntity_MultiBlockBase) mTileEntity.getMetaTileEntity();
            if (mActive == 1 && aMetaTile.mCurrentRecipe != null) {
                for (int i = 1; i <= 6; i++) {
                    if (aMetaTile.mCurrentRecipe.mInputs.length > i-1) {
                        ItemStack aInputStack = aMetaTile.mCurrentRecipe.mInputs[i-1].copy().setStackDisplayName("Recipe Input " + i);
                        putStackInSlot(i, aInputStack);
                    }else{
                        putStackInSlot(i,null);
                    }
                    if (aMetaTile.mCurrentRecipe.mOutputs.length > i-1) {
                        ItemStack aOutputStack = aMetaTile.mCurrentRecipe.mOutputs[i-1].copy().setStackDisplayName("Recipe Output " + i);
                        System.out.println(aOutputStack.getUnlocalizedName());
                        putStackInSlot(i+6, aOutputStack);
                    }else{
                        putStackInSlot(i+6,null);
                    }
                }
            } else {
                for (int i = 1; i <= 12; i++) {
                    putStackInSlot(i, null);
                }
            }
        }

        oActive = mActive;
        oDisplayErrorCode = mDisplayErrorCode;
        oWorkEnabled = mWorkEnabled;
    }

    @Override
    public void updateProgressBar(int par1, int par2) {
        super.updateProgressBar(par1, par2);
        switch (par1) {
            case 100:
                mActive = par2;
                break;
            case 101:
                mDisplayErrorCode = par2;
                break;
            case 102:
                mWorkEnabled = par2;
                break;
        }
        mHasInitialMultiMachineClientUpdateBeenRecieved = true;
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity, 0, 152, 5));

        addSlotToContainer(new Slot(mTileEntity, 1, 18, 14));
        addSlotToContainer(new Slot(mTileEntity, 2, 35, 14));
        addSlotToContainer(new Slot(mTileEntity, 3, 52, 14));
        addSlotToContainer(new Slot(mTileEntity, 4, 18, 31));
        addSlotToContainer(new Slot(mTileEntity, 5, 35, 31));
        addSlotToContainer(new Slot(mTileEntity, 6, 52, 31));
        addSlotToContainer(new Slot(mTileEntity, 7, 108, 14));
        addSlotToContainer(new Slot(mTileEntity, 8, 125, 14));
        addSlotToContainer(new Slot(mTileEntity, 9, 142, 14));
        addSlotToContainer(new Slot(mTileEntity, 10, 108, 31));
        addSlotToContainer(new Slot(mTileEntity, 11, 125, 31));
        addSlotToContainer(new Slot(mTileEntity, 12, 142, 31));
    }

    @Override
    public int getSlotCount() {
        return 1;
    }

    @Override
    public int getShiftClickSlotCount() {
        return 1;
    }

    @Override
    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
        System.out.println(aSlotIndex);
        return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }
}
