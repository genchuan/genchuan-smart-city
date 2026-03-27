package cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.transferreserve;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferreserve.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.GarbageTransferDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferReserveDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferReserveDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.GarbageTransferMapper;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.TransferReserveMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.garbagetransfer.TransferReserveCodeGenerator;
import cn.iocoder.yudao.module.envirhealth.framework.util.json.JsonArrayUtils;
import cn.iocoder.yudao.module.envirhealth.framework.util.json.StringSplitUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.TRANSFER_RESERVE_NOT_EXISTS;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.TRANSFER_RESERVE_SORT_TYPE_INVALID;

/**
 * 进站预约 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TransferReserveServiceImpl implements TransferReserveService {

    @Resource
    private TransferReserveMapper transferReserveMapper;

    @Resource
    private GarbageTransferMapper garbageTransferMapper;

    @Resource
    private TransferReserveCodeGenerator codeGenerator;

    @Override
    @Transactional(rollbackFor = Exception.class) // 新增事务注解
    public Long createTransferReserve(TransferReserveSaveReqVO createReqVO) {
        // 1. 构建预约DO
        TransferReserveDO transferReserve = BeanUtils.toBean(createReqVO, TransferReserveDO.class);
        transferReserve.setId(null);
        String reserveId = codeGenerator.generateReserveId();
        transferReserve.setReserveId(reserveId);

        // 2. 插入预约记录
        transferReserveMapper.insert(transferReserve);

        // 3. 同步更新垃圾转运站的reserve_id（仅progress_status=车辆待进站）
        syncAddReserveIdToTransfer(createReqVO.getTransferId(), transferReserve.getId());

        // 返回主键
        return transferReserve.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class) // 新增事务注解
    public void deleteTransferReserve(Long id) {
        // 1. 校验预约存在
        TransferReserveDO reserve = validateTransferReserveExists(id);
        String transferId = reserve.getTransferId();
        Long reserveId = reserve.getId();

        // 2. 先同步移除垃圾转运站的关联ID
        syncRemoveReserveIdFromTransfer(transferId, reserveId);

        // 3. 再删除预约记录
        transferReserveMapper.deleteById(id);
    }

    @Override
    public void updateTransferReserve(TransferReserveSaveReqVO updateReqVO) {
        // 校验存在
        validateTransferReserveExists(updateReqVO.getId());
        // 更新
        TransferReserveDO updateObj = BeanUtils.toBean(updateReqVO, TransferReserveDO.class);
        transferReserveMapper.updateById(updateObj);
    }

    private TransferReserveDO validateTransferReserveExists(Long id) {
        TransferReserveDO reserve = transferReserveMapper.selectById(id);
        if (reserve == null) {
            throw exception(TRANSFER_RESERVE_NOT_EXISTS);
        }
        return reserve;
    }

    @Override
    public TransferReserveDO getTransferReserve(Long id) {
        return transferReserveMapper.selectById(id);
    }

    @Override
    public PageResult<TransferReserveDO> getTransferReservePage(TransferReservePageReqVO pageReqVO) {
        return transferReserveMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<TransferReserveDetailDO> getTransferReserveDetailPage(TransferReservePageReqVO pageReqVO) {

        // ====== 【工具类清洗：自动处理 [1,2,3] / [] / 空 / null】 ======
        String idStr = pageReqVO.getIdStr();
        if (idStr != null) {
            // 1. 工具类解析
            List<Long> ids = StringSplitUtils.splitToLongList(idStr);

            // 2. 如果解析后是空 → 直接返回空列表
            if (ids.isEmpty()) {
                return PageResult.empty();
            }

            // 3. 有值 → 拼接成 1,2,3
            String jsonStr = ids.stream()
                    .map(String::valueOf)
                    .reduce((a, b) -> a + "," + b)
                    .orElse(null);
            pageReqVO.setIdStr(jsonStr);
        }

        Long total = transferReserveMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<TransferReserveDetailDO> list = transferReserveMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchSortTransferReserve(TransferReserveBatchSortReqVO reqVO) {
        List<Long> ids = reqVO.getIds();
        if (ids == null || ids.isEmpty()) {
            return;
        }
        // 1. 查询这些预约
        List<TransferReserveDO> list = transferReserveMapper.selectBatchIds(ids);
        if (list.isEmpty()) {
            return;
        }
        // 只对“待排序”的做排序
        list.removeIf(item -> !"待排序".equals(item.getReserveStatus()));
        if (list.isEmpty()) {
            return;
        }
        // 2. 根据排序规则在内存中排序
        switch (reqVO.getSortType()) {
            case "time":
                // 按预计进站时间从早到晚
                list.sort(Comparator.comparing(TransferReserveDO::getExpectedTime));
                break;
            case "type":
                // 先按垃圾品类，再按预计时间
                list.sort(Comparator
                        .comparing(TransferReserveDO::getGarbageTypeId, Comparator.nullsLast(String::compareTo))
                        .thenComparing(TransferReserveDO::getExpectedTime, Comparator.nullsLast(LocalDateTime::compareTo)));
                break;
            default:
                throw exception(TRANSFER_RESERVE_SORT_TYPE_INVALID);
        }

        // 当前登录用户，作为处理人/更新人
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        String username = loginUser != null ? String.valueOf(loginUser.getId()) : null;

        // 3. 生成排序号，从 已排序的最大值 开始
        Integer maxSortNo = transferReserveMapper.selectMaxSortNo();
        int sortNo = (maxSortNo != null ? maxSortNo : 0) + 1;

        LocalDateTime now = LocalDateTime.now();
        for (TransferReserveDO reserve : list) {
            TransferReserveDO update = new TransferReserveDO();
            update.setId(reserve.getId());
            update.setSortNo(sortNo++);
            update.setReserveStatus("已排序");
            update.setHandleBy(username);    // 记录处理人
            update.setUpdater(username);     // 审计字段
            update.setAbnormalCreateTime(now);

            transferReserveMapper.updateById(update);
        }
    }

    @Override
    public TransferReserveDashboardRespVO getDashboardStats() {
        TransferReserveDashboardRespVO resp = new TransferReserveDashboardRespVO();

        // 1. 卡片数据 - 直接从Mapper查询
        resp.setPendingVehicles(transferReserveMapper.selectPendingVehicles());
        resp.setSortedVehicles(transferReserveMapper.selectSortedVehicles());
        resp.setTodayTotalReserves(transferReserveMapper.selectTodayTotalReserves());

        // 2. 圆环图数据 - 垃圾品类分布
        resp.setGarbageTypeDistribution(transferReserveMapper.selectGarbageTypePie());

        // 3. 圆环图数据 - 区域分布
        resp.setAreaDistribution(transferReserveMapper.selectAreaPie());

        // 4. 柱状图数据 - 不同时段预约车辆数量对比
        resp.setReserveCountByTimeSlot(transferReserveMapper.selectReserveCountByTimeSlot());

        return resp;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sortTransferReserve(TransferReserveSortReqVO reqVO) {
        // 1. 根据前端传递的预约ID，查询对应的预约记录
        Long reserveId = reqVO.getId();
        TransferReserveDO reserve = transferReserveMapper.selectById(reserveId);
        if (reserve == null) {
            throw exception(TRANSFER_RESERVE_NOT_EXISTS); // “预约不存在”
        }
        // 校验：仅允许对“待排序”的预约排号
        if (!"待排序".equals(reserve.getReserveStatus())) {
            throw exception("仅支持对「待排序」状态的预约进行排号");
        }

        // 2. 从预约记录中提取转运站ID
        String transferId = reserve.getTransferId();

        // 3. 查询该转运站下已排序的最大序号
        Integer maxSortNo = transferReserveMapper.selectMaxSortNoByTransferId(transferId);
        int nextSortNo = (maxSortNo == null ? 1 : maxSortNo + 1); // 无已排序则从1开始

        // 4. 补充登录人、时间等审计字段
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        String username = loginUser != null ? String.valueOf(loginUser.getId()) : null;
        LocalDateTime now = LocalDateTime.now();

        // 5. 更新该预约的排号和状态
        TransferReserveDO updateObj = new TransferReserveDO();
        updateObj.setId(reserveId);
        updateObj.setSortNo(nextSortNo);
        updateObj.setReserveStatus("已排序"); // 标记为已排序
        updateObj.setUpdater(username);      // 更新人
        updateObj.setAbnormalCreateTime(now); // 排序时间

        transferReserveMapper.updateById(updateObj);
    }

    // ========== 私有方法：同步添加reserve_id到垃圾转运站 ==========
    private void syncAddReserveIdToTransfer(String transferId, Long reserveId) {
        if (transferId == null || reserveId == null) {
            return;
        }

        // 1. 直接查询 reserve_id 字符串
        String oldReserveIds = garbageTransferMapper.selectReserveIdByTransferId(transferId);
        if (oldReserveIds == null) {
            return;
        }
        // 2. 拼接去重
        String newReserveIds = JsonArrayUtils.addElement(oldReserveIds, reserveId);
        // 3. 更新
        garbageTransferMapper.updateReserveIdsByTransferId(transferId, newReserveIds);
    }

    // ========== 私有方法：同步移除reserve_id从垃圾转运站 ==========
    private void syncRemoveReserveIdFromTransfer(String transferId, Long reserveId) {
        if (transferId == null || reserveId == null) {
            return;
        }
        // 1. 查询转运站当前的reserve_id
        String oldReserveIds = garbageTransferMapper.selectReserveIdByTransferId(transferId);
        if (oldReserveIds == null) {
            return;
        }
        // 2. 移除指定reserve_id
        String newReserveIds = JsonArrayUtils.removeElement(oldReserveIds, reserveId);
        // 3. 更新转运站的reserve_id
        garbageTransferMapper.updateReserveIdsByTransferId(transferId, newReserveIds);
    }
}