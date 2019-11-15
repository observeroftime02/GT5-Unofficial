package gregtech.common.covers;

import eu.usrv.yamcore.auxiliary.LogHelper;
import gregtech.api.enums.ItemList;
import gregtech.api.interfaces.tileentity.ICoverable;
import gregtech.api.util.GT_CoverBehavior;
import gregtech.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import static gregtech.api.objects.XSTR.XSTR_INSTANCE;

public class GT_Cover_SolarPanel_MultiAmp extends GT_CoverBehavior {

    public static LogHelper Logger = new LogHelper("GT5U");

    private final int mVoltage;
    public boolean mMultiAmp;
    public long mAmpere;



    public GT_Cover_SolarPanel_MultiAmp(int aVoltage, boolean aMultiAmp, long aAmpere) {
        this.mVoltage = aVoltage;
        this.mMultiAmp = aMultiAmp;
        this.mAmpere = aAmpere;
    }


    public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity, long aTimer) {
        int coverState=aCoverVariable&0x3;
        int coverNum=aCoverVariable>>2;


        if (aTimer % 200 == 0) {
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

        }



        if(aTimer % 36000L == 0L && coverNum<100 && (coverNum>75 || XSTR_INSTANCE.nextInt(8)==4))
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
        }
        if (mMultiAmp){
            GT_Utility.sendChatToPlayer(aPlayer, "You need Anime Girl Panties in your inventory to clean the panel.");
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