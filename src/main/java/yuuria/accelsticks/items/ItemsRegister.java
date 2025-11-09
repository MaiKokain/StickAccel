package yuuria.accelsticks.items;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import yuuria.accelsticks.AccelSticks;
import yuuria.accelsticks.items.sticks.BaseAccelSticks;
import yuuria.accelsticks.items.sticks.CreativeAccelSticks;
import yuuria.accelsticks.items.sticks.StoneAccelSticks;

public class ItemsRegister {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AccelSticks.MODID);

    public static final DeferredItem<Item> WOODEN_STICK = ITEMS.register(
            "wooden_accel_stick",
            () -> new BaseAccelSticks(defaultProperties())
    );
    public static final DeferredItem<Item> STONE_STICK = ITEMS.register(
            "stone_accel_stick",
            () -> new StoneAccelSticks(defaultProperties())
    );
    public static final DeferredItem<Item> CREATIVE_STICKS = ITEMS.register(
            "creative_accel_stick",
            () -> new CreativeAccelSticks(defaultProperties())
    );

    private static Item.Properties defaultProperties() {
        return new Item.Properties()
                .component(StickDataComponent.SPEED.get(), 0)
                .stacksTo(1);
    }
}
