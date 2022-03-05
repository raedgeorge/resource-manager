package com.atech.mongodbapp.service.products;

import com.atech.mongodbapp.entity.products.CCTV;
import com.atech.mongodbapp.entity.products.Fire;

import java.util.List;

public interface CCTVService {

    List<CCTV> findAll();

    CCTV save(CCTV cctv, String id);

    CCTV findById(String id);

    void delete(CCTV cctv);

    void deleteById(String id);

    List<CCTV> searchAnyByString(String str);
}
