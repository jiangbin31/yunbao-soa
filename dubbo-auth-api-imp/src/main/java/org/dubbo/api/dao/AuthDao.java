package org.dubbo.api.dao;

import org.dubbo.pojo.base.BaseRequest;
import org.dubbo.pojo.bean.auth.AuthTokenBean;

import java.util.List;

/**
 * Created by jiangbin on 2018/7/10.
 */
public interface AuthDao {

    List<AuthTokenBean> getAuthTokenBeanList();

    AuthTokenBean getAuthTokenBean(String token);



}
