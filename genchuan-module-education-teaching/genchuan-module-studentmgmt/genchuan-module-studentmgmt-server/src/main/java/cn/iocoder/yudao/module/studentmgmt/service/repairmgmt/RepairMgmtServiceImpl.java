package cn.iocoder.yudao.module.studentmgmt.service.repairmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.repairmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.repairmgmt.RepairMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.repairmgmt.RepairMgmtMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.RepairCheckStatusEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.RepairMgmtCheckStatusEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.RepairStatusEnum;
import com.alibaba.fastjson.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.REPAIR_MGMT_NOT_EXISTS;

/**
 * 报修管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RepairMgmtServiceImpl implements RepairMgmtService {

    @Resource
    private RepairMgmtMapper repairMgmtMapper;

    @Override
    public Long createRepairMgmt(RepairMgmtSaveReqVO createReqVO) {
        // 插入
        RepairMgmtDO repairMgmt = BeanUtils.toBean(createReqVO, RepairMgmtDO.class);
        repairMgmtMapper.insert(repairMgmt);

        // 返回
        return repairMgmt.getId();
    }

    @Override
    public void updateRepairMgmt(RepairMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateRepairMgmtExists(updateReqVO.getId());
        // 更新
        RepairMgmtDO updateObj = BeanUtils.toBean(updateReqVO, RepairMgmtDO.class);
        repairMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteRepairMgmt(Long id) {
        // 校验存在
        validateRepairMgmtExists(id);
        // 删除
        repairMgmtMapper.deleteById(id);
    }

    @Override
    public void deleteRepairMgmtListByIds(List<Long> ids) {
        // 删除
        repairMgmtMapper.deleteByIds(ids);
    }


    private RepairMgmtDO validateRepairMgmtExists(Long id) {
        RepairMgmtDO repairMgmt = repairMgmtMapper.selectById(id);
        if (repairMgmt == null) {
            throw exception(REPAIR_MGMT_NOT_EXISTS);
        }
        return repairMgmt;
    }

    @Override
    public RepairMgmtDO getRepairMgmt(Long id) {
        return repairMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<RepairMgmtDO> getRepairMgmtPage(RepairMgmtPageReqVO pageReqVO) {
        return repairMgmtMapper.selectPage(pageReqVO);
    }

    @Override
    public Boolean assign(RepairMgmtAssignReqVO reqVO) {
        int total = 0;
        for (Long id : reqVO.getIds()) {
            // 校验存在
            RepairMgmtDO repairMgmt = validateRepairMgmtExists(id);

            // 更新
            repairMgmt.setRepairUser(reqVO.getRepairUser());
            // 自动填充派单人、派单时间，更新申请状态为 “维修中”，
            // 获取当前用户
            String loginUserNickname = SecurityFrameworkUtils.getLoginUserNickname();
            repairMgmt.setDispatchUser(loginUserNickname);
            repairMgmt.setDispatchTime(LocalDateTime.now());
            repairMgmt.setStatus(RepairStatusEnum.REPAIRING.getStatus());
            repairMgmt.setRemark(reqVO.getRemark());
            int i = repairMgmtMapper.updateById(repairMgmt);
            total += i;
        }
        if (total > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean feedback(RepairMgmtFeedbackReqVO reqVO) {
        int total = 0;
        for (Long id : reqVO.getIds()) {
            // 校验存在
            RepairMgmtDO repairMgmt = validateRepairMgmtExists(id);

            // 更新
            // 获取当前用户
            String loginUserNickname = SecurityFrameworkUtils.getLoginUserNickname();
            repairMgmt.setDispatchUser(loginUserNickname);
            repairMgmt.setFeedbackTime(LocalDateTime.now());
            repairMgmt.setFeedbackContent(reqVO.getFeedbackContent());
            repairMgmt.setStatus(RepairStatusEnum.COMPLETED.getStatus());
            repairMgmt.setRemark(reqVO.getRemark());
            int i = repairMgmtMapper.updateById(repairMgmt);
            total += i;
        }
        if (total > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean accept(RepairMgmtAcceptReqVO reqVO) {
        // 校验存在
        RepairMgmtDO repairMgmt = validateRepairMgmtExists(reqVO.getId());

        // 更新
        // 获取当前用户
        String loginUserNickname = SecurityFrameworkUtils.getLoginUserNickname();
        //自动填充验收人、验收时间，更新验收状态为 “已验收”，
        repairMgmt.setCheckUser(loginUserNickname);
        repairMgmt.setCheckTime(LocalDateTime.now());
        repairMgmt.setStatus(RepairCheckStatusEnum.checked.getStatus());
        repairMgmt.setRemark(reqVO.getRemark());
        int i = repairMgmtMapper.updateById(repairMgmt);
        if (i > 0) {
            return true;
        }
        return false;
    }


    @Override
    public RepairMgmtChartRespVO chart(RepairMgmtChartReqVO reqVO) {
        RepairMgmtChartRespVO vo = new RepairMgmtChartRespVO();

        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }
        String dormBuilding = reqVO.getDormBuilding();
        // 1. 卡片数据
        vo = repairMgmtMapper.selectTotalCount(startTime, endTime, dormBuilding,
                RepairStatusEnum.PENDING_DISPATCH.getStatus(), RepairStatusEnum.REPAIRING.getStatus(),
                RepairStatusEnum.COMPLETED.getStatus(), RepairMgmtCheckStatusEnum.CHECKED.getStatus()
        );

//        dailyTrend (array): 每日报修趋势数据，包含日期、报修数。
        List<JSONObject> totalList = repairMgmtMapper.selectDailyTrend(startTime, endTime, dormBuilding);
        List dailyTrendList = new ArrayList();
        if (startTime != null) {
            for (LocalDateTime date = startTime; date.isBefore(endTime); date = date.plusDays(1)) {
                System.out.println(date + ": " + date);

                JSONObject jsonObject = new JSONObject();
                // 从totalList 中查找 date
                for (JSONObject item : totalList) {
                    String applyTime = item.getString("date");
                    if (applyTime.equals(date)) {
                        jsonObject.put("date", date);
                        jsonObject.put("count", item.getInteger("count"));
                        dailyTrendList.add(jsonObject);
                        break;
                    }
                }
            }

        }
//        typeDistribution (array): 报修类型分布数据，包含类型、数量。

        List<JSONObject> typeDistributionList = repairMgmtMapper.selectTypeDistributionList(startTime, endTime, dormBuilding);
        vo.setTypeDistribution(typeDistributionList);

        return vo;
    }

    @Override
    public RepairMgmtCountRespVO repairCount(RepairMgmtCountReqVO reqVO) {
        RepairMgmtCountRespVO vo = new RepairMgmtCountRespVO();
        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }
        // typeStatistics (array): 各报修类型统计数据，包含类型、报修数、完成数、完成率。
        List<JSONObject> typeStatisticsList = repairMgmtMapper.selectTypeStatisticsList(startTime, endTime, RepairStatusEnum.COMPLETED.getStatus());

        //buildingStatistics (array): 各楼栋报修统计数据，包含楼栋、报修数、完成数、完成率。
        List<JSONObject> buildingStatisticsList = repairMgmtMapper.selectBuildingStatisticsList(startTime, endTime, RepairStatusEnum.COMPLETED.getStatus());
        vo.setTypeStatisticsList(typeStatisticsList);
        vo.setBuildingStatisticsList(buildingStatisticsList);
        return vo;
    }

}