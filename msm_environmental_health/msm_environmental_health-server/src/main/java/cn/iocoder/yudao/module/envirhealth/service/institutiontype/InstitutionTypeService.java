package cn.iocoder.yudao.module.envirhealth.service.institutiontype;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.controller.admin.institutiontype.vo.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.institutiontype.InstitutionTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

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

}