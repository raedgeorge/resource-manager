package com.atech.mongodbapp.repository.products;

import com.atech.mongodbapp.entity.products.Fire;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FireRepository extends CrudRepository<Fire, String> {

    List<Fire> findAllByDescriptionOrTybeNumber(String str);
}
