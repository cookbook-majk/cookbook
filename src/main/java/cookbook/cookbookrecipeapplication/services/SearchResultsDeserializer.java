package cookbook.cookbookrecipeapplication.services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import cookbook.cookbookrecipeapplication.models.Recipe;
import cookbook.cookbookrecipeapplication.models.SearchResults;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SearchResultsDeserializer extends StdDeserializer<SearchResults> {

    public SearchResultsDeserializer() {
        this(null);
    }

    public SearchResultsDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public SearchResults deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        SearchResults searchResults = new SearchResults();
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);

        // try catch block
        JsonNode offsetNode = node.get("offset");
        String offset = offsetNode.asText();
        searchResults.setOffset(Integer.parseInt(offset));

        JsonNode numberNode = node.get("number");
        String number = numberNode.asText();
        searchResults.setNumber(Integer.parseInt(number));

        List<Recipe> recipes = new ArrayList<>();
        JsonNode resultsNode = node.get("results");
        resultsNode.forEach(recipeNode -> {
            Recipe recipe = new Recipe(
                    recipeNode.get("id").asLong(),
                    recipeNode.get("image").asText(),
                    recipeNode.get("imageType").asText(),
                    new Date(),
                    recipeNode.get("title").asText()
            );
            recipes.add(recipe);
        });

        searchResults.setResults(recipes);

        JsonNode totalResultsNode = node.get("totalResults");
        String totalResults = totalResultsNode.asText();
        searchResults.setTotalResults(Integer.parseInt(totalResults));

        return searchResults;
    }
}

