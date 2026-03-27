package yuuria.accelstick.items.sticks;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import yuuria.accelstick.Ticker;
import yuuria.accelstick.gui.AccelStickScreen;

public class BaseAccelSticks extends Item {
    public final int maxSpeed;
    public BaseAccelSticks(Properties properties) {
        super(properties);
        this.maxSpeed = 2;
    }

    public BaseAccelSticks(Properties properties, int maxSpeed)
    {
        super(properties);
        this.maxSpeed = maxSpeed;
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        Player player = pContext.getPlayer();
        Level level = pContext.getLevel();
        ItemStack itemStack = pContext.getItemInHand();
        CompoundTag itemTag = itemStack.getTag();
        if (level.isClientSide() || player == null || itemTag == null || !itemTag.contains("current_speed") || !player.isShiftKeyDown()) return InteractionResult.FAIL;
        new Ticker().tick(pContext.getClickedPos(), level, itemTag.getInt("current_speed"));

        return InteractionResult.SUCCESS;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (pPlayer.isShiftKeyDown() && pLevel.isClientSide) {
            this.showSettingsScreen(pPlayer);
        }
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
    }

    public void showSettingsScreen(Player player)
    {
        Minecraft.getInstance().setScreen(
                new AccelStickScreen(Component.translatable("yuuria.accelstick.gui.settings"), player)
        );
    }

    public int getMaxSpeed()
    {
        return maxSpeed;
    }
}
