package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<domain.Delivery, Long> {

    // JPQL query referencing the entity field projectId (mapped to database column
    // project_id)
    @Query("SELECT d FROM Delivery d WHERE d.projectId = :projectId")
    List<domain.Delivery> findByProjectId(@Param("projectId") Integer projectId);

}
