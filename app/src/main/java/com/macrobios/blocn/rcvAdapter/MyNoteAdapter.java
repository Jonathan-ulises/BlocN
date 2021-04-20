package com.macrobios.blocn.rcvAdapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.macrobios.blocn.R;
import com.macrobios.blocn.model.Note;

import java.util.List;

public class MyNoteAdapter extends RecyclerView.Adapter<MyNoteAdapter.ViewHolder> {

    private final List<Note> mNoteList;

    public MyNoteAdapter(List<Note> mNoteList) {
        this.mNoteList = mNoteList;
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

            imgbtnEdit.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    NavController navController = Navigation.findNavController(mView);
                    navController.navigate(R.id.action_FirstFragment_to_SecondFragment);
                    return false;
                }
            });

            imgbtnDelete.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog dialog = createDialog(v).create();
                    dialog.show();
                    return false;
                }
            });
        }

        /**
         * Method to create a Alert Dialog
         * @param v Execution context view
         * @return AlertDialogBuilder
         */
        public AlertDialog.Builder createDialog(View v){
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Delete");
            builder.setMessage("Are you sure?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(v.getContext(), "U_U", Toast.LENGTH_SHORT).show();
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
}
