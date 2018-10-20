# AudioThief

<img src="https://github.com/TakuSemba/AudioThief/blob/master/arts/logo.png">

## Gradle

```groovy

//app/build.gradle
dependencies {
    implementation 'com.github.takusemba:audiothief:x.x.x'
}

// build.gradle
allprojects {
  repositories {
    // AudioFocus will be available in Jcenter soon.
    maven { url "https://dl.bintray.com/takusemba/maven" }
  }
}

```

<br/>

<img src="https://github.com/TakuSemba/AudioThief/blob/master/arts/sample.gif" align="right" width="30%">

## Features
![Platform](http://img.shields.io/badge/platform-android-green.svg?style=flat)
![Download](https://api.bintray.com/packages/takusemba/maven/audiothief/images/download.svg)
![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)
![API](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat)

Since multiple apps can play audio to the sample ouptut stream simultaneously, it is requred to handle AudioFocus correctly.

However you do not want to call or set an alerm just to see if your impletemtation is working as you expected. 

AudioThief gain and release AudioFocus from your app.

<br/>

## Usage

#### 1. Add AudioThief dependency

```groovy

dependencies {
    implementation 'com.github.takusemba:audiothief:x.x.x'
}

```

#### 2. Edit your manifest.xml

```xml

<manifest xmlns:android="http://schemas.android.com/apk/res/android">
  <!--this permission is required if your app targets Android 9.0 (API level 28)-->
  <uses-permission android:name="android.permission.FOREGROUND_SERVICE"/>

  <application>
  <!--...-->
    <service
       android:name="com.takusemba.audiothief.AudioFocusGainService"
       android:exported="true"
       />
  </application>
       
</manifest>

```

#### 3. Gain and Release AudioFocus

You can start a foreground service that gains AudioFocus via adb command, or simply start the srevice inside your app.

```bash
adb shell am startservice --ei AUDIO_REQUEST_KEY [audioRequestKey] your.package.name/com.takusemba.audiothief.AudioFocusGainService
```

It holds AudioFocus until the foreground service stops.

```
adb shell am stopservice --ei AUDIO_REQUEST_KEY [audioRequestKey] your.package.name/com.takusemba.audiothief.AudioFocusGainService
```

## AudioRequestKey

AudioRequestKey defines how you gain AudioFocus. That would be Int of 1, 2, 3, or 4.

|  AudioRequestKey  |  FocusGain  |  Usage  |  Content Type  |
| :---: | :---: | :---: | :---: |
|  1  |  AUDIOFOCUS_GAIN  |  USAGE_MEDIA  |  CONTENT_TYPE_MOVIE  |
|  2  |  AUDIOFOCUS_GAIN_TRANSIENT  |  USAGE_ALARM  |  CONTENT_TYPE_SONIFICATION  |
|  3  |  AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK  |  USAGE_NOTIFICATION  |  CONTENT_TYPE_SONIFICATION  |
|  4  |  AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE  |  USAGE_ALARM  |  CONTENT_TYPE_SONIFICATION  |


## Sample
Clone this repo and check out the [app](https://github.com/TakuSemba/AudioThief/tree/master/app) module.

## Change Log

### Version: 1.0.1

  * remove support library dependency


## Author

* **Taku Semba**
    * **Github** - (https://github.com/takusemba)
    * **Twitter** - (https://twitter.com/takusemba)
    * **Facebook** - (https://www.facebook.com/takusemba)

## Licence
```
Copyright 2017 Taku Semba.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```


