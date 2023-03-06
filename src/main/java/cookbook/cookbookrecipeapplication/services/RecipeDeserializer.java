package cookbook.cookbookrecipeapplication.services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import cookbook.cookbookrecipeapplication.models.Recipe;

import java.io.IOException;

public class RecipeDeserializer extends StdDeserializer<Recipe> {

    public RecipeDeserializer() {
        this(null);
    }
    public RecipeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Recipe deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Recipe recipe = new Recipe();
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);

        JsonNode imageNode = node.get("image");
        String image = imageNode.asText();
        recipe.setImage(image);

        JsonNode imageTypeNode = node.get("imageType");
        String imageType = imageTypeNode.asText();
        recipe.setImage_type(imageType);

        JsonNode spoonacularIdNode = node.get("id");
        String spoonacularId = spoonacularIdNode.asText();
        recipe.setSpoonacular_id(Long.parseLong(spoonacularId));

        JsonNode titleNode = node.get("title");
        String title = titleNode.asText();
        recipe.setTitle(title);

        return recipe;
    }
}

