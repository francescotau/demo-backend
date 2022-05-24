package org.example.rest;

import org.example.business.MainService;
import org.example.model.dto.BasicDto;
import org.example.model.entity.EventEntity;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/main")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MainRestService {

    @Inject
    MainService mainService;

    @GET
    @Path("/test/{owner}")
    public Response test(@PathParam("owner") String owner) {
        EventEntity eventEntity = mainService.test(owner);
        System.out.println(eventEntity);

        BasicDto basicDto = new BasicDto();
        basicDto.setOutcome("OK");
        return Response.ok().entity(basicDto).build();
    }
}
