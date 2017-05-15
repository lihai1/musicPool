package com.dao;

import com.entities.SavedNumbers;
import com.entities.User;
import com.entities.UserNumbers;
import com.models.FormRequest;
import com.models.SaveNumbersForm;
import com.repositories.SavedNumbersRepository;
import com.repositories.UserNumbersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

/**
 * Created by LihaiMac on 4/20/17.
 */
@Service
public class UserNumbersService {

    @Autowired
    private UserNumbersRepository userNumbersRepository;

    @Autowired
    private SavedNumbersRepository savedNumbersRepository;

    @PersistenceContext
    private EntityManager em;

    private Logger logger = LoggerFactory.getLogger(UserNumbersService.class);

    public Set<UserNumbers> getUserNumbersByUser(Long id) {
        return userNumbersRepository.getUserNumbersByUserOrderByIdAsc(id);
    }

    public UserNumbers saveNumbers(Long userId, Long numbersId,List<Integer> willBe) {
        User user = em.getReference(User.class, userId);
        SavedNumbers saved = savedNumbersRepository.findById(numbersId);
        try {
            //numbers.setUser(user);
            UserNumbers nums = new UserNumbers(user,willBe,saved);
            //SavedNumbers tmp = new SavedNumbers();
            //nums.setNumbers(tmp);
            return userNumbersRepository.save(nums);
        } catch (NoSuchElementException e) {
            logger.error("UserNumbersService:saveNumbers: " + e);
        }

        UserNumbers usr = new UserNumbers();
        //usr.setName("user exist!");
        return usr;

    }

    public UserNumbers saveNumbers(SaveNumbersForm numbers) {
        return saveNumbers(numbers.getUserId(), numbers.getNumbersId(),numbers.getWillBe());
    }

    public List<UserNumbers> getNumbers(Long id) {
        User user = em.getReference(User.class, id);
        List<UserNumbers> l = userNumbersRepository.findByUser(user);
        return l;
    }

    public List<SavedNumbers> generateNumbers(FormRequest request){
        List<SavedNumbers> saved = new ArrayList<>();
                //savedNumbersRepository.findByBetweenDateStartAndDateEnd(request.getFrom(),request.getTo());
        if(saved.size()==0)
            return generateNewNumbers(request);
        else
            return saved;
    }

    public List<SavedNumbers> generateNewNumbers(FormRequest request){
        UserNumbers nums = new UserNumbers();
        //nums.setNumbers(saved);
        int[][] result = request.runRequestAsArrays();
        List<SavedNumbers> toSave = new ArrayList<>();
        SavedNumbers tosave;
        for(int[] res:result){
            tosave = new SavedNumbers(request.getFrom(),request.getTo(),res);
            toSave.add(savedNumbersRepository.save(tosave));
        }
        //SavedNumbers tmp = new SavedNumbers();
        //List list = new ArrayList();

        //list.add(1);
        //list.add(4);

        //tmp.setNumbers(list);
        //tmp = savedNumbersRepository.save(tmp);
        //numbers.setNumbers(tmp);
        return toSave;
    }

    public static List<Integer> listFromString(String willBe) {
        if (willBe == null) return null;
        List<Integer> result = new ArrayList();
        List<String> tmp = Arrays.asList(willBe.replace("[", "").replace("]", "").split(","));
        for (String n : tmp) {
            result.add(Integer.parseInt(n));
        }
        return result;
    }

    public static String StringifyIntegerList(List willBe) {
        String result ="[";
        for(int i=0;i<willBe.size();i++)
            result += willBe.get(i)+",";
        result = result.substring(0,result.length()-1);
        result+=']';
        return result;
    }

}
