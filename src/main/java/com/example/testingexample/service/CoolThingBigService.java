package com.example.testingexample.service;

import com.example.testingexample.domain.CoolThing;

import java.util.UUID;

public interface CoolThingBigService {
    CoolThing findById(Integer id);

    Boolean makeCoolThingGreatAgain(Integer id, String name, String desc);
}
