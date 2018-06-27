package com.example;

import groovyx.net.http.HttpResponseDecorator;
import groovyx.net.http.RESTClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;

import static groovyx.net.http.ContentType.JSON;

public class SimpleExample {
    public static void main(String[] args) {
        try {
            RESTClient restClient = new RESTClient("https://jsonplaceholder.typicode.com/");

            final Map contentType = new LinkedHashMap() {{
                put("Content-Type", "application/json");
            }};

            Map payload = new LinkedHashMap() {
                {
                    put("path", "posts/1");
                    put("headers", contentType);
                    put("requestContentType", JSON);
//                    put("body", new LinkedHashMap() {
//                        {
//                            put("key", value);
//                        }
//                    });
                }
            };
            HttpResponseDecorator response = null;
            response = (HttpResponseDecorator) restClient.get(payload);

            Map map = (Map) response.getData();
            System.out.println("HEADER: " + response.getAllHeaders());

            System.out.println(map);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
