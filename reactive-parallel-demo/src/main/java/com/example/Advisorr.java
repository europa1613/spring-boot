package com.example;

import lombok.*;

@Getter
@Setter
@ToString
public class Advisorr {
    private String id;
    private String firstname;
    private String lastname;
    private String dateOfBirth;

    public Advisorr(String id) {
        this.id = id;
    }

}
