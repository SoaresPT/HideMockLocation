# Hide Mock Location
Main Repo   

[![Release](https://img.shields.io/github/v/release/emotionbug/HideMockLocation?label=Release)](https://github.com/emotionbug/HideMockLocation/releases/latest)
[![Download](https://img.shields.io/github/downloads/emotionbug/HideMockLocation/total)](https://github.com/emotionbug/HideMockLocation/releases/latest)

## Summary
![Logo](app/src/main/res/mipmap-xhdpi/ic_launcher.png)

Hide Mock Location is a modified Xposed module focused mostly on bypassing mock location
detection for Niantic games, and it should work for other scoped apps that use the same
Android location mock flags. It supports Android 9 and newer, and requires LSPosed/Vector
to be installed.

## Usage
* Install module to your device.
* Enable module in LSPosed and reboot device.
  * Target app/game
  * GPS Joystick, if you use the compatibility helper on Samsung One UI devices
* That's it. The module hides mock-location flags from scoped applications.
