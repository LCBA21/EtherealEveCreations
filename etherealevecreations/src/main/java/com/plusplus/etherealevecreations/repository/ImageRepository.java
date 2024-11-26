package com.plusplus.etherealevecreations.repository;

import com.plusplus.etherealevecreations.entity.Image;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {

}
