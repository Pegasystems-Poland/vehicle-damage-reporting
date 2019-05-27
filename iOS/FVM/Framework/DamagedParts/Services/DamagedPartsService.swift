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
    internal var originalConfiguration: String {
        return initialConfiguration ?? ""
    }
    internal var originalSelectionRoot: SelectionRoot? {
        return initialSelectionRoot
    }
    private var parser: Serializer<SelectionRoot>
    private var validator: DamagedPartsValidator
    private var repository: DamagedPartsRepository
    private var initialSelectionRoot: SelectionRoot?
    private var initialConfiguration: String?
    
    init(parser: Serializer<SelectionRoot>, validator: DamagedPartsValidator, repository: DamagedPartsRepository) {
        self.parser = parser
        self.validator = validator
        self.repository = repository
    }
    
    internal func createAndGetCollectionOfDamagedParts(json: String) -> [Selection] {
        if (initialConfiguration == nil) {
            initialConfiguration = json
        }
        createCollectionOfDamagedParts(json: json)
        return getCollectionOfDamagedParts()
    }
    
    internal func getCollectionOfDamagedParts() -> [Selection] {
        return repository.getAll()
    }
    
    internal func createCollectionOfDamagedParts(json: String) {
        let root = parser.deserialize(jsonData: json)
        if (initialSelectionRoot == nil) {
            initialSelectionRoot = root
        }
        
        let validated = validator.validate(partsNames: root?.selection ?? [Selection]())
        repository.clear()
        repository.add(selections: validated)
    }
    
    internal func addPart(part: Selection) {
        if validator.validate(part: part) != nil {
            repository.add(selection: part)
        }
    }
    
    internal func removePart(partId: String) {
        repository.remove(partId: partId)
    }
    
    internal func getSerializedParts() -> String {
        return parser.serialize(element: SelectionRoot(selectionArray: repository.getAll(), text: initialSelectionRoot?.mainScreenText ?? ""))
    }
}
