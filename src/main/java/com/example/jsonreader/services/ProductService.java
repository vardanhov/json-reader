package com.example.jsonreader.services;


import com.example.jsonreader.domain.Product;
import com.example.jsonreader.utils.JsonToObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    public List<Product> getAllProducts(String sort, String order, String category) throws IOException {
        InputStream inputStream = TypeReference.class.getResourceAsStream("/goods.json");
        assert inputStream != null;
        String text = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
        JSONObject jsonObject = new JSONObject(text);
        JSONArray array = (JSONArray) jsonObject.get("goods");

        List<Product> productList = JsonToObjectMapper.mapJsonToObjects(array);
        if (category != null) {
            productList = productList.stream().filter(product -> product.getCategory().getName().equals(category)).collect(Collectors.toList());
        }
        productList = sortGoods(productList, sort, order);
        return productList;
    }

    public List<Product> sortGoods(List<Product> productList, String sort, String order) {
        if ("date".equals(sort) && "desc".equals(order)) {
            productList = productList.stream().sorted(Comparator.comparing(Product::getDate).reversed()).collect(Collectors.toList());
        }
        if ("price".equals(sort) && "asc".equals(order)) {
            productList = productList.stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
        }
        if ("price".equals(sort) && "desc".equals(order)) {
            productList = productList.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).collect(Collectors.toList());
        }
        return productList;
    }

}
