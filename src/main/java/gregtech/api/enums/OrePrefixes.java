package gregtech.api.enums;

import gregtech.api.GregTech_API;
import gregtech.api.enums.TC_Aspects.TC_AspectStack;
import gregtech.api.interfaces.ICondition;
import gregtech.api.interfaces.IMaterialHandler;
import gregtech.api.interfaces.IOreRecipeRegistrator;
import gregtech.api.interfaces.ISubTagContainer;
import gregtech.api.objects.ItemData;
import gregtech.api.objects.MaterialStack;
import gregtech.api.util.GT_Utility;
import gregtech.loaders.materialprocessing.ProcessingModSupport;
import net.minecraft.item.ItemStack;

import java.util.*;

import static gregtech.api.enums.GT_Values.M;

public enum OrePrefixes {
    oreBlackgranite("Black Granite Ores", "Granite ", " Ore", true, true, false, false, false, true, false, false, false, true, MaterialFlags.ORE.bit, -1, 64, -1), // In case of an End-Ores Mod. Ore -> Material is a Oneway Operation!
    oreRedgranite("Red Granite Ores", "Granite ", " Ore", true, true, false, false, false, true, false, false, false, true, MaterialFlags.ORE.bit, -1, 64, -1), // In case of an End-Ores Mod. Ore -> Material is a Oneway Operation!
    oreMarble("Marble Ores", "Marble ", " Ore", true, true, false, false, false, true, false, false, false, true, MaterialFlags.ORE.bit, -1, 64, -1), // In case of an End-Ores Mod. Ore -> Material is a Oneway Operation!
    oreBasalt("Basalt Ores", "Basalt ", " Ore", true, true, false, false, false, true, false, false, false, true, MaterialFlags.ORE.bit, -1, 64, -1), // In case of an End-Ores Mod. Ore -> Material is a Oneway Operation!
    oreNetherrack("Netherrack Ores", "Nether ", " Ore", true, true, false, false, false, true, false, false, false, true, MaterialFlags.ORE.bit, -1, 64, -1), // Prefix of the Nether-Ores Mod. Causes Ores to double. Ore -> Material is a Oneway Operation!
    oreNether("Nether Ores", "Nether ", " Ore", true, true, false, false, false, true, false, false, false, true, MaterialFlags.ORE.bit, -1, 64, -1), // Prefix of the Nether-Ores Mod. Causes Ores to double. Ore -> Material is a Oneway Operation!
    oreSmall("Small Ores", "Small ", " Ore", true, true, false, false, false, true, false, false, false, true, MaterialFlags.ORE.bit, -1, 64, 67), // Prefix of Railcraft.
    oreEndstone("Endstone Ores", "End ", " Ore", true, true, false, false, false, true, false, false, false, true, MaterialFlags.ORE.bit, -1, 64, -1), // In case of an End-Ores Mod. Ore -> Material is a Oneway Operation!
    oreEnd("End Ores", "End ", " Ore", true, true, false, false, false, true, false, false, false, true, MaterialFlags.ORE.bit, -1, 64, -1), // In case of an End-Ores Mod. Ore -> Material is a Oneway Operation!
    ore("Ores", "", " Ore", true, true, false, false, false, true, false, false, false, true, MaterialFlags.ORE.bit, -1, 64, 68), // Regular Ore Prefix. Ore -> Material is a Oneway Operation! Introduced by Eloraam
    oreChunk("Ore Chunks", "", " Ore Chunk", true, true, false, false, false, true, false, false, false, true, MaterialFlags.ORE.bit, -1, 64, 125), // Regular Ore Prefix. Ore -> Material is a Oneway Operation! Introduced by Eloraam
    crushedCentrifuged("Centrifuged Ores", "Centrifuged ", " Ore", true, true, false, false, false, false, false, true, false, true, MaterialFlags.ORE.bit, -1, 64, 7),
    crushedPurified("Purified Ores", "Purified ", " Ore", true, true, false, false, false, false, false, true, false, true, MaterialFlags.ORE.bit, -1, 64, 6),
    crushed("Crushed Ores", "Crushed ", " Ore", true, true, false, false, false, false, false, true, false, true, MaterialFlags.ORE.bit, -1, 64, 5),
    ingotHot("Hot Ingots", "Hot ", " Ingot", true, true, false, false, false, false, false, true, false, false, MaterialFlags.HINGOT.bit, M, 16, 12), // A hot Ingot, which has to be cooled down by a Vacuum Freezer.
    ingot("Ingots", "", " Ingot", true, true, false, false, false, false, false, true, false, false, MaterialFlags.SOLID.bit, M, 64, 11), // A regular Ingot. Introduced by Eloraam
    gemChipped("Chipped Gemstones", "Chipped ", "", true, true, true, false, false, false, true, true, false, false, MaterialFlags.GEM.bit, M / 4, 64, 59), // A regular Gem worth one small Dust. Introduced by TerraFirmaCraft
    gemFlawed("Flawed Gemstones", "Flawed ", "", true, true, true, false, false, false, true, true, false, false, MaterialFlags.GEM.bit, M / 2, 64, 60), // A regular Gem worth two small Dusts. Introduced by TerraFirmaCraft
    gemFlawless("Flawless Gemstones", "Flawless ", "", true, true, true, false, false, false, true, true, false, false, MaterialFlags.GEM.bit, M * 2, 32, 61), // A regular Gem worth two Dusts. Introduced by TerraFirmaCraft
    gemExquisite("Exquisite Gemstones", "Exquisite ", "", true, true, true, false, false, false, true, true, false, false, MaterialFlags.GEM.bit, M * 4, 16, 62), // A regular Gem worth four Dusts. Introduced by TerraFirmaCraft
    gem("Gemstones", "", "", true, true, true, false, false, false, true, true, false, false, MaterialFlags.GEM.bit, M, 64, 8), // A regular Gem worth one Dust. Introduced by Eloraam
    dustTiny("Tiny Dusts", "Tiny Pile of ", " Dust", true, true, false, false, false, false, false, true, false, false, MaterialFlags.DUST.bit | MaterialFlags.BDUST.bit, M / 9, 64, 0), // 1/9th of a Dust.
    dustSmall("Small Dusts", "Small Pile of ", " Dust", true, true, false, false, false, false, false, true, false, false, MaterialFlags.DUST.bit | MaterialFlags.BDUST.bit, M / 4, 64, 1), // 1/4th of a Dust.
    dustImpure("Impure Dusts", "Impure Pile of ", " Dust", true, true, false, false, false, false, false, true, false, true, MaterialFlags.ORE.bit, M, 64, 3), // Dust with impurities. 1 Unit of Main Material and 1/9 - 1/4 Unit of secondary Material
    dustPure("Purified Dusts", "Purified Pile of ", " Dust", true, true, false, false, false, false, false, true, false, true, MaterialFlags.ORE.bit, M, 64, 4),
    dust("Dusts", "", " Dust", true, true, false, false, false, false, false, true, false, false, MaterialFlags.DUST.bit | MaterialFlags.BDUST.bit, M, 64, 2), // Pure Dust worth of one Ingot or Gem. Introduced by Alblaka.
    nugget("Nuggets", "", " Nugget", true, true, false, false, false, false, false, true, false, false, MaterialFlags.SOLID.bit, M / 9, 64, 9), // A Nugget. Introduced by Eloraam
    plateDense("Dense Plates", "Dense ", " Plate", true, true, false, false, false, false, true, true, false, false, MaterialFlags.DPLATE.bit, M * 9, 8, 22), // 9 Plates combined in one Item.
    plate("Plates", "", " Plate", true, true, false, false, false, false, true, true, false, false, MaterialFlags.PLATE.bit, M, 64, 17), // Regular Plate made of one Ingot/Dust. Introduced by Calclavia
    foil("Foils", "", " Foil", true, true, false, false, false, false, true, true, false, false, MaterialFlags.FOIL.bit, M / 4, 64, 29), // Foil made of 1/4 Ingot/Dust.
    stick("Sticks/Rods", "", " Rod", true, true, false, false, false, false, true, true, false, false, MaterialFlags.STICK.bit, M / 2, 64, 23), // Stick made of half an Ingot. Introduced by Eloraam
    bolt("Bolts", "", " Bolt", true, true, false, false, false, false, true, true, false, false, MaterialFlags.BOLT.bit, M / 8, 64, 26), // consisting out of 1/8 Ingot or 1/4 Stick.
    screw("Screws", "", " Screw", true, true, false, false, false, false, true, true, false, false, MaterialFlags.SCREW.bit, M / 9, 64, 27), // consisting out of a Bolt.
    ring("Rings", "", " Ring", true, true, false, false, false, false, true, true, false, false, MaterialFlags.RING.bit, M / 4, 64, 28), // consisting out of 1/2 Stick.
    spring("Springs", "", " Spring", true, true, false, false, false, false, true, true, false, false, MaterialFlags.SPRING.bit, M, 64, 56), // consisting out of 2 Sticks.
    wireFine("Fine Wires", "Fine ", " Wire", true, true, false, false, false, false, true, true, false, false, MaterialFlags.FWIRE.bit, M / 8, 64, 51), // consisting out of 1/8 Ingot or 1/4 Wire.
    rotor("Rotors", "", " Rotor", true, true, false, false, false, false, true, true, false, false, MaterialFlags.ROTOR.bit, M * 4 + M / 4, 16, 53), // consisting out of 4 Plates, 1 Ring and 1 Screw.
    gearGtSmall("Small Gears", "Small ", " Gear", true, true, false, false, false, false, true, true, false, false, MaterialFlags.SGEAR.bit, M, 64, 52),
    gearGt("Gears", "", " Gear", true, true, false, false, false, false, true, true, false, false, MaterialFlags.GEAR.bit, M * 4, 16, 63), // Introduced by me because BuildCraft has ruined the gear Prefix...
    lens("Lenses", "", " Lens", true, true, false, false, false, false, true, true, false, false, MaterialFlags.GEM.bit, (M * 3) / 4, 64, 24), // 3/4 of a Plate or Gem used to shape a Lense. Normally only used on Transparent Materials.
    cellPlasma("Cells of Plasma", "", " Plasma Cell", true, true, true, true, false, false, false, true, false, false, MaterialFlags.PLASMA.bit, M, 64, 31), // Hot Cell full of Plasma, which can be used in the Plasma Generator.
    cell("Cells", "", " Cell", true, true, true, true, false, false, true, true, false, false, MaterialFlags.CELL.bit, M, 64, 30), // Regular RefineryGas/Fluid Cell. Introduced by Calclavia
    toolHeadSword("Sword Blades", "", " Sword Blade", true, true, false, false, false, false, true, true, false, false, MaterialFlags.TOOL.bit, M * 2, 16, 32), // consisting out of 2 Ingots.
    toolHeadPickaxe("Pickaxe Heads", "", " Pickaxe Head", true, true, false, false, false, false, true, true, false, false, MaterialFlags.TOOL.bit, M * 3, 16, 33), // consisting out of 3 Ingots.
    toolHeadShovel("Shovel Heads", "", " Shovel Head", true, true, false, false, false, false, true, true, false, false, MaterialFlags.TOOL.bit, M, 16, 34), // consisting out of 1 Ingots.
    toolHeadUniversalSpade("Universal Spade Heads", "", " Universal Spade Head", true, true, false, false, false, false, true, true, false, false, MaterialFlags.TOOL.bit, M, 16, 43), // consisting out of 1 Ingots.
    toolHeadAxe("Axe Heads", "", " Axe Head", true, true, false, false, false, false, true, true, false, false, MaterialFlags.TOOL.bit, M * 3, 16, 35), // consisting out of 3 Ingots.
    toolHeadHoe("Hoe Heads", "", " Hoe Head", true, true, false, false, false, false, true, true, false, false, MaterialFlags.TOOL.bit, M * 2, 16, 36), // consisting out of 2 Ingots.
    toolHeadSense("Sense Blades", "", " Sense Blade", true, true, false, false, false, false, true, true, false, false, MaterialFlags.TOOL.bit, M * 3, 16, 44), // consisting out of 3 Ingots.
    toolHeadFile("File Heads", "", " File Head", true, true, false, false, false, false, true, true, false, false, MaterialFlags.TOOL.bit, M * 2, 16, 38), // consisting out of 2 Ingots.
    toolHeadHammer("Hammer Heads", "", " Hammer Head", true, true, false, false, false, false, true, true, false, false, MaterialFlags.TOOL.bit, M * 6, 16, 37), // consisting out of 6 Ingots.
    toolHeadPlow("Plow Heads", "", " Plow Head", true, true, false, false, false, false, true, true, false, false, MaterialFlags.TOOL.bit, M * 4, 16, 45), // consisting out of 4 Ingots.
    toolHeadSaw("Saw Blades", "", " Saw Blade", true, true, false, false, false, false, true, true, false, false, MaterialFlags.TOOL.bit, M * 2, 16, 39), // consisting out of 2 Ingots.
    toolHeadBuzzSaw("Buzzsaw Blades", "", " Buzzsaw Blade", true, true, false, false, false, false, true, true, false, false, MaterialFlags.TOOL.bit, M * 4, 16, 48), // consisting out of 4 Ingots.
    toolHeadScrewdriver("Screwdriver Tips", "", " Screwdriver Tip", true, true, false, false, false, false, true, false, false, false, MaterialFlags.TOOL.bit, M, 16, 47), // consisting out of 1 Ingots.
    toolHeadDrill("Drill Tips", "", " Drill Tip", true, true, false, false, false, false, true, true, false, false, MaterialFlags.TOOL.bit, M * 4, 16, 40), // consisting out of 4 Ingots.
    toolHeadChainsaw("Chainsaw Tips", "", " Chainsaw Tip", true, true, false, false, false, false, true, true, false, false, MaterialFlags.TOOL.bit, M * 2, 16, 41), // consisting out of 2 Ingots.
    toolHeadWrench("Wrench Tips", "", " Wrench Tip", true, true, false, false, false, false, true, true, false, false, MaterialFlags.TOOL.bit, M * 4, 16, 42), // consisting out of 4 Ingots.
    toolHeadMallet("Mallet Heads", "", " Mallet Head", true, true, false, false, false, false, true, true, false, false, MaterialFlags.TOOL.bit, M * 6, 16, 127), // Reverse Head consisting out of 6 Ingots.
    turbineBlade("Turbine Blades", "", " Turbine Blade", true, true, false, false, false, false, true, true, false, false, MaterialFlags.TOOL.bit, M * 3, 64, 100), // consisting out of 6 Ingots.
    handleMallet("Mallet Handle", "", " Handle", true, true, false, false, false, false, true, true, false, false, MaterialFlags.SOLID.bit | MaterialFlags.GEM.bit, M / 2, 64, 126), // Reverse Stick made of half an Ingot. Introduced by Eloraam
    compressed("Compressed Materials", "Compressed ", "", true, true, false, false, false, false, true, false, false, false, 0, M * 2, 64, -1), // Compressed Material, worth 1 Unit. Introduced by Galacticraft
    glass("Glasses", "", "", false, false, true, false, true, false, false, false, false, false, 0, -1, 64, -1),
    block("Storage Blocks", "Block of ", "", true, true, false, false, false, true, true, false, false, false, 0, M * 9, 64, 71), // Storage Block consisting out of 9 Ingots/Gems/Dusts. Introduced by CovertJaguar
    crafting("Crafting Ingredients", "", "", false, false, false, false, false, false, false, false, false, false, 0, -1, 64, -1), // Special Prefix used mainly for the Crafting Handler.
    log("Logs", "", "", false, false, false, false, false, true, false, false, false, false, 0, -1, 64, -1), // Prefix used for Logs. Usually as "logWood". Introduced by Eloraam
    slab("Slabs", "", "", false, false, false, false, false, true, false, false, false, false, 0, -1, 64, -1), // Prefix used for Slabs. Usually as "slabWood" or "slabStone". Introduced by SirSengir
    plank("Planks", "", "", false, false, false, false, false, true, false, false, false, false, 0, -1, 64, -1), // Prefix for Planks. Usually "plankWood". Introduced by Eloraam
    treeSapling("Saplings", "", "", false, false, true, false, false, true, false, false, false, false, 0, -1, 64, -1), // Prefix for Saplings.
    treeLeaves("Leaves", "", "", false, false, true, false, false, true, false, false, false, false, 0, -1, 64, -1), // Prefix for Leaves.
    stoneCobble("Cobblestones", "", "", false, false, true, false, false, true, false, false, false, false, 0, -1, 64, -1), // Cobblestone Prefix for all Cobblestones.
    stoneSmooth("Smoothstones", "", "", false, false, true, false, false, true, false, false, false, false, 0, -1, 64, -1), // Smoothstone Prefix.
    stoneMossyBricks("mossy Stone Bricks", "", "", false, false, true, false, false, true, false, false, false, false, 0, -1, 64, -1), // Mossy Stone Bricks.
    stoneMossy("Mossy Stones", "", "", false, false, true, false, false, true, false, false, false, false, 0, -1, 64, -1), // Mossy Cobble.
    stoneBricks("Stone Bricks", "", "", false, false, true, false, false, true, false, false, false, false, 0, -1, 64, -1), // Stone Bricks.
    stoneCracked("Cracked Stones", "", "", false, false, true, false, false, true, false, false, false, false, 0, -1, 64, -1), // Cracked Bricks.
    stoneChiseled("Chiseled Stones", "", "", false, false, true, false, false, true, false, false, false, false, 0, -1, 64, -1), // Chiseled Stone.
    stone("Stones", "", "", false, true, true, false, true, true, false, false, false, false, 0, -1, 64, -1), // Prefix to determine which kind of Rock this is.
    item("Items", "", "", false, false, false, false, false, false, false, false, false, false, 0, -1, 64, -1), // Random Item. Introduced by Alblaka
    paper("Papers", "", "", false, false, false, false, false, false, false, false, false, false, 0, -1, 64, -1), // Used for Papers of any kind.
    dye("Dyes", "", "", false, false, true, false, false, false, false, false, false, false, 0, -1, 64, -1), // Used for the 16 dyes. Introduced by Eloraam
    frameGt("Frame Boxes", "", "", true, true, false, false, true, false, true, false, false, false, 0, M * 2, 64, 83),
    pipeTiny("Tiny Pipes", "Tiny ", " Pipe", true, true, false, false, true, false, true, false, false, false, 0, M / 2, 64, 78),
    pipeSmall("Small Pipes", "Small ", " Pipe", true, true, false, false, true, false, true, false, false, false, 0, M, 64, 79),
    pipeMedium("Medium Pipes", "Medium ", " Pipe", true, true, false, false, true, false, true, false, false, false, 0, M * 3, 64, 80),
    pipeLarge("Large pipes", "Large ", " Pipe", true, true, false, false, true, false, true, false, false, false, 0, M * 6, 64, 81),
    pipeHuge("Huge Pipes", "Huge ", " Pipe", true, true, false, false, true, false, true, false, false, false, 0, M * 12, 64, 82),
    pipeRestrictiveTiny("Tiny Restrictive Pipes", "Tiny Restrictive ", " Pipe", true, true, false, false, true, false, true, false, false, false, 0, M / 2, 64, 78),
    pipeRestrictiveSmall("Small Restrictive Pipes", "Small Restrictive ", " Pipe", true, true, false, false, true, false, true, false, false, false, 0, M, 64, 79),
    pipeRestrictiveMedium("Medium Restrictive Pipes", "Medium Restrictive ", " Pipe", true, true, false, false, true, false, true, false, false, false, 0, M * 3, 64, 80),
    pipeRestrictiveLarge("Large Restrictive Pipes", "Large Restrictive ", " Pipe", true, true, false, false, true, false, true, false, false, false, 0, M * 6, 64, 81),
    pipeRestrictiveHuge("Huge Restrictive Pipes", "Huge Restrictive ", " Pipe", true, true, false, false, true, false, true, false, false, false, 0, M * 12, 64, 82),
    pipe("Pipes", "", " Pipe", false, false, false, false, false, false, false, false, false, false, 0, -1, 64, 77),
    wireGt16("16x Wires", "16x ", " Wire", true, true, false, false, false, false, true, false, false, false, 0, M * 8, 64, -1),
    wireGt12("12x Wires", "12x ", " Wire", true, true, false, false, false, false, true, false, false, false, 0, M * 6, 64, -1),
    wireGt08("8x Wires", "8x ", " Wire", true, true, false, false, false, false, true, false, false, false, 0, M * 4, 64, -1),
    wireGt04("4x Wires", "4x ", " Wire", true, true, false, false, false, false, true, false, false, false, 0, M * 2, 64, -1),
    wireGt02("2x Wires", "2x ", " Wire", true, true, false, false, false, false, true, false, false, false, 0, M, 64, -1),
    wireGt01("1x Wires", "1x ", " Wire", true, true, false, false, false, false, true, false, false, false, 0, M / 2, 64, -1),
    cableGt12("12x Cables", "12x ", " Cable", true, true, false, false, false, false, true, false, false, false, 0, M * 6, 64, -1),
    cableGt08("8x Cables", "8x ", " Cable", true, true, false, false, false, false, true, false, false, false, 0, M * 4, 64, -1),
    cableGt04("4x Cables", "4x ", " Cable", true, true, false, false, false, false, true, false, false, false, 0, M * 2, 64, -1),
    cableGt02("2x Cables", "2x ", " Cable", true, true, false, false, false, false, true, false, false, false, 0, M, 64, -1),
    cableGt01("1x Cables", "1x ", " Cable", true, true, false, false, false, false, true, false, false, false, 0, M / 2, 64, -1),
    battery("Reusable Batteries", "", "", false, true, false, false, false, false, false, false, false, false, 0, -1, 64, -1), // Introduced by Calclavia
    circuit("Circuits", "", "", true, true, false, false, false, false, false, false, false, false, 0, -1, 64, -1), // Introduced by Calclavia

