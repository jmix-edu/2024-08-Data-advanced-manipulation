package com.company.jmixpm.security;

import com.company.jmixpm.entity.ProjectStats;
import com.company.jmixpm.entity.TimeEntry;
import io.jmix.security.model.RowLevelBiPredicate;
import io.jmix.security.model.RowLevelPolicyAction;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.PredicateRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;
import org.springframework.context.ApplicationContext;

@RowLevelRole(name = "Own time entries", code = OwnTimeEntriesRole.CODE)
public interface OwnTimeEntriesRole {
    String CODE = "own-time-entries";

    @JpqlRowLevelPolicy(entityClass = TimeEntry.class, where = "{E}.user.id = :current_user_id")
    void timeEntry();

//    @PredicateRowLevelPolicy(entityClass = ProjectStats.class, actions = {RowLevelPolicyAction.READ, RowLevelPolicyAction.CREATE, RowLevelPolicyAction.UPDATE, RowLevelPolicyAction.DELETE})
//    default RowLevelBiPredicate<ProjectStats, ApplicationContext> projectStatsPredicate() {
//        return (projectStats, applicationContext) -> {
//            return true;
//        };
//    }
}