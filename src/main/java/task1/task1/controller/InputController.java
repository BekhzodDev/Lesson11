package task1.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task1.task1.entity.Input;
import task1.task1.payload.InputDto;
import task1.task1.payload.Result;
import task1.task1.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {
    @Autowired
    InputService inputService;

    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto) {
        return inputService.addInput(inputDto);
    }

    @GetMapping
    public List<Input> getInputs() {
        return inputService.getInputs();
    }

    @GetMapping("/{id}")
    public Input getInput(@PathVariable Integer id) {
        return inputService.getInput(id);
    }

    @PutMapping("/{id}")
    public Result editInput(@PathVariable Integer id, @RequestBody InputDto inputDto) {
        return inputService.editInput(id, inputDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteInput(@PathVariable Integer id) {
        return inputService.deleteInput(id);
    }

}
