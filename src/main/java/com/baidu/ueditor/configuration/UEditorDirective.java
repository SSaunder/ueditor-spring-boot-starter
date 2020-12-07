package com.baidu.ueditor.configuration;

import java.util.Arrays;
import java.util.Optional;

/**
 * UEditor执行的动作
 */
public enum UEditorDirective {
    /**
     * 获取配置
     */
    CONFIG("config"),
    /**
     * 上传图片
     */
    IMAGE_UPLOAD("uploadimage"),
    /**
     * 上传涂鸦图片
     */
    SCRAWL_UPLOAD("uploadscrawl"),
    /**
     * 上传截屏图片
     */
    SNAP_SCREEN_UPLOAD("uploadimage"),
    /**
     * 远程抓取图片
     */
    CATCHER_IMAGE("catchimage"),
    /**
     * 上传视频
     */
    VIDEO_UPLOAD("uploadvideo"),
    /**
     * 上传文件
     */
    FILE_UPLOAD("uploadfile"),
    /**
     * 图片管理
     */
    IMAGE_MANAGER("listimage"),
    /**
     * 文件管理
     */
    FILE_MANAGER("listfile");

    private String directive;

    UEditorDirective(String directive) {
        this.directive = directive;
    }

    public String getDirective() {
        return directive;
    }

    public static Optional<UEditorDirective> directiveOf(String directive) {
        return Arrays.asList(values()).stream().filter(e -> e.getDirective().equals(directive)).findAny();
    }

    @Override
    public String toString() {
        return "UEditorDirective{" +
                "directive='" + directive + '\'' +
                "} " + super.toString();
    }
}
