package com.atech.mongodbapp.service.products;

import com.atech.mongodbapp.entity.products.Fire;

import java.util.List;

public interface FireService {

    List<Fire> findAll();

    Fire save(Fire fire, String id);

    Fire findById(String id);

    void delete(Fire fire);

    void deleteById(String id);

    List<Fire> searchAnyByString(String str);
}
