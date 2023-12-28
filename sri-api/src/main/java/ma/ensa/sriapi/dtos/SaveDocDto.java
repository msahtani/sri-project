package ma.ensa.sriapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveDocDto {
    private String semestre ;
    private String module ;
    private String level ;
    private String branch ;
}
