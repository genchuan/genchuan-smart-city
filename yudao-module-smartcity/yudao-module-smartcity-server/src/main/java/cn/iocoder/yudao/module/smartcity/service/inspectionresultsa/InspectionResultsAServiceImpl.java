package cn.iocoder.yudao.module.smartcity.service.inspectionresultsa;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionresultsa.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectionresultsa.InspectionResultsADO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.inspectionresultsa.InspectionResultsAMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 巡查结果 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class InspectionResultsAServiceImpl implements InspectionResultsAService {

    @Resource
    private InspectionResultsAMapper inspectionResultsAMapper;

    @Override
    public Long createInspectionResultsA(InspectionResultsASaveReqVO createReqVO) {
        // 插入
        InspectionResultsADO inspectionResultsA = BeanUtils.toBean(createReqVO, InspectionResultsADO.class);
        inspectionResultsAMapper.insert(inspectionResultsA);
        // 返回
        return inspectionResultsA.getId();
    }

    @Override
    public void updateInspectionResultsA(InspectionResultsASaveReqVO updateReqVO) {
        // 校验存在
        validateInspectionResultsAExists(updateReqVO.getId());
        // 更新
        InspectionResultsADO updateObj = BeanUtils.toBean(updateReqVO, InspectionResultsADO.class);
        inspectionResultsAMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectionResultsA(Long id) {
        // 校验存在
        validateInspectionResultsAExists(id);
        // 删除
        inspectionResultsAMapper.deleteById(id);
    }

    private void validateInspectionResultsAExists(Long id) {
        if (inspectionResultsAMapper.selectById(id) == null) {
            throw exception(INSPECTION_RESULTS_A_NOT_EXISTS);
        }
    }

    @Override
    public InspectionResultsADO getInspectionResultsA(Long id) {
        return inspectionResultsAMapper.selectById(id);
    }

    @Override
    public PageResult<InspectionResultsADO> getInspectionResultsAPage(InspectionResultsAPageReqVO pageReqVO) {
        return inspectionResultsAMapper.selectPage(pageReqVO);
    }

}