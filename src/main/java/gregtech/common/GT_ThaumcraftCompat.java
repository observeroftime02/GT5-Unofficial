package gregtech.common;

import gregtech.api.GregTech_API;
import gregtech.api.enums.ConfigCategories;
import gregtech.api.enums.Aspects;
import gregtech.api.interfaces.internal.IThaumcraftCompat;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GT_ThaumcraftCompat implements IThaumcraftCompat {
    public GT_ThaumcraftCompat() {
        Aspects.AER.mAspect = Aspect.AIR;
        Aspects.ALIENIS.mAspect = Aspect.ELDRITCH;
        Aspects.AQUA.mAspect = Aspect.WATER;
        Aspects.ARBOR.mAspect = Aspect.TREE;
        Aspects.AURAM.mAspect = Aspect.AURA;
        Aspects.BESTIA.mAspect = Aspect.BEAST;
        Aspects.COGNITIO.mAspect = Aspect.MIND;
        Aspects.CORPUS.mAspect = Aspect.FLESH;
        Aspects.EXANIMIS.mAspect = Aspect.UNDEAD;
        Aspects.FABRICO.mAspect = Aspect.CRAFT;
        Aspects.FAMES.mAspect = Aspect.HUNGER;
        Aspects.GELUM.mAspect = Aspect.COLD;
        Aspects.GRANUM.mAspect = Aspect.PLANT;
        Aspects.HERBA.mAspect = Aspect.PLANT;
        Aspects.HUMANUS.mAspect = Aspect.MAN;
        Aspects.IGNIS.mAspect = Aspect.FIRE;
        Aspects.INSTRUMENTUM.mAspect = Aspect.TOOL;
        Aspects.ITER.mAspect = Aspect.TRAVEL;
        Aspects.LIMUS.mAspect = Aspect.SLIME;
        Aspects.LUCRUM.mAspect = Aspect.GREED;
        Aspects.LUX.mAspect = Aspect.LIGHT;
        Aspects.MACHINA.mAspect = Aspect.MECHANISM;
        Aspects.MESSIS.mAspect = Aspect.CROP;
        Aspects.METALLUM.mAspect = Aspect.METAL;
        Aspects.METO.mAspect = Aspect.HARVEST;
        Aspects.MORTUUS.mAspect = Aspect.DEATH;
        Aspects.MOTUS.mAspect = Aspect.MOTION;
        Aspects.ORDO.mAspect = Aspect.ORDER;
        Aspects.PANNUS.mAspect = Aspect.CLOTH;
        Aspects.PERDITIO.mAspect = Aspect.ENTROPY;
        Aspects.PERFODIO.mAspect = Aspect.MINE;
        Aspects.PERMUTATIO.mAspect = Aspect.EXCHANGE;
        Aspects.POTENTIA.mAspect = Aspect.ENERGY;
        Aspects.PRAECANTATIO.mAspect = Aspect.MAGIC;
        Aspects.SANO.mAspect = Aspect.HEAL;
        Aspects.SENSUS.mAspect = Aspect.SENSES;
        Aspects.SPIRITUS.mAspect = Aspect.SOUL;
        Aspects.TELUM.mAspect = Aspect.WEAPON;
        Aspects.TERRA.mAspect = Aspect.EARTH;
        Aspects.TEMPESTAS.mAspect = Aspect.WEATHER;
        Aspects.TENEBRAE.mAspect = Aspect.DARKNESS;
        Aspects.TUTAMEN.mAspect = Aspect.ARMOR;
        Aspects.VACUOS.mAspect = Aspect.VOID;
        Aspects.VENENUM.mAspect = Aspect.POISON;
        Aspects.VICTUS.mAspect = Aspect.LIFE;
        Aspects.VINCULUM.mAspect = Aspect.TRAP;
        Aspects.VITIUM.mAspect = Aspect.TAINT;
        Aspects.VITREUS.mAspect = Aspect.CRYSTAL;
        Aspects.VOLATUS.mAspect = Aspect.FLIGHT;

        Aspects.STRONTIO.mAspect = new Aspect("strontio", 15647411, new Aspect[]{Aspect.MIND, Aspect.ENTROPY}, new ResourceLocation("gregtech:textures/aspects/" + Aspects.STRONTIO.name() + ".png"), 1);
        Aspects.NEBRISUM.mAspect = new Aspect("nebrisum", 15658622, new Aspect[]{Aspect.MINE, Aspect.GREED}, new ResourceLocation("gregtech:textures/aspects/" + Aspects.NEBRISUM.name() + ".png"), 1);
        Aspects.ELECTRUM.mAspect = new Aspect("electrum", 12644078, new Aspect[]{Aspect.ENERGY, Aspect.MECHANISM}, new ResourceLocation("gregtech:textures/aspects/" + Aspects.ELECTRUM.name() + ".png"), 1);
        Aspects.MAGNETO.mAspect = new Aspect("magneto", 12632256, new Aspect[]{Aspect.METAL, Aspect.TRAVEL}, new ResourceLocation("gregtech:textures/aspects/" + Aspects.MAGNETO.name() + ".png"), 1);
        Aspects.RADIO.mAspect = new Aspect("radio", 12648384, new Aspect[]{Aspect.LIGHT, Aspect.ENERGY}, new ResourceLocation("gregtech:textures/aspects/" + Aspects.RADIO.name() + ".png"), 1);

        GT_LanguageManager.addStringLocalization("tc.aspect.strontio", "Stupidness, Incompetence");
        GT_LanguageManager.addStringLocalization("tc.aspect.nebrisum", "Cheatyness, Raiding");
        GT_LanguageManager.addStringLocalization("tc.aspect.electrum", "Electricity, Lightning");
        GT_LanguageManager.addStringLocalization("tc.aspect.magneto", "Magnetism, Attraction");
        GT_LanguageManager.addStringLocalization("tc.aspect.radio", "Radiation");
    }

    private static final AspectList getAspectList(List<Aspects.AspectStack> aAspects) {
        AspectList rAspects = new AspectList();
        Aspects.AspectStack tAspect;
        for (Iterator i$ = aAspects.iterator(); i$.hasNext(); rAspects.add((Aspect) tAspect.mAspect.mAspect, (int) tAspect.mAmount)) {
            tAspect = (Aspects.AspectStack) i$.next();
        }
        return rAspects;
    }

    public Object addResearch(String aResearch, String aName, String aText, String[] aParentResearches, String aCategory, ItemStack aIcon, int aComplexity, int aType, int aX, int aY, List<Aspects.AspectStack> aAspects, ItemStack[] aResearchTriggers, Object[] aPages) {
        if (!GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.researches, aResearch, true)) {
            return null;
        }
        ResearchCategoryList tCategory = ResearchCategories.getResearchList(aCategory);
        if (tCategory == null) {
            return null;
        }
        for (Iterator i$ = tCategory.research.values().iterator(); i$.hasNext(); ) {
            ResearchItem tResearch = (ResearchItem) i$.next();
            if ((tResearch.displayColumn == aX) && (tResearch.displayRow == aY)) {
                aX += (aX > 0 ? 5 : -5);
                aY += (aY > 0 ? 5 : -5);
            }
        }
        ResearchItem rResearch = new ResearchItem(aResearch, aCategory, getAspectList(aAspects), aX, aY, aComplexity, aIcon);
        ArrayList<ResearchPage> tPages = new ArrayList(aPages.length);
        GT_LanguageManager.addStringLocalization("tc.research_name." + aResearch, aName);
        GT_LanguageManager.addStringLocalization("tc.research_text." + aResearch, "[GT] " + aText);
        for (Object tPage : aPages) {
            if ((tPage instanceof String)) {
                tPages.add(new ResearchPage((String) tPage));
            } else if ((tPage instanceof IRecipe)) {
                tPages.add(new ResearchPage((IRecipe) tPage));
            } else if ((tPage instanceof IArcaneRecipe)) {
                tPages.add(new ResearchPage((IArcaneRecipe) tPage));
            } else if ((tPage instanceof CrucibleRecipe)) {
                tPages.add(new ResearchPage((CrucibleRecipe) tPage));
            } else if ((tPage instanceof InfusionRecipe)) {
                tPages.add(new ResearchPage((InfusionRecipe) tPage));
            } else if ((tPage instanceof InfusionEnchantmentRecipe)) {
                tPages.add(new ResearchPage((InfusionEnchantmentRecipe) tPage));
            }
        }
        if ((aType & 0x40) != 0) {
            rResearch.setAutoUnlock();
        }
        if ((aType & 0x1) != 0) {
            rResearch.setSecondary();
        }
        if ((aType & 0x20) != 0) {
            rResearch.setSpecial();
        }
        if ((aType & 0x8) != 0) {
            rResearch.setVirtual();
        }
        if ((aType & 0x4) != 0) {
            rResearch.setHidden();
        }
        if ((aType & 0x10) != 0) {
            rResearch.setRound();
        }
        if ((aType & 0x2) != 0) {
            rResearch.setStub();
        }
        if (aParentResearches != null) {
            ArrayList<String> tParentResearches = new ArrayList();
            for (String tParent : aParentResearches) {
                if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.researches, aResearch, true)) {
                    tParentResearches.add(tParent);
                }
            }
            if (tParentResearches.size() > 0) {
                rResearch.setParents((String[]) tParentResearches.toArray(new String[tParentResearches.size()]));
                rResearch.setConcealed();
            }
        }
        if (aResearchTriggers != null) {
            rResearch.setItemTriggers(aResearchTriggers);
            rResearch.setHidden();
        }
        rResearch.setPages((ResearchPage[]) tPages.toArray(new ResearchPage[tPages.size()]));
        return rResearch.registerResearchItem();
    }

    public Object addCrucibleRecipe(String aResearch, Object aInput, ItemStack aOutput, List<Aspects.AspectStack> aAspects) {
        if ((GT_Utility.isStringInvalid(aResearch)) || (aInput == null) || (aOutput == null) || (aAspects == null) || (aAspects.isEmpty())) {
            return null;
        }
        return ThaumcraftApi.addCrucibleRecipe(aResearch, GT_Utility.copy(aOutput), ((aInput instanceof ItemStack)) || ((aInput instanceof ArrayList)) ? aInput : aInput.toString(), getAspectList(aAspects));
    }

    public Object addInfusionRecipe(String aResearch, ItemStack aMainInput, ItemStack[] aSideInputs, ItemStack aOutput, int aInstability, List<Aspects.AspectStack> aAspects) {
        if ((GT_Utility.isStringInvalid(aResearch)) || (aMainInput == null) || (aSideInputs == null) || (aOutput == null) || (aAspects == null) || (aAspects.isEmpty())) {
            return null;
        }
        return ThaumcraftApi.addInfusionCraftingRecipe(aResearch, GT_Utility.copy(aOutput), aInstability, getAspectList(aAspects), aMainInput, aSideInputs);
    }
    
	public boolean registerThaumcraftAspectsToItem(ItemStack aExampleStack, List<Aspects.AspectStack> aAspects, String aOreDict) {
		if (aAspects.isEmpty()) return false;
		ThaumcraftApi.registerObjectTag(aOreDict, getAspectList(aAspects));
		return true;
	}

	public boolean registerThaumcraftAspectsToItem(ItemStack aStack, List<Aspects.AspectStack> aAspects, boolean aAdditive) {
		if (aAspects.isEmpty()) return false;
		if (aAdditive) {
			ThaumcraftApi.registerComplexObjectTag(aStack, getAspectList(aAspects));
			return true;
		}
		AspectList tAlreadyRegisteredAspects = ThaumcraftApiHelper.getObjectAspects(aStack);
		if (tAlreadyRegisteredAspects == null || tAlreadyRegisteredAspects.size() <= 0) {
			ThaumcraftApi.registerObjectTag(aStack, getAspectList(aAspects));
		}
		return true;
	}

    public boolean registerPortholeBlacklistedBlock(Block aBlock) {
        ThaumcraftApi.portableHoleBlackList.add(aBlock);
        return true;
    }
}
