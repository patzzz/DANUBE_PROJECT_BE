package com.patzzzcode.DanubeProject.service;

import com.patzzzcode.DanubeProject.bo.Announce;
import com.patzzzcode.DanubeProject.bo.Category;
import com.patzzzcode.DanubeProject.bo.Subcategory;
import com.patzzzcode.DanubeProject.bo.User;
import com.patzzzcode.DanubeProject.repositories.AnnounceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AnnounceService {

    @Autowired
    private AnnounceRepository announceRepository;

    public Announce createAnnounce(Announce announce, Category category, Subcategory subcategory, User user) {
        Announce a = new Announce(null, announce.getTitle(), announce.getDescription(), announce.getPrice(), category,
                subcategory, new Date(), new Date(), user);
        a = announceRepository.save(a);
        return a;
    }
}
