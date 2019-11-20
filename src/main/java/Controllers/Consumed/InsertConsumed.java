package Controllers.Consumed;

import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;

@Path("Consumed")
public class InsertConsumed {
    @POST
    @Path("add")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)


    public String InsertConsumed(@FormDataParam("ConsumedID") Integer ConsumedID, @FormDataParam("FoodID") Integer FoodID, @FormDataParam("UserID") Integer UserID,
                                 @FormDataParam("MealType") String MealType, @FormDataParam("PortionSize") Integer PortionSize, @FormDataParam("DateEaten") String DateEaten,
                                 @FormDataParam("HealthyPoints") Integer HealthyPoints) {
        System.out.println("Consumed/add");
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Consumed(ConsumedID, FoodID, UserID, MealType, PortionSize, DateEaten, HealthyPoints) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, ConsumedID);
            ps.setInt(2, FoodID);
            ps.setInt(3, UserID);
            ps.setString(4, MealType);
            ps.setInt(5, PortionSize);
            ps.setString(6, DateEaten);
            ps.setInt(7, HealthyPoints);
            ps.execute();
            return "{\"status\": \"OK\"}";
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
            return "{\"error\": \"Unable to create new item, please see server console for more info.\"}";
        }
    }
}
