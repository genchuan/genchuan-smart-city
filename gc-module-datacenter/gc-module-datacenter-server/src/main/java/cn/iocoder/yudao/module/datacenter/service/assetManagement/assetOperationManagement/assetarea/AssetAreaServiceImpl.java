package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetOperationManagement.assetarea;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetarea.vo.AssetAreaPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetarea.vo.AssetAreaSaveReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetarea.AssetAreaDO;
import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetOperationManagement.assetarea.AssetAreaMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;



import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 资产关联行政区划 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetAreaServiceImpl implements AssetAreaService {

    @Resource
    private AssetAreaMapper assetAreaMapper;

    @Override
    public Long createAssetArea(AssetAreaSaveReqVO createReqVO) {
        // 插入
        AssetAreaDO assetArea = BeanUtils.toBean(createReqVO, AssetAreaDO.class);
        assetAreaMapper.insert(assetArea);
        // 返回
        return assetArea.getId();
    }

    @Override
    public void updateAssetArea(AssetAreaSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetAreaExists(updateReqVO.getId());
        // 更新
        AssetAreaDO updateObj = BeanUtils.toBean(updateReqVO, AssetAreaDO.class);
        assetAreaMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetArea(Long id) {
        // 校验存在
        validateAssetAreaExists(id);
        // 删除
        assetAreaMapper.deleteById(id);
    }

    private void validateAssetAreaExists(Long id) {
        if (assetAreaMapper.selectById(id) == null) {
            throw exception(ASSET_AREA_NOT_EXISTS);
        }
    }

    @Override
    public AssetAreaDO getAssetArea(Long id) {
        return assetAreaMapper.selectById(id);
    }

    @Override
    public PageResult<AssetAreaDO> getAssetAreaPage(AssetAreaPageReqVO pageReqVO) {
        return assetAreaMapper.selectPage(pageReqVO);
    }

}