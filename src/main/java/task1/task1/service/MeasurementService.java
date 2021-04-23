package task1.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task1.task1.entity.Measurement;
import task1.task1.payload.Result;
import task1.task1.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;


    public Result addMeasurement(Measurement measurement) {
        if (measurementRepository.existsByName(measurement.getName()))
            return new Result("Bunday o'lcho'v birligi mavjud", false);
        measurementRepository.save(measurement);
        return new Result("O'lcho'v birligi saqlandi", true);
    }

    public List<Measurement> getMeasurementList() {
        return measurementRepository.findAll();
    }

    public Measurement getMeasurement(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()) return null;
        return optionalMeasurement.get();
    }

    public Result editMeasurement(Integer id, Measurement measurement) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent())
            return new Result("Bunday o'lcho'v ID birligi mavjud emas", false);
        if (measurementRepository.existsByName(measurement.getName()))
            return new Result("Bunday o'lcho'v birligi mavjud", false);
        Measurement measurement1 = optionalMeasurement.get();
        if (measurement.getName()!=null)
        measurement1.setName(measurement.getName());
        measurement1.setActive(measurement.isActive());
        measurementRepository.save(measurement1);
        return new Result("O'lcho'v birligi taxrirlandi", true);
    }

    public Result deleteMeasurement(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent())
            return new Result("Bunday o'lcho'v ID birligi mavjud emas", false);
        measurementRepository.deleteById(id);
        return new Result("O'lchov birligi o'chirildi", true);
    }

}
