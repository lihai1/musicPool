package com.repositories;

import com.entities.Rating;
import com.entities.UserMusicUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by LihaiMac on 4/26/17.
 */
@Repository
public interface UsersMusicRepository extends JpaRepository<UserMusicUrl,Long>{
    Set<UserMusicUrl> getUserNumbersByUserOrderByIdAsc(Long id);

    List<UserMusicUrl> findByUser(Rating rating);

    List<UserMusicUrl> findByNameOrUrl(String name,String url);

}
