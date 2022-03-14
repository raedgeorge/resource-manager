package com.atech.mongodbapp.service.products;

import com.atech.mongodbapp.entity.products.CCTV;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CCTVService {

    List<CCTV> findAll();

    CCTV save(CCTV cctv, String id);

    CCTV findById(String id);

    void delete(CCTV cctv);

    void deleteById(String id);

    List<CCTV> searchByAnyString(String str);

    Page<CCTV> findPaginated(int pageNo, int pageSize);
}
