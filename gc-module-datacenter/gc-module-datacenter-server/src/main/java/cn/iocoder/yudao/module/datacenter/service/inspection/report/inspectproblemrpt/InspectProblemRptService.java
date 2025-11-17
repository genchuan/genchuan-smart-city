package cn.iocoder.yudao.module.datacenter.service.inspection.report.inspectproblemrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemrpt.vo.InspectProblemRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemrpt.vo.InspectProblemRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.report.inspectproblemrpt.InspectProblemRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检问题上报记录 Service 接口
 *
 * @author zcq
 */
public interface InspectProblemRptService {

    /**
     * 创建巡查巡检问题上报记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectProblemRpt(@Valid InspectProblemRptSaveReqVO createReqVO);

    /**
     * 更新巡查巡检问题上报记录
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectProblemRpt(@Valid InspectProblemRptSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检问题上报记录
     *
     * @param id 编号
     */
    void deleteInspectProblemRpt(Long id);

    /**
     * 获得巡查巡检问题上报记录
     *
     * @param id 编号
     * @return 巡查巡检问题上报记录
     */
    InspectProblemRptDO getInspectProblemRpt(Long id);

    /**
     * 获得巡查巡检问题上报记录分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检问题上报记录分页
     */
    PageResult<InspectProblemRptDO> getInspectProblemRptPage(InspectProblemRptPageReqVO pageReqVO);

}