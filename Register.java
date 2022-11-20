package com.f55121058_NiluhNiaDevi.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.f55121058_NiluhNiaDevi.sqlite.databinding.ActivityMainBinding;
import com.f55121058_NiluhNiaDevi.sqlite.databinding.ActivityRegisterBinding;

import utils.DatabaseHelper;

public class Register extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.btnLogin.setOnClickListener(view -> {
            Intent LoginIntent = new Intent(Register.this, MainActivity.class);
            startActivity(LoginIntent);
        });

        binding.btnRegister.setOnClickListener(view -> {
            String username = binding.edtUsername.getText().toString().trim();
            String password = binding.edtPassword.getText().toString().trim();
            String confPass = binding.edtConfirm.getText().toString().trim();

            if (password.equals(confPass)) {
                long val = databaseHelper.addUser(username, password);
                if (val > 0) {
                    Toast.makeText(Register.this,"You have Register", Toast.LENGTH_SHORT).show();
                    Intent moveToLogin = new Intent(Register.this, MainActivity.class);
                    startActivity(moveToLogin);
                }
            }
            else {
                Toast.makeText(Register.this,"Password is Not Matching", Toast.LENGTH_SHORT).show();
            }
        });
    }
}