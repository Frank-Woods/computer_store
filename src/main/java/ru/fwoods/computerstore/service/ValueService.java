package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fwoods.computerstore.domain.Value;
import ru.fwoods.computerstore.model.Attribute;
import ru.fwoods.computerstore.repository.ValueRepository;

@Service
public class ValueService {
    @Autowired
    private ValueRepository valueRepository;

    public Value save(Attribute attribute) {
        Value valueDomain = valueRepository.findByValue(attribute.getValue());
        if (valueDomain == null) {
            valueDomain = new Value();
            valueDomain.setValue(attribute.getValue());
            valueDomain.setUnit(attribute.getUnit());
            valueDomain = valueRepository.save(valueDomain);
        }
        return valueDomain;
    }

    public void deleteById(Value value) {
        valueRepository.deleteById(value.getId());
    }
}