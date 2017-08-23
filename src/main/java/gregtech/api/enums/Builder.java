//package gregtech.api.enums;
//
//import gregtech.api.objects.MaterialStack;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class Builder {
//	public static final int DIESEL = 0, GAS = 1, THERMAL = 2, SEMIFLUID = 3, PLASMA = 4, MAGIC = 5;
//
//	private int metaItemSubID;
//	private TextureSet iconSet;
//	private float toolSpeed = 1.0f;
//	private int durability = 0;
//	private int toolQuality = 0;
//	private int types = 0;
//	private int r = 255, g = 255, b = 255, a = 0;
//	private String name;
//	private String defaultLocalName;
//	private int fuelType = 0;
//	private int fuelPower = 0;
//	private int meltingPoint = 0;
//	private int blastFurnaceTemp = 0;
//	private boolean blastFurnaceRequired = false;
//	private boolean transparent = false;
//	private int oreValue = 1;
//	private int densityMultiplier = 1;
//	private int densityDivider = 1;
//	private Dyes color = Dyes.dyeNULL;
//	private int extraData = 0;
//	private List<MaterialStack> materialList = new ArrayList<MaterialStack>();
//	private List<Aspects.AspectStack> aspects = new ArrayList<Aspects.AspectStack>();
//	private boolean hasCorrespondingFluid = false;
//	private boolean hasCorrespondingGas = false;
//	private boolean canBeCracked = false;
//	private boolean canBeSteamCracked = false;
//	private int liquidTemperature = 300;
//	private int gasTemperature = 300;
//
//	public Builder(int metaItemSubID, TextureSet iconSet, String defaultLocalName) {
//		this.metaItemSubID = metaItemSubID;
//		this.iconSet = iconSet;
//		this.name = defaultLocalName.replace(" ", "").replace("-", "");
//		this.defaultLocalName = defaultLocalName;
//	}
//
//	public Builder(int metaItemSubID, String defaultLocalName, int r, int g, int b, Dyes color, TextureSet iconSet) {
//		this.metaItemSubID = metaItemSubID;
//		this.defaultLocalName = defaultLocalName;
//		this.r = r;
//		this.g = g;
//		this.b = b;
//		this.color = color;
//		this.iconSet = iconSet;
//	}
//
//	public Materials build() {
//		return new Materials(metaItemSubID, iconSet, toolSpeed, durability, toolQuality, types, r, g, b, a, name, defaultLocalName, fuelType, fuelPower, meltingPoint, blastFurnaceTemp,
//				blastFurnaceRequired, transparent, oreValue, densityMultiplier, densityDivider, color, extraData, materialList, aspects)
//				.setHasCorrespondingFluid(hasCorrespondingFluid)
//				.setHasCorrespondingGas(hasCorrespondingGas)
//				.setCanBeCracked(canBeCracked);
//	}
//
//	public Builder asFluid(int fuelPower) {
//		this.hasCorrespondingFluid = true;
//		types = types | 16;
//		this.fuelPower = fuelPower;
//		return this;
//	}
//
//	public Builder asGas(int fuelPower) {
//		this.hasCorrespondingGas = true;
//		types = types | 16;
//		this.fuelPower = fuelPower;
//		this.fuelType = GAS;
//		return this;
//	}
//
//	public Builder asCrackGas(int fuelPower) {
//		this.asGas(fuelPower);
//		this.addElectrolyzerRecipe();
//		this.setCanBeCracked(true);
//		return this;
//	}
//
//	public Builder asDust() {
//		this.asFluid(0);
//		types = types | 1;
//		return this;
//	}
//
//	public Builder asSolid() {
//		this.asDust();
//		return this;
//	}
//
//	public Builder asMetal() {
//		this.asSolid();
//		types = types | 2;
//		return this;
//	}
//
//	public Builder asGem(boolean transparent) {
//		asSolid();
//		types = types | 2;
//		this.transparent = transparent;
//		return this;
//	}
//
//	public Builder addOre(){
//		types = types | 8;
//		return this;
//	}
//
//	public Builder addCell(){
//		types = types | 16;
//		return this;
//	}
//
//	public Builder addPlasma() {
//		types = types | 32;
//		return this;
//	}
//
//	public Builder addTool(int toolSpeed, int toolQuality, int durability){
//		types = types | 64;
//		this.toolSpeed = toolSpeed;
//		this.toolQuality = toolQuality;
//		this.durability = durability;
//		return this;
//	}
//
//	public Builder addGear(){
//		types = types | 128;
//		return this;
//	}
//
//	public Builder setFuelType(int fuelType) {
//		this.fuelType = fuelType;
//		return this;
//	}
//
//	public Builder setFuelPower(int fuelPower) {
//		this.fuelPower = fuelPower;
//		return this;
//	}
//
//	public Builder setMeltingPoint(int meltingPoint) {
//		this.meltingPoint = meltingPoint;
//		return this;
//	}
//
//	public Builder setBlastFurnaceTemp(int blastFurnaceTemp) {
//		this.blastFurnaceTemp = blastFurnaceTemp;
//		return this;
//	}
//
//	public Builder setBlastFurnaceRequired(boolean blastFurnaceRequired) {
//		this.blastFurnaceRequired = blastFurnaceRequired;
//		return this;
//	}
//
//	public Builder setOreValue(int oreValue) {
//		this.oreValue = oreValue;
//		return this;
//	}
//
//	public Builder setDensityMultiplier(int densityMultiplier) {
//		this.densityMultiplier = densityMultiplier;
//		return this;
//	}
//
//	public Builder setDensityDivider(int densityDivider) {
//		this.densityDivider = densityDivider;
//		return this;
//	}
//
//	public Builder setExtraData(int extraData) {
//		this.extraData = extraData;
//		return this;
//	}
//
//	public Builder addElectrolyzerRecipe(){
//		extraData = extraData | 1;
//		return this;
//	}
//
//	public Builder addCentrifugeRecipe(){
//		extraData = extraData | 2;
//		return this;
//	}
//
//	public Builder setMats(List<MaterialStack> materialList) {
//		this.materialList = materialList;
//		return this;
//	}
//
//	public Builder setMats(MaterialStack ... materials) {
//		this.materialList = Arrays.asList(materials);
//		return this;
//	}
//
//	public Builder setAspects(List<Aspects.AspectStack> aspects) {
//		this.aspects = aspects;
//		return this;
//	}
//
//	public boolean canBeCracked() {
//		return canBeCracked;
//	}
//
//	public Builder setCanBeCracked(boolean canBeCracked) {
//		this.canBeCracked = canBeCracked;
//		return this;
//	}
//
//}
