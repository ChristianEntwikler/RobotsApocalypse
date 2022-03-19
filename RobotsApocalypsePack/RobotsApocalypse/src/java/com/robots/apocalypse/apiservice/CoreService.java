/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robots.apocalypse.apiservice;

import com.robots.apocalypse.db.Dbutil;
import com.robots.apocalypse.dtos.LocationUpdateRequestDto;
import com.robots.apocalypse.dtos.PercentageResponseDto;
import com.robots.apocalypse.dtos.ReportSurvivorRequestDto;
import com.robots.apocalypse.dtos.ResponseDto;
import com.robots.apocalypse.dtos.RobotDto;
import com.robots.apocalypse.dtos.RobotsDto;
import com.robots.apocalypse.dtos.SearchRobotsRequestDto;
import com.robots.apocalypse.dtos.SurvivorInfectedRequestDto;
import com.robots.apocalypse.dtos.SurvivorRequestDto;
import com.robots.apocalypse.enums.AppEnums;
import com.robots.apocalypse.utils.Util;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author cenebeli
 */
public class CoreService {
    
    public ResponseDto InsertSurvivors(SurvivorRequestDto req)
    {
        if((req.getId()!=null && !req.getId().isEmpty()) && (req.getLocationLatitude()!=null && !req.getLocationLatitude().isEmpty()) && (req.getLocationLongitude()!=null && !req.getLocationLongitude().isEmpty()) && (req.getAge()!=null && !req.getAge().isEmpty()) && (req.getGender()!=null && !req.getGender().isEmpty()) && (req.getIdtype()!=null && !req.getIdtype().isEmpty()) && (req.getInfectedStatus()!=null && !req.getInfectedStatus().isEmpty()) && (req.getName()!=null && !req.getName().isEmpty()))
        {
            return new Dbutil().InsertSurvivors(req);
        }
        else
        {
            ResponseDto resp = new ResponseDto();
            resp.setResponseStatus("02");
            resp.setResponseMessage("All values required");
            return resp;
        }
        
    }
    
    public ResponseDto UpdateLocation(LocationUpdateRequestDto req)
    {
        if((req.getId()!=null && !req.getId().isEmpty()) && (req.getLocationLatitude()!=null && !req.getLocationLatitude().isEmpty()) && (req.getLocationLongitude()!=null && !req.getLocationLongitude().isEmpty()))
        {
            return new Dbutil().UpdateLocation(req);
        }
        else
        {
            ResponseDto resp = new ResponseDto();
            resp.setResponseStatus("02");
            resp.setResponseMessage("All values required");
            return resp;
        }
    }
    
    public ResponseDto FlagInfected(SurvivorInfectedRequestDto req)
    {
        if((req.getId()!=null && !req.getId().isEmpty()) && (req.getInfectedStatus()!=null && !req.getInfectedStatus().isEmpty()))
        {
            return new Dbutil().FlagInfected(req);
        }
        else
        {
            ResponseDto resp = new ResponseDto();
            resp.setResponseStatus("02");
            resp.setResponseMessage("All values required");
            return resp;
        }
        
    }
    
    public List<RobotDto> ListRobots()
    {
      RobotsDto resp =  new Util().ListRobots();
      List<RobotDto> ret = new ArrayList<RobotDto>();
      if(resp.getData() != null && resp.getData().size() > 0)
      {
      ret = resp.getData().stream().sorted(Comparator.comparing(RobotDto::getCategory)).collect(Collectors.toList());
      }
      return ret;
    }
    
    public List<RobotDto> SearchRobots(SearchRobotsRequestDto request)
    {
        RobotsDto ret = new Util().ListRobots();
        List<RobotDto> resp = ret.getData().stream().filter(p -> ((request.getCategory()!=null && !request.getCategory().isEmpty()) && p.getCategory().equals(request.getCategory()))).collect(Collectors.toList());
        
        return resp;
    }
    
    public List<ReportSurvivorRequestDto> ReportSurvivors(AppEnums.InfectedStatus status)
    {
        return new Dbutil().ListSurvivors(status);
    }
    
    public PercentageResponseDto PercentageSurvivors(AppEnums.InfectedStatus status)
    {
        return new Dbutil().PercentageSurvivors(status);
    }
}
