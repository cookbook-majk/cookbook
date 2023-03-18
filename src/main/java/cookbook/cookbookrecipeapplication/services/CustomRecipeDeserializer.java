package cookbook.cookbookrecipeapplication.services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import cookbook.cookbookrecipeapplication.models.CustomRecipe;
import cookbook.cookbookrecipeapplication.models.Ingredient;
import cookbook.cookbookrecipeapplication.models.Instruction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomRecipeDeserializer extends StdDeserializer<CustomRecipe> {

    public CustomRecipeDeserializer() {
        this(null);
    }
    public CustomRecipeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public CustomRecipe deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        CustomRecipe customRecipe = new CustomRecipe();
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);

        JsonNode readyInMinutesNode = node.get("readyInMinutes");
        String readyInMinutes = readyInMinutesNode.asText();
        customRecipe.setReadyInMinutes(Long.parseLong(readyInMinutes));

        JsonNode servingsNode = node.get("servings");
        String servings = servingsNode.asText();
        customRecipe.setServings(Integer.parseInt(servings));

        JsonNode summaryNode = node.get("summary");
        String summary = summaryNode.asText();
        customRecipe.setSummary(summary);

        List<Ingredient> ingredients = new ArrayList<>();
        JsonNode ingredientsNode = node.get("extendedIngredients");
        ingredientsNode.forEach(recipeNode -> {
            Ingredient ingredient = new Ingredient(
                    recipeNode.get("originalName").asText(),
                    recipeNode.get("unit").asText(),
                    recipeNode.get("amount").asDouble()
            );
            ingredients.add(ingredient);
        });
        customRecipe.setIngredients(ingredients);

        List<Instruction> instructions = new ArrayList<>();
        JsonNode instructionsNode = node.get("analyzedInstructions");
        JsonNode firstIndexNode = instructionsNode.get(0);
        JsonNode stepsNode = firstIndexNode.get("steps");

        stepsNode.forEach(recipeNode -> {
            Instruction instruction = new Instruction(
                    recipeNode.get("number").asInt(),
                    recipeNode.get("step").asText()
            );
            instructions.add(instruction);
        });
        customRecipe.setInstructions(instructions);

        return customRecipe;
    }
}

