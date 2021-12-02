package co.edu.javeriana.mc.survey.repository.likert.scale;

import co.edu.javeriana.mc.survey.model.likert.scale.ScaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
* Generated by Spring Data Generator on 14/09/2021
*/
@Repository
public interface ScaleItemRepository extends JpaRepository<ScaleItem, Long>, JpaSpecificationExecutor<ScaleItem> {

}
