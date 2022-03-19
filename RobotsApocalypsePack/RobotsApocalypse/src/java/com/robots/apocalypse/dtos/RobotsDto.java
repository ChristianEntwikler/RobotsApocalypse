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
public class RobotsDto {
    private List<RobotDto> data = new ArrayList<RobotDto>();

    public List<RobotDto> getData() {
        return data;
    }

    public void setData(List<RobotDto> data) {
        this.data = data;
    }
}
