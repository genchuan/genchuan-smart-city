package cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarnmngcomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmngcomp.vo.EarlyWarnMngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmngcomp.vo.EarlyWarnMngCompSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnmngcomp.EarlyWarnMngCompDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 按管理部件预警告警统计 Service 接口
 *
 * @author zcq
 */
public interface EarlyWarnMngCompService {

    /**
     * 创建按管理部件预警告警统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEarlyWarnMngComp(@Valid EarlyWarnMngCompSaveReqVO createReqVO);

    /**
     * 更新按管理部件预警告警统计
     *
     * @param updateReqVO 更新信息
     */
    void updateEarlyWarnMngComp(@Valid EarlyWarnMngCompSaveReqVO updateReqVO);

    /**
     * 删除按管理部件预警告警统计
     *
     * @param id 编号
     */
    void deleteEarlyWarnMngComp(Long id);

    /**
     * 获得按管理部件预警告警统计
     *
     * @param id 编号
     * @return 按管理部件预警告警统计
     */
    EarlyWarnMngCompDO getEarlyWarnMngComp(Long id);

    /**
     * 获得按管理部件预警告警统计分页
     *
     * @param pageReqVO 分页查询
     * @return 按管理部件预警告警统计分页
     */
    PageResult<EarlyWarnMngCompDO> getEarlyWarnMngCompPage(EarlyWarnMngCompPageReqVO pageReqVO);

}