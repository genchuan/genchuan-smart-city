package cn.iocoder.yudao.module.datacenter.service.alarm.count.earlywarncustomcfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarncustomcfg.vo.EarlyWarnCustomCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarncustomcfg.vo.EarlyWarnCustomCfgSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarncustomcfg.EarlyWarnCustomCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 预警告警自定义统计配置 Service 接口
 *
 * @author zcq
 */
public interface EarlyWarnCustomCfgService {

    /**
     * 创建预警告警自定义统计配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEarlyWarnCustomCfg(@Valid EarlyWarnCustomCfgSaveReqVO createReqVO);

    /**
     * 更新预警告警自定义统计配置
     *
     * @param updateReqVO 更新信息
     */
    void updateEarlyWarnCustomCfg(@Valid EarlyWarnCustomCfgSaveReqVO updateReqVO);

    /**
     * 删除预警告警自定义统计配置
     *
     * @param id 编号
     */
    void deleteEarlyWarnCustomCfg(Long id);

    /**
     * 获得预警告警自定义统计配置
     *
     * @param id 编号
     * @return 预警告警自定义统计配置
     */
    EarlyWarnCustomCfgDO getEarlyWarnCustomCfg(Long id);

    /**
     * 获得预警告警自定义统计配置分页
     *
     * @param pageReqVO 分页查询
     * @return 预警告警自定义统计配置分页
     */
    PageResult<EarlyWarnCustomCfgDO> getEarlyWarnCustomCfgPage(EarlyWarnCustomCfgPageReqVO pageReqVO);

}