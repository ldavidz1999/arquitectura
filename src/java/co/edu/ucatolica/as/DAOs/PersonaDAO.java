/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucatolica.as.DAOs;

import co.edu.ucatolica.as.DTOs.Persona;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author sala5
 */
public interface PersonaDAO {
    public boolean crearPersona(Persona p, Connection con);
    public ArrayList<Persona> consultarPersona(Persona p, Connection con);
    public boolean editarPersona(Persona p, Connection con);
}
