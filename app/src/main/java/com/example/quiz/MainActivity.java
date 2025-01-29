package com.example.quiz;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity{

    ListView listView;

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


        Question[] questionList = {
                new Question("What is the capital of Canada?", new String[]{"Toronto", "Ottawa", "Vancouver"}, 1, true),
                new Question("Which planet is known as the Red Planet?", new String[]{"Venus", "Jupiter", "Mars"}, 2, true),
                new Question("Who painted the Mona Lisa?", new String[]{"Vincent van Gogh", "Leonardo da Vinci", "Pablo Picasso"}, 1, true),
                new Question("Which ocean is the largest on Earth?", new String[]{"Atlantic Ocean", "Indian Ocean", "Pacific Ocean"}, 2, true),
                new Question("What is the chemical symbol for gold?", new String[]{"Au", "Ag", "Pb"}, 0, true),
                new Question("Who wrote 'Harry Potter'?", new String[]{"J.R.R. Tolkien", "J.K. Rowling", "George R.R. Martin"}, 1, true),
                new Question("How many continents are there on Earth?", new String[]{"5", "6", "7"}, 2, true),
                new Question("What is the fastest land animal?", new String[]{"Cheetah", "Lion", "Gazelle"}, 0, true),
                new Question("Which element is needed for breathing?", new String[]{"Hydrogen", "Oxygen", "Nitrogen"}, 1, true),
                new Question("Who invented the telephone?", new String[]{"Thomas Edison", "Alexander Graham Bell", "Nikola Tesla"}, 1, true),
                new Question("SUMMARY", new String[]{"SUMMARY", "SUMMARY", "SUMMARY"}, -1, false),
        };

        listView = findViewById(R.id.lvQuestionsList);
        CustomQuestionAdapter customQuestionAdapter = new CustomQuestionAdapter(getApplicationContext(), questionList);
        listView.setAdapter(customQuestionAdapter);
    }
}