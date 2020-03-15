/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucatolica.as.DAOs;

import co.edu.ucatolica.as.DTOs.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nixoduaa
 */
public class PersonaMySQLDAO implements PersonaDAO{
    
    
    
    public boolean crearPersona(Persona p, Connection con)
    {
        PreparedStatement pstmt = null;
        boolean respuesta = false;
        try {            
            
            Logger.getLogger(PersonaMySQLDAO.class.getName()).log(Level.INFO, "Ejecutando crearPersona...");
            
            pstmt = con.prepareStatement("INSERT INTO persona (identificacion, nombre_1,nombre_2, "
                    + " apellido_1, apellido_2, genero, telefono, email, "
                    + " fecha_nacimiento, tipo_persona) "
                    + " VALUES (?,?,?,?,?,?,?,?,?,?)");
            
            //pstmt.setInt(1,p.getId());
            pstmt.setString(1, p.getIdentificacion());
            pstmt.setString(2, p.getNombre1());
            pstmt.setString(3, p.getNombre2());
            pstmt.setString(4, p.getApellido1());
            pstmt.setString(5, p.getApellido2());
            pstmt.setString(6, p.getGenero());
            pstmt.setString(7, p.getTelef());
            pstmt.setString(8, p.getEmail());
            pstmt.setString(9, p.getfNacimiento());
            pstmt.setString(10, p.getTipoP());
            
            pstmt.execute();
            
            con.close();
            
            respuesta = true;
        } catch (SQLException ex) {
            Logger.getLogger(PersonaMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return respuesta;

    }

    public ArrayList<Persona> consultarPersona(Persona p, Connection con)
    {
        
        ArrayList<Persona> datos = new ArrayList();
        
        Logger.getLogger(PersonaMySQLDAO.class.getName()).log(Level.INFO, "Ejecutando consultarPersona...");
        
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery ("select identificacion, nombre_1,nombre_2, "
                    + " apellido_1, apellido_2, genero, telefono, email, "
                    + " fecha_nacimiento, tipo_persona, id_persona "
                    + " from persona "
                    + " where "
                    + " identificacion='" + p.getIdentificacion()+"'"
                    + " AND nombre_1='"+p.getNombre1()+"'");
            
            while (rs.next())
            { 
                Persona per = new Persona();
                per.setIdentificacion(rs.getString(1));
                per.setNombre1(rs.getString(2));
                per.setNombre2(rs.getString(3));
                per.setApellido1(rs.getString(4));
                per.setApellido2(rs.getString(5));
                per.setGenero(rs.getString(6));
                per.setTelef(rs.getString(7));
                per.setEmail(rs.getString(8));
                per.setfNacimiento(rs.getString(9));
                per.setTipoP(rs.getString(10));
                per.setId(rs.getInt(11));
                
                datos.add(per);
                
            }
            
            Logger.getLogger(PersonaMySQLDAO.class.getName()).log(Level.INFO, "Ejecutando consultarPersona fin..." + datos.size());
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonaMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return datos;
    }
          
    public boolean editarPersona(Persona p, Connection con)
    {
        PreparedStatement pstmt = null;
        boolean respuesta = false;
        try {            
            
            Logger.getLogger(PersonaMySQLDAO.class.getName()).log(Level.INFO, "Ejecutando editarPersona...");
            
            pstmt = con.prepareStatement("UPDATE persona "
                    + " SET "
                    + " identificacion=?"
                    + " , nombre_1=?"
                    + " , nombre_2=?"
                    + " where id_persona=?");
                        
            pstmt.setString(1, p.getIdentificacion());
            pstmt.setString(2, p.getNombre1());
            pstmt.setString(3, p.getNombre2());
            pstmt.setInt(4, p.getId());
            
            pstmt.executeUpdate();
            
            con.close();
            
            respuesta = true;
        } catch (SQLException ex) {
            Logger.getLogger(PersonaMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return respuesta;

    }
}
