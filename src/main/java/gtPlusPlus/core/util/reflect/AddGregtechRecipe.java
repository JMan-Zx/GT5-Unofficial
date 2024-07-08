package gtPlusPlus.core.util.reflect;

import gregtech.api.util.GT_Utility;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import gregtech.api.enums.GT_Values;
import gregtech.api.util.GT_Recipe;
import gtPlusPlus.core.lib.CORE;
import gtPlusPlus.core.recipe.common.CI;

import static gregtech.api.recipe.RecipeMaps.pyrolyseRecipes;
import static gtPlusPlus.api.recipe.GTPPRecipeMaps.cokeOvenRecipes;

public final class AddGregtechRecipe {

    public static boolean importPyroRecipe(GT_Recipe aRecipe) {

        int aModifiedTime = (int) (aRecipe.mDuration * 0.8);

        if (aRecipe.mInputs == null || aRecipe.mFluidInputs == null
            || aRecipe.mFluidOutputs == null
            || aRecipe.mOutputs == null) {
            return false;
        }
        if (aRecipe.mInputs.length > 2 || aRecipe.mFluidInputs.length > 1
            || aRecipe.mFluidOutputs.length > 1
            || aRecipe.mOutputs.length > 9) {
            return false;
        } else if (aRecipe.mInputs.length <= 0) {
            return false;
        }

        int aCircuitNumber = -1;
        Item aCircuit = GT_Utility.getIntegratedCircuit(1)
            .getItem();
        boolean hasCircuit = false;

        for (ItemStack a : aRecipe.mInputs) {
            if (a != null && a.getItem() == aCircuit) {
                hasCircuit = true;
                aCircuitNumber = a.getItemDamage();
                break;
            }
        }

        ItemStack aInputItem = null;
        if (!hasCircuit || aCircuitNumber < 1) {
            return false;
        }

        for (ItemStack a : aRecipe.mInputs) {
            if (a != null && a.getItem() != aCircuit) {
                aInputItem = a;
                break;
            }
        }

        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(aCircuitNumber),
                aInputItem
            )
            .itemOutputs(aRecipe.mOutputs)
            .fluidInputs(aRecipe.mFluidInputs)
            .fluidOutputs(aRecipe.mFluidOutputs)
            .eut(aRecipe.mEUt)
            .duration(aModifiedTime)
            .addTo(cokeOvenRecipes);

        return true;
    }

    public static boolean addCokeAndPyrolyseRecipes(ItemStack input1, int circuitNumber, FluidStack inputFluid1,
        ItemStack output1, FluidStack outputFluid1, int timeInSeconds, int euTick) {
        // Seconds Conversion
        int TIME = timeInSeconds * 20;
        int TIMEPYRO = TIME + (TIME / 5);
        // Even though it says coke and pyrolyse, ICO recipes are imported from pyrolyse by #importPyroRecipe
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(circuitNumber),
                input1
            )
            .itemOutputs(output1)
            .fluidInputs(inputFluid1)
            .fluidOutputs(outputFluid1)
            .duration(TIMEPYRO)
            .eut(euTick)
            .addTo(pyrolyseRecipes);

        return false;
    }
}
