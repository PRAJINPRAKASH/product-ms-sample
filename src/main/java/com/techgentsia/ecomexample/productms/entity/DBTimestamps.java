package com.techgentsia.ecomexample.productms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.OffsetDateTime;

@MappedSuperclass
@Data
public abstract class DBTimestamps {
    @Column(name = "created_at", updatable = false)
    @JsonProperty(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    @CreationTimestamp
    @Setter(value = AccessLevel.NONE)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    @JsonProperty(value = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    @UpdateTimestamp
    @Setter(value = AccessLevel.NONE)
    private OffsetDateTime updatedAt;
}

