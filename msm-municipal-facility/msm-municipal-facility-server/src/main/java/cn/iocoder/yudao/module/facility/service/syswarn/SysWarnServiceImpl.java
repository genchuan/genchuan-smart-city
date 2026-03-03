package cn.iocoder.yudao.module.facility.service.syswarn;

import cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo.SysWarnPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo.SysWarnRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo.SysWarnSaveReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.syswarn.SysWarnDO;
import cn.iocoder.yudao.module.facility.dal.mysql.syswarn.SysWarnMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.facility.enums.ErrorCodeConstants.*;

/**
 * 通用预警 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class SysWarnServiceImpl implements SysWarnService {

    @Resource
    private SysWarnMapper sysWarnMapper;

    @Override
    public Long createSysWarn(SysWarnSaveReqVO createReqVO) {
        // 插入
        SysWarnDO sysWarn = BeanUtils.toBean(createReqVO, SysWarnDO.class);
        sysWarnMapper.insert(sysWarn);
        // 返回
        return sysWarn.getId();
    }

    @Override
    public void updateSysWarn(SysWarnSaveReqVO updateReqVO) {
        // 校验存在
        validateSysWarnExists(updateReqVO.getId());
        // 更新
        SysWarnDO updateObj = BeanUtils.toBean(updateReqVO, SysWarnDO.class);
        sysWarnMapper.updateById(updateObj);
    }

    @Override
    public void deleteSysWarn(Long id) {
        // 校验存在
        validateSysWarnExists(id);
        // 删除
        sysWarnMapper.deleteById(id);
    }

    private void validateSysWarnExists(Long id) {
        if (sysWarnMapper.selectById(id) == null) {
            throw exception(SYS_WARN_NOT_EXISTS);
        }
    }

    @Override
    public SysWarnDO getSysWarn(Long id) {
        return sysWarnMapper.selectById(id);
    }

//    @Override
//    public PageResult<SysWarnDO> getSysWarnPage(SysWarnPageReqVO pageReqVO) {
//        return sysWarnMapper.selectPage(pageReqVO);
//    }

    @Override
    public PageResult<SysWarnRespVO> getSysWarnPage(SysWarnPageReqVO pageReqVO) {

        // 1. 查询 DO 分页数据
        PageResult<SysWarnDO> pageResult = sysWarnMapper.getSysWarnPage(pageReqVO);

        // 2. DO -> VO
        PageResult<SysWarnRespVO> voPage =
                BeanUtils.toBean(pageResult, SysWarnRespVO.class);

        // 3. 计算剩余时间（非数据库字段）
        voPage.getList().forEach(vo -> {
            vo.setRemainTime(
                    calcRemainTime(vo.getTriggerTime(), vo.getDealLimit())
            );
        });

        return voPage;
    }


    /**
     * 计算预警剩余时间（小时，保留 2 位小数）
     *
     * 规则：
     * (trigger_time + deal_limit) - now
     */
    private String calcRemainTime(LocalDateTime triggerTime, BigDecimal dealLimit) {
        if (triggerTime == null || dealLimit == null) {
            return "--";
        }

        // 触发时间 + 处置时限
        LocalDateTime deadline = triggerTime.plusMinutes(
                dealLimit.multiply(BigDecimal.valueOf(60)).longValue()
        );

        long minutes = Duration.between(LocalDateTime.now(), deadline).toMinutes();
        if (minutes <= 0) {
            return "0";
        }

        return BigDecimal.valueOf(minutes)
                .divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP)
                .toPlainString();
    }
}
