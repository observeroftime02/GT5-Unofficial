package gregtech.api.metatileentity.implementations;

import gregtech.api.enums.*;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.MetaPipeEntity;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import static gregtech.api.enums.GT_Values.RA;

public class GT_MetaPipeEntity_Frame extends MetaPipeEntity {
    public final Materials mMaterial;

    public GT_MetaPipeEntity_Frame(int aID, String aName, String aNameRegional, Materials aMaterial) {
        super(aID, aName, aNameRegional, 0);
        mMaterial = aMaterial;

        GT_OreDictUnificator.registerOre(OrePrefixes.frameGt, aMaterial, getStackForm(1));
        GT_ModHandler.addShapedToolRecipe(getStackForm(2), "SSS", "SwS", "SSS", 'S', GT_OreDictUnificator.get(OrePrefixes.stick, mMaterial));
        RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, aMaterial, 4), ItemList.Circuit_Integrated.getWithDamage(0, 4), getStackForm(1), 64, 8);
    }

    public GT_MetaPipeEntity_Frame(String aName, Materials aMaterial) {
        super(aName, 0);
        mMaterial = aMaterial;
    }

    @Override
    public byte getTileEntityBaseType() {
        return mMaterial == null ? 4 : (byte) ((mMaterial.contains(SubTag.WOOD) ? 12 : 4) + Math.max(0, Math.min(3, mMaterial.mToolQuality)));
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaPipeEntity_Frame(mName, mMaterial);
    }

    @Override
    public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, int aSide, int aConnections, int aColorIndex, boolean aConnected, boolean aRedstone) {
        return new ITexture[]{new GT_RenderedTexture(mMaterial.mIconSet.mTextures[OrePrefixes.frameGt.mTextureIndex], Dyes.getModulation(aColorIndex, mMaterial.mRGBa))};
    }

    @Override
    public String[] getDescription() {
        return new String[]{"Just something you can put a Cover or CFoam on."};
    }

    @Override
    public final boolean isSimpleMachine() {
        return true;
    }

    @Override
    public final boolean isFacingValid(int aFacing) {
        return false;
    }

    @Override
    public final boolean isValidSlot(int aIndex) {
        return false;
    }

    @Override
    public final boolean renderInside(byte aSide) {
        return true;
    }

    @Override
    public final float getThickNess() {
        return 1.0F;
    }

    @Override
    public final void saveNBTData(NBTTagCompound aNBT) {/*Do nothing*/}

    @Override
    public final void loadNBTData(NBTTagCompound aNBT) {/*Do nothing*/}

    @Override
    public final boolean allowPutStack(IGregTechTileEntity aBaseMetaTileEntity, int aIndex, int aSide, ItemStack aStack) {
        return false;
    }

    @Override
    public final boolean allowPullStack(IGregTechTileEntity aBaseMetaTileEntity, int aIndex, int aSide, ItemStack aStack) {
        return false;
    }
}