package cn.iocoder.yudao.module.waterdetection.service.gb5749standard;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.gb5749standard.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.gb5749standard.Gb5749StandardDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 《生活饮用水卫生标准》GB 5749-2022标准 Service 接口
 *
 * @author 朱聪权
 */
public interface Gb5749StandardService {

    /**
     * 创建《生活饮用水卫生标准》GB 5749-2022标准
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGb5749Standard(@Valid Gb5749StandardSaveReqVO createReqVO);

    /**
     * 更新《生活饮用水卫生标准》GB 5749-2022标准
     *
     * @param updateReqVO 更新信息
     */
    void updateGb5749Standard(@Valid Gb5749StandardSaveReqVO updateReqVO);

    /**
     * 删除《生活饮用水卫生标准》GB 5749-2022标准
     *
     * @param id 编号
     */
    void deleteGb5749Standard(Long id);

    /**
     * 获得《生活饮用水卫生标准》GB 5749-2022标准
     *
     * @param id 编号
     * @return 《生活饮用水卫生标准》GB 5749-2022标准
     */
    Gb5749StandardDO getGb5749Standard(Long id);

    /**
     * 获得《生活饮用水卫生标准》GB 5749-2022标准分页
     *
     * @param pageReqVO 分页查询
     * @return 《生活饮用水卫生标准》GB 5749-2022标准分页
     */
    PageResult<Gb5749StandardDO> getGb5749StandardPage(Gb5749StandardPageReqVO pageReqVO);

}