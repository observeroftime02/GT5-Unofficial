package gregtech.api.interfaces.tileentity;

import gregtech.api.util.GT_CoverBehavior;
import net.minecraft.item.ItemStack;

public interface ICoverable extends IRedstoneTileEntity, IHasInventory, IBasicEnergyContainer {
    boolean canPlaceCoverIDAtSide(byte aSide, int aID);

    boolean canPlaceCoverItemAtSide(byte aSide, ItemStack aCover);

    boolean dropCover(byte aSide, byte aDroppedSide, boolean aForced);

    void setCoverDataAtSide(int aSide, int aData);

    void setCoverIDAtSide(int aSide, int aID);

    void setCoverItemAtSide(int aSide, ItemStack aCover);

    int getCoverDataAtSide(int aSide);

    int getCoverIDAtSide(int aSide);

    ItemStack getCoverItemAtSide(int aSide);

    GT_CoverBehavior getCoverBehaviorAtSide(int aSide);

    /**
     * For use by the regular MetaTileEntities. Returns the Cover Manipulated input Redstone.
     * Don't use this if you are a Cover Behavior. Only for MetaTileEntities.
     */
    byte getInternalInputRedstoneSignal(byte aSide);

    /**
     * For use by the regular MetaTileEntities. This makes it not conflict with Cover based Redstone Signals.
     * Don't use this if you are a Cover Behavior. Only for MetaTileEntities.
     */
    void setInternalOutputRedstoneSignal(byte aSide, byte aStrength);

    /**
     * Causes a general Cover Texture update.
     * Sends 6 Integers to Client + causes @issueTextureUpdate()
     */
    void issueCoverUpdate(int aSide);
}