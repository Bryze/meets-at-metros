package com.example.raunak.justmetrothings;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

    private TextView name;
    private TextView gender;
    private TextView location;
    private TextView relation;
    private FirebaseUser user;
    private FirebaseAuth firebaseauth;
    private DatabaseReference datareference;
    private String uid;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragments_info, container, false);

        name = (TextView)rootView.findViewById(R.id.info_name);
        gender = (TextView)rootView.findViewById(R.id.info_gender);
        location = (TextView)rootView.findViewById(R.id.info_location);
        relation = (TextView)rootView.findViewById(R.id.info_relation);
        firebaseauth = FirebaseAuth.getInstance();
        user = firebaseauth.getCurrentUser();
        if(user!=null){
            uid= user.getUid();
        }
        datareference = FirebaseDatabase.getInstance().getReference();
        datareference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserInformation ui = dataSnapshot.getValue(UserInformation.class);
                name.setText(ui.name);
                gender.setText(ui.gender);
                relation.setText(ui.relationship);
                location.setText(ui.location);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        return  rootView;
    }
}
