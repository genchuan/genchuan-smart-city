package cn.iocoder.yudao.module.datacenter.dal.mysql.commanddispatch.crossregiondispatch;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.crossregiondispatch.vo.CrossRegionDispatchPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.crossregiondispatch.CrossRegionDispatchDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 跨域调度协调 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface CrossRegionDispatchMapper extends BaseMapperX<CrossRegionDispatchDO> {

    default PageResult<CrossRegionDispatchDO> selectPage(CrossRegionDispatchPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CrossRegionDispatchDO>()
                .eqIfPresent(CrossRegionDispatchDO::getCoordinateId, reqVO.getCoordinateId())
                .eqIfPresent(CrossRegionDispatchDO::getCoordinateNo, reqVO.getCoordinateNo())
                .eqIfPresent(CrossRegionDispatchDO::getApplyUnitId, reqVO.getApplyUnitId())
                .likeIfPresent(CrossRegionDispatchDO::getApplyUnitName, reqVO.getApplyUnitName())
                .eqIfPresent(CrossRegionDispatchDO::getCooperUnitId, reqVO.getCooperUnitId())
                .likeIfPresent(CrossRegionDispatchDO::getCooperUnitName, reqVO.getCooperUnitName())
                .eqIfPresent(CrossRegionDispatchDO::getApplyReason, reqVO.getApplyReason())
                .eqIfPresent(CrossRegionDispatchDO::getResType, reqVO.getResType())
                .likeIfPresent(CrossRegionDispatchDO::getResName, reqVO.getResName())
                .eqIfPresent(CrossRegionDispatchDO::getRequireQuantity, reqVO.getRequireQuantity())
                .eqIfPresent(CrossRegionDispatchDO::getCooperRegion, reqVO.getCooperRegion())
                .eqIfPresent(CrossRegionDispatchDO::getApplyStatus, reqVO.getApplyStatus())
                .eqIfPresent(CrossRegionDispatchDO::getApplyUserId, reqVO.getApplyUserId())
                .likeIfPresent(CrossRegionDispatchDO::getApplyUserName, reqVO.getApplyUserName())
                .betweenIfPresent(CrossRegionDispatchDO::getApplyTime, reqVO.getApplyTime())
                .eqIfPresent(CrossRegionDispatchDO::getAuditUserId, reqVO.getAuditUserId())
                .likeIfPresent(CrossRegionDispatchDO::getAuditUserName, reqVO.getAuditUserName())
                .betweenIfPresent(CrossRegionDispatchDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(CrossRegionDispatchDO::getAuditOpinion, reqVO.getAuditOpinion())
                .eqIfPresent(CrossRegionDispatchDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(CrossRegionDispatchDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(CrossRegionDispatchDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(CrossRegionDispatchDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(CrossRegionDispatchDO::getId));
    }

}