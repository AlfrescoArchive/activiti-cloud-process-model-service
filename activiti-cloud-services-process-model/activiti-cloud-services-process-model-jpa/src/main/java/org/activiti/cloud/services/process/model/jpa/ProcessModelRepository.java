package org.activiti.cloud.services.process.model.jpa;

import org.activiti.cloud.services.process.model.core.model.ProcessModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Repository for {link @ProcessModel) entity
 */
public interface ProcessModelRepository extends JpaRepository<ProcessModel, String> {

}
