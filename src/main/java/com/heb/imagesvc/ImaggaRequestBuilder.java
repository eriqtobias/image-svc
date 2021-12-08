package com.heb.imagesvc;

import com.heb.imagesvc.models.RequestModel;
import com.heb.imagesvc.models.ResponseModel;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ImaggaRequestBuilder {

    public String buildRequest(RequestModel requestModel) throws IOException {
        String image_url = requestModel.getImageURL();
        String credentialsToEncode = "acc_a8a195c3f9169a2" + ":" + "7819967cbeaca0a3aa2bf7ae4f7aa716";
        String basicAuth = Base64.getEncoder().encodeToString(credentialsToEncode.getBytes(StandardCharsets.UTF_8));

        String endpoint_url = "https://api.imagga.com/v2/tags";
        String url = endpoint_url + "?image_url=" + image_url;
        URL urlObject = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection) urlObject.openConnection();

        connection.setRequestProperty("Authorization", "Basic " + basicAuth);

        int responseCode = connection.getResponseCode();

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader connectionInput = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String jsonResponse = connectionInput.readLine();

        connectionInput.close();

        return jsonResponse;

    }
}
