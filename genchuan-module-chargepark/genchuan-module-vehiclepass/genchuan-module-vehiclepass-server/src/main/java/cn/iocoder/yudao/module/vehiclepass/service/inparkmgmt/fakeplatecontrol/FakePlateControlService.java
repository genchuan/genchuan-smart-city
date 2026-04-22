package cn.iocoder.yudao.module.vehiclepass.service.inparkmgmt.fakeplatecontrol;

import java.util.*;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlBatchHandleReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.MyFakePlateControlRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inparkmgmt.fakeplatecontrol.FakePlateControlDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 套牌管控 Service 接口
 *
 * @author 亘川智城
 */
public interface FakePlateControlService {

    /**
     * 创建套牌管控
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPlateControl(@Valid FakePlateControlSaveReqVO createReqVO);

    /**
     * 更新套牌管控
     *
     * @param updateReqVO 更新信息
     */
    void updatePlateControl(@Valid FakePlateControlSaveReqVO updateReqVO);

    /**
     * 删除套牌管控
     *
     * @param id 编号
     */
    void deletePlateControl(Long id);

    /**
     * 批量删除套牌管控
     *
     * @param ids 编号
     */
    void deletePlateControlListByIds(List<Long> ids);

    /**
     * 获得套牌管控
     *
     * @param id 编号
     * @return 套牌管控
     */
    FakePlateControlDO getPlateControl(Long id);

    /**
     * 获得套牌管控分页
     *
     * @param pageReqVO 分页查询
     * @return 套牌管控分页
     */
    PageResult<FakePlateControlDO> getPlateControlPage(FakePlateControlPageReqVO pageReqVO);

    PageResult<MyFakePlateControlRespVO> getFakePlateControlPage(FakePlateControlPageReqVO pageReqVO);

    /**
     * 批量处置套牌管控
     *
     * @param reqVO 批量处置请求
     */
    void batchHandle(FakePlateControlBatchHandleReqVO reqVO);
}