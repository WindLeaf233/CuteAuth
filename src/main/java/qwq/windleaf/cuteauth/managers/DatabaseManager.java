package qwq.windleaf.cuteauth.managers;

import org.bukkit.configuration.file.FileConfiguration;
import qwq.windleaf.cuteauth.CuteAuth;
import qwq.windleaf.cuteauth.Log;
import qwq.windleaf.cuteauth.utils.Utils;

import java.sql.*;

public class DatabaseManager {

    static FileConfiguration fc = CuteAuth.instance.getConfig();
    public static Boolean useDatabase = fc.getBoolean("database.enable");
    private static String userName;
    private static String password;
    private static String url;

    protected static Connection connection;
    protected static Statement statement;

    public DatabaseManager() {

    }

    private ResultSet executeSQL(String sql) {
        try {
            connection = DriverManager.getConnection(url, userName, password);
            statement = connection.createStatement();
            statement.execute("USE data;");
            statement.execute(sql);
            return statement.getResultSet();
        } catch (SQLException e) {
            Utils.disableByError("&c操作数据库失败了, 请检查是否配置正确 (T^T) ");
            e.printStackTrace();
            return null;
        }
    }

    public void loadDatabase() {
        String host = fc.getString("database.host", "localhost");
        String port = String.valueOf(fc.getInt("database.port", 3306));
        userName = fc.getString("database.username", "root");
        password = fc.getString("database.password");
        String name = fc.getString("database.name", "data");
        url = String.format("jdbc:mysql://%s:%s/%s?useSSL=false", host, port, name);
        ResultSet rs = this.executeSQL("CREATE TABLE IF NOT EXISTS player_data (UUID VARCHAR(255) PRIMARY KEY NOT NULL, EncryptedPassword TEXT NOT NULL, Salt TEXT NOT NULL);");
        if (rs != null) Log.console("&a好耶, 数据库一切正常 ヾ(✿ﾟ▽ﾟ)ノ");
    }

    public void registerPlayer(String UUID, String encryptedPassword, String salt) {
        this.executeSQL(String.format("INSERT INTO player_data (UUID, EncryptedPassword, Salt) VALUES ('%s', '%s', '%s');", UUID, encryptedPassword, salt));
    }

    public String get(String UUID, String columnName) {
        try {
            ResultSet rs = this.executeSQL(String.format("SELECT %s FROM player_data WHERE UUID='%s';", columnName, UUID));
            assert rs != null;
            String result = null;
            while (rs.next()) result = rs.getString(1);
            statement.close();
            connection.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removePlayer(String UUID) {
        this.executeSQL(String.format("DELETE FROM player_data WHERE UUID='%s';", UUID));
    }

    public Boolean isRegistered(String UUID) {
        String password = this.get(UUID, "EncryptedPassword");
        String salt = this.get(UUID, "Salt");
        return password != null || salt != null;
    }

}
