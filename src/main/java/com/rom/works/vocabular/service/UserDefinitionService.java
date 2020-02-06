package com.rom.works.vocabular.service;

import com.rom.works.vocabular.dto.UserDefinition;
import com.rom.works.vocabular.entity.Definition;
import com.rom.works.vocabular.entity.User;
import com.rom.works.vocabular.exception.UserNotFoundException;
import com.rom.works.vocabular.repository.DefinitionRepository;
import com.rom.works.vocabular.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Optional;

@Log
@Service
public class UserDefinitionService {
    private final UserRepository userRepository;
    private final DefinitionRepository definitionRepository;

    public UserDefinitionService(UserRepository userRepository, DefinitionRepository definitionRepository) {
        this.userRepository = userRepository;
        this.definitionRepository = definitionRepository;
    }

    public UserDefinition save(UserDefinition userDefinition) {
        Definition definition = definitionRepository.findByWordAndExample(userDefinition.getWord(), userDefinition.getExample()).orElse(createDefinition(userDefinition));
        Definition persistedDefinition = definitionRepository.save(definition);
        Optional<User> optionUser = userRepository.findByEmail(userDefinition.getUserEmail());
        optionUser.ifPresent(u -> u.addDefinition(persistedDefinition));
        User user = optionUser.orElse(createUser(userDefinition.getUserEmail(), persistedDefinition));
        userRepository.save(user);
        log.info(MessageFormat.format("{0} saved", userDefinition));
        return userDefinition;
    }

    private Definition createDefinition(UserDefinition userDefinition) {
        return new Definition(userDefinition.getWord(), userDefinition.getMeaning(), userDefinition.getExample());
    }

    private User createUser(String userEmail, Definition definition) {
        User user = new User();
        user.setEmail(userEmail);
        user.addDefinition(definition);
        return user;
    }

    public Collection<Definition> findUserDefinitions(String userId) {
        User user = userRepository.findByEmail(userId).orElseThrow(UserNotFoundException::new);
        return user.getDefinitions();
    }
}
