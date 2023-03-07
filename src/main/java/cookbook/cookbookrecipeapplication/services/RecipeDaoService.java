package cookbook.cookbookrecipeapplication.services;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import cookbook.cookbookrecipeapplication.PropertiesReader;
import cookbook.cookbookrecipeapplication.models.*;
import cookbook.cookbookrecipeapplication.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeDaoService {

    private final RecipeRepository recipeDao;
    private final CustomRecipeRepository customRecipeDao;
    private final IngredientRepository ingredientDao;
    private final InstructionRepository instructionDao;
    private final ReviewRepository reviewDao;


    public RecipeDaoService(RecipeRepository recipeRepository, CustomRecipeRepository customRecipeRepository, IngredientRepository ingredientRepository, InstructionRepository instructionRepository, ReviewRepository reviewRepository) {
        this.recipeDao = recipeRepository;
        this.customRecipeDao = customRecipeRepository;
        this.ingredientDao = ingredientRepository;
        this.instructionDao = instructionRepository;
        this.reviewDao = reviewRepository;
    }

    public Recipe findRecipeById(long id) {
        return recipeDao.findById(id).get();
    }

    public List<CustomRecipe> findAllCustomRecipesByUser(User user) {
        return customRecipeDao.findCustomRecipesByUser(user);
    }

    public List<Review> findAllReviewsByRecipeId(long id) {
        return reviewDao.findAllByRecipe_id(id);
    }

    public CustomRecipe getCustomRecipeSpoonacular(long spoontacular_id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/" + spoontacular_id + "/information?includeNutrition=false"))
                .header("X-RapidAPI-Key", PropertiesReader.getProperty("SPOONACULAR_API_KEY"))
                .header("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("CustomRecipeDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(CustomRecipe.class, new CustomRecipeDeserializer());
        mapper.registerModule(module);
        System.out.println(response.body());

        return mapper.readValue(response.body(), CustomRecipe.class);
    }

    public Recipe getRecipeSpoonacular() throws IOException {
        String uri = "https://4f305d33-68e6-4896-a493-63bcf82396c8.mock.pstmn.io/recipe-test";
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(uri, String.class);
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("RecipeDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Recipe.class, new RecipeDeserializer());
        mapper.registerModule(module);

        return mapper.readValue(json, Recipe.class);
    }

    public SearchResults getSearchResultsSpoonacular(String searchParam) throws IOException, InterruptedException {
        String updatedSearchParam = searchParam.replaceAll(" ", "%20");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/complexSearch?query=" + updatedSearchParam + "&instructionsRequired=true&fillIngredients=false&addRecipeInformation=false&sort=popularity&sortDirection=asc&number=5"))
                .header("X-RapidAPI-Key", PropertiesReader.getProperty("SPOONACULAR_API_KEY"))
                .header("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("SearchResultsDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(SearchResults.class, new SearchResultsDeserializer());
        mapper.registerModule(module);
        SearchResults searchResults = mapper.readValue(response.body(), SearchResults.class);

        List<Recipe> recipeResults = searchResults.getResults();
        for (Recipe recipe : recipeResults) {
            System.out.println(recipe.getTitle());
        }
        return searchResults;
    }
    public void saveCustomRecipe(CustomRecipe customRecipe){
        customRecipeDao.save(customRecipe);
    }
    public void saveRecipe(Recipe recipe){
        recipeDao.save(recipe);
    }

    public void saveIngredient(Ingredient ingredient){
        ingredientDao.save(ingredient);
    }

    public void saveInstruction(Instruction instruction){
        instructionDao.save(instruction);
    }

    public static void main(String[] args) throws IOException, InterruptedException {

//        System.out.println(getCustomRecipeSpoonacular(126987));
//        getRecipeSpoonacular();
//        getMultipleRecipesSpoonacular();
//        System.out.println(getSearchResultsSpoonacular("blueberry muffin"));

    }
}



