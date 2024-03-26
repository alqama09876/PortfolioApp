package com.example.portfolioapp.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.portfolioapp.Adapter.CertificateAdapter;
import com.example.portfolioapp.Adapter.ProjectAdapter;
import com.example.portfolioapp.Model.CertificateClass;
import com.example.portfolioapp.Model.ProjectClass;
import com.example.portfolioapp.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ProjectFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    RecyclerView rv_project;
    ImageButton btn_add, btn_delete;
    ArrayList<ProjectClass> arrayList = new ArrayList<>();
    ProjectAdapter adapter;
    SharedPreferences sharedPreferences;

    public ProjectFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_project, container, false);

//      find id...
        btn_add = view.findViewById(R.id.btn_add);
        btn_delete = view.findViewById(R.id.btn_delete);
        rv_project = view.findViewById(R.id.rv_project);


        // Load saved projects
        Context context = requireContext();
        sharedPreferences = context.getSharedPreferences("ProjectPrefs", Context.MODE_PRIVATE);
        Set<String> savedProjects = sharedPreferences.getStringSet("projects", new HashSet<String>());
        for (String projectString : savedProjects) {
            String[] parts = projectString.split(",");
            ProjectClass project = new ProjectClass(parts[0], parts[1], parts[2]);
            arrayList.add(project);
        }

        rv_project.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ProjectAdapter(context, arrayList);
        rv_project.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddProjectDialog();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllProjects();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            Log.d("ProjectFragment", "Selected image URI: " + imageUri.toString()); // Check if image URI is valid
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), imageUri);
                Log.d("ProjectFragment", "Bitmap created successfully"); // Check if bitmap is successfully created
                // Find the ImageView in the currently visible item of the RecyclerView
                RecyclerView.ViewHolder viewHolder = rv_project.findViewHolderForAdapterPosition(0); // Change the position as per your requirement
                if (viewHolder instanceof ProjectAdapter.ViewHolder) {
                    ProjectAdapter.ViewHolder projectViewHolder = (ProjectAdapter.ViewHolder) viewHolder;
                    projectViewHolder.certificate_pic.setImageBitmap(bitmap); // Set the selected image to ImageView
                    Log.d("ProjectFragment", "Image set to ImageView successfully");
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("ProjectFragment", "Error loading image: " + e.getMessage()); // Log any errors that occur
            }
        }
    }

    private void showAddProjectDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_project, null);

        ImageView certificate_pic = dialogView.findViewById(R.id.certificate_pic);
        EditText edt_pname = dialogView.findViewById(R.id.edt_pname);
        EditText edt_pDescription = dialogView.findViewById(R.id.edt_pDescription);

        // Set the default image or use any appropriate method to load the image
        certificate_pic.setImageResource(R.drawable.default_project_image); ///////////////////////////////////.........

        certificate_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch intent to pick an image
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        builder.setView(dialogView)
                .setTitle("Add Projects")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = edt_pname.getText().toString();
                        String description = edt_pDescription.getText().toString();

                        if (!name.isEmpty() && !description.isEmpty()) {
                            // Set the image URL or resource identifier here
                            String image = ""; // Replace this with the appropriate image URL or resource identifier
                            ProjectClass project = new ProjectClass(image, name, description);
                            arrayList.add(project);
                            adapter.notifyDataSetChanged();

                            // Save experiences
                            saveProjects();
                        } else {
                            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteAllProjects() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Delete All Projects")
                .setMessage("Are you sure you want to delete all projects?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arrayList.clear();
                        adapter.notifyDataSetChanged();

                        // Clear saved certificates
                        clearSavedProjects();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void saveProjects() {
        Set<String> projectSet = new HashSet<>();
        for (ProjectClass project : arrayList) {
            String projectString = project.getImage() + "," + project.getName() + "," + project.getDescription();
            projectSet.add(projectString);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("projects", projectSet);
        editor.apply();
    }

    private void clearSavedProjects() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("projects");
        editor.apply();
    }
}

