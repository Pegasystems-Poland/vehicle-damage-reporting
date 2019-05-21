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

internal class DamagedPartsNamesProvider : DamagedPartsNamesProviderProtocol {
    private var validPartsNames: [String]
    
    init() {
        validPartsNames = [String]()
        let path = Bundle.main.path(forResource: "supported-parts", ofType: "txt")
        if let path = path {
            do {
                let content = try String(contentsOfFile: path).replacingOccurrences(of: "\n", with: "").split(separator: ",")
                for partName in content {
                    validPartsNames.append(String(partName.replacingOccurrences(of: "\"", with: "")))
                }
            } catch {
                Log.error("File couldn't be read: supported-parts.txt")
            }
        } else {
            Log.error("File not found: supported-parts.txt")
        }
        
        Log.info(validPartsNames.last!)
    }
    
    public func getValidNames() -> [String] {
        return validPartsNames
    }
}
