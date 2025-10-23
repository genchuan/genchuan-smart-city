package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetOperationManagement.assetarea;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetarea.vo.AssetAreaListReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetarea.vo.AssetAreaSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetarea.AssetAreaDO;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetOperationManagement.assetarea.AssetAreaMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

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
        // 校验父级编号的有效性
        validateParentAssetArea(null, createReqVO.getParentId());
        // 校验名字的唯一性
        validateAssetAreaNameUnique(null, createReqVO.getParentId(), createReqVO.getName());

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
        // 校验父级编号的有效性
        validateParentAssetArea(updateReqVO.getId(), updateReqVO.getParentId());
        // 校验名字的唯一性
        validateAssetAreaNameUnique(updateReqVO.getId(), updateReqVO.getParentId(), updateReqVO.getName());

        // 更新
        AssetAreaDO updateObj = BeanUtils.toBean(updateReqVO, AssetAreaDO.class);
        assetAreaMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetArea(Long id) {
        // 校验存在
        validateAssetAreaExists(id);
        // 校验是否有子资产关联行政区划
        if (assetAreaMapper.selectCountByParentId(id) > 0) {
            throw exception(ASSET_AREA_EXITS_CHILDREN);
        }
        // 删除
        assetAreaMapper.deleteById(id);
    }

    private void validateAssetAreaExists(Long id) {
        if (assetAreaMapper.selectById(id) == null) {
            throw exception(ASSET_AREA_NOT_EXISTS);
        }
    }

    private void validateParentAssetArea(Long id, Long parentId) {
        if (parentId == null || AssetAreaDO.PARENT_ID_ROOT.equals(parentId)) {
            return;
        }
        // 1. 不能设置自己为父资产关联行政区划
        if (Objects.equals(id, parentId)) {
            throw exception(ASSET_AREA_PARENT_ERROR);
        }
        // 2. 父资产关联行政区划不存在
        AssetAreaDO parentAssetArea = assetAreaMapper.selectById(parentId);
        if (parentAssetArea == null) {
            throw exception(ASSET_AREA_PARENT_NOT_EXITS);
        }
        // 3. 递归校验父资产关联行政区划，如果父资产关联行政区划是自己的子资产关联行政区划，则报错，避免形成环路
        if (id == null) { // id 为空，说明新增，不需要考虑环路
            return;
        }
        for (int i = 0; i < Short.MAX_VALUE; i++) {
            // 3.1 校验环路
            parentId = parentAssetArea.getParentId();
            if (Objects.equals(id, parentId)) {
                throw exception(ASSET_AREA_PARENT_IS_CHILD);
            }
            // 3.2 继续递归下一级父资产关联行政区划
            if (parentId == null || AssetAreaDO.PARENT_ID_ROOT.equals(parentId)) {
                break;
            }
            parentAssetArea = assetAreaMapper.selectById(parentId);
            if (parentAssetArea == null) {
                break;
            }
        }
    }

    private void validateAssetAreaNameUnique(Long id, Long parentId, String name) {
        AssetAreaDO assetArea = assetAreaMapper.selectByParentIdAndName(parentId, name);
        if (assetArea == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的资产关联行政区划
        if (id == null) {
            throw exception(ASSET_AREA_NAME_DUPLICATE);
        }
        if (!Objects.equals(assetArea.getId(), id)) {
            throw exception(ASSET_AREA_NAME_DUPLICATE);
        }
    }

    @Override
    public AssetAreaDO getAssetArea(Long id) {
        return assetAreaMapper.selectById(id);
    }

    @Override
    public List<AssetAreaDO> getAssetAreaList(AssetAreaListReqVO listReqVO) {
        return assetAreaMapper.selectList(listReqVO);
    }

}