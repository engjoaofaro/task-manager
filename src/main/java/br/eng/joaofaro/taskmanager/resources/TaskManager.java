package br.eng.joaofaro.taskmanager.resources;

import br.eng.joaofaro.taskmanager.dto.ResponseDto;
import br.eng.joaofaro.taskmanager.dto.TaskDto;
import br.eng.joaofaro.taskmanager.exception.StatusNotFoundException;
import br.eng.joaofaro.taskmanager.exception.TaskManagerException;
import br.eng.joaofaro.taskmanager.exception.TaskNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author João Faro    contato@joaofaro.eng.br on 13/11/20
 * @version 1.0.0
 */
@RolesAllowed("user,super_user")
public interface TaskManager {

    @GetMapping(value="/healthcheck")
    ResponseEntity<?> healthCheck();
    @PostMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    ResponseEntity<ResponseDto> create(TaskDto task, UriComponentsBuilder uriBuilder) throws TaskManagerException;
    @GetMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON)
    ResponseEntity<List<ResponseDto>> list(String status) throws TaskManagerException, TaskNotFoundException, StatusNotFoundException;
}
