package com.xys.cenxi.customer.util;

/**
 * 生成一个8位长的字符串，用于排序
 * @author 吴佳龙
 *
 */
public class OrderGenerator {
    private static long oldOrder = 0;
    private static final String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    private OrderGenerator() {
    }

    /**
     * 生成一个新的顺序号字符串，长度是8位
     * @return 返回顺序号字符串
     */
    public synchronized static String newOrder() {
        long order = System.currentTimeMillis();
        while (order <= oldOrder) {
            order++;
        }
        oldOrder = order;
        StringBuffer rt = new StringBuffer(10);
        while (order > 0) {
            rt.insert(0, chars.charAt((int) (order % 36)));
            order = order / 36;
        }
        return rt.toString();
    }


}
