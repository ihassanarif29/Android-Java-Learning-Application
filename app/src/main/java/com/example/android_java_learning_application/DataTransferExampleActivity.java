package com.example.android_java_learning_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DataTransferExampleActivity extends AppCompatActivity {


    static String KEY_FIRST_NAME = "FIRST_NAME";
    static String KEY_LAST_NAME = "LAST_NAME";
    static String KEY_EMAIL = "EMAIL";
    static String KEY_PHONE_NUMBER = "PHONE_NUMBER";
    static String KEY_CNIC = "CNIC";
    static String KEY_ADDRESS = "ADDRESS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data_transfer_example);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        EditText firstNameEditText = findViewById(R.id.editTextFirstName);
        EditText lastNameExitText = findViewById(R.id.editTextLastName);
        EditText emailEditText = findViewById(R.id.editTextEmail);
        EditText phoneNumberEditText = findViewById(R.id.editTextPhoneNumber);
        EditText cnicEditText = findViewById(R.id.editTextCNIC);
        EditText addressEditText = findViewById(R.id.editTextAddress);

        Button sendDataButton = findViewById(R.id.buttonSendData);
        sendDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameExitText.getText().toString();
                String email = emailEditText.getText().toString();
                String phoneNumber = phoneNumberEditText.getText().toString();
                String cnic = cnicEditText.getText().toString();
                String address = addressEditText.getText().toString();

                Intent intent = new Intent(DataTransferExampleActivity.this, DataReceivedExampleActivity.class);

                intent.putExtra(KEY_FIRST_NAME,firstName);
                intent.putExtra(KEY_LAST_NAME,lastName);
                intent.putExtra(KEY_EMAIL,email);
                intent.putExtra(KEY_PHONE_NUMBER,phoneNumber);
                intent.putExtra(KEY_CNIC,cnic);
                intent.putExtra(KEY_ADDRESS,address);
                startActivity(intent);
            }
        });

    }
}