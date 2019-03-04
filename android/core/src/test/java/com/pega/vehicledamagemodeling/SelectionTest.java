package com.pega.vehicledamagemodeling;

import com.pega.vehicledamagemodeling.api.Selection;

import org.junit.Assert;
import org.junit.Test;

public class SelectionTest {

    @Test
    public void testCreate() {
        Selection selection = new Selection("test");
        Assert.assertNotNull(selection);
    }

    @Test
    public void testGetId(){
        String test = "test";
        Selection selection = new Selection(test);
        Assert.assertNotNull(selection.getId());
        Assert.assertEquals(test,selection.getId());
    }
}
