package cn.iocoder.yudao.module.datacenter.service.alarm.disposal.earlywarnarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnarch.vo.EarlyWarnArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnarch.vo.EarlyWarnArchSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarnarch.EarlyWarnArchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 预警告警处置归档 Service 接口
 *
 * @author zcq
 */
public interface EarlyWarnArchService {

    /**
     * 创建预警告警处置归档
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEarlyWarnArch(@Valid EarlyWarnArchSaveReqVO createReqVO);

    /**
     * 更新预警告警处置归档
     *
     * @param updateReqVO 更新信息
     */
    void updateEarlyWarnArch(@Valid EarlyWarnArchSaveReqVO updateReqVO);

    /**
     * 删除预警告警处置归档
     *
     * @param id 编号
     */
    void deleteEarlyWarnArch(Long id);

    /**
     * 获得预警告警处置归档
     *
     * @param id 编号
     * @return 预警告警处置归档
     */
    EarlyWarnArchDO getEarlyWarnArch(Long id);

    /**
     * 获得预警告警处置归档分页
     *
     * @param pageReqVO 分页查询
     * @return 预警告警处置归档分页
     */
    PageResult<EarlyWarnArchDO> getEarlyWarnArchPage(EarlyWarnArchPageReqVO pageReqVO);

}