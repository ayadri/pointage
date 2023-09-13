package com.example.backendpointage.controller;

import com.example.backendpointage.Service.PauseService;
import com.example.backendpointage.Service.PointageService;
import com.example.backendpointage.dao.model.Employe;
import com.example.backendpointage.dao.model.Pointage;
import com.example.backendpointage.dao.repositories.EmployeRepository;
import com.example.backendpointage.enums.StatusPointage;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class PointageController {
    private final PointageService pointageService;

    private PauseService pauseService;
    private EmployeRepository employeRepository;
   /* @PostConstruct
    void init() throws Exception {
        SavePointage pointage = new SavePointage(1);
        pointageService.pointerArrivage((long) pointage.getEmploye());
    }*/

    @GetMapping("/getAllPointages")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public List<Pointage> getAllPointages() {
       return pointageService.pointage();
    }

    @GetMapping("/pointageArriver/{employeN}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<Pointage> addPointageArrive(@PathVariable String employeN) throws Exception {
        Pointage savedPointage = pointageService.pointerArrivage(employeN);
        return ResponseEntity.ok(savedPointage);
    }

    @GetMapping("/pointageSortie/{employeN}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<Pointage> addPointageSortie(@PathVariable String employeN) throws Exception {
        Pointage savedPointage = pointageService.pointerSortie(employeN);
        return ResponseEntity.ok(savedPointage);
    }
    @GetMapping("/pointageDebutPause/{employeN}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<Pointage> addPointageDebutPause(@PathVariable String employeN) throws Exception {
        Pointage savedPointage = pointageService.pointerDebutPause(employeN);
        return ResponseEntity.ok(savedPointage);
    }
    @GetMapping("/pointageFinPause/{employeN}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<Pointage> addPointageFinPause(@PathVariable String employeN) throws Exception {
        Pointage savedPointage = pointageService.pointerFinPause(employeN);
        return ResponseEntity.ok(savedPointage);
    }
    @GetMapping("/getPointageById/{id}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public Pointage getPointageById(@PathVariable Long id) {
        Pointage byId = pointageService.getById(id);
        return byId;
    }
    /*@PostMapping("/{pointageId}/commentaire")
    public ResponseEntity<?> ajouterCommentaire(@PathVariable Long pointageId, @RequestBody String commentaire) {
        Pointage pointage = pointageRepository.findById(pointageId)
                .orElseThrow(() -> new EntityNotFoundException("Pointage not found"));

        pointage.setCommentaire(commentaire);
        pointageRepository.save(pointage);

        return ResponseEntity.ok("Commentaire ajouté avec succès");
    }*/
  /*  public ResponseEntity<Pointage> ajouterPointage(@RequestBody Pointage pointageRequest) {
        Pointage pointage = new Pointage();
        pointage.setPointage(pointageRequest.getPointage());
        pointage.setDatePointage(pointageRequest.getDatePointage());

        Employe employe = new Employe();
        employe.setId(pointageRequest.getEmploye().getId());
        pointage.setEmploye(employe);

        Pointage ajoutPointage = pointageService.ajouterPointage(pointage);
        return new ResponseEntity<>(ajoutPointage, HttpStatus.CREATED);
    }*/

    @GetMapping("/getAllPointages/search")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<Pointage> searchPointages(@RequestParam("keyword") String keyword) throws ParseException {
       return pointageService.findPointageByDate(keyword);
    }

   /* @Autowired
    private  PointageRepository pointageRepository;


    @GetMapping("/getPointageById/{id}")
    public ResponseEntity<Pointage> getPointageById(@PathVariable Long id) {
        Optional<Pointage> pointage = pointageRepository.findById(id);
        return pointage.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getAllPointages/search")
    public List<Pointage> searchPointages(@RequestParam("keyword") String keyword) {
        if(keyword.isEmpty()){
            List<Pointage> pointageList = new ArrayList<>();
            pointageRepository.findAll().forEach(pointageList::add);
            return pointageList;
        }else
        return pointageRepository.findPointageByDate(keyword);
    }
    @PostMapping("/addPointage")
    public ResponseEntity<Pointage> addPointage(@RequestBody Pointage pointage) {
        Pointage savedPointage = pointageRepository.save(pointage);
        return ResponseEntity.ok(savedPointage);
    }

 *//*   @PutMapping("/updatePointageById/{id}")
    public ResponseEntity<Pointage> updatePointageById(@PathVariable Long id, @RequestBody Pointage updatedPointage) {
        Optional<Pointage> existingPointage = pointageRepository.findById(id);
        if (existingPointage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

     *//**//*   Pointage pointageToUpdate = existingPointage.get();
        pointageToUpdate.setHeureArrivee(updatedPointage.getHeureArrivee());
        pointageToUpdate.setHeureDepart(updatedPointage.getHeureDepart());
        pointageToUpdate.setHeureSup(updatedPointage.getHeureSup());*//**//*

      //  Pointage updatedPointageEntity = pointageRepository.save(pointageToUpdate);

        //return ResponseEntity.ok(updatedPointageEntity);
    }*//*

    @DeleteMapping("/deletePointageById/{id}")
    public ResponseEntity<Void> deletePointageById(@PathVariable Long id) {
        pointageRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }*/
   @GetMapping("/calcul-duree-pause")
   public String calculerDureePause(@RequestParam List<StatusPointage> pointages) {
       Duration dureePause = pauseService.calculerDureePause(pointages);

       long heures = dureePause.toHours();
       long minutes = dureePause.toMinutesPart();

       return heures + " heures " + minutes + " minutes";
   }

    @GetMapping("/heures-travaillees-par-mois/{name}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")

    public Map<String, Double> getHeuresTravailleesParMois(@PathVariable String name) {
        Employe employe = employeRepository.findByNomContains(name);
        return pointageService.calculerHeuresTravailleesParMois(employe);
    }


}


