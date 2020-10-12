package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:磁盘 按块分配
 * @auther:cyf
 * @create:2020/10/10 12:16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Disk {
    private String diskNum;
}
