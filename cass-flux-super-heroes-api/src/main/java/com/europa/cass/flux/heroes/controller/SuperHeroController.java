package com.europa.cass.flux.heroes.controller;

import com.europa.cass.flux.heroes.controller.orchestrator.DataLoadOrchestrator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supes")
public class SuperHeroController {

  private final DataLoadOrchestrator orchestrator;

  public SuperHeroController(DataLoadOrchestrator orchestrator) {
    this.orchestrator = orchestrator;
  }

  @GetMapping("/load")
  public ResponseEntity<DataLoadResponse> submit()
      throws InterruptedException {
    orchestrator.start();
    return ResponseEntity.accepted().body(new DataLoadResponse("PROCESSING"));
  }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class DataLoadResponse {

  private String status;
}
