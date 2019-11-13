?/*
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Sessions {


    public static void InsertSession(int SessionID, int ExerciseID, int UserID, String DateExercised, int Duration) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Sessions(SessionID, ExerciseID, UserID, DateExercised, Duration) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, SessionID);
            ps.setInt(2, ExerciseID);
            ps.setInt(3, UserID);
            ps.setString(4, DateExercised);
            ps.setInt(5, Duration);
            ps.executeUpdate();
            System.out.println();
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    public static void ListSession() {

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT SessionID, ExerciseID, UserID, DateExercised, Duration FROM Sessions");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int SessionID = results.getInt(1);
                int ExerciseID = results.getInt(2);
                int UserID = results.getInt(3);
                String DateExercised = results.getString(4);
                int Duration = results.getInt(5);
                System.out.print("SessionID: " + SessionID + "\n");
                System.out.print("ExerciseID: " + ExerciseID + "\n");
                System.out.print("UserID: " + UserID + "\n");
                System.out.print("DateExercised: " + DateExercised + "\n");
                System.out.print("Duration: " + Duration + "\n");

            }


        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }


    }

    public static void UpdateSession(int SessionID, int ExerciseID, int UserID, String DateExercised, int Duration) {

        try {

            PreparedStatement ps = Main.db.prepareStatement(
                    "UPDATE Sessions SET ExcerciseID = ?, UserID = ?, DateExcercised = ?, Duration = ?  WHERE SessionID = ?");

            ps.setInt(1, ExerciseID);
            ps.setInt(2, UserID);
            ps.setString(3, DateExercised);
            ps.setInt(4, Duration);
            ps.setInt(5, SessionID);
            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    public static void DeleteSession(int SessionID) {

        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Sessions WHERE SessionID = ?");

            ps.setInt(1, SessionID);
            ps.execute();
            System.out.print("SessionID: " + SessionID+ " deleted \n");

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }

}

 */
