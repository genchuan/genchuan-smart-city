package cn.iocoder.yudao.module.industry.service.park.marketing.parksmoothparkingcard;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parksmoothparkingcard.vo.ParkSmoothParkingCardPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parksmoothparkingcard.vo.ParkSmoothParkingCardSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.marketing.parksmoothparkingcard.ParkSmoothParkingCardDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 畅停卡 Service 接口
 *
 * @author lxs
 */
public interface ParkSmoothParkingCardService {

    /**
     * 创建畅停卡
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkSmoothParkingCard(@Valid ParkSmoothParkingCardSaveReqVO createReqVO);

    /**
     * 更新畅停卡
     *
     * @param updateReqVO 更新信息
     */
    void updateParkSmoothParkingCard(@Valid ParkSmoothParkingCardSaveReqVO updateReqVO);

    /**
     * 删除畅停卡
     *
     * @param id 编号
     */
    void deleteParkSmoothParkingCard(Long id);

    /**
     * 获得畅停卡
     *
     * @param id 编号
     * @return 畅停卡
     */
    ParkSmoothParkingCardDO getParkSmoothParkingCard(Long id);

    /**
     * 获得畅停卡分页
     *
     * @param pageReqVO 分页查询
     * @return 畅停卡分页
     */
    PageResult<ParkSmoothParkingCardDO> getParkSmoothParkingCardPage(ParkSmoothParkingCardPageReqVO pageReqVO);

}
