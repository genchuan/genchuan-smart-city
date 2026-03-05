package cn.iocoder.yudao.module.envirhealth.service.publictoilet.toiletconsumable;

import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.publictoilet.PublicToiletPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletconsumable.ToiletConsumablePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletconsumable.ToiletConsumableSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.PublicToiletDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.ToiletConsumableDetailDO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletConsumableDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 公厕耗材配置 Service 接口
 *
 * @author 亘川智城
 */
public interface ToiletConsumableService {

    /**
     * 创建公厕耗材配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createToiletConsumable(@Valid ToiletConsumableSaveReqVO createReqVO);

    /**
     * 更新公厕耗材配置
     *
     * @param updateReqVO 更新信息
     */
    void updateToiletConsumable(@Valid ToiletConsumableSaveReqVO updateReqVO);

    /**
     * 删除公厕耗材配置
     *
     * @param id 编号
     */
    void deleteToiletConsumable(Long id);

    /**
     * 获得公厕耗材配置
     *
     * @param id 编号
     * @return 公厕耗材配置
     */
    ToiletConsumableDO getToiletConsumable(Long id);

    /**
     * 获得公厕耗材配置分页
     *
     * @param pageReqVO 分页查询
     * @return 公厕耗材配置分页
     */
    PageResult<ToiletConsumableDO> getToiletConsumablePage(ToiletConsumablePageReqVO pageReqVO);

    /**
     * 获得公厕耗材分页(详情)
     *
     * @param pageReqVO 分页查询
     * @return 公厕耗材配置分页
     */
    PageResult<ToiletConsumableDetailDO> getToiletConsumableDetailPage(ToiletConsumablePageReqVO pageReqVO);
}