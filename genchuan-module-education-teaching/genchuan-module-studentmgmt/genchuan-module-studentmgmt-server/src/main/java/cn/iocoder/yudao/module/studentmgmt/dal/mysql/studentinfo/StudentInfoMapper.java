package cn.iocoder.yudao.module.studentmgmt.dal.mysql.studentinfo;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studentinfo.StudentInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo.vo.*;
import org.apache.ibatis.annotations.Param;
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
                .likeIfPresent(StudentInfoDO::getStudentNo, reqVO.getStudentNo())
                .likeIfPresent(StudentInfoDO::getName, reqVO.getName())
                .likeIfPresent(StudentInfoDO::getIdCard, reqVO.getIdCard())
                .likeIfPresent(StudentInfoDO::getPhoto, reqVO.getPhoto())
                .eqIfPresent(StudentInfoDO::getEducationLevel, reqVO.getEducationLevel())
                .eqIfPresent(StudentInfoDO::getStudyForm, reqVO.getStudyForm())
                .likeIfPresent(StudentInfoDO::getMajor, reqVO.getMajor())
                .likeIfPresent(StudentInfoDO::getClassName, reqVO.getClassName())
                .eqIfPresent(StudentInfoDO::getStudentType, reqVO.getStudentType())
                .eqIfPresent(StudentInfoDO::getStatus, reqVO.getStatus())
                .likeIfPresent(StudentInfoDO::getPhone, reqVO.getPhone())
                .likeIfPresent(StudentInfoDO::getParentPhone, reqVO.getParentPhone())
                .eqIfPresent(StudentInfoDO::getRemark, reqVO.getRemark())
                .eqIfPresent(StudentInfoDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(StudentInfoDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(StudentInfoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(StudentInfoDO::getId));
    }


    default List<StudentInfoDO> isExist(StudentInfoSaveReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<StudentInfoDO>()
                .likeIfPresent(StudentInfoDO::getStudentNo, reqVO.getStudentNo())
                .likeIfPresent(StudentInfoDO::getName, reqVO.getName())
                .likeIfPresent(StudentInfoDO::getIdCard, reqVO.getIdCard())
                .likeIfPresent(StudentInfoDO::getPhone, reqVO.getPhone())
                .orderByDesc(StudentInfoDO::getId));
    }

    // ========== 卡片数据 ==========

    /**
     * 获取学生信息分布看板
     *
     * @param grade       年级
     * @param major       专业
     * @param status      状态
     * @param studentType 学生类型
     * @return
     */
    Long selectTotalStudentCount(@Param("grade") String grade, @Param("major") String major,
                                 @Param("status") String status, @Param("studentType") String studentType);

    /**
     * 按年级 / 专业 / 班级分布统计
     *
     * @param dimension 维度名称，如年级 / 专业 / 班级名称
     * @return
     */
    List<StudentInfoDistributionCountRespVO> selectDistributionCount(@Param("dimension") String dimension);

    /**
     * 核心指标统计
     *
     * @param startTime
     * @param endTime
     * @return
     */
    List<StudentInfoCoreIndexRespVO> getCoreIndex(@Param("startTime") LocalDateTime startTime,
                                            @Param("endTime") LocalDateTime endTime);

    List<StudentInfoBaseVO> selectBaseInfoList();

}