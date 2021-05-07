package com.example.demo.service;


import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private BedroomRepository bedroomRepository;
    @Autowired
    private KitchenRepository kitchenRepository;
    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private LivingroomRepository livingroomRepository;
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private FunitureRepository funitureRepository;


    // 큰 카테고리
    public List<FunitureCategory> getAllCategory() {
        return funitureRepository.findAll();
    }

    // 작은 카테고리 각각
    public List<BedroomCategory> getAllBedroomCateNo() {
        return bedroomRepository.findAll();
    }

    public List<KitchenCategory> getAllKitchenCateNo() {
        return kitchenRepository.findAll();
    }

    public List<LibraryCategory> getAllLibraryCateNo() {
        return libraryRepository.findAll();
    }

    public List<LivingroomCategory> getAllLivingroomCateNo() {
        return livingroomRepository.findAll();
    }

    public List<StorageCategory> getAllStorageCateNo() {
        return storageRepository.findAll();
    }
}
