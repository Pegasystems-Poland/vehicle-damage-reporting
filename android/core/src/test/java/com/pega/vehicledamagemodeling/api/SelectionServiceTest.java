/*
 * Copyright (c) 2019 Flying Vehicle Monster team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pega.vehicledamagemodeling.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SelectionServiceTest {
    private Parser parser;
    private SelectedPartsRepository selectedPartsRepository;
    private static final String SELECTION = "selection";
    private static final String ID = "id";
    private static final String MAIN_SCREEN_TEXT = "mainScreenText";
    private static final String NOTHING = "nothing";
    private static final String ROOF = "roof";
    private static final String FRONT_BUMPER = "front bumper";

    @Before
    public void setUp() {
        parser = new Parser();
        selectedPartsRepository = mock(SelectedPartsRepository.class);
    }

    @Test
    public void whenJsonContainsTwoPartsThenReturnCorrectInitJson() {
        //given
        JsonObject expectedJson = new JsonObject();
        JsonArray partsArray = new JsonArray();
        JsonObject jsonProperty = new JsonObject();
        jsonProperty.addProperty(ID, ROOF);
        partsArray.add(jsonProperty);
        JsonObject jsonProperty2 = new JsonObject();
        jsonProperty2.addProperty(ID, FRONT_BUMPER);
        partsArray.add(jsonProperty2);
        expectedJson.add(SELECTION, partsArray);
        when(selectedPartsRepository.getInitJson()).thenReturn(expectedJson);
        SelectionService selectionService = new SelectionService(selectedPartsRepository, parser);

        //when
        JsonObject result = selectionService.getInitJson();

        //then
        assertEquals(expectedJson, result);
    }

    @Test
    public void whenJsonIsEmptyThenReturnEmptyInitJson(){
        //given
        JsonObject initJson = new JsonObject();
        when(selectedPartsRepository.getInitJson()).thenReturn(initJson);
        SelectionService selectionService = new SelectionService(selectedPartsRepository, parser);

        //when
        JsonObject result = selectionService.getInitJson();

        //then
        assertEquals(initJson, result);
    }

    @Test
    public void whenInitJsonContainTextThenReturnCorrectText() {
        //given
        JsonObject initJson = new JsonObject();
        initJson.addProperty(MAIN_SCREEN_TEXT, NOTHING);
        when(selectedPartsRepository.getMainScreenText()).thenReturn(NOTHING);
        SelectionService selectionService = new SelectionService(selectedPartsRepository, parser);

        //when
        String result = selectionService.getMainScreenText();

        //then
        assertEquals(NOTHING, result);
    }

    @Test
    public void whenJsonIsEmptyAndNewPartIsAddedThenReturnModifiedJson(){
        //given
        JsonObject initJson = new JsonObject();
        JsonObject expectedJson = new JsonObject();
        JsonArray partsArray = new JsonArray();
        JsonObject jsonProperty = new JsonObject();
        jsonProperty.addProperty(ID, ROOF);
        partsArray.add(jsonProperty);
        expectedJson.addProperty(MAIN_SCREEN_TEXT, "");
        expectedJson.add(SELECTION, partsArray);
        SelectionService selectionService = new SelectionService(new SelectedPartsRepository(), parser);
        selectionService.attachJson(initJson);
        selectionService.setSelectedPart(ROOF);

        //when
        JsonObject result = selectionService.getModifiedJson();

        //then
        assertEquals(expectedJson, result);
    }
}