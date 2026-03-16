package cn.iocoder.yudao.module.facility.service.sysarchive;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.facility.controller.admin.sysarchive.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.sysarchive.SysArchiveDO;
import cn.iocoder.yudao.module.facility.dal.dataobject.sysuser.SysUserDO;
import cn.iocoder.yudao.module.facility.dal.dataobject.syswarn.SysWarnDO;
import cn.iocoder.yudao.module.facility.dal.dataobject.workorder.WorkOrderDO;
import cn.iocoder.yudao.module.facility.dal.mysql.sysarchive.SysArchiveMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.sysuser.SysUserMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.syswarn.SysWarnMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.workorder.WorkOrderMapper;
import cn.iocoder.yudao.module.facility.service.sysuser.SysUserService;
import cn.iocoder.yudao.module.facility.service.workorder.WorkOrderService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.facility.enums.ErrorCodeConstants.*;

/**
 * 归档 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
@Slf4j
public class SysArchiveServiceImpl implements SysArchiveService {

    @Resource
    private SysArchiveMapper sysArchiveMapper;

    @Resource
    private WorkOrderMapper workOrderMapper;

    @Resource
    private SysWarnMapper sysWarnMapper;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private WorkOrderService workOrderService;
    @Resource
    private SysUserMapper sysUserMapper;

    // TODO
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createSysArchive(SysArchiveSaveReqVO createReqVO) {
        // 1. 校验必填参数
        if (StrUtil.isBlank(createReqVO.getFacilityCode())) {
            throw new ServiceException(400, "[设施编码]不能为空");
        }
        if (createReqVO.getCheckStaffId() == null) {
            throw new ServiceException(400, "[核查人ID]不能为空");
        }

        // 2. 生成归档编号 (唯一)
        String archiveNo = generateArchiveNo();
        createReqVO.setArchiveNo(archiveNo);


        // 4. 处理预警/工单关联
        String warnNo = createReqVO.getWarnNo();
        if (StrUtil.isNotBlank(warnNo)) {
            // 查询预警记录
            SysWarnDO warn = sysWarnMapper.selectByWarnNo(warnNo);
            if (warn != null) {
                // 设置预警相关信息
                createReqVO.setOverIndexName(warn.getOverIndex());
                createReqVO.setBeforeIndexValue(warn.getOverValue());
                createReqVO.setThresholdValue(warn.getThresholdValue());

                // 获取关联工单号
                String orderNo = createReqVO.getOrderNo();


                // 查询工单详情
                WorkOrderDO order = workOrderMapper.selectByOrderNo(orderNo);
                if (order != null) {
                    createReqVO.setOrderType(order.getOrderType());
                    createReqVO.setBizType(order.getBizType());
                    createReqVO.setCompleteTime(order.getCompleteTime());
                    createReqVO.setAssignStaffId(order.getAssignStaffId());
                    createReqVO.setAssignStaffName(order.getAssignStaffName());

                    // 处理时间 = 工单完成时间 - 预警产生时间（分钟）
                    if (warn.getCreateTime() != null && order.getCompleteTime() != null) {
                        long minutes = Duration.between(warn.getCreateTime(), order.getCompleteTime()).toMinutes();
                        createReqVO.setDealDuration((int) minutes);
                    }

                    // 处置后指标值从工单获取
                    createReqVO.setAfterIndexValue(order.getAfterIndexValue());

                    // 恢复值 = 处置前 - 处置后
                    if (warn.getOverIndex() != null && order.getAfterIndexValue() != null) {
                        createReqVO.setRecoverValue(warn.getOverValue().subtract(order.getAfterIndexValue()));
                    }

                    // 统计归档资料数（假设工单关联附件表）
                    int fileNum = workOrderService.countByOrderNo(orderNo);
                    createReqVO.setFileNum((double) fileNum);
                }
            }
        }

        // 5. 根据核查人ID查询姓名
        if (createReqVO.getCheckStaffId() != null) {
            SysUserDO staff =sysUserMapper.selectById(createReqVO.getCheckStaffId());
            if (staff != null) {
                createReqVO.setCheckStaffName(staff.getNickname());
            }
        }

//        // 6. 设置审计字段（创建人、更新人、租户）
//        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
//        if (loginUser != null) {
//            createReqVO.setCreator(String.valueOf(loginUser.getId()));
//            createReqVO.setUpdater(String.valueOf(loginUser.getId()));
//            createReqVO.setTenantId(loginUser.getTenantId());
//        }

        // 7. VO转DO并插入
        SysArchiveDO sysArchive = BeanUtils.toBean(createReqVO, SysArchiveDO.class);
        sysArchiveMapper.insert(sysArchive);

        // 8. 返回新记录ID
        return sysArchive.getId();
    }

    /**
     * 生成归档编号（示例：ARC + yyyyMMdd + 6位序列）
     */
    private String generateArchiveNo() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        // 从数据库获取当天最大序列（需自行实现），此处简单使用时间戳
        String seq = String.format("%06d", (int) (System.currentTimeMillis() % 1000000));
        return "ARC" + date + seq;
    }



    @Override
    public void updateSysArchive(@Valid SysArchiveUpdateReqVO updateReqVO) {
        // 校验存在
        validateSysArchiveExists(updateReqVO.getId());
        // 更新
        SysArchiveDO updateObj = BeanUtils.toBean(updateReqVO, SysArchiveDO.class);
        sysArchiveMapper.updateById(updateObj);
    }

    @Override
    public void deleteSysArchive(Long id) {
        // 校验存在
        validateSysArchiveExists(id);
        // 删除
        sysArchiveMapper.deleteById(id);
    }

    private void validateSysArchiveExists(Long id) {
        if (sysArchiveMapper.selectById(id) == null) {
            throw exception(SYS_ARCHIVE_NOT_EXISTS);
        }
    }

    @Override
    public SysArchiveDO getSysArchive(Long id) {
        return sysArchiveMapper.selectById(id);
    }

    @Override
    public PageResult<SysArchiveDO> getSysArchivePage(SysArchivePageReqVO pageReqVO) {
        return sysArchiveMapper.selectPage(pageReqVO);
    }

    @Override
    public List<FlowRecordRespVO> getWorkOrderFlowRecords(Long archiveId) {

        List<FlowRecordRespVO> list = new ArrayList<>();

        // 3 查询归档
        SysArchiveDO archive = sysArchiveMapper.selectById(archiveId);
        if (archive==null){
            throw exception(500,"归档文件不存在数据库");
        }
        // 1 查询工单
        WorkOrderDO workOrder = workOrderMapper.selectByOrderNo(archive.getOrderNo());
        if (workOrder == null) {
            return list;
        }

        // 2 查询预警
        SysWarnDO warn = null;
        if (workOrder.getWarnId() != null) {
            warn = sysWarnMapper.selectById(workOrder.getWarnId());
        }

        /*
         * ========= 1 预警触发 =========
         */
        if (warn != null) {
            FlowRecordRespVO node = new FlowRecordRespVO();
            node.setStepNo(1);
            node.setNodeName("预警触发");
            node.setNodeTime(warn.getTriggerTime());
            node.setOperatorName("系统");
            node.setNodeDesc("监测指标超过阈值触发预警");
            node.setRefNo(warn.getWarnNo());
            list.add(node);
        }

        /*
         * ========= 2 创建工单 =========
         */
        FlowRecordRespVO createNode = new FlowRecordRespVO();
        createNode.setStepNo(2);
        createNode.setNodeName("创建工单");
        createNode.setNodeTime(workOrder.getCreateTime());
        createNode.setOperatorName("系统");
        createNode.setNodeDesc("系统生成工单");
        createNode.setRefNo(workOrder.getOrderNo());
        list.add(createNode);

        /*
         * ========= 3 派单 =========
         */
        if (workOrder.getRemindTime() != null) {
            FlowRecordRespVO assignNode = new FlowRecordRespVO();
            assignNode.setStepNo(3);
            assignNode.setNodeName("派单");
            assignNode.setNodeTime(workOrder.getRemindTime());
            assignNode.setOperatorName(workOrder.getAssignStaffName());
            assignNode.setNodeDesc("指派运维人员处理");
            assignNode.setRefNo(workOrder.getOrderNo());
            list.add(assignNode);
        }

        /*
         * ========= 4 到达现场 =========
         */
        if (workOrder.getArriveTime() != null) {
            FlowRecordRespVO arriveNode = new FlowRecordRespVO();
            arriveNode.setStepNo(4);
            arriveNode.setNodeName("到达现场");
            arriveNode.setNodeTime(workOrder.getArriveTime());
            arriveNode.setOperatorName(workOrder.getAssignStaffName());
            arriveNode.setNodeDesc("运维人员抵达现场");
            arriveNode.setRefNo(workOrder.getOrderNo());
            list.add(arriveNode);
        }

        /*
         * ========= 5 处置完成 =========
         */
        if (workOrder.getCompleteTime() != null) {
            FlowRecordRespVO completeNode = new FlowRecordRespVO();
            completeNode.setStepNo(5);
            completeNode.setNodeName("处置完成");
            completeNode.setNodeTime(workOrder.getCompleteTime());
            completeNode.setOperatorName(workOrder.getAssignStaffName());
            completeNode.setNodeDesc("工单处理完成");
            completeNode.setRefNo(workOrder.getOrderNo());
            list.add(completeNode);
        }

        /*
         * ========= 6 归档 =========
         */
        if (archive != null) {
            FlowRecordRespVO archiveNode = new FlowRecordRespVO();
            archiveNode.setStepNo(6);
            archiveNode.setNodeName("归档");
            archiveNode.setNodeTime(archive.getCreateTime());
            archiveNode.setOperatorName(archive.getCheckStaffName());
            archiveNode.setNodeDesc("核查完成并归档");
            archiveNode.setRefNo(archive.getArchiveNo());
            list.add(archiveNode);
        }

        // 7 按时间排序
        list.sort(Comparator.comparing(FlowRecordRespVO::getNodeTime));

        return list;
    }


    //可用4512
    @Override
    public void downloadArchiveFiles(DownloadArchiveFilesReqVO reqVO, HttpServletResponse response) throws IOException {
        Long archiveId = reqVO.getArchiveId();
        log.info("[downloadArchiveFiles] 请求归档ID: {}", archiveId);

        // 1. 查询归档
        SysArchiveDO archive = sysArchiveMapper.selectById(archiveId);
        if (archive == null) {
            log.error("[downloadArchiveFiles] 归档不存在: {}", archiveId);
            throw exception(500, "归档不存在");
        }
        log.info("[downloadArchiveFiles] 查询归档成功: {}", archive.getArchiveNo());

        // 2. 查询工单
        WorkOrderDO workOrder = workOrderMapper.selectByOrderNo(archive.getOrderNo());
        if (workOrder == null) {
            log.error("[downloadArchiveFiles] 工单不存在: {}", archive.getOrderNo());
            throw exception(500, "工单不存在");
        }
        log.info("[downloadArchiveFiles] 查询工单成功: {}", workOrder.getOrderNo());

        // 3. 获取文件 JSON 字符串
        String fileStr = workOrder.getSiteDataUrlListStr();
        if (fileStr == null || fileStr.isEmpty()) {
            log.warn("[downloadArchiveFiles] 工单没有归档资料: {}", workOrder.getOrderNo());
            throw exception(500, "没有归档资料");
        }

        // 4. 解析 JSON
        List<Map<String, Object>> fileList;
        try {
            fileList = com.alibaba.fastjson2.JSON.parseArray(fileStr, (Type) Map.class);
            log.info("[downloadArchiveFiles] JSON解析成功，文件数量: {}", fileList.size());
        } catch (Exception e) {
            log.error("[downloadArchiveFiles] JSON解析失败: {}", fileStr, e);
            throw exception(500, "归档资料解析失败");
        }
        if (fileList.isEmpty()) {
            log.warn("[downloadArchiveFiles] JSON文件列表为空");
            throw exception(500, "没有归档资料");
        }

        // 5. 设置下载头
        String fileName = "归档资料_" + archive.getArchiveNo() + ".zip";
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + encodedFileName + "\"; filename*=" + encodedFileName);
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        log.info("[downloadArchiveFiles] 开始写入 ZIP 流");

        // 6. 创建 ZIP 输出流
        try (ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())) {
            for (Map<String, Object> fileMap : fileList) {
                String url = (String) fileMap.get("url");
                String name = (String) fileMap.get("name");
                if (url == null || name == null) {
                    log.warn("[downloadArchiveFiles] 文件信息不完整，跳过: {}", fileMap);
                    continue;
                }

                String safeName = name.replaceAll("[\\\\/:*?\"<>|]", "_");
                log.info("[downloadArchiveFiles] 添加文件到ZIP: {} (URL: {})", safeName, url);

                zos.putNextEntry(new ZipEntry(safeName));

                try (InputStream inputStream = new URL(url).openStream()) {
                    byte[] buffer = new byte[4096];
                    int len;
                    while ((len = inputStream.read(buffer)) != -1) {
                        zos.write(buffer, 0, len);
                    }
                    log.info("[downloadArchiveFiles] 下载完成: {}", safeName);
                } catch (IOException e) {
                    log.error("[downloadArchiveFiles] 下载文件失败: {}", url, e);
                }

                zos.closeEntry();
            }
            zos.finish();
            log.info("[downloadArchiveFiles] ZIP 写入完成: {}", fileName);
        } catch (IOException e) {
            log.error("[downloadArchiveFiles] 写ZIP流异常", e);
            throw e;
        }
    }


}
