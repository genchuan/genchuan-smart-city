package cn.iocoder.yudao.module.smartcity.service.institutionalinformationinput;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.institutionalinformationinput.vo.InstitutionalInformationInputPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.institutionalinformationinput.vo.InstitutionalInformationInputSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.institutionalinformationinput.InstitutionalInformationInputDO;
import jakarta.validation.Valid;

/**
 * 机构信息录入 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface InstitutionalInformationInputService {

    /**
     * 创建机构信息录入
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInstitutionalInformationInput(@Valid InstitutionalInformationInputSaveReqVO createReqVO);

    /**
     * 更新机构信息录入
     *
     * @param updateReqVO 更新信息
     */
    void updateInstitutionalInformationInput(@Valid InstitutionalInformationInputSaveReqVO updateReqVO);

    /**
     * 删除机构信息录入
     *
     * @param id 编号
     */
    void deleteInstitutionalInformationInput(Long id);

    /**
     * 获得机构信息录入
     *
     * @param id 编号
     * @return 机构信息录入
     */
    InstitutionalInformationInputDO getInstitutionalInformationInput(Long id);

    /**
     * 获得机构信息录入分页
     *
     * @param pageReqVO 分页查询
     * @return 机构信息录入分页
     */
    PageResult<InstitutionalInformationInputDO> getInstitutionalInformationInputPage(InstitutionalInformationInputPageReqVO pageReqVO);

}