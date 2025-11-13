package cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarnmoncomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmoncomp.vo.EarlyWarnMonCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmoncomp.vo.EarlyWarnMonCompSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnmoncomp.EarlyWarnMonCompDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 按监测部件预警告警统计 Service 接口
 *
 * @author zcq
 */
public interface EarlyWarnMonCompService {

    /**
     * 创建按监测部件预警告警统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEarlyWarnMonComp(@Valid EarlyWarnMonCompSaveReqVO createReqVO);

    /**
     * 更新按监测部件预警告警统计
     *
     * @param updateReqVO 更新信息
     */
    void updateEarlyWarnMonComp(@Valid EarlyWarnMonCompSaveReqVO updateReqVO);

    /**
     * 删除按监测部件预警告警统计
     *
     * @param id 编号
     */
    void deleteEarlyWarnMonComp(Long id);

    /**
     * 获得按监测部件预警告警统计
     *
     * @param id 编号
     * @return 按监测部件预警告警统计
     */
    EarlyWarnMonCompDO getEarlyWarnMonComp(Long id);

    /**
     * 获得按监测部件预警告警统计分页
     *
     * @param pageReqVO 分页查询
     * @return 按监测部件预警告警统计分页
     */
    PageResult<EarlyWarnMonCompDO> getEarlyWarnMonCompPage(EarlyWarnMonCompPageReqVO pageReqVO);

}