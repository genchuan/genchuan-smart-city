package cn.iocoder.yudao.module.datacenter.service.policylegislation.polinterpretation;

import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.polinterpretation.vo.PolInterpretationPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.polinterpretation.vo.PolInterpretationSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.policylegislation.polinterpretation.PolInterpretationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 政策解读信息 Service 接口
 *
 * @author 亘川智城
 */
public interface PolInterpretationService {

    /**
     * 创建政策解读信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPolInterpretation(@Valid PolInterpretationSaveReqVO createReqVO);

    /**
     * 更新政策解读信息
     *
     * @param updateReqVO 更新信息
     */
    void updatePolInterpretation(@Valid PolInterpretationSaveReqVO updateReqVO);

    /**
     * 删除政策解读信息
     *
     * @param id 编号
     */
    void deletePolInterpretation(Long id);

    /**
     * 获得政策解读信息
     *
     * @param id 编号
     * @return 政策解读信息
     */
    PolInterpretationDO getPolInterpretation(Long id);

    /**
     * 获得政策解读信息分页
     *
     * @param pageReqVO 分页查询
     * @return 政策解读信息分页
     */
    PageResult<PolInterpretationDO> getPolInterpretationPage(PolInterpretationPageReqVO pageReqVO);

}