package uk.co.jamesmcnee.urbancli.domain;

import java.time.ZonedDateTime;
import java.util.List;

public class UrbanDictionaryEntry implements DictionaryEntry {

    private final String definitionId;
    private final String word;
    private final String permalink;
    private final String definition;
    private final String example;
    private final int thumbsUp;
    private final int thumbsDown;
    private final List<String> audioUrls;
    private final String author;
    private final String currentVote;
    private final ZonedDateTime createdDate;

    private UrbanDictionaryEntry(Builder builder) {
        this.definitionId = builder.definitionId;
        this.word = builder.word;
        this.permalink = builder.permalink;
        this.definition = builder.definition;
        this.example = builder.example;
        this.thumbsUp = builder.thumbsUp;
        this.thumbsDown = builder.thumbsDown;
        this.audioUrls = builder.audioUrls;
        this.author = builder.author;
        this.currentVote = builder.currentVote;
        this.createdDate = builder.createdDate;
    }

    public String getDefinitionId() {
        return definitionId;
    }

    public String getWord() {
        return word;
    }

    public String getPermalink() {
        return permalink;
    }

    @Override
    public String getDefinition() {
        return definition;
    }

    @Override
    public String getExample() {
        return example;
    }

    public int getThumbsUp() {
        return thumbsUp;
    }

    public int getThumbsDown() {
        return thumbsDown;
    }

    public List<String> getAudioUrls() {
        return audioUrls;
    }

    public String getAuthor() {
        return author;
    }

    public String getCurrentVote() {
        return currentVote;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public static final class Builder {
        private String definitionId;
        private String word;
        private String permalink;
        private String definition;
        private String example;
        private int thumbsUp;
        private int thumbsDown;
        private List<String> audioUrls;
        private String author;
        private String currentVote;
        private ZonedDateTime createdDate;

        public Builder withDefinitionId(String definitionId) {
            this.definitionId = definitionId;
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

        public Builder withAudioUrls(List<String> audioUrls) {
            this.audioUrls = audioUrls;
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

        public Builder withCreatedDate(ZonedDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public UrbanDictionaryEntry build() {
            return new UrbanDictionaryEntry(this);
        }
    }
}
