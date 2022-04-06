package recipesharing.logic;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import recipesharing.db.IngredientDao;

@Data
@Document("Ingredient")
public class Ingredient {

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

    public Ingredient(String title, Double quantity, Unit unit) {
        this.title = title;
        this.quantity = quantity;
        this.unit = unit;
    }

    public void addUnit(Unit unit) {
        this.unit = unit;
    }
}
