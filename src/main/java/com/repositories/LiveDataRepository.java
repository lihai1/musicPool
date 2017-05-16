package com.repositories;

import com.entities.LiveData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LihaiMac on 4/26/17.
 */
@Repository
public interface LiveDataRepository extends JpaRepository<LiveData,Long>{

    List<LiveData> findFirst5ByOrderByStartedAsc();

}
