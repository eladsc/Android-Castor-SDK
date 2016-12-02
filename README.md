# CastorSDK

[![License](https://img.shields.io/cocoapods/l/CastorSDK.svg?style=flat)](http://cocoapods.org/pods/CastorSDK)


CastorSDK is a SDK that allows you to sell 3D printed models directly from your app. Just display the CastorStoreFragment to the user with the item you would like to sell and the Castor SDK will take care of the rest: from sale , through printing to final shipping and delivery of the item to the client's home.

Before integrating the SDK, please contact me in order to catalog the models you are interested in selling in your app. Once we are done we will supply you with an API key that will setup the SDK to be a storefront for your 3D models.

## Example

To run the example project, clone the repo, and run the example app. 

## Requirements

Android SDK 15 and above

## Installation

CastorSDK is available through JCenter(). To install
it, simply add the following line to your Module Gradle file inside the dependencies block:

```ruby
compile 'com.castor.castorsdk:castor-sdk:+'
```
## Use
The SDK needs the `android.permission.INTERNET`  permission. If you didn't add it yet please add it to your manifest xml file.

Inside your App class or main activity call the configure function of the Castor singleton in order to setup the SDK:

```
Castor.getInstance().configure(YOUR_API_KEY)
```

Once you are ready to display the store UI you will need to create and display a `CastorStoreFragment` object. You will need to create it with the `Product` object that represents the specific 3D model you want to sell. You can ether obtain the `Product` object by its Id or by selecting it from an array that contains all your products:

```
// An example of getting an array of all the Product objects
List<Product> arr = Castor.getInstance().getProductArray();


// An example of getting a Product object that you know its Id.
Product product = Castor.getInstance().getProducts().get(PRODUCT_ID);

```
Now you can create a CastorStoreFragment:

```

Fragment fragment = CastorStoreFragment.newInstance(YOUR_PRODUCT_OBJECT);


```
We recommend you display this fragment on the full screen of the device.

You must implement the CastorStoreFragmentListener in order to respond to the SDK completing:

```
@Override
public void complete(CompletionStatus result) {
//Respond to the fragments completion and remove the fragment.
}

```

Thats it. Enjoy!


## Author

Elad Schiller, elad@3dcastor.com

## License

CastorSDK is available under the MIT license. See the LICENSE file for more info.
