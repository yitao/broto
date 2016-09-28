package com.broto.backstage.dao.mybatis;

import com.broto.backstage.dao.BaseSqlDao;
import com.broto.backstage.entity.BaseDataEntity;
import com.broto.backstage.util.IDUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * mybatis基类，支持注解
 * Created by yitao on 2016/5/19.
 */
public class BaseSqlDaoImpl<T extends BaseDataEntity, PK extends String> extends SqlSessionDaoSupport implements BaseSqlDao<T, PK> {

    public static final String SQL_INSERT = "insert";

    public static final String SQL_HARD_DELETE_BY_ID = "hardDelete";
    public static final String SQL_NO_HARD_DELETE_BY_ID = "noHardDelete";

    public static final String SQL_UPDATE = "update";
    public static final String SQL_UPDATE_BY_MAP = "updateByMap";

    public static final String SQL_FIND_BY_ID = "findById";
    public static final String SQL_FIND_BY_MAP = "findAllByMap";
    public static final String SQL_COUNT_BY_MAP = "countAllByMap";

    // 批量操作：修改和假删除
    public static final String SQL_BATCH_OPT = "batchOpt";
    // 批量插入
    public static final String SQL_BATCH_INSERT = "batchInsert";

    public static final String SQL_INSERT_RELATION = "insertRelation";

    protected String className;

    public String getClassName() {
        return className;
    }

    protected Class<T> clazz;

    @PostConstruct
    public void init() {
        clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        className = clazz.getName();
    }

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public String sql(String s){
        return className + "." + s;
    }


    @Override
    public void insert(T entity) {
        getSqlSession().insert(sql(SQL_INSERT), entity);
    }


    @Override
    public void save(T entity) {
        if (entity.isNew()) {
            entity.setId(IDUtils.genUUID32());
            insert(entity);
        } else {
            update(entity);
        }
    }


    @Override
    public void update(T entity) {
        getSqlSession().update(sql(SQL_UPDATE), entity);
    }

    @Override
    public void delete(PK id, boolean isHardDelete) {
        if(isHardDelete){
            hardDelete(id);
        }else{
            getSqlSession().update(sql(SQL_NO_HARD_DELETE_BY_ID),id);
        }
    }

    @Override
    public void hardDelete(PK id) {
        getSqlSession().delete(sql(SQL_HARD_DELETE_BY_ID),id);
    }

    @Override
    public T findById(PK id) {
        return getSqlSession().selectOne(sql(SQL_FIND_BY_ID),id);
    }

    @Override
    public List<T> findAllByMap(Map<String, Object> query) {
        query = query==null?new HashMap<String,Object>():query;
        return getSqlSession().selectList(sql(SQL_FIND_BY_MAP),query);
    }

    @Override
    public long countAllByMap(Map<String, Object> query) {
        query = query==null?new HashMap<String,Object>():query;
        return getSqlSession().selectOne(sql(SQL_COUNT_BY_MAP),query);
    }
}
