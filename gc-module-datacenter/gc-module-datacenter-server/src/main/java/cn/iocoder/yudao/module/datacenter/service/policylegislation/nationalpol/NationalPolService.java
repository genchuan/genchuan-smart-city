package cn.iocoder.yudao.module.datacenter.service.policylegislation.nationalpol;

import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.nationalpol.vo.NationalPolPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.nationalpol.vo.NationalPolSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.policylegislation.nationalpol.NationalPolDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 国家政策信息 Service 接口
 *
 * @author 亘川智城
 */
public interface NationalPolService {

    /**
     * 创建国家政策信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createNationalPol(@Valid NationalPolSaveReqVO createReqVO);

    /**
     * 更新国家政策信息
     *
     * @param updateReqVO 更新信息
     */
    void updateNationalPol(@Valid NationalPolSaveReqVO updateReqVO);

    /**
     * 删除国家政策信息
     *
     * @param id 编号
     */
    void deleteNationalPol(Long id);

    /**
     * 获得国家政策信息
     *
     * @param id 编号
     * @return 国家政策信息
     */
    NationalPolDO getNationalPol(Long id);

    /**
     * 获得国家政策信息分页
     *
     * @param pageReqVO 分页查询
     * @return 国家政策信息分页
     */
    PageResult<NationalPolDO> getNationalPolPage(NationalPolPageReqVO pageReqVO);

}