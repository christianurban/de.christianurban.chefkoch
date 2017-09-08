import java.awt.*;
import java.io.File;
import java.io.PrintWriter;

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

            StringBuilder sb = new StringBuilder();
            sb.append("\"2\",\""+r.getTitle()+"\",\"");
            for(String i : r.getInstructions())
            {
                //i = i.replace("°C","Grad Celsius");
                sb.append(i + "<br/>");
            }
            sb.append("\",\"\"");



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

            PrintWriter pw = new PrintWriter("test.csv","ISO-8859-1");
            pw.write(sb.toString());
            pw.flush();
            pw.close();


            File htmlFile = new File("test.csv");
            //Desktop.getDesktop().browse(htmlFile.toURI());
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
