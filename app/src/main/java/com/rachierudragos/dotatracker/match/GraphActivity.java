package com.rachierudragos.dotatracker.match;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.rachierudragos.dotatracker.R;

import java.util.List;

public class GraphActivity extends AppCompatActivity {
    private LineGraphSeries<DataPoint> seriesg;
    private LineGraphSeries<DataPoint> seriesx;
    private GraphView graph;
    private static boolean GPM = true;
    private static boolean XPM = false;
    private boolean afis_g = true;
    private boolean afis_x = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        graph = (GraphView) findViewById(R.id.graph);
        try {
            List<Integer> gpm = (List<Integer>) getIntent().getSerializableExtra("gpm");
            List<Integer> xpm = (List<Integer>) getIntent().getSerializableExtra("xpm");
            DataPoint[] pointsg = new DataPoint[gpm.size()];
            DataPoint[] pointsx = new DataPoint[xpm.size()];
            for (int i = 0; i < gpm.size(); ++i) {
                pointsg[i] = (new DataPoint(i, gpm.get(i)));
                pointsx[i] = (new DataPoint(i, xpm.get(i)));
            }
            ////GPM
            seriesg = new LineGraphSeries<DataPoint>(pointsg);
            seriesg.setColor(Color.YELLOW);
            ////XPM
            seriesx = new LineGraphSeries<DataPoint>(pointsx);
            seriesx.setColor(Color.BLUE);
            ////Graph
            graph.addSeries(seriesg);
            graph.addSeries(seriesx);
            graph.getViewport().setScalable(true);
            graph.getViewport().setScrollable(true);
            graph.setTitle("GPM&XPM");
        } catch (Exception e) {
            Toast.makeText(this,"Match not parsed yet, please wait for opendota to parse it.",Toast.LENGTH_LONG);
            finish();
        }
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
            finish();
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
