package com.rom.works.vocabular.controller;

import com.rom.works.vocabular.dto.DefinitionResponse;
import com.rom.works.vocabular.dto.UserDefinition;
import com.rom.works.vocabular.entity.Definition;
import com.rom.works.vocabular.repository.DictionaryDefinitionClient;
import com.rom.works.vocabular.service.EmailService;
import com.rom.works.vocabular.service.UserDefinitionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RequestMapping(path = "api")
@RestController
public class UserDefinitionRestController {

    private final DictionaryDefinitionClient dictionaryService;
    private final UserDefinitionService userDefinitionService;
    private final EmailService emailService;

    public UserDefinitionRestController(DictionaryDefinitionClient dictionaryService, UserDefinitionService userDefinitionService, EmailService emailService) {
        this.dictionaryService = dictionaryService;
        this.userDefinitionService = userDefinitionService;
        this.emailService = emailService;
    }

    @GetMapping(path = "/dictionary/{word}")
    public DefinitionResponse getDefinition(@PathVariable String word) {
        List<DefinitionResponse> definitions = dictionaryService.getDefinitions(word);
        if (definitions.isEmpty()) return null;
        return definitions.get(0);
    }

    @PostMapping(path = "/")
    public void saveUserDefinition(@RequestBody UserDefinition userDefinition) {
        userDefinitionService.save(userDefinition);
    }

    @GetMapping(path = "/{userId}")
    public Collection<Definition> getUserDefinitions(@PathVariable String userId) {
        return userDefinitionService.findUserDefinitions(userId);
    }

    @PutMapping(path = "/{userId}/{subject}/{message}")
    public void sendEmail(@PathVariable String userId, @PathVariable String subject, @PathVariable String message) {
        emailService.sendSimpleMessage(userId, subject, message);
    }
}
