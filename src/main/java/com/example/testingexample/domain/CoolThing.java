package com.example.testingexample.domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "cool_thing")
public class CoolThing {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id = 1;

    @Column
    private String name;

    @Column
    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = "Very cool thing description: " + desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoolThing coolThing = (CoolThing) o;

        if (!id.equals(coolThing.id)) return false;
        if (!name.equals(coolThing.name)) return false;
        return desc.equals(coolThing.desc);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + desc.hashCode();
        return result;
    }
}
