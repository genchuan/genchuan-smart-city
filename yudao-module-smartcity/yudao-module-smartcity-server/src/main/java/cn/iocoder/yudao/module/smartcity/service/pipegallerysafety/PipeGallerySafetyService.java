package cn.iocoder.yudao.module.smartcity.service.pipegallerysafety;

import java.util.*;
import jakarta.validation.Valid;
import cn.iocoder.yudao.module.smartcity.controller.admin.pipegallerysafety.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.pipegallerysafety.PipeGallerySafetyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 管廊安全 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface PipeGallerySafetyService {

    /**
     * 创建管廊安全
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPipeGallerySafety(@Valid PipeGallerySafetySaveReqVO createReqVO);

    /**
     * 更新管廊安全
     *
     * @param updateReqVO 更新信息
     */
    void updatePipeGallerySafety(@Valid PipeGallerySafetySaveReqVO updateReqVO);

    /**
     * 删除管廊安全
     *
     * @param id 编号
     */
    void deletePipeGallerySafety(Long id);

    /**
     * 获得管廊安全
     *
     * @param id 编号
     * @return 管廊安全
     */
    PipeGallerySafetyDO getPipeGallerySafety(Long id);

    /**
     * 获得管廊安全分页
     *
     * @param pageReqVO 分页查询
     * @return 管廊安全分页
     */
    PageResult<PipeGallerySafetyDO> getPipeGallerySafetyPage(PipeGallerySafetyPageReqVO pageReqVO);

}