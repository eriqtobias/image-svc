package com.heb.imagesvc;

import com.heb.imagesvc.models.ResponseModel;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.heb.imagesvc.models.RequestModel;
import com.heb.imagesvc.utils.jsonParser;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Controller
@RequestMapping("/images")
public class ImageResource {

    @GetMapping("")
    public @ResponseBody String getImages(@RequestParam(required = false, value = "objects") String objects) throws SQLException {

        MyJDBC myJDBC = new MyJDBC();
        ResultSet resultSet;
        if (objects == null) {
            resultSet = myJDBC.getAllImages();
        }
        else{
            resultSet = myJDBC.getImagesContaining(objects);
        }
        StringBuilder stringBuilder = new StringBuilder();
        while(resultSet.next()){
            stringBuilder.append(
            resultSet.getString("ImageId") + "\n" +
                    resultSet.getString("URL") + "\n" +
                    resultSet.getString("label") + "\n" +
                    resultSet.getString("detectedObjects") + "\n");

        }
        return stringBuilder.toString(); //TODO: Return as JSON

    }

    @GetMapping("/{image-id}")
    public @ResponseBody String getImageById(@PathVariable("image-id") String imageId) throws SQLException {
        MyJDBC myJDBC = new MyJDBC();
        ResultSet resultSet = myJDBC.getImageById(imageId);
        return resultSet.getString("ImageId") + "\n" +
                resultSet.getString("URL") + "\n" +
                resultSet.getString("label") + "\n" +
                resultSet.getString("detectedObjects") + "\n";
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseModel postImage(@RequestBody RequestModel requestModel) throws IOException {
        String url = requestModel.getImageURL();
        System.out.println(requestModel.getImageURL());
        ImaggaRequestBuilder imaggaRequestBuilder = new ImaggaRequestBuilder();
        MyJDBC myJDBC = new MyJDBC();
        jsonParser parser = new jsonParser();

        String responseString = "";
        if(requestModel.getObjectDetection()) {
            responseString = imaggaRequestBuilder.buildRequest(requestModel);
        }

        UUID uuid = UUID.randomUUID();

        String id = uuid.toString();

        String label = requestModel.getLabel();

        String detectedObjects =  parser.parseObjects(responseString);

        if (label == null || label == ""){
            String firstObject[] = detectedObjects.split(",");
            label = firstObject[0];
        }

        if (label == null || label == ""){
            label = "image:" + id;
        }

        myJDBC.writeDb(id, url, label, detectedObjects);

        ResponseModel responseModel = new ResponseModel(label, id, url, detectedObjects);

        return responseModel;

    }

}
