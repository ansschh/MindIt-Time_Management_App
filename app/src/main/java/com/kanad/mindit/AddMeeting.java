package com.kanad.mindit;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddMeeting extends Fragment {
    Spinner weight_spinner;
    SwitchCompat togglebutton;
    String addthistodeadline,spinnerTEXT,remindme,deadlineinmilli;
    EditText tittle_edtTXT,description_txt,timerequired_txt,deadline_txt,typeoftask,edt_goal,meetinglink,edt_rel_activity;
    RadioGroup radiogroup;
    RadioButton threetimes,fivetimes;
    Button add_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_meeting, container, false);
        weight_spinner = v.findViewById(R.id.weight_spinner);
        add_btn = v.findViewById(R.id.add_btn);
        meetinglink = v.findViewById(R.id.meetinglink);
        edt_rel_activity = v.findViewById(R.id.edt_rel_activity);
        if (ContextCompat.checkSelfPermission(
                getContext(), Manifest.permission.READ_CALENDAR) ==
                PackageManager.PERMISSION_GRANTED) {

        } else {
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
            requestPermissionLauncher.launch(
                    Manifest.permission.READ_CALENDAR);
        }
        if (ContextCompat.checkSelfPermission(
                getContext(), Manifest.permission.WRITE_CALENDAR) ==
                PackageManager.PERMISSION_GRANTED) {

        } else {
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
            requestPermissionLauncher.launch(
                    Manifest.permission.WRITE_CALENDAR);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.weight_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fivetimes = v.findViewById(R.id.fivetimes);
        tittle_edtTXT = v.findViewById(R.id.tittle_edtTXT);
        description_txt = v.findViewById(R.id.description_txt);
        timerequired_txt = v.findViewById(R.id.timerequired_txt);
        deadline_txt = v.findViewById(R.id.deadline_txt);
        edt_goal = v.findViewById(R.id.edt_goal);
        typeoftask = v.findViewById(R.id.typeoftask);
        threetimes = v.findViewById(R.id.threetimes);
        radiogroup = v.findViewById(R.id.radiogroup);
        weight_spinner.setAdapter(adapter);
        deadline_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(deadline_txt);
            }
        });
        togglebutton
                = (SwitchCompat)v.findViewById(
                R.id.toggleButton);
        togglebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (togglebutton.isChecked()) {
                    addthistodeadline = "Yes";
                }
                else {
                    addthistodeadline  = "No";
                }
            }
        });
        weight_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?>arg0, View view, int arg2, long arg3) {

                spinnerTEXT=weight_spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) v.findViewById(selectedId);
                remindme = radioButton.getText().toString();
            }
        });
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tittle_edtTXT.getText()!=null){
                    if (description_txt.getText()!=null){
                        if (timerequired_txt.getText()!=null){
                            if (deadline_txt.getText()!=null){
                                    if (meetinglink.getText()!=null){
                                        if (spinnerTEXT!=null){
                                            if (remindme!=null){
                                                saveTask();
                                            }else{
                                                Toast.makeText(v.getContext(), "Select, How Many Times We should Remind You?", Toast.LENGTH_SHORT).show();
                                            }
                                        }else{
                                            Toast.makeText(v.getContext(), "Select Activity Type", Toast.LENGTH_SHORT).show();
                                        }
                                    }else{
                                        edt_goal.setError("");
                                    }
                            }else{
                                deadline_txt.setError("");
                            }
                        }else{
                            timerequired_txt.setError("");
                        }
                    }else{
                        description_txt.setError("");
                    }
                }else{
                    tittle_edtTXT.setError("");
                }
            }
        });
        return v;
    }

    private void showDateTimeDialog(EditText deadline_txt) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR,i);
                calendar.set(Calendar.MONTH,i1);
                calendar.set(Calendar.DAY_OF_MONTH,i2);
                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {

                        calendar.set(Calendar.HOUR_OF_DAY,i);
                        calendar.set(Calendar.MINUTE,i1);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm aa");
                        deadline_txt.setText(simpleDateFormat.format(calendar.getTime()));
                        deadlineinmilli = String.valueOf(calendar.getTimeInMillis());
                    }
                };
                new TimePickerDialog(getContext(),timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };
        new DatePickerDialog(getContext(),dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void saveTask() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String i1 = sdf.format(new Date());
        if (addthistodeadline.equalsIgnoreCase("Yes")){
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Meetings").child(GoogleSignIn.getLastSignedInAccount(getContext()).getId()).child(String.valueOf(i1));
            AddingMeeting addingMeeting = new AddingMeeting(tittle_edtTXT.getText().toString(),description_txt.getText().toString(),timerequired_txt.getText().toString(),spinnerTEXT,deadline_txt.getText().toString(),remindme,meetinglink.getText().toString(),String.valueOf(i1),deadlineinmilli,edt_rel_activity.getText().toString());
            databaseReference.setValue(addingMeeting).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Meetings").child(GoogleSignIn.getLastSignedInAccount(getContext()).getId()).child(String.valueOf(i1));
        AddingMeeting addingMeeting = new AddingMeeting(tittle_edtTXT.getText().toString(),description_txt.getText().toString(),timerequired_txt.getText().toString(),spinnerTEXT,deadline_txt.getText().toString(),remindme,meetinglink.getText().toString(),String.valueOf(i1),deadlineinmilli,edt_rel_activity.getText().toString());
        databaseReference.setValue(addingMeeting).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(getContext());
                builder.setMessage("You Task was successfully added!");
                builder.setTitle("Task Added");
                builder.setCancelable(false);
                builder
                        .setPositiveButton(
                                "Ok",
                                new DialogInterface
                                        .OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which)
                                    {
                                        dialog.cancel();
                                        Intent intent = new Intent(Intent.ACTION_INSERT);
                                        intent.setData(CalendarContract.Events.CONTENT_URI);
                                        intent.putExtra(CalendarContract.Events.TITLE,tittle_edtTXT.getText().toString());
                                        intent.putExtra(CalendarContract.Events.DESCRIPTION,description_txt.getText().toString());
                                        intent.putExtra(CalendarContract.Events.DTEND, Long.valueOf(deadlineinmilli));
                                        intent.putExtra(CalendarContract.Events.EVENT_TIMEZONE,Calendar.getInstance().getTimeZone().getID());
                                        intent.putExtra(CalendarContract.Events.CALENDAR_ID,1);
                                        intent.putExtra(Intent.EXTRA_EMAIL,GoogleSignIn.getLastSignedInAccount(getContext()).getEmail());

                                        if (intent.resolveActivity(getActivity().getPackageManager())!=null){
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(getContext(), "Google Calendar is not available", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        ContentResolver cr = getContext().getContentResolver();
        ContentValues cv = new ContentValues();
        cv.put(CalendarContract.Events.TITLE,tittle_edtTXT.getText().toString());
        cv.put(CalendarContract.Events.DESCRIPTION,description_txt.getText().toString());
        cv.put(CalendarContract.Events.DTEND, Long.valueOf(deadlineinmilli));
        cv.put(CalendarContract.Events.DTSTART,Calendar.getInstance().getTimeInMillis());
        cv.put(CalendarContract.Events.EVENT_TIMEZONE,Calendar.getInstance().getTimeZone().getID());
        cv.put(CalendarContract.Events.CALENDAR_ID,1);
        Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI,cv);


    }
    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {

                } else {

                }
            });
}