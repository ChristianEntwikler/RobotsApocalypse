/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robots.apocalypse.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cenebeli
 */
public class SurvivorRequestDto {
    private String id;
    private String idtype;
    private String name;
    private String age;
    private String gender;
    private String locationLatitude;
    private String locationLongitude;
    private String infectedStatus;
    private List<SurvivorInventoryRequestDto> inventory = new ArrayList<SurvivorInventoryRequestDto>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdtype() {
        return idtype;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(String locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public String getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(String locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public String getInfectedStatus() {
        return infectedStatus;
    }

    public void setInfectedStatus(String infectedStatus) {
        this.infectedStatus = infectedStatus;
    }

    public List<SurvivorInventoryRequestDto> getInventory() {
        return inventory;
    }

    public void setInventory(List<SurvivorInventoryRequestDto> inventory) {
        this.inventory = inventory;
    }
}
