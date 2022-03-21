package com.atech.mongodbapp.service.products;

import com.atech.mongodbapp.entity.products.CCTV;
import com.atech.mongodbapp.repository.products.CCTVRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CCTVServiceImpl implements CCTVService{

    private final CCTVRepository cctvRepository;

    public CCTVServiceImpl(CCTVRepository cctvRepository) {
        this.cctvRepository = cctvRepository;
    }

    @Override
    public List<CCTV> findAll() {

        List<CCTV> cctvList = new ArrayList<>();
        cctvRepository.findAll().forEach(cctvList::add);
        return cctvList;
    }

    @Override
    public CCTV save(CCTV cctv, String id) {

        if (id.trim().equals("")) {
            return cctvRepository.save(cctv);
        }
        else {
            cctv.setId(id);
            return cctvRepository.save(cctv);
        }
    }

    @Override
    public CCTV findById(String id) {
        return cctvRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(CCTV cctv) {
        cctvRepository.delete(cctv);
    }

    @Override
    public void deleteById(String id) {
        cctvRepository.deleteById(id);
    }

    @Override
    public List<CCTV> searchByAnyString(String str) {

        log.error("inside the cctv service impl search method");

        List<CCTV> cctvList = new ArrayList<>();

        if (!str.trim().equals("")) {
            cctvRepository.findAll().stream().forEach(cctv -> {
                if (cctv.getTybeNumber().toLowerCase().contains(str.toLowerCase()) ||
                    cctv.getDescription().toLowerCase().contains(str.toLowerCase())) {

                    cctvList.add(cctv);
                }
            });
        }
        else cctvList.addAll(cctvRepository.findAll());

        System.out.println("SIZE >>> " + cctvList.size());

        return cctvList;
    }

    @Override
    public Page<CCTV> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {

        Sort ascending = Sort.by(sortField).ascending();
        Sort descending = Sort.by(sortField).descending();
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? ascending : descending;

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return cctvRepository.findAll(pageable);
    }
}
