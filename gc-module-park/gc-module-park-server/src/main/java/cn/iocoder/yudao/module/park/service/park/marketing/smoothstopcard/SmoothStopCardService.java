package cn.iocoder.yudao.module.park.service.park.marketing.smoothstopcard;

import cn.iocoder.yudao.module.park.controller.admin.park.marketing.smoothstopcard.vo.SmoothStopCardPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.marketing.smoothstopcard.vo.SmoothStopCardSaveReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.marketing.smoothstopcard.vo.VerifyOrderFreeReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.marketing.smoothstopcard.vo.VerifyOrderFreeRespVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.marketing.smoothstopcard.SmoothStopCardDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 畅停卡 Service 接口
 *
 * @author 亘川智城
 */
public interface SmoothStopCardService {

    /**
     * 创建畅停卡
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSmoothStopCard(@Valid SmoothStopCardSaveReqVO createReqVO);

    /**
     * 更新畅停卡
     *
     * @param updateReqVO 更新信息
     */
    void updateSmoothStopCard(@Valid SmoothStopCardSaveReqVO updateReqVO);

    /**
     * 删除畅停卡
     *
     * @param id 编号
     */
    void deleteSmoothStopCard(Long id);

    /**
     * 获得畅停卡
     *
     * @param id 编号
     * @return 畅停卡
     */
    SmoothStopCardDO getSmoothStopCard(Long id);

    /**
     * 获得畅停卡分页
     *
     * @param pageReqVO 分页查询
     * @return 畅停卡分页
     */
    PageResult<SmoothStopCardDO> getSmoothStopCardPage(SmoothStopCardPageReqVO pageReqVO);

    VerifyOrderFreeRespVO verifyOrderFreeBySmoothCard(VerifyOrderFreeReqVO reqVO);
}
