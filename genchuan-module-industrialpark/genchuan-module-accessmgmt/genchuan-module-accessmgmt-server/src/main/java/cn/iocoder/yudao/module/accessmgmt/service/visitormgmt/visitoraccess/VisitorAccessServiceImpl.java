package cn.iocoder.yudao.module.accessmgmt.service.visitormgmt.visitoraccess;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitoraccess.vo.*;
import cn.iocoder.yudao.module.accessmgmt.dal.dataobject.visitormgmt.visitoraccess.VisitorAccessDO;
import cn.iocoder.yudao.module.accessmgmt.dal.mysql.visitormgmt.visitoraccess.VisitorAccessMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.accessmgmt.enums.ErrorCodeConstants.VISITOR_ACCESS_NOT_EXISTS;

/**
 * 访客通行 Service 实现类
 * <p>
 * 提供访客通行的全流程业务：分页查询、凭证核验、通行管控（放行/禁行）、提醒、数据导出及区域分布统计。
 *
 * @author 亘川智城
 */
@Service
@Validated
public class VisitorAccessServiceImpl implements VisitorAccessService {

    @Resource
    private VisitorAccessMapper visitorAccessMapper;

    // ==================== 通行查询 ====================

    @Override
    public PageResult<VisitorAccessRespVO> getVisitorAccessPage(VisitorAccessPageReqVO pageReqVO) {
        // 分页查询，Mapper 层按 visitorName(模糊)/accessArea(精确)/ticketStatus(精确)/accessStatus(精确)/accessTime 范围动态条件筛选，按主键倒序
        PageResult<VisitorAccessDO> pageResult = visitorAccessMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult, VisitorAccessRespVO.class);
    }

    @Override
    public VisitorAccessRespVO getVisitorAccess(Long id) {
        // 按主键查单条，不存在抛 VISITOR_ACCESS_NOT_EXISTS 业务异常
        VisitorAccessDO entity = visitorAccessMapper.selectById(id);
        if (entity == null) {
            throw exception(VISITOR_ACCESS_NOT_EXISTS);
        }
        return BeanUtils.toBean(entity, VisitorAccessRespVO.class);
    }

    // ==================== 凭证核验 ====================

    @Override
    public VisitorAccessCheckRespVO checkVisitorAccess(VisitorAccessCheckReqVO reqVO) {
        // 根据预约ID查询通行记录（LambdaQueryWrapper 精确匹配 appointId）
        VisitorAccessDO entity = visitorAccessMapper.selectOne(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<VisitorAccessDO>()
                        .eq(VisitorAccessDO::getAppointId, reqVO.getAppointId()));
        VisitorAccessCheckRespVO respVO = new VisitorAccessCheckRespVO();
        if (entity == null) {
            // 未找到记录 → 核验不通过
            respVO.setPass(false);
            respVO.setMsg("未找到对应的访客通行记录");
            return respVO;
        }
        // 找到记录 → 核验通过
        respVO.setPass(true);
        respVO.setMsg("凭证核验通过");
        return respVO;
    }

    // ==================== 通行管控（放行 / 禁行） ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean passVisitorAccess(VisitorAccessPassReqVO reqVO) {
        // 放行：校验记录存在 → accessStatus → "已放行"
        VisitorAccessDO exist = visitorAccessMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(VISITOR_ACCESS_NOT_EXISTS);
        }
        VisitorAccessDO updateObj = new VisitorAccessDO();
        updateObj.setId(reqVO.getId());
        updateObj.setAccessStatus("已放行");
        visitorAccessMapper.updateById(updateObj);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean blockVisitorAccess(VisitorAccessBlockReqVO reqVO) {
        // 禁行：校验记录存在 → accessStatus → "已禁行"，同时将禁行原因写入 checkResult
        VisitorAccessDO exist = visitorAccessMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(VISITOR_ACCESS_NOT_EXISTS);
        }
        VisitorAccessDO updateObj = new VisitorAccessDO();
        updateObj.setId(reqVO.getId());
        updateObj.setAccessStatus("已禁行");
        updateObj.setCheckResult(reqVO.getBlockReason());
        visitorAccessMapper.updateById(updateObj);
        return true;
    }

    // ==================== 提醒 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean remindVisitorAccess(VisitorAccessRemindReqVO reqVO) {
        // 提醒：校验记录存在 → 提醒内容写入 reserve1
        VisitorAccessDO exist = visitorAccessMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(VISITOR_ACCESS_NOT_EXISTS);
        }
        VisitorAccessDO updateObj = new VisitorAccessDO();
        updateObj.setId(reqVO.getId());
        updateObj.setReserve1(reqVO.getRemindContent());
        visitorAccessMapper.updateById(updateObj);
        return true;
    }

    // ==================== 数据导出 ====================

    @Override
    public List<VisitorAccessRespVO> getVisitorAccessList(VisitorAccessPageReqVO pageReqVO) {
        // 设置 PAGE_SIZE_NONE 绕过 MyBatis-Plus 分页限制，查询全量数据
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<VisitorAccessDO> pageResult = visitorAccessMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult.getList(), VisitorAccessRespVO.class);
    }

    // ==================== 统计态势 ====================

    @Override
    public VisitorAccessChartRespVO getVisitorAccessChart(Long startTime, Long endTime) {
        // 三维度聚合：各区域通行次数统计 + 通行时间趋势 + 凭证状态分布
        VisitorAccessChartRespVO chartVO = new VisitorAccessChartRespVO();
        chartVO.setAreaCountList(visitorAccessMapper.selectAreaCountList(startTime, endTime));
        chartVO.setTimeTrendList(visitorAccessMapper.selectTimeTrendList(startTime, endTime));
        chartVO.setTicketStatusList(visitorAccessMapper.selectTicketStatusList(startTime, endTime));
        return chartVO;
    }

}
