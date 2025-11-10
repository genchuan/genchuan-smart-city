package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetserverattrcfg;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetserverattrcfg.vo.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetserverattrcfg.AssetServerAttrCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetDataMng.assetserverattrcfg.AssetServerAttrCfgMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 资产服务端属性配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetServerAttrCfgServiceImpl implements AssetServerAttrCfgService {

    @Resource
    private AssetServerAttrCfgMapper assetServerAttrCfgMapper;

    @Override
    public Long createAssetServerAttrCfg(AssetServerAttrCfgSaveReqVO createReqVO) {
        // 插入
        AssetServerAttrCfgDO assetServerAttrCfg = BeanUtils.toBean(createReqVO, AssetServerAttrCfgDO.class);
        assetServerAttrCfgMapper.insert(assetServerAttrCfg);
        // 返回
        return assetServerAttrCfg.getId();
    }

    @Override
    public void updateAssetServerAttrCfg(AssetServerAttrCfgSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetServerAttrCfgExists(updateReqVO.getId());
        // 更新
        AssetServerAttrCfgDO updateObj = BeanUtils.toBean(updateReqVO, AssetServerAttrCfgDO.class);
        assetServerAttrCfgMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetServerAttrCfg(Long id) {
        // 校验存在
        validateAssetServerAttrCfgExists(id);
        // 删除
        assetServerAttrCfgMapper.deleteById(id);
    }

    private void validateAssetServerAttrCfgExists(Long id) {
        if (assetServerAttrCfgMapper.selectById(id) == null) {
            throw exception(ASSET_SERVER_ATTR_CFG_NOT_EXISTS);
        }
    }

    @Override
    public AssetServerAttrCfgDO getAssetServerAttrCfg(Long id) {
        return assetServerAttrCfgMapper.selectById(id);
    }

    @Override
    public PageResult<AssetServerAttrCfgDO> getAssetServerAttrCfgPage(AssetServerAttrCfgPageReqVO pageReqVO) {
        return assetServerAttrCfgMapper.selectPage(pageReqVO);
    }

    /**
     *
     * @return 返回资产服务端属性配置列表数据
     */
    @Override
    public List<AssetServerAttrCfgSimpleRespVO> getAssetServerAttrList() {
        List<AssetServerAttrCfgDO> attrs = assetServerAttrCfgMapper.selectList(
                new LambdaQueryWrapperX<AssetServerAttrCfgDO>()
                        .select(AssetServerAttrCfgDO::getRelAssetId,
                                AssetServerAttrCfgDO::getAttrName,
                                AssetServerAttrCfgDO::getAttrCode,
                                AssetServerAttrCfgDO::getAttrDataType,
                                AssetServerAttrCfgDO::getAttrValue,
                                AssetServerAttrCfgDO::getCollectFreq,
                                AssetServerAttrCfgDO::getLastCollectTime)
        );
        return BeanUtils.toBean(attrs, AssetServerAttrCfgSimpleRespVO.class);
    }

    /**
     * 导入资产服务端属性配置
     *
     * @param importList 导入信息列表
     * @param updateSupport 是否支持更新已有数据
     * @return 导入结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AssetServerAttrCfgImportRespVO importAssetServerAttrCfgList(List<AssetServerAttrCfgImportExcelVO> importList, boolean updateSupport) {
        // 1. 参数校验
        if (CollUtil.isEmpty(importList)) {
            throw exception(ASSET_SERVER_ATTR_CFG_IMPORT_LIST_IS_EMPTY);
        }

        // 2. 初始化结果对象
        AssetServerAttrCfgImportRespVO respVO = AssetServerAttrCfgImportRespVO.builder()
                .createAssetServerAttrCfgIds(new ArrayList<>())
                .updateAssetServerAttrCfgIds(new ArrayList<>())
                .failureAssetServerAttrCfgIds(new LinkedHashMap<>())
                .build();

        // 3. 批量查询已存在的资产服务端属性配置
        List<String> assetServerAttrIds = importList.stream()
                .map(AssetServerAttrCfgImportExcelVO::getAssetServerAttrId)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());

        List<AssetServerAttrCfgDO> existingConfigs = new ArrayList<>();
        if (CollUtil.isNotEmpty(assetServerAttrIds)) {
            LambdaQueryWrapper<AssetServerAttrCfgDO> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(AssetServerAttrCfgDO::getAssetServerAttrId, assetServerAttrIds);
            existingConfigs = assetServerAttrCfgMapper.selectList(queryWrapper);
        }
        Map<String, AssetServerAttrCfgDO> existingConfigMap = existingConfigs.stream()
                .collect(Collectors.toMap(AssetServerAttrCfgDO::getAssetServerAttrId, config -> config));

        // 4. 遍历导入数据
        int index = 1;
        for (AssetServerAttrCfgImportExcelVO importVO : importList) {
            String assetServerAttrId = importVO.getAssetServerAttrId();
            try {
                // 4.1 基础必填字段校验
                if (StrUtil.isBlank(assetServerAttrId)) {
                    throw new RuntimeException("服务端属性ID不能为空");
                }
                if (StrUtil.isBlank(importVO.getAttrName())) {
                    throw new RuntimeException("属性名称不能为空");
                }
                if (StrUtil.isBlank(importVO.getAttrCode())) {
                    throw new RuntimeException("属性代码不能为空");
                }

                // 4.2 检查是否已存在
                AssetServerAttrCfgDO existingConfig = existingConfigMap.get(assetServerAttrId);

                if (existingConfig != null) {
                    // 更新逻辑
                    if (!updateSupport) {
                        respVO.getFailureAssetServerAttrCfgIds().put(assetServerAttrId,
                                "服务端属性ID已存在，且不支持更新");
                        continue;
                    }
                    // 执行更新
                    updateAssetServerAttrCfgImportData(existingConfig, importVO);
                    respVO.getUpdateAssetServerAttrCfgIds().add(assetServerAttrId);
                } else {
                    // 创建逻辑
                    createAssetServerAttrCfgImportData(importVO);
                    respVO.getCreateAssetServerAttrCfgIds().add(assetServerAttrId);
                }
            } catch (Exception e) {
                String errorKey = StrUtil.isNotBlank(assetServerAttrId) ? assetServerAttrId : "第" + index + "行";
                respVO.getFailureAssetServerAttrCfgIds().put(errorKey, e.getMessage());
            }
            index++;
        }

        // 5. 统计结果
        respVO.setCreateCount(respVO.getCreateAssetServerAttrCfgIds().size());
        respVO.setUpdateCount(respVO.getUpdateAssetServerAttrCfgIds().size());
        respVO.setFailureCount(respVO.getFailureAssetServerAttrCfgIds().size());
        respVO.setSuccessCount(respVO.getCreateCount() + respVO.getUpdateCount());

        return respVO;
    }


    /**
     * 从导入数据创建资产服务端属性配置
     */
    private void createAssetServerAttrCfgImportData(AssetServerAttrCfgImportExcelVO importVO) {
        AssetServerAttrCfgDO assetServerAttrCfg = BeanUtils.toBean(importVO, AssetServerAttrCfgDO.class);

        // 设置系统字段默认值
        if (StrUtil.isBlank(assetServerAttrCfg.getOperUser())) {
            assetServerAttrCfg.setOperUser("系统导入");
        }
        if (StrUtil.isBlank(assetServerAttrCfg.getUpdateUser())) {
            assetServerAttrCfg.setUpdateUser("系统导入");
        }

        // 设置时间字段
        LocalDateTime now = LocalDateTime.now();
        assetServerAttrCfg.setCreateTime(now);
        assetServerAttrCfg.setUpdateTime(now);

        // 如果更新时间为空，设置为当前时间
        if (assetServerAttrCfg.getUpdatedTime() == null) {
            assetServerAttrCfg.setUpdatedTime(now);
        }
        // 如果配置时间为空，设置为当前时间
        if (assetServerAttrCfg.getCfgTime() == null) {
            assetServerAttrCfg.setCfgTime(now);
        }

        assetServerAttrCfgMapper.insert(assetServerAttrCfg);
    }

    /**
     * 从导入数据更新资产服务端属性配置
     */
    private void updateAssetServerAttrCfgImportData(AssetServerAttrCfgDO existingConfig, AssetServerAttrCfgImportExcelVO importVO) {
        // 更新对象
        AssetServerAttrCfgDO updateObj = BeanUtils.toBean(importVO, AssetServerAttrCfgDO.class);
        updateObj.setId(existingConfig.getId());

        // 保留原有的系统字段
        updateObj.setCreateTime(existingConfig.getCreateTime());
        updateObj.setCreator(existingConfig.getCreator());
        updateObj.setDeleted(existingConfig.getDeleted());

        // 设置更新时间
        LocalDateTime now = LocalDateTime.now();
        updateObj.setUpdateTime(now);
        updateObj.setUpdatedTime(now);

        // 如果更新人为空，设置为默认值
        if (StrUtil.isBlank(updateObj.getUpdateUser())) {
            updateObj.setUpdateUser("系统导入");
        }

        // 执行更新
        assetServerAttrCfgMapper.updateById(updateObj);
    }

}