package com.nokia.modules.workflow.service.impl;

import com.nokia.modules.workflow.service.SimpleIdentityService;
import com.nokia.utils.RData;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wow on 2019/6/26.
 */
@Service("simpleIdentityService")
public class SimpleIdentityServiceImpl implements SimpleIdentityService {

    private static final Logger logger = LoggerFactory.getLogger(SimpleIdentityServiceImpl.class);

    @Autowired
    private org.activiti.engine.IdentityService identityService;

    public RData updateUser(String id, String name) {
        User dbUser = identityService.createUserQuery().userId(id).singleResult();
        if (dbUser != null) {
            logger.warn("user {}-{} already exists, will do update", id, name);
            if(!name.equals(dbUser.getFirstName())) {
                dbUser.setFirstName(name);
                identityService.saveUser(dbUser);
            }
        } else {
            User user = identityService.newUser(id);
            user.setFirstName(name);
            identityService.saveUser(user);
        }
        return RData.ok();
    }

    public RData deleteUser(String id) {
        identityService.deleteUser(id);
        return RData.ok();
    }

    public RData updateGroup(String id, String name) {
        Group dbGroup = identityService.createGroupQuery().groupId(id).singleResult();
        if (dbGroup != null) {
            logger.warn("group {}-{} already exists, will do update", id, name);
            if(!name.equals(dbGroup.getName())) {
                dbGroup.setName(name);
                identityService.saveGroup(dbGroup);
            }
        } else {
            Group group = identityService.newGroup(id);
            group.setName(name);
            identityService.saveGroup(group);
        }
        return RData.ok();
    }

    public RData deleteGroup(String id) {
        identityService.deleteGroup(id);
        return RData.ok();
    }

    public RData updateMemberShip(String userId, String groupId) {
        return null;
    }

}
