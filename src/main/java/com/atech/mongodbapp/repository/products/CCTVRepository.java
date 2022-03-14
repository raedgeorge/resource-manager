package com.atech.mongodbapp.repository.products;

import com.atech.mongodbapp.entity.products.CCTV;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CCTVRepository extends MongoRepository<CCTV, String> {

}
