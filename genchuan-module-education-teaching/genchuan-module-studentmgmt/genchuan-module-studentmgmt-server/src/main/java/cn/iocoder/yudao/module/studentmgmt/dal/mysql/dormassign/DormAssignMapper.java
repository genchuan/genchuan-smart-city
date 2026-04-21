package cn.iocoder.yudao.module.studentmgmt.dal.mysql.dormassign;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dormassign.DormAssignDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.dormassign.vo.*;

/**
 * 宿舍分配 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface DormAssignMapper extends BaseMapperX<DormAssignDO> {

    default PageResult<DormAssignDO> selectPage(DormAssignPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DormAssignDO>()
                .eqIfPresent(DormAssignDO::getStudentId, reqVO.getStudentId())
                .eqIfPresent(DormAssignDO::getDormNum, reqVO.getDormNum())
                .eqIfPresent(DormAssignDO::getBedId, reqVO.getBedId())
                .eqIfPresent(DormAssignDO::getRuleContent, reqVO.getRuleContent())
                .betweenIfPresent(DormAssignDO::getAssignTime, reqVO.getAssignTime())
                .betweenIfPresent(DormAssignDO::getAdjustTime, reqVO.getAdjustTime())
                .eqIfPresent(DormAssignDO::getFinishRate, reqVO.getFinishRate())
                .eqIfPresent(DormAssignDO::getStatus, reqVO.getStatus())
                .eqIfPresent(DormAssignDO::getRemark, reqVO.getRemark())
                .eqIfPresent(DormAssignDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(DormAssignDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(DormAssignDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DormAssignDO::getId));
    }

}