    // random known prefixes without special abilities.
    wood("Woods", "", "", false, false, false, false, false, false, false, false, false, false, 0, -1, 64, -1), // Introduced by Eloraam
    chunk("Chunks", "", "", false, false, false, false, false, false, false, false, false, false, 0, -1, 64, -1),
    wire("Wires", "", "", false, false, false, false, true, false, false, false, false, false, 0, -1, 64, -1),
    sheet("Sheets", "", "", false, false, false, false, false, false, false, false, false, false, 0, -1, 64, -1),
    gear("Gears", "", "", false, false, false, false, false, false, false, false, false, false, 0, -1, 64, -1), // Introduced by SirSengir

    crystal("Crystals", "", " Crystal", false, true, false, false, false, false, true, false, false, false, MaterialFlags.GEM.bit, M, 64, -1);

    public static volatile int VERSION = 509;

    static {
        ingotHot.mHeatDamage = 3.0F;
        cellPlasma.mHeatDamage = 6.0F;

        block.ignoreMaterials(Materials.Concrete, Materials.Glass, Materials.Glowstone, Materials.Marble, Materials.Quartz, Materials.CertusQuartz);
        ingot.ignoreMaterials(Materials.Brick);

        dust.addFamiliarPrefix(dustTiny);
        dust.addFamiliarPrefix(dustSmall);
        dustTiny.addFamiliarPrefix(dust);
        dustTiny.addFamiliarPrefix(dustSmall);
        dustSmall.addFamiliarPrefix(dust);
        dustSmall.addFamiliarPrefix(dustTiny);

        ingot.addFamiliarPrefix(nugget);
        nugget.addFamiliarPrefix(ingot);

        for (OrePrefixes tPrefix1 : values())
            if (tPrefix1.name().startsWith("ore")) for (OrePrefixes tPrefix2 : values())
                if (tPrefix2.name().startsWith("ore")) tPrefix1.addFamiliarPrefix(tPrefix2);
        for (OrePrefixes tPrefix1 : values())
            if (tPrefix1.name().startsWith("pipe")) for (OrePrefixes tPrefix2 : values())
                if (tPrefix2.name().startsWith("pipe")) tPrefix1.addFamiliarPrefix(tPrefix2);
        for (OrePrefixes tPrefix1 : values())
            if (tPrefix1.name().startsWith("wireGt")) for (OrePrefixes tPrefix2 : values())
                if (tPrefix2.name().startsWith("wireGt")) tPrefix1.addFamiliarPrefix(tPrefix2);
        for (OrePrefixes tPrefix1 : values())
            if (tPrefix1.name().startsWith("cableGt")) for (OrePrefixes tPrefix2 : values())
                if (tPrefix2.name().startsWith("cableGt")) tPrefix1.addFamiliarPrefix(tPrefix2);

        // These are only the important ones.
        gem.mNotGeneratedItems.add(Materials.Coal);
        gem.mNotGeneratedItems.add(Materials.Charcoal);
        gem.mNotGeneratedItems.add(Materials.NetherStar);
        gem.mNotGeneratedItems.add(Materials.Diamond);
        gem.mNotGeneratedItems.add(Materials.Emerald);
        gem.mNotGeneratedItems.add(Materials.NetherQuartz);
        gem.mNotGeneratedItems.add(Materials.EnderPearl);
        gem.mNotGeneratedItems.add(Materials.EnderEye);
        gem.mNotGeneratedItems.add(Materials.Flint);
        gem.mNotGeneratedItems.add(Materials.Lapis);
        dust.mNotGeneratedItems.add(Materials.Bone);
        dust.mNotGeneratedItems.add(Materials.Redstone);
        dust.mNotGeneratedItems.add(Materials.Glowstone);
        dust.mNotGeneratedItems.add(Materials.Blaze);
        stick.mNotGeneratedItems.add(Materials.Wood);
        stick.mNotGeneratedItems.add(Materials.Bone);
        stick.mNotGeneratedItems.add(Materials.Blaze);
        ingot.mNotGeneratedItems.add(Materials.Iron);
        ingot.mNotGeneratedItems.add(Materials.Gold);
        ingot.mNotGeneratedItems.add(Materials.Brick);
        ingot.mNotGeneratedItems.add(Materials.BrickNether);
        ingot.mNotGeneratedItems.add(Materials.WoodSealed);
        ingot.mNotGeneratedItems.add(Materials.Wood);
        nugget.mNotGeneratedItems.add(Materials.Gold);
        plate.mNotGeneratedItems.add(Materials.Paper);

        block.mNotGeneratedItems.add(Materials.Iron);
        block.mNotGeneratedItems.add(Materials.Gold);
        block.mNotGeneratedItems.add(Materials.Lapis);
        block.mNotGeneratedItems.add(Materials.Emerald);
        block.mNotGeneratedItems.add(Materials.Redstone);
        block.mNotGeneratedItems.add(Materials.Diamond);
        block.mNotGeneratedItems.add(Materials.Coal);

        //-----

        dustImpure.mGeneratedItems.add(Materials.GraniteRed);
        dustImpure.mGeneratedItems.add(Materials.GraniteBlack);
        dustImpure.mGeneratedItems.add(Materials.Quartzite);
        dustImpure.mGeneratedItems.add(Materials.Flint);
        dustImpure.mGeneratedItems.add(Materials.Basalt);
        dustImpure.mGeneratedItems.add(Materials.Marble);
        dustImpure.mGeneratedItems.add(Materials.Netherrack);
        dustImpure.mGeneratedItems.add(Materials.Endstone);
        dustImpure.mGeneratedItems.add(Materials.Stone);

        plate.mGeneratedItems.add(Materials.Redstone);
        plate.mGeneratedItems.add(Materials.Concrete);
        plate.mGeneratedItems.add(Materials.GraniteRed);
        plate.mGeneratedItems.add(Materials.GraniteBlack);
        plate.mGeneratedItems.add(Materials.Glowstone);
        plate.mGeneratedItems.add(Materials.Obsidian);

        plate.mGeneratedItems.add(Materials.Paper);
        ring.mGeneratedItems.add(Materials.Paper);
        
        lens.mGeneratedItems.add(Materials.EnderPearl);
        lens.mGeneratedItems.add(Materials.EnderEye);

        //-----

        dust.mGeneratedItems.addAll(dustPure.mGeneratedItems);
        dust.mGeneratedItems.addAll(dustImpure.mGeneratedItems);
        dustTiny.mGeneratedItems.addAll(dust.mGeneratedItems);
        dustSmall.mGeneratedItems.addAll(dust.mGeneratedItems);

        //-----

        toolHeadFile.mCondition = new ICondition.And<ISubTagContainer>(new ICondition.Not<ISubTagContainer>(SubTag.NO_SMASHING), new ICondition.Not<ISubTagContainer>(SubTag.BOUNCY));
        toolHeadSaw.mCondition = new ICondition.And<ISubTagContainer>(new ICondition.Not<ISubTagContainer>(SubTag.NO_SMASHING), new ICondition.Not<ISubTagContainer>(SubTag.BOUNCY));
        toolHeadDrill.mCondition = new ICondition.And<ISubTagContainer>(new ICondition.Not<ISubTagContainer>(SubTag.NO_SMASHING), new ICondition.Not<ISubTagContainer>(SubTag.BOUNCY));
        toolHeadChainsaw.mCondition = new ICondition.And<ISubTagContainer>(new ICondition.Not<ISubTagContainer>(SubTag.NO_SMASHING), new ICondition.Not<ISubTagContainer>(SubTag.BOUNCY));
        toolHeadWrench.mCondition = new ICondition.And<ISubTagContainer>(new ICondition.Not<ISubTagContainer>(SubTag.NO_SMASHING), new ICondition.Not<ISubTagContainer>(SubTag.BOUNCY));
        toolHeadBuzzSaw.mCondition = new ICondition.And<ISubTagContainer>(new ICondition.Not<ISubTagContainer>(SubTag.NO_SMASHING), new ICondition.Not<ISubTagContainer>(SubTag.BOUNCY));
        turbineBlade.mCondition = new ICondition.And<ISubTagContainer>(new ICondition.Not<ISubTagContainer>(SubTag.NO_SMASHING), new ICondition.Not<ISubTagContainer>(SubTag.BOUNCY));

        rotor.mCondition = new ICondition.Nor<ISubTagContainer>(SubTag.CRYSTAL, SubTag.STONE, SubTag.BOUNCY);

        spring.mCondition = new ICondition.Or<ISubTagContainer>(SubTag.STRETCHY, SubTag.BOUNCY, new ICondition.Not<ISubTagContainer>(SubTag.NO_SMASHING));

        gemChipped.mCondition = new ICondition.And<ISubTagContainer>(SubTag.TRANSPARENT, SubTag.CRYSTAL, new ICondition.Not<ISubTagContainer>(SubTag.QUARTZ), new ICondition.Not<ISubTagContainer>(SubTag.PEARL), new ICondition.Not<ISubTagContainer>(SubTag.MAGICAL));
        gemFlawed.mCondition = new ICondition.And<ISubTagContainer>(SubTag.TRANSPARENT, SubTag.CRYSTAL, new ICondition.Not<ISubTagContainer>(SubTag.QUARTZ), new ICondition.Not<ISubTagContainer>(SubTag.PEARL), new ICondition.Not<ISubTagContainer>(SubTag.MAGICAL));
        gemFlawless.mCondition = new ICondition.And<ISubTagContainer>(SubTag.TRANSPARENT, SubTag.CRYSTAL, new ICondition.Not<ISubTagContainer>(SubTag.QUARTZ), new ICondition.Not<ISubTagContainer>(SubTag.PEARL), new ICondition.Not<ISubTagContainer>(SubTag.MAGICAL));
        gemExquisite.mCondition = new ICondition.And<ISubTagContainer>(SubTag.TRANSPARENT, SubTag.CRYSTAL, new ICondition.Not<ISubTagContainer>(SubTag.QUARTZ), new ICondition.Not<ISubTagContainer>(SubTag.PEARL), new ICondition.Not<ISubTagContainer>(SubTag.MAGICAL));

        lens.mCondition = new ICondition.Or<ISubTagContainer>(SubTag.MAGICAL, new ICondition.And<ISubTagContainer>(SubTag.TRANSPARENT, SubTag.HAS_COLOR));

        plateDense.mCondition = new ICondition.Not<ISubTagContainer>(SubTag.NO_SMASHING);

        wireFine.mCondition = SubTag.METAL;

        //-----

        pipeRestrictiveTiny.mSecondaryMaterial = new MaterialStack(Materials.Steel, ring.mMaterialAmount);
        pipeRestrictiveSmall.mSecondaryMaterial = new MaterialStack(Materials.Steel, ring.mMaterialAmount * 2);
        pipeRestrictiveMedium.mSecondaryMaterial = new MaterialStack(Materials.Steel, ring.mMaterialAmount * 3);
        pipeRestrictiveLarge.mSecondaryMaterial = new MaterialStack(Materials.Steel, ring.mMaterialAmount * 4);
        pipeRestrictiveHuge.mSecondaryMaterial = new MaterialStack(Materials.Steel, ring.mMaterialAmount * 5);
        cableGt12.mSecondaryMaterial = new MaterialStack(Materials.Ash, dustSmall.mMaterialAmount * 4);
        cableGt08.mSecondaryMaterial = new MaterialStack(Materials.Ash, dustSmall.mMaterialAmount * 3);
        cableGt04.mSecondaryMaterial = new MaterialStack(Materials.Ash, dustSmall.mMaterialAmount * 2);
        cableGt02.mSecondaryMaterial = new MaterialStack(Materials.Ash, dustSmall.mMaterialAmount);
        cableGt01.mSecondaryMaterial = new MaterialStack(Materials.Ash, dustSmall.mMaterialAmount);
        cell.mSecondaryMaterial = new MaterialStack(Materials.Tin, plate.mMaterialAmount * 2);
        cellPlasma.mSecondaryMaterial = new MaterialStack(Materials.Tin, plate.mMaterialAmount * 2);
        oreRedgranite.mSecondaryMaterial = new MaterialStack(Materials.GraniteRed, dust.mMaterialAmount);
        oreBlackgranite.mSecondaryMaterial = new MaterialStack(Materials.GraniteBlack, dust.mMaterialAmount);
        oreNetherrack.mSecondaryMaterial = new MaterialStack(Materials.Netherrack, dust.mMaterialAmount);
        oreNether.mSecondaryMaterial = new MaterialStack(Materials.Netherrack, dust.mMaterialAmount);
        oreEndstone.mSecondaryMaterial = new MaterialStack(Materials.Endstone, dust.mMaterialAmount);
        oreEnd.mSecondaryMaterial = new MaterialStack(Materials.Endstone, dust.mMaterialAmount);
        oreMarble.mSecondaryMaterial = new MaterialStack(Materials.Marble, dust.mMaterialAmount);
        oreBasalt.mSecondaryMaterial = new MaterialStack(Materials.Basalt, dust.mMaterialAmount);
        oreSmall.mSecondaryMaterial = new MaterialStack(Materials.Stone, dust.mMaterialAmount * 2);
        ore.mSecondaryMaterial = new MaterialStack(Materials.Stone, dust.mMaterialAmount);
        crushed.mSecondaryMaterial = new MaterialStack(Materials.Stone, dust.mMaterialAmount);
        toolHeadDrill.mSecondaryMaterial = new MaterialStack(Materials.Steel, plate.mMaterialAmount * 4);
        toolHeadChainsaw.mSecondaryMaterial = new MaterialStack(Materials.Steel, plate.mMaterialAmount * 4 + ring.mMaterialAmount * 2);
        toolHeadWrench.mSecondaryMaterial = new MaterialStack(Materials.Steel, ring.mMaterialAmount + screw.mMaterialAmount * 2);
    }

