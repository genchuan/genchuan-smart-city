package cn.iocoder.yudao.module.studentmgmt.dal.mysql.dormcheck;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.clubmgmt.vo.ClubMgmtPageReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.clubmgmt.vo.ClubMgmtRespVO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.clubmgmt.ClubMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dormcheck.DormCheckDO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studentinfo.StudentInfoDO;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.commons.lang3.StringUtils;
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

    default PageResult<DormCheckRespVO> selectJoinPage(DormCheckPageReqVO reqVO) {
        // 1. 构建分页对象
        Page<DormCheckRespVO> page = new Page<>(
                Objects.requireNonNullElse(reqVO.getPageNo(), 1),
                Objects.requireNonNullElse(reqVO.getPageSize(), 10)
        );

        // 2. 构建 MPJ 联表 Wrapper
        MPJLambdaWrapper<DormCheckDO> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(DormCheckDO.class);
        wrapper.selectAs(StudentInfoDO::getName, ClubMgmtRespVO::getStudentName);
        wrapper.leftJoin(StudentInfoDO.class, StudentInfoDO::getId, DormCheckDO::getStudentId);

        if (null != reqVO.getStudentId()) {
            wrapper.eq(DormCheckDO::getStudentId, reqVO.getStudentId());
        }
        if (null != reqVO.getCheckTime()) {
            wrapper.between(DormCheckDO::getCheckTime, reqVO.getCheckTime()[0], reqVO.getCheckTime()[1]);
        }
        if (StringUtils.isNotBlank(reqVO.getAbnormalType())) {
            wrapper.eq(DormCheckDO::getAbnormalType, reqVO.getAbnormalType());
        }
        if (StringUtils.isNotBlank(reqVO.getCheckStatus())) {
            wrapper.eq(DormCheckDO::getCheckStatus, reqVO.getCheckStatus());
        }
        if (StringUtils.isNotBlank(reqVO.getStatus())) {
            wrapper.eq(DormCheckDO::getStatus, reqVO.getStatus());
        }
        wrapper.orderByDesc(DormCheckDO::getId);// ===== 主表字段 =====

        // 3. 执行联表分页查询
        IPage<DormCheckRespVO> resultPage = selectJoinPage(page, DormCheckRespVO.class, wrapper);

        // 4. 返回结果
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    Integer selectTotalCount(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime,
                             @Param("status") String status, @Param("checkStatus") String checkStatus, @Param("abnormalType") String abnormalType);

    Integer selectAbnormalCount(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime, @Param("checkStatus") String checkStatus);

    Integer selectAbnormalCountByClassName(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime, @Param("checkStatus") String checkStatus, @Param("className") String className);

    DormCheckChartRespVO selectTotalCheckCount(LocalDate checkTime, String status);

    List<JSONObject> getAbnormalStatsList(LocalDate checkTime);

    JSONObject getCoreIndex(String className, LocalDate checkTime);

    DormCheckDO selectByStudentIdAndCheckTime(Long studentId, LocalDateTime checkTime);
}