package main.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.dto.DtoTask;
import main.model.Task;
import main.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/")
    public String init() {
        return LocalDateTime
                .now()
                .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
    }
    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> getTask(@PathVariable int id) {
        Optional<Task> optTask = taskRepository.findById(id);
        if (optTask.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(optTask.get(), HttpStatus.OK);
    }
    @GetMapping("/tasks")
    public ResponseEntity<?> getTasks() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
    @PostMapping(
            value = "/tasks",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<String> addTask(@RequestBody String json) throws JsonProcessingException {
        DtoTask dtoTask = mapper.readValue(json, DtoTask.class);
        Task task = new Task();
        task.setCreationTime(LocalDateTime.now());
        task.setTitle(dtoTask.getTitle());
        task.setDescription(dtoTask.getDescription());
        task.setIsDone(false);
        taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PatchMapping(
            value = "/tasks/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> patchTask(@PathVariable int id, @RequestBody String json) throws JsonProcessingException {
        Optional<Task> optTask = taskRepository.findById(id);
        ObjectMapper mapper = new ObjectMapper();
        DtoTask dtoTask = mapper.readValue(
                json.replaceAll("isDone", "done"), DtoTask.class
        );
        if (optTask.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Task task = optTask.get();
        if (dtoTask.getTitle() != null) {
            task.setTitle(dtoTask.getTitle());
        }
        if (dtoTask.getDescription() != null) {
            task.setDescription(dtoTask.getDescription());
        }
        if (task.getIsDone() != dtoTask.isDone() && json.contains("isDone")) {
            task.setIsDone(dtoTask.isDone());
        }
        taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable int id) {
        Optional<Task> optTask = taskRepository.findById(id);
        if (optTask.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        taskRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
