package cn.iocoder.yudao.module.smartcity.service.municipalpublicutilities;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.municipalpublicutilities.vo.MunicipalPublicUtilitiesPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.municipalpublicutilities.vo.MunicipalPublicUtilitiesSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.municipalpublicutilities.MunicipalPublicUtilitiesDO;
import jakarta.validation.Valid;

/**
 * 市政公用 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface MunicipalPublicUtilitiesService {

    /**
     * 创建市政公用
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMunicipalPublicUtilities(@Valid MunicipalPublicUtilitiesSaveReqVO createReqVO);

    /**
     * 更新市政公用
     *
     * @param updateReqVO 更新信息
     */
    void updateMunicipalPublicUtilities(@Valid MunicipalPublicUtilitiesSaveReqVO updateReqVO);

    /**
     * 删除市政公用
     *
     * @param id 编号
     */
    void deleteMunicipalPublicUtilities(Long id);

    /**
     * 获得市政公用
     *
     * @param id 编号
     * @return 市政公用
     */
    MunicipalPublicUtilitiesDO getMunicipalPublicUtilities(Long id);

    /**
     * 获得市政公用分页
     *
     * @param pageReqVO 分页查询
     * @return 市政公用分页
     */
    PageResult<MunicipalPublicUtilitiesDO> getMunicipalPublicUtilitiesPage(MunicipalPublicUtilitiesPageReqVO pageReqVO);

}