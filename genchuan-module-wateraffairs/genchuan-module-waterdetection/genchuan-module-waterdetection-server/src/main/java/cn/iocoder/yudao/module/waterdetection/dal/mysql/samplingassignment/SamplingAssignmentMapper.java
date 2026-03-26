package cn.iocoder.yudao.module.waterdetection.dal.mysql.samplingassignment;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.samplingassignment.SamplingAssignmentDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.samplingassignment.vo.*;

/**
 * 采样人员分配 Mapper
 *
 * @author zcq
 */
@Mapper
public interface SamplingAssignmentMapper extends BaseMapperX<SamplingAssignmentDO> {

    default PageResult<SamplingAssignmentDO> selectPage(SamplingAssignmentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SamplingAssignmentDO>()
                .eqIfPresent(SamplingAssignmentDO::getPlanCode, reqVO.getPlanCode())
                .eqIfPresent(SamplingAssignmentDO::getPointList, reqVO.getPointList())
                .eqIfPresent(SamplingAssignmentDO::getResponsiblePerson, reqVO.getResponsiblePerson())
                .betweenIfPresent(SamplingAssignmentDO::getAssignTime, reqVO.getAssignTime())
                .eqIfPresent(SamplingAssignmentDO::getDeadline, reqVO.getDeadline())
                .eqIfPresent(SamplingAssignmentDO::getContactInfo, reqVO.getContactInfo())
                .betweenIfPresent(SamplingAssignmentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SamplingAssignmentDO::getId));
    }

}