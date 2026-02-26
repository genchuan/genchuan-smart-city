package cn.iocoder.yudao.module.envir.service.personstatus;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envir.controller.admin.personstatus.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.personstatus.PersonStatusDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

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