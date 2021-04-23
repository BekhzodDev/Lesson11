package task1.task1.payload;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private Integer categoryId;
    private Integer attachmentId;
    private Integer measurementId;
    private boolean active;
}
