package gregtech.loaders.postload;

import com.dreammaster.gthandler.CustomItemList;
import com.github.bartimaeusnek.bartworks.common.loaders.ItemRegistry;
import com.github.bartimaeusnek.bartworks.util.BW_Util;
import cpw.mods.fml.common.Loader;
import gregtech.GT_Mod;
import gregtech.api.enums.*;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import ic2.core.Ic2Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.github.technus.tectech.recipe.TT_recipeAdder;
import com.github.technus.tectech.thing.metaTileEntity.hatch.GT_MetaTileEntity_Hatch_Rack;



import static gregtech.api.enums.GTNH_ExtraMaterials.*;
import static gregtech.api.enums.OrePrefixes.*;

public class GT_CustomCraftingRecipeLoader implements Runnable {
    @Override
    public void run() {

        long bitsd = GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE
                | GT_ModHandler.RecipeBits.REVERSIBLE | GT_ModHandler.RecipeBits.BUFFERED;


        GT_ModHandler.addCraftingRecipe(ItemList.Generator_Naquadah_Mark_X.get(1L), bitsd, new Object[]{"NCN", "FMF", "WCW", 'M', ItemList.Hull_MAX, 'F', ItemList.Field_Generator_UHV, 'C', circuit.get(Materials.Quantum), 'W', cableGt04.get(Materials.Bedrockium), 'N', stick.get(Materials.Neutronium)});


        GT_ModHandler.addCraftingRecipe(CustomItemList.WetTransformer_UXV_UMV.get(1L),
                bitsd,
                new Object[]{"XOC", "STA", "POC",
                        'A', OrePrefixes.springSmall.get(Materials.BlackPlutonium),
                        'C', OrePrefixes.wireGt16.get(Materials.Quantium),
                        'S', OrePrefixes.spring.get(Materials.DraconiumAwakened),
                        'X', OrePrefixes.wireGt08.get(Materials.BlackPlutonium),
                        'O', ItemList.Reactor_Coolant_Sp_2,
                        'P', ItemList.Electric_Pump_LuV,
                        'T', CustomItemList.Transformer_UXV_UMV});

        GT_ModHandler.addCraftingRecipe(CustomItemList.WetTransformer_OPV_UXV.get(1L),
                bitsd,
                new Object[]{"XOC", "STA", "POC",
                        'A', OrePrefixes.springSmall.get(Materials.Infinity),
                        'C', OrePrefixes.wireGt16.get(Materials.BlackPlutonium),
                        'S', OrePrefixes.spring.get(Materials.BlackPlutonium),
                        'X', OrePrefixes.wireGt08.get(Materials.DraconiumAwakened),
                        'O', ItemList.Reactor_Coolant_Sp_3,
                        'P', ItemList.Electric_Pump_ZPM,
                        'T', CustomItemList.Transformer_OPV_UXV});

        GT_ModHandler.addCraftingRecipe(CustomItemList.WetTransformer_MAXV_OPV.get(1L),
                bitsd,
                new Object[]{"XOC", "STA", "POC",
                        'A', OrePrefixes.springSmall.get(Materials.Infinity),
                        'C', OrePrefixes.wireGt16.get(Materials.DraconiumAwakened),
                        'S', OrePrefixes.spring.get(GTNH_ExtraMaterials.Weebium),
                        'X', OrePrefixes.wireGt08.get(Materials.Infinity),
                        'O', ItemList.Reactor_Coolant_Sp_6,
                        'P', ItemList.Electric_Pump_UHV,
                        'T', CustomItemList.Transformer_MAXV_OPV});



        GT_ModHandler.addCraftingRecipe(ItemList.Machine_UHV_AdvancedChemicalReactor.get(1L),
                bitsd,
                new Object[]{"PRP", "WMW", "CHC",
                        'P', pipeHuge.get(Materials.MysteriousCrystal),
                        'R', rotor.get(Materials.Neutronium),
                        'W', wireGt08.get(Materials.Ichorium),
                        'M', ItemList.Electric_Motor_UHV,
                        'C', circuit.get(Materials.Infinite),
                        'H', ItemList.Hull_MAX});


        GT_ModHandler.addCraftingRecipe(ItemList.Machine_UEV_AdvancedChemicalReactor.get(1L),
                bitsd,
                new Object[]{"PRP", "WMW", "CHC",
                        'P', pipeHuge.get(Materials.NetherStar),
                        'R', rotor.get(Materials.Draconium),
                        'W', cableGt08.get(Materials.Draconium),
                        'M', ItemList.Electric_Motor_UEV,
                        'C', circuit.get(Materials.Bio),
                        'H', CustomItemList.Hull_UEV});

        GT_ModHandler.addCraftingRecipe(ItemList.Machine_UIV_AdvancedChemicalReactor.get(1L),
                bitsd,
                new Object[]{"PRP", "WMW", "CHC",
                        'P', pipeHuge.get(Materials.Infinity),
                        'R', rotor.get(Materials.Infinity),
                        'W', cableGt08.get(Materials.NetherStar),
                        'M', ItemList.Electric_Motor_UIV,
                        'C', CustomItemList.NanoCircuit,
                        'H', CustomItemList.Hull_UIV});

        GT_ModHandler.addCraftingRecipe(ItemList.Machine_UMV_AdvancedChemicalReactor.get(1L),
                bitsd,
                new Object[]{"PRP", "WMW", "CHC",
                        'P', pipeHuge.get(Weebium),
                        'R', rotor.get(Weebium),
                        'W', cableGt08.get(Weebium),
                        'M', ItemList.Electric_Motor_UIV,
                        'C', CustomItemList.PikoCircuit,
                        'H', CustomItemList.Hull_UMV});




    }
}
