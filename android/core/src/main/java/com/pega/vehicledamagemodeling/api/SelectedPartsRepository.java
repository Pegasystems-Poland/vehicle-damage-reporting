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
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.HashSet;

import static com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute.createDiffuse;

public class SelectedPartsRepository {
    private JsonObject initJson;
    private String mainScreenText = "";
    private HashMap<String, Material> selectedPartsWithMaterial = new HashMap<>();
    private static final Material selectionMaterial = new Material(createDiffuse(0.859f,0.447f,0.447f,1));

    public HashSet<String> getSelectedParts() {
        return new HashSet<>(selectedPartsWithMaterial.keySet());
    }

    public String getMainScreenText() {
        return mainScreenText;
    }

    public JsonObject getInitJson() {
        return initJson;
    }

    public void setInitJson(JsonObject initJson) {
        this.initJson = initJson;
    }

    public void setMainScreenText(String mainScreenText) {
        this.mainScreenText = mainScreenText;
    }

    public Material remove(String part) {
        return selectedPartsWithMaterial.remove(part);
    }

    public Material getReverseMaterial(String partName, Material currentMaterial) {
        Material reverseMaterial;
        if (selectedPartsWithMaterial.containsKey(partName)) {
            reverseMaterial = selectedPartsWithMaterial.get(partName);
            selectedPartsWithMaterial.remove(partName);
        } else {
            reverseMaterial = selectionMaterial;
            selectedPartsWithMaterial.put(partName, currentMaterial.copy());
        }
        return reverseMaterial;
    }
}
