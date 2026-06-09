# Hide Mock Location
Modified fork focused on Pokemon GO and other Niantic games.

[![Release](https://img.shields.io/github/v/release/SoaresPT/HideMockLocation?label=Release)](https://github.com/SoaresPT/HideMockLocation/releases/latest)
[![Download](https://img.shields.io/github/downloads/SoaresPT/HideMockLocation/total)](https://github.com/SoaresPT/HideMockLocation/releases)

## Summary
![Logo](app/src/main/res/mipmap-xhdpi/ic_launcher.png)

Hide Mock Location is a modified and stripped-down Xposed module focused mostly on
bypassing mock location detection for Pokemon GO and other Niantic games. It removes the
old system GNSS/location-provider hooks and keeps the smaller app-level mock flag hooks
needed for spoofing-focused use cases. It should also work for other scoped apps and games
that rely on the same Android location mock flags.

It supports Android 9 and newer, and requires LSPosed/Vector to be installed.

Releases are available at https://github.com/SoaresPT/HideMockLocation/releases.

## Usage
* Install module to your device.
* Enable module in LSPosed and reboot device.
  * Target app/game
  * GPS Joystick, if you use the compatibility helper on Samsung One UI devices
* That's it. The module hides mock-location flags from scoped applications.
