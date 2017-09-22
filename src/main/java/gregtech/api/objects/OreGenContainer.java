package gregtech.api.objects;

import gregtech.api.interfaces.ITexture;
import net.minecraft.block.Block;

public class OreGenContainer {
    public Block tTargetBlock;
    public int tTargetMeta;
    public ITexture tTexture;

    public OreGenContainer(Block aTargetBlock, int aTargetMeta, ITexture aTexture) {
        tTargetBlock = aTargetBlock;
        tTargetMeta = aTargetMeta;
        tTexture = aTexture;
    }
}