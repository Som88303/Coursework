
package Controllers.Users;


import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;

@Path("Users")
public class InsertUser {
    @POST
    @Path("add")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)

    public String InsertUser(@FormDataParam("UserID") Integer UserID, @FormDataParam("FirstName") String FirstName, @FormDataParam("LastName") String LastName,
                             @FormDataParam("DateOfBirth") String DateOfBirth, @FormDataParam("Gender") String Gender, @FormDataParam("Age") Integer Age,
                             @FormDataParam("Username") String Username, @FormDataParam("Password") String Password) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Users(UserID, FirstName, LastName, DateOfBirth, Gender, Age, Username, Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, UserID);
            ps.setString(2, FirstName);
            ps.setString(3, LastName);
            ps.setString(4, DateOfBirth);
            ps.setString(5, Gender);
            ps.setInt(6, Age);
            ps.setString(7, Username);
            ps.setString(8, Password);
            ps.execute();
            return "{\"status\": \"OK\"}";
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
            return "{\"error\": \"Unable to create new item, please see server console for more info.\"}";
        }
    }

}


