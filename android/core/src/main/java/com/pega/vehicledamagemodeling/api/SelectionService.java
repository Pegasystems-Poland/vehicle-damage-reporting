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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.utils.Array;
import com.google.gson.JsonObject;

public class SelectionService {
    private SelectedPartsRepository selectedPartsRepository;
    private Parser parser;

    private static final Material selectionMaterial = new Material(ColorAttribute.createDiffuse(Color.RED));

    public SelectionService(SelectedPartsRepository selectedPartsRepository, Parser parser) {
        this.selectedPartsRepository = selectedPartsRepository;
        this.parser = parser;
    }

    public void attachJson(JsonObject json, Array<ModelInstance> parts) {
        selectedPartsRepository.setInitJson(json);
        selectedPartsRepository.setMainScreenText(parser.parseToMainScreenText(json));

        for (String partName : parser.parseToSelectedParts(json)) {
            ModelInstance part = getPartByName(partName, parts);
            if (part != null) {
                setSelectedPart(part);
            }
        }
    }

    public void setSelectedPart(ModelInstance part) {
        String partName = getPartName(part);
        Material material;
        if (selectedPartsRepository.contains(partName)) {
            material = selectedPartsRepository.getMaterial(partName);
            setMaterial(part, material);
            selectedPartsRepository.remove(partName);
        } else {
            material = getPartMaterial(part).copy();
            setMaterial(part, selectionMaterial);
            selectedPartsRepository.add(partName, material);
        }
    }

    private void setMaterial(ModelInstance part, Material material) {
        part.materials.get(0).set(material);
    }

    private Material getPartMaterial(ModelInstance part) {
        return part.materials.get(0);
    }

    private String getPartName(ModelInstance part) {
        return part.nodes.get(0).id;
    }

    private ModelInstance getPartByName(String partName, Array<ModelInstance> parts) {
        for (ModelInstance part: parts) {
            if (getPartName(part).equals(partName)) {
                return part;
            }
        }
        return null;
    }

    public JsonObject getModifiedJson() {
        return parser.parseToJson(selectedPartsRepository.getMainScreenText(), selectedPartsRepository.getSelectedParts());
    }

    public JsonObject getInitJson() {
        return selectedPartsRepository.getInitJson();
    }

    public String getMainScreenText() {
        return selectedPartsRepository.getMainScreenText();
    }


}
