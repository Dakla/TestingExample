package com.example.testingexample.service;

import com.example.testingexample.domain.CoolThing;
import com.example.testingexample.repo.CoolThingRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CoolThingBigServiceImpl implements CoolThingBigService {

    private final CoolThingRepository repository;

    public CoolThingBigServiceImpl(CoolThingRepository repository) {
        this.repository = repository;
    }

    @Override
    public CoolThing findById(Integer id) {
        //Делаем вид, что мы не нашли эту штуковину
        return repository.findById(id).orElse(null);
    }

    @Override
    public Boolean makeCoolThingGreatAgain(Integer id, String name, String desc) {
        //Типо сложная логика, которая нуждается в моках
        return name.length() % 2 == 0;
    }
}
