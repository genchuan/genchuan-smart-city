package cn.iocoder.yudao.module.envirhealth.service.user.personstatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.personstatus.PersonStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.personstatus.PersonStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.PersonStatusDO;
import jakarta.validation.Valid;

/**
 * 人员状态字典 Service 接口
 *
 * @author 芋道源码
 */
public interface PersonStatusService {

    /**
     * 创建人员状态字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPersonStatus(@Valid PersonStatusSaveReqVO createReqVO);

    /**
     * 更新人员状态字典
     *
     * @param updateReqVO 更新信息
     */
    void updatePersonStatus(@Valid PersonStatusSaveReqVO updateReqVO);

    /**
     * 删除人员状态字典
     *
     * @param id 编号
     */
    void deletePersonStatus(Long id);

    /**
     * 获得人员状态字典
     *
     * @param id 编号
     * @return 人员状态字典
     */
    PersonStatusDO getPersonStatus(Long id);

    /**
     * 获得人员状态字典分页
     *
     * @param pageReqVO 分页查询
     * @return 人员状态字典分页
     */
    PageResult<PersonStatusDO> getPersonStatusPage(PersonStatusPageReqVO pageReqVO);

}