//
//  FVMDamagedCarViewController.swift
//  FVM
//
//  Created by Czajka, Kamil on 3/15/19.
//  Copyright © 2019 Czajka, Kamil. All rights reserved.
//

import UIKit

class FVMDamagedCarViewController: UIViewController {
 
    @IBOutlet weak var damageSelector: FVMCarModelViewController!
    
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
        
        print(damageSelector.onCancel())
        print(damageSelector.onAccept())
        super.viewDidLoad()
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
