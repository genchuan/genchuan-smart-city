package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetclientattrcfg;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetclientattrcfg.vo.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetclientattrcfg.AssetClientAttrCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetDataMng.assetclientattrcfg.AssetClientAttrCfgMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 资产客户端属性配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetClientAttrCfgServiceImpl implements AssetClientAttrCfgService {

    @Resource
    private AssetClientAttrCfgMapper assetClientAttrCfgMapper;

    @Override
    public Long createAssetClientAttrCfg(AssetClientAttrCfgSaveReqVO createReqVO) {
        // 插入
        AssetClientAttrCfgDO assetClientAttrCfg = BeanUtils.toBean(createReqVO, AssetClientAttrCfgDO.class);
        assetClientAttrCfgMapper.insert(assetClientAttrCfg);
        // 返回
        return assetClientAttrCfg.getId();
    }

    @Override
    public void updateAssetClientAttrCfg(AssetClientAttrCfgSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetClientAttrCfgExists(updateReqVO.getId());
        // 更新
        AssetClientAttrCfgDO updateObj = BeanUtils.toBean(updateReqVO, AssetClientAttrCfgDO.class);
        assetClientAttrCfgMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetClientAttrCfg(Long id) {
        // 校验存在
        validateAssetClientAttrCfgExists(id);
        // 删除
        assetClientAttrCfgMapper.deleteById(id);
    }

    private void validateAssetClientAttrCfgExists(Long id) {
        if (assetClientAttrCfgMapper.selectById(id) == null) {
            throw exception(ASSET_CLIENT_ATTR_CFG_NOT_EXISTS);
        }
    }

    @Override
    public AssetClientAttrCfgDO getAssetClientAttrCfg(Long id) {
        return assetClientAttrCfgMapper.selectById(id);
    }

    @Override
    public PageResult<AssetClientAttrCfgDO> getAssetClientAttrCfgPage(AssetClientAttrCfgPageReqVO pageReqVO) {
        return assetClientAttrCfgMapper.selectPage(pageReqVO);
    }

    @Override
    public List<AssetClientAttrCfgSimpleRespVO> getAssetClientAttrList() {
        List<AssetClientAttrCfgDO> attrs = assetClientAttrCfgMapper.selectList(
                new LambdaQueryWrapper<AssetClientAttrCfgDO>()
                        .select(AssetClientAttrCfgDO::getRelAssetId,
                                AssetClientAttrCfgDO::getAttrName,
                                AssetClientAttrCfgDO::getAttrDataType,
                                AssetClientAttrCfgDO::getAttrValue)
        );
        return BeanUtils.toBean(attrs,AssetClientAttrCfgSimpleRespVO.class);
    }


    /**
     * 导入资产客户端属性配置
     *
     * @param importList 导入信息列表
     * @param updateSupport 是否支持更新已有数据
     * @return 导入结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AssetClientAttrCfgImportRespVO importAssetClientAttrCfgList(List<AssetClientAttrCfgImportExcelVO> importList, boolean updateSupport) {
        // 1. 参数校验
        if (CollUtil.isEmpty(importList)) {
            throw exception(ASSET_CLIENT_ATTR_CFG_IMPORT_LIST_IS_EMPTY);
        }

        // 2. 初始化结果对象
        AssetClientAttrCfgImportRespVO respVO = AssetClientAttrCfgImportRespVO.builder()
                .createAssetClientAttrCfgIds(new ArrayList<>())
                .updateAssetClientAttrCfgIds(new ArrayList<>())
                .failureAssetClientAttrCfgIds(new LinkedHashMap<>())
                .build();

        // 3. 批量查询已存在的资产客户端属性配置
        List<String> assetClientAttrIds = importList.stream()
                .map(AssetClientAttrCfgImportExcelVO::getAssetClientAttrId)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());

        List<AssetClientAttrCfgDO> existingConfigs = new ArrayList<>();
        if (CollUtil.isNotEmpty(assetClientAttrIds)) {
            LambdaQueryWrapper<AssetClientAttrCfgDO> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(AssetClientAttrCfgDO::getAssetClientAttrId, assetClientAttrIds);
            existingConfigs = assetClientAttrCfgMapper.selectList(queryWrapper);
        }
        Map<String, AssetClientAttrCfgDO> existingConfigMap = existingConfigs.stream()
                .collect(Collectors.toMap(AssetClientAttrCfgDO::getAssetClientAttrId, config -> config));

        // 4. 遍历导入数据
        int index = 1;
        for (AssetClientAttrCfgImportExcelVO importVO : importList) {
            String assetClientAttrId = importVO.getAssetClientAttrId();
            try {
                // 4.1 基础必填字段校验
                if (StrUtil.isBlank(assetClientAttrId)) {
                    throw new RuntimeException("客户端属性ID不能为空");
                }
                if (StrUtil.isBlank(importVO.getAttrName())) {
                    throw new RuntimeException("属性名称不能为空");
                }
                if (StrUtil.isBlank(importVO.getAttrCode())) {
                    throw new RuntimeException("属性代码不能为空");
                }
                if (StrUtil.isBlank(importVO.getClientIp())) {
                    throw new RuntimeException("客户端IP不能为空");
                }

                // 4.2 检查是否已存在
                AssetClientAttrCfgDO existingConfig = existingConfigMap.get(assetClientAttrId);

                if (existingConfig != null) {
                    // 更新逻辑
                    if (!updateSupport) {
                        respVO.getFailureAssetClientAttrCfgIds().put(assetClientAttrId,
                                "客户端属性ID已存在，且不支持更新");
                        continue;
                    }
                    // 执行更新
                    updateAssetClientAttrCfgImportData(existingConfig, importVO);
                    respVO.getUpdateAssetClientAttrCfgIds().add(assetClientAttrId);
                } else {
                    // 创建逻辑
                    createAssetClientAttrCfgImportData(importVO);
                    respVO.getCreateAssetClientAttrCfgIds().add(assetClientAttrId);
                }
            } catch (Exception e) {
                String errorKey = StrUtil.isNotBlank(assetClientAttrId) ? assetClientAttrId : "第" + index + "行";
                respVO.getFailureAssetClientAttrCfgIds().put(errorKey, e.getMessage());
            }
            index++;
        }

        // 5. 统计结果
        respVO.setCreateCount(respVO.getCreateAssetClientAttrCfgIds().size());
        respVO.setUpdateCount(respVO.getUpdateAssetClientAttrCfgIds().size());
        respVO.setFailureCount(respVO.getFailureAssetClientAttrCfgIds().size());
        respVO.setSuccessCount(respVO.getCreateCount() + respVO.getUpdateCount());

        return respVO;
    }


    /**
     * 从导入数据创建资产客户端属性配置
     */
    private void createAssetClientAttrCfgImportData(AssetClientAttrCfgImportExcelVO importVO) {
        AssetClientAttrCfgDO assetClientAttrCfg = BeanUtils.toBean(importVO, AssetClientAttrCfgDO.class);

        // 设置系统字段默认值
        if (StrUtil.isBlank(assetClientAttrCfg.getOperUser())) {
            assetClientAttrCfg.setOperUser("系统导入");
        }
        if (StrUtil.isBlank(assetClientAttrCfg.getUpdateUser())) {
            assetClientAttrCfg.setUpdateUser("系统导入");
        }

        // 设置时间字段
        LocalDateTime now = LocalDateTime.now();
        assetClientAttrCfg.setCreateTime(now);
        assetClientAttrCfg.setUpdateTime(now);

        // 如果更新时间为空，设置为当前时间
        if (assetClientAttrCfg.getUpdatedTime() == null) {
            assetClientAttrCfg.setUpdatedTime(now);
        }
        // 如果配置时间为空，设置为当前时间
        if (assetClientAttrCfg.getCfgTime() == null) {
            assetClientAttrCfg.setCfgTime(now);
        }

        assetClientAttrCfgMapper.insert(assetClientAttrCfg);
    }

    /**
     * 从导入数据更新资产客户端属性配置
     */
    private void updateAssetClientAttrCfgImportData(AssetClientAttrCfgDO existingConfig, AssetClientAttrCfgImportExcelVO importVO) {
        // 更新对象
        AssetClientAttrCfgDO updateObj = BeanUtils.toBean(importVO, AssetClientAttrCfgDO.class);
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
        assetClientAttrCfgMapper.updateById(updateObj);
    }

}