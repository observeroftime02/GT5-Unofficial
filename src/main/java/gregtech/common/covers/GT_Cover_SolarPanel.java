package gregtech.common.covers;

import com.dreammaster.lib.Refstrings;
import eu.usrv.yamcore.auxiliary.LogHelper;
import forestry.core.genetics.alleles.EnumAllele;
import gregtech.api.enums.ItemList;
import gregtech.api.interfaces.tileentity.ICoverable;
import gregtech.api.util.GT_CoverBehavior;
import gregtech.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import static gregtech.api.objects.XSTR.XSTR_INSTANCE;

public class GT_Cover_SolarPanel
        extends GT_CoverBehavior {
    private final int mVoltage;
    public boolean mMultiAmp;
    public long mAmpere;
    public static LogHelper Logger = new LogHelper("GT5U");


    public GT_Cover_SolarPanel(int aVoltage) {
        this.mVoltage = aVoltage;
    }


    public GT_Cover_SolarPanel(int aVoltage, boolean aMultiAmp, long aAmpere) {
        this.mVoltage = aVoltage;
        this.mMultiAmp = aMultiAmp;
        this.mAmpere = aAmpere;
    }


    public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity, long aTimer) {
        //if(aSide != 1)return 0;
        int coverState=aCoverVariable&0x3;
        int coverNum=aCoverVariable>>2;

        /*
        if (aTimer % 100L == 0L) {
            if (aTileEntity.getWorld().isThundering()) {
                return aTileEntity.getBiome().rainfall > 0.0F && aTileEntity.getSkyAtSide(aSide) ? Math.min(20,coverNum)<<2 : coverNum<<2;
            } else {
                if(aTileEntity.getWorld().isRaining() && aTileEntity.getBiome().rainfall > 0.0F){//really rains
                    if(aTileEntity.getSkyAtSide(aSide)) coverNum=Math.min(30,coverNum);
                    if(aTileEntity.getWorld().skylightSubtracted >= 4){
                        if(aTileEntity.getWorld().isDaytime()){
                            coverState=2;
                        }else{
                            return coverNum<<2;
                        }
                    }
                }else{//not rains
                    if(aTileEntity.getWorld().isDaytime()){
                        coverState=1;
                    }else{
                        coverState=2;
                    }
                }
            }
        } */

        if (aTimer % 100 == 0) {
            if (aTileEntity.getWorld().isDaytime()){
                coverState=1;
            } else {
                coverState=2;
            }
        }

        if (mMultiAmp){
            if (coverState == 1){
                aTileEntity.injectEnergyUnits((byte) 6, ((100L - (long) coverNum) * ((long) this.mVoltage)) / 100L, mAmpere);
            } else if (coverState == 2) {
                if (aTimer % 4L == 0) aTileEntity.injectEnergyUnits((byte) 6, ((100L - (long) coverNum) * ((long) this.mVoltage)) / 100L, mAmpere);
            }

        } else {
            if (coverState == 1){
                aTileEntity.injectEnergyUnits((byte) 6, mVoltage, 1L);
            } else if (coverState == 2) {
                if (aTimer % 4L == 0) aTileEntity.injectEnergyUnits((byte) 6, mVoltage, 1L);
            }
        }

        /*
        if (mMultiAmp) {
            if (coverState == 1 || (coverState == 2 && aTimer % 8L == 0L)) {
                aTileEntity.injectEnergyUnits((byte) 6, ((100L - (long) coverNum) * ((long) this.mVoltage)) / 100L, mAmpere);
            }
        } else {
            if (coverState == 1 || (coverState == 2 && aTimer % 8L == 0L)) {
                aTileEntity.injectEnergyUnits((byte) 6, ((100L - (long) coverNum) * ((long) this.mVoltage)) / 100L, 1L);
            }
        }*/

       /* if (coverState == 1 || (coverState == 2 && aTimer % 8L == 0L)) {
            aTileEntity.injectEnergyUnits((byte) 6, ((100L-(long)coverNum)*((long)this.mVoltage))/100L, 1L);
        } */


        if(aTimer % 28800L == 0L && coverNum<100 && (coverNum>10 || XSTR_INSTANCE.nextInt(3)==2))
            coverNum++;
        return coverState+(coverNum<<2);
    }

    @Override
    public boolean onCoverRightclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
        if(aPlayer.capabilities.isCreativeMode){
            GT_Utility.sendChatToPlayer(aPlayer,"Cleaned solar panel from "+(aCoverVariable>>2)+"% dirt");

            if (mMultiAmp) {
                GT_Utility.sendChatToPlayer(aPlayer, "Special Multi-Amp status: " + EnumChatFormatting.GREEN + mMultiAmp + EnumChatFormatting.RESET + ", outputting " + EnumChatFormatting.RED + mAmpere + EnumChatFormatting.RESET + " Ampere.");
            }
            aTileEntity.setCoverDataAtSide(aSide, (aCoverVariable & 0x3));
            return true;
        }
        if (mMultiAmp) {
            for (int i = 0; i < aPlayer.inventory.mainInventory.length; i++) {
                ItemStack is = aPlayer.inventory.mainInventory[i];
                if (is == null) continue;
                if (is.isItemEqual(ItemList.Gamer_girl_Panties.get(1L))) {
                    aPlayer.inventory.mainInventory[i] = ItemList.Consumed_Gamer_Girl_Panties.get(1L);
                    if (aPlayer.inventoryContainer != null) aPlayer.inventoryContainer.detectAndSendChanges();
                    GT_Utility.sendChatToPlayer(aPlayer, "Used the panties to clean " + (aCoverVariable >> 2) + "% dirt");
                    aTileEntity.setCoverDataAtSide(aSide, (aCoverVariable & 0x3));
                    return true;
                }
            }
        } else {
            for (int i = 0; i < aPlayer.inventory.mainInventory.length; i++) {
                ItemStack is = aPlayer.inventory.mainInventory[i];
                if (is == null) continue;
                if (is.getUnlocalizedName().equals(new ItemStack(Items.water_bucket).getUnlocalizedName())) {
                    aPlayer.inventory.mainInventory[i] = new ItemStack(Items.bucket);
                    if (aPlayer.inventoryContainer != null) aPlayer.inventoryContainer.detectAndSendChanges();
                    GT_Utility.sendChatToPlayer(aPlayer, "Cleaned solar panel from " + (aCoverVariable >> 2) + "% dirt");
                    aTileEntity.setCoverDataAtSide(aSide, (aCoverVariable & 0x3));
                    return true;
                }
            }
        }
        if (mMultiAmp){
            GT_Utility.sendChatToPlayer(aPlayer, "You need Anime Girl Panties in your inventory to clean the panel.");
        } else {
            GT_Utility.sendChatToPlayer(aPlayer, "You need water bucket in inventory to clean the panel.");
        }
        return false;
    }

    public boolean alwaysLookConnected(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return true;
    }
    
    public int getTickRate(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return 1;
    }
}