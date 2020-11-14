package br.eng.joaofaro.taskmanager.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.RolesAllowed;

/**
 * @author João Faro    contato@joaofaro.eng.br on 13/11/20
 * @version 1.0.0
 */
@RolesAllowed("USER,SUPER_USER")
public interface TaskManager {

    @GetMapping(value="/healthcheck")
    ResponseEntity<String> healthCheck();
}
