package cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.depositplan;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 押金方案 Service 接口
 *
 * @author 亘川智城
 */
public interface DepositPlanService {

    /**
     * 创建押金方案
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDepositPlan(@Valid DepositPlanSaveReqVO createReqVO);

    /**
     * 更新押金方案
     *
     * @param updateReqVO 更新信息
     */
    void updateDepositPlan(@Valid DepositPlanSaveReqVO updateReqVO);

    /**
     * 删除押金方案
     *
     * @param id 编号
     */
    void deleteDepositPlan(Long id);

    /**
    * 批量删除押金方案
    *
    * @param ids 编号
    */
    void deleteDepositPlanListByIds(List<Long> ids);

    /**
     * 获得押金方案
     *
     * @param id 编号
     * @return 押金方案
     */
    DepositPlanDO getDepositPlan(Long id);

    /**
     * 获得押金方案分页
     *
     * @param pageReqVO 分页查询
     * @return 押金方案分页
     */
    PageResult<DepositPlanDO> getDepositPlanPage(DepositPlanPageReqVO pageReqVO);

}
