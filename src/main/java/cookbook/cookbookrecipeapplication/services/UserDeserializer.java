package cookbook.cookbookrecipeapplication.services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import cookbook.cookbookrecipeapplication.models.Recipe;
import cookbook.cookbookrecipeapplication.models.User;

import java.io.IOException;

public class UserDeserializer extends StdDeserializer<User> {

    public UserDeserializer() {
        this(null);
    }
    public UserDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public User deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User();
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);

        JsonNode sourceNameNode = node.get("sourceName");
        String sourceName = sourceNameNode.asText();
        user.setUsername(sourceName);

        JsonNode sourceURLNode = node.get("sourceUrl");
        String sourceURL = sourceURLNode.asText();
        user.setUserBio(sourceURL);

        user.setProfilePicture("https://spoonacular.com/images/spoonacular-logo-b.svg");

        return user;
    }
}

