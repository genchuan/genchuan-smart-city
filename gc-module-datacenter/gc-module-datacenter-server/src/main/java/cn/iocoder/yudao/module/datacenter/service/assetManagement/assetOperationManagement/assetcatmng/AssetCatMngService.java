package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetOperationManagement.assetcatmng;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo.AssetCatMngImportExcelVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo.AssetCatMngImportRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo.AssetCatMngListReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo.AssetCatMngSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetcatmng.AssetCatMngDO;

/**
 * 资产分类管理 Service 接口
 *
 * @author 亘川智城
 */
public interface AssetCatMngService {

    /**
     * 创建资产分类管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetCatMng(@Valid AssetCatMngSaveReqVO createReqVO);

    /**
     * 更新资产分类管理
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetCatMng(@Valid AssetCatMngSaveReqVO updateReqVO);

    /**
     * 删除资产分类管理
     *
     * @param id 编号
     */
    void deleteAssetCatMng(Long id);

    /**
     * 获得资产分类管理
     *
     * @param id 编号
     * @return 资产分类管理
     */
    AssetCatMngDO getAssetCatMng(Long id);

    /**
     * 获得资产分类管理列表
     *
     * @param listReqVO 查询条件
     * @return 资产分类管理列表
     */
    List<AssetCatMngDO> getAssetCatMngList(AssetCatMngListReqVO listReqVO);

    /**
     * 导入资产分类管理
     *
     * @param importAssetCatMng 导入信息列表
     * @param isUpdateSupport 是否支持更新已有数据
     * @return 导入结果
     */
    AssetCatMngImportRespVO importAssetCatMngList(List<AssetCatMngImportExcelVO> importAssetCatMng, boolean isUpdateSupport);

}