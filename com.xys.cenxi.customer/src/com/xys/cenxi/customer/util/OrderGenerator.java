package com.xys.cenxi.customer.util;

/**
 * ����һ��8λ�����ַ�������������
 * @author �����
 *
 */
public class OrderGenerator {
    private static long oldOrder = 0;
    private static final String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    private OrderGenerator() {
    }

    /**
     * ����һ���µ�˳����ַ�����������8λ
     * @return ����˳����ַ���
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
