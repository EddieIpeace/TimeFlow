package com.example.timeflow;

import android.content.SharedPreferences;

import org.litepal.exceptions.ParseConfigurationFileException;
import org.litepal.parser.LitePalAttr;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class SimpleData {
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    SimpleData(SharedPreferences para) {
        pref = para;
        editor = pref.edit();
    }

    public boolean isStart() {
        boolean start = pref.getBoolean("time_start", false);
        return start;
    }
    public void changeStart() {
        boolean start = pref.getBoolean("time_start", false);
        if (start) {
            start = false;
        } else {
            start = true;
        }
        editor.putBoolean("time_start", start);
        editor.apply();
    }
}
