package com.example.portfolioapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.portfolioapp.Model.CertificateClass;
import com.example.portfolioapp.Model.ExperienceClass;
import com.example.portfolioapp.R;

import java.util.ArrayList;

public class CertificateAdapter extends RecyclerView.Adapter<CertificateAdapter.ViewHolder> {

    Context context;
    ArrayList<CertificateClass> arrayList = new ArrayList<>();

    public CertificateAdapter(Context context, ArrayList<CertificateClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CertificateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_rv_certifications_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CertificateAdapter.ViewHolder holder, int position) {
        if (position >= 0 && position < arrayList.size()) {
            CertificateClass certificateClass = arrayList.get(position);

            Glide.with(context)
                    .load(certificateClass.getImage()) // Assuming 'getImage()' returns the URL of the image
                    .placeholder(R.drawable.placeholder_image) // Optional placeholder image while loading
                    .error(R.drawable.error_image) // Optional error image if loading fails
                    .into(holder.certificate_pic);
            holder.edt_cname.setText(certificateClass.getName());
            holder.edt_issueOrganization.setText(certificateClass.getIssueOrganization());
            holder.edt_IssueDateYear.setText(certificateClass.getIssueDateYear());
            holder.edt_skills.setText(certificateClass.getSkill());
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView certificate_pic;
        EditText edt_cname, edt_issueOrganization, edt_IssueDateYear, edt_skills;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            certificate_pic = itemView.findViewById(R.id.certificate_pic);
            edt_cname = itemView.findViewById(R.id.edt_cname);
            edt_issueOrganization = itemView.findViewById(R.id.edt_issueOrganization);
            edt_IssueDateYear = itemView.findViewById(R.id.edt_IssueDateYear);
            edt_skills = itemView.findViewById(R.id.edt_skills);
        }
    }
}
