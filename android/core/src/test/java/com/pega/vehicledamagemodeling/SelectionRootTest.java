package com.pega.vehicledamagemodeling;

import com.pega.vehicledamagemodeling.api.Selection;
import com.pega.vehicledamagemodeling.api.SelectionRoot;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

public class SelectionRootTest {

    @Test
    public void testCreate() {
        ArrayList<Selection> arrayList = new ArrayList<>();
        String test = "test";
        SelectionRoot selectionRoot = new SelectionRoot(arrayList, test);
        Assert.assertNotNull(selectionRoot);
    }

    @Test
    public void testReturnArray(){
        ArrayList<Selection> arrayList = new ArrayList<>();
        String test = "test";
        SelectionRoot selectionRoot = new SelectionRoot(arrayList, test);
        ArrayList<Selection> arrayTest = selectionRoot.returnArray();
        Assert.assertEquals(arrayList, arrayTest);
    }

    @Test
    public void testGetMainScreenText() {
        ArrayList<Selection> arrayList = new ArrayList<>();
        String test = "test";
        SelectionRoot selectionRoot = new SelectionRoot(arrayList, test);
        String tekstTest = selectionRoot.getMainScreenText();
        Assert.assertEquals(test,tekstTest);

    }
}


