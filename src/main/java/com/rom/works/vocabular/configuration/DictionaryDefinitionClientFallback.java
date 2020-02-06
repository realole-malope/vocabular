package com.rom.works.vocabular.configuration;

import com.rom.works.vocabular.dto.DefinitionResponse;
import com.rom.works.vocabular.repository.DictionaryDefinitionClient;
import lombok.extern.java.Log;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

@Log
public class DictionaryDefinitionClientFallback implements DictionaryDefinitionClient {
    @Override
    public List<DefinitionResponse> getDefinitions(String word) {
        log.info(MessageFormat.format("getDefinitions failed for: {0}", word));
        return Collections.emptyList();
    }
}
