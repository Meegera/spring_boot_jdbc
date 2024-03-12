package com.homework.hw_jdbc.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
    private String text;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getProductName(String name){
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        String result = namedParameterJdbcTemplate.query(read("myScript.sql"),
                params,
                (rs, rowNum) ->{
                    String productName = rs.getString("product_name");
                    System.out.println(productName);
                    return productName;
                }).toString();
        return result;
    }
}