    public final ArrayList<ItemStack> mPrefixedItems = new ArrayList<ItemStack>();
    public final short mTextureIndex;
    public final String mRegularLocalName, mLocalizedMaterialPre, mLocalizedMaterialPost;
    public final boolean mIsUsedForOreProcessing, mIsEnchantable, mIsUnificatable, mIsMaterialBased, mIsSelfReferencing, mIsContainer, mDontUnificateActively, mIsUsedForBlocks, mAllowNormalRecycling, mGenerateDefaultItem;
    public final List<TC_AspectStack> mAspects = new ArrayList<TC_AspectStack>();
    public final Collection<OrePrefixes> mFamiliarPrefixes = new HashSet<OrePrefixes>();
    /**
     * Used to determine the amount of Material this Prefix contains.
     * Multiply or Divide GregTech_API.MATERIAL_UNIT to get the Amounts in comparision to one Ingot.
     * 0 = Null
     * Negative = Undefined Amount
     */
    public final int mMaterialAmount;
    public final Collection<Materials> mDisabledItems = new HashSet<Materials>(), mNotGeneratedItems = new HashSet<Materials>(), mIgnoredMaterials = new HashSet<Materials>(), mGeneratedItems = new HashSet<Materials>();
    private final ArrayList<IOreRecipeRegistrator> mOreProcessing = new ArrayList<IOreRecipeRegistrator>();
    public ItemStack mContainerItem = null;
    public ICondition<ISubTagContainer> mCondition = null;
    public byte mDefaultStackSize = 64;
    public MaterialStack mSecondaryMaterial = null;
    public OrePrefixes mPrefixInto = this;
    public float mHeatDamage = 0.0F; // Negative for Frost Damage
    public static List<OrePrefixes> mPreventableComponents = new LinkedList<>(Arrays.asList(OrePrefixes.gem, OrePrefixes.ingotHot, OrePrefixes.plate, OrePrefixes.plateDense, OrePrefixes.stick, OrePrefixes.bolt, OrePrefixes.screw, OrePrefixes.ring, OrePrefixes.foil, OrePrefixes.toolHeadSword, OrePrefixes.toolHeadPickaxe, OrePrefixes.toolHeadShovel, OrePrefixes.toolHeadAxe, OrePrefixes.toolHeadHoe, OrePrefixes.toolHeadHammer, OrePrefixes.toolHeadFile, OrePrefixes.toolHeadSaw, OrePrefixes.toolHeadDrill, OrePrefixes.toolHeadChainsaw, OrePrefixes.toolHeadWrench, OrePrefixes.toolHeadUniversalSpade, OrePrefixes.toolHeadSense, OrePrefixes.toolHeadPlow, OrePrefixes.toolHeadBuzzSaw, OrePrefixes.turbineBlade, OrePrefixes.wireFine, OrePrefixes.gearGtSmall, OrePrefixes.rotor, OrePrefixes.spring, OrePrefixes.gemChipped, OrePrefixes.gemFlawed, OrePrefixes.gemFlawless, OrePrefixes.gemExquisite, OrePrefixes.gearGt));
    /**
     * Yes this Value can be changed to add Bits for the MetaGenerated-Item-Check.
     */
    public int mMaterialGenerationBits = 0;
    OrePrefixes(String aRegularLocalName, String aLocalizedMaterialPre, String aLocalizedMaterialPost, boolean aIsUnificatable, boolean aIsMaterialBased, boolean aIsSelfReferencing, boolean aIsContainer, boolean aDontUnificateActively, boolean aIsUsedForBlocks, boolean aAllowNormalRecycling, boolean aGenerateDefaultItem, boolean aIsEnchantable, boolean aIsUsedForOreProcessing, int aMaterialGenerationBits, int aMaterialAmount, int aDefaultStackSize, int aTextureindex) {
        mIsUnificatable = aIsUnificatable;
        mIsMaterialBased = aIsMaterialBased;
        mIsSelfReferencing = aIsSelfReferencing;
        mIsContainer = aIsContainer;
        mDontUnificateActively = aDontUnificateActively;
        mIsUsedForBlocks = aIsUsedForBlocks;
        mAllowNormalRecycling = aAllowNormalRecycling;
        mGenerateDefaultItem = aGenerateDefaultItem;
        mIsEnchantable = aIsEnchantable;
        mIsUsedForOreProcessing = aIsUsedForOreProcessing;
        mMaterialGenerationBits = aMaterialGenerationBits;
        mMaterialAmount = aMaterialAmount;
        mRegularLocalName = aRegularLocalName;
        mLocalizedMaterialPre = aLocalizedMaterialPre;
        mLocalizedMaterialPost = aLocalizedMaterialPost;
        mDefaultStackSize = (byte) aDefaultStackSize;
        mTextureIndex = (short) aTextureindex;

        if (name().startsWith("ore")) {
            new TC_AspectStack(TC_Aspects.TERRA, 1).addToAspectList(mAspects);
        } else if (name().startsWith("wire") || name().startsWith("cable")) {
            new TC_AspectStack(TC_Aspects.ELECTRUM, 1).addToAspectList(mAspects);
        } else if (name().startsWith("dust")) {
            new TC_AspectStack(TC_Aspects.PERDITIO, 1).addToAspectList(mAspects);
        } else if (name().startsWith("crushed")) {
            new TC_AspectStack(TC_Aspects.PERFODIO, 1).addToAspectList(mAspects);
        } else if (name().startsWith("ingot") || name().startsWith("nugget")) {
            new TC_AspectStack(TC_Aspects.METALLUM, 1).addToAspectList(mAspects);
        } else if (name().startsWith("armor")) {
            new TC_AspectStack(TC_Aspects.TUTAMEN, 1).addToAspectList(mAspects);
        } else if (name().startsWith("stone")) {
            new TC_AspectStack(TC_Aspects.TERRA, 1).addToAspectList(mAspects);
        } else if (name().startsWith("pipe")) {
            new TC_AspectStack(TC_Aspects.ITER, 1).addToAspectList(mAspects);
        } else if (name().startsWith("gear")) {
            new TC_AspectStack(TC_Aspects.MOTUS, 1).addToAspectList(mAspects);
            new TC_AspectStack(TC_Aspects.MACHINA, 1).addToAspectList(mAspects);
        } else if (name().startsWith("frame") || name().startsWith("plate")) {
            new TC_AspectStack(TC_Aspects.FABRICO, 1).addToAspectList(mAspects);
        } else if (name().startsWith("tool")) {
            new TC_AspectStack(TC_Aspects.INSTRUMENTUM, 2).addToAspectList(mAspects);
        } else if (name().startsWith("gem") || name().startsWith("crystal") || name().startsWith("lens")) {
            new TC_AspectStack(TC_Aspects.VITREUS, 1).addToAspectList(mAspects);
        } else if (name().startsWith("crate")) {
            new TC_AspectStack(TC_Aspects.ITER, 2).addToAspectList(mAspects);
        } else if (name().startsWith("circuit")) {
            new TC_AspectStack(TC_Aspects.COGNITIO, 1).addToAspectList(mAspects);
        } else if (name().startsWith("computer")) {
            new TC_AspectStack(TC_Aspects.COGNITIO, 4).addToAspectList(mAspects);
        } else if (name().startsWith("battery")) {
            new TC_AspectStack(TC_Aspects.ELECTRUM, 1).addToAspectList(mAspects);
        }
    }

