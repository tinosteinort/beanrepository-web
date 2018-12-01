package com.github.tinosteinort.beanrepository.web.dispatcher;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PathMatcherTest {

    private PathMatcher matcher = new PathMatcher();

    @Test public void matches() {

        assertTrue(matcher.pathMatches("path", "/path/"));
        assertTrue(matcher.pathMatches("/path", "/path/"));
        assertTrue(matcher.pathMatches("/path/", "/path/"));
    }

    @Test public void normalise() {

        assertEquals("", matcher.normalise(null));
        assertEquals("", matcher.normalise(""));
        assertEquals("", matcher.normalise("/"));
        assertEquals("path", matcher.normalise("path"));
        assertEquals("path", matcher.normalise("/path"));
        assertEquals("path", matcher.normalise("/path/"));
        assertEquals("segment1/segment2", matcher.normalise("/segment1/segment2"));
        assertEquals("segment1/segment2", matcher.normalise("/segment1/segment2/"));
    }

    @Test public void asArray() {

        assertArrayEquals(new String[0], matcher.asArray(null));
        assertArrayEquals(new String[0], matcher.asArray(""));
        assertArrayEquals(new String[0], matcher.asArray("    "));
        assertArrayEquals(new String[] { "path" }, matcher.asArray("path"));
        assertArrayEquals(new String[] { "segment1", "segment2" }, matcher.asArray("segment1/segment2"));
        assertArrayEquals(new String[] { "segment1", "segment2" }, matcher.asArray("segment1/segment2/"));
        assertArrayEquals(new String[] { "segment1", "segment2" }, matcher.asArray("segment1/segment2/  "));
    }
}