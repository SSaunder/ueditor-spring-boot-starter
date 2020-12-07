package com.baidu.ueditor.configuration;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * UEditor配置属性
 */
@ConfigurationProperties("com.baidu.ueditor")
public class UEditorProperties implements InitializingBean {
    /**
     * 文件名格式化
     */
    private final static String FILE_NAME_FORMAT = "/ueditor/upload/%s/";
    /**
     * 默认上传文件名称
     */
    private final static String FILED_NAME = "uploadFile";
    /**
     * 默认文件访问路径
     */
    private final static String URL_PREFIX = "";
    /**
     * 图片插入浮动方式
     */
    private final static String INSERT_ALIGN = "none";
    /**
     * 显示文件数量
     */
    private final static int SHOW_FILE_SIZE = 20;
    /**
     * 存储根目录
     */
    private String homeDir;
    /**
     * 默认文件访问路径
     */
    private String baseUrl;

    private Map<UEditorDirective, AbstractUEditorProperties> maps = new HashMap<>();
    private UEditorProperties.ImageUpload imageUpload;
    private UEditorProperties.ScrawlUpload scrawlUpload;
    private UEditorProperties.SnapScreenUpload snapScreenUpload;
    private UEditorProperties.CatcherUpload catcherUpload;
    private UEditorProperties.VideoUpload videoUpload;
    private UEditorProperties.FileUpload fileUpload;
    private UEditorProperties.ImageManager imageManager;
    private UEditorProperties.FileManager fileManager;

    public UEditorProperties() {
        this.imageUpload = new UEditorProperties.ImageUpload();
        this.scrawlUpload = new UEditorProperties.ScrawlUpload();
        this.snapScreenUpload = new UEditorProperties.SnapScreenUpload();
        this.catcherUpload = new UEditorProperties.CatcherUpload();
        this.videoUpload = new UEditorProperties.VideoUpload();
        this.fileUpload = new UEditorProperties.FileUpload();
        this.imageManager = new UEditorProperties.ImageManager();
        this.fileManager = new UEditorProperties.FileManager();
        maps.put(this.imageUpload.getDirective(), this.imageUpload);
        maps.put(this.scrawlUpload.getDirective(), this.scrawlUpload);
        maps.put(this.snapScreenUpload.getDirective(), this.snapScreenUpload);
        maps.put(this.catcherUpload.getDirective(), this.catcherUpload);
        maps.put(this.videoUpload.getDirective(), this.videoUpload);
        maps.put(this.fileUpload.getDirective(), this.fileUpload);
        maps.put(this.imageManager.getDirective(), this.imageManager);
        maps.put(this.fileManager.getDirective(), this.fileManager);
    }

    public String getHomeDir() {
        return homeDir;
    }

    public void setHomeDir(String homeDir) {
        this.homeDir = homeDir;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public ImageUpload getImageUpload() {
        return imageUpload;
    }

    public void setImageUpload(ImageUpload imageUpload) {
        this.imageUpload = imageUpload;
    }

    public ScrawlUpload getScrawlUpload() {
        return scrawlUpload;
    }

    public void setScrawlUpload(ScrawlUpload scrawlUpload) {
        this.scrawlUpload = scrawlUpload;
    }

    public SnapScreenUpload getSnapScreenUpload() {
        return snapScreenUpload;
    }

    public void setSnapScreenUpload(SnapScreenUpload snapScreenUpload) {
        this.snapScreenUpload = snapScreenUpload;
    }

    public CatcherUpload getCatcherUpload() {
        return catcherUpload;
    }

    public void setCatcherUpload(CatcherUpload catcherUpload) {
        this.catcherUpload = catcherUpload;
    }

    public VideoUpload getVideoUpload() {
        return videoUpload;
    }

    public void setVideoUpload(VideoUpload videoUpload) {
        this.videoUpload = videoUpload;
    }

    public FileUpload getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(FileUpload fileUpload) {
        this.fileUpload = fileUpload;
    }

    public ImageManager getImageManager() {
        return imageManager;
    }

    public void setImageManager(ImageManager imageManager) {
        this.imageManager = imageManager;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.homeDir, "storage home directory is not null");
        Assert.notNull(this.baseUrl, "baseUrl is not null");
    }

    public AbstractUEditorProperties getProperties(UEditorDirective directive) {
        return this.maps.get(directive);
    }

