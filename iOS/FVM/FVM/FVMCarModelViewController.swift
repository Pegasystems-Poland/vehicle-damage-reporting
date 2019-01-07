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

import UIKit
import SceneKit

open class FVMCarModelViewController : SCNView {
    var scnScene: SCNScene!
    var scnCamera : SCNNode!
    var highlightedParts = [SCNNode]()
    
    public func onStartup() {
     //   self.backgroundColor = UIColor.darkGray
        self.allowsCameraControl = true
        self.autoenablesDefaultLighting = true
        
        let tapGesture = UITapGestureRecognizer(target: self, action: #selector(handleTapGesture(_:)))
        self.addGestureRecognizer(tapGesture)
        setupScene()
    }

    func setupScene() {
        scnScene = SCNScene(named: "art.scnassets/model.scn")
        self.scene = scnScene
        
        scnCamera = SCNNode()
        scnCamera.camera = SCNCamera()
        scnCamera.position = SCNVector3(x: 0, y: 0, z: 10)
        scnCamera.camera?.zFar = 50
        scnScene.rootNode.addChildNode(scnCamera)
    }
    
    @objc
    func handleTapGesture(_ gestureRecognizer: UIGestureRecognizer) {
        let p = gestureRecognizer.location(in: self)
        let hitResults = self.hitTest(p, options: [:])
        if hitResults.count > 0 {
            let result = hitResults.first!
            if highlightedParts.contains(result.node) {
                SCNTransaction.begin()
                SCNTransaction.animationDuration = 0.5
                highlightedParts.remove(at: highlightedParts.firstIndex(of: result.node)!)
                result.node.scale = SCNVector3(x: 1, y: 1, z: 1)
                SCNTransaction.commit()
            } else {
                SCNTransaction.begin()
                SCNTransaction.animationDuration = 0.5
                highlightedParts.append(result.node)
                result.node.scale = SCNVector3(x: 2, y: 2, z: 2)
                SCNTransaction.commit()
            }
        } else {
            setHighlightsOff()
        }
    }
    
    func setHighlightsOff() {
        for part in highlightedParts {
            SCNTransaction.begin()
            SCNTransaction.animationDuration = 0.5
            part.scale = SCNVector3(x: 1, y: 1, z: 1)
            SCNTransaction.commit()
        }
        highlightedParts.removeAll()
    }
    
    func positionCameraAccordingly(xAmount : Int, yAmount : Int, radius : CGFloat) {
        let x : Float = Float(xAmount - 1) * Float(radius)
        let y : Float = Float(yAmount - 1) * Float(radius)
        scnCamera.position = SCNVector3(x: x, y: y, z: 10)
    }
}
