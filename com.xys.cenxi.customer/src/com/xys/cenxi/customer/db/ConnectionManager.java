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
 * �������ݿ�����
 * @author Aron
 *
 */
public class ConnectionManager {
	
	private static ConnectionProvider connProvider;
	
	private static Logger log = LoggerFactory.getLogger(ConnectionManager.class);

	/**
	 * ��ϵͳ����ʱ���ã���ʼ�����ݿ�
	 * @throws ProxoolException
	 */
	public static void initDBConnectionProvider(){
		connProvider = new HsqlConnectionProvider();
		connProvider.start();
	}
	
	/**
	 * ��ȡ���ݿ�����
	 * @return ���ݿ����Ӷ���
	 * @throws RuntimeException �����׳�����ʱ�쳣�����ش���
	 */
	public static Connection getConnection(){
		try {
			return connProvider.getConnection();
		} catch (SQLException e) {
			log.error("��ȡ���ݿ�����ʧ�ܣ�{}", e);
			throw new CusException(e);
		}
	}
	
	public static void closeStatement(Statement st){
		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("�ر�Statement ����: {}", e);
		}
	}
	
	public static void closeConnection(Connection conn){
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("�ر����ӳ���: {}", e);
		}
	}
	
	/**
	 * ִ�в�ѯSQL��䣬������
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
			log.error("ִ�в�ѯ����: {}", e);
			throw new CusException(e);
		}finally{
			if(ps != null){
				closeStatement(ps);
			}
		}
		return result;
	}
	
	/**
	 * ִ�в�ѯSQL���
	 * @param querySql
	 * @return �����
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
			log.error("ִ�в�ѯ����: {}", e);
			throw new CusException(e);
		}finally{
			closeStatement(sta);
		}
		return result;
	}
	
	/**
	 * ���ò���
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
			log.error("���ò�ѯ��������: {}", e);
			throw new CusException(e);
		}
	}
	
	/**
	 * ִ�и���SQL����������
	 * @param sql
	 */
	public static void executeUpdate(String sql){
		executeUpdate(sql, (Object[])null);
	}
	
	/**
	 * ִ�и�����䣬������
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
			log.error("ִ�и���SQL ����SQL �� {}, \n ������Ϣ��: {}", sql, e); 
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
			log.error("�ع�ʧ�ܣ�{}", e);
		}
	}
}
