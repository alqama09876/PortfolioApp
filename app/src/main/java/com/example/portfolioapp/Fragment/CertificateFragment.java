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
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.portfolioapp.Adapter.CertificateAdapter;
import com.example.portfolioapp.Model.CertificateClass;
import com.example.portfolioapp.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CertificateFragment extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 1;
    RecyclerView rv_certificate;
    ImageButton btn_add, btn_delete;
    ArrayList<CertificateClass> arrayList = new ArrayList<>();
    CertificateAdapter adapter;
    SharedPreferences sharedPreferences;

    ImageView certificate_pic;

    public CertificateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_certificate, container, false);

        // find id...
        btn_add = view.findViewById(R.id.btn_add);
        btn_delete = view.findViewById(R.id.btn_delete);
        rv_certificate = view.findViewById(R.id.rv_certificate);

        // Load saved experiences
        Context context = requireContext();
        sharedPreferences = context.getSharedPreferences("CertificatePrefs", Context.MODE_PRIVATE);
        Set<String> savedCertificates = sharedPreferences.getStringSet("certificates", new HashSet<String>());
        for (String certificateString : savedCertificates) {
            String[] parts = certificateString.split(",");
            CertificateClass certificate = new CertificateClass(parts[0], parts[1], parts[2], parts[3], parts[4]);
            arrayList.add(certificate);
        }

        rv_certificate.setLayoutManager(new LinearLayoutManager(context));
        adapter = new CertificateAdapter(context, arrayList);
        rv_certificate.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddCertificateDialog();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllCertificates();
            }
        });
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            Log.d("CertificateFragment", "Selected image URI: " + imageUri.toString()); // Check if image URI is valid
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), imageUri);
                Log.d("CertificateFragment", "Bitmap created successfully"); // Check if bitmap is successfully created
                // Find the ImageView in the currently visible item of the RecyclerView
                RecyclerView.ViewHolder viewHolder = rv_certificate.findViewHolderForAdapterPosition(0); // Change the position as per your requirement
                if (viewHolder instanceof CertificateAdapter.ViewHolder) {
                    CertificateAdapter.ViewHolder certificateViewHolder = (CertificateAdapter.ViewHolder) viewHolder;
                    certificateViewHolder.certificate_pic.setImageBitmap(bitmap); // Set the selected image to ImageView
                    Log.d("CertificateFragment", "Image set to ImageView successfully");
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("CertificateFragment", "Error loading image: " + e.getMessage()); // Log any errors that occur
            }
        }
    }

    private void showAddCertificateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_certificate, null);

        ImageView certificate_pic = dialogView.findViewById(R.id.certificate_pic);
        EditText edt_cname = dialogView.findViewById(R.id.edt_cname);
        EditText edt_issueOrganization = dialogView.findViewById(R.id.edt_issueOrganization);
        EditText edt_IssueDateYear = dialogView.findViewById(R.id.edt_IssueDateYear);
        EditText edt_skills = dialogView.findViewById(R.id.edt_skills);

        // Set the default image or use any appropriate method to load the image
        certificate_pic.setImageResource(R.drawable.default_certificate_image); ///////////////////////////////////.........

        certificate_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch intent to pick an image
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        builder.setView(dialogView)
                .setTitle("Add Certificate")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = edt_cname.getText().toString();
                        String issueOrganization = edt_issueOrganization.getText().toString();
                        String issueDateYear = edt_IssueDateYear.getText().toString();
                        String skill = edt_skills.getText().toString();

                        if (!name.isEmpty() && !issueOrganization.isEmpty() && !issueDateYear.isEmpty() && !skill.isEmpty()) {
                            // Set the image URL or resource identifier here
                            String image = ""; // Replace this with the appropriate image URL or resource identifier
                            CertificateClass certificate = new CertificateClass(image, name, issueOrganization, issueDateYear, skill);
                            arrayList.add(certificate);
                            adapter.notifyDataSetChanged();

                            // Save experiences
                            saveCertificates();
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

    private void deleteAllCertificates() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Delete All Certificates")
                .setMessage("Are you sure you want to delete all certificates?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arrayList.clear();
                        adapter.notifyDataSetChanged();

                        // Clear saved certificates
                        clearSavedCertificates();
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

    private void saveCertificates() {
        Set<String> certificateSet = new HashSet<>();
        for (CertificateClass certificate : arrayList) {
            String certificateString = certificate.getImage() + "," + certificate.getName() + "," + certificate.getIssueOrganization() + "," + certificate.getIssueDateYear() + "," + certificate.getSkill();
            certificateSet.add(certificateString);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("certificates", certificateSet);
        editor.apply();
    }

    private void clearSavedCertificates() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("certificates");
        editor.apply();
    }
}




////////////////////////////////////////////////////////////////////////
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.example.portfolioapp.Adapter.CertificateAdapter;
//import com.example.portfolioapp.Adapter.ExperienceAdapter;
//import com.example.portfolioapp.Model.CertificateClass;
//import com.example.portfolioapp.Model.ExperienceClass;
//import com.example.portfolioapp.R;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Set;
//
//public class CertificateFragment extends Fragment {
//    RecyclerView rv_certificate;
//    ImageButton btn_add, btn_delete;
//    ArrayList<CertificateClass> arrayList = new ArrayList<>();
//    CertificateAdapter adapter;
//    SharedPreferences sharedPreferences;
//    Context context;
//
//    public CertificateFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_certificate, container, false);
//
//        // find id...
//        btn_add = view.findViewById(R.id.btn_add);
//        btn_delete = view.findViewById(R.id.btn_delete);
//        rv_certificate = view.findViewById(R.id.rv_certificate);
//
//        // Load saved experiences
//        sharedPreferences = context.getSharedPreferences("CertificatePrefs", Context.MODE_PRIVATE);
//        Set<String> savedCertificates = sharedPreferences.getStringSet("certificates", new HashSet<String>());
//        for (String certificateString : savedCertificates) {
//            String[] parts = certificateString.split(",");
//            CertificateClass certificate = new CertificateClass(parts[0], parts[1], parts[2], parts[3], parts[4]);
//            arrayList.add(certificate);
//        }
//
//        rv_certificate.setLayoutManager(new LinearLayoutManager(context));
//        adapter = new CertificateAdapter(context, arrayList);
//        rv_certificate.setAdapter(adapter);
//
//        btn_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showAddCertificateDialog();
//            }
//        });
//
//        btn_delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteAllExperiences();
//            }
//        });
//
//        return view;
//    }
//
//    private void showAddCertificateDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        LayoutInflater inflater = requireActivity().getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialog_add_certificate, null);
//
//        ImageView certificate_pic = dialogView.findViewById(R.id.certificate_pic);
//        EditText edt_cname = dialogView.findViewById(R.id.edt_cname);
//        EditText edt_issueOrganization = dialogView.findViewById(R.id.edt_issueOrganization);
//        EditText edt_IssueDateYear = dialogView.findViewById(R.id.edt_IssueDateYear);
//        EditText edt_skills = dialogView.findViewById(R.id.edt_skills);
//
//        builder.setView(dialogView)
//                .setTitle("Add Certificate")
//                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String image = certificate_pic.getText().toString();
//                        String name = edt_cname.getText().toString();
//                        String issueOrganization = edt_issueOrganization.getText().toString();
//                        String issueDateYear = edt_IssueDateYear.getText().toString();
//                        String skill = edt_skills.getText().toString();
//
//                        if (!image.isEmpty() && !name.isEmpty() && !issueOrganization.isEmpty() && !issueDateYear.isEmpty() && !skill.isEmpty()) {
//                            CertificateClass certificate = new CertificateClass(image, name, issueOrganization, issueDateYear, skill);
//                            arrayList.add(certificate);
//                            adapter.notifyDataSetChanged();
//
//                            // Save experiences
//                            saveCertifications();
//                        } else {
//                            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }
//
//    private void deleteAllExperiences() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("Delete All Experiences")
//                .setMessage("Are you sure you want to delete all experiences?")
//                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        arrayList.clear();
//                        adapter.notifyDataSetChanged();
//
//                        // Clear saved experiences
//                        clearSavedExperiences();
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }
//
//    private void saveCertifications() {
//        Set<String> certificateSet = new HashSet<>();
//        for (CertificateClass certificate : arrayList) {
//            String certificateString = certificate.getImage() + "," + certificate.getName() + "," + certificate.getIssueOrganization() + "," + certificate.getIssueDateYear() + "," + certificate.getSkill();
//            certificateSet.add(certificateString);
//        }
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putStringSet("certificates", certificateSet);
//        editor.apply();
//    }
//
//    private void clearSavedExperiences() {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.remove("certificates");
//        editor.apply();
//    }
//}