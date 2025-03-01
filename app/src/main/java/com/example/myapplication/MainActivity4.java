package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity4 extends AppCompatActivity {

    Button btn_edit;
    EditText et_oldPassword;
    EditText et_newPassword;
    User loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_edit = findViewById(R.id.e4_btn_edit);
        et_oldPassword = findViewById(R.id.e4_et_oldPass);
        et_newPassword = findViewById(R.id.e4_et_newPass);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            loggedInUser = GlobalData.getInstance().getUserByLogin(extras.getString("currentLogin"));
        }
        else {
            loggedInUser = null;
        }

        btn_edit.setEnabled(false);
        et_oldPassword.setEnabled(false);

        et_oldPassword.setText(loggedInUser.GetPassword());
        et_newPassword.setText(loggedInUser.GetPassword());

        et_newPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btn_edit.setEnabled(!
                        et_oldPassword.getText().toString().equals(et_newPassword.getText().toString()) &&
                        !et_newPassword.getText().toString().isBlank()
                );
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = loggedInUser;
                user.SetPassword(et_newPassword.getText().toString());
                GlobalData.getInstance().replaceUserByLogin("Admin", user);

                GlobalData.getInstance().setFirstLogin(false);

                Intent intent = new Intent(MainActivity4.this, MainActivity2.class);
                intent.putExtra("currentLogin", loggedInUser.GetLogin());

                startActivity(intent);
            }
        });

    }
}