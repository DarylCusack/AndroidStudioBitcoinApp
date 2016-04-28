package hdsd.bitcoinexrate;

import android.app.Activity;
import android.app.ProgressDialog;

import android.os.Bundle;

import android.widget.Switch;
import android.widget.TextView;

public class SettingsActivity extends Activity {


    private Switch switch0, switch1, switch2,switch3;

    // Progress dialog
    private ProgressDialog pDialog;

    private TextView txtResponse;

    // temporary string to show the parsed response
    private String jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switch0 = (Switch) findViewById(R.id.switch0);
        switch1 = (Switch) findViewById(R.id.switch1);
        switch2 = (Switch) findViewById(R.id.switch2);
        switch3 = (Switch) findViewById(R.id.switch3);

    }

}
