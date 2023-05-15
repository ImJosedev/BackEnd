    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfoliojz.JZ.Security.Dto;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author aula medicina 2
 */
public class LoginUsuario {
    @NotBlank
    private String NombreUsuario;
    @NotBlank
    private String Password;

    //Getters & Setters

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
}
