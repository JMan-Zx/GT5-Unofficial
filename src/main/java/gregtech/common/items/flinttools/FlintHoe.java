package gregtech.common.items.flinttools;

import net.minecraft.item.ItemHoe;
import net.minecraft.creativetab.CreativeTabs;

public class FlintHoe extends ItemHoe {

    public FlintHoe() {
        super(FlintTools.FLINT_MATERIAL);
        this.setUnlocalizedName("flintHoe");
        this.setTextureName("gregtech:tools/flintHoe");
    }
}
