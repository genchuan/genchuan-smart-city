package cn.iocoder.yudao.module.datacenter.service.inspection.count.inspectefficacyrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectefficacyrpt.vo.InspectEfficacyRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectefficacyrpt.vo.InspectEfficacyRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.count.inspectefficacyrpt.InspectEfficacyRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检效率统计 Service 接口
 *
 * @author zcq
 */
public interface InspectEfficacyRptService {

    /**
     * 创建巡查巡检效率统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectEfficacyRpt(@Valid InspectEfficacyRptSaveReqVO createReqVO);

    /**
     * 更新巡查巡检效率统计
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectEfficacyRpt(@Valid InspectEfficacyRptSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检效率统计
     *
     * @param id 编号
     */
    void deleteInspectEfficacyRpt(Long id);

    /**
     * 获得巡查巡检效率统计
     *
     * @param id 编号
     * @return 巡查巡检效率统计
     */
    InspectEfficacyRptDO getInspectEfficacyRpt(Long id);

    /**
     * 获得巡查巡检效率统计分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检效率统计分页
     */
    PageResult<InspectEfficacyRptDO> getInspectEfficacyRptPage(InspectEfficacyRptPageReqVO pageReqVO);

}