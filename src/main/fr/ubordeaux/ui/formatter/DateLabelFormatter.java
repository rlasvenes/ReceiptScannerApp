package main.fr.ubordeaux.ui.formatter;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Project :
 * Class : DateLabelFormatter
 * Created at 04/03/20 at 16:32
 * Author : rlasvenes
 */

public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
    private String datePattern = "dd/MM/yyyy HH:mm";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    public DateLabelFormatter() {
        dateFormatter.setLenient(false);
    }

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }
}
