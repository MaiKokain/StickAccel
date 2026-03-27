package yuuria.accelstick.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import yuuria.accelstick.items.sticks.BaseAccelSticks;
import yuuria.accelstick.network.AccelStickPacketHandler;

public class AccelStickScreen extends Screen {
    private final Player player;
    public AccelStickScreen(Component pTitle, Player player) {
        super(pTitle);
        this.player = player;
    }

    @Override
    public void init() {
        super.init();
        ItemStack itemStack = player.getMainHandItem();
        BaseAccelSticks item = (BaseAccelSticks) itemStack.getItem();

        String maxSpeed = String.valueOf(item.getMaxSpeed());
        if (itemStack.getTag() == null) {
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.putInt("current_speed", 1);
            itemStack.setTag(compoundTag);
        }
        int currentSpeed = itemStack.getTag().getInt("current_speed");

        StringWidget maxSpeedLabel = new StringWidget(width / 2 - 50, height / 2 - 10, 100, -20, Component.translatable("yuuria.accelstick.gui.max_speed", maxSpeed), font);

        EditBox speedInput = new EditBox(font, width / 2 - 50,  height / 2 - 10, 100, 20, Component.translatable("yuuria.accelstick.gui.set_speed"));
        speedInput.setValue(String.valueOf(currentSpeed));
        speedInput.setFilter(input -> {
            if (input.isEmpty()) return true;

            if (!input.matches("-?\\d*")) return false;

            try {
                int val = Integer.parseInt(input);

                return val <= item.getMaxSpeed();
            } catch (NumberFormatException e) {
                return false;
            }
        });

        Button saveButtonn = Button.builder(
                Component.translatable("yuuria.accelstick.gui.save_button"),
                pButton -> {
                    int newSpeed = Integer.parseInt(speedInput.getValue());
                    AccelStickPacketHandler.sendToServer(newSpeed);
                    close();
                }
        ).pos(width / 2 - 50, height / 2 + 20).size(100, 20).build();

        addRenderableWidget(speedInput);
        addRenderableWidget(maxSpeedLabel);
        addRenderableWidget(saveButtonn);
    }

    @Override
    public void renderBackground(GuiGraphics pGuiGraphics) {
        super.renderBackground(pGuiGraphics);
        pGuiGraphics.drawCenteredString(font, title, width / 2, height / 2 - 30, 0xFFFFFF);
    }

    private void close()
    {
        Minecraft.getInstance().setScreen(null);
    }
}
