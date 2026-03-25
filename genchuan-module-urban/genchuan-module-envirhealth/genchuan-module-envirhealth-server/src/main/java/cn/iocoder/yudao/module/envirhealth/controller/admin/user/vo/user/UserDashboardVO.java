package cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.user;

import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "环境卫生管理 - 用户看板统计返回VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDashboardVO {

    // ========== 卡片数据 ==========
    @Schema(description = "总人员数", example = "120")
    private Long totalUserCount;

    @Schema(description = "在岗人数", example = "98")
    private Long onDutyCount;

    @Schema(description = "全勤人数", example = "85")
    private Long fullAttendanceCount;

    @Schema(description = "考核优秀人数", example = "45")
    private Long excellentAssessmentCount;

    @Schema(description = "待排班人数", example = "12")
    private Long pendingScheduleCount;

    // ========== 圆环图数据 ==========
    @Schema(description = "岗位类型占比（name=岗位名称,value=人员数量）")
    private List<PieItemVO> positionTypeDistribution;

    @Schema(description = "人员状态占比（name=人员状态,value=人员数量）")
    private List<PieItemVO> userStatusDistribution;

    @Schema(description = "所属班组分布占比（name=班组名称,value=人员数量）")
    private List<PieItemVO> teamDistribution;

    // ========== 基础柱状图数据 ==========
    @Schema(description = "不同班组人员数量对比（name=班组名称,value=人员数量）")
    private List<BarItemVO> userCountByTeam;

    @Schema(description = "不同岗位平均考核得分对比（name=岗位名称,value=平均得分）")
    private List<BarItemVO> avgAssessmentScoreByPosition;
}