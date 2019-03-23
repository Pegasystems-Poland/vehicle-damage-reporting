// Copyright 2019 Flying Vehicle Monster team
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

import Foundation

extension ViewController {
    internal func formatDamagedParts() -> String {
        let cleanString = ResultContainer.damagedCarParts.replacingOccurrences(of: " ", with: ";").replacingOccurrences(of: ",", with: ";")
        let partsIdentifiers = cleanString.split(separator: ";")
        var result = ""
        for identifier in partsIdentifiers {
            result.append("{\"id\":\"\(identifier)\"},")
        }
        return String(result.dropLast())
    }
    
    internal func getReadableDamagedParts(_ data: String) -> String? {
        let binaryData = data.data(using: .utf8)
        let selectionRoot = try? JSONDecoder().decode(SelectionRoot.self, from: binaryData!)
        
        var result = ""
        for part in selectionRoot!.selection {
            result.append("\(part.id); ")
        }
        return result
    }
    
    internal func getConfigurationData() -> String {
        ResultContainer.damagedCarParts = partsTextView.text
        ResultContainer.description = descriptionTextView.text
        let damagedParts = formatDamagedParts()
        return """
        {
        "mainScreenText": "\(ResultContainer.description)",
        "selection": [\(damagedParts)]
        }
        """
    }
}
