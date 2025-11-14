package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regional.domdivasset;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivasset.vo.DomDivAssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivasset.DomDivAssetDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产分域 Mapper
 *
 * @author zcq
 */
@Mapper
public interface DomDivAssetMapper extends BaseMapperX<DomDivAssetDO> {

    default PageResult<DomDivAssetDO> selectPage(DomDivAssetPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DomDivAssetDO>()
                .eqIfPresent(DomDivAssetDO::getDomId, reqVO.getDomId())
                .likeIfPresent(DomDivAssetDO::getDomName, reqVO.getDomName())
                .eqIfPresent(DomDivAssetDO::getDomCode, reqVO.getDomCode())
                .eqIfPresent(DomDivAssetDO::getAssetMajorId, reqVO.getAssetMajorId())
                .likeIfPresent(DomDivAssetDO::getAssetMajorName, reqVO.getAssetMajorName())
                .eqIfPresent(DomDivAssetDO::getAssetMinorId, reqVO.getAssetMinorId())
                .likeIfPresent(DomDivAssetDO::getAssetMinorName, reqVO.getAssetMinorName())
                .eqIfPresent(DomDivAssetDO::getRelAssetCount, reqVO.getRelAssetCount())
                .eqIfPresent(DomDivAssetDO::getRelPtCount, reqVO.getRelPtCount())
                .eqIfPresent(DomDivAssetDO::getRelDeviceCount, reqVO.getRelDeviceCount())
                .eqIfPresent(DomDivAssetDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(DomDivAssetDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(DomDivAssetDO::getDomStatus, reqVO.getDomStatus())
                .eqIfPresent(DomDivAssetDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(DomDivAssetDO::getUpdateTimeBiz, reqVO.getUpdateTimeBiz())
                .eqIfPresent(DomDivAssetDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DomDivAssetDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DomDivAssetDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DomDivAssetDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DomDivAssetDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DomDivAssetDO::getId));
    }

}