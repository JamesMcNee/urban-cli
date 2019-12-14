package uk.co.jamesmcnee.urbancli.entity.urbandictionary;

import uk.co.jamesmcnee.urbancli.domain.UrbanDictionaryEntry;

import java.time.ZonedDateTime;
import java.util.List;

public class UrbanDictionaryEntryEntity {

    private final String defId;
    private final String word;
    private final String permalink;
    private final String definition;
    private final String example;
    private final int thumbsUp;
    private final int thumbsDown;
    private final List<String> soundUrls;
    private final String author;
    private final String currentVote;
    private final ZonedDateTime writtenOn;

    private UrbanDictionaryEntryEntity(Builder builder) {
        this.defId = builder.defId;
        this.word = builder.word;
        this.permalink = builder.permalink;
        this.definition = builder.definition;
        this.example = builder.example;
        this.thumbsUp = builder.thumbsUp;
        this.thumbsDown = builder.thumbsDown;
        this.soundUrls = builder.soundUrls;
        this.author = builder.author;
        this.currentVote = builder.currentVote;
        this.writtenOn = builder.writtenOn;
    }

    public String getDefId() {
        return defId;
    }

    public String getWord() {
        return word;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getDefinition() {
        return definition;
    }

    public String getExample() {
        return example;
    }

    public int getThumbsUp() {
        return thumbsUp;
    }

    public int getThumbsDown() {
        return thumbsDown;
    }

    public List<String> getSoundUrls() {
        return soundUrls;
    }

    public String getAuthor() {
        return author;
    }

    public String getCurrentVote() {
        return currentVote;
    }

    public ZonedDateTime getWrittenOn() {
        return writtenOn;
    }

    public UrbanDictionaryEntry toDomain() {
        return new UrbanDictionaryEntry.Builder()
                .withDefinitionId(this.defId)
                .withWord(this.word)
                .withPermalink(this.permalink)
                .withDefinition(this.definition)
                .withExample(this.example)
                .withThumbsUp(this.thumbsUp)
                .withThumbsDown(this.thumbsDown)
                .withAudioUrls(this.soundUrls)
                .withAuthor(this.author)
                .withCurrentVote(this.currentVote)
                .withCreatedDate(this.writtenOn)
                .build();
    }

    public static final class Builder {
        private String defId;
        private String word;
        private String permalink;
        private String definition;
        private String example;
        private int thumbsUp;
        private int thumbsDown;
        private List<String> soundUrls;
        private String author;
        private String currentVote;
        private ZonedDateTime writtenOn;

        public Builder withDefId(String defId) {
            this.defId = defId;
            return this;
        }

        public Builder withWord(String word) {
            this.word = word;
            return this;
        }

        public Builder withPermalink(String permalink) {
            this.permalink = permalink;
            return this;
        }

        public Builder withDefinition(String definition) {
            this.definition = definition;
            return this;
        }

        public Builder withExample(String example) {
            this.example = example;
            return this;
        }

        public Builder withThumbsUp(int thumbsUp) {
            this.thumbsUp = thumbsUp;
            return this;
        }

        public Builder withThumbsDown(int thumbsDown) {
            this.thumbsDown = thumbsDown;
            return this;
        }

        public Builder withSoundUrls(List<String> soundUrls) {
            this.soundUrls = soundUrls;
            return this;
        }

        public Builder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder withCurrentVote(String currentVote) {
            this.currentVote = currentVote;
            return this;
        }

        public Builder withWrittenOn(ZonedDateTime writtenOn) {
            this.writtenOn = writtenOn;
            return this;
        }

        public UrbanDictionaryEntryEntity build() {
            return new UrbanDictionaryEntryEntity(this);
        }
    }
}
