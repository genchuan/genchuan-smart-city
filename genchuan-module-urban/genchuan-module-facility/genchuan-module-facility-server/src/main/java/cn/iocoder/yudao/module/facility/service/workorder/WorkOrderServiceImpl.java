package cn.iocoder.yudao.module.facility.service.workorder;

import cn.hutool.core.util.RandomUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.module.facility.controller.admin.workorder.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.syswarn.SysWarnDO;
import cn.iocoder.yudao.module.facility.dal.dataobject.workorder.WorkOrderDO;
import cn.iocoder.yudao.module.facility.dal.mysql.syswarn.SysWarnMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.workorder.WorkOrderMapper;

import cn.iocoder.yudao.module.facility.framework.lxsutils.common.file.FileUploadService;
import cn.iocoder.yudao.module.facility.service.syswarn.SysWarnService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import org.springframework.web.multipart.MultipartFile;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.facility.enums.ErrorCodeConstants.*;

/**
 * 工单 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
@Slf4j
public class WorkOrderServiceImpl implements WorkOrderService {

    @Resource
    private WorkOrderMapper workOrderMapper;

    @Resource
    private FileUploadService fileUploadService;

    @Resource
    private SysWarnService sysWarnService;

    @Resource
    private SysWarnMapper sysWarnMapper;

    @Resource
    private Validator validator; // Spring 会自动注入 javax.validation.Validator

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createWorkOrder(WorkOrderSaveReqVO createReqVO) {
        // ====== 1.手动校验 ======
        Set<ConstraintViolation<WorkOrderSaveReqVO>> violations = validator.validate(createReqVO);
        if (!violations.isEmpty()) {
            String errorMsg = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("; "));
            throw new ServiceException(400, errorMsg);
        }

        // ================== 2. 自动生成工单编号 ==================
        // 规则：WOR + 月日(MMdd) + 6位流水号，例如：WOR0304000001
        String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMdd"));
        String prefix = "WOR" + dateStr;

        // 查询当天最大编号（示例，具体 SQL 你可以用 like prefix%） TODO 流水号先简单处理
//        String maxWarnNo = sysWarnMapper.selectMaxWarnNoByPrefix(prefix);
        String nextSeq;
        String maxWorkOrderNo = null;
        if (maxWorkOrderNo == null) {
//            nextSeq = "000001";
            nextSeq = RandomUtil.randomNumbers(6);
        } else {
            String seq = maxWorkOrderNo.substring(maxWorkOrderNo.length() - 6);
            nextSeq = String.format("%06d", Integer.parseInt(seq) + 1);
        }
        createReqVO.setOrderNo(prefix + nextSeq);


        // 3. 根据 warnId 获取预警相关信息
        if (createReqVO.getWarnId()==null){
            throw exception(400,"预警id不能为空");
        }
        SysWarnDO warn = sysWarnMapper.selectById(createReqVO.getWarnId());
        if (warn != null) {
            //如果预警不为空，先查看该预警是否有处理工单
            if (warn.getWorkOrderId()!=null){
                throw exception(400,"该预警已经产生了工单，请勿重复添加");
            }

            //把预警参数插入到 创建实体
            createReqVO.setWarnNo(warn.getWarnNo());
            createReqVO.setFacilityId(warn.getFacilityId());
            createReqVO.setFacilityName(warn.getFacilityName());
            createReqVO.setFacilityType(warn.getFacilityType());
        }else {
            throw exception(400,"预警不存在");
        }


        // 4. 填充工单初始状态
        createReqVO.setProcessStatus("待处置");  // 默认状态
        createReqVO.setProcessDesc("工单待处置");



        // 5. 工单插入数据库
        WorkOrderDO workOrder = BeanUtils.toBean(createReqVO, WorkOrderDO.class);
        workOrderMapper.insert(workOrder);

        // 5. 修改预警的派单状态、状态，回填工单id、工单编号，并更新到数据库
        warn.setWorkOrderId(workOrder.getId());       // 回填工单ID
        warn.setWorkOrderCode(workOrder.getOrderNo()); // 回填工单编号
        warn.setAssignStatus("已派单");               // 派单状态更新
        warn.setStatus("已派单");                     // 预警状态更新，可按业务调整
        sysWarnMapper.updateById(warn);
        // 6. 返回插入ID
        return workOrder.getId();
    }

    @Override
    public void updateWorkOrder(WorkOrderUpdateReqVO updateReqVO) {
        // 校验存在
        validateWorkOrderExists(updateReqVO.getId());
        // 更新
        WorkOrderDO updateObj = BeanUtils.toBean(updateReqVO, WorkOrderDO.class);
        workOrderMapper.updateById(updateObj);
    }

    @Override
    public void deleteWorkOrder(Long id) {
        // 校验存在
        validateWorkOrderExists(id);
        // 删除
        workOrderMapper.deleteById(id);
    }

    private void validateWorkOrderExists(Long id) {
        if (workOrderMapper.selectById(id) == null) {
            throw exception(WORK_ORDER_NOT_EXISTS);
        }
    }

    @Override
    public WorkOrderDO getWorkOrder(Long id) {
        return workOrderMapper.selectById(id);
    }

    @Override
    public PageResult<WorkOrderDO> getWorkOrderPage(WorkOrderPageReqVO pageReqVO) {
        return workOrderMapper.selectPage(pageReqVO);



    }


    @Override
    public PageResult<WorkOrderRespVO> getCompleteWorkOrderPage(WorkOrderPageReqVO pageReqVO) {
        // 1. 分页查询
        PageResult<WorkOrderDO> doPageResult = workOrderMapper.selectPage(pageReqVO);

        List<WorkOrderDO> doList = doPageResult.getList();

        // 2. 重新构建PageResult
        List<WorkOrderRespVO> respList = BeanUtils.toBean(doList,WorkOrderRespVO.class);
        PageResult<WorkOrderRespVO> resultPageResult = new PageResult<>(respList, doPageResult.getTotal());

        if (respList == null || respList.isEmpty()) {
            return resultPageResult;
        }

        // 当前时间
        LocalDateTime now = LocalDateTime.now();

        // 2. 遍历填充字段
        for (WorkOrderRespVO order : respList) {

            BigDecimal dealLimit = order.getDealLimit(); // 处置时限（小时）
            LocalDateTime createTime = order.getCreateTime();

            if (dealLimit == null || createTime == null) {
                continue;
            }

            // 计算截止时间 = 创建时间 + 处置时限
            LocalDateTime deadline = createTime.plusMinutes(dealLimit.multiply(new BigDecimal(60)).longValue());

            // 剩余时间
            long minutes = Duration.between(now, deadline).toMinutes();
            BigDecimal remainTime = new BigDecimal(minutes).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP);

            // 剩余时间最少为0
            order.setRemainTime(remainTime.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : remainTime);

            // 是否超时
            if (minutes < 0) {
                order.setOverTimeFlag(1);
            } else {
                order.setOverTimeFlag(0);
            }
        }

        return resultPageResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long batchRemind(BatchRemindReqVO reqVO) {

        // 1. 获取工单id列表
        List<Long> idList = reqVO.getIdList();

        if (idList == null || idList.isEmpty()) {
            throw exception(500,"请传入工单id列表");
        }

        // 2. 查询数据库对应工单
        List<WorkOrderDO> workOrderList = workOrderMapper.selectBatchIds(idList);

        // 如果全部不存在
        if (workOrderList == null || workOrderList.isEmpty()) {
            throw exception(WORK_ORDER_NOT_EXISTS);
        }

        // 当前时间
        LocalDateTime now = LocalDateTime.now();

        // 4. 收集需要更新的工单
        List<WorkOrderDO> updateList = new ArrayList<>();

        for (WorkOrderDO workOrder : workOrderList) {

            // 3. TODO 对接待办/提醒模块（暂时不处理）

            // 设置提醒时间
            workOrder.setRemindTime(now);

            updateList.add(workOrder);
        }

        // 5. 批量更新
        if (!updateList.isEmpty()) {
            workOrderMapper.updateBatch(updateList);
        }

        // 6. 返回成功提醒数量
        return (long) updateList.size();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean reassignWorkOrder(ReassignWorkOrderReqVO reqVO) {

        // 1. 校验工单是否存在
        WorkOrderDO workOrder = workOrderMapper.selectById(reqVO.getWorkOrderId());
        if (workOrder == null) {
            throw exception(WORK_ORDER_NOT_EXISTS);
        }

        // 2. 判断是否重复派单
        if (Objects.equals(workOrder.getAssignStaffId(), reqVO.getAssignStaffId())) {
            return true; // 已经是当前运维员
        }

        // 3. 更新派单信息
        WorkOrderDO updateObj = new WorkOrderDO();
        updateObj.setId(reqVO.getWorkOrderId());
        updateObj.setAssignStaffId(reqVO.getAssignStaffId());
        updateObj.setAssignStaffName(reqVO.getAssignStaffName());

        workOrderMapper.updateById(updateObj);

        // 4. TODO 推送待办 / 通知新运维员
        // todoService.createWorkOrderTodo(reqVO.getAssignStaffId(), reqVO.getWorkOrderId());

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateProcessStatus(UpdateProcessStatusReqVO reqVO) {

        // 1. 校验工单是否存在
        WorkOrderDO workOrder = workOrderMapper.selectById(reqVO.getWorkOrderId());
        if (workOrder == null) {
            throw exception(WORK_ORDER_NOT_EXISTS);
        }

        // 当前状态
        String currentStatus =  workOrder.getProcessStatus();

//        // 目标状态
//        String targetStatus =

        // 2. 构造更新对象（只更新需要修改的字段）
        WorkOrderDO updateObj = new WorkOrderDO();
        updateObj.setId(reqVO.getWorkOrderId());
//        updateObj.setProcessStatus(reqVO.getProcessStatus());
        updateObj.setProcessDesc(reqVO.getProcessDesc());
        //3.记录更新时间
        updateObj.setUpdateTime(LocalDateTime.now());

        // =========================
        // 3. 严格状态机控制
        // =========================

        // 待处置 -> 处置中
        if ("待处置".equals(currentStatus) ) {

            // 自动记录抵达时间
            if (workOrder.getArriveTime() == null) {
                updateObj.setArriveTime(LocalDateTime.now());
            }

            updateObj.setProcessStatus("处置中");
        }

        // 处置中 -> 处理完成
        else if ("处置中".equals(currentStatus) ) {

            // 必须上传现场资料
            if (workOrder.getSiteDataUrlListStr() == null || workOrder.getSiteDataUrlListStr().trim().isEmpty()) {
                throw exception(500, "处理完成前必须上传现场资料");
            }

            // 必须填写处理后的指标
            if (workOrder.getAfterIndexValue() == null) {
                throw exception(500, "处理完成前必须填写处理后的指标数值");
            }

            // =========================
            // 指标恢复校验（示例）
            // =========================
            // 这里应该查询阈值，例如：
            // BigDecimal thresholdValue = sysWarnMapper.selectThresholdByWarnId(workOrder.getWarnId());

            // 示例逻辑（需要替换为真实阈值）
            // if (workOrder.getAfterIndexValue().compareTo(thresholdValue) >= 0) {
            //     throw exception(500, "指标仍然超标，不能完成工单");
            // }

            // 自动记录完成时间
            updateObj.setCompleteTime(LocalDateTime.now());

            updateObj.setProcessStatus("处理完成");
        }

        // 处理完成 -> 已核查
        else if ("处理完成".equals(currentStatus)) {
            throw exception(500,"当前进度已经是处理完成了，请静待审查");
//            updateObj.setProcessStatus("已核查");
        }
//        // 已核查
//        else if ("已核查".equals(currentStatus)) {
//            throw exception(500,"当前进度已经是已核查，无需更新进度");
////            updateObj.setProcessStatus("已核查");
//        }

        // 非法状态流转
        else {
            throw exception(500, "非法的工单状态流转");
        }

        // 6. 更新数据库
        int update = workOrderMapper.updateById(updateObj);
        return update > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean superviseOvertime(SuperviseOvertimeReqVO reqVO) {

        // 1. 校验工单是否存在
        WorkOrderDO workOrder = workOrderMapper.selectById(reqVO.getWorkOrderId());
        if (workOrder == null) {
            throw exception(WORK_ORDER_NOT_EXISTS);
        }

        // 2. 构造更新对象（只更新督办意见）
        WorkOrderDO updateObj = new WorkOrderDO();
        updateObj.setId(reqVO.getWorkOrderId());
        updateObj.setSuperviseOpinion(reqVO.getSuperviseOpinion());
        updateObj.setUpdateTime(LocalDateTime.now());

        // 3. 更新数据库
        int update = workOrderMapper.updateById(updateObj);

        // 4. TODO: 推送督办提醒给运维员
        //4.1 从数据库数据获取到 运维员
        Long assignStaffId = workOrder.getAssignStaffId();
        if (assignStaffId==null){
            throw exception(500,"运维人员在后端是空，请联系管理员处理");
        }
        //4.2 发送督办意见给 运维人员
        // todoService.createSuperviseTodo(workOrder.getAssignStaffId(), reqVO.getWorkOrderId());

        return update > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UploadWorkOrderFileRespVO uploadWorkOrderFile(UploadWorkOrderFileReqVO reqVO, MultipartFile file) {

        // 1. 校验工单是否存在
        WorkOrderDO workOrder = workOrderMapper.selectById(reqVO.getWorkOrderId());
        if (workOrder == null) {
            throw exception(WORK_ORDER_NOT_EXISTS);
        }

        try {
            //7.判断处理后的指标数值是否超过预警阈值
            if (reqVO.getAfterIndexValue()==null){
                throw exception(400,"处理后的指标数值不能为空");
            }
            if (workOrder.getWarnId()==null){
                throw exception(500,"预警id不存在工单");
            }
            SysWarnDO sysWarnDO = sysWarnMapper.selectById(workOrder.getWarnId());
            if (sysWarnDO==null){
                throw exception(500,"预警不存在数据库");
            }
            // 判断 afterIndexValue >= thresholdValue
            if (reqVO.getAfterIndexValue().compareTo(sysWarnDO.getThresholdValue()) >= 0) {
                throw exception(500,"处理后的指标数值不能超过或者等于预警阈值:"+sysWarnDO.getThresholdValue());
            }

            //1.文件描述叠加
            String oldDesc = workOrder.getFileDesc();
            String newDesc;
            if (oldDesc == null || oldDesc.isEmpty()) {
                newDesc = reqVO.getFileDesc();
            } else {
                newDesc = oldDesc + "; " + reqVO.getFileDesc(); // 或者用换行、逗号分隔
            }

            // 2. 上传文件到 MinIO
            String fileUrl = fileUploadService.uploadAvatar(file);

            // 3. 构建文件信息对象
            Map<String, String> fileInfo = new HashMap<>();
            fileInfo.put("url", fileUrl);
            fileInfo.put("name", file.getOriginalFilename());

            // 根据后缀决定 type
            String lowerName = file.getOriginalFilename().toLowerCase();
            if (lowerName.endsWith(".png") || lowerName.endsWith(".jpg") || lowerName.endsWith(".jpeg") || lowerName.endsWith(".gif")) {
                fileInfo.put("type", "image");
            } else if (lowerName.endsWith(".xls") || lowerName.endsWith(".xlsx")) {
                fileInfo.put("type", "excel");
            } else if (lowerName.endsWith(".doc") || lowerName.endsWith(".docx")) {
                fileInfo.put("type", "word");
            } else {
                fileInfo.put("type", "file"); // 其他通用文件
            }

            // 4. 获取原有 JSON
            String oldJson = workOrder.getSiteDataUrlListStr();
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, String>> fileList;

            if (oldJson == null || oldJson.isEmpty()) {
                fileList = new ArrayList<>();
            } else {
                // 解析原有 JSON
                fileList = objectMapper.readValue(oldJson, new TypeReference<List<Map<String, String>>>() {});
            }

            // 5. 添加新文件
            fileList.add(fileInfo);

            // 6. 转成 JSON 字符串
            String newJson = objectMapper.writeValueAsString(fileList);

            // 7. 更新数据库
            WorkOrderDO update = new WorkOrderDO();
            update.setId(workOrder.getId());
            update.setSiteDataUrlListStr(newJson);
            update.setFileDesc(newDesc);
            update.setAfterIndexValue(reqVO.getAfterIndexValue());
            workOrderMapper.updateById(update);

            // 8. 返回结果
            UploadWorkOrderFileRespVO respVO = new UploadWorkOrderFileRespVO();
            respVO.setWorkOrderId(workOrder.getId());
            respVO.setFileUrl(fileUrl);
            respVO.setFileName(file.getOriginalFilename());

            return respVO;

        } catch (Exception e) {
            log.error("上传工单资料失败", e);

            if (e instanceof ServiceException) {
                throw (ServiceException) e;
            }

            throw new ServiceException(500, "上传文件失败");
        }
    }

    @Override
    public int countByOrderNo(String orderNo) {

        // 1. 参数校验
        if (orderNo == null || orderNo.trim().isEmpty()) {
            return 0;
        }

        // 2. 查询数据库
        return workOrderMapper.countByOrderNo(orderNo);
    }
}
