package cn.iocoder.yudao.module.datacenter.service.thingsboard.asset;

import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.mysql.thingsboard.asset.AssetMapper;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.asset.Dao.AssetTbDao;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.thingsboard.asset.AssetDO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import org.thingsboard.server.common.data.asset.Asset;
import org.thingsboard.server.common.data.asset.AssetInfo;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;
import org.thingsboard.server.common.data.page.TimePageLink;

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
    public Long createAsset(AssetSaveReqVO createReqVO) {
        // 插入
        AssetDO assetInfo = BeanUtils.toBean(createReqVO, AssetDO.class);
        assetMapper.insert(assetInfo);
        // 返回
        return assetInfo.getId();
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
    public void deleteAsset(Long id) {
        // 校验存在
        validateAssetExists(id);
        // 删除
        assetMapper.deleteById(id);
    }


    private void validateAssetExists(Long id) {
        if (assetMapper.selectById(id) == null) {
            throw exception(ASSET_NOT_EXISTS);
        }
    }

    @Override
    public AssetInfo getAsset(String id) {
        return assetTbDao.getAssetInfoById(id);
    }

    @Override
    public PageResult<AssetDO> getAssetPage(AssetPageReqVO  pageReqVO) {
        return assetMapper.selectPage(pageReqVO);
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

    @Override
    public PageResult<AssetDetailRespVO> getAssetPage1(Integer pageSize, Integer page) {
        TimePageLink pageLink = new TimePageLink(pageSize, page);
        PageData<Asset> assetPageData = assetTbDao.getAllAssets(pageLink);

        if (assetPageData == null || assetPageData.getData() == null) {
            return new PageResult<>(Collections.emptyList(), 0L);
        }

        // 转换每个资产为包含属性和设备的详细VO
        List<AssetDetailRespVO> assetDetailList = assetPageData.getData().stream()
                .map(this::convertToAssetDetailVO)
                .collect(Collectors.toList());

        return new PageResult<>(assetDetailList, assetPageData.getTotalElements());
    }

    /**
     * 将Asset对象转换为包含属性和设备的详细VO
     */
    private AssetDetailRespVO convertToAssetDetailVO(Asset asset) {
        AssetDetailRespVO vo = BeanUtils.toBean(asset, AssetDetailRespVO.class);

        // 获取资产属性
        List<Map<String, Object>> attributes = assetTbDao.getAssetAttributes(asset.getId().toString());
        if (attributes != null && !attributes.isEmpty()) {
            List<AttributeVO> attributeVOList = attributes.stream()
                    .map(attr -> {
                        AttributeVO attributeVO = new AttributeVO();
                        attributeVO.setLastUpdateTs((Long) attr.get("lastUpdateTs"));
                        attributeVO.setKey((String) attr.get("key"));
                        attributeVO.setValue(attr.get("value"));
                        return attributeVO;
                    })
                    .collect(Collectors.toList());
            vo.setAttributes(attributeVOList);
        }

        // 获取关联设备
        List<Map<String, Object>> devices = assetTbDao.getAssetRelatedDevices(asset.getId().toString());
        if (devices != null && !devices.isEmpty()) {
            List<ContextDeviceVO> contextDeviceList = devices.stream()
                    .map(device -> {
                        ContextDeviceVO contextDevice = new ContextDeviceVO();
                        contextDevice.setDeviceName((String) device.get("deviceName"));
                        contextDevice.setEntityType((String) device.get("entityType"));
                        contextDevice.setDeviceId((String) device.get("deviceId"));
                        return contextDevice;
                    })
                    .collect(Collectors.toList());
            vo.setContextDevice(contextDeviceList);
        }

        return vo;
    }

}