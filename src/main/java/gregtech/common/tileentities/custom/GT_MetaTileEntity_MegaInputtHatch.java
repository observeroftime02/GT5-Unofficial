package gregtech.common.tileentities.custom;

import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Input;

public class GT_MetaTileEntity_MegaInputtHatch extends GT_MetaTileEntity_Hatch_Input {

    public GT_MetaTileEntity_MegaInputtHatch(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional, 9);
        this.mDescriptionArray[1] = "One input hatch to rule them all..." +
                "Extra Huge Fluid Input for certain ridiculous machines." +
        "Capacity: 2147000000L";
        //this.mDescriptionArray[2] = "Extra Huge Fluid Input for certain ridiculous machines.";
        //this.mDescriptionArray[3] = "Capacity: 2147000000L";
    }

    public GT_MetaTileEntity_MegaInputtHatch(String aName, int aTier, String[] aDescription, ITexture[][][] aTextures) {
        super(aName, aTier, aDescription, aTextures);
    }

    @Override
    public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_MegaInputtHatch(this.mName, this.mTier, this.mDescriptionArray, this.mTextures);
    }

    @Override
    public int getCapacity() {
        return 2147000000;
    }

}
