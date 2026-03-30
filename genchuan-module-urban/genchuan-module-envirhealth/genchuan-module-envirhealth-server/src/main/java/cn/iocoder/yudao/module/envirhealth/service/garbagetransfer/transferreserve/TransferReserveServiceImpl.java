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
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        transferReserve.setReserveStatus("待排序");

        // 2. 插入预约记录
        transferReserveMapper.insert(transferReserve);

        // 3. 同步更新垃圾转运站的reserve_id（仅progress_status=车辆待进站）
        Long newReserveId = transferReserve.getId(); // 拿到新增预约的主键

        // 根据 transferId 复制一条 garbage_transfer 记录
        syncAddNewGarbageTransferByReserve(createReqVO.getTransferId(), newReserveId);

        // 返回主键
        return transferReserve.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class) // 新增事务注解
    public void deleteTransferReserve(Long id) {
        TransferReserveDO reserve = validateTransferReserveExists(id);
        String transferId = reserve.getTransferId();
        Long reserveId = reserve.getId();

        // 直接删除对应预约的 garbage_transfer 记录
        syncDeleteTransferByReserveId(transferId, reserveId);

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
            case "EXPECTED_TIME":
                // 按预计进站时间从早到晚
                list.sort(Comparator.comparing(TransferReserveDO::getExpectedTime));
                break;
            case "type":
            case "GARBAGE_TYPE_TIME":
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
        LocalDateTime now = LocalDateTime.now();

        // 3. 按转运站分组，每个转运站独立生成排序号
        Map<String, List<TransferReserveDO>> transferGroupMap = list.stream()
                .collect(Collectors.groupingBy(TransferReserveDO::getTransferId));

        // 遍历每个转运站的预约列表
        for (Map.Entry<String, List<TransferReserveDO>> entry : transferGroupMap.entrySet()) {
            String transferId = entry.getKey();
            List<TransferReserveDO> reserveList = entry.getValue();

            // 查询【当前转运站】下已排序的最大序号
            Integer maxSortNo = transferReserveMapper.selectMaxSortNoByTransferId(transferId);
            int sortNo = (maxSortNo == null ? 1 : maxSortNo + 1);

            // 给当前转运站的预约依次设置排序号
            for (TransferReserveDO reserve : reserveList) {
                TransferReserveDO update = new TransferReserveDO();
                update.setId(reserve.getId());
                update.setSortNo(sortNo++);
                update.setReserveStatus("已排序");
                update.setUpdater(username);
                update.setAbnormalCreateTime(now);

                transferReserveMapper.updateById(update);
            }
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

    /**
     * 新增预约时 → 新增一条 garbage_transfer 记录
     * 1. 从现有 transferId 复制基础数据
     * 2. reserve_id 设为当前预约ID
     * 3. 状态改为 车辆待进站
     */
    private void syncAddNewGarbageTransferByReserve(String transferId, Long reserveId) {
        if (transferId == null || reserveId == null) {
            return;
        }

        // 1. 查询该转运站的任意一条原始数据（用来复制）
        GarbageTransferDO original = garbageTransferMapper.selectOne(
                new LambdaQueryWrapper<GarbageTransferDO>()
                        .eq(GarbageTransferDO::getTransferId, transferId)
                        .last("LIMIT 1")
        );
        if (original == null) {
            return;
        }

        // 2. 复制基础信息
        GarbageTransferDO newTransfer = BeanUtils.toBean(original, GarbageTransferDO.class);
        newTransfer.setId(null); // 清空ID，自动生成新主键

        // 3. 设置关键数据
        newTransfer.setReserveId(reserveId);        // 绑定本次预约ID
        newTransfer.setProgressStatus("车辆待进站"); // 固定状态

        // 4. 插入新记录
        garbageTransferMapper.insert(newTransfer);
    }

    private void syncDeleteTransferByReserveId(String transferId, Long reserveId) {
        if (transferId == null || reserveId == null) {
            return;
        }
        garbageTransferMapper.delete(
                new LambdaQueryWrapper<GarbageTransferDO>()
                        .eq(GarbageTransferDO::getTransferId, transferId)
                        .eq(GarbageTransferDO::getReserveId, reserveId)
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmTransferReserve(TransferReserveConfirmReqVO reqVO) {
        // 1. 校验预约存在
        Long reserveId = reqVO.getId();
        TransferReserveDO reserve = validateTransferReserveExists(reserveId);

        // 2. 校验预约状态为「已排序」
        if (!"已排序".equals(reserve.getReserveStatus())) {
            throw exception("仅支持对「已排序」状态的预约执行确认进站操作");
        }

        // 3. 更新预约状态为「已进站」
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        String username = loginUser != null ? String.valueOf(loginUser.getId()) : null;
        LocalDateTime now = LocalDateTime.now();

        TransferReserveDO updateObj = new TransferReserveDO();
        updateObj.setId(reserveId);
        updateObj.setReserveStatus("已进站");
        updateObj.setUpdater(username);
        updateObj.setUpdateTime(now);
        transferReserveMapper.updateById(updateObj);

        // 4. 直接删除 garbage_transfer 中 对应这条预约 的数据
        String transferId = reserve.getTransferId();
        syncDeleteTransferByReserveId(transferId, reserveId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelTransferReserve(Long id) {
        // 1. 校验预约存在
        TransferReserveDO reserve = validateTransferReserveExists(id);

        // ====================== 状态校验：只有 待排序 / 已排序 可以取消 ======================
        String status = reserve.getReserveStatus();
        if (!"待排序".equals(status) && !"已排序".equals(status)) {
            throw exception("仅允许对【待排序】或【已排序】状态的预约进行取消操作");
        }

        // 2. 修改预约状态为 已取消
        TransferReserveDO updateObj = new TransferReserveDO();
        updateObj.setId(id);
        updateObj.setReserveStatus("已取消");

        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        String username = loginUser != null ? String.valueOf(loginUser.getId()) : null;
        updateObj.setUpdater(username);
        updateObj.setUpdateTime(LocalDateTime.now());

        transferReserveMapper.updateById(updateObj);

        // 3. 同步删除 garbage_transfer 对应的那条数据
        syncDeleteTransferByReserveId(reserve.getTransferId(), id);
    }
}