package brandon.cs157.restful.orderApplication;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/rest")
public class Restful 
{
	@Path("/title")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	
	public String returnTitle()
	{
		return "Hellow from Restful.java";
	}
	

}
