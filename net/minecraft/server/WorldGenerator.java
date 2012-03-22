package net.minecraft.server;

import java.util.Random;

public abstract class WorldGenerator {

    private final boolean a;

    public WorldGenerator() {
        this.a = false;
    }

    public WorldGenerator(boolean flag) {
        this.a = flag;
    }

    public abstract boolean a(World world, Random random, int i, int j, int k);

    public void a(double d0, double d1, double d2) {}

    protected void setType(World world, int i, int j, int k, int l) {
        this.setTypeAndData(world, i, j, k, l, 0);
    }

    protected void setTypeAndData(World world, int i, int j, int k, int l, int i1) {
        if (this.a) {
            world.setTypeIdAndData(i, j, k, l, i1);
        } else if (world.getChunkAtWorldCoords(i >> 4, k >> 4).seenByPlayer) {
            if (world.setRawTypeIdAndData(i, j, k, l, i1)) {
                world.notify(i, j, k);
            }
        } else {
            world.setRawTypeIdAndData(i, j, k, l, i1);
        }
    }
}
