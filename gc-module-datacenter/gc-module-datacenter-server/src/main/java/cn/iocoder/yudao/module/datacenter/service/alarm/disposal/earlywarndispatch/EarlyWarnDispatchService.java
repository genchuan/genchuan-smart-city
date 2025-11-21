package cn.iocoder.yudao.module.datacenter.service.alarm.disposal.earlywarndispatch;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarndispatch.vo.EarlyWarnDispatchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarndispatch.vo.EarlyWarnDispatchSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarndispatch.EarlyWarnDispatchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 预警告警派单 Service 接口
 *
 * @author zcq
 */
public interface EarlyWarnDispatchService {

    /**
     * 创建预警告警派单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEarlyWarnDispatch(@Valid EarlyWarnDispatchSaveReqVO createReqVO);

    /**
     * 更新预警告警派单
     *
     * @param updateReqVO 更新信息
     */
    void updateEarlyWarnDispatch(@Valid EarlyWarnDispatchSaveReqVO updateReqVO);

    /**
     * 删除预警告警派单
     *
     * @param id 编号
     */
    void deleteEarlyWarnDispatch(Long id);

    /**
     * 获得预警告警派单
     *
     * @param id 编号
     * @return 预警告警派单
     */
    EarlyWarnDispatchDO getEarlyWarnDispatch(Long id);

    /**
     * 获得预警告警派单分页
     *
     * @param pageReqVO 分页查询
     * @return 预警告警派单分页
     */
    PageResult<EarlyWarnDispatchDO> getEarlyWarnDispatchPage(EarlyWarnDispatchPageReqVO pageReqVO);

}