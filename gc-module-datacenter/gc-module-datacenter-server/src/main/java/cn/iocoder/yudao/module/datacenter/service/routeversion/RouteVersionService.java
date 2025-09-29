package cn.iocoder.yudao.module.datacenter.service.routeversion;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.routeversion.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.routeversion.RouteVersionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 路线版本 Service 接口
 *
 * @author zcq
 */
public interface RouteVersionService {

    /**
     * 创建路线版本
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRouteVersion(@Valid RouteVersionSaveReqVO createReqVO);

    /**
     * 更新路线版本
     *
     * @param updateReqVO 更新信息
     */
    void updateRouteVersion(@Valid RouteVersionSaveReqVO updateReqVO);

    /**
     * 删除路线版本
     *
     * @param id 编号
     */
    void deleteRouteVersion(Long id);

    /**
     * 获得路线版本
     *
     * @param id 编号
     * @return 路线版本
     */
    RouteVersionDO getRouteVersion(Long id);

    /**
     * 获得路线版本分页
     *
     * @param pageReqVO 分页查询
     * @return 路线版本分页
     */
    PageResult<RouteVersionDO> getRouteVersionPage(RouteVersionPageReqVO pageReqVO);

}