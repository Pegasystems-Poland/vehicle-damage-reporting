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

    private HashMap<String, Material> partsWithMaterial = new HashMap<>();
    private JsonObject initJson;
    private String mainScreenText;

    public void setInitJson(JsonObject initJson) {
        this.initJson = initJson;
    }

    public void setMainScreenText(String mainScreenText) {
        this.mainScreenText = mainScreenText;
    }

    public void setPartsWithMaterial(HashMap<String, Material> partsWithMaterial) {
        this.partsWithMaterial = partsWithMaterial;
    }

    public void add(String part, Material material) {
        if( !partsWithMaterial.containsKey(part)){
            partsWithMaterial.put(part,material);
        }
    }

    public Material remove(String part) {
        return partsWithMaterial.remove(part);
    }

    public HashSet<String> getAll() {
        HashSet<String> allInHashSet = new HashSet<>();
        allInHashSet.addAll(partsWithMaterial.keySet());
        return allInHashSet;
    }

    public JsonObject getJson(){
        return Parser.parseToJson(this.mainScreenText, this.getAll());
    }

    public String getMainScreenText(){
        return mainScreenText;
    }

    public JsonObject getInitJson() {
        return initJson;
    }
}
