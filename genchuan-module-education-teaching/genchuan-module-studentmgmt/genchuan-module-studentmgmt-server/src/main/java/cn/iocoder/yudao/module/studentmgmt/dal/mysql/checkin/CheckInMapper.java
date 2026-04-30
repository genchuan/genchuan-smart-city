package cn.iocoder.yudao.module.studentmgmt.dal.mysql.checkin;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.checkin.CheckInDO;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.checkin.vo.*;

/**
 * 报到管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CheckInMapper extends BaseMapperX<CheckInDO> {

    default PageResult<CheckInDO> selectPage(CheckInPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CheckInDO>()
                .eqIfPresent(CheckInDO::getStudentId, reqVO.getStudentId())
                .eqIfPresent(CheckInDO::getExamScore, reqVO.getExamScore())
                .eqIfPresent(CheckInDO::getSupplyInfo, reqVO.getSupplyInfo())
                .betweenIfPresent(CheckInDO::getConfirmTime, reqVO.getConfirmTime())
                .eqIfPresent(CheckInDO::getAuditUser, reqVO.getAuditUser())
                .betweenIfPresent(CheckInDO::getAuditTime, reqVO.getAuditTime())
                .betweenIfPresent(CheckInDO::getAccountCreateTime, reqVO.getAccountCreateTime())
                .eqIfPresent(CheckInDO::getAccountStatus, reqVO.getAccountStatus())
                .eqIfPresent(CheckInDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CheckInDO::getRemark, reqVO.getRemark())
                .eqIfPresent(CheckInDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(CheckInDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(CheckInDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CheckInDO::getId));
    }

    CheckInChartRespVO selectTotalCount(Integer year, String pendingConfirm, String pendingAudit, String checkedIn);

    List<JSONObject> selectDateList(Integer year);

    CheckInChartIndexRespVO selectCheckInCount(Integer year, String status, String accountStatus);
}