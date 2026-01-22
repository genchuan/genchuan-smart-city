package cn.iocoder.yudao.module.park.service.park.statrpt.chargeabnormal;

import java.util.*;

import cn.iocoder.yudao.module.park.controller.admin.park.statrpt.chargeabnormal.vo.*;
import cn.iocoder.yudao.module.park.dal.dataobject.park.statrpt.chargeabnormal.ChargeAbnormalDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 收费异常 Service 接口
 *
 * @author lxs
 */
public interface ChargeAbnormalService {

    /**
     * 创建收费异常
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createChargeAbnormal(@Valid ChargeAbnormalSaveReqVO createReqVO);

    /**
     * 更新收费异常
     *
     * @param updateReqVO 更新信息
     */
    void updateChargeAbnormal(@Valid ChargeAbnormalSaveReqVO updateReqVO);

    /**
     * 删除收费异常
     *
     * @param id 编号
     */
    void deleteChargeAbnormal(Long id);

    /**
     * 获得收费异常
     *
     * @param id 编号
     * @return 收费异常
     */
    ChargeAbnormalDO getChargeAbnormal(Long id);

    /**
     * 获得收费异常分页
     *
     * @param pageReqVO 分页查询
     * @return 收费异常分页
     */
    PageResult<ChargeAbnormalDO> getChargeAbnormalPage(ChargeAbnormalPageReqVO pageReqVO);


    StatReportRespVO statReport(StatReportReqVO reqVO);

    TrendRespVO statTrend(StatReportReqVO reqVO);
    List<StatDistributionRespVO> sortStat(StatReportReqVO reqVO);

    List<StatRegionRespVO> statGroupRegion(StatReportReqVO reqVO);

    PageResult<ChargeAbnormalDO> statPage(StatReportReqVO reqVO);
}
