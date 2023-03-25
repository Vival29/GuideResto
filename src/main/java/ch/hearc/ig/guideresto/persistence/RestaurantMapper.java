package ch.hearc.ig.guideresto.persistence;

import ch.hearc.ig.guideresto.business.City;
import ch.hearc.ig.guideresto.business.Restaurant;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class RestaurantMapper {
    private Integer id;
    private String DBURL = "jdbc:oracle:thin@db.ig.he-arc.ch";
    private String DBUSER = "virginie_valentin1";
    private String DBPWD = "virginie_valentin1";
    private Connection cnn = null;

    public void openConnection(){
        try{
            cnn = DriverManager.getConnection(DBURL, DBUSER, DBPWD);
            cnn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Set<Restaurant> getAllRestaurants() throws SQLException {
        Set<Restaurant> restaurants = new HashSet<>();
        openConnection();
        PreparedStatement pSt = cnn.prepareStatement("SELECT r.numero, r.nom, r.adresse, r.description, r.site_web, t.libelle, v.nom_ville FROM RESTAURANTS r INNER JOIN VILLES");
        ResultSet cur = pSt.executeQuery();

        while(cur.next()){
            PreparedStatement pStVille = cnn.prepareStatement("SELECT )

            Restaurant resto = new Restaurant(cur.getInt("numero"), );
            restaurants.add(resto);
        }
        pSt.close();

        return restaurants;
    }
}
