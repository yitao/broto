package com.broto.backstage.service;

import com.broto.backstage.dao.*;
import com.broto.backstage.entity.*;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yitao on 2016/9/28.
 */
@Service
public class PermissionService {
    @Autowired
    private RMADao rmaDao;
    @Autowired
    private ARDao arDao;
    @Autowired
    private R2RDao r2rDao;
    @Autowired
    private R2MADao r2maDao;

    @Autowired
    private RoleService roleService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ActionService actionService;

    public AR saveAR(String accountId, String roleId) {
        AR ar = new AR(accountId, roleId);
        arDao.insert(ar);
        return ar;
    }

    public void deleteAR(String accountId, String roleId) {
        AR ar = new AR(accountId, roleId);
        arDao.delete(ar);
    }

    public void deleteAR(String accountId) {
        arDao.deleteByAccountId(accountId);
    }

    public RMA saveRMA(String roleId, String moduleId, String actionId) {
        RMA rma = new RMA(roleId, moduleId, actionId);
        rmaDao.insert(rma);
        return rma;
    }

    public void saveRM(String roleId, String moduleId) {
        for (Action action : actionService.findAll(moduleId, null, null)) {
            String actionId = action.getId();
            saveRMA(roleId, moduleId, actionId);
        }
    }

    public void deleteRMAByRM(String roleId, String moduleId) {
        for (Action action : actionService.findAll(moduleId, null, null)) {
            String actionId = action.getId();
            deleteRMA(roleId, moduleId, actionId);
        }
    }

    public void deleteRMA(String roleId, String moduleId, String actionId) {
        RMA rma = new RMA(roleId, moduleId, actionId);
        rmaDao.delete(rma);
    }

    public void deleteRMAByRoleId(String roleId){
        rmaDao.deleteByRoleId(roleId);
    }

    public void deleteRMAByModuleId(String moduleId){
        rmaDao.deleteByModuleId(moduleId);
    }

    public void deleteRMAByActionId(String actionId){
        rmaDao.deleteByActionId(actionId);
    }


    public R2R saveR2R(String roleId, String openedRoleId) {
        R2R r2r = new R2R(roleId, openedRoleId);
        r2rDao.insert(r2r);
        return r2r;
    }

    public void deleteR2R(String roleId, String openedRoleId) {
        R2R r2r = new R2R(roleId, openedRoleId);
        r2rDao.delete(r2r);
    }

    public R2MA saveR2MA(String roleId, String moduleId, String actionId) {
        R2MA r2ma = new R2MA(roleId, moduleId, actionId);
        r2maDao.insert(r2ma);
        return r2ma;
    }

    public void saveR2M(String roleId, String moduleId) {
        for (Action action : actionService.findAll(moduleId, null, null)) {
            String actionId = action.getId();
            saveR2MA(roleId, moduleId, actionId);
        }
    }

    public void deleteR2M(String roleId, String moduleId) {
        for (Action action : actionService.findAll(moduleId, null, null)) {
            String actionId = action.getId();
            deleteR2MA(roleId, moduleId, actionId);
        }
    }

    public void deleteR2MA(String roleId, String moduleId, String actionId) {
        R2MA r2ma = new R2MA(roleId, moduleId, actionId);
        r2maDao.delete(r2ma);
    }

    public List<String> findAllExcludeActionByAccountId(String accountId) {
        return rmaDao.findAllExcludeActionByAccount(accountId);
    }

    public List<String> findAllActionByAccountId(String accountId) {
        return rmaDao.findAllActionByAccount(accountId);
    }

    //查询当前用户所有角色
    public List<Role> findAllRoleByAccountId(String accountId) {
        List<Role> result = new ArrayList<>();
        //TODO
        return result;
    }

    //查询当前用户角色所有可见角色
    public List<Role> findAllOpenedRoleByAccountId(String accountId) {
        List<Role> result = new ArrayList<>();
        //TODO
        return result;
    }

    //查询当前用户角色所有可见模块，功能
    public List<Module> findAllMA4Opened(String roleId) {
        List<Module> result = new ArrayList<>();
        return result;
    }

