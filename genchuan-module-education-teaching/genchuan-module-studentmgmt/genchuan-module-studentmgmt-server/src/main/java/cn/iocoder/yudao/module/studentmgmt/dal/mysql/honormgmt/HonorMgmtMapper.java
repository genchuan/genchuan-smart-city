package cn.iocoder.yudao.module.studentmgmt.dal.mysql.honormgmt;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.honormgmt.HonorMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studentinfo.StudentInfoDO;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.commons.lang3.StringUtils;
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

    default PageResult<HonorMgmtPageRespVO> selectJoinPage(HonorMgmtPageReqVO reqVO) {

        // 1. 构建分页对象
        Page<HonorMgmtPageRespVO> page = new Page<>(
                Objects.requireNonNullElse(reqVO.getPageNo(), 1),
                Objects.requireNonNullElse(reqVO.getPageSize(), 10)
        );

        // 2. 构建 MPJ 联表 Wrapper
        MPJLambdaWrapper<HonorMgmtDO> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(HonorMgmtDO.class);
        wrapper.selectAs(StudentInfoDO::getName, HonorMgmtPageRespVO::getStudentName);
        wrapper.selectAs(StudentInfoDO::getClassName, HonorMgmtPageRespVO::getClassName);
        wrapper.leftJoin(StudentInfoDO.class, StudentInfoDO::getId, HonorMgmtDO::getStudentId);

        if (null != reqVO.getStudentId()) {
            wrapper.eq(HonorMgmtDO::getStudentId, reqVO.getStudentId());
        }
        if (StringUtils.isNotBlank(reqVO.getStatus())) {
            wrapper.eq(HonorMgmtDO::getStatus, reqVO.getStatus());
        }
        if (StringUtils.isNotBlank(reqVO.getHonorType())) {
            wrapper.eq(HonorMgmtDO::getHonorType, reqVO.getHonorType());
        }
        if (StringUtils.isNotBlank(reqVO.getStatus())) {
            wrapper.eq(HonorMgmtDO::getStatus, reqVO.getStatus());
        }

        if (StringUtils.isNotBlank(reqVO.getHonorName())) {
            wrapper.like(HonorMgmtDO::getHonorName, reqVO.getHonorName());
        }
        if (null != reqVO.getStudentName()) {
            wrapper.like(StudentInfoDO::getName, reqVO.getStudentName());
        }
        // 班级
        if (StringUtils.isNotBlank(reqVO.getClassName())) {
            wrapper.like(StudentInfoDO::getClassName, reqVO.getClassName());
        }

        if (null != reqVO.getGetTime()) {
            wrapper.between(HonorMgmtDO::getGetTime, reqVO.getGetTime()[0], reqVO.getGetTime()[1]);
        }

        wrapper.orderByDesc(HonorMgmtDO::getId);// ===== 主表字段 =====

        // 3. 执行联表分页查询
        IPage<HonorMgmtPageRespVO> resultPage = selectJoinPage(page, HonorMgmtPageRespVO.class, wrapper);

        // 4. 返回结果
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    Integer selectTotalHonorCount(@Param("grade") String grade, @Param("major") String major,
                                  @Param("status") String status, @Param("honorType") String honorType);

    Integer selectTodayPushCount(@Param("grade") String grade, @Param("major") String major);

    Integer audit(@Param("ids") List<Long> ids, @Param("auditRemark") String auditRemark,
                  @Param("auditUser") String auditUser, @Param("status") String status);

    List<HonorCountRespVO> selectCountByType(LocalDateTime startTime, LocalDateTime endTime);

    List<HonorCountRespVO> selectCountByClass(LocalDateTime startTime, LocalDateTime endTime);
}