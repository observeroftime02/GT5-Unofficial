package gregtech.common.tileentities.machines.multi;

import gregtech.api.enums.GTNH_ExtraMaterials;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;

import static gregtech.api.enums.GT_Values.VN;

public class GT_MetaTileEntity_OreDrillingPlant5 extends GT_MetaTileEntity_OreDrillingPlantBase {
    public GT_MetaTileEntity_OreDrillingPlant5(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
        mTier=80;
    }

    public GT_MetaTileEntity_OreDrillingPlant5(String aName) {
        super(aName);
        mTier=80;
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
    protected String[] getDescriptionInternal(String tierSuffix) {
        String casings = getCasingBlockItem().get(0).getDisplayName();
        return new String[]{
                "Forgive me Master, I just had to go all out, just this once!",
                "Feed me ores you slut!!! I'll succ the earth dry...",
                "Controller Block for the Ore Drilling Plant " + (tierSuffix != null ? tierSuffix : ""),
                "Size(WxHxD): 3x7x3, Controller (Front middle bottom)",
                "3x1x3 Base of " + casings,
                "1x3x1 " + casings + " pillar (Center of base)",
                "1x3x1 " + getFrameMaterial().mName + " Frame Boxes (Each pillar side and on top)",
                "1x Input Hatch for drilling fluid (Any bottom layer casing)",
                "1x Input Bus for mining pipes (Any bottom layer casing; not necessary)",
                "1x Output Bus (Any bottom layer casing)",
                "1x Maintenance Hatch (Any bottom layer casing)",
                "1x " + VN[getMinTier()] + "+ Energy Hatch (Any bottom layer casing)",
                "Radius is " + (getRadiusInChunks() << 4) + " blocks",
                "Fortune bonus of " + mTier * 5};
    }

    @Override
    protected Materials getFrameMaterial() {
        return GTNH_ExtraMaterials.Weebium;
    }

    @Override
    protected int getCasingTextureIndex() {
        return 59;
    }

    @Override
    protected int getRadiusInChunks() {
        return 12;
    }

    @Override
    protected int getMinTier() {
        return 6;
    }

    @Override
    protected int getBaseProgressTime() {
        return 80;
    }


}

