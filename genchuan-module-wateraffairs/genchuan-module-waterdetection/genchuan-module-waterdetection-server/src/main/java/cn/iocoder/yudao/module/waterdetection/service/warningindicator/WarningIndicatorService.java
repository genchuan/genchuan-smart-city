package cn.iocoder.yudao.module.waterdetection.service.warningindicator;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.warningindicator.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.warningindicator.WarningIndicatorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 预警指标配置 Service 接口
 *
 * @author zcq
 */
public interface WarningIndicatorService {

    /**
     * 创建预警指标配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWarningIndicator(@Valid WarningIndicatorSaveReqVO createReqVO);

    /**
     * 更新预警指标配置
     *
     * @param updateReqVO 更新信息
     */
    void updateWarningIndicator(@Valid WarningIndicatorSaveReqVO updateReqVO);

    /**
     * 删除预警指标配置
     *
     * @param id 编号
     */
    void deleteWarningIndicator(Long id);

    /**
     * 获得预警指标配置
     *
     * @param id 编号
     * @return 预警指标配置
     */
    WarningIndicatorDO getWarningIndicator(Long id);

    /**
     * 获得预警指标配置分页
     *
     * @param pageReqVO 分页查询
     * @return 预警指标配置分页
     */
    PageResult<WarningIndicatorDO> getWarningIndicatorPage(WarningIndicatorPageReqVO pageReqVO);

}