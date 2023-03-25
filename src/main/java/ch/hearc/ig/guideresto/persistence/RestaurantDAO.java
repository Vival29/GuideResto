package ch.hearc.ig.guideresto.persistence;

import ch.hearc.ig.guideresto.business.City;
import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.business.RestaurantType;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class RestaurantDAO {
    public Set<Restaurant> getAllRestaurants() throws SQLException {
        Set<Restaurant> restaurants = new HashSet<>();
        Connection cnn = new DAO().openConnection();
        PreparedStatement pSt = cnn.prepareStatement("SELECT v.numero, v.code_postal, v.nom_ville, t.numero, t.libelle, t.description, r.numero, r.nom, r.description, r.site_web, r.adresse FROM RESTAURANTS r INNER JOIN VILLES v ON r.fk_vill = v.numero INNER JOIN TYPES_GASTRONOMIQUES t ON r.fk_type = t.numero");
        ResultSet cur = pSt.executeQuery();

        while (cur.next()) {
            City city = new City(cur.getInt("numero"), cur.getString("code_postal"), cur.getString("nom_ville"));
            RestaurantType type = new RestaurantType(cur.getInt("numero"), cur.getString("libelle"), cur.getString("description"));
            Restaurant resto = new Restaurant(cur.getInt("numero"), cur.getString("nom"), cur.getString("description"), cur.getString("site_web"), cur.getString("adresse"), city, type);
            restaurants.add(resto);
        }
        pSt.close();
        return restaurants;
    }

    public void updateRestaurant(Integer id, String name, String description, String website, String street, City city, RestaurantType type) throws SQLException {
        Connection cnn = new DAO().openConnection();
        Integer idCity = city.getId();
        String zipCode = city.getZipCode();
        String cityName = city.getCityName();

        Integer idType = type.getId();
        String libelle = type.getLabel();
        String descriptionType = type.getDescription();

        PreparedStatement restPS = cnn.prepareStatement("UPDATE RESTAURANT SET (numero, nom, adresse, description, site_web, fk_type, fk_vill) VALUES(?, ?, ?, ?, ?, ?, ?)");
        restPS.setInt(1, id);
        restPS.setString(2,name);
        restPS.setString(3, street);
        restPS.setString(4, description);
        restPS.setString(5, website);
        restPS.setInt(6, idType);
        restPS.setInt(7, idCity);
        restPS.executeUpdate();

        PreparedStatement typePS = cnn.prepareStatement("UPDATE types_gastronomiques SET (numero, libelle, description) VALUES(?, ?, ?)");
        typePS.setInt(1, idType);
        typePS.setString(2,libelle);
        typePS.setString(3, descriptionType);
        typePS.executeUpdate();

        PreparedStatement cityPS = cnn.prepareStatement("UPDATE VILLES SET (numero, code_postal, nom_ville) VALUES(?, ?, ?)");
        cityPS.setInt(1, idCity);
        cityPS.setString(2,zipCode);
        cityPS.setString(3, cityName);
        cityPS.executeUpdate();

        restPS.close();
        typePS.close();
        cityPS.close();
    }
}
