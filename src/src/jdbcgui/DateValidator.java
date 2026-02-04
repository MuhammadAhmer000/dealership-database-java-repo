/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbcgui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @author Ahmer
 */
public class DateValidator {

    public static boolean isValidDateFormat(String inputDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        sdf.setLenient(false);

        try {
            sdf.parse(inputDate);
            return true; // Date is in the expected format
        } catch (ParseException e) {
            return false; // Invalid date format
        }
    }
}
