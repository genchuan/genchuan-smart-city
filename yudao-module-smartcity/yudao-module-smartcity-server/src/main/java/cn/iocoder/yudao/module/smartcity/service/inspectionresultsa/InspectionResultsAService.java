package cn.iocoder.yudao.module.smartcity.service.inspectionresultsa;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionresultsa.vo.InspectionResultsAPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionresultsa.vo.InspectionResultsASaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectionresultsa.InspectionResultsADO;
import jakarta.validation.Valid;

/**
 * 巡查结果 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface InspectionResultsAService {

    /**
     * 创建巡查结果
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectionResultsA(@Valid InspectionResultsASaveReqVO createReqVO);

    /**
     * 更新巡查结果
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectionResultsA(@Valid InspectionResultsASaveReqVO updateReqVO);

    /**
     * 删除巡查结果
     *
     * @param id 编号
     */
    void deleteInspectionResultsA(Long id);

    /**
     * 获得巡查结果
     *
     * @param id 编号
     * @return 巡查结果
     */
    InspectionResultsADO getInspectionResultsA(Long id);

    /**
     * 获得巡查结果分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查结果分页
     */
    PageResult<InspectionResultsADO> getInspectionResultsAPage(InspectionResultsAPageReqVO pageReqVO);

}