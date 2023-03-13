package cookbook.cookbookrecipeapplication.services;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import cookbook.cookbookrecipeapplication.PropertiesReader;
import cookbook.cookbookrecipeapplication.models.*;
import cookbook.cookbookrecipeapplication.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeDaoService {

    private final RecipeRepository recipeDao;
    private final CustomRecipeRepository customRecipeDao;
    private final IngredientRepository ingredientDao;
    private final InstructionRepository instructionDao;
    private final ReviewRepository reviewDao;
    private final DishTypeRepository dishTypeDao;
    private final UserRepository userDao;
    private final ChapterRepository chapterDao;
    private final RecentActivityRepository recentActivityDao;



    public RecipeDaoService(RecipeRepository recipeRepository, CustomRecipeRepository customRecipeRepository, IngredientRepository ingredientRepository, InstructionRepository instructionRepository, ReviewRepository reviewRepository, DishTypeRepository dishTypeDao, UserRepository userDao, ChapterRepository chapterDao, RecentActivityRepository recentActivityDao) {
        this.recipeDao = recipeRepository;
        this.customRecipeDao = customRecipeRepository;
        this.ingredientDao = ingredientRepository;
        this.instructionDao = instructionRepository;
        this.reviewDao = reviewRepository;
        this.dishTypeDao = dishTypeDao;
        this.userDao = userDao;
        this.chapterDao = chapterDao;
        this.recentActivityDao = recentActivityDao;
    }

    public Recipe findRecipeById(long id) {
        return recipeDao.findById(id).get();
    }

    public Recipe findRecipeBySpoonacularId(long spoonacularId){
        return recipeDao.findBySpoonacularId(spoonacularId);
    }

    public List<CustomRecipe> findAllCustomRecipesByUser(User user) {
        return customRecipeDao.findCustomRecipesByUser(user);
    }

    public List<Review> findAllReviewsByRecipeId(long id) {
        return reviewDao.findAllByRecipe_id(id);
    }

    public Recipe getRecipeAndCustomRecipeBySpoonacularId(long spoontacular_id) throws IOException, InterruptedException {
        // Fetches recipe data from spoonacular from the specified spoonacular id
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/" + spoontacular_id + "/information?includeNutrition=false"))
                .header("X-RapidAPI-Key", PropertiesReader.getProperty("SPOONACULAR_API_KEY"))
                .header("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        // Deserializes data into custom recipe object
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("CustomRecipeDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(CustomRecipe.class, new CustomRecipeDeserializer());
        mapper.registerModule(module);
        CustomRecipe customRecipe = mapper.readValue(response.body(), CustomRecipe.class);
        // Adds spoonacular as creator of recipe
        customRecipe.setCreator_id(userDao.findByUsername("Spoonacular"));

        // Deserializes data into recipe object and adds it into custom recipe object
        ObjectMapper mapper2 = new ObjectMapper();
        SimpleModule module2 =
                new SimpleModule("RecipeDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Recipe.class, new RecipeDeserializer());
        mapper.registerModule(module);
        Recipe recipe = mapper.readValue(response.body(), Recipe.class);
        recipe.setReviews(new ArrayList<>());
        recipe.setCustom_recipe(customRecipe);

        for (Instruction instruction : recipe.getCustom_recipe().getInstructions()){
            System.out.println(instruction.getOrder_num() + " " + instruction.getContent());
        }

        return recipe;
    }

    public Recipe getRecipeSpoonacular(long spoontacular_id) throws IOException, InterruptedException {
        // Fetches recipe data from spoonacular from the specified spoonacular id
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/" + spoontacular_id + "/information?includeNutrition=false"))
                .header("X-RapidAPI-Key", PropertiesReader.getProperty("SPOONACULAR_API_KEY"))
                .header("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("RecipeDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Recipe.class, new RecipeDeserializer());
        mapper.registerModule(module);

        return mapper.readValue(response.body(), Recipe.class);
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

        return searchResults;
    }

    public SearchResults getCategorySearchResultsSpoonacular(String searchParam) throws IOException, InterruptedException {
        String updatedSearchParam = searchParam.replaceAll("-", "%20");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/complexSearch?query=%20&type=" + updatedSearchParam + "&instructionsRequired=true&fillIngredients=false&addRecipeInformation=false&ignorePantry=true&sortDirection=asc&number=5"))
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

        return searchResults;
    }

    public List<Recipe> findTrendingRecipes() {
        List<Long> recipeIds = recipeDao.findTrendingRecipeIds();

        List<Recipe> recipes = new ArrayList<>();
        for(Long recipeId : recipeIds) {
            Recipe recipe = recipeDao.findById(recipeId).get();
            if (recipe.getSpoonacularId() != 0){
                CustomRecipe customRecipe = new CustomRecipe(userDao.findByUsername("spoonacular"));
                recipe.setCustom_recipe(customRecipe);
                recipe.setId(recipe.getSpoonacularId());
                recipes.add(recipe);
            } else {
                recipes.add(recipe);
            }
        }
        return recipes;
    }
    public List<Recipe> findMostSavedRecipes() {
        List<Long> recipeIds = recipeDao.findMostSavedRecipeIds();

        List<Recipe> recipes = new ArrayList<>();
        for(Long recipeId : recipeIds) {
            Recipe recipe = recipeDao.findById(recipeId).get();
            if (recipe.getSpoonacularId() != 0){
                CustomRecipe customRecipe = new CustomRecipe(userDao.findByUsername("spoonacular"));
                recipe.setCustom_recipe(customRecipe);
                recipe.setId(recipe.getSpoonacularId());
                recipes.add(recipe);
            } else {
                recipes.add(recipe);
            }
        }
        return recipes;
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
    public DishType getDishTypeById(long id){
        return dishTypeDao.findById(id).get();
    }
    public long getNumberOfSavesByRecipeId(long recipeId){
        return chapterDao.getChaptersBySavedRecipes(recipeDao.findById(recipeId)).size();
    }
    public long getNumberOfSavesByRecipeTitle(String recipeTitle){
        return chapterDao.getChaptersBySavedRecipes(Optional.ofNullable(recipeDao.findByTitle(recipeTitle))).size();
    }
    public void saveReview(Review review){
        reviewDao.save(review);
    }
    public void deleteReview(Review review){
        reviewDao.delete(review);
    }
    public Review getReviewById(long id){
        return reviewDao.findById(id).get();
    }
    public void deleteIngredient(List<Ingredient> ingredients) {
        ingredientDao.deleteAllInBatch(ingredients);
    }
    public void deleteInstruction(List<Instruction> instructions) {
        instructionDao.deleteAllInBatch(instructions);
    }

    public List<Recipe> searchRecipesByTitle(String searchTerm){
        return recipeDao.findRecipesByTitle(searchTerm);
    }

    public List<CustomRecipe> findCustomRecipesBySummary(String searchTerm){
        return customRecipeDao.findCustomRecipesBySummary(searchTerm);
    }

    public void deleteRecipe(Recipe recipe){
        deleteRecentActivity(recentActivityDao.getRecentCreatedActivityByUserIdAndRecipe(recipe.getCustom_recipe().getCreator_id().getId(), recipe.getId()));
        recipeDao.delete(recipe);
    }

    public void deleteRecentActivity(RecentActivity recentActivity){
        recentActivityDao.delete(recentActivity);
    }

    public List<Recipe> findRecipesByDishType(long dishTypeId){
        List<Recipe> recipes = new ArrayList<>();
        for (Long recipeId : recipeDao.findRecipesByDishType(dishTypeId)){
            recipes.add(findRecipeById(recipeId));
        }
        return recipes;
    }

    public DishType getDishTypeByName(String name){
        return dishTypeDao.getDishTypeByType(name);
    }

    public Recipe findRecipeByTitle(String title){
        return recipeDao.findByTitle(title);
    }

    public int getRatingAverageByRecipe(Recipe recipe){
        if (recipe.getReviews().size() != 0) {
            List<Review> reviews = recipe.getReviews();
            int total = 0;
            for (Review review : reviews){
                int rating = review.getRating();
                total += rating;
            }
            return (total / reviews.size());
        } else {
            return 0;
        }
    }

}
