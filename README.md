# Vehicle Damage Modeling

This repository contains template projects for Vehicle Damage Modeling project.

## iOS

### Requirements

 - XCode 10.1
 - Developer Profile
 - Physical device with iOS 10.0 or newer

### Structure
There are two projects:

 - _VehicleDamageModeling_ - template for developing framework with UI component for choosing damaged parts of vehicle
 - _SampleApp_ - sample application which demonstrates how to import and use _VehicleDamageModeling_ framework

### Notice
iOS project uses [Metal Framework](https://developer.apple.com/metal/) for rendering. As some of required classes (like `CAMetalLayer`) is not available on iOS Simulator - please use iOS Device as compilation target.

### Run sample project
1. Open project from ```iOS/SampleApp/SampleApp.xcodeproj```
2. Select ```SampleApp``` project in XCodes Project Navigator and set your Team in Signing settings
3. Set Active Scheme to ```SampleApp``` and select your device as build destination
4. Click 'Play' (build and run) button.

## Android

Android project uses [libGDX](https://libgdx.badlogicgames.com/) game development framework.

#### Prerequisites

 - __Android Studio__: 3.2.0 or higher.
 - __Android SDK__: Android ICE_CREAM_SANDWICH or higher (14 API)

# License

Code provided in this repository is distributed under Apache License, Version 2.0. Please refer [LICENSE](../master/LICENSE) file for details.
