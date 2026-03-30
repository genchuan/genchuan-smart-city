package cn.iocoder.yudao.module.evaluate.service.platformreport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.platformreport.vo.PlatformReportPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.platformreport.vo.PlatformReportSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.platformreport.PlatformReportDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.platformreport.PlatformReportMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.PLATFORM_REPORT_NOT_EXISTS;

/**
 * 平台上报 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PlatformReportServiceImpl implements PlatformReportService {

    @Resource
    private PlatformReportMapper platformReportMapper;

    @Override
    public Long createPlatformReport(PlatformReportSaveReqVO createReqVO) {
        // 插入
        PlatformReportDO platformReport = BeanUtils.toBean(createReqVO, PlatformReportDO.class);
        platformReportMapper.insert(platformReport);
        // 返回
        return platformReport.getId();
    }

    @Override
    public void updatePlatformReport(PlatformReportSaveReqVO updateReqVO) {
        // 校验存在
        validatePlatformReportExists(updateReqVO.getId());
        // 更新
        PlatformReportDO updateObj = BeanUtils.toBean(updateReqVO, PlatformReportDO.class);
        platformReportMapper.updateById(updateObj);
    }

    @Override
    public void deletePlatformReport(Long id) {
        // 校验存在
        validatePlatformReportExists(id);
        // 删除
        platformReportMapper.deleteById(id);
    }

    private void validatePlatformReportExists(Long id) {
        if (platformReportMapper.selectById(id) == null) {
            throw exception(PLATFORM_REPORT_NOT_EXISTS);
        }
    }

    @Override
    public PlatformReportDO getPlatformReport(Long id) {
        return platformReportMapper.selectById(id);
    }

    @Override
    public PageResult<PlatformReportDO> getPlatformReportPage(PlatformReportPageReqVO pageReqVO) {
        return platformReportMapper.selectPage(pageReqVO);
    }

}