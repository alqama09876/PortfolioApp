//package com.example.portfolioapp.Fragment;
//
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.ImageButton;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.portfolioapp.Adapter.ExperienceAdapter;
//import com.example.portfolioapp.Model.ExperienceClass;
//import com.example.portfolioapp.R;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Set;
//
//public class ExperienceFragment extends Fragment {
//
//    RecyclerView rv_experience;
//    ImageButton btn_add, btn_delete;
//    ArrayList<ExperienceClass> arrayList = new ArrayList<>();
//    ExperienceAdapter adapter;
//    SharedPreferences sharedPreferences;
//
//    public ExperienceFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        // Inflate the layout for this fragment...
//        View view = inflater.inflate(R.layout.fragment_experience, container, false);
//
//        // find id...
//        btn_add = view.findViewById(R.id.btn_add);
//        btn_delete = view.findViewById(R.id.btn_delete);
//        rv_experience = view.findViewById(R.id.rv_experience);
//
//        // Load saved experiences
//        sharedPreferences = getActivity().getSharedPreferences("ExperiencePrefs", Context.MODE_PRIVATE);
//        Set<String> savedExperiences = sharedPreferences.getStringSet("experiences", new HashSet<String>());
//        for (String experienceString : savedExperiences) {
//            String[] parts = experienceString.split(",");
//            ExperienceClass experience = new ExperienceClass(parts[0], parts[1], parts[2], parts[3], parts[4]);
//            arrayList.add(experience);
//        }
//
//        rv_experience.setLayoutManager(new LinearLayoutManager(requireContext()));
//        adapter = new ExperienceAdapter(requireContext(), arrayList);
//        rv_experience.setAdapter(adapter);
//
//        btn_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showAddExperienceDialog();
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
//    private void showAddExperienceDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        LayoutInflater inflater = requireActivity().getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialog_add_experience, null);
//
//        EditText edt_jobTitle = dialogView.findViewById(R.id.edt_jobTitle);
//        EditText edt_company = dialogView.findViewById(R.id.edt_company);
//        EditText edt_jobType = dialogView.findViewById(R.id.edt_jobType);
//        EditText edt_timeDuration = dialogView.findViewById(R.id.edt_timeDuration);
//        EditText edt_skills = dialogView.findViewById(R.id.edt_skills);
//
//        builder.setView(dialogView)
//                .setTitle("Add Experience")
//                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String jobTitle = edt_jobTitle.getText().toString();
//                        String company = edt_company.getText().toString();
//                        String jobType = edt_jobType.getText().toString();
//                        String timeDuration = edt_timeDuration.getText().toString();
//                        String skills = edt_skills.getText().toString();
//
//                        ExperienceClass experience = new ExperienceClass(jobTitle, company, jobType, timeDuration, skills);
//                        arrayList.add(experience);
//                        adapter.notifyDataSetChanged();
//
//                        // Save experiences
//                        saveExperiences();
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
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
//    private void saveExperiences() {
//        Set<String> experiencesSet = new HashSet<>();
//        for (ExperienceClass experience : arrayList) {
//            String experienceString = experience.getJobTitle() + "," + experience.getCompany() + "," + experience.getJobType() + "," + experience.getTimeDuration() + "," + experience.getSkills();
//            experiencesSet.add(experienceString);
//        }
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putStringSet("experiences", experiencesSet);
//        editor.apply();
//    }
//
//    private void clearSavedExperiences() {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.remove("experiences");
//        editor.apply();
//    }
//}


//////////////////////////////////////////////////////////////////////////////////////
//package com.example.portfolioapp.Fragment;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.Toast;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.portfolioapp.Adapter.ExperienceAdapter;
//import com.example.portfolioapp.Model.ExperienceClass;
//import com.example.portfolioapp.R;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Set;
//
//public class ExperienceFragment extends Fragment {
//
//    RecyclerView rv_experience;
//    ImageButton btn_add, btn_delete;
//    ArrayList<ExperienceClass> arrayList = new ArrayList<>();
//    ExperienceAdapter adapter;
//    SharedPreferences sharedPreferences;
//    Context context;
//
//    public ExperienceFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        this.context = context;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_experience, container, false);
//
//        // find id...
//        btn_add = view.findViewById(R.id.btn_add);
//        btn_delete = view.findViewById(R.id.btn_delete);
//        rv_experience = view.findViewById(R.id.rv_experience);
//
//        // Load saved experiences
//        sharedPreferences = context.getSharedPreferences("ExperiencePrefs", Context.MODE_PRIVATE);
//        Set<String> savedExperiences = sharedPreferences.getStringSet("experiences", new HashSet<String>());
//        for (String experienceString : savedExperiences) {
//            String[] parts = experienceString.split(",");
//            ExperienceClass experience = new ExperienceClass(parts[0], parts[1], parts[2], parts[3], parts[4]);
//            arrayList.add(experience);
//        }
//
//        rv_experience.setLayoutManager(new LinearLayoutManager(context));
//        adapter = new ExperienceAdapter(context, arrayList);
//        rv_experience.setAdapter(adapter);
//
//        btn_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showAddExperienceDialog();
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
//    private void showAddExperienceDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        LayoutInflater inflater = requireActivity().getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialog_add_experience, null);
//
//        EditText edt_jobTitle = dialogView.findViewById(R.id.edt_jobTitle);
//        EditText edt_company = dialogView.findViewById(R.id.edt_company);
//        EditText edt_jobType = dialogView.findViewById(R.id.edt_jobType);
//        EditText edt_timeDuration = dialogView.findViewById(R.id.edt_timeDuration);
//        EditText edt_skills = dialogView.findViewById(R.id.edt_skills);
//
//        builder.setView(dialogView)
//                .setTitle("Add Experience")
//                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String jobTitle = edt_jobTitle.getText().toString();
//                        String company = edt_company.getText().toString();
//                        String jobType = edt_jobType.getText().toString();
//                        String timeDuration = edt_timeDuration.getText().toString();
//                        String skills = edt_skills.getText().toString();
//
//                        if (!jobTitle.isEmpty() && !company.isEmpty() && !jobType.isEmpty() && !timeDuration.isEmpty() && !skills.isEmpty()) {
//                            ExperienceClass experience = new ExperienceClass(jobTitle, company, jobType, timeDuration, skills);
//                            arrayList.add(experience);
//                            adapter.notifyDataSetChanged();
//
//                            // Save experiences
//                            saveExperiences();
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
//    private void saveExperiences() {
//        Set<String> experiencesSet = new HashSet<>();
//        for (ExperienceClass experience : arrayList) {
//            String experienceString = experience.getJobTitle() + "," + experience.getCompany() + "," + experience.getJobType() + "," + experience.getTimeDuration() + "," + experience.getSkills();
//            experiencesSet.add(experienceString);
//        }
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putStringSet("experiences", experiencesSet);
//        editor.apply();
//    }
//
//    private void clearSavedExperiences() {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.remove("experiences");
//        editor.apply();
//    }
//}


