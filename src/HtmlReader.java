import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by christianurban on 02.11.16.
 */
public class HtmlReader {
    public Recipe ReadHtmlFromUrl(String recipeUrl) throws IOException {
        Connection conn = Jsoup.connect(recipeUrl);
        Document doc = conn.get();
        Recipe r = new Recipe();
        r.setTitle(ReadTitle(doc));
        r.setIngredients(ReadIngredients(doc));
        r.setInstructions(ReadInstructions(doc));
        //r.setNutritions(ReadNutritions(doc));
        return r;
    }

    public String ReadTitle(Document doc)
    {
        return doc.select("[class=page-title]").text();
    }

    public ArrayList<Ingredient> ReadIngredients(Document doc)
    {
        ArrayList<Ingredient> ingredientsList = new ArrayList<>();
        for(Element ingredientTable : doc.select("[class=incredients]"))
        {
            for(Element ingredientNode : ingredientTable.children())
            {
                for(Element ingredient : ingredientNode.children())
                {
                    Elements ingredientsParts = ingredient.children();
                    Ingredient i = new Ingredient();
                    for(int counter = 0; counter < ingredientsParts.size(); counter++)
                    {
                        if(counter==0)
                        {
                            i.setQuantity(ingredientsParts.get(counter).text());
                        }
                        else
                        {
                            i.setName(ingredientsParts.get(counter).text());
                        }
                    }
                    ingredientsList.add(i);

                }
            }
        }
        return ingredientsList;
    }

    public ArrayList<String> ReadInstructions(Document doc)
    {
        ArrayList<String> instructions = new ArrayList<>();
        instructions.add(doc.select("[class=instructions m-b-200]").text());
        return instructions;
    }
}
