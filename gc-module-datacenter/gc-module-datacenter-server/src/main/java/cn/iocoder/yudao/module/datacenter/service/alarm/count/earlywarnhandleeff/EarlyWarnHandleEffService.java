package cn.iocoder.yudao.module.datacenter.service.alarm.count.earlywarnhandleeff;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnhandleeff.vo.EarlyWarnHandleEffPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnhandleeff.vo.EarlyWarnHandleEffSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarnhandleeff.EarlyWarnHandleEffDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 预警告警处置效率统计 Service 接口
 *
 * @author zcq
 */
public interface EarlyWarnHandleEffService {

    /**
     * 创建预警告警处置效率统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEarlyWarnHandleEff(@Valid EarlyWarnHandleEffSaveReqVO createReqVO);

    /**
     * 更新预警告警处置效率统计
     *
     * @param updateReqVO 更新信息
     */
    void updateEarlyWarnHandleEff(@Valid EarlyWarnHandleEffSaveReqVO updateReqVO);

    /**
     * 删除预警告警处置效率统计
     *
     * @param id 编号
     */
    void deleteEarlyWarnHandleEff(Long id);

    /**
     * 获得预警告警处置效率统计
     *
     * @param id 编号
     * @return 预警告警处置效率统计
     */
    EarlyWarnHandleEffDO getEarlyWarnHandleEff(Long id);

    /**
     * 获得预警告警处置效率统计分页
     *
     * @param pageReqVO 分页查询
     * @return 预警告警处置效率统计分页
     */
    PageResult<EarlyWarnHandleEffDO> getEarlyWarnHandleEffPage(EarlyWarnHandleEffPageReqVO pageReqVO);

}