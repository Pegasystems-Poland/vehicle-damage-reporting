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

import XCTest
import SceneKit
@testable import FVM

class ModelScaleProviderTests: XCTestCase {
    func testIfCorrectScaleReturnedWhenBoxIsScaledByOne() {
        let sut = ModelScaleProvider(makeBox(withScale: 1))
        
        let actual = sut.getModelScale()
        
        XCTAssertEqual(0.054033345583028, actual, accuracy: 0.0001)
    }
    
    func testIfCorrectScaleReturnedWhenPyramidIsScaledByOne() {
        let sut = ModelScaleProvider(makePyramid(withScale: 1))
        
        let actual = sut.getModelScale()
        
        XCTAssertEqual(0.04411804, actual, accuracy: 0.0001)
    }
    
    func testIfCorrectScaleReturnedWhenBoxIsScaledByFive() {
        let sut = ModelScaleProvider(makeBox(withScale: 5))
        
        let actual = sut.getModelScale()
        
        XCTAssertEqual(0.27016672, actual, accuracy: 0.0001)
    }
    
    func testIfCorrectScaleReturnedWhenPyramidIsScaledByFive() {
        let sut = ModelScaleProvider(makePyramid(withScale: 5))
        
        let actual = sut.getModelScale()
        
        XCTAssertEqual(0.2205902, actual, accuracy: 0.0001)
    }
    
    func testIfCorrectScaleReturnedWhenBoxIsScaledByThousand() {
        let sut = ModelScaleProvider(makeBox(withScale: 1000))
        
        let actual = sut.getModelScale()
        
        XCTAssertEqual(54.033344, actual, accuracy: 0.0001)
    }
    
    func testIfCorrectScaleReturnedWhenPyramidIsScaledByThousand() {
        let sut = ModelScaleProvider(makePyramid(withScale: 1000))
        
        let actual = sut.getModelScale()
        
        XCTAssertEqual(44.1180444, actual, accuracy: 0.0001)
    }
    
    func testIfCorrectScaleReturnedWhenBoxIsScaledByZero() {
        let sut = ModelScaleProvider(makeBox(withScale: 0))
        
        let actual = sut.getModelScale()
        
        XCTAssertEqual(0, actual, accuracy: 0.0001)
    }
    
    func testIfCorrectScaleReturnedWhenPyramidIsScaledByZero() {
        let sut = ModelScaleProvider(makePyramid(withScale: 0))
        
        let actual = sut.getModelScale()
        
        XCTAssertEqual(0, actual, accuracy: 0.0001)
    }
    
    private func makeBox(withScale scale: Float) -> SCNNode {
        let node = SCNNode()
        node.geometry = SCNBox()
        node.scale *= scale
        return node
    }
    
    private func makePyramid(withScale scale: Float) -> SCNNode {
        let node = SCNNode()
        node.geometry = SCNPyramid()
        node.scale *= scale
        return node
    }
}
