package cn.iocoder.yudao.module.waterdetection.service.pollutionsourcearchive;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.pollutionsourcearchive.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.pollutionsourcearchive.PollutionSourceArchiveDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 周边污染源档案管理 Service 接口
 *
 * @author zcq
 */
public interface PollutionSourceArchiveService {

    /**
     * 创建周边污染源档案管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPollutionSourceArchive(@Valid PollutionSourceArchiveSaveReqVO createReqVO);

    /**
     * 更新周边污染源档案管理
     *
     * @param updateReqVO 更新信息
     */
    void updatePollutionSourceArchive(@Valid PollutionSourceArchiveSaveReqVO updateReqVO);

    /**
     * 删除周边污染源档案管理
     *
     * @param id 编号
     */
    void deletePollutionSourceArchive(Long id);

    /**
     * 获得周边污染源档案管理
     *
     * @param id 编号
     * @return 周边污染源档案管理
     */
    PollutionSourceArchiveDO getPollutionSourceArchive(Long id);

    /**
     * 获得周边污染源档案管理分页
     *
     * @param pageReqVO 分页查询
     * @return 周边污染源档案管理分页
     */
    PageResult<PollutionSourceArchiveDO> getPollutionSourceArchivePage(PollutionSourceArchivePageReqVO pageReqVO);

}