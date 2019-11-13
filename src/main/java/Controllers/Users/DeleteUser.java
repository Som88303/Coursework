package Controllers.Users;


import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;

public class DeleteUser {
    @POST
    @Path("delete")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)

    public String DeleteUser(@FormDataParam("UserID")Integer UserID) {

        try {
            if (UserID == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("Users/delete" + UserID);
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Users WHERE UserID = ?");
            ps.setInt(1, UserID);
            ps.execute();
            System.out.print("UserID: " + UserID+ " deleted \n");
            return "{\"status\": \"OK\"}";

    } catch (Exception exception) {
        System.out.println("Database error: " + exception.getMessage());
        return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }
    }
}


