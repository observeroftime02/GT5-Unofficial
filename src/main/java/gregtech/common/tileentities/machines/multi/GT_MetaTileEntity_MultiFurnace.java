package gregtech.common.tileentities.machines.multi;

import static gregtech.api.enums.GT_Values.VN;

import java.util.ArrayList;

import gregtech.api.GregTech_API;
import gregtech.api.enums.Textures;
import gregtech.api.gui.GT_GUIContainer_MultiMachine;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Energy;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Muffler;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_MultiBlockBase;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

public class GT_MetaTileEntity_MultiFurnace
        extends GT_MetaTileEntity_MultiBlockBase {
    private int mLevel = 0;
    private int mCostDiscount = 1;
    private boolean hasTurboCoil = false;

    public GT_MetaTileEntity_MultiFurnace(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public GT_MetaTileEntity_MultiFurnace(String aName) {
        super(aName);
    }

    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_MultiFurnace(this.mName);
    }

    public String[] getDescription() {
        return new String[]{
                "Controller Block for the Multi Smelter",
                "Smelts up to 8-128 Items at once",
                "Size(WxHxD): 3x3x3 (Hollow), Controller (Front middle at bottom)",
                "8x Heating Coils (Middle layer, hollow)",
                "1x Input Bus (One of bottom)",
                "1x Output Bus (One of bottom)",
                "1x Maintenance Hatch (One of bottom)",
                "1x Muffler Hatch (Top middle)",
                "1x Energy Hatch (One of bottom)",
                "Heat Proof Machine Casings for the rest",
                "Causes " + 20 * getPollutionPerTick(null) + " Pollution per second"};
    }

    public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, byte aSide, byte aFacing, byte aColorIndex, boolean aActive, boolean aRedstone) {
        if (aSide == aFacing) {
            return new ITexture[]{Textures.BlockIcons.CASING_BLOCKS[11], new GT_RenderedTexture(aActive ? Textures.BlockIcons.OVERLAY_FRONT_MULTI_SMELTER_ACTIVE : Textures.BlockIcons.OVERLAY_FRONT_MULTI_SMELTER)};
        }
        return new ITexture[]{Textures.BlockIcons.CASING_BLOCKS[11]};
    }

    public Object getClientGUI(int aID, InventoryPlayer aPlayerInventory, IGregTechTileEntity aBaseMetaTileEntity) {
        return new GT_GUIContainer_MultiMachine(aPlayerInventory, aBaseMetaTileEntity, getLocalName(), "MultiFurnace.png");
    }

    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sFurnaceRecipes;
    }

    public boolean isCorrectMachinePart(ItemStack aStack) {
        return true;
    }

    public boolean isFacingValid(byte aFacing) {
        return aFacing > 1;
    }

    public boolean checkRecipe(ItemStack aStack) {
        ArrayList<ItemStack> tInputList = getStoredInputs();
        if (!tInputList.isEmpty()) {
            int mVolatage=GT_Utility.safeInt(getMaxInputVoltage());

            int j = 0;
            this.mOutputItems = new ItemStack[8 * this.mLevel];
            for (int i = 0; (i < 256) && (j < this.mOutputItems.length); i++) {
                if (null != (this.mOutputItems[j] = GT_ModHandler.getSmeltingOutput((ItemStack) tInputList.get(i % tInputList.size()), true, null))) {
                    j++;
                }
            }
            if (j > 0) {
                this.mEfficiency = (10000 - (getIdealStatus() - getRepairStatus()) * 1000);
                this.mEfficiencyIncrease = 10000;
                calculateOverclockedNessMulti(4, 512, 1, mVolatage);
                //In case recipe is too OP for that machine
                if (mMaxProgresstime == Integer.MAX_VALUE - 1 && mEUt == Integer.MAX_VALUE - 1)
                    return false;

                if (hasTurboCoil){
                    this.mEUt = 0;
                } else {
                    this.mEUt = GT_Utility.safeInt(((long) mEUt) * this.mLevel / (long) this.mCostDiscount, 1);
                }
                if (mEUt == Integer.MAX_VALUE - 1)
                    return false;
                if (this.mEUt > 0) {
                    if (hasTurboCoil){
                        this.mEUt = 0;
                        this.mMaxProgresstime = 1;
                    } else {
                        this.mEUt = (-this.mEUt);
                    }
                }
            }
            updateSlots();
            return true;
        }
        return false;
    }

    private boolean checkMachineFunction(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        int xDir = ForgeDirection.getOrientation(aBaseMetaTileEntity.getBackFacing()).offsetX;
        int zDir = ForgeDirection.getOrientation(aBaseMetaTileEntity.getBackFacing()).offsetZ;

        this.mLevel = 0;
        this.mCostDiscount = 1;
        if (!aBaseMetaTileEntity.getAirOffset(xDir, 1, zDir)) {
            return false;
        }
        addMufflerToMachineList(aBaseMetaTileEntity.getIGregTechTileEntityOffset(xDir, 2, zDir), 11);
        replaceDeprecatedCoils(aBaseMetaTileEntity);
        byte tUsedMeta = aBaseMetaTileEntity.getMetaIDOffset(xDir + 1, 1, zDir);
        switch (tUsedMeta) {
            case 0:
                this.mLevel = 1;
                this.mCostDiscount = 1;
                hasTurboCoil = false;
                break;
            case 1:
                this.mLevel = 2;
                this.mCostDiscount = 1;
                hasTurboCoil = false;
                break;
            case 2:
                this.mLevel = 4;
                this.mCostDiscount = 1;
                hasTurboCoil = false;
                break;
            case 3:
                this.mLevel = 8;
                this.mCostDiscount = 1;
                hasTurboCoil = false;
                break;
            case 4:
                this.mLevel = 16;
                this.mCostDiscount = 2;
                hasTurboCoil = false;
                break;
            case 5:
                this.mLevel = 16;
                this.mCostDiscount = 4;
                hasTurboCoil = false;
                break;
            case 6:
                this.mLevel = 16;
                this.mCostDiscount = 8;
                hasTurboCoil = false;
                break;
            case 7:
                this.mLevel = 16;
                this.mCostDiscount = 16;
                hasTurboCoil = false;
                break;
            case 8:
                this.mLevel = 16;
                this.mCostDiscount = 24;
                hasTurboCoil = false;
                break;
            case 9:
                this.mLevel = 16;
                this.mCostDiscount = 36;
                hasTurboCoil = true;
                break;
            default:
                return false;
        }
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if ((i != 0) || (j != 0)) {
                    if (aBaseMetaTileEntity.getBlockOffset(xDir + i, 1, zDir + j) != GregTech_API.sBlockCasings5) {
                        return false;
                    }
                    if (aBaseMetaTileEntity.getMetaIDOffset(xDir + i, 1, zDir + j) != tUsedMeta) {
                        return false;
                    }
                    if (aBaseMetaTileEntity.getBlockOffset(xDir + i, 2, zDir + j) != GregTech_API.sBlockCasings1) {
                        return false;
                    }
                    if (aBaseMetaTileEntity.getMetaIDOffset(xDir + i, 2, zDir + j) != 11) {
                        return false;
                    }
                }
            }
        }
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if ((xDir + i != 0) || (zDir + j != 0)) {
                    IGregTechTileEntity tTileEntity = aBaseMetaTileEntity.getIGregTechTileEntityOffset(xDir + i, 0, zDir + j);
                    if ((!addMaintenanceToMachineList(tTileEntity, 11)) && (!addInputToMachineList(tTileEntity, 11)) && (!addOutputToMachineList(tTileEntity, 11)) && (!addEnergyInputToMachineList(tTileEntity, 11))) {
                        if (aBaseMetaTileEntity.getBlockOffset(xDir + i, 0, zDir + j) != GregTech_API.sBlockCasings1) {
                            return false;
                        }
                        if (aBaseMetaTileEntity.getMetaIDOffset(xDir + i, 0, zDir + j) != 11) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
        public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack){
        boolean result= this.checkMachineFunction(aBaseMetaTileEntity,aStack);
              if (!result) this.mLevel=0;
              return result;
        }

    public int getMaxEfficiency(ItemStack aStack) {
        return 10000;
    }

    public int getPollutionPerTick(ItemStack aStack) {
        return 20;
    }

    public int getDamageToComponent(ItemStack aStack) {
        return 0;
    }

    public boolean explodesOnComponentBreak(ItemStack aStack) {
        return false;
    }

    private void replaceDeprecatedCoils(IGregTechTileEntity aBaseMetaTileEntity) {
        int xDir = ForgeDirection.getOrientation(aBaseMetaTileEntity.getBackFacing()).offsetX;
        int zDir = ForgeDirection.getOrientation(aBaseMetaTileEntity.getBackFacing()).offsetZ;
        int tX = aBaseMetaTileEntity.getXCoord() + xDir;
        int tY = (int) aBaseMetaTileEntity.getYCoord();
        int tZ = aBaseMetaTileEntity.getZCoord() + zDir;
        int tUsedMeta;
        for (int xPos = tX - 1; xPos <= tX + 1; xPos++) {
            for (int zPos = tZ - 1; zPos <= tZ + 1; zPos++) {
                if ((xPos == tX) && (zPos == tZ)) {
                    continue;
                }
                tUsedMeta = aBaseMetaTileEntity.getMetaID(xPos, tY + 1, zPos);
                if (tUsedMeta >= 12 && tUsedMeta <= 14 && aBaseMetaTileEntity.getBlock(xPos, tY + 1, zPos) == GregTech_API.sBlockCasings1) {
                    aBaseMetaTileEntity.getWorld().setBlock(xPos, tY + 1, zPos, GregTech_API.sBlockCasings5, tUsedMeta - 12, 3);
                }
            }
        }
    }


    @Override
    public String[] getInfoData() {
        int mPollutionReduction=0;
        for (GT_MetaTileEntity_Hatch_Muffler tHatch : mMufflerHatches) {
            if (isValidMetaTileEntity(tHatch)) {
                mPollutionReduction=Math.max(tHatch.calculatePollutionReduction(100),mPollutionReduction);
            }
        }

        long storedEnergy=0;
        long maxEnergy=0;
        for(GT_MetaTileEntity_Hatch_Energy tHatch : mEnergyHatches) {
            if (isValidMetaTileEntity(tHatch)) {
                storedEnergy+=tHatch.getBaseMetaTileEntity().getStoredEU();
                maxEnergy+=tHatch.getBaseMetaTileEntity().getEUCapacity();
            }
        }

        return new String[]{
        		StatCollector.translateToLocal("GT5U.multiblock.Progress")+": "+
                EnumChatFormatting.GREEN + Integer.toString(mProgresstime/20) + EnumChatFormatting.RESET +" s / "+
                        EnumChatFormatting.YELLOW + Integer.toString(mMaxProgresstime/20) + EnumChatFormatting.RESET +" s",
                StatCollector.translateToLocal("GT5U.multiblock.energy")+": "+
                EnumChatFormatting.GREEN + Long.toString(storedEnergy) + EnumChatFormatting.RESET +" EU / "+
                        EnumChatFormatting.YELLOW + Long.toString(maxEnergy) + EnumChatFormatting.RESET +" EU",
                StatCollector.translateToLocal("GT5U.multiblock.usage")+": "+
                        EnumChatFormatting.RED + Integer.toString(-mEUt) + EnumChatFormatting.RESET + " EU/t",
                StatCollector.translateToLocal("GT5U.multiblock.mei")+": "+
                        EnumChatFormatting.YELLOW+Long.toString(getMaxInputVoltage())+EnumChatFormatting.RESET+" EU/t(*2A) "+StatCollector.translateToLocal("GT5U.machines.tier")+": "+
                        EnumChatFormatting.YELLOW+VN[GT_Utility.getTier(getMaxInputVoltage())]+ EnumChatFormatting.RESET,
                StatCollector.translateToLocal("GT5U.multiblock.problems")+": "+
                        EnumChatFormatting.RED+ (getIdealStatus() - getRepairStatus())+EnumChatFormatting.RESET+
                        " "+StatCollector.translateToLocal("GT5U.multiblock.efficiency")+": "+
                        EnumChatFormatting.YELLOW+Float.toString(mEfficiency / 100.0F)+EnumChatFormatting.RESET + " %",
                StatCollector.translateToLocal("GT5U.MS.multismelting")+": "+
                        EnumChatFormatting.GREEN+mLevel*8+EnumChatFormatting.RESET+" Discount: (EU/t) / "+EnumChatFormatting.GREEN+mCostDiscount+EnumChatFormatting.RESET,
                StatCollector.translateToLocal("GT5U.multiblock.pollution")+": "+ EnumChatFormatting.GREEN + mPollutionReduction+ EnumChatFormatting.RESET+" %"
        };
    }

    public void saveNBTData(NBTTagCompound aNBT){
        aNBT.setBoolean("mHasTurboCoil", hasTurboCoil);
    }

    public void loadNBTData(NBTTagCompound aNBT){
        hasTurboCoil = aNBT.getBoolean("mHasTurboCoil");
    }

}
