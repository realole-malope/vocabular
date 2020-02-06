package com.rom.works.vocabular.repository;

import com.rom.works.vocabular.configuration.DictionaryDefinitionClientFallback;
import com.rom.works.vocabular.dto.DefinitionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "${feign.dictionary.client.name}", url = "${feign.dictionary.client.url}", fallback = DictionaryDefinitionClientFallback.class)
public interface DictionaryDefinitionClient {
    @GetMapping(params = "define", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<DefinitionResponse> getDefinitions(@RequestParam(value = "define") String word);
}
