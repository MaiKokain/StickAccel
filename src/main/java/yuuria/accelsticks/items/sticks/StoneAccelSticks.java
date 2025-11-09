package yuuria.accelsticks.items.sticks;

public class StoneAccelSticks extends BaseAccelSticks {
    public StoneAccelSticks(Properties properties) {
        super(properties);

    }

    @Override
    public int getMaxSpeed() {
        return 8;
    }
}
