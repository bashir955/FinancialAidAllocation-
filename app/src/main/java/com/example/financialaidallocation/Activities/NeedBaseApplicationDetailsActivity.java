package com.example.financialaidallocation.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialaidallocation.Adapters.NeedbaseImageAdapter;
import com.example.financialaidallocation.Classes.ApplicationSuggestionModel;
import com.example.financialaidallocation.Classes.EvidenceDocumentModel;
import com.example.financialaidallocation.Classes.SuggestionModel;
import com.example.financialaidallocation.R;

public class NeedBaseApplicationDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_need_base_application_details);


        TextView nameTextView = findViewById(R.id.detail_name);
        TextView aridTextView = findViewById(R.id.detail_arid);
        TextView fathername = findViewById(R.id.fathername);
        TextView FStatus = findViewById(R.id.fatherstatus);
        TextView semester = findViewById(R.id.Semester);
        TextView Cgpa = findViewById(R.id.Cgpa);
        TextView reason = findViewById(R.id.reason);
        TextView requiredamount = findViewById(R.id.req_amunt);
        TextView comment = findViewById(R.id.comment);
        TextView applicationstatus = findViewById(R.id.appli_status);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewforimages);

        // Get the application details from the intent
        ApplicationSuggestionModel application = (ApplicationSuggestionModel) getIntent().getSerializableExtra("application");

        if (application != null) {
            nameTextView.setText(application.getRe().getName());
            aridTextView.setText(application.getRe().getArid_no());
            fathername.setText(application.getRe().getFather_name());
            FStatus.setText(application.getRe().getFather_status());
            semester.setText(String.valueOf(application.getRe().getSemester()));
            Cgpa.setText(application.getRe().getCgpa());
            reason.setText(application.getRe().getReason());
            requiredamount.setText(application.getRe().getRequiredAmount());

            SuggestionModel suggestionModel = application.getSuggestionModel();
            if (suggestionModel != null) {
                comment.setText(suggestionModel.getComment());
            } else {
                comment.setText("No comment available");
            }
            applicationstatus.setText(application.getApplicationStatus());

        }
        // Setup RecyclerView
        if (application != null && application.getRe() != null && application.getRe().getEvidenceDocuments() != null) {
            NeedbaseImageAdapter imageAdapter = new NeedbaseImageAdapter(application.getRe().getEvidenceDocuments(), this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setAdapter(imageAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
}