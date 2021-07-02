package com.example.library.model.dto;

import com.example.library.model.User;
import com.example.library.model.Workplace;

public class LibrarianRegisterDto extends UserRegisterDto {

    private Workplace workplace;

    public Workplace getWorkplace() {
        return workplace;
    }

    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
    }
}
