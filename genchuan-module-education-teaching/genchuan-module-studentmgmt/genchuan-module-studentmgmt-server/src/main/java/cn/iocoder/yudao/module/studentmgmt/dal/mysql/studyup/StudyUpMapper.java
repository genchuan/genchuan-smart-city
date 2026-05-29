package cn.iocoder.yudao.module.studentmgmt.dal.mysql.studyup;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartCountVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.clubmgmt.vo.ClubMgmtRespVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.studyup.vo.StudyUpChartRespVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.studyup.vo.StudyUpPageReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.studyup.vo.StudyUpQueryRespVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.studyup.vo.StudyUpRespVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.violatemgmt.vo.ViolateMgmtPageReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.violatemgmt.vo.ViolateMgmtPageRespVO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studentinfo.StudentInfoDO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studyup.StudyUpDO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.violatemgmt.ViolateMgmtDO;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 升学管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface StudyUpMapper extends BaseMapperX<StudyUpDO> {

    default PageResult<StudyUpDO> selectPage(StudyUpPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StudyUpDO>()
                .eqIfPresent(StudyUpDO::getStudentId, reqVO.getStudentId())
                .likeIfPresent(StudyUpDO::getSchoolName, reqVO.getSchoolName())
                .eqIfPresent(StudyUpDO::getSchoolType, reqVO.getSchoolType())
                .eqIfPresent(StudyUpDO::getMajor, reqVO.getMajor())
                .eqIfPresent(StudyUpDO::getPlanContent, reqVO.getPlanContent())
                .betweenIfPresent(StudyUpDO::getPlanTime, reqVO.getPlanTime())
                .betweenIfPresent(StudyUpDO::getRecordTime, reqVO.getRecordTime())
                .eqIfPresent(StudyUpDO::getStatus, reqVO.getStatus())
                .eqIfPresent(StudyUpDO::getRemark, reqVO.getRemark())
                .eqIfPresent(StudyUpDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(StudyUpDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(StudyUpDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(StudyUpDO::getId));
    }

    default PageResult<StudyUpRespVO> selectJoinPage(StudyUpPageReqVO reqVO) {
        // 1. 构建分页对象
        Page<StudyUpRespVO> page = new Page<>(
                Objects.requireNonNullElse(reqVO.getPageNo(), 1),
                Objects.requireNonNullElse(reqVO.getPageSize(), 10)
        );

        // 2. 构建 MPJ 联表 Wrapper
        MPJLambdaWrapper<StudyUpDO> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(StudyUpDO.class);
        wrapper.selectAs(StudentInfoDO::getName, ClubMgmtRespVO::getStudentName);
        wrapper.leftJoin(StudentInfoDO.class, StudentInfoDO::getId, StudyUpDO::getStudentId);

        if (null != reqVO.getStudentId()) {
            wrapper.eq(StudyUpDO::getStudentId, reqVO.getStudentId());
        }
        if (StringUtils.isNotBlank(reqVO.getSchoolName())) {
            wrapper.like(StudyUpDO::getSchoolName, reqVO.getSchoolName());
        }
        if (StringUtils.isNotBlank(reqVO.getSchoolType())) {
            wrapper.eq(StudyUpDO::getSchoolType, reqVO.getSchoolType());
        }
        if (StringUtils.isNotBlank(reqVO.getMajor())) {
            wrapper.like(StudyUpDO::getMajor, reqVO.getMajor());
        }

        if (StringUtils.isNotBlank(reqVO.getStatus())) {
            wrapper.eq(StudyUpDO::getStatus, reqVO.getStatus());
        }
        if (StringUtils.isNotBlank(reqVO.getRemark())) {
            wrapper.eq(StudyUpDO::getRemark, reqVO.getRemark());
        }

        wrapper.orderByDesc(StudyUpDO::getId);// ===== 主表字段 =====

        // 3. 执行联表分页查询
        IPage<StudyUpRespVO> resultPage = selectJoinPage(page, StudyUpRespVO.class, wrapper);

        // 4. 返回结果
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }
    StudyUpQueryRespVO selectByStudentId(@NotNull(message = "学生 ID不能为空") Long studentId);

    StudyUpChartRespVO selectTotalCount(LocalDateTime startTime, LocalDateTime endTime, String pending_plan, String planned);

    List<JSONObject> selectSchoolTopCount(LocalDateTime startTime, LocalDateTime endTime);

    List<ChartCountVO> selectIntentionDistributionList(LocalDateTime startTime, LocalDateTime endTime);

    List<ChartCountVO> selectSchoolTypeDistributionList(LocalDateTime startTime, LocalDateTime endTime);
}