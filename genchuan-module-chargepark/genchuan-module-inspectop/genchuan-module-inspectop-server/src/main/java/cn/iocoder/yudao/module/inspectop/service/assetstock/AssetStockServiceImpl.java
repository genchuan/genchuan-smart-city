package cn.iocoder.yudao.module.inspectop.service.assetstock;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.assetstock.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.assetstock.AssetStockDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.assetstock.AssetStockMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

/**
 * 库存管理 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class AssetStockServiceImpl implements AssetStockService {

    @Resource
    private AssetStockMapper assetStockMapper;

    @Override
    public Long createAssetStock(AssetStockSaveReqVO createReqVO) {
        // 插入
        AssetStockDO assetStock = BeanUtils.toBean(createReqVO, AssetStockDO.class);
        assetStockMapper.insert(assetStock);

        // 返回
        return assetStock.getId();
    }

    @Override
    public void updateAssetStock(AssetStockSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetStockExists(updateReqVO.getId());
        // 更新
        AssetStockDO updateObj = BeanUtils.toBean(updateReqVO, AssetStockDO.class);
        assetStockMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetStock(Long id) {
        // 校验存在
        validateAssetStockExists(id);
        // 删除
        assetStockMapper.deleteById(id);
    }

    @Override
        public void deleteAssetStockListByIds(List<Long> ids) {
        // 删除
        assetStockMapper.deleteByIds(ids);
        }


    private void validateAssetStockExists(Long id) {
        if (assetStockMapper.selectById(id) == null) {
            throw exception(ASSET_STOCK_NOT_EXISTS);
        }
    }

    @Override
    public AssetStockDO getAssetStock(Long id) {
        return assetStockMapper.selectById(id);
    }

    @Override
    public PageResult<AssetStockDO> getAssetStockPage(AssetStockPageReqVO pageReqVO) {
        return assetStockMapper.selectPage(pageReqVO);
    }

}