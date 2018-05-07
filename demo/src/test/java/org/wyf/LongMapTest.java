package org.wyf;

import org.junit.Test;
import org.wyf.bloomfilter.LongMap;

/**
 * Created by wangyaofu on 2017/10/17.
 */
public class LongMapTest {
    @Test
    public void TestAdd() {
        LongMap longMap = new LongMap(40);
        longMap.add(3);
        System.out.println("check 3 res:" + longMap.contains(3));
        System.out.println("check 5 res:" + longMap.contains(5));
        System.out.println("check 5 res:" + Long.MAX_VALUE);
    }
}
