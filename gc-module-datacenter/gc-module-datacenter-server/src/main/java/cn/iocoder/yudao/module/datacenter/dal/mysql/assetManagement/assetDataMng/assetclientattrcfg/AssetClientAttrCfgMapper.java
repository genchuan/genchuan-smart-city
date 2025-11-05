package cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetDataMng.assetclientattrcfg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetclientattrcfg.vo.AssetClientAttrCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetclientattrcfg.AssetClientAttrCfgDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产客户端属性配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AssetClientAttrCfgMapper extends BaseMapperX<AssetClientAttrCfgDO> {

    default PageResult<AssetClientAttrCfgDO> selectPage(AssetClientAttrCfgPageReqVO reqVO) {
        // 构建完整的查询条件
        LambdaQueryWrapperX<AssetClientAttrCfgDO> queryWrapper = new LambdaQueryWrapperX<>();
        // 处理特殊排序逻辑
        if ("cfgTime".equals(reqVO.getOrderByColumn())) {
            // 配置时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetClientAttrCfgDO::getCfgTime);
            return selectPage(reqVO, null, queryWrapper);
        }else if ("updatedTime".equals(reqVO.getOrderByColumn())) {
            // 更新时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetClientAttrCfgDO::getUpdatedTime);
            return selectPage(reqVO, null, queryWrapper);
        }
        return selectPage(reqVO, new LambdaQueryWrapperX<AssetClientAttrCfgDO>()
                .eqIfPresent(AssetClientAttrCfgDO::getAssetClientAttrId, reqVO.getAssetClientAttrId())
                .likeIfPresent(AssetClientAttrCfgDO::getRelAssetId, reqVO.getRelAssetId())
                .likeIfPresent(AssetClientAttrCfgDO::getRelAssetName, reqVO.getRelAssetName())
                .likeIfPresent(AssetClientAttrCfgDO::getAttrName, reqVO.getAttrName())
                .likeIfPresent(AssetClientAttrCfgDO::getAttrCode, reqVO.getAttrCode())
                .eqIfPresent(AssetClientAttrCfgDO::getAttrDataType, reqVO.getAttrDataType())
                .eqIfPresent(AssetClientAttrCfgDO::getAttrValue, reqVO.getAttrValue())
                .eqIfPresent(AssetClientAttrCfgDO::getAttrPurpose, reqVO.getAttrPurpose())
                .eqIfPresent(AssetClientAttrCfgDO::getClientIp, reqVO.getClientIp())
                .betweenIfPresent(AssetClientAttrCfgDO::getCfgTime, reqVO.getCfgTime())
                .eqIfPresent(AssetClientAttrCfgDO::getOperUser, reqVO.getOperUser())
                .eqIfPresent(AssetClientAttrCfgDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(AssetClientAttrCfgDO::getUpdatedTime, reqVO.getUpdatedTime())
                .eqIfPresent(AssetClientAttrCfgDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AssetClientAttrCfgDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AssetClientAttrCfgDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AssetClientAttrCfgDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(AssetClientAttrCfgDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssetClientAttrCfgDO::getId));
    }

}