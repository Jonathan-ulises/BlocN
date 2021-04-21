package com.macrobios.blocn.viewModel.createNoteViewModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.macrobios.blocn.db.AdminSQLiteOpenHelper;

public class CreateNoteViewModel extends ViewModel {

    private int numCharacter;

    public int getNumCharacter(){return numCharacter;}

    public void addCharacter(int numCharacter){
        this.numCharacter = numCharacter;
    }

    /**
     * Method for insert notes
     * @param ctx Ejecution Context
     * @param tl Note´s title
     * @param nt Note's body
     */
    public void insertNote(@NonNull Context ctx, @NonNull String tl,@NonNull String nt){
        //Conexion con la base de datos-------------
        AdminSQLiteOpenHelper admin =
                new AdminSQLiteOpenHelper(ctx, "BlocNDB", null, 1);

        //Abrir la base de datos en modo lectura y escritura
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        //--------------------------------------------//

        String title, note = null;
        title = tl;
        note = nt;

        if(!title.isEmpty() || !note.isEmpty()){
            ContentValues dataNote = new ContentValues();
            dataNote.put("title", tl);
            dataNote.put("note", nt);

            baseDatos.insert("notes", null, dataNote);
            baseDatos.close();

            Toast.makeText(ctx, "New note has been created", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(ctx, "You can´t create empty notes", Toast.LENGTH_SHORT).show();
        }
    }


}
