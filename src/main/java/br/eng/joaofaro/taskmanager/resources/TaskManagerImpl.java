package br.eng.joaofaro.taskmanager.resources;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author João Faro    contato@joaofaro.eng.br on 13/11/20
 * @version 1.0.0
 */
@RestController
@RequestMapping("/task-manager")
@Slf4j
public class TaskManagerImpl implements TaskManager{

    @Override
    public ResponseEntity<String> healthCheck() {
        log.debug("Calling health check");
        return ResponseEntity.ok().build();
    }
}
