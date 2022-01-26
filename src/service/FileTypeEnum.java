package service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Description: 压缩文件类型
 * @Date: 2019/1/22
 * @Auther:
 */
@AllArgsConstructor
@NoArgsConstructor
public enum FileTypeEnum {
    FILE_TYPE_ZIP("application/zip", ".zip"),
    FILE_TYPE_RAR("application/octet-stream", ".rar");
    public String type;
    public String fileStufix;

    public static String getFileStufix(String type) {
        for (FileTypeEnum orderTypeEnum : FileTypeEnum.values()) {
            if (orderTypeEnum.type.equals(type)) {
                return orderTypeEnum.fileStufix;
            }
        }
        return null;
    }
}
