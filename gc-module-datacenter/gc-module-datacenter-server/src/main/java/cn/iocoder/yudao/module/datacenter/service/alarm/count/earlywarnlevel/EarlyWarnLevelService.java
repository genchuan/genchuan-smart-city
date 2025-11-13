package cn.iocoder.yudao.module.datacenter.service.alarm.count.earlywarnlevel;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnlevel.vo.EarlyWarnLevelPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnlevel.vo.EarlyWarnLevelSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarnlevel.EarlyWarnLevelDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 预警告警等级维度统计 Service 接口
 *
 * @author zcq
 */
public interface EarlyWarnLevelService {

    /**
     * 创建预警告警等级维度统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEarlyWarnLevel(@Valid EarlyWarnLevelSaveReqVO createReqVO);

    /**
     * 更新预警告警等级维度统计
     *
     * @param updateReqVO 更新信息
     */
    void updateEarlyWarnLevel(@Valid EarlyWarnLevelSaveReqVO updateReqVO);

    /**
     * 删除预警告警等级维度统计
     *
     * @param id 编号
     */
    void deleteEarlyWarnLevel(Long id);

    /**
     * 获得预警告警等级维度统计
     *
     * @param id 编号
     * @return 预警告警等级维度统计
     */
    EarlyWarnLevelDO getEarlyWarnLevel(Long id);

    /**
     * 获得预警告警等级维度统计分页
     *
     * @param pageReqVO 分页查询
     * @return 预警告警等级维度统计分页
     */
    PageResult<EarlyWarnLevelDO> getEarlyWarnLevelPage(EarlyWarnLevelPageReqVO pageReqVO);

}