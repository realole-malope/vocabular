package com.rom.works.listener;

import com.rom.works.dto.DefinitionResponse;

import retrofit2.Call;

public interface DictionaryApiClientEventListener {
    void onSuccess(Call call, DefinitionResponse[] responses);

    void onError(Call call, Throwable t);
}
