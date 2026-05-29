package cn.iocoder.yudao.module.smartcampus.service.archive;

import java.util.*;

import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.module.smartcampus.controller.admin.importer.vo.ImportRespVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.smartcampus.controller.admin.archive.vo.*;
import cn.iocoder.yudao.module.smartcampus.dal.dataobject.archive.ArchiveDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 学生学籍档案 Service 接口
 *
 * @author 亘川智城
 */
public interface ArchiveService {

    /**
     * 创建学生学籍档案
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createArchive(@Valid ArchiveSaveReqVO createReqVO);

    /**
     * 更新学生学籍档案
     *
     * @param updateReqVO 更新信息
     */
    void updateArchive(@Valid ArchiveSaveReqVO updateReqVO);

    /**
     * 删除学生学籍档案
     *
     * @param id 编号
     */
    void deleteArchive(Long id);

    /**
    * 批量删除学生学籍档案
    *
    * @param ids 编号
    */
    void deleteArchiveListByIds(List<Long> ids);

    /**
     * 获得学生学籍档案
     *
     * @param id 编号
     * @return 学生学籍档案
     */
    ArchiveDO getArchive(Long id);

    /**
     * 获得学生学籍档案分页
     *
     * @param pageReqVO 分页查询
     * @return 学生学籍档案分页
     */
    PageResult<ArchiveDO> getArchivePage(ArchivePageReqVO pageReqVO);

    /**
     * 学籍审核流转
     * @param reqVO     审核请求参数
     * @return 是否成功
     */
    boolean audit(@Valid ArchiveAuditReqVO reqVO, LoginUser loginUser);

    /**
     * 学籍状态维护
     *
     * @param reqVO     维护请求参数
     * @return 是否成功
     */
    boolean maintain(@Valid ArchiveMaintainReqVO reqVO, LoginUser loginUser);

    /**
     * 获取统计数据
     */
    ArchiveStatisticCardRespVO getStatisticCard();
    ArchiveStatisticChartRespVO getStatisticChart();

    ImportRespVO<ArchiveSaveReqVO> batchImport(List<ArchiveSaveReqVO> importList);

}