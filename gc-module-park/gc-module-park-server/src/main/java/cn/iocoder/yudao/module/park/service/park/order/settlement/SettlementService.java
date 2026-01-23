package cn.iocoder.yudao.module.park.service.park.order.settlement;

import java.util.*;

import cn.iocoder.yudao.module.park.controller.admin.park.order.settlement.vo.SettlementPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.order.settlement.vo.SettlementSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.order.settlement.SettlementDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 分账结算 Service 接口
 *
 * @author 亘川智城
 */
public interface SettlementService {

    /**
     * 创建分账结算
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSettlement(@Valid SettlementSaveReqVO createReqVO);

    /**
     * 更新分账结算
     *
     * @param updateReqVO 更新信息
     */
    void updateSettlement(@Valid SettlementSaveReqVO updateReqVO);

    /**
     * 删除分账结算
     *
     * @param id 编号
     */
    void deleteSettlement(Long id);

    /**
     * 获得分账结算
     *
     * @param id 编号
     * @return 分账结算
     */
    SettlementDO getSettlement(Long id);

    /**
     * 获得分账结算分页
     *
     * @param pageReqVO 分页查询
     * @return 分账结算分页
     */
    PageResult<SettlementDO> getSettlementPage(SettlementPageReqVO pageReqVO);

}
