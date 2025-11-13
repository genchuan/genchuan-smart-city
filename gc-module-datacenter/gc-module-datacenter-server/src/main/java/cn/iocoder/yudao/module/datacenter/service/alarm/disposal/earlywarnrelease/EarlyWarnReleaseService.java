package cn.iocoder.yudao.module.datacenter.service.alarm.disposal.earlywarnrelease;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnrelease.vo.EarlyWarnReleasePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnrelease.vo.EarlyWarnReleaseSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarnrelease.EarlyWarnReleaseDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 预警告警解除 Service 接口
 *
 * @author zcq
 */
public interface EarlyWarnReleaseService {

    /**
     * 创建预警告警解除
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEarlyWarnRelease(@Valid EarlyWarnReleaseSaveReqVO createReqVO);

    /**
     * 更新预警告警解除
     *
     * @param updateReqVO 更新信息
     */
    void updateEarlyWarnRelease(@Valid EarlyWarnReleaseSaveReqVO updateReqVO);

    /**
     * 删除预警告警解除
     *
     * @param id 编号
     */
    void deleteEarlyWarnRelease(Long id);

    /**
     * 获得预警告警解除
     *
     * @param id 编号
     * @return 预警告警解除
     */
    EarlyWarnReleaseDO getEarlyWarnRelease(Long id);

    /**
     * 获得预警告警解除分页
     *
     * @param pageReqVO 分页查询
     * @return 预警告警解除分页
     */
    PageResult<EarlyWarnReleaseDO> getEarlyWarnReleasePage(EarlyWarnReleasePageReqVO pageReqVO);

}