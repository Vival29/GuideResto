package ch.hearc.ig.guideresto.persistence;

import ch.hearc.ig.guideresto.business.City;
import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.business.RestaurantType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class RestaurantTypeDAO {
    public Set<RestaurantType> getRestaurantTypes() throws SQLException {
        Set<RestaurantType> types = new HashSet<>();
        Connection cnn = new DAO().openConnection();
        PreparedStatement pSt = cnn.prepareStatement("SELECT numero, libelle, description FROM types_gastronomiques");
        ResultSet cur = pSt.executeQuery();

        while (cur.next()) {
            RestaurantType type = new RestaurantType(cur.getInt("numero"), cur.getString("libelle"), cur.getString("description"));
            types.add(type);
        }
        pSt.close();

        return types;
    }
}