    /**
     * 获取总配置
     * @return
     */
    public Map<String, Object> getConfig() {
        Map<String, Object> result = new HashMap<>();
        result.putAll(this.imageUpload.getConfig());
        result.putAll(this.scrawlUpload.getConfig());
        result.putAll(this.snapScreenUpload.getConfig());
        result.putAll(this.catcherUpload.getConfig());
        result.putAll(this.videoUpload.getConfig());
        result.putAll(this.fileUpload.getConfig());
        result.putAll(this.imageManager.getConfig());
        result.putAll(this.fileManager.getConfig());
        return result;
    }

    /**
     * 图片上传配置属性
     */
    public static class ImageUpload extends AbstractUEditorProperties {
        public ImageUpload() { super(UEditorDirective.IMAGE_UPLOAD); }

        /**
         * 提交文件表单名称
         */
        private String fieldName = FILED_NAME;
        /**
         * 上传大小限制，单位B
         */
        private long maxSize = 2048000L;
        /**
         * 启用上传压缩
         */
        private boolean enableCompress = true;
        /**
         * 压缩边长
         */
        private int compressBorder = 1600;
        /**
         * 图片插入对其方式
         */
        private String insertAlign = INSERT_ALIGN;
        /**
         * 文件名格式化
         */
        private String pathFormat = String.format(FILE_NAME_FORMAT, "image");
        /**
         * 图片文件访问路径
         */
        private String urlPrefix = URL_PREFIX;
        /**
         * 允许上传的文件类型
         */
        private String[] allowFiles = {".png", ".jpg", ".jpeg", ".gif", ".bmp"};

        @Override
        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        @Override
        public long getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(long maxSize) {
            this.maxSize = maxSize;
        }

        public boolean isEnableCompress() {
            return enableCompress;
        }

        public void setEnableCompress(boolean enableCompress) {
            this.enableCompress = enableCompress;
        }

        public int getCompressBorder() {
            return compressBorder;
        }

        public void setCompressBorder(int compressBorder) {
            this.compressBorder = compressBorder;
        }

        public String getInsertAlign() {
            return insertAlign;
        }

        public void setInsertAlign(String insertAlign) {
            this.insertAlign = insertAlign;
        }

        public String getPathFormat() {
            return pathFormat;
        }

        public void setPathFormat(String pathFormat) {
            this.pathFormat = pathFormat;
        }

        @Override
        public String getUrlPrefix() {
            return urlPrefix;
        }

        public void setUrlPrefix(String urlPrefix) {
            this.urlPrefix = urlPrefix;
        }

        @Override
        public String[] getAllowFiles() {
            return allowFiles;
        }

        public void setAllowFiles(String[] allowFiles) {
            this.allowFiles = allowFiles;
        }

        @Override
        public String getFilenameFormat() {
            return this.pathFormat;
        }

        @Override
        public Map<String, Object> getConfig() {
            return new HashMap<String, Object>() {{
                put("imageActionName", getDirective().getDirective());
                put("imageFieldName", getFieldName());
                put("imageMaxSize", getMaxSize());
                put("imageAllowFiles", getAllowFiles());
                put("imageCompressEnable", isEnableCompress());
                put("imageCompressBorder", getCompressBorder());
                put("imageInsertAlign", getInsertAlign());
                put("imageUrlPrefix", getUrlPrefix());
                put("imagePathFormat", getPathFormat());
            }};
        }

        @Override
        public Map<String, Object> getSimpleConfig() {
            return new HashMap<String, Object>() {{
                put("isBase64", "true");
                put("maxSize", getMaxSize());
                put("allowFiles", allowFiles);
                put("fieldName", getFieldName());
            }};
        }

        @Override
        public String toString() {
            return "ImageUpload{" +
                    "fieldName='" + fieldName + '\'' +
                    ", maxSize=" + maxSize +
                    ", enableCompress=" + enableCompress +
                    ", compressBorder=" + compressBorder +
                    ", insertAlign='" + insertAlign + '\'' +
                    ", pathFormat='" + pathFormat + '\'' +
                    ", urlPrefix='" + urlPrefix + '\'' +
                    ", allowFiles=" + Arrays.toString(allowFiles) +
                    "} " + super.toString();
        }
    }
    /**
     * 涂鸦图片上传配置
     */
    public static class ScrawlUpload extends AbstractUEditorProperties {

