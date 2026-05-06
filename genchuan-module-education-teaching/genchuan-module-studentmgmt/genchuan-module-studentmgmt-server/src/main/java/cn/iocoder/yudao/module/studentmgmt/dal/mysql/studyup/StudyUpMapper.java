package cn.iocoder.yudao.module.studentmgmt.dal.mysql.studyup;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartCountVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.studyup.vo.StudyUpChartRespVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.studyup.vo.StudyUpPageReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.studyup.vo.StudyUpQueryRespVO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studyup.StudyUpDO;
import com.alibaba.fastjson.JSONObject;
import jakarta.validation.constraints.NotNull;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

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

    StudyUpQueryRespVO selectByStudentId(@NotNull(message = "学生 ID不能为空") Long studentId);

    StudyUpChartRespVO selectTotalCount(LocalDateTime startTime, LocalDateTime endTime, String pending_plan, String planned);

    List<JSONObject> selectSchoolTopCount(LocalDateTime startTime, LocalDateTime endTime);

    List<ChartCountVO> selectIntentionDistributionList(LocalDateTime startTime, LocalDateTime endTime);

    List<ChartCountVO> selectSchoolTypeDistributionList(LocalDateTime startTime, LocalDateTime endTime);
}