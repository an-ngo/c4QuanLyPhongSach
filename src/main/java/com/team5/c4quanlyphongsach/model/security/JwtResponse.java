package com.team5.c4quanlyphongsach.model.security;
import com.team5.c4quanlyphongsach.service.customer.ICustomerService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Getter
@Setter
public class JwtResponse implements Serializable {


    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private String email;
    private Long idCustomer;


    public JwtResponse(String jwttoken, String email) {
        this.jwttoken = jwttoken;
        this.email = email;
    }

    public JwtResponse(String jwttoken, String email, Long idCustomer) {
        this.jwttoken = jwttoken;
        this.email = email;
        this.idCustomer = idCustomer;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }
}
