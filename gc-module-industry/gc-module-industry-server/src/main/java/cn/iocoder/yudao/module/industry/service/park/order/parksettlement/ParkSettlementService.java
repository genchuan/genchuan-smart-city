package cn.iocoder.yudao.module.industry.service.park.order.parksettlement;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parksettlement.vo.ParkSettlementPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parksettlement.vo.ParkSettlementSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parksettlement.ParkSettlementDO;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 分账结算表 Service 接口
 *
 * @author lxs
 */
public interface ParkSettlementService {

    /**
     * 创建分账结算表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkSettlement(@Valid ParkSettlementSaveReqVO createReqVO);

    /**
     * 更新分账结算表
     *
     * @param updateReqVO 更新信息
     */
    void updateParkSettlement(@Valid ParkSettlementSaveReqVO updateReqVO);

    /**
     * 删除分账结算表
     *
     * @param id 编号
     */
    void deleteParkSettlement(Long id);

    /**
     * 获得分账结算表
     *
     * @param id 编号
     * @return 分账结算表
     */
    ParkSettlementDO getParkSettlement(Long id);

    /**
     * 获得分账结算表分页
     *
     * @param pageReqVO 分页查询
     * @return 分账结算表分页
     */
    PageResult<ParkSettlementDO> getParkSettlementPage(ParkSettlementPageReqVO pageReqVO);

}
