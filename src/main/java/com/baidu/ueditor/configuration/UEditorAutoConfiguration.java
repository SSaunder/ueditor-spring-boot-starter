package com.baidu.ueditor.configuration;

import com.baidu.ueditor.hanlder.ConfigHandler;
import com.baidu.ueditor.hanlder.FileManagerHandler;
import com.baidu.ueditor.hanlder.FileUploadHandler;
import com.baidu.ueditor.hanlder.ScrawlUploadHandler;
import com.baidu.ueditor.hanlder.UEditorContextFactory;
import com.baidu.ueditor.hanlder.UEditorHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties({UEditorProperties.class})
public class UEditorAutoConfiguration {

    private final UEditorProperties properties;

    public UEditorAutoConfiguration(UEditorProperties properties) {
        this.properties = properties;
    }

    @Bean
    public UEditorContextFactory uEditorContextFactory() {
        FileUploadHandler fileUploadHandler = new FileUploadHandler(properties);
        FileManagerHandler fileManagerHandler = new FileManagerHandler(properties);
        ScrawlUploadHandler scrawlUploadHandler = new ScrawlUploadHandler(properties);
        ConfigHandler configHandler = new ConfigHandler(properties);
        Map<UEditorDirective, UEditorHandler> maps = new HashMap() {{
            put(UEditorDirective.FILE_UPLOAD, fileUploadHandler);
            put(UEditorDirective.IMAGE_UPLOAD, fileUploadHandler);
            put(UEditorDirective.VIDEO_UPLOAD, fileUploadHandler);
            put(UEditorDirective.SCRAWL_UPLOAD, scrawlUploadHandler);
            put(UEditorDirective.FILE_MANAGER, fileManagerHandler);
            put(UEditorDirective.IMAGE_MANAGER, fileManagerHandler);
            put(UEditorDirective.CONFIG, configHandler);
        }};
        return new UEditorContextFactory(maps);
    }
}
