package com.pennyWise.PennyWise.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name="blackListTokens")
@NoArgsConstructor
@AllArgsConstructor
public class BlacklistedToken {

    @Id
    private String jti;

    private Date expiry;

}
