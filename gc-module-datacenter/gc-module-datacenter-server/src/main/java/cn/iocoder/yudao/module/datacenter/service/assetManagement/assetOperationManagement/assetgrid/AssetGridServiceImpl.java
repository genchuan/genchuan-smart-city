package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetOperationManagement.assetgrid;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetgrid.vo.AssetGridPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetgrid.vo.AssetGridSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetgrid.AssetGridDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetOperationManagement.assetgrid.AssetGridMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 资产关联网格 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetGridServiceImpl implements AssetGridService {

    @Resource
    private AssetGridMapper assetGridMapper;

    @Override
    public Long createAssetGrid(AssetGridSaveReqVO createReqVO) {
        // 插入
        AssetGridDO assetGrid = BeanUtils.toBean(createReqVO, AssetGridDO.class);
        assetGridMapper.insert(assetGrid);
        // 返回
        return assetGrid.getId();
    }

    @Override
    public void updateAssetGrid(AssetGridSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetGridExists(updateReqVO.getId());
        // 更新
        AssetGridDO updateObj = BeanUtils.toBean(updateReqVO, AssetGridDO.class);
        assetGridMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetGrid(Long id) {
        // 校验存在
        validateAssetGridExists(id);
        // 删除
        assetGridMapper.deleteById(id);
    }

    private void validateAssetGridExists(Long id) {
        if (assetGridMapper.selectById(id) == null) {
            throw exception(ASSET_GRID_NOT_EXISTS);
        }
    }

    @Override
    public AssetGridDO getAssetGrid(Long id) {
        return assetGridMapper.selectById(id);
    }

    @Override
    public PageResult<AssetGridDO> getAssetGridPage(AssetGridPageReqVO pageReqVO) {
        return assetGridMapper.selectPage(pageReqVO);
    }

}