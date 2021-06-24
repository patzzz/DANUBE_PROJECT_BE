package com.patzzzcode.DanubeProject.service;

import java.util.Date;

import com.patzzzcode.DanubeProject.bo.Announce;
import com.patzzzcode.DanubeProject.bo.Category;
import com.patzzzcode.DanubeProject.bo.Subcategory;
import com.patzzzcode.DanubeProject.bo.User;
import com.patzzzcode.DanubeProject.dto.AnnounceDto;
import com.patzzzcode.DanubeProject.jwt.service.DecodeJwtService;
import com.patzzzcode.DanubeProject.mappers.AnnounceDtoAnnounceMapper;
import com.patzzzcode.DanubeProject.repositories.AnnounceRepository;
import com.patzzzcode.DanubeProject.repositories.CategoryRepository;
import com.patzzzcode.DanubeProject.repositories.SubcategoryRepository;
import com.patzzzcode.DanubeProject.repositories.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AnnounceService {

    @Autowired
    private AnnounceRepository announceRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubcategoryRepository subcategoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DecodeJwtService decodeJwtService;
    @Autowired
    private AnnounceDtoAnnounceMapper announceDtoAnnounceMapper;

    public void addAnnounce(UsernamePasswordAuthenticationToken principal, AnnounceDto announceDto, Long categoryId,
            Long subcategoryId) {
        Long userId = decodeJwtService.decodeJWT(principal).getUserId();
        User user = userRepository.findById(userId).orElse(null);
        Category category = categoryRepository.findById(categoryId).orElse(null);
        Subcategory subcategory = subcategoryRepository.findById(subcategoryId).orElse(null);
        createAnnounceWithoutMapper(announceDto, category, subcategory, user);
    }

    public void addAnnounceWithMapper(UsernamePasswordAuthenticationToken principal, AnnounceDto announceDto,
            Long categoryId, Long subcategoryId) {
        Long userId = decodeJwtService.decodeJWT(principal).getUserId();
        User user = userRepository.findById(userId).orElse(null);
        Category category = categoryRepository.findById(categoryId).orElse(null);
        Subcategory subcategory = subcategoryRepository.findById(subcategoryId).orElse(null);
        createAnnounceWithMapper(announceDto, category, subcategory, user);
    }

    private void createAnnounceWithoutMapper(AnnounceDto announceDto, Category category, Subcategory subcategory,
            User user) {
        Announce announce = new Announce();
        announce.setId(null);
        announce.setCategory(category);
        announce.setDescription(announceDto.getDescription());
        announce.setPrice(announceDto.getPrice());
        announce.setTitle(announceDto.getTitle());
        announce.setCreationDate(new Date());
        announce.setUser(user);
        announce.setSubcategory(subcategory);
        announce.setLastUpdateDate(new Date());
        announceRepository.save(announce);
    }

    private void createAnnounceWithMapper(AnnounceDto announceDto, Category category, Subcategory subcategory,
            User user) {
        Announce announce = new Announce();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.addMappings(announceDtoAnnounceMapper);
        modelMapper.map(announceDto, announce);
        announce.setId(null);
        announce.setCreationDate(new Date());
        announce.setUser(user);
        announce.setSubcategory(subcategory);
        announce.setLastUpdateDate(new Date());
        announceRepository.save(announce);
    }
}