/////////////////////////////////////////////////////////////////////////////////////////////
package com.example.portfolioapp.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.portfolioapp.Adapter.ExperienceAdapter;
import com.example.portfolioapp.Model.ExperienceClass;
import com.example.portfolioapp.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ExperienceFragment extends Fragment {

    RecyclerView rv_experience;
    ImageButton btn_add, btn_delete;
    EditText edt_jobTitle, edt_timeDuration, edt_skills;
    ArrayList<ExperienceClass> arrayList = new ArrayList<>();
    ExperienceAdapter adapter;
    SharedPreferences sharedPreferences;
    Context context;

    public ExperienceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_experience, container, false);

        // find id...
        btn_add = view.findViewById(R.id.btn_add);
        btn_delete = view.findViewById(R.id.btn_delete);
        rv_experience = view.findViewById(R.id.rv_experience);


        // Load saved experiences
        sharedPreferences = context.getSharedPreferences("ExperiencePrefs", Context.MODE_PRIVATE);
        Set<String> savedExperiences = sharedPreferences.getStringSet("experiences", new HashSet<String>());
        for (String experienceString : savedExperiences) {
            String[] parts = experienceString.split(",");
            ExperienceClass experience = new ExperienceClass(parts[0], parts[1], parts[2]);
            arrayList.add(experience);
        }

        rv_experience.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ExperienceAdapter(context, arrayList);
        rv_experience.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddExperienceDialog();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllExperiences();
            }
        });

        return view;
    }

    private void showAddExperienceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_experience, null);

        EditText edt_jobTitle = dialogView.findViewById(R.id.edt_jobTitle);
        EditText edt_timeDuration = dialogView.findViewById(R.id.edt_timeDuration);
        EditText edt_skills = dialogView.findViewById(R.id.edt_skills);

        builder.setView(dialogView)
                .setTitle("Add Experience")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String jobTitle = edt_jobTitle.getText().toString();
                        String timeDuration = edt_timeDuration.getText().toString();
                        String skills = edt_skills.getText().toString();

                        if (!jobTitle.isEmpty() && !timeDuration.isEmpty() && !skills.isEmpty()) {
                            ExperienceClass experience = new ExperienceClass(jobTitle, timeDuration, skills);
                            arrayList.add(experience);
                            adapter.notifyDataSetChanged();

                            // Save experiences
                            saveExperiences();
                        } else {
                            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show();
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

    private void deleteAllExperiences() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete All Experiences")
                .setMessage("Are you sure you want to delete all experiences?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arrayList.clear();
                        adapter.notifyDataSetChanged();

                        // Clear saved experiences
                        clearSavedExperiences();
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

    private void saveExperiences() {
        Set<String> experiencesSet = new HashSet<>();
        for (ExperienceClass experience : arrayList) {
            String experienceString = experience.getJobTitle() + "," + experience.getTimeDuration() + "," + experience.getSkills();
            experiencesSet.add(experienceString);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("experiences", experiencesSet);
        editor.apply();
    }

    private void clearSavedExperiences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("experiences");
        editor.apply();
    }

//    private void showProfileDialog() {
//        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
//        LayoutInflater inflater = requireActivity().getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.custom_add_dialogbox2, null);
//
//        // Initialize views in the dialog
//        TextView dialogTitle = dialogView.findViewById(R.id.dialogTitle);
//        EditText dialogContent = dialogView.findViewById(R.id.dialogContent);
//        Button updateBtn = dialogView.findViewById(R.id.updateBtn);
//
//        // Set previous content
//        dialogImage.setImageDrawable(profileImage.getDrawable());
//        dialogTitle.setText(profileTitle.getText());
//        dialogContent.setText(profileContent.getText());
//
//        builder.setView(dialogView)
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//
//        androidx.appcompat.app.AlertDialog dialog = builder.create();
//
//        // Add update button functionality
//        updateBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Update profile content
//                String newContent = dialogContent.getText().toString();
//                profileContent.setText(newContent);
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//    }

}
