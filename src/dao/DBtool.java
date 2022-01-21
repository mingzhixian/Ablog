package dao;

import com.ibatis.common.jdbc.ScriptRunner;
import com.ibatis.common.resources.Resources;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import service.Article;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DBtool {
    static DataSource ds = null;
    private static int IsFrist = 0;
    //数据库以及文章、图片等数据文件位置
    private static final String dataPath = "/home/kic/Ablog/";
    //数据库地址
    private static final String filePath = dataPath + "Ablog" + ".db";
    private static final String DbUrl = "jdbc:sqlite:" + filePath;

    //默认附件和数据库统一存放，不在项目下
    public static String getDataPath() {
        return dataPath;
    }

    private static void setDataSource() throws ClassNotFoundException {
        new File(filePath).getParentFile().mkdirs();
        Class.forName("org.sqlite.JDBC");
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DbUrl);
        config.addDataSourceProperty("connectionTimeout", "1000"); // 连接超时：1秒
        config.addDataSourceProperty("idleTimeout", "5*60000"); // 空闲超时：5*1分钟
        config.addDataSourceProperty("maximumPoolSize", "10"); // 最大连接数：10
        ds = new HikariDataSource(config);
    }

    private static Connection getconnection() throws ClassNotFoundException, SQLException, IOException {
        if (ds == null) {
            setDataSource();
        }
        Class.forName("org.sqlite.JDBC");
        //初次运行判断数据库是否初始化
        if (IsFrist == 0) {
            IsFrist = 1;
            Connection connection = ds.getConnection();
            List<String> tables = getTables(connection);
            if (!tables.toString().contains("Art")) {
                SqlInit(connection);
            }
            return connection;
        } else {
            return ds.getConnection();
        }
    }

    public static void excute(String str) throws SQLException, ClassNotFoundException, IOException {
        try (Connection connection = getconnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(str);
            }
        }
    }

    public static void excuteBatch(List<String> sqls) throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = getconnection()) {
            try (Statement statement = connection.createStatement()) {
                for (String sql : sqls) {
                    statement.execute(sql);
                }
            }
        }
    }

    public static int allEle(String table) throws SQLException, IOException, ClassNotFoundException {
        int a;
        try (Connection connection = getconnection()) {
            try (Statement statement = connection.createStatement()) {
                a = statement.executeQuery("select count(*) from " + table + ";").getInt(1);
            }
        }
        return a;
    }

    public static List<Article> GetArt(String sql) throws SQLException {
        List<Article> articles = new ArrayList<>();
        try (Connection connection = getconnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet one = statement.executeQuery(sql);
                while (one.next()) {
                    articles.add(new Article(one.getString("ArtName"), one.getString("ArtUrl"), one.getString("ComUrl"), one.getString("Type"), one.getString("Date")));
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return articles;
    }

    //返回所有type
    public static List<String> GetType() throws SQLException {
        List<String> strings = new ArrayList<>();
        String sql = "select * from Art;";
        try (Connection connection = getconnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet one = statement.executeQuery(sql);
                while (one.next()) {
                    if (!Objects.equals(one.getString("Type"), "index")) {
                        strings.add(one.getString("Type"));
                    }
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

    private static void SqlInit(Connection connection) throws SQLException, IOException {
        // 创建ScriptRunner，用于执行SQL脚本
        ScriptRunner runner = new ScriptRunner(connection, false, false);
        runner.setErrorLogWriter(null);
        runner.setLogWriter(null);
        runner.runScript(Resources.getResourceAsReader("/dao/db_init.sql"));
    }

    //用于列出tables，返回List
    private static List<String> getTables(Connection connection) throws SQLException {
        List<String> strings = new ArrayList<>();
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet resultSet = databaseMetaData.getTables(null, "public", "%", new String[]{"TABLE"});
        while (resultSet.next()) {
            strings.add(resultSet.getString("TABLE_NAME"));
        }
        resultSet.close();
        return strings;
    }
}
