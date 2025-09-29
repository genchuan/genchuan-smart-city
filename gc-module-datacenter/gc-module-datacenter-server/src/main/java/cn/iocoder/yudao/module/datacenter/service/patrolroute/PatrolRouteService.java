package cn.iocoder.yudao.module.datacenter.service.patrolroute;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.patrolroute.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.patrolroute.PatrolRouteDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 巡查路线 Service 接口
 *
 * @author zcq
 */
public interface PatrolRouteService {

    /**
     * 创建巡查路线
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPatrolRoute(@Valid PatrolRouteSaveReqVO createReqVO);

    /**
     * 更新巡查路线
     *
     * @param updateReqVO 更新信息
     */
    void updatePatrolRoute(@Valid PatrolRouteSaveReqVO updateReqVO);

    /**
     * 删除巡查路线
     *
     * @param id 编号
     */
    void deletePatrolRoute(Long id);

    /**
     * 获得巡查路线
     *
     * @param id 编号
     * @return 巡查路线
     */
    PatrolRouteDO getPatrolRoute(Long id);

    /**
     * 获得巡查路线分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查路线分页
     */
    PageResult<PatrolRouteDO> getPatrolRoutePage(PatrolRoutePageReqVO pageReqVO);

}