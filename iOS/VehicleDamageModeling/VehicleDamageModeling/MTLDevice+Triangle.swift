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
import Metal

extension MTLDevice {
    func makeBufferWithTriangle() -> MTLBuffer {
        let data: [Float] = [
          // X     Y    Z
            1.0,  1.0, 0.0,
           -1.0,  0.0, 0.0,
            1.0, -1.0, 0.0
        ]
        let dataSize = data.count * MemoryLayout.size(ofValue: data[0])
        return self.makeBuffer(bytes: data, length: dataSize, options: [])!
    }

    func makeBasicPipelineState(_ library: MTLLibrary) throws -> MTLRenderPipelineState {
        //let library = self.makeDefaultLibrary()!
        // See VertexShaders.metal file for implementation of this functions
        let fragmentFunc = library.makeFunction(name: "basic_fragment")
        let vertexFunc = library.makeFunction(name: "basic_vertex")

        // Now we're passing vertex and fragment functions to
        // pipeline descriptor
        let pipelineDescriptor = MTLRenderPipelineDescriptor()
        pipelineDescriptor.vertexFunction = vertexFunc
        pipelineDescriptor.fragmentFunction = fragmentFunc
        pipelineDescriptor.colorAttachments[0].pixelFormat = .bgra8Unorm

        // finally, we may create renderPipelineState which is required
        // for render commands encoder.
        return try self.makeRenderPipelineState(descriptor: pipelineDescriptor)
    }
}
