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

import Foundation
import UIKit
import Metal

public class VehicleDamageSelectorView: UIView {

    var device: MTLDevice!

    var vertexBuffer: MTLBuffer!
    var pipelineState: MTLRenderPipelineState!
    var commandQueue: MTLCommandQueue!

    var displayLink: CADisplayLink!

    open var selectedParts: Set<BodyPart> {
        get {
            return [.Hood, .Grilles]
        }
    }

    // As agreed in Definition Of Done, our component should return JSON array with selected
    // parts. So we will map selectedParts property to JSON. This is example implementation,
    // Please agree part names with your Product Owner.
    // You also should implement setter here, so we will be able to pre-select damaged parts.
    open var selectedPartsJSON: String {
        get {
            let parts = selectedParts.map { (part) -> String in part.rawValue }
            do {
               let data = try JSONSerialization.data(withJSONObject: parts, options: .prettyPrinted)
                return String(data: data, encoding: .utf8) ?? "[]"
            } catch {
                return "[]"
            }
        }
    }

    // CAMetalLayer is not available on simulator, please compile for device.
    override open class var layerClass: AnyClass {
        return CAMetalLayer.self
    }

    var metalLayer: CAMetalLayer {
        return layer as! CAMetalLayer
    }

    private func setup() {
        // device is forsibly-unwrapped. We don't check
        // is MTLCreateSystemDefaultDevice returned real value or nil,
        // although on real implementation we should do this, and if
        // MTLCreateSystemDefaultDevice return nil, we should show
        // some error message as this will mean that there's no
        // devices with Metal support found.
        device = MTLCreateSystemDefaultDevice()
        metalLayer.device = device
        metalLayer.pixelFormat = .bgra8Unorm
        metalLayer.framebufferOnly = true

        vertexBuffer = device.makeBufferWithTriangle()

        let bundle = Bundle(for: VehicleDamageSelectorView.self)
        // for simplicity and demonstration we don't use try-catch
        // statement here. On real implementation we should use it
        // to ensure stability.
        let library = try! device.makeDefaultLibrary(bundle: bundle)
        pipelineState = try! device.makeBasicPipelineState(library)
        commandQueue = device.makeCommandQueue()

        //displayLink = CADisplayLink(target: self, selector: #selector(loop))
        //displayLink.add(to: .current, forMode: .default)
    }

    // normally, we should create displayLink (like in commented-out lines
    // above), although for demonstration purproses, as we're rendering
    // static triangle, it's okay to use layoutSubviews function to
    // render out content.
    open override func layoutSubviews() {
        super.layoutSubviews()
        if device == nil {
            setup()
        }

        autoreleasepool {
            render()
        }
    }

    func render() {
        // to perform rendering, we need drawable from metal layer,
        // and command buffer. When command buffer is committed,
        // it is added to commandQueue. Commands from commandQueue
        // is passed to metal device in same order as they commited.
        guard let drawable = metalLayer.nextDrawable(),
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

    /*
    @objc func loop() {
        autoreleasepool {
            self.render()
        }
    }*/
}
