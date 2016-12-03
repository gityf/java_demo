package org.wyf;

import org.junit.Test;

/**
 * Created by wyf on 16/10/28.
 */
public class HighLowOrder {

    public long getLowOrder(long highOrder) {
        System.out.println((highOrder >> 46 & 0x3ffff) << 32);
        System.out.println(highOrder & 0xffffffffL);

        return ((highOrder >> 46 & 0x3ffff) << 32) | (highOrder & 0xffffffffL);
    }
    @Test
    public  void  getLowOrderTest() {
        long orderId = 70413770884219L;
        System.out.println(getLowOrder(orderId));
    }
}
