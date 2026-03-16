package cn.iocoder.yudao.module.facility.service.sysarchive;

import java.io.IOException;
import java.util.*;

import cn.iocoder.yudao.module.facility.controller.admin.sysarchive.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.sysarchive.SysArchiveDO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 归档 Service 接口
 *
 * @author 亘川智城
 */
public interface SysArchiveService {

    /**
     * 创建归档
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSysArchive(@Valid SysArchiveSaveReqVO createReqVO);

    /**
     * 更新归档
     *
     * @param updateReqVO 更新信息
     */
    void updateSysArchive(@Valid SysArchiveUpdateReqVO updateReqVO);

    /**
     * 删除归档
     *
     * @param id 编号
     */
    void deleteSysArchive(Long id);

    /**
     * 获得归档
     *
     * @param id 编号
     * @return 归档
     */
    SysArchiveDO getSysArchive(Long id);

    /**
     * 获得归档分页
     *
     * @param pageReqVO 分页查询
     * @return 归档分页
     */
    PageResult<SysArchiveDO> getSysArchivePage(SysArchivePageReqVO pageReqVO);

    List<FlowRecordRespVO> getWorkOrderFlowRecords(Long id);

    void downloadArchiveFiles(DownloadArchiveFilesReqVO reqVO, HttpServletResponse response) throws IOException;
}
