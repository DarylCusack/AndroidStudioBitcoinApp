package hdsd.bitcoinexrate;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {
    //response data
    private static String high;
    private static String low;
    private static String bid;
    private static String last;
    private static String vwap;
    private static String volume;
    private static String ask;


    // json object response url
    private String urlJsonObj1 = "https://www.bitstamp.net/api/ticker/";
    private String urlJsonObj2 = "https://www.bitstamp.net/api/ticker_hour/";


    private static String TAG = MainActivity.class.getSimpleName();
    private ImageButton btnMakeObjectRequest1, btnGraph, btnSettings;

    // Progress dialog
    private ProgressDialog pDialog;

    private TextView txtHighDesc, txtHighPrice,txtLowDesc, txtLowPrice,txtVolumeDesc,
            txtVolumePrice, txtAskingDesc, txtAskingPrice, txtBidDesc, txtBidPrice, txtLastDesc, txtLastPrice;

    // temporary string to show the parsed response
    private String jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMakeObjectRequest1 = (ImageButton) findViewById(R.id.btnObjRequest1);

        btnGraph = (ImageButton) findViewById(R.id.btnGraph);
        btnSettings = (ImageButton) findViewById(R.id.btnSettings);
        txtHighDesc = (TextView) findViewById(R.id.txtHighDesc);
        txtHighPrice = (TextView) findViewById(R.id.txtHighPrice);
        txtLowDesc = (TextView) findViewById(R.id.txtLowDesc);
        txtLowPrice = (TextView) findViewById(R.id.txtLowPrice);
        txtVolumeDesc = (TextView) findViewById(R.id.txtVolumeDesc);
        txtVolumePrice = (TextView) findViewById(R.id.txtVolumePrice);
        txtAskingDesc = (TextView) findViewById(R.id.txtAskingDesc);
        txtAskingPrice = (TextView) findViewById(R.id.txtAskingPrice);
        txtBidDesc = (TextView) findViewById(R.id.txtBidDesc);
        txtBidPrice = (TextView) findViewById(R.id.txtBidPrice);
        txtLastDesc = (TextView) findViewById(R.id.txtLastDesc);
        txtLastPrice = (TextView) findViewById(R.id.txtLastPrice);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        //Calls the data once the app is loaded
        makeJsonObjectRequest2();

        btnMakeObjectRequest1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NoteTaker.class);
                startActivity(intent);
            }
        });


        btnGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Graph.class);
                startActivity(intent);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });


    }

    /**
     * Method to make json object request where json response starts with {
     * */

    /**
     * Method to make json object request where json response starts wtih {
     * */
    private void makeJsonObjectRequest2() {

        showpDialog();

        JsonObjectRequest jsonObjReq2 = new JsonObjectRequest(Method.GET,
                urlJsonObj2, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    high = response.getString("high");
                    last = response.getString("last");
                    //String timestamp = response.getString("timestamp");
                    bid = response.getString("bid");
                    vwap = response.getString("vwap");
                    low = response.getString("low");
                    ask = response.getString("ask");

                    txtHighDesc.setText("Highest Hourly Price:");
                    txtHighPrice.setText("€"+high);

                    txtLowDesc.setText("Lowest Hourly Price:");
                    txtLowPrice.setText("€"+low);

                    txtLastDesc.setText("Last Traded Price:");
                    txtLastPrice.setText("€"+last);

                    txtBidDesc.setText("Current Bid Price:");
                    txtBidPrice.setText("€"+bid);

                    txtVolumeDesc.setText("Volume Weighted Average:");
                    txtVolumePrice.setText("€"+vwap);

                    txtAskingDesc.setText("Current Asking Price:");
                    txtAskingPrice.setText("€"+ask);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
            }
        });

        // Adding request to request queue
        hdsd.bitcoinexrate.AppController.getInstance().addToRequestQueue(jsonObjReq2);
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public static float getHigh(){
        return Float.parseFloat(high);
    }

    public static float getLow(){
        return Float.parseFloat(low);
    }

    public static float getBid(){
        return Float.parseFloat(bid);
    }

    public static float getLast(){
        return Float.parseFloat(last);
    }

    public static float getVwap(){
        return Float.parseFloat(vwap);
    }


    public static float getAsk(){
        return Float.parseFloat(ask);
    }
}