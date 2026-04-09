package cn.iocoder.yudao.module.studentmgmt.service.studentinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studentinfo.StudentInfoDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.studentinfo.StudentInfoMapper;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.STUDENT_INFO_NOT_EXISTS;

/**
 * 学生信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class StudentInfoServiceImpl implements StudentInfoService {

    @Resource
    private StudentInfoMapper studentInfoMapper;

    @Override
    public Long createStudentInfo(StudentInfoSaveReqVO createReqVO) {
        // 插入
        StudentInfoDO studentInfo = BeanUtils.toBean(createReqVO, StudentInfoDO.class);
        studentInfoMapper.insert(studentInfo);

        // 返回
        return studentInfo.getId();
    }

    @Override
    public void updateStudentInfo(StudentInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateStudentInfoExists(updateReqVO.getId());
        // 更新
        StudentInfoDO updateObj = BeanUtils.toBean(updateReqVO, StudentInfoDO.class);
        studentInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteStudentInfo(Long id) {
        // 校验存在
        validateStudentInfoExists(id);
        // 删除
        studentInfoMapper.deleteById(id);
    }

    @Override
        public void deleteStudentInfoListByIds(List<Long> ids) {
        // 删除
        studentInfoMapper.deleteByIds(ids);
        }


    private void validateStudentInfoExists(Long id) {
        if (studentInfoMapper.selectById(id) == null) {
            throw exception(STUDENT_INFO_NOT_EXISTS);
        }
    }

    @Override
    public StudentInfoDO getStudentInfo(Long id) {
        return studentInfoMapper.selectById(id);
    }

    @Override
    public PageResult<StudentInfoDO> getStudentInfoPage(StudentInfoPageReqVO pageReqVO) {
        return studentInfoMapper.selectPage(pageReqVO);
    }

    /**
     * 学生信息分布看板
     * @param reqVO
     * @return
     */
    @Override
    public StudentInfoDashboardVO getStudentInfoDashboard(@Valid StudentInfoChartReqVO reqVO) {
        StudentInfoDashboardVO vo = new StudentInfoDashboardVO();

        String grade = reqVO.getGrade();
        String major = reqVO.getMajor();

        // 1. 卡片数据
        vo.setTotalStudentCount(studentInfoMapper.selectTotalStudentCount(grade, major, "", ""));
        vo.setInSchoolCount(studentInfoMapper.selectTotalStudentCount(grade, major, "在籍", ""));
        vo.setSuspendCount(studentInfoMapper.selectTotalStudentCount(grade, major, "休学", ""));
        vo.setDropOutCount(studentInfoMapper.selectTotalStudentCount(grade, major, "退学", ""));
        vo.setTransferCount(studentInfoMapper.selectTotalStudentCount(grade, major, "异动", ""));
        vo.setNormalStudentCount(studentInfoMapper.selectTotalStudentCount(grade, major, "","普通生"));
        vo.setSpecialStudentCount(studentInfoMapper.selectTotalStudentCount(grade, major, "","特长生"));
        vo.setTransferStudentCount(studentInfoMapper.selectTotalStudentCount(grade, major,"" ,"转学生"));

        // 2. 圆环图数据
//        vo.setOperationStatusDistribution(studentInfoMapper.selectOperationStatusDistribution());
//        vo.setAreaDistribution(studentInfoMapper.selectAreaDistribution());

        // 3. 柱状图数据
//        vo.setEnvironmentComplianceRateByPark(studentInfoMapper.selectEnvironmentComplianceRateByPark());

        return vo;
    }

    /**
     * 按年级 / 专业 / 班级分布统计
     * @param reqVO
     * @return
     */
    @Override
    public StudentInfoDistributionCountRespVO getDistributionCount(StudentInfoDistributionCountReqVO reqVO) {
        String dimension = reqVO.getDimension();
        return studentInfoMapper.selectDistributionCount(dimension);
    }

    @Override
    public StudentInfoCoreIndexRespVO getCoreIndex(StudentInfoCoreIndexReqVO reqVO) {
        LocalDateTime startTime = reqVO.getStartTime();
        LocalDateTime endTime = reqVO.getEndTime();
        return studentInfoMapper.getCoreIndex(startTime, endTime);
    }

}