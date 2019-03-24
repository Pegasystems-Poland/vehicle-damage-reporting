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

import com.google.gson.JsonObject;

public class SelectionService {
    private SelectedPartsRepository selectedPartsRepository = new SelectedPartsRepository();

    public JsonObject getModifiedJson(){
        return selectedPartsRepository.getModifiedJson();
    }

    public JsonObject getInitJson(){
        return selectedPartsRepository.getInitJson();
    }

    public String getMainScreenText(){
        return selectedPartsRepository.getMainScreenText();
    }

    public void setSelectedPart(String name){
        //coloring and add to repo
        //to do
    }

    public void attachedJson(JsonObject json){
        //save json in repo
        selectedPartsRepository.setInitJson(json);
        //parse json to (TextAndParts) object
        //save text in repo
        selectedPartsRepository.setMainScreenText(Parser.parseToMainScreenText(json));
        //set parts
        for( String s : Parser.parseToSelectedParts(json)){
            setSelectedPart(s);
        }
    }
}
