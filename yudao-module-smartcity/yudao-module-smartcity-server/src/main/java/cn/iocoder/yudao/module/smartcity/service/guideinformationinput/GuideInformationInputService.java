package cn.iocoder.yudao.module.smartcity.service.guideinformationinput;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.guideinformationinput.vo.GuideInformationInputPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.guideinformationinput.vo.GuideInformationInputSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.guideinformationinput.GuideInformationInputDO;
import jakarta.validation.Valid;

/**
 * 指南信息录入 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface GuideInformationInputService {

    /**
     * 创建指南信息录入
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGuideInformationInput(@Valid GuideInformationInputSaveReqVO createReqVO);

    /**
     * 更新指南信息录入
     *
     * @param updateReqVO 更新信息
     */
    void updateGuideInformationInput(@Valid GuideInformationInputSaveReqVO updateReqVO);

    /**
     * 删除指南信息录入
     *
     * @param id 编号
     */
    void deleteGuideInformationInput(Long id);

    /**
     * 获得指南信息录入
     *
     * @param id 编号
     * @return 指南信息录入
     */
    GuideInformationInputDO getGuideInformationInput(Long id);

    /**
     * 获得指南信息录入分页
     *
     * @param pageReqVO 分页查询
     * @return 指南信息录入分页
     */
    PageResult<GuideInformationInputDO> getGuideInformationInputPage(GuideInformationInputPageReqVO pageReqVO);

}