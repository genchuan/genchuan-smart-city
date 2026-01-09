package cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.config;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.config.vo.SceneConfigQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.config.vo.SceneConfigRespVO;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.config.vo.SceneConfigSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.field.SceneFieldDO;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.scenemapconfig.SceneMapConfigDO;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.scenestatusicon.SceneStatusIconDO;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.selectedfieldstatusmap.SelectedFieldStatusMapDO;
import cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.scene.config.SceneConfigMapper;
import cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.scene.field.SceneFieldMapper;
import cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.scene.scenemapconfig.SceneMapConfigMapper;
import cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.scene.scenestatusicon.SceneStatusIconMapper;
import cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.scene.selectedfieldstatusmap.SelectedFieldStatusMapMapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 获取场景配置接口 Service 实现类
 * <p>
 * 功能说明：
 * 1. 实现 SceneConfigService 接口中的业务逻辑方法
 * 2. 调用对应的 Mapper 进行数据库查询
 * 3. 提供统一的 Service 层接口给 Controller 使用
 */
@Service
public class SceneConfigServiceImpl implements SceneConfigService {

    @Resource
    private SceneFieldMapper sceneFieldMapper;
    @Resource
    private SelectedFieldStatusMapMapper selectedFieldStatusMapMapper;
    @Resource
    private SceneMapConfigMapper sceneMapConfigMapper;
    @Resource
    private SceneStatusIconMapper sceneStatusIconMapper;

    /**
     * 查询获取场景配置接口数据
     *
     * @param reqVO 查询条件 VO 对象
     * @return SceneConfigRespVO
     */
    @Override
    public SceneConfigRespVO getSceneConfig(SceneConfigQueryReqVO reqVO) {

        // 对外参数 sceneKey，对内统一叫 sceneCode
        String sceneCode = reqVO.getSceneKey();

        SceneConfigRespVO respVO = new SceneConfigRespVO();

        // 1. 查询场景字段配置（biz_scene_field）
        List<SceneFieldDO> fieldList =
                sceneFieldMapper.selectBySceneCode(sceneCode);

        // 1.1 已选择字段 key 列表
        respVO.setSelectedFields(
                fieldList.stream()
                        .map(SceneFieldDO::getFieldCode)
                        .toList()
        );

        // 1.2 字段渲染配置（含 statusMap）
        respVO.setSelectedFieldsConfig(
                buildSelectedFieldConfig(sceneCode, fieldList)
        );

        // 2. 查询场景地图整体配置（biz_scene_map_config）
        SceneMapConfigDO mapConfig =
                sceneMapConfigMapper.selectBySceneCode(sceneCode);

        if (mapConfig != null) {
            respVO.setDefaultIconSize(buildDefaultIconSize(mapConfig));
            respVO.setDefaultIconUrl(mapConfig.getDefaultIconUrl());
            respVO.setMapCenter(buildMapCenter(mapConfig));
            respVO.setOrbitAnimation(buildOrbitAnimation(mapConfig));
            respVO.setLegendConfig(buildLegendConfig(sceneCode, mapConfig));
        }

        // 3. 状态字段图标配置（biz_scene_status_icon）
        respVO.setStatusIconConfigList(
                buildStatusIconConfig(sceneCode)
        );

        return respVO;
    }

    /**
     * 构建字段渲染配置
     */
    private List<SceneConfigRespVO.SelectedFieldConfigVO> buildSelectedFieldConfig(
            String sceneCode,
            List<SceneFieldDO> fieldList) {

        // 查询 status 映射
        Map<String, Map<String, String>> statusMap =
                selectedFieldStatusMapMapper.selectBySceneCode(sceneCode)
                        .stream()
                        .collect(Collectors.groupingBy(
                                SelectedFieldStatusMapDO::getFieldCode,
                                Collectors.toMap(
                                        SelectedFieldStatusMapDO::getRawValue,
                                        SelectedFieldStatusMapDO::getDisplayText
                                )
                        ));

        return fieldList.stream().map(field -> {
            SceneConfigRespVO.SelectedFieldConfigVO vo =
                    new SceneConfigRespVO.SelectedFieldConfigVO();
            vo.setKey(field.getFieldCode());
            vo.setLabel(field.getLabel());
            vo.setRenderType(field.getType());

            // 仅 status 类型字段才设置 statusMap
            if ("status".equals(field.getType())) {
                vo.setStatusMap(statusMap.get(field.getFieldCode()));
            }
            return vo;
        }).toList();
    }

