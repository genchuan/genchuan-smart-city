package cn.iocoder.yudao.module.studentmgmt.dal.mysql.violatemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.clubmgmt.vo.ClubMgmtRespVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.violatemgmt.vo.ViolateMgmtPageReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.violatemgmt.vo.ViolateMgmtPageRespVO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studentinfo.StudentInfoDO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.violatemgmt.ViolateMgmtDO;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 违纪管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ViolateMgmtMapper extends BaseMapperX<ViolateMgmtDO> {

    default PageResult<ViolateMgmtDO> selectPage(ViolateMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ViolateMgmtDO>()
                .eqIfPresent(ViolateMgmtDO::getStudentId, reqVO.getStudentId())
                .eqIfPresent(ViolateMgmtDO::getViolateType, reqVO.getViolateType())
                .eqIfPresent(ViolateMgmtDO::getPunishType, reqVO.getPunishType())
                .betweenIfPresent(ViolateMgmtDO::getViolateTime, reqVO.getViolateTime())
                .likeIfPresent(ViolateMgmtDO::getViolateReason, reqVO.getViolateReason())
                .likeIfPresent(ViolateMgmtDO::getAuditUser, reqVO.getAuditUser())
                .betweenIfPresent(ViolateMgmtDO::getAuditTime, reqVO.getAuditTime())
                .betweenIfPresent(ViolateMgmtDO::getPushTime, reqVO.getPushTime())
                .betweenIfPresent(ViolateMgmtDO::getWarnTime, reqVO.getWarnTime())
                .eqIfPresent(ViolateMgmtDO::getStatus, reqVO.getStatus())
                .likeIfPresent(ViolateMgmtDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ViolateMgmtDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(ViolateMgmtDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(ViolateMgmtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ViolateMgmtDO::getId));
    }

    default PageResult<ViolateMgmtPageRespVO> selectJoinPage(ViolateMgmtPageReqVO reqVO) {
        // 1. 构建分页对象
        Page<ViolateMgmtPageRespVO> page = new Page<>(
                Objects.requireNonNullElse(reqVO.getPageNo(), 1),
                Objects.requireNonNullElse(reqVO.getPageSize(), 10)
        );

        // 2. 构建 MPJ 联表 Wrapper
        MPJLambdaWrapper<ViolateMgmtDO> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(ViolateMgmtDO.class);
        wrapper.selectAs(StudentInfoDO::getName, ClubMgmtRespVO::getStudentName);
        wrapper.leftJoin(StudentInfoDO.class, StudentInfoDO::getId, ViolateMgmtDO::getStudentId);

        if (null != reqVO.getStudentId()) {
            wrapper.eq(ViolateMgmtDO::getStudentId, reqVO.getStudentId());
        }
        if (StringUtils.isNotBlank(reqVO.getViolateType())) {
            wrapper.eq(ViolateMgmtDO::getViolateType, reqVO.getViolateType());
        }
        if (StringUtils.isNotBlank(reqVO.getPunishType())) {
            wrapper.eq(ViolateMgmtDO::getPunishType, reqVO.getPunishType());
        }
        if (null != reqVO.getViolateTime()) {
            wrapper.between(ViolateMgmtDO::getViolateTime, reqVO.getViolateTime()[0], reqVO.getViolateTime()[1]);
        }
        if (StringUtils.isNotBlank(reqVO.getViolateReason())) {
            wrapper.like(ViolateMgmtDO::getViolateReason, reqVO.getViolateReason());
        }
        if (StringUtils.isNotBlank(reqVO.getAuditUser())) {
            wrapper.like(ViolateMgmtDO::getAuditUser, reqVO.getAuditUser());
        }
        if (null != reqVO.getAuditTime()) {
            wrapper.between(ViolateMgmtDO::getAuditTime, reqVO.getAuditTime()[0], reqVO.getAuditTime()[1]);
        }
        if (StringUtils.isNotBlank(reqVO.getStatus())) {
            wrapper.eq(ViolateMgmtDO::getStatus, reqVO.getStatus());
        }
        if (StringUtils.isNotBlank(reqVO.getRemark())) {
            wrapper.eq(ViolateMgmtDO::getRemark, reqVO.getRemark());
        }

        wrapper.orderByDesc(ViolateMgmtDO::getId);// ===== 主表字段 =====

        // 3. 执行联表分页查询
        IPage<ViolateMgmtPageRespVO> resultPage = selectJoinPage(page, ViolateMgmtPageRespVO.class, wrapper);

        // 4. 返回结果
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    Integer auditViolateMgmtListByIds(@Param("ids") List<Long> ids, @Param("status") String status, @Param("userId") Long userId);

    Long selectTotalCount(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime,
                          @Param("status") String status, @Param("violateType") String violateType);

    Long selectHighRiskStudentCount(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    List<JSONObject> selectViolateTypeCount(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    List<JSONObject> selectViolateClassCount(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    Integer selectTotalViolate(LocalDateTime startTime, LocalDateTime endTime, String className, String grade);

    Integer selectUnhandledViolate(LocalDateTime startTime, LocalDateTime endTime, String className, String grade, String status);

    List<JSONObject> selectTotalCountByDate(LocalDateTime startTime, LocalDateTime endTime, String cycle);
}