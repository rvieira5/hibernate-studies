package com.andcelsode.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "city")
public class City implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id", nullable = false, unique = true)
    private Short id;

    private String city;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    @JsonBackReference
    private Country country;

    public City() {
    }

    public City(Short id, String city, LocalDateTime lastUpdate) {
        this.id = id;
        this.city = city;
        this.lastUpdate = lastUpdate;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
