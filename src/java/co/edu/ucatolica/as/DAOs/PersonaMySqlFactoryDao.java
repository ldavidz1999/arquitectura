/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucatolica.as.DAOs;

import co.edu.ucatolica.as.DTOs.Persona;
import co.edu.ucatolica.as.bds.MySqlDataSource;
import java.sql.Connection;

/**
 *
 * @author sala5
 */
public class PersonaMySqlFactoryDao {
    private Connection cn;

    public PersonaMySqlFactoryDao() {
        cn = MySqlDataSource.getConexionBD();
    }

    public Persona buscarUsuario(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void insertarUsuario(Persona usuario) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void modificarUsuario(Persona usuario) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
