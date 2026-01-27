package cn.iocoder.yudao.module.park.dal.mysql.park.user.visitor;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.user.visitor.vo.VisitorPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.visitor.VisitorDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 访客 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface VisitorMapper extends BaseMapperX<VisitorDO> {

    default PageResult<VisitorDO> selectPage(VisitorPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<VisitorDO>()
                .likeIfPresent(VisitorDO::getVisitorName, reqVO.getVisitorName())
                .eqIfPresent(VisitorDO::getPhone, reqVO.getPhone())
                .eqIfPresent(VisitorDO::getIdCard, reqVO.getIdCard())
                .eqIfPresent(VisitorDO::getVisitResourceId, reqVO.getVisitResourceId())
                .eqIfPresent(VisitorDO::getVisitReason, reqVO.getVisitReason())
                .betweenIfPresent(VisitorDO::getVisitTime, reqVO.getVisitTime())
                .betweenIfPresent(VisitorDO::getExpectLeaveTime, reqVO.getExpectLeaveTime())
                .betweenIfPresent(VisitorDO::getLeaveTime, reqVO.getLeaveTime())
                .eqIfPresent(VisitorDO::getRegisterBy, reqVO.getRegisterBy())
                .betweenIfPresent(VisitorDO::getRegisterTime, reqVO.getRegisterTime())
                .eqIfPresent(VisitorDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(VisitorDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(VisitorDO::getRemark, reqVO.getRemark())
                .eqIfPresent(VisitorDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(VisitorDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(VisitorDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(VisitorDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(VisitorDO::getId));
    }

}
