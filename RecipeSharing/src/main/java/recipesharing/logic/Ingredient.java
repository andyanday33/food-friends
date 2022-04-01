package recipesharing.logic;

import recipesharing.db.IngredientDao;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Ingredient")
public class Ingredient {

    @Autowired
    IngredientDao ingredientDao;
    @Id
    private String id;
    private String title;
    private Double quantity;
    private Unit unit;

    //TODO add unit enum
    public Ingredient(String title, Double quantity) {
        this.title = title;
        this.quantity = quantity;
    }

    public void addUnit(Unit unit) {
        this.unit = unit;
    }
}
