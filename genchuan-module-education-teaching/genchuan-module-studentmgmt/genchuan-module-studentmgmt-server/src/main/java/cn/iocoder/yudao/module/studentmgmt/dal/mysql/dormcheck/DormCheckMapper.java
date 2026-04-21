package cn.iocoder.yudao.module.studentmgmt.dal.mysql.dormcheck;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dormcheck.DormCheckDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcheck.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 宿舍考勤 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface DormCheckMapper extends BaseMapperX<DormCheckDO> {

    default PageResult<DormCheckDO> selectPage(DormCheckPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DormCheckDO>()
                .eqIfPresent(DormCheckDO::getStudentId, reqVO.getStudentId())
                .betweenIfPresent(DormCheckDO::getCheckTime, reqVO.getCheckTime())
                .eqIfPresent(DormCheckDO::getCheckStatus, reqVO.getCheckStatus())
                .eqIfPresent(DormCheckDO::getAbnormalType, reqVO.getAbnormalType())
                .betweenIfPresent(DormCheckDO::getRepairTime, reqVO.getRepairTime())
                .eqIfPresent(DormCheckDO::getRepairUser, reqVO.getRepairUser())
                .betweenIfPresent(DormCheckDO::getPushTime, reqVO.getPushTime())
                .eqIfPresent(DormCheckDO::getInRate, reqVO.getInRate())
                .eqIfPresent(DormCheckDO::getStatus, reqVO.getStatus())
                .eqIfPresent(DormCheckDO::getRemark, reqVO.getRemark())
                .eqIfPresent(DormCheckDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(DormCheckDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(DormCheckDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DormCheckDO::getId));
    }

    Integer selectTotalCount(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime,
                             @Param("status") String status, @Param("checkStatus") String checkStatus, @Param("abnormalType") String abnormalType);

    Integer selectAbnormalCount(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime, @Param("checkStatus") String checkStatus);

    Integer selectAbnormalCountByClassName(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime, @Param("status") String status, @Param("className") String className);
}