package gregtech.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.GregTech_API;
import gregtech.api.enums.*;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.tileentity.ITexturedTileEntity;
import gregtech.api.items.GT_Generic_Block;
import gregtech.api.objects.GT_CopiedBlockTexture;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.common.render.GT_Renderer_Block;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class GT_Block_Ores extends GT_Generic_Block implements ITexturedTileEntity {

    public static ITexture[] aOreTexturesas = new ITexture[]{new GT_CopiedBlockTexture(Blocks.stone, 0, 0), new GT_CopiedBlockTexture(Blocks.netherrack, 0, 0), new GT_CopiedBlockTexture(Blocks.end_stone, 0, 0), new GT_RenderedTexture(Textures.BlockIcons.GRANITE_BLACK_STONE), new GT_RenderedTexture(Textures.BlockIcons.GRANITE_RED_STONE), new GT_RenderedTexture(Textures.BlockIcons.MARBLE_STONE), new GT_RenderedTexture(Textures.BlockIcons.BASALT_STONE)};
    public Materials aMaterial;
    public ITexture aTexture = new GT_CopiedBlockTexture(Blocks.stone, 0, 0);

    public GT_Block_Ores(Materials aMaterial) {
        super(ItemBlock.class, "gt.blockores." + aMaterial.mName.toLowerCase(), Material.rock);
        setStepSound(soundTypeStone);
        setCreativeTab(GregTech_API.TAB_GREGTECH_ORES);

        this.aMaterial = aMaterial;
        //this.aTexture[1] = new GT_RenderedTexture(aMaterial.mIconSet.mTextures[OrePrefixes.ore.mTextureIndex], aMaterial.mRGBa);

        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".name", getLocalizedName(aMaterial));
        //GT_LanguageManager.addStringLocalization("gt." + aUnlocalizedPrefix + "." + aMaterial.mName.toLowerCase() + ".1.name", "Small " + getLocalizedName(aMaterial));

        //codechicken.nei.api.API.hideItem(new ItemStack(this, 1, 0));
        //codechicken.nei.api.API.hideItem(new ItemStack(this, 1, 1));
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
        return aMeta < 8 ? "pickaxe" : "shovel";
    }

    public int getHarvestLevel(int aMeta) {
        return aMeta == 5 || aMeta == 6 ? 2 : aMeta % 8;
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

    public String getUnlocalizedName() {
        return "gt.blockores." + aMaterial.mName.toLowerCase();
    }

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

        if (aMeta == 0) { //Normal Ore
            aDrops.add(GT_OreDictUnificator.get(OrePrefixes.oreChunk, aMaterial));
        } else { //Small Ore
            aDrops.add(GT_OreDictUnificator.get(OrePrefixes.crushed, aMaterial));
        }
        return aDrops;
    }

    public OrePrefixes[] getProcessingPrefix() { //Must have 8 entries; an entry can be null to disable automatic recipes.
        return new OrePrefixes[]{OrePrefixes.ore, OrePrefixes.oreNetherrack, OrePrefixes.oreEndstone, OrePrefixes.oreBlackgranite, OrePrefixes.oreRedgranite, OrePrefixes.oreMarble, OrePrefixes.oreBasalt, null};
    }

    public int getBaseBlockHarvestLevel(int aMeta) {
        switch (aMeta) {
            case 3:
            case 4:return 3;
            case 0:
            case 1:
            case 2:
            case 5:
            case 6:
            default:return 0;
        }
    }

    public Materials[] getOreMaterials() { //Must have 8 entries; can be null.
        return new Materials[]{Materials.Stone, Materials.Netherrack, Materials.Endstone, Materials.GraniteBlack, Materials.GraniteRed, Materials.Marble, Materials.Basalt, Materials.Stone};
    }

    @Override
    public ITexture[] getTexture(Block aBlock, byte aSide) {
        return new ITexture[]{aTexture, new GT_RenderedTexture(aMaterial.mIconSet.mTextures[OrePrefixes.ore.mTextureIndex], aMaterial.mRGBa)};
    }

    public IIcon getIcon(IBlockAccess aIBlockAccess, int aX, int aY, int aZ, int aSide) {
        return Blocks.stone.getIcon(0, 0);
    }

    public IIcon getIcon(int aSide, int aMeta) {
        return Blocks.stone.getIcon(0, 0);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister aIconRegister) {
        aIconRegister.registerIcon(GT_Values.MOD_ID + ":" + mUnlocalizedName);
    }

    public static boolean setOreBlock(World aWorld, int aX, int aY, int aZ, int aMetaData, boolean isSmallOre) {
        return setOreBlock(aWorld, aX, aY, aZ, aMetaData, isSmallOre, false);
    }

    public static boolean setOreBlock(World aWorld, int aX, int aY, int aZ, int aMetaData, boolean isSmallOre, boolean air) {
        if (!air) {
            aY = Math.min(aWorld.getActualHeight(), Math.max(aY, 1));
        }
        Block tBlock = aWorld.getBlock(aX, aY, aZ);
        //ITexture[] aTexture = new ITexture[]{aOreTextures[0], new GT_RenderedTexture(GregTech_API.sGeneratedMaterials[aMetaData].mIconSet.mTextures[isSmallOre ? OrePrefixes.oreSmall.mTextureIndex : OrePrefixes.ore.mTextureIndex], GregTech_API.sGeneratedMaterials[aMetaData].mRGBa)};
        ITexture aTexture = new GT_CopiedBlockTexture(Blocks.stone, 0, 0);

        if (aMetaData > 0 && (tBlock != Blocks.air || air)) {
            /*if (BlockName.equals("tile.igneousStone")) {
                if (GregTech_API.sBlockOresUb1 != null) {
                    tOreBlock = GregTech_API.sBlockOresUb1;
                    aBlockMeta += (BlockMeta * 1000);
                }
            } else if (BlockName.equals("tile.metamorphicStone")) {
                if (GregTech_API.sBlockOresUb2 != null) {
                    tOreBlock = GregTech_API.sBlockOresUb2;
                    aBlockMeta += (BlockMeta * 1000);
                }
            } else if (BlockName.equals("tile.sedimentaryStone")) {
                if (GregTech_API.sBlockOresUb3 != null) {
                    tOreBlock = GregTech_API.sBlockOresUb3;
                    aBlockMeta += (BlockMeta * 1000);
                }
            } else if (BlockName.equals("tile.moonBlock") && (BlockMeta == 3 || BlockMeta == 4)) {
                if (GregTech_API.sBlockOresGC != null) {
                    switch (BlockMeta) {
                        case 3: tOreBlock = GregTech_API.sBlockOresGC; break;
                        case 4: aBlockMeta += 1000; tOreBlock = GregTech_API.sBlockOresGC; break;
                    }
                }
            } else if (BlockName.equals("tile.mars") && (BlockMeta == 6 || BlockMeta == 9)) {
                if (GregTech_API.sBlockOresGC != null) {
                    switch (BlockMeta) {
                        case 6: aBlockMeta += 2000; tOreBlock = GregTech_API.sBlockOresGC; break;
                        case 9: aBlockMeta += 3000; tOreBlock = GregTech_API.sBlockOresGC; break;
                    }
                }
            } else*/ if (tBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.netherrack)) {
                aTexture = new GT_CopiedBlockTexture(Blocks.netherrack, 0, 0);
            } else if (tBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.end_stone)) {
                aTexture = new GT_CopiedBlockTexture(Blocks.end_stone, 0, 0);
            } else if (tBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, GregTech_API.sBlockGranites)) {
                if (tBlock == GregTech_API.sBlockGranites) {
                    if (aWorld.getBlockMetadata(aX, aY, aZ) < 8) {
                        aTexture = new GT_RenderedTexture(Textures.BlockIcons.GRANITE_BLACK_STONE);
                    } else {
                        aTexture = new GT_RenderedTexture(Textures.BlockIcons.GRANITE_RED_STONE);
                    }
                } else {
                    aTexture = new GT_RenderedTexture(Textures.BlockIcons.GRANITE_BLACK_STONE);
                }
            } else if (tBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, GregTech_API.sBlockStones)) {
                if (tBlock == GregTech_API.sBlockStones) {
                    if (aWorld.getBlockMetadata(aX, aY, aZ) < 8) {
                        aTexture = new GT_RenderedTexture(Textures.BlockIcons.MARBLE_STONE);
                    } else {
                        aTexture = new GT_RenderedTexture(Textures.BlockIcons.BASALT_STONE);
                    }
                } else {
                    aTexture = new GT_RenderedTexture(Textures.BlockIcons.MARBLE_STONE);
                }
            } else if (!tBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.stone)) {
                return false;
            }
            aWorld.setBlock(aX, aY, aZ, GregTech_API.sGeneratedMaterials[aMetaData].aOreBlock, isSmallOre ? 1 : 0, 0);
            ((GT_Block_Ores) aWorld.getBlock(aX, aY, aZ)).aTexture = aTexture;
            //((GT_Block_Ores_Abstract) aWorld.getBlock(aX, aY, aZ)).aTexture[1] = new GT_RenderedTexture(GregTech_API.sGeneratedMaterials[aMetaData].mIconSet.mTextures[!isSmallOre ? OrePrefixes.ore.mTextureIndex : OrePrefixes.oreSmall.mTextureIndex], GregTech_API.sGeneratedMaterials[aMetaData].mRGBa);
            //((GT_Block_Ores_Abstract) aWorld.getBlock(aX, aY, aZ)).aTexture = new ITexture[]{aTexture, new GT_RenderedTexture(GregTech_API.sGeneratedMaterials[aMetaData].mIconSet.mTextures[!isSmallOre ? OrePrefixes.ore.mTextureIndex : OrePrefixes.oreSmall.mTextureIndex], GregTech_API.sGeneratedMaterials[aMetaData].mRGBa)};
            return true;
        }
        return false;
    }

    @Override
    public void getSubBlocks(Item aItem, CreativeTabs aTab, List aList) {
        if (this.aMaterial != null) {
            if (this.aMaterial.hasFlag(MaterialFlags.ORE)) {
                aList.add(new ItemStack(aItem, 1, 0));
            }
            if (this.aMaterial.hasFlag(MaterialFlags.SORE)) {
                aList.add(new ItemStack(aItem, 1, 1));
            }
        }
    }
}