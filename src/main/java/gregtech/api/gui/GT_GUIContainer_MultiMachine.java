package gregtech.api.gui;

import gregtech.api.enums.ItemList;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Utility;
import gregtech.common.items.GT_MetaGenerated_Tool_01;
import gregtech.common.tileentities.machines.multi.GT_MetaTileEntity_DrillerBase;
import gregtech.common.tileentities.machines.multi.GT_MetaTileEntity_LargeTurbine;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

import static gregtech.api.enums.GT_Values.RES_PATH_GUI;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * <p/>
 * The GUI-Container I use for all my Basic Machines
 * <p/>
 * As the NEI-RecipeTransferRect Handler can't handle one GUI-Class for all GUIs I needed to produce some dummy-classes which extend this class
 */
public class GT_GUIContainer_MultiMachine extends GT_GUIContainerMetaTile_Machine {
    private static String aPipeError = trans("132", "Pipe is loose.");
    private static String aScrewError = trans("133", "Screws are missing.");
    private static String aStuckError = trans("134", "Something is stuck.");
    private static String aPlateError = trans("135", "Platings are dented.");
    private static String aCircuitError = trans("136", "Circuitry burned out.");
    private static String aBelongError = trans("137", "That doesn't belong there.");
    private static String aStructureError = trans("138", "Incomplete Structure.");
    private static String aDisabled1Error = trans("139", "Hit with Soft Mallet");
    private static String aDisabled2Error = trans("140", "To cycle the Machine");
    private static String aPerfectError = trans("142", "Machine State: ");
    private static String aMinerError = trans("143", "Missing Mining Pipe");
    private static String aTurbineError = trans("144", "Missing Turbine Rotor");
    //private static String aCursor = "_";

    public String mName = "";
    public String mNEI = "";

    public String mLastError = "";
    public boolean mDefaultGUI = false;

    public GT_GUIContainer_MultiMachine(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, String aName, String aTextureFile) {
        super(new GT_Container_MultiMachine(aInventoryPlayer, aTileEntity), RES_PATH_GUI + "multimachines/" + (aTextureFile == null ? "MultiblockDisplay" : aTextureFile));
        mName = aName;
        mDefaultGUI = aTextureFile == null;
    }

    public GT_GUIContainer_MultiMachine(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, String aName, String aTextureFile, String aNEI) {
        super(new GT_Container_MultiMachine(aInventoryPlayer, aTileEntity), RES_PATH_GUI + "multimachines/" + (aTextureFile == null ? "MultiblockDisplay" : aTextureFile));
        mName = aName;
        mNEI = aNEI;
        mDefaultGUI = aTextureFile == null;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int aMouseX, int aMouseY) {
        fontRendererObj.drawString(mName, 88 - fontRendererObj.getStringWidth(mName) / 2, 4, 4210752);

        if (mContainer != null) {
            GT_Container_MultiMachine aMultiMachineContainer = (GT_Container_MultiMachine) mContainer;

            if (aMultiMachineContainer.mHasInitialBasicMachineClientUpdateBeenRecieved && aMultiMachineContainer.mHasInitialMultiMachineClientUpdateBeenRecieved) {
                String[] aDebugInfo = new String[2];
                int aErrorCode = aMultiMachineContainer.mDisplayErrorCode;
                if ((aErrorCode & 1) != 0) aDebugInfo[0] = aPipeError;
                if ((aErrorCode & 2) != 0) aDebugInfo[0] = aScrewError;
                if ((aErrorCode & 4) != 0) aDebugInfo[0] = aStuckError;
                if ((aErrorCode & 8) != 0) aDebugInfo[0] = aPlateError;
                if ((aErrorCode & 16) != 0) aDebugInfo[0] = aCircuitError;
                if ((aErrorCode & 32) != 0) aDebugInfo[0] = aBelongError;
                if ((aErrorCode & 64) != 0) aDebugInfo[0] = aStructureError;

                if (aMultiMachineContainer.mDisplayErrorCode == 0) {
                    if (aMultiMachineContainer.mActive == 0) {
                        aDebugInfo[0] = "Status: " + ((aMultiMachineContainer.mWorkEnabled == 1) ? "Idle" : "Work Disabled");
                        if (!mLastError.isEmpty()) {
                            aDebugInfo[1] = mLastError;
                        }
                    } else {
                        aDebugInfo[0] = "Status: Running";
                        aDebugInfo[1] = "Time Remaining: " + (aMultiMachineContainer.mMaxProgressTime - aMultiMachineContainer.mProgressTime) / 20 + "s";
                    }
                    if (aMultiMachineContainer.mTileEntity.getMetaTileEntity() instanceof GT_MetaTileEntity_DrillerBase) {
                        ItemStack tItem = aMultiMachineContainer.mTileEntity.getMetaTileEntity().getStackInSlot(1);
                        if (tItem == null || !GT_Utility.areStacksEqual(tItem, ItemList.Block_MiningPipe.get(1))) {
                            aDebugInfo[0] = aMinerError;
                        }
                    } else if (aMultiMachineContainer.mTileEntity.getMetaTileEntity() instanceof GT_MetaTileEntity_LargeTurbine) {
                        ItemStack tItem = aMultiMachineContainer.mTileEntity.getMetaTileEntity().getStackInSlot(1);
                        if (tItem == null || !(tItem.getItem() == GT_MetaGenerated_Tool_01.INSTANCE && tItem.getItemDamage() >= 170 && tItem.getItemDamage() <= 177)) {
                            aDebugInfo[0] = aTurbineError;
                        }
                    }
                }

                fontRendererObj.drawString(aDebugInfo[0] /*+ (aCursor.equals("") ? "_" : "")*/, 18, 59, 16448255);
                if (aDebugInfo.length >= 2) {
                    fontRendererObj.drawString(aDebugInfo[1], 18, 68, 16448255);
                }
            }

            /*int aGUIBoundX = aMouseX - (width - xSize) / 2;
            int aGUIBoundY = aMouseY - (height - ySize) / 2 + 5;
            if (aGUIBoundX >= 72 && aGUIBoundX <= 102 && aGUIBoundY >= 30 && aGUIBoundY <= 50) {
                List<String> aList = new ArrayList<>();
                aList.add(aMultiMachineContainer.mActive == 1 ? ("Seconds Remaining: " + (aMultiMachineContainer.mMaxProgressTime - aMultiMachineContainer.mProgressTime) / 20) : "Idle");
                drawHoveringText(aList, aGUIBoundX, aGUIBoundY, fontRendererObj);
            }*/
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        if (mContainer != null && mContainer.mMaxProgressTime > 0) {
            if (mDefaultGUI) {
                drawTexturedModalRect(x + 78, y + 24, 176, 0, (mContainer.mProgressTime * 20) / mContainer.mMaxProgressTime, 18);
            } else {
                //drawTexturedModalRect(x + 83, y + 25 + 54 - tScale, 204, 54 - tScale, 10, tScale);
                drawTexturedModalRect(x + 81, y + 23, 179, 2, 14, -(mContainer.mProgressTime / 20));
            }
        }
    }
}