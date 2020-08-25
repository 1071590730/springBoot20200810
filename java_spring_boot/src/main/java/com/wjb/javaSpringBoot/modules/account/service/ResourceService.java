package com.wjb.javaSpringBoot.modules.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wjb.javaSpringBoot.modules.account.entity.Resource;
import com.wjb.javaSpringBoot.modules.common.vo.Result;
import com.wjb.javaSpringBoot.modules.common.vo.SearchVo;

/**
 * Created by ASUS on 2020/8/25 16:27
 */

public interface ResourceService {

    Result<Resource> editResource(Resource resource);

    Result<Resource> deleteResource(int resourceId);

    PageInfo<Resource> getResources(SearchVo searchVo);

    List<Resource> getResourcesByRoleId(int roleId);

    Resource getResourceById(int resourceId);
}
