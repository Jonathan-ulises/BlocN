package com.macrobios.blocn.viewModel.CreateNoteViewModel;

import androidx.lifecycle.ViewModel;

public class CreateNoteViewModel extends ViewModel {

    private int numCharacter;

    public int getNumCharacter(){return numCharacter;}

    public void addCharacter(int numCharacter){
        this.numCharacter = numCharacter;
    }
}
