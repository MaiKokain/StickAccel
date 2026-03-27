package yuuria.accelstick;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class Ticker {
    public void tick(BlockPos blockPos, Level level, int speed) {
        if (level.isClientSide() || !(level instanceof ServerLevel serverLevel)) return;

        BlockState blockState = level.getBlockState(blockPos);

        if (blockState.isRandomlyTicking()) {
            for (int i = 0; i < speed; i++) {
                blockState.randomTick(serverLevel, blockPos, level.random);
            }
            return;
        }

        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity != null) {
            BlockEntityTicker<BlockEntity> ticker = blockState.getTicker(level, (BlockEntityType<BlockEntity>) blockEntity.getType());

            if (ticker != null) {
                for (int i = 0; i < speed; i++) {
                    ticker.tick(level, blockPos, blockState, blockEntity);
                }
            }
        }
    }
}
