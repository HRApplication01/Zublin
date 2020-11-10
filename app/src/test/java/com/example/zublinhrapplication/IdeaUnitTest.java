package com.example.zublinhrapplication;

import com.example.zublinhrapplication.model.Idea;
import com.example.zublinhrapplication.model.Pending;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class IdeaUnitTest {
    private Idea testIdea;

    @Test
    public void testCreatingIdeaDefault() {
        testIdea = new Idea();
        assertEquals(null, testIdea.getAuthor());
        assertEquals(null, testIdea.getDescription());
        assertEquals(null, testIdea.getFeasibility());
        assertEquals(null, testIdea.getId());
        assertEquals(null, testIdea.getIdeaTitle());
        assertEquals(null, testIdea.getLocationSite());
        assertEquals(null, testIdea.getPending());
        assertEquals(null, testIdea.getProblem());
        assertEquals(null, testIdea.getSolution());
        assertEquals(null, testIdea.getSolutionTriedDesc());
        assertEquals(null, testIdea.getTitle());

        assertEquals(false, testIdea.isAdv1Checked());
        assertEquals(false, testIdea.isAdv2Checked());
        assertEquals(false, testIdea.isAdv3Checked());
        assertEquals(false, testIdea.isAdv4Checked());
        assertEquals(false, testIdea.isMySelf());
        assertEquals(false, testIdea.isOther());
        assertEquals(false, testIdea.isPremium1Checked());
        assertEquals(false, testIdea.isPremium2Checked());
        assertEquals(false, testIdea.isPremium3Checked());
        assertEquals(false, testIdea.isTried1Checked());
        assertEquals(false, testIdea.isTried2Checked());
    }

    @Test
    public void testCreatingIdeaParameters() {
        testIdea = new Idea( true,  true,  true,  true,  "author",  "description",  "feasibility",  Long.valueOf(-1),  "ideaTitle",  "locationSite",  true,  true, Long.valueOf(1),  true,  true,  true,  "problem",  "solution",  "solutionTriedDesc",  "title",  true,  true);
        assertEquals("author", testIdea.getAuthor());
        assertEquals("description", testIdea.getDescription());
        assertEquals("feasibility", testIdea.getFeasibility());
        assertEquals(Long.valueOf(-1), testIdea.getId());
        assertEquals("ideaTitle", testIdea.getIdeaTitle());
        assertEquals("locationSite", testIdea.getLocationSite());
        assertEquals(Long.valueOf(1), testIdea.getPending());
        assertEquals("problem", testIdea.getProblem());
        assertEquals("solution", testIdea.getSolution());
        assertEquals("solutionTriedDesc", testIdea.getSolutionTriedDesc());
        assertEquals("title", testIdea.getTitle());

        assertEquals(true, testIdea.isAdv1Checked());
        assertEquals(true, testIdea.isAdv2Checked());
        assertEquals(true, testIdea.isAdv3Checked());
        assertEquals(true, testIdea.isAdv4Checked());
        assertEquals(true, testIdea.isMySelf());
        assertEquals(true, testIdea.isOther());
        assertEquals(true, testIdea.isPremium1Checked());
        assertEquals(true, testIdea.isPremium2Checked());
        assertEquals(true, testIdea.isPremium3Checked());
        assertEquals(true, testIdea.isTried1Checked());
        assertEquals(true, testIdea.isTried2Checked());
    }

    @Test
    public void testSetAuthor() {
        testIdea = new Idea();
        testIdea.setAuthor("test");
        assertEquals("test", testIdea.getAuthor());
    }

    @Test
    public void testSetDescription() {
        testIdea = new Idea();
        testIdea.setDescription("test");
        assertEquals("test", testIdea.getDescription());
    }

    @Test
    public void testSetFeasibility() {
        testIdea = new Idea();
        testIdea.setFeasibility("test");
        assertEquals("test", testIdea.getFeasibility());
    }

    @Test
    public void testSetId() {
        testIdea = new Idea();
        testIdea.setId((long) 1);
        assertEquals(Long.valueOf(1), testIdea.getId());
    }

    @Test
    public void testSetIdeaTitle() {
        testIdea = new Idea();
        testIdea.setIdeaTitle("test");
        assertEquals("test", testIdea.getIdeaTitle());
    }

    @Test
    public void testSetLocationSite() {
        testIdea = new Idea();
        testIdea.setLocationSite("test");
        assertEquals("test", testIdea.getLocationSite());
    }

    @Test
    public void testSetPending() {
        testIdea = new Idea();
        testIdea.setPending((long) 1);
        assertEquals(Long.valueOf(1), testIdea.getPending());
    }

    @Test
    public void testSetProblem() {
        testIdea = new Idea();
        testIdea.setProblem("test");
        assertEquals("test", testIdea.getProblem());
    }

    @Test
    public void testSetSolution() {
        testIdea = new Idea();
        testIdea.setSolution("test");
        assertEquals("test", testIdea.getSolution());
    }

    @Test
    public void testSetSolutionTriedDesc() {
        testIdea = new Idea();
        testIdea.setSolutionTriedDesc("test");
        assertEquals("test", testIdea.getSolutionTriedDesc());
    }

    @Test
    public void testSetTitle() {
        testIdea = new Idea();
        testIdea.setTitle("test");
        assertEquals("test", testIdea.getTitle());
    }

    @Test
    public void testSetAdv1Checked() {
        testIdea = new Idea();
        testIdea.setAdv1Checked(true);
        assertEquals(true, testIdea.isAdv1Checked());
    }

    @Test
    public void testSetAdv2Checked() {
        testIdea = new Idea();
        testIdea.setAdv2Checked(true);
        assertEquals(true, testIdea.isAdv2Checked());
    }

    @Test
    public void testSetAdv3Checked() {
        testIdea = new Idea();
        testIdea.setAdv3Checked(true);
        assertEquals(true, testIdea.isAdv3Checked());
    }

    @Test
    public void testSetAdv4Checked() {
        testIdea = new Idea();
        testIdea.setAdv4Checked(true);
        assertEquals(true, testIdea.isAdv4Checked());
    }

    @Test
    public void testSetMySelf() {
        testIdea = new Idea();
        testIdea.setMySelf(true);
        assertEquals(true, testIdea.isMySelf());
    }

    @Test
    public void testSetOther() {
        testIdea = new Idea();
        testIdea.setOther(true);
        assertEquals(true, testIdea.isOther());
    }

    @Test
    public void testSetPremium1Checked() {
        testIdea = new Idea();
        testIdea.setPremium1Checked(true);
        assertEquals(true, testIdea.isPremium1Checked());
    }

    @Test
    public void testSetPremium2Checked() {
        testIdea = new Idea();
        testIdea.setPremium2Checked(true);
        assertEquals(true, testIdea.isPremium2Checked());
    }

    @Test
    public void testPremium3Checked() {
        testIdea = new Idea();
        testIdea.setPremium3Checked(true);
        assertEquals(true, testIdea.isPremium3Checked());
    }

    @Test
    public void testSetTried1Checked() {
        testIdea = new Idea();
        testIdea.setTried1Checked(true);
        assertEquals(true, testIdea.isTried1Checked());
    }

    @Test
    public void testSetTried2Checked() {
        testIdea = new Idea();
        testIdea.setTried2Checked(true);
        assertEquals(true, testIdea.isTried2Checked());
    }



}
