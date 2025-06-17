
package blog.platforma.resource;

import java.util.List;

import blog.platforma.model.Korisnik;
import blog.platforma.service.KorisnikService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/korisnici")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KorisnikResource {

    @Inject
    KorisnikService service;

    @GET
    public List<Korisnik> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    public Korisnik getById(@PathParam("id") Long id) {
        return service.getById(id);
    }

}
