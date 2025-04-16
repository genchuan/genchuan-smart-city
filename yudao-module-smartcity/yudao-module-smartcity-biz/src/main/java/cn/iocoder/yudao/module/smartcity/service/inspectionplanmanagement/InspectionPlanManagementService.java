package cn.iocoder.yudao.module.smartcity.service.inspectionplanmanagement;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionplanmanagement.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectionplanmanagement.InspectionPlanManagementDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 巡查计划管理 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface InspectionPlanManagementService {

    /**
     * 创建巡查计划管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectionPlanManagement(@Valid InspectionPlanManagementSaveReqVO createReqVO);

    /**
     * 更新巡查计划管理
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectionPlanManagement(@Valid InspectionPlanManagementSaveReqVO updateReqVO);

    /**
     * 删除巡查计划管理
     *
     * @param id 编号
     */
    void deleteInspectionPlanManagement(Long id);

    /**
     * 获得巡查计划管理
     *
     * @param id 编号
     * @return 巡查计划管理
     */
    InspectionPlanManagementDO getInspectionPlanManagement(Long id);

    /**
     * 获得巡查计划管理分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查计划管理分页
     */
    PageResult<InspectionPlanManagementDO> getInspectionPlanManagementPage(InspectionPlanManagementPageReqVO pageReqVO);

}