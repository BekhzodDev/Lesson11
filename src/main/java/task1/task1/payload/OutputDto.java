package task1.task1.payload;

import lombok.Data;

@Data
public class OutputDto {
    private Integer warehouseId;
    private Integer currencyeId;
    private Integer clientId;
    private String factureNumber;
}
