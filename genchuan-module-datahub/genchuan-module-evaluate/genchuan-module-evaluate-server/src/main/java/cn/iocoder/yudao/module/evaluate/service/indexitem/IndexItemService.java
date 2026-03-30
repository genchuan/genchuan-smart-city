package cn.iocoder.yudao.module.evaluate.service.indexitem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexitem.vo.IndexItemPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexitem.vo.IndexItemSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexitem.IndexItemDO;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

/**
 * 指标项 Service 接口
 *
 * @author 亘川智城
 */
public interface IndexItemService {

    /**
     * 创建指标项
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createIndexItem(@Valid IndexItemSaveReqVO createReqVO);

    /**
     * 更新指标项
     *
     * @param updateReqVO 更新信息
     */
    void updateIndexItem(@Valid IndexItemSaveReqVO updateReqVO);

    /**
     * 删除指标项
     *
     * @param id 编号
     */
    void deleteIndexItem(Long id);

    /**
     * 获得指标项
     *
     * @param id 编号
     * @return 指标项
     */
    IndexItemDO getIndexItem(Long id);

    /**
     * 获得指标项分页
     *
     * @param pageReqVO 分页查询
     * @return 指标项分页
     */
    PageResult<IndexItemDO> getIndexItemPage(IndexItemPageReqVO pageReqVO);

    /**
     * 根据业务UUID查询指标项（兜底）
     *
     * @param itemId 指标项业务ID（UUID）
     * @return 指标项
     */
    IndexItemDO getIndexItemByItemId(String itemId);

    /**
     * 批量更新指标项的权重（用于计算时自动归一化）
     *
     * @param itemWeights itemId -> 归一化后的权重
     */
    void updateBatchItemWeight(Map<Long, java.math.BigDecimal> itemWeights);

    /**
     * 根据 systemUuid 获取指标项列表
     *
     * @param systemUuid 体系UUID
     * @return 指标项列表
     */
    List<IndexItemDO> getItemListBySystemIdFromCache(String systemUuid);

}
