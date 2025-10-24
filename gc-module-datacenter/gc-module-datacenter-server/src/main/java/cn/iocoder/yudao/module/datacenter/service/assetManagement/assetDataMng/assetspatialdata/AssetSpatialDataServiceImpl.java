package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetspatialdata;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetspatialdata.vo.AssetSpatialDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetspatialdata.vo.AssetSpatialDataSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetspatialdata.AssetSpatialDataDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetDataMng.assetspatialdata.AssetSpatialDataMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 资产空间数据 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetSpatialDataServiceImpl implements AssetSpatialDataService {

    @Resource
    private AssetSpatialDataMapper assetSpatialDataMapper;

    @Override
    public Long createAssetSpatialData(AssetSpatialDataSaveReqVO createReqVO) {
        // 插入
        AssetSpatialDataDO assetSpatialData = BeanUtils.toBean(createReqVO, AssetSpatialDataDO.class);
        assetSpatialDataMapper.insert(assetSpatialData);
        // 返回
        return assetSpatialData.getId();
    }

    @Override
    public void updateAssetSpatialData(AssetSpatialDataSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetSpatialDataExists(updateReqVO.getId());
        // 更新
        AssetSpatialDataDO updateObj = BeanUtils.toBean(updateReqVO, AssetSpatialDataDO.class);
        assetSpatialDataMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetSpatialData(Long id) {
        // 校验存在
        validateAssetSpatialDataExists(id);
        // 删除
        assetSpatialDataMapper.deleteById(id);
    }

    private void validateAssetSpatialDataExists(Long id) {
        if (assetSpatialDataMapper.selectById(id) == null) {
            throw exception(ASSET_SPATIAL_DATA_NOT_EXISTS);
        }
    }

    @Override
    public AssetSpatialDataDO getAssetSpatialData(Long id) {
        return assetSpatialDataMapper.selectById(id);
    }

    @Override
    public PageResult<AssetSpatialDataDO> getAssetSpatialDataPage(AssetSpatialDataPageReqVO pageReqVO) {
        return assetSpatialDataMapper.selectPage(pageReqVO);
    }

}