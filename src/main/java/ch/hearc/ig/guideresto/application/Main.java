package ch.hearc.ig.guideresto.application;


import ch.hearc.ig.guideresto.persistence.CityDAO;
import ch.hearc.ig.guideresto.persistence.EvaluationCriteriaDAO;
import ch.hearc.ig.guideresto.persistence.RestaurantDAO;
import ch.hearc.ig.guideresto.persistence.RestaurantTypeDAO;
import ch.hearc.ig.guideresto.presentation.CLI;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws SQLException {
    var scanner = new Scanner(System.in);
    var rest = new RestaurantDAO();
    var city = new CityDAO();
    var type = new RestaurantTypeDAO();
    var evalCrit = new EvaluationCriteriaDAO();
    var printStream = System.out;
    var cli = new CLI(scanner, printStream, rest, city, type, evalCrit);

    cli.start();
  }
}
