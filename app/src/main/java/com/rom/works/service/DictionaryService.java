package com.rom.works.service;

import com.rom.works.dto.DefinitionResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface DictionaryService {
    @GET("/")
    Call<DefinitionResponse[]> getDefinitionList(@QueryMap Map<String, String> options);
}
