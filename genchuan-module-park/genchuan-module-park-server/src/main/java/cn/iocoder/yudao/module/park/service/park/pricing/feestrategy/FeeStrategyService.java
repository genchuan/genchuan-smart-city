package cn.iocoder.yudao.module.park.service.park.pricing.feestrategy;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.feestrategy.vo.FeeStrategyPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.feestrategy.vo.FeeStrategySaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.feestrategy.FeeStrategyDO;
import jakarta.validation.Valid;

/**
 * 费率策略 Service 接口
 *
 * @author 亘川智城
 */
public interface FeeStrategyService {

    /**
     * 创建费率策略
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createFeeStrategy(@Valid FeeStrategySaveReqVO createReqVO);

    /**
     * 更新费率策略
     *
     * @param updateReqVO 更新信息
     */
    void updateFeeStrategy(@Valid FeeStrategySaveReqVO updateReqVO);

    /**
     * 删除费率策略
     *
     * @param id 编号
     */
    void deleteFeeStrategy(Long id);

    /**
     * 获得费率策略
     *
     * @param id 编号
     * @return 费率策略
     */
    FeeStrategyDO getFeeStrategy(Long id);

    /**
     * 获得费率策略分页
     *
     * @param pageReqVO 分页查询
     * @return 费率策略分页
     */
    PageResult<FeeStrategyDO> getFeeStrategyPage(FeeStrategyPageReqVO pageReqVO);

}
