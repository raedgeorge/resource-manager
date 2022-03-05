package com.atech.mongodbapp.repository.products;

import com.atech.mongodbapp.entity.products.CCTV;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CCTVRepository extends CrudRepository<CCTV, String> {

    List<CCTV> findCCTVByTybeNumberIn(String str);
    List<CCTV> findCCTVByTybeNumberLike(String str);
}
