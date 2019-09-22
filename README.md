September 2017:
1) Download test framework
2) Be sure that you have required browser versions, maven and java (java8).
    During implementation were used:
     - MacOS 10.13.2
     - java8 (1.8.0_77)
     - firefox v57.0.2 or google chrome v63.0
3) Framework currently supports 2 types of WebDriver (ChromeDriver and FirefoxDriver).
To change WebDriver type change BROWSER value to 'firefox' or 'chrome' in store-e2e-tests.properties file
4) Framework currently supports 3 types of OS: 'macos','windows' and 'linux' (tested only on MacOS)
5) To run test navigate inside store-e2e-tests directory, then run 'mvn clean test'
    Notice: Be sure that maven was added to the Environment Path
6) maven-surefire-plugin is used, so you may find reports inside target/surefire-reports directory after test run is complete

Automated test cases (2 TC):
[PurchaseProcessTest.class]
Test Case 1: (Parameterized, depends on input parameters: tab where to buy, products to buy)
  case#1: buy 1 'Magic Mouse' on Accessories page
  case#2: buy 2 'Magic Mouse' and 1 'Apple 13-inch MacBook Pro' on MacBooks page
     * - Open browser and Navigate to home page
     * - Go to some product category tab
     * - Parse and store product's data from product category tab
     * - Buy products
     * - Go to cart (checkout)
     * - Check that 'Your Cart' tab has expected products (same names, prices, total prices, quantities)
     * - Click Continue and go to 'Info' tab
     * - Fill out dummy email, shipping and billing data
     * - Click 'Purchase' and go to 'Final' tab (Transaction Results)
     * - Check that Transaction Results have expected products (same names, prices, total prices, quantities)
     * - Close browser
