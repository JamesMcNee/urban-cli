package uk.co.jamesmcnee.urbancli.entity.urbandictionary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import uk.co.jamesmcnee.urbancli.entity.urbandictionary.mapper.UrbanDictionaryListEntityDeserialiser;

import java.util.List;

@JsonDeserialize(using = UrbanDictionaryListEntityDeserialiser.class)
public class UrbanDictionaryEntryListEntity {

    @JsonProperty("list")
    private final List<UrbanDictionaryEntryEntity> entries;

    private UrbanDictionaryEntryListEntity(Builder builder) {
        this.entries = builder.entries;
    }

    public List<UrbanDictionaryEntryEntity> getEntries() {
        return entries;
    }

    public static final class Builder {
        private List<UrbanDictionaryEntryEntity> entries;

        public Builder withEntries(List<UrbanDictionaryEntryEntity> entries) {
            this.entries = entries;
            return this;
        }

        public UrbanDictionaryEntryListEntity build() {
            return new UrbanDictionaryEntryListEntity(this);
        }
    }
}
