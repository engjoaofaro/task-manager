package br.eng.joaofaro.taskmanager.resources;

import br.eng.joaofaro.taskmanager.dto.ResponseDto;
import br.eng.joaofaro.taskmanager.dto.TaskDto;
import br.eng.joaofaro.taskmanager.exception.StatusNotFoundException;
import br.eng.joaofaro.taskmanager.exception.TaskAlreadyCompletedStatusException;
import br.eng.joaofaro.taskmanager.exception.TaskManagerException;
import br.eng.joaofaro.taskmanager.exception.TaskNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * <p>Interface to handle all api requests</p>
 * <p>Her we have some configurations methods</p>
 *
 * @see TaskManagerImpl
 * @author João Faro    contato@joaofaro.eng.br on 13/11/20
 * @version 1.0.0
 */
@RolesAllowed("user,super_user")
public interface TaskManager {

    @GetMapping(value="/healthcheck")
    ResponseEntity<?> healthCheck();
    @PostMapping(value = "/task", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    ResponseEntity<ResponseDto> create(TaskDto task, UriComponentsBuilder uriBuilder) throws TaskManagerException;
    @GetMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON)
    ResponseEntity<List<ResponseDto>> list(String status) throws TaskManagerException, TaskNotFoundException,
            StatusNotFoundException, TaskAlreadyCompletedStatusException;
    @PutMapping(value = "/task/{id}/{status}", produces = MediaType.APPLICATION_JSON)
    ResponseEntity<?> updateStatus(@PathVariable Long id, @PathVariable String status) throws TaskManagerException,
            StatusNotFoundException, TaskAlreadyCompletedStatusException, TaskNotFoundException;
    @GetMapping(value = "/task/{id}", produces = MediaType.APPLICATION_JSON)
    ResponseEntity<ResponseDto> getById(@PathVariable Long id) throws TaskManagerException, TaskNotFoundException,
            TaskAlreadyCompletedStatusException;
    @DeleteMapping(value = "/task/{id}", produces = MediaType.APPLICATION_JSON)
    ResponseEntity<?> delete(@PathVariable Long id) throws TaskManagerException, TaskNotFoundException,
            TaskAlreadyCompletedStatusException;
}
