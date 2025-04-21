package cn.iocoder.yudao.module.smartcity.service.reportofillegalconstructions;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.reportofillegalconstructions.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.reportofillegalconstructions.ReportOfIllegalConstructionsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.reportofillegalconstructions.ReportOfIllegalConstructionsMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 违建上报 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class ReportOfIllegalConstructionsServiceImpl implements ReportOfIllegalConstructionsService {

    @Resource
    private ReportOfIllegalConstructionsMapper reportOfIllegalConstructionsMapper;

    @Override
    public Long createReportOfIllegalConstructions(ReportOfIllegalConstructionsSaveReqVO createReqVO) {
        // 插入
        ReportOfIllegalConstructionsDO reportOfIllegalConstructions = BeanUtils.toBean(createReqVO, ReportOfIllegalConstructionsDO.class);
        reportOfIllegalConstructionsMapper.insert(reportOfIllegalConstructions);
        // 返回
        return reportOfIllegalConstructions.getId();
    }

    @Override
    public void updateReportOfIllegalConstructions(ReportOfIllegalConstructionsSaveReqVO updateReqVO) {
        // 校验存在
        validateReportOfIllegalConstructionsExists(updateReqVO.getId());
        // 更新
        ReportOfIllegalConstructionsDO updateObj = BeanUtils.toBean(updateReqVO, ReportOfIllegalConstructionsDO.class);
        reportOfIllegalConstructionsMapper.updateById(updateObj);
    }

    @Override
    public void deleteReportOfIllegalConstructions(Long id) {
        // 校验存在
        validateReportOfIllegalConstructionsExists(id);
        // 删除
        reportOfIllegalConstructionsMapper.deleteById(id);
    }

    private void validateReportOfIllegalConstructionsExists(Long id) {
        if (reportOfIllegalConstructionsMapper.selectById(id) == null) {
            throw exception(REPORT_OF_ILLEGAL_CONSTRUCTIONS_NOT_EXISTS);
        }
    }

    @Override
    public ReportOfIllegalConstructionsDO getReportOfIllegalConstructions(Long id) {
        return reportOfIllegalConstructionsMapper.selectById(id);
    }

    @Override
    public PageResult<ReportOfIllegalConstructionsDO> getReportOfIllegalConstructionsPage(ReportOfIllegalConstructionsPageReqVO pageReqVO) {
        return reportOfIllegalConstructionsMapper.selectPage(pageReqVO);
    }

}