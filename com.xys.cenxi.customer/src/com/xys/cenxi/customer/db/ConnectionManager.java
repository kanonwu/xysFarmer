package com.xys.cenxi.customer.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;

import org.logicalcobwebs.proxool.ProxoolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xys.cenxi.customer.exception.CusException;


/**
 * 管理数据库连接
 * @author Aron
 *
 */
public class ConnectionManager {
	
	private static ConnectionProvider connProvider;
	
	private static Logger log = LoggerFactory.getLogger(ConnectionManager.class);

	/**
	 * 在系统启动时调用，初始化数据库
	 * @throws ProxoolException
	 */
	public static void initDBConnectionProvider(){
		connProvider = new HsqlConnectionProvider();
		connProvider.start();
	}
	
	/**
	 * 获取数据库连接
	 * @return 数据库连接对象
	 * @throws RuntimeException 出错抛出运行时异常，严重错误
	 */
	public static Connection getConnection(){
		try {
			return connProvider.getConnection();
		} catch (SQLException e) {
			log.error("获取数据库连接失败：{}", e);
			throw new CusException(e);
		}
	}
	
	public static void closeStatement(Statement st){
		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("关闭Statement 出错: {}", e);
		}
	}
	
	public static void closeConnection(Connection conn){
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("关闭连接出错: {}", e);
		}
	}
	
	/**
	 * 执行查询SQL语句，带参数
	 * @param querySql
	 * @param args
	 * @return
	 */
	public static ResultSet executeQuery(String querySql, Object... args){
		ResultSet result = null;
		Connection conn = getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(querySql);
			setArguments(ps);
			result = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("执行查询出错: {}", e);
			throw new CusException(e);
		}finally{
			if(ps != null){
				closeStatement(ps);
			}
		}
		return result;
	}
	
	/**
	 * 执行查询SQL语句
	 * @param querySql
	 * @return 结果集
	 */
	public static ResultSet executeQuery(String querySql){
		ResultSet result = null;
		Connection conn = getConnection();
		Statement sta = null;
		try {
			sta = conn.createStatement();
			result = sta.executeQuery(querySql);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("执行查询出错: {}", e);
			throw new CusException(e);
		}finally{
			closeStatement(sta);
		}
		return result;
	}
	
	/**
	 * 配置参数
	 * @param ps
	 * @param args
	 */
	public static void setArguments(PreparedStatement ps, Object... args){
		int index = 1;
		try{
			if(args != null){
				for(Object arg : args){
					if(arg == null){
						ps.setNull(index, Types.NULL);
						continue;
					}
					if(arg instanceof String){
						ps.setString(index, (String) arg);
					}else if(arg instanceof Integer){
						ps.setInt(index, (Integer) arg);
					}else if(arg instanceof Float){
						ps.setFloat(index, (Float) arg);
					}else if(arg instanceof Date){
						Date dateArg = (Date) arg;
						ps.setDate(index, new java.sql.Date(dateArg.getTime()));
					}
					index++;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error("设置查询参数出错: {}", e);
			throw new CusException(e);
		}
	}
	
	/**
	 * 执行更新SQL，不带参数
	 * @param sql
	 */
	public static void executeUpdate(String sql){
		executeUpdate(sql, (Object[])null);
	}
	
	/**
	 * 执行更新语句，带参数
	 * @param sql
	 * @param args
	 */
	public static void executeUpdate(String sql, Object... args){
		Connection conn = getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			setArguments(ps, args);
			ps.executeUpdate();
			closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("执行更新SQL 出错，SQL 是 {}, \n 错误信息是: {}", sql, e); 
			rollback(conn);
			throw new CusException(e);
		}finally{
			closeStatement(ps);
		}
	}
	
	public static void rollback(Connection conn){
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("回滚失败：{}", e);
		}
	}
}
