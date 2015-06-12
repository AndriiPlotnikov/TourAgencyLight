/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * wrapper for InnerMapping properties.
 * 
 * @author andre
 */
public class InnerMapping {
    // filepath for pathholder
    private final static String PATHS = "application\\properties\\Paths.properties";
    //private final static String PATHS = "D:\\javaProjects\\TestMyDAOinWeb\\src\\java\\application\\properties\\Paths.properties";
    // key to filepath
    private final static String PATH_TO_INNER_MAPPING = "InnerMapping";
    // singleton instance
    private static Properties instance;
    
    /**
     * return singleton instance of Properties object
     * 
     * @return singleton instance
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static Properties getInstance() throws FileNotFoundException, IOException{
        if( instance == null ){
            instance = new Properties();
            updateInstance();
        }
        return instance;
    }
    
    /**
     * searches for file and updates instance
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void updateInstance() throws FileNotFoundException, IOException{
            InputStream istream = InnerMapping.class.getClassLoader().getResourceAsStream(PATHS);
            Properties prop = new Properties();
            prop.load(istream); 
            String filename = prop.getProperty(PATH_TO_INNER_MAPPING);
            instance.load(new FileInputStream(filename));
    }
}
