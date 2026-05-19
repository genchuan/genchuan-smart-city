package cn.iocoder.yudao.module.studentmgmt.service.studentinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studentinfo.StudentInfoDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.studentinfo.StudentInfoMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.StudentInfoStatusEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.StudentInfoTypeEnum;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.STUDENT_INFO_IS_EXISTS;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.STUDENT_INFO_NOT_EXISTS;
import static cn.iocoder.yudao.module.studentmgmt.enums.LogRecordConstants.*;

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
    @LogRecord(type = STUDENT_INFO_TYPE, subType = STUDENT_INFO_CREATE_SUB_TYPE, bizNo = "{{#studentInfo.id}}",
            success = STUDENT_INFO_CREATE_SUCCESS)
    public Long createStudentInfo(StudentInfoSaveReqVO createReqVO) {
        // 判断某个字段的值是否已经存在了该学生
        List<StudentInfoDO> list = studentInfoMapper.isExist(createReqVO);
        if (null != list && list.size() > 0) {
            throw exception(STUDENT_INFO_IS_EXISTS);
        }

        // 插入
        StudentInfoDO studentInfo = BeanUtils.toBean(createReqVO, StudentInfoDO.class);
        studentInfoMapper.insert(studentInfo);

        // 记录操作日志上下文
        LogRecordContext.putVariable("studentInfo", studentInfo);
        // 返回
        return studentInfo.getId();
    }

    @Override
    @LogRecord(type = STUDENT_INFO_TYPE, subType = STUDENT_INFO_UPDATE_SUB_TYPE, bizNo = "{{#updateReqVO.id}}",
            success = STUDENT_INFO_UPDATE_SUCCESS)
    public void updateStudentInfo(StudentInfoSaveReqVO updateReqVO) {
        // 校验存在
        StudentInfoDO studentInfoDO = validateStudentInfoExists(updateReqVO.getId());
        // 更新
        StudentInfoDO updateObj = BeanUtils.toBean(updateReqVO, StudentInfoDO.class);
        studentInfoMapper.updateById(updateObj);

        // 3. 记录操作日志上下文
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, BeanUtils.toBean(studentInfoDO, StudentInfoSaveReqVO.class));
        LogRecordContext.putVariable("studentInfo", studentInfoDO);
    }


    @Override
    @LogRecord(type = STUDENT_INFO_TYPE, subType = STUDENT_INFO_DELETE_SUB_TYPE, bizNo = "{{#id}}",
            success = STUDENT_INFO_DELETE_SUCCESS)
    public void deleteStudentInfo(Long id) {
        // 校验存在
        StudentInfoDO studentInfoDO = validateStudentInfoExists(id);
        // 删除
        studentInfoMapper.deleteById(id);

        // 记录操作日志上下文
        LogRecordContext.putVariable("studentName", studentInfoDO.getName());
    }

    @Override
    @LogRecord(type = STUDENT_INFO_TYPE, subType = STUDENT_INFO_DELETE_SUB_TYPE, bizNo = "{{#id}}",
            success = STUDENT_INFO_DELETE_SUCCESS)
    public void deleteStudentInfoListByIds(List<Long> ids) {
        List<StudentInfoDO> studentInfoDOS = studentInfoMapper.selectByIds(ids);
        // 删除
        int i = studentInfoMapper.deleteByIds(ids);
        // 逐条追加变更日志
        String studentName = studentInfoDOS.stream().map(StudentInfoDO::getName).collect(Collectors.joining(","));
        LogRecordContext.putVariable("id", ids.get(0));
        LogRecordContext.putVariable("studentName", studentName);
    }


    private StudentInfoDO validateStudentInfoExists(Long id) {
        StudentInfoDO studentInfoDO = studentInfoMapper.selectById(id);
        if (studentInfoDO == null) {
            throw exception(STUDENT_INFO_NOT_EXISTS);
        }
        return studentInfoDO;
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
        vo.setInSchoolCount(studentInfoMapper.selectTotalStudentCount(grade, major, StudentInfoStatusEnum.STUDENT_INFO_STATUS_1.getStatus(), null));
        vo.setSuspendCount(studentInfoMapper.selectTotalStudentCount(grade, major, StudentInfoStatusEnum.STUDENT_INFO_STATUS_2.getStatus(), null));
        vo.setDropOutCount(studentInfoMapper.selectTotalStudentCount(grade, major, StudentInfoStatusEnum.STUDENT_INFO_STATUS_3.getStatus(), null));
        vo.setTransferCount(studentInfoMapper.selectTotalStudentCount(grade, major, StudentInfoStatusEnum.STUDENT_INFO_STATUS_4.getStatus(), null));
        vo.setNormalStudentCount(studentInfoMapper.selectTotalStudentCount(grade, major, "", StudentInfoTypeEnum.STATUS_1.getStatus()));
        vo.setSpecialStudentCount(studentInfoMapper.selectTotalStudentCount(grade, major, "",StudentInfoTypeEnum.STATUS_2.getStatus()));
        vo.setTransferStudentCount(studentInfoMapper.selectTotalStudentCount(grade, major,"" ,StudentInfoTypeEnum.STATUS_3.getStatus()));

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
    public List<StudentInfoDistributionCountRespVO> getDistributionCount(StudentInfoDistributionCountReqVO reqVO) {
        String dimension = reqVO.getDimension();
        return studentInfoMapper.selectDistributionCount(dimension);
    }

    @Override
    public List<StudentInfoCoreIndexRespVO> getCoreIndex(StudentInfoCoreIndexReqVO reqVO) {
        LocalDateTime startTime = reqVO.getStartTime();
        LocalDateTime endTime = reqVO.getEndTime();
        return studentInfoMapper.getCoreIndex(startTime, endTime, StudentInfoStatusEnum.STUDENT_INFO_STATUS_4.getStatus());
    }

    @Override
    public List<StudentInfoBaseVO> getAll() {
        List<StudentInfoBaseVO> studentInfoDOS = studentInfoMapper.selectBaseInfoList();
        return studentInfoDOS;
    }

}