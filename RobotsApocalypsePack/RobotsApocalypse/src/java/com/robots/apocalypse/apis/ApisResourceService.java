/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robots.apocalypse.apis;

import com.robots.apocalypse.apiservice.CoreService;
import com.robots.apocalypse.converters.JsonConverter;
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
import com.robots.apocalypse.enums.AppEnums.InfectedStatus;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

/**
 *
 * @author cenebeli
 */
public class ApisResourceService implements ApisResource{
    
    CoreService service = new CoreService();
    
    @Override
    public Response InsertSurvivors(InputStream is, HttpHeaders headers, HttpServletRequest servUrl) {
    
        SurvivorRequestDto request = new SurvivorRequestDto();
        Response.ResponseBuilder build = Response.ok();
        
        if (headers.getMediaType().toString().contains("application/json")) {
            request = JsonConverter.toObj(is, SurvivorRequestDto.class);
        } else {
            build = Response.noContent();
            return build.build(); 
        }

        final ResponseDto resp = service.InsertSurvivors(request);

        StreamingOutput res = new StreamingOutput() {
            public void write(OutputStream outputStream)
                    throws IOException, WebApplicationException {
                    JsonConverter.toJson(outputStream, resp);
            }
        };
        if (headers.getRequestHeader("Accept") != null && headers.getRequestHeader("Accept").get(0).contains("application/xml")) {
            build.type(MediaType.APPLICATION_XML_TYPE);
        } else {
            build.type(MediaType.APPLICATION_JSON_TYPE);
        }
        build.entity(res);

        return build.build();  
    }

    @Override
    public Response UpdateLocation(InputStream is, HttpHeaders headers, HttpServletRequest servUrl) {
        
        LocationUpdateRequestDto request = new LocationUpdateRequestDto();
        Response.ResponseBuilder build = Response.ok();
        
        if (headers.getMediaType().toString().contains("application/json")) {
            request = JsonConverter.toObj(is, LocationUpdateRequestDto.class);
        } else {
            build = Response.noContent();
            return build.build(); 
        }

        final ResponseDto resp = service.UpdateLocation(request);

        StreamingOutput res = new StreamingOutput() {
            public void write(OutputStream outputStream)
                    throws IOException, WebApplicationException {
                    JsonConverter.toJson(outputStream, resp);
            }
        };
        if (headers.getRequestHeader("Accept") != null && headers.getRequestHeader("Accept").get(0).contains("application/xml")) {
            build.type(MediaType.APPLICATION_XML_TYPE);
        } else {
            build.type(MediaType.APPLICATION_JSON_TYPE);
        }
        build.entity(res);

        return build.build(); 
    }

    @Override
    public Response FlagInfected(InputStream is, HttpHeaders headers, HttpServletRequest servUrl) {
        
        SurvivorInfectedRequestDto request = new SurvivorInfectedRequestDto();
        Response.ResponseBuilder build = Response.ok();
        
        if (headers.getMediaType().toString().contains("application/json")) {
            request = JsonConverter.toObj(is, SurvivorInfectedRequestDto.class);
        } else {
            build = Response.noContent();
            return build.build(); 
        }

        final ResponseDto resp = service.FlagInfected(request);

        StreamingOutput res = new StreamingOutput() {
            public void write(OutputStream outputStream)
                    throws IOException, WebApplicationException {
                    JsonConverter.toJson(outputStream, resp);
            }
        };
        if (headers.getRequestHeader("Accept") != null && headers.getRequestHeader("Accept").get(0).contains("application/xml")) {
            build.type(MediaType.APPLICATION_XML_TYPE);
        } else {
            build.type(MediaType.APPLICATION_JSON_TYPE);
        }
        build.entity(res);

        return build.build(); 
    }

    @Override
    public Response ListRobots(HttpHeaders headers, HttpServletRequest servlets) {
        Response.ResponseBuilder build = Response.ok();
        final List<RobotDto> resp = service.ListRobots();

        StreamingOutput res = new StreamingOutput() {
            public void write(OutputStream outputStream)
                    throws IOException, WebApplicationException {
                    JsonConverter.toJson(outputStream, resp);
            }
        };
        if (headers.getRequestHeader("Accept") != null && headers.getRequestHeader("Accept").get(0).contains("application/xml")) {
            build.type(MediaType.APPLICATION_XML_TYPE);
        } else {
            build.type(MediaType.APPLICATION_JSON_TYPE);
        }
        build.entity(res);

        return build.build(); 
    }
    
