package com.macrobios.blocn.rcvAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        public Note mItem;

        public ViewHolder(View view) {
            super(view);

            mView = view;
            tvTitle = view.findViewById(R.id.tvTitle);
            tvNote = view.findViewById(R.id.tvNote);
        }
    }
}
