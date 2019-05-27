# Vehicle Damage Reporting

This project implements a user friendly way of reporting damage sustained by a vehicle involved in an accident. It is achieved by allowing the end-user to interact with a 3D model of a car displayed on a mobile device and select the damaged parts.

The functionality is provided in form a library, that can be added to a mobile app for filing an insurance claim. It's available for Android and iOS.

Additionally, sample applications for iOS and Android are provided to demonstrate how to use the library in real-life apps.

## iOS

iOS project uses [SceneKit](https://developer.apple.com/documentation/scenekit) - a 3D graphics application programming interface (API) developed by Apple Inc. 

### Prerequisites

 - XCode 10.1
 - Developer Profile
 - Physical device with iOS 9 or newer

### Structure
There are two projects:

 - _FVM_ - framework containing UI component that allows user to choose damaged parts of vehicle   
 - _SampleApp_ - sample application which demonstrates usage of FVM framework

### Run sample app
1. Open project from ```iOS/SampleApp/SampleApp.xcodeproj```
2. Select ```SampleApp``` project in XCodes Project Navigator and set your Team in Signing settings
3. Set Active Scheme to ```SampleApp``` and select your device as build destination
4. Click 'Play' (build and run) button.

## Android

Android project uses [libGDX](https://libgdx.badlogicgames.com/) game development framework.

### Prerequisites

 - __Android Studio__: 3.2.0 or higher.
 - __Android SDK__: Android ICE_CREAM_SANDWICH or higher (14 API)

### Structure
 
 - _vehicledamagemodeling_ - the library for Android
 - _core_ - implementation in Java, used internally by the Android library 
 - _app_ - sample Android application which demonstrates usage of the library
 

# License

Code provided in this repository is distributed under Apache License, Version 2.0. Please refer [LICENSE](../master/LICENSE) file for details.
