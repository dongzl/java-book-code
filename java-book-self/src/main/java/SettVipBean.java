import java.util.Date;

/**
 * @author dongzonglei
 * @description
 * @date 2020/3/2 下午5:29
 */
public class SettVipBean {
    
    private String pin;
    
    private Long VipOrderId;
    
    private Byte vipServiceType;
    
    private Integer vipSourceType;
    
    private Date serviceStartTime;
    
    private Date serviceEndTime;
    
    private String serviceUuid;
    
    public SettVipBean(String pin, Long vipOrderId, Byte vipServiceType, Integer vipSourceType, Date serviceStartTime, Date serviceEndTime, String serviceUuid) {
        this.pin = pin;
        VipOrderId = vipOrderId;
        this.vipServiceType = vipServiceType;
        this.vipSourceType = vipSourceType;
        this.serviceStartTime = serviceStartTime;
        this.serviceEndTime = serviceEndTime;
        this.serviceUuid = serviceUuid;
    }
    
    public String getPin() {
        return pin;
    }
    
    public void setPin(String pin) {
        this.pin = pin;
    }
    
    public Date getServiceStartTime() {
        return serviceStartTime;
    }
    
    public void setServiceStartTime(Date serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }
    
    public Date getServiceEndTime() {
        return serviceEndTime;
    }
    
    public void setServiceEndTime(Date serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }
    
    public Long getVipOrderId() {
        return VipOrderId;
    }
    
    public void setVipOrderId(Long vipOrderId) {
        VipOrderId = vipOrderId;
    }
    
    public Integer getVipSourceType() {
        return vipSourceType;
    }
    
    public void setVipSourceType(Integer vipSourceType) {
        this.vipSourceType = vipSourceType;
    }
    
    public Byte getVipServiceType() {
        return vipServiceType;
    }
    
    public void setVipServiceType(Byte vipServiceType) {
        this.vipServiceType = vipServiceType;
    }
    
    public String getServiceUuid() {
        return serviceUuid;
    }
    
    public void setServiceUuid(String serviceUuid) {
        this.serviceUuid = serviceUuid;
    }
}
