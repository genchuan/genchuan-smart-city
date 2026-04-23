package cn.iocoder.yudao.module.inspectop.service.assetinfo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.inspectop.framework.ImportRespVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.assetinfo.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.assetinfo.AssetInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.assetinfo.AssetInfoMapper;
import org.springframework.web.multipart.MultipartFile;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

/**
 * 资产信息 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class AssetInfoServiceImpl implements AssetInfoService {

    @Resource
    private AssetInfoMapper assetInfoMapper;

    @Override
    public Long createAssetInfo(AssetInfoSaveReqVO createReqVO) {
        // 插入
        AssetInfoDO assetInfo = BeanUtils.toBean(createReqVO, AssetInfoDO.class);
        assetInfoMapper.insert(assetInfo);

        // 返回
        return assetInfo.getId();
    }

    @Override
    public void updateAssetInfo(AssetInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetInfoExists(updateReqVO.getId());
        // 更新
        AssetInfoDO updateObj = BeanUtils.toBean(updateReqVO, AssetInfoDO.class);
        assetInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetInfo(Long id) {
        // 校验存在
        validateAssetInfoExists(id);
        // 删除
        assetInfoMapper.deleteById(id);
    }

    @Override
        public void deleteAssetInfoListByIds(List<Long> ids) {
        // 删除
        assetInfoMapper.deleteByIds(ids);
        }


    private void validateAssetInfoExists(Long id) {
        if (assetInfoMapper.selectById(id) == null) {
            throw exception(ASSET_INFO_NOT_EXISTS);
        }
    }

    @Override
    public AssetInfoDO getAssetInfo(Long id) {
        return assetInfoMapper.selectById(id);
    }

    @Override
    public PageResult<AssetInfoRespVO> getAssetInfoPage(AssetInfoPageReqVO pageReqVO) {
        // 创建 MyBatis-Plus 分页对象
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<AssetInfoRespVO> mpPage =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(
                        pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 调用 Mapper 的关联查询方法
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<AssetInfoRespVO> resultPage =
                assetInfoMapper.selectPageWithJoin(mpPage, pageReqVO);

        // 构造并返回 PageResult
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    @Override
    public void disableAssetInfo(Long id) {
        // 1. 校验存在
        validateAssetInfoExists(id);

        // 2. 构建更新对象，设置状态为2（禁用）
        AssetInfoDO updateObj = new AssetInfoDO();
        updateObj.setId(id);
        updateObj.setStatus("2"); // 状态2表示禁用

        // 3. 更新数据库
        assetInfoMapper.updateById(updateObj);
    }

    @Override
    public void scrapAssetInfo(AssetInfoScrapReqVO scrapReqVO) {
        // 1. 校验存在
        validateAssetInfoExists(scrapReqVO.getId());

        // 2. 构建更新对象，设置状态为3（报废），并将报废理由存入reserve1字段
        AssetInfoDO updateObj = new AssetInfoDO();
        updateObj.setId(scrapReqVO.getId());
        updateObj.setStatus("3"); // 状态3表示报废
        updateObj.setReserve1(scrapReqVO.getScrapRemark()); // 报废理由存入备用字段1

        // 3. 更新数据库
        assetInfoMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ImportRespVO importAssetInfo(MultipartFile file, boolean updateSupport) {
        ImportRespVO resp = new ImportRespVO();
        resp.setSuccessCount(0);
        resp.setFailureCount(0);
        resp.setFailureList(new ArrayList<>());

        try {
            // 1. 使用Excel工具类读取文件数据，并映射到AssetInfoImportReqVO列表
            List<AssetInfoImportReqVO> importList = ExcelUtils.read(file, AssetInfoImportReqVO.class);

            if (CollUtil.isEmpty(importList)) {
                throw exception(ASSET_INFO_IMPORT_DATA_EMPTY);
            }

            int successCount = 0;
            List<ImportRespVO.ImportFailure> failures = new ArrayList<>();

            // 2. 遍历处理每一行数据
            for (int i = 0; i < importList.size(); i++) {
                AssetInfoImportReqVO importReqVO = importList.get(i);
                int rowIndex = i + 2; // Excel行号（从1开始，标题行占1行）

                try {
                    // 2.1 校验必填字段
                    if (StrUtil.isBlank(importReqVO.getName())) {
                        throw exception(ASSET_INFO_NAME_NOT_NULL);
                    }
                    if (StrUtil.isBlank(importReqVO.getType())) {
                        throw exception(ASSET_INFO_TYPE_NOT_NULL);
                    }
                    if (StrUtil.isBlank(importReqVO.getStatus())) {
                        throw exception(ASSET_INFO_STATUS_NOT_NULL);
                    }
                    if (importReqVO.getStationId() == null) {
                        throw exception(ASSET_INFO_STATION_ID_NOT_NULL);
                    }

                    // 2.2 根据唯一标识（资产名称）查找是否已存在
                    AssetInfoDO existAsset = assetInfoMapper.selectOne(
                            new LambdaQueryWrapperX<AssetInfoDO>()
                                    .eq(AssetInfoDO::getName, importReqVO.getName())
                    );

                    if (existAsset != null) {
                        if (updateSupport) {
                            // 更新已存在的记录
                            AssetInfoDO updateObj = BeanUtils.toBean(importReqVO, AssetInfoDO.class);
                            updateObj.setId(existAsset.getId()); // 保留原有ID
                            assetInfoMapper.updateById(updateObj);
                        } else {
                            throw exception(ASSET_INFO_EXISTS, importReqVO.getName());
                        }
                    } else {
                        // 新增记录
                        AssetInfoDO assetInfo = BeanUtils.toBean(importReqVO, AssetInfoDO.class);
                        assetInfoMapper.insert(assetInfo);
                    }
                    successCount++;

                } catch (Exception e) {
                    // 2.3 记录本行导入失败的信息
                    ImportRespVO.ImportFailure failure = new ImportRespVO.ImportFailure();
                    failure.setRowIndex(rowIndex);
                    failure.setMessage(e.getMessage());
                    failures.add(failure);
                }
            }

            // 3. 设置最终导入结果
            resp.setSuccessCount(successCount);
            resp.setFailureCount(failures.size());
            resp.setFailureList(failures);
            return resp;

        } catch (Exception e) {
            // 4. 处理整体导入失败（如文件格式错误、读取异常等）
            ImportRespVO.ImportFailure failure = new ImportRespVO.ImportFailure();
            failure.setRowIndex(1);
            failure.setMessage("导入失败：" + e.getMessage());
            resp.getFailureList().add(failure);
            resp.setFailureCount(1);
            return resp;
        }
    }

    @Override
    public AssetInfoChartRespVO getAssetInfoChart() {
        AssetInfoChartRespVO respVO = new AssetInfoChartRespVO();

        // 获取资产类型分布数据
        List<AssetInfoChartRespVO.TypeData> typeData = assetInfoMapper.selectTypeData();
        respVO.setTypeData(typeData);

        // 获取卡片统计数据
        AssetInfoChartRespVO.CardData cardData = assetInfoMapper.selectCardData();
        respVO.setCardData(cardData);

        return respVO;
    }

}