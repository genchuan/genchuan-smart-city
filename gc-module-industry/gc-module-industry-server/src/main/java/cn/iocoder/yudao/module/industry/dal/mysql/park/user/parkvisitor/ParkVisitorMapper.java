package cn.iocoder.yudao.module.industry.dal.mysql.park.user.parkvisitor;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkvisitor.vo.ParkVisitorPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkvisitor.ParkVisitorDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 访客 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkVisitorMapper extends BaseMapperX<ParkVisitorDO> {

    default PageResult<ParkVisitorDO> selectPage(ParkVisitorPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkVisitorDO>()
                .likeIfPresent(ParkVisitorDO::getVisitorName, reqVO.getVisitorName())
                .eqIfPresent(ParkVisitorDO::getPhone, reqVO.getPhone())
                .eqIfPresent(ParkVisitorDO::getIdCard, reqVO.getIdCard())
                .eqIfPresent(ParkVisitorDO::getVisitAssetId, reqVO.getVisitAssetId())
                .eqIfPresent(ParkVisitorDO::getVisitReason, reqVO.getVisitReason())
                .betweenIfPresent(ParkVisitorDO::getVisitTime, reqVO.getVisitTime())
                .betweenIfPresent(ParkVisitorDO::getLeaveTime, reqVO.getLeaveTime())
                .eqIfPresent(ParkVisitorDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ParkVisitorDO::getApproveBy, reqVO.getApproveBy())
                .betweenIfPresent(ParkVisitorDO::getApproveTime, reqVO.getApproveTime())
                .betweenIfPresent(ParkVisitorDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkVisitorDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkVisitorDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkVisitorDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkVisitorDO::getExtCommon4, reqVO.getExtCommon4())
                .eqIfPresent(ParkVisitorDO::getRemark, reqVO.getRemark())
                .orderByDesc(ParkVisitorDO::getId));
    }

}
