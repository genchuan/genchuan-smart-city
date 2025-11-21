package cn.iocoder.yudao.module.datacenter.service.alarm.disposal.earlywarnhandletrace;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandletrace.vo.EarlyWarnHandleTracePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandletrace.vo.EarlyWarnHandleTraceSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarnhandletrace.EarlyWarnHandleTraceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 预警告警处置跟踪 Service 接口
 *
 * @author zcq
 */
public interface EarlyWarnHandleTraceService {

    /**
     * 创建预警告警处置跟踪
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEarlyWarnHandleTrace(@Valid EarlyWarnHandleTraceSaveReqVO createReqVO);

    /**
     * 更新预警告警处置跟踪
     *
     * @param updateReqVO 更新信息
     */
    void updateEarlyWarnHandleTrace(@Valid EarlyWarnHandleTraceSaveReqVO updateReqVO);

    /**
     * 删除预警告警处置跟踪
     *
     * @param id 编号
     */
    void deleteEarlyWarnHandleTrace(Long id);

    /**
     * 获得预警告警处置跟踪
     *
     * @param id 编号
     * @return 预警告警处置跟踪
     */
    EarlyWarnHandleTraceDO getEarlyWarnHandleTrace(Long id);

    /**
     * 获得预警告警处置跟踪分页
     *
     * @param pageReqVO 分页查询
     * @return 预警告警处置跟踪分页
     */
    PageResult<EarlyWarnHandleTraceDO> getEarlyWarnHandleTracePage(EarlyWarnHandleTracePageReqVO pageReqVO);

}