        /**
         * 提交文件表单名称
         */
        private String fieldName = FILED_NAME;
        /**
         * 文件名格式化
         */
        private String pathFormat = String.format(FILE_NAME_FORMAT, "scrawl");
        /**
         * 上传大小限制，单位B
         */
        private long maxSize = 2048000L;
        /**
         * 图片文件访问路径
         */
        private String urlPrefix = URL_PREFIX;
        /**
         * 图片插入对其方式
         */
        private String insertAlign = INSERT_ALIGN;

        public ScrawlUpload() {
            super(UEditorDirective.SCRAWL_UPLOAD);
        }

        @Override
        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getPathFormat() {
            return pathFormat;
        }

        public void setPathFormat(String pathFormat) {
            this.pathFormat = pathFormat;
        }

        @Override
        public long getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(long maxSize) {
            this.maxSize = maxSize;
        }

        @Override
        public String getUrlPrefix() {
            return urlPrefix;
        }

        public void setUrlPrefix(String urlPrefix) {
            this.urlPrefix = urlPrefix;
        }

        public String getInsertAlign() {
            return insertAlign;
        }

        public void setInsertAlign(String insertAlign) {
            this.insertAlign = insertAlign;
        }

        @Override
        public String getFilename() {
            return "scrawl";
        }

        @Override
        public String getFilenameFormat() {
            return this.pathFormat;
        }

        @Override
        public Map<String, Object> getConfig() {
            return new HashMap<String, Object>() {{
                put("scrawlActionName", getDirective().getDirective());
                put("scrawlFieldName", getFieldName());
                put("scrawlPathFormat", getPathFormat());
                put("scrawlUrlPrefix", getUrlPrefix());
                put("scrawlMaxSize", getMaxSize());
                put("scrawlInsertAlign", getInsertAlign());
            }};
        }

        @Override
        public Map<String, Object> getSimpleConfig() {
            return new HashMap<String, Object>() {{
                put("isBase64", "true");
                put("maxSize", getMaxSize());
                put("filedName", getFieldName());
                put("filename", "scrawl");
            }};
        }

        @Override
        public String toString() {
            return "ScrawlUpload{" +
                    "fieldName='" + fieldName + '\'' +
                    ", pathFormat='" + pathFormat + '\'' +
                    ", maxSize=" + maxSize +
                    ", urlPrefix='" + urlPrefix + '\'' +
                    ", insertAlign='" + insertAlign + '\'' +
                    "} " + super.toString();
        }
    }
    /**
     * 截图配置
     */
    public static class SnapScreenUpload extends AbstractUEditorProperties {

        /**
         * 文件名格式化
         */
        private String pathFormat = String.format(FILE_NAME_FORMAT, "snapscreen");
        /**
         * 图片文件访问路径
         */
        private String urlPrefix = URL_PREFIX;
        /**
         * 图片插入对其方式
         */
        private String insertAlign = INSERT_ALIGN;

        public SnapScreenUpload() {
            super(UEditorDirective.SNAP_SCREEN_UPLOAD);
        }

        public String getInsertAlign() {
            return insertAlign;
        }

        public void setInsertAlign(String insertAlign) {
            this.insertAlign = insertAlign;
        }

        public String getPathFormat() {
            return pathFormat;
        }

        public void setPathFormat(String pathFormat) {
            this.pathFormat = pathFormat;
        }

        @Override
        public String getUrlPrefix() {
            return urlPrefix;
        }

        @Override
        public String getFilename() {
            return null;
        }

        @Override
        public String getFieldName() {
            return null;
        }

        @Override
        public String[] getAllowFiles() {
            return new String[0];
        }

        @Override
        public long getMaxSize() {
            return -1;
        }

        public void setUrlPrefix(String urlPrefix) {
            this.urlPrefix = urlPrefix;
        }

        @Override
        public Map<String, Object> getConfig() {
            return new HashMap<String, Object>() {{
                put("snapscreenActionName", getDirective().getDirective());
                put("snapscreenPathFormat", getPathFormat());
                put("snapscreenUrlPrefix", getUrlPrefix());
                put("snapscreenInsertAlign", getInsertAlign());
            }};
        }

        @Override
        public Map<String, Object> getSimpleConfig() {
            return null;
        }

        @Override
        public boolean isBase64() {
            return false;
        }

