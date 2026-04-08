package cn.iocoder.yudao.module.studentmgmt.service.studentinfo;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studentinfo.StudentInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.studentinfo.StudentInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

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

    @Override
    public StudentInfoDashboardVO getStudentInfoDashboard() {
        StudentInfoDashboardVO vo = new StudentInfoDashboardVO();

        // 1. 卡片数据
        vo.setTotalStudentCount(studentInfoMapper.selectTotalStudentCount());
        vo.setInSchoolCount(studentInfoMapper.selectInSchoolCount());
        vo.setSuspendCount(studentInfoMapper.selectSuspendCount());
        vo.setDropOutCount(studentInfoMapper.selectDropOutCount());
        vo.setTransferCount(studentInfoMapper.selectTransferCount());

        vo.setNormalStudentCount(studentInfoMapper.selectNormalStudentCount());
        vo.setSpecialStudentCount(studentInfoMapper.selectSpecialStudentCount());
        vo.setTransferStudentCount (studentInfoMapper.selectTransferStudentCount ());

        // 2. 圆环图数据
//        vo.setOperationStatusDistribution(studentInfoMapper.selectOperationStatusDistribution());
//        vo.setAreaDistribution(studentInfoMapper.selectAreaDistribution());

        // 3. 柱状图数据
//        vo.setEnvironmentComplianceRateByPark(studentInfoMapper.selectEnvironmentComplianceRateByPark());

        return vo;
    }

}