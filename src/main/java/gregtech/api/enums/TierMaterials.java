package gregtech.api.enums;


import com.dreammaster.gthandler.CustomItemList;



/**
 * Experimental Class for later
 */
public class TierMaterials {

    public static final TierMaterials[]
            CONSTRUCTION = new TierMaterials[]{

                    new TierMaterials(SubTag.CONSTRUCTION_MATERIAL, 0, 8, null, null, null, null, null, null, null, null),
                    new TierMaterials(SubTag.CONSTRUCTION_MATERIAL, 1, 32, Materials.WroughtIron, ItemList.Hull_LV, OrePrefixes.cableGt01.get(Materials.Tin), OrePrefixes.wireGt01.get(Materials.RedstoneAlloy), OrePrefixes.pipeTiny.get(Materials.Copper), OrePrefixes.rotor.get(Materials.Tin), OrePrefixes.circuit.get(Materials.Basic), ItemList.Electric_Motor_LV),
                    new TierMaterials(SubTag.CONSTRUCTION_MATERIAL, 2, 128, Materials.Aluminium, ItemList.Hull_MV, OrePrefixes.cableGt01.get(Materials.Copper), OrePrefixes.wireGt01.get(Materials.SuperconductorMV), OrePrefixes.pipeTiny.get(Materials.Bronze), OrePrefixes.rotor.get(Materials.Bronze), OrePrefixes.circuit.get(Materials.Good), ItemList.Electric_Motor_MV),
                    new TierMaterials(SubTag.CONSTRUCTION_MATERIAL, 3, 512, Materials.StainlessSteel, ItemList.Hull_HV, OrePrefixes.cableGt02.get(Materials.Gold), OrePrefixes.wireGt01.get(Materials.SuperconductorHV), OrePrefixes.pipeSmall.get(Materials.Plastic), OrePrefixes.rotor.get(Materials.Steel), OrePrefixes.circuit.get(Materials.Advanced), ItemList.Electric_Motor_HV),
                    new TierMaterials(SubTag.CONSTRUCTION_MATERIAL, 4, 2048, Materials.Titanium, ItemList.Hull_EV, OrePrefixes.cableGt02.get(Materials.Aluminium), OrePrefixes.wireGt01.get(Materials.SuperconductorEV), OrePrefixes.pipeLarge.get(Materials.Plastic), OrePrefixes.rotor.get(Materials.StainlessSteel), OrePrefixes.circuit.get(Materials.Data), ItemList.Electric_Motor_EV),
                    new TierMaterials(SubTag.CONSTRUCTION_MATERIAL, 5, 8192, Materials.TungstenSteel, ItemList.Hull_IV, OrePrefixes.cableGt02.get(Materials.Platinum), OrePrefixes.wireGt01.get(Materials.SuperconductorIV), OrePrefixes.pipeLarge.get(Materials.Titanium), OrePrefixes.rotor.get(Materials.TungstenSteel), OrePrefixes.circuit.get(Materials.Elite), ItemList.Electric_Motor_IV),
                    new TierMaterials(SubTag.CONSTRUCTION_MATERIAL, 6, 32768, Materials.Chrome, ItemList.Hull_LuV, OrePrefixes.cableGt02.get(Materials.VanadiumGallium), OrePrefixes.wireGt01.get(Materials.SuperconductorLuV), OrePrefixes.pipe.get(Materials.PolyvinylChloride), OrePrefixes.rotor.get(Materials.Chrome), OrePrefixes.circuit.get(Materials.Master), ItemList.Electric_Motor_LuV),
                    new TierMaterials(SubTag.CONSTRUCTION_MATERIAL, 7, 131072, Materials.Iridium, ItemList.Hull_ZPM, OrePrefixes.cableGt04.get(Materials.Naquadah), OrePrefixes.wireGt01.get(Materials.SuperconductorZPM), OrePrefixes.pipeLarge.get(Materials.PolyvinylChloride), OrePrefixes.rotor.get(Materials.Iridium), OrePrefixes.circuit.get(Materials.Ultimate), ItemList.Electric_Motor_ZPM),
                    new TierMaterials(SubTag.CONSTRUCTION_MATERIAL, 8, 524288, Materials.Osmium, ItemList.Hull_UV, OrePrefixes.cableGt04.get(Materials.ElectrumFlux), OrePrefixes.wireGt01.get(Materials.SuperconductorUV), OrePrefixes.pipeHuge.get(Materials.PolyvinylChloride), OrePrefixes.rotor.get(Materials.Osmium), OrePrefixes.circuit.get(Materials.Superconductor), ItemList.Electric_Motor_UV),
                    new TierMaterials(SubTag.CONSTRUCTION_MATERIAL, 9, 2097152, Materials.Neutronium, ItemList.Hull_MAX, OrePrefixes.wireGt08.get(Materials.Ichorium), OrePrefixes.wireGt01.get(Materials.SuperconductorUHV), OrePrefixes.pipe.get(Materials.MysteriousCrystal), OrePrefixes.rotor.get(Materials.Neutronium), OrePrefixes.circuit.get(Materials.Infinite), ItemList.Electric_Motor_UHV),
                    new TierMaterials(SubTag.CONSTRUCTION_MATERIAL, 10, 8388608, Materials.Bedrockium, CustomItemList.Hull_UEV, OrePrefixes.cableGt08.get(Materials.Draconium), null, OrePrefixes.pipe.get(Materials.NetherStar), OrePrefixes.rotor.get(Materials.Draconium), OrePrefixes.circuit.get(Materials.Bio), ItemList.Electric_Motor_UEV),
                    new TierMaterials(SubTag.CONSTRUCTION_MATERIAL, 11, 33554432, Materials.BlackPlutonium, CustomItemList.Hull_UIV, OrePrefixes.cableGt08.get(Materials.NetherStar), null, OrePrefixes.pipe.get(Materials.Infinity), OrePrefixes.rotor.get(Materials.Infinity), OrePrefixes.circuit.get(Materials.Nano), ItemList.Electric_Motor_UIV),
                    new TierMaterials(SubTag.CONSTRUCTION_MATERIAL, 12, 134217728, GTNH_ExtraMaterials.Weebium, CustomItemList.Hull_UMV, OrePrefixes.cableGt08.get(GTNH_ExtraMaterials.PuellaMagium), null, OrePrefixes.pipe.get(Materials.Quantium), OrePrefixes.rotor.get(GTNH_ExtraMaterials.Weebium), OrePrefixes.circuit.get(Materials.Piko), ItemList.Electric_Motor_UIV),
    };

    /**
     * Used for Crafting Recipes
     */
    public final Object mHull, mCable, mLosslessCable, mCircuit, mPipe, mRotor, mMotor;
    private final SubTag mType;
    private final byte mRank;
    private final long mPrimaryValue;
    private final Materials mMaterial;

    public TierMaterials(SubTag aType, int aRank, long aPrimaryValue, Materials aMaterial, Object aHullObject, Object aConductingObject, Object aLosslessConductingObject, Object aPipeObject, Object aRotorObject, Object aCircuit, Object aMotorObject) {
        mType = aType;
        mRank = (byte) aRank;
        mPrimaryValue = aPrimaryValue;
        mMaterial = aMaterial;
        mPipe = aPipeObject;
        mRotor = aRotorObject;
        mHull = aHullObject;
        mCable = aConductingObject;
        mCircuit = aCircuit;
        mLosslessCable = aLosslessConductingObject;
        mMotor = aMotorObject;
    }

    public byte getRank() {
        return mRank;
    }

    public SubTag getEnergyType() {
        return mType;
    }

    public long getEnergyPrimary() {
        return mPrimaryValue;
    }

    public Materials getMaterial() {
        return mMaterial;
    }
}
