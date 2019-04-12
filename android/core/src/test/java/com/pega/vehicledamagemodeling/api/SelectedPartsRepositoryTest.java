// Copyright 2018 Flying Vehicle Monster team
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.pega.vehicledamagemodeling.api;

import com.badlogic.gdx.graphics.g3d.Material;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class SelectedPartsRepositoryTest {
    private SelectedPartsRepository selectedPartsRepository;
    private static final String SELECTION = "selection";
    private static final String ID = "id";
    private static final String ROOF = "roof";
    private static final String FRONT_BUMPER = "front bumper";
    private static final String TEST = "test";
    private static final String SAMPLE_TEXT = "the text";

    @Before
    public void setUp() {
        selectedPartsRepository = new SelectedPartsRepository();
    }

    @Test
    public void whenPartDoesNotExistThenReturnNull(){
        //given
        JsonObject initJson = new JsonObject();
        JsonArray partsArray = new JsonArray();
        JsonObject jsonProperty = new JsonObject();
        jsonProperty.addProperty(ID, ROOF);
        partsArray.add(jsonProperty);
        JsonObject jsonProperty2 = new JsonObject();
        jsonProperty2.addProperty(ID,FRONT_BUMPER);
        partsArray.add(jsonProperty2);
        initJson.add(SELECTION, partsArray);
        selectedPartsRepository.setInitJson(initJson);
        Material expected = null;

        //when
        Material result = selectedPartsRepository.remove(TEST);

        //then
        assertEquals(expected, result);
    }

    @Test
    public void whenSelectionContainsTwoPartsThenReturnCorrectParts() {
        //given
        selectedPartsRepository.add(ROOF, new Material());
        selectedPartsRepository.add(FRONT_BUMPER, new Material());

        HashSet<String> expected = new HashSet<>();
        expected.add(ROOF);
        expected.add(FRONT_BUMPER);

        //when
        HashSet<String> result = selectedPartsRepository.getSelectedParts();

        //then
        assertEquals(expected, result);
    }

    @Test
    public void whenSelectionIsEmptyThenReturnEmptyHashSet(){
        //given
        HashSet<String> expected = new HashSet<>();

        //when
        HashSet<String> result = selectedPartsRepository.getSelectedParts();

        //then
        assertEquals(expected, result);
    }

    @Test
    public void whenJsonIsEmptyThenReturnEmptyJson(){
        //given
        JsonObject initJson = new JsonObject();
        selectedPartsRepository.setInitJson(initJson);

        //when
        JsonObject result = selectedPartsRepository.getInitJson();

        //then
        assertEquals(initJson, result);
    }

    @Test
    public void whenJsonIsNotEmptyThenReturnCorrectJson(){
        //given
        JsonObject initJson = new JsonObject();
        JsonArray partsArray = new JsonArray();
        JsonObject jsonProperty = new JsonObject();
        jsonProperty.addProperty(ID, ROOF);
        partsArray.add(jsonProperty);
        initJson.add(SELECTION, partsArray);
        selectedPartsRepository.setInitJson(initJson);

        //when
        JsonObject result = selectedPartsRepository.getInitJson();

        //then
        assertEquals(initJson, result);
    }

    @Test
    public void whenTextIsNotEmptyThenReturnCorrectText(){
        //given
        selectedPartsRepository.setMainScreenText(SAMPLE_TEXT);
        String expected = SAMPLE_TEXT;

        //when
        String result = selectedPartsRepository.getMainScreenText();

        //then
        assertEquals(expected, result);
    }

    @Test
    public void whenTextIsEmptyThenReturnEmptyText(){
        //given
        selectedPartsRepository.setMainScreenText("");

        //when
        String result = selectedPartsRepository.getMainScreenText();

        //then
        assertEquals("", result);
    }

    @Test
    public void whenPartExistThenDoNotAdd(){
        //given
        selectedPartsRepository.add(ROOF, new Material());
        selectedPartsRepository.add(ROOF, new Material());
        HashSet<String> expected = new HashSet<>();
        expected.add(ROOF);

        //when
        HashSet<String> result = selectedPartsRepository.getSelectedParts();

        //then
        assertEquals(expected, result);
    }

    @Test
    public void whenPartDoesNotExistThenAdd(){
        //given
        selectedPartsRepository.add(ROOF, new Material());
        HashSet<String> expected = new HashSet<>();
        expected.add(ROOF);

        //when
        HashSet<String> result = selectedPartsRepository.getSelectedParts();

        //then
        assertEquals(expected, result);
    }
}
