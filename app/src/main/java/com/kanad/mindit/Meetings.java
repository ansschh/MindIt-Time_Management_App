package com.kanad.mindit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Meetings extends Fragment {
    RecyclerView availableLoadsRecView;
    ArrayList<AddingMeeting> loadsArrayList;
    AddingMeetingAdapter adapter;
    LinearLayout progressLayout;
    ProgressBar pdt;
    TextView date,total;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_meetings, container, false);
        availableLoadsRecView = v.findViewById(R.id.availableLoadsRecView);
        availableLoadsRecView.setHasFixedSize(true);
        total = v.findViewById(R.id.total);
        date = v.findViewById(R.id.date);
        progressLayout = v.findViewById(R.id.progressLayout);
        availableLoadsRecView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadsArrayList = new ArrayList<>();
        pdt = v.findViewById(R.id.pdt);
        adapter = new AddingMeetingAdapter(getContext(),loadsArrayList);
        progressLayout.setVisibility(View.VISIBLE);
        availableLoadsRecView.setVisibility(View.GONE);
        pdt.setVisibility(View.VISIBLE);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        date.setText(formattedDate);
        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Meetings").child(GoogleSignIn.getLastSignedInAccount(getContext()).getId());
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    loadsArrayList.add(dataSnapshot.getValue(AddingMeeting.class));
                }
                availableLoadsRecView.setVisibility(View.VISIBLE);
                progressLayout.setVisibility(View.GONE);
                pdt.setVisibility(View.GONE);
                total.setText("Total Meetings: "+loadsArrayList.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        availableLoadsRecView.setAdapter(adapter);
        return v;
    }
}