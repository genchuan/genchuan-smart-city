package cn.iocoder.yudao.module.envirhealth.service.publictoilet.toiletconsumable;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletconsumable.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletConsumableDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.ToiletConsumableDetailDO;
import jakarta.validation.Valid;

import java.util.List;

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
     * 批量公厕耗材配置
     *
     * @param ids 编号列表
     */
    void deleteToiletConsumableBatch(List<Long> ids);

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

    /**
     * 批量补充登记公厕耗材
     *
     * @param reqVO 批量补充登记请求
     */
    void batchSupplyToiletConsumable(ToiletConsumableBatchSupplyReqVO reqVO);

    /**
     * 补充登记公厕耗材
     *
     * @param reqVO 批量补充登记请求
     */
    void supplyToiletConsumable(ToiletConsumableSupplyReqVO reqVO);

    /**
     * 卡片/圆环图/柱状图统计(待补充)
     */
    ToiletConsumablePendingRespVO getPending();
}