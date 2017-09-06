# VolleyLibrary
Volley automatically schedule all network requests. 
It means that Volley will be taking care of all the network requests your app executes for fetching response or image from web.


Android volley is a networking library was introduced to make networking calls much easier, faster without writing tons of code. 
By default all the volley network calls works asynchronously.

![screenshot_1504680665](https://user-images.githubusercontent.com/22514415/30098037-eb188836-92fd-11e7-9d0a-4a35e3c0d114.png)

# Volley offers the following benefits:

--Automatic scheduling of network requests.
--Multiple concurrent network connections.
--Transparent disk and memory response caching with standard HTTP cache coherence.
--Support for request prioritization.
--Cancellation request API. You can cancel a single request, or you can set blocks or scopes of requests to cancel.
--Ease of customization, for example, for retry and backoff.
--Strong ordering that makes it easy to correctly populate your UI with data fetched asynchronously from the network.
--Debugging and tracing tools.


  # JsonObjectRequest
  
```
    JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                getResources().getString(R.string.url_main), new JSONObject(),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
```


Parameters passed into the constructor:

--RequestMethod (GET, POST, PUT, DELETE, etc.)
--URL: String of the URL of the required object
--JSONObject: An optional object posted with the request, null if there is no object posted
--ResponseListener: Response Listener, whose callback method will contain the response
--ErrorListener: A Response.ErrorListener whose callback method will contain any problem with the request.



# AppController

Remember to update your Manifest as well. ie. Add this class in your AndroidManifest.xml using name attribute for <application> tag.

<application
        android:name=".AppController"/>
        
        
        
