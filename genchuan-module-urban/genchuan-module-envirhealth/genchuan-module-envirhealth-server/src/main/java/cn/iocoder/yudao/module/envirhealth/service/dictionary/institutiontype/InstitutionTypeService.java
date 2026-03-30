package cn.iocoder.yudao.module.envirhealth.service.dictionary.institutiontype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.institutiontype.vo.InstitutionTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.institutiontype.vo.InstitutionTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.InstitutionTypeDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 机构类型字典 Service 接口
 *
 * @author 芋道源码
 */
public interface InstitutionTypeService {

    /**
     * 创建机构类型字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInstitutionType(@Valid InstitutionTypeSaveReqVO createReqVO);

    /**
     * 更新机构类型字典
     *
     * @param updateReqVO 更新信息
     */
    void updateInstitutionType(@Valid InstitutionTypeSaveReqVO updateReqVO);

    /**
     * 删除机构类型字典
     *
     * @param id 编号
     */
    void deleteInstitutionType(Long id);

    /**
     * 获得机构类型字典
     *
     * @param id 编号
     * @return 机构类型字典
     */
    InstitutionTypeDO getInstitutionType(Long id);

    /**
     * 获得机构类型字典分页
     *
     * @param pageReqVO 分页查询
     * @return 机构类型字典分页
     */
    PageResult<InstitutionTypeDO> getInstitutionTypePage(InstitutionTypePageReqVO pageReqVO);

    /**
     * 获得机构类型下拉框选项
     * @return 下拉框选项列表
     */
    List<OptionVO> getInstitutionTypeOptions();
}