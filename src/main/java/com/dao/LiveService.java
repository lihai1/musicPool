package com.dao;

import com.entities.LiveData;
import com.repositories.LiveDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LihaiMac on 4/20/17.
 */
@Service
public class LiveService {

    @Autowired
    private LiveDataRepository liveDataRepository;


    public LiveService() {


    }

    public String saveCurrent(LiveData cur) {
        try {
            cur = liveDataRepository.save(cur);
        } catch (Exception e) {
             e.printStackTrace();
             return e.getMessage();
        }

        return "Success!";

    }

    public List<LiveData> getCurrent() {
        return liveDataRepository.findFirst5ByOrderByStartedAsc();
    }


}
