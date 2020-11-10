package com.example.zublinhrapplication;

import junit.framework.TestCase;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CheckAddUserUnitTest extends TestCase {

    protected void setUp() {
    }

    public void testCheckAddUserSuccess() {
        boolean pass = true;
        String nameLocal = "not empty";
        String usernameLocal = "not empty";
        String passwordLocal = "not empty";
        String accountType = "not empty";
        pass = AddAdminUser.approveAddUser(pass, nameLocal, usernameLocal, passwordLocal, accountType);
        assertEquals(true, pass);
    }

    public void testCheckAddUserName() {
        boolean pass = true;
        String nameLocal = "";
        String usernameLocal = "not empty";
        String passwordLocal = "not empty";
        String accountType = "not empty";
        pass = AddAdminUser.approveAddUser(pass, nameLocal, usernameLocal, passwordLocal, accountType);
        assertEquals(false, pass);
    }

    public void testCheckAddUserUsername() {
        boolean pass = true;
        String nameLocal = "not empty";
        String usernameLocal = "";
        String passwordLocal = "not empty";
        String accountType = "not empty";
        pass = AddAdminUser.approveAddUser(pass, nameLocal, usernameLocal, passwordLocal, accountType);
        assertEquals(false, pass);
    }

    public void testCheckAddUserPassword() {

        boolean pass = true;
        String nameLocal = "not empty";
        String usernameLocal = "not empty";
        String passwordLocal = "";
        String accountType = "not empty";
        pass = AddAdminUser.approveAddUser(pass, nameLocal, usernameLocal, passwordLocal, accountType);
        assertEquals(false, pass);
    }

    public void testCheckAddUserAccountType() {

        boolean pass = true;
        String nameLocal = "not empty";
        String usernameLocal = "not empty";
        String passwordLocal = "not empty";
        String accountType = "";
        pass = AddAdminUser.approveAddUser(pass, nameLocal, usernameLocal, passwordLocal, accountType);
        assertEquals(false, pass);
    }
}