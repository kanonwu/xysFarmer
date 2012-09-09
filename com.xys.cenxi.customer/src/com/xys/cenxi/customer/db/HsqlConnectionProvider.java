package com.xys.cenxi.customer.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xys.cenxi.customer.exception.CusException;
import com.xys.cenxi.customer.util.Util;


/**
 * 用嵌入式数据库做测试
 */
public class HsqlConnectionProvider implements ConnectionProvider {
	
    private Properties settings;
    private String serverURL;
    private String driver = "org.hsqldb.jdbcDriver";
    private String proxoolURL;
    
    private Logger logger = LoggerFactory.getLogger(HsqlConnectionProvider.class);

    public HsqlConnectionProvider() {
//        System.setProperty("org.apache.commons.logging.LogFactory", "org.jivesoftware.util.log.util.CommonsLogFactory");
    }

    public boolean isPooled() {
        return true;
    }

    public Connection getConnection() throws SQLException {
    	//proxool.gym:org.hsqldb.jdbcDriver:jdbc:hsqldb:G:\workspace\GYM\gymhsqldb\dbgym
        try {
            Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
            return DriverManager.getConnection(proxoolURL, settings);

        	
//        	Platform.getBundle("proxool").loadClass("org.logicalcobwebs.proxool.ProxoolDriver");
//        	Platform.getBundle("Extlib").loadClass("org.hsqldb.jdbcDriver");
//        	Platform.getBundle(Activator.PLUGIN_ID).loadClass("org.logicalcobwebs.proxool.ProxoolDriver");
//            Class.forName("org.hsqldb.jdbcDriver");
//        	return DriverManager.getConnection("proxool.gym:org.hsqldb.jdbcDriver:jdbc:hsqldb:file:ttaa", settings);
//        	return DriverManager.getConnection("proxool.gym:org.hsqldb.jdbcDriver:jdbc:hsqldb:hsql://localhost/xdb", settings);
//            return DriverManager.getConnection("jdbc:hsqldb:file:stsd", settings);
        }
        catch (ClassNotFoundException e) {
            throw new CusException("找不到驱动: "+e);
        }
    }

    public void start() {
        //创建存放数据库的文件夹
        Util.createDatabaseDir();

        //得到数据库文件夹路径
        String databasePath = Util.getDatabaseDir();
        serverURL = "jdbc:hsqldb:" + databasePath + File.separator + Util.DB_NAME;
        proxoolURL = "proxool.gym:"+driver+":"+serverURL;
        settings = new Properties();
        settings.setProperty("proxool.maximum-connection-count", "25");
        settings.setProperty("proxool.minimum-connection-count", "3");
        settings.setProperty("proxool.maximum-connection-lifetime", Integer.toString((int)(86400000 * 0.5)));
        settings.setProperty("user", "sa");
        settings.setProperty("password", "");
    }

    public void restart() {
        // Kill off pool.
        destroy();
        // Start a new pool.
        start();
    }

    public void destroy() {
        // Shutdown the database.
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement("SHUTDOWN");
            pstmt.execute();
        }
        catch (SQLException sqle) {
        	sqle.printStackTrace();
            logger.error(sqle.getMessage(), sqle);
        }
        finally {
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(con);
        }
        // Blank out the settings
        settings = null;
    }

    @Override
	public void finalize() throws Throwable {
        super.finalize();
        destroy();
    }
}