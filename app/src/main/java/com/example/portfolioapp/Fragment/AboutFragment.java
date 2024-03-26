package com.example.portfolioapp.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.portfolioapp.R;

public class AboutFragment extends Fragment {

    LinearLayout profileLayout;
    ImageView profileImage;
    TextView profileTitle, profileContent;

    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment...
        View view = inflater.inflate(R.layout.fragment_about, container, false);

//      find id...
        profileLayout = view.findViewById(R.id.profileLayout);
        profileImage = view.findViewById(R.id.profileImage);
        profileTitle = view.findViewById(R.id.profileTitle);
        profileContent = view.findViewById(R.id.profileContent);

        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show dialog to add/update profile
                showProfileDialog();
            }
        });

        return view;
    }

    private void showProfileDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_add_dialogbox, null);

        // Initialize views in the dialog
        ImageView dialogImage = dialogView.findViewById(R.id.dialogImage);
        TextView dialogTitle = dialogView.findViewById(R.id.dialogTitle);
        EditText dialogContent = dialogView.findViewById(R.id.dialogContent);
        Button updateBtn = dialogView.findViewById(R.id.updateBtn);

        // Set previous content
        dialogImage.setImageDrawable(profileImage.getDrawable());
        dialogTitle.setText(profileTitle.getText());
        dialogContent.setText(profileContent.getText());

        builder.setView(dialogView)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();

        // Add update button functionality
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update profile content
                String newContent = dialogContent.getText().toString();
                profileContent.setText(newContent);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}