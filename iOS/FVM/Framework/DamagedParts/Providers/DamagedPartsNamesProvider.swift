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
    private static let validPartsNames = [ "bumper-front",
                                           "bumper-rear",
                                           "door-front-left",
                                           "door-front-right",
                                           "door-rear-left",
                                           "door-rear-right",
                                           "fender-front-left",
                                           "fender-front-right",
                                           "fender-rear-left",
                                           "fender-rear-right",
                                           "hood",
                                           "lamp-front-right",
                                           "lamp-rear-left",
                                           "lamp-rear-right",
                                           "lamp-front-left",
                                           "roof",
                                           "pillar-left",
                                           "pillar-right",
                                           "trunk",
                                           "wheel-front-left",
                                           "wheel-front-right",
                                           "wheel-rear-left",
                                           "wheel-rear-right",
                                           "window-front",
                                           "window-front-left",
                                           "window-front-right",
                                           "window-rear",
                                           "window-rear-left",
                                           "window-rear-right"]
    
    public func getValidNames() -> [String] {
        return DamagedPartsNamesProvider.validPartsNames
    }
}
