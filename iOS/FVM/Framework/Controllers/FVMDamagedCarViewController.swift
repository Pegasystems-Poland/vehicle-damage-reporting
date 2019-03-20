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
    @IBOutlet weak var acceptButton: UIButton!
    public var configurationData: String?
    public var getReturningData: ((String) -> Void)?
    private let ROTATION_PROMPT = "RotationPrompt"
    
    override public func viewDidLoad() {
        setupDamagedCarScene()
        showRotationPrompt()
        addAcceptButtonObservers()
        super.viewDidLoad()
    }
    
    @IBAction internal func closeButtonTapped(_ sender: UIButton) {
        _ = getReturningData?(damageSelector.onCancel())
        self.dismiss(animated: true, completion: nil)
    }
    
    @IBAction internal func acceptButtonTapped(_ sender: UIButton) {
        _ = getReturningData?(damageSelector.onAccept())
        self.dismiss(animated: true, completion: nil)
    }
    
    private func setupDamagedCarScene() {
        damageSelector.onStartup(configuration: configurationData!)
        fillFVMDamagedCarScene()
    }
    
    private func showRotationPrompt() {
        self.view.addSubview(rotationPromptUIImageView)
        NotificationCenter.default.addObserver(self, selector: #selector(hideRotationPrompt), name: .hideRotationPrompt, object: nil)
    }
    
    private func addAcceptButtonObservers() {
        NotificationCenter.default.addObserver(self, selector: #selector(disableAcceptButton), name: .disableAcceptButton, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(enableAcceptButton), name: .enableAcceptButton, object: nil)
    }
    
    private func fillFVMDamagedCarScene() {
        let initialSelectionRoot = damageSelector.getInitialSelectionRoot()
        fillUserPromptText(initialSelectionRoot?.mainScreenText)
        disableAcceptButton()
    }
    
    fileprivate func fillUserPromptText(_ userInfoText: String?) {
        informationForUserTextView.text = userInfoText
    }
    
    @objc
    fileprivate func disableAcceptButton() {
        acceptButton.setTitleColor(UIColor.lightGray, for: .disabled)
        acceptButton.alpha = 0.5
        acceptButton.isEnabled = false
    }
    
    @objc
    fileprivate func enableAcceptButton() {
        acceptButton.setTitleColor(UIColor.blue, for: .normal)
        acceptButton.alpha = 1.0
        acceptButton.isEnabled = true
    }
    
    @objc
    fileprivate func hideRotationPrompt() {
        for view in self.view.subviews {
            if view.restorationIdentifier?.isEqual(ROTATION_PROMPT) ?? false as Bool {
                view.removeFromSuperview()
            }
        }
    }
}
