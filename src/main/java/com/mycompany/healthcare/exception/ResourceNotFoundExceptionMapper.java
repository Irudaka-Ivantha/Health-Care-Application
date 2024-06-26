/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.healthcare.exception;

/**
 *
 * @author Dell
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceNotFoundExceptionMapper.class);

    @Override
    public Response toResponse(ResourceNotFoundException exception) {
        LOGGER.error("ResourceNotFoundException caught: {}", exception.getMessage(), exception);
        
        // Construct a JSON object with the error message
        JsonObject jsonResponse = Json.createObjectBuilder()
                .add("error", exception.getMessage())
                .build();
        
        
        return Response.status(Response.Status.NOT_FOUND)
                       .entity(jsonResponse.toString())
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
}