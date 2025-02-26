package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    Button btn_exit;
    Button btn_add;
    Button btn_remove;
    Button btn_edit;
    EditText et_login;
    EditText et_password;
    EditText et_firstName;
    EditText et_lastName;
    CheckBox cb_isAdmin;
    ListView lv_loginList;
    ArrayList<String> logins;
    ArrayAdapter<String> adapter;
    User selecterUser = null;
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_exit = findViewById(R.id.e2_btn_exit);
        btn_add = findViewById(R.id.e2_btn_add);
        btn_remove = findViewById(R.id.e2_btn_remove);
        btn_edit = findViewById(R.id.e2_btn_edit);

        et_login = findViewById(R.id.e2_et_login);
        et_password = findViewById(R.id.e2_et_password);
        et_firstName = findViewById(R.id.e2_et_firstName);
        et_lastName = findViewById(R.id.e2_et_lastName);

        cb_isAdmin = findViewById(R.id.e2_cb_isAdmin);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            currentUser = GlobalData.getInstance().getUserByIndex(extras.getInt("currentIndex"));
        }
        else {
            currentUser = null;
        }

        lv_loginList = findViewById(R.id.e2_lv_loginList);
        logins = new ArrayList<String>();

        for (User user: GlobalData.getInstance().getUsers()) {
            logins.add(user.GetFirstName() + (user.GetIsAdmin() ? " *" : " ") + user.GetLastName());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, logins);
        lv_loginList.setAdapter(adapter);

        btn_remove.setEnabled(false);
        btn_edit.setEnabled(false);
        btn_add.setEnabled(false);
        et_login.setEnabled(false);

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExitToE1();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddUser();
            }
        });

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                AreTextFieldsEmpty();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        et_password.addTextChangedListener(textWatcher);
        et_firstName.addTextChangedListener(textWatcher);
        et_lastName.addTextChangedListener(textWatcher);
    }

    private void ExitToE1(){
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        intent.removeExtra("currentIndex");
        startActivity(intent);
    }

    private void AreTextFieldsEmpty(){
        if (!(et_password.getText().toString().isBlank() || et_firstName.getText().toString().isBlank() || et_lastName.getText().toString().isBlank())){
            btn_add.setEnabled(et_login.getText().toString().isBlank());
        }
        else {
            btn_add.setEnabled(false);
        }
    }

    private void AddUser(){
        User user = new User(
                et_firstName.getText().toString(),
                et_lastName.getText().toString(),
                "",
                et_password.getText().toString(),
                cb_isAdmin.isChecked()
        );

        if (GlobalData.getInstance().getUserByLogin(user.GetLogin()) != null) {
            Toast.makeText(MainActivity2.this, "User already exists", Toast.LENGTH_SHORT).show();
            return;
        }

        GlobalData.getInstance().addUser(user);
        logins.add(user.GetFirstName() + (user.GetIsAdmin() ? " *" : " ") + user.GetLastName());

        adapter.notifyDataSetChanged();

        ClearInputs();
    }

    private void ClearInputs(){
        et_firstName.setText("");
        et_lastName.setText("");
        et_login.setText("");
        et_password.setText("");
        cb_isAdmin.setChecked(false);
    }
}