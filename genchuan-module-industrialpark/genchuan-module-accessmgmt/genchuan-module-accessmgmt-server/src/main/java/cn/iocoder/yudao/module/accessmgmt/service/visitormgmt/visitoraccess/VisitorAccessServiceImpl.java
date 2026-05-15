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
 *
 * @author 亘川智城
 */
@Service
@Validated
public class VisitorAccessServiceImpl implements VisitorAccessService {

    @Resource
    private VisitorAccessMapper visitorAccessMapper;

    @Override
    public PageResult<VisitorAccessRespVO> getVisitorAccessPage(VisitorAccessPageReqVO pageReqVO) {
        PageResult<VisitorAccessDO> pageResult = visitorAccessMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult, VisitorAccessRespVO.class);
    }

    @Override
    public VisitorAccessRespVO getVisitorAccess(Long id) {
        VisitorAccessDO entity = visitorAccessMapper.selectById(id);
        if (entity == null) {
            throw exception(VISITOR_ACCESS_NOT_EXISTS);
        }
        return BeanUtils.toBean(entity, VisitorAccessRespVO.class);
    }

    @Override
    public VisitorAccessCheckRespVO checkVisitorAccess(VisitorAccessCheckReqVO reqVO) {
        VisitorAccessDO entity = visitorAccessMapper.selectOne(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<VisitorAccessDO>()
                        .eq(VisitorAccessDO::getAppointId, reqVO.getAppointId()));
        VisitorAccessCheckRespVO respVO = new VisitorAccessCheckRespVO();
        if (entity == null) {
            respVO.setPass(false);
            respVO.setMsg("未找到对应的访客通行记录");
            return respVO;
        }
        respVO.setPass(true);
        respVO.setMsg("凭证核验通过");
        return respVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean passVisitorAccess(VisitorAccessPassReqVO reqVO) {
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean remindVisitorAccess(VisitorAccessRemindReqVO reqVO) {
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

    @Override
    public List<VisitorAccessRespVO> getVisitorAccessList(VisitorAccessPageReqVO pageReqVO) {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<VisitorAccessDO> pageResult = visitorAccessMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult.getList(), VisitorAccessRespVO.class);
    }

    @Override
    public VisitorAccessChartRespVO getVisitorAccessChart(String startTime, String endTime) {
        VisitorAccessChartRespVO chartVO = new VisitorAccessChartRespVO();
        chartVO.setAreaCountList(visitorAccessMapper.selectAreaCountList(startTime, endTime));
        chartVO.setTimeTrendList(visitorAccessMapper.selectTimeTrendList(startTime, endTime));
        chartVO.setTicketStatusList(visitorAccessMapper.selectTicketStatusList(startTime, endTime));
        return chartVO;
    }

}
