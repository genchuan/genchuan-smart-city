package cn.iocoder.yudao.module.park.service.park.user.enterpriseinformation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.user.enterpriseinformation.vo.EnterpriseInformationPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.enterpriseinformation.vo.EnterpriseInformationSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.enterpriseinformation.EnterpriseInformationDO;
import jakarta.validation.Valid;

/**
 * 企业信息 Service 接口
 *
 * @author 亘川智城
 */
public interface EnterpriseInformationService {

    /**
     * 创建企业信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEnterpriseInformation(@Valid EnterpriseInformationSaveReqVO createReqVO);

    /**
     * 更新企业信息
     *
     * @param updateReqVO 更新信息
     */
    void updateEnterpriseInformation(@Valid EnterpriseInformationSaveReqVO updateReqVO);

    /**
     * 删除企业信息
     *
     * @param id 编号
     */
    void deleteEnterpriseInformation(Long id);

    /**
     * 获得企业信息
     *
     * @param id 编号
     * @return 企业信息
     */
    EnterpriseInformationDO getEnterpriseInformation(Long id);

    /**
     * 获得企业信息分页
     *
     * @param pageReqVO 分页查询
     * @return 企业信息分页
     */
    PageResult<EnterpriseInformationDO> getEnterpriseInformationPage(EnterpriseInformationPageReqVO pageReqVO);

}
