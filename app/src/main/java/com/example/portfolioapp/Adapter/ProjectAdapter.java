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
import com.example.portfolioapp.Model.ProjectClass;
import com.example.portfolioapp.R;

import java.util.ArrayList;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    Context context;
    ArrayList<ProjectClass> arrayList = new ArrayList<>();

    public ProjectAdapter(Context context, ArrayList<ProjectClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ProjectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_rv_projects_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.ViewHolder holder, int position) {
        if (position >= 0 && position < arrayList.size()) {
            ProjectClass projectClass = arrayList.get(position);

            Glide.with(context)
                    .load(projectClass.getImage()) // Assuming 'getImage()' returns the URL of the image
                    .placeholder(R.drawable.placeholder_image) // Optional placeholder image while loading
                    .error(R.drawable.error_image) // Optional error image if loading fails
                    .into(holder.certificate_pic);
            holder.edt_pname.setText(projectClass.getName());
            holder.edt_projectDescription.setText(projectClass.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView certificate_pic;
        EditText edt_pname, edt_projectDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            certificate_pic = itemView.findViewById(R.id.certificate_pic);
            edt_pname = itemView.findViewById(R.id.edt_pname);
            edt_projectDescription = itemView.findViewById(R.id.edt_projectDescription);
        }
    }
}
