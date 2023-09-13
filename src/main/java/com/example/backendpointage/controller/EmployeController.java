package com.example.backendpointage.controller;

import com.example.backendpointage.Service.EmployeService;
import com.example.backendpointage.dao.model.Employe;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// ...



@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class EmployeController {

    private EmployeService employeService;
    @GetMapping("/getAllEmployes")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<List<Employe>> getAllEmployes(){
        try{
            List<Employe> employeList = new ArrayList<>();
            employeService.listEmploye().forEach(employeList::add);

            if (employeList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(employeList,HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getEmployeById/{id}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")

    public ResponseEntity<Employe> getAllEmployesById(@PathVariable Long id){
        Employe byId = employeService.getById(id);

        if (byId!=null){
            return new ResponseEntity<>(byId,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("getAllEmployes/search")
    @PreAuthorize("hasAuthority('SCOPE_USER')")

    public Employe searchEmployes(@RequestParam("keyword") String keyword) {
      return employeService.findByNomContains("%"+keyword+"%");
    }

    @PostMapping("/addEmploye")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")

    public ResponseEntity<Employe> addEmploye(@RequestBody  Employe employe ){
       Employe employeObj =employeService.save(employe);

       return new ResponseEntity<>(employeObj, HttpStatus.OK);
    }
    @PostMapping("/updateEmployeById/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")

    public ResponseEntity<Employe> updateEmployeById(@PathVariable Long id, @RequestBody Employe newemployeData){
        Employe employeById = employeService.getById(id);
        if (employeById!=null){

            employeById.setCin(newemployeData.getCin());
            employeById.setEmail(newemployeData.getEmail());
            employeById.setNom(newemployeData.getNom());
            employeById.setPrenom(newemployeData.getPrenom());
            employeById.setDateN(newemployeData.getDateN());
            employeById.setTel(newemployeData.getTel());

           Employe employeObj=employeService.save(employeById);
           return new ResponseEntity<>(employeObj, HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/deleteEmployeById/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")

    public ResponseEntity<HttpStatus> deleteEmployeById(@PathVariable Long id){
        employeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}

