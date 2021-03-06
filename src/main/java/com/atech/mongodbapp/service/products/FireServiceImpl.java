package com.atech.mongodbapp.service.products;

import com.atech.mongodbapp.entity.products.Fire;
import com.atech.mongodbapp.repository.products.FireRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FireServiceImpl implements FireService {

    private final FireRepository fireRepository;

    public FireServiceImpl(FireRepository fireRepository) {
        this.fireRepository = fireRepository;
    }

    @Override
    public List<Fire> findAll() {
        List<Fire> fireList = new ArrayList<>();
        fireRepository.findAll().forEach(fireList::add);
        return fireList;
    }

    @Override
    public Fire save(Fire fire, String id) {

        if (id.equals("")){
            return fireRepository.save(fire);
        }
        else {
            fire.setId(id);
            return fireRepository.save(fire);
        }

    }

    @Override
    public Fire findById(String id) {
        return fireRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Fire fire) {
        fireRepository.delete(fire);
    }

    @Override
    public void deleteById(String id) {
        fireRepository.deleteById(id);
    }

    @Override
    public List<Fire> searchAnyByString(String str) {

        return fireRepository.findAllByDescriptionOrTybeNumber(str);
    }

    @Override
    public Page<Fire> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {

        Sort ascending = Sort.by(sortField).ascending();
        Sort descending = Sort.by(sortField).descending();
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? ascending : descending;

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return fireRepository.findAll(pageable);
    }
}
