package com.heb.imagesvc;

import com.heb.imagesvc.models.ResponseModel;

import java.sql.*;

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

    public ResultSet getAllImages()throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/imageschema", "root", "pass");

        Statement statement = connection.createStatement();

        String sqlQuery = "SELECT `images`.`ImageId`, `images`.`URL`, `images`.`label`, `images`.`detectedObjects` FROM `imageschema`.`images`;";

        ResultSet resultSet = statement.executeQuery(sqlQuery);

        return resultSet;

    }

    public ResultSet getImageById(String id) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/imageschema", "root", "pass");

        Statement statement = connection.createStatement();

        String sqlQuery = "SELECT * FROM images WHERE ImageId = \'" + id +"\'";

        return statement.executeQuery(sqlQuery);

    }

    public ResultSet getImagesContaining(String objects) throws SQLException{

        objects = objects.replace("\"", "");
        String param[] = objects.split(",");


        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/imageschema", "root", "pass");

        Statement statement = connection.createStatement();

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("SELECT * FROM images WHERE ");

        String temp = "";
        for (String object : param){
            stringBuilder.append(temp);
            stringBuilder.append("detectedObjects LIKE '%" + object + "%'");
            temp = " OR ";

        }

        String sqlQuery = stringBuilder.toString();

        ResultSet resultSet = statement.executeQuery(sqlQuery);

        return resultSet;

    }
}
