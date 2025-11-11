package pe.com.user.administrator.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
    private String number;
    private String citycode;
    private String contrycode;
}
