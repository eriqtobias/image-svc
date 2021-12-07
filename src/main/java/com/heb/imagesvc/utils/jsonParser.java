package com.heb.imagesvc.utils;

import org.springframework.util.StringUtils;

public class jsonParser {
    public String parseObjects(String jsonResponse){
        StringBuilder commaDelimited = new StringBuilder();

        for(int i = 0; i < jsonResponse.length()-7; i++){
            if (jsonResponse.substring(i, i+6).equals("\"en\":\"")){
                i += 6;
                while(jsonResponse.charAt(i) != '\"') {
                    commaDelimited.append(jsonResponse.charAt(i));
                    i++;
                }
                commaDelimited.append(",");
            }

        }
        return commaDelimited.toString();
    }
}
