//package com.example.portfolioapp.Adapter;
//
//import android.content.Context;
//import android.view.ContextThemeWrapper;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.portfolioapp.Model.ExperienceClass;
//import com.example.portfolioapp.R;
//
//import java.util.ArrayList;
//
//public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.ViewHolder> {
//
//    Context context;
//    ArrayList<ExperienceClass> arrayList = new ArrayList<>();
//
//    public ExperienceAdapter(Context context, ArrayList<ExperienceClass> arrayList) {
//        this.context = context;
//        this.arrayList = arrayList;
//    }
//
//    @NonNull
//    @Override
//    public ExperienceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.custom_rv_experience_layout, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ExperienceAdapter.ViewHolder holder, int position) {
//        if (position >= 0 && position < arrayList.size()) {
//            ExperienceClass experienceClass = arrayList.get(position);
//
//            holder.edt_jobTitle.setText(experienceClass.getJobTitle());
//            holder.edt_company.setText(experienceClass.getCompany());
//            holder.edt_jobType.setText(experienceClass.getJobType());
//            holder.edt_timeDuration.setText(experienceClass.getTimeDuration());
//            holder.edt_skills.setText(experienceClass.getSkills());
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return arrayList.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        EditText edt_jobTitle, edt_company, edt_jobType, edt_timeDuration, edt_skills;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            edt_jobTitle = itemView.findViewById(R.id.edt_jobTitle);
//            edt_company = itemView.findViewById(R.id.edt_company);
//            edt_jobType = itemView.findViewById(R.id.edt_jobType);
//            edt_timeDuration = itemView.findViewById(R.id.edt_timeDuration);
//            edt_skills = itemView.findViewById(R.id.edt_skills);
//        }
//    }
//}


package com.example.portfolioapp.Adapter;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.portfolioapp.Model.ExperienceClass;
import com.example.portfolioapp.R;

import java.util.ArrayList;

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.ViewHolder> {

    Context context;
    ArrayList<ExperienceClass> arrayList = new ArrayList<>();

    public ExperienceAdapter(Context context, ArrayList<ExperienceClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ExperienceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_rv_experience_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExperienceAdapter.ViewHolder holder, int position) {
        if (position >= 0 && position < arrayList.size()) {
            ExperienceClass experienceClass = arrayList.get(position);

            holder.edt_jobTitle.setText(experienceClass.getJobTitle());
            holder.edt_timeDuration.setText(experienceClass.getTimeDuration());
            holder.edt_skills.setText(experienceClass.getSkills());
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        EditText edt_jobTitle, edt_timeDuration, edt_skills;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            edt_jobTitle = itemView.findViewById(R.id.edt_jobTitle);
            edt_timeDuration = itemView.findViewById(R.id.edt_timeDuration);
            edt_skills = itemView.findViewById(R.id.edt_skills);
        }
    }
}