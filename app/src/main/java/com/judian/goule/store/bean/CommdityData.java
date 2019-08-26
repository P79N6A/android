package com.judian.goule.store.bean;


import java.io.Serializable;
import java.util.List;

/**
 * 淘宝的商品详情
 */
public class CommdityData implements Serializable{
    /**
     * code : 200
     * msg : 获取详情成功
     * result : {"images":["https://img.alicdn.com/imgextra/i2/64265083/TB22AgFjYsrBKNjSZFpXXcXhFXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i3/64265083/TB2r4rdyKGSBuNjSspbXXciipXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i2/64265083/TB2FhzdyKGSBuNjSspbXXciipXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i1/64265083/TB24cnGewjN8KJjSZFkXXaboXXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i1/64265083/TB25FCTmxHI8KJjy1zbXXaxdpXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i2/64265083/TB22WSGdQfb_uJkSmFPXXcrCFXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i1/64265083/TB2.anUewjN8KJjSZFCXXb3GpXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i2/64265083/TB2yc45qviSBuNkSnhJXXbDcpXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i2/64265083/TB2RCUCdH1YBuNjSszhXXcUsFXa_!!64265083.png","https://img.alicdn.com/imgextra/i2/64265083/TB2fXNxXPgy_uJjSZTEXXcYkFXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i4/64265083/TB2lbL5etLO8KJjSZFxXXaGEVXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i3/64265083/TB2mAW3yFGWBuNjy0FbXXb4sXXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i4/64265083/TB2FZiIqyOYBuNjSsD4XXbSkFXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i1/64265083/TB2uiYYyKySBuNjy1zdXXXPxFXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i1/64265083/TB2j5_ViRsmBKNjSZFFXXcT9VXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i1/64265083/TB2QC6OyH1YBuNjSszhXXcUsFXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i3/64265083/TB2IkO3yFGWBuNjy0FbXXb4sXXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i3/64265083/TB2sgSCmtrJ8KJjSspaXXXuKpXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i1/64265083/TB2IvJHdL5TBuNjSspcXXbnGFXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i2/64265083/TB2MNASk7fb_uJjSsrbXXb6bVXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i3/64265083/TB2fRIXevjM8KJjSZFNXXbQjFXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i2/64265083/TB2RfPOetHO8KJjSZFLXXaTqVXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i1/64265083/TB2hIvEmx6I8KJjy0FgXXXXzVXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i2/64265083/TB2L6WMmwLD8KJjSszeXXaGRpXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i3/64265083/TB234tPmBfH8KJjy1XbXXbLdXXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i1/64265083/TB2Pe2qmwDD8KJjy0FdXXcjvXXa_!!64265083.jpg","https://img.alicdn.com/imgextra/i3/64265083/TB2kEyUdQfb_uJkHFCcXXXagFXa_!!64265083.jpg"]}
     */

    private int code;
    private String msg;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable{
        private List<String> images;

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
