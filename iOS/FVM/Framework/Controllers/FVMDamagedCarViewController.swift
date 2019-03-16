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
    @IBOutlet weak var rotationPromptUIImageView: UIImageView!
    @IBOutlet weak var informationForUserTextView: UITextView!
    @IBOutlet weak var closeButton: UIButton!
    @IBOutlet weak var finishButton: UIButton!
    public var jsonConfigurationData: String?
    public var getReturningJSONData: ((String) -> Void)?
    
    override public func viewDidLoad() {
        setUpDamagedCarScene()
        showRotationPrompt()
        addFinishButtonObservers()
        super.viewDidLoad()
    }
    
    @IBAction internal func closeButtonTapped(_ sender: UIButton) {
        _ = getReturningJSONData?(damageSelector.onCancel())
        self.dismiss(animated: true, completion: nil)
    }
    
    @IBAction internal func finishButtonTapped(_ sender: UIButton) {
        _ = getReturningJSONData?(damageSelector.onAccept())
        self.dismiss(animated: true, completion: nil)
    }
    
    private func setUpDamagedCarScene() {
        damageSelector.onStartup(jsonConfiguration: jsonConfigurationData!)
        parseJSONFromSampleApp()
    }
    
    private func showRotationPrompt() {
        self.view.addSubview(rotationPromptUIImageView)
        NotificationCenter.default.addObserver(self, selector: #selector(hideRotationPrompt), name: .hideRotationPrompt, object: nil)
    }
    
    private func addFinishButtonObservers() {
        NotificationCenter.default.addObserver(self, selector: #selector(disableFinishButton), name: .disableFinishButton, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(enableFinishButton), name: .enableFinishButton, object: nil)
    }
    
    private func parseJSONFromSampleApp() {
        let parser = JsonParser<SelectionRoot>()
        let selectionRoot = parser.parse(jsonData: jsonConfigurationData!)
        setInformationForUserTextView(selectionRoot)
        setFinishButtonEnabled(selectionRoot)
    }
    
    fileprivate func setInformationForUserTextView(_ selectionRoot: SelectionRoot?) {
        informationForUserTextView.text = selectionRoot?.mainScreenText ?? ""
    }
    
    fileprivate func setFinishButtonEnabled(_ selectionRoot: SelectionRoot?) {
        if selectionRoot?.selection.isEmpty ?? false as Bool {
            finishButton.isEnabled = false
        }
        else {
            finishButton.isEnabled = true
        }
    }
    
    @objc
    fileprivate func disableFinishButton() {
        finishButton.setTitleColor(UIColor.lightGray, for: .disabled)
        finishButton.isEnabled = false
    }
    
    @objc
    fileprivate func enableFinishButton() {
        finishButton.setTitleColor(UIColor.blue, for: .normal)
        finishButton.isEnabled = true
    }
    
    @objc
    fileprivate func hideRotationPrompt() {
        for view in self.view.subviews {
            if view.restorationIdentifier?.isEqual("RotationPrompt") ?? false as Bool {
                view.removeFromSuperview()
            }
        }
    }
}
