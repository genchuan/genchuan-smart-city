package cn.iocoder.yudao.module.datacenter.service.thingsboard.asset;

import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.AssetSimpleRespVO;
import cn.iocoder.yudao.module.datacenter.dal.mysql.thingsboard.asset.AssetMapper;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.asset.Dao.AssetTbDao;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.thingsboard.asset.AssetDO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.AssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.AssetSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import org.thingsboard.server.common.data.asset.Asset;
import org.thingsboard.server.common.data.asset.AssetInfo;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.ASSET_NOT_EXISTS;

/**
 * 资产 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class AssetServiceImpl implements AssetService {

    @Resource
    private AssetMapper assetMapper;

    @Resource
    private AssetTbDao assetTbDao;

    @Override
    public String createAsset(AssetSaveReqVO createReqVO) {
        // 插入
        AssetDO asset = BeanUtils.toBean(createReqVO, AssetDO.class);
        assetMapper.insert(asset);

        // 返回
        return asset.getId();
    }

    @Override
    public void updateAsset(AssetSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetExists(updateReqVO.getId());
        // 更新
        AssetDO updateObj = BeanUtils.toBean(updateReqVO, AssetDO.class);
        assetMapper.updateById(updateObj);
    }

    @Override
    public void deleteAsset(String id) {
        // 校验存在
        validateAssetExists(id);
        // 删除
        assetMapper.deleteById(id);
    }

    @Override
    public void deleteAssetListByIds(List<String> ids) {
        // 删除
        assetMapper.deleteByIds(ids);
    }

    private void validateAssetExists(String id) {
        if (assetMapper.selectById(id) == null) {
            throw exception(ASSET_NOT_EXISTS);
        }
    }

    @Override
    public AssetInfo getAsset(String id) {
        return assetTbDao.getAssetInfoById(id);
    }

    @Override
    public PageResult<Asset> getAssetPage(AssetPageReqVO pageReqVO) {
        return assetTbDao.getAssetPage(pageReqVO);
    }

    /**
     *
     * @return 资产ID及名称
     */
    @Override
    public List<AssetSimpleRespVO> getAssetList() {
        // 从 ThingsBoard 获取资产列表
        PageResult<Asset> assetPageResult = assetTbDao.getAssetPage(new AssetPageReqVO());

        if (assetPageResult == null || assetPageResult.getList() == null) {
            return Collections.emptyList();
        }

        // 转换为简单响应VO
        return assetPageResult.getList().stream()
                .map(asset -> {
                    AssetSimpleRespVO vo = new AssetSimpleRespVO();
                    vo.setId(asset.getId().toString());
                    vo.setName(asset.getName());
                    return vo;
                })
                .collect(Collectors.toList());
    }

}