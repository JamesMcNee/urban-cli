package uk.co.jamesmcnee.urbancli.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import uk.co.jamesmcnee.urbancli.domain.UrbanDictionaryEntry;
import uk.co.jamesmcnee.urbancli.entity.urbandictionary.UrbanDictionaryEntryEntity;
import uk.co.jamesmcnee.urbancli.entity.urbandictionary.UrbanDictionaryEntryListEntity;
import uk.co.jamesmcnee.urbancli.infrastructure.configuration.UrbanDictionaryConfiguration;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;

@Repository
public class UrbanDictionaryRepository {

    private final RestTemplate restTemplate;
    private final UrbanDictionaryConfiguration urbanDictionaryConfiguration;

    @Autowired
    public UrbanDictionaryRepository(RestTemplate restTemplate, UrbanDictionaryConfiguration urbanDictionaryConfiguration) {
        this.restTemplate = restTemplate;
        this.urbanDictionaryConfiguration = urbanDictionaryConfiguration;
    }

    public List<UrbanDictionaryEntry> getEntriesForTerm(String term) {
        URI uri = UriComponentsBuilder.fromUriString(urbanDictionaryConfiguration.getUrl())
                .pathSegment("v0").pathSegment("define").queryParam("term", term).build().toUri();

        ResponseEntity<UrbanDictionaryEntryListEntity> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, UrbanDictionaryEntryListEntity.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            UrbanDictionaryEntryListEntity body = responseEntity.getBody();

            if (nonNull(body)) {
                return body.getEntries().stream().map(UrbanDictionaryEntryEntity::toDomain).collect(Collectors.toList());
            }
        }

        return emptyList();
    }

}
