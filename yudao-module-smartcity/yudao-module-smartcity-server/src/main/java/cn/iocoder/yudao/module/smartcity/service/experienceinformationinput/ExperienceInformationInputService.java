package cn.iocoder.yudao.module.smartcity.service.experienceinformationinput;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.experienceinformationinput.vo.ExperienceInformationInputPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.experienceinformationinput.vo.ExperienceInformationInputSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.experienceinformationinput.ExperienceInformationInputDO;
import jakarta.validation.Valid;

/**
 * 经验信息录入 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface ExperienceInformationInputService {

    /**
     * 创建经验信息录入
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createExperienceInformationInput(@Valid ExperienceInformationInputSaveReqVO createReqVO);

    /**
     * 更新经验信息录入
     *
     * @param updateReqVO 更新信息
     */
    void updateExperienceInformationInput(@Valid ExperienceInformationInputSaveReqVO updateReqVO);

    /**
     * 删除经验信息录入
     *
     * @param id 编号
     */
    void deleteExperienceInformationInput(Long id);

    /**
     * 获得经验信息录入
     *
     * @param id 编号
     * @return 经验信息录入
     */
    ExperienceInformationInputDO getExperienceInformationInput(Long id);

    /**
     * 获得经验信息录入分页
     *
     * @param pageReqVO 分页查询
     * @return 经验信息录入分页
     */
    PageResult<ExperienceInformationInputDO> getExperienceInformationInputPage(ExperienceInformationInputPageReqVO pageReqVO);

}