package cn.iocoder.yudao.module.usermerchant.service.userreport.cyclereport;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.usermerchant.controller.admin.userreport.cyclereport.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.userreport.cyclereport.CycleReportDO;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.userreport.cyclereport.CycleReportMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 周期报表存储 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class CycleReportServiceImpl implements CycleReportService {

    @Resource
    private CycleReportMapper cycleReportMapper;

    @Override
    public Long createCycleReport(CycleReportSaveReqVO createReqVO) {
        // 插入
        CycleReportDO cycleReport = BeanUtils.toBean(createReqVO, CycleReportDO.class);
        cycleReportMapper.insert(cycleReport);

        // 返回
        return cycleReport.getId();
    }

    @Override
    public void updateCycleReport(CycleReportSaveReqVO updateReqVO) {
        // 校验存在
        validateCycleReportExists(updateReqVO.getId());
        // 更新
        CycleReportDO updateObj = BeanUtils.toBean(updateReqVO, CycleReportDO.class);
        cycleReportMapper.updateById(updateObj);
    }

    @Override
    public void deleteCycleReport(Long id) {
        // 校验存在
        validateCycleReportExists(id);
        // 删除
        cycleReportMapper.deleteById(id);
    }

    @Override
        public void deleteCycleReportListByIds(List<Long> ids) {
        // 删除
        cycleReportMapper.deleteByIds(ids);
        }


    private void validateCycleReportExists(Long id) {
        if (cycleReportMapper.selectById(id) == null) {
            throw exception(CYCLE_REPORT_NOT_EXISTS);
        }
    }

    @Override
    public CycleReportDO getCycleReport(Long id) {
        return cycleReportMapper.selectById(id);
    }

    @Override
    public PageResult<CycleReportDO> getCycleReportPage(CycleReportPageReqVO pageReqVO) {
        return cycleReportMapper.selectPage(pageReqVO);
    }

}