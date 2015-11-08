package org.bitionaire.epiphany.resources;

import com.google.common.base.Optional;
import lombok.extern.slf4j.Slf4j;
import org.bitionaire.epiphany.model.Idea;
import org.bitionaire.epiphany.persistence.IdeasDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Slf4j
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IdeasResource {

    private final IdeasDAO ideasDAO;

    public IdeasResource(final IdeasDAO ideasDAO) {
        this.ideasDAO = ideasDAO;
    }

    @GET
    @Path("/{id}")
    public Optional<Idea> get(@PathParam("id") final String id) {
        return Optional.fromNullable(ideasDAO.find(id));
    }

}
