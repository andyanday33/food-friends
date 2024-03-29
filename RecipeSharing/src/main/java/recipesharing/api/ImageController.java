package recipesharing.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import recipesharing.logic.Image;
import recipesharing.service.ImageService;
import recipesharing.vo.Result;

import java.io.IOException;
import java.util.Base64;

/**
 *
 */
@Controller
@RequestMapping("images")
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping("/add")
    public Result addImage(@RequestParam("title") String title,
                           @RequestParam("image") MultipartFile image)
            throws IOException {
        String id = imageService.addImage(title, image);
        return Result.success(id);
    }
    
    @GetMapping("/images/{id}")
    public Result getImage(@PathVariable String id) {
        Image image = imageService.getImage(id);
//        image.setContent(Base64.getEncoder().encodeToString(image.getContent().getData()));

        return Result.success(image);
    }


}
