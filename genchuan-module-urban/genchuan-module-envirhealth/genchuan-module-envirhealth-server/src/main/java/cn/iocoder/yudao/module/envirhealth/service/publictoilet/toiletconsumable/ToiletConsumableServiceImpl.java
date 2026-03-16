package cn.iocoder.yudao.module.envirhealth.service.publictoilet.toiletconsumable;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletconsumable.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletConsumableDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.ToiletConsumableDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet.ToiletConsumableMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 公厕耗材配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
@Slf4j
public class ToiletConsumableServiceImpl implements ToiletConsumableService {

    @Resource
    private ToiletConsumableMapper toiletConsumableMapper;

    @Override
    public Long createToiletConsumable(ToiletConsumableSaveReqVO createReqVO) {
        // 插入
        ToiletConsumableDO toiletConsumable = BeanUtils.toBean(createReqVO, ToiletConsumableDO.class);
        toiletConsumableMapper.insert(toiletConsumable);
        // 返回
        return toiletConsumable.getId();
    }

    @Override
    public void updateToiletConsumable(ToiletConsumableSaveReqVO updateReqVO) {
        // 校验存在
        validateToiletConsumableExists(updateReqVO.getId());
        // 更新
        ToiletConsumableDO updateObj = BeanUtils.toBean(updateReqVO, ToiletConsumableDO.class);
        toiletConsumableMapper.updateById(updateObj);
    }

    @Override
    public void deleteToiletConsumable(Long id) {
        // 校验存在
        validateToiletConsumableExists(id);
        // 删除
        toiletConsumableMapper.deleteById(id);
    }

    @Override
    public void deleteToiletConsumableBatch(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }

        List<ToiletConsumableDO> toiletConsumables = toiletConsumableMapper.selectBatchIds(ids);
        if (toiletConsumables.size() != ids.size()) {
            throw exception(TOILET_CONSUMABLE_NOT_EXISTS);
        }

