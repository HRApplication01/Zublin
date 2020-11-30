package com.example.zublinhrapplication;

import com.example.zublinhrapplication.model.Comment;
import com.example.zublinhrapplication.model.Idea;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class CommentUnitTest {
    private Comment testComment;

    @Test
    public void testCreatingIdeaDefault() {
        testComment  = new Comment();
        assertEquals(null, testComment.getAuthor());
        assertEquals(null, testComment.getComments());
        assertEquals(null, testComment.getIdeaId());
        assertEquals(null, testComment.getId());
    }

    @Test
    public void testCreatingIdeaParameters() {
        testComment = new Comment((long)1, (long)1,"testAuthor", "testComments");
        assertEquals("testAuthor", testComment.getAuthor());
        assertEquals("testComments", testComment.getComments());
        assertEquals(new Long(1), testComment.getIdeaId());
        assertEquals(new Long(1), testComment.getId());
    }

    @Test
    public void testSetAuthor() {
        testComment = new Comment();
        testComment.setAuthor("test");
        assertEquals("test", testComment.getAuthor());
    }

    @Test
    public void testSetCommnets() {
        testComment = new Comment();
        testComment.setComments("test");
        assertEquals("test", testComment.getComments());
    }

    @Test
    public void testSetId() {
        testComment = new Comment();
        testComment.setId(new Long(2));
        assertEquals(new Long(2), testComment.getId());
    }

    @Test
    public void testSetIdeaId() {
        testComment = new Comment();
        testComment.setIdeaId(new Long(2));
        assertEquals(new Long(2), testComment.getIdeaId());
    }





}
