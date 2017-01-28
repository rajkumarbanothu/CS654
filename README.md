                   -Raj Kumar Banothu

----------------------------------------------------------------------
----------------------------------------------------------------------

-->This application contains three components:

1. MobileTracker, which is responsible for Performing Operations like Siren on/off ,locking mobile ,sending location of mobile to server continuously if simcard changed and much more functionalities as described in report/PPT.

2. MyWebService, which contains jsp/servlets code running on Application server(in my case, Tomcat) to provide the webservice features i.e webservice continuosly takes the geo location of stolen mobile and sends that received geo location coordinates(i.e latitude and longitude) to clients(preregistered mobile numbers) upon receiving the HTTP Request from preregistered mobile number.

3. The third component i.e   Googlemaps which is client side application for tracking lost/stolen mobile phone on google maps(configuration required to get google maps in application is described in below.

-------------------------------------------------------------------------
-------------------------------------------------------------------------
 

How To Install Mobile Tracker Application ?

--> Unzip the project folder.

--> Open eclipse and import existing android project into eclipse workspace.

--> make sure that android OS version 3.1 or later.

--> Run the project as Android Application in eclipse.

-----------------------------------------------------------------------------
-----------------------------------------------------------------------------

How to import google maps into project for client to track lost mobile on google maps?

--> Get API key from google API console for google maps.

--> Download google play services library and add them to project lib folder.

--> Make changes in AndroidManifest.xml file as suggested in developer website.

--> Add Permissions in AndroidManifest.xml file

-->Now we are able to get google maps in our own application.

-------------------------------------------------------------------------------
-------------------------------------------------------------------------------

--> Before Running App make sure that webservice is started for tracking purpose.

--> if we are unable to access webservice in aplication check ip addresses in written code and current ip address of your machine is same.

-------------------------------------------------------------------------------
-------------------------------------------------------------------------------