        toiletConsumableMapper.deleteBatchIds(ids);
    }

    private void validateToiletConsumableExists(Long id) {
        if (toiletConsumableMapper.selectById(id) == null) {
            throw exception(TOILET_CONSUMABLE_NOT_EXISTS);
        }
    }

    @Override
    public ToiletConsumableDO getToiletConsumable(Long id) {
        return toiletConsumableMapper.selectById(id);
    }

    @Override
    public PageResult<ToiletConsumableDO> getToiletConsumablePage(ToiletConsumablePageReqVO pageReqVO) {
        return toiletConsumableMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<ToiletConsumableDetailDO> getToiletConsumableDetailPage(ToiletConsumablePageReqVO pageReqVO) {
        Long total = toiletConsumableMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<ToiletConsumableDetailDO> list = toiletConsumableMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchSupplyToiletConsumable(ToiletConsumableBatchSupplyReqVO reqVO) {
        // 1. 参数校验
        List<ToiletConsumableBatchSupplyReqVO.SupplyItem> supplyItems = reqVO.getSupplyItems();
        if (CollectionUtils.isEmpty(supplyItems)) {
            throw exception(TOILET_CONSUMABLE_ITEMS_NOT_EMPTY);
        }

        LocalDateTime now = LocalDateTime.now();

        // 2. 遍历每个补充项
        for (ToiletConsumableBatchSupplyReqVO.SupplyItem item : supplyItems) {
            String consumableId = item.getConsumableId();
            Integer supplyQuantity = item.getSupplyQuantity();

            // 校验补充数量
            if (supplyQuantity == null || supplyQuantity <= 0) {
                throw exception(TOILET_CONSUMABLE_SUPPLY_QUANTITY_INVALID, consumableId);
            }

            // 3. 查询所有consumable_id等于前端传递值的耗材配置
            LambdaQueryWrapperX<ToiletConsumableDO> queryWrapper = new LambdaQueryWrapperX<>();
            queryWrapper.eq(ToiletConsumableDO::getConsumableId, consumableId);

            List<ToiletConsumableDO> consumableList = toiletConsumableMapper.selectList(queryWrapper);

            if (CollectionUtils.isEmpty(consumableList)) {
                throw exception(TOILET_CONSUMABLE_NOT_FOUND_BY_ID, consumableId);
            }

            // 4. 批量更新这些耗材配置的库存
            for (ToiletConsumableDO consumable : consumableList) {
                // 获取当前库存（处理null值）
                Integer currentStock = consumable.getConsumableStock() != null ? consumable.getConsumableStock() : 0;
                Integer newStock = currentStock + supplyQuantity;

                // 计算缺口数量（阈值 - 新库存）
                Integer threshold = consumable.getConsumableThreshold() != null ? consumable.getConsumableThreshold() : 0;
                Integer gap = threshold - newStock;
                if (gap < 0) {
                    gap = 0;
                }

                //获取当前用户
                String nickname = SecurityFrameworkUtils.getLoginUserNickname();

                // 计算预警状态
                String warningStatus = calculateWarningStatus(newStock, threshold);

                // 更新耗材配置
                ToiletConsumableDO updateObj = new ToiletConsumableDO();
                updateObj.setId(consumable.getId());
                updateObj.setConsumableStock(newStock);
                updateObj.setLastSupplyTime(now);
                updateObj.setConsumableGap(gap);
                updateObj.setConsumableWarning(warningStatus);
                updateObj.setUpdater(nickname);

                toiletConsumableMapper.updateById(updateObj);
            }
        }

        // 5. 记录操作日志
        log.info("批量补充登记公厕耗材完成，补充项数量: {}", supplyItems.size());
    }

    /**
     * 计算预警状态
     */
    private String calculateWarningStatus(Integer stock, Integer threshold) {
        if (threshold == null || threshold <= 0) {
            return "正常";
        }

        if (stock == null) {
            stock = 0;
        }

        // 严重预警：库存低于阈值的30%
        if (stock < threshold * 0.3) {
            return "严重预警";
        }
        // 预警：库存低于阈值
        else if (stock < threshold) {
            return "预警";
        }
        // 正常：库存大于等于阈值
        else {
            return "正常";
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void supplyToiletConsumable(ToiletConsumableSupplyReqVO reqVO) {

        //验证存在
        ToiletConsumableDO consumable = toiletConsumableMapper.selectById(reqVO.getId());
        if (consumable == null) {
            throw exception(TOILET_CONSUMABLE_NOT_EXISTS);
        }

        LocalDateTime now = LocalDateTime.now();

        //获取当前库存数量和消耗阈值
        Integer currentStock = consumable.getConsumableStock() != null ? consumable.getConsumableStock() : 0;
        Integer threshold = consumable.getConsumableThreshold() != null ? consumable.getConsumableThreshold() : 0;
        Integer newStock = currentStock + reqVO.getSupplyQuantity();

        //计算当前的缺口数量
        Integer gap = threshold - newStock;
        if (gap < 0) {
            gap = 0;
        }

        //获取当前用户
        String nickname = SecurityFrameworkUtils.getLoginUserNickname();

        //计算预警状态
        String warningStatus = calculateWarningStatus(newStock, threshold);

        //更新操作
        ToiletConsumableDO updateObj = new ToiletConsumableDO();
        updateObj.setId(consumable.getId());
        updateObj.setConsumableStock(newStock);
        updateObj.setLastSupplyTime(now);
        updateObj.setConsumableGap(gap);
        updateObj.setConsumableWarning(warningStatus);
        updateObj.setUpdater(nickname);

        //处理json格式
        List<String> photoUrls = reqVO.getPhotoUrls();
        String photoUrlsJson = CollectionUtils.isEmpty(photoUrls)
                ? null
                : JsonUtils.toJsonString(photoUrls);
        updateObj.setPhotoUrls(photoUrlsJson);

        // 照片
        updateObj.setPhotoUrls(photoUrlsJson);

        toiletConsumableMapper.updateById(updateObj);
    }

    @Override
    public ToiletConsumablePendingRespVO getPending() {
        ToiletConsumablePendingRespVO resp = new ToiletConsumablePendingRespVO();
        resp.setPendingTotal(toiletConsumableMapper.countPendingTotal());
        resp.setHighWarningCount(toiletConsumableMapper.countHighWarningPending());
        resp.setPendingAreaCount(toiletConsumableMapper.countPendingAreaDistinct());
        resp.setTypeDistribution(toiletConsumableMapper.selectTypeDistributionPending());
        resp.setWarningDistribution(toiletConsumableMapper.selectWarningDistributionPending());
        resp.setGapByConsumable(toiletConsumableMapper.selectGapByConsumablePending());
        return resp;
    }
}