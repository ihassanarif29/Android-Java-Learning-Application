package com.example.android_java_learning_application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DataReceivedExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data_received_example);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String firstName = intent.getStringExtra(DataTransferExampleActivity.KEY_FIRST_NAME);
        String lastName = intent.getStringExtra(DataTransferExampleActivity.KEY_LAST_NAME);
        String email = intent.getStringExtra(DataTransferExampleActivity.KEY_EMAIL);
        String phoneNumber = intent.getStringExtra(DataTransferExampleActivity.KEY_PHONE_NUMBER);
        String cnic = intent.getStringExtra(DataTransferExampleActivity.KEY_CNIC);
        String address = intent.getStringExtra(DataTransferExampleActivity.KEY_ADDRESS);

        TextView userDetailTextView = findViewById(R.id.textViewUserDetail);
        userDetailTextView.setText("User "+firstName+"  "+lastName+"having CNIC number "+cnic+" is register on email "+email+" as a new user if there is any miss information kindly contact on following number "+phoneNumber+" or its home address "+address+" .");

    }
}