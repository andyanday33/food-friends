package recipesharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import recipesharing.db.CuisineDao;
import recipesharing.logic.Cuisine;
import recipesharing.logic.Cuisine;
import recipesharing.logic.Recipe;

import java.util.List;

/**
 *
 */
@Service
public class CuisineService {

    @Autowired
    CuisineDao cuisineService;

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Cuisine> getAllCuisines() {
        return cuisineService.getAllCuisines();
    }

    public List<Cuisine> findAllCuisinesWithPageLimit(int page, int size) {
        
        Query query = new Query();
        long count = mongoTemplate.count(query, Cuisine.class);

        Pageable pageable = PageRequest.of(page - 1, size);

        return mongoTemplate.find(query.with(pageable).with(Sort.by(Sort.Direction.DESC, "name")), Cuisine.class);
    }

    /**
     * find cuisines with page limits and query the query can specify the name, id etc
     *
     * @param query query condition
     * @param page  current
     * @param size  the number of records shows up
     *
     * @return cuisine list
     */
    public List<Cuisine> findAllCuisinesByQueryWithPageLimit(Query query, int page, int size) {

        long count = mongoTemplate.count(query, Cuisine.class);

        Pageable pageable = PageRequest.of(page - 1, size);

        return mongoTemplate.find(query.with(pageable).with(Sort.by(Sort.Direction.DESC, "name")), Cuisine.class);
    }

    public void addOneCuisine(Cuisine cuisine) {
        cuisineService.addOneCuisine(cuisine);
    }

    public void delOneCuisine(Cuisine cuisine) {
        cuisineService.delOneCuisine(cuisine);
    }


}