    public static void initMaterialComponents() { //TODO CALL THIS TO REANBLE CONFIG MATS
        boolean enablePerItemSettings = GregTech_API.sMaterialComponents.get("general", "enablePerItemSettings", false);
        for (IMaterialHandler aRegistrator : Materials.mMaterialHandlers) {
            aRegistrator.onComponentInit();
        }
        for (Materials aMaterial : Materials.MATERIALS_ALL) {
            if (aMaterial.mMetaItemSubID > 0) {
                for (IMaterialHandler aRegistrator : Materials.mMaterialHandlers) {
                    aRegistrator.onComponentIteration(aMaterial);
                }
                if (enablePerItemSettings) {
                    StringBuilder aConfigPathSB = new StringBuilder();
                    aConfigPathSB.append("materialcomponents.").append(aMaterial.mConfigSection).append(".").append(aMaterial.mName);
                    String aConfigPath = aConfigPathSB.toString();
                    for (OrePrefixes aPrefix : mPreventableComponents) {
                        boolean aEnableComponent = GregTech_API.sMaterialComponents.get(aConfigPath, aPrefix.toString(), !aPrefix.mDisabledItems.contains(aMaterial));
                        MaterialFlags aFlag = MaterialFlags.getFlagForValue(aPrefix.mMaterialGenerationBits);
                        if (!aEnableComponent) { //Disable component if false and is not already in disabled list
                            if (aMaterial.hasFlag(aFlag)) {
                                aMaterial.remove(aFlag);
                            }
                        } else if (aEnableComponent) { //Enable component if true and is not already in enabled list
                            if (!aMaterial.hasFlag(aFlag)) {
                                aMaterial.add(aFlag);
                            }
                        }
                    }
                    aConfigPathSB.setLength(0);
                }
            }
        }
    }

