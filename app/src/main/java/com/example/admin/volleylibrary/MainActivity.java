package com.example.admin.volleylibrary;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.example.admin.volleylibrary.Constant.first;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    TextView textview_name,textview_location;
    private InternetConnection internetConnection = new InternetConnection();
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewByIds();


        if (internetConnection.isNetworkAvailable(getApplicationContext())) {
            getDataForUrl();

        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.internet_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public  void  findViewByIds(){
        progressDialog=new ProgressDialog(MainActivity.this);
        textview_name= (TextView) findViewById(R.id.textview_name);
        textview_location= (TextView) findViewById(R.id.textview_location);
    }

    private void getDataForUrl() {

        String getDataForUrl = "getDataForUrl";

        progressDialog.setMessage(getResources().getString(R.string.please_wait));
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                getResources().getString(R.string.url_main), new JSONObject(),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("response*******"+response);

                        try {
                             {

                                progressDialog.dismiss();

                                JSONArray jsonArrayUserArray = response.getJSONArray(Constant.results);
                                for(int i=0;i<jsonArrayUserArray.length();i++){
                                    JSONObject jsonObjectresults=jsonArrayUserArray.getJSONObject(i);
                                    String gender=jsonObjectresults.getString(Constant.gender);
                                    JSONObject jsonObjectname=jsonObjectresults.getJSONObject(Constant.name);
                                    String title=jsonObjectname.getString(Constant.title);
                                    String first=jsonObjectname.getString(Constant.first);
                                    String last=jsonObjectname.getString(Constant.last);
                                    System.out.println("first*******"+first);
                                    textview_name.setText(title+" "+first+" "+last);

                                    JSONObject jsonObjectlocation=jsonObjectresults.getJSONObject(Constant.location);
                                    String street=jsonObjectlocation.getString(Constant.street);
                                    String city=jsonObjectlocation.getString(Constant.city);
                                    String state=jsonObjectlocation.getString(Constant.state);

                                    textview_location.setText(street+" ,"+city+ " \n" +state);
                                    String email=jsonObjectresults.getString(Constant.email);



                                }





                            }
                        }catch (JSONException e){
                            progressDialog.dismiss();
                            System.out.println("response**e*****"+e);
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();

                if (error.networkResponse == null) {

                    if(error.getClass().equals(NoConnectionError.class)){
                        Toast.makeText(getApplicationContext(), R.string.timeout_error,
                                Toast.LENGTH_LONG).show();
                    }else if (error.getClass().equals(TimeoutError.class)) {
                        // Show timeout error message

                        Toast.makeText(getApplicationContext(), R.string.timeout_error,
                                Toast.LENGTH_LONG).show();

                    }
                }


            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();

                return headers;
            }



        };

        // Adding request to request queue
        int socketTimeout = Constant.SocketTimeout;//60 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjReq.setRetryPolicy(policy);
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, getDataForUrl);

    }

    //Json Response

//    {
//        "results": [
//        {
//            "gender": "male",
//                "name": {
//            "title": "mr",
//                    "first": "erwan",
//                    "last": "berger"
//        },
//            "location": {
//            "street": "1483 rue abel",
//                    "city": "asnières-sur-seine",
//                    "state": "la réunion",
//                    "postcode": 49206
//        },
//            "email": "erwan.berger@example.com",
//                "login": {
//            "username": "blackbutterfly823",
//                    "password": "pandora",
//                    "salt": "568kwygk",
//                    "md5": "d0a803d93098031aca33e1400c7177e4",
//                    "sha1": "4251187a1ec5abec5d14b4f1a8475aae0474a50c",
//                    "sha256": "0585e33bd57325af4cc81a7bdd60d41e4cfcd2cd1ecc6e028e7b56cc19821aa2"
//        },
//            "dob": "1989-11-05 01:04:26",
//                "registered": "2005-02-05 10:13:07",
//                "phone": "03-80-24-21-33",
//                "cell": "06-60-06-70-92",
//                "id": {
//            "name": "INSEE",
//                    "value": "1891082868888 21"
//        },
//            "picture": {
//            "large": "https://randomuser.me/api/portraits/men/45.jpg",
//                    "medium": "https://randomuser.me/api/portraits/med/men/45.jpg",
//                    "thumbnail": "https://randomuser.me/api/portraits/thumb/men/45.jpg"
//        },
//            "nat": "FR"
//        }
//    ],
//        "info": {
//        "seed": "abb09e97a2a77891",
//                "results": 1,
//                "page": 1,
//                "version": "1.1"
//    }
//    }

}
