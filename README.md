# PAMN Project

## Overview

### This repository contains:

The main application (final version).
Several Codelab exercises implemented for learning purposes.

### The initial version of the app featured:

A black background and a title at the top.
The title uses the Netflix font, which is included in the font folder.
A swipe gesture to the left that navigates to another screen where the user can enter input.

### Current Structure

- **app/** → Main application
    - MainActivity.kt
    - MovieModels.kt
    - ui.theme/
        - Color.kt
        - Theme.kt
        - Type.kt
- **codelabs/** → Codelab exercises
    - SwipeActivity.kt

## How to Run

By default, the app launches MainActivity (final version).

## To view the Codelab work:

Open AndroidManifest.xml.
Change the activity name from: android:name=".app.MainActivity"
to: android:name=".codelabs.SwipeActivity"

## Features

Netflix-style title font included in the font directory.
Swipe gesture: Swiping left navigates to a new screen for user input.

## Requirements

JDK 17
Android Gradle Plugin 8.1.0
compileSdk = 34 (or as configured)
Tested on Pixel 4 Emulator (API 36)

## Notes

The project uses a single module with a clear package separation:
app for the main application.
codelabs for exercises.
### Please keep this structure for clarity and maintainability.