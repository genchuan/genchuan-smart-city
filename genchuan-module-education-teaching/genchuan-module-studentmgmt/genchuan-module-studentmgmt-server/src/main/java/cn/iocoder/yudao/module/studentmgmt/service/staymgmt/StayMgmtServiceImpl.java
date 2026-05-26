package cn.iocoder.yudao.module.studentmgmt.service.staymgmt;

import cn.iocoder.yudao.framework.common.biz.system.dict.dto.DictDataRespDTO;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.staymgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.staymgmt.StayMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.staymgmt.StayMgmtMapper;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.studentinfo.StudentInfoMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants;
import cn.iocoder.yudao.module.studentmgmt.enums.StayStatusEnum;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;
import cn.iocoder.yudao.module.system.api.dict.DictDataApi;
import com.alibaba.fastjson.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.STAY_MGMT_NOT_EXISTS;

/**
 * 留宿管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class StayMgmtServiceImpl implements StayMgmtService {

    @Resource
    private StayMgmtMapper stayMgmtMapper;
    @Resource
    private DeptApi deptApi;
    @Resource
    private DictDataApi dictDataApi;
    @Resource
    private StudentInfoMapper studentInfoMapper;

    @Override
    public Long createStayMgmt(StayMgmtSaveReqVO createReqVO) {
        // 插入
        StayMgmtDO stayMgmt = BeanUtils.toBean(createReqVO, StayMgmtDO.class);
        stayMgmtMapper.insert(stayMgmt);

        // 返回
        return stayMgmt.getId();
    }

    @Override
    public void updateStayMgmt(StayMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateStayMgmtExists(updateReqVO.getId());
        // 更新
        StayMgmtDO updateObj = BeanUtils.toBean(updateReqVO, StayMgmtDO.class);
        stayMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteStayMgmt(Long id) {
        // 校验存在
        validateStayMgmtExists(id);
        // 删除
        stayMgmtMapper.deleteById(id);
    }

    @Override
    public void deleteStayMgmtListByIds(List<Long> ids) {
        // 删除
        stayMgmtMapper.deleteByIds(ids);
    }


    private StayMgmtDO validateStayMgmtExists(Long id) {
        StayMgmtDO stayMgmt = stayMgmtMapper.selectById(id);
        if (stayMgmt == null) {
            throw exception(STAY_MGMT_NOT_EXISTS);
        }
        return stayMgmt;
    }

    @Override
    public StayMgmtDO getStayMgmt(Long id) {
        return stayMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<StayMgmtDO> getStayMgmtPage(StayMgmtPageReqVO pageReqVO) {
        return stayMgmtMapper.selectPage(pageReqVO);
    }

    @Override
    public Boolean confirm(StayMgmtConfirmReqVO reqVO) {
        int total = 0;
        for (Long id : reqVO.getIds()) {
            // 校验存在
            StayMgmtDO stayMgmt = validateStayMgmtExists(id);
            if (!stayMgmt.getStatus().equals(StayStatusEnum.PENDING_CONFIRM.getStatus())) {
                throw exception("仅允许确认待确认状态的申请");
            }
            stayMgmt.setParentConfirmTime(LocalDateTime.now());
            stayMgmt.setStatus(StayStatusEnum.PENDING_AUDIT.getStatus());
            // 更新
            int i = stayMgmtMapper.updateById(stayMgmt);
            total += i;
        }
        if (total > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean audit(StayMgmtConfirmReqVO reqVO) {
        int total = 0;
        for (Long id : reqVO.getIds()) {
            // 校验存在
            StayMgmtDO stayMgmt = validateStayMgmtExists(id);

            if (!stayMgmt.getStatus().equals(StayStatusEnum.PENDING_AUDIT.getStatus())) {
                throw exception("仅允许确认待确认状态的申请");
            }
            String loginUserNickname = SecurityFrameworkUtils.getLoginUserNickname();
            stayMgmt.setAuditUser(loginUserNickname);
            stayMgmt.setAuditTime(LocalDateTime.now());
            stayMgmt.setParentConfirmTime(LocalDateTime.now());
            stayMgmt.setStatus(StayStatusEnum.PASSED.getStatus());
            // 更新
            int i = stayMgmtMapper.updateById(stayMgmt);
            total += i;
        }
        if (total > 0) {
            return true;
        }
        return false;
    }

    @Override
    public StayMgmtChartRespVO chart(StayMgmtChartReqVO reqVO) {
        StayMgmtChartRespVO vo = new StayMgmtChartRespVO();

        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }
        ;
        // 班级ID
        Long classId = reqVO.getClassId();
        String className = null;
        // 判断是否有按班级查询
        if (null != classId) {
            // 通过classId 查询 System模块的 dept.id 的名称，减少关联查询
            CommonResult<DeptRespDTO> dept = deptApi.getDept(classId);
            DeptRespDTO data = dept.getData();
            if (data == null) {
                throw new ServiceException(ErrorCodeConstants.DEPT_NOT_EXISTS);
            }
            className = data.getName();
        }
        // 1. 卡片数据
        //totalCount (integer): 本期考评总记录数。
        vo = stayMgmtMapper.selectTotalCount(startTime, endTime, className,
                StayStatusEnum.PENDING_CONFIRM.getStatus(),
                StayStatusEnum.PENDING_AUDIT.getStatus(),
                StayStatusEnum.PASSED.getStatus()
        );
        if (vo == null) {
            vo = new StayMgmtChartRespVO();
        }
        if (vo.getPassedCount() == null) {
            vo.setPassedCount(0);
        }
        if (vo.getPendingAuditCount() == null) {
            vo.setPendingAuditCount(0);
        }
        if (vo.getPendingConfirmCount() == null) {
            vo.setPendingConfirmCount(0);
        }
        if (vo.getTotalStayCount() == null) {
            vo.setTotalStayCount(0);
        }

        // weekendTrend (array): 周末留宿趋势数据，包含日期、留宿人数。
        List<JSONObject> weekendTrendList = stayMgmtMapper.getWeekendTrend(startTime, endTime, className);
        if (weekendTrendList == null) {
            weekendTrendList = new ArrayList<>();
        }
        //statusDistribution (array): 留宿申请状态分布数据，包含状态、数量。
        List<JSONObject> statusDistributionList = stayMgmtMapper.getStatusDistribution(startTime, endTime, className);
        // 将statusDistributionList里的status字段转为中文
        List<JSONObject> statusList = new ArrayList<>();
        for (JSONObject jsonObject : statusDistributionList) {
            String status = jsonObject.getString("status");

            String dictDataLabel = status;
            CommonResult<List<DictDataRespDTO>> dictDataList = dictDataApi.getDictDataList(StayStatusEnum.DICT_TYPE);
            if (dictDataList.getData() != null) {
                for (DictDataRespDTO dictData : dictDataList.getData()) {
                    if (dictData.getValue().equals(status)) {
                        dictDataLabel = dictData.getLabel();
                        break;
                    }
                }
            }
            jsonObject.put("status", dictDataLabel);
            statusList.add(jsonObject);

        }
        vo.setWeekendTrend(weekendTrendList);
        vo.setStatusDistribution(statusList);

        return vo;

    }

    @Override
    public StayMgmtStayCountRespVO stayCount(StayMgmtStayCountReqVO reqVO) {
        StayMgmtStayCountRespVO vo = new StayMgmtStayCountRespVO();
        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }
        // typeStatistics (array): 各报修类型统计数据，包含类型、报修数、完成数、完成率。
        List<JSONObject> classStatisticsList = stayMgmtMapper.getClassStatisticsList(startTime, endTime);
        // 查询该班级学生人数，并计算出占比
        List<JSONObject> classStatistics = new ArrayList<>();
        for (JSONObject jsonObject : classStatisticsList) {
            String className = jsonObject.getString("className");
            Integer totalCount = studentInfoMapper.selectCountByClassName(className);
            if (totalCount == null) {
                totalCount = 0;
            }
            // 计算占比
            Integer stayCount = jsonObject.getInteger("count");
            if (stayCount == null) {
                stayCount = 0;
            }
            BigDecimal percentage = BigDecimal.valueOf(stayCount).multiply(BigDecimal.valueOf(100))
                    .divide(BigDecimal.valueOf(totalCount), 2, RoundingMode.HALF_UP);
            jsonObject.put("percentage", percentage);

            JSONObject classStatisticsJson = new JSONObject();
            classStatisticsJson.put("className", className);
            classStatisticsJson.put("totalCount", totalCount);
            classStatisticsJson.put("stayCount", stayCount);
            classStatistics.add(classStatisticsJson);

        }
        vo.setClassStatistics(classStatistics);
        return vo;
    }

}