package recipesharing.api;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import recipesharing.logic.Cuisine;
import recipesharing.service.CuisineService;
import recipesharing.vo.Result;

import java.util.List;

/**
 *
 */
@RestController
public class CuisineController {

    @Autowired
    CuisineService cuisineService;

    @GetMapping("/findAllCuisines")
    public Result getAllCuisines(){
        List<Cuisine> allCuisines = cuisineService.getAllCuisines();
        return Result.success(allCuisines);
    }

    @PostMapping("/findRecipesByCuisine")
    public Result getRecipesByCuisineName(@RequestParam String cuisineName){

        return null;
    }


    @PostMapping("/addOneMoreCuisine")
    public Result addOneCuisine(@RequestParam String cuisineName){
        cuisineService.addOneCuisine(new Cuisine(cuisineName));
        return Result.success(null);
    }

}
