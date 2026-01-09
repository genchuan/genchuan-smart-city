package cn.iocoder.yudao.module.smartcity.service.componentinformation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.componentinformation.vo.ComponentInformationPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.componentinformation.vo.ComponentInformationSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.componentinformation.ComponentInformationDO;
import jakarta.validation.Valid;

/**
 * 部件信息 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface ComponentInformationService {

    /**
     * 创建部件信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createComponentInformation(@Valid ComponentInformationSaveReqVO createReqVO);

    /**
     * 更新部件信息
     *
     * @param updateReqVO 更新信息
     */
    void updateComponentInformation(@Valid ComponentInformationSaveReqVO updateReqVO);

    /**
     * 删除部件信息
     *
     * @param id 编号
     */
    void deleteComponentInformation(Long id);

    /**
     * 获得部件信息
     *
     * @param id 编号
     * @return 部件信息
     */
    ComponentInformationDO getComponentInformation(Long id);

    /**
     * 获得部件信息分页
     *
     * @param pageReqVO 分页查询
     * @return 部件信息分页
     */
    PageResult<ComponentInformationDO> getComponentInformationPage(ComponentInformationPageReqVO pageReqVO);

}