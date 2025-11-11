package cn.iocoder.yudao.module.datacenter.service.analysis.analysismngmatter;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismngmatter.vo.AnalysisMngMatterPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismngmatter.vo.AnalysisMngMatterSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysismngmatter.AnalysisMngMatterDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.analysis.analysismngmatter.AnalysisMngMatterMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;
/**
 * 按管理事项分析研判统计 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AnalysisMngMatterServiceImpl implements AnalysisMngMatterService {

    @Resource
    private AnalysisMngMatterMapper analysisMngMatterMapper;

    @Override
    public Long createAnalysisMngMatter(AnalysisMngMatterSaveReqVO createReqVO) {
        // 插入
        AnalysisMngMatterDO analysisMngMatter = BeanUtils.toBean(createReqVO, AnalysisMngMatterDO.class);
        analysisMngMatterMapper.insert(analysisMngMatter);
        // 返回
        return analysisMngMatter.getId();
    }

    @Override
    public void updateAnalysisMngMatter(AnalysisMngMatterSaveReqVO updateReqVO) {
        // 校验存在
        validateAnalysisMngMatterExists(updateReqVO.getId());
        // 更新
        AnalysisMngMatterDO updateObj = BeanUtils.toBean(updateReqVO, AnalysisMngMatterDO.class);
        analysisMngMatterMapper.updateById(updateObj);
    }

    @Override
    public void deleteAnalysisMngMatter(Long id) {
        // 校验存在
        validateAnalysisMngMatterExists(id);
        // 删除
        analysisMngMatterMapper.deleteById(id);
    }

    private void validateAnalysisMngMatterExists(Long id) {
        if (analysisMngMatterMapper.selectById(id) == null) {
            throw exception(ANALYSIS_MNG_MATTER_NOT_EXISTS);
        }
    }

    @Override
    public AnalysisMngMatterDO getAnalysisMngMatter(Long id) {
        return analysisMngMatterMapper.selectById(id);
    }

    @Override
    public PageResult<AnalysisMngMatterDO> getAnalysisMngMatterPage(AnalysisMngMatterPageReqVO pageReqVO) {
        return analysisMngMatterMapper.selectPage(pageReqVO);
    }

}