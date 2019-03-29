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

public class SelectionService {
    private SelectedPartsRepository selectedPartsRepository = new SelectedPartsRepository();

    public void attachJson(JsonObject json){
        selectedPartsRepository.setInitJson(json);
        selectedPartsRepository.setMainScreenText(Parser.parseToMainScreenText(json));
        for( String s : Parser.parseToSelectedParts(json)){
            setSelectedPart(s);
        }
    }

    public void setSelectedPart(String name){
        //coloring and add to repo
        selectedPartsRepository.add(name,new Material());
        //todo
    }

    public JsonObject getModifiedJson(){
        return Parser.parseToJson(selectedPartsRepository.getMainScreenText(), selectedPartsRepository.getSelectedParts());
    }

    public JsonObject getInitJson(){
        return selectedPartsRepository.getInitJson();
    }

    public String getMainScreenText(){
        return selectedPartsRepository.getMainScreenText();
    }

}
