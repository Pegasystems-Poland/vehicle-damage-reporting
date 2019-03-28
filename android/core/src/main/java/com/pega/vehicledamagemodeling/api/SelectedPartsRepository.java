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

public class SelectedPartsRepository{

    private JsonObject initJson;
    private String mainScreenText;
    private HashMap<String, Material> selectedPartsWithMaterial = new HashMap<>();

    public HashSet<String> getSelectedParts() {
        return new HashSet<>(selectedPartsWithMaterial.keySet());
    }

    public String getMainScreenText(){
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

    public void add(String part, Material material) {
        if(!selectedPartsWithMaterial.containsKey(part)){
            selectedPartsWithMaterial.put(part,material);
        }
    }

    public Material remove(String part) {
        return selectedPartsWithMaterial.remove(part);
    }
}
