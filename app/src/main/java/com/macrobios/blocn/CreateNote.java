package com.macrobios.blocn;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.macrobios.blocn.viewModel.CreateNoteViewModel.CreateNoteViewModel;

public class CreateNote extends Fragment implements View.OnClickListener{

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

        Principal.fab.setOnClickListener(this);
        Principal.fab.setImageResource(R.drawable.outline_save_24);
        etTitle = view.findViewById(R.id.etTitle);
        etNote = view.findViewById(R.id.etNote);
        tvChCount = view.findViewById(R.id.tvChCount);

        tvChCount.setText(Integer.toString(createNoteViewModel.getNumCharacter()) + "/255");

        etNote.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               if(s.length() <= 255){
                   createNoteViewModel.addCharacter(s.length());
                   tvChCount.setText(Integer.toString(createNoteViewModel.getNumCharacter()) + "/255");

               }else{
                   etNote.setText(etNote.getText().toString().substring(0, 255));
                   hideKeyboar(getActivity(), view);
                   Snackbar.make(view, "Just can type 255 characters", Snackbar.LENGTH_LONG).show();
               }
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });

    }

    private void hideKeyboar(Activity act, View v){
        InputMethodManager imm =
                (InputMethodManager) act.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "SAVE NOTE", Toast.LENGTH_SHORT).show();
    }
}