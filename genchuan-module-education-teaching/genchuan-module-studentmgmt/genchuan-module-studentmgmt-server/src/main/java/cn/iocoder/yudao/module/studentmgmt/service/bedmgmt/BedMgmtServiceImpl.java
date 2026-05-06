package cn.iocoder.yudao.module.studentmgmt.service.bedmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.bedmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.bedmgmt.BedMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.bedmgmt.BedMgmtMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.BedStatusEnum;
import com.alibaba.fastjson.JSONObject;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.BED_MGMT_NOT_EXISTS;
import static cn.iocoder.yudao.module.studentmgmt.enums.LogRecordConstants.*;

/**
 * 床位管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class BedMgmtServiceImpl implements BedMgmtService {

    @Resource
    private BedMgmtMapper bedMgmtMapper;

    @Override
    public Long createBedMgmt(BedMgmtSaveReqVO createReqVO) {
        // 插入
        BedMgmtDO bedMgmt = BeanUtils.toBean(createReqVO, BedMgmtDO.class);
        bedMgmtMapper.insert(bedMgmt);

        // 返回
        return bedMgmt.getId();
    }

    @Override
    public void updateBedMgmt(BedMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateBedMgmtExists(updateReqVO.getId());
        // 更新
        BedMgmtDO updateObj = BeanUtils.toBean(updateReqVO, BedMgmtDO.class);
        bedMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteBedMgmt(Long id) {
        // 校验存在
        validateBedMgmtExists(id);
        // 删除
        bedMgmtMapper.deleteById(id);
    }

    @Override
    public void deleteBedMgmtListByIds(List<Long> ids) {
        // 删除
        bedMgmtMapper.deleteByIds(ids);
    }


    private void validateBedMgmtExists(Long id) {
        if (bedMgmtMapper.selectById(id) == null) {
            throw exception(BED_MGMT_NOT_EXISTS);
        }
    }

    @Override
    public BedMgmtDO getBedMgmt(Long id) {
        return bedMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<BedMgmtDO> getBedMgmtPage(BedMgmtPageReqVO pageReqVO) {
        return bedMgmtMapper.selectPage(pageReqVO);
    }

    @Override
    @LogRecord(type = BED_MGMT_TYPE, subType = BED_MGMT_ASSIGN_SUB_TYPE, bizNo = "{{#assessMgmt.id}}",
            success = BED_MGMT_ASSIGN_SUB_TYPE_SUCCESS)
    public boolean assign(BedMgmtAssignReqVO reqVO) {
        Long[] bedIds = reqVO.getBedIds();
        Long[] studentIds = reqVO.getStudentIds();

        if (bedIds.length != studentIds.length) {
            throw exception("床位ID与学生ID长度不匹配");
        }

        int total = 0;
        for (int i = 0; i < bedIds.length; i++) {
            Long bedId = bedIds[i];
            Long studentId = studentIds[i];
            BedMgmtDO bedMgmt = bedMgmtMapper.selectById(bedId);
            // 自动校验目标床位是否为未分配状态
            if (!BedStatusEnum.BED_STATUS_UNALLOCATED.getStatus().equals(bedMgmt.getStatus())) {
                throw exception(500, "目标床位不是未分配状态");
            }
            // 校验学生是否已分配其他床位
            BedMgmtDO bedMgmtByStudentId = bedMgmtMapper.selectOne(BedMgmtDO::getStudentId, studentId);
            if (bedMgmtByStudentId != null) {
                throw exception(500, "学生已分配其他床位");
            }

            LocalDateTime assignTime = reqVO.getAssignTime();
            if (assignTime == null) {
                assignTime = LocalDateTime.now();
            }
            bedMgmt.setStudentId(studentId);
            bedMgmt.setAssignTime(assignTime);
            bedMgmt.setStatus(BedStatusEnum.BED_STATUS_ALLOCATED.getStatus());
            int insert = bedMgmtMapper.updateById(bedMgmt);
            total = total + insert;

        }
        if (total == bedIds.length) {
            return true;
        }
        return false;
    }

    @Override
    @LogRecord(type = BED_MGMT_TYPE, subType = BED_MGMT_ADJUST_SUB_TYPE, bizNo = "{{#reqVO.newBedId}}",
            success = BED_MGMT_ADJUST_SUB_TYPE_SUCCESS)
    public boolean adjust(BedMgmtAdjustReqVO reqVO) {
        Long oldBedId = Long.valueOf(reqVO.getOldBedId());
        Long newBedId = Long.valueOf(reqVO.getNewBedId());
        Long studentId = Long.valueOf(reqVO.getStudentId());
        LocalDateTime adjustTime = reqVO.getAdjustTime();

        BedMgmtDO bedMgmt = bedMgmtMapper.selectById(oldBedId);
        // 判断原床位是否为该学生
        if (!studentId.equals(bedMgmt.getStudentId())) {
            throw exception(500, "原床位不是该学生的");
        }
        // 判断新床为是否已经被分配
        BedMgmtDO newBedMgmt = bedMgmtMapper.selectById(newBedId);
//        if (!BedStatusEnum.BED_STATUS_UNALLOCATED.getStatus().equals(newBedMgmt.getStatus())) {
//            throw exception(500, "目标床位不是未分配状态");
//        }

        // 校验学生是否已分配其他床位
        BedMgmtDO bedMgmtByStudentId = bedMgmtMapper.selectOne(BedMgmtDO::getStudentId, studentId);
        if (bedMgmtByStudentId != null) {
            // 该床位是否为oldBedId，如果不是，则表示还存在其它的旧床位
            if (!oldBedId.equals(bedMgmtByStudentId.getId())) {
                throw exception(500, "学生已分配其他床位");
            }
        }
        // 设置新床位为学生
        if (adjustTime == null) {
            adjustTime = LocalDateTime.now();
        }
        bedMgmt.setStudentId(studentId);
        newBedMgmt.setAdjustTime(adjustTime);
        bedMgmt.setStatus(BedStatusEnum.BED_STATUS_ALLOCATED.getStatus());
        int update = bedMgmtMapper.updateById(newBedMgmt);
        if (update > 0) {
            // 记录操作日志上下文
            String content = "原床位ID：" + oldBedId + "，新床位ID：" + newBedId + "，学生ID：" + studentId;
            LogRecordContext.putVariable("content", content);
            return true;
        }

        return false;
    }

    @Override
    public BedMgmtChartRespVO chart(BedMgmtChartReqVO reqVO) {
        BedMgmtChartRespVO vo = new BedMgmtChartRespVO();

        String building = reqVO.getBuilding();

        // 1. 卡片数据
        // 获取总床位数
        Integer totalBed = bedMgmtMapper.selectTotalBed(building, "");
        vo.setTotalBed(totalBed);
        // 获取已分配床位数
        Integer usedBed = bedMgmtMapper.selectTotalBed(building, BedStatusEnum.BED_STATUS_ALLOCATED.getStatus());
        vo.setUsedBed(usedBed);
        vo.setUnusedBed(bedMgmtMapper.selectTotalBed(building, BedStatusEnum.BED_STATUS_UNALLOCATED.getStatus()));
        // 计算使用率
        vo.setUsageRate(BigDecimal.valueOf(usedBed).divide(BigDecimal.valueOf(totalBed), 2, RoundingMode.HALF_UP));

        // 2. 各楼栋床位统计列表
        // 查询所有楼栋
        List<JSONObject> buildingStatsList = new ArrayList<>();

        List<String> buildings = bedMgmtMapper.selectDistinctBuilding();
        for (String buildingName : buildings) {
            // 获取该楼栋的床位数
            Integer totalBuildingBed = bedMgmtMapper.selectTotalBed(buildingName, "");
            // 获取该楼栋已分配床位数
            Integer usedBuildingBed = bedMgmtMapper.selectTotalBed(buildingName, BedStatusEnum.BED_STATUS_ALLOCATED.getStatus());
            // 计算该楼栋未分配床位数
            Integer unusedBuildingBed = totalBuildingBed - usedBuildingBed;
            JSONObject buildingJson = new JSONObject();
            buildingJson.put("building", buildingName);
            buildingJson.put("total", totalBuildingBed);
            buildingJson.put("used", usedBuildingBed);
            buildingJson.put("unused", unusedBuildingBed);
            buildingStatsList.add(buildingJson);
        }
        vo.setBuildingStats(buildingStatsList);
        return vo;
    }

    @Override
    public BedMgmtBedDistributionRespVO bedDistribution() {
        BedMgmtBedDistributionRespVO vo = new BedMgmtBedDistributionRespVO();
        List<String> labels = new ArrayList<>();
        List<BigDecimal> data = new ArrayList<>();

        // 查询所有楼栋
        List<String> buildings = bedMgmtMapper.selectDistinctBuilding();
        for (String buildingName : buildings) {
            // 获取该楼栋的床位数
            Integer totalBuildingBed = bedMgmtMapper.selectTotalBed(buildingName, "");
            // 获取该楼栋已分配床位数
            Integer usedBuildingBed = bedMgmtMapper.selectTotalBed(buildingName, BedStatusEnum.BED_STATUS_ALLOCATED.getStatus());

            // 占比
            BigDecimal usageRate = BigDecimal.valueOf(usedBuildingBed).multiply(BigDecimal.valueOf(100))
                    .divide(BigDecimal.valueOf(totalBuildingBed), 2, RoundingMode.HALF_UP);

            labels.add(buildingName);
            data.add(usageRate);
        }
        vo.setLabels(labels);
        vo.setData(data);
        return vo;
    }

    @Override
    public BedMgmtBedIndexRespVO bedIndex() {
        BedMgmtBedIndexRespVO vo = new BedMgmtBedIndexRespVO();
//        assignCount (integer): 统计周期内分配总次数。
//        adjustCount (integer): 统计周期内调整总次数。
//        newAssignCount (integer): 今日新增分配次数。
//        newAdjustCount (integer): 今日新增调整次数。
//        trendList (array): 近 7 天操作趋势列表。

        JSONObject assignAndAdjustCountJson = bedMgmtMapper.selectAssignAndAdjustCount();
        JSONObject todayAssignAndAdjustCountJson = bedMgmtMapper.selectTodayAssignAndAdjustCount();
        List<JSONObject> sevenDayAssignCountJsonList = bedMgmtMapper.select7dayAssignCount();
        List<JSONObject> sevenDayAdjustCountJson = bedMgmtMapper.select7dayAdjustCount();

        vo.setAdjustCount(assignAndAdjustCountJson.getInteger("adjustCount"));
        vo.setAssignCount(assignAndAdjustCountJson.getInteger("assignCount"));
        vo.setNewAdjustCount(todayAssignAndAdjustCountJson.getInteger("newAdjustCount"));
        vo.setNewAssignCount(todayAssignAndAdjustCountJson.getInteger("newAssignCount"));
        // 最近七天的日期
        List<JSONObject> trendList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            JSONObject trendJson = new JSONObject();
            String dataStr = LocalDate.now().minusDays(i).toString();

            trendJson.put("date", dataStr);

            // 取出sevenDayAssignCountJson与dataStr匹配的数据
            Integer assign = 0;
            for (JSONObject json : sevenDayAssignCountJsonList) {
                String assignTime = json.getString("assignTime");
                if (assignTime.equals(dataStr)) {
                    assign = json.getInteger("assign");
                    break;
                }
            }
            trendJson.put("assign", assign);

            // 取出sevenDayAdjustCountJson与dataStr匹配的数据
            Integer adjust = 0;
            for (JSONObject json : sevenDayAdjustCountJson) {
                String adjustTime = json.getString("adjustTime");
                if (adjustTime.equals(dataStr)) {
                    adjust = json.getInteger("adjust");
                    break;
                }
            }
            trendJson.put("adjust", adjust);
            trendList.add(trendJson);
        }
        vo.setTrendList(trendList);
        return vo;
    }
}