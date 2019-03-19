package de.jonashackt.springbootvuejs.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
public class AppStatus {

    public AppStatus() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    private String appGuid;
    private String appName;
    private boolean up;

}
