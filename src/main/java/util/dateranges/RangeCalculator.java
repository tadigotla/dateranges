package util.dateranges;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

public class RangeCalculator {

    /**
     * Calculate the number of days between multiple periods, optionally excluding weekends.
     *
     * @param periods          The JSON array containing date periods.
     * @param includeWeekends  Flag to include or exclude weekends.
     * @param dateFormatStr    The date format. Default is "yyyy-MM-dd".
     * @return                 The number of unique days.
     */
    public static long calculateUniqueDays(JSONArray periods, boolean includeWeekends, String dateFormatStr) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormatStr);

        Set<LocalDate> uniqueDays = new HashSet<>();

        for (int i = 0; i < periods.length(); i++) {
            JSONObject period = periods.getJSONObject(i);
            LocalDate startDate = LocalDate.parse(period.getString("start_date"), dateFormatter);
            LocalDate endDate = LocalDate.parse(period.getString("end_date"), dateFormatter);

            long totalDays = ChronoUnit.DAYS.between(startDate, endDate) + 1; // include the end date

            for (long day = 0; day < totalDays; day++) {
                LocalDate currentDate = startDate.plusDays(day);
                if (includeWeekends || (currentDate.getDayOfWeek().getValue() != 6 && currentDate.getDayOfWeek().getValue() != 7)) {
                    uniqueDays.add(currentDate);
                }
            }
        }

        return uniqueDays.size();
    }

    public static void main(String[] args) {
        if (args.length < 1 || args.length > 2) {
            System.out.println("Usage: java RangeCalculator <json_file_path> [--include-weekends]");
            System.out.println("Assuming the date format is yyyy-MM-dd");
            return;
        }

        String jsonFilePath = args[0];
        boolean includeWeekends = args.length == 2 && args[1].equals("--include-weekends");

        String dateFormat = "yyyy-MM-dd";
        System.out.println("Assuming the date format is " + dateFormat);

        try (FileInputStream fis = new FileInputStream(jsonFilePath)) {
            JSONTokener tokener = new JSONTokener(fis);
            JSONArray periods = new JSONArray(tokener);

            long days = calculateUniqueDays(periods, includeWeekends, dateFormat);
            System.out.println("Number of unique days: " + days);
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }
    }
}
