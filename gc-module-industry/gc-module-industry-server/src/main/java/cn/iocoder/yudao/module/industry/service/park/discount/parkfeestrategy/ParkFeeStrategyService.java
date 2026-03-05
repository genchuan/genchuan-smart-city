package cn.iocoder.yudao.module.industry.service.park.discount.parkfeestrategy;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkfeestrategy.vo.ParkFeeStrategyPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkfeestrategy.vo.ParkFeeStrategySaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkfeestrategy.ParkFeeStrategyDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 费率策略 Service 接口
 *
 * @author lxs
 */
public interface ParkFeeStrategyService {

    /**
     * 创建费率策略
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkFeeStrategy(@Valid ParkFeeStrategySaveReqVO createReqVO);

    /**
     * 更新费率策略
     *
     * @param updateReqVO 更新信息
     */
    void updateParkFeeStrategy(@Valid ParkFeeStrategySaveReqVO updateReqVO);

    /**
     * 删除费率策略
     *
     * @param id 编号
     */
    void deleteParkFeeStrategy(Long id);

    /**
     * 获得费率策略
     *
     * @param id 编号
     * @return 费率策略
     */
    ParkFeeStrategyDO getParkFeeStrategy(Long id);

    /**
     * 获得费率策略分页
     *
     * @param pageReqVO 分页查询
     * @return 费率策略分页
     */
    PageResult<ParkFeeStrategyDO> getParkFeeStrategyPage(ParkFeeStrategyPageReqVO pageReqVO);

}
