package cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetDataMng.assetserverattrcfg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetserverattrcfg.vo.AssetServerAttrCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetserverattrcfg.AssetServerAttrCfgDO;

import org.apache.ibatis.annotations.Mapper;


/**
 * 资产服务端属性配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AssetServerAttrCfgMapper extends BaseMapperX<AssetServerAttrCfgDO> {

    default PageResult<AssetServerAttrCfgDO> selectPage(AssetServerAttrCfgPageReqVO reqVO) {
        // 构建完整的查询条件
        LambdaQueryWrapperX<AssetServerAttrCfgDO> queryWrapper = new LambdaQueryWrapperX<>();
        // 处理特殊排序逻辑
        if ("lastCollectTime".equals(reqVO.getOrderByColumn())) {
            // 创建时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetServerAttrCfgDO::getLastCollectTime);
            return selectPage(reqVO, null, queryWrapper);
        }else if ("cfgTime".equals(reqVO.getOrderByColumn())) {
            // 排序时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetServerAttrCfgDO::getCfgTime);
            return selectPage(reqVO, null, queryWrapper);
        }else if ("updatedTime".equals(reqVO.getOrderByColumn())) {
            // 排序时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetServerAttrCfgDO::getUpdatedTime);
            return selectPage(reqVO, null, queryWrapper);
        }
        return selectPage(reqVO, new LambdaQueryWrapperX<AssetServerAttrCfgDO>()
                .eqIfPresent(AssetServerAttrCfgDO::getAssetServerAttrId, reqVO.getAssetServerAttrId())
                .likeIfPresent(AssetServerAttrCfgDO::getRelAssetId, reqVO.getRelAssetId())
                .likeIfPresent(AssetServerAttrCfgDO::getRelAssetName, reqVO.getRelAssetName())
                .likeIfPresent(AssetServerAttrCfgDO::getAttrName, reqVO.getAttrName())
                .likeIfPresent(AssetServerAttrCfgDO::getAttrCode, reqVO.getAttrCode())
                .eqIfPresent(AssetServerAttrCfgDO::getAttrDataType, reqVO.getAttrDataType())
                .eqIfPresent(AssetServerAttrCfgDO::getAttrValue, reqVO.getAttrValue())
                .eqIfPresent(AssetServerAttrCfgDO::getCollectFreq, reqVO.getCollectFreq())
                .betweenIfPresent(AssetServerAttrCfgDO::getLastCollectTime, reqVO.getLastCollectTime())
                .betweenIfPresent(AssetServerAttrCfgDO::getCfgTime, reqVO.getCfgTime())
                .eqIfPresent(AssetServerAttrCfgDO::getOperUser, reqVO.getOperUser())
                .eqIfPresent(AssetServerAttrCfgDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(AssetServerAttrCfgDO::getUpdatedTime, reqVO.getUpdatedTime())
                .eqIfPresent(AssetServerAttrCfgDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AssetServerAttrCfgDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AssetServerAttrCfgDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AssetServerAttrCfgDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(AssetServerAttrCfgDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssetServerAttrCfgDO::getId));
    }

}