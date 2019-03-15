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

class FVMDamagedCarViewController: UIViewController {
    @IBOutlet weak var damageSelector: FVMCarModelViewController!
    @IBOutlet weak var informationForUserTextView: UITextView!
    @IBOutlet weak var closeButton: UIButton!
    @IBOutlet weak var finishButton: UIButton!
    
    override func viewDidLoad() {
        damageSelector.onStartup(jsonConfiguration: """
        {
            "mainScreenText": "text",
                "selection":
                [
                    {
                        "id":"Roof"
                    },
                    {
                        "id":"Hood"
                    }
                ]
            }
        """)
        
        // TODO : Should be configuralbe
        informationForUserTextView.text = "Hello FVM!"
        
        print(damageSelector.onCancel())
        print(damageSelector.onAccept())
        super.viewDidLoad()
    }
    
    // TODO: Sending JSON when tapped
    @IBAction func closeButtonTapped(_ sender: UIButton) {
        self.dismiss(animated: false, completion: nil)
    }
    
    @IBAction func finishButtonTapped(_ sender: Any) {
        self.dismiss(animated: false, completion: nil)
    }
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */
}
