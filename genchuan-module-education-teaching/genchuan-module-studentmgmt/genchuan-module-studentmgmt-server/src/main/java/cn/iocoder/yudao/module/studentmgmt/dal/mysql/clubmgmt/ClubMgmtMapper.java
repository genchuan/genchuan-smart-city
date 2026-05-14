package cn.iocoder.yudao.module.studentmgmt.dal.mysql.clubmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.bedmgmt.vo.BedMgmtRespVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.clubmgmt.vo.ClubMgmtPageReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.clubmgmt.vo.ClubMgmtRespVO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.clubmgmt.ClubMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studentinfo.StudentInfoDO;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 社团管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ClubMgmtMapper extends BaseMapperX<ClubMgmtDO> {

    default PageResult<ClubMgmtDO> selectPage(ClubMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ClubMgmtDO>()
                .likeIfPresent(ClubMgmtDO::getClubName, reqVO.getClubName())
                .eqIfPresent(ClubMgmtDO::getClubType, reqVO.getClubType())
                .eqIfPresent(ClubMgmtDO::getStudentId, reqVO.getStudentId())
                .betweenIfPresent(ClubMgmtDO::getApplyTime, reqVO.getApplyTime())
                .likeIfPresent(ClubMgmtDO::getAuditUser, reqVO.getAuditUser())
                .betweenIfPresent(ClubMgmtDO::getAuditTime, reqVO.getAuditTime())
                .betweenIfPresent(ClubMgmtDO::getArchiveTime, reqVO.getArchiveTime())
                .eqIfPresent(ClubMgmtDO::getVenueApplyStatus, reqVO.getVenueApplyStatus())
                .eqIfPresent(ClubMgmtDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ClubMgmtDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ClubMgmtDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(ClubMgmtDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(ClubMgmtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ClubMgmtDO::getId));
    }


    default PageResult<ClubMgmtRespVO> selectJoinPage(ClubMgmtPageReqVO reqVO) {
        // 1. 构建分页对象
        Page<ClubMgmtRespVO> page = new Page<>(
                Objects.requireNonNullElse(reqVO.getPageNo(), 1),
                Objects.requireNonNullElse(reqVO.getPageSize(), 10)
        );

        // 2. 构建 MPJ 联表 Wrapper
        MPJLambdaWrapper<ClubMgmtDO> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(ClubMgmtDO.class);
        wrapper.selectAs(StudentInfoDO::getName, ClubMgmtRespVO::getStudentName);
        wrapper.leftJoin(StudentInfoDO.class, StudentInfoDO::getId, ClubMgmtDO::getStudentId);

        if (null != reqVO.getStudentId()) {
            wrapper.eq(ClubMgmtDO::getStudentId, reqVO.getStudentId());
        }
        if (StringUtils.isNotBlank(reqVO.getClubName())) {
            wrapper.like(ClubMgmtDO::getClubName, reqVO.getClubName());
        }
        if (StringUtils.isNotBlank(reqVO.getClubType())) {
            wrapper.eq(ClubMgmtDO::getClubType, reqVO.getClubType());
        }
        if (StringUtils.isNotBlank(reqVO.getVenueApplyStatus())) {
            wrapper.eq(ClubMgmtDO::getVenueApplyStatus, reqVO.getVenueApplyStatus());
        }
        if (null != reqVO.getApplyTime()) {
            wrapper.between(ClubMgmtDO::getApplyTime, reqVO.getApplyTime()[0], reqVO.getApplyTime()[1]);
        }


        if (StringUtils.isNotBlank(reqVO.getStatus())) {
            wrapper.eq(ClubMgmtDO::getStatus, reqVO.getStatus());
        }
        if (StringUtils.isNotBlank(reqVO.getAuditUser())) {
            wrapper.like(ClubMgmtDO::getAuditUser, reqVO.getClubType());
        }
        wrapper.orderByDesc(ClubMgmtDO::getId);// ===== 主表字段 =====

        // 3. 执行联表分页查询
        IPage<ClubMgmtRespVO> resultPage = selectJoinPage(page, ClubMgmtRespVO.class, wrapper);

        // 4. 返回结果
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }


    Long selectTotalCount(LocalDateTime startTime, LocalDateTime endTime);

    Long selectTotalMemberCount(LocalDateTime startTime, LocalDateTime endTime);

    Long selectPendingAuditCount(LocalDateTime startTime, LocalDateTime endTime, String status);

    Long selectVenueApplyCount(LocalDateTime startTime, LocalDateTime endTime);

    List<JSONObject> selectClubTypeDistribution(LocalDateTime startTime, LocalDateTime endTime);

    List<JSONObject> selectMonthlyApplyTrend(LocalDateTime startTime, LocalDateTime endTime);

    List<JSONObject> selectClubStatistics(LocalDateTime startTime, LocalDateTime endTime);

    List<JSONObject> selectTypeMemberDistribution(LocalDateTime startTime, LocalDateTime endTime);
}