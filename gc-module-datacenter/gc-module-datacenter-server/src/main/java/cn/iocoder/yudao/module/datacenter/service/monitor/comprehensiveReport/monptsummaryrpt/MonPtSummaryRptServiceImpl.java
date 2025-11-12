package cn.iocoder.yudao.module.datacenter.service.monitor.comprehensiveReport.monptsummaryrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.monptsummaryrpt.vo.MonPtSummaryRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.monptsummaryrpt.vo.MonPtSummaryRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.comprehensiveReport.monptsummaryrpt.MonPtSummaryRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.comprehensiveReport.monptsummaryrpt.MonPtSummaryRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 监测点位汇总报表 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class MonPtSummaryRptServiceImpl implements MonPtSummaryRptService {

    @Resource
    private MonPtSummaryRptMapper monPtSummaryRptMapper;

    @Override
    public Long createMonPtSummaryRpt(MonPtSummaryRptSaveReqVO createReqVO) {
        // 插入
        MonPtSummaryRptDO monPtSummaryRpt = BeanUtils.toBean(createReqVO, MonPtSummaryRptDO.class);
        monPtSummaryRptMapper.insert(monPtSummaryRpt);
        // 返回
        return monPtSummaryRpt.getId();
    }

    @Override
    public void updateMonPtSummaryRpt(MonPtSummaryRptSaveReqVO updateReqVO) {
        // 校验存在
        validateMonPtSummaryRptExists(updateReqVO.getId());
        // 更新
        MonPtSummaryRptDO updateObj = BeanUtils.toBean(updateReqVO, MonPtSummaryRptDO.class);
        monPtSummaryRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteMonPtSummaryRpt(Long id) {
        // 校验存在
        validateMonPtSummaryRptExists(id);
        // 删除
        monPtSummaryRptMapper.deleteById(id);
    }

    private void validateMonPtSummaryRptExists(Long id) {
        if (monPtSummaryRptMapper.selectById(id) == null) {
            throw exception(MON_PT_SUMMARY_RPT_NOT_EXISTS);
        }
    }

    @Override
    public MonPtSummaryRptDO getMonPtSummaryRpt(Long id) {
        return monPtSummaryRptMapper.selectById(id);
    }

    @Override
    public PageResult<MonPtSummaryRptDO> getMonPtSummaryRptPage(MonPtSummaryRptPageReqVO pageReqVO) {
        return monPtSummaryRptMapper.selectPage(pageReqVO);
    }

}