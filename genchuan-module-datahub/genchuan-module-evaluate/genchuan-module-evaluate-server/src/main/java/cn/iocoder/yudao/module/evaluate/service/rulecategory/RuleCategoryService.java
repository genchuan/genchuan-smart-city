package cn.iocoder.yudao.module.evaluate.service.rulecategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.RuleCategoryPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.RuleCategoryRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.RuleCategorySaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.rulecategory.RuleCategoryDO;
import jakarta.validation.Valid;

/**
 * 规则分类管理 Service 接口
 *
 * @author 亘川智城
 */
public interface RuleCategoryService {

    /**
     * 创建规则分类管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRuleCategory(@Valid RuleCategorySaveReqVO createReqVO);

    /**
     * 更新规则分类管理
     *
     * @param updateReqVO 更新信息
     */
    void updateRuleCategory(@Valid RuleCategorySaveReqVO updateReqVO);

    /**
     * 删除规则分类管理
     *
     * @param id 编号
     */
    void deleteRuleCategory(Long id);

    /**
     * 获得规则分类管理
     *
     * @param id 编号
     * @return 规则分类管理
     */
    RuleCategoryDO getRuleCategory(Long id);

    /**
     * 获得规则分类管理分页
     *
     * @param pageReqVO 分页查询
     * @return 规则分类管理分页
     */
    PageResult<RuleCategoryDO> getRuleCategoryPage(RuleCategoryPageReqVO pageReqVO);

    /**
     * 获得规则分类管理分页（全部/启用/停用），关联查询
     *
     * @param pageReqVO 分页查询
     * @return 规则分类管理分页（含关联表名称）
     */
    PageResult<RuleCategoryRespVO> getRuleCategoryJoinPage(RuleCategoryPageReqVO pageReqVO);

}