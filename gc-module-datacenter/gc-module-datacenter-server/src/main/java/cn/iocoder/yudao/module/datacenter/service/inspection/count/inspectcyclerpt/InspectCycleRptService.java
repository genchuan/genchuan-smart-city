package cn.iocoder.yudao.module.datacenter.service.inspection.count.inspectcyclerpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectcyclerpt.vo.InspectCycleRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectcyclerpt.vo.InspectCycleRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.count.inspectcyclerpt.InspectCycleRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检周期统计 Service 接口
 *
 * @author zcq
 */
public interface InspectCycleRptService {

    /**
     * 创建巡查巡检周期统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectCycleRpt(@Valid InspectCycleRptSaveReqVO createReqVO);

    /**
     * 更新巡查巡检周期统计
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectCycleRpt(@Valid InspectCycleRptSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检周期统计
     *
     * @param id 编号
     */
    void deleteInspectCycleRpt(Long id);

    /**
     * 获得巡查巡检周期统计
     *
     * @param id 编号
     * @return 巡查巡检周期统计
     */
    InspectCycleRptDO getInspectCycleRpt(Long id);

    /**
     * 获得巡查巡检周期统计分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检周期统计分页
     */
    PageResult<InspectCycleRptDO> getInspectCycleRptPage(InspectCycleRptPageReqVO pageReqVO);

}