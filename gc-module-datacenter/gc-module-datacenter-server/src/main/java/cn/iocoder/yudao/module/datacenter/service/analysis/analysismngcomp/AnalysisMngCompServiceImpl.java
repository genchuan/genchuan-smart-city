package cn.iocoder.yudao.module.datacenter.service.analysis.analysismngcomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismngcomp.vo.AnalysisMngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismngcomp.vo.AnalysisMngCompSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysismngcomp.AnalysisMngCompDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.analysis.analysismngcomp.AnalysisMngCompMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;
/**
 * 按管理部件分析研判统计 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AnalysisMngCompServiceImpl implements AnalysisMngCompService {

    @Resource
    private AnalysisMngCompMapper analysisMngCompMapper;

    @Override
    public Long createAnalysisMngComp(AnalysisMngCompSaveReqVO createReqVO) {
        // 插入
        AnalysisMngCompDO analysisMngComp = BeanUtils.toBean(createReqVO, AnalysisMngCompDO.class);
        analysisMngCompMapper.insert(analysisMngComp);
        // 返回
        return analysisMngComp.getId();
    }

    @Override
    public void updateAnalysisMngComp(AnalysisMngCompSaveReqVO updateReqVO) {
        // 校验存在
        validateAnalysisMngCompExists(updateReqVO.getId());
        // 更新
        AnalysisMngCompDO updateObj = BeanUtils.toBean(updateReqVO, AnalysisMngCompDO.class);
        analysisMngCompMapper.updateById(updateObj);
    }

    @Override
    public void deleteAnalysisMngComp(Long id) {
        // 校验存在
        validateAnalysisMngCompExists(id);
        // 删除
        analysisMngCompMapper.deleteById(id);
    }

    private void validateAnalysisMngCompExists(Long id) {
        if (analysisMngCompMapper.selectById(id) == null) {
            throw exception(ANALYSIS_MNG_COMP_NOT_EXISTS);
        }
    }

    @Override
    public AnalysisMngCompDO getAnalysisMngComp(Long id) {
        return analysisMngCompMapper.selectById(id);
    }

    @Override
    public PageResult<AnalysisMngCompDO> getAnalysisMngCompPage(AnalysisMngCompPageReqVO pageReqVO) {
        return analysisMngCompMapper.selectPage(pageReqVO);
    }

}