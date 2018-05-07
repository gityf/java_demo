package org.wyf.bloomfilter;

/**
 * Created by wangyaofu on 2017/10/17.
 */
public class LongMap {
    private static final long MAX = Long.MAX_VALUE;
    private final int MACHINE64 = 64;

    public LongMap() {
        longs = new long[93750000];
    }

    public LongMap(int size) {
        longs = new long[size];
    }

    private long[] longs = null;

    public void add(long i) {
        int r = (int) (i / MACHINE64);
        long c = i % MACHINE64;
        longs[r] = longs[r] | (1 << c);
    }

    public boolean contains(long i) {
        int r = (int) (i / MACHINE64);
        long c = i % MACHINE64;
        if (((longs[r] >>> c) & 1) == 1) {
            return true;
        }
        return false;
    }

    public void remove(long i) {
        int r = (int) (i / MACHINE64);
        long c = i % MACHINE64;
        longs[r] = longs[r] & (((1 << (c + 1)) - 1) ^ MAX);
    }
}

