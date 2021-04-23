package task1.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task1.task1.entity.Output;
import task1.task1.payload.OutputDto;
import task1.task1.payload.Result;
import task1.task1.service.OutputService;

import java.util.List;

@RestController
@RequestMapping("/output")
public class OutputController {
    @Autowired
    OutputService outputService;
    @PostMapping
    public Result addOutput(@RequestBody OutputDto outputDto) {
        return outputService.addOutput(outputDto);
    }

    @GetMapping
    public List<Output> getOutputs() {
        return outputService.getOutputs();
    }

    @GetMapping("/{id}")
    public Output getOutput(@PathVariable Integer id) {
        return outputService.getOutput(id);
    }

    @PutMapping("/{id}")
    public Result editOutput(@PathVariable Integer id, @RequestBody OutputDto outputDto) {
        return outputService.editOutput(id, outputDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteOutput(@PathVariable Integer id) {
        return outputService.deleteOutput(id);
    }
}
