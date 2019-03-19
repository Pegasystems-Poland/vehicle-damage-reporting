package com.pega.vehicledamagemodeling;

import com.pega.vehicledamagemodeling.api.PartRoot;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class PartRootTest {

    @Test
    public void whenArrayNullThenGetEmptyList(){
        //given
        ArrayList<String> arrayList = new ArrayList<>();
        String testString = "test";
        PartRoot partRoot = new PartRoot(arrayList, testString);
        ArrayList<String> expected = new ArrayList<>();

        //when
        ArrayList<String> result = partRoot.getParts();

        //then
        assertEquals(expected,result);
    }

    @Test
    public void whenTestNotNullThenReturnCorrectText() {
        //given
        ArrayList<String> arrayList = new ArrayList<>();
        String testString = "test";
        PartRoot partRoot = new PartRoot(arrayList, testString);

        //when
        String result = partRoot.getMainScreenText();

        //then
        assertEquals("test",result);

    }
}


