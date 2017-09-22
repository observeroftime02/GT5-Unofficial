package gregtech.api.objects;

import gregtech.api.interfaces.tileentity.ICoverable;
import gregtech.api.util.GT_CoverBehavior;
import gregtech.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fluids.Fluid;

public class GT_Cover_Default extends GT_CoverBehavior {
    /**
     * This is the Dummy, if there is a generic Cover without behavior
     */
    public GT_Cover_Default() {
        super();
    }

    @Override
    public boolean isSimpleCover() {
        return true;
    }

    @Override
    public int onCoverScrewdriverclick(int aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
        aCoverVariable = ((aCoverVariable + 1) & 15);
        GT_Utility.sendChatToPlayer(aPlayer, ((aCoverVariable & 1) != 0 ? trans("128", "Redstone ") : "") + ((aCoverVariable & 2) != 0 ? trans("129", "Energy ") : "") + ((aCoverVariable & 4) != 0 ? trans("130", "Fluids ") : "") + ((aCoverVariable & 8) != 0 ? trans("131", "Items ") : ""));
        return aCoverVariable;
    }

    @Override
    public boolean letsRedstoneGoIn(int aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return (aCoverVariable & 1) != 0;
    }

    @Override
    public boolean letsRedstoneGoOut(int aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return (aCoverVariable & 1) != 0;
    }

    @Override
    public boolean letsEnergyIn(int aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return (aCoverVariable & 2) != 0;
    }

    @Override
    public boolean letsEnergyOut(int aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return (aCoverVariable & 2) != 0;
    }

    @Override
    public boolean letsFluidIn(int aSide, int aCoverID, int aCoverVariable, Fluid aFluid, ICoverable aTileEntity) {
        return (aCoverVariable & 4) != 0;
    }

    @Override
    public boolean letsFluidOut(int aSide, int aCoverID, int aCoverVariable, Fluid aFluid, ICoverable aTileEntity) {
        return (aCoverVariable & 4) != 0;
    }

    @Override
    public boolean letsItemsIn(int aSide, int aCoverID, int aCoverVariable, int aSlot, ICoverable aTileEntity) {
        return (aCoverVariable & 8) != 0;
    }

    @Override
    public boolean letsItemsOut(int aSide, int aCoverID, int aCoverVariable, int aSlot, ICoverable aTileEntity) {
        return (aCoverVariable & 8) != 0;
    }
}