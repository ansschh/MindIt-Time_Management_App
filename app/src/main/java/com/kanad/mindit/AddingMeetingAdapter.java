package com.kanad.mindit;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddingMeetingAdapter extends RecyclerView.Adapter<AddingMeetingAdapter.MyViewHolder> {


    private static Context context;
    private static ArrayList<AddingMeeting> loadsArrayList;

    public AddingMeetingAdapter(Context context, ArrayList<AddingMeeting> loadsArrayList) {
        this.context = context;
        this.loadsArrayList = loadsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.meeting, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AddingMeeting addingMeeting = loadsArrayList.get(position);
        holder.Title.setText(addingMeeting.getTitle());
        holder.description.setText(addingMeeting.getDescription());
        holder.deadline.setText(addingMeeting.getDeadline());
        holder.meetinglink.setText(addingMeeting.getMeetinglink());
        holder.rel_activity.setText(addingMeeting.getRelatedActivity());

        if (addingMeeting.getActivitytype().equalsIgnoreCase("Project Related")){
            holder.project.setVisibility(View.VISIBLE);
            holder.Title.setTextColor(Color.parseColor("#FF00B9F1"));
        }else if (addingMeeting.getActivitytype().equalsIgnoreCase("School Related")){
            holder.school.setVisibility(View.VISIBLE);
            holder.Title.setTextColor(Color.parseColor("#FFCA4545"));

        } else if (addingMeeting.getActivitytype().equalsIgnoreCase("Study Related")){
            holder.book.setVisibility(View.VISIBLE);
            holder.Title.setTextColor(Color.parseColor("#FF41A800"));

        }else if (addingMeeting.getActivitytype().equalsIgnoreCase("Gmail Related")){
            holder.gmail.setVisibility(View.VISIBLE);
            holder.Title.setTextColor(Color.parseColor("#E8A600"));

        }else if (addingMeeting.getActivitytype().equalsIgnoreCase("Research Related")){
            holder.search.setVisibility(View.VISIBLE);
            holder.Title.setTextColor(Color.parseColor("#FF97C100"));

        }else if (addingMeeting.getActivitytype().equalsIgnoreCase("Find Something")){
            holder.find.setVisibility(View.VISIBLE);
            holder.Title.setTextColor(Color.parseColor("#FF00BD6B"));

        }else if (addingMeeting.getActivitytype().equalsIgnoreCase("Diet Related")){
            holder.diet.setVisibility(View.VISIBLE);
            holder.Title.setTextColor(Color.parseColor("#FFFFD500"));

        }else if (addingMeeting.getActivitytype().equalsIgnoreCase("Extra Note")){
            holder.essay.setVisibility(View.VISIBLE);
            holder.Title.setTextColor(Color.parseColor("#FFD30E00"));

        }else if (addingMeeting.getActivitytype().equalsIgnoreCase("Application Related")){
            holder.googleforms.setVisibility(View.VISIBLE);
            holder.Title.setTextColor(Color.parseColor("#FF6D2FFF"));

        }else if (addingMeeting.getActivitytype().equalsIgnoreCase("Practise Something")){
            holder.pencil.setVisibility(View.VISIBLE);
            holder.Title.setTextColor(Color.parseColor("#FFFF5722"));

        }
        holder.cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Meetings").child(GoogleSignIn.getLastSignedInAccount(holder.itemView.getContext()).getId()).child(addingMeeting.getTask_id());
                databaseReference.removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(view.getContext(), "Task Complete.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return loadsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Title,description,deadline,goal,meetinglink,rel_activity,cancel_button;
        ImageView book,school,project,gmail,search,find,diet,essay,googleforms,pencil;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cancel_button = itemView.findViewById(R.id.cancel_button);
            Title = itemView.findViewById(R.id.Title);
            description = itemView.findViewById(R.id.description);
            deadline = itemView.findViewById(R.id.deadline);
            goal = itemView.findViewById(R.id.goal);
            meetinglink = itemView.findViewById(R.id.meetinglink);
            book = itemView.findViewById(R.id.book);
            school = itemView.findViewById(R.id.school);
            project = itemView.findViewById(R.id.project);
            gmail = itemView.findViewById(R.id.gmail);
            search = itemView.findViewById(R.id.search);
            find = itemView.findViewById(R.id.find);
            diet = itemView.findViewById(R.id.diet);
            essay = itemView.findViewById(R.id.essay);
            googleforms = itemView.findViewById(R.id.googleforms);
            pencil = itemView.findViewById(R.id.pencil);
            rel_activity = itemView.findViewById(R.id.rel_activity);
        }
    }
}
