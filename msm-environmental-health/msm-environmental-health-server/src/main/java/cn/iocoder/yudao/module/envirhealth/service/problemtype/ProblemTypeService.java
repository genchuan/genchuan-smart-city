package cn.iocoder.yudao.module.envirhealth.service.problemtype;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.controller.admin.problemtype.vo.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.problemtype.ProblemTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

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

}