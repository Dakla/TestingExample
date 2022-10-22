package com.example.testingexample.repo;

import com.example.testingexample.domain.CoolThing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CoolThingRepository extends JpaRepository<CoolThing, Integer> {
}
