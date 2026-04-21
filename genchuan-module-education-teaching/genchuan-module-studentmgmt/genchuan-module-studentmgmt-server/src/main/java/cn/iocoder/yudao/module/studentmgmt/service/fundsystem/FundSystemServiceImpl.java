package cn.iocoder.yudao.module.studentmgmt.service.fundsystem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.fundsystem.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.fundsystem.FundSystemDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.fundsystem.FundSystemMapper;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.studentinfo.StudentInfoMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.BehaviorLevelTypeEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.FundSystemStatusEnum;
import com.alibaba.fastjson.JSONObject;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.FUND_SYSTEM_NOT_EXISTS;
import static cn.iocoder.yudao.module.studentmgmt.enums.LogRecordConstants.*;

/**
 * 资助系统 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class FundSystemServiceImpl implements FundSystemService {

    @Resource
    private FundSystemMapper fundSystemMapper;
    @Resource
    private StudentInfoMapper studentInfoMapper;

    @Override
    public Long createFundSystem(FundSystemSaveReqVO createReqVO) {
        // 插入
        FundSystemDO fundSystem = BeanUtils.toBean(createReqVO, FundSystemDO.class);
        fundSystemMapper.insert(fundSystem);

        // 返回
        return fundSystem.getId();
    }

    @Override
    public void updateFundSystem(FundSystemSaveReqVO updateReqVO) {
        // 校验存在
        validateFundSystemExists(updateReqVO.getId());
        // 更新
        FundSystemDO updateObj = BeanUtils.toBean(updateReqVO, FundSystemDO.class);
        fundSystemMapper.updateById(updateObj);
    }

    @Override
    public void deleteFundSystem(Long id) {
        // 校验存在
        validateFundSystemExists(id);
        // 删除
        fundSystemMapper.deleteById(id);
    }

    @Override
    public void deleteFundSystemListByIds(List<Long> ids) {
        // 删除
        fundSystemMapper.deleteByIds(ids);
    }


    private FundSystemDO validateFundSystemExists(Long id) {
        FundSystemDO fundSystemDO = fundSystemMapper.selectById(id);
        if (fundSystemDO == null) {
            throw exception(FUND_SYSTEM_NOT_EXISTS);
        }
        return fundSystemDO;
    }

    @Override
    public FundSystemDO getFundSystem(Long id) {
        return fundSystemMapper.selectById(id);
    }

    @Override
    public PageResult<FundSystemDO> getFundSystemPage(FundSystemPageReqVO pageReqVO) {
        return fundSystemMapper.selectPage(pageReqVO);
    }

    @Override
    @LogRecord(type = FUND_SYSTEM_TYPE, subType = FUND_SYSTEM_AUDIT_SUB_TYPE, bizNo = "{{#fund.id}}",
            success = FUND_SYSTEM_AUDIT_SUCCESS)
    public boolean audit(FundSystemAuditReqVO reqVO) {
        FundSystemDO fundSystemDO = validateFundSystemExists(reqVO.getId());
        fundSystemDO.setAuditTime(LocalDateTime.now());
        // 获取当前用户
//        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
//        SecurityFrameworkUtils.getLoginUserNickname();
        String username = SecurityFrameworkUtils.getLoginUserNickname();
        fundSystemDO.setAuditUser(username);
        fundSystemDO.setStatus(reqVO.getStatus());

        int i = fundSystemMapper.updateById(fundSystemDO);
        if (i > 0) {
            // 记录操作日志上下文
            LogRecordContext.putVariable("fund", fundSystemDO);
            return true;
        }
        return false;

    }

    @Override
    public FundSystemChartRespVO chart(FundSystemChartReqVO reqVO) {
        FundSystemChartRespVO vo = new FundSystemChartRespVO();
        // 1. 卡片数据
        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }
//        totalApplyCount (integer): 本学期资助申请总次数。
//        pendingAuditCount (integer): 待审核资助申请数。
//        totalApplyAmount (decimal): 本学期申请总金额。
//        approvedCount (integer): 已审核通过申请数。
//        fundTypeDistribution (array): 资助类型分布统计，包含类型名称、对应数量。
//        gradeApplyTrend (array): 各年级申请趋势，包含年级名称、对应申请人数。

        vo.setTotalApplyCount(fundSystemMapper.selectTotalCount(startTime, endTime, ""));
        vo.setPendingAuditCount(fundSystemMapper.selectTotalCount(startTime, endTime, FundSystemStatusEnum.FUND_SYSTEM_STATUS_0.getStatus()));
        vo.setApprovedCount(fundSystemMapper.selectTotalCount(startTime, endTime, FundSystemStatusEnum.FUND_SYSTEM_STATUS_1.getStatus()));

        vo.setTotalApplyAmount(fundSystemMapper.selectTotalAmount(startTime, endTime, FundSystemStatusEnum.FUND_SYSTEM_STATUS_1.getStatus()));

        List<JSONObject> typeList = fundSystemMapper.selectFundTypeDistribution(startTime, endTime, FundSystemStatusEnum.FUND_SYSTEM_STATUS_1.getStatus());
        // 将key转换成name
        typeList.forEach(item -> {
            item.put("name", FundSystemStatusEnum.getNameByKey(item.getString("name")));
        });

        vo.setFundTypeDistribution(typeList);

        vo.setGradeApplyTrend(fundSystemMapper.selectGradeApplyTrend(startTime, endTime, FundSystemStatusEnum.FUND_SYSTEM_STATUS_1.getStatus()));
        return vo;
    }

    @Override
    public FundSystemFundCountRespVO fundCount(FundSystemFundCountReqVO reqVO) {
        FundSystemFundCountRespVO vo = new FundSystemFundCountRespVO();
        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null == timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }

        List<JSONObject> list = new ArrayList<>();
        // 查询所有学生的年级
        List<String> gradeList = studentInfoMapper.selectAllGrade();
        for (String grade : gradeList) {

            JSONObject json = new JSONObject();

            List<JSONObject> typeList = fundSystemMapper.selectFundTypeGradeDistribution(startTime, endTime,
                    FundSystemStatusEnum.FUND_SYSTEM_STATUS_1.getStatus(),grade );
            // 将key转换成namegr
            typeList.forEach(item -> {
                item.put("name", FundSystemStatusEnum.getNameByKey(item.getString("name")));
            });


            json.put("grade", grade);
            json.put("typeDistribution", typeList);
            list.add(json);

        }
        vo.setGradeStatistics(list);
        return vo;
    }

}