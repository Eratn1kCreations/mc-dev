package net.minecraft.server;

import java.util.Random;

public class BlockCommand extends BlockContainer {

    public BlockCommand(int i) {
        super(i, 184, Material.ORE);
    }

    public TileEntity a(World world) {
        return new TileEntityCommand();
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        if (!world.isStatic) {
            boolean flag = world.isBlockIndirectlyPowered(i, j, k);
            int i1 = world.getData(i, j, k);
            boolean flag1 = (i1 & 1) != 0;

            if (flag && !flag1) {
                world.setRawData(i, j, k, i1 | 1);
                world.a(i, j, k, this.id, this.r_());
            } else if (!flag && flag1) {
                world.setRawData(i, j, k, i1 & -2);
            }
        }
    }

    public void b(World world, int i, int j, int k, Random random) {
        TileEntity tileentity = world.getTileEntity(i, j, k);

        if (tileentity != null && tileentity instanceof TileEntityCommand) {
            ((TileEntityCommand) tileentity).a(world);
        }
    }

    public int r_() {
        return 1;
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        TileEntityCommand tileentitycommand = (TileEntityCommand) world.getTileEntity(i, j, k);

        if (tileentitycommand != null) {
            entityhuman.a((TileEntity) tileentitycommand);
        }

        return true;
    }
}
