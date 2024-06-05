package util.dateranges;

import org.json.JSONArray;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RangeCalculatorTest {

    @Test
    public void testCalculateUniqueDaysExcludingWeekends() {
        String jsonString = "[{\"start_date\":\"2024-06-01\", \"end_date\":\"2024-06-10\"}, " +
                            "{\"start_date\":\"2024-06-05\", \"end_date\":\"2024-06-15\"}, " +
                            "{\"start_date\":\"2024-06-20\", \"end_date\":\"2024-06-25\"}]";

        JSONArray periods = new JSONArray(jsonString);
        long result = RangeCalculator.calculateUniqueDays(periods, false, "yyyy-MM-dd");
        assertEquals(14, result); // Expected: 14 weekdays (excluding weekends)
    }

    @Test
    public void testCalculateUniqueDaysIncludingWeekends() {
        String jsonString = "[{\"start_date\":\"2024-06-01\", \"end_date\":\"2024-06-10\"}, " +
                            "{\"start_date\":\"2024-06-05\", \"end_date\":\"2024-06-15\"}, " +
                            "{\"start_date\":\"2024-06-20\", \"end_date\":\"2024-06-25\"}]";

        JSONArray periods = new JSONArray(jsonString);
        long result = RangeCalculator.calculateUniqueDays(periods, true, "yyyy-MM-dd");
        assertEquals(21, result); // Expected: 21 days (including weekends)
    }
}
