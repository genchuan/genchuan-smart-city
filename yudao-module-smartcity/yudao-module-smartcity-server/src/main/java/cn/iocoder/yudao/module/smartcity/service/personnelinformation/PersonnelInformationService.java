package cn.iocoder.yudao.module.smartcity.service.personnelinformation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.personnelinformation.vo.PersonnelInformationPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.personnelinformation.vo.PersonnelInformationSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.personnelinformation.PersonnelInformationDO;
import jakarta.validation.Valid;

/**
 * 人员信息 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface PersonnelInformationService {

    /**
     * 创建人员信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPersonnelInformation(@Valid PersonnelInformationSaveReqVO createReqVO);

    /**
     * 更新人员信息
     *
     * @param updateReqVO 更新信息
     */
    void updatePersonnelInformation(@Valid PersonnelInformationSaveReqVO updateReqVO);

    /**
     * 删除人员信息
     *
     * @param id 编号
     */
    void deletePersonnelInformation(Long id);

    /**
     * 获得人员信息
     *
     * @param id 编号
     * @return 人员信息
     */
    PersonnelInformationDO getPersonnelInformation(Long id);

    /**
     * 获得人员信息分页
     *
     * @param pageReqVO 分页查询
     * @return 人员信息分页
     */
    PageResult<PersonnelInformationDO> getPersonnelInformationPage(PersonnelInformationPageReqVO pageReqVO);

}