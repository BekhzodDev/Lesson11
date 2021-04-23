package task1.task1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import task1.task1.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbsEntity {
    @ManyToOne (optional = false)
    private Category category;
    @OneToOne
    private Attachment attachment;
    @Column(unique = true)
    private String code;
    @ManyToOne (optional = false)
    private Measurement measurement;

}
