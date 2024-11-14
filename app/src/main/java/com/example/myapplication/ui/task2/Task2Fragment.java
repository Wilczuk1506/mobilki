package com.example.myapplication.ui.task2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Task2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Task2Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int currentIndex = 0;


    public Task2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Task2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Task2Fragment newInstance(String param1, String param2) {
        Task2Fragment fragment = new Task2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_task2, container, false);

        Button btPrev = rootView.findViewById(R.id.buttonPrev);
        Button btNext = rootView.findViewById(R.id.buttonNext);
        TextView imgDesc = rootView.findViewById(R.id.textViewDesc);
        ImageView img = rootView.findViewById(R.id.imageViewImage);

        Drawable[] drawables = new Drawable[]{
                ContextCompat.getDrawable(getActivity(), R.drawable.img1),
                ContextCompat.getDrawable(getActivity(), R.drawable.img2),
                ContextCompat.getDrawable(getActivity(), R.drawable.img3),
                ContextCompat.getDrawable(getActivity(), R.drawable.img4),
                ContextCompat.getDrawable(getActivity(), R.drawable.img5),
                ContextCompat.getDrawable(getActivity(), R.drawable.img6),
                ContextCompat.getDrawable(getActivity(), R.drawable.img7),
        };

        String[] descriptions = new String[]{
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sollicitudin lorem volutpat, tempor nunc nec, bibendum sapien. Praesent ut lacinia turpis. Duis a quam ut tortor varius lacinia quis ut nibh. Praesent elementum eget ipsum ut tempor. Suspendisse imperdiet nisl vitae elit mattis bibendum.",
                "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed semper, lorem sed sagittis eleifend, lorem libero eleifend quam, at porttitor nibh purus id mauris. Nulla egestas et mi sit amet pharetra. In a dui sed arcu faucibus efficitur. Etiam id facilisis urna.",
                "Nulla facilisi. Aliquam varius nibh ut massa aliquam, ac faucibus elit aliquam. Nullam ullamcorper rhoncus blandit. Ut in sapien eu tortor sagittis porta id nec metus. Quisque cursus ullamcorper justo eu rutrum. Phasellus eget libero ante. Pellentesque lectus orci, facilisis vel justo semper, efficitur malesuada nisl.",
                "Sed feugiat mauris accumsan est commodo, et luctus elit feugiat. Nam eu justo consectetur, tristique nisl rutrum, efficitur metus. Mauris luctus commodo turpis efficitur pellentesque.",
                "Sed ullamcorper, lectus non facilisis aliquam, ipsum lectus rutrum mauris, a ultricies ipsum erat nec justo. Morbi consectetur velit a risus vehicula, semper vestibulum orci porta.",
                "Quisque sed dui nisi. Suspendisse venenatis fringilla ultricies. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Cras tellus arcu, volutpat vel congue a, cursus vitae sem.",
                "Donec iaculis pellentesque maximus. Aliquam erat volutpat. Donec ullamcorper, ante ac elementum posuere, eros urna convallis quam, et sollicitudin purus massa vitae ligula. Proin euismod scelerisque ipsum eget molestie. Morbi a dolor pulvinar, egestas tellus sed, lobortis elit. Mauris sagittis porta auctor."
        };

        btPrev.setEnabled(false);
        img.setImageDrawable(drawables[currentIndex]);
        imgDesc.setText(descriptions[currentIndex]);

        btPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex -= 1;

                if(currentIndex == 0){
                    btPrev.setEnabled(false);
                }
                if(currentIndex == 5){
                    btNext.setEnabled(true);
                }

                img.setImageDrawable(drawables[currentIndex]);

                imgDesc.setText(descriptions[currentIndex]);
            }
        });

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex += 1;

                if(currentIndex == 1){
                    btPrev.setEnabled(true);
                }
                if(currentIndex == 6){
                    btNext.setEnabled(false);
                }

                img.setImageDrawable(drawables[currentIndex]);

                imgDesc.setText(descriptions[currentIndex]);
            }
        });

        return rootView;
    }
}