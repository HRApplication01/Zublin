package com.example.zublinhrapplication;

import junit.framework.TestCase;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CheckValidityUnitTest extends TestCase {



    protected void setUp() {
    }

    public void testCheckValidityRandom() {
        boolean pass = true;
        boolean mySelf = true;
        boolean other = false;
        String ideaTitle = "not empty";
        String locationSite = "not empty";
        String problem = "not empty";
        String solution = "not empty";
        String feasibility = "not empty";
        String solutionTriedDesc = "not empty";
        boolean adv1Checked = true;
        boolean adv2Checked = true;
        boolean adv3Checked = true;
        boolean adv4Checked = true;
        boolean tried2Checked = true;
        boolean tried1Checked = true;
        boolean premium1Checked = true;
        boolean premium2Checked = false;
        boolean premium3Checked = false;
        pass = EmployeeIdea.checkValidity(pass, mySelf, other, ideaTitle, locationSite, problem, solution, feasibility, solutionTriedDesc, adv1Checked, adv2Checked, adv3Checked, adv4Checked, tried1Checked, tried2Checked, premium1Checked, premium2Checked, premium3Checked);
        assertEquals(true, pass);
    }

    public void testCheckValidityAuthorCheck() {
        boolean pass = true;
        boolean mySelf = false;
        boolean other = false;
        String ideaTitle = "not empty";
        String locationSite = "not empty";
        String problem = "not empty";
        String solution = "not empty";
        String feasibility = "not empty";
        String solutionTriedDesc = "not empty";
        boolean adv1Checked = true;
        boolean adv2Checked = true;
        boolean adv3Checked = true;
        boolean adv4Checked = true;
        boolean tried2Checked = true;
        boolean tried1Checked = true;
        boolean premium1Checked = true;
        boolean premium2Checked = false;
        boolean premium3Checked = false;
        pass = EmployeeIdea.checkValidity(pass, mySelf, other, ideaTitle, locationSite, problem, solution, feasibility, solutionTriedDesc, adv1Checked, adv2Checked, adv3Checked, adv4Checked, tried1Checked, tried2Checked, premium1Checked, premium2Checked, premium3Checked);
        assertEquals(false, pass);
    }

    public void testCheckValidityAdvCheck() {
        boolean pass = true;
        boolean mySelf = true;
        boolean other = false;
        String ideaTitle = "not empty";
        String locationSite = "not empty";
        String problem = "not empty";
        String solution = "not empty";
        String feasibility = "not empty";
        String solutionTriedDesc = "not empty";
        boolean adv1Checked = false;
        boolean adv2Checked = false;
        boolean adv3Checked = false;
        boolean adv4Checked = false;
        boolean tried2Checked = true;
        boolean tried1Checked = true;
        boolean premium1Checked = true;
        boolean premium2Checked = false;
        boolean premium3Checked = false;
        pass = EmployeeIdea.checkValidity(pass, mySelf, other, ideaTitle, locationSite, problem, solution, feasibility, solutionTriedDesc, adv1Checked, adv2Checked, adv3Checked, adv4Checked, tried1Checked, tried2Checked, premium1Checked, premium2Checked, premium3Checked);
        assertEquals(false, pass);
    }

    public void testCheckValidityTriedCheck() {
        boolean pass = true;
        boolean mySelf = true;
        boolean other = false;
        String ideaTitle = "not empty";
        String locationSite = "not empty";
        String problem = "not empty";
        String solution = "not empty";
        String feasibility = "not empty";
        String solutionTriedDesc = "not empty";
        boolean adv1Checked = true;
        boolean adv2Checked = false;
        boolean adv3Checked = false;
        boolean adv4Checked = false;
        boolean tried2Checked = false;
        boolean tried1Checked = false;
        boolean premium1Checked = true;
        boolean premium2Checked = false;
        boolean premium3Checked = false;
        pass = EmployeeIdea.checkValidity(pass, mySelf, other, ideaTitle, locationSite, problem, solution, feasibility, solutionTriedDesc, adv1Checked, adv2Checked, adv3Checked, adv4Checked, tried1Checked, tried2Checked, premium1Checked, premium2Checked, premium3Checked);
        assertEquals(false, pass);
    }

    public void testCheckValidityPremiumCheck() {
        boolean pass = true;
        boolean mySelf = true;
        boolean other = false;
        String ideaTitle = "not empty";
        String locationSite = "not empty";
        String problem = "not empty";
        String solution = "not empty";
        String feasibility = "not empty";
        String solutionTriedDesc = "not empty";
        boolean adv1Checked = true;
        boolean adv2Checked = false;
        boolean adv3Checked = false;
        boolean adv4Checked = false;
        boolean tried2Checked = true;
        boolean tried1Checked = false;
        boolean premium1Checked = false;
        boolean premium2Checked = false;
        boolean premium3Checked = false;
        pass = EmployeeIdea.checkValidity(pass, mySelf, other, ideaTitle, locationSite, problem, solution, feasibility, solutionTriedDesc, adv1Checked, adv2Checked, adv3Checked, adv4Checked, tried1Checked, tried2Checked, premium1Checked, premium2Checked, premium3Checked);
        assertEquals(false, pass);
    }

    public void testCheckValidityEmptyString() {
        boolean pass = true;
        boolean mySelf = true;
        boolean other = false;
        String ideaTitle = "";
        String locationSite = "not empty";
        String problem = "not empty";
        String solution = "not empty";
        String feasibility = "not empty";
        String solutionTriedDesc = "not empty";
        boolean adv1Checked = true;
        boolean adv2Checked = true;
        boolean adv3Checked = true;
        boolean adv4Checked = true;
        boolean tried2Checked = true;
        boolean tried1Checked = true;
        boolean premium1Checked = true;
        boolean premium2Checked = false;
        boolean premium3Checked = false;
        pass = EmployeeIdea.checkValidity(pass, mySelf, other, ideaTitle, locationSite, problem, solution, feasibility, solutionTriedDesc, adv1Checked, adv2Checked, adv3Checked, adv4Checked, tried1Checked, tried2Checked, premium1Checked, premium2Checked, premium3Checked);
        assertEquals(false, pass);
    }
}