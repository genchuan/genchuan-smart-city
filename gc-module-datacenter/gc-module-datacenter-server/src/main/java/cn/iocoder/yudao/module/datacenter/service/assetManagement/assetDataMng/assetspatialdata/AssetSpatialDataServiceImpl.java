package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetspatialdata;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetspatialdata.vo.AssetSpatialDataImportExcelVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetspatialdata.vo.AssetSpatialDataImportRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetspatialdata.vo.AssetSpatialDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetspatialdata.vo.AssetSpatialDataSaveReqVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetspatialdata.AssetSpatialDataDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetDataMng.assetspatialdata.AssetSpatialDataMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 资产空间数据 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetSpatialDataServiceImpl implements AssetSpatialDataService {

    @Resource
    private AssetSpatialDataMapper assetSpatialDataMapper;

    @Override
    public Long createAssetSpatialData(AssetSpatialDataSaveReqVO createReqVO) {
        // 插入
        AssetSpatialDataDO assetSpatialData = BeanUtils.toBean(createReqVO, AssetSpatialDataDO.class);
        assetSpatialDataMapper.insert(assetSpatialData);
        // 返回
        return assetSpatialData.getId();
    }

    @Override
    public void updateAssetSpatialData(AssetSpatialDataSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetSpatialDataExists(updateReqVO.getId());
        // 更新
        AssetSpatialDataDO updateObj = BeanUtils.toBean(updateReqVO, AssetSpatialDataDO.class);
        assetSpatialDataMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetSpatialData(Long id) {
        // 校验存在
        validateAssetSpatialDataExists(id);
        // 删除
        assetSpatialDataMapper.deleteById(id);
    }

    private void validateAssetSpatialDataExists(Long id) {
        if (assetSpatialDataMapper.selectById(id) == null) {
            throw exception(ASSET_SPATIAL_DATA_NOT_EXISTS);
        }
    }

    @Override
    public AssetSpatialDataDO getAssetSpatialData(Long id) {
        return assetSpatialDataMapper.selectById(id);
    }

    @Override
    public PageResult<AssetSpatialDataDO> getAssetSpatialDataPage(AssetSpatialDataPageReqVO pageReqVO) {
        return assetSpatialDataMapper.selectPage(pageReqVO);
    }


    /**     * 导入资产空间数据
     *
     * @param importAssetSpatialData 导入信息列表
     * @param isUpdateSupport 是否支持更新已有数据
     * @return 导入结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AssetSpatialDataImportRespVO importAssetSpatialDataList(List<AssetSpatialDataImportExcelVO> importAssetSpatialData, boolean isUpdateSupport) {
        // 1. 参数校验
        if (CollUtil.isEmpty(importAssetSpatialData)) {
            throw exception(ASSET_SPATIAL_DATA_IMPORT_LIST_IS_EMPTY);
        }

        // 2. 初始化结果对象
        AssetSpatialDataImportRespVO respVO = AssetSpatialDataImportRespVO.builder()
                .createAssetSpatialIds(new ArrayList<>())
                .updateAssetSpatialIds(new ArrayList<>())
                .failureAssetSpatialIds(new LinkedHashMap<>())
                .build();
        // 3. 批量查询已存在的资产空间数据
        List<String> asssetSpatialIds = importAssetSpatialData.stream()
                .map(AssetSpatialDataImportExcelVO::getAssetSpatialId)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());

        List<AssetSpatialDataDO> existingAssetCats = new ArrayList<>();
        if (CollUtil.isNotEmpty(asssetSpatialIds)) {
            LambdaQueryWrapper<AssetSpatialDataDO> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(AssetSpatialDataDO::getAssetSpatialId, asssetSpatialIds);
            existingAssetCats = assetSpatialDataMapper.selectList(queryWrapper);
        }
        Map<String, AssetSpatialDataDO> existingAssetSpatialDataMap = existingAssetCats.stream()
                .collect(Collectors.toMap(AssetSpatialDataDO::getAssetSpatialId,  assetSpatial -> assetSpatial));
        // 4. 遍历导入数据
        int index = 1;
        for (AssetSpatialDataImportExcelVO importVO : importAssetSpatialData){
            String assetSpatialId = importVO.getAssetSpatialId();
            try {
                // 4.1 基础必填字段校验（仅校验最基础的必填字段）
                if (StrUtil.isBlank(assetSpatialId)) {
                    throw new RuntimeException("资产空间数据ID不能为空");
                }
                // 4.2 检查是否已存在
                AssetSpatialDataDO existingAssetSpatialData = existingAssetSpatialDataMap.get(assetSpatialId);

                if (existingAssetSpatialData != null) {
                    // 更新逻辑
                    if (!isUpdateSupport) {
                        respVO.getFailureAssetSpatialIds().put(assetSpatialId,
                                "资产空间数据ID已存在，且不支持更新");
                        continue;
                    }
                    // 执行更新
                    updateAssetSpatialImportData(existingAssetSpatialData, importVO);
                    respVO.getUpdateAssetSpatialIds().add(assetSpatialId);
                } else {
                    // 创建逻辑
                    createAssetSpatialImportData(importVO);
                    respVO.getCreateAssetSpatialIds().add(assetSpatialId);
                }
            }catch (Exception e) {
                String errorKey  = StrUtil.isNotBlank(assetSpatialId) ? assetSpatialId :
                        StrUtil.isNotBlank(assetSpatialId) ? assetSpatialId : "第" + index + "行";
                respVO.getFailureAssetSpatialIds().put(errorKey, e.getMessage());
        }
            index++;
        }

        // 5. 统计结果
        respVO.setCreateCount(respVO.getCreateAssetSpatialIds().size());
        respVO.setUpdateCount(respVO.getUpdateAssetSpatialIds().size());
        respVO.setFailureCount(respVO.getFailureAssetSpatialIds().size());
        respVO.setSuccessCount(respVO.getCreateCount() + respVO.getUpdateCount());

        return respVO;
    }
    /**
     * 从导入数据创建资产空间数据
     */
    private  void createAssetSpatialImportData(AssetSpatialDataImportExcelVO importVO) {
        AssetSpatialDataDO assetSpatialData = BeanUtils.toBean(importVO, AssetSpatialDataDO.class);
        // 设置默认值

        // 设置系统字段默认值
        if (StrUtil.isBlank(assetSpatialData.getOperUser())) {
            assetSpatialData.setOperUser("系统导入");//默认操作人
        }
        // 设置时间字段 - 使用 java.time.LocalDateTime
        java.time.LocalDateTime now = java.time.LocalDateTime.now();

        // 设置 create_time 和 update_time（MyBatis-Plus 自动填充字段）
        assetSpatialData.setCreateTime(now);
        assetSpatialData.setUpdateTime(now);

        // 设置updated_time（业务字段）
        if (assetSpatialData.getUpdatedTime() == null) {
            assetSpatialData.setUpdatedTime(now);
        }
        assetSpatialDataMapper.insert(assetSpatialData);
    }

    /**
     * 从导入数据更新资产空间数据
     */
    private void updateAssetSpatialImportData(AssetSpatialDataDO existingAssetSpatialData, AssetSpatialDataImportExcelVO importVO) {
        // 更新对象
        AssetSpatialDataDO updateObj = BeanUtils.toBean(importVO, AssetSpatialDataDO.class);
        updateObj.setId(existingAssetSpatialData.getId());
        // 保留原有的系统字段
        updateObj.setCreateTime(existingAssetSpatialData.getCreateTime());
        updateObj.setCreator(existingAssetSpatialData.getCreator());
        updateObj.setDeleted(existingAssetSpatialData.getDeleted());

        // 设置更新时间 - 使用完整限定名避免冲突
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        updateObj.setUpdateTime(now);
        updateObj.setUpdatedTime(now);

        // 执行更新
        assetSpatialDataMapper.updateById(updateObj);
    }

}