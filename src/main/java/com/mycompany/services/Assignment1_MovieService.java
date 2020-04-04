/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.controller.AssignmentMovieJpaController;
import com.mycompany.models.AssignmentMovie;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.ws.soap.MTOM;


/**
 *
 * @author rishi
 */
@WebService(serviceName = "Assignment1_MovieService")
@MTOM(enabled=true,threshold = 1000000)
@HandlerChain(file = "Assignment1_MovieService_handler.xml")
public class Assignment1_MovieService {

    /**
     * This is a sample web service operation
     */
    
    @WebMethod(operationName = "addData")
    public AssignmentMovie addData(@WebParam(name = "addData") AssignmentMovie movie) {
            try{
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("Assignment1_MovieSoapService");           
                AssignmentMovieJpaController movieRepo = new AssignmentMovieJpaController(emf);
                 List<AssignmentMovie> movies = movieRepo.findAssignmentMovieEntities();
                ArrayList<BigDecimal> movieIds= new ArrayList<BigDecimal>();
                for(AssignmentMovie mov:movies){
                    movieIds.add(mov.getId());
                }  
                movie.setId(Collections.max(movieIds).add(BigDecimal.ONE));
                movieRepo.create(movie);
                return movie;
            } catch (Exception ex) {
            Logger.getLogger(Assignment1_MovieService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @WebMethod(operationName = "updateData")
    public boolean updateData(@WebParam(name = "updateData") AssignmentMovie movie) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Assignment1_MovieSoapService");
            AssignmentMovieJpaController movieRepo = new AssignmentMovieJpaController(emf);
            movieRepo.edit(movie);   
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Assignment1_MovieService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    @WebMethod(operationName = "removeData")
    public boolean removeData(@WebParam(name = "removeData") BigDecimal id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Assignment1_MovieSoapService");
            AssignmentMovieJpaController movieRepo = new AssignmentMovieJpaController(emf);
            movieRepo.destroy(id);   
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Assignment1_MovieService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //Read
    @WebMethod(operationName = "displayData")
    public List<AssignmentMovie> displayData(){
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Assignment1_MovieSoapService");
       
        AssignmentMovieJpaController movieRepo = new AssignmentMovieJpaController(emf);
        List<AssignmentMovie> findMovieEntities = movieRepo.findAssignmentMovieEntities();
        return findMovieEntities;
         }catch (Exception ex) {
            Logger.getLogger(Assignment1_MovieService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    //Find
    @WebMethod(operationName = "findData")
    public AssignmentMovie findData(BigDecimal id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Assignment1_MovieSoapService");
        AssignmentMovieJpaController movieRepo = new AssignmentMovieJpaController(emf);
        AssignmentMovie movie = movieRepo.findAssignmentMovie(id);
        return movie;
    }
}
