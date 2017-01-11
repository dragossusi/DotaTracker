package com.rachierudragos.dotatracker;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Collections;
import java.util.List;

public class GraphActivity extends AppCompatActivity {
    private LineGraphSeries<DataPoint> seriesg;
    private LineGraphSeries<DataPoint> seriesx;
    private GraphView graph;
    private static boolean GPM = true;
    private static boolean XPM = false;
    private boolean afis_g = true;
    private boolean afis_x = true;
    private int max_gpm;
    private int max_xpm;
    private int min_gpm;
    private int min_xpm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        graph = (GraphView) findViewById(R.id.graph);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);
        List<Integer> gpm = (List<Integer>) getIntent().getSerializableExtra("gpm");
        List<Integer> xpm = (List<Integer>) getIntent().getSerializableExtra("xpm");
        min_gpm = Collections.min(gpm);
        min_xpm = Collections.min(xpm);
        max_gpm = Collections.max(gpm);
        max_xpm = Collections.max(xpm);
        DataPoint[] pointsg = new DataPoint[gpm.size()];
        DataPoint[] pointsx = new DataPoint[xpm.size()];
        for (int i = 0; i < gpm.size(); ++i) {
            pointsg[i] = (new DataPoint(i, gpm.get(i)));
            pointsx[i] = (new DataPoint(i, xpm.get(i)));
        }
        seriesg = new LineGraphSeries<DataPoint>(pointsg);
        seriesg.setColor(Color.YELLOW);
        seriesx = new LineGraphSeries<DataPoint>(pointsx);
        seriesx.setColor(Color.BLUE);
        graph.addSeries(seriesg);
        graph.addSeries(seriesx);
        graph.setTitle("GPM&XPM");
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
        return super.onOptionsItemSelected(item);
    }

    private void changeGraph(boolean graphic) {
        if (graphic == GPM) {
            if (afis_g) {
                graph.removeSeries(seriesg);
                afis_g = false;
                if (afis_x)
                    graph.setTitle("XPM");
                else
                    graph.setTitle("");
            } else {
                graph.addSeries(seriesg);
                afis_g = true;
                if (afis_x)
                    graph.setTitle("GPM & XPM");
                else
                    graph.setTitle("GPM");
            }
        } else {
            if (afis_x) {
                graph.removeSeries(seriesx);
                afis_x = false;
                if (afis_g)
                    graph.setTitle("GPM");
                else
                    graph.setTitle("");
            } else {
                graph.addSeries(seriesx);
                afis_x = true;
                if (afis_g)
                    graph.setTitle("GPM & XPM");
                else
                    graph.setTitle("XPM");
            }
        }
    }
}
