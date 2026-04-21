package cn.iocoder.yudao.module.inspectop.service.inspectreport;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspectreport.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspectreport.InspectReportDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.inspectreport.InspectReportMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

/**
 * 巡检上报 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class InspectReportServiceImpl implements InspectReportService {

    @Resource
    private InspectReportMapper inspectReportMapper;

    @Override
    public Long createInspectReport(InspectReportSaveReqVO createReqVO) {
        // 插入
        InspectReportDO inspectReport = BeanUtils.toBean(createReqVO, InspectReportDO.class);
        inspectReportMapper.insert(inspectReport);

        // 返回
        return inspectReport.getId();
    }

    @Override
    public void updateInspectReport(InspectReportSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectReportExists(updateReqVO.getId());
        // 更新
        InspectReportDO updateObj = BeanUtils.toBean(updateReqVO, InspectReportDO.class);
        inspectReportMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectReport(Long id) {
        // 校验存在
        validateInspectReportExists(id);
        // 删除
        inspectReportMapper.deleteById(id);
    }

    @Override
        public void deleteInspectReportListByIds(List<Long> ids) {
        // 删除
        inspectReportMapper.deleteByIds(ids);
        }


    private void validateInspectReportExists(Long id) {
        if (inspectReportMapper.selectById(id) == null) {
            throw exception(INSPECT_REPORT_NOT_EXISTS);
        }
    }

    @Override
    public InspectReportDO getInspectReport(Long id) {
        return inspectReportMapper.selectById(id);
    }

    @Override
    public PageResult<InspectReportDO> getInspectReportPage(InspectReportPageReqVO pageReqVO) {
        return inspectReportMapper.selectPage(pageReqVO);
    }

}