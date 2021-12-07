package com.heb.imagesvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("")
    public @ResponseBody String postImage(@RequestBody String image){
        return image + " will be saved and identified";
    }

}
