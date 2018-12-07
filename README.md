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

## Android

Android project uses [Vulkan Graphics API](https://developer.android.com/ndk/guides/graphics/) for high-performance, 3D graphics. Based on _android-vulkan-tutorials_ from Google ([tutorial05_triangle](https://github.com/googlesamples/android-vulkan-tutorials/tree/master/tutorial05_triangle/)).

#### Prerequisites

 - __Android Studio__: 3.2.0 or higher.
 - __Android SDK__: Android N or higher (24 API)
 - __Android NDK__ and __CMake__ build tools

#### Import project

 1. __Build shaderc in the NDK__ - go to `${ANDROID_HOME}/ndk-bundle/sources/third_party/shaderc` and run following command:

    ```
    ../../../ndk-build NDK_PROJECT_PATH=. APP_BUILD_SCRIPT=Android.mk APP_STL:=c++_static APP_ABI=armeabi-v7a libshaderc_combined -j16
    ```

 2. Install [Ninja](https://ninja-build.org/) build system (using Homebrew):

    ```
    brew install ninja
    ```

3. Import project using Android Studio.

# License

Code provided in this repository is distributed under Apache License, Version 2.0. Please refer [LICENSE](../master/LICENSE) file for details.
