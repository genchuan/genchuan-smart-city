package cn.iocoder.yudao.module.envirhealth.service.publictoilet.consumable;

import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.consumable.ConsumablePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.consumable.ConsumableSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ConsumableDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 耗材字典 Service 接口
 *
 * @author 芋道源码
 */
public interface ConsumableService {

    /**
     * 创建耗材字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createConsumable(@Valid ConsumableSaveReqVO createReqVO);

    /**
     * 更新耗材字典
     *
     * @param updateReqVO 更新信息
     */
    void updateConsumable(@Valid ConsumableSaveReqVO updateReqVO);

    /**
     * 删除耗材字典
     *
     * @param id 编号
     */
    void deleteConsumable(Long id);

    /**
     * 获得耗材字典
     *
     * @param id 编号
     * @return 耗材字典
     */
    ConsumableDO getConsumable(Long id);

    /**
     * 获得耗材字典分页
     *
     * @param pageReqVO 分页查询
     * @return 耗材字典分页
     */
    PageResult<ConsumableDO> getConsumablePage(ConsumablePageReqVO pageReqVO);

}