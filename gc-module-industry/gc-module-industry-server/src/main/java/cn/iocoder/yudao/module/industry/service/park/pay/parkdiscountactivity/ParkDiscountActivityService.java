package cn.iocoder.yudao.module.industry.service.park.pay.parkdiscountactivity;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkdiscountactivity.vo.ParkDiscountActivityPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkdiscountactivity.vo.ParkDiscountActivitySaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkdiscountactivity.ParkDiscountActivityDO;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 优惠活动 Service 接口
 *
 * @author lxs
 */
public interface ParkDiscountActivityService {

    /**
     * 创建优惠活动
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkDiscountActivity(@Valid ParkDiscountActivitySaveReqVO createReqVO);

    /**
     * 更新优惠活动
     *
     * @param updateReqVO 更新信息
     */
    void updateParkDiscountActivity(@Valid ParkDiscountActivitySaveReqVO updateReqVO);

    /**
     * 删除优惠活动
     *
     * @param id 编号
     */
    void deleteParkDiscountActivity(Long id);

    /**
     * 获得优惠活动
     *
     * @param id 编号
     * @return 优惠活动
     */
    ParkDiscountActivityDO getParkDiscountActivity(Long id);

    /**
     * 获得优惠活动分页
     *
     * @param pageReqVO 分页查询
     * @return 优惠活动分页
     */
    PageResult<ParkDiscountActivityDO> getParkDiscountActivityPage(ParkDiscountActivityPageReqVO pageReqVO);

}
