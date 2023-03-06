package cookbook.cookbookrecipeapplication.services;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import cookbook.cookbookrecipeapplication.models.*;
import cookbook.cookbookrecipeapplication.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
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

    public static CustomRecipe getCustomRecipeSpoonacular() throws IOException {
        String uri = "https://4f305d33-68e6-4896-a493-63bcf82396c8.mock.pstmn.io/recipe-test";
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(uri, String.class);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("CustomRecipeDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(CustomRecipe.class, new CustomRecipeDeserializer());
        mapper.registerModule(module);

        return mapper.readValue(json, CustomRecipe.class);
    }

    public static Recipe getRecipeSpoonacular() throws IOException {
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

    public static SearchResults getSearchResultsSpoonacular() throws IOException {
        String uri = "https://184dbb6d-9815-4abd-bfbb-e31ffccf495b.mock.pstmn.io/multipe-recipes";
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(uri, String.class);
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("SearchResultsDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(SearchResults.class, new SearchResultsDeserializer());
        mapper.registerModule(module);
        SearchResults searchResults = mapper.readValue(json, SearchResults.class);

        return searchResults;
    }

    public static void main(String[] args) throws IOException {

//        getCustomRecipeSpoonacular();
//        getRecipeSpoonacular();
//        getMultipleRecipesSpoonacular();
//        System.out.println(getSearchResultsSpoonacular());

    }
}



