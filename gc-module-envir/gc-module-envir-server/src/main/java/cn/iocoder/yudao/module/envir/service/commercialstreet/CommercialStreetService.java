package cn.iocoder.yudao.module.envir.service.commercialstreet;

import java.util.*;

import cn.iocoder.yudao.module.envir.dal.dataobject.commercialstreet.CommercialStreetDetailDO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envir.controller.admin.commercialstreet.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.commercialstreet.CommercialStreetDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 商业街 Service 接口
 *
 * @author 芋道源码
 */
public interface CommercialStreetService {

    /**
     * 创建商业街
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCommercialStreet(@Valid CommercialStreetSaveReqVO createReqVO);

    /**
     * 更新商业街
     *
     * @param updateReqVO 更新信息
     */
    void updateCommercialStreet(@Valid CommercialStreetSaveReqVO updateReqVO);

    /**
     * 删除商业街
     *
     * @param id 编号
     */
    void deleteCommercialStreet(Long id);

    /**
     * 获得商业街
     *
     * @param id 编号
     * @return 商业街
     */
    CommercialStreetDO getCommercialStreet(Long id);

    /**
     * 获得商业街分页
     *
     * @param pageReqVO 分页查询
     * @return 商业街分页
     */
    PageResult<CommercialStreetDO> getCommercialStreetPage(CommercialStreetPageReqVO pageReqVO);

    /**
     * 获得商业街详情列表
     *
     * @return 商业街详情列表
     */
    List<CommercialStreetDetailDO> getCommercialStreetListDetail();
}