package com.rachierudragos.dotatracker;

import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.rachierudragos.dotatracker.Fragments.MatchesFragment;
import com.rachierudragos.dotatracker.Wrapper.Dota2Stats;
import com.rachierudragos.dotatracker.Wrapper.account.AccountDetails;
import com.rachierudragos.dotatracker.Wrapper.account.AccountDetailsImpl;

import org.apache.http.HttpStatus;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Dota2Stats stats;
    private FragmentManager fm;
    private static long ID;
    AccountDetails accountDetails;
    SharedPreferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        //generat de ei

        preference = PreferenceManager.getDefaultSharedPreferences(this);
        ID = preference.getLong("id", -1);
        if(ID==-1)
            dialogID();

        ImageView ivpoza = (ImageView) header.findViewById(R.id.img_acc);
        TextView tvmmrs = (TextView) header.findViewById(R.id.text_mmrs);
        TextView tvmmrp = (TextView) header.findViewById(R.id.text_mmrp);
        TextView tvnume = (TextView) header.findViewById(R.id.txt_nume);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                accountDetails = new AccountDetailsImpl(String.valueOf(ID));
            }
        });
        t.start();
        try {
            t.join();
            tvmmrs.setText("" + accountDetails.getSoloMMR());
            tvmmrp.setText("" + accountDetails.getPartyMMR());
            tvnume.setText(accountDetails.getName());
            new ImageDownloaderTask(ivpoza, accountDetails.getPhotoURL(), this).execute(accountDetails.getPhotoURL());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_main, new MatchesFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            dialogID();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static long getID() {
        return ID;
    }

    private void dialogID() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText editText = new EditText(this);
        //LinearLayout
        builder.setView(editText)
                .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!editText.getText().toString().equals("")) {
                            ID = Long.parseLong(editText.getText().toString());
                            SharedPreferences.Editor editor = preference.edit();
                            editor.putLong("id", ID).commit();
                        }
                    }
                });

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_recent_matches) {
            // Handle the camera action
            fm.beginTransaction().replace(R.id.content_main, new MatchesFragment()).commit();
        } else if (id == R.id.nav_all_matches) {

        } else if (id == R.id.nav_heroes) {

        } else if (id == R.id.nav_records) {

        } else if (id == R.id.nav_stats) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        private Context context;
        private String url;

        public ImageDownloaderTask(ImageView imageView, String url, Context context) {
            this.imageView = imageView;
            this.url = url;
            this.context = context;
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            return downloadBitmap(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }

        private Bitmap downloadBitmap(String url) {
            HttpURLConnection urlConnection = null;
            try {
                URL uri = new URL(url);
                urlConnection = (HttpURLConnection) uri.openConnection();

                int statusCode = urlConnection.getResponseCode();
                if (statusCode != HttpStatus.SC_OK) {
                    return null;
                }

                InputStream inputStream = urlConnection.getInputStream();
                if (inputStream != null) {

                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    return bitmap;
                }
            } catch (Exception e) {
                System.out.println("URLCONNECTIONERROR" + e.toString());
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                System.out.println("ImageDownloader Error downloading image from " + url);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();

                }
            }
            return null;
        }
    }
}