    /**
     * 构建默认图标尺寸
     */
    private SceneConfigRespVO.DefaultIconSizeVO buildDefaultIconSize(
            SceneMapConfigDO mapConfig) {

        SceneConfigRespVO.DefaultIconSizeVO vo =
                new SceneConfigRespVO.DefaultIconSizeVO();
        vo.setWidth(mapConfig.getDefaultIconWidth());
        vo.setHeight(mapConfig.getDefaultIconHeight());
        return vo;
    }

    /**
     * 构建地图中心
     */
    private SceneConfigRespVO.MapCenterVO buildMapCenter(
            SceneMapConfigDO mapConfig) {

        SceneConfigRespVO.MapCenterVO vo =
                new SceneConfigRespVO.MapCenterVO();
        vo.setLat(mapConfig.getCenterLat());
        vo.setLng(mapConfig.getCenterLng());
        return vo;
    }

    /**
     * 构建环绕动画配置
     */
    private SceneConfigRespVO.OrbitAnimationVO buildOrbitAnimation(
            SceneMapConfigDO mapConfig) {

        SceneConfigRespVO.OrbitAnimationVO vo =
                new SceneConfigRespVO.OrbitAnimationVO();
        vo.setRotateSpeed(mapConfig.getRotateSpeed());
        vo.setLoop(mapConfig.getLoopType());
        vo.setPitch(mapConfig.getPitch());
        vo.setZoom(mapConfig.getZoom());
        vo.setCenterType(mapConfig.getCenterType());

        // 自定义中心点
        if ("custom".equals(mapConfig.getCenterType())) {
            SceneConfigRespVO.MapCenterVO customCenter =
                    new SceneConfigRespVO.MapCenterVO();
            customCenter.setLat(mapConfig.getCustomCenterLat());
            customCenter.setLng(mapConfig.getCustomCenterLng());
            vo.setCustomCenter(customCenter);
        }
        return vo;
    }

    /**
     * 构建状态字段图标配置
     */
    private List<SceneConfigRespVO.StatusIconConfigVO> buildStatusIconConfig(
            String sceneCode) {

        return sceneStatusIconMapper.selectBySceneCode(sceneCode)
                .stream()
                .map(icon -> {
                    SceneConfigRespVO.StatusIconConfigVO vo =
                            new SceneConfigRespVO.StatusIconConfigVO();
                    vo.setFieldKey(icon.getFieldCode());
                    vo.setRawValue(icon.getRawValue());
                    vo.setLegend_text(icon.getLegendText());
                    vo.setIcon(icon.getIconUrl());
                    return vo;
                }).toList();
    }

    /**
     * 构建图例配置
     */
    private SceneConfigRespVO.LegendConfigVO buildLegendConfig(
            String sceneCode,
            SceneMapConfigDO mapConfig) {

        SceneConfigRespVO.LegendConfigVO vo =
                new SceneConfigRespVO.LegendConfigVO();
        Boolean showFlag;
        if (mapConfig.getShowFlag()==1){
            showFlag=true;
        }else {
            showFlag=false;
        }
        vo.setShow(showFlag);
        vo.setPosition(mapConfig.getPosition());

        vo.setItems(
                sceneStatusIconMapper.selectBySceneCode(sceneCode)
                        .stream()
                        .map(icon -> {
                            SceneConfigRespVO.LegendItemVO item =
                                    new SceneConfigRespVO.LegendItemVO();
                            item.setFieldKey(icon.getFieldCode());
                            item.setRawValue(icon.getRawValue());
                            item.setText(icon.getLegendText());
                            item.setIcon(icon.getIconUrl());
                            return item;
                        }).toList()
        );

        return vo;
    }




    // ================== 保存接口实现 ==================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSceneConfig(SceneConfigSaveReqVO reqVO) {

        String sceneCode = reqVO.getSceneKey();

        // 1. 清空旧配置（覆盖式）
        sceneFieldMapper.deleteBySceneCode(sceneCode);
        selectedFieldStatusMapMapper.deleteBySceneCode(sceneCode);
        sceneStatusIconMapper.deleteBySceneCode(sceneCode);
        sceneMapConfigMapper.deleteBySceneCode(sceneCode);

        // 2. 保存场景字段配置
        saveSceneFields(sceneCode, reqVO);

        // 3. 保存状态字段映射
        saveStatusMap(sceneCode, reqVO);

        // 4. 保存状态字段图标
        saveStatusIcons(sceneCode, reqVO);

