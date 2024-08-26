package com.company.jmixpm.security;

import com.company.jmixpm.entity.Task;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "TestRole", code = TestRole.CODE, scope = "UI")
public interface TestRole {
    String CODE = "test-role";

    @MenuPolicy(menuIds = "Task_repo.list")
    @ViewPolicy(viewIds = "Task_repo.list")
    void screens();

    @EntityAttributePolicy(entityClass = Task.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    void task();
}