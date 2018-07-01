package com.r4sh33d.journalapp;

import com.r4sh33d.journalapp.utility.Utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExampleUnitTest {

    @Test
    public void formatDate() throws Exception {
        String expected = "12 November 2000";
        String actual = Utils.formatDate("2000-11-12");
        assertEquals(expected, actual);
    }

}
