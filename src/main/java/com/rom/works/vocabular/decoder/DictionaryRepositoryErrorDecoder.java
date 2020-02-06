package com.rom.works.vocabular.decoder;

import com.rom.works.vocabular.exception.BusinessException;
import com.rom.works.vocabular.exception.DefinitionNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.java.Log;

@Log
public class DictionaryRepositoryErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        if (response.status() == 404 || response.status() == 204) {
            return new DefinitionNotFoundException();
        }
        return new BusinessException("Oops, something went wrong");
    }
}
