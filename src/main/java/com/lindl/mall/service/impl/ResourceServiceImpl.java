package com.lindl.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lindl.mall.common.HttpRequest;
import com.lindl.mall.common.HttpResponse;
import com.lindl.mall.common.exception.ExceptionFactory;
import com.lindl.mall.common.exception.ExceptionMsg;
import com.lindl.mall.mapper.MallResourceMapper;
import com.lindl.mall.pojo.MallResource;
import com.lindl.mall.service.ResourceService;
import com.lindl.mall.vo.req.MallResourceListReq;
import com.lindl.mall.vo.res.MallResourceListRes;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/12 14:27
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ResourceServiceImpl implements ResourceService {

    @Resource
    private MallResourceMapper mallResourceMapper;

    @Resource
    private ExceptionFactory exceptionFactory;

    @Resource
    private RedissonClient redissonClient;

    @Value("${resource.cache.pre}")
    public String resourceCacheKeyPre;


    @Override
    public HttpResponse add(HttpRequest<MallResource> request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw exceptionFactory.create(ExceptionMsg.ERROR_PARAMTER_MISS, ExceptionMsg.msg.get(ExceptionMsg.ERROR_PARAMTER_MISS));
        }

        MallResource data = request.getData();
        int count = mallResourceMapper.insert(data);
        if (count != 1) {
            throw exceptionFactory.create(ExceptionMsg.ERR_INSERT, ExceptionMsg.msg.get(ExceptionMsg.ERR_INSERT));
        }

        return HttpResponse.build(null);
    }

    @Override
    public HttpResponse delete(Long id) throws Exception {
        if (Objects.isNull(id)) {
            throw exceptionFactory.create(ExceptionMsg.ERROR_PARAMTER_MISS, ExceptionMsg.msg.get(ExceptionMsg.ERROR_PARAMTER_MISS));
        }

        int delete = mallResourceMapper.delete(id);
        if (delete != 1) {
            throw exceptionFactory.create(ExceptionMsg.ERR_DELETE, ExceptionMsg.msg.get(ExceptionMsg.ERR_DELETE));
        }
        return HttpResponse.build(null);
    }

    @Override
    public HttpResponse<MallResourceListRes> findList(HttpRequest<MallResourceListReq> request){
        MallResourceListReq data = request.getData();
        PageHelper.startPage(data.getPageNum(), data.getPageSize());
        List<MallResource> list = mallResourceMapper.findList(data);
        PageInfo<MallResource> pageInfo = PageInfo.of(list);
        log.info("pageInfo:{}", pageInfo);
        return HttpResponse.build(MallResourceListRes.builder().pageInfo(pageInfo).build());
    }

    @Override
    public HttpResponse modify(HttpRequest<MallResource> request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw exceptionFactory.create(ExceptionMsg.ERROR_PARAMTER_MISS, ExceptionMsg.msg.get(ExceptionMsg.ERROR_PARAMTER_MISS));
        }
        MallResource data = request.getData();
        int update = 0;
        RLock lock = redissonClient.getLock(resourceCacheKeyPre + data.getId());
        try {
            lock.lock();
            update = mallResourceMapper.update(data);
        }  finally {
            lock.unlock();
        }
        if (update != 1) {
            throw exceptionFactory.create(ExceptionMsg.ERR_UPDATE, ExceptionMsg.msg.get(ExceptionMsg.ERR_UPDATE));
        }
        return HttpResponse.build(null);
    }
}
