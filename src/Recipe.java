import java.util.ArrayList;

/**
 * Created by christianurban on 02.11.16.
 */
public class Recipe {
    public  Recipe()
    {
        setIngredients(new ArrayList<>());
        setInstructions(new ArrayList<>());
        setNutritions(new ArrayList<>());
    }

    private String title;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> instructions;
    private ArrayList<Nutrition> nutritions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(ArrayList<String> instructions) {
        this.instructions = instructions;
    }

    public ArrayList<Nutrition> getNutritions() {
        return nutritions;
    }

    public void setNutritions(ArrayList<Nutrition> nutritions) {
        this.nutritions = nutritions;
    }
}
