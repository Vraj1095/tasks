package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int country_id;

    @Column(name = "country_name")
    private String countryName;

    public Country() {
    }


    public Country(String countryName) {
        this.countryName = countryName;
    }


    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int id) {
        this.country_id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String name) {
        this.countryName = name;
    }
}
