package yuuria.accelsticks.items.sticks;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.checkerframework.checker.nullness.qual.NonNull;
import yuuria.accelsticks.Ticker;
import yuuria.accelsticks.guis.AccelSticksScreen;
import yuuria.accelsticks.items.StickDataComponent;

public class BaseAccelSticks extends Item {
    public BaseAccelSticks(Properties properties) {
        super(properties);
    }

    @NonNull
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level world = context.getLevel();
        ItemStack itemStack =context.getItemInHand();
        int speedL = itemStack.get(StickDataComponent.SPEED.get());
        if (world.isClientSide || player == null) return InteractionResult.FAIL;
        if (!player.isShiftKeyDown()) return InteractionResult.FAIL;
        new Ticker().tickBlock(context.getClickedPos(), world, speedL);
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if (player.isShiftKeyDown() && level.isClientSide) {
            showSettingScreen(player);
//            Minecraft.getInstance().setScreen(new AccelSticksScreen(Component.translatable("ui.stickaccel.stick_screen"), player));
        }
        return InteractionResultHolder.success(stack);
    }

    public void showSettingScreen(Player player) {
        Minecraft.getInstance().setScreen(new AccelSticksScreen(Component.translatableWithFallback("ui.stickaccel.stick_screen", "Acceleration Stick settings"), player));
    }

    public int getMaxSpeed() {
        return 2;
    }

}
