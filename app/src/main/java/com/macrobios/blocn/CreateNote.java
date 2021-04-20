package com.macrobios.blocn;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.macrobios.blocn.viewModel.CreateNoteViewModel.CreateNoteViewModel;

public class CreateNote extends Fragment {

    EditText etTitle;
    EditText etNote;

    TextView tvChCount;

    CreateNoteViewModel createNoteViewModel;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        createNoteViewModel = new ViewModelProvider(this).get(CreateNoteViewModel.class);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       etTitle = view.findViewById(R.id.etTitle);
       etNote = view.findViewById(R.id.etNote);
       tvChCount = view.findViewById(R.id.tvChCount);

       tvChCount.setText(Integer.toString(createNoteViewModel.getNumCharacter()) + "/255");

       etNote.setOnEditorActionListener(new TextView.OnEditorActionListener() {
           @Override
           public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
               Log.i("ACTION", Integer.toString(event.getAction()));
               return true;
           }
       });
    }
}