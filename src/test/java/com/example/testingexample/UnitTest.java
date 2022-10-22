package com.example.testingexample;

import com.example.testingexample.domain.CoolThing;
import com.example.testingexample.service.CoolThingBigService;
import com.example.testingexample.service.CoolThingService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
class UnitTest {

    //Просто добавляем сервис в тест
    @Autowired
    private CoolThingService coolThingService;

    //Эмулируем с помощью моков сервис
    @MockBean
    private CoolThingBigService coolThingBigService;

    //Тестируем простое создание крутой штуковины
    @Test
    void create() {
        CoolThing coolThing = coolThingService.create("Top cool thing", "Top cool thing Desc");
        //Простой тест на то, что объект создался
        Assert.assertNotNull(coolThing);

        //Проверяем, что наш мок вызывал 1 раз метод findById
        Mockito.verify(coolThingBigService, Mockito.times(1)).findById(
                ArgumentMatchers.any()
        );
        //Проверяем, что наш мок вызывал 1 раз метод makeCoolThingGreatAgain с опр параметрами
        Mockito.verify(coolThingBigService, Mockito.times(1)).makeCoolThingGreatAgain(
                //Простое соответствие
                ArgumentMatchers.eq(coolThing.getId()),
                //Вообще пофиг на значение, главное чтобы это была строка
                ArgumentMatchers.anyString(),
                //Строка, которая начинается с ...
                ArgumentMatchers.startsWith("Very cool thing description: ")
        );
    }

    @Test
    void createAlreadyCreatedCoolThing() {
        Mockito.doReturn(new CoolThing())
                .when(coolThingBigService)
                .findById(ArgumentMatchers.any());

        CoolThing coolThing = coolThingService.create("Top cool thing", "Top cool thing Desc");

        Assert.assertNull(coolThing);

        //Проверяем, что наш мок вызывал 1 раз метод findById
        Mockito.verify(coolThingBigService, Mockito.times(1)).findById(
                ArgumentMatchers.anyInt()
        );
        //Проверяем, что наш мок вызывал 0 раз метод makeCoolThingGreatAgain, т.к. такая крутая штуковина уже есть
        // и до сюда мы не дошли
        Mockito.verify(coolThingBigService, Mockito.times(0)).makeCoolThingGreatAgain(
                //Простое соответствие
                ArgumentMatchers.any(),
                //Вообще пофиг на значение, главное чтобы это была строка
                ArgumentMatchers.anyString(),
                //Строка, которая начинается с ...
                ArgumentMatchers.anyString()
        );
    }
}