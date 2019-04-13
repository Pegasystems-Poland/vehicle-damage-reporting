//
//  FVMDamagedCarViewControllerTests.swift
//  FVMTests
//
//  Created by Smykala, Szymon on 09/04/2019.
//  Copyright © 2019 Czajka, Kamil. All rights reserved.
//

import Foundation
import SceneKit
import XCTest
@testable import FVM


public class FVMDamage: XCTestCase {


    
    func testIfSomething() {
        
        
        let sut = FVMDamagedCarViewController();
        sut.viewDidLoad()
        sut.configuration = ""
        var alpha = sut.rotationPrompt.alpha;
    
        
        XCTAssert(alpha == 100)
    }

}
