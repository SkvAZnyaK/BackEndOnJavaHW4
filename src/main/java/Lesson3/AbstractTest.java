package Lesson3;

import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class AbstractTest {

    private static String apiKey = "c2da8f4d878d4ea9b0c3a76734c360db";
    private static String hash = "fe9ed0899990eb9b25d260a7f90cbe67dcadf769";
    private static String userName = "/skvaznyak/";
    private static String searchRecipesUrl = "https://api.spoonacular.com/recipes/complexSearch";
    private static String classifyCuisineUrl = "https://api.spoonacular.com/recipes/cuisine";
    private static String mealPlannerUrl = "https://api.spoonacular.com/mealplanner";

    public static String getApiKey() {return apiKey; };
    public static String getHash() {return hash; };
    public static String getUserName() {return userName; };
    public static String getSearchRecipesUrl() {return searchRecipesUrl; };
    public static String getClassifyCuisineUrl() {return classifyCuisineUrl; };
    public static String getMealPlannerUrl() {return mealPlannerUrl; };

//static Properties prop = new Properties();
//private static InputStream configFile;
//private static String apiKey;
//private static String searchRecipesUrl;
//private static String classifyCuisineUrl;
//
//@BeforeAll
//static void initTest () throws IOException {
//    configFile = new FileInputStream("src/main/resources/testdata");
//    prop.load(configFile);
//    apiKey = prop.getProperty(apiKey);
//    searchRecipesUrl = prop.getProperty(searchRecipesUrl);
//    classifyCuisineUrl = prop.getProperty(classifyCuisineUrl);
//}

}