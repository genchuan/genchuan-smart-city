package cn.iocoder.yudao.module.envir.service.route;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envir.controller.admin.route.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.route.RouteDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envir.dal.mysql.route.RouteMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envir.enums.ErrorCodeConstants.*;

/**
 * 路线 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RouteServiceImpl implements RouteService {

    @Resource
    private RouteMapper routeMapper;

    @Override
    public Long createRoute(RouteSaveReqVO createReqVO) {
        // 插入
        RouteDO route = BeanUtils.toBean(createReqVO, RouteDO.class);
        routeMapper.insert(route);
        // 返回
        return route.getId();
    }

    @Override
    public void updateRoute(RouteSaveReqVO updateReqVO) {
        // 校验存在
        validateRouteExists(updateReqVO.getId());
        // 更新
        RouteDO updateObj = BeanUtils.toBean(updateReqVO, RouteDO.class);
        routeMapper.updateById(updateObj);
    }

    @Override
    public void deleteRoute(Long id) {
        // 校验存在
        validateRouteExists(id);
        // 删除
        routeMapper.deleteById(id);
    }

    private void validateRouteExists(Long id) {
        if (routeMapper.selectById(id) == null) {
            throw exception(ROUTE_NOT_EXISTS);
        }
    }

    @Override
    public RouteDO getRoute(Long id) {
        return routeMapper.selectById(id);
    }

    @Override
    public PageResult<RouteDO> getRoutePage(RoutePageReqVO pageReqVO) {
        return routeMapper.selectPage(pageReqVO);
    }

}