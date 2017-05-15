package com.repositories;

import com.entities.UserMusicUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LihaiMac on 4/26/17.
 */
@Repository
public interface UsersMusicRepository extends JpaRepository<UserMusicUrl,Long>{

    List<UserMusicUrl> findByNameOrUrl(String name,String url);

}
