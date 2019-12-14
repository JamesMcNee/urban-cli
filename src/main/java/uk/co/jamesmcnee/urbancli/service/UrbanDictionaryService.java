package uk.co.jamesmcnee.urbancli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.jamesmcnee.urbancli.domain.UrbanDictionaryEntry;
import uk.co.jamesmcnee.urbancli.repository.UrbanDictionaryRepository;

import java.util.List;

@Service
public class UrbanDictionaryService {

    private final UrbanDictionaryRepository urbanDictionaryRepository;

    @Autowired
    public UrbanDictionaryService(UrbanDictionaryRepository urbanDictionaryRepository) {
        this.urbanDictionaryRepository = urbanDictionaryRepository;
    }

    public List<UrbanDictionaryEntry> getEntriesForTerm(String term) {
        return this.urbanDictionaryRepository.getEntriesForTerm(term);
    }
}
