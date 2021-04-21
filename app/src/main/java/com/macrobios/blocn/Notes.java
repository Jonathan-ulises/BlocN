package com.macrobios.blocn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.macrobios.blocn.model.Note;
import com.macrobios.blocn.rcvAdapter.MyNoteAdapter;
import com.macrobios.blocn.viewModel.notesViewModel.NotesViewModel;

import java.util.ArrayList;
import java.util.List;

public class Notes extends Fragment implements View.OnClickListener{

    NavController navController;
    RecyclerView rcvNotes;
    MyNoteAdapter adapterNotes;
    NotesViewModel notesViewModel;
    List<Note> mListN = new ArrayList<>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        mListN = notesViewModel.getListNotes(getContext());
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        Principal.fab.setOnClickListener(this::onClick);
        Principal.fab.setImageResource(R.drawable.outline_add_24);

        rcvNotes = view.findViewById(R.id.rcvNotes);
        rcvNotes.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapterNotes = new MyNoteAdapter(mListN, notesViewModel);
        rcvNotes.setAdapter(adapterNotes);
    }

    @Override
    public void onClick(View v) {
        navController.navigate(R.id.action_FirstFragment_to_SecondFragment);
    }
}