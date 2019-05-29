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

public class FvmFactory {
    public class func create(_ initialConfiguration: String, _ completionAction: ((String) -> Void)?) -> FVMDamagedCarViewController {
        let bundle = Bundle(for: FVMDamagedCarViewController.self)
        let storyboard = UIStoryboard(name: "DamagedCar", bundle: bundle)
        guard let fvmViewController = storyboard.instantiateInitialViewController() as? FVMDamagedCarViewController else {
            Log.error("No initial view controller found.")
            fatalError()
        }
        fvmViewController.configuration = initialConfiguration
        fvmViewController.completionAction = completionAction
        fvmViewController.loadViewIfNeeded()
        return fvmViewController
    }
}
