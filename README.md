# react-native-config-bridge

The aim of this library is to read variables from Android and iOS native config with Javascript. This can be useful, for example, to know what buildType/flavor/configuration the current app has been built upon.

## Getting started

```shell script
yarn add react-native-config-bridge
```

##### For RN 0.60 and upper
```shell script
cd ios
pod install
```

##### For RN 0.59 and below
```shell script
react-native link react-native-config-bridge
```

## Usage
##### Android

1. Edit your `android/app/build.gradle`
    ```gradle
    ...
    defaultConfig {
        ...
        buildConfigField('String', 'ENV_NAME', '"development"')
    }
    ...
    ```
1. Override your config field per buildType or flavor, if needed.
    ```gradle
    ...
    buildTypes {
        ...
        release {
            ...
            buildConfigField('String', 'ENV_NAME', '"production"')
        }
    }
    ...
    ```
1. Enjoy.

##### iOS
1. Edit your `ios/Info.plist`
    ```xml
    ...
    <key>ENV_NAME</key>
    <string>$(BUILD_SETTING_NAME)</string>
    ...
    ```
2. Create a new build setting (`BUILD_SETTING_NAME` is a placeholder) in your target
3. Customize the build setting for each configuration, if needed.


##### Javascript
```javascript
import RNConfig from 'react-native-config-bridge';

const envName = RNConfig.ENV_NAME;
```
