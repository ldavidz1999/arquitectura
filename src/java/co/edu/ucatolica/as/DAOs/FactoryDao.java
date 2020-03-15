/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @http://rinconprogramadorcito.blogspot.com.co/2010/11/patron-dao-data-access-object.html
 */
package co.edu.ucatolica.as.DAOs;

/**
 *
 * @author sala5
 */
public abstract class FactoryDao {
    public static final int Oracle_FACTORY = 1;
    public static final int MYSQL_FACTORY = 2;

    public abstract PersonaMySQLDAO getPersonaDao();

    public static FactoryDao getFactory(int claveFactory){
        switch(claveFactory){
            case Oracle_FACTORY:
                return new OracleFactoryDao();
            case MYSQL_FACTORY:
                return new MySqlFactoryDao();
            default:
                throw new IllegalArgumentException();
        }
    }    
}
