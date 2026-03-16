package cn.iocoder.yudao.module.evaluate.service.statreport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.statreport.vo.StatReportPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.statreport.vo.StatReportSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.statreport.StatReportDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.statreport.StatReportMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.STAT_REPORT_NOT_EXISTS;

/**
 * 统计分析报 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class StatReportServiceImpl implements StatReportService {

    @Resource
    private StatReportMapper statReportMapper;

    @Override
    public Long createStatReport(StatReportSaveReqVO createReqVO) {
        // 插入
        StatReportDO statReport = BeanUtils.toBean(createReqVO, StatReportDO.class);
        statReportMapper.insert(statReport);
        // 返回
        return statReport.getId();
    }

    @Override
    public void updateStatReport(StatReportSaveReqVO updateReqVO) {
        // 校验存在
        validateStatReportExists(updateReqVO.getId());
        // 更新
        StatReportDO updateObj = BeanUtils.toBean(updateReqVO, StatReportDO.class);
        statReportMapper.updateById(updateObj);
    }

    @Override
    public void deleteStatReport(Long id) {
        // 校验存在
        validateStatReportExists(id);
        // 删除
        statReportMapper.deleteById(id);
    }

    private void validateStatReportExists(Long id) {
        if (statReportMapper.selectById(id) == null) {
            throw exception(STAT_REPORT_NOT_EXISTS);
        }
    }

    @Override
    public StatReportDO getStatReport(Long id) {
        return statReportMapper.selectById(id);
    }

    @Override
    public PageResult<StatReportDO> getStatReportPage(StatReportPageReqVO pageReqVO) {
        return statReportMapper.selectPage(pageReqVO);
    }

}