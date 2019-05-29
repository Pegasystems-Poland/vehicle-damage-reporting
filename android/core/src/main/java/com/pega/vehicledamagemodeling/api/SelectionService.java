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
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.utils.Array;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SelectionService {
    private SelectedPartsRepository selectedPartsRepository;
    private Parser parser;
    private List<String> includedParts;

    public SelectionService(SelectedPartsRepository selectedPartsRepository, Parser parser) {
        this.selectedPartsRepository = selectedPartsRepository;
        this.parser = parser;
    }

    public JsonObject getModifiedJson() {
        return parser.parseToJson(
                selectedPartsRepository.getMainScreenText(),
                selectedPartsRepository.getSelectedParts());
    }

    public JsonObject getInitJson() {
        return selectedPartsRepository.getInitJson();
    }

    public String getMainScreenText() {
        return selectedPartsRepository.getMainScreenText();
    }

    public void attachJson(JsonObject json, Array<ModelInstance> parts, String includedPartsPath) {
        selectedPartsRepository.setInitJson(json);
        selectedPartsRepository.setMainScreenText(parser.parseToMainScreenText(json));
        includedParts = getIncludedParts(includedPartsPath);

        for (String partName : parser.parseToSelectedParts(json)) {
            ModelInstance part = getPartByName(partName, parts);
            if (part != null) {
                setSelectedPart(part);
            }
        }
    }

    private List<String> getIncludedParts(String includedPartsPath) {
        String fileText = tryReadFile(includedPartsPath);
        JsonArray jsonArrayIncludedParts = parseToJSONArray(fileText);
        return covertJSONArrayToList(jsonArrayIncludedParts);
    }

    private String tryReadFile(String includedPartsPath) {
        String fileText = "";
        try {
            fileText = readFile(includedPartsPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileText;
    }

    private String readFile(String includedPartsPath) throws IOException {
        InputStream resourceAsStream = SelectionService.class.getResourceAsStream(includedPartsPath);
        return readFromInputStream(resourceAsStream);
    }

    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    private JsonArray parseToJSONArray(String fileText) {
        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(fileText).getAsJsonObject();
        return getJSONArrayIncludedParts(o);
    }

    private JsonArray getJSONArrayIncludedParts(JsonObject jsonObject) {
        return jsonObject.getAsJsonObject("properties")
                .getAsJsonObject("selection")
                .getAsJsonObject("items")
                .getAsJsonObject("properties")
                .getAsJsonObject("id")
                .getAsJsonArray("enum");
    }

    private List<String> covertJSONArrayToList(JsonArray jsonArray) {
        List<String> elements = new ArrayList<>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.size(); i++) {
                elements.add(jsonArray.get(i).getAsString());
            }
        }
        return elements;
    }

    private ModelInstance getPartByName(String partName, Array<ModelInstance> parts) {
        for (ModelInstance part: parts) {
            if (getPartName(part).equals(partName)) {
                return part;
            }
        }
        return null;
    }

    public void setSelectedPart(ModelInstance part) {
        if (part != null) {
            String partName = getPartName(part);
            Material currentMaterial = getPartMaterial(part);
            Material reverseMaterial = selectedPartsRepository.getReverseMaterial(partName, currentMaterial);
            getPartMaterial(part).set(reverseMaterial);
        }
    }

    private String getPartName(ModelInstance part) {
        return getPartMaterial(part).id;
    }

    private Material getPartMaterial(ModelInstance part) {
        return part.materials.get(0);
    }

    public boolean isIncludedPart(ModelInstance selectedPart) {
        return includedParts.contains(selectedPart.materials.get(0).id);
    }
}