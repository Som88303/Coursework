/*
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Exercises {

    public static void InsertExercise(int ExerciseID, String ExerciseName, int CalPerHour) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Exercises(ExerciseID, ExerciseName, CalPerHour) VALUES (?, ?, ?)");
            ps.setInt(1, ExerciseID);
            ps.setString(2, ExerciseName);
            ps.setInt(3, CalPerHour);
            ps.executeUpdate();
            System.out.println();
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    public static void ListExercise() {

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT ExerciseID, ExerciseName, CalPerHour FROM Exercises");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int ExerciseID = results.getInt(1);
                String ExerciseName = results.getString(2);
                int CalPerHour = results.getInt(3);
                System.out.print("ExerciseID: " + ExerciseID + "\n");
                System.out.print("ExerciseName: " + ExerciseName + "\n");
                System.out.print("CalPerHour: " + CalPerHour + "\n");

            }


        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }


    }

    public static void UpdateExercise(int ExerciseID, String ExerciseName, int CalPerHour) {

        try {

            PreparedStatement ps = Main.db.prepareStatement(
                    "UPDATE Exercises SET ExerciseName = ? , CalPerHour = ?  WHERE ExerciseID = ?");

            ps.setString(1, ExerciseName);
            ps.setInt(2, CalPerHour);
            ps.setInt(3, ExerciseID);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    public static void DeleteExercise(int ExerciseID) {

        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Exercises WHERE ExerciseID = ?");

            ps.setInt(1, ExerciseID);
            ps.execute();
            System.out.print("ExerciseID: " + ExerciseID+ " deleted \n");

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }



}
*/