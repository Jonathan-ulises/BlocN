package com.macrobios.blocn.viewModel.notesViewModel;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.macrobios.blocn.db.AdminSQLiteOpenHelper;
import com.macrobios.blocn.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesViewModel extends ViewModel {

    private List<Note> listNotes;

    /**
     * Method to get a List Notes
     * @param ctx Execution Context
     * @return List of Notes
     */
    public List<Note> getListNotes(Context ctx){
        List<Note> mNotesList = new ArrayList<>();

        AdminSQLiteOpenHelper admin =
                new AdminSQLiteOpenHelper(ctx, "BlocNDB", null, 1);

        SQLiteDatabase baseDatos = admin.getReadableDatabase();
        Cursor fila = baseDatos.rawQuery("SELECT * FROM notes", null);

        if(fila.moveToFirst()){
            while(fila.moveToNext()){
                Note objN = new Note();
                objN.setId(fila.getInt(fila.getColumnIndex("id")));
                objN.setTitle(fila.getString(fila.getColumnIndex("title")));
                objN.setNoteBody(fila.getString(fila.getColumnIndex("note")));
                mNotesList.add(objN);
            }
            baseDatos.close();
        }else{
            Toast.makeText(ctx, "Notes Empty", Toast.LENGTH_SHORT).show();
            baseDatos.close();
        }

        return mNotesList;
    }

    public void updateNote(
            @NonNull Context ctx, @NonNull String tl,
            @NonNull String nt, @NonNull int id){

    }

    public void deleteNote(@NonNull Context ctx, @NonNull int id){
        AdminSQLiteOpenHelper admin =
                new AdminSQLiteOpenHelper(ctx, "BlocNDB", null, 1);

        SQLiteDatabase baseDatos = admin.getWritableDatabase();

        /*
        El metodo delete retorna la cantidad de registros eliminados
         */
        int cantidad = baseDatos.delete("notes", "id = " + id,null);
        baseDatos.close();

    }
}
