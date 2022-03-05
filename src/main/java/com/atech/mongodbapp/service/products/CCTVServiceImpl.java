package com.atech.mongodbapp.service.products;

import com.atech.mongodbapp.entity.products.CCTV;
import com.atech.mongodbapp.repository.products.CCTVRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<CCTV> searchAnyByString(String str) {

        log.error("inside the cctv service impl search method");

        List<CCTV> cctvList;

        cctvList = cctvRepository.findCCTVByTybeNumberIn(str).stream()
                .filter(cctv -> cctv.getTybeNumber().equalsIgnoreCase(str))
                .collect(Collectors.toList());

        System.out.println("SIZE >>> " + cctvList.size());

        return cctvList;
    }
}
