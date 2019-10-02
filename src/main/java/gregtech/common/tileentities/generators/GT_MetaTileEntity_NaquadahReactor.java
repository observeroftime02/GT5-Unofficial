package gregtech.common.tileentities.generators;

import gregtech.api.GregTech_API;
import gregtech.api.enums.ConfigCategories;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicGenerator;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.util.GT_Recipe;

import static gregtech.api.enums.GT_Values.V;

public class GT_MetaTileEntity_NaquadahReactor extends GT_MetaTileEntity_BasicGenerator {

    private int mEfficiency;

    public GT_MetaTileEntity_NaquadahReactor(int aID, String aName, String[] aDescription, String aNameRegional, int aTier) {
        super(aID, aName, aNameRegional, aTier, aDescription, new ITexture[0]);
        if (aTier > 8 || aTier < 4) {
            new Exception("Tier without Recipe Map!").printStackTrace();
        }
        onConfigLoad();
    }

    public GT_MetaTileEntity_NaquadahReactor(String aName, int aTier, String[] aDescription, ITexture[][][] aTextures) {
        super(aName, aTier, aDescription, aTextures);
        if (aTier > 8 || aTier < 4) {
            new Exception("Tier without Recipe Map!").printStackTrace();
        }
        onConfigLoad();
    }

    public boolean isOutputFacing(byte aSide) {
        return (aSide > 1) && (aSide != getBaseMetaTileEntity().getFrontFacing()) && (aSide != getBaseMetaTileEntity().getBackFacing());
    }

    public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_NaquadahReactor(this.mName, this.mTier, this.mDescriptionArray, this.mTextures);
    }

    public GT_Recipe.GT_Recipe_Map getRecipes() {
        GT_Recipe.GT_Recipe_Map ret;
        switch (this.mTier){
            case 4: {
                ret = GT_Recipe.GT_Recipe_Map.sSmallNaquadahReactorFuels;
                break;
            }
            case 5: {
                ret = GT_Recipe.GT_Recipe_Map.sLargeNaquadahReactorFuels;
                break;
            }
            case 6: {
                ret = GT_Recipe.GT_Recipe_Map.sHugeNaquadahReactorFuels;
                break;
            }
            case 7: {
                ret = GT_Recipe.GT_Recipe_Map.sExtremeNaquadahReactorFuels;
                break;
            }
            case 8:{
                ret = GT_Recipe.GT_Recipe_Map.sUltraHugeNaquadahReactorFuels;
                break;
            }
            case 9:{
                ret = GT_Recipe.GT_Recipe_Map.sRidiculousNaquadahReactorFuels;
                break;
            }
            default:{
                ret = null;
                break;
            }

        }
        return ret;
    }

    public int getCapacity() {
        return getRecipes() != null ? getRecipes().mMinimalInputFluids>0 ? 8000*(mTier+1) : 0 : 0 ;
    }

    public int getEfficiency() {
        return mEfficiency == 0 ? onConfigLoad() : mEfficiency;
    }

    public long maxEUStore() {

        switch (this.mTier) {
            case 4: {
                return Math.max(getEUVar(), V[mTier] * 40L + getMinimumStoredEU());
            }
            case 5: {
                return Math.max(getEUVar(), V[mTier] * 50L + getMinimumStoredEU());
            }
            case 6: {
                return Math.max(getEUVar(), V[mTier] * 60L + getMinimumStoredEU());
            }
            case 7: {
                return Math.max(getEUVar(), V[mTier] * 70L + getMinimumStoredEU());
            }
            case 8: {
                return Math.max(getEUVar(), V[mTier] * 80L + getMinimumStoredEU());
            }
            case 9: {
                return Long.MAX_VALUE-7;
            }
            default: {
                return Math.max(getEUVar(), V[mTier] * 4100L + getMinimumStoredEU());
            }
        }
    }


    private int getBaseEff() {

        switch (this.mTier) {

            case 9: {
                return 300;
            }
            default: {
                return mTier == 4 ? 80 : 100 + (50 * (mTier - 5));
            }
        }



    }

    public int onConfigLoad() {
       return this.mEfficiency = GregTech_API.sMachineFile.get(ConfigCategories.machineconfig, "SolidNaquadah.efficiency.tier." + this.mTier, getBaseEff());
    }

    public ITexture[] getFront(byte aColor) {
        return new ITexture[]{super.getFront(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.NAQUADAH_REACTOR_SOLID_FRONT)};
    }

    public ITexture[] getBack(byte aColor) {
        return new ITexture[]{super.getBack(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.NAQUADAH_REACTOR_SOLID_BACK)};
    }

    public ITexture[] getBottom(byte aColor) {
        return new ITexture[]{super.getBottom(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.NAQUADAH_REACTOR_SOLID_BOTTOM)};
    }

    public ITexture[] getTop(byte aColor) {
        return new ITexture[]{super.getTop(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.NAQUADAH_REACTOR_SOLID_TOP)};
    }

    public ITexture[] getSides(byte aColor) {
        return new ITexture[]{super.getSides(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.NAQUADAH_REACTOR_SOLID_SIDE)};
    }

    public ITexture[] getFrontActive(byte aColor) {
        return new ITexture[]{super.getFrontActive(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.NAQUADAH_REACTOR_SOLID_FRONT_ACTIVE)};
    }

    public ITexture[] getBackActive(byte aColor) {
        return new ITexture[]{super.getBackActive(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.NAQUADAH_REACTOR_SOLID_BACK_ACTIVE)};
    }

    public ITexture[] getBottomActive(byte aColor) {
        return new ITexture[]{super.getBottomActive(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.NAQUADAH_REACTOR_SOLID_BOTTOM_ACTIVE)};
    }

    public ITexture[] getTopActive(byte aColor) {
        return new ITexture[]{super.getTopActive(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.NAQUADAH_REACTOR_SOLID_TOP_ACTIVE)};
    }

    public ITexture[] getSidesActive(byte aColor) {
        return new ITexture[]{super.getSidesActive(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.NAQUADAH_REACTOR_SOLID_SIDE_ACTIVE)};
    }

    @Override
    public int getPollution() {
        return 0;
    }
}