    public static OrePrefixes getOrePrefix(String aOre) {
        for (OrePrefixes tPrefix : values())
            if (aOre.startsWith(tPrefix.toString())) {
                if (tPrefix == oreNether && aOre.equals("oreNetherQuartz")) return ore;
                if (tPrefix == oreBasalt && aOre.equals("oreBasalticMineralSand")) return ore;
                return tPrefix;
            }
        return null;
    }

    public static String stripPrefix(String aOre) {
        for (OrePrefixes tPrefix : values()) {
            if (aOre.startsWith(tPrefix.toString())) {
                return aOre.replaceFirst(tPrefix.toString(), "");
            }
        }
        return aOre;
    }

    public static String replacePrefix(String aOre, OrePrefixes aPrefix) {
        for (OrePrefixes tPrefix : values()) {
            if (aOre.startsWith(tPrefix.toString())) {
                return aOre.replaceFirst(tPrefix.toString(), aPrefix.toString());
            }
        }
        return "";
    }

    public static OrePrefixes getPrefix(String aPrefixName) {
        return getPrefix(aPrefixName, null);
    }

    public static OrePrefixes getPrefix(String aPrefixName, OrePrefixes aReplacement) {
        Object tObject = GT_Utility.getFieldContent(OrePrefixes.class, aPrefixName, false, false);
        if (tObject instanceof OrePrefixes) return (OrePrefixes) tObject;
        return aReplacement;
    }

