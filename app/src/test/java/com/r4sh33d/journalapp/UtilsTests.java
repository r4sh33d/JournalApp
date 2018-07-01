package com.r4sh33d.journalapp;

import com.r4sh33d.journalapp.utility.Utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UtilsTests {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void formatDate() throws Exception {
        String expected = "12 November 2000";
        String actual = Utils.formatDate("2000-11-12");
        assertEquals(expected, actual);
    }
}