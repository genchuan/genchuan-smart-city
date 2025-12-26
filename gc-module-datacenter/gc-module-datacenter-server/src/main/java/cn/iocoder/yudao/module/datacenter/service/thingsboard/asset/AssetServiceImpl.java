package cn.iocoder.yudao.module.datacenter.service.thingsboard.asset;

import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.mysql.thingsboard.asset.AssetMapper;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.asset.Dao.AssetTbDao;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.thingsboard.asset.AssetDO;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.asset.util.AssetBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import org.thingsboard.server.common.data.asset.Asset;
import org.thingsboard.server.common.data.asset.AssetInfo;
import org.thingsboard.server.common.data.asset.AssetProfile;
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

    private static final Logger log = LoggerFactory.getLogger(AssetServiceImpl.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Long createAsset(AssetSaveReqVO createReqVO) {
//        // 插入
//        AssetDO assetInfo = BeanUtils.toBean(createReqVO, AssetDO.class);
//        assetMapper.insert(assetInfo);
//        // 返回
//        return assetInfo.getId();
        try {
            // 1. 先同步到ThingsBoard
            Asset assetToCreate = AssetBuilder.buildAsset(
                    createReqVO.getAssetName(),
                    createReqVO.getAssetProfileId(),
                    createReqVO.getAssetLabel(),
                    createReqVO.getCustomerId(),
                    extractDescription(createReqVO.getAdditionalInfo())
            );

            Asset createdAsset = assetTbDao.createAsset(assetToCreate);

            // 2. 再保存到本地数据库
            AssetDO assetInfo = BeanUtils.toBean(createReqVO, AssetDO.class);
            // 设置从ThingsBoard返回的资产ID
            if (createdAsset != null && createdAsset.getId() != null) {
                assetInfo.setAssetId(createdAsset.getId().getId().toString());
            }

            assetMapper.insert(assetInfo);

            log.info("资产创建成功，本地ID: {}, ThingsBoard ID: {}",
                    assetInfo.getId(), assetInfo.getAssetId());

            return assetInfo.getId();

        } catch (Exception e) {
            log.error("创建资产失败", e);
            throw new RuntimeException("创建资产失败: " + e.getMessage());
        }
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
//        // 校验存在
//        validateAssetExists(id);
//        // 删除
//        assetMapper.deleteById(id);
        try {
            // 1. 先校验存在并获取资产信息
            AssetDO assetDO = validateAssetExists(id);

            // 2. 从ThingsBoard删除
            if (assetDO.getAssetId() != null) {
                assetTbDao.deleteAsset(assetDO.getAssetId());
            }

            // 3. 从本地数据库删除
            assetMapper.deleteById(id);

            log.info("资产删除成功，本地ID: {}, ThingsBoard ID: {}", id, assetDO.getAssetId());

        } catch (Exception e) {
            log.error("删除资产失败", e);
            throw new RuntimeException("删除资产失败: " + e.getMessage());
        }
    }


    private AssetDO validateAssetExists(Long id) {
//        if (assetMapper.selectById(id) == null) {
//            throw exception(ASSET_NOT_EXISTS);
//        }
        AssetDO assetDO = assetMapper.selectById(id);
        if (assetDO == null) {
            throw exception(ASSET_NOT_EXISTS);
        }
        return assetDO;
    }

    // 辅助方法：从additionalInfo中提取description
    private String extractDescription(String additionalInfo) {
        if (additionalInfo == null || additionalInfo.isEmpty()) {
            return "";
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(additionalInfo);
            if (node.has("description")) {
                return node.get("description").asText();
            }
        } catch (Exception e) {
            log.warn("解析附加信息失败", e);
        }
        return "";
    }

    @Override
    public AssetDO getAsset(Long id) {
//        return assetTbDao.getAssetInfoById(id);
        return assetMapper.selectById(id);
    }

    @Override
    public PageResult<AssetDO> getAssetPage(AssetPageReqVO pageReqVO) {
        return assetMapper.selectPage(pageReqVO);
    }


    /**
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

    /**
     * 同步ThingsBoard资产到本地数据库
     *
     * @return 同步结果信息
     */
    @Override
    public Map<String, Object> syncAssetsFromThingsBoard() {
        try {
            log.info("开始自动同步ThingsBoard资产数据");
            int pageSize = 50;
            int currentPage = 0;
            int totalSynced = 0;

            while (true) {
                TimePageLink pageLink = new TimePageLink(pageSize, currentPage);
                PageData<Asset> assetPageData = assetTbDao.getAllAssets(pageLink);

                if (assetPageData == null || assetPageData.getData() == null || assetPageData.getData().isEmpty()) {
                    break;
                }

                // 批量处理当前页的资产（遇到错误立即抛出异常，停止同步）
                for (Asset asset : assetPageData.getData()) {
                    syncSingleAsset(asset); // 移除了内部 try-catch，异常会直接抛出
                    totalSynced++;
                }

                log.info("已同步第{}页资产数据，共{}条", currentPage + 1, assetPageData.getData().size());

                if (assetPageData.getData().size() < pageSize) {
                    break;
                }
                currentPage++;
            }

            log.info("资产同步完成，共处理{}条数据", totalSynced);
            return Map.of("success", true, "totalSynced", totalSynced);

        } catch (Exception e) {
            log.error("自动同步资产数据失败，已停止同步", e);
            return Map.of("success", false, "error", e.getMessage());
        }
    }

    @Override
    public PageData<AssetProfile> getAssetProfiles(Integer pageSize, Integer page, String sortProperty, String sortOrder) {
        return assetTbDao.getAssetProfiles(pageSize, page, sortProperty, sortOrder);
    }

    @Override
    public void addAssetAttributes(String assetId, Map<String, Object> attributes) {
        try {
            // 1. 先添加到ThingsBoard
            assetTbDao.addAssetAttributes(assetId, attributes);

            // 2. 更新本地数据库中的属性信息
            updateLocalAssetAttributes(assetId);

            log.info("资产属性添加成功，资产ID: {}", assetId);

        } catch (Exception e) {
            log.error("添加资产属性失败", e);
            throw new RuntimeException("添加资产属性失败: " + e.getMessage());
        }
    }

    @Override
    public void deleteAssetAttributes(String assetId, String scope, List<String> keys) {
        try {
            // 1. 先从ThingsBoard删除
            assetTbDao.deleteAssetAttributes(assetId, scope, keys);

            // 2. 更新本地数据库中的属性信息
            updateLocalAssetAttributes(assetId);

            log.info("资产属性删除成功，资产ID: {}, 删除的属性: {}", assetId, keys);

        } catch (Exception e) {
            log.error("删除资产属性失败", e);
            throw new RuntimeException("删除资产属性失败: " + e.getMessage());
        }
    }

    /**
     * 同步单个资产（移除 try-catch，让异常抛出）
     */
    private void syncSingleAsset(Asset asset) {
        String assetId = asset.getId().getId().toString();
        // 移除 try-catch，任何异常都会直接抛出到上层方法
        AssetDO existingAsset = assetMapper.selectByAssetId(assetId);
        String attributesJson = getAssetAttributesJson(assetId);
        String devicesJson = getAssetDevicesJson(assetId);
        AssetDO assetDO = buildAssetDO(asset, attributesJson, devicesJson);

        if (existingAsset != null) {
            assetDO.setId(existingAsset.getId());
            assetDO.setCreateTime(existingAsset.getCreateTime());
            if (isAssetChanged(existingAsset, assetDO)) {
                assetMapper.updateById(assetDO);
                log.debug("更新资产: {}", asset.getName());
            }
        } else {
            assetMapper.insert(assetDO);
            log.debug("新增资产: {}", asset.getName());
        }
    }

    /**
     * 判断资产数据是否发生变化
     */
    private boolean isAssetChanged(AssetDO existing, AssetDO latest) {
        return !Objects.equals(existing.getAssetName(), latest.getAssetName()) ||
                !Objects.equals(existing.getAssetType(), latest.getAssetType()) ||
                !Objects.equals(existing.getVersion(), latest.getVersion()) ||
                !Objects.equals(existing.getAttributes(), latest.getAttributes()) ||
                !Objects.equals(existing.getContextDevices(), latest.getContextDevices());
    }

    /**
     * 获取资产属性JSON字符串
     */
    private String getAssetAttributesJson(String assetId) {
        try {
            List<Map<String, Object>> attributes = assetTbDao.getAssetAttributes(assetId);
            if (attributes != null && !attributes.isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.writeValueAsString(attributes);
            }
        } catch (Exception e) {
            log.warn("获取资产属性失败: {}", assetId, e);
        }
        return null;
    }


    /**
     * 获取资产关联设备JSON字符串
     */
    private String getAssetDevicesJson(String assetId) {
        try {
            List<Map<String, Object>> devices = assetTbDao.getAssetRelatedDevices(assetId);
            if (devices != null && !devices.isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.writeValueAsString(devices);
            }
        } catch (Exception e) {
            log.warn("获取资产关联设备失败: {}", assetId, e);
        }
        return null;
    }

    /**
     * 构建 AssetDO 对象
     */
    private AssetDO buildAssetDO(Asset asset, String attributesJson, String devicesJson) {
        JsonNode additionalInfo = asset.getAdditionalInfo();
        String customerTitle = "";
        Boolean customerIsPublic = null;
        String assetProfileName = ""; // 默认值为空字符串，避免 NULL

        if (additionalInfo != null) {
            if (additionalInfo.has("customerTitle")) {
                customerTitle = additionalInfo.get("customerTitle").asText();
            }
            if (additionalInfo.has("customerIsPublic")) {
                customerIsPublic = additionalInfo.get("customerIsPublic").asBoolean();
            }
            if (additionalInfo.has("assetProfileName")) {
                assetProfileName = additionalInfo.get("assetProfileName").asText();
            }
        }

        return AssetDO.builder()
                .assetId(asset.getId().getId().toString())
                .entityType(asset.getId().getEntityType().name())
                .createdTime(asset.getCreatedTime())
                .tenantEntityType(asset.getTenantId() != null ? asset.getTenantId().getEntityType().name() : null)
                .customerId(asset.getCustomerId() != null ? asset.getCustomerId().getId().toString() : null)
                .customerEntityType(asset.getCustomerId() != null ? asset.getCustomerId().getEntityType().name() : null)
                .assetProfileId(asset.getAssetProfileId() != null ? asset.getAssetProfileId().getId().toString() : null)
                .assetProfileEntityType(asset.getAssetProfileId() != null ? asset.getAssetProfileId().getEntityType().name() : null)
                .assetName(asset.getName())
                .assetType(asset.getType())
                .assetLabel(asset.getLabel())
                .externalId(asset.getExternalId() != null ? asset.getExternalId().getId().toString() : null)
                .version(asset.getVersion() != null ? asset.getVersion().intValue() : null)
                .customerTitle(customerTitle) // 确保不为 null
                .customerIsPublic(customerIsPublic)
                .assetProfileName(assetProfileName) // 确保不为 null
                .additionalInfo(convertAdditionalInfoToJson(additionalInfo))
                .attributes(attributesJson)
                .contextDevices(devicesJson)
                .tenantIdSys(getCurrentTenantId())
                .extCommon1(null)  // 或从 additionalInfo 提取，但根据需求可能留空
                .extCommon2(null)
                .extCommon3(null)
                .extCommon4(null)
                .build();
    }

    /**
     * 转换附加信息为JSON字符串
     */
    private String convertAdditionalInfoToJson(JsonNode additionalInfo) {
        if (additionalInfo == null || additionalInfo.isNull()) {
            return null;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(additionalInfo);
        } catch (Exception e) {
            log.warn("转换附加信息失败", e);
            return null;
        }
    }

    /**
     * 获取当前租户ID
     */
    private Long getCurrentTenantId() {
        // 根据您的权限系统实现获取当前租户ID
        // 例如：return SecurityFrameworkUtils.getLoginUser().getTenantId();
        return 1L; // 临时返回默认值
    }

    /**
     * 更新本地数据库中的资产属性信息
     */
    private void updateLocalAssetAttributes(String assetId) {
        try {
            // 根据assetId查找本地资产记录
            AssetDO assetDO = assetMapper.selectByAssetId(assetId);
            if (assetDO == null) {
                log.warn("本地数据库中没有找到对应的资产记录，assetId: {}", assetId);
                return;
            }

            // 重新获取最新的属性信息
            String attributesJson = getAssetAttributesJson(assetId);

            // 更新属性信息
            AssetDO updateObj = new AssetDO();
            updateObj.setId(assetDO.getId());
            updateObj.setAttributes(attributesJson);

            assetMapper.updateById(updateObj);

            log.debug("本地资产属性更新成功，资产ID: {}", assetId);

        } catch (Exception e) {
            log.error("更新本地资产属性失败", e);
            // 这里不抛出异常，因为ThingsBoard操作已经成功，本地更新失败可以记录日志但不要影响主流程
        }
    }


}