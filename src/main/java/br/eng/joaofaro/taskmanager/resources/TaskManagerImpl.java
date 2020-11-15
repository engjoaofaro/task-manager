package br.eng.joaofaro.taskmanager.resources;

import br.eng.joaofaro.taskmanager.beans.AccountUserBean;
import br.eng.joaofaro.taskmanager.beans.TaskBean;
import br.eng.joaofaro.taskmanager.dto.ResponseDto;
import br.eng.joaofaro.taskmanager.dto.TaskDto;
import br.eng.joaofaro.taskmanager.exception.TaskManagerException;
import br.eng.joaofaro.taskmanager.services.TaskManagerService;
import br.eng.joaofaro.taskmanager.services.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @author João Faro    contato@joaofaro.eng.br on 13/11/20
 * @version 1.0.0
 */
@RestController
@RequestMapping("/task-manager")
@Slf4j
public class TaskManagerImpl implements TaskManager{

    private final ModelMapper mapper;
    private final UserInfoService userInfoService;
    private final TaskManagerService taskManagerService;

    public TaskManagerImpl(ModelMapper mapper, UserInfoService userInfoService, TaskManagerService taskManagerService) {
        this.mapper = mapper;
        this.userInfoService = userInfoService;
        this.taskManagerService = taskManagerService;
    }

    @Override
    public ResponseEntity<?> healthCheck() {
        log.debug("Calling health check");
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ResponseDto> create(@RequestBody TaskDto task, UriComponentsBuilder uriBuilder) throws TaskManagerException {
        log.info("Receiving Task: {}", task);
        log.info("Getting user details");
        AccountUserBean userInfo = userInfoService.getUserInfo();
        log.info("User: {}", userInfo);

        log.info("Mapping task to Bean");
        TaskBean taskBean = mapper.map(task, TaskBean.class);
        log.info("Task: {}", taskBean);
        TaskBean taskNew = taskManagerService.createNew(taskBean, userInfo);
        ResponseDto responseDto = buildResponse(taskNew, userInfo);
        URI uri = uriBuilder.path("/task-manager/tasks/{id}")
                .buildAndExpand(responseDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(responseDto);
    }

    private ResponseDto buildResponse(TaskBean taskNew, AccountUserBean userInfo) {
        log.info("Building response with task: {}", taskNew);
        ResponseDto dto = mapper.map(taskNew, ResponseDto.class);
        dto.setCreatedUser(userInfo.getName());
        log.info("returnig Response: {}", dto);
        return dto;
    }
}
