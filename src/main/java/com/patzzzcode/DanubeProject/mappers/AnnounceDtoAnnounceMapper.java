package com.patzzzcode.DanubeProject.mappers;



import com.patzzzcode.DanubeProject.bo.Announce;
import com.patzzzcode.DanubeProject.dto.AnnounceDto;
import com.patzzzcode.DanubeProject.mapping.CustomMapping;

import org.modelmapper.PropertyMap;



@CustomMapping
public class AnnounceDtoAnnounceMapper extends PropertyMap<AnnounceDto, Announce> {

    @Override
    protected void configure() {
        skip(destination.getId());
      
    }

}
