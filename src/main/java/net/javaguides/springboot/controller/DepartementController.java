package net.javaguides.springboot.controller;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Departement;
import net.javaguides.springboot.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class DepartementController {

    @Autowired
    private DepartementRepository DepartementRepository;

    //get Departements
    @GetMapping("/Departements")
    public List<Departement> getAllDepartement(){
        return this.DepartementRepository.findAll();
    }
    //get Departement by id
    @GetMapping("/Departements/{id}")
    public ResponseEntity<Departement> getDepartementById(@PathVariable(value = "id") Long DepartementId)
            throws ResourceNotFoundException {
        Departement Departement = DepartementRepository.findById(DepartementId)
                .orElseThrow(() -> new ResourceNotFoundException("Departement not found for this id :: " + DepartementId));
        return ResponseEntity.ok().body(Departement);
    }
    //save Departement
    @PostMapping("Departements")
    public Departement createDepartement(@RequestBody Departement Departement) {
        return this.DepartementRepository.save(Departement);
    }
    //update Departement
    @PutMapping("Departements/{id}")
    public ResponseEntity<Departement> updateDepartement(@PathVariable(value = "id") Long DepartementId,
                                                   @Validated @RequestBody Departement DepartementDetails) throws ResourceNotFoundException {
        Departement Departement = DepartementRepository.findById(DepartementId)
                .orElseThrow(() -> new ResourceNotFoundException("Departement not found for this id :: " + DepartementId));
        Departement.setDescription(DepartementDetails.getDescription());


        return ResponseEntity.ok(this.DepartementRepository.save(Departement));
    }
    //delete Departement
    @DeleteMapping("Departements/{id}")
    public Map<String , Boolean> deleteDepartement(@PathVariable(value = "id") Long DepartementId) throws ResourceNotFoundException {
        Departement Departement = DepartementRepository.findById(DepartementId)
                .orElseThrow(() -> new ResourceNotFoundException("Departement not found for this id :: " + DepartementId));

        this.DepartementRepository.delete(Departement);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);

        return response;
    }
}