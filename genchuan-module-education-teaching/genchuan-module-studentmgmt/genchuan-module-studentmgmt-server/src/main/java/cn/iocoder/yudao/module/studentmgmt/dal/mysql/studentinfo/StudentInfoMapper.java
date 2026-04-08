package cn.iocoder.yudao.module.studentmgmt.dal.mysql.studentinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studentinfo.StudentInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo.vo.*;
import org.apache.ibatis.annotations.Select;

/**
 * 学生信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface StudentInfoMapper extends BaseMapperX<StudentInfoDO> {

    default PageResult<StudentInfoDO> selectPage(StudentInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StudentInfoDO>()
                .eqIfPresent(StudentInfoDO::getStudentNo, reqVO.getStudentNo())
                .likeIfPresent(StudentInfoDO::getName, reqVO.getName())
                .eqIfPresent(StudentInfoDO::getIdCard, reqVO.getIdCard())
                .eqIfPresent(StudentInfoDO::getPhoto, reqVO.getPhoto())
                .eqIfPresent(StudentInfoDO::getEducationLevel, reqVO.getEducationLevel())
                .eqIfPresent(StudentInfoDO::getStudyForm, reqVO.getStudyForm())
                .eqIfPresent(StudentInfoDO::getMajor, reqVO.getMajor())
                .likeIfPresent(StudentInfoDO::getClassName, reqVO.getClassName())
                .eqIfPresent(StudentInfoDO::getStudentType, reqVO.getStudentType())
                .eqIfPresent(StudentInfoDO::getStatus, reqVO.getStatus())
                .eqIfPresent(StudentInfoDO::getPhone, reqVO.getPhone())
                .eqIfPresent(StudentInfoDO::getParentPhone, reqVO.getParentPhone())
                .eqIfPresent(StudentInfoDO::getRemark, reqVO.getRemark())
                .eqIfPresent(StudentInfoDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(StudentInfoDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(StudentInfoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(StudentInfoDO::getId));
    }

    // ========== 卡片数据 ==========
    @Select("SELECT COUNT(*) FROM student_info WHERE deleted = 0")
    Long selectTotalStudentCount();

    @Select("SELECT COUNT(*) FROM student_info WHERE status = '在籍' ")
    Long selectInSchoolCount();

    @Select("SELECT COUNT(*) FROM student_info WHERE status = '休学' ")
    Long selectSuspendCount();

    @Select("SELECT COUNT(*) FROM student_info WHERE status = '退学' ")
    Long selectDropOutCount();

    @Select("SELECT COUNT(*) FROM student_info WHERE status = '异动' ")
    Long selectTransferCount();

    @Select("SELECT COUNT(*) FROM student_info WHERE student_type = '普通生' ")
    Long selectNormalStudentCount();

    @Select("SELECT COUNT(*) FROM student_info WHERE student_type = '特长生' ")
    Long selectSpecialStudentCount();

    @Select("SELECT COUNT(*) FROM student_info WHERE student_type = '转学生' ")
    Long selectTransferStudentCount();

}