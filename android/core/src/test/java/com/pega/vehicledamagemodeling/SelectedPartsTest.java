package com.pega.vehicledamagemodeling;

import com.google.gson.JsonObject;
import com.pega.vehicledamagemodeling.api.Parser;
import com.pega.vehicledamagemodeling.api.SelectedPartsRepository;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class SelectedPartsTest {

    @Test
    public void testRemove(){  //removeNonexistentPart
        //given
        SelectedPartsRepository selectedPartsRepository = new SelectedPartsRepository(new JsonObject(), new Parser());
        boolean expected = false;

        //when
        boolean result = selectedPartsRepository.remove("test");

        //then
        assertEquals(expected,result);
    }

    @Test
    public void testGetAll(){
        //given
        SelectedPartsRepository selectedPartsRepository = new SelectedPartsRepository(new JsonObject(), new Parser());
        ArrayList<String> expected = new ArrayList<>();

        //when
        ArrayList<String> result = selectedPartsRepository.getAll();

        //then
        assertEquals(expected,result);
    }

    @Test
    public void testGetInitJson(){
        //given
        JsonObject jsonObject = new JsonObject();
        SelectedPartsRepository selectedPartsRepository = new SelectedPartsRepository(jsonObject, new Parser());
        JsonObject expected = new JsonObject();

        //when
        JsonObject result = selectedPartsRepository.getInitJson();

        //then
        assertEquals(expected,result);
    }
}
