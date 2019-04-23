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

extension ViewController : UITextViewDelegate {
    fileprivate static let TERMINATING_CHAR = "\n"
    fileprivate static let DESCRIPTION_ID = "Description"
    fileprivate static let DESCRIPTION_VALUE = "Rotate model and select damaged parts with a tap on the model. If completed press button."
    fileprivate static let DAMAGED_PARTS_ID = "DamagedCarParts"
    fileprivate static let DAMAGED_PARTS_VALUE = "Hood; Roof;"
    fileprivate static let DEFAULT_RESULT =
    """
    FVM Result:
    {
    }
    """
    
    public func textView(_ textView: UITextView, shouldChangeTextIn: NSRange, replacementText: String) -> Bool {
        if replacementText == ViewController.TERMINATING_CHAR {
            textView.resignFirstResponder()
            return false
        }
        return true
    }
    
    public func textViewDidBeginEditing(_ textView: UITextView) {
        if textView.textColor == UIColor.lightGray {
            textView.text = nil
            textView.textColor = UIColor.black
        }
    }
    
    public func textViewDidEndEditing(_ textView: UITextView) {
        if textView.text.isEmpty {
            textView.text = getDefaultText(textView)
            textView.textColor = UIColor.lightGray
        }
    }
    
    internal func initializePartsTextView() {
        partsTextView.text = ViewController.DAMAGED_PARTS_VALUE
        partsTextView.textColor = UIColor.lightGray
        partsTextView.delegate = self
    }
    
    internal func initializeDescriptionTextView() {
        descriptionTextView.text = ViewController.DESCRIPTION_VALUE
        descriptionTextView.textColor = UIColor.lightGray
        descriptionTextView.delegate = self
    }
    
    internal func initializeResultTextView() {
        resultTextView.text = ViewController.DEFAULT_RESULT
    }
    
    fileprivate func getDefaultText(_ textViewId: UITextView) -> String {
        switch textViewId.restorationIdentifier {
        case ViewController.DESCRIPTION_ID:
            return ViewController.DESCRIPTION_VALUE
        case ViewController.DAMAGED_PARTS_ID:
            return ViewController.DAMAGED_PARTS_VALUE
        default:
            fatalError("Unexpected identifier: \(String(describing: textViewId.restorationIdentifier))")
        }
    }
}
