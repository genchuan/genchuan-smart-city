package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetOperationManagement.assetappscene;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetappscene.vo.AssetAppScenePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetappscene.vo.AssetAppSceneSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetappscene.AssetAppSceneDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetOperationManagement.assetappscene.AssetAppSceneMapper;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 资产关联应用场景 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetAppSceneServiceImpl implements AssetAppSceneService {

    @Resource
    private AssetAppSceneMapper assetAppSceneMapper;

    @Override
    public Long createAssetAppScene(AssetAppSceneSaveReqVO createReqVO) {
        // 插入
        AssetAppSceneDO assetAppScene = BeanUtils.toBean(createReqVO, AssetAppSceneDO.class);
        assetAppSceneMapper.insert(assetAppScene);
        // 返回
        return assetAppScene.getId();
    }

    @Override
    public void updateAssetAppScene(AssetAppSceneSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetAppSceneExists(updateReqVO.getId());
        // 更新
        AssetAppSceneDO updateObj = BeanUtils.toBean(updateReqVO, AssetAppSceneDO.class);
        assetAppSceneMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetAppScene(Long id) {
        // 校验存在
        validateAssetAppSceneExists(id);
        // 删除
        assetAppSceneMapper.deleteById(id);
    }

    private void validateAssetAppSceneExists(Long id) {
        if (assetAppSceneMapper.selectById(id) == null) {
            throw exception(ASSET_APP_SCENE_NOT_EXISTS);
        }
    }

    @Override
    public AssetAppSceneDO getAssetAppScene(Long id) {
        return assetAppSceneMapper.selectById(id);
    }

    @Override
    public PageResult<AssetAppSceneDO> getAssetAppScenePage(AssetAppScenePageReqVO pageReqVO) {
        return assetAppSceneMapper.selectPage(pageReqVO);
    }

    /**
     * 批量删除资产关联应用场景
     *
     * @param ids 编号列表
     */
    @Override
    public void deleteAssetAppSceneBatch(List<Long> ids) {
        // 校验存在
        for (Long id : ids) {
            validateAssetAppSceneExists(id);
        }
        // 批量删除
        assetAppSceneMapper.deleteByIds(ids);
    }

}