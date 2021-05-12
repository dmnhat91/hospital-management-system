package model;

import javax.persistence.*;

@Entity
@Table(name="icd")
public class ICD {
    @Id
    @Column
    private String code;

    @Column
    private String description;

    public ICD(){}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

/*

 */