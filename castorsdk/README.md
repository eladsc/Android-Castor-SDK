# CastorSDK



CastorSDK is a SDK that allows you to sell 3D printed models directly from your app. Just display the StoreViewController to the user with the item you would like to sell and the Castor SDK will take care of the rest: from sale , through printing to final shipping and delivery of the item to the client's home.

Before integrating the SDK, please contact me in order to catalog the models you are interested in selling in your app. Once we are done we will supply you with an API key that will setup the SDK to be a storefront for your 3D models.

## Example

To run the example project, clone the repo, and run `pod install` from the Example directory first.

## Requirements

iOS 8 or above

## Installation

CastorSDK is available through [CocoaPods](http://cocoapods.org). To install
it, simply add the following line to your Podfile:

```ruby
pod "CastorSDK"
```
## Use
First you must allow arbitrary loads for our domain. This is in order to allow the Wix store  to load. Wix deploys SSL only once the checkout flow commences. Simply add the following keys to your info.plist

```
<key>NSAppTransportSecurity</key>
<dict>
  <key>NSAllowsArbitraryLoads</key>
  <true/>
  <key>NSExceptionDomains</key>
  <dict>
    <key>3dcastor.wixsite.com</key>
    <string></string>
  </dict>
</dict>
```

`import CastorSDK` into your AppDelegate. Inside your `didFinishLaunchingWithOptions` function call the configure function of the Castor singleton in order to setup the SDK:

```
Castor.manager.configure(key: YOUR_API_KEY)
```

Once you are ready to display the store UI you will need to create and display a `StoreViewController` object. You will need to create it with the `Product` object that represents the specific 3D model you want to sell. You can ether obtain the `Product` object by its Id or by selecting it from an array that contains all your products:

```
// An example of getting an array of all the Product objects
  let array = Castor.manager.productsArray

// An example of getting a Product object that you know its Id.
  let product = Castor.manager.products[PRODUCT_ID]
```
Now you can create and display the StoreViewController:

```

let controller = StoreViewController.makeStoreViewController(product: THE_PRODUCT_TO_SELL, delegate: self)
present(controller, animated: true, completion: nil)

```

You must implement the StoreViewControllerDelegate in order to respond to the SDK completing:

```
extension ViewController : StoreViewControllerDelegate{

  func complete( viewController: StoreViewController , status: CompletionStatus){
    viewController.dismiss(animated: true, completion: nil)
  }

}

```

Thats it. Enjoy!


## Author

Elad Schiller, eladsc@gmail.com

## License

CastorSDK is available under the MIT license. See the LICENSE file for more info.
