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

extension ViewController {
    fileprivate static let ALERT_TITLE = "Information"
    fileprivate static let ALERT_ACTION_TITLE = "OK"
    fileprivate static let FIRST_TIME_ALERT_MESSAGE = "You can set description sending to FVM"
    fileprivate static let INFORMATION_ALERT_MESSAGE = "You can set description sending to FVM and here is displayed JSON returning from FVM"
    
    internal func showFirstTimeOpenAppInformationAlert() {
        let alert = UIAlertController(title: ViewController.ALERT_TITLE, message: ViewController.FIRST_TIME_ALERT_MESSAGE, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: ViewController.ALERT_ACTION_TITLE, style: .default, handler: nil))
        self.present(alert, animated: true, completion: nil)
        
        disableShowingFirstTimeOpenAppInformationAlert()
    }
    
    internal func showInformationAlertWhenReturingFVM() {
        let alert = UIAlertController(title: ViewController.ALERT_TITLE, message: ViewController.INFORMATION_ALERT_MESSAGE, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: ViewController.ALERT_ACTION_TITLE, style: .default, handler: nil))
        self.present(alert, animated: true, completion: nil)
    }
    
    fileprivate func disableShowingFirstTimeOpenAppInformationAlert() {
        CurrentValue.FirstTimeInformation = false
    }
}