    public static Materials getMaterial(String aOre) {
        return Materials.get(stripPrefix(aOre));
    }

    public static Materials getMaterial(String aOre, OrePrefixes aPrefix) {
        return Materials.get(aOre.replaceFirst(aPrefix.toString(), ""));
    }

    public static Materials getRealMaterial(String aOre, OrePrefixes aPrefix) {
        return Materials.getRealMaterial(aOre.replaceFirst(aPrefix.toString(), ""));
    }

    public static boolean isInstanceOf(String aName, OrePrefixes aPrefix) {
        return aName != null && aName.startsWith(aPrefix.toString());
    }

    public boolean add(ItemStack aStack) {
        if (aStack == null) return false;
        if (!contains(aStack)) mPrefixedItems.add(aStack);
        while (mPrefixedItems.contains(null)) mPrefixedItems.remove(null);
        return true;
    }

    public boolean contains(ItemStack aStack) {
        if (aStack == null) return false;
        for (ItemStack tStack : mPrefixedItems)
            if (GT_Utility.areStacksEqual(aStack, tStack, !tStack.hasTagCompound())) return true;
        return false;
    }

    public boolean doGenerateItem(Materials aMaterial) {
        return aMaterial != null && aMaterial != Materials._NULL && (aMaterial.hasFlag(mMaterialGenerationBits) || mGeneratedItems.contains(aMaterial)) && !mNotGeneratedItems.contains(aMaterial) && !mDisabledItems.contains(aMaterial) && (mCondition == null || mCondition.isTrue(aMaterial));
    }

