package com.dojo.codingdojo.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Source {
    private long id;
    private String firstName;
    private String lastName;
    private int age;
}