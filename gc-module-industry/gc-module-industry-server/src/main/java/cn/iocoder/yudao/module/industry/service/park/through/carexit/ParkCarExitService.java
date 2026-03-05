package cn.iocoder.yudao.module.industry.service.park.through.carexit;

import cn.iocoder.yudao.module.industry.controller.admin.park.through.carexit.vo.ParkCarExitPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.carexit.vo.ParkCarExitSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.carexit.ParkCarExitDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 离场记录 Service 接口
 *
 * @author zhucongquan
 */
public interface ParkCarExitService {

    /**
     * 创建离场记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkCarExit(@Valid ParkCarExitSaveReqVO createReqVO);

    /**
     * 更新离场记录
     *
     * @param updateReqVO 更新信息
     */
    void updateParkCarExit(@Valid ParkCarExitSaveReqVO updateReqVO);

    /**
     * 删除离场记录
     *
     * @param id 编号
     */
    void deleteParkCarExit(Long id);

    /**
     * 获得离场记录
     *
     * @param id 编号
     * @return 离场记录
     */
    ParkCarExitDO getParkCarExit(Long id);

    /**
     * 获得离场记录分页
     *
     * @param pageReqVO 分页查询
     * @return 离场记录分页
     */
    PageResult<ParkCarExitDO> getParkCarExitPage(ParkCarExitPageReqVO pageReqVO);

}