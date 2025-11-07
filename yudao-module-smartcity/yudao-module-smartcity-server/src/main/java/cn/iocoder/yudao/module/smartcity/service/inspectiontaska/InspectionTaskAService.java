package cn.iocoder.yudao.module.smartcity.service.inspectiontaska;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectiontaska.vo.InspectionTaskAPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectiontaska.vo.InspectionTaskASaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectiontaska.InspectionTaskADO;
import jakarta.validation.Valid;

/**
 * 巡查任务 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface InspectionTaskAService {

    /**
     * 创建巡查任务
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectionTaskA(@Valid InspectionTaskASaveReqVO createReqVO);

    /**
     * 更新巡查任务
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectionTaskA(@Valid InspectionTaskASaveReqVO updateReqVO);

    /**
     * 删除巡查任务
     *
     * @param id 编号
     */
    void deleteInspectionTaskA(Long id);

    /**
     * 获得巡查任务
     *
     * @param id 编号
     * @return 巡查任务
     */
    InspectionTaskADO getInspectionTaskA(Long id);

    /**
     * 获得巡查任务分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查任务分页
     */
    PageResult<InspectionTaskADO> getInspectionTaskAPage(InspectionTaskAPageReqVO pageReqVO);

}