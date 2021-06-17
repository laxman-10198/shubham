package com.example.onlinedirectoryprovider;

public class StringFunction {
   public String htmlEncoding(String htmlEncoding){
       htmlEncoding.replaceAll("&lt;[^&]*","");
        return htmlEncoding;
    }
}
