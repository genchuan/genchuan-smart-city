package cn.iocoder.yudao.module.waterdetection.service.warningthreshold;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.warningthreshold.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.warningthreshold.WarningThresholdDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 预警阈值管理 Service 接口
 *
 * @author zcq
 */
public interface WarningThresholdService {

    /**
     * 创建预警阈值管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWarningThreshold(@Valid WarningThresholdSaveReqVO createReqVO);

    /**
     * 更新预警阈值管理
     *
     * @param updateReqVO 更新信息
     */
    void updateWarningThreshold(@Valid WarningThresholdSaveReqVO updateReqVO);

    /**
     * 删除预警阈值管理
     *
     * @param id 编号
     */
    void deleteWarningThreshold(Long id);

    /**
     * 获得预警阈值管理
     *
     * @param id 编号
     * @return 预警阈值管理
     */
    WarningThresholdDO getWarningThreshold(Long id);

    /**
     * 获得预警阈值管理分页
     *
     * @param pageReqVO 分页查询
     * @return 预警阈值管理分页
     */
    PageResult<WarningThresholdDO> getWarningThresholdPage(WarningThresholdPageReqVO pageReqVO);

}