        @Override
        public String getFilenameFormat() {
            return pathFormat;
        }

        @Override
        public String toString() {
            return "SnapScreenUpload{" +
                    "pathFormat='" + pathFormat + '\'' +
                    ", urlPrefix='" + urlPrefix + '\'' +
                    ", insertAlign='" + insertAlign + '\'' +
                    "} " + super.toString();
        }
    }
    /**
     * 图片抓取配置
     */
    public static class CatcherUpload extends AbstractUEditorProperties {

        /**
         * 图片抓取域名
         */
        private String[] localDomain = {"127.0.0.1", "localhost", "img.baidu.com"};
        /**
         * 提交文件表单名称
         */
        private String fieldName = FILED_NAME;
        /**
         * 上传大小限制，单位B
         */
        private long maxSize = 2048000L;
        /**
         * 文件名格式化
         */
        private String pathFormat = String.format(FILE_NAME_FORMAT, "catcher");
        /**
         * 图片文件访问路径
         */
        private String urlPrefix = URL_PREFIX;
        /**
         * 允许上传的文件类型
         */
        private String[] allowFiles = {".png", ".jpg", ".jpeg", ".gif", ".bmp"};

        public CatcherUpload() {
            super(UEditorDirective.CATCHER_IMAGE);
        }

        public String[] getLocalDomain() {
            return localDomain;
        }

        public void setLocalDomain(String[] localDomain) {
            this.localDomain = localDomain;
        }

        @Override
        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        @Override
        public long getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(long maxSize) {
            this.maxSize = maxSize;
        }

        public String getPathFormat() {
            return pathFormat;
        }

        public void setPathFormat(String pathFormat) {
            this.pathFormat = pathFormat;
        }

        @Override
        public String getUrlPrefix() {
            return urlPrefix;
        }

        public void setUrlPrefix(String urlPrefix) {
            this.urlPrefix = urlPrefix;
        }

        @Override
        public String[] getAllowFiles() {
            return allowFiles;
        }

        public void setAllowFiles(String[] allowFiles) {
            this.allowFiles = allowFiles;
        }

        @Override
        public String getFilenameFormat() {
            return pathFormat;
        }

        @Override
        public String[] getFilter() {
            return localDomain;
        }

        @Override
        public String getFilename() {
            return "remote";
        }

        @Override
        public Map<String, Object> getConfig() {
            return new HashMap<String, Object>() {{
                put("catcherLocalDomain", getLocalDomain());
                put("catcherActionName", getDirective().getDirective());
                put("catcherFieldName", getFieldName());
                put("catcherPathFormat", getPathFormat());
                put("catcherUrlPrefix", getUrlPrefix());
                put("catcherMaxSize", getMaxSize());
                put("catcherAllowFiles", getAllowFiles());
            }};
        }

        @Override
        public Map<String, Object> getSimpleConfig() {
            return new HashMap<String, Object>() {{
                put("filename", "remote");
                put("filter", localDomain);
                put("maxSize", getMaxSize());
                put("allowFiles", allowFiles);
                put("fieldName", getFieldName());
            }};
        }

        @Override
        public String toString() {
            return "CatcherUpload{" +
                    "localDomain=" + Arrays.toString(localDomain) +
                    ", fieldName='" + fieldName + '\'' +
                    ", maxSize=" + maxSize +
                    ", pathFormat='" + pathFormat + '\'' +
                    ", urlPrefix='" + urlPrefix + '\'' +
                    ", allowFiles=" + Arrays.toString(allowFiles) +
                    "} " + super.toString();
        }
    }
    /**
     * 视频上传配置
     */
    public static class VideoUpload extends AbstractUEditorProperties {

        /**
         * 提交表单名称
         */
        private String fieldName = FILED_NAME;
        /**
         * 文件名格式化
         */
        private String pathFormat = String.format(FILE_NAME_FORMAT, "video");
        /**
         * 上传大小限制，单位B
         */
        private long maxSize = 102400000L;
        /**
         * 图片文件访问路径
         */
        private String urlPrefix = URL_PREFIX;
        /**
         * 允许上传的文件
         */
        private String[] allowFiles = {
                ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
                ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid"
        };

        public VideoUpload() {
            super(UEditorDirective.VIDEO_UPLOAD);
        }

        @Override
        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getPathFormat() {
            return pathFormat;
        }