        // 5. 保存地图整体配置
        saveMapConfig(sceneCode, reqVO);
    }

    /**
     * 保存场景字段（biz_scene_field）
     */
    private void saveSceneFields(String sceneCode, SceneConfigSaveReqVO reqVO) {

        reqVO.getSelectedFieldsConfig().forEach(fieldVO -> {
            SceneFieldDO field = new SceneFieldDO();
            field.setSceneCode(sceneCode);
            field.setFieldCode(fieldVO.getKey());
            field.setLabel(fieldVO.getLabel());
            field.setType(fieldVO.getRenderType());
            field.setIsSelected(1);
            sceneFieldMapper.insert(field);
        });
    }

    /**
     * 保存状态字段映射（biz_selected_field_status_map）
     */
    private void saveStatusMap(String sceneCode, SceneConfigSaveReqVO reqVO) {

        reqVO.getSelectedFieldsConfig().forEach(fieldVO -> {
            if (!"status".equals(fieldVO.getRenderType())) {
                return;
            }
            if (fieldVO.getStatusMap() == null) {
                return;
            }

            fieldVO.getStatusMap().forEach((rawValue, displayText) -> {
                SelectedFieldStatusMapDO statusMapDO =
                        new SelectedFieldStatusMapDO();
                statusMapDO.setSceneCode(sceneCode);
                statusMapDO.setFieldCode(fieldVO.getKey());
                statusMapDO.setRawValue(rawValue);
                statusMapDO.setDisplayText(displayText);
                selectedFieldStatusMapMapper.insert(statusMapDO);
            });
        });
    }

    //====================================保存=====================================================================
    /**
     * 保存状态字段图标配置（biz_scene_status_icon）
     */
    private void saveStatusIcons(String sceneCode, SceneConfigSaveReqVO reqVO) {

        reqVO.getStatusIconConfigList().forEach(iconVO -> {
            SceneStatusIconDO iconDO = new SceneStatusIconDO();
            iconDO.setSceneCode(sceneCode);
            iconDO.setFieldCode(iconVO.getFieldKey());
            iconDO.setRawValue(iconVO.getRawValue());
            iconDO.setLegendText(
                    StringUtils.isBlank(iconVO.getLegend_text()) ? null : iconVO.getLegend_text()
            );
            iconDO.setIconUrl(iconVO.getIcon());
            sceneStatusIconMapper.insert(iconDO);
        });
    }

    /**
     * 保存地图整体配置（biz_scene_map_config）
     */
    private void saveMapConfig(String sceneCode, SceneConfigSaveReqVO reqVO) {

        SceneMapConfigDO mapConfig = new SceneMapConfigDO();
        mapConfig.setSceneCode(sceneCode);

        // 默认图标
        if (reqVO.getDefaultIconSize() != null) {
            mapConfig.setDefaultIconWidth(reqVO.getDefaultIconSize().getWidth());
            mapConfig.setDefaultIconHeight(reqVO.getDefaultIconSize().getHeight());
        }
        mapConfig.setDefaultIconUrl(reqVO.getDefaultIconUrl());

        // 图例
        if (reqVO.getLegendConfig() != null) {
            mapConfig.setShowFlag(
                    Boolean.TRUE.equals(reqVO.getLegendConfig().getShow()) ? 1 : 0
            );
            mapConfig.setPosition(reqVO.getLegendConfig().getPosition());
        }

        // 地图中心
        if (reqVO.getMapCenter() != null) {
            mapConfig.setCenterLat(reqVO.getMapCenter().getLat());
            mapConfig.setCenterLng(reqVO.getMapCenter().getLng());
        }

        // 环绕动画
        if (reqVO.getOrbitAnimation() != null) {
            mapConfig.setRotateSpeed(reqVO.getOrbitAnimation().getRotateSpeed());
            mapConfig.setLoopType(reqVO.getOrbitAnimation().getLoop());
            mapConfig.setPitch(reqVO.getOrbitAnimation().getPitch());
            mapConfig.setZoom(reqVO.getOrbitAnimation().getZoom());
            mapConfig.setCenterType(reqVO.getOrbitAnimation().getCenterType());

            if (reqVO.getOrbitAnimation().getCustomCenter() != null) {
                mapConfig.setCustomCenterLat(
                        reqVO.getOrbitAnimation().getCustomCenter().getLat()
                );
                mapConfig.setCustomCenterLng(
                        reqVO.getOrbitAnimation().getCustomCenter().getLng()
                );
            }
        }

        sceneMapConfigMapper.insert(mapConfig);
    }


    //=========================清除============================================================
    @Override
    public void clearSceneConfig(String sceneKey) {
        // 删除场景字段配置
        sceneFieldMapper.deleteBySceneCode(sceneKey);

        // 删除状态映射
        selectedFieldStatusMapMapper.deleteBySceneCode(sceneKey);

        // 删除状态图标
        sceneStatusIconMapper.deleteBySceneCode(sceneKey);

        // 删除地图配置
        sceneMapConfigMapper.deleteBySceneCode(sceneKey);
    }


}
