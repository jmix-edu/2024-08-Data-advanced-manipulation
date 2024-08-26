package com.company.jmixpm.repository;

import com.company.jmixpm.entity.Task;
import io.jmix.core.repository.ApplyConstraints;
import io.jmix.core.repository.JmixDataRepository;

import java.util.UUID;

@ApplyConstraints(false)
public interface TaskRepository extends JmixDataRepository<Task, UUID> {
}