package cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarnregion;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnregion.vo.EarlyWarnRegionGenerateReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnregion.vo.EarlyWarnRegionPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnregion.vo.EarlyWarnRegionSaveReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnregion.vo.WarningAlertRespVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnregion.EarlyWarnRegionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 按行政区划预警告警统计 Service 接口
 *
 * @author zcq
 */
public interface EarlyWarnRegionService {

    /**
     * 创建按行政区划预警告警统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEarlyWarnRegion(@Valid EarlyWarnRegionSaveReqVO createReqVO);

    /**
     * 更新按行政区划预警告警统计
     *
     * @param updateReqVO 更新信息
     */
    void updateEarlyWarnRegion(@Valid EarlyWarnRegionSaveReqVO updateReqVO);

    /**
     * 删除按行政区划预警告警统计
     *
     * @param id 编号
     */
    void deleteEarlyWarnRegion(Long id);

    /**
     * 获得按行政区划预警告警统计
     *
     * @param id 编号
     * @return 按行政区划预警告警统计
     */
    EarlyWarnRegionDO getEarlyWarnRegion(Long id);

    /**
     * 获得按行政区划预警告警统计分页
     *
     * @param pageReqVO 分页查询
     * @return 按行政区划预警告警统计分页
     */
    PageResult<EarlyWarnRegionDO> getEarlyWarnRegionPage(EarlyWarnRegionPageReqVO pageReqVO);

    /**
     * 生成按行政区划预警告警统计
     *
     * @param generateReqVO 生成统计请求参数
     * @return 统计记录ID
     */
    Long generateEarlyWarnRegionStat(@Valid EarlyWarnRegionGenerateReqVO generateReqVO);

    /**
     * 获取预警明细分页（钻取功能）
     *
     * @param regionCode 行政区划代码
     * @param statCycle 统计周期
     * @param pageReqVO 分页参数
     * @return 预警明细分页
     */
    PageResult<WarningAlertRespVO> getWarningAlertPage(String regionCode, String statCycle, PageParam pageReqVO);
}