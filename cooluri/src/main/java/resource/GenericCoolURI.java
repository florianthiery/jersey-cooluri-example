package resource;

import exceptions.Logging;
import java.net.URI;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import utils.config.ConfigProperties;

@Path("/{collection}/{resource}")
public class GenericCoolURI {

    @GET
	@Produces({"application/json;charset=UTF-8", "application/xml;charset=UTF-8", "application/rdf+xml;charset=UTF-8", "text/turtle;charset=UTF-8", "text/n3;charset=UTF-8", "application/ld+json;charset=UTF-8", "application/rdf+json;charset=UTF-8"})
    public Response get(@HeaderParam("Accept-Encoding") String acceptEncoding, @HeaderParam("Accept") String acceptHeader, @PathParam("collection") String collection, @PathParam("resource") String resource) {
        try {
			URI targetURIForRedirection = new URI(ConfigProperties.getPropertyParam("API_HOST").replace("*", collection + "/" + resource));
			return Response.seeOther(targetURIForRedirection).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Logging.getMessageJSON(e, "data.DefaultPath"))
                    .header("Content-Type", "application/json;charset=UTF-8").build();
        }
    }

}
