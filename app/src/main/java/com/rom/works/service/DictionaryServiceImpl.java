package com.rom.works.service;

import com.rom.works.client.DictionaryApiClient;
import com.rom.works.dto.DefinitionResponse;
import com.rom.works.listener.DictionaryApiClientEventListener;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DictionaryServiceImpl {
    public void getDefinitionList(String word, final DictionaryApiClientEventListener listener) {
        Retrofit retrofit = DictionaryApiClient.get();
        DictionaryService dictionaryService = retrofit.create(DictionaryService.class);
        Map<String, String> params = new HashMap<>();
        params.put("define", word);
        params.put("lang", "en");
        Call<DefinitionResponse[]> definitionList = dictionaryService.getDefinitionList(params);
        definitionList.enqueue(new Callback<DefinitionResponse[]>() {
            @Override
            public void onResponse(Call<DefinitionResponse[]> call, Response<DefinitionResponse[]> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(call, response.body());
                }
            }

            @Override
            public void onFailure(Call<DefinitionResponse[]> call, Throwable t) {
                listener.onError(call, t);
            }
        });
    }
}
