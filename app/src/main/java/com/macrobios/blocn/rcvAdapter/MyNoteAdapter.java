package com.macrobios.blocn.rcvAdapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.macrobios.blocn.CreateNote;
import com.macrobios.blocn.R;
import com.macrobios.blocn.model.Note;
import com.macrobios.blocn.viewModel.createNoteViewModel.CreateNoteViewModel;
import com.macrobios.blocn.viewModel.notesViewModel.NotesViewModel;

import java.util.List;

public class MyNoteAdapter extends RecyclerView.Adapter<MyNoteAdapter.ViewHolder> {

    private final List<Note> mNoteList;
    private NotesViewModel notesViewModel;

    public MyNoteAdapter(List<Note> mNoteList, NotesViewModel mViewModel) {
        this.mNoteList = mNoteList;
        this.notesViewModel = mViewModel;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mNoteList.get(position);
        holder.tvTitle.setText(holder.mItem.getTitle());
        holder.tvNote.setText(holder.mItem.getNoteBody());

        holder.imgbtnEdit.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                NavController navController = Navigation.findNavController(holder.mView);
                navController.navigate(R.id.action_FirstFragment_to_SecondFragment);
                return false;
            }
        });

        holder.imgbtnDelete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog dialog = createDialog(v, holder.mItem).create();
                dialog.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        public final TextView tvTitle;
        public final TextView tvNote;
        public final ImageButton imgbtnEdit;
        public final ImageButton imgbtnDelete;
        public Note mItem;

        public ViewHolder(View view) {
            super(view);

            mView = view;
            tvTitle = view.findViewById(R.id.tvTitle);
            tvNote = view.findViewById(R.id.tvNote);
            imgbtnEdit = view.findViewById(R.id.imgbtnEdit);
            imgbtnDelete = view.findViewById(R.id.imgbtnDelete);

        }


    }

    /**
     * Method to create a Alert Dialog
     * @param v Execution context view
     * @return AlertDialogBuilder
     */
    public AlertDialog.Builder createDialog(View v, Note n){
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Delete");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                notesViewModel.deleteNote(v.getContext(), n.getId());
                Toast.makeText(v.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(v.getContext(), "Nice :V", Toast.LENGTH_SHORT).show();
            }
        });

        return builder;
    }
}
