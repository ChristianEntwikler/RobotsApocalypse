/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robots.apocalypse.dtos;

/**
 *
 * @author cenebeli
 */
public class SurvivorInfectedRequestDto {
    private String id;
    private String infectedStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInfectedStatus() {
        return infectedStatus;
    }

    public void setInfectedStatus(String infectedStatus) {
        this.infectedStatus = infectedStatus;
    }
}
