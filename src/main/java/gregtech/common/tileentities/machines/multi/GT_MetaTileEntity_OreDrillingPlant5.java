package gregtech.common.tileentities.machines.multi;

import gregtech.api.enums.GTNH_ExtraMaterials;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;

public class GT_MetaTileEntity_OreDrillingPlant5 extends GT_MetaTileEntity_OreDrillingPlantBase {
    public GT_MetaTileEntity_OreDrillingPlant5(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
        mTier=5;
    }

    public GT_MetaTileEntity_OreDrillingPlant5(String aName) {
        super(aName);
        mTier=5;
    }

    @Override
    public String[] getDescription() {
        return getDescriptionInternal("V");
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_OreDrillingPlant5(mName);
    }

    @Override
    protected ItemList getCasingBlockItem() {
        return ItemList.Casing_Weebium;
    }

    @Override
    protected Materials getFrameMaterial() {
        return GTNH_ExtraMaterials.Weebium;
    }

    @Override
    protected int getCasingTextureIndex() {
        return 65;
    }

    @Override
    protected int getRadiusInChunks() {
        return 10;
    }

    @Override
    protected int getMinTier() {
        return 6;
    }

    @Override
    protected int getBaseProgressTime() {
        return 480;
    }
}
