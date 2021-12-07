package com.heb.imagesvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.heb.imagesvc.models.RequestModel;

import java.io.IOException;

@Controller
@RequestMapping("/images")
public class ImageResource {

    @GetMapping("")
    public @ResponseBody String getImages(@RequestParam(required = false) String objects){
        if (objects == null) {
            return "All images will be returned here";
        }
        return "This will return images containing: " + objects;
    }

    @GetMapping("/{image-id}")
    public @ResponseBody String getImageById(@PathVariable("image-id") String imageId){
        return "This will return an image by ID: " + imageId;
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String postImage(@RequestBody RequestModel requestModel) throws IOException {
        System.out.println(requestModel.getImageURL());
        ImaggaRequestBuilder imaggaRequestBuilder = new ImaggaRequestBuilder();
        return imaggaRequestBuilder.buildRequest(requestModel);
    }

}
