package com.example.zublinhrapplication;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;

import org.mockito.*;

import com.example.zublinhrapplication.model.Idea;

import junit.framework.TestCase;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UserInfoTestCase extends TestCase {

    @Mock
    Intent mockIntent;

    @Captor
    private ArgumentCaptor<String> captorUsername;

    @Captor
    private ArgumentCaptor<String> captorName;

    public void setUp() {
        mockIntent = mock(Intent.class);
    }

    public void tearDown() {

    }

    public void testMakeUserInfo() {
        when(mockIntent.getStringExtra("username")).thenReturn("testusername");
        when(mockIntent.getStringExtra("name")).thenReturn("testname");

        UserInfo ui = new UserInfo(mockIntent);
        assertEquals("testusername", ui.getUsername());
        assertEquals("testname", ui.getName());
    }

    public void testSetUserInfo() {



    }

}

