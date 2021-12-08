package com.heb.imagesvc.utils;

import com.heb.imagesvc.models.ResponseModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResponseBuilder {
    public static ResponseModel[] builder(ResultSet resultSet, int index) throws SQLException {

        ResponseModel responseModel[];

        if (resultSet.next()){
            ResponseModel model = new ResponseModel(resultSet.getString("label"), resultSet.getString("imageId"), resultSet.getString("url"), resultSet.getString("detectedObjects"));
            responseModel = builder(resultSet, index+1);
            responseModel[index] = model;

        }else{
            responseModel = new ResponseModel[index];
        }
        return responseModel;
    }
}
