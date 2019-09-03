package com.clone.whats.admin.whatsclone.activity;

import android.app.Activity;
import android.os.Bundle;

import com.clone.whats.admin.whatsclone.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends Activity {

    private DatabaseReference referenciaFirebase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        referenciaFirebase.child("pontos").setValue(100);


    }
}
