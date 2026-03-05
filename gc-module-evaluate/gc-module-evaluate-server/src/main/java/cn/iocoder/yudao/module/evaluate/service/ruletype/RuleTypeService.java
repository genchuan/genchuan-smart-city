package cn.iocoder.yudao.module.evaluate.service.ruletype;

import cn.iocoder.yudao.module.evaluate.controller.admin.sys.ruletype.vo.RuleTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.ruletype.vo.RuleTypeSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.sys.ruletype.RuleTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 规则类型字典 Service 接口
 *
 * @author 亘川智城
 */
public interface RuleTypeService {

    /**
     * 创建规则类型字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRuleType(@Valid RuleTypeSaveReqVO createReqVO);

    /**
     * 更新规则类型字典
     *
     * @param updateReqVO 更新信息
     */
    void updateRuleType(@Valid RuleTypeSaveReqVO updateReqVO);

    /**
     * 删除规则类型字典
     *
     * @param id 编号
     */
    void deleteRuleType(Long id);

    /**
     * 获得规则类型字典
     *
     * @param id 编号
     * @return 规则类型字典
     */
    RuleTypeDO getRuleType(Long id);

    /**
     * 获得规则类型字典分页
     *
     * @param pageReqVO 分页查询
     * @return 规则类型字典分页
     */
    PageResult<RuleTypeDO> getRuleTypePage(RuleTypePageReqVO pageReqVO);

}