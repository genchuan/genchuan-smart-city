package cn.iocoder.yudao.module.evaluate.service.patrolinspection;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.patrolinspection.vo.PatrolInspectionPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.patrolinspection.vo.PatrolInspectionSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.patrolinspection.PatrolInspectionDO;
import jakarta.validation.Valid;

/**
 * 巡查巡检 Service 接口
 *
 * @author 亘川智城
 */
public interface PatrolInspectionService {

    /**
     * 创建巡查巡检
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPatrolInspection(@Valid PatrolInspectionSaveReqVO createReqVO);

    /**
     * 更新巡查巡检
     *
     * @param updateReqVO 更新信息
     */
    void updatePatrolInspection(@Valid PatrolInspectionSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检
     *
     * @param id 编号
     */
    void deletePatrolInspection(Long id);

    /**
     * 获得巡查巡检
     *
     * @param id 编号
     * @return 巡查巡检
     */
    PatrolInspectionDO getPatrolInspection(Long id);

    /**
     * 获得巡查巡检分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检分页
     */
    PageResult<PatrolInspectionDO> getPatrolInspectionPage(PatrolInspectionPageReqVO pageReqVO);

}