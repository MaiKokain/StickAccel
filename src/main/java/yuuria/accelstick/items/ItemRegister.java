package yuuria.accelstick.items;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yuuria.accelstick.AccelStick;
import yuuria.accelstick.items.sticks.BaseAccelSticks;

public class ItemRegister {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AccelStick.MODID);

    public static final RegistryObject<Item> CREATIVE_ACCEL_STICK = ITEMS.register(
            "creative_accel_stick",
            () -> new BaseAccelSticks(defaultProps(), Integer.MAX_VALUE)
    );

    private static Item.Properties defaultProps()
    {
        return new Item.Properties().stacksTo(1);
    }
}
