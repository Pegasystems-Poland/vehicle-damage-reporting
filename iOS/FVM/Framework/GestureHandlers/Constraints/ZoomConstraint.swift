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
import UIKit

internal class ZoomConstraint {
    private static let minFovBase: CGFloat = 20
    private static let maxFovBase: CGFloat = 70
    
    internal static var minFOV: CGFloat {
        return UIDevice.current.orientation.isLandscape
            ? minFovBase / scale
            : minFovBase
    }
    internal static var maxFOV: CGFloat {
        return UIDevice.current.orientation.isLandscape
            ? maxFovBase / scale
            : maxFovBase
    }
    internal static var scale: CGFloat {
        return UIScreen.main.nativeBounds.height / UIScreen.main.nativeBounds.width
    }
}
