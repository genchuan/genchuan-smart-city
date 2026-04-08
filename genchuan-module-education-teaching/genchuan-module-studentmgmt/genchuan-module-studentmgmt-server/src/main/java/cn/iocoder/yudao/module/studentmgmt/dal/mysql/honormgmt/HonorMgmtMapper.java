package cn.iocoder.yudao.module.studentmgmt.dal.mysql.honormgmt;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.honormgmt.HonorMgmtDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.honormgmt.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 荣誉管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface HonorMgmtMapper extends BaseMapperX<HonorMgmtDO> {

    default PageResult<HonorMgmtDO> selectPage(HonorMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<HonorMgmtDO>()
                .eqIfPresent(HonorMgmtDO::getStudentId, reqVO.getStudentId())
                .eqIfPresent(HonorMgmtDO::getHonorType, reqVO.getHonorType())
                .likeIfPresent(HonorMgmtDO::getHonorName, reqVO.getHonorName())
                .betweenIfPresent(HonorMgmtDO::getGetTime, reqVO.getGetTime())
                .eqIfPresent(HonorMgmtDO::getAuditUser, reqVO.getAuditUser())
                .betweenIfPresent(HonorMgmtDO::getAuditTime, reqVO.getAuditTime())
                .betweenIfPresent(HonorMgmtDO::getPushTime, reqVO.getPushTime())
                .eqIfPresent(HonorMgmtDO::getStatus, reqVO.getStatus())
                .eqIfPresent(HonorMgmtDO::getRemark, reqVO.getRemark())
                .eqIfPresent(HonorMgmtDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(HonorMgmtDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(HonorMgmtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(HonorMgmtDO::getId));
    }

    Integer selectTotalHonorCount(@Param("grade")String grade, @Param("major") String major,
                                  @Param("status") String status, @Param("honorType") String honorType);

    Integer selectTodayPushCount(@Param("grade") String grade, @Param("major") String major);
}