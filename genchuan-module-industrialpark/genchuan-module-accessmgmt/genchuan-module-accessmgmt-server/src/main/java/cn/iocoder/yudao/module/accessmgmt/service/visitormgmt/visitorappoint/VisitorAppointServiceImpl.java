package cn.iocoder.yudao.module.accessmgmt.service.visitormgmt.visitorappoint;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitorappoint.vo.*;
import cn.iocoder.yudao.module.accessmgmt.dal.dataobject.visitormgmt.visitorappoint.VisitorAppointDO;
import cn.iocoder.yudao.module.accessmgmt.dal.mysql.visitormgmt.visitorappoint.VisitorAppointMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.accessmgmt.enums.ErrorCodeConstants.*;

/**
 * 访客预约 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class VisitorAppointServiceImpl implements VisitorAppointService {

    @Resource
    private VisitorAppointMapper visitorAppointMapper;

    // ==================== 预约查询 ====================

    @Override
    public PageResult<VisitorAppointRespVO> getVisitorAppointPage(VisitorAppointPageReqVO pageReqVO) {
        // 分页查询，Mapper 层按 visitorName(模糊)/idCard(模糊)/visitCompany(模糊)/appointStatus/visitTime 范围动态条件筛选，按主键倒序
        PageResult<VisitorAppointDO> pageResult = visitorAppointMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult, VisitorAppointRespVO.class);
    }

    @Override
    public VisitorAppointRespVO getVisitorAppoint(Long id) {
        // 按主键查单条，不存在抛 VISITOR_APPOINT_NOT_EXISTS 业务异常
        VisitorAppointDO entity = visitorAppointMapper.selectById(id);
        if (entity == null) {
            throw exception(VISITOR_APPOINT_NOT_EXISTS);
        }
        return BeanUtils.toBean(entity, VisitorAppointRespVO.class);
    }

    // ==================== 预约申请 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createVisitorAppoint(VisitorAppointCreateReqVO createReqVO) {
        // VO → DO，前端传入毫秒时间戳 → 东八区 LocalDateTime，初始状态 "待审核"
        VisitorAppointDO entity = BeanUtils.toBean(createReqVO, VisitorAppointDO.class);
        entity.setVisitTime(Instant.ofEpochMilli(createReqVO.getVisitTime()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime());
        entity.setAppointStatus("待审核");
        visitorAppointMapper.insert(entity);
        return true;
    }

    // ==================== 审核流程（通过 / 驳回） ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean auditVisitorAppoint(VisitorAppointAuditReqVO reqVO) {
        // 审核通过：校验记录存在 → appointStatus → "已通过"，写入 checkResult
        VisitorAppointDO exist = visitorAppointMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(VISITOR_APPOINT_NOT_EXISTS);
        }
        VisitorAppointDO updateObj = new VisitorAppointDO();
        updateObj.setId(reqVO.getId());
        updateObj.setAppointStatus("已通过");
        updateObj.setCheckResult(reqVO.getCheckResult());
        visitorAppointMapper.updateById(updateObj);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean rejectVisitorAppoint(VisitorAppointRejectReqVO reqVO) {
        // 审核驳回：校验记录存在 → appointStatus → "已驳回"，写入 rejectReason
        VisitorAppointDO exist = visitorAppointMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(VISITOR_APPOINT_NOT_EXISTS);
        }
        VisitorAppointDO updateObj = new VisitorAppointDO();
        updateObj.setId(reqVO.getId());
        updateObj.setAppointStatus("已驳回");
        updateObj.setRejectReason(reqVO.getRejectReason());
        visitorAppointMapper.updateById(updateObj);
        return true;
    }

    // ==================== 凭证管理 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VisitorAppointGenerateRespVO generateVisitorAppoint(VisitorAppointGenerateReqVO reqVO) {
        // 审核通过后生成通行凭证：校验记录存在
        VisitorAppointDO exist = visitorAppointMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(VISITOR_APPOINT_NOT_EXISTS);
        }
        // 凭证格式: VST-20260518-001（前缀 + 日期 + 三位随机数）
        String ticket = "VST-" + LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"))
                + "-" + String.format("%03d", (int)(Math.random() * 999 + 1));
        // 凭证号写回数据库
        VisitorAppointDO updateObj = new VisitorAppointDO();
        updateObj.setId(reqVO.getId());
        updateObj.setTicket(ticket);
        visitorAppointMapper.updateById(updateObj);
        // 返回凭证号
        VisitorAppointGenerateRespVO respVO = new VisitorAppointGenerateRespVO();
        respVO.setSuccess(true);
        respVO.setTicket(ticket);
        return respVO;
    }

    @Override
    public VisitorAppointVerifyRespVO verifyVisitorAppoint(VisitorAppointVerifyReqVO reqVO) {
        // 到访验证：根据凭证号 ticket 查询预约记录
        VisitorAppointDO entity = visitorAppointMapper.selectByTicket(reqVO.getTicket());
        VisitorAppointVerifyRespVO respVO = new VisitorAppointVerifyRespVO();
        if (entity == null) {
            // 凭证不存在 → 验证失败
            respVO.setPass(false);
            respVO.setMsg("凭证无效");
            return respVO;
        }
        // 凭证有效 → 返回访客姓名、被访企业
        respVO.setPass(true);
        respVO.setVisitorName(entity.getVisitorName());
        respVO.setVisitCompany(entity.getVisitCompany());
        respVO.setMsg("验证通过，欢迎到访");
        return respVO;
    }

    // ==================== 预约状态变更 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean cancelVisitorAppoint(VisitorAppointCancelReqVO reqVO) {
        // 取消预约：校验记录存在 → appointStatus → "已取消"
        VisitorAppointDO exist = visitorAppointMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(VISITOR_APPOINT_NOT_EXISTS);
        }
        VisitorAppointDO updateObj = new VisitorAppointDO();
        updateObj.setId(reqVO.getId());
        updateObj.setAppointStatus("已取消");
        visitorAppointMapper.updateById(updateObj);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean leaveVisitorAppoint(VisitorAppointLeaveReqVO reqVO) {
        // 确认离园：校验记录存在 → appointStatus → "已离园"，记录离开时间
        VisitorAppointDO exist = visitorAppointMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(VISITOR_APPOINT_NOT_EXISTS);
        }
        VisitorAppointDO updateObj = new VisitorAppointDO();
        updateObj.setId(reqVO.getId());
        updateObj.setAppointStatus("已离园");
        updateObj.setLeaveTime(LocalDateTime.now());
        visitorAppointMapper.updateById(updateObj);
        return true;
    }

    @Override
    public List<VisitorAppointRespVO> getVisitorAppointList(VisitorAppointPageReqVO pageReqVO) {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<VisitorAppointDO> pageResult = visitorAppointMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult.getList(), VisitorAppointRespVO.class);
    }

    @Override
    public VisitorAppointChartRespVO getVisitorAppointChart(Long startTime, Long endTime) {
        // 双维度聚合：每日预约趋势 + 各企业预约统计
        VisitorAppointChartRespVO chartVO = new VisitorAppointChartRespVO();
        chartVO.setDayTrendList(visitorAppointMapper.selectDayTrendList(startTime, endTime));
        chartVO.setCompanyCountList(visitorAppointMapper.selectCompanyCountList(startTime, endTime));
        return chartVO;
    }

}
