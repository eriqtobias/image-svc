package com.heb.imagesvc;

import com.heb.imagesvc.models.ResponseModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyJDBC {
    public void writeDb(String id, String url, String label, String detectedObjects){

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/imageschema", "root", "pass");

            Statement statement = connection.createStatement();

            String sqlQuery = "INSERT INTO images VALUES ('" + id + "', '" + url + "', '" + label + "', '" + detectedObjects + "')";

            statement.executeUpdate(sqlQuery);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet getAllImages(){
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/imageschema", "root", "pass");

            Statement statement = connection.createStatement();

            String sqlQuery = "SELECT `images`.`ImageId`, `images`.`URL`, `images`.`label`, `images`.`detectedObjects` FROM `imageschema`.`images`;";

            ResultSet resultSet = statement.executeQuery(sqlQuery);

            return resultSet;

        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getImageById(String id){
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/imageschema", "root", "pass");

            Statement statement = connection.createStatement();

            String sqlQuery = "SELECT * FROM imageschema WHERE ImageId LIKE " + id;

            ResultSet resultSet = statement.executeQuery(sqlQuery);

            return resultSet;

        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getImagesContaining(String objects){

        String param[] = objects.split(",");

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/imageschema", "root", "pass");

            Statement statement = connection.createStatement();

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("SELECT * FROM imageschema WHERE ");

            String temp = "";
            for (String object : param){
                stringBuilder.append(temp);
                stringBuilder.append("detectedObjects LIKE '%" + object + "%'");
                temp = " OR ";

            }

            String sqlQuery = stringBuilder.toString();
            System.out.println(sqlQuery);

            ResultSet resultSet = statement.executeQuery(sqlQuery);

            return resultSet;

        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
