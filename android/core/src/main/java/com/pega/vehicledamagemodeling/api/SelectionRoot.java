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

import java.util.ArrayList;

public class SelectionRoot {
    private ArrayList<Selection> selections;
    private String mainScreenText;

    public SelectionRoot(ArrayList<Selection> selection, String mainScreenText){
        this.selections = selection;
        this.mainScreenText = mainScreenText;
    }

    public ArrayList<Selection> returnArray(){
        return this.selections;
    }

    public String getMainScreenText() {
        return this.mainScreenText;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof SelectionRoot) {
            SelectionRoot other = (SelectionRoot) obj;
            return selections.equals(other.selections) &&
                    mainScreenText.equals(other.mainScreenText);
        }
        return false;
    }
}
