package com.example.testingexample.service;

import com.example.testingexample.domain.CoolThing;
import com.example.testingexample.repo.CoolThingRepository;
import org.springframework.stereotype.Service;

@Service
public class CoolThingServiceImpl implements CoolThingService {

    private final CoolThingBigService coolThingBigService;
    private final CoolThingRepository repository;

    public CoolThingServiceImpl(CoolThingBigService coolThingBigService, CoolThingRepository repository) {
        this.coolThingBigService = coolThingBigService;
        this.repository = repository;
    }

    @Override
    public CoolThing create(String name, String desc) {
        CoolThing coolThing = new CoolThing();
        coolThing.setName(name);
        coolThing.setDesc(desc);
        if (coolThingBigService.findById(coolThing.getId()) != null) {
            return null;
        }
        coolThingBigService.makeCoolThingGreatAgain(coolThing.getId(), coolThing.getName(), coolThing.getDesc());
        return repository.save(coolThing);
    }
}
