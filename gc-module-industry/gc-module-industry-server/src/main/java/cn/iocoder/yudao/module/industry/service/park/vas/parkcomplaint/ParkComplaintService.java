package cn.iocoder.yudao.module.industry.service.park.vas.parkcomplaint;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkcomplaint.vo.ParkComplaintPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkcomplaint.vo.ParkComplaintSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkcomplaint.ParkComplaintDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 投诉记录 Service 接口
 *
 * @author lxs
 */
public interface ParkComplaintService {

    /**
     * 创建投诉记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkComplaint(@Valid ParkComplaintSaveReqVO createReqVO);

    /**
     * 更新投诉记录
     *
     * @param updateReqVO 更新信息
     */
    void updateParkComplaint(@Valid ParkComplaintSaveReqVO updateReqVO);

    /**
     * 删除投诉记录
     *
     * @param id 编号
     */
    void deleteParkComplaint(Long id);

    /**
     * 获得投诉记录
     *
     * @param id 编号
     * @return 投诉记录
     */
    ParkComplaintDO getParkComplaint(Long id);

    /**
     * 获得投诉记录分页
     *
     * @param pageReqVO 分页查询
     * @return 投诉记录分页
     */
    PageResult<ParkComplaintDO> getParkComplaintPage(ParkComplaintPageReqVO pageReqVO);

}
