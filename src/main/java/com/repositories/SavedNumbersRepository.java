package com.repositories;

import com.entities.SavedNumbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by LihaiMac on 4/26/17.
 */
@Repository
public interface SavedNumbersRepository extends JpaRepository<SavedNumbers,Long>{
    SavedNumbers findById(Long numbersId);
    //List<SavedNumbers> findByBetweenDateStartAndDateEnd(Date dateStart, Date dateEnd);
}
