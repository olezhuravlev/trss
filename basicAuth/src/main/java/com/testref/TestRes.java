package com.testref;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class TestRes {
    
    public static void main(String... args) {
        
        System.out.println("Begin");
        
        try {
            //testResourceFromClasspath();
            //testResourceLoadFromJar();
            //testRelativeResourceFromJar();
            //testDefaultClassLoader();
            testDifferentPathNames();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("End");
    }
    
    private static void testResourceFromClasspath() throws IOException, URISyntaxException {
        
        InputStream inputStream = TestRes.class.getResourceAsStream("/postgresql.properties");
        
        Properties properties = new Properties();
        properties.load(inputStream);
        
        Object driver = properties.get("pgsql.driver");
        Object jdbcUrl = properties.get("pgsql.jdbcUrl");
        Object username = properties.get("pgsql.username");
        Object password = properties.get("pgsql.password");
    }
    
    public static void testResourceLoadFromJar() throws IOException, URISyntaxException {
        
        URL url = TestRes.class.getResource("/test.jar");
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { url.toURI().toURL() });
        
        ClassPathResource classPathResource = new ClassPathResource("postgresql.properties", classLoader);
        String protocol = classPathResource.getURL().getProtocol();
        
        InputStream inputStream = classPathResource.getInputStream();
        
        Properties properties = new Properties();
        properties.load(inputStream);
        
        Object driver = properties.get("pgsql.driver");
        Object jdbcUrl = properties.get("pgsql.jdbcUrl");
        Object username = properties.get("pgsql.username");
        Object password = properties.get("pgsql.password");
    }
    
    public static void testRelativeResourceFromJar() throws IOException, URISyntaxException {
        
        URL url = TestRes.class.getResource("/test.jar");
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { url.toURI().toURL() });
        
        ClassPathResource classPathResource = new ClassPathResource("postgresql.properties", classLoader);
        String protocol = classPathResource.getURL().getProtocol();
        
        InputStream inputStream = classPathResource.getInputStream();
        
        Properties properties = new Properties();
        properties.load(inputStream);
        
        Object driver = properties.get("pgsql.driver");
        Object jdbcUrl = properties.get("pgsql.jdbcUrl");
        Object username = properties.get("pgsql.username");
        Object password = properties.get("pgsql.password");
        
        Resource relativeResource = classPathResource.createRelative("obsolete/mysqldb.properties");
        InputStream relativeInputStream = relativeResource.getInputStream();
        Properties obsoleteProperties = new Properties();
        obsoleteProperties.load(relativeInputStream);
        
        Object obsoleteDriver = obsoleteProperties.get("mysql.driver");
        Object obsoleteJdbcUrl = obsoleteProperties.get("mysql.jdbcUrl");
        Object obsoleteUsername = obsoleteProperties.get("mysql.username");
        Object obsoletePassword = obsoleteProperties.get("mysql.password");
    }
    
    public static void testDefaultClassLoader() throws IOException, URISyntaxException {
        
        try {
            
            URL url = TestRes.class.getResource("/test.jar");
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { url.toURI().toURL() });
            
            Thread.currentThread().setContextClassLoader(classLoader);
            
            List<String> result = retrieveParameters("postgresql.properties", null);
            
            System.out.println(result.toString());
            
        } finally {
            Thread.currentThread().setContextClassLoader(null);
        }
    }
    
    private static List<String> retrieveParameters(String fileName, ClassLoader classLoader) throws IOException {
        
        ClassPathResource classPathResource;
        if (classLoader != null) {
            classPathResource = new ClassPathResource(fileName, classLoader);
        } else {
            classPathResource = new ClassPathResource(fileName);
        }
        
        InputStream inputStream = classPathResource.getInputStream();
        
        Properties properties = new Properties();
        properties.load(inputStream);
        
        List<String> result = new ArrayList();
        
        for (Map.Entry entry : properties.entrySet()) {
            result.add((String) entry.getValue());
        }
        
        return result;
    }
    
    public static void testDifferentPathNames() throws IOException, URISyntaxException {
        
        // The path is automatically normalized by Spring.
        URL url = TestRes.class.getResource("/test.jar");
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { url.toURI().toURL() });
        
        List<String> parameters = retrieveParameters("obsolete/../obsolete/mysqldb.properties", classLoader);
        System.out.println(parameters.toString());
        
        parameters = retrieveParameters("obsolete\\mysqldb.properties", classLoader);
        System.out.println(parameters.toString());
        
        parameters = retrieveParameters("\\obsolete\\mysqldb.properties", classLoader);
        System.out.println(parameters.toString());
        
        parameters = retrieveParameters("/obsolete/mysqldb.properties", classLoader);
        System.out.println(parameters.toString());
        
        parameters = retrieveParameters("./obsolete/mysqldb.properties", classLoader);
        System.out.println(parameters.toString());
        
        parameters = retrieveParameters("obsolete/../obsolete/./mysqldb.properties", classLoader);
        System.out.println(parameters.toString());
    }
}
