package service;

import dao.DBtool;

import java.sql.SQLException;

public class GetBrowse {
    public static String GetBrowse(String ArtNAme) throws SQLException {
        String browse = String.valueOf(DBtool.GetArt("select * from Art where ArtName ='" + ArtNAme + "';").get(0).getBrowse());
        return "{\"ArtName\":\"" + ArtNAme + "\",\"Browse\":\"" + browse + "\"}";
    }
}
