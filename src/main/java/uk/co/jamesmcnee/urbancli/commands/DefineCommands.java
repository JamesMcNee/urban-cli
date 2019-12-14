package uk.co.jamesmcnee.urbancli.commands;

import org.jline.reader.LineReader;
import org.jline.utils.AttributedString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import uk.co.jamesmcnee.urbancli.domain.UrbanDictionaryEntry;
import uk.co.jamesmcnee.urbancli.service.UrbanDictionaryService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;


@ShellComponent
public class DefineCommands {

    private final UrbanDictionaryService urbanDictionaryService;
    private final LineReader lineReader;

    @Autowired
    public DefineCommands(UrbanDictionaryService urbanDictionaryService, @Lazy LineReader lineReader) {
        this.urbanDictionaryService = urbanDictionaryService;
        this.lineReader = lineReader;
    }

    @ShellMethod(value = "Clear all search history", group = "")
    public AttributedString clearHistory() throws IOException {
        lineReader.getHistory().purge();

        return CommandHelper.standardPrefixed("Info", "Command history has now been purged!");
    }

    @ShellMethod(value = "Lookup a word/term and see the definition", key = {"define", "lookup"})
    public List<AttributedString> define(@ShellOption({"--term"}) String term, @ShellOption(value = {"--use-highest-rated"},
            help = "If true will choose the highest rated definition.", defaultValue = "false") boolean useHighestRated) {
        Optional<UrbanDictionaryEntry> dictionaryEntry = urbanDictionaryService.getEntriesForTerm(term).stream().min((a, b) -> {
            if (useHighestRated) {
                return Integer.compare(b.getThumbsUp(), a.getThumbsUp());
            }

            return -1;
        });

        return dictionaryEntry.map(entry -> {
            return asList(
                    CommandHelper.bold(String.format("Here is the definition for %s. Ratings 👍: %d - 👎: %d.", entry.getWord(), entry.getThumbsUp(), entry.getThumbsDown())),
                    CommandHelper.standardPrefixed("Definition", entry.getDefinition()),
                    CommandHelper.standardPrefixed("Example Usage", entry.getExample())
            );
        }).orElse(singletonList(CommandHelper.error(String.format("Could not get a definition for: %s", term))));
    }
}
