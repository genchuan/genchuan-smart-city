package cn.iocoder.yudao.module.datacenter.service.evaluate.inspectionstatistics;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspectionstatistics.vo.InspectionStatisticsPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspectionstatistics.vo.InspectionStatisticsSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.inspectionstatistics.InspectionStatisticsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.inspectionstatistics.InspectionStatisticsMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 环卫考核统计结果 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class InspectionStatisticsServiceImpl implements InspectionStatisticsService {

    @Resource
    private InspectionStatisticsMapper inspectionStatisticsMapper;

    @Override
    public Long createInspectionStatistics(InspectionStatisticsSaveReqVO createReqVO) {
        // 插入
        InspectionStatisticsDO inspectionStatistics = BeanUtils.toBean(createReqVO, InspectionStatisticsDO.class);
        inspectionStatisticsMapper.insert(inspectionStatistics);
        // 返回
        return inspectionStatistics.getId();
    }

    @Override
    public void updateInspectionStatistics(InspectionStatisticsSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectionStatisticsExists(updateReqVO.getId());
        // 更新
        InspectionStatisticsDO updateObj = BeanUtils.toBean(updateReqVO, InspectionStatisticsDO.class);
        inspectionStatisticsMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectionStatistics(Long id) {
        // 校验存在
        validateInspectionStatisticsExists(id);
        // 删除
        inspectionStatisticsMapper.deleteById(id);
    }

    private void validateInspectionStatisticsExists(Long id) {
        if (inspectionStatisticsMapper.selectById(id) == null) {
            throw exception(INSPECTION_STATISTICS_NOT_EXISTS);
        }
    }

    @Override
    public InspectionStatisticsDO getInspectionStatistics(Long id) {
        return inspectionStatisticsMapper.selectById(id);
    }

    @Override
    public PageResult<InspectionStatisticsDO> getInspectionStatisticsPage(InspectionStatisticsPageReqVO pageReqVO) {
        return inspectionStatisticsMapper.selectPage(pageReqVO);
    }

}