        public void setPathFormat(String pathFormat) {
            this.pathFormat = pathFormat;
        }

        @Override
        public long getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(long maxSize) {
            this.maxSize = maxSize;
        }

        @Override
        public String getUrlPrefix() {
            return urlPrefix;
        }

        public void setUrlPrefix(String urlPrefix) {
            this.urlPrefix = urlPrefix;
        }

        @Override
        public String[] getAllowFiles() {
            return allowFiles;
        }

        public void setAllowFiles(String[] allowFiles) {
            this.allowFiles = allowFiles;
        }

        @Override
        public String getFilenameFormat() {
            return pathFormat;
        }

        @Override
        public Map<String, Object> getConfig() {
            return new HashMap<String, Object>() {{
                put("videoActionName", getDirective().getDirective());
                put("videoFieldName", getFieldName());
                put("videoPathFormat", getPathFormat());
                put("videoUrlPrefix", getUrlPrefix());
                put("videoMaxSize", getMaxSize());
                put("videoAllowFiles", getAllowFiles());
            }};
        }

        @Override
        public Map<String, Object> getSimpleConfig() {
            return new HashMap<String, Object>() {{
                put("maxSize", getMaxSize());
                put("allowFiles", allowFiles);
                put("fieldName", getFieldName());
            }};
        }

        @Override
        public String toString() {
            return "VideoUpload{" +
                    "fieldName='" + fieldName + '\'' +
                    ", pathFormat='" + pathFormat + '\'' +
                    ", maxSize=" + maxSize +
                    ", urlPrefix='" + urlPrefix + '\'' +
                    ", allowFiles=" + Arrays.toString(allowFiles) +
                    "} " + super.toString();
        }
    }
    /**
     * 上传文件配置
     */
    public static class FileUpload extends AbstractUEditorProperties  {

        /**
         * 提交文件表单名称
         */
        private String fieldName = FILED_NAME;
        /**
         * 上传大小限制，单位B
         */
        private long maxSize = 51200000L;
        /**
         * 文件名格式化
         */
        private String pathFormat = String.format(FILE_NAME_FORMAT, "file");
        /**
         * 图片文件访问路径
         */
        private String urlPrefix = URL_PREFIX;
        /**
         * 允许上传的文件
         */
        private String[] allowFiles = {
                ".png", ".jpg", ".jpeg", ".gif", ".bmp",
                ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
                ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid",
                ".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso",
                ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml"
        };

        public FileUpload() {
            super(UEditorDirective.FILE_UPLOAD);
        }

        @Override
        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        @Override
        public long getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(long maxSize) {
            this.maxSize = maxSize;
        }

        public String getPathFormat() {
            return pathFormat;
        }

        public void setPathFormat(String pathFormat) {
            this.pathFormat = pathFormat;
        }

        @Override
        public String getUrlPrefix() {
            return urlPrefix;
        }

        public void setUrlPrefix(String urlPrefix) {
            this.urlPrefix = urlPrefix;
        }

        @Override
        public String[] getAllowFiles() {
            return allowFiles;
        }

        public void setAllowFiles(String[] allowFiles) {
            this.allowFiles = allowFiles;
        }

        @Override
        public String getFilenameFormat() {
            return pathFormat;
        }

        @Override
        public Map<String, Object> getConfig() {
            return new HashMap<String, Object>() {{
                put("fileActionName", getDirective().getDirective());
                put("fileFieldName", getFieldName());
                put("filePathFormat", getPathFormat());
                put("fileUrlPrefix", getUrlPrefix());
                put("fileMaxSize", getMaxSize());
                put("fileAllowFiles", getAllowFiles());
            }};
        }

        @Override
        public Map<String, Object> getSimpleConfig() {
            return new HashMap<String, Object>() {{
                put("isBase64", "false");
                put("maxSize", getMaxSize());
                put("allowFiles", allowFiles);
                put("fieldName", getFieldName());
            }};
        }