    /**
     * 查询所有角色以及其可见模块功能
     *
     * @return
     */
    public List<Role> queryR2R() {
        //查询当前用户角色所有可见角色
        List<Role> roles = roleService.findAll();
        //查询当前用户角色所有可见模块，功能
        for (Role role : roles) {
            List<Role> openedRoles = new ArrayList<>();
            String roleId = role.getId();
            List<R2R> r2rs = r2rDao.findAllByRoleId(roleId);
            for (Role role2 : roles) {
                Role openedRole = role2.clone();
                for (R2R r2r : r2rs) {
                    if (StringUtils.equals(r2r.getOpenedRoleId(), openedRole.getId())) {
                        openedRole.setState(1);
                        break;
                    }
                }
                openedRoles.add(openedRole);
            }
            role.setOpenedRoles(openedRoles);
        }
        return roles;
    }

    /**
     * 查询所有角色以及其可见模块功能
     *
     * @return
     */
    public List<Role> queryrolewithprivilege(String userId) {
        boolean buildIn = false;
        List<Role> ownerRoles = findAllRoleByAccountId(userId);
        Role owner = null;
        if (ownerRoles.size() > 0) {
            owner = ownerRoles.get(0);
        }
        for (Role role : ownerRoles) {
            if (role.isAp()) {
                buildIn = true;
                break;
            }
        }
        List<Role> roles = null;
        List<Module> modules = null;
        if (buildIn) {
            roles = roleService.findAll();
            modules = moduleService.findAll();
        } else {
            //查询当前用户角色所有可见角色
            roles = findAllOpenedRoleByAccountId(userId);
            //查询当前用户角色所有可见模块，功能
            modules = findAllMA4Opened(owner.getId());
        }
        int maCheckCount = 0;
        for (Role role : roles) {
            if (null == role) continue;
            List<Module> nmodules = new ArrayList<>();
            String roleId = role.getId();
            List<RMA> rmas = rmaDao.findAllByRoleId(roleId);
            for (Module module : modules) {
                Module nmodule = module.clone();
                nmodules.add(nmodule);
                String moduleId = nmodule.getId();
                maCheckCount = 0;
                for (Action action : nmodule.getActions()) {
                    action.setState(0);
                    for (RMA rma : rmas) {
                        if (StringUtils.equals(rma.getActionId(), action.getId()) && StringUtils.equals(rma.getModuleId(), moduleId)) {
                            action.setState(1);
                            maCheckCount++;
                            break;
                        }
                    }
                }
                if (module.getActions().size() == 0) {
                    nmodule.setState(0);
                } else {
                    if (maCheckCount == module.getActions().size()) {
                        nmodule.setState(1);
                    } else if (maCheckCount > 0) {
                        nmodule.setState(2);
                    } else {
                        nmodule.setState(0);
                    }
                }
            }
            role.setModules(nmodules);
        }
        return roles;
    }

    /**
     * 查询所有角色以及所有模块功能
     *
     * @return
     */
    public List<Role> queryrolewithprivilege4show() {
        //查询所有角色
        List<Role> roles = roleService.findAll();
        //查询所有模块功能
        List<Module> modules = moduleService.findAll();
        int maCheckCount = 0;
        for (Role role : roles) {
            List<Module> nmodules = new ArrayList<>();
            String roleId = role.getId();
            List<R2MA> rmas = r2maDao.findAllByRoleId(roleId);
            for (Module module : modules) {
                Module nmodule = module.clone();
                nmodules.add(nmodule);
                String moduleId = nmodule.getId();
                maCheckCount = 0;
                for (Action action : nmodule.getActions()) {
                    action.setState(0);
                    for (R2MA rma : rmas) {
                        if (StringUtils.equals(rma.getActionId(), action.getId()) && StringUtils.equals(rma.getModuleId(), moduleId)) {
                            action.setState(1);
                            maCheckCount++;
                            break;
                        }
                    }
                }
                if (module.getActions().size() == 0) {
                    nmodule.setState(0);
                } else {
                    if (maCheckCount == module.getActions().size()) {
                        nmodule.setState(1);
                    } else if (maCheckCount > 0) {
                        nmodule.setState(2);
                    } else {
                        nmodule.setState(0);
                    }
                }
            }
            role.setModules(nmodules);
        }
        return roles;
    }

}
