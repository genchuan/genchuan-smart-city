package cn.iocoder.yudao.module.studentmgmt.dal.mysql.classassign;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartTrendVO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.classassign.ClassAssignDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.classassign.vo.*;

/**
 * 分班管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ClassAssignMapper extends BaseMapperX<ClassAssignDO> {

    default PageResult<ClassAssignDO> selectPage(ClassAssignPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ClassAssignDO>()
                .eqIfPresent(ClassAssignDO::getRuleContent, reqVO.getRuleContent())
                .eqIfPresent(ClassAssignDO::getStudentNum, reqVO.getStudentNum())
                .betweenIfPresent(ClassAssignDO::getAssignTime, reqVO.getAssignTime())
                .eqIfPresent(ClassAssignDO::getConfirmUser, reqVO.getConfirmUser())
                .betweenIfPresent(ClassAssignDO::getConfirmTime, reqVO.getConfirmTime())
                .eqIfPresent(ClassAssignDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ClassAssignDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ClassAssignDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(ClassAssignDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(ClassAssignDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ClassAssignDO::getId));
    }

    List<ChartTrendVO> select7dayTrendCount();

    ClassAssignChartRespVO selectTotalCount(LocalDateTime startTime, LocalDateTime endTime, String assigned, String unassigned);
}