package com.repositories;

import com.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LihaiMac on 4/26/17.
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating,Long>{
    List<Rating> findById(Long id);
}
