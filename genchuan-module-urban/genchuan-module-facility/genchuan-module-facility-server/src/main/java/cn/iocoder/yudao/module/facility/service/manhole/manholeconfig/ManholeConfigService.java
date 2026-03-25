package cn.iocoder.yudao.module.facility.service.manhole.manholeconfig;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholeconfig.ManholeConfigDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;

/**
 * 窨井盖监测配置 Service 接口
 *
 * @author 亘川智城
 */
public interface ManholeConfigService {

    /**
     * 创建窨井盖监测配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createManholeConfig(@Valid ManholeConfigSaveReqVO createReqVO);

    /**
     * 更新窨井盖监测配置
     *
     * @param updateReqVO 更新信息
     */
    void updateManholeConfig(@Valid ManholeConfigSaveReqVO updateReqVO);

    /**
     * 删除窨井盖监测配置
     *
     * @param id 编号
     */
    void deleteManholeConfig(Long id);

    /**
     * 获得窨井盖监测配置
     *
     * @param id 编号
     * @return 窨井盖监测配置
     */
    ManholeConfigDO getManholeConfig(Long id);

    /**
     * 获得窨井盖监测配置分页
     *
     * @param pageReqVO 分页查询
     * @return 窨井盖监测配置分页
     */
    PageResult<ManholeConfigDO> getManholeConfigPage(ManholeConfigPageReqVO pageReqVO);

    void saveConfig(ManholeConfigReqVO configVO);
    /**
     * 获得窨井盖监测配置分页
     * @return 窨井盖监测配置分页
     */
    PageResult<ManholeCoverConfigPageRespVO> getConfigPage(String coverId, Integer configStatus, String tenantId, Integer pageNo, Integer pageSize);
    /**
     * 获得窨井盖监测配置详情
     * @return 窨井盖监测配置详情
     */
    ManholeCoverConfigDetailRespVO getDetail(Long id, Long tenantId);

    @Transactional(rollbackFor = Exception.class)
    CommonResult<ManholeCoverConfigAddRespVO> addManholeCoverConfig(ManholeCoverConfigAddReqVO reqVO);
}