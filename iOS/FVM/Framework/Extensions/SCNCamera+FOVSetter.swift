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
import SceneKit

extension SCNCamera {
    public func setFOV(_ value: CGFloat) {
        if #available(iOS 11.0, *) {
            fieldOfView = value
        } else {
            yFov *= Double(value)
        }
    }
    
    public func multiplyFOV(by multiplier: CGFloat) {
        if #available(iOS 11.0, *) {
            fieldOfView *= multiplier
        } else {
            yFov *= Double(multiplier)
        }
    }
    
    public func divideFOV(by divider: CGFloat) {
        if #available(iOS 11.0, *) {
            fieldOfView /= divider
        } else {
            yFov /= Double(divider)
        }
    }
    
    public func truncateFOVOverflow() {
        if #available(iOS 11.0, *) {
            fieldOfView = min(fieldOfView, ZoomConstraint.maxFOV)
            fieldOfView = max(fieldOfView, ZoomConstraint.minFOV)
        } else {
            yFov = min(yFov, Double(ZoomConstraint.maxFOV))
            yFov = max(yFov, Double(ZoomConstraint.minFOV))
        }
    }
    
    public func printFOV() {
        if #available(iOS 11.0, *) {
            if (UIDevice.current.orientation.isLandscape) {
                Log.info("\(fieldOfView) in landscape, after calc: \(fieldOfView * ZoomConstraint.scale)")
            }
            else
            {
                Log.info("\(fieldOfView) in portrait")
            }
        } else {
            if (UIDevice.current.orientation.isLandscape) {
                Log.info("\(yFov) in landscape, after calc: \(yFov * Double(ZoomConstraint.scale))")
            }
            else
            {
                Log.info("\(yFov) in portrait")
            }
        }
    }
}
