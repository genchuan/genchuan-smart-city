package cn.iocoder.yudao.module.industry.service.park.vas.parkreversesearch;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkreversesearch.vo.ParkReverseSearchPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkreversesearch.vo.ParkReverseSearchSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkreversesearch.ParkReverseSearchDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 反向寻车记录 Service 接口
 *
 * @author lxs
 */
public interface ParkReverseSearchService {

    /**
     * 创建反向寻车记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkReverseSearch(@Valid ParkReverseSearchSaveReqVO createReqVO);

    /**
     * 更新反向寻车记录
     *
     * @param updateReqVO 更新信息
     */
    void updateParkReverseSearch(@Valid ParkReverseSearchSaveReqVO updateReqVO);

    /**
     * 删除反向寻车记录
     *
     * @param id 编号
     */
    void deleteParkReverseSearch(Long id);

    /**
     * 获得反向寻车记录
     *
     * @param id 编号
     * @return 反向寻车记录
     */
    ParkReverseSearchDO getParkReverseSearch(Long id);

    /**
     * 获得反向寻车记录分页
     *
     * @param pageReqVO 分页查询
     * @return 反向寻车记录分页
     */
    PageResult<ParkReverseSearchDO> getParkReverseSearchPage(ParkReverseSearchPageReqVO pageReqVO);

}
