package cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetDataMng.assetshareattrcfg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetshareattrcfg.vo.AssetShareAttrCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetshareattrcfg.AssetShareAttrCfgDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产共享属性配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AssetShareAttrCfgMapper extends BaseMapperX<AssetShareAttrCfgDO> {

    default PageResult<AssetShareAttrCfgDO> selectPage(AssetShareAttrCfgPageReqVO reqVO) {
        // 构建完整的查询条件
        LambdaQueryWrapperX<AssetShareAttrCfgDO> queryWrapper = new LambdaQueryWrapperX<>();
        // 处理特殊排序逻辑
        if ("cfgTime".equals(reqVO.getOrderByColumn())) {
            // 配置时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetShareAttrCfgDO::getCfgTime);
            return selectPage(reqVO, null, queryWrapper);
        }else if ("updatedTime".equals(reqVO.getOrderByColumn())) {
            // 更新时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetShareAttrCfgDO::getUpdatedTime);
            return selectPage(reqVO, null, queryWrapper);
        }

        return selectPage(reqVO, new LambdaQueryWrapperX<AssetShareAttrCfgDO>()
                .eqIfPresent(AssetShareAttrCfgDO::getAssetShareAttrId, reqVO.getAssetShareAttrId())
                .likeIfPresent(AssetShareAttrCfgDO::getRelAssetId, reqVO.getRelAssetId())
                .likeIfPresent(AssetShareAttrCfgDO::getRelAssetName, reqVO.getRelAssetName())
                .likeIfPresent(AssetShareAttrCfgDO::getAttrName, reqVO.getAttrName())
                .likeIfPresent(AssetShareAttrCfgDO::getAttrCode, reqVO.getAttrCode())
                .eqIfPresent(AssetShareAttrCfgDO::getAttrValue, reqVO.getAttrValue())
                .eqIfPresent(AssetShareAttrCfgDO::getShareObjType, reqVO.getShareObjType())
                .eqIfPresent(AssetShareAttrCfgDO::getShareObjId, reqVO.getShareObjId())
                .likeIfPresent(AssetShareAttrCfgDO::getShareObjName, reqVO.getShareObjName())
                .eqIfPresent(AssetShareAttrCfgDO::getSharePerm, reqVO.getSharePerm())
                .betweenIfPresent(AssetShareAttrCfgDO::getCfgTime, reqVO.getCfgTime())
                .eqIfPresent(AssetShareAttrCfgDO::getOperUser, reqVO.getOperUser())
                .eqIfPresent(AssetShareAttrCfgDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(AssetShareAttrCfgDO::getUpdatedTime, reqVO.getUpdatedTime())
                .eqIfPresent(AssetShareAttrCfgDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AssetShareAttrCfgDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AssetShareAttrCfgDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AssetShareAttrCfgDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(AssetShareAttrCfgDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssetShareAttrCfgDO::getId));
    }

}