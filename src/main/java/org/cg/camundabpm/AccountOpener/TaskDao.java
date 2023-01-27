package org.cg.camundabpm.AccountOpener;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDao extends JpaRepository<TaskStatus, Integer> {

}
