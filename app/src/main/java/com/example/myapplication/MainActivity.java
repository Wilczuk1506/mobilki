package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btn_login;
    EditText et_login;
    EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_login = findViewById(R.id.e1_btn_login);
        et_login = findViewById(R.id.e1_et_login);
        et_password = findViewById(R.id.e1_et_password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                User currentUser = GlobalData.getInstance().getUserByLogin(et_login.getText().toString());
                if (currentUser != null) { //User Exists
                    if (currentUser.GetPassword().equals(et_password.getText().toString())){ //Correct Password
                        Intent intent;

                        if(currentUser.GetIsAdmin()){ //If Admin
                            intent = new Intent(MainActivity.this, MainActivity2.class);
                        }
                        else {
                            intent = new Intent(MainActivity.this, MainActivity3.class);
                        }

                        intent.putExtra("currentIndex", GlobalData.getInstance().getIndexByLogin(currentUser.GetLogin()));

                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "User Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}