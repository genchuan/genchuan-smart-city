package cn.iocoder.yudao.module.kitchen.service.enterpriseinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.kitchen.controller.admin.enterpriseinfo.vo.EnterpriseInfoPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.enterpriseinfo.vo.EnterpriseInfoSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.enterpriseinfo.EnterpriseInfoDO;
import jakarta.validation.Valid;

/**
 * 企业信息 Service 接口
 *
 * @author 亘川智城
 */
public interface EnterpriseInfoService {

    /**
     * 创建企业信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEnterpriseInfo(@Valid EnterpriseInfoSaveReqVO createReqVO);

    /**
     * 更新企业信息
     *
     * @param updateReqVO 更新信息
     */
    void updateEnterpriseInfo(@Valid EnterpriseInfoSaveReqVO updateReqVO);

    /**
     * 删除企业信息
     *
     * @param id 编号
     */
    void deleteEnterpriseInfo(Long id);

    /**
     * 获得企业信息
     *
     * @param id 编号
     * @return 企业信息
     */
    EnterpriseInfoDO getEnterpriseInfo(Long id);

    /**
     * 获得企业信息分页
     *
     * @param pageReqVO 分页查询
     * @return 企业信息分页
     */
    PageResult<EnterpriseInfoDO> getEnterpriseInfoPage(EnterpriseInfoPageReqVO pageReqVO);

}
