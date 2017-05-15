package com.repositories;

import com.entities.User;
import com.entities.UserNumbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by LihaiMac on 4/26/17.
 */
@Repository
public interface UserNumbersRepository extends JpaRepository<UserNumbers,Long>{
    Set<UserNumbers> getUserNumbersByUserOrderByIdAsc(Long id);

    List<UserNumbers> findByUser(User user);
}
