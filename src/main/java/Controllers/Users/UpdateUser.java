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
public class UpdateUser {
    @POST
    @Path("update")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String UpdateFood(@FormDataParam("UserID") Integer UserID, @FormDataParam("FirstName") String FirstName, @FormDataParam("LastName") String LastName,
                                  @FormDataParam("DateOfBirth") String DateOfBirth, @FormDataParam("Gender") String Gender, @FormDataParam("Age") Integer Age,
                                  @FormDataParam("Username") String Username, @FormDataParam("Password") String Password) {
        // Takes in parameters given by the user for the corresponding attributes of the table Controllers.Foods
        try {
            if (UserID == null || FirstName == null || LastName == null ||DateOfBirth == null || Gender == null || Age == null || Username == null || Password == null  ) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            PreparedStatement ps = Main.db.prepareStatement(
                    "UPDATE Users SET FirstName = ?, LastName = ?, DateOfBirth = ?, Gender = ?, Age = ?, Username = ?, Password = ?  WHERE UserID = ?");
            // Updates the attributes in the table Controllers.Foods for the corresponding FoodID given by the user
            ps.setString(1, FirstName);
            ps.setString(2, LastName);
            ps.setString(3, DateOfBirth);
            ps.setString(4, Gender);
            ps.setInt(5, Age);
            ps.setString(6, Username);
            ps.setString(7, Password);
            ps.setInt(8, UserID);
            ps.executeUpdate();                               // Finally, sets the given parameters in the database itself
            return "{\"status\": \"OK\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            // If something goes wrong with the code this error message comes up
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";
        }
    }

}
