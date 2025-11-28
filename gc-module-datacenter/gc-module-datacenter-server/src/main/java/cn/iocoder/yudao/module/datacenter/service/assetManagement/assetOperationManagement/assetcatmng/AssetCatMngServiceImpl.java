package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetOperationManagement.assetcatmng;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetcatmng.AssetCatMngDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetOperationManagement.assetcatmng.AssetCatMngMapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 资产分类管理 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetCatMngServiceImpl implements AssetCatMngService {

    @Resource
    private AssetCatMngMapper assetCatMngMapper;

    @Override
    public Long createAssetCatMng(AssetCatMngSaveReqVO createReqVO) {
        // 插入
        AssetCatMngDO assetCatMng = BeanUtils.toBean(createReqVO, AssetCatMngDO.class);
        assetCatMngMapper.insert(assetCatMng);
        // 返回
        return assetCatMng.getId();
    }

    @Override
    public void updateAssetCatMng(AssetCatMngSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetCatMngExists(updateReqVO.getId());
        // 更新
        AssetCatMngDO updateObj = BeanUtils.toBean(updateReqVO, AssetCatMngDO.class);
        assetCatMngMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetCatMng(Long id) {
        // 校验存在
        validateAssetCatMngExists(id);
        // 删除
        assetCatMngMapper.deleteById(id);
    }

    private void validateAssetCatMngExists(Long id) {
        if (assetCatMngMapper.selectById(id) == null) {
            throw exception(ASSET_CAT_MNG_NOT_EXISTS);
        }
    }

    @Override
    public AssetCatMngDO getAssetCatMng(Long id) {
        return assetCatMngMapper.selectById(id);
    }

    @Override
    public PageResult<AssetCatMngDO> getAssetCatMngPage(AssetCatMngPageReqVO pageReqVO) {
        return assetCatMngMapper.selectPage(pageReqVO);
    }

    /**
     *
     * @return 资产分类列表数据
     */
    @Override
    public List<AssetCategorySimpleVO> getAssetCatList() {
        List<AssetCatMngDO> cats = assetCatMngMapper.selectList(
                new LambdaQueryWrapperX<AssetCatMngDO>()
                        .select(AssetCatMngDO::getAssetCatId,
                                AssetCatMngDO::getAssetCatName,
                                AssetCatMngDO::getParentCatId,
                                AssetCatMngDO::getCatLevel)
        );
        return BeanUtils.toBean(cats, AssetCategorySimpleVO.class);
    }

    /**
     *
     * @return 启用的资产分类列表
     */
    @Override
    public List<AssetCategorySimpleVO> getEnabledAssetCategories() {
        // 查询启用状态为1的资产分类
        List<AssetCatMngDO> categoryList = assetCatMngMapper.selectList(
                new LambdaQueryWrapperX<AssetCatMngDO>()
                        .eq(AssetCatMngDO::getEnableStatus, "1")
                        .select(AssetCatMngDO::getAssetCatId,
                                AssetCatMngDO::getAssetCatName,
                                AssetCatMngDO::getParentCatId,
                                AssetCatMngDO::getCatLevel)
                        .orderByAsc(AssetCatMngDO::getAssetCatId)
        );

        return BeanUtils.toBean(categoryList, AssetCategorySimpleVO.class);
    }

    /**
     * 导入资产分类管理
     * @param importAssetCatMng 导入信息列表
     * @param isUpdateSupport 是否支持更新已有数据
     * @return 导入结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AssetCatMngImportRespVO importAssetCatMngList(List<AssetCatMngImportExcelVO> importAssetCatMng, boolean isUpdateSupport) {
        // 1. 参数校验
        if (CollUtil.isEmpty(importAssetCatMng)) {
            throw exception(ASSET_CAT_MNG_IMPORT_LIST_IS_EMPTY);
        }

        // 2. 初始化结果对象
        AssetCatMngImportRespVO respVO = AssetCatMngImportRespVO.builder()
                .createAssetCatCodes(new ArrayList<>())
                .updateAssetCatCodes(new ArrayList<>())
                .failureAssetCatCodes(new LinkedHashMap<>())
                .build();

        // 3. 批量查询已存在的资产分类（根据资产分类编码）
        List<String> assetCatCodes = importAssetCatMng.stream()
                .map(AssetCatMngImportExcelVO::getAssetCatCode)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());

        List<AssetCatMngDO> existingAssetCats = new ArrayList<>();
        if (CollUtil.isNotEmpty(assetCatCodes)) {
            LambdaQueryWrapper<AssetCatMngDO> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(AssetCatMngDO::getAssetCatCode, assetCatCodes);
            existingAssetCats = assetCatMngMapper.selectList(queryWrapper);
        }

        Map<String, AssetCatMngDO> existingAssetCatMap = existingAssetCats.stream()
                .collect(Collectors.toMap(AssetCatMngDO::getAssetCatCode, assetCat -> assetCat));

        // 4. 遍历导入数据
        int index = 1;
        for (AssetCatMngImportExcelVO importVO : importAssetCatMng) {
            String assetCatCode = importVO.getAssetCatCode();
            String assetCatName = importVO.getAssetCatName();

            try {
                // 4.1 基础必填字段校验（仅校验最基础的必填字段）
                if (StrUtil.isBlank(assetCatCode)) {
                    throw new RuntimeException("资产分类编码不能为空");
                }
                if (StrUtil.isBlank(assetCatName)) {
                    throw new RuntimeException("资产分类名称不能为空");
                }

                // 4.2 检查是否已存在
                AssetCatMngDO existingAssetCat = existingAssetCatMap.get(assetCatCode);

                if (existingAssetCat != null) {
                    // 更新逻辑
                    if (!isUpdateSupport) {
                        respVO.getFailureAssetCatCodes().put(assetCatCode, "资产分类编码已存在且不支持更新");
                        continue;
                    }
                    // 执行更新
                    updateAssetCatFromImport(existingAssetCat, importVO);
                    respVO.getUpdateAssetCatCodes().add(assetCatCode);
                } else {
                    // 新增逻辑
                    createAssetCatFromImport(importVO);
                    respVO.getCreateAssetCatCodes().add(assetCatCode);
                }
            } catch (Exception e) {
                String errorKey = StrUtil.isNotBlank(assetCatCode) ? assetCatCode :
                        StrUtil.isNotBlank(assetCatName) ? assetCatName : "第" + index + "行";
                respVO.getFailureAssetCatCodes().put(errorKey, e.getMessage());
            }
            index++;
        }

        // 5. 计算统计信息
        respVO.setCreateCount(respVO.getCreateAssetCatCodes().size());
        respVO.setUpdateCount(respVO.getUpdateAssetCatCodes().size());
        respVO.setFailureCount(respVO.getFailureAssetCatCodes().size());
        respVO.setSuccessCount(respVO.getCreateCount() + respVO.getUpdateCount());

        return respVO;
    }


    /**
     * 从导入数据创建资产分类
     */
    private void createAssetCatFromImport(AssetCatMngImportExcelVO importVO) {
        AssetCatMngDO assetCatMng = BeanUtils.toBean(importVO, AssetCatMngDO.class);

        // 设置默认值

        // 设置系统字段默认值
        if (StrUtil.isBlank(assetCatMng.getEnableStatus())) {
            assetCatMng.setEnableStatus("1"); // 默认启用
        }

        // 设置时间字段 - 使用 java.time.LocalDateTime
        java.time.LocalDateTime now = java.time.LocalDateTime.now();

        // 设置 create_time 和 update_time（MyBatis-Plus 自动填充字段）
        assetCatMng.setCreateTime(now);
        assetCatMng.setUpdateTime(now);

        // 设置 created_time 和 updated_time（业务字段）
        if (assetCatMng.getCreatedTime() == null) {
            assetCatMng.setCreatedTime(now);
        }
        if (assetCatMng.getUpdatedTime() == null) {
            assetCatMng.setUpdatedTime(now);
        }

        // 直接插入
        assetCatMngMapper.insert(assetCatMng);
    }

    /**
     * 从导入数据更新资产分类
     */
    private void updateAssetCatFromImport(AssetCatMngDO existingAssetCat, AssetCatMngImportExcelVO importVO) {
        // 更新字段
        AssetCatMngDO updateObj = BeanUtils.toBean(importVO, AssetCatMngDO.class);
        updateObj.setId(existingAssetCat.getId());

        // 保留原有的系统字段
        updateObj.setCreateTime(existingAssetCat.getCreateTime());
        updateObj.setCreator(existingAssetCat.getCreator());
        updateObj.setDeleted(existingAssetCat.getDeleted());

        // 设置更新时间 - 使用完整限定名避免冲突
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        updateObj.setUpdateTime(now);
        updateObj.setUpdatedTime(now);

        // 直接更新
        assetCatMngMapper.updateById(updateObj);
    }

}