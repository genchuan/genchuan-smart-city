package cn.iocoder.yudao.module.datacenter.service.thingsboard.asset.util;

import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.AssetSaveReqVO;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.asset.AssetServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thingsboard.server.common.data.asset.Asset;
import org.thingsboard.server.common.data.id.AssetId;
import org.thingsboard.server.common.data.id.AssetProfileId;
import org.thingsboard.server.common.data.id.CustomerId;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

public class AssetBuilder {
    private static final Logger log = LoggerFactory.getLogger(AssetServiceImpl.class);


    public static Asset buildAssetFromReqVO(AssetSaveReqVO reqVO) {

        Asset asset = new Asset();

        // 设置资产名称 - 这是ThingsBoard必填字段
        if (reqVO.getAssetName() != null && !reqVO.getAssetName().isEmpty()) {
            asset.setName(reqVO.getAssetName());
        } else {
            throw new IllegalArgumentException("资产名称不能为空");
        }

        // 设置资产配置ID
        if (reqVO.getAssetProfileId() != null && !reqVO.getAssetProfileId().isEmpty()) {
            try {
                AssetProfileId assetProfileIdObj = new AssetProfileId(UUID.fromString(reqVO.getAssetProfileId()));
                asset.setAssetProfileId(assetProfileIdObj);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("无效的资产档案ID格式: " + reqVO.getAssetProfileId(), e);
            }
        } else {
            throw new IllegalArgumentException("资产档案ID不能为空");
        }

        // 设置标签 - 使用label字段
        if (reqVO.getLabel() != null && !reqVO.getLabel().isEmpty()) {
            asset.setLabel(reqVO.getLabel());
        }

        // 设置客户ID
        if (reqVO.getCustomerId() != null && !reqVO.getCustomerId().isEmpty()) {
            try {
                UUID customerUuid = UUID.fromString(reqVO.getCustomerId());
                CustomerId customerIdObj = new CustomerId(customerUuid);
                asset.setCustomerId(customerIdObj);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("无效的客户ID格式: " + reqVO.getCustomerId(), e);
            }
        }

        // 设置附加信息
        if (reqVO.getAdditionalInfo() != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                String additionalInfoJson = mapper.writeValueAsString(reqVO.getAdditionalInfo());
                JsonNode additionalInfo = mapper.readTree(additionalInfoJson);
                asset.setAdditionalInfo(additionalInfo);
            } catch (Exception e) {
                log.warn("设置附加信息失败", e);
            }
        }

        // 设置资产类型（如果有）
        if (reqVO.getType() != null && !reqVO.getType().isEmpty()) {
            asset.setType(reqVO.getType());
        }

        return asset;
    }

    // 在 AssetBuilder 中添加新方法
    public static Asset buildAssetForUpdate(AssetSaveReqVO reqVO, String assetId) {
        Asset asset = buildAssetFromReqVO(reqVO);

        if (assetId != null && !assetId.isEmpty()) {
            try {
                AssetId assetIdObj = new AssetId(UUID.fromString(assetId));
                asset.setId(assetIdObj);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("无效的资产ID格式: " + assetId, e);
            }
        }

        return asset;
    }
}