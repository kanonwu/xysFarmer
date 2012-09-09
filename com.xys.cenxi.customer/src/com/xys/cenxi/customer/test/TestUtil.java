package com.xys.cenxi.customer.test;

import java.math.BigDecimal;

import org.nutz.ioc.val.SysPropValue;

import com.xys.cenxi.customer.util.Util;

public class TestUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
/*		System.err.println("null, null = " + Util.checkInteger(null, null));
		System.err.println(" , =" + Util.checkInteger("", ""));
		System.err.println("0, 0 = " + Util.checkInteger("0", "0"));
		System.err.println("0, 1 = " + Util.checkInteger("0", "1"));
		System.err.println("0,  = " + Util.checkInteger("0", ""));
		System.err.println("1, 0 = " + Util.checkInteger("1", "0"));
		System.err.println("1, 2 =" + Util.checkInteger("1", "2"));;
*/
/*		System.err.println("0, 0 = " + Util.checkFloat("0", "0"));;
		System.err.println("0, . = " + Util.checkFloat("0", "."));;
		System.err.println(", .12 = " + Util.checkFloat("", ".12"));;
		System.err.println("1., 2 = " + Util.checkFloat("1.", "2"));;
		System.err.println(" , . = " + Util.checkFloat("", "."));;
		System.err.println("1 , 2 = " + Util.checkFloat("1", "2"));;
		
		System.err.println(Float.valueOf("00"));
		System.err.println(Float.valueOf("01."));
		System.err.println(Float.valueOf("012.12"));
		System.err.println(Float.valueOf("1."));
		
		double f = 12345678923423434d;
		System.err.println(f);
		BigDecimal big = BigDecimal.valueOf(f);
		System.err.println(big.toString());
		System.err.println(big.toPlainString());*/
		
		float f = 0.345f;
		BigDecimal bf = BigDecimal.valueOf(f);
		System.out.println(f);
		System.out.println(bf);
		System.out.println(bf.setScale(2, BigDecimal.ROUND_HALF_UP));
	}

}
