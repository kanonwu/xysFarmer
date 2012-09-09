package com.xys.cenxi.customer.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.widgets.DateTime;
import org.slf4j.LoggerFactory;

import com.xys.cenxi.customer.exception.CusException;


public class Util {

	/**
	 * 日期时间格式：yyyy-MM-dd HH:mm:ss
	 */
	public static final SimpleDateFormat DATETIME_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 日期格式 yyyy-MM-dd，没有时间
	 */
	public static final SimpleDateFormat DATE_SDF = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 存放数据库文件的文件夹名称
	 */
	public static final String DB_HOME_DIR = "hsqldb";
	
	/**
	 * 数据库名称
	 */
	public static final String DB_NAME = "dbxys";
	
	/**
	 * 正则：正整数
	 */
	public static final Pattern P_DIGITAL = Pattern.compile("^[1-9][0-9]*$");

	/**
	 * 正整数和正浮点数
	 */
	public static final Pattern P_FLOAT = Pattern.compile("^\\d+(\\.\\d+)?$");
	
	public static final IdcardValidator idCardVlidator = new IdcardValidator();
	
	/**
	 * 身份证生日
	 */
	public static final SimpleDateFormat SDF_BIRTHDAY = new SimpleDateFormat("yyyyMMdd");
	
	/**
	 * 得到工程项目运行路径，绝对路径
	 * @return
	 */
	public static String getHomeDirectory(){
		String homeDir = null;
		
/*		try {
			//homeDir = /G:/workspace/GYM/
			homeDir = FileLocator.toFileURL(Platform.getBundle(Activator.PLUGIN_ID).getEntry("")).getPath();
		} catch (IOException e) {
			e.printStackTrace();
			LoggerFactory.getLogger(Util.class).error("获取工程路径失败：{}", e);
			throw new GymRuntimeException(e.getMessage());
		}
*/		
		return homeDir;
	}
	
	
	public static String getDatabaseDir(){
		String dbDirPath = DB_HOME_DIR;
		LoggerFactory.getLogger(Util.class).info("数据库文件路径：{}", dbDirPath);
		File file = new File(dbDirPath);
		String dir = null;
		try {
			dir = file.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
			LoggerFactory.getLogger(Util.class).error("获取数据库文件存放路径出错: {}", e);
			throw new CusException(e);
		}
		return dir;
	}
	
	public static void createDatabaseDir(){
		String dbDirPath = getDatabaseDir();
		File dbFile = new File(dbDirPath);
		LoggerFactory.getLogger(Util.class).info("创建数据库文件夹：{}", dbDirPath);
		if(!dbFile.exists()){
			dbFile.mkdir();
			LoggerFactory.getLogger(Util.class).info("创建数据库文件夹：{}", dbDirPath);
		}
	}
	
	public static boolean isEmpty(String str){
		if(str == null)
			return true;
		if(str.trim().length() == 0){
			return true;
		}
		return false;
	}
	
	public static Date getDate(DateTime dt){
		Calendar cal = Calendar.getInstance();
		cal.set(dt.getYear(), dt.getMonth(), dt.getDay());
		return cal.getTime();
	}
	
	public static void verifyID(VerifyEvent e, String text){
		String oldText = text;
		if(oldText.length() > 0){
			oldText = text.substring(0, e.start) + text.substring(e.end, text.length());
		}
		String newStr = oldText + e.text;
		if(newStr.length() == 0){
			e.doit = true;
			return;
		}
		if(newStr.length() > 18){
			e.doit = false;
			return;
		}
		e.doit = Util.checkFloat(oldText, e.text);
		if(newStr.length() == 18){
			Matcher matcher = P_DIGITAL.matcher(newStr.substring(0, 17));
			if(matcher.find()){
				e.doit = true;
			}else{
				e.doit = false;
				return;
			}
			if("0123456789x".contains(String.valueOf(newStr.charAt(17)).toLowerCase())){
				e.doit = true;
			}else{
				e.doit = false;
				return;
			}
		}else{
			e.doit = checkInteger("", newStr);
		}
	}
	
	/**
	 * 校验输入金额，只能输入数字，包括小数
	 * @param old
	 * @param input
	 * @return
	 */
	public static void verifyMoney(VerifyEvent e, String text) {
		String oldText = text;
		if(oldText.length() > 0){
			oldText = text.substring(0, e.start) + text.substring(e.end, text.length());
		}
		String newStr = oldText + e.text;
		if(newStr.length() == 0){
			e.doit = true;
			return;
		}
		if(newStr.length() > 10){
			e.doit = false;
			return;
		}
		e.doit = Util.checkFloat(oldText, e.text);
	}
	
	/**
	 * 验证正整数
	 * @param old
	 * @param newStr
	 * @return
	 */
	public static boolean checkInteger(String old, String newStr){
		String str = "";
		if(old != null){
			str = old;
		}
		if(newStr != null){
			str += newStr;
		}
		if(str.length() == 0){
			return true;
		}
		
		Matcher matcher = P_DIGITAL.matcher(str);
		if(matcher.find()){
			return true;
		}
		
		return false;
	}
	
	/**
	 * 检查输入是否为正整数，返回输入后的字符串
	 * @param e
	 * @param text
	 * @return
	 */
	public static String verifyInteger(VerifyEvent e, String text) {
		String oldText = text;
		if(oldText.length() > 0){
			oldText = text.substring(0, e.start) + text.substring(e.end, text.length());
		}
		String newStr = oldText + e.text;
		if(newStr.length() == 0)
		{
			e.doit = true;
		}
		e.doit = Util.checkInteger(oldText, e.text);
		return newStr;
	}
	
	public static boolean checkFloat(String old, String newStr){
		String str = "";
		if(old != null){
			str = old;
		}
		if(newStr != null){
			str += newStr;
		}
		if(str.indexOf(".") == str.length() - 1){
			return true;
		}
		
		Matcher matcher = P_FLOAT.matcher(str);
		if(matcher.find()){
			return true;
		}
		
		return false;
	}
	
//	public static String toPlainString(BigDecimal value, int decimal){
//        BigDecimal pow = new BigDecimal(10);
//        pow = pow.pow(decimal);
//        value = value.multiply(pow);
//        MathContext mc = new MathContext(5, RoundingMode.CEILING);
//        BigDecimal round = value.round(mc);
//        value = round.divide(pow);
//        return value.toPlainString();
//	}
	
	public static String toPlainString(BigDecimal value){
		BigDecimal big = null;
		big = value.setScale(2, BigDecimal.ROUND_HALF_UP);
		return big.toPlainString();

	}
	
	public static String toPlainString(Float f){
		BigDecimal big = BigDecimal.valueOf(f);
		big = big.setScale(2, BigDecimal.ROUND_HALF_UP);
		return big.toPlainString();
//		if(f.toString().contains("E")){
//		}else{
//			return f.toString();
//		}
	}
}
