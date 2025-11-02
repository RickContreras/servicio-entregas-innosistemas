package domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;

@Entity
@Table(name = "deliveries")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String description;
    private String file_url;
    @Column(nullable = false)
    private LocalDateTime created_at;
    @Column(nullable = false)
    private Integer project_id;

    public Delivery() {
    }

    public Delivery(Long id, String title, String description, String file_url, LocalDateTime created_at,
            Integer project_id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.file_url = file_url;
        this.created_at = created_at;
        this.project_id = project_id;
    }

    @PrePersist
    protected void onCreate() {
        if (this.created_at == null) {
            this.created_at = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

}
