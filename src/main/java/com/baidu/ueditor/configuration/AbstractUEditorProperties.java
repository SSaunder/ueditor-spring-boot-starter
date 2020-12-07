package com.baidu.ueditor.configuration;

import java.util.Map;

public abstract class AbstractUEditorProperties implements IUEditorProperties {
    /**
     * 执行动作名称
     */
    private UEditorDirective directive;

    public AbstractUEditorProperties(UEditorDirective directive) {
        this.directive = directive;
    }

    public UEditorDirective getDirective() {
        return this.directive;
    }

    /**
     * 获取配置
     * @return
     */
    public abstract Map<String, Object> getConfig();

    /**
     * 获取简单配置
     * @return
     */
    public abstract Map<String, Object> getSimpleConfig();

    @Override
    public String toString() {
        return "AbstractUEditorProperties{" +
                "directive=" + directive +
                '}';
    }
}
