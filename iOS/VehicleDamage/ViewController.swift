// Copyright 2018 Pegasystems Inc.
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
import Metal

class ViewController: UIViewController {

    @IBOutlet var metalView: MetalView!

    var device: MTLDevice!

    var vertexBuffer: MTLBuffer!
    var pipelineState: MTLRenderPipelineState!
    var commandQueue: MTLCommandQueue!

    var displayLink: CADisplayLink!

    override func viewDidLoad() {
        super.viewDidLoad()
        // device is forsibly-unwrapped. We don't check
        // is MTLCreateSystemDefaultDevice returned real value or nil,
        // although on real implementation we should do this, and if
        // MTLCreateSystemDefaultDevice return nil, we should show
        // some error message as this will mean that there's no
        // devices with Metal support found.
        device = MTLCreateSystemDefaultDevice()
        metalView.metalLayer.device = device
        metalView.metalLayer.pixelFormat = .bgra8Unorm
        metalView.metalLayer.framebufferOnly = true

        vertexBuffer = device.makeBufferWithTriangle()
        // for simplicity and demonstration we don't use try-catch
        // statement here. On real implementation we should use it
        // to ensure stability.
        pipelineState = try! device.makeBasicPipelineState()
        commandQueue = device.makeCommandQueue()

        displayLink = CADisplayLink(target: self, selector: #selector(loop))
        displayLink.add(to: .current, forMode: .default)
    }

    func render() {
        // to perform rendering, we need drawable from metal layer,
        // and command buffer. When command buffer is committed,
        // it is added to commandQueue. Commands from commandQueue
        // is passed to metal device in same order as they commited.
        guard let drawable = metalView.metalLayer.nextDrawable(),
              let commandBuffer = commandQueue.makeCommandBuffer()
            else {return}

        let renderEncoder = commandBuffer
            .makeRenderCommandEncoder(descriptor: drawable.renderPassDescriptior())!
        renderEncoder.setRenderPipelineState(pipelineState)
        renderEncoder.setVertexBuffer(vertexBuffer, offset: 0, index: 0)
        renderEncoder
            .drawPrimitives(type: .triangle, vertexStart: 0, vertexCount: 3, instanceCount: 1)
        renderEncoder.endEncoding()

        commandBuffer.present(drawable)
        commandBuffer.commit()
    }

    @objc func loop() {
        // CADisplayLink is added to main run loop, which should
        // have it's own autorelease pool which is drained quite often, but
        // we want to have separate autoreleasepool for rendering,
        // as on real implementation rendering may have quite a lot
        // of temporary objects, which should be removed from memory
        // as soon as possible to minimise memory usage of mobile application.
        autoreleasepool {
            self.render()
        }
    }
}

