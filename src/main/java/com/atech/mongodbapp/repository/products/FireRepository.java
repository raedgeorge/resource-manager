package com.atech.mongodbapp.repository.products;

import com.atech.mongodbapp.entity.products.Fire;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FireRepository extends MongoRepository<Fire, String> {

    List<Fire> findAllByDescriptionOrTybeNumber(String str);
}
