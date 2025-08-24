package com.dexterv.keycloak.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // getters/setters
    @Setter
    @Getter
    private String name;
    private Integer age;

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}