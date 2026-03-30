package cn.iocoder.yudao.module.waterdetection.service.invaliddata;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.invaliddata.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.invaliddata.InvalidDataDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 不合格数据处理 Service 接口
 *
 * @author zcq
 */
public interface InvalidDataService {

    /**
     * 创建不合格数据处理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInvalidData(@Valid InvalidDataSaveReqVO createReqVO);

    /**
     * 更新不合格数据处理
     *
     * @param updateReqVO 更新信息
     */
    void updateInvalidData(@Valid InvalidDataSaveReqVO updateReqVO);

    /**
     * 删除不合格数据处理
     *
     * @param id 编号
     */
    void deleteInvalidData(Long id);

    /**
     * 获得不合格数据处理
     *
     * @param id 编号
     * @return 不合格数据处理
     */
    InvalidDataDO getInvalidData(Long id);

    /**
     * 获得不合格数据处理分页
     *
     * @param pageReqVO 分页查询
     * @return 不合格数据处理分页
     */
    PageResult<InvalidDataDO> getInvalidDataPage(InvalidDataPageReqVO pageReqVO);

}