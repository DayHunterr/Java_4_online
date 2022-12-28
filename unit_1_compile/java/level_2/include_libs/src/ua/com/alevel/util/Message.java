package ua.com.alevel.util;

import com.diogonunes.jcolor.AnsiFormat;

import static com.diogonunes.jcolor.Attribute.*;

public class Message {

    public void showMessage(String msg) {
        AnsiFormat fInfo = new AnsiFormat(YELLOW_TEXT());
        System.out.println(fInfo.format(msg));
    }
}