    @Override
    public Response SearchRobots(InputStream is, HttpHeaders headers, HttpServletRequest servUrl) {
        
        SearchRobotsRequestDto request = new SearchRobotsRequestDto();
        Response.ResponseBuilder build = Response.ok();
        
        if (headers.getMediaType().toString().contains("application/json")) {
            request = JsonConverter.toObj(is, SearchRobotsRequestDto.class);
        } else {
            build = Response.noContent();
            return build.build(); 
        }
        
        final List<RobotDto> resp = service.SearchRobots(request);

        StreamingOutput res = new StreamingOutput() {
            public void write(OutputStream outputStream)
                    throws IOException, WebApplicationException {
                    JsonConverter.toJson(outputStream, resp);
            }
        };
        if (headers.getRequestHeader("Accept") != null && headers.getRequestHeader("Accept").get(0).contains("application/xml")) {
            build.type(MediaType.APPLICATION_XML_TYPE);
        } else {
            build.type(MediaType.APPLICATION_JSON_TYPE);
        }
        build.entity(res);

        return build.build(); 
    }

    @Override
    public Response PercentageInfected(HttpHeaders headers, HttpServletRequest servlets) {
        Response.ResponseBuilder build = Response.ok();
        final PercentageResponseDto resp = service.PercentageSurvivors(InfectedStatus.YES);

        StreamingOutput res = new StreamingOutput() {
            public void write(OutputStream outputStream)
                    throws IOException, WebApplicationException {
                    JsonConverter.toJson(outputStream, resp);
            }
        };
        if (headers.getRequestHeader("Accept") != null && headers.getRequestHeader("Accept").get(0).contains("application/xml")) {
            build.type(MediaType.APPLICATION_XML_TYPE);
        } else {
            build.type(MediaType.APPLICATION_JSON_TYPE);
        }
        build.entity(res);

        return build.build(); 
    }

    @Override
    public Response PercentageNoninfected(HttpHeaders headers, HttpServletRequest servlets) {
        Response.ResponseBuilder build = Response.ok();
        final PercentageResponseDto resp = service.PercentageSurvivors(InfectedStatus.NO);

        StreamingOutput res = new StreamingOutput() {
            public void write(OutputStream outputStream)
                    throws IOException, WebApplicationException {
                    JsonConverter.toJson(outputStream, resp);
            }
        };
        if (headers.getRequestHeader("Accept") != null && headers.getRequestHeader("Accept").get(0).contains("application/xml")) {
            build.type(MediaType.APPLICATION_XML_TYPE);
        } else {
            build.type(MediaType.APPLICATION_JSON_TYPE);
        }
        build.entity(res);

        return build.build(); 
    }

    @Override
    public Response ListInfected(HttpHeaders headers, HttpServletRequest servlets) {
        Response.ResponseBuilder build = Response.ok();
        final List<ReportSurvivorRequestDto> resp = service.ReportSurvivors(InfectedStatus.YES);

        StreamingOutput res = new StreamingOutput() {
            public void write(OutputStream outputStream)
                    throws IOException, WebApplicationException {
                    JsonConverter.toJson(outputStream, resp);
            }
        };
        if (headers.getRequestHeader("Accept") != null && headers.getRequestHeader("Accept").get(0).contains("application/xml")) {
            build.type(MediaType.APPLICATION_XML_TYPE);
        } else {
            build.type(MediaType.APPLICATION_JSON_TYPE);
        }
        build.entity(res);

        return build.build(); 
    }

    @Override
    public Response ListNoninfected(HttpHeaders headers, HttpServletRequest servlets) {
        Response.ResponseBuilder build = Response.ok();
        final List<ReportSurvivorRequestDto> resp = service.ReportSurvivors(InfectedStatus.NO);

        StreamingOutput res = new StreamingOutput() {
            public void write(OutputStream outputStream)
                    throws IOException, WebApplicationException {
                    JsonConverter.toJson(outputStream, resp);
            }
        };
        if (headers.getRequestHeader("Accept") != null && headers.getRequestHeader("Accept").get(0).contains("application/xml")) {
            build.type(MediaType.APPLICATION_XML_TYPE);
        } else {
            build.type(MediaType.APPLICATION_JSON_TYPE);
        }
        build.entity(res);

        return build.build(); 
    }

    
    
}
