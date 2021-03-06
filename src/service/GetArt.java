package service;

import dao.DBtool;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;

@Service
public class GetArt {
    private static final String sql = "select * from Art where ArtName ='%s';";

    public static Article GetArt(String ArtName) throws SQLException, IOException, ClassNotFoundException {
        String str = String.format(sql, ArtName);
        return DBtool.GetArt(str).get(0);
    }
}
