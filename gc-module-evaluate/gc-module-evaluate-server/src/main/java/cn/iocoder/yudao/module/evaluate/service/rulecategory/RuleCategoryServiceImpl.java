package cn.iocoder.yudao.module.evaluate.service.rulecategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.RuleCategoryPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.RuleCategoryRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.RuleCategorySaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.rulecategory.RuleCategoryDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.rulecategory.RuleCategoryMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.RULE_CATEGORY_NOT_EXISTS;

/**
 * 规则分类 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class RuleCategoryServiceImpl implements RuleCategoryService {

    @Resource
    private RuleCategoryMapper ruleCategoryMapper;

    @Override
    public Long createRuleCategory(RuleCategorySaveReqVO createReqVO) {
        // 插入
        RuleCategoryDO ruleCategory = BeanUtils.toBean(createReqVO, RuleCategoryDO.class);
        ruleCategoryMapper.insert(ruleCategory);
        // 返回
        return ruleCategory.getId();
    }

    @Override
    public void updateRuleCategory(RuleCategorySaveReqVO updateReqVO) {
        // 校验存在
        validateRuleCategoryExists(updateReqVO.getId());
        // 更新
        RuleCategoryDO updateObj = BeanUtils.toBean(updateReqVO, RuleCategoryDO.class);
        ruleCategoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteRuleCategory(Long id) {
        // 校验存在
        validateRuleCategoryExists(id);
        // 删除
        ruleCategoryMapper.deleteById(id);
    }

    private void validateRuleCategoryExists(Long id) {
        if (ruleCategoryMapper.selectById(id) == null) {
            throw exception(RULE_CATEGORY_NOT_EXISTS);
        }
    }

    @Override
    public RuleCategoryDO getRuleCategory(Long id) {
        return ruleCategoryMapper.selectById(id);
    }

    @Override
    public PageResult<RuleCategoryDO> getRuleCategoryPage(RuleCategoryPageReqVO pageReqVO) {
        return ruleCategoryMapper.selectPage(pageReqVO);
    }

    @Override
    public Page<RuleCategoryRespVO> queryRulePage(Page<RuleCategoryRespVO> page, RuleCategoryPageReqVO query) {
        return null;
    }


    /**
     * 分页查询规则列表（评分规则 + 否决规则）
     */
    @Override
    public PageResult<RuleCategoryRespVO> getRuleCategoryPageList(RuleCategoryPageReqVO reqVO) {
        IPage<RuleCategoryRespVO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        IPage<RuleCategoryRespVO> pageResult = ruleCategoryMapper.selectRuleCategoryPage(page, reqVO);
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }
    @Override
    public PageResult<RuleCategoryRespVO> getRuleCategoryAllPage(RuleCategoryPageReqVO reqVO) {
        // 构建分页对象
        Page<RuleCategoryRespVO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        // 调用Mapper的联表分页查询
        Page<RuleCategoryRespVO> resultPage = ruleCategoryMapper.selectRuleCategoryAllPage(page, reqVO);
        // 转换为框架通用的PageResult
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }
}