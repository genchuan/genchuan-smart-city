package cn.iocoder.yudao.module.smartcity.service.probleminput;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.probleminput.vo.ProblemInputPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.probleminput.vo.ProblemInputSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.probleminput.ProblemInputDO;
import jakarta.validation.Valid;

/**
 * 问题录入 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface ProblemInputService {

    /**
     * 创建问题录入
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProblemInput(@Valid ProblemInputSaveReqVO createReqVO);

    /**
     * 更新问题录入
     *
     * @param updateReqVO 更新信息
     */
    void updateProblemInput(@Valid ProblemInputSaveReqVO updateReqVO);

    /**
     * 删除问题录入
     *
     * @param id 编号
     */
    void deleteProblemInput(Long id);

    /**
     * 获得问题录入
     *
     * @param id 编号
     * @return 问题录入
     */
    ProblemInputDO getProblemInput(Long id);

    /**
     * 获得问题录入分页
     *
     * @param pageReqVO 分页查询
     * @return 问题录入分页
     */
    PageResult<ProblemInputDO> getProblemInputPage(ProblemInputPageReqVO pageReqVO);

}