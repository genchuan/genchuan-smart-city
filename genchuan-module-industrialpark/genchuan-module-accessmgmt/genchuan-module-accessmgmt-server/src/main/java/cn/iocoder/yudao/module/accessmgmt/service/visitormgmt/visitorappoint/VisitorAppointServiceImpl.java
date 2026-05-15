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
import java.util.UUID;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.accessmgmt.enums.ErrorCodeConstants.*;

@Service
@Validated
public class VisitorAppointServiceImpl implements VisitorAppointService {

    @Resource
    private VisitorAppointMapper visitorAppointMapper;

    @Override
    public PageResult<VisitorAppointRespVO> getVisitorAppointPage(VisitorAppointPageReqVO pageReqVO) {
        PageResult<VisitorAppointDO> pageResult = visitorAppointMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult, VisitorAppointRespVO.class);
    }

    @Override
    public VisitorAppointRespVO getVisitorAppoint(Long id) {
        VisitorAppointDO entity = visitorAppointMapper.selectById(id);
        if (entity == null) {
            throw exception(VISITOR_APPOINT_NOT_EXISTS);
        }
        return BeanUtils.toBean(entity, VisitorAppointRespVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createVisitorAppoint(VisitorAppointCreateReqVO createReqVO) {
        VisitorAppointDO entity = BeanUtils.toBean(createReqVO, VisitorAppointDO.class);
        entity.setVisitTime(Instant.ofEpochSecond(createReqVO.getVisitTime()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime());
        entity.setAppointStatus("待审核");
        visitorAppointMapper.insert(entity);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean auditVisitorAppoint(VisitorAppointAuditReqVO reqVO) {
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VisitorAppointGenerateRespVO generateVisitorAppoint(VisitorAppointGenerateReqVO reqVO) {
        VisitorAppointDO exist = visitorAppointMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(VISITOR_APPOINT_NOT_EXISTS);
        }
        String ticket = "VST-" + LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"))
                + "-" + String.format("%03d", (int)(Math.random() * 999 + 1));
        VisitorAppointDO updateObj = new VisitorAppointDO();
        updateObj.setId(reqVO.getId());
        updateObj.setTicket(ticket);
        visitorAppointMapper.updateById(updateObj);

        VisitorAppointGenerateRespVO respVO = new VisitorAppointGenerateRespVO();
        respVO.setSuccess(true);
        respVO.setTicket(ticket);
        return respVO;
    }

    @Override
    public VisitorAppointVerifyRespVO verifyVisitorAppoint(VisitorAppointVerifyReqVO reqVO) {
        VisitorAppointDO entity = visitorAppointMapper.selectByTicket(reqVO.getTicket());
        VisitorAppointVerifyRespVO respVO = new VisitorAppointVerifyRespVO();
        if (entity == null) {
            respVO.setPass(false);
            respVO.setMsg("凭证无效");
            return respVO;
        }
        respVO.setPass(true);
        respVO.setVisitorName(entity.getVisitorName());
        respVO.setVisitCompany(entity.getVisitCompany());
        respVO.setMsg("验证通过，欢迎到访");
        return respVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean cancelVisitorAppoint(VisitorAppointCancelReqVO reqVO) {
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
        VisitorAppointChartRespVO chartVO = new VisitorAppointChartRespVO();
        chartVO.setDayTrendList(visitorAppointMapper.selectDayTrendList(startTime, endTime));
        chartVO.setCompanyCountList(visitorAppointMapper.selectCompanyCountList(startTime, endTime));
        return chartVO;
    }

}
