package com.baidu.ueditor.hanlder;

import com.baidu.ueditor.configuration.AbstractUEditorProperties;
import com.baidu.ueditor.configuration.ListDirectoryState;
import com.baidu.ueditor.configuration.UEditorDirective;
import com.baidu.ueditor.configuration.UEditorProperties;
import com.baidu.ueditor.exception.UEditorException;
import com.baidu.ueditor.util.UEditorAssert;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class FileManagerHandler implements UEditorHandler {

    private final UEditorProperties properties;

    public FileManagerHandler(UEditorProperties properties) {
        this.properties = properties;
    }

    @Override
    public Object doAction(HttpServletRequest request, UEditorDirective directive) throws UEditorException {
        int start = UEditorAssert.parseStartError(request);
        AbstractUEditorProperties config = this.properties.getProperties(directive);
        String homeDir = properties.getHomeDir();
        String baseUrl = properties.getBaseUrl();
        String relativeDir = config.getDir();
        String targetDir = homeDir + relativeDir;
        File file = new File(targetDir);
        UEditorAssert.notExist(file);
        UEditorAssert.notDirectory(file);
        Collection<File> list = FileUtils.listFiles(file, config.getAllowFiles(), true);
        if (start < 0 || start > list.size()) {
            return new ListDirectoryState("SUCCESS");
        } else {
            int size = list.size() < config.getSize() ? list.size() : config.getSize();
            Object[] objects = Arrays.copyOfRange(list.toArray(), start, start + size);
            return new ListDirectoryState(
                    "SUCCESS",
                    Arrays.asList(objects).stream().map(e -> new ListDirectoryState.FileItem(
                            baseUrl + relativeDir + ((File) e).getName())
                    ).collect(Collectors.toList()),
                    start,
                    list.size());
        }
    }
}
