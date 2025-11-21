package cn.iocoder.yudao.module.datacenter.service.inspection.report.inspectproblemSb;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemSb.vo.InspectProblemSbPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemSb.vo.InspectProblemSbSaveVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.report.inspectproblemSb.InspectProblemSbDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检问题上报记录 Service 接口
 *
 * @author zcq
 */
public interface InspectProblemSbService {

    /**
     * 创建巡查巡检问题上报记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectProblemRpt(@Valid InspectProblemSbSaveVO createReqVO);

    /**
     * 更新巡查巡检问题上报记录
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectProblemRpt(@Valid InspectProblemSbSaveVO updateReqVO);

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
    InspectProblemSbDO getInspectProblemRpt(Long id);

    /**
     * 获得巡查巡检问题上报记录分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检问题上报记录分页
     */
    PageResult<InspectProblemSbDO> getInspectProblemRptPage(InspectProblemSbPageReqVO pageReqVO);

}