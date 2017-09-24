package gregtech.common.blocks;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.GT_Mod;
import gregtech.api.GregTech_API;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.Textures;
import gregtech.api.items.GT_Generic_Block;
import gregtech.api.objects.GT_CopiedBlockTexture;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.objects.OreGenContainer;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.common.GT_Worldgen_GT_Ore_Layer;
import gregtech.common.render.GT_Renderer_Block;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public abstract class GT_Block_Ores_Abstract extends GT_Generic_Block {
    public static boolean aHideOres = Loader.isModLoaded("NotEnoughItems");
    public static OreGenContainer[][] aDimContainerArray = new OreGenContainer[512][];
    public Materials aMaterial;

    public GT_Block_Ores_Abstract(String aUnlocPrefix, Materials aMaterial) {
        super(GT_Item_Ores.class, "gt." + aUnlocPrefix + "." + aMaterial.mName.toLowerCase(), Material.rock);
        setStepSound(soundTypeStone);
        setCreativeTab(GregTech_API.TAB_GREGTECH_ORES);
        this.aMaterial = aMaterial;
    }

    public static void construct() {
        OreGenContainer[] aOverworldContainer;
        if (Loader.isModLoaded("UndergroundBiomes") && GT_Mod.gregtechproxy.enableUBOres) {
            GT_Block_Ores_UB1.aUBBlock1 = GameRegistry.findBlock("UndergroundBiomes", "igneousStone");
            GT_Block_Ores_UB1.aUBBlock2 =  GameRegistry.findBlock("UndergroundBiomes", "metamorphicStone");
            GT_Block_Ores_UB2.aUBBlock3 = GameRegistry.findBlock("UndergroundBiomes", "sedimentaryStone");
            aOverworldContainer = new OreGenContainer[] {
                new OreGenContainer(GT_Block_Ores_UB1.aUBBlock1, new int[]{0, 1, 2, 3, 4, 5, 6, 7}, 1, new int[]{0, 1, 2, 3, 4, 5, 6, 7}, new GT_CopiedBlockTexture(GT_Block_Ores_UB1.aUBBlock1, 0, 0), new GT_CopiedBlockTexture(GT_Block_Ores_UB1.aUBBlock1, 0, 1), new GT_CopiedBlockTexture(GT_Block_Ores_UB1.aUBBlock1, 0, 2), new GT_CopiedBlockTexture(GT_Block_Ores_UB1.aUBBlock1, 0, 3), new GT_CopiedBlockTexture(GT_Block_Ores_UB1.aUBBlock1, 0, 4), new GT_CopiedBlockTexture(GT_Block_Ores_UB1.aUBBlock1, 0, 5), new GT_CopiedBlockTexture(GT_Block_Ores_UB1.aUBBlock1, 0, 6), new GT_CopiedBlockTexture(GT_Block_Ores_UB1.aUBBlock1, 0, 7)),
                new OreGenContainer(GT_Block_Ores_UB1.aUBBlock2, new int[]{0, 1, 2, 3, 4, 5, 6, 7}, 1, new int[]{8, 9, 10, 11, 12, 13, 14, 15}, new GT_CopiedBlockTexture(GT_Block_Ores_UB1.aUBBlock2, 0, 0), new GT_CopiedBlockTexture(GT_Block_Ores_UB1.aUBBlock2, 0, 1), new GT_CopiedBlockTexture(GT_Block_Ores_UB1.aUBBlock2, 0, 2), new GT_CopiedBlockTexture(GT_Block_Ores_UB1.aUBBlock2, 0, 3), new GT_CopiedBlockTexture(GT_Block_Ores_UB1.aUBBlock2, 0, 4), new GT_CopiedBlockTexture(GT_Block_Ores_UB1.aUBBlock2, 0, 5), new GT_CopiedBlockTexture(GT_Block_Ores_UB1.aUBBlock2, 0, 6), new GT_CopiedBlockTexture(GT_Block_Ores_UB1.aUBBlock2, 0, 7)),
                new OreGenContainer(GT_Block_Ores_UB2.aUBBlock3, new int[]{0, 1, 2, 3, 4, 5, 6, 7}, 2, new int[]{0, 1, 2, 3, 4, 5, 6, 7}, new GT_CopiedBlockTexture(GT_Block_Ores_UB2.aUBBlock3, 0, 0), new GT_CopiedBlockTexture(GT_Block_Ores_UB2.aUBBlock3, 0, 1), new GT_CopiedBlockTexture(GT_Block_Ores_UB2.aUBBlock3, 0, 2), new GT_CopiedBlockTexture(GT_Block_Ores_UB2.aUBBlock3, 0, 3), new GT_CopiedBlockTexture(GT_Block_Ores_UB2.aUBBlock3, 0, 4), new GT_CopiedBlockTexture(GT_Block_Ores_UB2.aUBBlock3, 0, 5), new GT_CopiedBlockTexture(GT_Block_Ores_UB2.aUBBlock3, 0, 6), new GT_CopiedBlockTexture(GT_Block_Ores_UB2.aUBBlock3, 0, 7)),
                new OreGenContainer(GregTech_API.sBlockGranites, new int[]{0}, new int[]{1}, new GT_RenderedTexture(Textures.BlockIcons.GRANITE_BLACK_STONE)),
                new OreGenContainer(GregTech_API.sBlockGranites, new int[]{8}, new int[]{2}, new GT_RenderedTexture(Textures.BlockIcons.GRANITE_RED_STONE)),
                new OreGenContainer(GregTech_API.sBlockStones, new int[]{0}, new int[]{3}, new GT_RenderedTexture(Textures.BlockIcons.MARBLE_STONE)),
                new OreGenContainer(GregTech_API.sBlockStones, new int[]{8}, new int[]{4}, new GT_RenderedTexture(Textures.BlockIcons.BASALT_STONE))
            };
            for (Materials aMaterial : Materials.MATERIALS_ORE) {
                aMaterial.aUB1OreBlock = new GT_Block_Ores(aMaterial);
                aMaterial.aUB2OreBlock = new GT_Block_Ores(aMaterial);
            }
        } else {
            aOverworldContainer = new OreGenContainer[] {
                new OreGenContainer(Blocks.stone, new int[]{0}, new int[]{0}, new GT_CopiedBlockTexture(Blocks.stone, 0, 0)),
                new OreGenContainer(GregTech_API.sBlockGranites, new int[]{0}, new int[]{1}, new GT_RenderedTexture(Textures.BlockIcons.GRANITE_BLACK_STONE)),
                new OreGenContainer(GregTech_API.sBlockGranites, new int[]{8}, new int[]{2}, new GT_RenderedTexture(Textures.BlockIcons.GRANITE_RED_STONE)),
                new OreGenContainer(GregTech_API.sBlockStones, new int[]{0}, new int[]{3}, new GT_RenderedTexture(Textures.BlockIcons.MARBLE_STONE)),
                new OreGenContainer(GregTech_API.sBlockStones, new int[]{8}, new int[]{4}, new GT_RenderedTexture(Textures.BlockIcons.BASALT_STONE))
            };
        }
        aDimContainerArray[0] = aOverworldContainer;
        for (Materials aMaterial : Materials.MATERIALS_ORE) {
            aMaterial.aDefOreBlock = new GT_Block_Ores(aMaterial);
        }

        OreGenContainer[] aNetherContainer = new OreGenContainer[] {
            new OreGenContainer(Blocks.netherrack, new int[]{0}, new int[]{5}, new GT_CopiedBlockTexture(Blocks.netherrack, 0, 0)),
            new OreGenContainer(GregTech_API.sBlockGranites, new int[]{0}, new int[]{1}, new GT_RenderedTexture(Textures.BlockIcons.GRANITE_BLACK_STONE)),
            new OreGenContainer(GregTech_API.sBlockGranites, new int[]{8}, new int[]{2}, new GT_RenderedTexture(Textures.BlockIcons.GRANITE_RED_STONE)),
            new OreGenContainer(GregTech_API.sBlockStones, new int[]{0}, new int[]{3}, new GT_RenderedTexture(Textures.BlockIcons.MARBLE_STONE)),
            new OreGenContainer(GregTech_API.sBlockStones, new int[]{8}, new int[]{4}, new GT_RenderedTexture(Textures.BlockIcons.BASALT_STONE))
        };
        aDimContainerArray[GT_Worldgen_GT_Ore_Layer.getFixedDimID(-1)] = aNetherContainer;

        OreGenContainer[] aEndContainer = new OreGenContainer[] {
            new OreGenContainer(Blocks.end_stone, new int[]{0}, new int[]{6}, new GT_CopiedBlockTexture(Blocks.end_stone, 0, 0)),
            new OreGenContainer(GregTech_API.sBlockGranites, new int[]{0}, new int[]{1}, new GT_RenderedTexture(Textures.BlockIcons.GRANITE_BLACK_STONE)),
            new OreGenContainer(GregTech_API.sBlockGranites, new int[]{8}, new int[]{2}, new GT_RenderedTexture(Textures.BlockIcons.GRANITE_RED_STONE)),
            new OreGenContainer(GregTech_API.sBlockStones, new int[]{0}, new int[]{3}, new GT_RenderedTexture(Textures.BlockIcons.MARBLE_STONE)),
            new OreGenContainer(GregTech_API.sBlockStones, new int[]{8}, new int[]{4}, new GT_RenderedTexture(Textures.BlockIcons.BASALT_STONE))
        };
        aDimContainerArray[1] = aEndContainer;

        if (Loader.isModLoaded("GalacticraftCore") && Loader.isModLoaded("GalacticraftMars") && GT_Mod.gregtechproxy.enableGCOres) {
            GT_Block_Ores.isGCEnabled = true;
            Block aMoonBlock = GameRegistry.findBlock("GalacticraftCore", "tile.moonBlock");
            Block aMarsBlock = GameRegistry.findBlock("GalacticraftMars", "tile.mars");
            OreGenContainer[] aMoonContainer = new OreGenContainer[]{
                new OreGenContainer(aMoonBlock, new int[]{3, 4}, new int[]{12, 13}, new GT_CopiedBlockTexture(aMoonBlock, 0, 3), new GT_CopiedBlockTexture(aMoonBlock, 0, 4))
            };
            OreGenContainer[] aMarsContainer = new OreGenContainer[]{
                new OreGenContainer(aMarsBlock, new int[]{6, 9}, new int[]{14, 15}, new GT_CopiedBlockTexture(aMarsBlock, 0, 6), new GT_CopiedBlockTexture(aMarsBlock, 0, 9))
            };
            aDimContainerArray[GT_Worldgen_GT_Ore_Layer.getFixedDimID(-28)] = aMoonContainer;
            aDimContainerArray[GT_Worldgen_GT_Ore_Layer.getFixedDimID(-29)] = aMarsContainer;
        }

        if (Loader.isModLoaded("GalacticraftPlanets")) {
            OreGenContainer[] aAsteroidContainer = new OreGenContainer[]{
                new OreGenContainer(Blocks.end_stone, new int[]{0}, new int[]{6}, new GT_CopiedBlockTexture(Blocks.end_stone, 0, 0)),
                new OreGenContainer(GregTech_API.sBlockGranites, new int[]{8}, new int[]{2}, new GT_RenderedTexture(Textures.BlockIcons.GRANITE_RED_STONE)),
            };
            aDimContainerArray[GT_Worldgen_GT_Ore_Layer.getFixedDimID(-30)] = aAsteroidContainer;
        }
    }

    public String getLocalizedName(Materials aMaterial) {
        switch (aMaterial.mName) {
            case "InfusedAir":
            case "InfusedEarth":
            case "InfusedEntropy":
            case "InfusedFire":
            case "InfusedOrder":
            case "InfusedWater":
                return aMaterial.mDefaultLocalName + " Infused Stone";
            case "Bentonite":
            case "Talc":
            case "BasalticMineralSand":
            case "GraniticMineralSand":
            case "GlauconiteSand":
            case "CassiteriteSand":
            case "Pitchblende":
                return aMaterial.mDefaultLocalName;
            default:
                return aMaterial.mDefaultLocalName + OrePrefixes.ore.mLocalizedMaterialPost;
        }
    }

    public boolean onBlockEventReceived(World world, int x, int y, int z, int p_149696_5_, int p_149696_6_) {
        super.onBlockEventReceived(world, x, y, z, p_149696_5_, p_149696_6_);
        TileEntity tileentity = world.getTileEntity(x, y, z);
        return tileentity != null && tileentity.receiveClientEvent(p_149696_5_, p_149696_6_);
    }

    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
        return (!(entity instanceof EntityDragon)) && (super.canEntityDestroy(world, x, y, z, entity));
    }

    public String getHarvestTool(int aMeta) {
        return "pickaxe";
    }

    public int getHarvestLevel(int aMeta) {
        return Blocks.stone.getHarvestLevel(aMeta);
    }

    public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {
        return 1.0F + getHarvestLevel(aWorld.getBlockMetadata(aX, aY, aZ)) * 1.0F;
    }

    public float getExplosionResistance(Entity par1Entity, World aWorld, int aX, int aY, int aZ, double explosionX, double explosionY, double explosionZ) {
        return 1.0F + getHarvestLevel(aWorld.getBlockMetadata(aX, aY, aZ)) * 1.0F;
    }

    protected boolean canSilkHarvest() {
        return false;
    }

    public abstract String getUnlocalizedName();

    public String getLocalizedName() {
        return StatCollector.translateToLocal(getUnlocalizedName() + ".name");
    }

    public int getRenderType() {
        if (GT_Renderer_Block.INSTANCE == null) {
            return super.getRenderType();
        }
        return GT_Renderer_Block.INSTANCE.mRenderID;
    }

    public boolean canBeReplacedByLeaves(IBlockAccess aWorld, int aX, int aY, int aZ) {
        return false;
    }

    public boolean isNormalCube(IBlockAccess aWorld, int aX, int aY, int aZ) {
        return true;
    }

    public boolean renderAsNormalBlock() {
        return true;
    }

    public boolean isOpaqueCube() {
        return true;
    }

    @Override
    public ArrayList<ItemStack> getDrops(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {
        ArrayList<ItemStack> aDrops = new ArrayList<>();
        ItemStack aChunk = GT_OreDictUnificator.get(OrePrefixes.oreChunk, aMaterial);
        aDrops.add(aChunk != null ? aChunk : new ItemStack(Blocks.cobblestone, 1));
        return aDrops;
    }

    @Override
    public abstract IIcon getIcon(int aSide, int aMeta);

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister aIconRegister) {
        aIconRegister.registerIcon(GT_Values.MOD_ID + ":" + mUnlocalizedName);
    }

    @Override
    public abstract void getSubBlocks(Item aItem, CreativeTabs aTab, List aList);
}