package cn.iocoder.yudao.module.datacenter.service.thingsboard.assetprofile;

import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.assetprofile.vo.AssetProfilePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.assetprofile.vo.AssetProfileSaveReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.thingsboard.assetprofile.AssetProfileDO;
import cn.iocoder.yudao.module.datacenter.dal.mysql.thingsboard.assetprofile.AssetProfileMapper;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.asset.AssetServiceImpl;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.assetprofile.Dao.AssetProfileTbDao;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.assetprofile.util.AssetProfileBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import org.thingsboard.server.common.data.asset.AssetProfile;
import org.thingsboard.server.common.data.page.PageData;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 资产配置信息 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class AssetProfileServiceImpl implements AssetProfileService {

    @Resource
    private AssetProfileMapper assetProfileMapper;

    @Resource
    private AssetProfileTbDao assetProfileTbDao;

    private static final Logger log = LoggerFactory.getLogger(AssetServiceImpl.class);

    @Override
    public Long createAssetProfile(AssetProfileSaveReqVO createReqVO) {
//        // 插入
//        AssetProfileDO assetProfile = BeanUtils.toBean(createReqVO, AssetProfileDO.class);
//        assetProfileMapper.insert(assetProfile);
//        // 返回
//        return assetProfile.getId();
        try {
            // 1. 先同步到ThingsBoard
            AssetProfile assetProfileToCreate = AssetProfileBuilder.buildAssetProfile(
                    createReqVO.getProfileName(),
                    createReqVO.getProfileDescription()
            );

            AssetProfile createdAssetProfile = assetProfileTbDao.createAssetProfile(assetProfileToCreate);

            // 2. 再保存到本地数据库
            AssetProfileDO assetProfile = BeanUtils.toBean(createReqVO, AssetProfileDO.class);
            // 设置从ThingsBoard返回的资产配置ID
            if (createdAssetProfile != null && createdAssetProfile.getId() != null) {
                assetProfile.setProfileId(createdAssetProfile.getId().getId().toString());
            }

            assetProfileMapper.insert(assetProfile);

            log.info("资产配置创建成功，本地ID: {}, ThingsBoard ID: {}",
                    assetProfile.getId(), assetProfile.getProfileId());

            return assetProfile.getId();

        } catch (Exception e) {
            log.error("创建资产配置失败", e);
            throw new RuntimeException("创建资产配置失败: " + e.getMessage());
        }
    }

    @Override
    public void updateAssetProfile(AssetProfileSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetProfileExists(updateReqVO.getId());
        // 更新
        AssetProfileDO updateObj = BeanUtils.toBean(updateReqVO, AssetProfileDO.class);
        assetProfileMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetProfile(Long id) {
//        // 校验存在
//        validateAssetProfileExists(id);
//        // 删除
//        assetProfileMapper.deleteById(id);
        try {
            // 1. 先校验存在并获取资产配置信息
            AssetProfileDO assetProfileDO = validateAssetProfileExists(id);

            // 2. 从ThingsBoard删除
            if (assetProfileDO.getProfileId() != null) {
                assetProfileTbDao.deleteAssetProfile(assetProfileDO.getProfileId());
            }

            // 3. 从本地数据库删除
            assetProfileMapper.deleteById(id);

            log.info("资产配置删除成功，本地ID: {}, ThingsBoard ID: {}", id, assetProfileDO.getProfileId());

        } catch (Exception e) {
            log.error("删除资产配置失败", e);
            throw new RuntimeException("删除资产配置失败: " + e.getMessage());
        }
    }

    private AssetProfileDO validateAssetProfileExists(Long id) {
        AssetProfileDO assetProfileDO = assetProfileMapper.selectById(id);
        if (assetProfileDO == null) {
            throw exception(ASSET_PROFILE_NOT_EXISTS);
        }
        return assetProfileDO;
    }

    @Override
    public AssetProfileDO getAssetProfile(Long id) {
        return assetProfileMapper.selectById(id);
    }

    @Override
    public PageResult<AssetProfileDO> getAssetProfilePage(AssetProfilePageReqVO pageReqVO) {
        return assetProfileMapper.selectPage(pageReqVO);
    }

    @Override
    public Map<String, Object> syncAssetProfilesFromThingsBoard() {
        try {
            log.info("开始自动同步ThingsBoard资产配置数据");
            int pageSize = 50;
            int currentPage = 0;
            int totalSynced = 0;

            while (true) {
                PageData<AssetProfile> assetProfilePageData = assetProfileTbDao.getAssetProfiles(pageSize, currentPage, "createdTime", "DESC");

                if (assetProfilePageData == null || assetProfilePageData.getData() == null || assetProfilePageData.getData().isEmpty()) {
                    break;
                }

                // 批量处理当前页的资产配置
                for (AssetProfile assetProfile : assetProfilePageData.getData()) {
                    syncSingleAssetProfile(assetProfile);
                    totalSynced++;
                }

                log.info("已同步第{}页资产配置数据，共{}条", currentPage + 1, assetProfilePageData.getData().size());

                if (assetProfilePageData.getData().size() < pageSize) {
                    break;
                }
                currentPage++;
            }

            log.info("资产配置同步完成，共处理{}条数据", totalSynced);
            return Map.of("success", true, "totalSynced", totalSynced);

        } catch (Exception e) {
            log.error("自动同步资产配置数据失败，已停止同步", e);
            return Map.of("success", false, "error", e.getMessage());
        }
    }

    @Override
    public PageData<AssetProfile> getAssetProfilesFromThingsBoard(Integer pageSize, Integer page, String sortProperty, String sortOrder) {
        return assetProfileTbDao.getAssetProfiles(pageSize, page, sortProperty, sortOrder);
    }

    @Override
    public List<AssetProfileDO> getAssetProfileList() {
        // 使用 LambdaQueryWrapperX 构建查询条件，不设置分页参数
        return assetProfileMapper.selectList(new LambdaQueryWrapperX<AssetProfileDO>()
                .orderByDesc(AssetProfileDO::getId));
    }

    /**
     * 同步单个资产配置
     */
    private void syncSingleAssetProfile(AssetProfile assetProfile) {
        String assetProfileId = assetProfile.getId().getId().toString();

        AssetProfileDO existingAssetProfile = assetProfileMapper.selectByProfileId(assetProfileId);
        AssetProfileDO assetProfileDO = buildAssetProfileDO(assetProfile);

        if (existingAssetProfile != null) {
            assetProfileDO.setId(existingAssetProfile.getId());
            assetProfileDO.setCreateTime(existingAssetProfile.getCreateTime());
            if (isAssetProfileChanged(existingAssetProfile, assetProfileDO)) {
                assetProfileMapper.updateById(assetProfileDO);
                log.debug("更新资产配置: {}", assetProfile.getName());
            }
        } else {
            assetProfileMapper.insert(assetProfileDO);
            log.debug("新增资产配置: {}", assetProfile.getName());
        }
    }

    /**
     * 判断资产配置数据是否发生变化
     */
    private boolean isAssetProfileChanged(AssetProfileDO existing, AssetProfileDO latest) {
        return !Objects.equals(existing.getProfileName(), latest.getProfileName()) ||
                !Objects.equals(existing.getProfileDescription(), latest.getProfileDescription()) ||
                !Objects.equals(existing.getDefaultRuleChainId(), latest.getDefaultRuleChainId()) ||
                !Objects.equals(existing.getVersion(), latest.getVersion()) ||
                !Objects.equals(existing.getIsDefault(), latest.getIsDefault());
    }

    /**
     * 构建 AssetProfileDO 对象
     */
    private AssetProfileDO buildAssetProfileDO(AssetProfile assetProfile) {
        return AssetProfileDO.builder()
                .profileId(assetProfile.getId().getId().toString())
                .entityType(assetProfile.getId().getEntityType().name())
                .createdTime(assetProfile.getCreatedTime())
                .tenantIdTb(assetProfile.getTenantId() != null ? assetProfile.getTenantId().getId().toString() : null)
                .tenantEntityType(assetProfile.getTenantId() != null ? assetProfile.getTenantId().getEntityType().name() : null)
                .profileName(assetProfile.getName())
                .profileDescription(assetProfile.getDescription())
                .profileImage(assetProfile.getImage() != null ? assetProfile.getImage() : null)
                .defaultRuleChainId(assetProfile.getDefaultRuleChainId() != null ? assetProfile.getDefaultRuleChainId().getId().toString() : null)
                .defaultDashboardId(assetProfile.getDefaultDashboardId() != null ? assetProfile.getDefaultDashboardId().getId().toString() : null)
                .defaultQueueName(assetProfile.getDefaultQueueName())
                .defaultEdgeRuleChainId(assetProfile.getDefaultEdgeRuleChainId() != null ? assetProfile.getDefaultEdgeRuleChainId().getId().toString() : null)
                .externalId(assetProfile.getExternalId() != null ? assetProfile.getExternalId().getId().toString() : null)
                .externalEntityType(assetProfile.getExternalId() != null ? assetProfile.getExternalId().getEntityType().name() : null)
                .version(assetProfile.getVersion() != null ? assetProfile.getVersion().intValue() : null)
                .isDefault(assetProfile.isDefault())
                .tenantId(getCurrentTenantId())
                .build();
    }

    /**
     * 获取当前租户ID
     */
    private Long getCurrentTenantId() {
        // 根据您的权限系统实现获取当前租户ID
        return 1L; // 临时返回默认值
    }

}