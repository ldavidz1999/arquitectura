/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucatolica.as.controllers;

/**
 *
 * @author NixonD
 */

import co.edu.ucatolica.as.DAOs.FactoryDao;
import co.edu.ucatolica.as.DAOs.PersonaMySQLDAO;
import co.edu.ucatolica.as.DTOs.Persona;
import co.edu.ucatolica.as.bds.MySqlDataSource;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
 
@Controller
@RequestMapping("/")
public class PersonaControllers implements Controller {
    
@RequestMapping(method = RequestMethod.GET, value = "personaCrear.htm")
    public String processSubmit(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {

        System.out.println("personaCrear");
        model.put("mensajePersona", "Pase por el controller de Persona:::"+req.getParameter("nombre"));
        return "persona_crear";
    }    
    
@RequestMapping(method = RequestMethod.POST, value = "personaRegistrar.htm")
    public String processSubmit1(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {

        //PersonaMySQLDAO pDao = new PersonaMySQLDAO();
        FactoryDao MySqlFactory = FactoryDao.getFactory(FactoryDao.MYSQL_FACTORY);
            
        Logger.getLogger(PersonaControllers.class.getName()).log(Level.INFO, "Ejecutando processSubmit1...");

        //int id = pDao.obtenerId(MySqlDataSource.getConexionBD());
        String ident = req.getParameter("identificacion");
        String nombre1 = req.getParameter("nombre1");
        String nombre2 = req.getParameter("nombre2");
        String apellido1 = req.getParameter("apellido1");
        String apellido2 = req.getParameter("apellido2");
        String genero = req.getParameter("genero");
        String tipoP = req.getParameter("tipop");
        String fNacimiento = req.getParameter("fecha");
        String telef = req.getParameter("telefono");
        String email = req.getParameter("email");
        
        Persona p = new Persona();
        //p.setId(id);
        p.setIdentificacion(ident);
        p.setNombre1(nombre1);
        p.setNombre2(nombre2);
        p.setApellido1(apellido1);
        p.setApellido2(apellido2);
        p.setGenero(genero);
        p.setTipoP(tipoP);
        p.setfNacimiento(fNacimiento);
        p.setTelef(telef);
        p.setEmail(email);                                    
            
        //boolean insert = pDao.crearPersona(p, MySqlDataSource.getConexionBD());
        boolean insert = MySqlFactory.getPersonaDao().crearPersona(p, MySqlDataSource.getConexionBD());

        Logger.getLogger(PersonaControllers.class.getName()).log(Level.SEVERE, null, "Registrar + " + ident + "-" + insert);
        
        if (insert)
            model.put("mensaje", "El registro fue creado satisfactoriamente!!!");
        else
            model.put("mensaje", "El registro NO fue creado, consulte con el administrador...");
        
        return "persona_crear";
    }     
    
@RequestMapping(method = RequestMethod.GET, value = "personaConsultar.htm")
    public String processSubmit2(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {      
        Logger.getLogger(PersonaControllers.class.getName()).log(Level.INFO, "Ejecutando processSubmit2...");
        return "persona_consultar";
    } 
    
@RequestMapping(method = RequestMethod.POST, value = "personaConsultarForm.htm")
    public String processSubmit3(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {

        PersonaMySQLDAO pDao = new PersonaMySQLDAO();
            
        Logger.getLogger(PersonaMySQLDAO.class.getName()).log(Level.INFO, "Ejecutando processSubmit3...");

        //int id = pDao.obtenerId(MySqlDataSource.getConexionBD());
        String ident = req.getParameter("identificacion");
        String nombre1 = req.getParameter("nombre1");
        
        Persona p = new Persona();
        //p.setId(id);
        p.setIdentificacion(ident);
        p.setNombre1(nombre1);
            
        List<Persona> datos = pDao.consultarPersona(p, MySqlDataSource.getConexionBD());

        Logger.getLogger(PersonaControllers.class.getName()).log(Level.SEVERE, null, "Consultar + " + ident + "-" + datos.size());
        
        model.put("listaPersonas", datos);
        if (datos.size() > 0)
            model.put("mensaje", "La consulta se realizo satisfactoriamente!!!" + datos.size());
        else
            model.put("mensaje", "La consulta NO tiene resultados...");
        
        return "persona_consultar";
    }     
    
@RequestMapping(method = RequestMethod.GET, value = "personaEditar.htm")
    public String processSubmit4(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {      
        Logger.getLogger(PersonaControllers.class.getName()).log(Level.INFO, "Ejecutando processSubmit4...");
        return "persona_editar";
    } 
    
@RequestMapping(method = RequestMethod.POST, value = "personaEditarForm1.htm")
    public String processSubmit5(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {

        PersonaMySQLDAO pDao = new PersonaMySQLDAO();
            
        Logger.getLogger(PersonaMySQLDAO.class.getName()).log(Level.INFO, "Ejecutando processSubmit5...");

        //int id = pDao.obtenerId(MySqlDataSource.getConexionBD());
        String ident = req.getParameter("identificacion");
        String nombre1 = req.getParameter("nombre1");
        
        Persona p = new Persona();
        //p.setId(id);
        p.setIdentificacion(ident);
        p.setNombre1(nombre1);
            
        List<Persona> datos = pDao.consultarPersona(p, MySqlDataSource.getConexionBD());

        Logger.getLogger(PersonaControllers.class.getName()).log(Level.SEVERE, null, "Consultar + " + ident + "-" + datos.size());
        
        model.put("listaPersonas", datos);
        
        
        return "persona_editar";
        
    }    
    
@RequestMapping(method = RequestMethod.POST, value = "personaEditarForm2.htm")
    public String processSubmit6(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {

        PersonaMySQLDAO pDao = new PersonaMySQLDAO();
            
        Logger.getLogger(PersonaMySQLDAO.class.getName()).log(Level.INFO, "Ejecutando processSubmit6...");

        int id = Integer.parseInt(req.getParameter("id"));
        String ident = req.getParameter("identificacion");
        String nombre1 = req.getParameter("nombre1");
        String nombre2 = req.getParameter("nombre2");
        String apellido1 = req.getParameter("apellido1");
        String apellido2 = req.getParameter("apellido2");
        String genero = req.getParameter("genero");
        String tipoP = req.getParameter("tipop");
        String fNacimiento = req.getParameter("fecha");
        String telef = req.getParameter("telefono");
        String email = req.getParameter("email");
        
        Logger.getLogger(PersonaMySQLDAO.class.getName()).log(Level.INFO, "Id persona: " + id);
        
        Persona p = new Persona();
        p.setId(id);
        p.setIdentificacion(ident);
        p.setNombre1(nombre1);
        p.setNombre2(nombre2);
        p.setApellido1(apellido1);
        p.setApellido2(apellido2);
        p.setGenero(genero);
        p.setTipoP(tipoP);
        p.setfNacimiento(fNacimiento);
        p.setTelef(telef);
        p.setEmail(email);
            
        boolean res = pDao.editarPersona(p, MySqlDataSource.getConexionBD());                         
        
        if (res)
            model.put("mensaje", "Se edito satisfactoriamente!!!");
        else
            model.put("mensaje", "NO se guardaron los cambios...");
        
        return "persona_editar";
        
    }    

    @Override
    public String value() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}