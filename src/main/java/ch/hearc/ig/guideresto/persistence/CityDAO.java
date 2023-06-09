package ch.hearc.ig.guideresto.persistence;

import ch.hearc.ig.guideresto.business.City;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CityDAO {


   public void insertCity (Integer id, String zipCode, String cityName) throws SQLException{
       Connection cnn = new DAO().openConnection();
       PreparedStatement pSt = cnn.prepareStatement("INSERT into VILLES (numero, code_postal, nom_ville) VALUES(?, ?, ?)");
       pSt.setInt(1, id);
       pSt.setString(2,zipCode);
       pSt.setString(3, cityName);
       pSt.executeUpdate();
       pSt.close();

   }

   public void updateCity (Integer id, String zipCode, String cityName) throws SQLException{
       Connection cnn = new DAO().openConnection();
       PreparedStatement pSt = cnn.prepareStatement("UPDATE VILLES SET (numero, code_postal, nom_ville) VALUES(?, ?, ?)");
       pSt.setInt(1, id);
       pSt.setString(2,zipCode);
       pSt.setString(3, cityName);
       pSt.executeUpdate();
       pSt.close();
   }
    public Set<City> getCities() throws SQLException {
       Set<City> cities = new HashSet<>();
        Connection cnn = new DAO().openConnection();
        PreparedStatement pSt = cnn.prepareStatement("SELECT * FROM VILLES");
        ResultSet cur = pSt.executeQuery();
        while(cur.next()){
            City city = new City(cur.getInt("numero"), cur.getString("code_postal"), cur.getString("nom_ville"));
            cities.add(city);
        }
        pSt.close();
       return cities;
    }
}
