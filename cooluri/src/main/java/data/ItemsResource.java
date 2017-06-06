package data;

import exceptions.Logging;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import restconfig.ResponseGZIP;

@Path("/items")
public class ItemsResource {

	@GET
	@Path("/")
	@Produces({"application/json;charset=UTF-8", "application/xml;charset=UTF-8", "application/rdf+xml;charset=UTF-8", "text/turtle;charset=UTF-8", "text/n3;charset=UTF-8", "application/ld+json;charset=UTF-8", "application/rdf+json;charset=UTF-8"})
	public Response getInfo(@HeaderParam("Accept-Encoding") String acceptEncoding, @HeaderParam("Accept") String acceptHeader) {
		try {
			if (acceptHeader.contains("application/json")) {
				return ResponseGZIP.setResponse(acceptEncoding, "");
			} else if (acceptHeader.contains("application/rdf+json")) {
				return Response.ok().header("Content-Type", "application/rdf+json;charset=UTF-8").build();
			} else if (acceptHeader.contains("text/html")) {
				//URI targetURIForRedirection = new URI("http://linkedgeodesy.org");
				//return Response.seeOther(targetURIForRedirection).build();
				return ResponseGZIP.setResponse(acceptEncoding, "");
			} else if (acceptHeader.contains("application/xml")) {
				return Response.ok("Content-Type", "application/xml;charset=UTF-8").build();
			} else if (acceptHeader.contains("application/rdf+xml")) {
				return Response.ok("Content-Type", "application/rdf+xml;charset=UTF-8").build();
			} else if (acceptHeader.contains("text/turtle")) {
				return Response.ok("Content-Type", "text/turtle;charset=UTF-8").build();
			} else if (acceptHeader.contains("text/n3")) {
				return Response.ok("Content-Type", "text/n3;charset=UTF-8").build();
			} else if (acceptHeader.contains("application/ld+json")) {
				return Response.ok("Content-Type", "application/ld+json;charset=UTF-8").build();
			} else {
				return ResponseGZIP.setResponse(acceptEncoding, "");
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Logging.getMessageJSON(e, "data.ItemsResource"))
					.header("Content-Type", "application/json;charset=UTF-8").build();
		}
	}

	@GET
	@Path("/{resource}")
	@Produces({"application/json;charset=UTF-8", "application/xml;charset=UTF-8", "application/rdf+xml;charset=UTF-8", "text/turtle;charset=UTF-8", "text/n3;charset=UTF-8", "application/ld+json;charset=UTF-8", "application/rdf+json;charset=UTF-8"})
	public Response getInfo2(@HeaderParam("Accept-Encoding") String acceptEncoding, @HeaderParam("Accept") String acceptHeader, @PathParam("resource") String resource) {
		try {
			if (acceptHeader.contains("application/json")) {
				return ResponseGZIP.setResponse(acceptEncoding, "");
			} else if (acceptHeader.contains("application/rdf+json")) {
				return Response.ok().header("Content-Type", "application/rdf+json;charset=UTF-8").build();
			} else if (acceptHeader.contains("text/html")) {
				//URI targetURIForRedirection = new URI("http://linkedgeodesy.org");
				//return Response.seeOther(targetURIForRedirection).build();
				return ResponseGZIP.setResponse(acceptEncoding, "");
			} else if (acceptHeader.contains("application/xml")) {
				return Response.ok("Content-Type", "application/xml;charset=UTF-8").build();
			} else if (acceptHeader.contains("application/rdf+xml")) {
				return Response.ok("Content-Type", "application/rdf+xml;charset=UTF-8").build();
			} else if (acceptHeader.contains("text/turtle")) {
				return Response.ok("Content-Type", "text/turtle;charset=UTF-8").build();
			} else if (acceptHeader.contains("text/n3")) {
				return Response.ok("Content-Type", "text/n3;charset=UTF-8").build();
			} else if (acceptHeader.contains("application/ld+json")) {
				return Response.ok("Content-Type", "application/ld+json;charset=UTF-8").build();
			} else {
				return ResponseGZIP.setResponse(acceptEncoding, "");
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Logging.getMessageJSON(e, "data.ItemsResource"))
					.header("Content-Type", "application/json;charset=UTF-8").build();
		}
	}

}
