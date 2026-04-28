package cn.iocoder.yudao.module.studentmgmt.service.treatmgmt;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.treatmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.treatmgmt.TreatMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 就诊管理 Service 接口
 *
 * @author 芋道源码
 */
public interface TreatMgmtService {

    /**
     * 创建就诊管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTreatMgmt(@Valid TreatMgmtSaveReqVO createReqVO);

    /**
     * 更新就诊管理
     *
     * @param updateReqVO 更新信息
     */
    void updateTreatMgmt(@Valid TreatMgmtSaveReqVO updateReqVO);

    /**
     * 删除就诊管理
     *
     * @param id 编号
     */
    void deleteTreatMgmt(Long id);

    /**
    * 批量删除就诊管理
    *
    * @param ids 编号
    */
    void deleteTreatMgmtListByIds(List<Long> ids);

    /**
     * 获得就诊管理
     *
     * @param id 编号
     * @return 就诊管理
     */
    TreatMgmtDO getTreatMgmt(Long id);

    /**
     * 获得就诊管理分页
     *
     * @param pageReqVO 分页查询
     * @return 就诊管理分页
     */
    PageResult<TreatMgmtDO> getTreatMgmtPage(TreatMgmtPageReqVO pageReqVO);

    Boolean appoint(@Valid TreatMgmtAppointReqVO reqVO);

    Boolean audit(@Valid TreatMgmtAuditReqVO reqVO);

    Boolean register(@Valid TreatMgmtRegisterReqVO reqVO);

    Boolean feedback(@Valid TreatMgmtFeedbackReqVO reqVO);

    TreatMgmtChartRespVO chart(@Valid TreatMgmtChartReqVO reqVO);

    TreatMgmtDistributionRespVO treatDistribution(@Valid TreatMgmtChartReqVO reqVO);
}