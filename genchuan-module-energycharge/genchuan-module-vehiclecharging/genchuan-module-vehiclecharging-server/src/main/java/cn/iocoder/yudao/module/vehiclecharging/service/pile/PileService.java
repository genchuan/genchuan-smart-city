package cn.iocoder.yudao.module.vehiclecharging.service.pile;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.pile.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.pile.vo.LotSimpleRespVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.pile.vo.StationSimpleRespVO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.pile.PileDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;

/**
 * 充电桩 Service 接口
 *
 * @author 亘川智城
 */
public interface PileService {

    /**
     * 创建充电桩
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPile(@Valid PileSaveReqVO createReqVO);

    /**
     * 更新充电桩
     *
     * @param updateReqVO 更新信息
     */
    void updatePile(@Valid PileSaveReqVO updateReqVO);

    /**
     * 删除充电桩
     *
     * @param id 编号
     */
    void deletePile(Long id);

    /**
    * 批量删除充电桩
    *
    * @param ids 编号
    */
    void deletePileListByIds(List<Long> ids);

    /**
     * 获得充电桩
     *
     * @param id 编号
     * @return 充电桩
     */
    PileRespVO getPile(Long id);

    /**
     * 获得充电桩分页
     *
     * @param pageReqVO 分页查询
     * @return 充电桩分页
     */
    PageResult<PileRespVO> getPilePage(PilePageReqVO pageReqVO);

    /**
     * 调试充电桩，将状态从"未调试"(3) 修改为"已调试"(4)
     *
     * @param id 编号
     */
    void debugPile(Long id);

    /**
     * 启用充电桩，将状态从"已调试"(4) 修改为"已启用"(1)
     *
     * @param id 编号
     */
    void enablePile(Long id);

    /**
     * 停用充电桩，将状态从"已启用"(1) 修改为"未调试"(3)
     *
     * @param id     编号
     * @param remark 停用备注
     */
    void disablePile(Long id, String remark);

    /**
     * 获取充电枪二维码
     *
     * @param id 编号
     * @return 二维码图片字节数组
     */
    byte[] getPileQrcode(Long id);

    /**
     * 远程重启充电桩
     *
     * @param id 编号
     * @return 操作结果，true 成功，false 失败
     */
    boolean restartPile(Long id);

    /**
     * 获取充电桩图表统计数据
     *
     * @param reqVO 统计请求参数
     * @return 图表统计数据
     */
    PileChartRespVO getPileChart(PileChartReqVO reqVO);

    /**
     * 获取充电桩运行时长趋势
     *
     * @param reqVO 查询参数
     * @return 运行时长趋势列表
     */
    List<PileRunTimeTrendRespVO> getRunTimeTrendList(PileRunTimeTrendReqVO reqVO);

    /**
     * 获取充电桩充电模式统计
     *
     * @param stationId 场站ID（可选）
     * @return 各充电模式的充电桩数量列表
     */
    List<PileChargeModeStatRespVO> getChargeModeStatList(Long stationId);

    List<PileStatusStatRespVO> getPileStatusStatList();

    /**
     * 获取充电桩状态字典列表
     *
     * @return 状态字典列表 [{value, label}]
     */
    List<PileStatusDictRespVO> getPileStatusDictList();

    /**
     * 获取充电模式字典列表
     *
     * @return 充电模式字典列表 [{value, label}]
     */
    List<PileStatusDictRespVO> getChargeModeDictList();

    /**
     * 获取充电车位简易列表
     *
     * @return 简易列表 [{value: 车位ID, label: 车位编号}]
     */
    List<LotSimpleRespVO> getLotSimpleList();

    /**
     * 获取场站简易列表
     *
     * @return 简易列表 [{value: 场站ID, label: 场站名称}]
     */
    List<StationSimpleRespVO> getStationSimpleList();

}