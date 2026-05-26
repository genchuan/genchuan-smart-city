package cn.iocoder.yudao.module.studentmgmt.service.clubmgmt;

import cn.iocoder.yudao.framework.common.biz.system.dict.dto.DictDataRespDTO;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.behaviormgmt.vo.BehaviorMgmtChartRespVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.clubmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.clubmgmt.ClubMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.clubmgmt.ClubMgmtMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.*;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;
import cn.iocoder.yudao.module.system.api.dict.DictDataApi;
import com.alibaba.fastjson.JSONObject;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.CLUB_MGMT_NOT_EXISTS;
import static cn.iocoder.yudao.module.studentmgmt.enums.LogRecordConstants.*;

/**
 * 社团管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ClubMgmtServiceImpl implements ClubMgmtService {

    @Resource
    private ClubMgmtMapper clubMgmtMapper;
    @Resource
    private DictDataApi dictDataApi;


    @Override
    @LogRecord(type = CLUB_TYPE, subType = CLUB_CREATE_SUB_TYPE, bizNo = "{{#club.id}}",
            success = CLUB_CREATE_SUCCESS)
    public Long createClubMgmt(ClubMgmtSaveReqVO createReqVO) {
        // 插入
        ClubMgmtDO clubMgmt = BeanUtils.toBean(createReqVO, ClubMgmtDO.class);
        clubMgmt.setStatus(ClubStatusEnum.Club_STATUS_0.getStatus());
        clubMgmt.setApplyTime(LocalDateTime.now());
        String loginUserNickname = SecurityFrameworkUtils.getLoginUserNickname();
        clubMgmt.setCreator(loginUserNickname);
        clubMgmtMapper.insert(clubMgmt);

        // 记录操作日志上下文
        LogRecordContext.putVariable("club", clubMgmt);
        // 返回
        return clubMgmt.getId();
    }

    @Override
    @LogRecord(type = CLUB_TYPE, subType = CLUB_UPDATE_SUB_TYPE, bizNo = "{{#club.id}}",
            success = CLUB_UPDATE_SUCCESS)
    public void updateClubMgmt(ClubMgmtSaveReqVO updateReqVO) {
        // 校验存在
        ClubMgmtDO clubMgmtDO = validateClubMgmtExists(updateReqVO.getId());

        // 更新
        ClubMgmtDO updateObj = BeanUtils.toBean(updateReqVO, ClubMgmtDO.class);
        clubMgmtMapper.updateById(updateObj);
        // 记录操作日志上下文
        LogRecordContext.putVariable("club", clubMgmtDO);
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, BeanUtils.toBean(clubMgmtDO, ClubMgmtSaveReqVO.class));

    }

    @Override
    public void deleteClubMgmt(Long id) {
        // 校验存在
        validateClubMgmtExists(id);
        // 删除
        clubMgmtMapper.deleteById(id);
    }

    @Override
    public void deleteClubMgmtListByIds(List<Long> ids) {
        // 删除
        clubMgmtMapper.deleteByIds(ids);
    }


    private ClubMgmtDO validateClubMgmtExists(Long id) {
        ClubMgmtDO clubMgmtDO = clubMgmtMapper.selectById(id);
        if (clubMgmtDO == null) {
            throw exception(CLUB_MGMT_NOT_EXISTS);
        }
        return clubMgmtDO;
    }

    @Override
    public ClubMgmtDO getClubMgmt(Long id) {
        return clubMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<ClubMgmtDO> getClubMgmtPage(ClubMgmtPageReqVO pageReqVO) {
        return clubMgmtMapper.selectPage(pageReqVO);
    }

    @Override
    @LogRecord(type = CLUB_TYPE, subType = CLUB_AUDIT_SUB_TYPE, bizNo = "{{#club.id}}",
            success = CLUB_AUDIT_SUCCESS)
    public boolean audit(ClubMgmtAuditReqVO reqVO) {
        Long[] ids = reqVO.getIds();
        int total = 0;
        for (Long id : ids) {
            ClubMgmtDO clubMgmtDO = clubMgmtMapper.selectById(id);
            if (clubMgmtDO == null) {
                throw exception(CLUB_MGMT_NOT_EXISTS);
            }
            clubMgmtDO.setAuditTime(LocalDateTime.now());
            // 获取当前用户
            String username = SecurityFrameworkUtils.getLoginUserNickname();
            clubMgmtDO.setAuditUser(username);
            clubMgmtDO.setStatus(reqVO.getStatus());

            int i = clubMgmtMapper.updateById(clubMgmtDO);
            total += i;
            // 记录操作日志上下文
            LogRecordContext.putVariable("club", clubMgmtDO);

        }
        if (total < 1) {
            return false;
        }
        return true;

    }

    @Override
    @LogRecord(type = CLUB_TYPE, subType = CLUB_ARCHIVE_SUB_TYPE, bizNo = "{{#club.id}}",
            success = CLUB_ARCHIVE_SUCCESS)
    public boolean archive(ClubMgmtArchiveReqVO reqVO) {
        Long[] ids = reqVO.getIds();
        int total = 0;
        for (Long id : ids) {
            ClubMgmtDO clubMgmtDO = clubMgmtMapper.selectById(id);
            if (clubMgmtDO == null) {
                throw exception(CLUB_MGMT_NOT_EXISTS);
            }
            // 更新状态为已建档，填充建档时间
            clubMgmtDO.setStatus(ClubStatusEnum.Club_STATUS_2.getStatus());
            clubMgmtDO.setArchiveTime(LocalDateTime.now());

            int i = clubMgmtMapper.updateById(clubMgmtDO);
            total += i;
            // 记录操作日志上下文
            LogRecordContext.putVariable("club", clubMgmtDO);

        }
        if (total < 1) {
            return false;
        }
        return true;

    }

    @Override
    public ClubMgmtChartRespVO chart(ClubMgmtChartReqVO reqVO) {
        ClubMgmtChartRespVO vo = new ClubMgmtChartRespVO();
        // 1. 卡片数据
        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }
//        totalClubCount (integer): 社团总数。
//        totalMemberCount (integer): 社团成员总数。
//        pendingAuditCount (integer): 待审核入团申请数。
//        venueApplyCount (integer): 场馆申请总次数。
//        clubTypeDistribution (array): 社团类型分布统计，包含类型名称、对应社团数量。
//        monthlyApplyTrend (array): 每月入团申请趋势，包含月份、对应申请人数。

        vo.setTotalClubCount(clubMgmtMapper.selectTotalCount(startTime, endTime));
        vo.setTotalMemberCount(clubMgmtMapper.selectTotalMemberCount(startTime, endTime));
        vo.setPendingAuditCount(clubMgmtMapper.selectPendingAuditCount(startTime, endTime, ClubStatusEnum.Club_STATUS_0.getStatus()));
        vo.setVenueApplyCount(clubMgmtMapper.selectVenueApplyCount(startTime, endTime));

        List<JSONObject> clubTypeList = clubMgmtMapper.selectClubTypeDistribution(startTime, endTime);
        // 获取字典数据
        List<DictDataRespDTO> dictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.CLUB_MGMT_CLUB_TYPE.getType()).getData();
        clubTypeList.forEach(item -> {
            String dictDataLabel = "";
            String type = item.getString("type");
            if (dictDataList != null) {
                for (DictDataRespDTO dictData : dictDataList) {
                    if (dictData.getValue().equals(type)) {
                        dictDataLabel = dictData.getLabel();
                        break;
                    }
                }
            }
            item.put("name", dictDataLabel);
        });

        vo.setClubTypeDistribution(clubTypeList);

        List<JSONObject> monthlyApplyTrendList = clubMgmtMapper.selectMonthlyApplyTrend(startTime, endTime);
        vo.setMonthlyApplyTrend(monthlyApplyTrendList);

        return vo;
    }

    @Override
    public ClubMgmtClubDistributionRespVO clubDistribution(ClubMgmtChartReqVO reqVO) {
        ClubMgmtClubDistributionRespVO vo = new ClubMgmtClubDistributionRespVO();
        // 1. 卡片数据
        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }
//        clubStatistics (array): 各社团统计数据，包含社团名称、成员人数、社团类型。
//        typeMemberDistribution (array): 各类型社团的成员人数分布，包含类型名称、对应成员总数。


        List<JSONObject> clubStatisticsList = clubMgmtMapper.selectClubStatistics(startTime, endTime);
        // 获取字典数据
        List<DictDataRespDTO> dictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.CLUB_MGMT_CLUB_TYPE.getType()).getData();
        clubStatisticsList.forEach(item -> {
            String dictDataLabel = "";
            String type = item.getString("clubType");
            if (dictDataList != null) {
                for (DictDataRespDTO dictData : dictDataList) {
                    if (dictData.getValue().equals(type)) {
                        dictDataLabel = dictData.getLabel();
                        break;
                    }
                }
            }
            item.put("clubType", dictDataLabel);
        });

        vo.setClubStatistics(clubStatisticsList);

        List<JSONObject> typeMemberDistribution = clubMgmtMapper.selectTypeMemberDistribution(startTime, endTime);
        typeMemberDistribution.forEach(item -> {
            String dictDataLabel = "";
            String type = item.getString("name");
            if (dictDataList != null) {
                for (DictDataRespDTO dictData : dictDataList) {
                    if (dictData.getValue().equals(type)) {
                        dictDataLabel = dictData.getLabel();
                        break;
                    }
                }
            }
            item.put("name", dictDataLabel);
        });
        vo.setTypeMemberDistribution(typeMemberDistribution);

        return vo;
    }

    @Override
    public boolean venueApply(ClubMgmtVenueApplyReqVO reqVO) {

        ClubMgmtDO clubMgmtDO = clubMgmtMapper.selectById(reqVO.getId());
        if (clubMgmtDO == null) {
            throw exception(CLUB_MGMT_NOT_EXISTS);
        }
        // 更新状态为已建档，填充建档时间
        clubMgmtDO.setVenueApplyStatus(ClubVenueApplyStatusEnum.VENUE_APPLY_STATUS_1.getStatus());
        clubMgmtDO.setApplyTime(reqVO.getApplyTime());
        clubMgmtDO.setApplyReason(reqVO.getApplyReason());
        clubMgmtDO.setVenueName(reqVO.getVenueName());

        int i = clubMgmtMapper.updateById(clubMgmtDO);

        // 记录操作日志上下文
        LogRecordContext.putVariable("club", clubMgmtDO);


        if (i > 0) {
            return true;
        }
        return false;

    }

    @Override
    public PageResult<ClubMgmtRespVO> getClubMgmtJoinPage(ClubMgmtPageReqVO pageReqVO) {
        return clubMgmtMapper.selectJoinPage(pageReqVO);
    }

}