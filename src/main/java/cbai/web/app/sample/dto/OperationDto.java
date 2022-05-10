package cbai.web.app.toolbox.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class OperationDto {

    public OperationDto(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public void addSubOption(String value, String label) {
        if (subOperations == null) {
        	subOperations = new ArrayList<OperationDto>();
        }
        subOperations.add(new OperationDto(value, label));
    }

    private String value;
    private String label;
    private List<OperationDto> subOperations;
}
