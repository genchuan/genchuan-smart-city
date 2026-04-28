package cn.iocoder.yudao.module.studentmgmt.dal.mysql.staymgmt;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcheck.vo.DormCheckChartRespVO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.staymgmt.StayMgmtDO;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.staymgmt.vo.*;

/**
 * 留宿管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface StayMgmtMapper extends BaseMapperX<StayMgmtDO> {

    default PageResult<StayMgmtDO> selectPage(StayMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StayMgmtDO>()
                .eqIfPresent(StayMgmtDO::getStudentId, reqVO.getStudentId())
                .betweenIfPresent(StayMgmtDO::getStayDate, reqVO.getStayDate())
                .eqIfPresent(StayMgmtDO::getStayReason, reqVO.getStayReason())
                .betweenIfPresent(StayMgmtDO::getApplyTime, reqVO.getApplyTime())
                .betweenIfPresent(StayMgmtDO::getParentConfirmTime, reqVO.getParentConfirmTime())
                .eqIfPresent(StayMgmtDO::getAuditUser, reqVO.getAuditUser())
                .betweenIfPresent(StayMgmtDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(StayMgmtDO::getStatus, reqVO.getStatus())
                .eqIfPresent(StayMgmtDO::getRemark, reqVO.getRemark())
                .eqIfPresent(StayMgmtDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(StayMgmtDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(StayMgmtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(StayMgmtDO::getId));
    }

    StayMgmtChartRespVO selectTotalCount(LocalDateTime startTime, LocalDateTime endTime, String className,
                                          String pendingConfirm, String pendingAudit, String passed);

    List<JSONObject> getWeekendTrend(LocalDateTime startTime, LocalDateTime endTime, String className);

    List<JSONObject> getStatusDistribution(LocalDateTime startTime, LocalDateTime endTime, String className);

    List<JSONObject> getClassStatisticsList(LocalDateTime startTime, LocalDateTime endTime);
}