package cn.iocoder.yudao.module.studentmgmt.service.registermgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.BaseChartReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartTrendVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.registermgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.registermgmt.RegisterMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.registermgmt.RegisterMgmtMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.RegisterStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.REGISTER_MGMT_NOT_EXISTS;

/**
 * 报名管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RegisterMgmtServiceImpl implements RegisterMgmtService {

    @Resource
    private RegisterMgmtMapper registerMgmtMapper;

    @Override
    public Long createRegisterMgmt(RegisterMgmtSaveReqVO createReqVO) {
        // 插入
        RegisterMgmtDO registerMgmt = BeanUtils.toBean(createReqVO, RegisterMgmtDO.class);
        registerMgmtMapper.insert(registerMgmt);

        // 返回
        return registerMgmt.getId();
    }

    @Override
    public void updateRegisterMgmt(RegisterMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateRegisterMgmtExists(updateReqVO.getId());
        // 更新
        RegisterMgmtDO updateObj = BeanUtils.toBean(updateReqVO, RegisterMgmtDO.class);
        registerMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteRegisterMgmt(Long id) {
        // 校验存在
        validateRegisterMgmtExists(id);
        // 删除
        registerMgmtMapper.deleteById(id);
    }

    @Override
    public void deleteRegisterMgmtListByIds(List<Long> ids) {
        // 删除
        registerMgmtMapper.deleteByIds(ids);
    }


    private RegisterMgmtDO validateRegisterMgmtExists(Long id) {
        RegisterMgmtDO registerMgmt = registerMgmtMapper.selectById(id);
        if (registerMgmt == null) {
            throw exception(REGISTER_MGMT_NOT_EXISTS);
        }
        return registerMgmt;
    }

    @Override
    public RegisterMgmtDO getRegisterMgmt(Long id) {
        return registerMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<RegisterMgmtDO> getRegisterMgmtPage(RegisterMgmtPageReqVO pageReqVO) {
        return registerMgmtMapper.selectPage(pageReqVO);
    }

    @Override
    public Boolean audit(RegisterMgmtAuditReqVO reqVO) {
        int total = 0;
        for (Long id : reqVO.getIds()) {
            // 校验存在
            RegisterMgmtDO registerMgmt = validateRegisterMgmtExists(id);
            //审核报名申请，将状态更新为已录取，记录审核人及审核时间
            registerMgmt.setStatus(RegisterStatusEnum.ADMITTED.getStatus());
            registerMgmt.setAuditUser(reqVO.getAuditUser());
            registerMgmt.setAuditTime(LocalDateTime.now());
            // 更新
            int i = registerMgmtMapper.updateById(registerMgmt);
            total += i;
        }
        return total > 0;
    }

    @Override
    public Boolean confirm(RegisterMgmtConfirmReqVO reqVO) {
        int total = 0;
        for (Long id : reqVO.getIds()) {
            // 校验存在
            RegisterMgmtDO registerMgmt = validateRegisterMgmtExists(id);
            //确认录取结果，记录录取确认时间，完成报名流程闭环
            registerMgmt.setStatus(RegisterStatusEnum.ADMITTED.getStatus());

            registerMgmt.setConfirmTime(LocalDateTime.now());
            // 更新
            int i = registerMgmtMapper.updateById(registerMgmt);
            total += i;
        }
        return total > 0;
    }

    @Override
    public RegisterMgmtChartRespVO chart(BaseChartReqVO reqVO) {
        RegisterMgmtChartRespVO vo = new RegisterMgmtChartRespVO();

        // 1. 卡片数据
        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }
        // 1. 卡片数据
        vo = registerMgmtMapper.selectTotalCount(startTime, endTime,
                RegisterStatusEnum.PENDING.getStatus(),
                RegisterStatusEnum.ADMITTED.getStatus()
        );
        // 如果统计为空，则设置为0
        if (vo == null) {
            vo = new RegisterMgmtChartRespVO();
        }
        if (vo.getAdmittedCount() == null) {
            vo.setAdmittedCount(0);
        }
        if (vo.getPendingAuditCount() == null) {
            vo.setPendingAuditCount(0);
        }
        if (vo.getTotalApplyCount() == null) {
            vo.setTotalApplyCount(0);
        }
        if (vo.getConfirmedCount() == null) {
            vo.setConfirmedCount(0);
        }

        //近一周报名趋势数据，包含日期及对应报名数

        List<ChartTrendVO> sevenDayTrendCountJsonList = registerMgmtMapper.select7dayTrendCount();
        // 最近七天的日期
        List<ChartTrendVO> trendList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            ChartTrendVO trendVo = new ChartTrendVO();
            String dataStr = LocalDate.now().minusDays(i).toString();
            trendVo.setDate(dataStr);
            // 取出 sevenDayTrendCountJsonList 与 dataStr 匹配的数据
            Integer count = 0;
            for (ChartTrendVO bean : sevenDayTrendCountJsonList) {
                String date = bean.getDate();
                if (date.equals(dataStr)) {
                    count = bean.getCount();
                    break;
                }
            }
            trendVo.setCount(count);
            trendList.add(trendVo);
        }
        vo.setRecentWeekApplyTrend(trendList);
        return vo;
    }

    @Override
    public RegisterMgmtEnrollCountRespVO enrollCount(BaseChartReqVO reqVO) {

        RegisterMgmtEnrollCountRespVO vo = new RegisterMgmtEnrollCountRespVO();

        // 1. 卡片数据
        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }
        // 各专业报名录取数据，包含专业名称、报名人数、录取人数。
        List<EnrollCountVO> majorEnrollData = registerMgmtMapper.selectMajorEnrollCount(startTime, endTime,
                RegisterStatusEnum.ADMITTED.getStatus());

        vo.setMajorEnrollData(majorEnrollData);
        return vo;
    }

}