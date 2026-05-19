package cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.facemgmt.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 人脸信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FaceMgmtPageReqVO extends PageParam {

    @Schema(description = "人员姓名，支持模糊查询")
    private String userName;

    @Schema(description = "手机号，支持模糊查询")
    private String phone;

    @Schema(description = "所属企业，支持模糊查询")
    private String company;

    @Schema(description = "通行区域")
    private String accessArea;

    @Schema(description = "权限状态（已授权/未授权/已过期），关联芋道字典表：face_mgmt_auth_status")
    private String authStatus;

}