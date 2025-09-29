package cn.iocoder.yudao.module.datacenter.service.routeversion;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.routeversion.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.routeversion.RouteVersionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.routeversion.RouteVersionMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 路线版本 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class RouteVersionServiceImpl implements RouteVersionService {

    @Resource
    private RouteVersionMapper routeVersionMapper;

    @Override
    public Long createRouteVersion(RouteVersionSaveReqVO createReqVO) {
        // 插入
        RouteVersionDO routeVersion = BeanUtils.toBean(createReqVO, RouteVersionDO.class);
        routeVersionMapper.insert(routeVersion);
        // 返回
        return routeVersion.getId();
    }

    @Override
    public void updateRouteVersion(RouteVersionSaveReqVO updateReqVO) {
        // 校验存在
        validateRouteVersionExists(updateReqVO.getId());
        // 更新
        RouteVersionDO updateObj = BeanUtils.toBean(updateReqVO, RouteVersionDO.class);
        routeVersionMapper.updateById(updateObj);
    }

    @Override
    public void deleteRouteVersion(Long id) {
        // 校验存在
        validateRouteVersionExists(id);
        // 删除
        routeVersionMapper.deleteById(id);
    }

    private void validateRouteVersionExists(Long id) {
        if (routeVersionMapper.selectById(id) == null) {
            throw exception(ROUTE_VERSION_NOT_EXISTS);
        }
    }

    @Override
    public RouteVersionDO getRouteVersion(Long id) {
        return routeVersionMapper.selectById(id);
    }

    @Override
    public PageResult<RouteVersionDO> getRouteVersionPage(RouteVersionPageReqVO pageReqVO) {
        return routeVersionMapper.selectPage(pageReqVO);
    }

}