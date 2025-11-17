package cn.iocoder.yudao.module.datacenter.service.inspection.assign.inspecttaskallocaterec;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.assign.inspecttaskallocaterec.vo.InspectTaskAllocateRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.assign.inspecttaskallocaterec.vo.InspectTaskAllocateRecSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.assign.inspecttaskallocaterec.InspectTaskAllocateRecDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检任务分配记录 Service 接口
 *
 * @author zcq
 */
public interface InspectTaskAllocateRecService {

    /**
     * 创建巡查巡检任务分配记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectTaskAllocateRec(@Valid InspectTaskAllocateRecSaveReqVO createReqVO);

    /**
     * 更新巡查巡检任务分配记录
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectTaskAllocateRec(@Valid InspectTaskAllocateRecSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检任务分配记录
     *
     * @param id 编号
     */
    void deleteInspectTaskAllocateRec(Long id);

    /**
     * 获得巡查巡检任务分配记录
     *
     * @param id 编号
     * @return 巡查巡检任务分配记录
     */
    InspectTaskAllocateRecDO getInspectTaskAllocateRec(Long id);

    /**
     * 获得巡查巡检任务分配记录分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检任务分配记录分页
     */
    PageResult<InspectTaskAllocateRecDO> getInspectTaskAllocateRecPage(InspectTaskAllocateRecPageReqVO pageReqVO);

}