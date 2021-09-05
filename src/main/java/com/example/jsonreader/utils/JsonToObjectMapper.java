package com.example.jsonreader.utils;

import com.example.jsonreader.domain.Category;
import com.example.jsonreader.domain.Product;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class JsonToObjectMapper {
    public static List<Product> mapJsonToObjects(JSONArray array){
        List<Product> productList = new ArrayList<Product>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonObj = array.getJSONObject(i);
            Product product = new Product();
            product.setId(jsonObj.getLong("id"));
            product.setName(jsonObj.getString("name"));
            product.setCategory(Category.ofName(jsonObj.getString("category")));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
            String localDateTime =
                    LocalDateTime.ofInstant(Instant.ofEpochMilli(jsonObj.getLong("timestamp")),
                            TimeZone.getDefault().toZoneId()).format(formatter);
            product.setDate(localDateTime);
            product.setPrice(jsonObj.getBigDecimal("price"));
            productList.add(product);
        }
        return productList;
    }
}
