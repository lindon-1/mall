package com.lindl.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lindl.mall.common.HttpRequest;
import com.lindl.mall.common.HttpResponse;
import com.lindl.mall.common.exception.ExceptionFactory;
import com.lindl.mall.common.exception.ExceptionMsg;
import com.lindl.mall.mapper.MallRoleMapper;
import com.lindl.mall.mapper.MallRoleResourceMapper;
import com.lindl.mall.pojo.MallRole;
import com.lindl.mall.pojo.MallRoleResource;
import com.lindl.mall.service.RoleService;
import com.lindl.mall.vo.req.MallRoleAddReq;
import com.lindl.mall.vo.req.MallRoleListReq;
import com.lindl.mall.vo.res.MallRoleListRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/18 15:41
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private MallRoleMapper mallRoleMapper;

    @Resource
    private MallRoleResourceMapper mallRoleResourceMapper;

    @Resource
    private ExceptionFactory exceptionFactory;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResponse add(HttpRequest<MallRoleAddReq> request, BindingResult bindingResult) throws Exception {
        MallRoleAddReq data = request.getData();
        MallRole mallRole = new MallRole();
        BeanUtils.copyProperties(data, mallRole);
        mallRoleMapper.insert(mallRole);
        List<Long> resourceIds = data.getResourceIds();
        if (!CollectionUtils.isEmpty(resourceIds)) {
            List<MallRoleResource> mallRoleResources = new ArrayList<>(resourceIds.size());
            resourceIds.forEach(e -> {
                MallRoleResource mallRoleResource = new MallRoleResource();
                mallRoleResource.setResourceId(e);
                mallRoleResource.setRoleId(mallRole.getId());
                mallRoleResources.add(mallRoleResource);
            });
            int nums = mallRoleResourceMapper.batchInsert(mallRoleResources);
            log.info("batch insert mallRoleResources nums: {}", nums);
        }
        return HttpResponse.build(null);
    }

    @Override
    public HttpResponse<MallRoleListRes> findList(HttpRequest<MallRoleListReq> request) throws Exception {
        MallRoleListReq data = request.getData();
        PageHelper.startPage(data.getPageNum(), data.getPageSize());
        List<MallRole> list = mallRoleMapper.findList(data.getName());
        PageInfo<MallRole> pageInfo = PageInfo.of(list);
        return HttpResponse.build(MallRoleListRes.builder().pageInfo(pageInfo).build());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResponse delete(Long id) throws Exception {
        if (Objects.isNull(id)) {
            throw exceptionFactory.create(ExceptionMsg.ERROR_PARAMTER_MISS, ExceptionMsg.msg.get(ExceptionMsg.ERROR_PARAMTER_MISS));
        }
        int rows = mallRoleMapper.delete(id);
        if (rows ==1) {
            log.info("deleted one role id:{}", id);
        }
        return HttpResponse.build(null);
    }
}
