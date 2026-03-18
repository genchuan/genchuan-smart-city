package cn.iocoder.yudao.module.envirhealth.service.dictionary.problemtype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.problemtype.vo.ProblemTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.problemtype.vo.ProblemTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.ProblemTypeDO;
import cn.iocoder.yudao.module.envirhealth.util.vo.OptionVO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 问题类型字典表【通用复用】 Service 接口
 *
 * @author 芋道源码
 */
public interface ProblemTypeService {

    /**
     * 创建问题类型字典表【通用复用】
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProblemType(@Valid ProblemTypeSaveReqVO createReqVO);

    /**
     * 更新问题类型字典表【通用复用】
     *
     * @param updateReqVO 更新信息
     */
    void updateProblemType(@Valid ProblemTypeSaveReqVO updateReqVO);

    /**
     * 删除问题类型字典表【通用复用】
     *
     * @param id 编号
     */
    void deleteProblemType(Long id);

    /**
     * 获得问题类型字典表【通用复用】
     *
     * @param id 编号
     * @return 问题类型字典表【通用复用】
     */
    ProblemTypeDO getProblemType(Long id);

    /**
     * 获得问题类型字典表【通用复用】分页
     *
     * @param pageReqVO 分页查询
     * @return 问题类型字典表【通用复用】分页
     */
    PageResult<ProblemTypeDO> getProblemTypePage(ProblemTypePageReqVO pageReqVO);

    /**
     * 获得问题类型下拉框选项
     * @return 下拉框选项列表
     */
    List<OptionVO> getProblemTypeOptions();
}