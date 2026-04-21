package cn.iocoder.yudao.module.chargepark.carservice.framework.utils;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants;
import cn.iocoder.yudao.module.inspectop.api.space.SpaceMonitorApi;
import cn.iocoder.yudao.module.inspectop.api.space.dto.SpaceMonitorRespDTO;
import cn.iocoder.yudao.module.stationresource.api.station.StationInfoApi;
import cn.iocoder.yudao.module.stationresource.api.station.dto.StationInfoRespDTO;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 跨模块外键校验工具 — 通过 Feign 验证 station_id / space_id / userId 真实有效。
 *
 * 约定:fail-closed。下游 RPC 抛异常时**不放行**,转抛 {@link ErrorCodeConstants#CROSS_MODULE_RPC_UNAVAILABLE}
 * 让调用方看到明确的业务错误而不是 500,避免写入脏数据。
 */
@Slf4j
@Component
public class CrossModuleValidator {

    @Resource private StationInfoApi stationInfoApi;
    @Resource private SpaceMonitorApi spaceMonitorApi;
    @Resource private AdminUserApi adminUserApi;

    /** 校验场站存在 */
    public void validateStationExists(Long stationId) {
        if (stationId == null) {
            return;
        }
        StationInfoRespDTO station;
        try {
            CommonResult<StationInfoRespDTO> r = stationInfoApi.getStation(stationId);
            station = r == null ? null : r.getData();
        } catch (ServiceException se) {
            throw se;
        } catch (Exception ex) {
            log.warn("[validateStationExists] stationresource RPC 异常 stationId={}", stationId, ex);
            throw exception(ErrorCodeConstants.CROSS_MODULE_RPC_UNAVAILABLE);
        }
        if (station == null) {
            throw exception(ErrorCodeConstants.STATION_NOT_EXISTS);
        }
    }

    /** 校验救援人员有效(存在 + 启用) */
    public void validateAdminUserActive(Long userId) {
        if (userId == null) {
            return;
        }
        AdminUserRespDTO user;
        try {
            user = adminUserApi.getUser(userId).getData();
        } catch (ServiceException se) {
            throw se;
        } catch (Exception ex) {
            log.warn("[validateAdminUserActive] system RPC 异常 userId={}", userId, ex);
            throw exception(ErrorCodeConstants.CROSS_MODULE_RPC_UNAVAILABLE);
        }
        if (user == null) {
            throw exception(ErrorCodeConstants.RESCUE_USER_NOT_EXISTS);
        }
        if (!CommonStatusEnum.isEnable(user.getStatus())) {
            throw exception(ErrorCodeConstants.RESCUE_USER_DISABLED);
        }
    }

    /** 校验车位存在 */
    public void validateSpaceExists(Long spaceId) {
        if (spaceId == null) {
            return;
        }
        SpaceMonitorRespDTO space;
        try {
            CommonResult<SpaceMonitorRespDTO> r = spaceMonitorApi.getLatestBySpaceId(spaceId);
            space = r == null ? null : r.getData();
        } catch (ServiceException se) {
            throw se;
        } catch (Exception ex) {
            log.warn("[validateSpaceExists] inspectop RPC 异常 spaceId={}", spaceId, ex);
            throw exception(ErrorCodeConstants.CROSS_MODULE_RPC_UNAVAILABLE);
        }
        if (space == null) {
            throw exception(ErrorCodeConstants.SPACE_NOT_EXISTS);
        }
    }

}
