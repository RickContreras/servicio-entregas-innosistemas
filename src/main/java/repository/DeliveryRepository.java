package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<domain.Delivery, Long> {

    // JPQL query referencing the entity field `project_id`. We use an explicit
    // @Query
    // so the repository exposes `findByProjectId(...)` even though the entity field
    // uses an underscore naming convention.
    @Query("SELECT d FROM Delivery d WHERE d.project_id = :projectId")
    List<domain.Delivery> findByProjectId(@Param("projectId") Integer projectId);

}
