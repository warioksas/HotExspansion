package com.example.ktuhot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AboutMaterialsActivity extends AppCompatActivity {

    List<Material> materials = new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    SweetAlertDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_aboutmaterials);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));

        if (isInternetWorking())
            initData();
        else
        {
            recyclerView.setVisibility(RecyclerView.INVISIBLE);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Nėra interneto!");
            builder.setMessage("Buvo užfiksuota, jog nėra tinkamo interneto ryšio. " +
                    "Prisijunkite prie interneto ir tik tuomet galėsite gauti informaciją!");
            builder.setCancelable(false);

            builder.setNegativeButton(
                    "Grįžti atgal",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            onBackPressed();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public boolean isInternetWorking() {
        boolean success = false;
        try {
            URL url = new URL("https://google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(10000);
            connection.connect();
            success = connection.getResponseCode() == 200;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }

    private void initData() {
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#242810"));
        pDialog.setTitleText("Gaunama informacija...");
        pDialog.setCancelable(false);
        pDialog.show();
        final DatabaseReference ref;
        ref = FirebaseDatabase.getInstance().getReference().child("Materials");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot data) {
                materials.clear();
                for (DataSnapshot ds : data.getChildren()) {
                    String name = ds.child("Name").getValue(String.class);
                    String coefficient = ds.child("Coefficient").getValue(String.class);
                    String image = ds.child("ImageUrl").getValue(String.class);
                    String description = ds.child("Description").getValue(String.class);
                    materials.add(new Material(name, coefficient, description, image));
                }
                Collections.sort(materials, new Comparator<Material>() {
                    public int compare(Material m1, Material m2) {
                        return m1.getName().compareTo(m2.getName());
                    }
                });
                final AboutMaterialsListAdapter adapter = new AboutMaterialsListAdapter(materials);
                recyclerView.setAdapter(adapter);
                pDialog.dismiss();
                pDialog.dismissWithAnimation();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Duomenų bazės klaida " + error, Toast.LENGTH_LONG).show();
            }
        });
    }
}