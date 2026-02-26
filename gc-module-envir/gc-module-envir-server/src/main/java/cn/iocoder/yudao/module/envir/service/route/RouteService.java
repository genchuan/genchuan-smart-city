package cn.iocoder.yudao.module.envir.service.route;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envir.controller.admin.route.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.route.RouteDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 路线 Service 接口
 *
 * @author 芋道源码
 */
public interface RouteService {

    /**
     * 创建路线
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRoute(@Valid RouteSaveReqVO createReqVO);

    /**
     * 更新路线
     *
     * @param updateReqVO 更新信息
     */
    void updateRoute(@Valid RouteSaveReqVO updateReqVO);

    /**
     * 删除路线
     *
     * @param id 编号
     */
    void deleteRoute(Long id);

    /**
     * 获得路线
     *
     * @param id 编号
     * @return 路线
     */
    RouteDO getRoute(Long id);

    /**
     * 获得路线分页
     *
     * @param pageReqVO 分页查询
     * @return 路线分页
     */
    PageResult<RouteDO> getRoutePage(RoutePageReqVO pageReqVO);

}