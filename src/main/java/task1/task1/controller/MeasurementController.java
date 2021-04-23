package task1.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task1.task1.entity.Measurement;
import task1.task1.payload.Result;
import task1.task1.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;
    @PostMapping
    public Result addMeasurement(@RequestBody Measurement measurement) {
        Result result = measurementService.addMeasurement(measurement);
        return result;
    }
    @GetMapping
    public List<Measurement> getMeasurements() {
        return measurementService.getMeasurementList();
    }
    @GetMapping("/{id}")
    public Measurement getMeasurement(@PathVariable Integer id) {
        return measurementService.getMeasurement(id);
    }
    @PutMapping("/{id}")
    public Result editMeasurement(@PathVariable Integer id, @RequestBody Measurement measurement) {
        return measurementService.editMeasurement(id, measurement);
    }
    @DeleteMapping("/{id}")
    public Result deleteMeasurement(@PathVariable Integer id) {
        return measurementService.deleteMeasurement(id);
    }
}
