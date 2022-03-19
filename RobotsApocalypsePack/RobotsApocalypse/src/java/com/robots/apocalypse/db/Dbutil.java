/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robots.apocalypse.db;

import static com.robots.apocalypse.converters.JsonConverter.toJson;
import com.robots.apocalypse.dtos.LocationUpdateRequestDto;
import com.robots.apocalypse.dtos.PercentageResponseDto;
import com.robots.apocalypse.dtos.ReportSurvivorRequestDto;
import com.robots.apocalypse.dtos.ResponseDto;
import com.robots.apocalypse.dtos.SurvivorInfectedRequestDto;
import com.robots.apocalypse.dtos.SurvivorInventoryRequestDto;
import com.robots.apocalypse.dtos.SurvivorRequestDto;
import com.robots.apocalypse.enums.AppEnums;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

/**
 *
 * @author cenebeli
 */
public class Dbutil {
    
     public ResponseDto InsertSurvivors(SurvivorRequestDto req)
  {
      ResponseDto resp = new ResponseDto();
        Connection conn = null;  
        CallableStatement stmt = null;
        try {
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
            String timestampz=sdf2.format(timestamp2);
            
            ResourceBundle tokenBundle = ResourceBundle.getBundle("config");
            conn = DatabaseResource.getLocalConnection(tokenBundle); 
             if (conn != null) {                   
                stmt = conn.prepareCall("{call proc_insert_survivor(?,?,?,?,?,?,?,?,?)}");
                int i=0;
                stmt.setString(++i, req.getId());
                stmt.setString(++i, req.getIdtype());
                stmt.setString(++i, req.getName());
                stmt.setString(++i, req.getAge());
                stmt.setString(++i, req.getGender());
                stmt.setString(++i, req.getLocationLatitude());
                stmt.setString(++i, req.getLocationLongitude());
                stmt.setString(++i, req.getInfectedStatus());
                stmt.setString(++i, timestampz);
                int ret = stmt.executeUpdate();
                
                if(ret > 0)
                {
                    ResponseDto reinvent = null;
                    if(req.getInventory()!=null && req.getInventory().size() > 0)
                    {
                     reinvent = InsertInventory(req);  
                    }
                    if(reinvent.getResponseStatus()!=null && !reinvent.getResponseStatus().equals("00"))
                    {
                        resp.setResponseStatus(reinvent.getResponseStatus());
                        resp.setResponseMessage(reinvent.getResponseMessage());   
                    }
                    else
                    {
                        resp.setResponseStatus("00");
                        resp.setResponseMessage("Survivor submitted successfully"); 
                    }
                }
             } 
             stmt.close();
        } catch (Exception ex) {
                if(ex.getMessage().contains("duplicate"))
                {
                    resp.setResponseStatus("01");
                    resp.setResponseMessage("Survivor already exist");
                }
                else
                    {
                    resp.setResponseStatus("02");
                    resp.setResponseMessage("Error submitting data");
                }
                Logger.getLogger(Dbutil.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                    if (stmt != null){ try { stmt.close(); stmt=null;} catch (SQLException ex) {Logger.getLogger(Dbutil.class.getName()).log(Level.SEVERE, null, ex);}}
                    if (conn != null){ try { conn.close(); conn = null;} catch (SQLException ex) {Logger.getLogger(Dbutil.class.getName()).log(Level.SEVERE, null, ex);}}              
            }
                return resp;
  }
     
     public ResponseDto InsertInventory(SurvivorRequestDto req)
  {
      ResponseDto resp = new ResponseDto();
      
        Connection conn = null;  
        CallableStatement stmt = null;
        try {
            for(SurvivorInventoryRequestDto reqs : req.getInventory())
            {
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
            String timestampz=sdf2.format(timestamp2);
            
            ResourceBundle tokenBundle = ResourceBundle.getBundle("config");
            conn = DatabaseResource.getLocalConnection(tokenBundle); 
             if (conn != null) {                   
                stmt = conn.prepareCall("{call proc_insert_inventory(?,?,?)}");
                int i=0;
                stmt.setString(++i, reqs.getInventoryName());
                stmt.setString(++i, req.getId());
                stmt.setString(++i, timestampz);
                int ret = stmt.executeUpdate();
                
                if(ret > 0)
                {
                  resp.setResponseStatus("00");
                  resp.setResponseMessage("Survivor Inventory submitted successfully");  
                }
             }
             } 
            stmt.close();
        } catch (Exception ex) {
                    resp.setResponseStatus("02");
                    resp.setResponseMessage("Error saving inventory");               
                Logger.getLogger(Dbutil.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                    if (stmt != null){ try { stmt.close(); stmt=null;} catch (SQLException ex) {Logger.getLogger(Dbutil.class.getName()).log(Level.SEVERE, null, ex);}}
                    if (conn != null){ try { conn.close(); conn = null;} catch (SQLException ex) {Logger.getLogger(Dbutil.class.getName()).log(Level.SEVERE, null, ex);}}              
            }
                return resp;
  }
     
    public ResponseDto UpdateLocation(LocationUpdateRequestDto req)
  {
      ResponseDto resp = new ResponseDto();
      
        Connection conn = null;  
        CallableStatement stmt = null;
        try {
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
            String timestampz=sdf2.format(timestamp2);
            
            ResourceBundle tokenBundle = ResourceBundle.getBundle("config");
            conn = DatabaseResource.getLocalConnection(tokenBundle); 
             if (conn != null) {                   
                stmt = conn.prepareCall("{call proc_update_location(?,?,?,?)}");
                int i=0;
                stmt.setString(++i, req.getLocationLatitude());
                stmt.setString(++i, req.getLocationLongitude());
                stmt.setString(++i, timestampz);
                stmt.setString(++i, req.getId());
                int ret = stmt.executeUpdate();
                
                if(ret > 0)
                {
                        resp.setResponseStatus("00");
                        resp.setResponseMessage("Survivor location updated successfully"); 
                }
             } 
             stmt.close();
        } catch (Exception ex) {
                    resp.setResponseStatus("02");
                    resp.setResponseMessage("Error submitting update");
                Logger.getLogger(Dbutil.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                    if (stmt != null){ try { stmt.close(); stmt=null;} catch (SQLException ex) {Logger.getLogger(Dbutil.class.getName()).log(Level.SEVERE, null, ex);}}
                    if (conn != null){ try { conn.close(); conn = null;} catch (SQLException ex) {Logger.getLogger(Dbutil.class.getName()).log(Level.SEVERE, null, ex);}}              
            }
                return resp;
  }
     
     
    public ResponseDto FlagInfected(SurvivorInfectedRequestDto req)
  {
      ResponseDto resp = new ResponseDto();
      
        Connection conn = null;  
        CallableStatement stmt = null;
        try {
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
            String timestampz=sdf2.format(timestamp2);
            
            ResourceBundle tokenBundle = ResourceBundle.getBundle("config");
            conn = DatabaseResource.getLocalConnection(tokenBundle); 
             if (conn != null) {                   
                stmt = conn.prepareCall("{call proc_update_infectionstatus(?,?,?)}");
                int i=0;
                stmt.setString(++i, req.getId());
                stmt.setString(++i, req.getInfectedStatus());
                stmt.setString(++i, timestampz);
                int ret = stmt.executeUpdate();
                
                if(ret > 0)
                {
                        resp.setResponseStatus("00");
                        resp.setResponseMessage("Survivor infection status updated successfully"); 
                }
             } 
             stmt.close();
        } catch (Exception ex) {
                    resp.setResponseStatus("02");
                    resp.setResponseMessage("Error submitting update");
                Logger.getLogger(Dbutil.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                    if (stmt != null){ try { stmt.close(); stmt=null;} catch (SQLException ex) {Logger.getLogger(Dbutil.class.getName()).log(Level.SEVERE, null, ex);}}
                    if (conn != null){ try { conn.close(); conn = null;} catch (SQLException ex) {Logger.getLogger(Dbutil.class.getName()).log(Level.SEVERE, null, ex);}}              
            }
                return resp;
  }
     
     
     public List<ReportSurvivorRequestDto> ListSurvivors(AppEnums.InfectedStatus status)
  {
      List<ReportSurvivorRequestDto> resp = new ArrayList<ReportSurvivorRequestDto>();
      
        Connection conn = null;  
        CallableStatement stmt = null;
        ResultSet rs = null;
        try {
            ResourceBundle tokenBundle = ResourceBundle.getBundle("config");
            conn = DatabaseResource.getLocalConnection(tokenBundle); 
             if (conn != null) {                   
                stmt = conn.prepareCall("{call proc_listSurvivors(?)}");
                int i=0;
                stmt.setString(++i, status.toString());
                rs = stmt.executeQuery();
                while (rs.next()) {
                    ReportSurvivorRequestDto data = new ReportSurvivorRequestDto();
                    data.setAge(rs.getString("age"));               
                    data.setDateCreated(rs.getString("datecreated"));               
                    data.setGender(rs.getString("gender"));                             
                    data.setId(rs.getString("id"));               
                    data.setIdtype(rs.getString("idtype"));               
                    data.setInfectedStatus(rs.getString("infected"));               
                    data.setLocationLatitude(rs.getString("locationlatitude"));               
                    data.setLocationLongitude(rs.getString("locationlongitude"));               
                    data.setName(rs.getString("fullname"));               
                    resp.add(data);
                }              
             } 
             rs.close();
             stmt.close();
        } catch (SQLException ex) {
                Logger.getLogger(Dbutil.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                    if (rs != null){ try { rs.close(); rs=null;} catch (SQLException ex) {Logger.getLogger(Dbutil.class.getName()).log(Level.SEVERE, null, ex);}}
                    if (stmt != null){ try { stmt.close(); stmt=null;} catch (SQLException ex) {Logger.getLogger(Dbutil.class.getName()).log(Level.SEVERE, null, ex);}}
                    if (conn != null){ try { conn.close(); conn = null;} catch (SQLException ex) {Logger.getLogger(Dbutil.class.getName()).log(Level.SEVERE, null, ex);}}              
            }
                return resp;
  }
     
     public PercentageResponseDto PercentageSurvivors(AppEnums.InfectedStatus status)
  {
      PercentageResponseDto resp = new PercentageResponseDto();
      
        Connection conn = null;  
        CallableStatement stmt = null;
        ResultSet rs = null;
        try {
            ResourceBundle tokenBundle = ResourceBundle.getBundle("config");
            conn = DatabaseResource.getLocalConnection(tokenBundle); 
             if (conn != null) {                   
                stmt = conn.prepareCall("{call proc_percentageSurvivors(?)}");
                int i=0;
                stmt.setString(++i, status.toString());
                rs = stmt.executeQuery();
                while (rs.next()) {
                    resp.setPercentageValue(rs.getString("pvalue"));               
                }              
             } 
             rs.close();
             stmt.close();
        } catch (SQLException ex) {
                Logger.getLogger(Dbutil.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                    if (rs != null){ try { rs.close(); rs=null;} catch (SQLException ex) {Logger.getLogger(Dbutil.class.getName()).log(Level.SEVERE, null, ex);}}
                    if (stmt != null){ try { stmt.close(); stmt=null;} catch (SQLException ex) {Logger.getLogger(Dbutil.class.getName()).log(Level.SEVERE, null, ex);}}
                    if (conn != null){ try { conn.close(); conn = null;} catch (SQLException ex) {Logger.getLogger(Dbutil.class.getName()).log(Level.SEVERE, null, ex);}}              
            }
                return resp;
  }
     
}