        @Override
        public String toString() {
            return "FileUpload{" +
                    "fieldName='" + fieldName + '\'' +
                    ", maxSize=" + maxSize +
                    ", pathFormat='" + pathFormat + '\'' +
                    ", urlPrefix='" + urlPrefix + '\'' +
                    ", allowFiles=" + Arrays.toString(allowFiles) +
                    "} " + super.toString();
        }
    }
    /**
     * 图片管理
     */
    public static class ImageManager extends AbstractUEditorProperties  {
        /**
         * 列出文件的目录
         */
        private String dir = "/ueditor/upload/image/";
        /**
         * 显示文件的数量
         */
        private int count = SHOW_FILE_SIZE;
        /**
         * 图片访问路径前缀
         */
        private String urlPrefix = URL_PREFIX;
        /**
         * 图片插入对其方式
         */
        private String insertAlign = INSERT_ALIGN;
        /**
         * 允许上传的文件类型
         */
        private String[] allowFiles = {"png", "jpg", "jpeg", "gif", "bmp"};

        public ImageManager() {
            super(UEditorDirective.IMAGE_MANAGER);
        }

        @Override
        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getUrlPrefix() {
            return urlPrefix;
        }

        public void setUrlPrefix(String urlPrefix) {
            this.urlPrefix = urlPrefix;
        }

        public String getInsertAlign() {
            return insertAlign;
        }

        public void setInsertAlign(String insertAlign) {
            this.insertAlign = insertAlign;
        }

        @Override
        public String[] getAllowFiles() {
            return allowFiles;
        }

        public void setAllowFiles(String[] allowFiles) {
            this.allowFiles = allowFiles;
        }

        @Override
        public int getSize() {
            return count;
        }

        @Override
        public Map<String, Object> getConfig() {
            return new HashMap<String, Object>() {{
                put("imageManagerActionName", getDirective().getDirective());
                put("imageManagerListPath", getDir());
                put("imageManagerUrlPrefix", getUrlPrefix());
                put("imageManagerListSize", getCount());
                put("imageManagerInsertAlign", getInsertAlign());
                put("imageManagerAllowFiles", getAllowFiles());
            }};
        }

        @Override
        public Map<String, Object> getSimpleConfig() {
            return new HashMap<String, Object>() {{
                put("allowFiles", getAllowFiles());
                put("dir", getDir());
                put("count", getCount());
            }};
        }

        @Override
        public String toString() {
            return "ImageManager{" +
                    "dir='" + dir + '\'' +
                    ", count=" + count +
                    ", urlPrefix='" + urlPrefix + '\'' +
                    ", insertAlign='" + insertAlign + '\'' +
                    ", allowFiles=" + Arrays.toString(allowFiles) +
                    "} " + super.toString();
        }
    }
    /**
     * 文件管理
     */
    public static class FileManager extends AbstractUEditorProperties  {

        /**
         * 列出文件的目录
         */
        private String dir = "/ueditor/upload/file/";
        /**
         * 显示文件的数量
         */
        private int count = SHOW_FILE_SIZE;
        /**
         * 图片访问路径前缀
         */
        private String urlPrefix = URL_PREFIX;
        /**
         * 允许上传的文件类型
         */
        private String[] allowFiles = new String[] {
                ".png", ".jpg", ".jpeg", ".gif", ".bmp",
                ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
                ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid",
                ".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso",
                ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml"
        };

        public FileManager() {
            super(UEditorDirective.FILE_MANAGER);
        }

        @Override
        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getUrlPrefix() {
            return urlPrefix;
        }

        public void setUrlPrefix(String urlPrefix) {
            this.urlPrefix = urlPrefix;
        }

        @Override
        public String[] getAllowFiles() {
            return allowFiles;
        }

        public void setAllowFiles(String[] allowFiles) {
            this.allowFiles = allowFiles;
        }

        @Override
        public int getSize() {
            return count;
        }

        @Override
        public Map<String, Object> getConfig() {
            return new HashMap<String, Object>() {{
                put("fileManagerActionName", getDirective().getDirective());
                put("fileManagerListPath", getDir());
                put("fileManagerUrlPrefix", getUrlPrefix());
                put("fileManagerListSize", getCount());
                put("fileManagerAllowFiles", getAllowFiles());
            }};
        }

        @Override
        public Map<String, Object> getSimpleConfig() {
            return new HashMap<String, Object>() {{
                put("allowFiles", getAllowFiles());
                put("dir", getDir());
                put("count", getCount());
            }};
        }

        @Override
        public String toString() {
            return "FileManager{" +
                    "dir='" + dir + '\'' +
                    ", count=" + count +
                    ", urlPrefix='" + urlPrefix + '\'' +
                    ", allowFiles=" + Arrays.toString(allowFiles) +
                    "} " + super.toString();
        }
    }

}