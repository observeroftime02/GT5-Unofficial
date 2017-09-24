package gregtech.api.objects;

import gregtech.api.enums.Materials;
import gregtech.api.interfaces.ITexture;
import gregtech.common.blocks.GT_Block_Ores_Abstract;
import net.minecraft.block.Block;

public class OreGenContainer {
    public Block tTargetBlock;
    public int[] tTargetMeta;
    public int[] tOreMeta;
    public ITexture[] tTextures;
    public int tOreType = 0;

    public OreGenContainer(Block aTargetBlock, int[] aTargetMeta, int[] aOreMeta, ITexture... aTextures) {
        tTargetBlock = aTargetBlock;
        tTargetMeta = aTargetMeta;
        tOreMeta = aOreMeta;
        tTextures = aTextures;
    }

    public OreGenContainer(Block aTargetBlock, int[] aTargetMeta, int aOreType, int[] aOreMeta, ITexture... aTextures) {
        tTargetBlock = aTargetBlock;
        tTargetMeta = aTargetMeta;
        tOreType = aOreType;
        tOreMeta = aOreMeta;
        tTextures = aTextures;
    }

    public GT_Block_Ores_Abstract getOreBlock(Materials aMaterial) {
        switch (tOreType) {
            case 0: return aMaterial.aDefOreBlock;
            case 1: return aMaterial.aUB1OreBlock;
            case 2: return aMaterial.aUB2OreBlock;
        }
        return aMaterial.aDefOreBlock;
    }
}