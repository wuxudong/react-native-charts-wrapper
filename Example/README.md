## Example

### start local server


```
expo start --dev-client
```

### android apk

![](https://raw.githubusercontent.com/wuxudong/react-native-charts-wrapper/master/Example/android_qr_code.JPG)

[apk](https://expo.dev/accounts/wuxudong/projects/Example/builds/ed4445b8-f35e-4edf-a004-3171042d2436)

### ios app [only for simulator]

[app](https://expo.dev/artifacts/eas/rht3bVj4Tv8AdXKGVP8AvS.tar.gz)

download and unzip, then drap it to your ios simulator.




### local build

```sh
cd Example
yarn install

# build android apk
eas build -p android --profile development --local

# build ios app for simulator
eas build -p ios --profile development --local
``` 




