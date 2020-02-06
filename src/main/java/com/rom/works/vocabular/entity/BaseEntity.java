package com.rom.works.vocabular.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity implements Comparable<BaseEntity> {

    @Id
    @JsonIgnore
    @GeneratedValue
    @Setter(AccessLevel.PROTECTED)
    private Long id;

    @Setter(AccessLevel.PROTECTED)
    private LocalDateTime dateCreated;

    @PrePersist
    public void prePersist() {
        dateCreated = LocalDateTime.now();
    }

    @Override
    public int compareTo(@NonNull BaseEntity o) {
        return Objects.compare(dateCreated, o.getDateCreated(), LocalDateTime::compareTo);
    }

    public abstract boolean equals(Object o);

    public abstract int hashCode();
}
