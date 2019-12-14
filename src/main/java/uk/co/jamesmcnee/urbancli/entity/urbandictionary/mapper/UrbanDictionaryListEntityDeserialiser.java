package uk.co.jamesmcnee.urbancli.entity.urbandictionary.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import uk.co.jamesmcnee.urbancli.entity.urbandictionary.UrbanDictionaryEntryEntity;
import uk.co.jamesmcnee.urbancli.entity.urbandictionary.UrbanDictionaryEntryListEntity;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class UrbanDictionaryListEntityDeserialiser extends StdDeserializer<UrbanDictionaryEntryListEntity> {

    public UrbanDictionaryListEntityDeserialiser() {
        this(null);
    }

    public UrbanDictionaryListEntityDeserialiser(Class<?> vc) {
        super(vc);
    }

    @Override
    public UrbanDictionaryEntryListEntity deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = parser.getCodec().readTree(parser);

        UrbanDictionaryEntryListEntity.Builder listEntity = new UrbanDictionaryEntryListEntity.Builder();

        if (node.has("list")) {
            List<UrbanDictionaryEntryEntity> entities = new ArrayList<>();

            node.get("list").forEach(entry -> {
                UrbanDictionaryEntryEntity entryEntity = new UrbanDictionaryEntryEntity.Builder()
                        .withDefId(asText(entry, "defid"))
                        .withWord(asText(entry, "word"))
                        .withPermalink(asText(entry, "permalink"))
                        .withDefinition(asText(entry, "definition"))
                        .withExample(asText(entry, "example"))
                        .withThumbsUp(asInt(entry, "thumbs_up"))
                        .withThumbsDown(asInt(entry, "thumbs_down"))
                        .withSoundUrls(asStringList(entry, "sound_urls"))
                        .withAuthor(asText(entry, "author"))
                        .withCurrentVote(asText(entry, "current_vote"))
                        .withWrittenOn(asZonedDateTime(entry, "written_on"))
                        .build();

                entities.add(entryEntity);
            });

            listEntity.withEntries(entities);
        }

        return listEntity.build();
    }

    private String asText(JsonNode node, String key) {
        return node.has(key) ? node.get(key).asText() : null;
    }

    private int asInt(JsonNode node, String key) {
        return node.has(key) ? node.get(key).asInt() : 0;
    }

    private ZonedDateTime asZonedDateTime(JsonNode node, String key) {
        String dateString = asText(node, key);

        if (nonNull(dateString)) {
            return ZonedDateTime.parse(dateString);
        }

        return null;
    }

    private List<String> asStringList(JsonNode node, String key) {
        List<String> stringList = new ArrayList<>();

        if (node.has(key)) {
            node.get(key).forEach(element -> stringList.add(element.asText()));
        }

        return stringList;
    }
}
