package hdsd.bitcoinexrate;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class Graph extends Activity {

    private BarChart mChart;
    ArrayList<String> valueNames;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_graph);

        setupChart();

        updateChartWithValues();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void setupChart() {
        mChart = (BarChart) findViewById(R.id.chart);
        if (mChart == null) {
            return;
        }
//        YAxis yAxis = mChart.getYAxis();
//        yAxis.setAxisMinValue(400.f);
//        yAxis.setAxisMaxValue(500.f);

        YAxis y = mChart.getAxisLeft();
        y.setAxisMaxValue(MainActivity.getHigh()+1);
        y.setAxisMinValue(MainActivity.getLow()-1);

        valueNames = new ArrayList<>();
        valueNames.add("High");
        valueNames.add("Low");
        valueNames.add("Last");
        valueNames.add("Ask");
        valueNames.add("Vwap");
        valueNames.add("Bid");
    }

    private void updateChartWithValues() {



        ArrayList<BarEntry> values = new ArrayList<>();

        values.add(new BarEntry(MainActivity.getHigh(), 0));
        values.add(new BarEntry(MainActivity.getLow(), 1));
        values.add(new BarEntry(MainActivity.getLast(), 2));
        values.add(new BarEntry(MainActivity.getAsk(), 3));
        values.add(new BarEntry(MainActivity.getVwap(), 4));
        values.add(new BarEntry(MainActivity.getBid(), 5));


        BarDataSet barDataSet = new BarDataSet(values, "Values");

        BarData data = new BarData(valueNames, barDataSet);

        mChart.setData(data);

        mChart.invalidate();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Graph Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://hdsd.bitcoinexrate/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Graph Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://hdsd.bitcoinexrate/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
