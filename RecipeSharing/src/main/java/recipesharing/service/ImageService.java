package recipesharing.service;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import recipesharing.db.ImageDao;
import recipesharing.logic.Image;

import java.io.IOException;

/**
 *
 */
@Service
public class ImageService {

    @Autowired
    private ImageDao imageDao;

    /**
     * add one image to the db
     * @param title image name
     * @param file image file
     * @return _id for the image
     * @throws IOException
     */
    public String addImage(String title, MultipartFile file)throws IOException{
        Image image = new Image(title);
        image.setContent(
                new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        image = imageDao.insert(image);
        return image.getId();
    }

    /**
     * get one image
     * @param id _id for the image
     * @return the image obj
     */
    public Image getImage(String id){
        return imageDao.findById(id).get();
    }
}