    public boolean ignoreMaterials(Materials... aMaterials) {
        for (Materials tMaterial : aMaterials) if (tMaterial != null) mIgnoredMaterials.add(tMaterial);
        return true;
    }

    public boolean isIgnored(Materials aMaterial) {
        if (aMaterial != null && (!aMaterial.mUnificatable || aMaterial != aMaterial.mMaterialInto)) return true;
        return mIgnoredMaterials.contains(aMaterial);
    }

    public boolean addFamiliarPrefix(OrePrefixes aPrefix) {
        if (aPrefix == null || mFamiliarPrefixes.contains(aPrefix) || aPrefix == this) return false;
        return mFamiliarPrefixes.add(aPrefix);
    }

    public boolean add(IOreRecipeRegistrator aRegistrator) {
        if (aRegistrator == null) return false;
        return mOreProcessing.add(aRegistrator);
    }

    public void processOre(Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
        if (aMaterial != null && (aMaterial != Materials._NULL || mIsSelfReferencing || !mIsMaterialBased) && GT_Utility.isStackValid(aStack)) {
            for (IOreRecipeRegistrator tRegistrator : mOreProcessing) {
                tRegistrator.registerOre(this, aMaterial, aOreDictName, aModName, GT_Utility.copyAmount(1, aStack));
            }
        }
    }

