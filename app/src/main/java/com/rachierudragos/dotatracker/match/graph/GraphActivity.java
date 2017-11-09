package com.rachierudragos.dotatracker.match.graph;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.renderer.YAxisRenderer;
import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.Wrapper.parcel.IntegerListParcel;

import java.util.ArrayList;
import java.util.List;

public class GraphActivity extends AppCompatActivity {
    //private LineGraphSeries<DataPoint> seriesg;
    //private LineGraphSeries<DataPoint> dataSetGpm;
    LineChart graph;
    private static boolean GPM = true;
    private static boolean XPM = false;
    private boolean afis_g = true;
    private boolean afis_x = true;
    Description description;
    private LineData lineData;
    private LineDataSet dataSetGpm;
    private LineDataSet dataSetXpm;

    private static final String XPM_DESC = "XPM";
    private static final String GPM_DESC = "GPM";
    private static final String XGPM_DESC = "GPM & XPM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        graph = findViewById(R.id.graph);
        //gpm

        List<Entry> entriesg = new ArrayList<>();
        List<Integer> gpm = ((IntegerListParcel) getIntent().getParcelableExtra("gpm")).getList();
        for (int i = 0; i < gpm.size(); ++i) {
            entriesg.add(new Entry(i, gpm.get(i)));
        }
        dataSetGpm = new LineDataSet(entriesg, "GPM");
        int color = ContextCompat.getColor(this, R.color.yellow);
        dataSetGpm.setDrawCircles(false);
        dataSetGpm.setColor(color);
        dataSetGpm.setValueTextSize(getResources().getDimension(R.dimen.text_size_small));
        dataSetGpm.setValueTextColor(color);
        //xpm
        List<Integer> xpm = ((IntegerListParcel) getIntent().getParcelableExtra("xpm")).getList();
        List<Entry> entriesx = new ArrayList<>();
        for (int i = 0; i < xpm.size(); ++i) {
            entriesx.add(new Entry(i, xpm.get(i)));
        }
        color = ContextCompat.getColor(this, R.color.blue);
        dataSetXpm = new LineDataSet(entriesx, "XPM");
        dataSetXpm.setDrawCircles(false);
        dataSetXpm.setColor(color);
        dataSetXpm.setValueTextSize(getResources().getDimension(R.dimen.text_size_small));
        dataSetXpm.setValueTextColor(color);

        description = new Description();
        description.setText("GPM & XPM");
        description.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        ////Graph
        lineData = new LineData(dataSetGpm, dataSetXpm);
        lineData.setDrawValues(false);
        graph.setData(lineData);

        XAxis xAxis = graph.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setTextSize(getResources().getDimensionPixelSize(R.dimen.text_size_small));


        YAxis yAxis = graph.getAxisLeft();
        yAxis.setGranularity(5000f);
        yAxis.setTextColor(Color.WHITE);
        yAxis.setTextSize(getResources().getDimensionPixelSize(R.dimen.text_size_small));

        //graph
        graph.setScaleYEnabled(false);
        graph.getAxisRight().setEnabled(false);
        graph.getLegend().setEnabled(false);
        graph.setDrawMarkers(true);
        graph.getViewPortHandler().setMinimumScaleX(1.5f);

        graph.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                //lineData.getDataSetByIndex(h.getDataSetIndex()).setDrawValues();
                Toast.makeText(GraphActivity.this, String.valueOf(h.getDataSetIndex()), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        graph.invalidate();

        //Toast.makeText(this, "Match not parsed yet, please wait for opendota to parse it.", Toast.LENGTH_LONG);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_graph, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_gpm) {
            changeGraph(GPM);
            return true;
        }
        if (id == R.id.action_xpm) {
            changeGraph(XPM);
            return true;
        }
        if (id == android.R.id.home) {
            supportFinishAfterTransition();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeGraph(boolean graphic) {
        if (graphic == GPM) {
            if (afis_g) {
                lineData.removeDataSet(dataSetGpm);
                afis_g = false;
                if (afis_x)
                    description.setText(XPM_DESC);
                else
                    description.setEnabled(false);
            } else {
                lineData.addDataSet(dataSetGpm);
                afis_g = true;
                if (afis_x)
                    description.setText(XGPM_DESC);
                else
                    description.setText(GPM_DESC);
            }
        } else {
            if (afis_x) {
                lineData.removeDataSet(dataSetXpm);
                afis_x = false;
                if (afis_g)
                    description.setText(GPM_DESC);
                else
                    description.setText("");
            } else {
                lineData.addDataSet(dataSetXpm);
                afis_x = true;
                if (afis_g)
                    description.setText(XGPM_DESC);
                else
                    description.setText(XPM_DESC);
            }
        }
        graph.invalidate();
    }
}
