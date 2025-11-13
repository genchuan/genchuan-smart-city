package cn.iocoder.yudao.module.datacenter.service.alarm.count.earlywarntime;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarntime.vo.EarlyWarnTimePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarntime.vo.EarlyWarnTimeSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarntime.EarlyWarnTimeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 预警告警时间维度统计 Service 接口
 *
 * @author zcq
 */
public interface EarlyWarnTimeService {

    /**
     * 创建预警告警时间维度统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEarlyWarnTime(@Valid EarlyWarnTimeSaveReqVO createReqVO);

    /**
     * 更新预警告警时间维度统计
     *
     * @param updateReqVO 更新信息
     */
    void updateEarlyWarnTime(@Valid EarlyWarnTimeSaveReqVO updateReqVO);

    /**
     * 删除预警告警时间维度统计
     *
     * @param id 编号
     */
    void deleteEarlyWarnTime(Long id);

    /**
     * 获得预警告警时间维度统计
     *
     * @param id 编号
     * @return 预警告警时间维度统计
     */
    EarlyWarnTimeDO getEarlyWarnTime(Long id);

    /**
     * 获得预警告警时间维度统计分页
     *
     * @param pageReqVO 分页查询
     * @return 预警告警时间维度统计分页
     */
    PageResult<EarlyWarnTimeDO> getEarlyWarnTimePage(EarlyWarnTimePageReqVO pageReqVO);

}