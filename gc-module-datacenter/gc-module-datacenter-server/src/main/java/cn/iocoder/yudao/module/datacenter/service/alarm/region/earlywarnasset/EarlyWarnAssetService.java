package cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarnasset;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnasset.vo.EarlyWarnAssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnasset.vo.EarlyWarnAssetSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnasset.EarlyWarnAssetDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 按资产分域预警告警统计 Service 接口
 *
 * @author zcq
 */
public interface EarlyWarnAssetService {

    /**
     * 创建按资产分域预警告警统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEarlyWarnAsset(@Valid EarlyWarnAssetSaveReqVO createReqVO);

    /**
     * 更新按资产分域预警告警统计
     *
     * @param updateReqVO 更新信息
     */
    void updateEarlyWarnAsset(@Valid EarlyWarnAssetSaveReqVO updateReqVO);

    /**
     * 删除按资产分域预警告警统计
     *
     * @param id 编号
     */
    void deleteEarlyWarnAsset(Long id);

    /**
     * 获得按资产分域预警告警统计
     *
     * @param id 编号
     * @return 按资产分域预警告警统计
     */
    EarlyWarnAssetDO getEarlyWarnAsset(Long id);

    /**
     * 获得按资产分域预警告警统计分页
     *
     * @param pageReqVO 分页查询
     * @return 按资产分域预警告警统计分页
     */
    PageResult<EarlyWarnAssetDO> getEarlyWarnAssetPage(EarlyWarnAssetPageReqVO pageReqVO);

}