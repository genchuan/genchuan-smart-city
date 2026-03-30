package cn.iocoder.yudao.module.waterdetection.service.waterbalance;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.waterbalance.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.waterbalance.WaterBalanceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 水量平衡与漏损分析 Service 接口
 *
 * @author zcq
 */
public interface WaterBalanceService {

    /**
     * 创建水量平衡与漏损分析
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWaterBalance(@Valid WaterBalanceSaveReqVO createReqVO);

    /**
     * 更新水量平衡与漏损分析
     *
     * @param updateReqVO 更新信息
     */
    void updateWaterBalance(@Valid WaterBalanceSaveReqVO updateReqVO);

    /**
     * 删除水量平衡与漏损分析
     *
     * @param id 编号
     */
    void deleteWaterBalance(Long id);

    /**
     * 获得水量平衡与漏损分析
     *
     * @param id 编号
     * @return 水量平衡与漏损分析
     */
    WaterBalanceDO getWaterBalance(Long id);

    /**
     * 获得水量平衡与漏损分析分页
     *
     * @param pageReqVO 分页查询
     * @return 水量平衡与漏损分析分页
     */
    PageResult<WaterBalanceDO> getWaterBalancePage(WaterBalancePageReqVO pageReqVO);

}