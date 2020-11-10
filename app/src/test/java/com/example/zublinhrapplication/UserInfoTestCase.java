package com.example.zublinhrapplication;


import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class UserInfoTestCase{

    @Mock
    Intent mockIntent;

    @Captor
    ArgumentCaptor<String> captorUsername;

    @Captor
    ArgumentCaptor<String> captorName;

    @Test
    public void testMakeUserInfo() {
        when(mockIntent.getStringExtra("username")).thenReturn("testusername");
        when(mockIntent.getStringExtra("name")).thenReturn("testname");

        UserInfo ui = new UserInfo(mockIntent);
        assertEquals("testusername", ui.getUsername());
        assertEquals("testname", ui.getName());
    }

    @Test
    public void testSetUserInfo() {
        UserInfo ui = new UserInfo("testusername", "testname");
        ui.setIntentStrings(mockIntent);

        verify(mockIntent).putExtra(eq("username"), captorUsername.capture());
        verify(mockIntent).putExtra(eq("name"), captorName.capture());
        assertEquals("testusername", captorUsername.getValue());
        assertEquals("testname", captorName.getValue());
    }

}

