package com.example.administrator.myapptextttttttt.beans;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/23
 * 描述:
 */

public class UmengMsgBean {

    @Override
    public String toString() {
        return "UmengMsgBean{" +
                "policy=" + policy +
                ", description='" + description + '\'' +
                ", production_mode=" + production_mode +
                ", appkey='" + appkey + '\'' +
                ", payload=" + payload +
                ", device_tokens='" + device_tokens + '\'' +
                ", type='" + type + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    /**
     * policy : {"expire_time":"2018-06-26 10:08:55"}
     * description : LIUXIAOWEI
     * production_mode : true
     * appkey : 5b2c4ce7f43e487250000092
     * payload : {"body":{"title":"TTTTTT","ticker":"TTTTTT","text":"123456qweqeqwe请问","after_open":"go_custom","custom":"zzzzzzzzzzzccccc ","play_vibrate":"true","play_lights":"false","play_sound":"true"},"display_type":"notification","extra":{"tttttt":"123456"}}
     * device_tokens : AkLgQqsE9YEm2hrq84x6oHzKI8uiM458HSSyIgqq9-NV
     * type : unicast
     * timestamp : 1529733737108
     */

    private PolicyBean policy;
    private String description;
    private boolean production_mode;
    private String appkey;
    private PayloadBean payload;
    private String device_tokens;
    private String type;
    private String timestamp;

    public PolicyBean getPolicy() {
        return policy;
    }

    public void setPolicy(PolicyBean policy) {
        this.policy = policy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isProduction_mode() {
        return production_mode;
    }

    public void setProduction_mode(boolean production_mode) {
        this.production_mode = production_mode;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public PayloadBean getPayload() {
        return payload;
    }

    public void setPayload(PayloadBean payload) {
        this.payload = payload;
    }

    public String getDevice_tokens() {
        return device_tokens;
    }

    public void setDevice_tokens(String device_tokens) {
        this.device_tokens = device_tokens;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public static class PolicyBean {
        /**
         * expire_time : 2018-06-26 10:08:55
         */

        private String expire_time;

        public String getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(String expire_time) {
            this.expire_time = expire_time;
        }
    }

    public static class PayloadBean {
        /**
         * body : {"title":"TTTTTT","ticker":"TTTTTT","text":"123456qweqeqwe请问","after_open":"go_custom","custom":"zzzzzzzzzzzccccc ","play_vibrate":"true","play_lights":"false","play_sound":"true"}
         * display_type : notification
         * extra : {"tttttt":"123456"}
         */

        private BodyBean body;
        private String display_type;
        private ExtraBean extra;

        public BodyBean getBody() {
            return body;
        }

        public void setBody(BodyBean body) {
            this.body = body;
        }

        public String getDisplay_type() {
            return display_type;
        }

        public void setDisplay_type(String display_type) {
            this.display_type = display_type;
        }

        public ExtraBean getExtra() {
            return extra;
        }

        public void setExtra(ExtraBean extra) {
            this.extra = extra;
        }

        public static class BodyBean {
            /**
             * title : TTTTTT
             * ticker : TTTTTT
             * text : 123456qweqeqwe请问
             * after_open : go_custom
             * custom : zzzzzzzzzzzccccc
             * play_vibrate : true
             * play_lights : false
             * play_sound : true
             */

            private String title;
            private String ticker;
            private String text;
            private String after_open;
            private String custom;
            private String play_vibrate;
            private String play_lights;
            private String play_sound;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTicker() {
                return ticker;
            }

            public void setTicker(String ticker) {
                this.ticker = ticker;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getAfter_open() {
                return after_open;
            }

            public void setAfter_open(String after_open) {
                this.after_open = after_open;
            }

            public String getCustom() {
                return custom;
            }

            public void setCustom(String custom) {
                this.custom = custom;
            }

            public String getPlay_vibrate() {
                return play_vibrate;
            }

            public void setPlay_vibrate(String play_vibrate) {
                this.play_vibrate = play_vibrate;
            }

            public String getPlay_lights() {
                return play_lights;
            }

            public void setPlay_lights(String play_lights) {
                this.play_lights = play_lights;
            }

            public String getPlay_sound() {
                return play_sound;
            }

            public void setPlay_sound(String play_sound) {
                this.play_sound = play_sound;
            }
        }

        public static class ExtraBean {
            /**
             * tttttt : 123456
             */

            private String tttttt;

            public String getTttttt() {
                return tttttt;
            }

            public void setTttttt(String tttttt) {
                this.tttttt = tttttt;
            }
        }
    }
}
