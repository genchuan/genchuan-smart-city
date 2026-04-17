package cn.iocoder.yudao.module.studentmgmt.dal.mysql.aidwork;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.aidwork.AidWorkDO;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.aidwork.vo.*;

/**
 * 奖助勤贷 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface AidWorkMapper extends BaseMapperX<AidWorkDO> {

    default PageResult<AidWorkDO> selectPage(AidWorkPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AidWorkDO>()
                .eqIfPresent(AidWorkDO::getStudentId, reqVO.getStudentId())
                .eqIfPresent(AidWorkDO::getAidType, reqVO.getAidType())
                .betweenIfPresent(AidWorkDO::getApplyAmount, reqVO.getApplyAmount())
                .betweenIfPresent(AidWorkDO::getApplyTime, reqVO.getApplyTime())
                .likeIfPresent(AidWorkDO::getAuditUser, reqVO.getAuditUser())
                .betweenIfPresent(AidWorkDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(AidWorkDO::getProcessStatus, reqVO.getProcessStatus())
                .eqIfPresent(AidWorkDO::getStatus, reqVO.getStatus())
                .likeIfPresent(AidWorkDO::getRemark, reqVO.getRemark())
                .eqIfPresent(AidWorkDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(AidWorkDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(AidWorkDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AidWorkDO::getId));
    }

    JSONObject selectTotalCount(LocalDateTime startTime, LocalDateTime endTime, String status);

    List<Map<String, Long>>selectTypeCount(LocalDateTime startTime, LocalDateTime endTime);

    List<Map<String, Long>> selectStatusCount(LocalDateTime startTime, LocalDateTime endTime);

    List<JSONObject> selectStatusCountByType(LocalDateTime startTime, LocalDateTime endTime,String type);
}