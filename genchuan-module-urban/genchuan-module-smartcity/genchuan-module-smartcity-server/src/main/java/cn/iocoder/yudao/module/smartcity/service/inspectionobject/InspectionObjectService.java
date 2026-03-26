package cn.iocoder.yudao.module.smartcity.service.inspectionobject;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionobject.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectionobject.InspectionObjectDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 双随机行政检查 Service 接口
 *
 * @author 朱聪权
 */
public interface InspectionObjectService {

    /**
     * 创建双随机行政检查
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectionObject(@Valid InspectionObjectSaveReqVO createReqVO);

    /**
     * 更新双随机行政检查
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectionObject(@Valid InspectionObjectSaveReqVO updateReqVO);

    /**
     * 删除双随机行政检查
     *
     * @param id 编号
     */
    void deleteInspectionObject(Long id);

    /**
     * 获得双随机行政检查
     *
     * @param id 编号
     * @return 双随机行政检查
     */
    InspectionObjectDO getInspectionObject(Long id);

    /**
     * 获得双随机行政检查分页
     *
     * @param pageReqVO 分页查询
     * @return 双随机行政检查分页
     */
    PageResult<InspectionObjectDO> getInspectionObjectPage(InspectionObjectPageReqVO pageReqVO);

}