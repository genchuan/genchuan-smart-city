package cn.iocoder.yudao.module.smartcity.service.inspectioninspectionplan;

import java.util.*;
import jakarta.validation.Valid;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectioninspectionplan.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectioninspectionplan.InspectionInspectionPlanDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 巡查计划 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface InspectionInspectionPlanService {

    /**
     * 创建巡查计划
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectionInspectionPlan(@Valid InspectionInspectionPlanSaveReqVO createReqVO);

    /**
     * 更新巡查计划
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectionInspectionPlan(@Valid InspectionInspectionPlanSaveReqVO updateReqVO);

    /**
     * 删除巡查计划
     *
     * @param id 编号
     */
    void deleteInspectionInspectionPlan(Long id);

    /**
     * 获得巡查计划
     *
     * @param id 编号
     * @return 巡查计划
     */
    InspectionInspectionPlanDO getInspectionInspectionPlan(Long id);

    /**
     * 获得巡查计划分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查计划分页
     */
    PageResult<InspectionInspectionPlanDO> getInspectionInspectionPlanPage(InspectionInspectionPlanPageReqVO pageReqVO);

}