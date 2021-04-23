package task1.task1.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import task1.task1.entity.template.AbsEntity;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Measurement extends AbsEntity {
}
