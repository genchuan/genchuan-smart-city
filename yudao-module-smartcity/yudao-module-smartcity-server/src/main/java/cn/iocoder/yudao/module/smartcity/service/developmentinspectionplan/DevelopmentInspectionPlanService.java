package cn.iocoder.yudao.module.smartcity.service.developmentinspectionplan;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.developmentinspectionplan.vo.DevelopmentInspectionPlanPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.developmentinspectionplan.vo.DevelopmentInspectionPlanSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.developmentinspectionplan.DevelopmentInspectionPlanDO;
import jakarta.validation.Valid;

/**
 * 巡查计划管理 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface DevelopmentInspectionPlanService {

    /**
     * 创建巡查计划管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDevelopmentInspectionPlan(@Valid DevelopmentInspectionPlanSaveReqVO createReqVO);

    /**
     * 更新巡查计划管理
     *
     * @param updateReqVO 更新信息
     */
    void updateDevelopmentInspectionPlan(@Valid DevelopmentInspectionPlanSaveReqVO updateReqVO);

    /**
     * 删除巡查计划管理
     *
     * @param id 编号
     */
    void deleteDevelopmentInspectionPlan(Long id);

    /**
     * 获得巡查计划管理
     *
     * @param id 编号
     * @return 巡查计划管理
     */
    DevelopmentInspectionPlanDO getDevelopmentInspectionPlan(Long id);

    /**
     * 获得巡查计划管理分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查计划管理分页
     */
    PageResult<DevelopmentInspectionPlanDO> getDevelopmentInspectionPlanPage(DevelopmentInspectionPlanPageReqVO pageReqVO);

}