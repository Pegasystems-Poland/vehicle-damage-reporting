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
import FVM

class ViewController: UIViewController {
    @IBOutlet weak var descriptionTextView: UITextView!
    @IBOutlet weak var partsTextView: UITextView!
    @IBOutlet weak var startButton: UIButton!
    @IBOutlet weak var resultTextView: UITextView!
    internal var selectionFormatter: SelectionFormatterProtocol!
    
    override func viewDidLoad() {
        selectionFormatter = SelectionFormatter()
        initializePartsTextView()
        initializeDescriptionTextView()
        initializeResultTextView()
        super.viewDidLoad()
    }
    
    @IBAction func runFVM(_ sender: UIButton) {
        let vc = FvmFactory.create(getConfigurationData()) { result in
            let formattedParts = self.getReadableDamagedParts(result)
            ResultContainer.damagedCarParts = formattedParts ?? ""
            self.partsTextView.text = formattedParts
            self.resultTextView.text = result
        }
        present(vc, animated: true, completion: nil)
    }
}