    public Object get(Object aMaterial) {
        if (aMaterial instanceof Materials) return new ItemData(this, (Materials) aMaterial);
        return name() + aMaterial;
    }

    @SuppressWarnings("incomplete-switch")
    public String getDefaultLocalNameForItem(Materials aMaterial) {
        // Certain Materials have slightly different Localizations.
        switch (aMaterial.mName) {
            case "Glass":
            case "BorosilicateGlass":
                if (name().startsWith("gem")) return mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Crystal";
                if (name().startsWith("plate")) return mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Pane";
                if (name().startsWith("ingot")) return mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Bar";
                if (name().startsWith("nugget")) return mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Chip";
                break;
            case "Wheat":
                if (name().startsWith("dust")) return mLocalizedMaterialPre + "Flour";
                break;
            case "Ice":
                if (name().startsWith("dust")) return mLocalizedMaterialPre + "Crushed Ice";
                break;
            case "Wood":
            case "WoodSealed":
                if (name().startsWith("bolt")) return "Short " + aMaterial.mDefaultLocalName + " Stick";
                if (name().startsWith("stick")) return mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Stick";
                if (name().startsWith("dust")) return mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Pulp";
                if (name().startsWith("nugget")) return mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Chip";
                if (name().startsWith("plate")) return mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Plank";
                break;
            case "Plastic":
            case "Rubber":
            case "Polyethylene":
            case "Epoxid":
            case "EpoxidFiberReinforced":
            case "Polydimethylsiloxane":
            case "Silicone":
            case "Polysiloxane":
            case "Polycaprolactam":
            case "Polytetrafluoroethylene":
            case "PolyvinylChloride":
            case "Polystyrene":
            case "StyreneButadieneRubber":
                if (name().startsWith("dust")) return mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Pulp";
                if (name().startsWith("plate")) return mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Sheet";
                if (name().startsWith("ingot")) return mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Bar";
                if (name().startsWith("nugget")) return mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Chip";
                if (name().startsWith("foil")) return "Thin " + aMaterial.mDefaultLocalName + " Sheet";
                break;
            case "FierySteel":
                if (mIsContainer) return mLocalizedMaterialPre + "Fiery Blood" + mLocalizedMaterialPost;
                break;
            case "Bone":
                if (name().startsWith("dust")) return mLocalizedMaterialPre + "Bone Meal";
                break;
            case "Blaze":
            case "Snow":
                if (name().startsWith("dust")) return mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Powder";
                break;
            case "Paper":
                if (name().startsWith("dust")) return mLocalizedMaterialPre + "Chad";
                switch (this) {
                    case plate: return "Sheet of Paper";
                }
                break;
            case "Ash":
            case "DarkAsh":
            case "Gunpowder":
            case "Sugar":
            case "Salt":
            case "RockSalt":
            case "RareEarth":
                if (name().startsWith("dust")) return mLocalizedMaterialPre + aMaterial.mDefaultLocalName;
                break;
            case "Bentonite":
            case "Talc":
            case "BasalticMineralSand":
            case "GraniticMineralSand":
            case "Pitchblende":
                if (name().startsWith("dust")) return mLocalizedMaterialPre + aMaterial.mDefaultLocalName;
                switch (this) {
                    case crushedCentrifuged:
                    case crushedPurified:
                        return mLocalizedMaterialPre + aMaterial.mDefaultLocalName;
                    case crushed:
                        return "Ground " + aMaterial.mDefaultLocalName;
                }
                break;
        }
        if (ProcessingModSupport.aEnableThaumcraftMats) {
            switch (aMaterial.mName) {
                case "InfusedAir":
                case "InfusedDull":
                case "InfusedEarth":
                case "InfusedEntropy":
                case "InfusedFire":
                case "InfusedOrder":
                case "InfusedVis":
                case "InfusedWater":
                    if (name().startsWith("gem")) return mLocalizedMaterialPre + "Shard of " + aMaterial.mDefaultLocalName;
                    if (name().startsWith("crystal")) return mLocalizedMaterialPre + "Shard of " + aMaterial.mDefaultLocalName;
                    if (name().startsWith("plate"))
                        return mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Crystal Plate";
                    if (name().startsWith("dust"))
                        return mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Crystal Powder";
                    switch (this) {
                        case crushedCentrifuged:
                        case crushedPurified:
                        case crushed:
                            return mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Crystals";
                    }
                    break;
            }
        }
        // Use Standard Localization
        return mLocalizedMaterialPre + aMaterial.mDefaultLocalName + mLocalizedMaterialPost;
    }
}