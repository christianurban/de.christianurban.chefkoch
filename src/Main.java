import java.awt.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.*;
/**
 * Created by christianurban on 02.11.16.
 */
public class Main {
    public static void main(String[] args)
    {
        try {
            HtmlReader reader = new HtmlReader();
            //reader.ReadHtmlFromUrl("http://www.chefkoch.de/rezepte/2558081400347453/Kartoffelsuppe-mit-Erbsen-und-Mettwuerstchen.html");
            Recipe r = reader.ReadHtmlFromUrl("http://www.chefkoch.de/rezepte/901131195473455/Mediterranes-Ofengemuese.html");

            int recipedId = 2;


            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO recipe (ID,Title,Preparation,ImageUrl) VALUES(");
            sb.append(recipedId+",\""+StringEscapeUtils.escapeHtml4(r.getTitle())+"\",\"");
            for(String i : r.getInstructions())
            {
                sb.append(StringEscapeUtils.escapeHtml4(i) + "<br/>");
            }
            sb.append("\",\"\");\n");

            for (Ingredient i : r.getIngredients()) {
                sb.append("INSERT INTO ingredient (RecipeID,ID,Quantity,Unit,Name) VALUES(");
                sb.append(recipedId+",NULL,");
                String test = i.getQuantity().replaceAll("\u00A0"," ");
                test = test.trim();
                String[] quantityArray = test.trim().split("\\s+");
                if(quantityArray.length == 1){
                    String quantityStr = quantityArray[0];
                    String unitString = "NULL";
                    if(StringUtils.isEmpty(quantityStr)) {
                        quantityStr = "NULL";
                    }
                    if(!quantityStr.matches("\\d*\\.?\\d+")){
                        quantityStr = "NULL";
                    }
                    else{
                        unitString = "\""+StringEscapeUtils.escapeHtml4("STÜCK")+"\"";
                    }
                    quantityStr = StringEscapeUtils.escapeHtml4(quantityStr);
                    sb.append(quantityStr+","+unitString+",");
                }
                if(quantityArray.length == 2){
                    String quantityStr = quantityArray[0];
                    String unitStr = quantityArray[1];
                    if(quantityStr.matches("\\d*\\.?\\d+") && StringUtils.isEmpty(unitStr)){
                        unitStr = "\"+STÜCK\"";
                    }
                    quantityStr = StringEscapeUtils.escapeHtml4(quantityStr);
                    unitStr = StringEscapeUtils.escapeHtml4(unitStr);
                    sb.append(quantityStr+",\""+unitStr+"\",");
                }
                sb.append(("\""+StringEscapeUtils.escapeHtml4(i.getName())+"\""));
                sb.append(");\n");
                System.out.println(i.getQuantity() + " - " + i.getName());
            }



            /*sb.append("<html>\n");
            sb.append("<body>\n");
            sb.append("<h1>\n");
            sb.append(r.getTitle());
            sb.append("</h1>\n");
            sb.append("<table><tr>\n");
            sb.append("<td>\n");
            sb.append("<h2>\n");
            sb.append("Zutaten:");
            sb.append("</h2>\n");
            sb.append("<ul>\n");
            for(Ingredient i : r.getIngredients())
            {
                sb.append("<li style='font-size:12px;'>"+i.getQuantity() + " " + i.getName() + "</li><br/>\n");
            }
            sb.append("</ul>\n");
            sb.append("</td>\n");
            sb.append("<td>\n");
            sb.append("<h2>\n");
            sb.append("Anleitung:");
            sb.append("</h2>\n");
            sb.append("<ul>\n");
            for(String i : r.getInstructions())
            {
                sb.append("<li style='font-size:12px;'>"+i + "</li><br/>\n");
            }
            sb.append("</ul>\n");
            sb.append("</td>\n");
            sb.append("<td>\n");
            sb.append("<h2>\n");
            sb.append("Sonstiges:");
            sb.append("</h2>\n");
            sb.append("<ul>\n");
            for(Nutrition i : r.getNutritions())
            {
                sb.append("<li style='font-size:12px;'>"+i.getName() + " " + i.getValue() + "</li><br/>\n");
            }
            sb.append("</ul>\n");
            sb.append("</td>\n");
            sb.append("</tr></table>\n");
            sb.append("</body>\n");
            sb.append("</html>");*/

            PrintWriter pw = new PrintWriter("test.sql","ISO-8859-1");
            pw.write(sb.toString());
            pw.flush();
            pw.close();


            //File htmlFile = new File("test.csv");
            //Desktop.getDesktop().browse(htmlFile.toURI());
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
