package com.hqyj.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("pay_info")
public class PayInfo {
    @TableId(value = "pay_id",type = IdType.AUTO)
    private Integer payId;
    @TableField(value = "pay_type")
    private String payType;
    private String payBody;
    private String payMoney;

}
