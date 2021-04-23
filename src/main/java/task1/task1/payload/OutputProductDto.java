package task1.task1.payload;

import lombok.Data;

import java.util.Date;

@Data
public class OutputProductDto {
    private Integer productId;
    private double amount;
    private double price;
    private Integer outputId;
}
