package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetshareattrcfg;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetshareattrcfg.vo.AssetShareAttrCfgImportExcelVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetshareattrcfg.vo.AssetShareAttrCfgImportRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetshareattrcfg.vo.AssetShareAttrCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetshareattrcfg.vo.AssetShareAttrCfgSaveReqVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetshareattrcfg.AssetShareAttrCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetDataMng.assetshareattrcfg.AssetShareAttrCfgMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 资产共享属性配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetShareAttrCfgServiceImpl implements AssetShareAttrCfgService {

    @Resource
    private AssetShareAttrCfgMapper assetShareAttrCfgMapper;

    @Override
    public Long createAssetShareAttrCfg(AssetShareAttrCfgSaveReqVO createReqVO) {
        // 插入
        AssetShareAttrCfgDO assetShareAttrCfg = BeanUtils.toBean(createReqVO, AssetShareAttrCfgDO.class);
        assetShareAttrCfgMapper.insert(assetShareAttrCfg);
        // 返回
        return assetShareAttrCfg.getId();
    }

    @Override
    public void updateAssetShareAttrCfg(AssetShareAttrCfgSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetShareAttrCfgExists(updateReqVO.getId());
        // 更新
        AssetShareAttrCfgDO updateObj = BeanUtils.toBean(updateReqVO, AssetShareAttrCfgDO.class);
        assetShareAttrCfgMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetShareAttrCfg(Long id) {
        // 校验存在
        validateAssetShareAttrCfgExists(id);
        // 删除
        assetShareAttrCfgMapper.deleteById(id);
    }

    private void validateAssetShareAttrCfgExists(Long id) {
        if (assetShareAttrCfgMapper.selectById(id) == null) {
            throw exception(ASSET_SHARE_ATTR_CFG_NOT_EXISTS);
        }
    }

    @Override
    public AssetShareAttrCfgDO getAssetShareAttrCfg(Long id) {
        return assetShareAttrCfgMapper.selectById(id);
    }

    @Override
    public PageResult<AssetShareAttrCfgDO> getAssetShareAttrCfgPage(AssetShareAttrCfgPageReqVO pageReqVO) {
        return assetShareAttrCfgMapper.selectPage(pageReqVO);
    }

    /**
     * 导入资产共享属性配置
     *
     * @param importList 导入信息列表
     * @param updateSupport 是否支持更新已有数据
     * @return 导入结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AssetShareAttrCfgImportRespVO importExcel(List<AssetShareAttrCfgImportExcelVO> importList, boolean updateSupport) {
        // 1. 参数校验
        if (CollUtil.isEmpty(importList)) {
            throw exception(ASSET_SHARE_ATTR_CFG_IMPORT_LIST_IS_EMPTY);
        }

        // 2. 初始化结果对象
        AssetShareAttrCfgImportRespVO respVO = AssetShareAttrCfgImportRespVO.builder()
                .createAssetShareAttrCfgIds(new ArrayList<>())
                .updateAssetShareAttrCfgIds(new ArrayList<>())
                .failureAssetShareAttrCfgIds(new LinkedHashMap<>())
                .build();

        // 3. 批量查询已存在的资产共享属性配置
        List<String> assetShareAttrIds = importList.stream()
                .map(AssetShareAttrCfgImportExcelVO::getAssetShareAttrId)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());

        List<AssetShareAttrCfgDO> existingConfigs = new ArrayList<>();
        if (CollUtil.isNotEmpty(assetShareAttrIds)) {
            LambdaQueryWrapper<AssetShareAttrCfgDO> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(AssetShareAttrCfgDO::getAssetShareAttrId, assetShareAttrIds);
            existingConfigs = assetShareAttrCfgMapper.selectList(queryWrapper);
        }
        Map<String, AssetShareAttrCfgDO> existingConfigMap = existingConfigs.stream()
                .collect(Collectors.toMap(AssetShareAttrCfgDO::getAssetShareAttrId, config -> config));

        // 4. 遍历导入数据
        int index = 1;
        for (AssetShareAttrCfgImportExcelVO importVO : importList) {
            String assetShareAttrId = importVO.getAssetShareAttrId();
            try {
                // 4.1 基础必填字段校验
                if (StrUtil.isBlank(assetShareAttrId)) {
                    throw new RuntimeException("共享属性ID不能为空");
                }
                if (StrUtil.isBlank(importVO.getAttrName())) {
                    throw new RuntimeException("属性名称不能为空");
                }
                if (StrUtil.isBlank(importVO.getAttrCode())) {
                    throw new RuntimeException("属性代码不能为空");
                }
                if (StrUtil.isBlank(importVO.getShareObjType())) {
                    throw new RuntimeException("共享对象类型不能为空");
                }
                if (StrUtil.isBlank(importVO.getShareObjId())) {
                    throw new RuntimeException("共享对象ID不能为空");
                }
                if (StrUtil.isBlank(importVO.getSharePerm())) {
                    throw new RuntimeException("共享权限不能为空");
                }

                // 4.2 检查是否已存在
                AssetShareAttrCfgDO existingConfig = existingConfigMap.get(assetShareAttrId);

                if (existingConfig != null) {
                    // 更新逻辑
                    if (!updateSupport) {
                        respVO.getFailureAssetShareAttrCfgIds().put(assetShareAttrId,
                                "共享属性ID已存在，且不支持更新");
                        continue;
                    }
                    // 执行更新
                    updateAssetShareAttrCfgImportData(existingConfig, importVO);
                    respVO.getUpdateAssetShareAttrCfgIds().add(assetShareAttrId);
                } else {
                    // 创建逻辑
                    createAssetShareAttrCfgImportData(importVO);
                    respVO.getCreateAssetShareAttrCfgIds().add(assetShareAttrId);
                }
            } catch (Exception e) {
                String errorKey = StrUtil.isNotBlank(assetShareAttrId) ? assetShareAttrId : "第" + index + "行";
                respVO.getFailureAssetShareAttrCfgIds().put(errorKey, e.getMessage());
            }
            index++;
        }

        // 5. 统计结果
        respVO.setCreateCount(respVO.getCreateAssetShareAttrCfgIds().size());
        respVO.setUpdateCount(respVO.getUpdateAssetShareAttrCfgIds().size());
        respVO.setFailureCount(respVO.getFailureAssetShareAttrCfgIds().size());
        respVO.setSuccessCount(respVO.getCreateCount() + respVO.getUpdateCount());

        return respVO;
    }

    /**
     * 从导入数据创建资产共享属性配置
     */
    private void createAssetShareAttrCfgImportData(AssetShareAttrCfgImportExcelVO importVO) {
        AssetShareAttrCfgDO assetShareAttrCfg = BeanUtils.toBean(importVO, AssetShareAttrCfgDO.class);

        // 设置系统字段默认值
        if (StrUtil.isBlank(assetShareAttrCfg.getOperUser())) {
            assetShareAttrCfg.setOperUser("系统导入");
        }
        if (StrUtil.isBlank(assetShareAttrCfg.getUpdateUser())) {
            assetShareAttrCfg.setUpdateUser("系统导入");
        }

        // 设置时间字段
        LocalDateTime now = LocalDateTime.now();
        assetShareAttrCfg.setCreateTime(now);
        assetShareAttrCfg.setUpdateTime(now);

        // 如果更新时间为空，设置为当前时间
        if (assetShareAttrCfg.getUpdatedTime() == null) {
            assetShareAttrCfg.setUpdatedTime(now);
        }
        // 如果配置时间为空，设置为当前时间
        if (assetShareAttrCfg.getCfgTime() == null) {
            assetShareAttrCfg.setCfgTime(now);
        }

        assetShareAttrCfgMapper.insert(assetShareAttrCfg);
    }

    /**
     * 从导入数据更新资产共享属性配置
     */
    private void updateAssetShareAttrCfgImportData(AssetShareAttrCfgDO existingConfig, AssetShareAttrCfgImportExcelVO importVO) {
        // 更新对象
        AssetShareAttrCfgDO updateObj = BeanUtils.toBean(importVO, AssetShareAttrCfgDO.class);
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
        assetShareAttrCfgMapper.updateById(updateObj);
    }


}