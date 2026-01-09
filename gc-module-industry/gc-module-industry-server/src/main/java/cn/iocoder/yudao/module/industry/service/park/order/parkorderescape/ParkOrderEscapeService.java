package cn.iocoder.yudao.module.industry.service.park.order.parkorderescape;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderescape.vo.ParkOrderEscapePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderescape.vo.ParkOrderEscapeSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkorderescape.ParkOrderEscapeDO;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 逃费订单 Service 接口
 *
 * @author lxs
 */
public interface ParkOrderEscapeService {

    /**
     * 创建逃费订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrderEscape(@Valid ParkOrderEscapeSaveReqVO createReqVO);

    /**
     * 更新逃费订单
     *
     * @param updateReqVO 更新信息
     */
    void updateOrderEscape(@Valid ParkOrderEscapeSaveReqVO updateReqVO);

    /**
     * 删除逃费订单
     *
     * @param id 编号
     */
    void deleteOrderEscape(Long id);

    /**
     * 获得逃费订单
     *
     * @param id 编号
     * @return 逃费订单
     */
    ParkOrderEscapeDO getOrderEscape(Long id);

    /**
     * 获得逃费订单分页
     *
     * @param pageReqVO 分页查询
     * @return 逃费订单分页
     */
    PageResult<ParkOrderEscapeDO> getOrderEscapePage(ParkOrderEscapePageReqVO pageReqVO);

    int insertBatch(List<ParkOrderEscapeDO> parkOrderEscapeDOList);

    Integer importExcel(MultipartFile file);


}
