# weather
Unit Testing a Spring App Example. The application is a Spring web app that uses two Google APIs and the darksky.net API.

## Getting Your API Keys
In order to use this application, you'll need to get a couple API keys. Here's how.
### Darksky
Go to https://darksky.net/dev and register with your email address. After registering, you'll see a page with an example URL that you can use to GET JSON data at an arbitrary latitude and longitude. Clicking that link will show you what the data looks like that our application will receive.

At the bottom of the page, you'll see your unique API key displayed. Set this into environment variable `DARKSKY_API_KEY`.
### Google
While logged in to a Google account, navigate to https://console.developers.google.com. From the menu, choose **Credentials**, click **Create credentials**, and select **API key**. When asked, choose **Server key**. At this point you'll see your key, which you should copy. Set this into environment variable `GOOGLE_API_KEY`.

At this point you need to enable the Geocoding and Places API, so that your API key grants you access to those APIs. Under the menu item **Overview**, you should see a categorized list of Google APIs. Under the **Google Maps** category, enable the following APIs:
 
  - Google Maps Geocoding API, and
  - Google Places API Web Service