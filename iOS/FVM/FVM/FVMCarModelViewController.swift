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
    var highlightedParts = [(node: SCNNode, material: Any?)]()
    
    public func onStartup() {
        self.allowsCameraControl = true
        self.autoenablesDefaultLighting = true
        
        let tapGesture = UITapGestureRecognizer(target: self, action: #selector(handleTapGesture(_:)))
        self.addGestureRecognizer(tapGesture)
        setupScene()
    }

    func setupScene() {
        scnScene = SCNScene(named: "art.scnassets/model.scn")
        scnScene.background.contents = "art.scnassets/background.png"
        self.scene = scnScene
        
        scnCamera = SCNNode()
        scnCamera.camera = SCNCamera()
        scnCamera.position = SCNVector3(x: 0, y: 0, z: 10)
        scnCamera.camera?.zFar = 50
        scnScene.rootNode.addChildNode(scnCamera)
        
        let topLight = SCNNode()
        topLight.light = SCNLight()
        topLight.light?.type = .directional
        topLight.position = SCNVector3(x: 0, y: 50, z: 0)
        topLight.eulerAngles = SCNVector3Make(Float(-Double.pi / 2), 0, 0)
        scnScene.rootNode.addChildNode(topLight)
        
        let bottomLight = SCNNode()
        bottomLight.light = SCNLight()
        bottomLight.light?.type = .directional
        bottomLight.light?.intensity = 750
        bottomLight.position = SCNVector3(x: 0, y: -50, z: 0)
        bottomLight.eulerAngles = SCNVector3Make(Float(-Double.pi / 2), 0, 0)
        scnScene.rootNode.addChildNode(bottomLight)
    }
    
    @objc
    func handleTapGesture(_ gestureRecognizer: UIGestureRecognizer) {
        let p = gestureRecognizer.location(in: self)
        let hitResults = self.hitTest(p, options: [:])
        if hitResults.count > 0 {
            let result = hitResults.first!
            let highlightedNodes = highlightedParts.map { $0.0 }
            if highlightedNodes.contains(result.node) {
                SCNTransaction.begin()
                SCNTransaction.animationDuration = 0.5
                let hitPartIndex = highlightedNodes.firstIndex(of: result.node)
                result.node.geometry?.firstMaterial?.diffuse.contents = highlightedParts[hitPartIndex!].material
                highlightedParts.remove(at: highlightedNodes.firstIndex(of: result.node)!)
                SCNTransaction.commit()
            } else {
                SCNTransaction.begin()
                SCNTransaction.animationDuration = 0.5
                highlightedParts.append((node: result.node, material: result.node.geometry?.firstMaterial?.diffuse.contents))
                result.node.geometry?.firstMaterial?.diffuse.contents = UIColor.red
                SCNTransaction.commit()
            }
        } else {
            setHighlightsOff()
        }
    }
    
    func setHighlightsOff() {
        for tuple in highlightedParts {
            SCNTransaction.begin()
            SCNTransaction.animationDuration = 0.5
            tuple.node.geometry?.firstMaterial?.diffuse.contents = tuple.material
            SCNTransaction.commit()
        }
        highlightedParts.removeAll()
    }
}
