package com.rachierudragos.dotatracker;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rachierudragos.dotatracker.Wrapper.ODotaAPI2;
import com.rachierudragos.dotatracker.heroes.HeroesFragment;
import com.rachierudragos.dotatracker.previews.MatchesFragment;
import com.rachierudragos.dotatracker.Wrapper.account.AccountDetail;
import com.rachierudragos.dotatracker.vars.App;
import com.rachierudragos.dotatracker.vars.Utils;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentManager fm;
    int id;
    int matchesNumber;
    private View header;
    AccountDetail accountDetail;
    private TextView textNume;
    private TextView textMmrp;
    private TextView textMmrs;
    private ImageView imgPoza;
    ODotaAPI2 api;

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
        header = navigationView.getHeaderView(0);

        imgPoza = header.findViewById(R.id.img_acc);
        textMmrs = header.findViewById(R.id.text_mmrs);
        textMmrp = header.findViewById(R.id.text_mmrp);
        textNume = header.findViewById(R.id.txt_nume);

        api = ((App) getApplication()).getApi();

        //generat de ei
        fm = getFragmentManager();
        id = Utils.getId();
        matchesNumber = Utils.getMatchesNumber();
        if (id == 0) {
            dialogID();
        } else {
            userdetails();
        }
    }


    private void userdetails() {
        Call<AccountDetail> call = api.getPlayer(id);
        call.enqueue(new Callback<AccountDetail>() {
            @Override
            public void onResponse(Call<AccountDetail> call, Response<AccountDetail> response) {
                Log.d("acc",call.request().url().toString());
                accountDetail = response.body();
                if (accountDetail.profile != null) {
                    textMmrs.setText(accountDetail.solo_competitive_rank);
                    textMmrp.setText(accountDetail.competitive_rank);
                    textNume.setText(accountDetail.profile.personaname);
                    Picasso.with(MainActivity.this)
                            .load(accountDetail.profile.avatarfull)
                            .into(imgPoza);
                    fm.beginTransaction().replace(R.id.content_main, new MatchesFragment()).commit();
                } else {
                    Toast.makeText(MainActivity.this, "ID can't be parsed", Toast.LENGTH_LONG).show();
                    dialogID();
                }
            }

            @Override
            public void onFailure(Call<AccountDetail> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Weird error", Toast.LENGTH_LONG).show();
            }
        });
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
        if (id == R.id.action_matches_number) {
            dialogMatchesNumber();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void dialogID() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText editText = new EditText(this);
        //LinearLayout
        builder.setView(editText)
                .setTitle("Give the Dota2 Id")
                .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!editText.getText().toString().equals("")) {
                            id = Integer.parseInt(editText.getText().toString());
                            Utils.putId(id);
                            userdetails();
                        }
                    }
                });
        builder.show();
    }

    private void dialogMatchesNumber() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText editText = new EditText(this);
        builder.setView(editText)
                .setTitle("Set number of recent matches to ")
                .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!editText.getText().toString().equals("")) {
                            int nr = Integer.parseInt(editText.getText().toString());
                            if (nr < 50)
                                matchesNumber = nr;
                            else {
                                matchesNumber = 50;
                                Toast.makeText(MainActivity.this, "Maximum number of recent matches is 50", Toast.LENGTH_SHORT).show();
                            }
                            Utils.putMatchesNumber(matchesNumber);
                        }
                    }
                });
        builder.show();
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
            fm.beginTransaction().replace(R.id.content_main, new HeroesFragment()).commit();
        } else if (id == R.id.nav_records) {

        } else if (id == R.id.nav_stats) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
