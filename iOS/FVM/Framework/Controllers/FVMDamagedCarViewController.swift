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

public class FVMDamagedCarViewController: UIViewController {
    @IBOutlet weak var damageSelector: FVMCarModelViewController!
    @IBOutlet weak var informationForUserTextView: UITextView!
    @IBOutlet weak var closeButton: UIButton!
    @IBOutlet weak var finishButton: UIButton!
    public var jsonConfigurationData: String?
    public var getReturningJSONData: ((String) -> Void)?
    
    override public func viewDidLoad() {
        damageSelector.onStartup(jsonConfiguration: jsonConfigurationData!)
        informationForUserTextView.text = parseDescriptionFromSampleApp()
        super.viewDidLoad()
    }
    
    internal func parseDescriptionFromSampleApp() -> String {
        let parser = JsonParser<SelectionRoot>()
        let selectionRoot = parser.parse(jsonData: jsonConfigurationData!)
        return selectionRoot?.mainScreenText ?? ""
    }
    
    @IBAction func closeButtonTapped(_ sender: UIButton) {
        _ = getReturningJSONData?(damageSelector.onCancel())
        self.dismiss(animated: true, completion: nil)
    }
    
    @IBAction func finishButtonTapped(_ sender: UIButton) {
        _ = getReturningJSONData?(damageSelector.onAccept())
        self.dismiss(animated: true, completion: nil)
    }
}
