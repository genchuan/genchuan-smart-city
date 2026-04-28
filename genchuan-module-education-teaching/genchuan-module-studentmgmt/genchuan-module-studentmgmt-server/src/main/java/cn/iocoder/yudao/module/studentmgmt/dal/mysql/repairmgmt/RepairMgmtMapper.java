package cn.iocoder.yudao.module.studentmgmt.dal.mysql.repairmgmt;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.repairmgmt.RepairMgmtDO;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.repairmgmt.vo.*;

/**
 * 报修管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface RepairMgmtMapper extends BaseMapperX<RepairMgmtDO> {

    default PageResult<RepairMgmtDO> selectPage(RepairMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RepairMgmtDO>()
                .eqIfPresent(RepairMgmtDO::getDormNum, reqVO.getDormNum())
                .eqIfPresent(RepairMgmtDO::getRepairType, reqVO.getRepairType())
                .betweenIfPresent(RepairMgmtDO::getApplyTime, reqVO.getApplyTime())
                .eqIfPresent(RepairMgmtDO::getDispatchUser, reqVO.getDispatchUser())
                .betweenIfPresent(RepairMgmtDO::getDispatchTime, reqVO.getDispatchTime())
                .eqIfPresent(RepairMgmtDO::getRepairUser, reqVO.getRepairUser())
                .eqIfPresent(RepairMgmtDO::getFeedbackContent, reqVO.getFeedbackContent())
                .betweenIfPresent(RepairMgmtDO::getFeedbackTime, reqVO.getFeedbackTime())
                .eqIfPresent(RepairMgmtDO::getCheckUser, reqVO.getCheckUser())
                .betweenIfPresent(RepairMgmtDO::getCheckTime, reqVO.getCheckTime())
                .eqIfPresent(RepairMgmtDO::getStatus, reqVO.getStatus())
                .eqIfPresent(RepairMgmtDO::getCheckStatus, reqVO.getCheckStatus())
                .eqIfPresent(RepairMgmtDO::getRemark, reqVO.getRemark())
                .eqIfPresent(RepairMgmtDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(RepairMgmtDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(RepairMgmtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RepairMgmtDO::getId));
    }

    RepairMgmtChartRespVO selectTotalCount(LocalDateTime startTime, LocalDateTime endTime, String dormBuilding,
                                           String pending, String repairing, String completed, String checkStatus);

    List<JSONObject> selectDailyTrend(LocalDateTime startTime, LocalDateTime endTime, String dormBuilding);

    List<JSONObject> selectTypeDistributionList(LocalDateTime startTime, LocalDateTime endTime, String dormBuilding);

    List<JSONObject> selectTypeStatisticsList(LocalDateTime startTime, LocalDateTime endTime,String completed);

    List<JSONObject> selectBuildingStatisticsList(LocalDateTime startTime, LocalDateTime endTime,String completed);
}