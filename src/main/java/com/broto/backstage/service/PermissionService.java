package com.broto.backstage.service;

import com.broto.backstage.dao.ARDao;
import com.broto.backstage.dao.R2MADao;
import com.broto.backstage.dao.R2RDao;
import com.broto.backstage.dao.RMADao;
import com.broto.backstage.entity.R2MA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yitao on 2016/9/28.
 */
@Service
public class PermissionService {
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ActionService actionService;
    @Autowired
    private RMADao rmaDao;
    @Autowired
    private ARDao arDao;
    @Autowired
    private R2RDao r2RDao;
    @Autowired
    private R2MADao r2MADao;

}
