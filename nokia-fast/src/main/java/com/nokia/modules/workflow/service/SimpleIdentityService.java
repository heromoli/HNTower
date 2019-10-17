package com.nokia.modules.workflow.service;

import com.nokia.utils.RData;

/**
 * Created by wow on 2019/6/26.
 */
public interface SimpleIdentityService {
    public RData updateUser(String id, String name);
    public RData deleteUser(String id);
    public RData updateGroup(String id, String name);
    public RData deleteGroup(String id);
    public RData updateMemberShip(String userId, String groupId);
}
