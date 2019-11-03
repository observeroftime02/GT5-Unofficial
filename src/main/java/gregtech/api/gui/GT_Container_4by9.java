package gregtech.api.gui;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class GT_Container_4by9 extends GT_ContainerMetaTile_Machine {

    public GT_Container_4by9(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(aInventoryPlayer, aTileEntity);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity, 0, 8, 8));
        addSlotToContainer(new Slot(mTileEntity, 1, 26, 8));
        addSlotToContainer(new Slot(mTileEntity, 2, 44, 8));
        addSlotToContainer(new Slot(mTileEntity, 3, 62, 8));
        addSlotToContainer(new Slot(mTileEntity, 4, 80, 8));
        addSlotToContainer(new Slot(mTileEntity, 5, 98, 8));
        addSlotToContainer(new Slot(mTileEntity, 6, 116, 8));
        addSlotToContainer(new Slot(mTileEntity, 7, 134, 8));
        addSlotToContainer(new Slot(mTileEntity, 8, 152, 8));

        addSlotToContainer(new Slot(mTileEntity, 9, 8, 26));
        addSlotToContainer(new Slot(mTileEntity, 10, 26, 26));
        addSlotToContainer(new Slot(mTileEntity, 11, 44, 26));
        addSlotToContainer(new Slot(mTileEntity, 12, 62, 26));
        addSlotToContainer(new Slot(mTileEntity, 13, 80, 26));
        addSlotToContainer(new Slot(mTileEntity, 14, 98, 26));
        addSlotToContainer(new Slot(mTileEntity, 15, 116, 26));
        addSlotToContainer(new Slot(mTileEntity, 16, 134, 26));
        addSlotToContainer(new Slot(mTileEntity, 17, 152, 26));

        addSlotToContainer(new Slot(mTileEntity, 18, 8, 44));
        addSlotToContainer(new Slot(mTileEntity, 19, 26, 44));
        addSlotToContainer(new Slot(mTileEntity, 20, 44, 44));
        addSlotToContainer(new Slot(mTileEntity, 21, 62, 44));
        addSlotToContainer(new Slot(mTileEntity, 22, 80, 44));
        addSlotToContainer(new Slot(mTileEntity, 23, 98, 44));
        addSlotToContainer(new Slot(mTileEntity, 24, 116, 44));
        addSlotToContainer(new Slot(mTileEntity, 25, 134, 44));
        addSlotToContainer(new Slot(mTileEntity, 26, 152, 44));

        addSlotToContainer(new Slot(mTileEntity, 27, 8, 62));
        addSlotToContainer(new Slot(mTileEntity, 28, 26, 62));
        addSlotToContainer(new Slot(mTileEntity, 29, 44, 62));
        addSlotToContainer(new Slot(mTileEntity, 30, 62, 62));
        addSlotToContainer(new Slot(mTileEntity, 31, 80, 62));
        addSlotToContainer(new Slot(mTileEntity, 32, 98, 62));
        addSlotToContainer(new Slot(mTileEntity, 33, 116, 62));
        addSlotToContainer(new Slot(mTileEntity, 34, 134, 62));
        addSlotToContainer(new Slot(mTileEntity, 35, 152, 62));

    }

    @Override
    public int getSlotCount() {
        return 36;
    }

    @Override
    public int getShiftClickSlotCount() {
        return 36;
    }
}
