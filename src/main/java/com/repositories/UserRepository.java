package com.repositories;

import com.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LihaiMac on 4/26/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    List<User> findById(Long id);
}
