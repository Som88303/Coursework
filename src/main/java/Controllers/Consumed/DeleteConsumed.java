package Controllers.Consumed;

import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;



@Path("Controllers/Consumed")
public class DeleteConsumed {
    @POST
    @Path("delete")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String DeleteConsumed(@FormDataParam("ConsumedID") Integer ConsumedID) {

        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Consumed WHERE ConsumedID = ?");

            ps.setInt(1, ConsumedID);
            ps.execute();
            System.out.print("ConsumedID: " + ConsumedID+ " deleted \n");
            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }
        }

    }
