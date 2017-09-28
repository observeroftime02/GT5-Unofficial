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

        if (mTileEntity.getMetaTileEntity() instanceof GT_MetaTileEntity_MultiBlockBase) {
            GT_MetaTileEntity_MultiBlockBase aMetaTile = (GT_MetaTileEntity_MultiBlockBase) mTileEntity.getMetaTileEntity();
            if (mActive == 1 && aMetaTile.mCurrentRecipe != null) {
                int aIndex1, aIndex2;
                for (int i = 0; i < 6; i++) {
                    aIndex1 = i + 1;
                    if (mTileEntity.getStackInSlot(aIndex1) == null && aMetaTile.mCurrentRecipe.mInputs.length >= aIndex1) {
                        ItemStack aInputStack = aMetaTile.mCurrentRecipe.mInputs[i].copy().setStackDisplayName("Recipe Input " + aIndex1);
                        mTileEntity.addStackToSlot(aIndex1, aInputStack, aInputStack.stackSize);
                    }
                    aIndex2 = i + 7;
                    if (mTileEntity.getStackInSlot(aIndex2) == null && aMetaTile.mCurrentRecipe.mOutputs.length >= aIndex1) {
                        ItemStack aOutputStack = aMetaTile.mCurrentRecipe.mOutputs[i].copy().setStackDisplayName("Recipe Output " + aIndex1);
                        mTileEntity.addStackToSlot(aIndex2, aOutputStack, aOutputStack.stackSize);
                    }
                }
                mTileEntity.markDirty();
            } else { //TODO mActive is 0 until the first network packet is gotten, so it clears the slots?
                for (int i = 50; i < 61; i++) {
                    mTileEntity.setInventorySlotContents(i, null);
                }
                mTileEntity.markDirty();
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
        addSlotToContainer(new Slot(mTileEntity, 50, 17, 17));
        addSlotToContainer(new Slot(mTileEntity, 51, 35, 17));
        addSlotToContainer(new Slot(mTileEntity, 52, 53, 17));
        addSlotToContainer(new Slot(mTileEntity, 53, 17, 35));
        addSlotToContainer(new Slot(mTileEntity, 54, 35, 35));
        addSlotToContainer(new Slot(mTileEntity, 55, 53, 35));
        addSlotToContainer(new Slot(mTileEntity, 56, 107, 17));
        addSlotToContainer(new Slot(mTileEntity, 57, 125, 17));
        addSlotToContainer(new Slot(mTileEntity, 58, 143, 17));
        addSlotToContainer(new Slot(mTileEntity, 59, 107, 35));
        addSlotToContainer(new Slot(mTileEntity, 60, 125, 35));
        addSlotToContainer(new Slot(mTileEntity, 61, 143, 35));
    }

    @Override
    public int getSlotCount() {
        return 13;
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
