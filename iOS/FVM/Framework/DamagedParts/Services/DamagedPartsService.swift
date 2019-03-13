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

internal class DamagedPartsService: DamagedPartsServiceProtocol {
    public var originalConfiguration: String {
        return initialConfiguration ?? ""
    }
    private var parser: JsonParser<SelectionRoot>
    private var validator: DamagedPartsValidator
    private var repository: DamagedPartsRepository
    private var initialSelectionRoot: SelectionRoot?
    private var initialConfiguration: String?
    
    init(parser: JsonParser<SelectionRoot>, validator: DamagedPartsValidator, repository: DamagedPartsRepository) {
        self.parser = parser
        self.validator = validator
        self.repository = repository
    }
    
    public func createAndGetCollectionOfDamagedParts(json: String) -> [Selection] {
        if (initialConfiguration == nil) {
            initialConfiguration = json.replacingOccurrences(of: " ", with: "").replacingOccurrences(of: "\n", with: "")
        }
        createCollectionOfDamagedParts(json: json)
        return getCollectionOfDamagedParts()
    }
    
    public func getCollectionOfDamagedParts() -> [Selection] {
        return repository.getAll()
    }
    
    public func createCollectionOfDamagedParts(json: String) {
        let root = parser.parse(jsonData: json)
        if (initialSelectionRoot == nil) {
            initialSelectionRoot = root
        }
        
        let validated = validator.validate(partsNames: root?.selection ?? [Selection]())
        repository.clear()
        repository.add(selections: validated)
    }
    
    public func addPart(part: Selection) {
        if validator.validate(part: part) != nil {
            repository.add(selection: part)
        }
    }
    
    public func removePart(partId: String) {
        repository.remove(partId: partId)
    }
    
    public func getSerializedParts() -> String {
        return parser.parse(element: SelectionRoot(selectionArray: repository.getAll(), text: initialSelectionRoot?.mainScreenText ?? ""))
    }
}