package gregtech.api.objects;

import gregtech.api.interfaces.tileentity.ICoverable;
import gregtech.api.util.GT_CoverBehavior;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fluids.Fluid;

public class GT_Cover_None extends GT_CoverBehavior {

    /**
     * This is the Dummy, if there is no Cover
     */
    public GT_Cover_None() {
    }

    @Override
    public float getBlastProofLevel(int aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return 10.0F;
    }

    @Override
    public boolean letsRedstoneGoIn(int aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return true;
    }

    @Override
    public boolean letsRedstoneGoOut(int aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return true;
    }

    @Override
    public boolean letsEnergyIn(int aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return true;
    }

    @Override
    public boolean letsEnergyOut(int aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return true;
    }

    @Override
    public boolean letsFluidIn(int aSide, int aCoverID, int aCoverVariable, Fluid aFluid, ICoverable aTileEntity) {
        return true;
    }

    @Override
    public boolean letsFluidOut(int aSide, int aCoverID, int aCoverVariable, Fluid aFluid, ICoverable aTileEntity) {
        return true;
    }

    @Override
    public boolean letsItemsIn(int aSide, int aCoverID, int aCoverVariable, int aSlot, ICoverable aTileEntity) {
        return true;
    }

    @Override
    public boolean letsItemsOut(int aSide, int aCoverID, int aCoverVariable, int aSlot, ICoverable aTileEntity) {
        return true;
    }

    @Override
    public boolean isGUIClickable(int aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return true;
    }

    @Override
    public boolean manipulatesSidedRedstoneOutput(int aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return false;
    }

    @Override
    public boolean onCoverRightclick(int aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
        return false;
    }

    @Override
    public boolean onCoverRemoval(int aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, boolean aForced) {
        return true;
    }

    @Override
    public int doCoverThings(int aSide, int aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity, long aTimer) {
        return 0;
    }

    @Override
    public boolean isSimpleCover() {
        return true;
    }
}
