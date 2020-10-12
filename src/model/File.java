package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:文件
 * @auther:cyf
 * @create:2020/10/10 12:00
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class File {
        /*
         文件名
          */
        private String fileName;
        /*
        文件类型
        */
        private String fileType;
        /*
        文件属性
         */
        private String fileProperty;
        /*
        起始盘块号
         */
        private Integer startBlockNum;
        /*
        文件长度为盘块
         */
        private Integer fileLength;
        /*
        文件内容
         */
        private String fileContent;
}
